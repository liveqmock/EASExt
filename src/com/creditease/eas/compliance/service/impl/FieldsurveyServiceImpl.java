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
import com.creditease.eas.compliance.dao.FieldsurveyMapper;
import com.creditease.eas.compliance.service.FieldsurveyService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @FieldsurveyServiceImpl.java	合规（初步调查——实地调查情况service实现类）
 * created at 2013-10-8 下午02:23:19 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class FieldsurveyServiceImpl implements FieldsurveyService {
	/** 合规（初步调查——实地调查情况DAO） **/
	@Autowired
	private FieldsurveyMapper fieldsurveyMapper;
	/** 合规（初步调查——实地调查情况Entity）**/
	private Fieldsurvey fieldsurvey;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(FieldsurveyServiceImpl.class);

	public int insert(Fieldsurvey fieldsurvey) throws Exception {
		return fieldsurveyMapper.insert(fieldsurvey);
	}

	public int delete(Integer id) throws Exception {
		return fieldsurveyMapper.deleteByPrimaryKey(id);
	}
	public int update(Fieldsurvey fieldsurvey) throws Exception {
		return fieldsurveyMapper.updateByPrimaryKey(fieldsurvey);
	}
	public Fieldsurvey getFieldsurveyById(Integer id) throws Exception {
		fieldsurvey = fieldsurveyMapper.selectByPrimaryKey(id);
		return fieldsurvey;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = fieldsurveyMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = fieldsurveyMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	@SuppressWarnings("unchecked")
	public List<Map> getXzbmfbResults() throws Exception {
		return fieldsurveyMapper.getXzbmfbResults();
	}
	public Fieldsurvey getFieldsurvey(Integer investigationId) throws Exception {
		return fieldsurveyMapper.getFieldsurvey(investigationId);
	}
}
