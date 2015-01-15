package com.creditease.eas.test.common.email;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.creditease.eas.common.action.SendMailAction;
import com.creditease.eas.common.vo.EmailInfo;


public class SendMailTest {

	/** 
	 * @author lining
	 * @since 2014-6-7
	 * @param args
	 */
	ApplicationContext context = null;
	SendMailAction sendMailAction = null;

	 @Before
	 public void initContext(){
		 this.context = new FileSystemXmlApplicationContext("cfg/applicationContext*.xml");
		 this.sendMailAction = (SendMailAction) context.getBean("sendMailAction");
	 }


	 @Test
	 public void insert(){
		 List<EmailInfo> emailInfos = new ArrayList<EmailInfo>();
		 EmailInfo emailInfo = new EmailInfo();
		 emailInfo.setBusinessId("1262");
		 emailInfo.setSenderAddr("yujing@creditease.cn");
		 emailInfo.setSenderPswd("71685181310_88742414496_39423605728_368300218064_336527293478_39423605728_279177259645_8843372541_295581924122_5720251213_271222985339");
		 emailInfo.setReceiverAddrs("382038053@qq.com;");
//		 emailInfo.setReceiverNames("张欣");
			emailInfo.setInfoType("5");
			emailInfo.setCreateUser("aaa");
	//	 emailInfo.setCcAddrs("V-ningli@creditease.cn;V-ningli@creditease.cn;");
		 emailInfo.setTheme("到期提醒（此邮件为系统发送，请勿回复）");
		 emailInfo.setContentPath("D:\\app\\tomcat-eas\\mismail\\adminipurc20140611163622.txt");
		 emailInfo.setSendDate("2014-06-11");
		 emailInfo.setSendTime("18:30:00");
		 emailInfo.setImages("D:\\app\\tomcat-eas\\easfile\\logo.png;");
		// emailInfo.setFiles("G:\\03-Task\\04test\\data\\测试用—选定的被关注人.xls;G:\\03-Task\\04test\\data\\测试用—选定的被关注人.xls;");
		 emailInfos.add(emailInfo);
		 sendMailAction.addEmailInfo(emailInfos);
	 }
	 
	/* @Test
	 public void send(){
		 sendMailAction.excuteSendMail();
	 }*/
}
