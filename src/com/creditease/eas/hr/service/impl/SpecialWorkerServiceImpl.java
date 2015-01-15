package com.creditease.eas.hr.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.SpecialWorkerQuery;
import com.creditease.eas.hr.service.ISpecialWorkerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("specwork")
public class SpecialWorkerServiceImpl implements ISpecialWorkerService {
	
	SpecialWorkerQuery spp=new SpecialWorkerQuery();
	
	@Override
	public String getSpecialWorkerServiceBycode(String fnumber) {
		List<HashMap<String, Object>> list=spp.getSpecialWorkerServiceBycode(fnumber);
		if (list.isEmpty()) {
			return "";
		} else {
			Gson json=new GsonBuilder().create();
			return json.toJson(list);
		}
	} 
}
