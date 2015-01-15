package com.creditease.eas.hr.kingdee.query;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.BasePersonInfoMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class BasePersonInfoQuery extends BaseMyBatisDao{
	/***
	 * 内网：人员基础信息
	 * @param fnumber
	 * @return
	 */
	public Map<String,Object> getPersonInfoByNumber(String fnumber){
		SqlSession session=null;
		Map<String,Object> map = null;
		try {
			session=getSession();
			BasePersonInfoMapper mapper=session.getMapper(BasePersonInfoMapper.class);
			map = mapper.getPersonInfoByNumber(fnumber);
		 if(map!=null){
			if(map.get("FID")!=null){
				String orgTopName = mapper.getTopOrgName(map.get("FID").toString());
				map.put("ORGTOPNAME", orgTopName);
			}
			Date date = (Date) map.get("BIRTHDAY");
			String birthady = new SimpleDateFormat("yyyy-MM-dd").format(date);
			map.remove("BIRTHDAY");
			map.put("BIRTHDAY", birthady);
			map.remove("FID");
		 }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
	   return map;
	
	}
	/***
	 * 根据传入的参数，查询员工的基本信息
	* @Title: queryPersonInfoByParams
	*created at 2014-6-15 下午02:21:31 by ygq  
	* @param map
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryPersonInfoByParams(Map map){
		SqlSession session=null;
		List<Map<String,Object>> list = null;
		try {
			session=getSession();
			BasePersonInfoMapper mapper=session.getMapper(BasePersonInfoMapper.class);
			list = mapper.queryPersonInfoByParams(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
		
	   return list;
	
	}
}
