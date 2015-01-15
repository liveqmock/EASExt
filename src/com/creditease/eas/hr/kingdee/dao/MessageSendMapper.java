package com.creditease.eas.hr.kingdee.dao;
import java.util.List;
import java.util.Map;
public interface MessageSendMapper {
	/**
	 * 
	 * 描述：城市信息
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryCityByCode(String code);
	/**
	 * 
	 * 描述：职位等级
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryPositionGradeByCode(String code);
	/**
	 * 
	 * 描述：职位类别
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryPositionTypeByCode(String code);
	/**
	 * 
	 * 描述：员工状态
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>> queryEmployeeStatusByCode(String code);
//	/**
//	 * 
//	 * 描述：职位测试
//	 * 2013-1-21 下午01:44:35 by ygq
//	 * @version
//	 * @param code
//	 * @return
//	 */
//	public List<Map<String,Object>> queryByCodeTest(Map map);
	/***********************************************下面三个方法为信息推送的功能**********************************************************/
	/**
	 * 
	 * 描述：查询组织变动信息(需要有一个开始时间，结束时间)
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> queryChangeOrgInfo(Map map);
	/**
	 * 
	 * 描述：查询职位变动信息
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */ 
	public List<Map<String,Object>> queryChangePositionInfo(Map map);
	/**
	 * 
	 * 描述：查询人员变动信息
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> queryChangePersonInfo(Map map);
}