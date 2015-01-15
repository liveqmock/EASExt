package com.creditease.eas.admin.service;

import java.util.Map;
import java.util.Set;

import com.creditease.eas.admin.bean.User;

public interface LoginService {
	User findOneUser(String username,String password) throws Exception;
	//Map findAuth(Set<User_Role> userRoles) throws Exception;
}
