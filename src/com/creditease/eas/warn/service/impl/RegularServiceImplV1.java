///**
// * 
// */
//package com.creditease.eas.warn.service.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Map.Entry;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.creditease.eas.util.FileReadUtil;
//import com.creditease.eas.util.StringUtil;
//import com.creditease.eas.util.TongJi;
//import com.creditease.eas.util.mail.HtmlContentCommonMethod;
//import com.creditease.eas.util.mail.MailSenderInfo;
//import com.creditease.eas.util.mail.SendMailUtil;
//import com.creditease.eas.warn.bean.WaringDetail;
//import com.creditease.eas.warn.dao.WaringDetailMapper;
//import com.creditease.eas.warn.kingdee.query.CommonPersonInfoSerachQuery;
//import com.creditease.eas.warn.service.RegularService;
///**
// * 这个是需求变化前的最后一个版本
// * @VariablesServiceImpl.java
// * created at 2012-12-26 下午04:01:37 by xjw
// * 
// * @author xjw({@link authorEmail})
// * @version $Revision$</br>
// * update: $Date$
// */
//@Service
//public class RegularServiceImplV1 implements RegularService{
//	private static final Logger logger = Logger.getLogger(RegularServiceImplV1.class);
//	@Autowired
//	private WaringDetailMapper waringdetailMapper;
//	@Override
//	public boolean queryPersonInfo() throws Exception{
////		List<String> listAll = sendMails();
////		System.out.println(listAll.size());
////		for(int i=0;i<listAll.size();i++){
////			System.out.print(listAll.get(i) + ";");
////		}
//		return true;
//	}
//	/**
//	 * 
//	 * 描述：插入数据,并发送邮件
//	 * 2013-1-3 下午12:35:30 by ygq
//	 * @version
//	 * @return
//	 */
////	private List<String> sendMails() throws Exception{
////		List<String> listAll = new ArrayList<String>();
////		Map<String,List<Map<String,Object>>> map = QualifyingMatureQuery001.queryQualifyPersonsInfo();//查询出来的是人员的信息
////		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
////			 String key = entry.getKey();//现在的key
////			/*********************处理需要转正的人员的信息******************************/
////			 List<Map<String,Object>> listPersonsInfo = entry.getValue();
////			 //这块的信息是显示哪些人需要转正用的
////			 String htmlContent = SendMailUtil.htmlContent(listPersonsInfo);
////			 Map<String,Object> mp = listPersonsInfo.get(0);
////			 String deptName =  mp.get("DEPTNAME").toString();
////			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContent,"regular","转正人员名单");
////			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中
////			 //发送邮件：直接负责人，间接负责人,部门负责人
////			  String title = deptName + "转正人员名单";
////			 //根据key，查询出来需要向你哪些人员发送信息
//////			  String fromAddress = "gaoquanyang@creditease.cn";
//////			  String toAddress = "gaoquanyang@creditease.cn";
//////			  SendMailUtil.sendMail(fromAddress, toAddress, title, htmlContent);
////				//查询出来直接上级，间接上级，部门负责人
////				//注意一点：
////				//如果邮箱信息查询不到，就查备用邮箱的信息
////				//这里需要判断一下
////				//将数据插入到waringDetail中
////			   //*********************处理发送给直接上级，间接上级，部门负责人的信息（部门负责人肯定不会为空）******************************/
////				// List<Map<String,Object>> mapHig = QualifyingMatureQuery001.queryHighPersonByPositionId(key);//查询他们的上级
////				//sendMailToHigh(mapHig,deptName,htmlContent,reletivePath);
////				//根据key，查询出来需要向你哪些人员发送信息
////				//查询出来直接上级，间接上级，部门负责人
////				//注意一点：
////				//如果邮箱信息查询不到，就查备用邮箱的信息
////				//这里需要判断一下
////				//将数据插入到waringDetail中
////				 WaringDetail waringDetail = new WaringDetail();
////				 waringDetail.setTypeid(2);//预警类别
////				 waringDetail.setWayid(2);//发送方式
////				 waringDetail.setSendtime(new Date());//发送时间
////				 waringDetail.setDepartid("");//部门id
////				 waringDetail.setDepartname(deptName);//部门名称
////				 waringDetail.setPostid("");//职位id
////				 waringDetail.setPostname("");//职位名称 
////				 waringDetail.setReceiverids("");//收件人IDS
////				 waringDetail.setReceiver("王三");//接收人：张三
////				 waringDetail.setEmail("xiaoquan328@163.com");//收件人邮箱
////				 waringDetail.setCopyids("");
////				 waringDetail.setCopyperson("");//抄送人
////				 waringDetail.setTheme(title);//
////				 waringDetail.setContentaddress(reletivePath);//文件路径，
////				 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
////				 waringDetail.setIssend(1);//成功了
////				 waringDetail.setSendcount(1);//默认是一次
////				 waringDetail.setCreatime(new Date());//创建日期
////				 /*****************************************短信内容:*****************/
////				 //waringDetail.setCell("13070189337");//电话
////				 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
////				 //waringDetail.setEnterdate(new Date());//入职日期?
////				 //waringDetail.setCellcontent(cellcontent);//
////				 waringdetailMapper.insert(waringDetail);
////		 }
////		 return listAll;
////	}
//	@Override
//	public void sendMailInfos(Entry<String,List<Map<String,Object>>> entry){
//		try{
//			 List<Map<String,Object>> listHig = CommonPersonInfoSerachQuery.queryHighPersonByPositionId(entry.getKey());//查询上级部门人员
//			 if(listHig == null||listHig.isEmpty()){//如果直接上级，间接上级，部门负责人都是空,程序不再往下执行
//					return;
//			 }
//			 //发送邮件：直接负责人，间接负责人,部门负责人
//			 String emails = "";//收件人邮箱
//			 String personIds = "";//收件人IDS
//			 String personNames = "";//收件人名字
//			 for(int i=0;i<listHig.size();i++){
//				Map<String,Object> mpp =  listHig.get(i);
//				Object cfmail = mpp.get("CFMAIL")==null?null:mpp.get("CFMAIL");//邮箱
//				if(null != cfmail){
//					emails += cfmail + ";";
//					personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
//					personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
//				}else{//取个人邮箱
//					cfmail = mpp.get("EMAIL")==null?null:mpp.get("EMAIL");//邮箱
//					if(null != cfmail){
//						emails += cfmail + ";";
//						personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
//						personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
//					}
//				}
//			 }
//			 if(emails.equals("")){//如果收件人一个邮件都没有，就放弃发送邮件,程序不再往下执行
//				 //System.out.println("放弃了");
//				 return;
//			 }
//			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
//			 if(TongJi.tongJiCount %50==0){
//				 Thread.sleep(1000*30);//系统休眠30秒
//			 }
//			 TongJi.tongJiCount ++;
//			 List<Map<String,Object>> listPersonsInfo = entry.getValue();
//			// String htmlContent = SendMailUtil.htmlContent(listPersonsInfo);//这块的信息是显示哪些人需要转正用的(版本1)
//			 //现在使用新版本里面的方法
//			 String htmlContent = HtmlContentCommonMethod.htmlContent(listPersonsInfo);
//			 Map<String,Object> mp = listPersonsInfo.get(0);
//			 String deptName =  StringUtil.objToString(mp.get("DEPTNAME"));
//			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
//			 String title = "员工试用期转正提醒通知";
//			 //String title = "重要！！员工试用期转正提醒通知";
////			 String fromAddress = "hr@mail.creditease.cn";
//			 //这里读取的是真实的人员的信息
//			 MailSenderInfo ms = new MailSenderInfo();
//			 Properties pr = ms.getProperties();
//			// String[] toAddress = emails.split(";");//这里读取的是真实的人员的信息
//			 
//			 String[] toAddress = pr.getProperty("mailInfos").split(",");//测试下
//			 
//			 String logoPath = pr.getProperty("logoPath");
//			 String fromAddress = pr.getProperty("fromAddress");
//			 System.out.println("fromAddress:  "+fromAddress);
//			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中
//			 //发送邮件：直接负责人，间接负责人,部门负责人
//			 //插入数据的测试
//			 //发送邮件给多个人:测试
//			 //SendMailUtil.sendMailToManyPerson(fromAddress, toAddress, title, htmlContent);
//			 //最新版本的，添加logo
//			 SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath);
//			 WaringDetail waringDetail = new WaringDetail();
//			 waringDetail.setTypeid(2);//预警类别
//			 waringDetail.setWayid(2);//发送方式
//			 waringDetail.setSendtime(new Date());//发送时间
////			 waringDetail.setDepartid("");//部门id
////			 waringDetail.setDepartname(deptName);//部门名称
////			 waringDetail.setPostid("");//职位id
////			 waringDetail.setPostname("");//职位名称 
//			 waringDetail.setReceiverids(personIds);//收件人IDS
//			 waringDetail.setReceiver(personNames);//接收人：张三
//			 waringDetail.setEmail(emails);//收件人邮箱
////			 waringDetail.setCopyids("");
////			 waringDetail.setCopyperson("");//抄送人
//			 waringDetail.setTheme(title);//
//			 waringDetail.setContentaddress(reletivePath);//文件路径，
//			 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
//			 waringDetail.setIssend(1);//成功了
//			 waringDetail.setSendcount(1);//默认是一次
//			 waringDetail.setCreatime(new Date());//创建日期
//			 /*****************************************短信内容:*****************/
//			 //waringDetail.setCell("13070189337");//电话
//			 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
//			 //waringDetail.setEnterdate(new Date());//入职日期?
//			 //waringDetail.setCellcontent(cellcontent);//
//			 waringdetailMapper.insert(waringDetail);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			TongJi.tongJiExceptionCount ++;
//			logger.error(ex.getMessage());
//		}
//	}
////	private List sendMailToHigh(List<Map<String,Object>> mapHig,String deptName,String htmlContent,String reletivePath){
////		String toAddress = "gaoquanyang@creditease.cn";
////		if(null != mapHig && mapHig.size()>0){
////			for(int i=0;i<mapHig.size();i++){
////				Map<String,Object> mph = mapHig.get(i);
////				//邮箱的有效性的验证
////				Object mail = (mph.get("CFMAIL")==null)?mph.get("EMAIL"):mph.get("CFMAIL");
////				List<String> list = new ArrayList<String>();
////				if(mail != null && !"".equals(mail)){
////					list.add(toAddress);
////					String pname = StringUtil.objToString(mph.get("PNAME"));
////					String title = deptName + "转正人员名单";
////					//根据key，查询出来需要向你哪些人员发送信息
////					//查询出来直接上级，间接上级，部门负责人
////					//注意一点：
////					//如果邮箱信息查询不到，就查备用邮箱的信息
////					//这里需要判断一下
////					//将数据插入到waringDetail中
////					 WaringDetail waringDetail = new WaringDetail();
////					 waringDetail.setTypeid(2);//预警类别
////					 waringDetail.setWayid(2);//发送方式
////					 waringDetail.setSendtime(new Date());//发送时间
////					 waringDetail.setDepartid("");//部门id
////					 waringDetail.setDepartname(deptName);//部门名称
////					 waringDetail.setPostid("");//职位id
////					 waringDetail.setPostname("");//职位名称 
////					 waringDetail.setReceiverids("");//收件人IDS
////					 waringDetail.setReceiver(pname);//接收人：张三
////					 waringDetail.setEmail(toAddress);//收件人邮箱
////					 waringDetail.setCopyids("");
////					 waringDetail.setCopyperson("");//抄送人
////					 waringDetail.setTheme(title);//
////					 waringDetail.setContentaddress(reletivePath);//文件路径，
////					 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
////					 waringDetail.setIssend(1);//成功了
////					 waringDetail.setSendcount(1);//默认是一次
////					 waringDetail.setCreatime(new Date());//创建日期
////					 /*****************************************短信内容:*****************/
////					 //waringDetail.setCell("13070189337");//电话
////					 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
////					 //waringDetail.setEnterdate(new Date());//入职日期?
////					 //waringDetail.setCellcontent(cellcontent);//
////					 waringdetailMapper.insert(waringDetail);
////				}
////			}
////		}
////		return null;
////	}
//	/**************************************************测试系统用的代码*************************************************************************************/
//	@Override
//	public void sendMailInfosTest(Entry<String,List<Map<String,Object>>> entry){
//		try{
//			 List<Map<String,Object>> listHig = CommonPersonInfoSerachQuery.queryHighPersonByPositionId(entry.getKey());//查询上级部门人员
//			 if(listHig == null||listHig.isEmpty()){//如果直接上级，间接上级，部门负责人都是空,程序不再往下执行
//					return;
//			 }
//			 //发送邮件：直接负责人，间接负责人,部门负责人
//			 String emails = "";//收件人邮箱
//			 String personIds = "";//收件人IDS
//			 String personNames = "";//收件人名字
//			 for(int i=0;i<listHig.size();i++){
//				Map<String,Object> mpp =  listHig.get(i);
//				Object cfmail = mpp.get("CFMAIL")==null?null:mpp.get("CFMAIL");//邮箱
//				if(null != cfmail){
//					emails += cfmail + ";";
//					personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
//					personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
//				}else{//取个人邮箱
//					cfmail = mpp.get("EMAIL")==null?null:mpp.get("EMAIL");//邮箱
//					if(null != cfmail){
//						emails += cfmail + ";";
//						personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
//						personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
//					}
//				}
//			 }
//			 if(emails.equals("")){//如果收件人一个邮件都没有，就放弃发送邮件,程序不再往下执行
//				 //System.out.println("放弃了");
//				 return;
//			 }
//			 System.out.println("emails::::::" + emails);
//			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
//			 if(TongJi.tongJiCount %50==0){
//				 Thread.sleep(1000*30);//系统休眠30秒
//			 }
//			 TongJi.tongJiCount ++;
//			 List<Map<String,Object>> listPersonsInfo = entry.getValue();
//			// String htmlContent = SendMailUtil.htmlContent(listPersonsInfo);//这块的信息是显示哪些人需要转正用的(版本1)
//			 //现在使用新版本里面的方法
//			 String htmlContent = HtmlContentCommonMethod.htmlContent(listPersonsInfo);
//			 Map<String,Object> mp = listPersonsInfo.get(0);
//			 String deptName =  StringUtil.objToString(mp.get("DEPTNAME"));
//			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContent,"regular","重要！！员工试用期转正提醒通知");//取
//			 String title = "员工试用期转正提醒通知";
//			 //String title = "重要！！员工试用期转正提醒通知";
//			 //String fromAddress = "hr@mail.creditease.cn";
//			 MailSenderInfo ms = new MailSenderInfo();
//			 Properties pr = ms.getProperties();
//			 String[] toAddress = pr.getProperty("mailInfos").split(",");
//			 String logoPath = pr.getProperty("logoPath");
//			 String fromAddress = pr.getProperty("fromAddress");
//			 System.out.println("fromAddress:  "+fromAddress);
//			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中
//			 //发送邮件：直接负责人，间接负责人,部门负责人
//			 //插入数据的测试
//			 //发送邮件给多个人:测试
//			 //SendMailUtil.sendMailToManyPerson(fromAddress, toAddress, title, htmlContent);
//			 //最新版本的，添加logo
//			 SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContent, logoPath);
//			 WaringDetail waringDetail = new WaringDetail();
//			 waringDetail.setTypeid(2);//预警类别
//			 waringDetail.setWayid(2);//发送方式
//			 waringDetail.setSendtime(new Date());//发送时间
////			 waringDetail.setDepartid("");//部门id
////			 waringDetail.setDepartname(deptName);//部门名称
////			 waringDetail.setPostid("");//职位id
////			 waringDetail.setPostname("");//职位名称 
//			 waringDetail.setReceiverids(personIds);//收件人IDS
//			 waringDetail.setReceiver(personNames);//接收人：张三
//			 waringDetail.setEmail(emails);//收件人邮箱
////			 waringDetail.setCopyids("");
////			 waringDetail.setCopyperson("");//抄送人
//			 waringDetail.setTheme(title);//
//			 waringDetail.setContentaddress(reletivePath);//文件路径，
//			 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
//			 waringDetail.setIssend(1);//成功了
//			 waringDetail.setSendcount(1);//默认是一次
//			 waringDetail.setCreatime(new Date());//创建日期
//			 /*****************************************短信内容:*****************/
//			 //waringDetail.setCell("13070189337");//电话
//			 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
//			 //waringDetail.setEnterdate(new Date());//入职日期?
//			 //waringDetail.setCellcontent(cellcontent);//
//			 waringdetailMapper.insert(waringDetail);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			TongJi.tongJiExceptionCount ++;
//			logger.error(ex.getMessage());
//		}
//	}
//	public static void main(String[] args) throws Exception {
////		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
////		RegularService regularService = (RegularService)app.getBean("regularService");
////		boolean va = regularService.queryPersonInfo();
////		System.out.println("ok" + va);
////		String fromAddress = "xiaoquan328@163.com";
////		String toAddress = "626264481@qq.com";
////		String title = "转正预警";
////		String htmlContent = SendMailUtil.htmlContent(null);
////		boolean va = SendMailUtil.sendMail(fromAddress, toAddress, title, htmlContent);
////		System.out.println("va:::::::" + va);
//	}
//}
