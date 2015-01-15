/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageConst;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.Variables;
import com.creditease.eas.warn.dao.VariablesMapper;
import com.creditease.eas.warn.service.VariablesService;

/**
 * @VariablesServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service("variablesServiceImpl")
//@Service
public class VariablesServiceImpl implements VariablesService{
	@Autowired
	private VariablesMapper variablesMapper;

	private Variables variables;
	
	
	

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

	@Override
	public int insert(Variables variables) throws Exception {
//		System.err.println("id: "+variables.getId());
		variablesMapper.insertVariables(variables);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = variablesMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public int update(Variables Variables) throws Exception {
		int i = variablesMapper.updateByPrimaryKey(Variables);
		return i;
	}
	
	public Variables getVariablesById(Integer id) throws Exception {
		variables = variablesMapper.selectByPrimaryKey(id);
		return variables;
	}
	
	@Override
//	public PageBean queryPage(PageBean page) {
//		// TODO Auto-generated method stub
//		int totalCounts = variablesMapper.getTotalCounts();
//		page = new PageBean(PageConst.PAGESIZE,page.getCurPage(),totalCounts);
//		Map map = PageUtil.getMap(page);
//		List<Variables> list = variablesMapper.queryPage(map);
//		page.setResult(list);
//		return page;
//	}
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = variablesMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = variablesMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}
	
	public int totalCount(){
		return variablesMapper.getTotalCounts();
	}

	public void insertAll(){
		Variables v1 = new Variables();
		v1.setCodesname("name");
		v1.setVariname("姓名");
		variablesMapper.insertVariables(v1);
		
		Variables v2 = new Variables();
		v2.setCodesname("age");
		v2.setVariname("年龄");
		variablesMapper.insertVariables(v2);
	}
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		VariablesService service = (VariablesService) app.getBean("variablesServiceImpl");
		Variables vari = new Variables();
//		vari.setId(310);
		vari.setCodesname("sssss");
		vari.setVariname("xxxxxxxx");
		int i = service.insert(vari);
		System.err.println(": "+i);
//		System.err.println("totalCount: "+service.totalCount());
	}
}
