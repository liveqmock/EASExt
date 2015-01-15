package com.creditease.eas.webservices.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.StringUtil;
import com.creditease.eas.webservices.kingdee.query.EmployeeInfoMapperQuery;
import com.creditease.eas.webservices.service.MicroMessageExpansionServices;

public class MicroMessageExpansionServicesImpl implements
		MicroMessageExpansionServices {
	private EmployeeInfoMapperQuery empInfo=new EmployeeInfoMapperQuery();
	@Override
	public String EnrollEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = empInfo.enrollEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String FluctuationEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = empInfo.fluctuationEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String ResignEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = empInfo.resignEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String UpdateEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = empInfo.updateEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}
}
