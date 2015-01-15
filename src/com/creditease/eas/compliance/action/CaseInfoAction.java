package com.creditease.eas.compliance.action;
import java.beans.MethodDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.compliance.bean.BaseType;
import com.creditease.eas.compliance.bean.CaseDetailType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.ChargeInfo;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Detailcasetype;
import com.creditease.eas.compliance.bean.Performance;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.compliance.bean.UpdateInfo;
import com.creditease.eas.compliance.service.ICaseInfoService;
import com.creditease.eas.compliance.service.IChargeService;
import com.creditease.eas.compliance.service.IPerformanceService;
import com.creditease.eas.util.Action;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.util.JsonUtil;
import com.creditease.eas.util.MethodDesAnnotation;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.UtilObjects;
import com.creditease.eas.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mchange.v2.lang.ObjectUtils;
/**
 * 案件
 * @author ygq
 * @version 1.0 13/11/16 22:20
 */
@Controller
@Scope("prototype")
public class CaseInfoAction extends BaseAction{
	/**日志**/
	private static Logger logger = Logger.getLogger(CaseInfoAction.class);
	//案件信息
	private Complain complainInfo;
	private List<DEPTINFO> deptinfo;
	/**货款品种类型**/
	private List<Dictionary> loanBreads;
	public List<DEPTINFO> getDeptinfo() {
		return deptinfo;
	}
	public void setDeptinfo(List<DEPTINFO> deptinfo) {
		this.deptinfo = deptinfo;
	}
	private Map mapCompliant;
	
	public Map getMapCompliant() {
		return mapCompliant;
	}
	public void setMapCompliant(Map mapCompliant) {
		this.mapCompliant = mapCompliant;
	}
	private String finicasetype;
	public String getFinicasetype() {
		return finicasetype;
	}
	public void setFinicasetype(String finicasetype) {
		this.finicasetype = finicasetype;
	}
	@Resource
	private  ICaseInfoService caseInfoService;
	public Complain getComplainInfo() {
		return complainInfo;
	}
	public void setComplainInfo(Complain complainInfo) {
		this.complainInfo = complainInfo;
	}
	public void setCaseInfoService(ICaseInfoService caseInfoService) {
		this.caseInfoService = caseInfoService;
	}
	private  List<RelInicasetype> relInicasetypes;
	
	public List<RelInicasetype> getRelInicasetypes() {
		return relInicasetypes;
	}
	public void setRelInicasetypes(List<RelInicasetype> relInicasetypes) {
		this.relInicasetypes = relInicasetypes;
	}
	private  List<Person> persons;
	private  List<Person> outPersons=new ArrayList<Person>();//内部人员
	private  List<Person> innPersons=new ArrayList<Person>();//外部人员
	
