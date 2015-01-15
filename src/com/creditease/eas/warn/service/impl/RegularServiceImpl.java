/**
 * 
 */
package com.creditease.eas.warn.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.mail.HtmlContentCommonMethod;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.kingdee.query.OrgAdminMapperQuery;
import com.creditease.eas.warn.service.BlackListInfoService;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.RegularService;
/**
 * @VariablesServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class RegularServiceImpl implements RegularService{
	private static final Logger logger = Logger.getLogger(RegularServiceImpl.class);
	@Autowired
	private WaringDetailMapper waringdetailMapper;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	
	@Autowired
	private BlackListInfoService blackListInfoServiceImpl;
	
	@Override
	public void sendResponsePersonMailInfo(List<Map<String, Object>> listHig,List<Map<String, Object>> listPersonsInfo) {
		if(null == listPersonsInfo || 1 > listPersonsInfo.size()){
			return;
		}
		try{
			 //发送邮件：直接负责人，间接负责人,部门负责人
			 String emails = "";//收件人邮箱
			 String personIds = "";//收件人IDS
			 String personNames = "";//收件人名字
			Map<String,Object> mpp =  listHig.get(0);
			if(mpp==null){
				return;
			}
			Object cfmail = mpp.get("CFMAIL")==null?null:mpp.get("CFMAIL");//邮箱
			if(null != cfmail){
				emails += cfmail;
				personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
				personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
			}else{//取个人邮箱
				cfmail = mpp.get("EMAIL")==null?null:mpp.get("EMAIL");//邮箱
				if(null != cfmail){
					emails += cfmail;
					personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
					personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
				}
			}
			 System.out.println("realMail\t" + emails);
			 if(emails.equals("")){//如果收件人一个邮件都没有，就放弃发送邮件,程序不再往下执行
				 //System.out.println("放弃了");
				 return;
			 }
			 //根据部门负责人是hr的，还是技术部负责人
			 //设置BP人员
			 //如果是技术部的yongbotong@creditease.cn
			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
			 if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 //现在使用新版本里面的方法
			 String htmlContent = HtmlContentCommonMethod.htmlContent(listPersonsInfo);
			 Map<String,Object> mp = listPersonsInfo.get(0);
			 String deptName =  StringUtil.objToString(mp.get("DEPTNAME"));
			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
			 String title = "员工试用期期满提醒通知(此邮件为系统发送，请勿回复)";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 String logoPath = pr.getProperty("logoPath");
			 String fromAddress = pr.getProperty("fromAddress");
			 String[] ccs = null;
			 //查询BP和综合管理部负责人的信息也需要查询出来
//			 if(emails.equals("yongbotong@creditease.cn")){
//				 ccs = new String[]{"nadong@creditease.cn","jingma4@creditease.cn"};//董娜是综合管理部负责人,技术部没有BP人员
//			 }else{
//				 ccs = new String[]{"shuoliu@creditease.cn","jingma4@creditease.cn"};
//			 }
			 String fnumber = StringUtil.objToString(mpp.get("DEPTCODE"));
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
//					String[] addcopys = mails.split(",");
				ccs = mails.split(",");
			}
//			 System.out.println("真的抄送人ccs\t" + ccs);
			 //真正发送的时候需要将这个地方改一下
			 String[] toAddress = new String[]{emails};
			
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(mpp.get("FNUMBER").toString(),1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(mpp.get("FNUMBER").toString(),2,1);//是否个贷  06GDXSF
			 if(black2!=null && ( black2.getWarntype()==2 || black2.getWarntype()==1 ) && black2.getModeid()==1){
					emails = black2.getRaplacemail();
					personNames = black2.getRaplacename();
					System.err.println(black2.getWarntype()+"代收:"+mpp.get("FNUMBER").toString()+":"+personNames+":"+emails);
			 }
//			 System.out.println("fromAddress:  "+fromAddress);
//			 ccs = new String[]{"626264481@qq.com"};//制造假的抄件人
			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中,添加logo
			 boolean va =  SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath,ccs);
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(2);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
			 waringDetail.setDepartid("");//部门id
			 waringDetail.setDepartname(deptName);//部门名称
			 waringDetail.setPostid("");//职位id
			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids(personIds);//收件人IDS
			 waringDetail.setReceiver(personNames);//接收人：张三
			 waringDetail.setEmail(emails);//收件人邮箱
			 waringDetail.setCopyids(mails);
			 waringDetail.setCopyperson(names);//抄送人
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
			  if(va){
					waringDetail.setIssend(1);//成功了
					waringDetail.setExt1("成功");
				}else{
					waringDetail.setIssend(0);//失败了
					waringDetail.setExt1("失败");
				}
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	@Override
	public void sendMailInfosMerge(Map<String,List<Map<String,Object>>> mpPerson) {
		List<Map<String,Object>> listUpTemp = mpPerson.get("PERSONINFO");
		Map<String,Object> mpToPerson = listUpTemp.get(0);//这里面存的是收件人的相关的信息
		if(null == mpToPerson||mpToPerson.size()==0){
			return;
		}
		try{
			 //发送邮件：直接负责人，间接负责人:
			//原来的代码中有bug,刚修复了 2013年11月10日 16:32
			 Object  objEmails = mpToPerson.get("CFMAIL")==null?mpToPerson.get("EMAIL"):mpToPerson.get("CFMAIL");//收件人邮箱
			 String emails = objEmails == null?null:objEmails.toString();
			 String personIds = mpToPerson.get("PERSONID").toString();
			 String personName = mpToPerson.get("PERSONNAME").toString();
			 
			 String pnumber = mpToPerson.get("PERSONCODE").toString(); 
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,2,2);//是否个贷  06GDXSF
			 if(black2!=null && ( black2.getWarntype()==2 || black2.getWarntype()==1 ) && black2.getModeid()==2){
//				 List<Map<String,Object>> immPerson = mpPerson.get("immDownPerson");//直接下属
//				 List<Map<String,Object>> indirPerson = mpPerson.get("indirDownPerson");//间接下属
				 mpPerson.put("indirDownPerson", null);
				 if(mpPerson.get("immDownPerson").size()<1){//直下为空
					 return;
				 }
				 System.err.println(personName+": 间下不收");
			 }
			 
//			 System.out.println("查看真实的收件人\t" + emails);
			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
			 if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %30==0){
				 Thread.sleep(1000*60);//系统休眠60秒
			 }
			 TongJi.tongJiCount ++;
			 List<Map<String,Object>> immPerson = mpPerson.get("immDownPerson");//直接下属
			 List<Map<String,Object>> indirPerson = mpPerson.get("indirDownPerson");//间接下属
			 if((null == immPerson || 1 > immPerson.size()) && ( null == indirPerson  || 1 > indirPerson.size())){
			 	return;
			 }
			 //现在使用新版本里面的方法
			 String htmlContent = HtmlContentCommonMethod.htmlContentImitate(mpPerson);
			 String reletivePath = FileReadUtil.writeFileImitate(htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
			 String title = "员工试用期期满提醒通知(此邮件为系统发送，请勿回复)";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 String logoPath = pr.getProperty("logoPath");
			 String fromAddress = pr.getProperty("fromAddress");//发件人
			 String[] toAddress = {emails};////收件人
//			 System.out.println("fromAddress:  "+fromAddress);
//			 System.out.println("toAddress:  "+fromAddress);
			 //根据key查询出来直接上级，间接上级，：将信息插入到明细表中,添加logo
			 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress,toAddress,title, htmlContent, logoPath);
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(2);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
//			 waringDetail.setDepartid("");//部门id
//			 waringDetail.setDepartname(deptName);//部门名称
//			 waringDetail.setPostid("");//职位id
//			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids(personIds);//收件人IDS
			 waringDetail.setReceiver(personName);//接收人：张三
			 waringDetail.setEmail(emails);//收件人邮箱
//			 waringDetail.setCopyids("");
//			 waringDetail.setCopyperson("");//抄送人
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename("转正人员名单.txt");//文件名称：先写死
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	/*****************************************************模拟调用过程**********************************************************************************/
	@Override
	public void sendResponsePersonMailInfoTest(List<Map<String, Object>> listHig,List<Map<String, Object>> listPersonsInfo) {
		if(null == listPersonsInfo || 1 > listPersonsInfo.size()){
			return;
		}
		try{
			 //发送邮件：直接负责人，间接负责人,部门负责人
			 String emails = "";//收件人邮箱
			 String personIds = "";//收件人IDS
			 String personNames = "";//收件人名字
			Map<String,Object> mpp =  listHig.get(0);//取得部门负责人的信息
			if(mpp==null){
				return;
			}
			System.out.println("部门编码\t" + mpp.get("DEPTCODE"));
			Object cfmail = mpp.get("CFMAIL")==null?null:mpp.get("CFMAIL");//邮箱
			if(null != cfmail){
				emails += cfmail;
				personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
				personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
			}else{//取个人邮箱
				cfmail = mpp.get("EMAIL")==null?null:mpp.get("EMAIL");//邮箱
				if(null != cfmail){
					emails += cfmail;
					personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
					personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
				}
			}
			 System.out.println("realMail\t" + emails);
			 if(emails.equals("")){//如果收件人一个邮件都没有，就放弃发送邮件,程序不再往下执行
				 //System.out.println("放弃了");
				 return;
			 }
			 //根据部门负责人是hr的，还是技术部负责人
			 //设置BP人员
			 //如果是技术部的yongbotong@creditease.cn
			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
			 if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %50==0){
				 Thread.sleep(1000*30);//系统休眠30秒
			 }
			 TongJi.tongJiCount ++;
			 //现在使用新版本里面的方法
			 String htmlContent = HtmlContentCommonMethod.htmlContent(listPersonsInfo);
			 Map<String,Object> mp = listPersonsInfo.get(0);
			 String deptName =  StringUtil.objToString(mp.get("DEPTNAME"));
			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
			 String title = "员工试用期期满提醒通知(此邮件为系统发送，请勿回复){测试}";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 String logoPath = pr.getProperty("logoPath");
			 String fromAddress = pr.getProperty("fromAddress");
			 String[] ccs = null;
//			 if(emails.equals("yongbotong@creditease.cn")){
//				 ccs = new String[]{"nadong@creditease.cn","jingma4@creditease.cn"};//董娜是综合管理部负责人,技术部没有BP人员
//			 }else{
//				 ccs = new String[]{"shuoliu@creditease.cn","jingma4@creditease.cn"};
//			 }
			String fnumber = StringUtil.objToString(mpp.get("DEPTCODE"));
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
//				String[] addcopys = mails.split(",");
//				ccs = mails.split(",");
//				System.out.println("ccs: "+mails);
				//测试用	一下六行代码显示打印真正的抄送人及其邮箱
				String[] realMails = mails.split(",");
				String[] realNames = names.split(",");
				System.out.println("************************真正的抄送人及其邮箱****************************");
				for(int i = 0; i < realMails.length; i++){
					System.out.println(realNames[i]+"---->"+realMails[i]);
				}
				System.out.println("***********************************************************************");
			}		
