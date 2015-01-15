package com.creditease.eas.roster.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.roster.service.IRosterOrgAdminService;
import com.creditease.eas.util.BaseAction;
/**
 * 投资理财三部组织结构action层
 * @RosterOrgAdminAction.java
 * created at 2014-3-24 下午05:17:48 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings({ "unused", "serial" })
@Controller
@Scope("prototype")
public class RosterOrgAdminAction extends BaseAction{
	@Autowired
	private IRosterOrgAdminService rosterOrgAdminServiceImpl;
	private static final Logger logger = Logger
	.getLogger(RosterAction.class);
	
	public List<Map<String,Object>> rosterOrgAdminList;

	public List<Map<String, Object>> getRosterOrgAdminList() {
		return rosterOrgAdminList;
	}

	public void setRosterOrgAdminList(List<Map<String, Object>> rosterOrgAdminList) {
		this.rosterOrgAdminList = rosterOrgAdminList;
	}
	/**
	 * 
	 * 描述：查看投资理财三部组织结构树
	 * 2014-3-24 下午05:38:22 by zhangxin
	 * @version
	 * @return
	 */
	public String viewRosterOrgAdmin(){
		rosterOrgAdminList = rosterOrgAdminServiceImpl.selectAllOrgAdmin();
		return "view";
	}
	
}
