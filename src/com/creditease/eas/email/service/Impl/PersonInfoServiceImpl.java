package com.creditease.eas.email.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.email.kingdee.query.PersonInfoQuery;
import com.creditease.eas.email.service.IPersonInfoService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * 
 * @PersonInfoServiceImpl.java
 * created at 2013-12-16 下午04:15:46 by zhangxin
 * 新员工入职信息实现类
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class PersonInfoServiceImpl implements IPersonInfoService{
	PersonInfoQuery personInfoQuery = new PersonInfoQuery(); 
	@Autowired
	private UserRoleService userRoleService;
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//当前登录用户信息
		User user = (User) request.getSession().getAttribute("user");
		//用户id 
		Integer userid = user.getId().intValue();
		//用户的角色集合
		List<Integer> rolelist = userRoleService.userroleidlist(userid);
		Map mapInfo = new HashMap();
		if(rolelist.contains(302)){//302-服务台特殊人员
			mapInfo.put("personrole", "302");
		}
		
		mapInfo.put("fname", request.getParameter("fname"));
		mapInfo.put("fnumber", request.getParameter("fnumber"));
		mapInfo.put("creattime", request.getParameter("creattime"));
		mapInfo.put("endtime",request.getParameter("endtime") );
		mapInfo.put("datatype", request.getParameter("datatype"));//0-系统生成 1-手动维护 " "-全部
		//查询总行数的方法
		int totalCounts = personInfoQuery.totalPerson(mapInfo);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = personInfoQuery.queryPersonInfo(mapPageInfo);
		page.setRows(list);
		return page;
	}
	
	/**
	 * 导出数据查询方法
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<Map> expQuery() {
		HttpServletRequest request= ServletActionContext.getRequest();
		//当前登录用户信息
		User user = (User) request.getSession().getAttribute("user");
		//用户id 
		Integer userid = user.getId().intValue();
		//用户的角色集合
		List<Integer> rolelist = userRoleService.userroleidlist(userid);
		Map mapInfo = new HashMap();
		if(rolelist.contains(302)){//302-服务台特殊人员
			mapInfo.put("personrole", "302");
		}
		mapInfo.put("fname", request.getParameter("fname"));
		mapInfo.put("fnumber", request.getParameter("fnumber"));
		mapInfo.put("creattime", request.getParameter("creattime"));
		mapInfo.put("endtime", request.getParameter("endtime"));
		mapInfo.put("datatype", request.getParameter("datatype"));//0-系统生成 1-手动维护 " "-全部
		List<Map>  list = personInfoQuery.expQuery(mapInfo);
		return list;
	}
	/**
	 * 根据人员编码查询人员信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map selectPerson(String fnumber) {
		Map map = personInfoQuery.selectPerson(fnumber);
		return map;
	}
	
	

}
