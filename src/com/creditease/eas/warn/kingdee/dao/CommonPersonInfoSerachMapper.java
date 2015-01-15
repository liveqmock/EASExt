package com.creditease.eas.warn.kingdee.dao;

import java.util.List;
import java.util.Map;

public interface CommonPersonInfoSerachMapper {
	/**
	 * 
	 * 描述：查询根据职位id查询部门领导的名字和邮件(这样查询出来的有问题，需要再过滤下级是当前人员)
	 * 2012-12-27 上午12:18:13 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectHighLeverPersonByPositionId(String positionId);
	/**
	 * 描述：查询部门负责人的信息
	 * 2013-1-9 上午11:16:17 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectResponsePersonInfo(String positionId);
}
