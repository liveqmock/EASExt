package com.creditease.eas.warn.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.kingdee.dao.DistinctivePersonMapper;

public class DistinctivePersonQuery extends BaseMyBatisDao{
	/**
	 * 查询信管中心将要转正的人员的信息
	 * @return
	 */
	public List<Map<String,String>> selectRequiredPerson(){
		SqlSession session  = getSession();
		DistinctivePersonMapper mapper = session.getMapper(DistinctivePersonMapper.class);
		List<Map<String,String>> list = mapper.selectRequiredPerson();
		return list;
	}
	//public 
	//查询信管中心合同将要到期的人员的信息
	public static void main(String[] args) {
		DistinctivePersonQuery query = new DistinctivePersonQuery();
		List<Map<String,String>> list = query.selectRequiredPerson();
		for(int i=0;i<list.size();i++){
			Map<String,String> map = list.get(i);
			System.out.println("map\t" + map);
		}
	}
}
