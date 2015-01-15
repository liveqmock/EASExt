package com.creditease.eas.institutional.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.webservice.dto.GroupInfoDTO;

/**
 * EAS系统行政组织信息访问接口
 * @author lining
 *
 */
public interface IEASOrgDao {

	/**
	 * 获取所有行政组织信息
	 * @return
	 * @throws Exception
	 */
	List<GroupInfoDTO> selectAllOrg() throws Exception;
	/**
	 * 获取新增组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	List<GroupInfoDTO> selectAddOrg(Map<String,Object> paramMap) throws Exception;
	/**
	 * 获取所有更新组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	List<GroupInfoDTO> selectUpdateOrg(Map<String,Object> paramMap) throws Exception;
	/**
	 * 获取所有无效组织信息
	 * @param paramMap 查询条件参数（beginTime,endTime）
	 * @return
	 * @throws Exception
	 */
	List<GroupInfoDTO> selectInvalidOrg(Map<String,Object> paramMap) throws Exception;
	
}
