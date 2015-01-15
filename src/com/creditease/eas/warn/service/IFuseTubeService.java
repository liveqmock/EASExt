package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;

/***
 * 处理信管中心的需求
 * @since 2013年11月9日星期六 15:23
 * @author ygq
 */
public interface IFuseTubeService{
	/***
	 * 发送邮件信息
	 * @param mpPerson
	 * @return
	 */
	public boolean sendMailInfosFuseTubeTest(List<Map<String,Object>> mpPerson);
	/***
	 * 发送邮件信息,合同到期提醒
	 * @param mpPerson
	 * @return
	 */
	public boolean sendMailInfoContractFuseTubeTest(List<Map<String,Object>> mpPerson);
}
