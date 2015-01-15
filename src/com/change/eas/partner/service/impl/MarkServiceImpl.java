package com.change.eas.partner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.change.eas.partner.bean.Mark;
import com.change.eas.partner.dao.MarkMapper;
import com.change.eas.partner.service.MarkService;
import com.change.eas.util.PageView;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
@Service("markService")
public class MarkServiceImpl implements MarkService {

	@Autowired
	private MarkMapper markMapper;
	@Override
	public List<Mark> listMark(Mark mark,PageView view) {
		Map<String, Object> map = mark.getMap();
		int tit = markMapper.getTitMark(mark);
		view.setAllRows(tit);
		map.put("start",view.getBeginRow());
		map.put("end", view.getEndRow());
		return markMapper.listMark(map);
	}
	public MarkMapper getMarkMapper() {
		return markMapper;
	}
	public void setMarkMapper(MarkMapper markMapper) {
		this.markMapper = markMapper;
	}
	@Override
	public long getNewMarkID() {
		return markMapper.getNewMarkID();
	}
	/**
	 * 有值返回True
	 */
	@Override
	public boolean checkMarkName(String name) {
		List<Mark> list = markMapper.findMarkByBookmarkname(name);
		return list==null||list.size()==0||list.get(0)==null;
	}
	@Override
	public void addMark(Mark mark) {
		markMapper.addMark(mark);
	}
	@Override
	public void delMark(long bookmarkid) {
		markMapper.delMark(bookmarkid);
	}
	@Override
	public Pagination queryMarkPageJson(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = new HashMap();
		try {
			map=setMapValue(request, map);
			int totalCounts=markMapper.getTotalsMark(map);
			page = new Pagination(currentPage,pageSize,totalCounts); 
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(request,map2);
			List list = markMapper.queryMarkPageJson(map2);
			page.setRows(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	private Map setMapValue(HttpServletRequest request,Map map) throws Exception{
		String bookmarkname = StringUtil.addLikeOperBoth(request.getParameter("bookmarkname"));
		int isloop=-1;
		if(request.getParameter("isloop")!=null &&!("").equals(request.getParameter("isloop")))
			isloop=Integer.parseInt(request.getParameter("isloop"));
		map.put("bookmarkname", bookmarkname);
		map.put("isloop", isloop);
		return map;
	}

}
