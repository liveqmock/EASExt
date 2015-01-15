package com.creditease.eas.hr.kingdee.dao;


public interface EmailForEmployeeCodeMapper {
	/**
	 * 根据邮箱前缀获得员工的12位员工编码
	 */
	public String queryFnumberByEmail(String femail);

}
