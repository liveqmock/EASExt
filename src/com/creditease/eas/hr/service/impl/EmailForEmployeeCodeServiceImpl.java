package com.creditease.eas.hr.service.impl;



import com.creditease.eas.hr.kingdee.query.EmailForEmployeeCodeQuery;
import com.creditease.eas.hr.service.IEmailForEmployeeCodeService;

public class EmailForEmployeeCodeServiceImpl implements
		IEmailForEmployeeCodeService {
	EmailForEmployeeCodeQuery emailquery=new EmailForEmployeeCodeQuery();
	@Override
	public String queryFnumberByEmail(String femail) {
		String cemail=emailquery.queryFnumberByEmail(femail);
		return cemail;
	}

}
