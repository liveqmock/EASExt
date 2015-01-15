/**
 * 
 */
package com.creditease.eas.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.util.BaseDAO;

/**
 * @MenuMapper.java
 * created at 2013-8-8 下午03:55:54 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface MenuMapper extends BaseDAO<Menu, Integer>{
	/**
	 * 
	 * 描述：根据id删除记录
	 * 2013-8-8 下午03:57:23 by caoyong
	 * @version
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 
     * 描述：添加菜单
     * 2013-8-8 下午03:58:13 by caoyong
     * @version
     * @param menu
     * @return
     */
    int insertSelective(Menu menu);
    /**
     * 
     * 描述：更新菜单
     * 2013-8-8 下午03:58:58 by caoyong
     * @version
     * @param menu
     * @return
     */
    int updateByPrimaryKey(Menu menu);
    
    /**
     * 
     * 描述：根据id查找菜单记录
     * 2013-8-8 下午05:37:14 by caoyong
     * @version
     * @param id
     * @return
     */
    Menu selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：查找所有的菜单
     * 2013-8-9 下午04:39:47 by caoyong
     * @param level级别
     * @version
     * @return
     */
    //@Param("level")Integer level
    List<Menu> findByLevel(Map map);

    /**
     * 查找所有菜单的id、name
     * 描述：
     * 2013-8-15 上午10:14:54 by Administrator
     * @version
     * @return
     */
    List<Menu> seleMenu();
    
    /**
     * 
     * 描述：获取同级菜单的节点顺序的最大值
     * 2013-9-5 下午03:36:24 by caoyong
     * @version
     * @param parentId
     * @return
     */
    int getMaxSubSequence(@Param("parentId") String parentId);
    
    /**
     * 查询所有顶级菜单
     * May 7, 2014 5:35:25 PM by hejw
     * @return
     */
    List seleteAllParentMenuIds();
    
    /**
     * 根据父级菜单id查找此父级菜单所有子菜单及自己
     * May 7, 2014 6:30:22 PM by hejw
     * @param parentId
     * @return
     */
    List<Menu> queryTreeByParent(String parentId);
    
}
