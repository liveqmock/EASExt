package com.creditease.eas.webservices.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.StringUtil;
import com.creditease.eas.webservices.kingdee.query.ItoumiMapperQuery;
import com.creditease.eas.webservices.service.ItoumiService;

public class ItoumiServiceImpl implements ItoumiService{
	private ItoumiMapperQuery itoumi = new ItoumiMapperQuery();
	@Override
	public String EnrollEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = itoumi.enrollEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String FluctuationEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = itoumi.fluctuationEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String ResignEmployeeQuery(String dataTime) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(dataTime);
		List<HashMap<String, Object>> list = itoumi.resignEmployeeQuery(map);
		return StringUtil.convertListToGson(list);
	}

	@Override
	public String ValidateEmployeeQuery(String empInfo) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(empInfo);
		List<HashMap<String, Object>> list = itoumi.validateEmployeeQuery(map);
		if(null == list || 0 == list.size()){
			HashMap<String, Object> noEmp= new HashMap<String, Object>();
			noEmp.put("hasEmp", "false");
			list.add(noEmp);
		}
		return StringUtil.convertListToGson(list);
	}
	
	public static void main(String[] args) {
		ItoumiServiceImpl impl = new ItoumiServiceImpl();
		String dataTime="{'empNO':'201211021145','cardNO':'dsf'}";
		String validateEmployeeQuery = impl.ValidateEmployeeQuery(dataTime);
		System.out.println(validateEmployeeQuery);
	}
	
}
