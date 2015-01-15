package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.Log;
import com.creditease.eas.util.BaseDAO;

public interface LogMapper extends BaseDAO<Log, Integer>{
	void insertLog(Log log);

	List<Map> queryPageByParamss(Map m);
}

