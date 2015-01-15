package com.creditease.eas.util.mail;

import java.io.FileInputStream;
import java.util.Properties;

public class Test {
	public static void main(String[] args) throws Exception{
		MailSenderInfo ms = new MailSenderInfo();
		Properties pro = ms.getProperties();
		System.out.println(pro.get("mail.smtp.host"));
	}
}
