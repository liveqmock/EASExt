package com.creditease.eas.hr.kingdee.query;



import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.EmailForEmployeeCodeMapper;
import com.creditease.eas.util.BaseMyBatisDao;


public class EmailForEmployeeCodeQuery extends BaseMyBatisDao{
	public String queryFnumberByEmail(String femail){
		SqlSession session = null;
		String cemail=null;
		try {
			session=getSession();
			EmailForEmployeeCodeMapper mapper=session.getMapper(EmailForEmployeeCodeMapper.class);
			cemail=mapper.queryFnumberByEmail(femail);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		
		
		return cemail;
		
	}

}
