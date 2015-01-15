package com.creditease.eas.test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SimpleMailSender;

public class SendMailUtil0914{
	
	public static  boolean sendImageMail(String fromAddress,String toAddress,String title,String imageName){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
	//	mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		MailSend0914 sms = new MailSend0914();
		boolean flag = sms.sendHtmlMail22(mailInfo);
		return flag;
	}
	public static String imageContent(String name,String personid) throws IOException{
//		 MailSenderInfo mailInfo = new MailSenderInfo();
		 String filePath = "D:\\app\\tomcat-eas\\easfile\\";
		 String  f1 = "D:\\app\\tomcat-eas\\easfile\\birthdayImage.jpg";
		 String f2 =filePath +  "upload/uploadImages/";
		 File file1 = new File(f1);
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
}
	