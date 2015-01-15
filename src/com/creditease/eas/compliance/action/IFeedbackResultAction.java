package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.compliance.service.IFeedbackResultService;
import com.creditease.eas.compliance.service.IFeedbackService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
/**
 * @IFeedbackResultAction.java	合规（初步调查——被投诉部门调查处理反馈结果action）
 * created at 2013-10-8 下午04:44:54 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class IFeedbackResultAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private IFeedbackResultService feedbackResultServiceImpl;
	@Autowired
	private IFeedbackService feedbackServiceImpl;
	/**合规（初步调查——被投诉部门调查处理反馈结果bean）**/
	private IFeedbackResult feedbackResult;
	/**日志**/
	private static Logger logger = Logger.getLogger(IFeedbackResultAction.class);
	/**初步调查表主键id**/
	private Integer investigationId;
	/**反馈表外键id**/
	private Integer feedBackId;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	/**调查方式下拉列表集合**/
	private List<Dictionary> feedBackTypes;
	/**下一步方案下拉列表集合**/
	private List<Dictionary> nextSchemes;
	/**关联的反馈信息**/
	private IFeedback feedback;
	private String returnBack = "false";
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
			this.pagination = feedbackResultServiceImpl.queryPage(pagination);
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
			this.getAllDictionarys();
			feedbackResult = feedbackResultServiceImpl.getIFeedbackResultById(feedbackResult.getId());
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
			feedbackResultServiceImpl.delete(feedbackResult.getId());
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
			if("true".equals(request.getParameter("returnBack"))) returnBack = "true";
			this.getAllDictionarys();
			if(request.getParameter("investigationId")!=null){
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
				this.feedBackId = feedbackResultServiceImpl.getFeedBackId(this.investigationId);
				this.feedback = feedbackServiceImpl.getIFeedbackById(this.feedBackId);
			} 
			if(feedbackResult!=null) 
				feedbackResult = feedbackResultServiceImpl.getIFeedbackResultById(feedbackResult.getId());
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
			feedbackResult.setLastUpdaterId(this.findUser().getId().intValue());
			feedbackResultServiceImpl.update(feedbackResult);
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
			feedbackResult.setCreaterId(this.findUser().getId().intValue());
			feedbackResultServiceImpl.insert(feedbackResult);
			if(feedbackResult.getNextSchemeId()==2){//需要补充调查时需要再次流程到被投诉部门调查处理待反馈（6）
				Map map = new HashMap();
				map.put("id", this.investigationId);
				map.put("statusId", 6);
				investigationServiceImpl.updateStatus(map);
				IFeedbackRequired feedbackRequired = feedbackResultServiceImpl.getFeedbackRequiredById(feedbackResult.getId());
				//发送案件进度邮件
				map.put("feedbackPersonEmail", feedbackRequired.getEmail());
				boolean sendSuccess = investigationServiceImpl.sendEmail(map);
				logger.info(sendSuccess?"给案件负责人和要求反馈的人员发送邮件成功！":"给案件负责人和要求反馈的人员发送邮件失败！");
			}
			this.json = "{\"success\":\"true\""+",\"feedBackResultId\":\""+feedbackResult.getId()+"\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 
	 * 描述：查询所有的下拉列表集合
	 * 2013-10-9 下午06:22:55 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getAllDictionarys() throws Exception{
		List<Map> list = feedbackResultServiceImpl.getFeedBackTypes();
		this.feedBackTypes = DictionaryUtil.getDictionarys(list);
		list = feedbackResultServiceImpl.getNextSchemes();
		this.nextSchemes = DictionaryUtil.getDictionarys(list);
	}
	/**
	 * @return the feedbackResult
	 */
	public IFeedbackResult getFeedbackResult() {
		return feedbackResult;
	}
	/**
	 * @param feedbackResult the feedbackResult to set
	 */
	public void setFeedbackResult(IFeedbackResult feedbackResult) {
		this.feedbackResult = feedbackResult;
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
	 * @return the feedBackId
	 */
	public Integer getFeedBackId() {
		return feedBackId;
	}
	/**
	 * @param feedBackId the feedBackId to set
	 */
	public void setFeedBackId(Integer feedBackId) {
		this.feedBackId = feedBackId;
	}
	/**
	 * @return the feedBackTypes
	 */
	public List<Dictionary> getFeedBackTypes() {
		return feedBackTypes;
	}
	/**
	 * @param feedBackTypes the feedBackTypes to set
	 */
	public void setFeedBackTypes(List<Dictionary> feedBackTypes) {
		this.feedBackTypes = feedBackTypes;
	}
	/**
	 * @return the nextSchemes
	 */
	public List<Dictionary> getNextSchemes() {
		return nextSchemes;
	}
	/**
	 * @param nextSchemes the nextSchemes to set
	 */
	public void setNextSchemes(List<Dictionary> nextSchemes) {
		this.nextSchemes = nextSchemes;
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
