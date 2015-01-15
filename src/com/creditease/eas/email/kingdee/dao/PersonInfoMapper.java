package com.creditease.eas.email.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;

/**
 * 
 * @PersonInfoMapper.java
 * created at 2013-12-13 下午04:29:47 by zhangxin
 * 新员工入职信息
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface PersonInfoMapper extends BaseDAO<Map, Integer>{
	/**
	 * 
	 * 描述：导出时查询
	 * 2013-12-26 下午02:26:37 by zhangxin
	 * @version
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> expQuery(Map params);
	/**
	 * 
	 * 描述：根据员工编码查询员工信息
	 * 2013-12-26 下午05:55:55 by zhangxin
	 * @version
	 * @param fnumber
	 * @return
	 */
	public Map selectPerson(String fnumber);
}
