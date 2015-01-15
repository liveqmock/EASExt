package com.creditease.eas.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SendMailUtilNew {
   /**
    * 
    * 描述：
    * 2014-1-13 下午04:32:02 by sunxiaofeng
    * @version
    * @param fromAddress
    * @param toAddress
    * @param title
    * @param htmlContent
    * @return
    */
	@SuppressWarnings("static-access")
	public static  boolean sendMailToManyPerson(String fromAddress,String password,String[] toAddress,String title,String htmlContent){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setPassword(password);
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		// 这个类主要来发送邮件
		SimpleMailSenderNew sms = new SimpleMailSenderNew();
        boolean flag = sms.sendHtmlMailToManyPerson(mailInfo);// 发送html格式邮件，发送给多个人
		return flag;
	}

    /**
     * 
     * 描述：发送邮件附件
     * 2014-1-14 上午10:16:20 by sunxiaofeng
     * @version
     * @param fromAddress
     * @param toAddress
     * @param title
     * @param filename
     * @return
     * @throws UnsupportedEncodingException
     */
	public static  boolean sendFileMail(String fromAddress,String password,String[] toAddress,String title,String filename) throws UnsupportedEncodingException{
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setPassword(password);
		mailInfo.setReceivers(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setFilename(filename);
		// 这个类主要来发送邮件
		SimpleMailSenderNew sms = new SimpleMailSenderNew();
		boolean flag = sms.sendFileMail(mailInfo);// 发送html格式
		return flag;
	}
	/**
	 * 
	 * 描述：发送内容及附件
	 * 2014-5-27 下午03:19:51 by sunxiaofeng
	 * @version
	 * @param fromAddress
	 * @param password
	 * @param toAddress
	 * @param title
	 * @param filename
	 * @param content
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static  boolean sendFileMailTo(String fromAddress,String password,String[] toAddress,String title,String filename,String content) throws UnsupportedEncodingException{
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setPassword(password);
		mailInfo.setReceivers(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setFilename(filename);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSenderNew sms = new SimpleMailSenderNew();
		boolean flag = sms.sendFileMailTo(mailInfo);// 发送html格式
		return flag;
	}
	/**
	 * 
	 * 描述：发送邮件给多个人,添加logo,并带有抄送人
	 * 2014-1-14 上午10:40:21 by sunxiaofeng
	 * @version
	 * @param fromAddress
	 * @param password
	 * @param toAddress
	 * @param title
	 * @param htmlContent
	 * @param imageName
	 * @param ccs
	 * @return
	 */
	public static  boolean sendMailToManyPersonAddLogo(String fromAddress,String password ,String[] toAddress,String title,String htmlContent,String imageName,String[] ccs){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress(fromAddress); // 发送人的邮箱地址
		mailInfo.setPassword(password);
		mailInfo.setReceivers(toAddress);//收件人的邮件地址
		mailInfo.setCcs(ccs);
		mailInfo.setSubject(title); // 邮箱标题
		mailInfo.setContent(htmlContent);// 邮件内容
		mailInfo.setImageName(imageName);
		// 这个类主要来发送邮件
		SimpleMailSenderNew sms = new SimpleMailSenderNew();
		boolean flag = sms.sendHtmlMailAndCopyAddLogo(mailInfo);
		return flag;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		MailSenderInfo mailInfo = new MailSenderInfo();
		Properties pro = mailInfo.getProperties();
		String fromAddress=pro.getProperty("FINANCE_USERNAME");
		String password=pro.getProperty("FINANCE_PASSWORD");
		String[] toAddress={"v-xiaofengsun@creditease.cn"};
		SendMailUtilNew.sendMailToManyPerson(fromAddress,password,toAddress,"祝福","祝你健康");
	}
}
