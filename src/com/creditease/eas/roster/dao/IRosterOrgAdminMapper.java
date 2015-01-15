package com.creditease.eas.roster.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
/**
 * 投资理财三部组织架构树Mapper
 * @IRosterOrgAdminMapper.java
 * created at 2014-3-24 下午05:03:10 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IRosterOrgAdminMapper extends BaseDAO<Map, Integer> {

	/**
	 * 
	 * 描述：查询投三所有组织
	 * 2014-3-24 下午05:07:10 by zhangxin
	 * @version
	 * @return
	 */
	public List<Map<String,Object>>  selectAllOrgAdmin(); 
}
