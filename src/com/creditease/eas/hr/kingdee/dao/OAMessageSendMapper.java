package com.creditease.eas.hr.kingdee.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.hr.bean.extoa.WSOAOrganization;
import com.creditease.eas.hr.bean.extoa.WSOAPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPluralityPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPosition;
/***
 * 主要是用于给OA提供人员，职位，组织的接口
 * @OAMessageSendMapper.java
 * created at 2013-10-17 上午09:53:02 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface OAMessageSendMapper {
	/***********************************************下面三个方法为信息拉取的功能**********************************************************/
	/**
	 * 
	 * 描述：查询组织变动信息(需要有一个开始时间，结束时间)
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> queryChangeOrgInfo(Map map);
	/***
	 * 查询成本中心的数量
	* @Title: getTotalOrgCount
	*created at 2014-6-18 下午09:58:44 by ygq  
	* @param map
	* @return
	* @return int
	 */
	public int getTotalOrgCount(Map map);
	/**
	 * 
	 * 描述：查询成本中心的(需要有一个开始时间，结束时间)
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> queryChangeCostcenterInfo(Map map);
	/***
	 * 查询成本中心的数量
	* @Title: getTotalOrgCount
	*created at 2014-6-18 下午09:58:44 by ygq  
	* @param map
	* @return
	* @return int
	 */
	public int getTotalCostCenterCount(Map map);
	/***
	 * 查询组织负责人对应的信息
	 * @return
	 */
	public List<Map<String,Object>> queryResponPositionInfo(Map map);
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
	public List<Map<String,Object>>  queryChangePersonInfo(Map map);
//	-----查出成本中心对应的id，查
//	
//	---数据量访问量
	/***
	 * 查询人员，职位中间表的信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPersonPositionTempInfo(Map map);
	/***
	 * 查询人员，职位中间表的数量
	 * @param map
	 * @return
	 */
	public Integer queryPersonPositionTempInfoCount(Map map);
	
	/***
	 * 查询合同信息
	 * @return
	 */
	public List<Map<String,Object>> queryContractInfo(Map map);
	/***
	 * 查询合同信息的数量
	 * @return
	 */
	public Integer queryContractInfoCount(Map map);
	/****
	 * 查询人员任职历史记录
	* @Title: queryWorkExpCur
	*created at 2014-6-12 下午03:23:41 by ygq  
	* @param map
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryWorkExpCur(Map map);
	/***
	 * 查询人员任职历史记录的数量
	* @Title: queryContractInfoCount
	*created at 2014-6-12 下午03:25:40 by ygq  
	* @param map
	* @return
	* @return Integer
	 */
	public Integer queryWorkExpCurCount(Map map);
	
}