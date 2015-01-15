package com.creditease.eas.institutional.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.creditease.webservice.dto.UserInfoDTO;
 


public interface WSInstitutionalPersonMapper{
	 
	
	/**
	 *  查询人员信息
	 * @return
	 */
	public List<UserInfoDTO> selectPerson(Map<String, Object> map);
	
	 
	
	
}