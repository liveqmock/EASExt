package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;

public interface RegularService{
	/**
	 * 描述：真正合并发送邮件的过程
	 * 2013-3-30 下午04:40:12 by ygq
	 * @version
	 * @param mpToPerson
	 * @param mpDown
	 */
	void sendMailInfosMerge(Map<String,List<Map<String,Object>>> mpPerson);
	/**
	 * 描述：发送邮件信息的测试
	 * 2013-1-11 上午10:15:59 by ygq
	 * @version
	 * @param entry
	 * @throws Exception
	 */
	public void sendResponsePersonMailInfo(List<Map<String,Object>> upPersons,List<Map<String,Object>> downPersons);
	/**
	 * 描述：模拟合并发送邮件的过程
	 * 2013-3-30 下午04:40:12 by ygq
	 * @version
	 * @param mpToPerson
	 * @param mpDown
	 */
	void sendMailInfosMergeTest(Map<String,List<Map<String,Object>>> mpPerson);
	/**
	 * 描述：模拟发送邮件信息的测试
	 * 2013-1-11 上午10:15:59 by ygq
	 * @version
	 * @param entry
	 * @throws Exception
	 */
	public void sendResponsePersonMailInfoTest(List<Map<String,Object>> upPersons,List<Map<String,Object>> downPersons);
}
