package com.creditease.eas.admin.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.util.Pagination;

/**
 * @MenuService.java菜单接口
 * created at 2013-8-8 下午04:55:11 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface MenuService{
	
	/**
	 * 
	 * 描述：添加菜单
	 * 2013-8-8 下午05:01:44 by caoyong
	 * @version
	 * @param menu
	 * @throws Exception
	 */
	void insert(Menu menu) throws Exception;
	/**
	 * 
	 * 描述：分页查询出菜单的列表记录
	 * 2013-8-8 下午05:10:26 by caoyong
	 * @version
	 * @param page
	 * @return
	 */
	Pagination queryPage(Pagination page) ;
	/**
	 * 
	 * 描述：更新菜单记录数据
	 * 2013-8-8 下午05:13:31 by caoyong
	 * @version
	 * @param id
	 */
	void update(Menu menu);
	/**
	 * 
	 * 描述：根据id删除菜单记录
	 * 2013-8-8 下午05:14:05 by caoyong
	 * @version
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 
	 * 描述：根据id查找记录
	 * 2013-8-8 下午05:35:37 by caoyong
	 * @version
	 * @param id
	 * @return
	 */
	Menu getMenuById(Integer id);
	
	/**
	 * 
	 * 描述：查找所有的菜单
	 * 2013-8-9 下午04:25:07 by caoyong
	 * @param level 级别
	 * @version
	 * @return
	 */
	List<Menu> findByLevel(Map map);
	
	/**
	 * 查找所有菜单的id,names
	 * 描述：
	 * 2013-8-15 上午10:20:50 by Administrator
	 * @version
	 * @return
	 */
	List<Menu> seleMenu();
	
	/**
	 * 
	 * 描述：获取同级菜单的节点顺序的最大值
	 * 2013-9-5 下午03:43:49 by caoyong
	 * @version
	 * @param parentId
	 * @return
	 */
	int getMaxSubSequence(String parentId);
	
	/**
	 * 查询所有顶级菜单id hejiwu
	 * @return
	 */
	List seleteAllParentMenuIds();
	
	/**
	 * 根据parentID查询所有子菜单及自己
	 * May 7, 2014 6:28:11 PM by hejw
	 * @return
	 */
	List<Menu> queryTreeByParent(String parentId);
}
