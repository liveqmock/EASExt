package com.creditease.eas.hr.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.FixedAssetsQuery;
import com.creditease.eas.hr.service.IFixedAssetsService;
import com.creditease.eas.hr.service.PersonnelInterfaceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("fixedAssetsServiceImpl")
public class FixedAssetsServiceImpl implements IFixedAssetsService {
	FixedAssetsQuery fixassets=new FixedAssetsQuery();
	@Override
	public String getFixedAssetsByOrgId(String fid) {
		// TODO Auto-generated method stub
		List<HashMap<String,Object>> map = fixassets.getPersonnelInterfaceByOrgId(fid);
		Gson gson=new GsonBuilder().create();
		String json="";
		if (map.isEmpty()) {
//			Map<String, Object> personmap=new HashMap<String, Object>();
//			personmap.put("USERCODE", "");
//			json=gson.toJson(personmap);
		}
		if (!map.isEmpty()) {
			if (map.get(0)==null) {
//				Map<String, Object> personmap=new HashMap<String, Object>();
//				personmap.put("USERCODE", "");
//				json=gson.toJson(personmap);
			}else {
				json=gson.toJson(map);
			}
			
		}
		 
		return json;
	}
	@Override
	public String getFixedAssetsByCode(String fnumber) {
		List<HashMap<String, Object>> map=fixassets.getPersonnelInterfaceByPerId(fnumber);
		Gson gson=new GsonBuilder().create();
		String json="";
		if (map.isEmpty()) {
		}
		if (!map.isEmpty()) {
			if (map.get(0)==null) {
			}else {
				json=gson.toJson(map);
			}
			
		}
		
		return json;
	}
	

}
