package com.creditease.eas.compliance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.Performance;
import com.creditease.eas.compliance.dao.PerformanceMapper;
import com.creditease.eas.compliance.service.IPerformanceService;


@Service
public class PerformanceServiceImpl implements IPerformanceService {
	@Autowired
	private PerformanceMapper performanceMapper;
	
	@Override
	public List<Performance> FindPerformanceInfoByComplainId(Integer fcomplainid) {
		return performanceMapper.selectPerformanceInfoByComplainId(fcomplainid);
	}

	@Override
	public void deletePerformanceInfoByComplainId(Integer fcomplainid) {
		performanceMapper.deletePerformanceInfoByComplainId(fcomplainid);
	}

	@Override
	public void insert(Performance performance) {
		performanceMapper.insert(performance);
	}
	
}
