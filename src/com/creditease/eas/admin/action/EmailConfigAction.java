package com.creditease.eas.admin.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.service.EmailConfigService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.service.InterfacePersonService;

@Controller
@Scope("prototype")
public class EmailConfigAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	/**是否查看**/
	private int isView;
	
	private EmailConfig emailConfig;

	public int getIsView() {
		return isView;
	}

	public void setIsView(int isView) {
		this.isView = isView;
	}

	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	@Autowired
	private EmailConfigService emailConfigService;

	public String queryPageJson() throws Exception {
		this.pagination = emailConfigService.queryPage(pagination);
		return "queryPageJson";
	}
	
	public String view() throws Exception{
		this.emailConfig=emailConfigService.getEmailConfigByFid(emailConfig.getFid());
		//interfacePersonService.sendorgmail();//发邮件测试
		List<Map> map=emailConfigService.test();
		System.out.println(map);
		return "view";
	}
	
	public void update() throws Exception{
		//设置更新人
		User user = (User)request.getSession().getAttribute("user");
		emailConfig.setFupdateid(Integer.parseInt(String.valueOf(user.getId())));
		
		emailConfigService.update(emailConfig);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
	}
}
