package com.creditease.eas.hr.service.impl;

import java.util.Map;

import com.creditease.eas.hr.kingdee.query.BasePersonInfoQuery;
import com.creditease.eas.hr.service.IBasePersonInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasePersonInfoServiceImpl implements IBasePersonInfoService {

	BasePersonInfoQuery baseInfo = new BasePersonInfoQuery();
	
	@Override
	public String getPersonInfoByNumber(String fnumber) {
		Map<String,Object> map = baseInfo.getPersonInfoByNumber(fnumber);
		Gson json = new GsonBuilder().create();
		return json.toJson(map);
	}

}
