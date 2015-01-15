package com.creditease.eas.institutional.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.institutional.bean.EASPerson;

/**
 * EAS系统人员信息访问接口
 * @author lining
 *
 */
public interface IEASPersonDao {

	/**
	 * 查询所有需要的人员信息
	 * @return 人员信息集合
	 * @throws Exception
	 */
	List<EASPerson> selectAllPerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询新增人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	List<EASPerson> selectAddPerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询信息更新人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	List<EASPerson> selectUpdatePerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询异动（所属部门的调整——添加新部门，删除旧部门记录）人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	List<EASPerson> selectAlterPerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询信息无效（离职、退休、不在职）人员信息
	 * @param paramMap 查询条件参数
	 * @return
	 * @throws Exception
	 */
	List<EASPerson> selectInvalidPerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询人员兼职数据
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<EASPerson> selectPersonAvocation(Map<String,Object> paramMap) throws Exception;
}
