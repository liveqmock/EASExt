package com.creditease.eas.test;

import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SimpleMailSender;
public class SendMailTest {
	//"626264481@qq.com"
	public static  void sendMail(String toAddress){
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setFromAddress("gaoquanyang@creditease.cn"); // 发送人的邮箱地址
		mailInfo.setToAddress(toAddress);  // 要发送的邮箱地址
		mailInfo.setSubject("**的主页"); // 邮箱标题
		// 邮件内容
		// html页面格式的邮件内容
		String htmlContent = "<div style='width:500px;height:100px;background-color:yellow;text-align:center;color:red;font-size:20px;border:solid red;'><b><a href='http://121374067.qzone.qq.com/'>欢迎来到我的主页！</a></b></div>";
		mailInfo.setContent(htmlContent);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean flag = sms.sendHtmlMail(mailInfo);// 发送html格式
		if(flag){
			System.out.println("发送成功！");
		}else{
			System.out.println("发送失败！");
		}
	} 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String toAdd = "xinwan@creditease.cn";
		String toAdd = "gaoquanyang@creditease.cn";
		sendMail(toAdd);
		//看明白之后
		//SendMailUtil:实际调用的时候使用
		//public static  boolean sendMail(String fromAddress,String toAddress,String title,String htmlContent);
	}
}            
