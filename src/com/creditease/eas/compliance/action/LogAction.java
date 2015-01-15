package com.creditease.eas.compliance.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.service.LogService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Pagination;

@Controller
@Scope("prototype")
public class LogAction extends BaseAction{
	private static Logger logger = Logger.getLogger(CaseInfoAction.class);
	
	protected Pagination pagination = new Pagination();
	
	@Autowired
	private LogService logService;
	public String queryPageJson() throws Exception {
		try {
			this.pagination = logService.queryPage(pagination,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
}
