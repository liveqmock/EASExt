package com.creditease.eas.hrnew.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.smp.manager.dto.EASEmployeeDTO;
import com.creditease.smp.manager.dto.EASOrganizeDTO;
import com.creditease.smp.manager.dto.EASPositionDTO;
import com.creditease.smp.manager.dto.EASTransferDTO;


public interface WSSMPPersonMapper{
	
	/**
	 *   查询内部异动
	 * @param map
	 * @return
	 */
	public List<EASTransferDTO> selectFluctuation(Map<String, Object> map);
	
	/**
	 *  查询人员信息
	 * @return
	 */
	public List<EASEmployeeDTO> selectPerson(Map<String, Object> map);
	
	
	/**
	 * 
	 * 描述：组织信息
	 * 2013-6-25 下午02:30:31 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<EASOrganizeDTO> selectOrgAdminQuery(Map<String, Object> map);
	/**
	 * 
	 * 描述：职位信息
	 * 2013-6-25 下午04:55:46 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public List<EASPositionDTO> selectOrgPositionQuery(Map<String, Object> map);
	
	
}