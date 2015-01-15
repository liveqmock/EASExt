/**
 * 
 */
package com.creditease.eas.warn.service;

 
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.OrgAdminCh;


/**
 * 
 * @OrgAdminChService.java
 * created at 2013-4-8 下午04:01:44 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface OrgAdminChService { 

	int insert(OrgAdminCh orgadminch) throws Exception;
	
	void deleteAll();
	
	public Pagination queryPage(Pagination page);
	
	public int queryCount();
	
	public List<String> allOrgAdmin();
	
	public List<Map<String, Object>> getHomologous(String fnumber);
	
	/**
	 * 
	 * 描述：获取个贷部门，邮件接收人
	 * 2013-4-22 下午07:03:42 by xjw
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<Map<String, Object>> getOrgByFnumber(String fnumber);

}
