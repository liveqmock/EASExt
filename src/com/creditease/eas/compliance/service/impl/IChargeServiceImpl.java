package com.creditease.eas.compliance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.ChargeInfo;
import com.creditease.eas.compliance.dao.ChargeInfoMapper;
import com.creditease.eas.compliance.service.IChargeService;

@Service
public class IChargeServiceImpl implements IChargeService {

	@Autowired
	private ChargeInfoMapper chargeInfoMapper;
	
	@Override
	public List<ChargeInfo> getChargeInfosByComplainId(int complainId) {
		return chargeInfoMapper.selectChargeInfoByComplainId(complainId);
	}

}
