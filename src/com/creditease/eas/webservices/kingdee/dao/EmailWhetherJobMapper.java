package com.creditease.eas.webservices.kingdee.dao;

import java.util.List;

public interface EmailWhetherJobMapper {
	//根据宜信邮箱查询此邮箱账户对应人员是否正式在职员工
	public List emailWorkingConditions(String cfmail);
}
