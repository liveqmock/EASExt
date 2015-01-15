package com.creditease.eas.compliance.dao;

import java.util.List;

import com.creditease.eas.compliance.bean.Performance;

public interface PerformanceMapper {
	List<Performance> selectPerformanceInfoByComplainId(Integer fcomplainid);
	
	void deletePerformanceInfoByComplainId(Integer fcomplainid);
	
	void insert(Performance performance);
}
