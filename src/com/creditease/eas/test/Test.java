package com.creditease.eas.test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creditease.eas.warn.bean.Person;
import com.creditease.eas.warn.kingdee.dao.PersonMapper;
import com.creditease.eas.warn.service.PersonService;

public class Test {
	public static void test(){
		   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
	       PersonService personService =(PersonService) context.getBean("personService");
	       System.out.println(personService);
	       Map param = new HashMap();
	      // personService.queryPage(param,1, 5);
	}
	private static final String RESOURCE = "mybatis-config.xml";   
	public static void testMybiats() throws IOException{
		//1、指定MyBaties配置文件   
		Reader reader = Resources.getResourceAsReader(RESOURCE);   
		//2、创建SqlSessionFactory()   
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			sessionFactory.getConfiguration().addMapper(PersonMapper.class); 
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
			Map map = new HashMap();
			map.put("startRow",10);
			map.put("endRow", 20);
			List<Person> list = mapper.queryPage(map);
			for(int i=0;i<list.size();i++){
				Person person = list.get(i);
				System.out.println(person.getFid() + "\t" + person.getFname());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (session!=null) {
				session.close();
			}
			
		}
	}
	/**
	 * 获得所有的信息
	 * @throws IOException
	 */
	public static void getTotalCounts() throws IOException{
		//1、指定MyBaties配置文件   
		Reader reader = Resources.getResourceAsReader(RESOURCE);   
		//2、创建SqlSessionFactory()   
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			sessionFactory.getConfiguration().addMapper(PersonMapper.class); 
			PersonMapper mapper = session.getMapper(PersonMapper.class); 
//			int totalCount = mapper.getTotalCounts();
//			System.out.println("totalCount::::" + totalCount);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (session!=null) {
				session.close();
			}
			
		}
	}
	public static void main(String[] args) throws Exception{
		testMybiats();
		//getTotalCounts();
		
	}
}
