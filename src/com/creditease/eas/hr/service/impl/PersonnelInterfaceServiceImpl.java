package com.creditease.eas.hr.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.PersonnelInterfaceQuery;
import com.creditease.eas.hr.service.PersonnelInterfaceService;
import com.creditease.eas.util.StringUtil;
@Service("personface")
public class PersonnelInterfaceServiceImpl implements PersonnelInterfaceService {
	PersonnelInterfaceQuery Personnel=new PersonnelInterfaceQuery();
	@Override
	public String getPersonnelInterfaceByOrgId(String fid,String ftypeid) {
		List<HashMap<String,Object>> listInfo = Personnel.getPersonnelInterfaceByOrgId(fid,ftypeid);
		String json = "";
		if(null != listInfo && !listInfo.isEmpty()){
			json = StringUtil.convertListToGson(listInfo);
		}
		return json;
	}
	@Override
	public String getPersonnelInterfaceByPerId(String fnumber,String ftypeid) {
		List<HashMap<String, Object>> listInfo = Personnel.getPersonnelInterfaceByPerId(fnumber,ftypeid);
		String json = "";
		if(null != listInfo && !listInfo.isEmpty()){
			json = StringUtil.convertListToGson(listInfo);
		}
		return json;
	}
	@Override
	public String getPersonnelInterfaceTypeById(String fid) {
		List<Map<String,Object>> listInfo =  Personnel.getPersonnelInterfaceTypeById(fid);
		String json = "";
		if(null != listInfo && !listInfo.isEmpty()){
			json = StringUtil.convertListToGson(listInfo);
		}
		return json;
	}
	

}
