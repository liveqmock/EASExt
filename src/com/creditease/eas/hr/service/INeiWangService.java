package com.creditease.eas.hr.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/***
 * 为内网提供数据（不通过WebService接口
 * @author ygq
 */
public interface INeiWangService {
	/***
	 * 获得每天过生日的的员工名单
	 * @param beginTime
	 * @param dt
	 * @param fnumber
	 * @return
	 */
	public  String selectedBirthdayList(String beginTime);
	/***
	 * 获得司龄满周年的员工名单
	 * @param beginTime
	 * @param dt
	 * @param fnumber
	 * @return
	 */
	public  String selectedWorkPerson(String beginTime);
}
