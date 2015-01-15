package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.util.Dictionary;

/**
 * 主要用于查询和投诉相关的一些公用的信息
 * @ComplainCommonMapper.java
 * created at 2013-10-14 下午03:37:16 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ComplainCommonMapper {
	/***
	 * 描述：查询客户信息
	 * 2013-10-14 下午03:45:05 by ygq
	 * @version
	 * @return
	 */
	public List<Dictionary> findCussource();
	/**
	 * 
	 * 描述：查询服务类型
	 * 2013-10-16 下午04:34:35 by ygq
	 * @version
	 * @return
	 */
	public List<Dictionary> findServicetype();
	/***
	 * 描述：查询客户状态
	 * 2013-10-14 下午03:45:05 by ygq
	 * @version
	 * @return
	 */
	public List<Dictionary> findCusstatus(Map map);
	/***
	 * 
	 * 描述：查询某个案件类型的最大的案件信息
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return
	 */
	public Integer findMaxComplain();
	/***
	 * 
	 * 描述：查询某个案件类型的最大的案件信息
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return
	 */
	public Map<String,Object> findinicasetype(Integer finicasetypeid);
	/***
	 * 
	 * 描述：投诉渠道 
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return
	 */
	public List<Dictionary> findDitch();
	/***
	 * 描述:证据类型
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return
	 */
	public List<Dictionary> findEvidenceType();
	/**
	 * 查询案件初步分类 
	 * @return
	 */
	public List<Dictionary> findInicaseType();
	/**
	 * 查询案件详细分类
	 * @return
	 */
	public List<Dictionary> findDetailCaseType(Integer finicasetype);
	/***
	 * 根据被投诉人的姓名查询这个人被投诉的次数
	 * @param map
	 * @return
	 */
	public Integer findPersonCompCount(Map map);
	/***
	 * 根据被投诉人的姓名查询涉及到的案子
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPersonRelComplian(Map map);
	/**
	 * 
	 * 描述：根据初步分类类型获得初步分类id
	 * @return
	 */
	List<String> getTypeNameById(String[] finicasetypes);
	/**
	 * 
	 * 描述：根据手机号查询该手机号被投诉的次数
	 * 2013-11-12 下午03:15:01 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public Integer findFmobileCount(Map<String, Object> map);
	/**
	 * 
	 * 描述：根据QQ号查询该QQ号被投诉次数
	 * 2013-11-12 下午03:50:19 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public Integer findFqqCount(Map<String, Object> map);
	/**
	 * 
	 * 描述：根据客户姓名查询该客户被投诉次数
	 * 2013-11-13 下午02:20:07 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public Integer findFcusNameCount(Map<String, Object> map);
	
	public List<CaseType> selectAllCaseType();
	
	/**
	 * 得到新分类详细总数
	 * @param map
	 * @return
	 */
	public int getCaseTypeDetailsTotalCountsByParams(Map map);
	
	/**
	 *得到新分类详细列表 
	 * @param map2
	 * @return
	 */
	public List<Map> queryCaseTypeDetailsPageByParamss(Map map2);
	
	/**
	 * 去除关联自身的案子
	 * @param map
	 * @return
	 */
	public Integer findPersonCompCountExceptSelf(Map map);
	
	/**
	 * 关联除自身的案子
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> findPersonRelComplianExceptSelf(
			Map<String, Object> map);
	
	public List<Dictionary> findCaseFrom();
}
