package com.creditease.eas.compliance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.compliance.dao.InvestigationMapper;
import com.creditease.eas.compliance.service.InvestFileService;
@Service("InvestFileService")
public class InvestFileServiceImpl implements InvestFileService{
	/** 合规初步调查DAO **/
	@Autowired
	private InvestigationMapper investigationMapper;
	/**
	 * 保存上传文件的信息
	 */
	@Override
	public void addInvestFile(InvestFile investfile) {
		int i=investigationMapper.addInvestFile(investfile);
	}
}
