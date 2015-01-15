package com.creditease.eas.common.bean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.creditease.eas.common.vo.MailInfo;
import com.creditease.eas.util.mail.RSAUtil;

/**
 * 邮件数据传输对象
 * @author lining
 * @since 2014-4-16
 */
public class Email {

	/**
	 * 邮件服务器配置
	 */
	private static final Properties EMAIL_HOST;
	/**
	 *校验邮箱的正则式
	 */
	private static final String EMAIL_CHECK;
	private static RSAUtil t = new RSAUtil();
	static {
		EMAIL_HOST = new Properties();
		try {
			/*
			 * 获取邮件服务器配置文件
			 */
			EMAIL_HOST.load(Email.class.getClassLoader().getResourceAsStream("mail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		EMAIL_CHECK = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	}
	/**
	 * 邮件信息数据集合，其中封装三个属性user，password,mail(如果为单封邮件，可以为MailInfo类的一个实例，如果为多封邮件可以为MailInfo实例的List集合)
	 */
	private List<MailInfo> mailInfos;
	/**
	 * 邮件服务器会话
	 */
	private Session mailSession;
	/**
	 * 邮件发送对象
	 */
	private Transport mailTransport;
	/**
	 * 邮件消息集合
	 */
	private Map<String,Object> mailMessages;
	/**
	 * 构造器
	 * @param mailInfo
	 */
	public Email(List<MailInfo> mailInfos){
		this.mailInfos = mailInfos;
	}
	/**
	 * 获取与邮件服务器的本次会话
	 * @return
	 */
	private Session getSession(){
		if(null == mailSession){
			mailSession = Session.getDefaultInstance(EMAIL_HOST);
			//向控制台输出邮件发送过程，由于调试代码
//			mailSession.setDebug(true);
		}
		return mailSession;
	}
	/**
	 * 打开邮件发送
	 * @return
	 * @throws NoSuchProviderException
	 */
	private Transport getTransport() throws NoSuchProviderException{
		if(null == mailTransport){
			Session session = this.getSession();
			mailTransport =  session.getTransport();
		}
		return mailTransport;
	}
	/**
	 * 判断是否为合法邮箱
	 * @param add
	 * @return
	 */
	private boolean isEmailAdress(String add){
		Pattern regex = Pattern.compile(EMAIL_CHECK);
		Matcher matcher = regex.matcher(add);
		return matcher.matches();
	}
private Message createMessage(MailInfo mailInfo,Message message) throws MessagingException, UnsupportedEncodingException{
		
		//创建邮件发送地址
		String from = mailInfo.getFrom();
		if(null != from && isEmailAdress(from)){
			Address sender = new InternetAddress(from);
			// 设置邮件消息的发送者
			message.setFrom(sender);
		}else{
			throw new RuntimeException("无法获取发件箱地址");
		}
		
		//设置抄送地址
		String[] cc = mailInfo.getCc();
    	if(null != cc && 0 < cc.length){
    		Address[] ccs = new Address[cc.length];
    		for(int i = 0;i < cc.length;i++){
    			if(null != cc[i] && isEmailAdress(cc[i])){
    				//创建邮件的抄送者地址
    				ccs[i] = new InternetAddress(cc[i]);
    			}
    		}
    		// Message.RecipientType.TO属性表示抄送者
    		message.setRecipients(Message.RecipientType.CC, ccs);
    	}
	    	
    	//设置秘密抄送地址
    	String[] bcc = mailInfo.getBcc();
    	if(null != bcc && 0 < bcc.length){
    		Address[] bccs = new Address[bcc.length];
    		for(int i = 0;i < bcc.length;i++){
    			if(null != bcc[i] && isEmailAdress(bcc[i])){
    				//创建邮件的密抄地址
    				bccs[i] = new InternetAddress(bcc[i]);
    			}
    		}
    		//Message.RecipientType.BCC属性表示密地址
    		message.setRecipients(Message.RecipientType.BCC, bccs);
    	}
	    	
    	//创建一个邮件信息容器，由于整合邮件的文本、图片、附件等内容
    	Multipart mainPart = new MimeMultipart();
    	
    	//创建一个包含HTML格式邮件内容的MimeBodyPart容器
    	BodyPart textBody = new MimeBodyPart();
    	//设置HTML内容
    	textBody.setContent(mailInfo.getContent(),"text/html; charset=utf-8");
    	//整合邮件的文本内容
    	mainPart.addBodyPart(textBody);
	    	
    	//为邮件添加图片
    	String[] images = mailInfo.getImages();
    	if(null != images && 0 < images.length){
    		for(String image:images){
    			if(null != image && "" != image){
    				//获取图片文件资源
    				FileDataSource fs = new FileDataSource(image);
    				// MiniMultipart类是一个容器类，用于存储非文本类型数据
    				BodyPart imageBody = new MimeBodyPart();
    				//将图片文件资源添加到图片容器中
    				imageBody.setDataHandler(new DataHandler(fs));
    				//获取图片文件名，并设置为在HTML文本中的id
    				((MimeBodyPart) imageBody).setContentID(fs.getName());
    				//将图片容器整合到邮件信息容器中
    				mainPart.addBodyPart(imageBody);
    			}
    		}
    	}
    	
    	//为邮件添加附件、做法基本同添加图片
    	String[] files = mailInfo.getFiles();
    	if(null != files && 0 < files.length){
    		for(String file:files){
    			if(null != file && "" != file){
    				FileDataSource fs = new FileDataSource(file);
    				BodyPart fileBody = new MimeBodyPart();  
    				fileBody.setDataHandler(new DataHandler(fs));
    				//获取文件名，并设置为该附件的名字(注意附件名乱码问题)
    				((MimeBodyPart) fileBody).setFileName(MimeUtility.encodeText(fs.getName(),"utf-8",null)); 
    				mainPart.addBodyPart(fileBody);
    			}
    		}
    	}
	    	
    	//将邮件信息容器对象设置为邮件内容
    	message.setContent(mainPart);
    	// 设置邮件消息的主题
    	message.setSubject(mailInfo.getTheme());
    	// 设置邮件消息发送的时间
    	message.setSentDate(new Date());
    	//保存更新  
    	message.saveChanges();
		return message;
	}
	/**
	 * 为所有收件人创建一封邮件消息
	 * @author lining
	 * @since 2014-5-13
	 * @param mailInfo
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	private List<Map<String,Object>> createMessageAsOne(MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException{
		
		Message message = new MimeMessage(this.getSession());
    		
		//设置收件箱
		String[] to = mailInfo.getTos();
		if(null != to && 0 < to.length){
			Address[] receiver = new Address[to.length];
			for(int i = 0;i < to.length;i++){
				if(null != to[i] && isEmailAdress(to[i])){
					//创建邮件的接收者地址，并设置到邮件消息中
					receiver[i] = new InternetAddress(to[i]);
				}
			}
			//Message.RecipientType.TO属性封装收件地址
			message.setRecipients(Message.RecipientType.TO, receiver);
		}else{
			throw new RuntimeException("无法获取收件箱地址");
		}
		message = this.createMessage(mailInfo,message);
    	Map<String,Object> mail = new HashMap<String,Object>();
		mail.put("mailInfoId", mailInfo.getInfoId());
		mail.put("receiver", mailInfo.getTos());
		mail.put("message", message);
		List<Map<String,Object>> mails = new ArrayList<Map<String,Object>>();
		mails.add(mail);
		return mails;
	}
	/**
	 * 为每个收件人单独创建一封邮件消息
	 * @author lining
	 * @since 2014-5-13
	 * @param mailInfo
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	private List<Map<String,Object>> createMessageAsMany(MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException{
		
		List<Map<String,Object>> mails = new ArrayList<Map<String,Object>>();
		String[] to = mailInfo.getTos();
		if(null != to && 0 < to.length){
			for(int i = 0;i < to.length;i++){
				Message message = new MimeMessage(this.getSession());
				Address receiver = new InternetAddress(to[i]);
				message.setRecipient(Message.RecipientType.TO, receiver);
				message = this.createMessage(mailInfo,message);
				Map<String,Object> mail = new HashMap<String,Object>();
				mail.put("mailInfoId", mailInfo.getInfoId());
				mail.put("receiver", mailInfo.getTos()[i]);
				mail.put("message", message);
				mails.add(mail);
			}
		}else{
			throw new RuntimeException("无法获取收件箱地址");
		}
		
		return mails;
	}
	/**
	 * 获取聚发邮件信息
	 * @author lining
	 * @since 2014-5-12
	 * @return
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	private Map<String,Object> getMessageAsOne() throws MessagingException, UnsupportedEncodingException{
		if(null == mailMessages){
			mailMessages = new HashMap<String,Object>();
			for(MailInfo mailInfo:mailInfos){
				String user = mailInfo.getFrom();
				if(mailMessages.containsKey(user)){
					Map<String,Object> mailMessage = (Map<String,Object>)mailMessages.get(user);
					List<Map<String,Object>> mails = (List<Map<String,Object>>)mailMessage.get("mails");
					mails.addAll(createMessageAsOne(mailInfo));
				}else{
					Map<String,Object> mailMessage = new HashMap<String,Object>();
					mailMessage.put("password", mailInfo.getPassword());
					mailMessage.put("mails", createMessageAsOne(mailInfo));
					mailMessages.put(mailInfo.getFrom(), mailMessage);
				}
			}
		}
		return mailMessages;
	}
	/**
	 * 获取分发邮件信息
	 * @author lining
	 * @since 2014-5-12
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	private Map<String,Object> getMessageAsMany()throws MessagingException, UnsupportedEncodingException{
		if(null == mailMessages){
			mailMessages = new HashMap<String,Object>();
			for(MailInfo mailInfo:mailInfos){
				String user = mailInfo.getFrom();
				if(mailMessages.containsKey(user)){
					Map<String,Object> mailMessage = (Map<String,Object>)mailMessages.get(user);
					List<Map<String,Object>> mails = (List<Map<String,Object>>)mailMessage.get("mails");
					mails.addAll(createMessageAsMany(mailInfo));
				}else{
					Map<String,Object> mailMessage = new HashMap<String,Object>();
					mailMessage.put("password", mailInfo.getPassword());
					mailMessage.put("mails", createMessageAsMany(mailInfo));
					mailMessages.put(mailInfo.getFrom(), mailMessage);
				}
			}
		}
		return mailMessages;
	}
	/**
	 * 将收件人添加到一封邮件中发送的方法
	 * @author lining
	 * @since 2014-5-12
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> sendAsOne(){
		Map<String, Object> mailMessages = null;
		try {
			mailMessages = this.getMessageAsOne();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(String user:mailMessages.keySet()){
			Transport transport = null;
			try {
				transport = this.getTransport();
				transport.connect(user, t.decodeSignautre((String)((Map<String,Object>)mailMessages.get(user)).get("password")));
				this.send((List<Map<String, Object>>) ((Map<String,Object>)mailMessages.get(user)).get("mails"),transport);
			} catch (Exception e) {
				for(Map<String,Object> mail:(List<Map<String, Object>>) ((Map<String,Object>)mailMessages.get(user)).get("mails")){
					mail.put("state", 0);
					mail.put("sendTime", new Date());
				}
				e.printStackTrace();
			}finally{
				if(null != transport){
					try {
						transport.close();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return mailMessages;
	}
	/**
	 * 向收件人分别发送一封邮件的方法
	 * @author lining
	 * @since 2014-5-12
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> sendAsMany(){
		Map<String, Object> mailMessages = null;
		try {
			mailMessages = this.getMessageAsMany();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(String user:mailMessages.keySet()){
			Transport transport = null;
			try {
				transport = this.getTransport();
				transport.connect(user, t.decodeSignautre((String)((Map<String,Object>)mailMessages.get(user)).get("password")));
				this.send((List<Map<String, Object>>) ((Map<String,Object>)mailMessages.get(user)).get("mails"),transport);
			} catch (Exception e) {
				for(Map<String,Object> mail:(List<Map<String, Object>>) ((Map<String,Object>)mailMessages.get(user)).get("mails")){
					mail.put("state", 0);
					mail.put("sendTime", new Date());
				}
				e.printStackTrace();
			}finally{
				if(null != transport){
					try {
						transport.close();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return mailMessages;
	}
	/**
	 * 逐一发送邮件并记录发送状态信息
	 * @author lining
	 * @since 2014-5-12
	 * @param mails
	 * @param transport
	 * @return
	 */
	private void send(List<Map<String,Object>> mails,Transport transport){
		for(Map<String,Object> mail:mails){
			try {
				transport.sendMessage((Message)mail.get("message"), ((Message)mail.get("message")).getAllRecipients());
				mail.put("state", 1);
				mail.put("sendTime", new Date());
			} catch (MessagingException e) {
				mail.put("state", 0);
				mail.put("sendTime", new Date());
				e.printStackTrace();
			}
		}
	}
}
