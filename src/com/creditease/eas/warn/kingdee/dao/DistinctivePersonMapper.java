package com.creditease.eas.warn.kingdee.dao;

import java.util.List;
import java.util.Map;

/**
 * 信管需求，解决转正和合同邮件发送的功能
 * @author ygq
 *
 */
public interface DistinctivePersonMapper {
	/**
	 * 查询系统中信管部门相关 的
	 * @return
	 */
	public List<Map<String,String>> selectRequiredPerson();
	
}
