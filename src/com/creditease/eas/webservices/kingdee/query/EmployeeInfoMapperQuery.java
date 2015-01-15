package com.creditease.eas.webservices.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.webservices.kingdee.dao.EmployeeInfoMapper;

public class EmployeeInfoMapperQuery extends BaseMyBatisDao {
	/**
	 * @author Administrator
	 * @return
	 */
	public List<HashMap<String, Object>> enrollEmployeeQuery(Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			EmployeeInfoMapper empinfo=session.getMapper(EmployeeInfoMapper.class);
			list = empinfo.enrollEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
		return list;
	}
	
	public List<HashMap<String, Object>> resignEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			EmployeeInfoMapper empinfo=session.getMapper(EmployeeInfoMapper.class);
			list = empinfo.resignEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public List<HashMap<String, Object>> fluctuationEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			EmployeeInfoMapper empinfo=session.getMapper(EmployeeInfoMapper.class);
			list = empinfo.fluctuationEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}

	public List<HashMap<String, Object>> updateEmployeeQuery (Map params) {
		List<HashMap<String, Object>> list = null;
		SqlSession session = null;
		try {
			session = getSession();
			EmployeeInfoMapper empinfo=session.getMapper(EmployeeInfoMapper.class);
			list = empinfo.updateEmployeeQuery(params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return list;
	}

}
