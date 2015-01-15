package com.creditease.eas.warn.kingdee.query;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.creditease.eas.warn.bean.PersonData;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
/**
 * 
 * @PersonDataQuery.java
 * created at 2012-12-25 上午11:04:46 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class PersonDataQuery {


	private static final String RESOURCE = "mybatis-config.xml";   
	
	
	
	public static List getPersonDataList() throws Exception{
		//1、指定MyBaties配置文件   
		Reader reader = Resources.getResourceAsReader(RESOURCE);   
		//2、创建SqlSessionFactory()   
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
		SqlSession session = null;
		List list=null;
		try {
			session = sessionFactory.openSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class); 
			list=mapper.personDataList();
			for (int i = 0; i < list.size(); i++) {
				PersonData pd=(PersonData) list.get(i);
				System.err.println("id: "+pd.getId()+" positionname:"+pd.getPositionname());
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
	/**
	 * 
	 * 描述：
	 * 2012-12-25 上午11:04:18 by xjw
	 * @version
	 * @throws Exception
	 */
	public static void insertWaringDetail() throws Exception{
		//1、指定MyBaties配置文件   
		Reader reader = Resources.getResourceAsReader(RESOURCE);   
		//2、创建SqlSessionFactory()   
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);   
		SqlSession session = null;
		List list=null;
		try {
			session = sessionFactory.openSession();
			list=getPersonDataList();
			for (int i = 0; i < list.size(); i++) {
				PersonData pd=(PersonData) list.get(i);
				//WaringDetailMapper waringDetailMapper = session.getMapper(WaringDetailMapper.class);
				//waringDetailMapper.insertWaringDetail(pd);
				PersonDataMapper personDataMapper = session.getMapper(PersonDataMapper.class);
				personDataMapper.insert(pd);
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

	public static void main(String[] args)  throws Exception{
		//insertWaringDetail();
		getPersonDataList();
	}
}
