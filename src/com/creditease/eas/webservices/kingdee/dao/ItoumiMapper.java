package com.creditease.eas.webservices.kingdee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItoumiMapper {
	public List<HashMap<String, Object>> enrollEmployeeQuery(Map map);

	public List<HashMap<String, Object>> resignEmployeeQuery(Map map);

	public List<HashMap<String, Object>> validateEmployeeQuery(Map map);

	public List<HashMap<String, Object>> fluctuationEmployeeQuery(Map map);
	
	public Map<String, String> validateCardTypeQuery(Map map);
	
}
