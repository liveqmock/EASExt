package com.creditease.eas.warn.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.FinanceGroupBean;
import com.creditease.eas.warn.service.FinanceGroupService;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FinanceGroupAction extends BaseAction {
	@Autowired
	private FinanceGroupService financeGroupService;
	public FinanceGroupBean financeGroupAction;
	List<FinanceGroupBean> financegrouplist;
	
	public List<FinanceGroupBean> getFinancegrouplist() {
		return financegrouplist;
	}

	public void setFinancegrouplist(List<FinanceGroupBean> financegrouplist) {
		this.financegrouplist = financegrouplist;
	}

	public FinanceGroupBean getFinanceGroupAction() {
		return financeGroupAction;
	}
	public void setFinanceGroupAction(FinanceGroupBean financeGroupAction) {
		this.financeGroupAction = financeGroupAction;
	}
	/**
	 * 查询组信息
	 * 描述：
	 * 2013-9-17 下午02:01:54 by zhangxin
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson(){
		this.pagination = financeGroupService.queryPage(pagination);
		financegrouplist = pagination.getRows();
		return "queryPageJson";
	}
	
	/**
	 * 新增、编辑
	 * 描述：跳转到编辑页面，新增编辑一个页面
	 * 2013-9-17 下午02:02:06 by zhangxin
	 * @version
	 * @return
	 */
	public String edit(){
		if(financeGroupAction != null)financeGroupAction = financeGroupService.selectByPrimaryKey(financeGroupAction.getId());
		return "edit";
	}
	/**
	 * 添加组信息
	 * 描述：
	 * 2013-9-17 下午04:48:42 by zhangxin
	 * @version
	 */
	public void insert(){
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String username = user.getUsername();
			financeGroupAction.setCreator(username);
			financeGroupService.insert(financeGroupAction);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			try {
				this.writerJsonToClient(this.json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 更新组信息
	 * 描述：
	 * 2013-9-17 下午04:55:46 by zhangxin
	 * @version
	 */
	public void update(){
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String username = user.getUsername();
			financeGroupAction.setLastupdater(username);
			financeGroupService.update(financeGroupAction);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			try {
				this.writerJsonToClient(this.json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * 描述：删除组信息
	 * 2013-9-17 下午05:16:57 by zhangxin
	 * @version
	 */
	public void delete(){
		financeGroupService.delete(financeGroupAction.getId());
	}
	
	/**
	 * 
	 * 描述：判断组是否已经存在
	 * 2013-9-18 下午01:49:37 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void selectedIfGroupExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = financeGroupService.selectedIfGroupExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
}
