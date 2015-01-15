package com.creditease.eas.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.admin.dao.RoleMapper;
import com.creditease.eas.admin.dao.UserAuthorityMapper;
import com.creditease.eas.admin.service.RoleService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.Utils;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper rolemapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	private Role role;
	@Autowired
	private UserAuthorityMapper	userAuthorityMapper;//引用的新创建的能够控制数据权限的映射
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public void inserRole(Role record) {
		try{
			record.setStutas(0);
			User user = UserUtil.getUser();
			record.setFcreator(user.getId());
			record.setFcreatedate(new Date());
			record.setFlastupdator(user.getId());
			record.setFlastupdatetime(new Date());
			rolemapper.insertRole(record);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	

	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = rolemapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = rolemapper.queryPage(map2);
//		for(int i=0;i<list.size();i++){
//		}
		page.setRows(list);
		return page;
	}

	@Override
	public void updateStatus(Integer id) {
		rolemapper.updateStatus(id);
		
	}

	@Override
	public List<Role> seleroleidname() {
		return rolemapper.seleroleidname();
	}

	@Override
	public Role selectByPrimaryKey(Integer roleid) {
		return rolemapper.selectByPrimaryKey(roleid);
	}

	@Override
	public void updateByPrimaryKey(Role role) {
		User user = UserUtil.getUser();
		role.setFlastupdator(user.getId());
		role.setFlastupdatetime(new Date());
		rolemapper.updateByPrimaryKey(role);
		
	}

	@Override
	public boolean selectedIfRoleExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findUserExists(Utils.setParams(
				"t_role", "rolename", request.getParameter("columnValue"))) == 0 ? false : true;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.RoleService#seleroleidnameByUserName(java.lang.String)
	 */
	@Override
	public List<Role> seleroleidnameByUserid(Long userid) {
		//查询人员的 下级的id
		List<User> users = userAuthorityMapper.selectedUserAndDownUser(userid);
		List list = new ArrayList();
		for(int i=0;i<users.size();i++){
			list.add(users.get(i).getId());
		}
		return rolemapper.seleroleidnameByUserids(list);
	}
	@Override
	public Role selectRoleByName(String rolename){
		return rolemapper.selectRoleByName(rolename);
	}
}
