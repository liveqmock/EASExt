package com.creditease.eas.compliance.service;

import com.creditease.eas.util.Pagination;

/**
 * 违规统计
 * @StatisService.java
 * created at 2013-11-5 下午03:23:26 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface StatisService {
	/**
	 * 
	 * 描述：查询违规数据
	 * 2013-11-5 下午03:26:47 by zhangxin
	 * @version
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Pagination queryPage(Pagination page) throws Exception;
}
