package com.creditease.eas.admin.dao;

import java.util.List;
import java.util.Map;


public interface ExtDataBaseMapper {
	/**
	 * 
	 * 描述：查询是否已经存在
	 * 2013-10-7 下午01:44:13 by caoyong
	 * @version
	 * @param map
	 * @return
	 */
	public int findExists(Map map);
	/**
	 * 
	 * 描述：查询是否已经存在
	 * 2013-12-3 下午01:57:18 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public int findUserExists(Map map);
}
