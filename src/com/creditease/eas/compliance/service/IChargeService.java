package com.creditease.eas.compliance.service;

import java.util.List;

import com.creditease.eas.compliance.bean.ChargeInfo;

/**
 * 收费信息的Service类(与案件相关)
 * @author liruyi
 *
 */
public interface IChargeService {
	/**
	 * 返回与案件ID关联的收费信息
	 * @param complainId 案件ID
	 * @return 收费信息 List<ChargeInfo>
	 */
	public List<ChargeInfo> getChargeInfosByComplainId(int complainId);
}
