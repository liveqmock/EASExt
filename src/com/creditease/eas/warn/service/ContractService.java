package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface ContractService{
	public boolean queryPersonInfo()  throws Exception;
	/**
	 * 描述：发送邮件信息的测试
	 * 2013-1-11 上午10:15:59 by ygq
	 * @version
	 * @param entry
	 * @throws Exception
	 */
	public void sendMailInfos(Entry<String,List<Map<String,Object>>> entry);
}
