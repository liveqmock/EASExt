/**
 * 
 */
package com.creditease.eas.compliance.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.compliance.dao.AuditResultMapper;
import com.creditease.eas.compliance.service.AuditResultService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @AuditresultServiceImpl.java	合规（初步调查——审核结果service实现类）
 * created at 2013-10-8 上午11:47:45 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class AuditResultServiceImpl implements AuditResultService {
	/** 合规（初步调查——审核结果DAO） **/
	@Autowired
	private AuditResultMapper auditResultMapper;
	/** 合规（初步调查——审核结果Entity）**/
	private AuditResult auditResult;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(AuditResultServiceImpl.class);

	public int insert(AuditResult auditResult) throws Exception {
		return auditResultMapper.insert(auditResult);
	}

	public int delete(Integer id) throws Exception {
		return auditResultMapper.deleteByPrimaryKey(id);
	}
	public int update(AuditResult auditResult) throws Exception {
		return auditResultMapper.updateByPrimaryKey(auditResult);//修改
	}
	public AuditResult getAuditResultById(Integer id) throws Exception {
		auditResult = auditResultMapper.selectByPrimaryKey(id);
		return auditResult;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = auditResultMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = auditResultMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	public int getApplysettlementId(Integer investigationId) throws Exception {
		return auditResultMapper.getApplysettlementId(investigationId);
	}
}
