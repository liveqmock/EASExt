package com.creditease.eas.util.mail;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
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
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.creditease.eas.util.Utils;
/***
 * 发送邮箱用的公用邮箱
 * @author ygq
 *
 */
public class SimpleMailSender {
	 private static Logger log = Logger.getLogger(SimpleMailSender.class);
	 private static RSAUtil t = new RSAUtil();
	 public static LinkedList getImages(String path, String[] imagesNames)
     throws Exception {
		 LinkedList attachList = new LinkedList();
		 for (int i = 0; i < imagesNames.length; i++) {
		     byte[] buffer = new byte[1024];
		     BufferedInputStream in = new BufferedInputStream(
		             new FileInputStream(path + imagesNames[i]));
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     int ii;
		     while ((ii = in.read()) != -1) {
		         bo.write(ii);
		     }
		     in.close();
		     bo.close();
		     attachList.add(bo.toByteArray());
		 }
		 return attachList;
	}
	 
	 public static void testxx(MailSenderInfo mailInfo)
	 {
		 Properties pro = mailInfo.getProperties();
		 String decodeSignautre = t.decodeSignautre(pro.getProperty("PASSWORD"));
		 System.out.println("decodeSignautre :" + decodeSignautre);
	 }
	 
		public static boolean sendHtmlMail22(MailSenderInfo mailInfo) {
			// 判断是否需要身份认证
			MyAuthenticator authenticator = null;
			Properties pro = mailInfo.getProperties();
//			System.out.println("USERNAME:" + pro.get("USERNAME"));
//			System.out.println("PASSWORD:" + pro.get("PASSWORD"));
			// 如果需要身份认证，则创建一个密码验证器
			if (pro.get("mail.smtp.auth").equals("true")) {
//				authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
				authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
			}
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
//	    	Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
//	    	每次都生成一个session，而不是单例
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
//				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				html.setContent("<img src='cid:xx.jpg'>", "text/html");  
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
	 
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		Properties pro = mailInfo.getProperties();
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
//			authenticator = new MyAuthenticator(mailInfo.getUserName(),
//					mailInfo.getPassword());
//			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
		}
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
			//可以设置多个
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 以HTML格式发送邮件：单人发送邮件
	 * @param mailInfo
	 *  待发送的邮件信息
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
//		System.out.println("USERNAME:" + pro.get("USERNAME"));
//		System.out.println("PASSWORD:" + pro.get("PASSWORD"));
		// 如果需要身份认证，则创建一个密码验证器
		if (pro.get("mail.smtp.auth").equals("true")) {
			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
//			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
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
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			//设置邮件图片 
			MimeBodyPart image = new MimeBodyPart();  
	        image.setDataHandler(new DataHandler(new FileDataSource(mailInfo.getImageName())));  //javamail jaf  
	        image.setContentID("xx.jpg"); 
			mainPart.addBodyPart(image);
			
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
            log.error("Mail send error", ex);
		}
		return false;
	}
	/**
	 * 
	 * 描述：发送附件邮件
	 * 2013-8-2 下午02:47:35 by Administrator
	 * @version
	 * @param mailInfo
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendFileMail(MailSenderInfo mailInfo) throws UnsupportedEncodingException {
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
////			Address to = new InternetAddress(mailInfo.getToAddress());
//			// Message.RecipientType.TO属性表示接收者的类型为TO
//			mailMessage.setRecipient(Message.RecipientType.TO, to);
			//创建邮件的接收者地址，并设置到邮件消息中
            String[] receivers = mailInfo.getReceivers();
            if (receivers != null) {
                // 为每个邮件接收者创建一个地址
                Address[] ccAdresses = new InternetAddress[receivers.length];
                for (int i = 0; i < receivers.length; i++) {
                    ccAdresses[i] = new InternetAddress(receivers[i]);
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                mailMessage.setRecipients(Message.RecipientType.TO, ccAdresses);
            }
            
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			
			FileDataSource fileds = new FileDataSource(mailInfo.getFilename());
			html.setDataHandler(new DataHandler(fileds));
			html.setFileName(MimeUtility.encodeText(fileds.getName(),"utf-8",null));  // 解决附件名称乱码
			mainPart.addBodyPart(html);
			
			//设置邮件图片 
//			MimeBodyPart image = new MimeBodyPart();  
//	        image.setDataHandler(new DataHandler(new FileDataSource(mailInfo.getImageName())));  //javamail jaf  
//	        image.setContentID("xx.jpg"); 
//			mainPart.addBodyPart(image);
			
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
            log.error("Mail send error", ex);
		}
		return false;
	}
	/**
	 * 以HTML格式发送邮件：多人发送邮件
	 * @param mailInfo
	 *  待发送的邮件信息
	 */
	public static boolean sendHtmlMailToManyPerson(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (pro.get("mail.smtp.auth").equals("true")) {
//			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
		}
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
			//创建邮件的接收者地址，并设置到邮件消息中
            String[] receivers = mailInfo.getReceivers();
            if (receivers != null) {
                // 为每个邮件接收者创建一个地址
                Address[] ccAdresses = new InternetAddress[receivers.length];
                for (int i = 0; i < receivers.length; i++) {
                    ccAdresses[i] = new InternetAddress(receivers[i]);
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                mailMessage.setRecipients(Message.RecipientType.TO, ccAdresses);
            }
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			
//			 FileDataSource fileds = new FileDataSource(filename);
//			 html.setDataHandler(new DataHandler(fileds));
//			 html.setFileName(MimeUtility.encodeText(fileds.getName(),"utf-8",null));  // 解决附件名称乱码
//			 files.add(fileds);
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			
			// 发送邮件
			Transport.send(mailMessage);
			log.info("ok");
			return true;
		} catch (MessagingException ex) {
            log.error("Mail send error", ex);
		}
		return false;
	}
	/**
	 * 
	 * 描述：带抄送人
	 * 2013-1-14 下午08:10:01 by xjw
	 * @version
	 * @param mailInfo
	 * @return
	 */
    public boolean sendHtmlMailAndCopy(MailSenderInfo mailInfo) {
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
			   // 获取抄送者信息，并设置到邮件消息中
            String[] ccs = mailInfo.getCcs();
            if (ccs != null) {
                // 为每个邮件抄送接收者创建一个地址
                Address[] ccAdresses = new InternetAddress[ccs.length];
                for (int i = 0; i < ccs.length; i++) {
                    ccAdresses[i] = new InternetAddress(ccs[i]);
                }
                // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
                mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
            }
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
//			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			html.setContent("<img src='cid:xx.jpg'>", "text/html");  
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
    /**
	 * 
	 * 描述：
	 * 2013-2-4下午08:10:01 by ygq
	 * @version
	 * @param mailInfo
	 * @return
	 */
    public boolean sendHtmlMailAndCopyAddLogo(MailSenderInfo mailInfo) {
    	// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (pro.get("mail.smtp.auth").equals("true")) {
//			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),pro.getProperty("PASSWORD"));
			authenticator = new MyAuthenticator(pro.getProperty("USERNAME"),t.decodeSignautre(pro.getProperty("PASSWORD")));
		}
		return sendMail(pro, authenticator, mailInfo);
	}
    /**
     * 
     * 描述：发送合规邮件
     * 2013-12-20 上午11:08:37 by caoyong
     * @version
     * @param mailInfo 邮件对象信息
     * @return 发送成功与否
     */
    public boolean sendComplianceMail(MailSenderInfo mailInfo){
    	// 判断是否需要身份认证
    	MyAuthenticator authenticator = null;
    	Properties pro = mailInfo.getProperties();
    	// 如果需要身份认证，则创建一个密码验证器
    	if (pro.get("mail.smtp.auth").equals("true")) {
    		authenticator = new MyAuthenticator(pro.getProperty("HEGUI_USERNAME"),t.decodeSignautre(pro.getProperty("HEGUI_PASSWORD")));
    	}
    	return sendMail(pro, authenticator, mailInfo);
    }
    /**
     * 
     * 描述：发送邮件
     * 2013-12-20 上午11:09:34 by caoyong
     * @version
     * @param pro 属性文件对象
     * @param authenticator 身份安全认证
     * @param mailInfo 邮件对象信息
     * @return
     */
    public static boolean sendMail(Properties pro,MyAuthenticator authenticator,MailSenderInfo mailInfo){
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
    		//创建邮件的接收者地址，并设置到邮件消息中
    		String[] receivers = mailInfo.getReceivers();
    		if (receivers != null) {
    			// 为每个邮件接收者创建一个地址
    			Address[] ccAdresses = new InternetAddress[receivers.length];
    			for (int i = 0; i < receivers.length; i++) {
    				ccAdresses[i] = new InternetAddress(Utils.toTrim(receivers[i]));
    			}
    			// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
    			mailMessage.setRecipients(Message.RecipientType.TO, ccAdresses);
    		}
    		// 获取抄送者信息，并设置到邮件消息中
    		String[] ccs = mailInfo.getCcs();
    		if (ccs != null) {
    			// 为每个邮件抄送接收者创建一个地址
    			Address[] ccAdresses = new InternetAddress[ccs.length];
    			for (int i = 0; i < ccs.length; i++) {
    				ccAdresses[i] = new InternetAddress(ccs[i]);
    			}
    			// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
    			mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
    		}
    		// 设置邮件消息的主题
    		mailMessage.setSubject(mailInfo.getSubject());
    		// 设置邮件消息发送的时间
    		mailMessage.setSentDate(new Date());
    		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
    		Multipart mainPart = new MimeMultipart();
    		// 创建一个包含HTML内容的MimeBodyPart
    		BodyPart html = new MimeBodyPart();
    		// 设置HTML内容
    		html.setContent(mailInfo.getContent(), "text/html;charset=utf-8");  
    		//4.设置邮件图片1  
    		MimeBodyPart image = new MimeBodyPart();  
    		image.setDataHandler(new DataHandler(new FileDataSource(mailInfo.getImageName())));  //javamail jaf  
    		image.setContentID("xx.jpg"); 
    		mainPart.addBodyPart(html);
    		mainPart.addBodyPart(image);
    		// 将MiniMultipart对象设置为邮件内容
    		mailMessage.setContent(mainPart);
    		// 发送邮件
    		Transport.send(mailMessage);
    		log.info("ok");
    		return true;
    	} catch (MessagingException ex) {
    		log.error("Mail send error", ex);
    		return false;
    	}
    }
    
    public static void main(String[] args) {
    	MailSenderInfo mailInfo = new MailSenderInfo();
		testxx(mailInfo);
	}
}
