/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.StringUtil;
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
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.YearOfWorkService;
import com.creditease.eas.warn.vo.PersonData2;
/**
 * @VariablesServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class YearOfWorkServiceImpl implements YearOfWorkService{
	private static final Logger logger = Logger.getLogger(YearOfWorkServiceImpl.class);
	
	@Autowired
	private WaringDetailMapper waringdetailMapper;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	
	@Autowired
	private BlackListInfoService blackListInfoServiceImpl;
	
	@Override
	public boolean queryPersonInfo() throws Exception{
		//sendMails();
		return true;
	}
	/**
	 * 测试
	 */
	public void sendHigherTest(String pnumber,Set<PersonData2> list){
		try{
			if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
		  
	     BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,1,3);//是否个贷  06GDXSF
		 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
	    	  System.err.println(black1.getPname()+": 全部不收");
	    	  return;
	     }
//	      if(1==1){
//				 System.err.println("编码: "+" : "+pnumber);
//				 return;
//		  }
	      List<PersonData2> lt1 = new ArrayList<PersonData2>(); 
		  List<PersonData2> lt2 = new ArrayList<PersonData2>();
	      for(PersonData2 mp : list){
	   	   if("zj".equals(mp.getTmp())){
	   		   lt1.add(mp);
	   	   }else{
	   		   	BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,3,2);
		   		if(black2!=null && ( black2.getWarntype()==3 || black2.getWarntype()==1 ) && black2.getModeid()==2){
		   			   System.err.println("间下不收: "+black2.getPname());
		   		   }else{
		   			   lt2.add(mp);
		   		   }
		   	   }
	      }
	      
	      Map<String,Object> org = ContractRenewalQuery.getYearPerson(pnumber);
	      if(null == org){
	    	  return;
	      }
		   if(org.get("CFEMAIL")!=null || org.get("FEMAIL")!=null){
				String name = org.get("NAME").toString(); 
				System.out.println(pnumber+" : "+name);
				String cfemail = (org.get("CFEMAIL")==null)?"":org.get("CFEMAIL").toString(); 
				if("".equals(cfemail)){
					cfemail = (org.get("FEMAIL")==null)?"":org.get("FEMAIL").toString(); 
				}
				if(lt1.size()<1 && lt2.size()<1){
					return;
				}
					String htmlContent = ContractHtmlContent.htmlContent33(lt1,lt2);
		            MailSenderInfo ms = new MailSenderInfo();
					Properties pr = ms.getProperties();
					String fromAddress = pr.getProperty("fromAddress");
					String[] toAddress = pr.getProperty("toAddress").split(",");
					String logoPath = pr.getProperty("logoPath");
					String title =  "员工入职周年纪念日提醒通知(此邮件为系统发送，请勿回复){测试}";
					boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath);

					 String reletivePath = FileReadUtil.writeFile("司龄",htmlContent,"司龄预警","员工入职周年人员名单");
					
				     WaringDetail waringDetail = new WaringDetail();
					 waringDetail.setTypeid(3);//预警类别
					 waringDetail.setWayid(2);//发送方式
					 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
					 waringDetail.setSendtime(new Date());//发送时间
					 waringDetail.setTheme(title);//
					 waringDetail.setContentaddress(reletivePath);//文件路径，
					 waringDetail.setFilename("员工入职周年纪念日.txt");//文件名称：先写死
					 if(va){
						waringDetail.setIssend(1);//成功了
						waringDetail.setExt1("成功");
					 }else{
						waringDetail.setIssend(0);//失败了
						waringDetail.setExt1("失败");
					 }
					 waringDetail.setSendcount(1);//默认是一次
					 waringDetail.setCreatime(new Date());//创建日期
					 waringDetail.setEmail(pr.getProperty("toAddress").toString());//收件人邮箱
					 waringDetail.setReceiver(name);//接收人：张三
					 waringdetailMapper.insert(waringDetail);
		      }
		}catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	/**
	 * 定时任务  直接上级、隔级上级
	 */
	public void sendHigher(String pnumber,Set<PersonData2> list){
		try{
			if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
	      // entry.getKey() 返回与此项对应的键
	      // entry.getValue() 返回与此项对应的值
	      List<PersonData2> lt1 = new ArrayList<PersonData2>();
	      List<PersonData2> lt2 = new ArrayList<PersonData2>();
	      for(PersonData2 mp : list){
	   	   if("zj".equals(mp.getTmp())){
	   		   lt1.add(mp);
	   	   }else{
		   		BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,3,2);
		   		if(black2!=null && ( black2.getWarntype()==3 || black2.getWarntype()==1 ) && black2.getModeid()==2){
	   			   System.err.println("间下不收: "+black2.getPname());
	   		   }else{
	   			   lt2.add(mp);
	   		   }
	   	   }
	      }
	      
	      Map<String,Object> org = ContractRenewalQuery.getYearPerson(pnumber);
	      if(null == org){
	    	  return;
	      }
		   if(org.get("CFEMAIL")!=null || org.get("FEMAIL")!=null){
				String name = org.get("NAME").toString(); 
				System.out.println(pnumber+" : "+name);
				String cfemail = (org.get("CFEMAIL")==null)?"":org.get("CFEMAIL").toString(); 
				if("".equals(cfemail)){
					cfemail = (org.get("FEMAIL")==null)?"":org.get("FEMAIL").toString(); 
				}
				if(lt1.size()<1 && lt2.size()<1){
					return;
				}
				String htmlContent = ContractHtmlContent.htmlContent33(lt1,lt2);
	            MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				String fromAddress = pr.getProperty("fromAddress");
				String[] toAddress = {cfemail};
				String logoPath = pr.getProperty("logoPath");
				String title =  "员工入职周年纪念日提醒通知(此邮件为系统发送，请勿回复)";
				boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath);

				String reletivePath = FileReadUtil.writeFile("司龄",htmlContent,"司龄预警","员工入职周年人员名单");
				
			     WaringDetail waringDetail = new WaringDetail();
				 waringDetail.setTypeid(3);//预警类别
				 waringDetail.setWayid(2);//发送方式
				 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
				 waringDetail.setSendtime(new Date());//发送时间
				 waringDetail.setTheme(title);//
				 waringDetail.setContentaddress(reletivePath);//文件路径，
				 waringDetail.setFilename("员工入职周年纪念日.txt");//文件名称：先写死
				 if(va){
					waringDetail.setIssend(1);//成功了
					waringDetail.setExt1("成功");
				 }else{
					waringDetail.setIssend(0);//失败了
					waringDetail.setExt1("失败");
				 }
				 waringDetail.setSendcount(1);//默认是一次
				 waringDetail.setCreatime(new Date());//创建日期
//				 waringDetail.setEmail(pr.getProperty("toAddress").toString());//收件人邮箱
				 waringDetail.setEmail(cfemail);
				 waringDetail.setReceiver(name);//接收人：张三
				 waringdetailMapper.insert(waringDetail);
		      }
		}catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送给部门负责人测试
	 */
	public void sendOrgTest(String orgnumber, Set<Map<String,Object>> list){
		try {
    	   if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
           MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			
			String logoPath = pr.getProperty("logoPath");
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
					String[] addcopys = pr.getProperty("ccs").split(",");
					 /**
					  * 以下两行为判断数据集是否为空已决定是否继续此预警流程
					  * @author lining
					  */
					 if(null == list || 0 >= list.size()){
						 return;
					 }
			        String htmlContent = ContractHtmlContent.htmlContentYearOfWork(list);
					String name = org.get("NAME").toString(); 
					String cfemail = (org.get("CFEMAIL")==null)?"":org.get("CFEMAIL").toString(); 
					if("".equals(cfemail)){
						cfemail = (org.get("FEMAIL")==null)?"":org.get("FEMAIL").toString(); 
					}
					String[] toAddress = pr.getProperty("toAddress").split(",");
					 
					 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),1,3);//是否个贷  06GDXSF
					 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
				    	  System.err.println(black1.getPname()+": 全部不收");
				    	  return;
				     }
					 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),3,1);//是否个贷  06GDXSF
					 if(black2!=null && ( black2.getWarntype()==3 || black2.getWarntype()==1 ) && black2.getModeid()==1){//全部货司龄
						cfemail = black2.getRaplacemail();
						name = black2.getRaplacename();
						System.err.println(black2.getWarntype()+"代收:"+orgnumber+":"+name+":"+cfemail);
					}
					
					toAddress = pr.getProperty("toAddress").split(",");
					String title =  "员工入职周年纪念日提醒通知(此邮件为系统发送，请勿回复){测试}";
					boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress,title, htmlContent, logoPath,addcopys);

					String reletivePath = FileReadUtil.writeFile(name,htmlContent,"司龄预警","员工入职周年人员名单");
					 WaringDetail waringDetail = new WaringDetail();
					 waringDetail.setTypeid(3);//预警类别
					 waringDetail.setWayid(2);//发送方式
					 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
					 waringDetail.setTheme(title);//
					 waringDetail.setContentaddress(reletivePath);//文件路径，
					 waringDetail.setFilename("员工入职周年纪念日.txt");//文件名称：先写死
					 waringDetail.setEmail(pr.getProperty("toAddress").toString());//收件人邮箱
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
					 waringDetail.setCopyids("gaoquanyang@creditease.cn");
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
	
	//发送给部门负责人  定时任务
	public void sendOrgTestQuartz(String orgnumber, Set<Map<String,Object>> list){
		try {
    	   if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 /**
			  * 以下两行为判断数据集是否为空已决定是否继续此预警流程
			  * @author lining
			  */
			 if(null == list || 0 >= list.size()){
				 return;
			 }
           MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			String logoPath = pr.getProperty("logoPath");
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
					names = names.replaceFirst(",", "");
					mails = mails.replaceFirst(",", "");
					String[] addcopys = mails.split(",");
			        String htmlContent = ContractHtmlContent.htmlContentYearOfWork(list);
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
					 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(org.get("FNUMBER").toString(),3,1);//是否个贷  06GDXSF
					 if(black2!=null && ( black2.getWarntype()==3 || black2.getWarntype()==1 ) && black2.getModeid()==1){//全部货司龄
						cfemail = black2.getRaplacemail();
						name = black2.getRaplacename();
						System.err.println(black2.getWarntype()+"代收:"+orgnumber+":"+name+":"+cfemail);
					}
					String title =  "员工入职周年纪念日提醒通知(此邮件为系统发送，请勿回复)";
					boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath,addcopys);

					String reletivePath = FileReadUtil.writeFile(name,htmlContent,"司龄预警","员工入职周年人员名单");
					 WaringDetail waringDetail = new WaringDetail();
					 waringDetail.setTypeid(3);//预警类别
					 waringDetail.setWayid(2);//发送方式
					 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
					 waringDetail.setTheme(title);//
					 waringDetail.setContentaddress(reletivePath);//文件路径，
					 waringDetail.setFilename("员工入职周年纪念日.txt");//文件名称：先写死
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
	
	//发送给本人
	public void sendPerson(Map<String,Object> map){
		try{
			if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 
			String tmp = Utils.formatTimestampToString(map.get("ENTERDATE"));
			int ss = Integer.parseInt( Utils.getCurrentDate().substring(0, 4) ) - Integer.parseInt(tmp.substring(0, 4));
			String revename = map.get("NAME").toString(); //收件人
			Object mailss= map.get("CFEMAIL")==null?null:map.get("CFEMAIL"); //收件人邮箱
			if(null==mailss){
				mailss = StringUtil.objToString(map.get("FEMAIL"));
			}
			String title = Utils.strTrim(map.get("NAME"))+"入职" +ss+ "周年纪念日(此邮件为系统发送，请勿回复){测试}";
			String htmlContent = SendMailUtil.imageContent(map.get("NAME").toString(),map.get("PERSONNUMBER").toString());
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			String toAddress = pr.getProperty("toAddress").toString();
			System.out.println(toAddress);
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(map.get("PERSONNUMBER").toString(),1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			boolean va = SendMailUtil.sendImageMail(fromAddress,toAddress,title,htmlContent);
			
			 WaringDetail waringDetail = new WaringDetail();
			 
			 waringDetail.setTypeid(3);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
			 waringDetail.setDepartid("");//部门id
			 waringDetail.setDepartname("");//部门名称
			 waringDetail.setPostid("");//职位id
			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids("");//收件人IDS
			 waringDetail.setReceiver(revename);//接收人 
			 waringDetail.setEmail(pr.getProperty("toAddress").toString());//收件人邮箱
		
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(htmlContent);//文件路径，
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
		}catch (Exception ex) {
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	
	//发送给本人的定时任务
	public void sendPersonQuartz(Map<String,Object> map){
		try{
			if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 
			String tmp = Utils.formatTimestampToString(map.get("ENTERDATE"));
			int ss = Integer.parseInt( Utils.getCurrentDate().substring(0, 4) ) - Integer.parseInt(tmp.substring(0, 4));
			String revename = map.get("NAME").toString(); //收件人
			Object mailss= map.get("CFEMAIL")==null?null:map.get("CFEMAIL"); //收件人邮箱
			if(null==mailss){
				mailss = StringUtil.objToString(map.get("FEMAIL"));
			}
			String title = Utils.strTrim(map.get("NAME"))+"入职" +ss+ "周年纪念日(此邮件为系统发送，请勿回复)";
			System.err.println(title);
			
			String htmlContent = SendMailUtil.imageContent(map.get("NAME").toString(),map.get("PERSONNUMBER").toString());
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("fromAddress");
			String toAddress = mailss.toString();
			System.out.println(toAddress);
			BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(map.get("PERSONNUMBER").toString(),1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			boolean va = SendMailUtil.sendImageMail(fromAddress,toAddress,title,htmlContent);
			
			 WaringDetail waringDetail = new WaringDetail();
			 
			 waringDetail.setTypeid(3);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
			 waringDetail.setDepartid("");//部门id
			 waringDetail.setDepartname("");//部门名称
			 waringDetail.setPostid("");//职位id
			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids("");//收件人IDS
			 waringDetail.setReceiver(revename);//接收人 
			 waringDetail.setEmail(mailss.toString());//收件人邮箱
		
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(htmlContent);//文件路径，
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
		}catch (Exception ex) {
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		YearOfWorkService yearOfWorkService = (YearOfWorkService)app.getBean("yearOfWorkServiceImpl");
//		boolean va = yearOfWorkService.queryPersonInfo();
//		System.out.println("ok" + va);
		
		
//		String fromAddress = "tracy_0201@163.com";
//		String toAddress = "tracy_0201@163.com";
//		String title = "司龄预警";
		
//		String htmlContent = SendMailUtil.htmlContent(null);
//		boolean va = SendMailUtil.sendImageMail(fromAddress, toAddress, title, htmlContent);
//		System.out.println("va:::::::" + va);
	}
}
