package com.creditease.eas.util.mail;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.Utils;
public class SendMailUtil {
	//public static final String SERVERHOST = "smtp.creditease.cn";
	//public static final String SERVERHOST = "smtp.163.com";
	//public static final String mailServerPort = "25";
	//public static final boolean validate = true;
	public static final String USERNAME = "gaoquanyang@creditease.cn";
	/**
	 * 发送简单的邮件
	 * @throws UnsupportedEncodingException 
	 */
	public static  boolean sendMail(String fromAddress,String toAddress,String title,String htmlContent) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		//mailInfo.setMailServerHost(SERVERHOST);
		//mailInfo.setMailServerPort(mailServerPort);
		//mailInfo.setValidate(validate);//是否验证
//		mailInfo.setUserName(USERNAME);  // 邮箱账户
//		mailInfo.setPassword(PASSWORD);// 邮箱密码
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMail(mailInfo);// 发送html格式
		return flag;
	}
	
	public static  boolean sendImageMail(String fromAddress,String toAddress,String title,String imageName){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
	//	mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMail22(mailInfo);
		return flag;
	}
	/**
	 * 
	 * 描述：发送邮件给多个人
	 * 2013-1-11 上午11:14:27 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @return
	 */
	public static  boolean sendMailToManyPerson(String fromAddress,String[] toAddress,String title,String htmlContent){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMailToManyPerson(mailInfo);// 发送html格式邮件，发送给多个人
		return flag;
	}
	/**
	 * 
	 * 描述：发送邮件给多个人,添加logo
	 * 2013-1-11 上午11:14:27 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @return
	 */
	public static  boolean sendMailToManyPersonAddLogo(String fromAddress,String[] toAddress,String title,String htmlContent,String imageName){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMailAndCopyAddLogo(mailInfo);
		return flag;
	}
	/**
	 * 
	 * 描述：发送邮件附件
	 * 2013-8-2 下午03:08:06 by Administrator
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param filename
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static  boolean sendFileMail(String fromAddress,String[] toAddress,String title,String filename) throws UnsupportedEncodingException{
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
//		mailInfo.setMailServerHost(SERVERHOST);
//		mailInfo.setMailServerPort(mailServerPort);
//		mailInfo.setValidate(validate);//是否验证
//		mailInfo.setUserName(USERNAME);  // 邮箱账户
//		mailInfo.setPassword(PASSWORD);// 邮箱密码
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setFilename(filename);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendFileMail(mailInfo);// 发送html格式
		return flag;
	}
	
	/**
	 * 
	 * 描述：发送邮件给多个人,添加logo,并带有抄送人
	 * 2013-1-11 上午11:14:27 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @return
	 */
	public static  boolean sendMailToManyPersonAddLogo(String fromAddress,String[] toAddress,String title,String htmlContent,String imageName,String[] ccs){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setCcs(ccs);
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMailAndCopyAddLogo(mailInfo);
		return flag;
	}
	/**
	 * 
	 * 描述：发送邮件给多个人,添加logo,并带有抄送人
	 * 2013-1-11 上午11:14:27 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @return
	 */
	public static  boolean sendComplianceMail(String fromAddress,String[] toAddress,
			String title,String htmlContent,String imageName,String[] ccs){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setCcs(ccs);
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendComplianceMail(mailInfo);
		return flag;
	}
