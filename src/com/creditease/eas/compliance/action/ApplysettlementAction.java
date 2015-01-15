package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.compliance.service.ApplysettlementService;
import com.creditease.eas.compliance.service.FieldsurveyService;
import com.creditease.eas.compliance.service.ICaseInfoService;
import com.creditease.eas.compliance.service.IFeedbackResultService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.compliance.service.ZcxzlsResultService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
/**
 * @ApplysettlementAction.java	合规（申请结案记录action）
 * created at 2013-10-8 下午01:25:49 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class ApplysettlementAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private ApplysettlementService applysettlementServiceImpl;
	/**要自动装配的初步调查service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	@Autowired
	private IFeedbackResultService feedbackResultServiceImpl;
	@Autowired
	private FieldsurveyService fieldsurveyServiceImpl;
	@Autowired
	private ZcxzlsResultService zcxzlsResultServiceImpl;
	/**合规（申请结案记录bean）**/
	private Applysettlement applysettlement;
	/**日志**/
	private static Logger logger = Logger.getLogger(ApplysettlementAction.class);
	/**案件最终归类下拉列表集合**/
	private List<Dictionary> cfClassifyIds;
	/**是否违规下拉列表集合**/
	private List<Dictionary> outofLineIds;
	/**违规级别下拉列表集合**/
	private List<Dictionary> outofLineLevelIds;
	/**关系的转部门协助调查反馈结果表外键id**/
	private Integer feedBackResultId;
	/**关系的再次协助落实表外键id**/
	private Integer zcxzlsId;
	/**关系的合规初步调查外键id**/
	private Integer investigationId;
	/**关系的本部门实地调查表外键id**/
	private Integer fieldSurveyId;
	/**关系的案件最终归类**/
	@SuppressWarnings("unchecked")
	private List<Map> relInitypes;
	/**是否特殊结案**/
	private String sepcialEnd = "false";
	/**特殊结案下拉列表集合**/
	private List<Dictionary> seClasssifys;
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
			this.pagination = applysettlementServiceImpl.queryPage(pagination);
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
			applysettlement = applysettlementServiceImpl.getApplysettlementById(applysettlement.getId());
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
			applysettlementServiceImpl.delete(applysettlement.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	@Resource
	private  ICaseInfoService caseInfoService;
	public void setCaseInfoService(ICaseInfoService caseInfoService) {
		this.caseInfoService = caseInfoService;
	}
	/**
	 * 新分类
	 */
	List<CaseType> newCaseType;
	private List<RelInicasetype> relInicasetypes;
	public List<CaseType> getNewCaseType() {
		return newCaseType;
	}
	public void setNewCaseType(List<CaseType> newCaseType) {
		this.newCaseType = newCaseType;
	}
	public List<RelInicasetype> getRelInicasetypes() {
		return relInicasetypes;
	}
	public void setRelInicasetypes(List<RelInicasetype> relInicasetypes) {
		this.relInicasetypes = relInicasetypes;
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
			newCaseType=caseInfoService.getNewCaseType();//案件新分类
			if("true".equals(request.getParameter("returnBack"))){
				returnBack = "true";
				
				
			} 
			this.getAllDictionarys();
			if(request.getParameter("investigationId")!=null){
				this.investigationId = Integer.parseInt(request.getParameter("investigationId"));
			} 
			if(request.getParameter("sepcialEnd")!=null){
				this.sepcialEnd = request.getParameter("sepcialEnd");
			} 
			if(request.getParameter("feedBackResultId")!=null) 
				this.feedBackResultId = Integer.parseInt(request.getParameter("feedBackResultId"));
			if(request.getParameter("zcxzlsId")!=null) 
				this.zcxzlsId = Integer.parseInt(request.getParameter("zcxzlsId"));
			if(request.getParameter("fieldSurveyId")!=null) 
				this.fieldSurveyId = Integer.parseInt(request.getParameter("fieldSurveyId"));
			if(applysettlement!=null){
				applysettlement = applysettlementServiceImpl.getApplysettlementById(applysettlement.getId());
				//relInitypes = applysettlementServiceImpl.getRelInitypes(applysettlement.getId());
				relInicasetypes=applysettlementServiceImpl.getRelInicasetypesByApplysettlementId(applysettlement.getId());
				
				caseInfoService.setCaseTypeIsChecked(newCaseType, relInicasetypes);
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
//	/**
//	 * 
//	 * 描述：修改
//	 * 2013-9-16 上午11:32:41 by caoyong
//	 * @version
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public void update() throws Exception{
//		try {
//			applysettlement.setLastUpdaterId(this.findUser().getId().intValue());
//			applysettlementServiceImpl.update(applysettlement);
//			//申请结案时修改案件的状态为待结案初审（13）
//			Map map = new HashMap();
//			map.put("statusId", 13);
//			map.put("id", applysettlement.getInvestigationId());
//			investigationServiceImpl.updateStatus(map);
//			boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
//			logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
//			//更新操作时先删除已经关联记录之前的最终归类中间记录，然后重新添加记录到中间表
//			String initypes[] = request.getParameterValues("initypes");
//			applysettlementServiceImpl.deleteRelInitype(applysettlement.getId());
//			applysettlementServiceImpl.insertIntoRelInitype(applysettlement.getId(),initypes);
//			this.json = "{\"success\":\"true\"}";
//		} catch (Exception e) {
//			e.printStackTrace();	
//			this.json = "{\"success\":\"false\"}";
//		}finally{
//			this.writerJsonToClient(this.json);
//		}
//	}
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
			applysettlement.setCreaterId(this.findUser().getId().intValue());
			applysettlementServiceImpl.insert(applysettlement);
			String initypes[] = request.getParameterValues("initypes");
			//申请结案时修改案件的状态为待结案初审（13）
			Map map = new HashMap();
			map.put("statusId", 13);
			map.put("id", applysettlement.getInvestigationId());
//			map.put("sepcialEnd", this.sepcialEnd.equals("true")?1:"");
			investigationServiceImpl.updateStatus(map);
			boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
			logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
			//申请结案后将案件最终归类保存到中间记录表中
			//applysettlementServiceImpl.insertIntoRelInitype(applysettlement.getId(),initypes);
			applysettlementServiceImpl.insertIntoRelInitype(applysettlement.getId(),request,getHttpSession());
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			try {
				//上一步操作到到申请结案失败时清理垃圾数据
				if(this.feedBackResultId != null){
					feedbackResultServiceImpl.delete(this.feedBackResultId);
				}else if(this.fieldSurveyId != null){
					fieldsurveyServiceImpl.delete(this.fieldSurveyId);
				}else if(this.zcxzlsId != null){
					zcxzlsResultServiceImpl.delete(this.zcxzlsId);
				}else if(this.investigationId != null){
					investigationServiceImpl.delete(
							investigationServiceImpl.getChildInvestigations(this.investigationId).get(0).getId());
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
		List<Map> list = applysettlementServiceImpl.getCfClassifyIds();
		this.cfClassifyIds = DictionaryUtil.getDictionarys(list);
		list = applysettlementServiceImpl.getOutofLineIds();
		this.outofLineIds = DictionaryUtil.getDictionarys(list);
		list = applysettlementServiceImpl.getOutofLineLevelIds();
		this.outofLineLevelIds = DictionaryUtil.getDictionarys(list);
		list = applysettlementServiceImpl.getSeClasssifys();
		this.seClasssifys = DictionaryUtil.getDictionarys(list);
	}
	/**
	 * @return the applysettlement
	 */
	public Applysettlement getApplysettlement() {
		return applysettlement;
	}
	/**
	 * @param applysettlement the applysettlement to set
	 */
	public void setApplysettlement(Applysettlement applysettlement) {
		this.applysettlement = applysettlement;
	}
	/**
	 * @return the cfClassifyIds
	 */
	public List<Dictionary> getCfClassifyIds() {
		return cfClassifyIds;
	}
	/**
	 * @param cfClassifyIds the cfClassifyIds to set
	 */
	public void setCfClassifyIds(List<Dictionary> cfClassifyIds) {
		this.cfClassifyIds = cfClassifyIds;
	}
	/**
	 * @return the outofLineIds
	 */
	public List<Dictionary> getOutofLineIds() {
		return outofLineIds;
	}
	/**
	 * @param outofLineIds the outofLineIds to set
	 */
	public void setOutofLineIds(List<Dictionary> outofLineIds) {
		this.outofLineIds = outofLineIds;
	}
	/**
	 * @return the outofLineLevelIds
	 */
	public List<Dictionary> getOutofLineLevelIds() {
		return outofLineLevelIds;
	}
	/**
	 * @param outofLineLevelIds the outofLineLevelIds to set
	 */
	public void setOutofLineLevelIds(List<Dictionary> outofLineLevelIds) {
		this.outofLineLevelIds = outofLineLevelIds;
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
	 * @return the feedBackResultId
	 */
	public Integer getFeedBackResultId() {
		return feedBackResultId;
	}
	/**
	 * @param feedBackResultId the feedBackResultId to set
	 */
	public void setFeedBackResultId(Integer feedBackResultId) {
		this.feedBackResultId = feedBackResultId;
	}
	/**
	 * @return the zcxzlsId
	 */
	public Integer getZcxzlsId() {
		return zcxzlsId;
	}
	/**
	 * @param zcxzlsId the zcxzlsId to set
	 */
	public void setZcxzlsId(Integer zcxzlsId) {
		this.zcxzlsId = zcxzlsId;
	}
	/**
	 * @return the fieldSurveyId
	 */
	public Integer getFieldSurveyId() {
		return fieldSurveyId;
	}
	/**
	 * @param fieldSurveyId the fieldSurveyId to set
	 */
	public void setFieldSurveyId(Integer fieldSurveyId) {
		this.fieldSurveyId = fieldSurveyId;
	}
	/**
	 * @return the relInitypes
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getRelInitypes() {
		return relInitypes;
	}
	/**
	 * @param relInitypes the relInitypes to set
	 */
	@SuppressWarnings("unchecked")
	public void setRelInitypes(List<Map> relInitypes) {
		this.relInitypes = relInitypes;
	}
	/**
	 * @return the sepcialEnd
	 */
	public String getSepcialEnd() {
		return sepcialEnd;
	}
	/**
	 * @param sepcialEnd the sepcialEnd to set
	 */
	public void setSepcialEnd(String sepcialEnd) {
		this.sepcialEnd = sepcialEnd;
	}
	/**
	 * @return the seClasssifys
	 */
	public List<Dictionary> getSeClasssifys() {
		return seClasssifys;
	}
	/**
	 * @param seClasssifys the seClasssifys to set
	 */
	public void setSeClasssifys(List<Dictionary> seClasssifys) {
		this.seClasssifys = seClasssifys;
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
