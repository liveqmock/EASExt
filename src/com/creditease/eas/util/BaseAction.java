package com.creditease.eas.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.page.PageBean;
import com.opensymphony.xwork2.ActionSupport;
public class BaseAction extends ActionSupport{
	protected Pagination pagination = new Pagination();
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	protected HttpSession getHttpSession(){
		return request.getSession();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	protected PageBean pageBean = new PageBean();
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	protected int querytype;//查询类型
	protected String condition;//查询条件
	protected String message;
	/**
	 * 返回json格式的字符串
	 */
	protected String json;
	public int getQuerytype() {
		return querytype;
	}
	public void setQuerytype(int querytype) {
		this.querytype = querytype;
	}
	public String getCondition() {
		return condition==null?"":condition.trim();
	}
	public void setCondition(String condition) {
		this.condition=(condition==null?"":condition.trim());
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * 
	 * 描述：将json格式的字符串写到客户端
	 * 2012-12-29 下午03:15:41 by ygq
	 * @version
	 * @param json
	 * @throws Exception
	 */
	public void writerJsonToClient(String json) throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	/**
	 * 
	 * 描述：将json格式的字符串写到客户端
	 * 增加了	request.setCharacterEncoding("utf-8");
	 * 2012-12-29 下午03:15:41 by ygq
	 * @version
	 * @param json
	 * @throws Exception
	 */
	public void writerJsonToClientEncode(String json) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	/**
	 * 
	 * 描述：获取系统登陆用户
	 * 2013-8-28 下午03:06:10 by caoyong
	 * @version
	 * @return
	 */
	protected User findUser() {
		User user = (User)request.getSession().getAttribute("user");
		return user;
	}
}
