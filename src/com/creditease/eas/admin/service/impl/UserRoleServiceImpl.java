package com.creditease.eas.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.UserRole;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.admin.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
    private UserRoleMapper userRoleMapper;
	 
	@Override
	public void inserUserRole(UserRole userrole) {
		userRoleMapper.insertUserRole(userrole);
		
	}

	@Override
	public void updateStutas(Integer id) {
		userRoleMapper.updateStatus(id);
		
	}

	@Override
	public List<Integer> userroleidlist(Integer id) {
		return userRoleMapper.userroleidlist(id);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		userRoleMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Integer> useridset() {
		return userRoleMapper.useridset();
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.UserRoleService#deleteUserRoleByUseridAndRoleId(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public int deleteUserRoleByUseridAndRoleId(Long userid, Integer roleid) {
		Map map = new HashMap();
		map.put("userid", userid);
		map.put("roleid",roleid);
		return userRoleMapper.deleteUserRoleByUseridAndRoleId(map);
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.UserRoleService#findUserRoleByUserIdAndRole(java.util.Map)
	 */
	@Override
	public UserRole findUserRoleByUserIdAndRole(Long userid, Integer roleid) {
		Map map = new HashMap();
		map.put("userid", userid);
		map.put("roleid",roleid);
		return userRoleMapper.findUserRoleByUserIdAndRole(map);
	}

}
