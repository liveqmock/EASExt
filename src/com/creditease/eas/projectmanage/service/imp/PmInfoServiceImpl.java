package com.creditease.eas.projectmanage.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.projectmanage.dao.IEmailHistoryMapper;
import com.creditease.eas.projectmanage.dao.PmChangeMapper;
import com.creditease.eas.projectmanage.dao.PmInfoMapper;
import com.creditease.eas.projectmanage.service.PmInfoService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

@Service
public class PmInfoServiceImpl implements PmInfoService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PmInfoMapper IPmInfoMapper;
	@Autowired
	private PmChangeMapper IPmChangeMapper;
	@Autowired
	private IEmailHistoryMapper IEmailHistoryMapper;
	
	//TODO 确认角色ID
	/**
	 * PM角色ID
	 */
	private final String ROLE_PM = "569"; 
	
	/**
	 * 项目信息管理员角色ID
	 */
	private final String ROLE_PROJECT_ADMIN = "567";
	
	/**
	 * 查询pm信息列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", StringUtil.addLikeOperBoth(request.getParameter("userName")));
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		int totalCounts = IPmInfoMapper.getTotalCountsByParams(map);//查询总行数
		
		page = new Pagination(currentPage, pageSize, totalCounts);
		Map<String,Object> map2 = PageUtil.getMap(page);
		map2.putAll(map);//复制map中的查询条件
		page.setRows(IPmInfoMapper.queryPageByParams(map2));//查询数据集
		return page;
	}
	
	/**
	 * 描述：根据id获取PM信息
	 * @param id
	 * @return
	 */
	@Override
	public PmInfo getPmInfoById(final int id){
		return IPmInfoMapper.getPMInfoById(id);
	}
	
	/**
	 * 绑定邮箱
	 */
	@Override
	public void updatePmEmail(PmInfo pmInfo){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		pmInfo.setLastupdaterId(user.getId().intValue());
		IPmInfoMapper.updatePmEmail(pmInfo);
	}
	
	/**
	 * 查询pm变更记录
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pagination queryPmChangeList(Pagination page){
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map = new HashMap<String,Object>();
		String userName = StringUtil.addLikeOperBoth(request.getParameter("userName"));// 添加
		String loanNo = StringUtil.addLikeOperBoth(request.getParameter("loanNo"));// 添加
		map.put("userName", userName);
		map.put("loanNo", loanNo);
		
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		int totalCounts = IPmChangeMapper.getPmChangeTotalCounts(map);// 查询总行数
		
		page = new Pagination(currentPage, pageSize, totalCounts);
		Map<String,Object> map2 = PageUtil.getMap(page);
		map2.putAll(map);//复制map中的查询条件
		page.setRows(IPmChangeMapper.queryAllPmChangeList(map2));// 查询数据集
		return page;
	}
	
	/**
	 * 维护PM信息表
	 * 1、新增用户，有PM角色，新增PM信息
	 * 2、维护用户，PM角色变更，新增或删除PM信息
	 */
	@Override
	public void maintainPmInfo(User user,String[] roleList){
		boolean isExistRole = false;//是否存在PM角色
		if(null != roleList && roleList.length > 0){
			for(String role : roleList){
				if((ROLE_PM.equals(role) || ROLE_PROJECT_ADMIN.equals(role)) && !isExistRole){
					isExistRole = true;
				}
			}
		}
		PmInfo pmInfo = IPmInfoMapper.queryPmInfoByUserId(user.getId().intValue());
		if(isExistRole){//用户包含PM角色
			if(null == pmInfo){//pm信息表中无记录则新增 
				pmInfo = new PmInfo();
				User loginUser = (User)ActionContext.getContext().getSession().get("user");
				pmInfo.setUserId(user.getId().intValue());
				pmInfo.setPmName(user.getUsername());
				pmInfo.setPmEmail("");
				pmInfo.setfCreateUserId(loginUser.getId().intValue());
				IPmInfoMapper.insertPmInfo(pmInfo);
			}
		}else{//用户不包含PM角色
			if(null != pmInfo){//存在pm记录则删除
				IPmInfoMapper.deletePmInfo(user.getId().intValue());
			}
		}
	}
	
	/**
	 * 删除用户，同时删除PM信息
	 * @param userId
	 */
	@Override
	public void deletePmInfo(Integer userId){
		PmInfo pmInfo = IPmInfoMapper.queryPmInfoByUserId(userId);
		if(null != pmInfo){
			IPmInfoMapper.deletePmInfo(userId);
		}
	}
	
	/**
	 * 查询预警发送记录
	 */
	@Override
	public Pagination queryEmailHistoryList(Pagination page){
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("warnType", StringUtil.objToString(request.getParameter("warnType")));//预警类型
		map.put("loanNo", StringUtil.addLikeOperBoth(request.getParameter("loanNo")));//贷款编号
		map.put("head", StringUtil.addLikeOperBoth(request.getParameter("head")));//PM
		map.put("loanTimeStart", StringUtil.objToString(request.getParameter("loanTimeStart")));//出借日起始
		map.put("loanTimeEnd", StringUtil.objToString(request.getParameter("loanTimeEnd")));//出借日结束
		map.put("expireTimeStart", StringUtil.objToString(request.getParameter("expireTimeStart")));//到期日起始
		map.put("expireTimeEnd", StringUtil.objToString(request.getParameter("expireTimeEnd")));//到期日结束
		map.put("interestTimeStart", StringUtil.objToString(request.getParameter("interestTimeStart")));//利息返还日起始
		map.put("interestTimeEnd", StringUtil.objToString(request.getParameter("interestTimeEnd")));//利息返还日结束
		map.put("sendState", StringUtil.objToString(request.getParameter("sendState")));//发送状态
		
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		int totalCounts = IEmailHistoryMapper.getEmailHistoryTotalCounts(map);// 查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map<String,Object> map2 = PageUtil.getMap(page);
		map2.putAll(map);//复制map中的查询条件
		page.setRows(IEmailHistoryMapper.queryAllEmailHistoryList(map2));
		return page;
	}

}
