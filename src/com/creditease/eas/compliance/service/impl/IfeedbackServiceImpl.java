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

import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.dao.IFeedbackMapper;
import com.creditease.eas.compliance.service.IFeedbackService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @IfeedbackServiceImpl.java	合规（初步调查——被投诉部门调查处理反馈service实现类）
 * created at 2013-10-8 下午02:56:01 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class IfeedbackServiceImpl implements IFeedbackService {
	/** 合规（初步调查——被投诉部门调查处理反馈DAO） **/
	@Autowired
	private IFeedbackMapper iFeedbackMapper;
	/** 合规（初步调查——被投诉部门调查处理反馈Entity）**/
	private IFeedback iFeedback;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(IfeedbackServiceImpl.class);

	public int insert(IFeedback iFeedback) throws Exception {
		return iFeedbackMapper.insert(iFeedback);
	}

	public int delete(Integer id) throws Exception {
		return iFeedbackMapper.deleteByPrimaryKey(id);
	}
	public int update(IFeedback iFeedback) throws Exception {
		return iFeedbackMapper.updateByPrimaryKey(iFeedback);//修改
	}
	public IFeedback getIFeedbackById(Integer id) throws Exception {
		iFeedback = iFeedbackMapper.selectByPrimaryKey(id);
		return iFeedback;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = iFeedbackMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = iFeedbackMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	public int getFeedbackRequiredId(Integer investigationId) throws Exception {
		return iFeedbackMapper.getFeedbackRequiredId(investigationId);
	}
	public int getFieldsurveyId(Integer investigationId) throws Exception {
		return iFeedbackMapper.getFieldsurveyId(investigationId);
	}
	public List<IFeedback> getRelationFeedBacks(Integer investigationId) throws Exception {
	return iFeedbackMapper.getRelationFeedBacks(investigationId);
	}
}
