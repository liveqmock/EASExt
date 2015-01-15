package com.creditease.eas.email.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.Pagination;

/**
 * 
 * @IPersonInfoService.java
 * created at 2013-12-16 下午04:09:57 by zhangxin
 * 查询当天新入职员工信息
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IPersonInfoService {
	/**
	 * 
	 * 描述：新入职员工信息
	 * 2013-12-16 下午04:12:45 by zhangxin
	 * @version
	 * @param page 
	 * @return 分页
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：导出时查询数据方法
	 * 2013-12-26 上午11:42:43 by zhangxin
	 * @version
	 * @return
	 */
	public List<Map> expQuery();
	/**
	 * 
	 * 描述：根据人员编码查询人员信息
	 * 2013-12-27 上午10:39:15 by zhangxin
	 * @version
	 * @param fnumber
	 * @return
	 */
	public Map selectPerson(String fnumber);
	
}
