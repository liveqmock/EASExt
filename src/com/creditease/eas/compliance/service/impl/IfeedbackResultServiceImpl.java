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

import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.compliance.dao.IFeedbackResultMapper;
import com.creditease.eas.compliance.service.IFeedbackResultService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @IfeedbackResultServiceImpl.java	合规（初步调查——被投诉部门调查处理反馈结果service实现类）
 * created at 2013-10-8 下午04:41:24 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class IfeedbackResultServiceImpl implements IFeedbackResultService {
	/** 合规（初步调查——被投诉部门调查处理反馈结果DAO）**/
	@Autowired
	private IFeedbackResultMapper iFeedbackResultMapper;
	/** 合规（初步调查——被投诉部门调查处理反馈结果Entity）**/
	private IFeedbackResult iFeedbackResult;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(IfeedbackResultServiceImpl.class);

	public int insert(IFeedbackResult iFeedbackResult) throws Exception {
		return iFeedbackResultMapper.insert(iFeedbackResult);
	}

	public int delete(Integer id) throws Exception {
		return iFeedbackResultMapper.deleteByPrimaryKey(id);
	}
	public int update(IFeedbackResult iFeedbackResult) throws Exception {
		return iFeedbackResultMapper.updateByPrimaryKey(iFeedbackResult);//修改
	}
	public IFeedbackResult getIFeedbackResultById(Integer id) throws Exception {
		iFeedbackResult = iFeedbackResultMapper.selectByPrimaryKey(id);
		return iFeedbackResult;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = iFeedbackResultMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = iFeedbackResultMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	public int getFeedBackId(Integer investigationId) throws Exception {
		return iFeedbackResultMapper.getFeedBackId(investigationId);
	}

	@SuppressWarnings("unchecked")
	public List<Map> getFeedBackTypes() throws Exception {
		return iFeedbackResultMapper.getFeedBackTypes();
	}

	@SuppressWarnings("unchecked")
	public List<Map> getNextSchemes() throws Exception {
		return iFeedbackResultMapper.getNextSchemes();
	}
	public IFeedbackRequired getFeedbackRequiredById(int feedbackResultId)
			throws Exception {
		return iFeedbackResultMapper.getFeedbackRequiredById(feedbackResultId);
	}
}
