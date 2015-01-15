package com.creditease.eas.common.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.common.vo.EmailInfo;

/**
 * 邮件缓存数据访问对象类
 * @author lining
 * @since 2014-6-1
 */
public interface SendMailDao{

	/**
	 * 查询待发邮件记录
	 * @author lining
	 * @since 2014-6-6
	 * @return
	 */
	public List<Map<String,Object>> selectMailForWillSend() throws Exception;

	/**
	 * 更新邮件发送后的邮件记录状态
	 * @author lining
	 * @since 2014-6-6
	 * @param sendReport
	 * @return
	 */
	public int updateMailAfterSended(Map<String, Object> sendReport) throws Exception;

	/**
	 * 向邮件缓存表中插入一条待发送邮件的记录
	 * @author lining
	 * @since 2014-6-6
	 * @param emailInfos
	 * @return
	 */
	public int insertMailForSend(EmailInfo emailInfos) throws Exception;

	/**
	 * 向邮件添加附件文件路径
	 * @author lining
	 * @since 2014-6-7
	 * @param attachments
	 */
	public int insertAttachmentForSend(List<Map<String,Object>> attachments);

	/**
	 * 根据邮件记录id获取邮件记录对应的附件
	 * @author lining
	 * @since 2014-6-8
	 * @param infoId
	 * @return
	 */
	public List<Map<String, Object>> selectAttachmentByInfoId(String infoId);

}
