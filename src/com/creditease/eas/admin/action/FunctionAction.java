package com.creditease.eas.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.admin.service.FunctionService;
import com.creditease.eas.util.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction{
	
	@Autowired
	private FunctionService functionService;
	
	List<Function> functionList;
	
	public Function functionaction;
	

	public Function getFunctionaction() {
		return functionaction;
	}

	public void setFunctionaction(Function functionaction) {
		this.functionaction = functionaction;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}
	
	/**
	 * 查询所有功能
	 * 描述：
	 * 2013-8-5 下午04:34:03 by Administrator
	 * @version
	 * @return
	 */
	public String queryPageJson(){
		this.pagination = functionService.queryPage(pagination);
		functionList = pagination.getRows();
		return "queryPageJson";
	}
	 public String queryPageJsonBack() throws Exception{
		return "queryPageJsonBack";
	 }
	
    /**
     * 删除数据时将状态值stutas改为1
     * 描述：
     * 2013-8-5 下午06:06:53 by Administrator
     * @version
     * @return
     */
	public void updateStutas(){
		Integer id = functionaction.getId();
		functionService.updateStutas(id);
		
	}
	/**
	 * 根据id查询功能信息
	 * 描述：
	 * 2013-8-9 下午04:48:48 by Administrator
	 * @version
	 * @return
	 */
	public String selectFunctionByKey(){
		Integer id = functionaction.getId();
		functionaction = functionService.selectFunctionByKey(id);
		return "selectFunctionByKey";
	}
	
	public String addfunctionAction(){
		return "addfunctionAction";
	}
	
	/**
	 * 修改功能信息
	 * 描述：
	 * 2013-8-5 下午07:07:26 by Administrator
	 * @version
	 * @return
	 */
	public void updateFunction(){
		functionService.updateFunction(functionaction);
		this.closewindow();
	}
	/**
	 * 插入功能信息
	 * 描述：
	 * 2013-8-9 下午04:41:20 by Administrator
	 * @version
	 * @return
	 */
	public void insertFunction(){
		functionService.insertFunction(functionaction);
		this.closewindow();
	}
	/**
	 * 判断功能是不是已经存在
	 * 描述：
	 * 2013-8-13 下午05:27:05 by Administrator
	 * @version
	 * @param funname
	 * @throws Exception 
	 */
	public void ifFunctionHasExists() throws Exception{
		String funname = request.getParameter("funname");
		int num = functionService.selectedIfFunctionExists(funname);
		writerJsonToClient("" + num);
		
	}
	
	public  void closewindow(){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print("<script type='text/javascript'>parent.test();</script>");
			pw.flush();
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
