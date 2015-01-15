package com.creditease.eas.test.ws.service.impl;
import java.util.List;
import java.util.Vector;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.creditease.eas.test.ws.User;
import com.creditease.eas.test.ws.service.UserService;
@WebService(endpointInterface = "com.ws.service.UserService")//这里要写接口,是必须的
public class UserServiceImpl implements UserService {
        static List<User> UserRepository = new Vector<User>();
        private static Logger log = Logger.getLogger(UserServiceImpl.class);
        {
                log.info("CXF正在初始化 UserServiceImpl.");
        }
        public void add(User user) {
                log.info("CXF正在添加一个用户： " + user.getFirst() + "." + user.getLast());
                UserRepository.add(user);
        }
        public User[] findAllUsers() {
                System.out.println("findAllUsers...");
                User[] users = new User[UserRepository.size()];
                UserRepository.toArray(users);
                return users;
        }
        public String testStr(String str) {
                String str2=str;
                System.out.println("testStr...str2="+str2);
                return str2;
        }
}
