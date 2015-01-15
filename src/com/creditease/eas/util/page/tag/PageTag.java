package com.creditease.eas.util.page.tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.creditease.eas.util.page.PageBean;


public class PageTag extends TagSupport{
	private PageBean pageBean;
	private HttpServletRequest request;
	private JspWriter writer;
	private Map<String,Object> params;
	@Override
	public int doEndTag() throws JspException {
		try{
			if(pageBean==null){
				writer.print("&nbsp;");
				return 0;
			}
			Object rootFilter = request.getAttribute("rootFilter");
			String paramFirst = "pageBean.curPage=1";
			String paramPre = "pageBean.curPage=" + (pageBean.getCurPage()-1);
			String paramNext = "pageBean.curPage=" + (pageBean.getCurPage() + 1);
			String paramLast = "pageBean.curPage=" + pageBean.getMaxPage();
			if(null != params && !params.isEmpty()){
				for(Entry<String,Object> ent: params.entrySet()){
					paramFirst += "&" + ent.getKey() + "=" + ent.getValue();
					paramPre += "&" + ent.getKey() + "=" + ent.getValue();
					paramNext += "&" + ent.getKey() + "=" + ent.getValue();
					paramLast += "&" + ent.getKey() + "=" + ent.getValue();
				}
			}
			if(pageBean.getCurPage()<=1){
				writer.println("<span>首页</span> ");
				writer.println("<span>上一页</span> ");
			}else{
				writer.println("<span><a href='"+rootFilter+"?" +paramFirst + "'>首页</a></span>");
				writer.println("<span><a href='"+rootFilter+"?" +paramPre + "'>上一页</a></span>");
			}
			if(pageBean.getCurPage()>=pageBean.getMaxPage()){
				writer.println("<span>下一页</span> ");
				writer.println("<span>尾页</span> ");
			}else{
				writer.println("<span><a href='"+rootFilter+"?" +paramNext + "'>下一页</a></span>");
				writer.println("<span><a href='"+rootFilter+"?" +paramLast + "'>尾页</a></span>");
			}
			writer.println("<span>第"+pageBean.getCurPage()+"页</span>");
			writer.println("<span>共"+pageBean.getMaxPage()+"页</span>");
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	@Override
	public int doStartTag() throws  JspException{
		params.clear();
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public void setPageContext(PageContext pageContext) {
		params = new HashMap<String,Object>();
		request = (HttpServletRequest)pageContext.getRequest();
		pageBean = (PageBean)request.getAttribute("pageBean");
		writer= pageContext.getOut();
	}
	/**
	 * 添加参数
	 * @param obj
	 */
	public void addParam(String paramName,String paramValue){
		params.put(paramName, paramValue);
	}
}
