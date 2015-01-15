package com.creditease.eas.hr.kingdee.query;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.SpecialWorkerMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class SpecialWorkerQuery extends BaseMyBatisDao {
	
	public List<HashMap<String, Object>> getSpecialWorkerServiceBycode(String fnumber){
		SqlSession session=null;
		List<HashMap<String,Object>> map=null;
		try {
			session=getSession();
			SpecialWorkerMapper mapper=session.getMapper(SpecialWorkerMapper.class);
			map=mapper.getSpecialWorkerServiceBycode(fnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return map;
		
	}

}
