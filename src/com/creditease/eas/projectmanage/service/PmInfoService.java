package com.creditease.eas.projectmanage.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.util.Pagination;

public interface PmInfoService {

	public Pagination queryPage(Pagination page);
	
	public PmInfo getPmInfoById(final int id);
	
	public void updatePmEmail(PmInfo pmInfo);
	
	public Pagination queryPmChangeList(Pagination page);
	
	public void maintainPmInfo(User user,String[] roleList);
	
	public void deletePmInfo(Integer userId);
	
	public Pagination queryEmailHistoryList(Pagination page);
   
}
