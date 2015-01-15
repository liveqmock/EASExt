/**
 * 
 */
package com.creditease.eas.warn.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.service.JumbosmsvService;

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
public class JumbosmsvAction extends BaseAction{
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Jumbosmsv jumbosmsv;
	
	public void setJumbosmsv(Jumbosmsv jumbosmsv) {
		this.jumbosmsv = jumbosmsv;
	}
	
	public Jumbosmsv getJumbosmsv() {
		return jumbosmsv;
	}
	
	@Autowired
	private JumbosmsvService jumbosmsvServiceImpl;
	
	public String queryPageJson() throws Exception {
		this.pagination = jumbosmsvServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
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
		try {
			jumbosmsv.setIsuse(0);
			jumbosmsvServiceImpl.insert(jumbosmsv);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 删除
	 * 描述：
	 * 2012-12-27 下午06:16:44 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void delete() throws Exception{
		try {
			jumbosmsvServiceImpl.delete(jumbosmsv.getId());
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 是否启用
	 * 描述：
	 * 2013-1-6 下午06:46:29 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String isuse() throws Exception{
		Jumbosmsv tmp = jumbosmsvServiceImpl.getJumbosmsvByIsuse(1);
		if(tmp!=null){
			tmp.setIsuse(0);
			jumbosmsvServiceImpl.update(tmp);
		}
		jumbosmsv = jumbosmsvServiceImpl.getJumbosmsvById(jumbosmsv.getId());
		jumbosmsv.setIsuse(1);
		jumbosmsvServiceImpl.update(jumbosmsv);
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
		if(jumbosmsv!=null) jumbosmsv = jumbosmsvServiceImpl.getJumbosmsvById(jumbosmsv.getId());
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
		try {
			jumbosmsvServiceImpl.update(jumbosmsv);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
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
		jumbosmsv = jumbosmsvServiceImpl.getJumbosmsvById(jumbosmsv.getId());
		return "view";
	}
	
}
