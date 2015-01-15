package com.creditease.eas.common.action;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.common.service.SendMailService;
import com.creditease.eas.common.vo.EmailInfo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 缓存邮件操作类
 * @author lining
 * @since 2014-5-19
 */
@SuppressWarnings("serial")
@Controller("sendMailAction")
@Scope("prototype")
public class SendMailAction extends ActionSupport{

	@Resource
	private SendMailService sendMailServiceImpl;
	/**
	 * 添加邮件信息
	 * @author lining
	 * @since 2014-5-19
	 * @return
	 */
	public String addEmailInfo(List<EmailInfo> emailInfos){
		String EmailInfoId = null;
		try {
			EmailInfoId = sendMailServiceImpl.createMail(emailInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EmailInfoId;
	}
	/**
	 * 获取EmailInfo数据
	 * @author lining
	 * @since 2014-5-19
	 * @param params
	 * @return
	 */
	public String showEmailInfo(Map<String,Object> params){
		String emailInfos = null;
		return emailInfos;
	}
	/**
	 * 执行邮件发送功能
	 * @author lining
	 * @since 2014-5-19
	 */
	public void excuteSendMail(){
		Map<String,Object> sendReport = null;
		try {
			 sendReport = this.sendMailServiceImpl.doSendMail();
		} catch (Exception e) {
			System.out.println(sendReport);
			e.printStackTrace();
		}
	}
}
