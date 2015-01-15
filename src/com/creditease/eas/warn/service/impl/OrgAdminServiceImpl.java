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

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.OrgAdmin;
import com.creditease.eas.warn.kingdee.dao.OrgAdminMapper;
import com.creditease.eas.warn.kingdee.query.OrgAdminMapperQuery;
import com.creditease.eas.warn.service.OrgAdminService;


@Service("orgAdminServiceImpl")
//@Service 
public class OrgAdminServiceImpl implements OrgAdminService{
//	@Autowired
//	private OrgAdminMapper oorgAdminMapper;

	private OrgAdmin orgadmin = new OrgAdmin();
	
	
	public OrgAdmin getOrgadmin() {
		return orgadmin;
	}


	public void setOrgadmin(OrgAdmin orgadmin) {
		this.orgadmin = orgadmin;
	}


	public Pagination queryPage(Pagination page) {
//		OrgAdminMapper oorgAdminMapper = OrgAdminMapperQuery.getOrgAdminMapper();
//		HttpServletRequest request= ServletActionContext.getRequest();
//		int currentPage = PageUtil.strToPage(request.getParameter("page"));
//		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		//查询总行数的方法
//		int totalCounts = oorgAdminMapper.getTotalCounts();
//		System.out.println("totalcount: "+totalCounts);
//		page = new Pagination(currentPage,pageSize,totalCounts); 
//		//查询数据集的方法
//		Map map2 = PageUtil.getMap(page);
//		List list = oorgAdminMapper.queryPage(map2);
//		page.setRows(list);
		return OrgAdminMapperQuery.queryPage(page);
	}
	
	public void test(){
		OrgAdminMapper oorgAdminMapper = OrgAdminMapperQuery.getOrgAdminMapper();
		int totalCounts = oorgAdminMapper.getTotalCounts();
		System.out.println("totalcount: "+totalCounts);
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrgAdminService service = (OrgAdminService) app.getBean("orgAdminServiceImpl");
		service.test();
	}
}
