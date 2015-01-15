/**
 * 
 */
package com.creditease.eas.warn.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.Variables;
import com.creditease.eas.warn.service.VariablesService;

/**
 * @JumbosmsvAction.java
 * created at 2012-12-26 下午05:04:08 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class VariablesAction extends BaseAction{
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	private Variables variables;
	
	public void setVariables(Variables variables) {
		this.variables = variables;
	}
	
	public Variables getVariables() {
		return variables;
	}
	

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	@Autowired
	private VariablesService variablesServiceImpl;
	
	
//	public String query() throws Exception{
//		page = variablesServiceImpl.queryPage(page);
//		return "list";
//	}
	
	public String queryPageJson() throws Exception {
		this.pagination = variablesServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	
	public String queryPageJsonBack() throws Exception{
		return "queryPageJsonBack";
	}
	
	/**
	 * 插入
	 * 描述：
	 * 2012-12-27 下午06:17:08 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		return "add";
	}
	/**
	 * 保存
	 * 描述：
	 * 2012-12-27 下午06:16:52 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception{
		//variables.setId(300);
		variablesServiceImpl.insert(variables);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
	
	}
	/**
	 * 删除
	 * 描述：
	 * 2012-12-27 下午06:16:44 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		variablesServiceImpl.delete(variables.getId());
		return "queryPageJsonBack";
		
	}
	/**
	 * 编辑
	 * 描述：
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		variables = variablesServiceImpl.getVariablesById(variables.getId());
		return "edit";
	}
	/**修改
	 * x
	 * 描述：
	 * 2012-12-27 下午06:15:59 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void update() throws Exception{
		variablesServiceImpl.update(variables);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
	}
	/**
	 * 查看
	 * 描述：
	 * 2012-12-27 下午06:16:18 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		variables = variablesServiceImpl.getVariablesById(variables.getId());
		return "view";
	}
	
}
