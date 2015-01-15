package com.creditease.eas.hr.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.bean.PersonInfo;
import com.creditease.eas.hr.kingdee.dao.PersonInfoMapper;
import com.creditease.eas.hr.kingdee.dao.PersonnelInterfaceMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class SeparationProcessesQuery extends BaseMyBatisDao{
	
	@SuppressWarnings("unchecked")
	public PersonInfo getFnumber(String fnumber){
//		Map <String,Object> forPoserlist=null;
//		SqlSession session = null;
//		try {
//			session = getSession();
//			PersonInfoMapper mapper=session.getMapper(PersonInfoMapper.class);
//			forPoserlist=mapper.getFnumber(fnumber);
//			
//		}catch (Exception e) {
//			return null;
//		}finally{
//			closeSession(session);
//			
//		}
//		return forPoserlist;
		PersonInfo  forPoserlist = null;
//		PersonInfo  forPoserlist = new PersonInfo();
		SqlSession session = null;
		try {
			session = getSession();
			PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
			forPoserlist =mapper.getFnumber(fnumber);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return forPoserlist;
	}
	
	public Map<String, Object> getByj(String fnumber){
		SqlSession session = null;
		Map<String, Object> byj = null;
		try {
			session = getSession();
			PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
			byj =mapper.getByj(fnumber);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return byj;
	}
	
}
