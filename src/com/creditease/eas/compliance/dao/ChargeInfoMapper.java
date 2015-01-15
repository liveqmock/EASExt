package com.creditease.eas.compliance.dao;

import java.util.List;

import com.creditease.eas.compliance.bean.ChargeInfo;
import com.creditease.eas.util.BaseDAO;

/**
 * 收费信息的接口(与案件关联)
 * @author liruyi
 *
 */
public interface ChargeInfoMapper extends BaseDAO<ChargeInfoMapper, Integer>{
	/**
	 * 返回与案件ID关联的收费信息
	 * @param complainId 案件ID
	 * @return 收费信息 List<ChargeInfo> 
	 */
	public List<ChargeInfo> selectChargeInfoByComplainId(int complainId);
	
	/**
	 * 删除与案件ID关联的收费信息
	 * @param complainId 案件ID
	 */
	public void deleteChargeInfoByComplainId(Integer complainId);
	
	/**
	 * 
	 * @param chargeInfo 收费信息
	 * @return 是否删除成功
	 */
	public int insert(ChargeInfo chargeInfo);
}
