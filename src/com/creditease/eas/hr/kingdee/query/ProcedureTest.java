package com.creditease.eas.hr.kingdee.query;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.hr.kingdee.dao.MessageSendMapper;
import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;

public class ProcedureTest extends BaseMyBatisDao{  
//	public void call(){
//		 String resource = "mybatis-config.xml";  
//	       Reader reader = Resources.getResourceAsReader(resource);  
//	       SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);  
//	       SqlSession session = ssf.openSession();  
//	       try {  
//	            Map<String, String> param = new HashMap<String, String>();  
//	            param.put("p_user_name", "zhangsan");  
//	            session.selectOne("com.creditease.eas.hr.kingdee.dao.BaseLogRecordMapper.proHello", param);  
//	            System.out.println("message=" + param.get("p_user_name"));  
//	            System.out.println("result=" + param.get("result"));  
//	            //System.out.println("returnValue=" + returnValue);  
//	        }catch (Exception e) {  
//	               e.printStackTrace();  
//	        } finally {  
//	             session.close();  
//	         }  
//	}
	/**
	 * 
	 * 描述：查询合同到期的人员的名单
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static String test(){
		SqlSession session = null;
		String str = "";
		try {
			session = getSession();
			 Map<String, String> param = new HashMap<String, String>();  
	         param.put("p_user_name", "zhangsan");  
			BaseLogRecordMapper mapper = session.getMapper(BaseLogRecordMapper.class); 
			str = mapper.proHello(param);
			System.out.println(str);
		    System.out.println("message=" + param.get("p_user_name"));  
            System.out.println("result=" + param.get("result"));  
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
    public static void main(String[] args) throws IOException {  
    	test();
    }
}  
