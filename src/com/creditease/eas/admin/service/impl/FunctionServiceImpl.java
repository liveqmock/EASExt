package com.creditease.eas.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.admin.dao.FunctionMapper;
import com.creditease.eas.admin.service.FunctionService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;

@Service
public class FunctionServiceImpl implements FunctionService{

	@Autowired
	private FunctionMapper functionMapper;
	private Function function;
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	
	@Override
	public List<Function> selectAllFunction() {
		List<Function> functionList = functionMapper.functionList();
		return functionList;
	}

	@Override
	public void updateStutas(Integer id) {
		
         functionMapper.updateStatus(id);		
	}

	@Override
	public void updateFunction(Function record) {

		functionMapper.updateByPrimaryKey(record);
		
	}

	@Override
	public void insertFunction(Function record) {
		/**
		 * 取到code最大值，插入时系统自动插入code*2的值
		 *//*
		Integer code = functionMapper.maxCode();
		if(code == null){
			code = 1;
		}else{
			code = code*2;
		}
		record.setCode(code);*/
		record.setStutas(0);
		functionMapper.insert(record);
		
	}

	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = functionMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = functionMapper.queryPage(map2);
		
		page.setRows(list);
		return page;
	}

	@Override
	public Function selectFunctionByKey(Integer id) {
		
		return functionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int selectedIfFunctionExists(String funname) {
		return functionMapper.selectedIfFunctionExists(funname);
	}

	@Override
	public List<Function> selefuncidname() {
		return functionMapper.selefuncidname();
	}
	
	

}
