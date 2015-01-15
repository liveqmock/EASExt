package com.creditease.eas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 现在 我们建立一个 分页类，这个类具有一些 属性，这些属性其中，
 * currentPage pageSize  是用户发送的数据，其他都是我们通过和数据库交互产生的
 * @author Administrator
 *
 */
public class Pagination {
	/**
	 * 用户发送的数据
	 */
	private int currentPage;   // 当前页
	private int pageSize;      // 每页要求最多显示的记录
	private int total;       // 查询 数据库 得到的所有 记录总数
	private List rows;       //  查询 得到 本页的数据，返回一个 装有po对象的 List
	
	private int pageCount;  // 总页数
	private int startPageIndex;//开始索引:查询list时用
	private int endPageIndex;//结束索引：查询list时用
	/**
	 * 无参构造方法 
	 */
	public Pagination() {
	}
	/****
	 * 2014年7月5日
	 * 构造函数
	 * @param currentPage
	 * @param pageSize
	 * @param total
	 */
	public Pagination(int currentPage, int pageSize, int total) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		pageCount = (total+pageSize-1)/pageSize;    //总页数
		if(1 == pageCount) {	// 总页码不大于NicConfiguration.getPageSize()
			startPageIndex = 1 ;		// 页码列表的开始索引
			endPageIndex = total ;	// 页码列表的结束索引
		} else if(1 < pageCount) {	// 总页码大于NicConfiguration.getPageSize() 
			startPageIndex = (currentPage - 1)*pageSize + 1;
			endPageIndex = startPageIndex + pageSize - 1 ;
			if(endPageIndex >= total ){	// 前面的页码不足4个   
				startPageIndex = total ; 
			} 
		}
	}
	/**
	 * geters and setters
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getStartPageIndex() {
		return startPageIndex;
	}
	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}