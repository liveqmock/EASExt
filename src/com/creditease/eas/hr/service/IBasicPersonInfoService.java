package com.creditease.eas.hr.service;

/***
 * 人员基本信息接口
 * @Title:IBasePersonInfoService.java
 * @Package com.creditease.eas.hr.service
 * @version 1.0
 */
public interface IBasicPersonInfoService {
	/***
	 * 根据参数查询人员的信息
	* @Title: queryPersonInfoByParams
	*created at 2014-6-15 下午02:41:51 by ygq  
	* @param json
	* @return
	* @return String
	 */
	public String queryPersonInfoByParams(String json);

	
}
