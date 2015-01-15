/**
 * Copyright 2011 suixingpay, Inc. All rights reserved.
 */
package com.creditease.eas.util.mail;

import java.util.Date;
import java.util.Properties;

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
/**
 * 邮件
 * @author Administrator
 */
public class SimpleMailSender_v1 {
     private static Logger log = Logger.getLogger(MailSenderInfo.class);
     /**
      * 以HTML格式发送邮件
      * 待发送的邮件信息
      * @param mailInfo
      * @return boolean
      */
     public boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
    	MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session
                .getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
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
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 为邮件添加附件
//            String[] attachFileNames = mailInfo.getAttachFileNames();
//            if (attachFileNames != null && attachFileNames.length > 0) {
//                // 存放邮件附件的MimeBodyPart
//                MimeBodyPart attachment = null;
//                File file = null;
//                for (int i = 0; i < attachFileNames.length; i++) {
//                    attachment = new MimeBodyPart();
//                    // 根据附件文件创建文件数据源
//                    file = new File(attachFileNames[i]);
//                    FileDataSource fds = new FileDataSource(file);
//                    attachment.setDataHandler(new DataHandler(fds));
//                    // 为附件设置文件名
//                    attachment.setFileName(MimeUtility.encodeWord(file.getName(), "GBK", null));
//                    mainPart.addBodyPart(attachment);
//                }
//            }

            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            //ex.printStackTrace();
            log.error("Merchant data update error", ex);
        }
//        catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            log.error("Merchant data update error", e);
//            //e.printStackTrace();
//        }
        return false;
    }
}
