/**
 * 
 */
package com.creditease.eas.warn.service;

 
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.Homologous;
import com.creditease.eas.warn.bean.OrgManager;


/**
 * 
 * @OrgAdminChService.java
 * created at 2013-4-8 下午04:01:44 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface OrgManagerService { 

	
	public Pagination queryPage(Pagination page) ;
	/**
	 * 
	 * 描述：查询抄送人信息
	 * 2014-5-26 下午01:49:13 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination querycopypeoplePage(Pagination page);
	/**
	 * 
	 * 描述：删除抄送人信息
	 * 2014-5-26 下午03:02:10 by zhangxin
	 * @version
	 */
	public void deleteCopyPeo();
	/**
	 * 
	 * 描述：根据fid查询抄送人信息
	 * 2014-5-26 下午04:58:54 by zhangxin
	 * @version
	 * @param fid
	 * @return 信息集合
	 */
	public Map<String,Object> selectInfobyFid(int fid);
	/**
	 * 
	 * 描述：保存修改的抄送人信息
	 * 2014-5-26 下午05:44:31 by zhangxin
	 * @version
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public int updateCopyPeo(Map map);
	/**
	 * 
	 * 描述：插入抄送人信息
	 * 2014-5-26 下午05:47:46 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int insertCopyPeo(Map map);
	
	/**
	 * 
	 * 描述：查询抄送人邮箱是否存在
	 * 2014-5-27 上午10:38:00 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean ifEmailHasExists() throws Exception;
	
	public List<OrgManager> allHomologousByFnumber();
	
	public OrgManager getOrgManagerByFnumber(String fnumber);
	
	public int selectCount(String fnumber);
	
	public void insert(Homologous homs);
	
	public void update(Homologous homs);
	
	public Map<String, Object> gethoms(String fnumber);
	
	public Map<String, Object> gethomsBp(String fnumber);
	
}
