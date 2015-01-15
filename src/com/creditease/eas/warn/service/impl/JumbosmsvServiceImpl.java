/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.dao.JumbosmsvMapper;
import com.creditease.eas.warn.service.JumbosmsvService;

/**
 * @JumbosmsvServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
//@Service("jumbosmsvServiceImpl")
@Service
public class JumbosmsvServiceImpl implements JumbosmsvService{
	@Autowired
	private JumbosmsvMapper jumbosmsvMapper;

	private Jumbosmsv jumbosmsv;
	
	
	public Jumbosmsv getJumbosmsv() {
		return jumbosmsv;
	}

	public void setJumbosmsv(Jumbosmsv jumbosmsv) {
		this.jumbosmsv = jumbosmsv;
	}

	@Override
	public int insert(Jumbosmsv jumbosmsv) throws Exception {
		// TODO Auto-generated method stub
		
//		Jumbosmsv jum = new Jumbosmsv();
//		jum.setName("demo1");
//		jum.setContent("今天天气很好。");
//		jumbosmsvMapper.insertJumbosmsv(jumbosmsv);
		jumbosmsvMapper.insert(jumbosmsv);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = jumbosmsvMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public int update(Jumbosmsv jumbosmsv) throws Exception {
		int i = jumbosmsvMapper.updateByPrimaryKey(jumbosmsv);
		return i;
	}
	
	public Jumbosmsv getJumbosmsvById(Integer id) throws Exception {
		jumbosmsv = jumbosmsvMapper.selectByPrimaryKey(id);
		return jumbosmsv;
	}
	
	public Jumbosmsv getJumbosmsvByIsuse(Integer isuse) {
		jumbosmsv = jumbosmsvMapper.selectByIsuse(isuse);
		return jumbosmsv;
	}
	@Override
//	public PageBean queryPage(PageBean page) {
//		// TODO Auto-generated method stub
//		int totalCounts = jumbosmsvMapper.getTotalCounts();
//		page = new PageBean(PageConst.PAGESIZE,page.getCurPage(),totalCounts);
//		Map map = PageUtil.getMap(page);
//		List<Jumbosmsv> list = jumbosmsvMapper.queryPage(map);
//		page.setResult(list);
//		return page;
//	}
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = jumbosmsvMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = jumbosmsvMapper.queryPage(map2);
		for (int i = 0; i < list.size(); i++) {
			jumbosmsv= (Jumbosmsv) list.get(i);
			System.err.println(jumbosmsv.getContent());
		}
		page.setRows(list);
		return page;
	}

	

//	public static void main(String[] args) throws Exception {
//		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//		JumbosmsvService service = (JumbosmsvService) app.getBean("jumbosmsvServiceImpl");
//		Jumbosmsv jumb = service.getJumbosmsvById(2);
//		String content = jumb.getContent();
//		content = content.replaceAll("jumb_name", "张三");
//		System.err.println("content: " + content);
//	}
}
