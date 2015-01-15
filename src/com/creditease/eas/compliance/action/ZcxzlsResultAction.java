package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.compliance.service.FieldsurveyService;
import com.creditease.eas.compliance.service.IFeedbackService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.compliance.service.ZcxzlsResultService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
/**
 * @ZcxzlsfbresultAction.java	合规（初步调查——再次协助落实反馈结果action）
 * created at 2013-10-8 下午05:14:45 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class ZcxzlsResultAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private ZcxzlsResultService zcxzlsfbResultServiceImpl;
	/**合规（初步调查——再次协助落实反馈结果bean）**/
	private ZcxzlsResult zcxzlsfbResult;
	/**日志**/
	private static Logger logger = Logger.getLogger(ZcxzlsResultAction.class);
	/**初步调查表主键id**/
	private Integer investigationId;
	/**调查方式下拉列表集合**/
	private List<Dictionary> xzbmfbResults;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	@Autowired
	private FieldsurveyService fieldsurveyServiceImpl;
	@Autowired
	private IFeedbackService feedbackServiceImpl;
	/**反馈历史记录**/
	private List<IFeedback> feedbacks;
	/**关联的反馈信息**/
	private IFeedback feedback;
	/**关联的实地调查信息**/
	private Fieldsurvey fieldsurvey;
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
			this.pagination = zcxzlsfbResultServiceImpl.queryPage(pagination);
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
			zcxzlsfbResult = zcxzlsfbResultServiceImpl.getZcxzlsfbResultById(zcxzlsfbResult.getId());
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
			zcxzlsfbResultServiceImpl.delete(zcxzlsfbResult.getId());
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
			this.getAllDictionarys();
			if(request.getParameter("investigationId")!=null){
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
				this.fieldsurvey = zcxzlsfbResultServiceImpl.getFieldsurvey(this.investigationId);
				this.feedbacks = zcxzlsfbResultServiceImpl.getRelationFeedBacks(this.investigationId);
				this.feedback = feedbacks.get(0);
			}
			if(zcxzlsfbResult!=null) 
				zcxzlsfbResult = zcxzlsfbResultServiceImpl.getZcxzlsfbResultById(zcxzlsfbResult.getId());
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
			zcxzlsfbResult.setLastUpdaterId(this.findUser().getId().intValue());
			zcxzlsfbResultServiceImpl.update(zcxzlsfbResult);
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
			zcxzlsfbResult.setCreaterId(this.findUser().getId().intValue());
			zcxzlsfbResultServiceImpl.insert(zcxzlsfbResult);
			if(zcxzlsfbResult.getXzbmfbResultId()==2){//需要再次协助落实时修改案件状态为"再次协助落实待反馈"（11）
				Map map = new HashMap();
				map.put("statusId", 11);
				map.put("id", this.investigationId);
				investigationServiceImpl.updateStatus(map);
				fieldsurvey = zcxzlsfbResultServiceImpl.getFieldsurvey(this.investigationId);
				//发送案件进度邮件
				map.put("feedbackPersonEmail", fieldsurvey.getEmail());
				boolean sendSuccess = investigationServiceImpl.sendEmail(map);
				logger.info(sendSuccess?"给案件负责人和要求反馈的人员发送邮件成功！":"给案件负责人和要求反馈的人员发送邮件失败！");
			}
			this.json = "{\"success\":\"true\",\"zcxzlsId\":\""+zcxzlsfbResult.getId()+"\"}";
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
		List<Map> list = fieldsurveyServiceImpl.getXzbmfbResults();
		this.xzbmfbResults = DictionaryUtil.getDictionarys(list);
	}
	/**
	 * @return the zcxzlsfbResult
	 */
	public ZcxzlsResult getZcxzlsfbResult() {
		return zcxzlsfbResult;
	}
	/**
	 * @param zcxzlsfbResult the zcxzlsfbResult to set
	 */
	public void setZcxzlsfbResult(ZcxzlsResult zcxzlsfbResult) {
		this.zcxzlsfbResult = zcxzlsfbResult;
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
	 * @return the xzbmfbResults
	 */
	public List<Dictionary> getXzbmfbResults() {
		return xzbmfbResults;
	}
	/**
	 * @param xzbmfbResults the xzbmfbResults to set
	 */
	public void setXzbmfbResults(List<Dictionary> xzbmfbResults) {
		this.xzbmfbResults = xzbmfbResults;
	}
	/**
	 * @return the feedbacks
	 */
	public List<IFeedback> getFeedbacks() {
		return feedbacks;
	}
	/**
	 * @param feedbacks the feedbacks to set
	 */
	public void setFeedbacks(List<IFeedback> feedbacks) {
		this.feedbacks = feedbacks;
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
	 * @return the fieldsurvey
	 */
	public Fieldsurvey getFieldsurvey() {
		return fieldsurvey;
	}
	/**
	 * @param fieldsurvey the fieldsurvey to set
	 */
	public void setFieldsurvey(Fieldsurvey fieldsurvey) {
		this.fieldsurvey = fieldsurvey;
	}
}
