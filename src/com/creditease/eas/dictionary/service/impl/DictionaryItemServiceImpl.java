package com.creditease.eas.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryItemService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * 数据字典子项实现类
 * @DictionaryItemServiceImpl.java
 * created at 2014-3-12 下午03:10:12 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("unused")
@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService{
	
	@Autowired
	private IDictionaryItemMapper dictionaryItemMapper;
	/**
	 * 根据id删除某条数据字典子项
	 */
	@Override
	public void deleteDictionaryItem(Integer id) {
		dictionaryItemMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据id查询某条数据字典子项
	 */
	@Override
	public DictionaryItem findOneDictionaryItem(Integer id) {
		return dictionaryItemMapper.selectByPrimaryKey(id);
	}
	/**
	 * 添加数据字典子项信息
	 */
	@Override
	public int insertDictionaryItem(DictionaryItem dictionaryItem) {
		return dictionaryItemMapper.insertItem(dictionaryItem);
	}
	/**
	 * 查询数据子项类型的具体子项信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map mapInfo = new HashMap();
		mapInfo.put("baseid", request.getParameter("baseid"));
		mapInfo.put("itemid", request.getParameter("itemid"));
		mapInfo.put("itemname", request.getParameter("itemname"));
		
		//查询总行数的方法
		int totalCounts = dictionaryItemMapper.getTotalCountsByParams(mapInfo);//查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = dictionaryItemMapper.queryPageByParams(mapPageInfo);
		page.setRows(list);
		return page;
	}
	/**
	 * 修改数据字典子项信息
	 */
	@Override
	public void updateDictionaryItem(DictionaryItem dictionaryItem) {
		dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
	}
	/**
	 * 判断数据类型关键字是否已经存在
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean itemidIfExists(Integer id) {
		HttpServletRequest request= ServletActionContext.getRequest();
		Map mapInfo = new HashMap();
		mapInfo.put("baseid", id);
		mapInfo.put("itemid", request.getParameter("columnValue"));
		return dictionaryItemMapper.itemidIfExists(mapInfo) == 0 ? false : true;
	}

	/**
	 * 判断子项数据值是否已经存在
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean itemnameIfExists(Integer id) {
		HttpServletRequest request= ServletActionContext.getRequest();
		Map mapInfo = new HashMap();
		mapInfo.put("baseid", id);
		mapInfo.put("itemname", request.getParameter("columnValue"));
		return dictionaryItemMapper.itemnameIfExists(mapInfo) == 0 ? false : true;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.dictionary.service.IDictionaryItemService#seleteItemData(java.lang.Integer)
	 */
	@Override
	public List<DictionaryItem> seleteItemData(Integer baseid) {
		// TODO Auto-generated method stub
		return dictionaryItemMapper.seleteItemData(baseid);
	}
}
