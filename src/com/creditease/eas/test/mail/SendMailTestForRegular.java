package com.creditease.eas.test.mail;

import java.util.Properties;

import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
public class SendMailTestForRegular {
	public static void main(String[] args) {
		 boolean flag = false;
		 String fromAddress = "HR@creditease.cn";
		 String toAddress = "gaoquanyang@creditease.cn";
		 // 邮件内容
		 try{
			String htmlContent = "good";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 flag = SendMailUtil.sendMailAndCopyForRegualr(fromAddress,toAddress,"测试",htmlContent,"c:\\logo.jpg",null);
//			flag = SendMailUtil.sendMailAndCopyForRegualr(fromAddress, toAddress, "转正预警测试", htmlContent);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(flag){
			System.out.println("发送成功！");
		}else{
			System.out.println("发送失败！");
		}
	}
}
