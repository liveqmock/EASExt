/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.kingdee.query.ContractRenewalQuery;
import com.creditease.eas.warn.service.BlackListInfoService;
import com.creditease.eas.warn.service.ContractRenewalService;
import com.creditease.eas.warn.service.OrgAdminChService;
/**
 * created at 2012-12-26 下午04:01:37 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class ContractRenewalServiceImpl implements ContractRenewalService{
	private static final Logger logger = Logger.getLogger(ContractRenewalServiceImpl.class);
	@Autowired
	private WaringDetailMapper waringdetailMapper;

	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	
	@Autowired
	private BlackListInfoService blackListInfoServiceImpl;
	
	/**
	 * 合同发送给部门负责人、抄送给综合管理和BP
	 */
	public void sendOrgTest(String orgnumber,Set<Map<String,Object>> list){
		if(null == list || 1 > list.size()){
			return;
		}
		try {
    	   if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 
           MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			String logoPath = pr.getProperty("logoPath");
//					String orgnumber = entry.getKey().toString();
			Map<String,Object> org = ContractRenewalQuery.getDeptPerson(orgnumber);
			//部门负责人是否为空
			if(null==org){
				return;
			}
			if(org.get("CFEMAIL")!=null || org.get("FEMAIL")!=null){
				String fnumber = org.get("DEPTCODE").toString();
				List<Map<String, Object>> homlist = orgAdminChServiceImpl.getHomologous(fnumber);
				String names = "";
				String mails = "";
				if(homlist!=null && homlist.size()>0){
					for (int i = 0; i < homlist.size(); i++) {
						Map<String, Object> homologous = homlist.get(i);
						mails +=","+ homologous.get("PERSONMAIL").toString();
						names +=","+ homologous.get("PNAME").toString();
					}
					//测试用	一下六行代码显示打印真正的抄送人及其邮箱
					String[] realMails = mails.split(",");
					String[] realNames = names.split(",");
					System.out.println("************************真正的抄送人及其邮箱****************************");
					for(int i = 0; i < realMails.length; i++){
						System.out.println(realNames[i]+"---->"+realMails[i]);
					}
					System.out.println("***********************************************************************");
					names = names.replaceFirst(",", "");
					mails = mails.replaceFirst(",", "");
					String[] toAddress = null;
					String[] addcopys = pr.getProperty("ccs").split(",");
//							Set<Map<String,Object>> list = (Set<Map<String,Object>>) entry.getValue();
			        String htmlContent = ContractHtmlContent.htmlContent44(list);
					String name = org.get("NAME").toString(); 
					String cfemail = (org.get("CFEMAIL")==null)?"":org.get("CFEMAIL").toString(); 
					if("".equals(cfemail)){
						cfemail = (org.get("FEMAIL")==null)?"":org.get("FEMAIL").toString(); 
					}
					
					System.out.println(name+"   "+fnumber+"  "+names);
					 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),1,3);//是否个贷  06GDXSF
					 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
				    	  System.err.println(black1.getPname()+": 全部不收");
				    	  return;
				     }
					 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),4,1);//是否个贷  06GDXSF
					 if(black2!=null && ( black2.getWarntype()==4 || black2.getWarntype()==1 ) && black2.getModeid()==1){//全部或合同
						cfemail = black2.getRaplacemail();
						name = black2.getRaplacename();
						System.err.println(black2.getWarntype()+"代收:"+orgnumber+":"+name+":"+cfemail);
					}
					toAddress = pr.getProperty("toAddress").split(",");
					String title = "员工劳动合同期满提醒通知(此邮件为系统发送，请勿回复){测试}";
					boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress,title, htmlContent, logoPath,addcopys);
					
					String reletivePath = FileReadUtil.writeFile(name,htmlContent,"合同预警","合同即将到期人员名单");
					 WaringDetail waringDetail = new WaringDetail();
					 waringDetail.setTypeid(4);//合同到期预警
					 waringDetail.setWayid(2);//发送方式:发送邮件
					 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
					 waringDetail.setTheme(title);//
					 waringDetail.setContentaddress(reletivePath);//文件路径，
					 waringDetail.setFilename("合同到期人员名单.txt");//文件名称：先写死
					 waringDetail.setEmail(cfemail);//收件人邮箱
					 waringDetail.setReceiver(name);//接收人：张三
					 if(va){
						 waringDetail.setIssend(1);//成功了
						 waringDetail.setExt1("成功");
					 }else{
						 waringDetail.setIssend(0);//失败了
						 waringDetail.setExt1("失败");
					 }
					 waringDetail.setSendcount(1);//默认是一次
					 waringDetail.setCreatime(new Date());//创建日期
					 waringDetail.setCopyids(mails);
					 waringDetail.setCopyperson(names);
					 waringdetailMapper.insert(waringDetail);
					}
					
		       }
		}catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 合同发送给部门负责人、抄送给综合管理和BP   定时任务
	 */
	public void sendOrgTestQuartz(String orgnumber,Set<Map<String,Object>> list){
		if(null == list || 1 > list.size()){
			return;
		}
		try {
    	   if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 
           MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			String logoPath = pr.getProperty("logoPath");
//					String orgnumber = entry.getKey().toString();
			
			Map<String,Object> org = ContractRenewalQuery.getDeptPerson(orgnumber);//获取部门负责人的基本信息
			//部门负责人是否为空
			if(null==org){
				return;
			}
			if(org.get("CFEMAIL")!=null || org.get("FEMAIL")!=null){
				String fnumber = org.get("DEPTCODE").toString();
				List<Map<String, Object>> homlist = orgAdminChServiceImpl.getHomologous(fnumber);
				String names = "";
				String mails = "";
				if(homlist!=null && homlist.size()>0){
					for (int i = 0; i < homlist.size(); i++) {
						Map<String, Object> homologous = homlist.get(i);
						mails +=","+ homologous.get("PERSONMAIL").toString();
						names +=","+ homologous.get("PNAME").toString();
					}
					names = names.replaceFirst(",", "");
					mails = mails.replaceFirst(",", "");
					String[] addcopys = mails.split(",");
					
//							Set<Map<String,Object>> list = (Set<Map<String,Object>>) entry.getValue();
			        String htmlContent = ContractHtmlContent.htmlContent44(list);
					String name = org.get("NAME").toString(); 
					String cfemail = (org.get("CFEMAIL")==null)?"":org.get("CFEMAIL").toString(); 
					if("".equals(cfemail)){
						cfemail = (org.get("FEMAIL")==null)?"":org.get("FEMAIL").toString(); 
					}
					
					String[] toAddress = {cfemail};
					BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),1,3);//是否个贷  06GDXSF
					 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
				    	  System.err.println(black1.getPname()+": 全部不收");
				    	  return;
				     }
					 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),4,1);//是否个贷  06GDXSF
					 if(black2!=null && ( black2.getWarntype()==4 || black2.getWarntype()==1 ) && black2.getModeid()==1){//全部或合同
						cfemail = black2.getRaplacemail();
						name = black2.getRaplacename();
						System.err.println(black2.getWarntype()+"代收:"+orgnumber+":"+name+":"+cfemail);
					}
					String title = "员工劳动合同期满提醒通知(此邮件为系统发送，请勿回复)";
					boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress,title, htmlContent, logoPath,addcopys);
					
					String reletivePath = FileReadUtil.writeFile(name,htmlContent,"合同预警","合同即将到期人员名单");
					 WaringDetail waringDetail = new WaringDetail();
					 waringDetail.setTypeid(4);//合同到期预警
					 waringDetail.setWayid(2);//发送方式:发送邮件
					 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
					 waringDetail.setTheme(title);//
					 waringDetail.setContentaddress(reletivePath);//文件路径，
					 waringDetail.setFilename("合同到期人员名单.txt");//文件名称：先写死
					 waringDetail.setEmail(cfemail);//收件人邮箱
					 waringDetail.setReceiver(name);//接收人：张三
					
					 if(va){
						 waringDetail.setIssend(1);//成功了
						 waringDetail.setExt1("成功");
					 }else{
						 waringDetail.setIssend(0);//失败了
						 waringDetail.setExt1("失败");
					 }
					 waringDetail.setSendcount(1);//默认是一次
					 waringDetail.setCreatime(new Date());//创建日期
					 waringDetail.setCopyids(mails);
					 waringDetail.setCopyperson(names);
					 waringdetailMapper.insert(waringDetail);
					}
					
		       }
		}catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 描述：根据接口人邮箱获取接口人姓名
	 * 2013-4-22 下午04:40:56 by xjw
	 * @version
	 * @param mail
	 * @return
	 */
	public String getNameBymail(String mail){
		 String name = waringdetailMapper.getPortByMail(mail);
		 return name;
	}
	
	/**
	 * 
	 * 描述：根据根部门对应接口人编码归类对应的人员信息集合、并以相应的接口人邮箱作为主键 (优化后)
	 * 思路分析：
	 * 将系统中的人员对应关系和系统中的合同将到期的人员进行组合，最后拼接出一个map
	 * 得到某个接口人对应的邮箱，应该收到哪些邮件信息
	 * 2013-3-8 上午10:19:24 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */

	public Map<String, List<Map<String,Object>>> getSectorInfo() throws Exception{
		List<Map<String,Object>> list = ContractRenewalQuery.queryContract(); //链接eas02
		
		List<Map<String,Object>> list2 = waringdetailMapper.getAllPort(); //连接easext
		
	//	List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
		
		Map<String, List<Map<String,Object>>> datamap = new HashMap<String, List<Map<String,Object>>>();
		
		Map<String, Object> mapmail = new HashMap<String,Object>();
		System.out.println("续签人员："+list.size());
		if(list2!=null){
			for (int j = 0; j < list2.size(); j++) {
				Map<String,Object> mp2 = list2.get(j);
				if(mp2.get("FNUMBER")!=null){
					String fnumber = Utils.strTrim(mp2.get("FNUMBER"));
					String mail = Utils.strTrim(mp2.get("FPERSONEMAIL"));
					mapmail.put(fnumber,mail);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> mp = list.get(i);
			if(mp.get("LONGNUMBER")!=null){
				String[] strs = mp.get("LONGNUMBER").toString().split("!");
				if(strs!=null && strs.length>1){
					/*
					 * 2014-7-11 lining 组合数据逻辑：从合同预警信息人员的最末级部门开始逐层向上匹配部门HR接口人，
					 * 如果可以匹配到则将其添加到匹配到的HR接口人邮箱（）对应的合同预警数据集合中，如果匹配不到则认为其没有所属的HR接口人，无法将其合同提醒信息发送到接口人
					 * 当可以匹配到多个级别的接口人时，将向其匹配到的每个HR接口人添加其合同预警信息
					 */
					//从预警合同信息人的最末级部门逐层向上判断
					for(int j = strs.length; j > 1; j--){
						//判断是否存在该部门HR接口人
						if(mapmail.containsKey(strs[j-1])){
							System.out.println("对应接口人邮箱："+mapmail.get(strs[j-1]));
							//以合同预警收件箱为key值判断是否已经在合同预警收件箱与预警合同数据对应map集合中
							if(datamap.containsKey(mapmail.get(strs[j-1]))){
								datamap.get(mapmail.get(strs[j-1])).add(mp);
							}else{
								List<Map<String,Object>> contractDatas = new ArrayList<Map<String,Object>>();
								contractDatas.add(mp);
								datamap.put((String) mapmail.get(strs[j-1]), contractDatas);
							}
						}
					}
					
					/* 2014-7-11 lining 注释掉旧的功能代码，旧功能代码只支持向二、三级部门HR接口人发送合同提醒邮件
					if(mapmail.containsKey(strs[1])){
						System.out.println("编码1："+strs[1]);
						String email = Utils.strTrim(mapmail.get(strs[1]));
						if(datamap.get(email)!=null && datamap.containsKey(email)){
							datamap.get(email).add(mp);
						}else{
							List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
							data.add(mp);
							datamap.put(email, data);
						}
	//					System.err.println(email);
					}
					
					if(mapmail.containsKey(strs[2])){
						String email = Utils.strTrim(mapmail.get(strs[2]));
						System.out.println("编码2："+strs[2]+" "+email);
						if(datamap.get(email)!=null && datamap.containsKey(email)){
	//						data.add(mp);
	//						datamap.put(email, data);
							datamap.get(email).add(mp);
						}else{
							List<Map<String,Object>> data =new ArrayList<Map<String,Object>>();
							data.add(mp);
							datamap.put(email, data);
						}
	//					System.err.println(email);
	//					datamap.put(email, data);
					}
					System.out.println("-----------------------");*/
					
				}
			}
		}
		return datamap;
	}
	/**
	 * 发送给对应接口人 测试
	 */
	public void sendSectorInfo(String email,String secname,List<Map<String,Object>> list){
		if(null == list || 1 > list.size()){
			return;
		}
		if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
			 try {
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//系统休眠60秒
		 }
		 TongJi.tongJiCount ++;
		 String title = "员工劳动合同期满提醒通知(此邮件为系统发送，请勿回复){测试}";
		 MailSenderInfo ms = new MailSenderInfo();
		 Properties pr = ms.getProperties();
		 String logoPath = pr.getProperty("logoPath");
		 
		 String fromAddress = pr.getProperty("fromAddress");
		 
		String htmlContentForContract = ContractHtmlContent.htmlContent(list);
		 String reletivePath = null;
		try {
			reletivePath = FileReadUtil.writeFile("shuoliu",htmlContentForContract,"合同预警","合同即将到期人员名单");
		} catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		 System.err.println("路径："+reletivePath);
		 String[] toAddress = pr.getProperty("toAddress").split(",");
		 System.out.println(secname+" : "+email);
		 //发送邮件给多个人:测试
		 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContentForContract,logoPath);//测试邮箱
		 
		 WaringDetail waringDetail = new WaringDetail();
		 waringDetail.setTypeid(4);//合同到期预警
		 waringDetail.setWayid(2);//发送方式:发送邮件
		 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
		 waringDetail.setTheme(title);//
		 waringDetail.setContentaddress(reletivePath);//文件路径，
		 waringDetail.setFilename("合同到期人员名单.txt");//文件名称：先写死
		 waringDetail.setEmail(email);//收件人邮箱
		 waringDetail.setReceiver(secname);//接收人：张三
		 if(va){
			 waringDetail.setIssend(1);//成功了
			 waringDetail.setExt1("成功");
		 }else{
			 waringDetail.setIssend(0);//失败了
			 waringDetail.setExt1("失败");
		 }
		 waringDetail.setSendcount(1);//默认是一次
		 waringDetail.setCreatime(new Date());//创建日期
		 waringdetailMapper.insert(waringDetail);	
	}
	/**
	 * 发送给对应接口人  定时任务 正式环境
	 */
	public void sendSectorInfoQuartz(String email,String secname,List<Map<String,Object>> list){
		if(null == list || 1 > list.size()){
			return;
		}
		if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
			 try {
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				TongJi.tongJiExceptionCount ++;
				logger.error(e.getMessage());
				e.printStackTrace();
			}//系统休眠60秒
		 }
		 TongJi.tongJiCount ++;
		 String title = "员工劳动合同期满提醒通知(此邮件为系统发送，请勿回复)";
		 MailSenderInfo ms = new MailSenderInfo();
		 Properties pr = ms.getProperties();
		 String logoPath = pr.getProperty("logoPath");
		 
		 String fromAddress = pr.getProperty("fromAddress");
		 
		String htmlContentForContract = ContractHtmlContent.htmlContent(list);
		 String reletivePath = null;
		try {
			reletivePath = FileReadUtil.writeFile(secname,htmlContentForContract,"合同预警","合同即将到期人员名单");
		} catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		 System.err.println("路径："+reletivePath);
		 String[] toAddress = email.split(";");
		 
		 System.out.println(secname+" : "+email);
		 //发送邮件给多个人
		 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContentForContract,logoPath);
		 
		 WaringDetail waringDetail = new WaringDetail();
		 waringDetail.setTypeid(4);//合同到期预警
		 waringDetail.setWayid(2);//发送方式:发送邮件
		 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
		 waringDetail.setTheme(title);//
		 waringDetail.setContentaddress(reletivePath);//文件路径，
		 waringDetail.setFilename("合同到期人员名单.txt");//文件名称：先写死
		 waringDetail.setEmail(email);//收件人邮箱
		 waringDetail.setReceiver(secname);//接收人：张三
		 if(va){
			 waringDetail.setIssend(1);//成功了
			 waringDetail.setExt1("成功");
		 }else{
			 waringDetail.setIssend(0);//失败了
			 waringDetail.setExt1("失败");
		 }
		 waringDetail.setSendcount(1);//默认是一次
		 waringDetail.setCreatime(new Date());//创建日期
		 waringdetailMapper.insert(waringDetail);	
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContractRenewalService contractRenewalService = (ContractRenewalService)app.getBean("contractRenewalServiceImpl");
//		contractRenewalService.sendMailInfos();
//		contractRenewalService.sendRenewalTest();
		
//		contractRenewalService.sendRenewal();
		
		System.out.println(contractRenewalService.getNameBymail("nannanxu@creditease.cn"));
	}

}
