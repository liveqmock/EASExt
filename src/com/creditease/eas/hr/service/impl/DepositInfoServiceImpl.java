package com.creditease.eas.hr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.DepositInfoQuery;
import com.creditease.eas.hr.service.IDepositInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DepositInfoServiceImpl implements IDepositInfoService {
	DepositInfoQuery depinfo=new DepositInfoQuery();
	
	
	@Override
	public String getPersoninfo(int pagesize,int pageNo) {
		Map<String, Object> map=depinfo.getPerson(pagesize, pageNo);
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(map);
		return json;
	}


	@Override
	public String getNamePersoninfo(String pname) {
		List<HashMap<String,Object>> map=depinfo.getNamePersoninfo(pname);
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(map);
		return json;
			
		
		
	}

}