//			 System.out.println("真的抄送人ccs\t" + ccs);
			 //真正发送的时候需要将这个地方改一下
			 String[] toAddress = pr.getProperty("toAddress").split(",");;
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(mpp.get("FNUMBER").toString(),1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(mpp.get("FNUMBER").toString(),2,1);//是否个贷  06GDXSF
			 if(black2!=null && ( black2.getWarntype()==2 || black2.getWarntype()==1 ) && black2.getModeid()==1){//全部或转正
				emails = black2.getRaplacemail();
				personNames = black2.getRaplacename();
				System.err.println(black2.getWarntype()+"代收:"+mpp.get("FNUMBER").toString()+":"+personNames+":"+emails);
			 }
			 System.out.println("fromAddress:  "+fromAddress);
			 ccs = pr.getProperty("ccs").split(",");//制造假的抄件人
			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中,添加logo
			 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath,ccs);
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(2);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
			 waringDetail.setDepartid("");//部门id
			 waringDetail.setDepartname(deptName);//部门名称
			 waringDetail.setPostid("");//职位id
			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids(personIds);//收件人IDS
			 waringDetail.setReceiver(personNames);//接收人：张三
			 waringDetail.setEmail(emails);//收件人邮箱
			 waringDetail.setCopyids(mails);
			 waringDetail.setCopyperson(names);//抄送人
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	@Override
	public void sendMailInfosMergeTest(Map<String,List<Map<String,Object>>> mpPerson) {
		List<Map<String,Object>> listUpTemp = mpPerson.get("PERSONINFO");
		Map<String,Object> mpToPerson = listUpTemp.get(0);//这里面存的是收件人的相关的信息
		if(null == mpToPerson||mpToPerson.size()==0){
			return;
		}
		try{
			 //发送邮件：直接负责人，间接负责人
			 Object  objEmails = mpToPerson.get("CFMAIL")==null?mpToPerson.get("EMAIL"):mpToPerson.get("CFMAIL");//收件人邮箱
			 String emails = objEmails == null?null:objEmails.toString();
			 String personIds = mpToPerson.get("PERSONID").toString();
			 String personName = mpToPerson.get("PERSONNAME").toString();
			 String pnumber = mpToPerson.get("PERSONCODE").toString(); 
			 BlackListInfo black1 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,1,3);//是否个贷  06GDXSF
			 if(black1!=null && black1.getWarntype()==1 && black1.getModeid()==3){
		    	  System.err.println(black1.getPname()+": 全部不收");
		    	  return;
		     }
			 BlackListInfo black2 = blackListInfoServiceImpl.selectBlackListInfoByPnumber(pnumber,2,2);//是否个贷  06GDXSF
			 if(black2!=null && ( black2.getWarntype()==2 || black2.getWarntype()==1 ) && black2.getModeid()==2){//全部或转正
//				 List<Map<String,Object>> immPerson = mpPerson.get("immDownPerson");//直接下属
//				 List<Map<String,Object>> indirPerson = mpPerson.get("indirDownPerson");//间接下属
				 mpPerson.put("indirDownPerson", null);
				 if(null != mpPerson.get("immDownPerson") && 0 < mpPerson.get("immDownPerson").size()){//直下为空
					 return;
				 }
				 System.err.println(personName+": 间下不收");
			 }
			 System.out.println("查看真实的收件人\t" + emails);
			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
			 if(TongJi.tongJiCount!=0 && TongJi.tongJiCount %50==0){
				 Thread.sleep(1000*30);//系统休眠30秒
			 }
			 TongJi.tongJiCount ++;
			 List<Map<String,Object>> immPerson = mpPerson.get("immDownPerson");//直接下属
			 List<Map<String,Object>> indirPerson = mpPerson.get("indirDownPerson");//间接下属
			 if((null == immPerson || 1 > immPerson.size()) && ( null == indirPerson  || 1 > indirPerson.size())){
			 	return;
			 }
			 //现在使用新版本里面的方法
			 String htmlContent = HtmlContentCommonMethod.htmlContentImitate(mpPerson);
			 String reletivePath = FileReadUtil.writeFileImitate(htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
			 String title = "员工试用期期满提醒通知(此邮件为系统发送，请勿回复){测试}";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 String logoPath = pr.getProperty("logoPath");
			 String fromAddress = pr.getProperty("fromAddress");//发件人
			 String[] toAddress = pr.getProperty("toAddress").split(",");//假的收件人
			 System.out.println("fromAddress:  "+fromAddress);
			 System.out.println("toAddress:  "+toAddress);
			 //根据key查询出来直接上级，间接上级，：将信息插入到明细表中,添加logo
			 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress,toAddress , title, htmlContent, logoPath);
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(2);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
//			 waringDetail.setDepartid("");//部门id
//			 waringDetail.setDepartname(deptName);//部门名称
//			 waringDetail.setPostid("");//职位id
//			 waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids(personIds);//收件人IDS
			 waringDetail.setReceiver(personName);//接收人：张三
			 waringDetail.setEmail(emails);//收件人邮箱
//			 waringDetail.setCopyids("");
//			 waringDetail.setCopyperson("");//抄送人
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename("转正人员名单.txt");//文件名称：先写死
			 if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			 }else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			 }
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
}
