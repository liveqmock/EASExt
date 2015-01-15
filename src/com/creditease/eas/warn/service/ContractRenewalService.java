package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface ContractRenewalService{
	
	public Map<String, List<Map<String,Object>>> getSectorInfo() throws Exception;
	//合同发送给部门负责人、抄送给综合管理和BP
	public void sendOrgTest(String orgnumber,Set<Map<String,Object>> list);
	//合同发送给部门负责人、抄送给综合管理和BP  正式环境
	public void sendOrgTestQuartz(String orgnumber,Set<Map<String,Object>> list);
	//发送给对应接口人  定时任务 
	public void sendSectorInfo(String email,String secname,List<Map<String,Object>> list);
	//发送给对应接口人  定时任务 正式环境
	public void sendSectorInfoQuartz(String email,String secname,List<Map<String,Object>> list);
	
	public String getNameBymail(String email);
	
}
