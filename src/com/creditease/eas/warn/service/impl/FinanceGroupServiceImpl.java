package com.creditease.eas.warn.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.bean.FinanceGroupBean;
import com.creditease.eas.warn.dao.FinanceGroupBeanMapper;
import com.creditease.eas.warn.service.FinanceGroupService;

@Service
public class FinanceGroupServiceImpl implements FinanceGroupService{
	
	@Autowired
	private FinanceGroupBeanMapper financeGroupBeanMapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	
		@Override
		public Pagination queryPage(Pagination page) {
			HttpServletRequest request= ServletActionContext.getRequest();
			int currentPage = PageUtil.strToPage(request.getParameter("page"));
			int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
			//查询总行数的方法
			int totalCounts = financeGroupBeanMapper.getTotalCounts();
			page = new Pagination(currentPage,pageSize,totalCounts); 
			//查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			List list = financeGroupBeanMapper.queryPage(map2);
			page.setRows(list);
			return page;
		}

		@Override
		public FinanceGroupBean selectByPrimaryKey(Integer id) {
			return financeGroupBeanMapper.selectByPrimaryKey(id);
		}

		@Override
		public void insert(FinanceGroupBean record) {
			financeGroupBeanMapper.insert(record);
		}

		@Override
		public void update(FinanceGroupBean record) {
			financeGroupBeanMapper.updateByPrimaryKey(record);
		}

		@Override
		public void delete(Integer id) {
			financeGroupBeanMapper.deleteByPrimaryKey(id);
		}


		@Override
		public boolean selectedIfGroupExists() throws Exception {
			HttpServletRequest request = ServletActionContext.getRequest();
			return extDataBaseMapper.findExists(Utils.setParams(
					"t_finance_group", "name", request.getParameter("columnValue"))) == 0 ? false : true;
		}
		
		
		
	} 


