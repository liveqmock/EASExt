package com.creditease.eas.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.bean.UserRole;
import com.creditease.eas.admin.service.RoleService;
import com.creditease.eas.admin.service.UserAuthorityService;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.projectmanage.service.PmInfoService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.UserUtil;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAuthorityAction extends BaseAction{
	@Autowired
	private UserAuthorityService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private PmInfoService pmInfoService;
	private UserRole userRole;
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	private List<Role> rolelist;
	private User user;
	private String roleidlist;//添加页面复选框的值
	private List<Integer> userroleidList;//当前用户的角色集合
	private String userrolechoseid;//修改页面复选框的值
	//没有角色的用户集合
	List<User> noroleuserList;
	//用户集合
	List<User>  useractionlist;
	
	//用户id 和 角色集合
	/*Map<Long, String> userroelmap;
	public Map<Long, String> getUserroelmap() {
		return userroelmap;
	}
	public void setUserroelmap(Map<Long, String> userroelmap) {
		this.userroelmap = userroelmap;
	}*/
	
	
	public List<User> getUseractionlist() {
		return useractionlist;
	}
	public void setUseractionlist(List<User> useractionlist) {
		this.useractionlist = useractionlist;
	}
	public List<Role> getRolelist() {
		return rolelist;
	}
	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}
	public String getUserrolechoseid() {
		return userrolechoseid;
	}
	public void setUserrolechoseid(String userrolechoseid) {
		this.userrolechoseid = userrolechoseid;
	}
	public List<Integer> getUserroleidList() {
		return userroleidList;
	}
	public void setUserroleidList(List<Integer> userroleidList) {
		this.userroleidList = userroleidList;
	}
	public String getRoleidlist() {
		return roleidlist;
	}
	public void setRoleidlist(String roleidlist) {
		this.roleidlist = roleidlist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 
	 * 描述：添加用户
	 * 2013-1-16 下午03:16:56 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void addUser() throws Exception{
		
		UserRole userRole = new UserRole();
		@SuppressWarnings("unused")
		int numuser = userService.insertUser(user);
		//将用户与角色添加到中间表
		int userid =user.getId().intValue();
		String roleidlist1 = roleidlist.replaceAll(" ", "");
		String[] rolechoseidlist = roleidlist1.split(",");
		for(int i=0;i<rolechoseidlist.length;i++){
			userRole.setUserid(userid);
			userRole.setRoleid(Integer.parseInt(rolechoseidlist[i]));
			userRole.setStutas(0);
			userRoleService.inserUserRole(userRole);
		}
//		pmInfoService.maintainPmInfo(user, rolechoseidlist);//包含PM角色则存入PM信息表
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		pw.print("<script type='text/javascript'>parent.test();</script>");
//		pw.flush();
//		pw.close();
	}
	
	/**
	 * 获得角色集合
	 * 描述：
	 * 2013-8-23 上午09:48:36 by Administrator
	 * @version
	 * @return
	 */
	public String adduserjsp(){
		rolelist = roleService.seleroleidnameByUserid(UserUtil.getUser().getId());
		//当前用户的角色集合
		userroleidList = userRoleService.userroleidlist(UserUtil.getUser().getId().intValue());
		//如果是职能与普惠金融行政团队高级行政经理：则可以更新采购组人员信息，这个也需要加一下
		//查询采购组负责人的角色
		Role caigouAll = roleService.selectRoleByName("行政采购合同预警采购组人员");	
		//查询部门总接口人的角色
		Role caigouGroup = roleService.selectRoleByName("行政采购总接口人");	

		//如果是行政采购合同预警采购组人员,则可以给总接口人授权
		if(null != userroleidList && !userroleidList.isEmpty()){
			for(int i=0;i<userroleidList.size();i++){
				Integer in = userroleidList.get(i);
				if(in == 718){
					rolelist.add(caigouAll);
//					rolelist.add(caigouGroup);
					//添加的时候不能加总结口人的信息
				}else if(in == 719){
					rolelist.add(caigouGroup);
				}
			}
		}
		return "adduser";
	}
	
	
	/**
	 * 新增加了seleroleidnameByUserid方法
	* @Title: edit
	*created at 2014-6-8 下午06:50:08 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String edit() throws Exception{
		user = userService.getUserById(user.getId());
		//角色集合
		rolelist = roleService.seleroleidnameByUserid(UserUtil.getUser().getId());
		//当前用户的角色集合
		userroleidList = userRoleService.userroleidlist(UserUtil.getUser().getId().intValue());

		//如果是职能与普惠金融行政团队高级行政经理：则可以更新采购组人员信息，这个也需要加一下
		//如果是行政采购合同预警采购组人员,则可以给总接口人授权
		Role caigouAll = roleService.selectRoleByName("行政采购合同预警采购组人员");	
		//查询部门总接口人的角色
		Role caigouGroup = roleService.selectRoleByName("行政采购总接口人");	

		//如果是行政采购合同预警采购组人员,则可以给总接口人授权
		if(null != userroleidList && !userroleidList.isEmpty()){
			for(int i=0;i<userroleidList.size();i++){
				Integer in = userroleidList.get(i);
				if(in == 718){
					rolelist.add(caigouAll);
					rolelist.add(caigouGroup);
				}else if(in == 719){
					rolelist.add(caigouGroup);
				}
			}
		}
		return "edit";
	}
	/**
	 * 编辑
	 * 描述：
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String editself() throws Exception{
		return "editself";
	}
	/**修改
	 * x
	 * 描述：
	 * 2012-12-27 下午06:15:59 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void update() throws Exception{
		userService.update(user);
		Integer userid = user.getId().intValue();
		
		userRoleService.deleteByPrimaryKey(userid);
		String userrolechoseid1 = userrolechoseid.replaceAll(" ","");
		String[] userrolechoseid2 = userrolechoseid1.split(",");
		UserRole userRole = new UserRole();
		for(int i=0;i<userrolechoseid2.length;i++){
			userRole.setRoleid(Integer.parseInt(userrolechoseid2[i]));
			userRole.setStutas(0);
			userRole.setUserid(userid);
			userRoleService.inserUserRole(userRole);
		}
		pmInfoService.maintainPmInfo(user, userrolechoseid2);//包含PM角色则存入PM信息表
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String cmd = request.getParameter("cmd");
		if(cmd ==null){
			pw.print("<script type='text/javascript'>parent.test();</script>");
		}else{
			pw.print("<script type='text/javascript'>parent.test();</script>");
		}
		pw.flush();
		pw.close();*/
	}
	
	
	public void updateSelf() throws Exception{
		try {
			userService.update(user);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	/**
	 * 
	 * 描述：返回Json格式的List
	 * 2012-12-30 下午08:20:21 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson() throws Exception {
		this.pagination = userService.queryPage(pagination);
		useractionlist = pagination.getRows();//用户集合
		return "queryPageJson";
	}
	
	/**
	 * 
	 * 描述：判断用户是否已经存在
	 * 2013-12-3 下午02:34:29 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void ifUserHasExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = userService.selectedIfUserExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
	/**
	 * 删除用户 将用户的状态值改为1
	 * 描述：
	 * 2013-8-23 下午01:38:11 by Administrator
	 * @version
	 */
	public void updatestutas(){
		Integer id = user.getId().intValue();
		userService.updateStatus(id);
		userRoleService.updateStutas(id);
		pmInfoService.deletePmInfo(id);//删除用户同时删除pm信息
	}
}
