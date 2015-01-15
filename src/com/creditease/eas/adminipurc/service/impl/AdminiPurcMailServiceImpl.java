package com.creditease.eas.adminipurc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditease.eas.adminipurc.dao.AdminContractInfoMapper;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.adminipurc.service.IAdminiPurcMailService;
import com.creditease.eas.common.action.SendMailAction;
import com.creditease.eas.common.vo.EmailInfo;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.MailSenderInfo;
/**
 * 行政采购合同预警业务层实现类
 * @AdminiPurcMailServiceImpl.java
 * created at 2014-5-20 下午05:10:13 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class AdminiPurcMailServiceImpl implements IAdminiPurcMailService{
	@Resource
	private AdminContractInfoMapper adminContractInfoMapper;//合同信息mapper
	@Resource
	private CommonPortinfoMapper commonPortinfoMapper;//接口人信息mapper
	@Autowired
	private IDictionaryItemMapper dictionaryItemMapper;//数据字典mapper
	@Resource
	private SendMailAction mailAction;//发邮件Action
	
	/**
	 * 
	 * 描述：给接口人发送合同到期邮件提醒
	 * 合同到期前28天进行提醒
	 * 2014-5-22 上午11:41:11 by zhangxin
	 * @version
	 * @return String 内容字符串
	 */
	@SuppressWarnings({ "unused", "null" })
	@Override
	public void purcPortMailInfo() {
		//查询合同信息
		List<Map<String, Object>> contractlist =  adminContractInfoMapper.queryInfoToMail();
		//发送邮件信息封装bean的list
		List<EmailInfo> emailinfoList = new ArrayList<EmailInfo>();
		for(Map<String,Object> map : contractlist){
			if(map.get("FLASTCOSTCENTER")==null||"".equals(map.get("FLASTCOSTCENTER"))||
					map.get("FCONTRACTTYPEID")==null||"".equals(map.get("FCONTRACTTYPEID"))||
					map.get("FORGNAME")==null||"".equals(map.get("FORGNAME"))||
					map.get("FOFFICEADDR")==null||"".equals(map.get("FOFFICEADDR"))||
					map.get("FSUPPLIERNAME")==null||"".equals(map.get("FSUPPLIERNAME"))||
					map.get("FENDDATE")==null||"".equals(map.get("FENDDATE")))continue;
			
			Format f = new SimpleDateFormat("yyyy-MM-dd"); 
			String today =f.format(Utils.getDate()) ;//获取当前系统时间
			Date endDate = Utils.toDate(map.get("FENDDATE").toString());//合同到期日
			String contractStatus = "";
			if(map.get("FCONTRACTSTATUS")!=null){
				contractStatus = map.get("FCONTRACTSTATUS").toString();//合同到期跟进状态
			}
				
			String fourweek = f.format(Utils.getBeforeDate(endDate, 28));//合同到期前28天进行提醒
			
			//判断合同到期日和合同到期跟进状态，合同到期前一个月并且没有办理续签，进行邮件提醒（795-已续签,797-不续签）
			//已续签和不续签不进行提醒，其他状态进行提醒
			if(today.equals(fourweek)&&(!contractStatus.equals("795"))&&(!contractStatus.equals("797"))){
			
			String lastCostcenter = map.get("FLASTCOSTCENTER").toString();//末级成本中心
			Integer contractTypeId = Integer.parseInt(map.get("FCONTRACTTYPEID").toString());//合同类别id
			String contractTypeName;
			DictionaryItem item = dictionaryItemMapper.selectByPrimaryKey(contractTypeId);

			if(item ==null || item.equals("")){

				contractTypeName = "";
			}else{
				contractTypeName = item.getItemname();//合同类型值
			}
			String id = map.get("FID").toString();//合同id
			String orgName = map.get("FORGNAME").toString();//使用部门
			String officeAddr = map.get("FOFFICEADDR").toString();//办公室坐落地址
			String supplierName = map.get("FSUPPLIERNAME").toString();//供应商名称	
		
			Map<String,Object> selMap = new HashMap<String,Object>();
			selMap.put("lastCostcenter", lastCostcenter);
			selMap.put("orgName", orgName);
			selMap.put("officeAddr", officeAddr);
			
			//邮件发送消息bean
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			//发件人邮箱
			String fromAddress = pr.getProperty("PURC_USERNAME");
			//发件人邮箱密码
			String password = pr.getProperty("PURC_PASSWORD");
			
			//接口人邮箱
			String toAddressemail;
			List<Map<String, Object>> commonportmailList = commonPortinfoMapper.portemailToSend(selMap);
			if(commonportmailList != null && commonportmailList.size()>0){
				toAddressemail = this.getEmailArr(commonportmailList);
			}else{
				continue;
			}
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				toAddressemail = "v-xinzhang@creditease.cn;";//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			
			//邮件内容列表
			Map<String,Object> portinfo = new HashMap<String, Object>();
			portinfo.put("部门", orgName);
			portinfo.put("办公室地址", officeAddr);
			portinfo.put("成本中心", lastCostcenter);
			portinfo.put("合同类别", contractTypeName);
			portinfo.put("供应商名称", supplierName);
			portinfo.put("合同到期日", f.format(endDate));
			//将邮件内容封装成HTMLTable.java类中公共生成表格要求的集合类型
			List<Map<String,Object>> portinfoList = new ArrayList<Map<String,Object>>();
			portinfoList.add(portinfo);
			List<List<Map<String,Object>>> portinfolistlist = new ArrayList<List<Map<String,Object>>>();
			portinfolistlist.add(portinfoList);
			Map<String,Object> tableDatas = new HashMap<String,Object>();
			tableDatas.put("datas", portinfolistlist);
			//拼接邮件内容
			String htmlContent = ContractHtmlContent.htmlAdminiPort(tableDatas);//邮件内容
			//邮件标题
			String title = lastCostcenter + contractTypeName + "到期提醒（此邮件为系统发送，请勿回复）";
			//将邮件内容写入文件中,返回文件路径
			String path = this.writeMailInfo(htmlContent);
			//将邮件内容拼接成李宁bean
			EmailInfo emailinfo = new EmailInfo();
			emailinfo.setBusinessId(id);
			emailinfo.setSenderAddr(fromAddress);
			emailinfo.setSenderPswd(password);
			emailinfo.setReceiverAddrs(toAddressemail);
			emailinfo.setTheme(title);
			emailinfo.setContent(htmlContent);
			emailinfo.setContentPath(path);
			emailinfo.setSendDate(today);
			emailinfo.setSendTime("20:25:00");  
		//	emailinfo.setSendTime("14:30:00");//测试时间
			emailinfo.setImages("/app/tomcat-eas/easfile/logo.png;"); 
		//	emailinfo.setImages("D:\\app\\tomcat-eas\\easfile\\logo.png;");//测试时
			
			emailinfoList.add(emailinfo);
			}else{
				continue;
			}
		}
			//公共发邮件方法，将数据插入缓存表，底层逻辑触发发邮件功能
			mailAction.addEmailInfo(emailinfoList);
	}
	
	/**
	 * 
	 * 描述：28天提醒后再次提醒为到期前每周一发送邮件提醒
	 * 2014-7-17 下午01:28:38 by zhangxin
	 * @version
	 */
	@Override
	public void purcMondayMailInfo() {
		//查询合同信息
		List<Map<String, Object>> contractlist =  adminContractInfoMapper.queryInfoToMail();
		//发送邮件信息封装bean的list
		List<EmailInfo> emailinfoList = new ArrayList<EmailInfo>();
		for(Map<String,Object> map : contractlist){
			if(map.get("FLASTCOSTCENTER")==null||"".equals(map.get("FLASTCOSTCENTER"))||
					map.get("FCONTRACTTYPEID")==null||"".equals(map.get("FCONTRACTTYPEID"))||
					map.get("FORGNAME")==null||"".equals(map.get("FORGNAME"))||
					map.get("FOFFICEADDR")==null||"".equals(map.get("FOFFICEADDR"))||
					map.get("FSUPPLIERNAME")==null||"".equals(map.get("FSUPPLIERNAME"))||
					map.get("FENDDATE")==null||"".equals(map.get("FENDDATE")))continue;
			
			Format f = new SimpleDateFormat("yyyy-MM-dd"); 
			Date todaydate = Utils.getDate();
			String today =f.format(todaydate) ;//获取当前系统时间
			Date endDate = Utils.toDate(map.get("FENDDATE").toString());//合同到期日
			String contractStatus = "";
			if(map.get("FCONTRACTSTATUS")!=null){
				contractStatus = map.get("FCONTRACTSTATUS").toString();//合同到期跟进状态
			}
			Date fourweekdate = Utils.getBeforeDate(endDate, 28);//合同到期前28天的日期
			
			//判断合同到期日和合同到期跟进状态，合同到期前28天第一次提醒之后，
			//第2、3、4次提醒时为每周一，合同为已续签或者不续签时不提醒，其他状态就要提醒（795-已续签,797-不续签）
			if(todaydate.before(endDate)&&todaydate.after(fourweekdate)&&(!contractStatus.equals("795"))&&(!contractStatus.equals("797"))){
			
			String lastCostcenter = map.get("FLASTCOSTCENTER").toString();//末级成本中心
			Integer contractTypeId = Integer.parseInt(map.get("FCONTRACTTYPEID").toString());//合同类别id
			String contractTypeName;
			DictionaryItem item = dictionaryItemMapper.selectByPrimaryKey(contractTypeId);
			if(item ==null || item.equals("")){
				contractTypeName = "";
			}else{
				contractTypeName = item.getItemname();//合同类型值
			}
			String id = map.get("FID").toString();//合同id
			String orgName = map.get("FORGNAME").toString();//使用部门
			String officeAddr = map.get("FOFFICEADDR").toString();//办公室坐落地址
			String supplierName = map.get("FSUPPLIERNAME").toString();//供应商名称	
		
			Map<String,Object> selMap = new HashMap<String,Object>();
			selMap.put("lastCostcenter", lastCostcenter);
			selMap.put("orgName", orgName);
			selMap.put("officeAddr", officeAddr);
			
			//邮件发送消息bean
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			//发件人邮箱
			String fromAddress = pr.getProperty("PURC_USERNAME");
			//发件人邮箱密码
			String password = pr.getProperty("PURC_PASSWORD");
			
			//接口人邮箱
			String toAddressemail;
			List<Map<String, Object>> commonportmailList = commonPortinfoMapper.portemailToSend(selMap);
			if(commonportmailList != null && commonportmailList.size()>0){
				toAddressemail = this.getEmailArr(commonportmailList);
			}else{
				continue;
			}
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				toAddressemail = "v-xinzhang@creditease.cn;";//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			
			//邮件内容列表
			Map<String,Object> portinfo = new HashMap<String, Object>();
			portinfo.put("部门", orgName);
			portinfo.put("办公室地址", officeAddr);
			portinfo.put("成本中心", lastCostcenter);
			portinfo.put("合同类别", contractTypeName);
			portinfo.put("供应商名称", supplierName);
			portinfo.put("合同到期日", f.format(endDate));
			//将邮件内容封装成HTMLTable.java类中公共生成表格要求的集合类型
			List<Map<String,Object>> portinfoList = new ArrayList<Map<String,Object>>();
			portinfoList.add(portinfo);
			List<List<Map<String,Object>>> portinfolistlist = new ArrayList<List<Map<String,Object>>>();
			portinfolistlist.add(portinfoList);
			Map<String,Object> tableDatas = new HashMap<String,Object>();
			tableDatas.put("datas", portinfolistlist);
			//拼接邮件内容
			String htmlContent = ContractHtmlContent.htmlAdminiPort(tableDatas);//邮件内容
			//邮件标题
			String title = lastCostcenter + contractTypeName + "到期提醒（此邮件为系统发送，请勿回复）";
			//将邮件内容写入文件中,返回文件路径
			String path = this.writeMailInfo(htmlContent);
			//将邮件内容拼接成李宁bean
			EmailInfo emailinfo = new EmailInfo();
			emailinfo.setBusinessId(id);
			emailinfo.setSenderAddr(fromAddress);
			emailinfo.setSenderPswd(password);
			emailinfo.setReceiverAddrs(toAddressemail);
			emailinfo.setTheme(title);
			emailinfo.setContent(htmlContent);
			emailinfo.setContentPath(path);
			emailinfo.setSendDate(today);
			emailinfo.setSendTime("08:50:00");  
			//emailinfo.setSendTime("15:20:00");//测试时间
			emailinfo.setImages("/app/tomcat-eas/easfile/logo.png;"); 
		//	emailinfo.setImages("D:\\app\\tomcat-eas\\easfile\\logo.png;");//测试时间
			
			emailinfoList.add(emailinfo);
			}else{
				continue;
			}
		}
			//公共发邮件方法，将数据插入缓存表，底层逻辑触发发邮件功能
			mailAction.addEmailInfo(emailinfoList);
	}
	
	
	
	/**
	 * 
	 * 描述：将收件人邮箱转换成";"号拼接的字符串
	 * 2014-5-29 下午07:37:43 by zhangxin
	 * @version
	 * @param list
	 * @return
	 */
	private String getEmailArr(List<Map<String, Object>> list){
		String emailArr = "";
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
				emailArr = emailArr +";"+list.get(i).get("FPORTEMAIL").toString();
			}
		}
		return emailArr.substring(1);
	} 
	/**
	 * 
	 * 描述：将邮件内容写入文件中
	 * 2014-6-7 下午02:34:56 by zhangxin
	 * @version
	 * @param mailinfo
	 * @return 返回文件路径
	 */
	@Override
	public String writeMailInfo(String mailinfo) {

		//String path = "D:/app/tomcat-eas/easfile/mismail";//文件路径,本地路径
		String path = "/app/tomcat-eas/easfile/mismail";//文件路径,测试机、正式环境路径
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();//路径不存在时,创建路径
		}
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddhhmmssSSS");//设置日期格式
		String timepath = time.format(new Date());//获取当前系统时间
		String emailfile = path + "/adminipurc" + timepath +(new Random().nextInt(1000000)) +".txt" ;
		File filepath = new File(emailfile);//将邮件内容写入文件中
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filepath,true);
			out.write(mailinfo.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {   
            if (out != null) {   
                try {   
                	out.close();   
                } catch (IOException io) {   
                	io.printStackTrace(); 
                }   
            }   
        }  

	
		return emailfile;
	}

	/**
	 * 
	 * 描述：每周四汇总本周的提醒邮件发送给采购组,包括本周五即将要发送的邮件
	 * 2014-6-7 下午01:25:01 by zhangxin
	 * @version
	 */
	@SuppressWarnings("unused")
	@Override
	public void totalPurcPortMailInfo() {
		//查询合同信息
		List<Map<String, Object>> contractlist =  adminContractInfoMapper.queryInfoToMail();
		//将邮件内容封装成HTMLTable.java类中公共生成表格要求的集合类型
		List<Map<String,Object>> portinfoList = new ArrayList<Map<String,Object>>();
		List<List<Map<String,Object>>> portinfolistlist = new ArrayList<List<Map<String,Object>>>();
		
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Date today = Utils.toDate(time.format(new Date()));//获取当前系统时间(周四)
		for(Map<String,Object> map : contractlist){
			if(map.get("FLASTCOSTCENTER")==null||"".equals(map.get("FLASTCOSTCENTER"))||
					map.get("FCONTRACTTYPEID")==null||"".equals(map.get("FCONTRACTTYPEID"))||
					map.get("FORGNAME")==null||"".equals(map.get("FORGNAME"))||
					map.get("FOFFICEADDR")==null||"".equals(map.get("FOFFICEADDR"))||
					map.get("FSUPPLIERNAME")==null||"".equals(map.get("FSUPPLIERNAME"))||
					map.get("FENDDATE")==null||"".equals(map.get("FENDDATE")))continue;
		
			Date endDate = Utils.toDate(map.get("FENDDATE").toString());//合同到期日
			String contractStatus = "";
			if(map.get("FCONTRACTSTATUS")!=null){
				contractStatus = map.get("FCONTRACTSTATUS").toString();//合同到期跟进状态
			}
			//判断此合同是否在本周周一到周五之间发
			boolean sendOrNo = this.sendOrNo(today,endDate, contractStatus);
			//如果为true,汇总此合同内容
			if(sendOrNo){
				String lastCostcenter = map.get("FLASTCOSTCENTER").toString();//末级成本中心
				Integer contractTypeId = Integer.parseInt(map.get("FCONTRACTTYPEID").toString());//合同类别id
				String contractTypeName;
				DictionaryItem item = dictionaryItemMapper.selectByPrimaryKey(contractTypeId);
				if(item ==null || item.equals("")){
					contractTypeName = "";
				}else{
					contractTypeName = item.getItemname();//合同类型值
				}
				String orgName = map.get("FORGNAME").toString();//使用部门
				String officeAddr = map.get("FOFFICEADDR").toString();//办公室坐落地址
				String supplierName = map.get("FSUPPLIERNAME").toString();//供应商名称	
			
				Map<String,Object> selMap = new HashMap<String,Object>();
				selMap.put("lastCostcenter", lastCostcenter);
				selMap.put("orgName", orgName);
				selMap.put("officeAddr", officeAddr);
				
				
				//邮件内容列表
				Map<String,Object> portinfo = new HashMap<String, Object>();
				portinfo.put("部门", orgName);
				portinfo.put("办公室地址", officeAddr);
				portinfo.put("成本中心", lastCostcenter);
				portinfo.put("合同类别", contractTypeName);
				portinfo.put("供应商名称", supplierName);
				portinfo.put("合同到期日", time.format(endDate));
				
				portinfoList.add(portinfo);
				}
			}
		
		if(portinfoList.size()>0&&portinfoList != null){
				//邮件发送消息bean
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				//发件人邮箱
				String fromAddress = pr.getProperty("PURC_USERNAME");
				//发件人邮箱密码
				String password = pr.getProperty("PURC_PASSWORD");
				//采购组邮箱 cg.list@creditease.cn
				String toAddressemail = "cg.list@creditease.cn;";
				if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
					toAddressemail = "v-xinzhang@creditease.cn;";//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
				}
				//封装成HTMLTable.java类中公共生成表格要求的集合类型
				portinfolistlist.add(portinfoList);
				Map<String,Object> tableDatas = new HashMap<String,Object>();
				tableDatas.put("datas", portinfolistlist);
				//拼接邮件内容
				String htmlContent = ContractHtmlContent.htmlAdminiPort(tableDatas);//邮件内容
				//邮件标题
				String title ="本周采购合同到期提醒邮件汇总（此邮件为系统发送，请勿回复）";
				//将邮件内容写入文件中,返回文件路径
				String path = this.writeMailInfo(htmlContent);
				//将邮件内容拼接成李宁bean
				EmailInfo emailinfo = new EmailInfo();
				emailinfo.setSenderAddr(fromAddress);
				emailinfo.setSenderPswd(password);
				emailinfo.setReceiverAddrs(toAddressemail);
				emailinfo.setTheme(title);
				emailinfo.setContent(htmlContent);
				emailinfo.setContentPath(path);
				emailinfo.setSendDate(time.format(today));
				emailinfo.setSendTime("22:20:00");  
				//emailinfo.setSendTime("15:20:00");//测试时间
				emailinfo.setImages("/app/tomcat-eas/easfile/logo.png;"); 
				//	emailinfo.setImages("D:\\app\\tomcat-eas\\easfile\\logo.png;");//测试路径
				//发送邮件信息封装bean的list，插入到缓存表
				List<EmailInfo> emailinfoList = new ArrayList<EmailInfo>();
				emailinfoList.add(emailinfo);
				//公共发邮件bean
				mailAction.addEmailInfo(emailinfoList);
		}
	}
	
	/**
	 * 
	 * 描述：每周四总接口人收到本部门的邮件提醒汇总邮件
	 * 2014-8-18 下午02:13:50 by zhangxin
	 * @version
	 */
	@Override
	public void orgTotalMailToPort() {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		Date today = Utils.toDate(time.format(new Date()));//获取当前系统时间(周四)
		//查询总接口人信息
		List<Map<String, Object>> orgAllPortList = commonPortinfoMapper.allPortEmailToSend();
		//查询合同信息
		List<Map<String, Object>> contractlist =  adminContractInfoMapper.queryInfoToMail();
		//循环总接口人信息
		for(Map<String,Object> map : orgAllPortList){
			
			//将邮件内容封装成HTMLTable.java类中公共生成表格要求的集合类型
			List<Map<String,Object>> portinfoList = new ArrayList<Map<String,Object>>();
			List<List<Map<String,Object>>> portinfolistlist = new ArrayList<List<Map<String,Object>>>();
			
			//循环合同信息
			for(Map<String,Object> contractmap : contractlist){
				if(contractmap.get("FLASTCOSTCENTER")==null||"".equals(contractmap.get("FLASTCOSTCENTER"))||
						contractmap.get("FCONTRACTTYPEID")==null||"".equals(contractmap.get("FCONTRACTTYPEID"))||
						contractmap.get("FORGNAME")==null||"".equals(contractmap.get("FORGNAME"))||
						contractmap.get("FOFFICEADDR")==null||"".equals(contractmap.get("FOFFICEADDR"))||
						contractmap.get("FSUPPLIERNAME")==null||"".equals(contractmap.get("FSUPPLIERNAME"))||
						contractmap.get("FENDDATE")==null||"".equals(contractmap.get("FENDDATE")))continue;
				
					String fext2 = (String)contractmap.get("FEXT2");
					String orgfid=map.get("FID").toString();
					
				//如果总接口人fid等于合同信息fext2,主外键关联，一对多，即该合同信息如果是这个总接口人负责则满足条件
				if( fext2 != null && !fext2.equals("") && fext2.equals(orgfid)){
					Date endDate = Utils.toDate(contractmap.get("FENDDATE").toString());//合同到期日
					String contractStatus = "";
					if(contractmap.get("FCONTRACTSTATUS")!=null){
						contractStatus = contractmap.get("FCONTRACTSTATUS").toString();//合同到期跟进状态
					}
					//判断此合同是否在本周周一到周五之间发
					boolean sendOrNo = this.sendOrNo(today,endDate, contractStatus);
					//如果为true,汇总此合同内容
					if(sendOrNo){
						String lastCostcenter = contractmap.get("FLASTCOSTCENTER").toString();//末级成本中心
						Integer contractTypeId = Integer.parseInt(contractmap.get("FCONTRACTTYPEID").toString());//合同类别id
						String contractTypeName;
						DictionaryItem item = dictionaryItemMapper.selectByPrimaryKey(contractTypeId);
						if(item ==null || item.equals("")){
							contractTypeName = "";
						}else{
							contractTypeName = item.getItemname();//合同类型值
						}
						String orgName = contractmap.get("FORGNAME").toString();//使用部门
						String officeAddr = contractmap.get("FOFFICEADDR").toString();//办公室坐落地址
						String supplierName = contractmap.get("FSUPPLIERNAME").toString();//供应商名称	
						//邮件内容列表
						Map<String,Object> portinfo = new HashMap<String, Object>();
						portinfo.put("部门", orgName);
						portinfo.put("办公室地址", officeAddr);
						portinfo.put("成本中心", lastCostcenter);
						portinfo.put("合同类别", contractTypeName);
						portinfo.put("供应商名称", supplierName);
						portinfo.put("合同到期日", time.format(endDate));
						
						portinfoList.add(portinfo);
						}
				}
			}
			if(portinfoList.size()>0 && portinfoList != null){
				//邮件发送消息bean
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				//发件人邮箱
				String fromAddress = pr.getProperty("PURC_USERNAME");
				//发件人邮箱密码
				String password = pr.getProperty("PURC_PASSWORD");
				//总接口人邮箱
				String orgportmail =(String) map.get("FPORTEMAIL");
				String toAddressemail;
				if(orgportmail != null && !orgportmail.equals("")){
					 toAddressemail = orgportmail;
				}else{
					continue;
				}
				if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
					toAddressemail = "v-xinzhang@creditease.cn;";//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
				}
				//封装成HTMLTable.java类中公共生成表格要求的集合类型
				portinfolistlist.add(portinfoList);
				Map<String,Object> tableDatas = new HashMap<String,Object>();
				tableDatas.put("datas", portinfolistlist);
				//拼接邮件内容
				String htmlContent = ContractHtmlContent.htmlAdminiPort(tableDatas);//邮件内容
				//邮件标题
				String title ="本周采购合同到期提醒邮件汇总（此邮件为系统发送，请勿回复）";
				//将邮件内容写入文件中,返回文件路径
				String path = this.writeMailInfo(htmlContent);
				//将邮件内容拼接成李宁bean
				EmailInfo emailinfo = new EmailInfo();
				emailinfo.setSenderAddr(fromAddress);
				emailinfo.setSenderPswd(password);
				emailinfo.setReceiverAddrs(toAddressemail);
				emailinfo.setTheme(title);
				emailinfo.setContent(htmlContent);
				emailinfo.setContentPath(path);
				emailinfo.setSendDate(time.format(today));
				emailinfo.setSendTime("22:20:00");  
				//emailinfo.setSendTime("16:20:00");//测试时间
				emailinfo.setImages("/app/tomcat-eas/easfile/logo.png;"); 
				//emailinfo.setImages("D:\\app\\tomcat-eas\\easfile\\logo.png;");//测试路径
				//发送邮件信息封装bean的list，插入到缓存表
				List<EmailInfo> emailinfoList = new ArrayList<EmailInfo>();
				emailinfoList.add(emailinfo);
				//公共发邮件bean
				mailAction.addEmailInfo(emailinfoList);
		}
		}
		
	}
	
	
	/**
	 * 
	 * 描述：判断合同信息是否需要发送预警邮件
	 * 2014-6-7 下午03:22:49 by zhangxin
	 * @version
	 * @param endDate 合同到期日
	 * @return  true 是   false 否
	 */
	@SuppressWarnings("unused")
	private boolean sendOrNo(Date today,Date endDate,String contractStatus){
		boolean sendOrNo;
		Format f = new SimpleDateFormat("yyyy-MM-dd"); 
		//周三
		String wedDate = f.format(Utils.getBeforeDate(today, 1));
		//周二
		String tueDate = f.format(Utils.getBeforeDate(today, 2));
		//周一
		Date mon = Utils.getBeforeDate(today, 3);
		String monDate = f.format(mon);
		//周五
		String friDate = f.format(Utils.getBeforeAfterDate(today, 1));
		//周四
		String fourDate = f.format(today);
		//合同在本周工作日内是否符合发送预警邮件的条件
		if(this.trueOrFalse(fourDate, endDate, contractStatus)||this.trueOrFalse(wedDate, endDate, contractStatus)||this.trueOrFalse(tueDate, endDate, contractStatus)
				||this.trueOrFalse(monDate, endDate, contractStatus)||this.trueOrFalse(friDate, endDate, contractStatus)||this.montrueOrFalse(mon, endDate, contractStatus)){
			sendOrNo = true;
		}else{
			sendOrNo = false;
		}
		return sendOrNo;
	}
	/**
	 * 
	 * 描述：根据日期判断
	 * 2014-6-7 下午04:36:42 by zhangxin
	 * @version
	 * @return true or false
	 * date:要比较的日期
	 * endDate：合同到期日
	 * contractStatus：合同状态 ，795-已续签,797-不续签 这两种状态不发，其他状态发邮件
	 */
	@SuppressWarnings("unused")
	private boolean trueOrFalse(String date,Date endDate,String contractStatus){
		Format f = new SimpleDateFormat("yyyy-MM-dd"); 
		Date beforeEndDate = Utils.getBeforeDate(endDate, 28);
		String onceSendMail = f.format(beforeEndDate);//第一次（合同到期前28天）发送合同预警信息
		//要比较的日期是否等于合同到期前28天并且合同状态不是已续签和不续签
		if(date.equals(onceSendMail)&&(!contractStatus.equals("795"))&&(!contractStatus.equals("797"))){
			return true;
	}else{
		return false;
	}
	}
	/**
	 * 
	 * 描述：是否符合周一发送邮件的规则
	 * 2014-7-17 下午02:50:35 by zhangxin
	 * @version
	 * @param date 周一
	 * @param endDate 合同到期日
	 * @param contractStatus 合同状态  795-已续签,797-不续签
	 * @return true or false
	 */
	@SuppressWarnings("unused")
	private boolean montrueOrFalse(Date date,Date endDate,String contractStatus){
		Format f = new SimpleDateFormat("yyyy-MM-dd"); 
		Date beforeEndDate = Utils.getBeforeDate(endDate, 28);//第一次（合同到期前28天）发送合同预警信息
		//要比较的日期是否等于合同到期前28天并且合同状态不是已续签和不续签
		if(date.before(endDate)&&date.after(beforeEndDate)&&(!contractStatus.equals("795"))&&(!contractStatus.equals("797"))){
			return true;
	}else{
		return false;
	}
	}

	
}
