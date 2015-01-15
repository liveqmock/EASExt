package com.creditease.eas.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.RoleFunction;
import com.creditease.eas.admin.dao.RoleFunctionMapper;
import com.creditease.eas.admin.service.RoleFunctionService;
@Service
public class RoleFunctionServiceImpl implements RoleFunctionService{
	@Autowired
	private RoleFunctionMapper rolefunctionmapper;
	
	@Override
	public void insertRoleFunction(RoleFunction record) {
		record.setStutas(0);
		rolefunctionmapper.insert(record);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> selerolefonctionid(Map map) {
		return rolefunctionmapper.selerolefonctionid(map);
		
	}

	@Override
	public void updateStatus(Integer id) {
		rolefunctionmapper.updateStatus(id);
		
	}

	@Override
	public void deleteByPrimaryKey(Map map) {
		rolefunctionmapper.deleteByPrimaryKey(map);
	}

}
