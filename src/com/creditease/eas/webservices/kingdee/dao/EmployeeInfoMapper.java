package com.creditease.eas.webservices.kingdee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeInfoMapper {
	public List<HashMap<String, Object>> enrollEmployeeQuery(Map map);

	public List<HashMap<String, Object>> resignEmployeeQuery(Map map);

	public List<HashMap<String, Object>> validateEmployeeQuery(Map map);

	public List<HashMap<String, Object>> fluctuationEmployeeQuery(Map map);
	
	public List<HashMap<String, Object>> updateEmployeeQuery(Map map);

}
