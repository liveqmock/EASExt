package com.creditease.eas.warn.service;


import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.FinanceGroupBean;

public interface FinanceGroupService {

	/**
	 * 查询功能
	 * 描述：
	 * 2013-9-16 下午03:45:04 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：根据id查找组信息
	 * 2013-9-17 下午02:40:05 by zhangxin
	 * @version
	 * @param id
	 * @return
	 */
	public FinanceGroupBean selectByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：添加组信息
	 * 2013-9-17 下午03:58:39 by zhangxin
	 * @version
	 * @param record
	 */
	public void insert(FinanceGroupBean record);
	
	/**
	 * 
	 * 描述：更新组信息
	 * 2013-9-17 下午04:52:57 by zhangxin
	 * @version
	 * @param record
	 */
	public void update(FinanceGroupBean record);
	
	/**
	 * 
	 * 描述：删除组信息
	 * 2013-9-17 下午05:14:16 by zhangxin
	 * @version
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * 描述：判断组名是否存在
	 * 2013-9-18 下午01:43:49 by zhangxin
	 * @version
	 * @param name
	 * @return
	 */
	public boolean selectedIfGroupExists() throws Exception;
	
	
}
