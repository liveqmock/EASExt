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
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.admin.service.UserService;
import com.creditease.eas.projectmanage.service.PmInfoService;
import com.creditease.eas.util.BaseAction;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
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
		pmInfoService.maintainPmInfo(user, rolechoseidlist);//包含PM角色则存入PM信息表
		/*response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();*/
	}
	
	/**
	 * 获得角色集合
	 * 描述：
	 * 2013-8-23 上午09:48:36 by Administrator
	 * @version
	 * @return
	 */
	public String adduserjsp(){
		rolelist = roleService.seleroleidname();
		return "adduser";
	}
	
	
	/**
	 * 编辑
	 * 描述：
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		user = userService.getUserById(user.getId());
		//角色集合
		rolelist = roleService.seleroleidname();
		//当前用户的角色集合
		userroleidList = userRoleService.userroleidlist(user.getId().intValue());
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
		/*for(int i=0;i<useractionlist.size();i++){
			//用户id
			Long userid = useractionlist.get(i).getId();
			//根据用户id查找其对应角色id集合
			List<Integer> userroleidlist = userRoleService.userroleidlist(userid.intValue());
			String userrolename = "";
			//根据角色id查找角色名称
			for(int j=0;j<userroleidlist.size();j++){
				int roleid = userroleidlist.get(j);
				Role userdeRole = roleService.selectByPrimaryKey(roleid);
				String name = userdeRole.getRolename();
				userrolename = " "+name;
			}
			userroelmap.put(userid, userrolename);
		}*/
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
	/**
	 * 批量为没有角色的用户授予权限
	 * 描述：
	 * 2013-8-29 下午02:20:40 by Administrator
	 * @version
	 */
	public void manyUsersLicens(){
		//查询所有用户
		List<User> userList = userService.seleUserList();
		//查询用户角色中间表中用户id集合
		List<Integer> useridlist = userRoleService.useridset();
		//没有角色的用户集合
		noroleuserList = new ArrayList<User>();
		for(int i = 0;i<userList.size();i++){
				int userbiaoid = userList.get(i).getId().intValue();
				if(useridlist.contains(userbiaoid)){
					 continue;
				}else{
					noroleuserList.add(userList.get(i));
				}
			    
		 }
		if(noroleuserList.size()==0 || noroleuserList == null ){
			
			response.setContentType("text/html;charset=utf-8");
			 response.setHeader("Pragma", "No-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
			PrintWriter pw;
			try {
				 pw = response.getWriter();
				 String data = "没有要授权的用户！";
				 pw.print(data);
				 pw.flush();
				 pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
		
		//根据typeid用户类型的不同授予不同的角色 1--hr管理员  其他数字 只能看到房屋合同预警菜单 角色为 行政一般用户      0 hr一般用户  typeid为空时为hr一般用户
		for(int k = 0;k<noroleuserList.size();k++){
			if(noroleuserList.get(k).getTypeid() == null){
				UserRole userRole = new UserRole();
				userRole.setUserid(noroleuserList.get(k).getId().intValue());
				userRole.setRoleid(64);//hr一般用户
				userRole.setStutas(0);
				userRoleService.inserUserRole(userRole);
			}else if(noroleuserList.get(k).getTypeid()==Long.parseLong("1")){
				UserRole userRole = new UserRole();
				userRole.setUserid(noroleuserList.get(k).getId().intValue());
				userRole.setRoleid(63);//hr管理员 不能看到系统管理下功能列表菜单
				userRole.setStutas(0);
				userRoleService.inserUserRole(userRole);
			}else if(noroleuserList.get(k).getTypeid()==Long.parseLong("0")){
				UserRole userRole = new UserRole();
				userRole.setUserid(noroleuserList.get(k).getId().intValue());
				userRole.setRoleid(64); //hr一般用户  不能看到系统管理菜单
				userRole.setStutas(0);
				userRoleService.inserUserRole(userRole);
			}else{
				UserRole userRole = new UserRole();
				userRole.setUserid(noroleuserList.get(k).getId().intValue());
				userRole.setRoleid(65); //角色为：行政人员 只显示房屋合同预警菜单
				userRole.setStutas(0);
				userRoleService.inserUserRole(userRole);
			}
		}
		response.setContentType("text/html;charset=utf-8");
		 response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			String data = "授权成功";
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}
		
	}
	
	
//	
//	public String insert() throws Exception{
//		waringdetailServiceImpl.insert();
//		return "queryWarn";
//	}
//	/**
//	 * 
//	 * 描述：返回json格式的字符串
//	 * 2012-12-28 下午05:07:23 by ygq
//	 * @version
//	 * @return
//	 */
//	public void selectAllSendWays() throws Exception{
//		json = waringdetailServiceImpl.selectAllSendWays();
//		writerJsonToClient(json);
//	}
//	/**
//	 * 描述：查询所有的预警类型
//	 * 2012-12-29 下午03:59:38 by ygq
//	 * @version
//	 * @throws Exception
//	 */
//	public void selectAllWaringType() throws Exception{
//		json = waringdetailServiceImpl.selectAllWaringType();
//		writerJsonToClient(json);
//	}
//	/**
//	 * 查询明细信息
//	 * @return
//	 * @throws Exception
//	 */
//	public String queryDetail() throws Exception{
//			waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
//			//String filePath = request.getRealPath("/");
//		    MailSenderInfo mailInfo = new MailSenderInfo();
//		    String filePath = mailInfo.getProperties().getProperty("filepath");
//			filePath +=  waringDetail.getContentaddress();
//			//System.out.println("filePath:::" + filePath);
//			String htmlContent = FileReadUtil.getUserFile(filePath);
//			request.setAttribute("htmlContent", htmlContent);
//			return "detail";
//	}
	public void test(){
		System.out.println("ok");
	}
}
