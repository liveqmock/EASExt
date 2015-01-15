package com.creditease.eas.test.ws.service;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditease.eas.test.ws.User;
@WebService(targetNamespace="hello")
public interface UserService {
	String testStr(@WebParam(name="str")String str);
	void add(User user);
	User[] findAllUsers();
}