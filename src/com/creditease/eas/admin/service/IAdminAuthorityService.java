package com.creditease.eas.admin.service;


import com.creditease.eas.admin.bean.AdminAuthority;
import com.creditease.eas.admin.bean.AdminUserAuthority;
/***
 * 数据权限Service
 * @Title:IAdminAuthorityService.java
 * @Package com.creditease.eas.admin.service
 * created at 2014-5-28 上午11:19:46 by ygq
 * @author ygq
 * @version 1.0
 */
public interface IAdminAuthorityService{
	/**
	 * 
	 * 描述：添加数据权限表的数据
	 * 2013-1-16 下午02:29:46 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public int insert(AdminAuthority authority) throws Exception;

//	/**   
//	* @Title: 添加数据权限和用户中间表的数据
//	*created at 2014-5-28 上午11:27:28 by ygq  
//	* @param adminUserAuthority
//	* @return
//	* @throws Exception
//	* @return int  
//	*/   
//	int insertAdminUserAuthority(AdminUserAuthority adminUserAuthority)throws Exception;
}
