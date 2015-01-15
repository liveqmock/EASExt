package com.creditease.eas.util;

import java.util.List;
import java.util.Map;
/**
 * 公用的Mapper接口
 * @BaseDAO.java
 * created at 2013-2-25 下午04:46:58 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 * @param <T>
 * @param <PK>
 */
public interface BaseDAO<T,PK> {
	//插入
	public int insert(T entity);
	//更新
	public int update(T entity);
	///删除
	public int delete(T entity);
	/***
	 * 根据类和主键进行删除
	* @Title: delete
	*created at 2014-6-1 上午10:59:11 by ygq  
	* @param entityClass
	* @param id
	* @return void
	 */
	public void delete(Class<T> entityClass, PK id);
	/***
	 * 根据主键进行删除
	* @Title: ByPrimaryKey
	*created at 2014-6-1 上午10:52:56 by ygq  
	* @param id
	* @return void
	 */
	public int deleteByPrimaryKey(Long id);
	/***
	 * 根据主键进行删除
	* @Title: ByPrimaryKey
	*created at 2014-6-1 上午10:52:56 by ygq  
	* @param id
	* @return void
	 */
	public int physicalDeletion(Long id);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T selectByPrimaryKey(Long id);
	
	//最初的方法
	@Deprecated
	public int getTotalCounts();
	/**
	 * 
	 * 描述：传参的获得总的记录数的方法
	 * 2012-12-29 下午04:19:54 by ygq
	 * @version
	 * @param entity
	 * @return
	 */
	public int getTotalCountsByParams(Map map);
	/**
	 * 
	 * 描述：参数传递的是开始的参数和后面的参数（下面的queryPage和这个方法重复了
	 * T传递的是对应的实体，带有查询条件的时候使用该方法
	 * 2012-12-29 下午04:21:13 by ygq
	 * @version
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<T> queryPageByParams(Map params);
	/****
	 * 根据id进行查询
	* @Title: findById
	*created at 2014-6-1 上午10:53:43 by ygq  
	* @param entityClass
	* @param id
	* @return
	* @return T
	 */
	public T findById(Class<T> entityClass, PK id);
	
	public List<T> findAll(Class<T> entityClass);
	@Deprecated
	public List<T> queryPage(Map params);
}