	public List<Person> getInnPersons() {
		return innPersons;
	}
	public void setInnPersons(List<Person> innPersons) {
		this.innPersons = innPersons;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public List<Person> getOutPersons() {
		return outPersons;
	}
	public void setOutPersons(List<Person> outPersons) {
		this.outPersons = outPersons;
	}
	/**
	 * 添加案件信息时用到
	 */
	private Integer fid;
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	/**
	 * 描述：加载客户来源
	 * 2013-10-14 下午03:28:43 by ygq
	 * @version
	 * @return
	 */
	public void findCussource(){
		try{
			String json = caseInfoService.findCussource();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}
	
	
	/**
	 * 描述：加载服务类型
	 * 2013-10-14 下午03:28:43 by ygq
	 * @version
	 * @return
	 */
	public void findServicetype(){
		try{
			String json = caseInfoService.findServicetype();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 导出
	 */
	public void exportExcel(){
		try {
			caseInfoService.exportExcel(request,response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 描述：加载客户状态
	 * 2013-10-14 下午03:28:43 by ygq
	 * @version
	 * @return
	 */
	public void findCusstatus(){
		try{
			String json = caseInfoService.findCusstatus(complainInfo.getFservicetypeid());
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 缓存ComlainInfo对象的JSON串
	 */
	private String jsonComplainInfo;
	
	public String getJsonComplainInfo() {
		return jsonComplainInfo;
	}
	public void setJsonComplainInfo(String jsonComplainInfo) {
		this.jsonComplainInfo = jsonComplainInfo;
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
	 * 描述：添加投诉信息
	 * 2013-10-8 下午12:00:55 by gqy
	 * @version
	 * @return
	 */
	public String addCompliant(){
//		int fid;
		try {
			JsonUtil<Complain> jsonUtil=new JsonUtil<Complain>();
			jsonComplainInfo=jsonUtil.fromObjToJson(complainInfo);
			
			newCaseType=caseInfoService.getNewCaseType();
			
//			fid = caseInfoService.addCompliant(complainInfo,getHttpSession());
//			request.setAttribute("fid", fid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addCompliant";
	}
	/**
	 * 描述：添加案件基本信息
	 * 2013-10-8 下午12:00:55 by gqy
	 * @version
	 * @return
	 */
	public String addCaseInfo(){
		try{
			//System.out.println(jsonComplainInfo);
			//JsonUtil<Complain> jsonUtil=new JsonUtil<Complain>();
			//Complain fromComplain= jsonUtil.fromJsonToObj(jsonComplainInfo);
			//complainInfo=caseInfoService.getTargetComplain(jsonComplainInfo, complainInfo);
			caseInfoService.addCaseInfo(jsonComplainInfo,complainInfo,request,getHttpSession());
//			this.json = fid > 0 ?"{\"success\":\"true\"}":"{\"success\":\"false\"}";
		}catch(Exception ex){
			ex.printStackTrace();
//			this.json = "{\"success\":\"false\"}";
		}
		return "addCaseInfo";
	}
	/**
	 * 描述：添加案件基本信息
	 * 2013-10-8 下午12:00:55 by gqy
	 * @version
	 * @return
	 */
	public String updateCaseInfo(){
		try{
			int fid = caseInfoService.updateCaseInfo(complainInfo,request,getHttpSession());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "addCaseInfo";
	}
	/**
	 * 描述：查询案件的列表信息
	 * 2013-10-11 下午05:14:03 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		try {
			this.pagination = caseInfoService.queryPage(pagination,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/*******************************addCase 相关的信息*********************************************************************************************/
	/**
	 * 描述：加载投诉渠道
	 * 2013-10-14 下午03:28:43 by ygq
	 * @version
	 * @return
	 */
	public void findDitch(){
		try{
			String json = caseInfoService.findDitch();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 描述：证据类型
	 * 2013-10-14 下午03:28:43 by ygq
	 * @version
	 * @return
	 */
	public void findEvidenceType(){
		
		try{
			String json = caseInfoService.findEvidenceType();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void findCaseFrom(){
		try{
			String json = caseInfoService.findCaseFromType();
			writerJsonToClient(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	    * 
	    * 描述：查询部门信息
	    * 2013-10-8 下午05:00:45 by sunxiaofeng
	    * @version
	    * @param costcenter
	 * @throws Exception 
	    */
	public String selectAdmin(){
		String fdeptname = request.getParameter("fdeptname");
		try {
			this.pagination = caseInfoService.selectAdmin(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	/**
	 * 描述：查询部门信息
	 * 2014-1-15 上午09:56:59 by caoyong
	 * @version
	 * @return
	 */
	public void getOrgData(){
		try {
//			response.getWriter().print(GsonBuilder.class.newInstance().create().toJson(caseInfoService.getOrgData()));
			this.writerJsonToClient(GsonBuilder.class.newInstance().create().toJson(caseInfoService.getOrgData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询案件初步分类
	 * @return
	 */
	public String findInicaseType(){
		try {
			String json = caseInfoService.findInicaseType();
			//System.out.println(json);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	public String findAllCaseType(){
		try {
			String json=caseInfoService.getAllCaseType();
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 查询案件初步分类
	 * @return
	 */
	public String findDetailCaseType(){
		try {
			Integer intFinicasetype = StringUtil.strToInt(finicasetype);
			String json = caseInfoService.findDetailCaseType(intFinicasetype);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 根据被投诉人的姓名查询这个人被投诉的次数
	 * @return
	 */
	public String findPersonCompCount(){
		try {
			Integer strjson = caseInfoService.findPersonCompCount(request);
			Gson gson = new Gson();
			String json = gson.toJson(strjson);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	/**
	 * 根据被投诉人的姓名查询这个人被投诉的次数
	 * @return
	 */
	public String findPersonCompCountExceptSelf(){
		try {
			Integer strjson = caseInfoService.findPersonCompCountExceptSelf(request);
			Gson gson = new Gson();
			String json = gson.toJson(strjson);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：根据手机号查询该手机号被投诉次数
	 * 2013-11-12 下午03:08:29 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String findFmobileCount(){
		try {
			String strjson = caseInfoService.findFmobileCount(request);
			Gson gson = new Gson();
			String json = gson.toJson(strjson);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：根据QQ号查询该QQ号被投诉次数
	 * 2013-11-12 下午03:47:19 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String findFqqCount(){
		try {
			Integer strjson = caseInfoService.findFqqCount(request);
			Gson gson = new Gson();
			String json = gson.toJson(strjson);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：根据客户姓名查询该客户被投诉次数
	 * 2013-11-13 下午02:17:28 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String findFcusNameCount(){
		try {
			Integer strjson = caseInfoService.findFcusNameCount(request);
			Gson gson = new Gson();
			String json = gson.toJson(strjson);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	/**
	 * 根据被投诉人的姓名查询涉及到的案子
	 * @return
	 */
	public String findPersonRelComplian(){
		try {
			String json = caseInfoService.findPersonRelComplian(request);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	/**
	 * 根据被投诉人的姓名查询涉及到的案子(除自身)
	 * @return
	 */
	public String findPersonRelComplianExceptSelf(){
		try {
			String json = caseInfoService.findPersonRelComplianExcepSelf(request);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	@Resource
	private IChargeService chargeService;
	@Resource
	private IPerformanceService performanceService;
	
	private List<ChargeInfo> chargeInfos;
	
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
	/**
	 * 根据id查看案件信息
	 * 描述：
	 * 2013-10-23 上午10:24:06 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String findCase(){
		complainInfo=caseInfoService.findCase(request.getParameter("id"));
		try {
			newCaseType=caseInfoService.getNewCaseType();
			//查询关联的初步分类和详细分类的信息
			relInicasetypes = caseInfoService.selectInicaseTypeByComplainId(Integer.parseInt(request.getParameter("id")));
			
			//设置新分类是否选中
			caseInfoService.setCaseTypeIsChecked(newCaseType, relInicasetypes);
			
			//查询案件信息关联的部门信息
		    deptinfo=caseInfoService.selectDeptInfoByComplainId(Integer.parseInt(request.getParameter("id")));
			
		    //查询案件关联的人员信息
			persons = caseInfoService.selectPersonByComplainId(Integer.parseInt(request.getParameter("id")));
			
			//李如意 查询案件关联的收费信息
			chargeInfos=chargeService.getChargeInfosByComplainId(Integer.parseInt(request.getParameter("id")));
			performances=performanceService.FindPerformanceInfoByComplainId(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("innPersonSize", 1);
			request.setAttribute("outPersonSize", 1);
			request.setAttribute("deptSize", 1);
			request.setAttribute("chargeSize", 1);
			request.setAttribute("performanceSize", 1);
			if(null != persons && !persons.isEmpty()){
				for (int i = 0; i <persons.size(); i++) {
					if(!"1".equals(persons.get(i).getFisinner())){
						outPersons.add(persons.get(i));
					}else{
						innPersons.add(persons.get(i));
						}
				}
				request.setAttribute("innPersonSize", innPersons.size());
				request.setAttribute("outPersonSize", outPersons.size());
			}
			/**
			 * sunxiaofeng
			 */
			if(null !=deptinfo && !deptinfo.isEmpty())
			request.setAttribute("deptSize", deptinfo.size());
			if(chargeInfos!=null && !chargeInfos.isEmpty()){
				request.setAttribute("chargeSize", chargeInfos.size());
			}
			if(performances!=null&&!performances.isEmpty()){
				request.setAttribute("performanceSize",performances.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editCase";
	}
	/**
	 * 根据id查看案件信息
	 * 描述：
	 * 2013-10-23 上午10:24:06 by sunxiaofeng
	 * @version
	 * @return
	 */
	public String findCompliant(){
		try {
			this.getAllDictionarys();
			complainInfo=caseInfoService.findCase(request.getParameter("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editCompliant";
	}
	
	private List<UpdateInfo> udpateInfos;
	/**
	 * 添加更新的字段  李如意
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static List<UpdateInfo> getUpdateInfos(Complain oldObj,Complain newObj) throws IllegalArgumentException, IllegalAccessException{
		List<UpdateInfo> list=new ArrayList<UpdateInfo>();
		Class<?> objClass=oldObj.getClass();
		Field[] fields=objClass.getDeclaredFields();
		if(fields!=null &&fields.length>0){
			for (int i = 0; i < fields.length; i++) {
				Field field=fields[i];
				field.setAccessible(true);
				if(field.isAnnotationPresent(MethodDesAnnotation.class)){
					String fieldName=field.getName();
					MethodDesAnnotation methodAnnotation=field.getAnnotation(MethodDesAnnotation.class);
					Object obj1=field.get(oldObj);
					Object obj2=field.get(newObj);
					
					
					UpdateInfo ui=UtilObjects.setUpdateInfo(obj1, obj2);
					if(ui!=null){
						ui=new UpdateInfo();
						ui.setFieldName(fieldName);
						ui.setFieldDescription(methodAnnotation.description());
						list.add(ui);
					}
					
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		Complain c1=new Complain();
		c1.setFidcard("fidcard1");
		c1.setFiscustomer("fiscustomer1");
		c1.setFcusName("fcusName1");
		c1.setFcomplainanter("fcomplainanter1");
		
		Complain c2=new Complain();
		c2.setFidcard("fidcard2");
		c2.setFiscustomer("fiscustomer2");
		c2.setFcusName("fcusName2");
		c2.setFcomplainanter("fcomplainanter2");
		
		
		try {
			List<UpdateInfo> list= getUpdateInfos(c1,c2);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 描述：编辑案件信息
	 * 2013-10-23 下午08:21:49 by sunxiaofeng
	 * @version
	 * @return
	 * @throws ParseException 
	 */
	private String compareJSON;
	
	
	public String getCompareJSON() {
		return compareJSON;
	}
	public void setCompareJSON(String compareJSON) {
		this.compareJSON = compareJSON;
	}
	//@Action(description="更新了投诉人为${complainInfo.fcomplainanter}的案子",updateDescription="udpateInfos")
	public String updateCompliant() throws Exception{
		/**
		 * 添加更新的字段  李如意
		 */
		/*Complain oldC=caseInfoService.findCase(complainInfo.getFid().toString());
		udpateInfos=getUpdateInfos(oldC, complainInfo);*/
		
		//String compareJSON=request.getParameter("compareJSON");
		/*if(compareJSON!=null&&!"".equals(compareJSON)){
			Type listType = new TypeToken<List<UpdateInfo>>() {}.getType();
			Gson g = new GsonBuilder().create();
			udpateInfos=g.fromJson(compareJSON, listType);	
		}*/
		
		
		
		User user = (User)request.getSession().getAttribute("user");
		complainInfo.setFlastupdatetime(new Date());
		if(null != user){
			complainInfo.setFupdateuserid(user.getId().intValue());
		}
		
		caseInfoService.updateCompliant(complainInfo);
		return "updateCompliant";
	}
	/***
	 * 查看案件信息
	 * @return
	 * @throws Exception 
	 */
	//@Action(description="查看投诉人为${mapCompliant.FCOMPLAINANT}的案子") 
	public String viewCaseInfo() throws Exception{
		//查询案件信息
	   mapCompliant= caseInfoService.findCaseById(request.getParameter("fid"));
	   mapCompliant=Utils.convertClob2String(mapCompliant);
		try {
			newCaseType=caseInfoService.getNewCaseType();
			this.getAllDictionarys();
			relInicasetypes = caseInfoService.selectInicaseTypeByComplainId(Integer.parseInt(request.getParameter("fid")));
			
			//设置被选中的新分类
			caseInfoService.setCaseTypeIsChecked(newCaseType, relInicasetypes);
			//查询案件信息关联的部门信息
			 deptinfo=caseInfoService.selectDeptInfoByComplainId(Integer.parseInt(request.getParameter("fid")));
			//查询案件关联的人员信息
			persons = caseInfoService.selectPersonByComplainId(Integer.parseInt(request.getParameter("fid")));
			
			chargeInfos=chargeService.getChargeInfosByComplainId(Integer.parseInt(request.getParameter("fid")));
			performances=performanceService.FindPerformanceInfoByComplainId(Integer.parseInt(request.getParameter("fid")));
			
			if(null != persons && !persons.isEmpty()){
				for (int i = 0; i <persons.size(); i++) {
					if(!"1".equals(persons.get(i).getFisinner())){
						outPersons.add(persons.get(i));
					}else{
						innPersons.add(persons.get(i));
					}
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewCaseInfo";
	}
	/**
	 * 
	 * 描述：查询所有的下拉列表集合
	 * 2013-10-9 下午06:22:55 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void getAllDictionarys() throws Exception{
		this.loanBreads = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.loanBread);
		response.getWriter().print(GsonBuilder.class.newInstance().create().toJson(this.loanBreads));
	}
	/**
	 * @return the loanBreads
	 */
	public List<Dictionary> getLoanBreads() {
		return loanBreads;
	}
	/**
	 * @param loanBreads the loanBreads to set
	 */
	public void setLoanBreads(List<Dictionary> loanBreads) {
		this.loanBreads = loanBreads;
	}
}
