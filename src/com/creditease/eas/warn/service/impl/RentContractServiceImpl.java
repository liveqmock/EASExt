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
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.RentContract;
import com.creditease.eas.warn.dao.RentContractMapper;
import com.creditease.eas.warn.service.RentContractService;

/**
 * @RentContractServiceImpl.java 房租合同信息service实现类
 * created at 2013-8-1 下午01:27:42 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class RentContractServiceImpl implements RentContractService{
	@Autowired
	private RentContractMapper rentContractMapper;
	@SuppressWarnings("deprecation")
	private RentContract rentContract;

	@SuppressWarnings("deprecation")
	@Override
	public int insert(RentContract rentContract) throws Exception {
		rentContractMapper.insert(rentContract);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = rentContractMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public int update(RentContract rentContract) throws Exception {
		int i = rentContractMapper.updateByPrimaryKey(rentContract);
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public RentContract getRentContractById(Integer id) throws Exception {
		rentContract = rentContractMapper.selectByPrimaryKey(id);
		return rentContract;
	}

	
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		
		User user = (User) request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		map.put("typeid",user.getTypeid());
		map.put("email",user.getEmail());
		
		map.put("orgname",request.getParameter("orgname"));
		map.put("startDate",request.getParameter("startDate"));
		map.put("paymentcycle",request.getParameter("paymentcycle"));
		map.put("lastcostcenter",request.getParameter("lastcostcenter"));
		map.put("endDate",request.getParameter("endDate"));
		//查询总行数的方法
		int totalCounts = rentContractMapper.getTotalCounts(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List list = rentContractMapper.queryPage(map2);
//		list = Utils.convertTimeOfList2String(list, "yyyy-MM-dd HH:mm:ss");
		page.setRows(list);
		return page;
	}

	public int selectRentByOfficeadd(String officeadd){
		return rentContractMapper.selectRentByOfficeadd(officeadd);
	}
}
