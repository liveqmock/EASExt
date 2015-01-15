package com.creditease.eas.warn.kingdee.query;

import java.io.Reader;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.creditease.eas.util.DataTest;
import com.creditease.eas.warn.dao.WaringDetailMapper;
public class WaringQuery {
	
	private static final String RESOURCE = "mybatis-config.xml";   
	
	
	
	public static List testMybiatsWaringDetail() throws Exception{
		//1、指定MyBaties配置文件   
		Reader reader = Resources.getResourceAsReader(RESOURCE);   
		//2、创建SqlSessionFactory()   
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
		SqlSession session = null;
		List list=null;
		try {
			session = sessionFactory.openSession();
			WaringDetailMapper mapper = session.getMapper(WaringDetailMapper.class); 
			list=mapper.waringDetaiList();
			for (int i = 0; i < list.size(); i++) {
				DataTest dt=(DataTest) list.get(i);
				System.err.println("id: "+dt.getId()+" positionname:"+dt.getPositionname());
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		return list;
	}

	public static void main(String[] args)  throws Exception{
		//testMybiats();
		testMybiatsWaringDetail();
	}
}
