package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.service.FieldsurveyService;
import com.creditease.eas.compliance.service.IFeedbackRequiredService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
/**
 * @FieldsurveyAction.java	合规（初步调查——实地调查情况action）
 * created at 2013-10-8 下午01:34:30 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class FieldsurveyAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private FieldsurveyService fieldsurveyServiceImpl;
	@Autowired
	private IFeedbackRequiredService iFeedbackRequiredServiceImpl;
	/**初步调查记录bean**/
	private Fieldsurvey fieldsurvey;
	/**日志**/
	private static Logger logger = Logger.getLogger(FieldsurveyAction.class);
	/**初步调查表主键id**/
	private Integer investigationId;
	/**调查方式下拉列表集合**/
	private List<Dictionary> xzbmfbResults;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
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
			this.pagination = fieldsurveyServiceImpl.queryPage(pagination);
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
			fieldsurvey = fieldsurveyServiceImpl.getFieldsurveyById(fieldsurvey.getId());
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
			fieldsurveyServiceImpl.delete(fieldsurvey.getId());
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
			if(request.getParameter("investigationId")!=null)
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
			if(fieldsurvey!=null) 
				fieldsurvey = fieldsurveyServiceImpl.getFieldsurveyById(fieldsurvey.getId());
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
			fieldsurvey.setLastUpdaterId(this.findUser().getId().intValue());
			fieldsurveyServiceImpl.update(fieldsurvey);
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
			fieldsurvey.setCreaterId(this.findUser().getId().intValue());
			fieldsurveyServiceImpl.insert(fieldsurvey);
			if(fieldsurvey.getXzbmfbResultId()==2){//需要再次协助落实时修改案件状态为"再次协助落实待反馈"（11）
				iFeedbackRequiredServiceImpl.insertUserByEmail(fieldsurvey.getEmail(),this.findUser().getUsername());
				Map map = new HashMap();
				map.put("statusId", 11);
				map.put("id", this.investigationId);
				investigationServiceImpl.updateStatus(map);
				//发送案件进度邮件
				map.put("feedbackPersonEmail", fieldsurvey.getEmail());
				boolean sendSuccess = investigationServiceImpl.sendEmail(map);
				logger.info(sendSuccess?"给案件负责人和要求反馈的人员发送邮件成功！":"给案件负责人和要求反馈的人员发送邮件失败！");
			}
			this.json = "{\"success\":\"true\",\"fieldSurveyId\":\""+fieldsurvey.getId()+"\"}";
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
