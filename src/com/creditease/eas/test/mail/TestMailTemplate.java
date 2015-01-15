package com.creditease.eas.test.mail;

import java.util.Properties;

import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;

public class TestMailTemplate {
	public static void main(String[] args) {
		 String fromAddress = "HR@creditease.cn";
			boolean va = false;
			 // 邮件内容
			try{
				String htmlContent = SendMailTemplate.info();
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				String[] toAddress = pr.getProperty("mailInfos").split(",");
				String logoPath = pr.getProperty("logoPath");
				va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, "转正预警测试", htmlContent,logoPath);
			}catch(Exception e){
				e.printStackTrace();
				//logger.info(e.getMessage());
			}
			if(va){
				System.out.println("发送成功！");
			}else{
				System.out.println("发送失败！");
			}
	}
}
