/**
 * 
 */
package com.creditease.eas.warn.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.service.SendPortInfoService;

/**
 * @SendPortInfoAction.java	已发送房租合同邮件明细action
 * created at 2013-8-2 上午10:28:08 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class SendPortInfoAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SendPortInfoService sendPortInfoServiceImpl;

	/**已发送房租合同邮件明细bean**/
	private SendPortInfo sendPortInfo;
	
	/**
	 * @return the sendPortInfo
	 */
	public SendPortInfo getSendPortInfo() {
		return sendPortInfo;
	}

	/**
	 * @param sendPortInfo the sendPortInfo to set
	 */
	public void setSendPortInfo(SendPortInfo sendPortInfo) {
		this.sendPortInfo = sendPortInfo;
	}

	public String queryPageJson() throws Exception {
		this.pagination = sendPortInfoServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}

	/**
	 * 查看
	 * 描述：
	 * 2012-12-27 下午06:16:18 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		sendPortInfo = sendPortInfoServiceImpl.getSendPortInfoById(sendPortInfo.getId());
		return "view";
	}
}
