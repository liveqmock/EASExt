package com.creditease.eas.compliance.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.compliance.bean.Log;
import com.creditease.eas.compliance.dao.LogMapper;
import com.creditease.eas.compliance.service.LogService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public void insertLog(Log log) {
		logMapper.insertLog(log);
	}

	@Override
	public Pagination queryPage(Pagination page,
			HttpServletRequest request) throws Exception {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		User user = (User) request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		/*map.put("fcompchannelid", request.getParameter("fcompchannelid"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("fcomplainant", request.getParameter("fcomplainant"));
		map.put("fcusname", request.getParameter("fcusname"));
		map.put("choocase", request.getParameter("choocase"));
		map.put("floanbread", request.getParameter("floanbread"));
		map.put("typename", request.getParameter("typename"));*/
		
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("fnumber", request.getParameter("fnumber"));
		map.put("fieldname", request.getParameter("fieldname"));
		map.put("opname", request.getParameter("opname"));
	
		// 查询总行数的方法
		int totalCounts = logMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List<Map> list = logMapper.queryPageByParamss(map2);
		page.setRows(list);
		return page;
	}
}
