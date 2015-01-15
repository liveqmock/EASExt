package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;


public interface BasePersonInfoMapper {
	/*
	 * 根据编码返回人员基本信息
	 */
	
	public Map<String,Object> getPersonInfoByNumber(String fnumber);
	
	/*
	 * 获取顶级部门
	 */
	
	public String getTopOrgName(String orgid);
	/***
	 * 根据传入的参数，查询员工的基本信息
	* @Title: queryPersonInfoByParams
	*created at 2014-6-15 下午02:21:31 by ygq  
	* @param map
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryPersonInfoByParams(Map map);

}
