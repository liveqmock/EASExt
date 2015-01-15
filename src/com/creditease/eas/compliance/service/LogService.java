package com.creditease.eas.compliance.service;

import javax.servlet.http.HttpServletRequest;

import com.creditease.eas.compliance.bean.Log;
import com.creditease.eas.util.Pagination;

public interface LogService {
	void insertLog(Log log);

	Pagination queryPage(Pagination pagination, HttpServletRequest request) throws Exception;
}
