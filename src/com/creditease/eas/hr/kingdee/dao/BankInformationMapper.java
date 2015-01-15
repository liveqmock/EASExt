package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;



public interface BankInformationMapper {
	
	public List<Map<String, Object>> getBankInfo(Map<String, Object> map);
	 public List<Map<String, Object>> getBankInfoById(Map<String, Object> map);
	 public int getCount();
	 public int getCount2(String fid);
	 

}
