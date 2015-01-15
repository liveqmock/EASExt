package com.creditease.eas.compliance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.compliance.bean.BaseType;
import com.creditease.eas.compliance.bean.CaseDetailType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.ChargeInfo;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.compliance.bean.Investigation;
import com.creditease.eas.compliance.bean.Performance;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.compliance.service.IChargeService;
import com.creditease.eas.compliance.service.IFeedbackRequiredService;
import com.creditease.eas.compliance.service.IPerformanceService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
import com.google.gson.GsonBuilder;
/**
 * @InvestigationAction.java	合规初步调查action
 * created at 2013-10-7 下午04:00:10 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class InvestigationAction extends BaseAction{
	/**序列化**/
	private static final long serialVersionUID = 1L;
	/**要自动装配的service接口**/
	@Autowired
	private InvestigationService investigationServiceImpl;
	/**初步调查记录bean**/
	private Investigation investigation;
	/**日志**/
	private static Logger logger = Logger.getLogger(InvestigationAction.class);
//	/**调查方式下拉列表集合**/
	private List<Dictionary> typeIds;
	/**案件初步分类下拉列表集合**/
	private List<Dictionary> iniCaseTypes;
	/**客户来源下拉列表集合**/
	private List<Dictionary> cusSources;
	/**下一步方案下拉列表集合**/
	private List<Dictionary> nextSchemeIds;
	/**父ID**/
	private Integer parentId;
	/**父记录bean**/
	private Investigation parentInvestigation;
	/**初步调查集合（多次继续调查时关联多个调查）**/
	private List<Investigation> investigations;
	
	/**关联的案件信息**/
	private Complain complain;
	/**关联的转部门要求反馈表信息**/
	private List<IFeedbackRequired> feedbackRequireds;
	/**关联的转部门/本部门实地调查 反馈信息**/
	private List<IFeedback> feedbacks;
	/**关联的转部门反馈结果表信息**/
	private List<IFeedbackResult> feedbackResults;
	/**关联的本部门实地调查信息**/
	private List<Fieldsurvey> fieldsurveys;
	private List<BaseType> baseTypes;
	/**关联的再次协助反馈结果表信息**/
	private List<ZcxzlsResult> zcxzlsfbResults;
	/**关联的申请结案信息**/
	private List<Applysettlement> applysettlements;
	/**审批结果信息**/
	private List<AuditResult> auditResults;
	/**关联的案件初步分类和详细分类**/
	private List<Map<String,Integer>> complainTypes;
	/**关联的案件被投诉人员集合**/
	private List<Person> compalinPersons;
	/**关联的案件被投诉部门集合**/
	private List<DEPTINFO> deptinfos;
	private Person person;
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
			this.pagination = investigationServiceImpl.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	
	public void exportExcel(){
		try {
			investigationServiceImpl.exportExcel(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * ：描述：查询修改邮箱列表页数据
	 */
	
	public String queryPageJsonForEamil(){
		try {
			this.pagination = investigationServiceImpl.queryPageForEmail(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	public String queryPageJsonForCallBack() throws Exception{
		this.pagination = investigationServiceImpl.queryPageForCallBack(pagination);
		return "queryPageJson";
	}
	
	@Autowired
	private IChargeService chargeService;
	@Autowired
	private IPerformanceService performanceService;
	public void setChargeService(IChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
	private List<ChargeInfo> chargeInfos;
	public void setPerformanceService(IPerformanceService performanceService) {
		this.performanceService = performanceService;
	}
	private List<Performance> performances;
	public List<ChargeInfo> getChargeInfos() {
		return chargeInfos;
	}
	public void setChargeInfos(List<ChargeInfo> chargeInfos) {
		this.chargeInfos = chargeInfos;
	}
	
	
	public List<Performance> getPerformances() {
		return performances;
	}
	public void setPerformances(List<Performance> performances) {
		this.performances = performances;
	}
	private List<Dictionary> loanBreads;
	public List<Dictionary> getLoanBreads() {
		return loanBreads;
	}
	public void setLoanBreads(List<Dictionary> loanBreads) {
		this.loanBreads = loanBreads;
	}
	private int returnedCount;
	public int getReturnedCount() {
		return returnedCount;
	}
	public void setReturnedCount(int returnedCount) {
		this.returnedCount = returnedCount;
	}
	
	/**
	 * 计算案件被退回次数
	 * @param auditResults
	 * @return
	 */
	private int calculateReturnedCount(List<AuditResult> auditResults){
		int returnedCount=0;
		if(auditResults!=null && auditResults.size()>0){
			for (int i = 0; i < auditResults.size(); i++) {
				AuditResult a=auditResults.get(i);
				if(a.getIsAgree()==1){
					returnedCount++;
				}
			}
		}
		return returnedCount;
	}
	/**
	 * 根据 investigationId修改协助负责人信息
	 * @throws Exception
	 */
	
	public void updateByinvestigation() throws Exception{
		try {
			
			investigationServiceImpl.updateByinvestigation(feedbackRequired);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	 
	
	}
	
	
	/**
	 * 新分类
	 */
	List<CaseType> newCaseType;
	public List<CaseType> getNewCaseType() {
		return newCaseType;
	}
	public void setNewCaseType(List<CaseType> newCaseType) {
		this.newCaseType = newCaseType;
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
			complain = investigationServiceImpl.getComplainByInvestigationId(investigation.getId());
			complainTypes = investigationServiceImpl.getComplainTypesByInvestigationId(investigation.getId());
			baseTypes=investigationServiceImpl.getBaseTypesByInvestigationId(investigation.getId());//初步分类
			
			
			
			newCaseType=investigationServiceImpl.getNewCaseType(investigation.getId());
			
			deptinfos = investigationServiceImpl.getDeptinfosByInvestigationId(investigation.getId());
			compalinPersons = investigationServiceImpl.getCompalinPersonsByInvestigationId(investigation.getId());
			investigations = investigationServiceImpl.getChildInvestigations(investigation.getId());
			feedbackRequireds = investigationServiceImpl.getFeedbackRequiredByInvestigationId(investigation.getId());
			feedbacks = investigationServiceImpl.getFeedbackByInvestigationId(investigation.getId());
			feedbackResults = investigationServiceImpl.getFeedbackResultByInvestigationId(investigation.getId());
			fieldsurveys = investigationServiceImpl.getFieldsurveyByInvestigationId(investigation.getId());
			zcxzlsfbResults = investigationServiceImpl.getZcxzlsfbResultByInvestigationId(investigation.getId());
			applysettlements = investigationServiceImpl.getApplysettlementByInvestigationId(investigation.getId());
			auditResults = investigationServiceImpl.getAuditResultsByInvestigationId(investigation.getId());
			chargeInfos=chargeService.getChargeInfosByComplainId(complain.getFid());
			performances=performanceService.FindPerformanceInfoByComplainId(complain.getFid());
			returnedCount=calculateReturnedCount(auditResults);//计算被退回次数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	public String viewPerson(){
		try {
			person = investigationServiceImpl.getPersonById(person.getFid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewPerson";
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
		List<Map> list = investigationServiceImpl.getTypeIds();
		this.typeIds = DictionaryUtil.getDictionarys(list);
		list = investigationServiceImpl.getNextSchemeIds();
		this.nextSchemeIds = DictionaryUtil.getDictionarys(list);
		this.loanBreads = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.loanBread);
	}
	/**
	 * 
	 * 描述：查找案件初步分类下拉列表集合
	 * 2013-10-9 下午06:22:55 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getIniCaseTypeDictionary() throws Exception{
		List<Map> list = investigationServiceImpl.getIniCaseTypes();
		this.iniCaseTypes = DictionaryUtil.getDictionarys(list);
		this.writerJsonToClient(GsonBuilder.class.newInstance().create().toJson(this.iniCaseTypes));
	}
	
	/**
	 * 案件状态
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getInvestigationStateDictionary() throws Exception{
		List<Map> list = investigationServiceImpl.getInvestigationState();
		this.iniCaseTypes = DictionaryUtil.getDictionarys(list);
		this.writerJsonToClient(GsonBuilder.class.newInstance().create().toJson(this.iniCaseTypes));
	}
	
	
	/**
	 * 
	 * 描述：客户来源下拉列表集合
	 * 2013-10-9 下午06:22:55 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getCusSourceDictionary() throws Exception{
		List<Map> list = investigationServiceImpl.getCusSources();
		this.cusSources = DictionaryUtil.getDictionarys(list);
		this.writerJsonToClient(GsonBuilder.class.newInstance().create().toJson(this.cusSources));
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
			investigationServiceImpl.delete(investigation.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delete";
	}
	/**
	 * 描述：根据investigationId查询列表
	 */
	
	private IFeedbackRequired feedbackRequired;
	//gaoyali
	@Autowired
	private IFeedbackRequiredService iFeedbackRequiredServiceImpl;
	 public String findFeedbackRequire() throws Exception{
		 int investigationid=Integer.parseInt(request.getParameter("investigationId"));
		 feedbackRequired = iFeedbackRequiredServiceImpl.getFeedbackRequiredByInvestigationId(investigationid);
		return "findFeedbackRequire";
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
			if("true".equals(request.getParameter("returnBack"))) returnBack = "true";
			if(request.getParameter("parentId")!=null){
				this.parentId = Integer.parseInt(request.getParameter("parentId"));//父id
				this.complain = investigationServiceImpl.getComplainByInvestigationId(this.parentId);
				this.investigations = investigationServiceImpl.getChildInvestigations(parentId);
				
				if(investigations!=null&&investigations.size()>0){
					for (int i = 0; i < investigations.size(); i++) {
						Investigation invest = investigations.get(i);
						String tmp=invest.getContent();
						if(tmp!=null&&!"".equals(tmp)){
							tmp=tmp.replaceAll("\n", "<br>");
							tmp=tmp.replaceAll("\r\n", "<br>");
							invest.setContent(tmp);
						}
					}
				}
				this.parentInvestigation = investigationServiceImpl.getInvestigationById(parentId);
			}
			if(investigation!=null){
//				this.investigations = investigationServiceImpl.getChildInvestigations(investigation.getId());
//				if(investigations.size()>1){
//					this.parentInvestigation = investigationServiceImpl.getInvestigationById(investigation.getId());
//					for(int i=0;i<investigations.size();i++){
//						if(investigations.get(i).getNextSchemeId()==2||investigations.get(i).getNextSchemeId()==3){
//							investigation = investigations.get(i);break;
//						}
//					}
//				}
				investigation = investigationServiceImpl.getInvestigationById(investigation.getId());
				this.complain = investigationServiceImpl.getComplainByInvestigationId(investigation.getId());
			} 
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
	@SuppressWarnings("unchecked")
	public void update() throws Exception{
		try {
			investigation.setLastUpdaterId(this.findUser().getId().intValue());
			investigationServiceImpl.update(investigation);
			Map map = new HashMap();
			map.put("id", investigation.getParentId()!=null?investigation.getParentId():investigation.getId());
			map.put("statusId", investigation.getNextSchemeId()==2?3:8);//是时审批通过修改案件状态为"已结案"（17）
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
	 * 
	 * 描述：新增记录
	 * 2013-9-16 上午11:33:59 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert() throws Exception{
		try {
			investigation.setCreaterId(this.findUser().getId().intValue());
			this.handleStatus(investigation);
			investigationServiceImpl.insert(investigation);//插入操作时会初始化案件调查状态
			this.parentInvestigation = investigationServiceImpl.getInvestigationById(investigation.getParentId());
			if(this.parentInvestigation!=null && this.parentInvestigation.getNextSchemeId()!=4 
					&& this.parentInvestigation.getNextSchemeId()!=5){
				this.handleStatus(parentInvestigation);
				Map map = new HashMap();
				map.put("statusId", parentInvestigation.getStatusId());
				map.put("id", parentInvestigation.getId());
				investigationServiceImpl.updateStatus(map);//更新父案件状态
				boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
				logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
			}else if(investigation != null && investigation.getNextSchemeId()!=4 && investigation.getNextSchemeId()!=5){
				Map map = new HashMap();
				map.put("statusId", investigation.getStatusId());
				map.put("id", investigation.getId());
				boolean sendSuccess = investigationServiceImpl.sendEmail(map);//发送案件进度邮件
				logger.info(sendSuccess?"给案件负责人发送邮件成功！":"给案件负责人发送邮件失败！");
			}
			this.json = "{\"success\":\"true\""+",\"investigationId\":\""+investigation.getId()+"\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	
	public void insertInTruning() throws Exception{
		try {
			investigation.setCreaterId(this.findUser().getId().intValue());
			investigationServiceImpl.insert(investigation);//插入操作时会初始化案件调查状态
			this.json = "{\"success\":\"true\""+",\"investigationId\":\""+investigation.getId()+"\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	public String addInvestInturning(){
		try {
			this.getAllDictionarys();
			if(request.getParameter("parentId")!=null){
				this.parentId = Integer.parseInt(request.getParameter("parentId"));//父id
				this.complain = investigationServiceImpl.getComplainByInvestigationId(this.parentId);
				this.investigations = investigationServiceImpl.getChildInvestigations(parentId);
				this.parentInvestigation = investigationServiceImpl.getInvestigationById(parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return "addInvestInturning";
	}
	

	
	/**
	 * 
	 * 描述：终审未通过时点击继续操作时更新调查状态为”待结案初审13“流转到初审人员（张强）待办列表
	 * 2013-9-16 上午11:33:59 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateStatus() throws Exception{
		try {
			Map map = new HashMap();
			map.put("statusId", Integer.parseInt(request.getParameter("statusId"))==16?13:2);
			map.put("id", request.getParameter("investigationId"));
			investigationServiceImpl.updateStatus(map);//更新状态
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
	 * 
	 * 描述：选择下一步时级联更新Entity的案件状态
	 * 2013-10-11 上午11:03:18 by caoyong
	 * @version
	 * @throws Exception
	 */
	private void handleStatus(Investigation investigation) throws Exception{
		int nextSchemeId = this.investigation.getNextSchemeId();//当前新增了记录的下一步id
		if(nextSchemeId==1) investigation.setStatusId(2);//继续调查
		else if(nextSchemeId==2) investigation.setStatusId(3);//被投诉部门调查处理待审批
		else if(nextSchemeId==3) investigation.setStatusId(8);//本部门实地调查待审批
//		else if(nextSchemeId==4) investigation.setStatusId(13);//待结案初审
//		else if(nextSchemeId==5) investigation.setStatusId(13);//待结案初审(特殊结案时走正常流程）
	}
	/**
	 * 
	 * 描述：展示文件信息列表
	 * 2013-10-29 上午10:40:58 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String queryInvestFile(){
		try {
			this.pagination = investigationServiceImpl.queryInvestFile(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryInvestFile";
	}
	/**
	 * @return the investigation
	 */
	public Investigation getInvestigation() {
		return investigation;
	}
	/**
	 * @param investigation the investigation to set
	 */
	public void setInvestigation(Investigation investigation) {
		this.investigation = investigation;
	}
	/**
	 * @return the typeIds
	 */
	public List<Dictionary> getTypeIds() {
		return typeIds;
	}
	/**
	 * @param typeIds the typeIds to set
	 */
	public void setTypeIds(List<Dictionary> typeIds) {
		this.typeIds = typeIds;
	}
	/**
	 * @return the nextSchemeIds
	 */
	public List<Dictionary> getNextSchemeIds() {
		return nextSchemeIds;
	}
	/**
	 * @param nextSchemeIds the nextSchemeIds to set
	 */
	public void setNextSchemeIds(List<Dictionary> nextSchemeIds) {
		this.nextSchemeIds = nextSchemeIds;
	}
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the investigations
	 */
	public List<Investigation> getInvestigations() {
		return investigations;
	}
	/**
	 * @param investigations the investigations to set
	 */
	public void setInvestigations(List<Investigation> investigations) {
		this.investigations = investigations;
	}
	/**
	 * @return the parentInvestigation
	 */
	public Investigation getParentInvestigation() {
		return parentInvestigation;
	}
	/**
	 * @param parentInvestigation the parentInvestigation to set
	 */
	public void setParentInvestigation(Investigation parentInvestigation) {
		this.parentInvestigation = parentInvestigation;
	}
	/**
	 * @return the iniCaseTypes
	 */
	public List<Dictionary> getIniCaseTypes() {
		return iniCaseTypes;
	}
	/**
	 * @param iniCaseTypes the iniCaseTypes to set
	 */
	public void setIniCaseTypes(List<Dictionary> iniCaseTypes) {
		this.iniCaseTypes = iniCaseTypes;
	}
	/**
	 * @return the cusSources
	 */
	public List<Dictionary> getCusSources() {
		return cusSources;
	}
	/**
	 * @param cusSources the cusSources to set
	 */
	public void setCusSources(List<Dictionary> cusSources) {
		this.cusSources = cusSources;
	}
	/**
	 * @return the complain
	 */
	public Complain getComplain() {
		return complain;
	}
	/**
	 * @param complain the complain to set
	 */
	public void setComplain(Complain complain) {
		this.complain = complain;
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
	 * @return the zcxzlsfbResults
	 */
	public List<ZcxzlsResult> getZcxzlsfbResults() {
		return zcxzlsfbResults;
	}
	/**
	 * @param zcxzlsfbResults the zcxzlsfbResults to set
	 */
	public void setZcxzlsfbResults(List<ZcxzlsResult> zcxzlsfbResults) {
		this.zcxzlsfbResults = zcxzlsfbResults;
	}
	/**
	 * @return the auditResults
	 */
	public List<AuditResult> getAuditResults() {
		return auditResults;
	}
	/**
	 * @param auditResults the auditResults to set
	 */
	public void setAuditResults(List<AuditResult> auditResults) {
		this.auditResults = auditResults;
	}
	/**
	 * @return the feedbackRequireds
	 */
	public List<IFeedbackRequired> getFeedbackRequireds() {
		return feedbackRequireds;
	}
	/**
	 * @param feedbackRequireds the feedbackRequireds to set
	 */
	public void setFeedbackRequireds(List<IFeedbackRequired> feedbackRequireds) {
		this.feedbackRequireds = feedbackRequireds;
	}
	/**
	 * @return the feedbackResults
	 */
	public List<IFeedbackResult> getFeedbackResults() {
		return feedbackResults;
	}
	/**
	 * @param feedbackResults the feedbackResults to set
	 */
	public void setFeedbackResults(List<IFeedbackResult> feedbackResults) {
		this.feedbackResults = feedbackResults;
	}
	/**
	 * @return the fieldsurveys
	 */
	public List<Fieldsurvey> getFieldsurveys() {
		return fieldsurveys;
	}
	/**
	 * @param fieldsurveys the fieldsurveys to set
	 */
	public void setFieldsurveys(List<Fieldsurvey> fieldsurveys) {
		this.fieldsurveys = fieldsurveys;
	}
	/**
	 * @return the applysettlements
	 */
	public List<Applysettlement> getApplysettlements() {
		return applysettlements;
	}
	/**
	 * @param applysettlements the applysettlements to set
	 */
	public void setApplysettlements(List<Applysettlement> applysettlements) {
		this.applysettlements = applysettlements;
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
	/**
	 * @return the complainTypes
	 */
	public List<Map<String, Integer>> getComplainTypes() {
		return complainTypes;
	}
	/**
	 * @param complainTypes the complainTypes to set
	 */
	public void setComplainTypes(List<Map<String, Integer>> complainTypes) {
		this.complainTypes = complainTypes;
	}
	/**
	 * @return the compalinPersons
	 */
	public List<Person> getCompalinPersons() {
		return compalinPersons;
	}
	/**
	 * @param compalinPersons the compalinPersons to set
	 */
	public void setCompalinPersons(List<Person> compalinPersons) {
		this.compalinPersons = compalinPersons;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the deptinfos
	 */
	public List<DEPTINFO> getDeptinfos() {
		return deptinfos;
	}
	/**
	 * @param deptinfos the deptinfos to set
	 */
	public void setDeptinfos(List<DEPTINFO> deptinfos) {
		this.deptinfos = deptinfos;
	}
	public IFeedbackRequired getFeedbackRequired() {
		return feedbackRequired;
	}
	public void setFeedbackRequired(IFeedbackRequired feedbackRequired) {
		this.feedbackRequired = feedbackRequired;
	}
	public List<BaseType> getBaseTypes() {
		return baseTypes;
	}
	public void setBaseTypes(List<BaseType> baseTypes) {
		this.baseTypes = baseTypes;
	}
	
}
