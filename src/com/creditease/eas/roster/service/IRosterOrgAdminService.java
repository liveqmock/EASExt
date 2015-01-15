package com.creditease.eas.roster.service;

import java.util.List;
import java.util.Map;

/**
 * 投资理财三部组织架构service层接口方法
 * @IRosterOrgAdminService.java
 * created at 2014-3-24 下午05:11:33 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IRosterOrgAdminService {
	/**
	 * 
	 * 描述：查询所有组织结构
	 * 2014-3-24 下午05:12:39 by zhangxin
	 * @version
	 * @return
	 */
	public List<Map<String, Object>> selectAllOrgAdmin();

}