//	public static void main(String[] args) {
//		String fromAddress,String[] toAddress,String title,String htmlContent,String imageName,String[] ccs
//		String fromAddress = "gaoquan@creditease.cn";
//		String[] toAddress = new String[]{"gaoquanyang@creditease.cn","626264481@qq.com"};
//		String title = "测试";
//		String htmlContent = "good";
//		String imageName = "d:/app/tomcat-eas/easfile/logo.png";
//		String[] ccs  = new String[]{"xiaoquan328@163.com","826037482@qq.com"};
//		boolean va = sendMailToManyPersonAddLogo(fromAddress,toAddress,title,htmlContent,imageName,ccs);
//		System.out.println(va);
//	}
	/**
	 * 
	 * 描述：
	 * 2013-1-10 下午03:11:29 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @param ccs：抄送人
	 * @return
	 */
	public static  boolean sendMailAndCopy(String fromAddress,String toAddress,String title,String imageName,String[] ccs){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
//		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		mailInfo.setCcs(ccs);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMailAndCopy(mailInfo);// 发送html格式
		return flag;
	}
	/**
	 * 
	 * 描述：获得试用期到期人员的html信息(&nabla;向下的符号)
	 * 2013-1-3 下午12:18:35 by ygq
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContent(List<Map<String,Object>> list){
		String htmlContent = "各位好！<br/>"+
		"根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。<br/>"+
		"为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。<br/>"+ 
		"以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：<br/>";
		String html = "<div><table style='border-collapse: collapse;width:960px;' cellpadding='5' border='1'>" +
				"<tr bgcolor='#0066cc' align='center'><td height='20'><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>员工姓名</font></td><td><font color='white' size='2'>员工编号</font></td><td><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>城市</font></td><td><font color='white' size='2'>岗位</font></td><td><font color='white' size='2'>入职日期</font></td><td bgcolor='orange'><font color='white' size='2'>状态</font></td></tr>";
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String personname = (String)mp.get("PERSONNAME");
			String personcode = (String)mp.get("PERSONCODE");
			String fdisplayname = getDisplayMethod(mp.get("FDISPLAYNAME"));
			String city = (mp.get("CITY")==null)?"":mp.get("CITY").toString();//地址
			String positionname = (String)mp.get("POSITIONNAME");
			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));
			int entmonth = DateUtil.monthsBetween(fenterdate);
			String deptname = (String)mp.get("DEPTNAME");
			String zhuanzheng = "";
			html += "<tr><td><font size='3'>" +fdisplayname+ "</font></td><td><font size='3'>" + personname + "</font></td><td><font size='3'>"+personcode+"</font></td><td><font size='3'>"+deptname+"</font></td><td><font size='3'>" +city+"</font></td>" +
					"<td><font size='3'>" +positionname + "</font></td><td><font size='3'>" +fenterdate+ "</font></td>";
			if(entmonth <6){
				html += "<td><font color='red'  size='3'>试用期将到期</font></td></tr>";
			}else{
				html += "<td><font color='red'  size='3'>试用期已到期</font></td></tr>";
			}
		}
		html += "</table></div>";
		html += "附：员工转正审批流程：<br/>"+
			"1、各部门人事接口人收到提醒邮件后，立即梳理下月转正人员信息，提交本部门/团队负责人确认是否同意为员工办理转正手续；<br/>"+
			"2、由本部门/团队负责人根据员工实际工作情况进行评估、审批及办理后续手续：<br/>"+
			"▉  如部门同意员工转正：<br/>"+
			"&nbsp;&nbsp;&nbsp;a.员工直接主管安排与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交其上级予以审批；<br/>"+
			"&nbsp;&nbsp;&nbsp;b.员工直接主管、部门负责人完成签批手续后将附件直接提交人力资源与行政部（外埠地区将附件扫描件以邮件形式予以呈报）；<br/>"+
			"&nbsp;&nbsp;&nbsp;c.如该转正申请未通过公司级审批，HR将予以邮件反馈。<br/>"+
			"▉ 如部门不同意员工转正：请部门务必于试用期到期前与员工协商一致、并依据公司相关规定办理正式离职手续。<br/>"+
			"感谢您对人力资源与行政部工作的大力支持！ 谢谢。<br/>";
		htmlContent += html;
		return htmlContent;
	}
	/**
	 * 
	 * 描述：获得合同到期人员的html信息
	 * 2013-1-3 下午12:18:35 by ygq
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContentForContract(List<Map<String,Object>> list){
		String htmlContent = "各位好！下面是合同即将到期的人员的名单:<br/><div style='width:900px;height:500px;text-align:center;font-size:20px;'>"
			+"<b>合同到期人员名单</b><div>";
		String html = "<div><table style='border-collapse: collapse;' cellpadding='5' border='1' align='center'>" +
				"<tr bgcolor='#0066cc' align='center'><td height='20'><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>员工姓名</font></td><td><font color='white' size='2'>员工编号</font></td><td><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>城市</font></td><td><font color='white' size='2'>岗位</font></td><td><font color='white' size='2'>入职日期</font></td><td bgcolor='orange'><font color='white' size='2'>状态</font></td></tr>";
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String personname = (String)mp.get("PERSONNAME");
			String personcode = (String)mp.get("PERSONCODE");
			String fdisplayname = getDisplayMethod(mp.get("FDISPLAYNAME"));
			String city = (mp.get("CITY")==null)?"":mp.get("CITY").toString();//地址
			String positionname = (String)mp.get("POSITIONNAME");
			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));
			String deptname = (String)mp.get("DEPTNAME");
			String zhuanzheng = "";
			html += "<tr><td><font size='3'>" +fdisplayname+ "</font></td><td><font size='3'>" + personname + "</font></td><td><font size='3'>"+personcode+"</font></td><td><font size='3'>"+deptname+"</font></td><td><font size='3'>" +city+"</font></td>" +
					"<td><font size='3'>" +positionname + "</font></td><td><font size='3'>" +fenterdate+ "</font></td>" +
					"<td><font color='red'  size='3'>合同将到期</font></td></tr>";
		}
		html += "</table></div>";
		htmlContent += html;
		return htmlContent;
	}
	
	public static String imageContent(String name,String personid) throws IOException{
		//String htmlContent = null;
		//String filePath =ServletActionContext.getRequest().getRealPath("/");
		 MailSenderInfo mailInfo = new MailSenderInfo();
		String filePath = mailInfo.getProperties().getProperty("filepath");
		String f1 =mailInfo.getProperties().getProperty("myImage");
		 String f2 =filePath +  "upload/uploadImages/";
		 File file1 = new File(f1);
		//创建文件夹
//         if(!file1.exists()){
//         	file1.mkdirs();
//         }
//		 String oscode=System.getProperty("file.encoding");   //是取得系统的编码方式。
//		 name=new String(name.getBytes("GBK"),oscode);
//		 System.err.println("name: "+name);
//		 System.out.println("name::::::" + name);
//		 ServletActionContext.getRequest().getSession().setAttribute("name", name);
	       BufferedImage image = ImageIO.read(file1);
	  	   Graphics g = image.getGraphics();
	  	   g.setFont(new Font("华文行楷",Font.BOLD,23));
	  	   g.setColor(Color.blue);
	  	   g.drawString(name, 20, 442);
	  	   
	  	   
	  	   //写文件
	  	 f2 +=Utils.getCurrentDate().substring(0,4)+"/"+ Utils.getCurrentDate()+"/";
	  	//创建文件夹
	  	 File file2 = new File(f2);
         if(!file2.exists()){
         	file2.mkdirs();
         }
         //创建文件
         String imgtmp = Utils.getCurrentDate().substring(0,4) + personid+".jpg";
	  	 f2+=imgtmp;
         File file3 = new File(f2);
         ImageIO.write(image, "JPEG", file3);
	  	// htmlContent  = "<div><b><IMG SRC='"+file2+"'   style='width:576px;height:433px;' ><br></b></div>";
		 return f2;
	}
	
	
	/**
	 * 
	 * 描述：获取中心的部门名称
	 * 2013-1-10 下午02:45:08 by ygq
	 * @version
	 * @param fdisplayname
	 * @return
	 */
	public static String getDisplayMethod(Object fdisplayname){
		if(fdisplayname == null){
			return "";
		}
		String[] strs = fdisplayname.toString().split("_");
		if(strs.length>=3){
			return strs[2];
		}else if(strs.length==2){
			return strs[1];
		}else{
			return strs[0];
		}
	}
	/**
	 * 
	 * 描述：试用期是否已经超过6月了
	 * 2013-1-10 下午02:46:35 by ygq
	 * @version
	 * @return
	 */
	public static boolean ifHasOverSixMonth(String enterDate){
		return false;
	}
	/***
	 * 
	 * 描述：邮箱格式验证
	 * 2013-1-10 下午02:43:39 by ygq
	 * @version
	 * @param mail
	 * @return
	 */
	public static boolean mailValidate(String mail){
		 Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		 Matcher matcher = pattern.matcher(mail);
		 boolean va = matcher.matches();
		 return va;
	}
	/**
	 * 
	 * 描述：将收件人数组转换成固定格式的字符串存储
	 * 2013-1-11 下午01:57:29 by ygq
	 * @version
	 * @param toAddress
	 * @return
	 */
	public static String convertReceiversToString(String[] toAddress){
		String address = "";
		if(null != toAddress && toAddress.length>0){
			for(int i=0;i<toAddress.length;i++){
				address += toAddress[i] + ";";
			}
		}
		return address;
	}
	/**
	 * 
	 * 描述：将收件人数组转换成固定格式的字符串存储
	 * 2013-1-16 下午03:48:25 by xjw
	 * @version
	 * @param toAddress
	 * @return
	 */
	public static String convertReceiversToString2(String[] toAddress){
		String address = "";
		if(null != toAddress && toAddress.length>0){
			for(int i=0;i<toAddress.length;i++){
				address +=","+ toAddress[i]  ;
			}
		}
		
		if (",".equals(address.substring(0, 1))) {
			address = address.replaceFirst(",", "");
		}
		return address;
	}
	
	/**
	 * 
	 * 描述：除了文件的正文，还包含图片
	 * 2013-1-10 下午03:11:29 by ygq
	 * @version
	 * @param fromAddress
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @param ccs：抄送人
	 * @return
	 */
	public static  boolean sendMailAndCopyForRegualr(String fromAddress,String toAddress,String title,String htmlContent,String imageName,String[] ccs){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		mailInfo.setCcs(ccs);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMailAndCopyAddLogo(mailInfo);// 发送html格式
		return flag;
	}
}
