package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.service.IFeedbackService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
/**
 * @IFeedbackAction.java	合规（初步调查——被投诉部门调查处理反馈action）
 * created at 2013-10-8 下午03:00:00 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class IFeedbackAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private IFeedbackService iFeedbackServiceImpl;
	/**合规（初步调查——被投诉部门调查处理反馈bean）**/
	private IFeedback feedback;
	/**日志**/
	private static Logger logger = Logger.getLogger(IFeedbackAction.class);
	/**初步调查表主键id**/
	private Integer investigationId;
	/**要求反馈表外键id**/
	private Integer feedbackRequiredId;
	/**实地调查表外键id**/
	private Integer fieldsurveyId;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	/**
	 * 
	 * 描述：查询列表页数据
	 * 2013-9-16 上午11:02:39 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson(){
		try {
			this.pagination = iFeedbackServiceImpl.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：查看Entity详细信息
	 * 2013-9-16 上午11:28:30 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view(){
		try {
			feedback = iFeedbackServiceImpl.getIFeedbackById(feedback.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 
	 * 描述：删除Entity
	 * 2013-9-16 上午11:28:58 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete(){
		try {
			iFeedbackServiceImpl.delete(feedback.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	/**
	 * 
	 * 描述：跳转到编辑页面
	 * 2013-9-16 上午11:31:32 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		try {
			if(request.getParameter("investigationId")!=null){ 
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
				if(iFeedbackServiceImpl.getFeedbackRequiredId(this.investigationId)!=0
						&& "6".equals(request.getParameter("statusId")))
					this.feedbackRequiredId = iFeedbackServiceImpl.getFeedbackRequiredId(this.investigationId);
				if(iFeedbackServiceImpl.getFieldsurveyId(this.investigationId)!=0
						&& "11".equals(request.getParameter("statusId")))
					this.fieldsurveyId = iFeedbackServiceImpl.getFieldsurveyId(this.investigationId);
			}
			if(feedback!=null) feedback = iFeedbackServiceImpl.getIFeedbackById(feedback.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	/**
	 * 
	 * 描述：修改
	 * 2013-9-16 上午11:32:41 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			feedback.setLastUpdaterId(this.findUser().getId().intValue());
			iFeedbackServiceImpl.update(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	/**
	 * 
	 * 描述：新增记录
	 * 2013-9-16 上午11:33:59 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert() throws Exception{
		try {
			feedback.setCreaterId(this.findUser().getId().intValue());
			iFeedbackServiceImpl.insert(feedback);
			//"被投诉部门调查处理待反馈"（6）状态时修改案件的状态为"被投诉部门调查处理已反馈"（7）
			//"再次协助落实待反馈"（11）状态时修改案件的状态为"再次协助落实已反馈"（12）
			Map map = new HashMap();
			map.put("statusId", feedback.getFeedBackRequiredId()!=null?7:12);
			map.put("id", this.investigationId);
			investigationServiceImpl.updateStatus(map);
			boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
			logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * @return the feedback
	 */
	public IFeedback getFeedback() {
		return feedback;
	}
	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(IFeedback feedback) {
		this.feedback = feedback;
	}
	/**
	 * @return the investigationId
	 */
	public Integer getInvestigationId() {
		return investigationId;
	}
	/**
	 * @param investigationId the investigationId to set
	 */
	public void setInvestigationId(Integer investigationId) {
		this.investigationId = investigationId;
	}
	/**
	 * @return the feedbackRequiredId
	 */
	public Integer getFeedbackRequiredId() {
		return feedbackRequiredId;
	}
	/**
	 * @param feedbackRequiredId the feedbackRequiredId to set
	 */
	public void setFeedbackRequiredId(Integer feedbackRequiredId) {
		this.feedbackRequiredId = feedbackRequiredId;
	}
	/**
	 * @return the fieldsurveyId
	 */
	public Integer getFieldsurveyId() {
		return fieldsurveyId;
	}
	/**
	 * @param fieldsurveyId the fieldsurveyId to set
	 */
	public void setFieldsurveyId(Integer fieldsurveyId) {
		this.fieldsurveyId = fieldsurveyId;
	}
}
