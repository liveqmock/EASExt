package com.creditease.eas.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.UserMapper;
import com.creditease.eas.admin.service.LoginService;
//crtl+shift+o
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public User findOneUser(String username, String password) throws Exception {
		User user = null;
		try{
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", username);
			map.put("password", password);
			user = userMapper.selectByNameAndPassword(map);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}
//	@Override
//	public Map findAuth(Set<User_Role> userRoles) throws Exception {
//		// TODO Auto-generated method stub
//		Map map = new HashMap<String, Object>();
//		for (Iterator iterator = userRoles.iterator(); iterator.hasNext();) {
//			User_Role userRole = (User_Role) iterator.next();
////			中间表里面一行表示用户拥有的这个角色
//			Role role = userRole.getRole();
//			
////			角色拥有的权利
//			Set<AccessControlList> acls  = role.getAcls();
////			遍历角色拥有的权限
//			for (Iterator iterator2 = acls.iterator(); iterator2.hasNext();) {
//				
//				AccessControlList acl = (AccessControlList) iterator2.next();
////				拿到角色能操作的模块
//				Module module = acl.getModule();
////				这个对象放的是什么？？？：这个角色对某个模块操作的能力
//				AuthInfo authInfo = new AuthInfo();
//				authInfo.setOperator(acl.getOperator());
//				authInfo.setPriority(userRole.getPriority());
//				authInfo.setUrl(module.getUrl());
//				System.out.println("LoginServiceImpl.findAuth()========="+userRole.getPriority()+"===="+module.getUrl()+"===="+acl.getOperator());
//				boolean flag = false;
//				Set set = map.keySet();
//				for (Iterator iterator3 = set.iterator(); iterator3.hasNext();) {
//					String key = (String) iterator3.next();
//					AuthInfo temp = (AuthInfo) map.get(key);
//					if (temp.getUrl().equals(authInfo.getUrl())) {
//						flag = true;
//						if (authInfo.getPriority()<temp.getPriority()) {
//							map.put(authInfo.getUrl(), authInfo);//放优先级高的对象
//						}
//					}
//				}
//				
//				if(!flag){
//					map.put(authInfo.getUrl(), authInfo);
//					
//				}
//				
//			}
//		}
//		Set set = map.keySet();
//		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
//			String key = (String) iterator.next();
//			AuthInfo authInfo = (AuthInfo) map.get(key);
//			System.out.println("LoginServiceImpl.findAuth()*********getUrl********"+authInfo.getUrl()+"**getOperator******"+authInfo.getOperator()+"***getPriority****"+authInfo.getPriority());
//		}
//		return map;
//	}
}
