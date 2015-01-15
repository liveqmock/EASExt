package com.creditease.eas.warn.kingdee.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.OrgAdmin;

public interface OrgAdminMapper extends BaseDAO<OrgAdmin, Integer>{
	/**
	 * 查询所有行政组织单元
	 * 描述：
	 * 2013-4-2 下午02:48:23 by guominggao
	 * @version
	 * @return
	 */
	public List<Map<String,Object>>  selectAllPerson();
	
	public List<Map<String,Object>>  selectAllOrg();
	
	public List<Map<String,Object>>  selectAllOrgManager();
	
	public String getOrgNameByFnumber(String fnumber);
	
	//根据人员编码查询人员部门编码相关信息 
	public Map<String,Object> selectPersonInfoByFnumber(String fnumber);
}
