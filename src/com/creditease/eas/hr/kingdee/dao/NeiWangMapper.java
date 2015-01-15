package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;

/***
 * 内网的接口
 * 提供每天过生日的人员名单和司龄满周年的人员名单
 * @author ygq
 * @version 1.0 2013/12/16 18:55
 */
public interface NeiWangMapper {
	/***
	 * 获得司龄满周年的员工名单
	 * @return
	 */
	public List<Map<String,Object>> getWorkPerson();
	/***
	 * 提供每天过生日的人员名单
	 * @return
	 */
	public List<Map<String,Object>> birthDayList();
}
