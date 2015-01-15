package com.creditease.eas.admin.service;


import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.Pagination;
/***
 * 用户service，解决数据权限的问题
 * @Title:UserAuthorityService.java
 * @Package com.creditease.eas.admin.service
 * created at 2014-5-27 下午03:57:07 by ygq
 * @author ygq
 * @version 1.0
 */
public interface UserAuthorityService{
	/**
	 * 
	 * 描述：添加用户
	 * 2013-1-16 下午02:29:46 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public int insertUser(User user) throws Exception;
	/**
	 * 
	 * 描述：查询数据
	 * 2013-1-16 下午03:18:41 by ygq
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：根据id查询用户
	 * 2013-1-17 上午10:57:26 by ygq
	 * @version
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	/***
	 * 描述：修改用户
	* @Title: update
	*created at 2014-5-28 下午04:40:29 by ygq  
	* @param user
	* @throws Exception
	* @return void
	 */
	public void update(User user) throws Exception;
	/**
	 * 
	 * 描述：判断用户是否已经存在
	 * 2013-12-3 下午02:29:47 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean selectedIfUserExists() throws Exception ;
	
	/**
	 * 
	 * 描述：判断用户是否已经存在
	 * 2013-1-17 下午12:37:02 by ygq
	 * @version
	 * @param username
	 * @return
	 */
	int selectedIfUserExists1(String username);
	
	
	/**
	 * 删除角色
	 * 描述：
	 * 2013-8-19 下午07:28:32 by Administrator
	 * @version
	 * @param id
	 */
	public void updateStatus(Integer id);

	 /**
     * 查询所有正常状态的用户
     * 描述：
     * 2013-8-29 下午02:41:47 by Administrator
     * @version
     * @return
     */
   public List<User> seleUserList();
   /***
    * 根据用户名，查用户的基本信息和角色信息
   * @Title: selectedUserAndRoleInfoByUserName
   *created at 2014-6-1 下午02:10:23 by ygq  
   * @param username
   * @return
   * @return Map<String,Object>
    */
   public List<Map<String,Object>> selectedUserAndRoleInfoByUserName(String username);
   /***
    * 根据用户名，查询用户的信息
   * @Title: selectOneUserByUsername
   *created at 2014-6-13 下午02:46:18 by ygq  
   * @param username
   * @return
   * @return User
    */
   public User selectOneUserByUsername(String username);
}
