package com.creditease.eas.util;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.creditease.eas.util.Utils;

public class BaseMyBatisDao {
	private static final String RESOURCE = "mybatis-config.xml";   
	/**
	 * 
	 * 描述：生成session
	 * 2012-12-24 下午11:22:31 by ygq
	 * @version
	 * @return
	 */
	public static SqlSession getSession(){
		SqlSession session = null;
		try {
			//1、指定MyBaties配置文件   
			Reader reader = Resources.getResourceAsReader(RESOURCE);   
			//2、创建SqlSessionFactory()   
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
			session = sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	/**
	 * 
	 * 描述：关闭session
	 * 2012-12-24 下午11:24:15 by ygq
	 * @version
	 * @param session
	 */
	public static void closeSession(SqlSession session){
		if(null != session){
			session.close();
		}
	}
}
