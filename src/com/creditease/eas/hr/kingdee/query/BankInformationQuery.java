package com.creditease.eas.hr.kingdee.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.client.HappyClient;
import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.BankInformationMapper;
import com.creditease.eas.util.BaseMyBatisDao;



public class BankInformationQuery extends BaseMyBatisDao{
	public Map<String,Object> getBankInfo(int pageSize,int pageNo){
		@SuppressWarnings("unused")
		List<Map<String, Object>>list=null;
		Map<String, Object> map=null;
		SqlSession session=null;
		try {
			session=getSession();
			BankInformationMapper bankmapper=session.getMapper(BankInformationMapper.class);
			Map<String, Object> queryParams=new HashMap<String, Object>();
			queryParams.put("fristResult", (pageNo-1)*pageSize);
			queryParams.put("maxResult", pageSize*pageNo);
			list=bankmapper.getBankInfo(queryParams);
			int pageCount = bankmapper.getCount();
			map = new HashMap<String,Object>();
			map.put("pageCount", pageCount);
			map.put("bankInfo", list);
//			bankmapper.getBankInfo(pageSize, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	
	public Map<String, Object> getBankInfoById(String fid, int pageSize, int pageNo){
		List<Map<String, Object>>  list1=null;
		Map<String, Object> map1=null;
		SqlSession session=null;
		try {
			session=getSession();
			BankInformationMapper bmapper=session.getMapper(BankInformationMapper.class);
			Map<String, Object> queryParams1=new HashMap<String, Object>();
			queryParams1.put("fristResult", (pageNo-1)*pageSize);
			queryParams1.put("maxResult", pageSize*pageNo);
			queryParams1.put("fid", fid);
			list1=bmapper.getBankInfoById(queryParams1);
			int pageCount1=bmapper.getCount2(fid);
			map1=new HashMap<String, Object>();
			map1.put("pageCount", pageCount1);
			map1.put("bankInfo", list1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map1;
		
	}
	
	

}
