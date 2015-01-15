package com.creditease.eas.hr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.kingdee.query.BasePersonInfoQuery;
import com.creditease.eas.hr.service.IBasicPersonInfoService;
import com.creditease.eas.util.StringUtil;
@Service("basicPersonInfoService")
public class BasicPersonInfoServiceImpl implements IBasicPersonInfoService {
	
	BasePersonInfoQuery baseInfo = new BasePersonInfoQuery();
	
	@Override
	public String queryPersonInfoByParams(String json) {
		Map map = StringUtil.convertJsonToMap(json);
		List<Map<String,Object>> list = baseInfo.queryPersonInfoByParams(map);
		return StringUtil.convertListToGson(list);
	}

}
