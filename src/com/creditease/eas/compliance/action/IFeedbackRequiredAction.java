package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.service.IFeedbackRequiredService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;

/**
 * @IFeedbackAction.java 合规（初步调查——被投诉部门调查处理反馈要求action） created at 2013-10-8
 *                       下午03:00:00 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Controller
@Scope("prototype")
public class IFeedbackRequiredAction extends BaseAction {
	/** 序列化 **/
	private static final long serialVersionUID = 1L;
	/** 要自动装配的service接口 **/
	@Autowired
	private IFeedbackRequiredService iFeedbackRequiredServiceImpl;
	/** 合规（初步调查——被投诉部门调查处理反馈要求bean） **/
	private IFeedbackRequired feedbackRequired;
	/** 日志 **/
	private static Logger logger = Logger
			.getLogger(IFeedbackRequiredAction.class);
	/** 初步调查表外键id **/
	private Integer investigationId;
	/** 要自动装配的初步调查service接口 **/
	@Autowired
	private InvestigationService investigationServiceImpl;
	private String returnBack = "false";

	/**
	 * 
	 * 描述：查询列表页数据 2013-9-16 上午11:02:39 by caoyong
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() {
		try {
			this.pagination = iFeedbackRequiredServiceImpl
					.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：查看Entity详细信息 2013-9-16 上午11:28:30 by caoyong
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() {
		try {
			feedbackRequired = iFeedbackRequiredServiceImpl
					.getFeedbackRequiredById(feedbackRequired.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**
	 * 
	 * 描述：删除Entity 2013-9-16 上午11:28:58 by caoyong
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() {
		try {
			iFeedbackRequiredServiceImpl.delete(feedbackRequired.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}

	/**
	 * 
	 * 描述：跳转到编辑页面 2013-9-16 上午11:31:32 by caoyong
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		try {
			if("true".equals(request.getParameter("returnBack"))) returnBack = "true";
			if (request.getParameter("investigationId") != null)
				this.investigationId = Integer.parseInt(request
						.getParameter("investigationId"));
			if (feedbackRequired != null)
				feedbackRequired = iFeedbackRequiredServiceImpl
						.getFeedbackRequiredById(feedbackRequired.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	/**
	 * 
	 * 描述：修改 2013-9-16 上午11:32:41 by caoyong
	 * 
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception {
		try {
			feedbackRequired.setLastUpdaterId(this.findUser().getId()
					.intValue());
			iFeedbackRequiredServiceImpl.update(feedbackRequired);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	 * 描述：新增记录 2013-9-16 上午11:33:59 by caoyong
	 * 
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert() throws Exception {
		try {
			feedbackRequired.setCreaterId(this.findUser().getId().intValue());
			iFeedbackRequiredServiceImpl.insert(feedbackRequired);
			// "被投诉部门调查处理审批已通过"状态时修改案件的状态为"被投诉部门调查处理待反馈"（6）
			Map map = new HashMap();
			map.put("statusId", 6);
			map.put("id", this.investigationId);
			investigationServiceImpl.updateStatus(map);
			//发送案件进度邮件
			iFeedbackRequiredServiceImpl.insertUserByEmail(feedbackRequired.getEmail(),this.findUser().getUsername());
			map.put("feedbackPersonEmail", feedbackRequired.getEmail());
			boolean sendSuccess = investigationServiceImpl.sendEmail(map);
			logger.info(sendSuccess?"给案件负责人和要求反馈的人员发送邮件成功！":"给案件负责人和要求反馈的人员发送邮件失败！");
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		} finally {
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * @return the feedbackRequired
	 */
	public IFeedbackRequired getFeedbackRequired() {
		return feedbackRequired;
	}

	/**
	 * @param feedbackRequired the feedbackRequired to set
	 */
	public void setFeedbackRequired(IFeedbackRequired feedbackRequired) {
		this.feedbackRequired = feedbackRequired;
	}

	/**
	 * @return the investigationId
	 */
	public Integer getInvestigationId() {
		return investigationId;
	}

	/**
	 * @param investigationId
	 *            the investigationId to set
	 */
	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}

	/**
	 * @return the returnBack
	 */
	public String getReturnBack() {
		return returnBack;
	}

	/**
	 * @param returnBack the returnBack to set
	 */
	public void setReturnBack(String returnBack) {
		this.returnBack = returnBack;
	}
}
