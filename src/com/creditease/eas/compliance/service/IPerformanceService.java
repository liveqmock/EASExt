package com.creditease.eas.compliance.service;

import java.util.List;

import com.creditease.eas.compliance.bean.Performance;

public interface IPerformanceService {
	List<Performance> FindPerformanceInfoByComplainId(Integer fcomplainid);

	void deletePerformanceInfoByComplainId(Integer fcomplainid);

	void insert(Performance performance);
}
