package com.creditease.eas.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.admin.dao.UserAuthorityMapper;
import com.creditease.eas.admin.service.UserAuthorityService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.Utils;
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService{
	@Autowired
	private UserAuthorityMapper userMapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	
	@Override
	public int insertUser(User user) throws Exception{
		user.setStutas(0);
		//获取系统的用户信息
		User sysUser = UserUtil.getUser();
		
		user.setParentid(sysUser.getId());//创建人及用户的父id
		user.setFcreator(sysUser.getId());//创建人
		user.setCreatedate(new Date());//创建时间
		user.setFlastupdator(sysUser.getId());//最后修改人
		user.setEditdate(new Date());//最后修改时间
		return userMapper.insertUser(user);
	}
	/**
	 * 
	 * 描述：设置公共的参数
	 * 2012-12-31 下午06:08:52 by ygq
	 * @version
	 * @param request
	 * @param map
	 * @return
	 */
	private Map setMapValue(HttpServletRequest request,Map map){
		String username = StringUtil.addLikeOperBoth(request.getParameter("username"));
		map.put("username", username);
		//获得用户的id，根据用户的id，去控制用户的数据权限
		map.put("userid", UserUtil.getUser().getId());
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		map = setMapValue(request, map);
		
		int totalCounts = userMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2 = setMapValue(request,map2);
		List list = userMapper.queryPageByParams(map2);
		page.setRows(list);
		return page;
	}
	
	
	
	
	@Override
	public User getUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public void update(User user) throws Exception{
		try{
		 user.setStutas(0);
		 //获取系统的用户信息
		 User sysUser = UserUtil.getUser();
		 user.setFlastupdator(sysUser.getId());//最后修改人
		 user.setEditdate(new Date());//最后修改时间
		 userMapper.updateByPrimaryKeySelective(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public void updateStatus(Integer id) {
		userMapper.updateStatus(id);
		
	}
	@Override
	public int selectedIfUserExists1(String username) {
		return userMapper.selectedIfUserExists(username);
	}
	@Override
	public List<User> seleUserList() {
		
		return userMapper.seleUserList();
	}
	@Override
	public boolean selectedIfUserExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findUserExists(Utils.setParams(
				"t_user", "username", request.getParameter("columnValue"))) == 0 ? false : true;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.UserService#selectedUserAndRoleInfoByUserName(java.lang.String)
	 */
	@Override
	public List<Map<String,Object>> selectedUserAndRoleInfoByUserName(String username) {
		return userMapper.selectedUserAndRoleInfoByUserName(username);
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.UserService#selectOneUserByUsername(java.lang.String)
	 */
	@Override
	public User selectOneUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectOneUserByUsername(username);
	}
}
