package com.creditease.eas.common.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.common.vo.EmailInfo;

/**
 * 邮件缓存服务类
 * @author lining
 * @since 2014-6-1
 */
public interface SendMailService {

	/**
	 * 根据邮件缓存表中信息发送邮件并更新表中邮件信息
	 * @author lining
	 * @since 2014-6-6
	 * @return 邮件发送状况集合
	 * @throws Exception
	 */
	public Map<String,Object> doSendMail() throws Exception;
	/**
	 * 创建一封邮件信息
	 * @author lining
	 * @since 2014-6-6
	 * @param emailInfos
	 * @return
	 * @throws Exception
	 */
	public String createMail(List<EmailInfo> emailInfos) throws Exception;
	/**
	 * 获取邮件信息
	 * @author lining
	 * @since 2014-6-6
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<EmailInfo> findMail(Map<String,Object> param) throws Exception;
}
