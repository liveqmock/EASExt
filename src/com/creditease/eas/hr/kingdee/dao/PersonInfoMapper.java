package com.creditease.eas.hr.kingdee.dao;

import java.util.Map;

import com.creditease.eas.hr.bean.PersonInfo;
import com.creditease.eas.util.BaseDAO;


public interface PersonInfoMapper extends BaseDAO<Map, Integer>{

	
	/**
	 * 描述：根据员工编码（判断是否是备用金负责人及获取员工是否借款）
	 */
	public PersonInfo getFnumber(String fnumber);
	
	public Map<String, Object> getByj(String fnumber);
}
