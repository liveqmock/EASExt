package com.creditease.eas.test;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.MyAuthenticator;
import com.creditease.eas.util.mail.RSAUtil;
import com.creditease.eas.util.mail.SimpleMailSender;
public class MailSend0914 {
	 private static Logger log = Logger.getLogger(SimpleMailSender.class);
	private static RSAUtil t = new RSAUtil();
	public static boolean sendHtmlMail22(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
//		System.out.println("USERNAME:" + pro.get("USERNAME"));
//		System.out.println("PASSWORD:" + pro.get("PASSWORD"));
		// 如果需要身份认证，则创建一个密码验证器
		if (pro.get("mail.smtp.auth").equals("true")) {
//			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
//    	Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
//    	每次都生成一个session，而不是单例
    	Session sendMailSession = Session.getInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent("中文乱码ccd<br/>,<img src='cid:xx.jpg'>", "text/html;charset=utf-8");  
			//4.设置邮件图片1  
			MimeBodyPart image = new MimeBodyPart();  
	        image.setDataHandler(new DataHandler(new FileDataSource(mailInfo.getImageName())));  //javamail jaf  
	        image.setContentID("xx.jpg"); 
	        
			mainPart.addBodyPart(html);
			mainPart.addBodyPart(image);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			mailMessage.saveChanges(); //保存更新  
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
            log.error("Mail send error", ex);
		}
		return false;
	}
}
