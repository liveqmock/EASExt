package com.creditease.eas.admin.service;

import java.util.List;

import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.util.Pagination;

public interface RoleService {
	
	/**
	 * 插入角色
	 * 描述：
	 * 2013-8-15 上午10:03:39 by Administrator
	 * @version
	 * @param record
	 */
	public void inserRole(Role record);
	
	
	/**
	 * 
	 * 描述：判断角色是否已经存在
	 * 2013-12-3 下午02:29:47 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean selectedIfRoleExists() throws Exception ;
	
	
	
	/**
	 * 查询所有角色
	 * 描述：
	 * 2013-8-16 上午09:54:16 by Administrator
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination queryPage(Pagination page);
	
	/**
	 * 删除角色
	 * 描述：
	 * 2013-8-19 下午07:28:32 by Administrator
	 * @version
	 * @param id
	 */
	public void updateStatus(Integer id);
	
	/**
	 * 查询角色的id和name
	 * 描述：
	 * 2013-8-23 上午09:43:28 by Administrator
	 * @version
	 * @return
	 */
	public List<Role> seleroleidname();
	
	
	
	/**
	 * 根据角色id查找角色对象
	 * 描述：
	 * 2013-9-4 上午10:41:33 by Administrator
	 * @version
	 * @param roleid
	 * @return
	 */
	public Role selectByPrimaryKey(Integer roleid);
	
	/**
	 * 修改角色信息
	 * 描述：
	 * 2013-9-4 下午02:16:31 by Administrator
	 * @version
	 * @param role
	 */
	public void updateByPrimaryKey(Role role);
	/***
	 * 根据用户id，查询角色的id和name
	* @Title: seleroleidnameByUserid
	*created at 2014-6-8 下午06:42:55 by ygq  
	* @param username
	* @return
	* @return List<Role>
	 */
	List<Role> seleroleidnameByUserid(Long userid);
	/***
	 * 根据角色名称查询角色信息
	* @Title: selectRoleByName
	*created at 2014-6-22 下午02:52:25 by ygq  
	* @param rolename
	* @return
	* @return Role
	 */
	public Role selectRoleByName(String rolename);
}
