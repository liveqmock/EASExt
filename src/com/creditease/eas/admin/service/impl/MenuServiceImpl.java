package com.creditease.eas.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.admin.dao.MenuMapper;
import com.creditease.eas.admin.service.MenuService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * @MenuServiceImpl.java菜单接口实现类
 * created at 2013-8-8 下午04:55:45 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public void insert(Menu menu) throws Exception{
		menuMapper.insert(menu);
	}
	
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = menuMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map = PageUtil.getMap(page);
		List list = menuMapper.queryPage(map);
		page.setRows(list);
		return page;
	}
	@Override
	public void update(Menu menu) {
		menuMapper.updateByPrimaryKey(menu);
	}
	@Override
	public void deleteById(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}
	@Override
	public Menu getMenuById(Integer id){
		Menu menu = menuMapper.selectByPrimaryKey(id);
		return menu;
	}

	@Override
	public List<Menu> findByLevel(Map map) {
		List<Menu> menus = menuMapper.findByLevel(map);
		return menus;
	}

	@Override
	public List<Menu> seleMenu() {
		return menuMapper.seleMenu();
	}

	@Override
	public int getMaxSubSequence(String parentId) {
		return menuMapper.getMaxSubSequence(parentId);
	}

	@Override
	public List seleteAllParentMenuIds() {
		return menuMapper.seleteAllParentMenuIds();
	}

	@Override
	public List<Menu> queryTreeByParent(String parentId) {
		return menuMapper.queryTreeByParent(parentId);
	}
}
