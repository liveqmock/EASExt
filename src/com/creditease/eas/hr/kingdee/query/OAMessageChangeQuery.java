package com.creditease.eas.hr.kingdee.query;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.bean.extoa.WSOAOrganization;
import com.creditease.eas.hr.bean.extoa.WSOAPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPluralityPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPosition;
import com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
/**
 * 组织，机构，人员变动信息查询（信息推送）
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class OAMessageChangeQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：组织变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  List<Map<String,Object>> selectListChangeOrgInfo(Map map){
		SqlSession session = null;
		try {
			session = getSession();
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.queryChangeOrgInfo",map);
			int totalCount = this.getTotalOrgCount(map);
			Map mapCount = new HashMap();
			mapCount.put("totalCount", totalCount);
			list.add(0, mapCount);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：查询组织变更的总条数
	 * 2013-11-19 下午04:32:59 by zhangxin
	 * @version
	 * @param map
	 * @return int
	 */
	public Integer getTotalOrgCount(Map map){
		Integer totalOrgCount = 0;
		SqlSession session = null;
		try {
			session = getSession();
			totalOrgCount = (Integer) session.selectOne("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.getTotalOrgCount", map);
		}catch (Exception e) {
			totalOrgCount = 0;
		}finally{
				closeSession(session);
		}
		return totalOrgCount;
	}
	/**
	 * 
	 * 描述：成本中心变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  List<Map<String,Object>> selectListChangeCostcenterInfo(Map map){
		SqlSession session = null;
		try {
			session = getSession();
			OAMessageSendMapper oaMessageSendMapper = session.getMapper(OAMessageSendMapper.class);
			List<Map<String,Object>> list = oaMessageSendMapper.queryChangeCostcenterInfo(map);
			int totalCount = oaMessageSendMapper.getTotalCostCenterCount(map);
			Map mapCount = new HashMap();
			mapCount.put("totalCount", totalCount);
			list.add(0, mapCount);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：查询组织关联的负责人的信息
	 * 2015-5-13 下午04:47 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryResponPositionInfo(Map map){
		SqlSession session = null;
		try {
			session = getSession();
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.queryResponPositionInfo",map);
			return list;
		}catch (Exception e) {
			return null;
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：职位变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  List<Map<String,Object>>  selectChangePositionInfo(Map map){
		SqlSession session = null;
		try {
			session = getSession();
			List<Map<String,Object>>  list = session.selectList("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.queryChangePositionInfo",map);
			int totalPositionCount = this.getTotalPositionCount(map);
			Map mapCount = new HashMap();
			mapCount.put("totalCount", totalPositionCount);
			list.add(0, mapCount);
			return list;
		}catch (Exception e) {
			return null;
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：查询职位变更的总条数
	 * 2013-11-19 下午04:53:23 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public Integer getTotalPositionCount(Map map){
		Integer totalPositionCount = 0;
		SqlSession session = null;
		try {
			session = getSession();
			totalPositionCount = (Integer) session.selectOne("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.getTotalPositionCount", map);
		}catch (Exception e) {
			return null;
		}finally{
				closeSession(session);
		}
		return totalPositionCount;
	}
	
	
	/**
	 * 
	 * 描述：人员变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public   List<Map<String,Object>> selectChangePersonInfo(Map map){
		//职位整合
		List<Map<String,Object>> list1 =  new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			list1 = session.selectList("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.queryChangePersonInfo",map);
			int totalCount = this.getTotalpersonCount(map);
			Map mapCount = new HashMap();
			mapCount.put("totalCount", totalCount);
			list1.add(0, mapCount);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list1;
	}
	/**
	 * 
	 * 描述：查询人员总条数
	 * 2013-11-21 下午04:10:28 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public Integer getTotalpersonCount(Map map){
		Integer totalpersonCount = 0;
		SqlSession session = null;
		WSOAPerson wsoaPerson = new WSOAPerson();
		try {
			session = getSession();
			totalpersonCount = (Integer) session.selectOne("com.creditease.eas.hr.kingdee.dao.OAMessageSendMapper.getTotalpersonCount", map);
			if(totalpersonCount!= null){
				wsoaPerson.setTotalPersonCount(totalpersonCount);
			}else{
				wsoaPerson.setTotalPersonCount(0);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
				closeSession(session);
			}
		return totalpersonCount;
	}
	/**
	 * 
	 * 描述：查询人员职位中间表的数据
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,Object>> queryPersonPositionTempInfo(Map map){
		//职位整合
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			OAMessageSendMapper mapper = session.getMapper(OAMessageSendMapper.class);
			int count = mapper.queryPersonPositionTempInfoCount(map);
			Map mapCount  = new HashMap();
			mapCount.put("totalCount",count);
			//查询人员职位中间表的数量
			list.add(mapCount);
			//查询人员职位中间表
			List<Map<String,Object>> listInfo = mapper.queryPersonPositionTempInfo(map);
			if(null != listInfo){
				list.addAll(listInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：查询人员关联的合同信息
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,Object>> selectContractInfo(Map map){
		//职位整合
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			OAMessageSendMapper mapper = session.getMapper(OAMessageSendMapper.class);
			int count = mapper.queryContractInfoCount(map);
			Map mapCount  = new HashMap();
			mapCount.put("totalCount",count);
			//查询数量
			list.add(mapCount);
			//查询合同信息
			List<Map<String,Object>> listInfo = mapper.queryContractInfo(map);
			if(null != listInfo){
				list.addAll(listInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/****
	 * 查询人员的任职
	* @Title: queryWorkExpCur
	*created at 2014-6-12 下午03:27:27 by ygq  
	* @param map
	* @return
	* @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public  List<Map<String,Object>> queryWorkExpCur(Map map){
		//职位整合
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		SqlSession session = null;
		try {
			session = getSession();
			OAMessageSendMapper mapper = session.getMapper(OAMessageSendMapper.class);
			int count = mapper.queryWorkExpCurCount(map);
			Map mapCount  = new HashMap();
			mapCount.put("totalCount",count);
			//查询人员职位中间表的数量
			list.add(mapCount);
			//查询人员职位中间表
			List<Map<String,Object>> listInfo = mapper.queryWorkExpCur(map);
			if(null != listInfo){
				list.addAll(listInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
}


