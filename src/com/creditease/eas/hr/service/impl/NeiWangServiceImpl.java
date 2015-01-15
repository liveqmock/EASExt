package com.creditease.eas.hr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.NeiWangQuery;
import com.creditease.eas.hr.service.INeiWangService;
import com.creditease.eas.util.DeepClone;
import com.creditease.eas.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class NeiWangServiceImpl implements INeiWangService{
	//查询数据用的query
	private NeiWangQuery neiw = new NeiWangQuery();
	@Override
	public String selectedBirthdayList(String beginTime) {
		Date dtbeginTime = StringUtil.strToDate(beginTime,"yyyy-MM-dd");
		List<Map<String,Object>> list = neiw.selectBirthDayList(dtbeginTime);
		List listTemp = DeepClone.putListToMap(list);
		String json = StringUtil.convertListToGson(listTemp);
		return json;
	}
	@Override
	public String selectedWorkPerson(String beginTime) {
		Date dtbeginTime = StringUtil.strToDate(beginTime,"yyyy-MM-dd");
		List<Map<String,Object>> list = neiw.selectWorkPerson(dtbeginTime);
		List listTemp = DeepClone.putListToMap(list);
		String json = StringUtil.convertListToGson(listTemp);
		return json;
	}

}
