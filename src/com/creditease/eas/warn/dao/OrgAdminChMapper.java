package com.creditease.eas.warn.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.OrgAdminCh;


public interface OrgAdminChMapper extends BaseDAO<OrgAdminCh, Integer>{
  
	/**
	 * 
	 * 描述： 将选择的组织添加到数据库中
	 * 2013-4-8 下午03:01:09 by guominggao
	 * @version
	 * @param record
	 * @return
	 */
    int insertOrgAdminCh(OrgAdminCh record);
    
    /**
     * 
     * 描述：清空表
     * 2013-4-12 上午09:52:10 by guominggao
     * @version
     */
    void deleteAllOrgAdminCh();
    
    public List<String> allOrgAdmin(); 
    
    public List<Map<String, Object>> getHomologous(String fnumber);
    
    public List<Map<String, Object>> getOrgByFnumber(String fnumber);
    
    /**
     * 
     * 描述：获取选择的部门编码
     * 2013-4-16 下午02:15:44 by xjw
     * @version
     * @return
     */
//    public List<Map<String,Object>> dynamicForeachOrgAdmin(); 
 
}