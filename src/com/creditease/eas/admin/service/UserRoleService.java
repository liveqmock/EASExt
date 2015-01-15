package com.creditease.eas.admin.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.UserRole;

public interface UserRoleService {
	/**
	 * 添加用户对应的角色到中间表
	 * 描述：
	 * 2013-8-23 上午10:09:35 by Administrator
	 * @version
	 */
	public void inserUserRole(UserRole userrole);
	/**
	 * 删除用户对应的角色
	 * 描述：
	 * 2013-8-23 下午01:35:37 by Administrator
	 * @version
	 * @param id
	 */
	public void updateStutas(Integer id);
	/**
	 * 根据id查询id对应的角色集合
	 * 描述：
	 * 2013-8-23 下午02:05:03 by Administrator
	 * @version
	 * @param id
	 * @return
	 */
	public List<Integer> userroleidlist(Integer id);
	/**
	 * 删除中间表中用户拥有的权限
	 * 描述：
	 * 2013-8-23 下午02:27:22 by Administrator
	 * @version
	 * @param id
	 */
	public void deleteByPrimaryKey(Integer id);
	
	 /**
     * 查询用户id的集合
     * 描述：
     * 2013-8-29 下午03:00:14 by Administrator
     * @version
     * @return
     */
    public List<Integer> useridset();
    /***
     * 根据用户id和角色id，删除用户和角色中间表的数据信息
    * @Title: deleteUserRoleByUseridAndRoleId
    *created at 2014-6-14 下午05:04:25 by ygq  
    * @param userid
    * @param roleid
    * @return
    * @return int
     */
    int deleteUserRoleByUseridAndRoleId(Long userid,Integer roleid);
    /**
     * 根据用户id和角色id
    * @Title: findUserRoleByUserIdAndRole
    *created at 2014-6-19 下午04:09:12 by ygq  
    * @param map
    * @return
    * @return UserRole
     */
    public UserRole findUserRoleByUserIdAndRole(Long userid, Integer roleid);
    
}
