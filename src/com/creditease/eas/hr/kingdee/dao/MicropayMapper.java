package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;
/***
 * 小微接口
 *MIcropayMapper
 * @author admin 2014-4-20
 */
public interface MicropayMapper {
	/***
	 * 提供员工信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryEmployeeInfo(Map map);
}
