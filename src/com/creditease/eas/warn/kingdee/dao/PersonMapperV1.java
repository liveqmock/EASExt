package com.creditease.eas.warn.kingdee.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Person;

public interface PersonMapperV1 extends BaseDAO<Person, Integer>{
	public List<Map<String,Object>> testQuery();
	public List<Map<String,Object>> selectPersonInfo();
	/**
	 * 
	 * 描述：查询岗位的分组的信息
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectPositions();
	/**
	 * 
	 * 描述：根据岗位的id，查询对应的将要转正的人员的信息
	 * 2012-12-26 下午11:53:40 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectPersonInfoByPositionId(String positionId);
	/**
	 * 描述：新的版本
	 * 2013-1-31 下午07:02:24 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectPersonInfoByPositionIdc1(String positionId); 
	/***************************************************下面的语句为测试信息***********************************************************************************/
	/**
	 * 
	 * 描述：
	 * 2012-12-27 下午05:02:59 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectPositionsAll();
	/**
	 * 
	 * 描述：查询所有的要转正人员的相关的信息
	 * 2012-12-27 下午05:22:34 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectHighLeverPersonByPositionIdAll(String positionId);
	/**
	 * 
	 * 描述：根据职位的id查询职位的名字
	 * 2012-12-27 下午05:45:09 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public String queryPositionNameById(String positionId);
	/**
	 * 查询负责人的信息
	 */
	public List<Map<String,Object>> testFuzeren(String positionId);
}
