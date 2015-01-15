package com.creditease.eas.hr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.MicropayQuery;
import com.creditease.eas.hr.service.IMicropayService;
import com.creditease.eas.util.DeepClone;
import com.creditease.eas.util.StringUtil;

@Service
public class MicropayServiceImpl implements IMicropayService{
	//查询数据用的query
	private MicropayQuery micropayQuery = new MicropayQuery();
	@Override
	public String queryEmployeeInfo(String fnumber) {
		List<Map<String,Object>> list = micropayQuery.queryEmployeeInfo(fnumber);
//		List listTemp = DeepClone.putListToMap(list);
		String json = StringUtil.convertListToGson(list);
		return json;
	}

}
