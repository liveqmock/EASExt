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

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.compliance.dao.ZcxzlsResultMapper;
import com.creditease.eas.compliance.service.ZcxzlsResultService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @ZcxzlsfbresultServiceImpl.java	合规（初步调查——再次协助落实反馈结果service实现类）
 * created at 2013-10-8 下午05:11:32 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class ZcxzlsResultServiceImpl implements ZcxzlsResultService {
	/** 合规（初步调查——再次协助落实反馈结果DAO） **/
	@Autowired
	private ZcxzlsResultMapper zcxzlsfbResultMapper;
	/** 合规（初步调查——再次协助落实反馈结果Entity）**/
	private ZcxzlsResult zcxzlsfbResult;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(ZcxzlsResultServiceImpl.class);

	public int insert(ZcxzlsResult zcxzlsfbResult) throws Exception {
		return zcxzlsfbResultMapper.insert(zcxzlsfbResult);
	}

	public int delete(Integer id) throws Exception {
		return zcxzlsfbResultMapper.deleteByPrimaryKey(id);
	}
	public int update(ZcxzlsResult zcxzlsfbResult) throws Exception {
		return zcxzlsfbResultMapper.updateByPrimaryKey(zcxzlsfbResult);//修改
	}
	public ZcxzlsResult getZcxzlsfbResultById(Integer id) throws Exception {
		zcxzlsfbResult = zcxzlsfbResultMapper.selectByPrimaryKey(id);
		return zcxzlsfbResult;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = zcxzlsfbResultMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = zcxzlsfbResultMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	public List<IFeedback> getRelationFeedBacks(Integer investigationId)
			throws Exception {
		return zcxzlsfbResultMapper.getRelationFeedBacks(investigationId);
	}
	public Fieldsurvey getFieldsurvey(Integer investigationId) throws Exception {
		return zcxzlsfbResultMapper.getFieldsurvey(investigationId);
	}
}
