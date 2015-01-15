package com.creditease.eas.hr.kingdee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DepositInfoMapper {
	/**
	 * 读取hr系统中带有备用金标志的人员分页查询
	 */
	public List<HashMap<String, Object>> getPersoninfo(Map<String,Object> map);
	/**
	 * 读取hr系统中带有备用金标志的人员总条数
	 */
	public int getCount();
	/*
	 * 根据员工姓名返回员工信息
	 */
	public List<HashMap<String, Object>> getNamePersoninfo(String pname);
 
}
