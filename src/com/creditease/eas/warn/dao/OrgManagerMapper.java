package com.creditease.eas.warn.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Homologous;
import com.creditease.eas.warn.bean.OrgManager;

public interface OrgManagerMapper extends BaseDAO<OrgManager, Integer>{

	public List<Map<String, Object>> allHomologousByType();

	public Map<String, Object> getHomologousByFnumber(String fnumber);
	
	public int selectCount(String fnumber);
	/**
	 * 
	 * 描述：查询抄送人总条数
	 * 2014-5-26 下午02:22:39 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public int selectCopyPeoCounts(Map map);
	/**
	 * 
	 * 描述：查询抄送人信息
	 * 2014-5-26 下午02:38:27 by zhangxin
	 * @version
	 * @param params
	 * @return 抄送人信息集合
	 */
	public List<Map> queryCopyPeoPage(Map params);
	/**
	 * 
	 * 描述：删除抄送人信息
	 * 2014-5-26 下午03:06:08 by zhangxin
	 * @version
	 * @param fid
	 * @return
	 */
	public int deleteByPrimaryKey(int fid);
	/**
	 * 
	 * 描述：根据fid查询抄送人信息
	 * 2014-5-26 下午05:04:43 by zhangxin
	 * @version
	 * @param fid
	 * @return 抄送人集合
	 */
	public Map selectInfobyFid(int fid);
	/**
	 * 
	 * 描述：保存修改的抄送人信息
	 * 2014-5-26 下午05:51:36 by zhangxin
	 * @version
	 * @param map
	 */
	public int updateCopyPeo(Map map);
	/**
	 * 
	 * 描述：插入抄送人信息
	 * 2014-5-26 下午05:52:34 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public int insertCopyPeo(Map map);
	/**
	 * 
	 * 描述：查询抄送人邮箱是否重复
	 * 2014-5-27 下午05:14:54 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public int findEmailExists(Map map);
	/**
	 * 
	 * 描述：获取下一个序列值
	 * 2014-5-27 下午06:51:54 by zhangxin
	 * @version
	 * @return
	 */
	public int nextSeq();
	
	public void insertHoms(Homologous homs);
	
	public void updateHoms(Homologous homs);
	
	public Map<String, Object> getHoms(String fnumber);
	
	public Map<String, Object> getHomsBp(String fnumber);
	
}
