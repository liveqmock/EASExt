/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */     
package com.creditease.eas.webservices.service.impl;    

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.StringUtil;
import com.creditease.eas.webservices.kingdee.query.CEPurseQuery;
import com.creditease.eas.webservices.service.CEPurseService;
    
public class CEPurseServiceImpl implements CEPurseService{
	
	CEPurseQuery cepurse = new CEPurseQuery();
	public String validateEmployeeQuery(String params) {
		Map<String, String> map= new HashMap<String, String>();
		map=StringUtil.convertJsonToMap(params);
		List<HashMap<String, Object>> employeeQuery = cepurse.validateEmployeeQuery(map);
		return StringUtil.convertListToGson(employeeQuery);
	}

}
  