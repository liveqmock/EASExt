package com.creditease.eas.webservices.service.impl;

import java.util.List;

import com.creditease.eas.webservices.kingdee.query.EmailWhetherJobQuery;
import com.creditease.eas.webservices.service.IEmailWhetherJobService;
/**
 * 
 * <p>Title: EmailWhetherJobServiceImpl</p>
 * <p>Description: 根据宜信邮箱判断此邮箱账户对应人员是否正式在职员工，反馈结果TRUE OR FALSE</p>
 * <p>date : 2014-7-21 下午02:11:35 </p>
 * @author gaoliya_thinking
 * @version 1.0
 */
public class EmailWhetherJobServiceImpl implements IEmailWhetherJobService{
		EmailWhetherJobQuery emailquery=new EmailWhetherJobQuery();
	@Override
	public boolean emailWorkingConditions(String cfmail) {
		List email=emailquery.emailWorkingConditions(cfmail);
		if (email.size()>0) {
			return true;
		}else {
			return false;
		}
		
	}

}
