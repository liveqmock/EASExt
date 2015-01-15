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

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.creditease.eas.util.Utils;
public class SendMailUtil2 {
	public static final String SERVERHOST = "smtp.creditease.cn";
//	public static final String SERVERHOST = "smtp.163.com";
	public static final String mailServerPort = "25";
//	public static final boolean validate = true;
//	public static final String USERNAME = "gaoquanyang@creditease.cn";
//	public static final String PASSWORD = "";
	
	  
	public static  boolean sendMail(String fromAddress,String toAddress,String title,String htmlContent,String filename) throws UnsupportedEncodingException{
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(SERVERHOST);
		mailInfo.setMailServerPort(mailServerPort);
//		mailInfo.setValidate(validate);//是否验证
//		mailInfo.setUserName(USERNAME);  // 邮箱账户
//		mailInfo.setPassword(PASSWORD);// 邮箱密码
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setFilename(filename);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMail(mailInfo);// 发送html格式
		return flag;
	}
	
	public static  boolean sendFileMail(String fromAddress,String[] toAddress,String title,String filename) throws UnsupportedEncodingException{
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(SERVERHOST);
		mailInfo.setMailServerPort(mailServerPort);
//		mailInfo.setValidate(validate);//是否验证
//		mailInfo.setUserName(USERNAME);  // 邮箱账户
//		mailInfo.setPassword(PASSWORD);// 邮箱密码
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
//		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setReceivers(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setFilename(filename);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendFileMail(mailInfo);// 发送html格式
		return flag;
	}
	public static String htmlContentForContractRenewal(List<Map<String,Object>> list){
		String htmlContent = "各位好！下面是合同即将到期的人员的名单:<br/><div style='width:900px;height:500px;text-align:center;font-size:20px;'>"
			+"<b>合同到期人员名单</b><div>";
		String html = "<div><table style='border-collapse: collapse;' cellpadding='5' border='1' align='center'>" +
				"<tr bgcolor='#0066cc' align='center'><td height='20'><font color='white' size='2'>员工编号</font></td><td><font color='white' size='2'>身份证姓名</font></td><td><font color='white' size='2'>城市</font></td><td><font color='white' size='2'>岗位</font></td><td><font color='white' size='2'>城市</font></td><td><font color='white' size='2'>任职资格</font></td><td><font color='white' size='2'>职类</font></td> <td><font color='white' size='2'>人员状态</font></td><td><font color='white' size='2'>入职日期</font></td> <td><font color='white' size='2'>性别</font></td><td><font color='white' size='2'>年龄</font></td><td><font color='white' size='2'>政治面貌</font></td><td><font color='white' size='2'>毕业院校</font></td><td><font color='white' size='2'>专业</font></td><td><font color='white' size='2'>学历</font></td>  <td bgcolor='orange'><font color='white' size='2'>状态</font></td></tr>";
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			
			String pid = mp.get("PERSONNUMBER").toString();
			String name = mp.get("PNAME").toString();
			String city = (mp.get("NPLACE")==null)?"":mp.get("NPLACE").toString(); //城市
			String position = mp.get("POSITION").toString();
			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
			String potype = (mp.get("POTYPE")==null)?"":mp.get("POTYPE").toString();   //职类
			String politics = (mp.get("POLITICS")==null)?"":mp.get("POLITICS").toString();  //政治面貌
			String enterdate = (mp.get("ENTERDATE")==null)?"":mp.get("ENTERDATE").toString(); 
			String sex="";
			Integer gender = Integer.parseInt(mp.get("GENDER").toString());
			if(gender==1)
				sex="男";
			else
				sex="女";
			String age= mp.get("AGE").toString();
			String school = (mp.get("SCHOOL")==null)?"":mp.get("SCHOOL").toString();  //毕业院校
			String specialty = (mp.get("SPECIALTY")==null)?"":mp.get("SPECIALTY").toString(); //专业
			String degreed = (mp.get("DEGREED")==null)?"":mp.get("DEGREED").toString(); //学历
			html += "<tr><td><font size='3'>" +pid+ "</font></td><td><font size='3'>" + name + "</font></td><td><font size='3'>"+city+"</font></td><td><font size='3'>"+position+"</font></td><td><font size='3'>" +city+"</font></td>" +
					"<td><font size='3'>" +posiqual + "</font></td><td><font size='3'>" +pstatus+ "</font></td>" +
					"<td><font size='3'>" +potype + "</font></td><td><font size='3'>" +politics+ "</font></td>" +
					"<td><font size='3'>" +enterdate + "</font></td><td><font size='3'>" +sex+ "</font></td>" +
					"<td><font size='3'>" +age + "</font></td><td><font size='3'>" +school+ "</font></td>" +
					"<td><font size='3'>" +specialty + "</font></td><td><font size='3'>" +degreed+ "</font></td>" +
					"<td><font color='red' size='3'>合同将到期</font></td></tr>";
		}
		html += "</table></div>";
		htmlContent += html;
		return htmlContent;
	}
	
	
	public static String htmlContent3(String name,String title) throws IOException{
		String htmlContent = null;
		//String tmp="yearOfWork.jpg";
		String filePath =ServletActionContext.getRequest().getRealPath("/");
		
		String f1 =filePath+  "upload\\uploadImages\\yearOfWork.jpg";
		 
		 String f2 =filePath +  "upload\\uploadImages\\";
		 
			//htmlContent = "<div style='width:900px;height:500px;text-align:center;font-size:20px;'>"
			//	+"<b>"+title+"</b><div>";
			
			
			 System.err.println("f1: "+f1);
		 	//File f1 = new File("c:\\aaa.jpg");
			 File file1 = new File(f1);
	         if(!file1.exists()){
	         	file1.mkdirs();
	         }
	         
	       BufferedImage image = ImageIO.read(file1);
	  	   Graphics g = image.getGraphics();
	  	   g.setFont(new Font("华文行楷",Font.BOLD,23));
	  	   g.setColor(Color.blue);
	  	   //g.drawString("王晓明", 100, 112);
	  	   g.drawString(name, 20, 442);
	  	   
	  	 f2 += Utils.getCurrentDate()+"\\";
	  	 String imgtmp = name+".jpg";
	  	 f2+=imgtmp;
	  	 File file2 = new File(f2);
         if(!file2.exists()){
         	file2.mkdirs();
         }
//	  	   File f2 = new File("c:\\yx_image\\copy.jpg");
	  	   ImageIO.write(image, "JPEG", file2);
	  	   System.err.println(file2+" : "+f2);
	  	 htmlContent  = "<div><b><IMG SRC='"+file2+"'   style='width:576px;height:433px;' ><br></b></div>";
	  	 System.err.println("htmlContent: "+htmlContent);
		 return htmlContent;
	}
	
	
	
	public static String htmlContent2(String name ) throws IOException{
		 String tmp="yearOfWork.jpg";
//		 String filePath =ServletActionContext.getRequest().getRealPath("/");
//		 filePath +=  "upload/uploadImages/"+tmp;
//		 System.err.println("filePath: "+filePath);
//	 	
//		 File file = new File(filePath);
//         if(!file.exists()){
//         	file.mkdirs();
//         }
       File f1 = new File("c:\\yearOfWork.jpg");
   	   BufferedImage image = ImageIO.read(f1);
	   Graphics g = image.getGraphics();
	   g.setFont(new Font("华文行楷",Font.BOLD,22));
	   g.setColor(Color.black);
	   //g.drawString("王晓明", 100, 112);
	   g.drawString(name, 20, 441);
	   File f2 = new File("c:\\yx_image\\copy.jpg");
	   ImageIO.write(image, "JPEG", f2);
		//String htmlContent = "<div><b><IMG SRC=c:\\yx_image\\copy.jpg  style='width:576px;height:433px;' ><br></b></div>";
	   String htmlContent = "<div><b><IMG SRC='"+f2+"'   style='width:576px;height:433px;' ><br></b></div>";
	  // System.err.println("htmlContent: "+htmlContent);
		return htmlContent;
	}
	
	
	public static void main(String[] args) throws Exception {
		String fromAddress = "HR@creditease.cn";
		//String fromAddress = "tracy_0201@163.com";
		String[] toAddress = {"tracy_0201@163.com"};
		String title = "入职纪念日";
//		sendMail(fromAddress, toAddress, title, htmlContent2("王晓明"),"c:/ccc/a.txt");
		sendFileMail(fromAddress, toAddress, title,"c:/ccc/车贷201308011852.xlsx");
		//htmlContent2("刘华");
//		List<Map<String,Object>> list = ContractRenewalQuery.queryContract();
//		System.err.println( htmlContentForContractRenewal(list) );
	}
}
