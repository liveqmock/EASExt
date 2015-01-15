package com.creditease.eas.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.admin.bean.RoleFunction;
import com.creditease.eas.admin.service.FunctionService;
import com.creditease.eas.admin.service.MenuService;
import com.creditease.eas.admin.service.RoleFunctionService;
import com.creditease.eas.admin.service.RoleService;
import com.creditease.eas.util.BaseAction;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleservice;
	@Autowired
	private RoleFunctionService rolefunctionservice;
	@Autowired
	private MenuService menuService;
	@Autowired
	private FunctionService functionService;

	public Role role;
	public RoleFunction rolefunction;
	public Function function;
	public Menu menu;
	List<Role> rolelist;
	static List rolemenuidlist;// 修改角色权限时菜单权限id
	static List rolefoncidlist;// 修改角色权限时功能权限id

	List<Menu> menulist; // 菜单集合
	List<Function> functionlist; // 功能集合

	private String menuidlist;// 添加角色时页面菜单复选框值
	private String functionidlist;// 添加角色时页面功能复选框值
	
	private List parentMenuIds;//所有父级菜单id集合
	private String parentMenuId;//父级菜单Id

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public List getParentMenuIds() {
		return parentMenuIds;
	}

	public void setParentMenuIds(List parentMenuIds) {
		this.parentMenuIds = parentMenuIds;
	}

	public List<Function> getFunctionlist() {
		return functionlist;
	}

	public void setFunctionlist(List<Function> functionlist) {
		this.functionlist = functionlist;
	}

	public List<Menu> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<Menu> menulist) {
		this.menulist = menulist;
	}

	public List getRolemenuidlist() {
		return rolemenuidlist;
	}

	public void setRolemenuidlist(List rolemenuidlist) {
		this.rolemenuidlist = rolemenuidlist;
	}

	public List getRolefoncidlist() {
		return rolefoncidlist;
	}

	public void setRolefoncidlist(List rolefoncidlist) {
		this.rolefoncidlist = rolefoncidlist;
	}

	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public String getMenuidlist() {
		return menuidlist;
	}

	public void setMenuidlist(String menuidlist) {
		this.menuidlist = menuidlist;
	}

	public String getFunctionidlist() {
		return functionidlist;
	}

	public void setFunctionidlist(String functionidlist) {
		this.functionidlist = functionidlist;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleFunction getRolefunction() {
		return rolefunction;
	}

	public void setRolefunction(RoleFunction rolefunction) {
		this.rolefunction = rolefunction;
	}

	/**
	 * 修改角色菜单权限 
	 * 描述： 2013-8-16 下午03:30:44 by Administrator
	 * 
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public void updateRoleMenuAction() {
		Integer roleid = rolefunction.getRoleId();
		String menuidlist1 = menuidlist.replaceAll(" ", "").replace("888888,", "");
		String[] menuchoseidlist = menuidlist1.split(",");

		// 删除rolemenuidlist里原来角色的权限 根据functionid 和 linktype 删除
		for(int j = 0;j<rolemenuidlist.size();j++){
			Map map = new HashMap();
			map.put("functionId", rolemenuidlist.get(j));
			map.put("linktype", 0);
			map.put("roleid", roleid);
			rolefunctionservice.deleteByPrimaryKey(map);
		}
		//在数据库中插入角色拥有的新的权限
		for (int i = 0; i < menuchoseidlist.length; i++) {
			rolefunction.setRoleId(roleid);
			rolefunction.setFunctionId(Integer.parseInt(menuchoseidlist[i]));
			rolefunction.setLinktype(0);
			rolefunction.setStutas(0);
			rolefunctionservice.insertRoleFunction(rolefunction);
		}
		this.closewindow();
	}

	/**
	 * 修改角色功能权限
	 * 
	 * 描述：
	 * 2013-8-21 上午11:09:09 by Administrator
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public void updateRoleFuncAction() {
		Integer roleid = rolefunction.getRoleId();
		//888888代表根节点
		String functionidlist1 = functionidlist.replaceAll(" ", "");
		String[] funcchoseidlist = functionidlist1.split(",");

		// 删除rolemenuidlist里原来角色的权限 根据functionid 和 linktype 删除
		for(int j = 0;j<rolefoncidlist.size();j++){
			Map map = new HashMap();
			map.put("functionId", rolefoncidlist.get(j));
			map.put("linktype", 1);
			map.put("roleid", roleid);
			rolefunctionservice.deleteByPrimaryKey(map);
		}
		//在数据库中插入角色拥有的新的权限
		for (int i = 0; i < funcchoseidlist.length; i++) {
			rolefunction.setRoleId(roleid);
			rolefunction.setFunctionId(Integer.parseInt(funcchoseidlist[i]));
			rolefunction.setLinktype(1);
			rolefunction.setStutas(0);
			rolefunctionservice.insertRoleFunction(rolefunction);
		}
		this.closewindow();
	
	}
	
	
	
	/**
	 * 查询菜单集合
	 *  描述： 2013-8-19 上午11:24:48 by Administrator
	 * 
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public void menuidnamelist() {
		menulist = menuService.seleMenu();
	}

	/**
	 * 查询功能集合
	 *  描述： 2013-8-19 下午06:52:44 by Administrator
	 * 
	 * @version
	 */
	public void funcidnamelist() {
		functionlist = functionService.selefuncidname();
	}

	/**
	 * 角色表中插入角色、角色功能中间表中插入角色功能信息
	 *  描述： 2013-8-15 上午10:49:16 by Administrator
	 * 
	 * @version
	 */
	public void insertRoleAction() {
		RoleFunction rolefunction = new RoleFunction();
		// 插入角色
		roleservice.inserRole(role);
		//888888代表虚拟根节点id
		String menuidlist1 = menuidlist.replaceAll(" ", "").replace("888888,", "");
		String[] menuchoseidlist = menuidlist1.split(",");
		for (int i = 0; i < menuchoseidlist.length; i++) {
			
			// 获得角色id
			rolefunction.setRoleId(role.getId());
			// 获得菜单id
			rolefunction.setFunctionId(Integer.parseInt(menuchoseidlist[i]));
			// 设置类型
			rolefunction.setLinktype(0);
			// 在中间表中插入数据
			rolefunctionservice.insertRoleFunction(rolefunction);
			}
		
		/*添加功能选项  后期做
		 * String functionidlist1 = functionidlist.replaceAll(" ", "");
		String[] functionchoseidlist = functionidlist1.split(",");
		for (int j = 0; j < functionchoseidlist.length; j++) {
				// 获得角色id
				rolefunction.setRoleId(role.getId());
				// 获得功能id
				rolefunction.setFunctionId(Integer.parseInt(functionchoseidlist[j]));
				// 设置类型
				rolefunction.setLinktype(1);
				// 在中间表中插入数据
				rolefunctionservice.insertRoleFunction(rolefunction);
			}*/
		
		this.closewindow();
		
	}
	/**
	 * 进入添加角色页面
	 * 描述：
	 * 2013-8-21 下午01:44:43 by Administrator
	 * @version
	 * @return
	 */
	public String addRoleAction(){
		this.menuidnamelist();
		this.funcidnamelist();
		
		//查询所有顶级菜单
		parentMenuIds = menuService.seleteAllParentMenuIds();
		return "addRoleAction";
	}
	
	
	/**
	 * 
	 * 描述：判断角色是否已经存在
	 * 2013-12-3 下午02:34:59 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void ifRoleHasExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = roleservice.selectedIfRoleExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	

	/**
	 * 查询所有角色 
	 * 描述： 2013-8-16 上午09:56:58 by Administrator
	 * 
	 * @version
	 * @return
	 */
	public String queryPageJson() {
		this.pagination = roleservice.queryPage(pagination);
		rolelist = pagination.getRows();
		return "queryPageJson";
	}

	public String queryPageJsonBack() throws Exception {
		return "queryPageJsonBack";
	}

	/**
	 * 查看角色拥有的菜单类权限
	 *  描述： 2013-8-16 下午02:00:27 by Administrator
	 * 
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selerolemenuid() {
		Integer roleid = rolefunction.getRoleId();
		Map map = new HashMap();
		map.put("linktype", 0);
		map.put("roleid", roleid);
		rolemenuidlist = rolefunctionservice.selerolefonctionid(map);
		// 查询菜单集合
		this.menuidnamelist();
		
		//查询所有顶级菜单
		parentMenuIds = menuService.seleteAllParentMenuIds();
		return "selerolemenuid";
	}

	/**
	 * 查看角色拥有的功能类权限 
	 * 描述： 2013-8-16 下午02:04:27 by Administrator
	 * 
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String selerolefuncid() {
		Integer roleid = rolefunction.getRoleId();
		Map map = new HashMap();
		map.put("linktype", 1);
		map.put("roleid", roleid);
		rolefoncidlist = rolefunctionservice.selerolefonctionid(map);
		// 查询功能集合
		this.funcidnamelist();
		return "selerolefuncid";
	}
	/**
	 * 根据角色id查找角色
	 * 描述：
	 * 2013-9-4 下午01:57:50 by Administrator
	 * @version
	 * @param roleid
	 * @return
	 */
	public String selectRolebyid(){
		int id = role.getId();
		role = roleservice.selectByPrimaryKey(id);
		return "editrolename";
	}
	/**
	 * 修改角色信息
	 * 描述：
	 * 2013-9-4 下午02:41:05 by Administrator
	 * @version
	 * @param role
	 */
	public void updateRoleName(){
		roleservice.updateByPrimaryKey(role);
		this.closewindow();
	}
	
	/**
	 * 删除角色
	 *  描述： 2013-8-19 下午07:09:01 by Administrator
	 * 
	 * @version
	 */
	public void updatestutas() {
		Integer roleid = rolefunction.getRoleId();
		roleservice.updateStatus(roleid);
		rolefunctionservice.updateStatus(roleid);

	}
	public  void closewindow(){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print("<script type='text/javascript'>parent.test();</script>");
			pw.flush();
			pw.close(); 
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到tree
	 * May 7, 2014 6:17:57 PM by hejw
	 */
	public void ajaxGetTree() {
		
//		List<Menu> tree = menuService.queryTreeByParent(parentMenuId);
		List<Map> tree = transToJson();
		try {
			Gson gson = new Gson();
//			String strJson = gson.toJson(tree);
			String json = gson.toJson(transToJson());
			writerJsonToClient(json);	
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 将对象转换为json格式,全部菜单用一棵树
	 * May 8, 2014 1:44:18 PM by hejw
	 * @param trees
	 * @param parentMenuId
	 * @return
	 */
	public List<Map> transToJson() {
		
		List<Map> list = new ArrayList<Map>();
		
		rolemenuidlist= new ArrayList();
		//查询角色拥有的菜单权限
		if(rolefunction != null) {
			Integer roleid = rolefunction.getRoleId();
			Map role = new HashMap();
			role.put("linktype", 0);
			role.put("roleid", roleid);
			rolemenuidlist = rolefunctionservice.selerolefonctionid(role);
		}
		
		Map root = new HashMap();
		root.put("id", "888888");
		root.put("name", "菜单权限根节点");
		root.put("open", true);
		root.put("pid", 0);
		list.add(root);
		
		//查询所有一级菜单id
		parentMenuIds = menuService.seleteAllParentMenuIds();
		
		for(Object parentMenuId:parentMenuIds) {
			
			//查询一级菜单下所有子菜单
			List<Menu> parentMenus = menuService.queryTreeByParent(((Integer)parentMenuId).toString());
			
			for(Menu childMenu:parentMenus) {
				Map map = new HashMap();
				map.put("id", childMenu.getId());
				map.put("name", childMenu.getName());
				map.put("open", true);
				//父级菜单
				if(childMenu.getId().intValue() == ((Integer)parentMenuId).intValue()) {
					map.put("pId", "888888");
				}
				else {
					map.put("pId", childMenu.getParentId());
				}
				//是否选中
				if(rolemenuidlist.contains(childMenu.getId())) {
					map.put("checked", true);
				}
				list.add(map);
			}
		}
		
		return list;
		
	}
	
	/**
	 * 将对象转换为json格式，每个父级菜单用一颗树
	 * May 8, 2014 1:44:18 PM by hejw
	 * @param trees
	 * @param parentMenuId
	 * @return
	 */
	public List<Map> transToJson(List<Menu> trees,String parentMenuId) {
		
		List<Map> list = new ArrayList<Map>();
//		rolemenuidlist = new ArrayList();
		//查询角色拥有的菜单权限
		if(rolefunction != null) {
			Integer roleid = rolefunction.getRoleId();
			Map role = new HashMap();
			role.put("linktype", 0);
			role.put("roleid", roleid);
			rolemenuidlist = rolefunctionservice.selerolefonctionid(role);
		}
		
		for(Menu menu:trees) {
			Map map = new HashMap();
			map.put("id", menu.getId());
			map.put("name", menu.getName());
			map.put("open", true);
			//父级菜单
			if((menu.getId().toString()).equals(parentMenuId)) {
				map.put("pId", 0);
			}
			else {
				map.put("pId", menu.getParentId());
			}
			//是否选中
			if(rolemenuidlist.contains(menu.getId())) {
				map.put("checked", true);
			}
			list.add(map);
		}
		
		return list;
		
	}
	
}
