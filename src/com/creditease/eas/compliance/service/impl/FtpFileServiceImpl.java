package com.creditease.eas.compliance.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.FtpFile;
import com.creditease.eas.compliance.dao.FtpFileMapper;
import com.creditease.eas.compliance.service.FtpFileService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
@Service
public class FtpFileServiceImpl implements FtpFileService {
	
	@Autowired
	private FtpFileMapper  ftpFileMapper;
	/**
	 * 新增文件信息
	 */
	public void addFtpFile(FtpFile ftpFile) {
		int i =ftpFileMapper.addFtpFile(ftpFile);
	}
	/**
	 * 文件信息列表
	 */
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = HashMap.class.newInstance();
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("filename", request.getParameter("filename"));
		map.put("creatername", request.getParameter("creatername"));
		map.put("filetype", request.getParameter("filetype"));
		//查询总行数的方法;
		int totalCounts = ftpFileMapper.getTotalCounts(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = ftpFileMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
/**
 * 根据id查询文件信息
 */
  public FtpFile getFtpFileById(Integer fid) {
	return ftpFileMapper.getFtpFileById(fid);
 }
}
