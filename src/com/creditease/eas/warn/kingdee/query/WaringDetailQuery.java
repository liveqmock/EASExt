/**
 * 
 */
package com.creditease.eas.warn.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.vo.QueryData;
/**
 * @WaringDetailQuery.java
 * created at 2012-12-25 下午02:17:36 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
//@Service("orgAdminChService")
public class WaringDetailQuery extends BaseMyBatisDao{

	public static List getWaringDetailList(List<String> numlist){
		List list=null;
		SqlSession session = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class); 
			list=mapper.waringQueryList(numlist);
//			for (int i = 0; i < list.size(); i++) {
//				QueryData qd=(QueryData) list.get(i);
//				System.err.println(qd.getPositionname()+" "+qd.getTpid()+" "+qd.getPersonnumber()+" "+qd.getBirthday()+" " +
//						""+qd.getBirthday().toString().substring(0,10)+" "+qd.getName());
//			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
		return list;
	}
	
	public static void insertDetailQuery(QueryData queryData){
		List list=null;
		SqlSession session = null;
		try {
			session = getSession();
			PersonDataMapper mapper = session.getMapper(PersonDataMapper.class); 
			//mapper.insert(queryData);
			
//			list=mapper.waringQueryList();
//			for (int i = 0; i < list.size(); i++) {
//				QueryData qd=(QueryData) list.get(i);
//				System.err.println(qd.getPositionname()+" "+qd.getTpid()+" "+qd.getPersonnumber()+" "+qd.getBirthday()+" " +
//						""+qd.getBirthday().toString().substring(0,10)+" "+qd.getName());
//			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		
	}
	
	
	public static List<Map<String,Object>> sendImage(List<String> numlist)
	{
		SqlSession session=null;
		session = getSession();
		PersonDataMapper mapper = session.getMapper(PersonDataMapper.class);
		List<Map<String,Object>> list = mapper.sendImage(numlist);
//		List<Map<String,Object>> list = mapper.sendImage();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> obj = list.get(i);
			System.out.println("name: "+obj.get("NAME"));
		}
//		System.out.println(list.size());
		return list;
		
	}
	public static void main(String[] args) {
//		getWaringDetailList();
//		sendImage();
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrgAdminChService service = (OrgAdminChService) app.getBean("orgAdminChService");
		List<String> list = service.allOrgAdmin();
//		for (int i = 0; i < list.size(); i++) {
//			String fnumber = list.get(i).toString();
//			System.out.println("fnumber: "+fnumber);
//		}
//		sendImage(list);
		
		getWaringDetailList(list);
	}
}
