package com.creditease.eas.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.AdminAuthority;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.admin.dao.UserAuthorityMapper;
import com.creditease.eas.admin.dao.UserMapper;
import com.creditease.eas.admin.service.IAdminAuthorityService;
import com.creditease.eas.admin.service.UserAuthorityService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.Utils;
@Service
public class AdminAuthorityServiceImpl implements IAdminAuthorityService{

	/* (non-Javadoc)
	 * @see com.creditease.eas.admin.service.IAdminAuthorityService#insert(com.creditease.eas.admin.bean.AdminAuthority)
	 */
	@Override
	public int insert(AdminAuthority authority) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
