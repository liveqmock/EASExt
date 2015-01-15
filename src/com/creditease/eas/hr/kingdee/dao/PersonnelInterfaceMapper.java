package com.creditease.eas.hr.kingdee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersonnelInterfaceMapper {
	/*
	 * 根据组织ID 类型ID获取人员编码，类型名称(根据组织id，查找人事接口人）
	 */
	public List<HashMap<String,Object>> getPersonnelInterfaceByOrgId(Map<String, Object> map);
	/*
	 * 取出该组织下关联的所有父id 与子id
	 */
	public List<HashMap<String,Object>> getParentId(String fid);
	/*
	 * 根据组织编码  类型ID获取人员编码，类型名称，组织ID（根据人事接口人编码查找人事接口人）
	 */
	public List<HashMap<String,Object>> getPersonnelInterfaceByPerId(Map<String, Object> map);
	/***
	 * 查询接口人类别的相关信息
	* @Title: getPersonnelInterfaceTypeById
	*created at 2014-6-9 下午03:58:39 by ygq  
	* @param personId
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> getPersonnelInterfaceTypeById(String fid);
}
