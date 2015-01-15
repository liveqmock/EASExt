package com.creditease.eas.warn.kingdee.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Person;

public interface PersonMapper extends BaseDAO<Person, Integer>{
	/**
	 * 描述：根据直接上级的职位编码进行分组
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectImmediateSuperiorPositions(List<String> numlist);
	/**
	 * 描述：根据间接上级的职位编码进行分组
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectIndirectSuperiorPositions(List<String> numlist);
	/**
	 * 描述：根据部门负责人职位编码进行分组
	 * 2012-12-26 下午11:07:12 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,String>> selectResponsePersonOrg(List<String> numlist);
	/**
	 * 描述：根据直接/间接上级的职位ID，查询直接/间接上级的人员信息
	 * 2012-12-26 下午11:53:40 by ygq
	 * @version
	 * @return
	 */
	public List<Map<String,Object>> selectSuperiorPerson(String positionId);
	/**
	 * 描述：据直接上级的职位ID，查询直接上级对应的直接下属的信息
	 * 2013-1-31 下午07:02:24 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectImmediatePersonInfoByParentPositionId(String positionId); 
	/**
	 * 描述：据间接上级的职位ID，查询间接上级对应的下属的信息
	 * 2013-1-31 下午07:02:24 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectIndirectPersonInfoByParentPositionId(String positionId); 
	/**
	 * 描述：根据部门负责人的编码查询，对应的部门负责人
	 * 2013-1-31 下午07:02:24 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectResponsePersonInfo(String positionId); 
	/**
	 * 描述：根据部门负责人的编码查询对应的该部门下有多少需要转正的人员
	 * 2013-1-31 下午07:02:24 by ygq
	 * @version
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectUnderResponsePersonInfo(String orgnumber); 
	/**
	 * 获取上级职位对应的过生日的直接下属员工
	 * @author lining
	 * @since 2014-5-26
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectImmediatePersonInfoByParentPositionIdOnBirthdayWarn(String positionId);
	/**
	 * 获取上级职位对应的过生日的间接下属员工
	 * @author lining
	 * @since 2014-5-26
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectIndirectPersonInfoByParentPositionIdOnBirthdayWarn(String positionId); 
	/**
	 * 获取部门负责人对应的过生日的下属
	 * @author lining
	 * @since 2014-5-26
	 * @param positionId
	 * @return
	 */
	public List<Map<String,Object>> selectUnderResponsePersonInfoOnBirthdayWarn(String positionId); 
}
