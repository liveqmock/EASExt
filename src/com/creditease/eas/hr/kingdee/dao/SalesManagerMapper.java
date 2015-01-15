package com.creditease.eas.hr.kingdee.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
public interface SalesManagerMapper {
	/***
	 * 查询转正的人员信息
	 * @param paramMap 传递的查询参数
	 * @return 离职的人员信息
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String,Object>> personTransformQuery(Map paramMap);
	
	/***
	 * 查询异动的人员信息
	 * @param paramMap 封装的人员信息
	 * @return 异动的人员信息
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String,Object>> personUnusualActionQuery(Map paramMap);
	/**
	 * 查询离职的人员信息
	 * @param paramMap 查询条件
	 * @return 离职人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String,Object>> personLeaveQuery(Map paramMap);
	/***
	 * 查询添加的组织信息
	 * @param paramMap 封装的组织信息
	 * @return 组织信息列表
	 */
	public List<Map<String,Object>> orgAddQuery(Map paramMap);
	/***
	 * 查询更新的组织信息
	 * @param paramMap 封装的组织信息
	 * @return 组织信息列表
	 */
	public List<Map<String,Object>> orgUpdateQuery(Map paramMap);
	/**
	 * 查询组织的职位信息
	 * @param paramMap 查询条件
	 * @return 组织的职位信息列表
	 * @author ln 2014/01/16 16:14
	 */
	public List<Map<String,Object>> rankQuery(Map paramMap);
	/**
	 * 查询新增的人员信息
	 * @param jsonParam 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personAddQuery(Map paramMap);
	/**
	 * 查询新增人员的职位信息
	 * @param paramMap 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personAddPositionQuery(Map paramMap);
	/**
	 * 查询新增人员的合同信息
	 * @param paramMap 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personAddContractQuery(Map paramMap);
	/**
	 * 查询新增人员的学历信息
	 * @param paramMap 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personAddDegreeQuery(Map paramMap);
	/**
	 * 查询更新的人员信息
	 * @param paramMap 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personUpdateQuery(Map paramMap);
	/**
	 * 查询更新人员的职位信息
	 * @param paramMap 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personUpdatePositionQuery(Map paramMap);
	/**
	 * 查询更新人员的合同信息
	 * @param paramMap 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personUpdateContractQuery(Map paramMap);
	/**
	 * 查询更新人员的学历信息
	 * @param paramMap 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String,Object>> personUpdateDegreeQuery(Map paramMap);
//	/**
//	 * 查询计薪人员信息
//	 * @param tempTable 查询条件
//	 * @return 计薪人员信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String, Object>> salaryQuery(@Param(value="tempTable") String tempTable);
//	/**
//	 * 查询计薪人员信息
//	 * @param paramMap 查询条件
//	 * @return 计薪人员信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String,Object>> salaryPersonQuery(Map paramMap);
	
//	/**
//	 * 查询计薪人员薪酬更新信息
//	 * @param paramMap 查询条件
//	 * @return 计薪人员薪酬更新信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String, Object>> salaryUpdateQuery(Map paramMap);

	/**
	 * 查询计薪人员薪酬发放表
	 * @return 计薪人员薪酬发放表列表
	 * @author ln 2014/04/05 10:37
	 */
	public List<String> salaryTableQuery();

	/**
	 * 计薪人员查询
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> paidStaffQuery(Map<String, Object> paramMap);

	/**
	 * 员工薪酬查询
	 * @param tempTable
	 * @return
	 */
	public List<Map<String, Object>> paymentQuery(
			@Param(value="tempTable") String tempTable);

}
