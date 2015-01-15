package com.creditease.eas.warn.kingdee.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Person;

public interface ContractMapper extends BaseDAO<Person, Integer>{
	/**
	 * 
	 * 描述：查询合同相关的信息
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectContractInfo();
	/**
	 * 
	 * 描述：查询合同快到期的人员的分组的id
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectPositions();
	/**
	 * 
	 * 描述：查询合同快到期的人员的信息
	 * 2013-1-10 上午11:23:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectContractPersonInfoByPositionId(String positionId);
	/**
	 * 
	 * 描述：查询所有的人员的信息
	 * 2013-1-14 下午02:20:03 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>>  selectPersons()throws Exception;
	/**
	 * 
	 * 描述：查询所有 的职位的id
	 * 2013-1-14 下午03:16:32 by ygq
	 * @version
	 * @param personId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>>  selectPositionByPersonId(String personId)throws Exception;
	/**
	 * 
	 * 描述：查询所有的人员的职位信息
	 * 2013-1-14 下午02:20:03 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object>  selectAdminInfo(String positionId)throws Exception;
	
	/**
	 * 
	 * 描述：合同续签人员名单
	 * 2013-2-1 下午02:43:42 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>>  selectRenewalList()throws Exception;
	
	public List<Map<String,Object>>  selectRenewalListById(String strid)throws Exception;
	
	public List<Map<String,Object>> test() throws Exception;
	
	public List<Map<String,Object>>  selectDeptList(List<String> numlist)throws Exception;
	
	public Map<String,Object>  getPersonByFnumber(String fnumber)throws Exception;
	
	public Map<String,Object>  getOrgByFnumber(String fnumber);
	
}
