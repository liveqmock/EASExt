package com.creditease.eas.adminipurc.action;

import java.util.Date;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.creditease.eas.adminipurc.service.IAdminiPurcPortService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.commbean.CommonPortinfo;
/****
 * hetong 信息
 *AdminiPuocAction
 * @author gqy 2014-5-19
 */
@Controller
@Scope("prototype")
public class AdminiPurcPortAction extends BaseAction{
	@Resource
	private IAdminiPurcPortService adminiProcPortService;
	private CommonPortinfo portinfo;
	/***
	 * 验证用的邮箱
	 */
	private String fportEmailValid;

	public String getFportEmailValid() {
		return fportEmailValid;
	}

	public void setFportEmailValid(String fportEmailValid) {
		this.fportEmailValid = fportEmailValid;
	}

	public IAdminiPurcPortService getAdminiProcPortService() {
		return adminiProcPortService;
	}

	public void setAdminiProcPortService(
			IAdminiPurcPortService adminiProcPortService) {
		this.adminiProcPortService = adminiProcPortService;
	}

	public CommonPortinfo getPortinfo() {
		return portinfo;
	}

	public void setPortinfo(CommonPortinfo portinfo) {
		this.portinfo = portinfo;
	}
	/***
	 *  接口人信息列表
	* @Title: queryPageJson   
	* @return
	* @throws Exception
	* @return String
	 */
	public String queryPageJson() throws Exception {
		this.pagination = adminiProcPortService.queryPage(pagination);
		return "queryPageJson";
	}
	/***
	 * 查看接口人信息
	* @Title: view
	*created at 2014-6-1 上午10:35:44 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String view() throws Exception{
		portinfo = adminiProcPortService.findPortinfoById(portinfo.getFid());
		return "view";
	}

	/***
	 * 编辑
	* @Title: edit
	*created at 2014-6-1 上午11:15:28 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String edit() throws Exception{
		portinfo = adminiProcPortService.findPortinfoById(portinfo.getFid());
		return "edit";
	}
	/***
	 * 修改
	* @Title: update
	*created at 2014-6-1 上午11:15:36 by ygq  
	* @throws Exception
	* @return void
	 */
	public void update() throws Exception{
		try {
			portinfo.setFlastupdater(UserUtil.getUser().getId());
			portinfo.setFlastupdatetime(new Date());
			adminiProcPortService.update(portinfo,fportEmailValid);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/***
	 * 物理删除
	* @Title: delete
	*created at 2014-6-1 上午11:15:43 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String delete() throws Exception{
		adminiProcPortService.delete(portinfo.getFid());
		return "queryPageJsonBack";
	}
}