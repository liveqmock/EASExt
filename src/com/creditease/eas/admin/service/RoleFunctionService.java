package com.creditease.eas.admin.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.RoleFunction;

public interface RoleFunctionService {

	/**
	 * 在角色功能中间表中插入数据
	 * 描述：
	 * 2013-8-15 上午10:37:07 by Administrator
	 * @version
	 * @param record
	 */
	public void insertRoleFunction(RoleFunction record);
	
	/**
	 * 根据角色id和链接类型查询角色的菜单或者功能链接
	 * 描述：
	 * 2013-8-16 下午02:53:18 by Administrator
	 * @version
	 * @param roleid
	 * @param linktype
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> selerolefonctionid(Map map);
	
	/**
	 * 删除已经删除的角色对应的数据
	 * 描述：
	 * 2013-8-19 下午07:24:52 by Administrator
	 * @version
	 * @param id
	 */
	public void updateStatus(Integer id);
	
	
	/**
	 * 删除数据库中角色现有的权限
	 * 描述：
	 * 2013-8-21 上午10:44:07 by Administrator
	 * @version
	 * @param map
	 */
	public void deleteByPrimaryKey(Map map);
	
}
