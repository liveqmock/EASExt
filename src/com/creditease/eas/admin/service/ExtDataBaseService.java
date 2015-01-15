package com.creditease.eas.admin.service;

import java.util.List;
import java.util.Map;

/**
 * 操作金蝶辅助系统的数据
 * @ExtDataBaseService.java
 * created at 2013-8-27 下午06:28:58 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ExtDataBaseService {
	/***
	 * 描述：
	 * 2013-8-28 下午06:55:40 by ygq
	 * @version
	 * @param sql
	 * @return
	 */
	public List<Map<String,Object>> operExtDateBase(String sql);
	/**
	 * 操作 
	 * @param sql
	 * @return
	 */
	public int operSql(String sql);
}
