/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.HashMap;
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
import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.dao.SendPortInfoMapper;
import com.creditease.eas.warn.service.SendPortInfoService;

@Service
public class SendPortInfoServiceImpl implements SendPortInfoService{
	@Autowired
	private SendPortInfoMapper sendPortInfoMapper;

	private SendPortInfo sendPortInfo;
	

	@Override
	public int insert(SendPortInfo sendPortInfo) throws Exception {
		// TODO Auto-generated method stub
		sendPortInfoMapper.insert(sendPortInfo);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = sendPortInfoMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public int update(SendPortInfo sendPortInfo) throws Exception {
		int i = sendPortInfoMapper.updateByPrimaryKey(sendPortInfo);
		return i;
	}
	
	public SendPortInfo getSendPortInfoById(Integer id) throws Exception {
		sendPortInfo = sendPortInfoMapper.selectByPrimaryKey(id);
		return sendPortInfo;
	}

	
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = HashMap.class.newInstance();
		map.put("orgname", request.getParameter("orgname"));
		map.put("city", request.getParameter("city"));
		map.put("officeadd", request.getParameter("officeadd"));
		map.put("lastcostcenter", request.getParameter("lastcostcenter"));
		map.put("portmail", request.getParameter("portmail"));
		//查询总行数的方法
		int totalCounts = sendPortInfoMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List list = sendPortInfoMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}


	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		SendPortInfoService service = (SendPortInfoService) app.getBean("sendPortInfoServiceImpl");
		SendPortInfo r = new SendPortInfo();
		r.setOrgname("cccc");
		r.setCity("xxxx");
		service.insert(r);
	}
}
