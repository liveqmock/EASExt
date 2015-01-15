package com.creditease.eas.hr.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.BankInformationQuery;
import com.creditease.eas.hr.service.IBankInformationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("bankInformationServiceImpl")
public class BankInformationServiceImpl implements IBankInformationService {
	BankInformationQuery bankquery=new BankInformationQuery();
	@Override
	
	public String getBankInfo(int pageSize, int pageNo) {
		Map<String, Object> map=bankquery.getBankInfo(pageSize, pageNo);
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(map);
		return json;
	}

	@Override
	public String getBankInfoById(String fid, int pageSize, int pageNo) {
		Map<String, Object> map=bankquery.getBankInfoById(fid, pageSize, pageNo);
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(map);
		return json;
	}

}
