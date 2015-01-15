package com.creditease.eas.hr.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.DepositInfoMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class DepositInfoQuery extends BaseMyBatisDao{
	/***
	 * 备用金接口
	* @Title: getPerson
	*created at 2014-5-26 下午03:56:25 by ygq  
	* @param pageSize
	* @param pageNo
	* @return
	* @return Map<String,Object>
	 */
	public Map<String, Object> getPerson(int pageSize,int pageNo){
		List<HashMap<String, Object>> list=null;
		SqlSession session=null;
		Map<String,Object> map = null;
		try {
			session=getSession();
			DepositInfoMapper mapper=session.getMapper(DepositInfoMapper.class);
			Map<String,Object> queryParams = new HashMap<String,Object>();
			queryParams.put("fristResult", (pageNo-1)*pageSize);
			queryParams.put("maxResult", pageSize*pageNo);
			list=mapper.getPersoninfo(queryParams);
			int pageCount = mapper.getCount();
			map = new HashMap<String,Object>();
			map.put("byjperson", list);
			map.put("pageCount", pageCount);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return map;
		
	}
	
	
	public List<HashMap<String,Object>> getNamePersoninfo(String pname){
		List<HashMap<String, Object>> personlist=null;
		SqlSession session=null;
		try {
			session=getSession();
			DepositInfoMapper depmapper=session.getMapper(DepositInfoMapper.class);
			personlist=depmapper.getNamePersoninfo(pname);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		return personlist;
		
	}

}
