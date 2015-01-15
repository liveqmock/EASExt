package com.creditease.eas.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.dao.EmailConfigMapper;
import com.creditease.eas.admin.service.EmailConfigService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;

@Service
public class EmailConfigServiceImpl implements EmailConfigService {

	@Autowired
	private EmailConfigMapper emailConfigMapper;

	public void setEmailConfigMapper(EmailConfigMapper emailConfigMapper) {
		this.emailConfigMapper = emailConfigMapper;
	}

	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		int totalCounts = emailConfigMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = emailConfigMapper.queryPage(map2);
		page.setRows(list);

		return page;
	}

	@Override
	public EmailConfig getEmailConfigByFid(Integer fid) {
		return emailConfigMapper.getEmailConfigByFid(fid);
	}

	@Override
	public int update(EmailConfig emailConfig) {
		return emailConfigMapper.update(emailConfig);
	}

	@Override
	public List<Map> test() {
		return emailConfigMapper.test();
	}

}
