package com.creditease.eas.warn.service;
import java.util.Map;
import java.util.Set;

import com.creditease.eas.warn.vo.PersonData2;
public interface YearOfWorkService{
	public boolean queryPersonInfo()  throws Exception;
	
	
	public void sendOrgTest(String orgnumber,Set<Map<String,Object>> list);
	
	public void sendOrgTestQuartz(String orgnumber,Set<Map<String,Object>> list);
	
	public void sendPerson(Map<String,Object> tmp);
	
	public void sendPersonQuartz(Map<String,Object> tmp);
	/**
	 * 
	 * 描述：直接上级、隔级上级测试
	 * 2013-7-9 下午03:17:12 by Administrator
	 * @version
	 * @param pnumber
	 * @param list
	 */
	public void sendHigherTest(String pnumber,Set<PersonData2> list);
	
	public void sendHigher(String pnumber,Set<PersonData2> list);
}
