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
import com.creditease.eas.warn.bean.OrgAdminCh;
import com.creditease.eas.warn.dao.OrgAdminChMapper;
import com.creditease.eas.warn.service.OrgAdminChService;

/**
 * 
 * @OrgAdminChServiceImpl.java
 * created at 2013-4-8 下午04:11:30 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service("orgAdminChService")
public class OrgAdminChServiceImpl implements OrgAdminChService{
	@Autowired
	private OrgAdminChMapper orgadminchMapper;

	private OrgAdminCh orgadminch;
	
	 

	public OrgAdminCh getOrgadminch() {
		return orgadminch;
	}



	public void setOrgadminch(OrgAdminCh orgadminch) {
		this.orgadminch = orgadminch;
	}



	@Override
	public int insert(OrgAdminCh orgadminch) throws Exception {
		// TODO Auto-generated method stub
		
//		Jumbosmsv jum = new Jumbosmsv();
//		jum.setName("demo1");
//		jum.setContent("今天天气很好。");
//		jumbosmsvMapper.insertJumbosmsv(jumbosmsv);
		orgadminchMapper.insertOrgAdminCh(orgadminch);
		return 1;
	}



	public List<String> allOrgAdmin(){
		return orgadminchMapper.allOrgAdmin();
	}
	/*
	 * (non-Javadoc)
	 * @see com.creditease.eas.warn.service.OrgAdminChService#deleteAll()
	 */
	public void deleteAll() {
		orgadminchMapper.deleteAllOrgAdminCh();
		
	}
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = orgadminchMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = orgadminchMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}
	
	public int queryCount(){
		return orgadminchMapper.getTotalCounts();
	}
	
	/**
	 * 获取综合管理和BP
	 */
	public List<Map<String, Object>> getHomologous(String fnumber){
		return orgadminchMapper.getHomologous(fnumber);
	}
	/**
	 * 
	 * 描述：获取个贷部门，邮件接收人
	 * 2013-4-22 下午07:03:42 by xjw
	 * @version
	 * @param fnumber
	 * @return
	 */
	public List<Map<String, Object>> getOrgByFnumber(String fnumber){
		return orgadminchMapper.getOrgByFnumber(fnumber);
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrgAdminChService service = (OrgAdminChService) app.getBean("orgAdminChService");
//		OrgAdminCh orgadminch = new OrgAdminCh();
//		orgadminch.setName("测试2");
//		orgadminch.setFnumber("002");
//		int temp = service.insert(orgadminch );
//		System.out.println(temp);
		
//		String number = "海外资产管理部,abc,大幅度,我帮,ss";
//		String name = "1,abc,2,我,4";
//		OrgAdminCh orgadminch = new OrgAdminCh();
//		
//		
//		String[] numbers = number.split(",");
//		String[] names = name.split(",");
//		service.deleteAll();
//		for (int i = 0; i < numbers.length; i++) {
//			orgadminch.setFnumber(numbers[i]);
//			orgadminch.setName(names[i]);
//			service.insert(orgadminch);
//		} 
		
//		List<String> list = service.allOrgAdmin();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("部门数: "+list.size());
//			String fnumber = list.get(i).toString();
//			System.out.println("fnumber: "+fnumber);
//		}
		
//		 List<Map<String,Object>> yearlist = YearOfWorkQuery3.getYearPerson(list);
//		 System.out.println("总数： "+yearlist.size());
//		List<Map<String, Object>> list = service.getHomologous("07QFGLF");
//		if(list!=null && list.size()>0){
//			for (Map<String, Object> obj : list) {
//				System.out.println(obj.get("PNAME").toString());
//			}
//		}
		
		List<Map<String, Object>> tmplist = service.getOrgByFnumber("06GDXSF");
	}
 

	
	
}
