package com.creditease.eas.compliance.kingdee.dao; 
import java.util.List;
import java.util.Map;
/***
 * 公用方法，查询组织相关的信息
 * @CompAdminfoMapping.java
 * created at 2013-10-20 下午06:02:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface CompAdminMapping {
	/**
	 * 描述：查询组织的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public int getTotalCountsByParams(Map map);
	/**
	 * 描述：查询组织的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectOrgAdminInfo(Map map);
	/**
	 * 描述：查询组织的信息
	 * 2014-1-15 上午09:55:04 by caoyong
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getOrgData(Map map);
}