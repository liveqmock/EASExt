package com.creditease.eas.quartz;


import javax.annotation.Resource;

import com.creditease.eas.common.action.SendMailAction;

/**
 * 定时器公共类
 * @author lining
 * @since 2014-5-14
 */
public class CommonTimer {

	/**
	 * 公用邮件缓存发送功能操作实例
	 */
	@Resource
	private SendMailAction sendMailAction;
	
	/**
	 * 自定义关注预警操作类实例
	 */
//	@Resource
//	private CustomWarnAction customWarnAction;
	/**
	 * 每个整点时刻的定时任务触发器
	 * @author lining
	 * @since 2014-5-14
	 */
	public void everyHourExecution(){
		System.out.println("CommonTimer-everyHourExecution-begining……………………………………………………");
		sendMailAction.excuteSendMail();
		System.out.println("CommonTimer-everyHourExecution-ending……………………………………………………");
	}
	/**
	 * 每日指定时刻触发的定时器
	 * @author lining
	 * @since 2014-5-14
	 */
	public void everyDayExecution(){
//		customWarnAction.makeEmailOfBirthdayWarn();
//		customWarnAction.makeEmailOfSeniorityWarn();
		
	}
	/**
	 * 每月指定日期指定时刻触发的定时器
	 * @author lining
	 * @since 2014-5-14
	 */
	public void everyMonthExecution(){
//		customWarnAction.makeEmailOfContractWarn();
//		customWarnAction.makeEmailOPermanentWarn();
	}
}
