package com.creditease.eas.admin.service;

import java.util.List;

import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.util.Pagination;

public interface FunctionService {
	
	/**
	 * 查询所有功能
	 * 描述：
	 * 2013-8-5 下午04:19:41 by Administrator
	 * @version
	 * @return
	 */
	public List<Function> selectAllFunction();

	/**
	 * 删除数据时，将状态值stutas改为1
	 * 描述：
	 * 2013-8-5 下午06:00:54 by Administrator
	 * @version
	 */
	public void updateStutas(Integer id);
	/**
	 * 修改功能对象的信息
	 * 描述：
	 * 2013-8-5 下午07:02:15 by Administrator
	 * @version
	 * @param recode
	 */
	public void updateFunction(Function record);
	
	/**
	 * 根据id查找功能对象
	 * 描述：
	 * 2013-8-9 下午04:39:48 by Administrator
	 * @version
	 * @param id
	 */
	public Function selectFunctionByKey(Integer id);
	/**
	 * 插入数据
	 * 描述：
	 * 2013-8-5 下午07:14:36 by Administrator
	 * @version
	 * @param recode
	 */
	public void insertFunction(Function record);
	
	/**
	 * 查询页面
	 * 描述：
	 * 2013-8-8 上午11:49:41 by Administrator
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 查询功能是否已经存在
	 * 描述：
	 * 2013-8-13 下午04:20:25 by Administrator
	 * @version
	 * @param funname
	 * @return
	 */
	public int selectedIfFunctionExists(String funname);
	
	/**
	 * 查询功能的id、name
	 * 描述：
	 * 2013-8-19 下午06:49:06 by Administrator
	 * @version
	 * @return
	 */
	public List<Function> selefuncidname();
	
}
