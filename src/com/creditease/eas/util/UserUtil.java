package com.creditease.eas.util;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import com.creditease.eas.admin.bean.User;
/***
 * 
 * @Title:UserUtil.java
 * @Package com.creditease.eas.util
 * created at 2014-5-24 上午10:43:21 by ygq
 * @Description: 用户基本信息的Util
 * @author ygq
 * @version 1.0
 */
public class UserUtil {
	/***
	 * 获得用户的基本信息
	* @Title: getUser   
	* @param @return
	* @return User
	* @throws
	 */
	public static User getUser(){
		Object userinfo =  ServletActionContext.getRequest().getSession().getAttribute("user");
		if(null != userinfo){
			return  (User)userinfo;
		}else{
			return null;
		}
	}
	/***
	 * 获得用户的角色id
	* @Title: getRoles
	*created at 2014-6-1 下午04:50:23 by ygq  
	* @return
	* @return List<Integer>
	 */
	public static List<Integer> getRoles(){
		Object userinfo =  ServletActionContext.getRequest().getSession().getAttribute("user");
		Object personrole =  ServletActionContext.getRequest().getSession().getAttribute("personrole");
		List<Integer> roleList = null;
		if(null != personrole){
			roleList = (List<Integer>)personrole;
		}
		return roleList;
	}
	/**
	 * 判断角色中是否包含某个角色
	* @Title: findIfSomeRole
	*created at 2014-6-1 下午04:52:19 by ygq  
	* @param roleId
	* @return
	* @return List<Integer>
	 */
	public static boolean findIfSomeRole(int roleId){
		boolean va = false;
		Object personrole =  ServletActionContext.getRequest().getSession().getAttribute("personrole");
		List<Integer> roleList = null;
		if(null != personrole){
			roleList = (List<Integer>)personrole;
			for(int i=0;i<roleList.size();i++){
				if(roleId == roleList.get(i)){
					va = true;
				}
			}
		}
		return va;
	}
	/***
	 * 
	* @Title: getRoleInfo
	*created at 2014-6-1 下午01:35:47 by ygq  
	* @return
	* @return List
	 */
	public static boolean findIfRoleExist(int roleId){
		boolean va = false;
		Object personrole =  ServletActionContext.getRequest().getSession().getAttribute("personrole");
		if(null != personrole){
			List<Integer> roleList = (List<Integer>)personrole;
			for(int i=0;i<roleList.size();i++){
				if(roleId == roleList.get(i)){
					va = true;
					break;
				}
			}
		}
		return va;
	}
	
}
