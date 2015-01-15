package com.creditease.eas.compliance.service.impl;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.EmailConfigMapper;
import com.creditease.eas.compliance.bean.CaseDetailType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.ChargeInfo;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Performance;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.compliance.bean.excel.CaseDetailTypeForExcel;
import com.creditease.eas.compliance.bean.excel.CaseTypeForExcel;
import com.creditease.eas.compliance.bean.excel.ChargeInfoForExcel;
import com.creditease.eas.compliance.bean.excel.ComplainForExcel;
import com.creditease.eas.compliance.bean.excel.DepartForExcel;
import com.creditease.eas.compliance.bean.excel.PersonForExcel;
import com.creditease.eas.compliance.dao.ChargeInfoMapper;
import com.creditease.eas.compliance.dao.ComplainCommonMapper;
import com.creditease.eas.compliance.dao.ComplainMapper;
import com.creditease.eas.compliance.dao.DEPTINFOMapper;
import com.creditease.eas.compliance.dao.PerformanceMapper;
import com.creditease.eas.compliance.dao.PersonMapper;
import com.creditease.eas.compliance.dao.RelInicasetypeMapper;
import com.creditease.eas.compliance.kingdee.query.CompAdminQuery;
import com.creditease.eas.compliance.service.ICaseInfoService;
import com.creditease.eas.compliance.service.IPerformanceService;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.JsonUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.excel.ChargeDictionary;
import com.creditease.eas.util.excel.LoanBreadDictionary;
import com.creditease.eas.util.excel.POIUtil;
import com.google.gson.reflect.TypeToken;
@Service
public class CaseInfoServiceImpl implements ICaseInfoService{
	private CompAdminQuery query = new CompAdminQuery();
	@Resource
	private ComplainMapper complainMapper;
	public void setComplainMapper(ComplainMapper complainMapper) {
		this.complainMapper = complainMapper;
	}
	@Resource
	private PerformanceMapper performanceMapper;
	public void setPerformanceMapper(PerformanceMapper performanceMapper) {
		this.performanceMapper = performanceMapper;
	}
	@Resource
	private DEPTINFOMapper deptInfoMapper;
	public void setDeptInfoMapper(DEPTINFOMapper deptInfoMapper) {
		this.deptInfoMapper = deptInfoMapper;
	}
	@Resource
	private ComplainCommonMapper commonMapper;
	public void setCommonMapper(ComplainCommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}
	@Resource
	private RelInicasetypeMapper relInicasetypeMapper;
	public void setRelInicasetypeMapper(RelInicasetypeMapper relInicasetypeMapper) {
		this.relInicasetypeMapper = relInicasetypeMapper;
	}
	@Resource
	private PersonMapper personMapper;
	public void setPersonMapper(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}
	
	@Autowired
	private ChargeInfoMapper chargeInfoMapper;
	public void setChargeInfoMapper(ChargeInfoMapper chargeInfoMapper) {
		this.chargeInfoMapper = chargeInfoMapper;
	}
	@Autowired
	private IPerformanceService performanceService;
	public void setPerformanceService(IPerformanceService performanceService) {
		this.performanceService = performanceService;
	}
	@Override
	public int addCompliant(Complain complian,HttpSession session) throws Exception{
		complian.setFtempstatus(2);//设置暂存状态，暂存状态的数据暂时不保存
		complian.setFcreatetime(new Date());
		complian.setFlastupdatetime(new Date());
		User user = (User)session.getAttribute("user");
		if(null != user){
			complian.setFcreateuserid(user.getId().intValue());
			complian.setFupdateuserid(user.getId().intValue());
		}
		int record = complainMapper.insert(complian);
		if(record>0){
			return complian.getFid();
		}else{
			return -1;
		}
	}
	/***
	 * 更新案件的信息，供添加和修改时使用
	 * @param complian
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private int editCaseInfo(Complain complian,HttpServletRequest request,HttpSession session,boolean isUpdate) throws Exception{
		int record =0;
		String[] finicasetypes = request.getParameterValues("finicasetype");
		//获取选中的初步分类信息,添加案件，案件初步分类的，详细分类的中间表
		String typeName="";
		if(null != finicasetypes){
			for(int i=0;i<finicasetypes.length;i++){
//				String fdetailCaseType = request.getParameter("fdetailCaseType" + (i+1));
				//关键的逻辑问题：详细分类的每个selected都会被提交
				//所以要根据初步分类来确定应该提交哪个详细的分类的信息
				String fdetailCaseType = request.getParameter("fdetailCaseType" + finicasetypes[i]);
				//String[] fdetailCaseType = request.getParameterValues("fdetailCaseType" + finicasetypes[i]);
				RelInicasetype relInicasetype = new RelInicasetype();
				relInicasetype.setComplainid(complian.getFid());//案件信息
				relInicasetype.setInicasetypeid(StringUtil.strToInt(finicasetypes[i]));//添加案件初步分类信息
				relInicasetype.setDetailcasetypeid(StringUtil.strToInt(fdetailCaseType));//添加案件初步分类信息
				relInicasetypeMapper.insert(relInicasetype);
			}
			List<String> typeNameList=commonMapper.getTypeNameById(finicasetypes);
				for (int i = 0; i < typeNameList.size(); i++) {
					if(i<typeNameList.size()-1){
						typeName+=typeNameList.get(i)+"-";
					}else{
						typeName+=typeNameList.get(i);
					}
				}
			}
		
		String[] newfinicasetype= request.getParameterValues("newfinicasetype");
		if(newfinicasetype!=null){
			for (int i = 0; i < newfinicasetype.length; i++) {
				if(newfinicasetype[i].equals("27")){
					RelInicasetype relInicasetype = new RelInicasetype();
					relInicasetype.setComplainid(complian.getFid());//案件信息
					relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
					relInicasetypeMapper.insert(relInicasetype);
				}else{
					String[] newfinicasetypes= request.getParameterValues("fnewdetailtypeid"+newfinicasetype[i]);
					if(newfinicasetypes!=null&&newfinicasetypes.length>0){
						for (int j = 0; j < newfinicasetypes.length; j++) {
							RelInicasetype relInicasetype = new RelInicasetype();
							relInicasetype.setComplainid(complian.getFid());//案件信息
							relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
							relInicasetype.setDetailcasetypeid(StringUtil.strToInt(newfinicasetypes[j]));//添加案件初步分类信息
							relInicasetypeMapper.insert(relInicasetype);
						}
					}else{
						RelInicasetype relInicasetype = new RelInicasetype();
						relInicasetype.setComplainid(complian.getFid());//案件信息
						relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
						relInicasetype.setDetailcasetypeid(null);//添加案件初步分类信息
						relInicasetypeMapper.insert(relInicasetype);
					}
				}
				
			}
		}
		User user = (User)session.getAttribute("user");
		//System.out.println(typeName);
		if(isUpdate==false){
			Integer fnumber = commonMapper.findMaxComplain();
			//案件序号和案件编号是一样的,之所以要这样弄，是因为前期案件编号比较复杂
			//后来改的比较简单，所以懒得去掉findex字段了
			complian.setFnumber(fnumber + "");
			complian.setFindex(fnumber);
		}else{
			complian.setFlastupdatetime(new Date());
			
			if(null != user){
				complian.setFupdateuserid(user.getId().intValue());
			}
			record = complainMapper.updateByPrimaryKey(complian);//更新案件的信息
		}
		
		complian.setFext1(typeName);
		
//		complian.setFtempstatus(1);//将案件状态更新为可用状态
		//
//		Complain complainNew = complainMapper.findById(Complain.class, complian.getFid());
		//设置新添加进来的案件的信息
		

		//添加被投诉部门的信息   sunxiaofeng
		String[] fdeptnames = request.getParameterValues("dfdeptname");
		String[] dfcityname =request.getParameterValues("dfcityname");
		String[] dfsaledepart=request.getParameterValues("dfsaledepart");
		if(null !=fdeptnames && !"".equals(fdeptnames)){
			for (int i = 0; i < fdeptnames.length; i++) {
				DEPTINFO deptInfo=new DEPTINFO();
				if((null!=fdeptnames[i] && !"".equals(fdeptnames[i]))
						|| (null!=dfcityname[i] && !"".equals(dfcityname[i]))
						|| (null!=dfsaledepart[i] && !"".equals(dfsaledepart[i]))){
				deptInfo.setFdeptname(fdeptnames[i]);
				deptInfo.setFcityname(StringUtil.getArrayInfo(request.getParameterValues("dfcityname"), i));
				deptInfo.setFsaledepart(StringUtil.getArrayInfo(request.getParameterValues("dfsaledepart"), i));
				deptInfo.setFcomplainid((long)complian.getFid());
				deptInfo.setFcreatetime(new Date());
				deptInfo.setFlastupdatetime(new Date());
				if(null != user){
					deptInfo.setFcreateuserid(user.getId());
					deptInfo.setFupdateuserid(user.getId());
				}
				deptInfoMapper.insert(deptInfo);
				}
			}
		}
	//添加关联的人员信息
		String[] fnames = request.getParameterValues("fname");
		String[] fmobiles = request.getParameterValues("fmobile");
		String[] fofficephones = request.getParameterValues("fofficephone");
		String[] fqqs = request.getParameterValues("fqq");
		String[] fcompanynames = request.getParameterValues("fcompanyname");
		if(null != fnames && !"".equals(fnames)){
			for(int i=0;i<fnames.length;i++){
				Person person = new Person();
				if((null != fnames[i] &&!"".equals(fnames[i]))
						||(null !=fmobiles[i] && !"".equals(fmobiles[i])) 
						||(null !=fofficephones[i] && !"".equals(fofficephones[i]))
						||(null !=fqqs[i] && !"".equals(fqqs[i]))
						||(null !=fcompanynames[i] && !"".equals(fcompanynames[i]))){//被投诉人姓名不能为空
					person.setFname(fnames[i]);//被投诉人姓名
					person.setFmobile(StringUtil.getArrayInfo(request.getParameterValues("fmobile"),i));//手机号码
					person.setFofficephone(StringUtil.getArrayInfo(request.getParameterValues("fofficephone"),i));//座机
					person.setFqq(StringUtil.getArrayInfo(request.getParameterValues("fqq"),i));//QQ
					person.setFemail(StringUtil.getArrayInfo(request.getParameterValues("femail"),i));//邮箱
					String[] strFlevel = request.getParameterValues("flevel");
					if(null != strFlevel && i<strFlevel.length){
						person.setFlevel(strFlevel[i]);//级别
					}
					person.setFdeptname(StringUtil.getArrayInfo(request.getParameterValues("fdeptname"),i));//部门
					person.setFcityname(StringUtil.getArrayInfo(request.getParameterValues("fcityname"),i));//城市
					person.setFsaledepart(StringUtil.getArrayInfo(request.getParameterValues("fsaledepart"),i));//营业部
					
					person.setFentrytime(StringUtil.getArrayInfo(request.getParameterValues("fentrytime"),i));//入职时间
					person.setFlastworkunit(StringUtil.getArrayInfo(request.getParameterValues("flastworkunit"),i));//上家工作单位地址
					person.setFeducation(StringUtil.getArrayInfo(request.getParameterValues("feducation"),i));
					person.setFreferrer(StringUtil.getArrayInfo(request.getParameterValues("freferrer"),i));//关联的案件信息
					String[] fcomplaintcounts = request.getParameterValues("fcomplaintcount");
					
					if(null != fcomplaintcounts && i<fcomplaintcounts.length){
						person.setFcomplaintcount(StringUtil.objToInteger(fcomplaintcounts[i]));//被投诉的次数
					}
					person.setFext1(StringUtil.getArrayInfo(request.getParameterValues("fext1"),i));//涉及到的案子
					person.setFrefercase(StringUtil.getArrayInfo(request.getParameterValues("frefercase"),i));
					person.setFcomplainid(complian.getFid());//关联的案件信息
					person.setFcompanyname(StringUtil.getArrayInfo(request.getParameterValues("fcompanyname"),i));
					person.setFbycompliantadd(StringUtil.getArrayInfo(request.getParameterValues("fbycompliantadd"),i));
					person.setFisagent(StringUtil.getArrayInfo(request.getParameterValues("fisagent"),i));
					person.setFisinner(StringUtil.getArrayInfo(request.getParameterValues("fisinner"),i));
					person.setFnotagentRemark(StringUtil.getArrayInfo(request.getParameterValues("fnotagentRemark"),i));
					person.setFcreatetime(new Date());
					person.setFlastupdatetime(new Date());
					if(null != user){
						person.setFcreateuserid(user.getFpersonid());
						person.setFupdateuserid(user.getFpersonid());
					}
					personMapper.insert(person);
				}
			}
		}
		
		//添加关联的收费信息
		if(complian.getIsCharge()!=null && !("").equals(complian.getIsCharge()) &&complian.getIsCharge().equals("1")){
			String[] ffreceiveType=request.getParameterValues("ffreceiveType");
			String[] fftype=request.getParameterValues("fftype");
			String[] ffmode=request.getParameterValues("ffmode");
			String[] ffamount=request.getParameterValues("ffamount");
			if(ffreceiveType!=null && ffreceiveType.length>0){
				for (int j = 0; j < ffreceiveType.length; j++) {
					ChargeInfo ci=new ChargeInfo();
					ci.setFcomplainId(complian.getFid());
					
					ci.setFreceiveType(ffreceiveType[j]);
					ci.setFtype(fftype[j]);
					ci.setFmode(ffmode[j]);
					ci.setFamount(ffamount[j]);
					
					chargeInfoMapper.insert(ci);
				}
			}
		}
		
		//添加业绩
		String[] ffcomplanantPerformance=request.getParameterValues("ffcomplanantPerformance");
		//String[] fftermPerformance=request.getParameterValues("fftermPerformance");
		if(ffcomplanantPerformance!=null&&ffcomplanantPerformance.length>0){
			for (int j = 0; j < ffcomplanantPerformance.length; j++) {
				Performance p=new Performance();
				p.setFcomplainId(complian.getFid());
				p.setFcomplanantPerformance(ffcomplanantPerformance[j]);
				//p.setFtermPerformance(fftermPerformance[j]);
				performanceMapper.insert(p);
			}
		}
		
		
		
		
		return record;
	}
	
	public int addCaseInfo(String json,Complain complian,HttpServletRequest request,HttpSession session) throws Exception {
		Complain target=getTargetComplain(json, complian);
		target.setFtempstatus(1);
		target.setFcreatetime(new Date());
		Integer fnumber = commonMapper.findMaxComplain();
		target.setFnumber(fnumber + "");
		target.setFindex(fnumber);
		
		
		User user = (User)session.getAttribute("user");
		if(null != user){
			target.setFcreateuserid(user.getId().intValue());
		}
		int record = complainMapper.insert(target);
		editCaseInfo( target, request, session,false);
		
		return  record;
	}
	@Override
	public int updateCaseInfo(Complain complian,HttpServletRequest request,HttpSession session) throws Exception {
		//删除掉案件关联的被投诉部门
		deptInfoMapper.deleteDeptByComplainId(complian.getFid());
		//删除掉案件关联的所有的人员信息
		personMapper.deletePersonByComplainId(complian.getFid());
		//删除掉案件关联的所有初步分类，详细分类的信息
		relInicasetypeMapper.deleteInicaseTypeByComplainId(complian.getFid());
		
		//李如意 删除案件关联的收费信息
		chargeInfoMapper.deleteChargeInfoByComplainId(complian.getFid());
		
		performanceMapper.deletePerformanceInfoByComplainId(complian.getFid());
		
		return editCaseInfo(complian, request, session,true);
	}
	
	@Autowired
	private EmailConfigMapper emailConfigMapper;
	public void setEmailConfigMapper(EmailConfigMapper emailConfigMapper) {
		this.emailConfigMapper = emailConfigMapper;
	}
	
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page,HttpServletRequest request) throws Exception {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		User user = (User)request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		map.put("fcompchannelid", request.getParameter("fcompchannelid"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("fcomplainant", request.getParameter("fcomplainant"));
		map.put("fcusname", request.getParameter("fcusname"));
		map.put("choocase", request.getParameter("choocase"));
		map.put("floanbread", request.getParameter("floanbread"));
		map.put("typename", request.getParameter("typename"));
		if ((request.getParameter("fqq") == null || "".equals(request.getParameter("fqq")))
				&& (request.getParameter("selectfcusname") == null || ""
						.equals(request.getParameter("selectfcusname")))
				&& (request.getParameter("selectfmobile") == null || ""
						.equals(request.getParameter("selectfmobile")))) {
			
			//判断是否是终审人员
			boolean flag=false;
			
			EmailConfig emailConfig= emailConfigMapper.getEmailConfigByFid(5);//终审人员
			if(emailConfig != null){
				if(emailConfig.getFemail() != null && !"".equals(emailConfig.getFemail())){
					String [] emails=emailConfig.getFemail().split(";");
					for (int i = 0; i < emails.length; i++) {
						if(emails[i].equals(user.getUsername())){
							flag=true;
							break;
						}
					}
				}
			}
			
			//判断是否是张然登陆，如果是张然登陆，可以看到所有的案件信息
			if(flag ||"heguiadmin".equals(user.getUsername())){
				map.put("fcreateuserid", null);
			}else{
				map.put("fcreateuserid", user.getUsername());//为实现转部门负责人的功能，将取id改成了取用户名
			}
			
			
			
		}
		map.put("fid", request.getParameter("fid"));
        map.put("fqq", request.getParameter("fqq"));
        map.put("selectfcusname", request.getParameter("selectfcusname"));
		map.put("selectfmobile", request.getParameter("selectfmobile"));
		//查询总行数的方法
		int totalCounts = complainMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List<Map> list = complainMapper.queryPageByParamss(map2);
		page.setRows(list);
		return page;
	}
	public String findCussource(){
		List<Dictionary> listDictionary = commonMapper.findCussource();
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public String findCusstatus(Integer fservicetypeid) {
		Map map = new HashMap();
		map.put("fservicetypeid", fservicetypeid);
		List<Dictionary> listDictionary = commonMapper.findCusstatus(map);
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public String findServicetype() {
		List<Dictionary> listDictionary = commonMapper.findServicetype();
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public String findDitch() {
		List<Dictionary> listDictionary = commonMapper.findDitch();
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public String findEvidenceType() {
		List<Dictionary> listDictionary = commonMapper.findEvidenceType();
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public Pagination selectAdmin(Pagination page) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		map.put("fname", request.getParameter("fname_l2"));
		map.put("fdeptname", request.getParameter("fdeptname"));
		map.put("fcityname", request.getParameter("fcityname"));
		map.put("fsaledepart", request.getParameter("fsaledepart"));
		map.put("teamdepart", request.getParameter("teamdepart"));
		//map = setMapValue(request, map);
		int totalCounts = query.getTotalCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List<Map<String,Object>> list = query.findOrgAdmin(map2);
		System.out.println("list\t" + list);
		page.setRows(list);
		return page;
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	public List getOrgData() throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		Map map = new HashMap();
		map.put("fsaledepart", request.getParameter("q"));
		return query.getOrgData(map);
	}
	/**
	 * 
	 * 描述：设置公共的参数
	 * 2012-12-31 下午06:08:52 by ygq
	 * @version
	 * @param request
	 * @param map
	 * @return
	 */
	private Map setMapValue(HttpServletRequest request,Map map){
		Date begin = StringUtil.strToDate(request.getParameter("begin"));
		Date end = StringUtil.strToDate(request.getParameter("end"));
		String receiver = StringUtil.addLikeOperBoth(request.getParameter("receiver"));//添加
		String departname =  StringUtil.addLikeOperBoth(request.getParameter("departname"));
		String postname = request.getParameter("postname");
		String theme = StringUtil.addLikeOperBoth(request.getParameter("theme"));
		String typeid = request.getParameter("typeid");
		String wayid = request.getParameter("wayid");
		String issend = request.getParameter("issend");
		map.put("begin", begin);
		map.put("end", end);
		map.put("receiver", receiver);
		map.put("departname", departname);
		map.put("postname", postname);
		map.put("theme", theme);
		map.put("typeid",typeid);
		map.put("wayid",wayid);
		map.put("issend",issend);
		return map;
	}
	@Override
	public String findInicaseType() {
		List<Dictionary> listDictionary = commonMapper.findInicaseType();
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public String findDetailCaseType(Integer finicasetype) {
		List<Dictionary> listDictionary = commonMapper.findDetailCaseType(finicasetype);
		return StringUtil.convertListToGson(listDictionary);
	}
	@Override
	public Integer findPersonCompCount(HttpServletRequest request) {
		String fnameStr = request.getParameter("fname");
		/*String fname = null;
		try {
			//使用平台默认的编码重新构造一个字符串
			fname = new String(fnameStr.getBytes("ISO-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fname", fnameStr);
		return commonMapper.findPersonCompCount(map);
	}
	@Override
	public String findPersonRelComplian(HttpServletRequest request) {
		String fnameStr = request.getParameter("fname");
		/*String fname = null;
		try {
			//使用平台默认的编码重新构造一个字符串
			fname = new String(fnameStr.getBytes("ISO-8859-1"),"GBK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fname", fnameStr);
		List<Map<String,Object>> mapList = commonMapper.findPersonRelComplian(map);
		return StringUtil.convertListToGson(mapList);
	}
	@Override
	public Complain findCase(String fid) {
		Complain complain=complainMapper.findCase(fid);
		return complain;
	}
	@Override
	public void updateCompliant(Complain complainInfo) {
		complainMapper.updateByPrimaryKey(complainInfo);
	}
	@Override 
	public List<RelInicasetype> selectInicaseTypeByComplainId(Integer complainid) throws Exception{
	     return relInicasetypeMapper.selectInicaseTypeByComplainId(complainid);
	}
	@Override
	public List<Person> selectPersonByComplainId(Integer complainid)
			throws Exception {
		return personMapper.selectPersonByComplainId(complainid);
	}
	@Override
	public Complain findCaseInfoById(String complainid) throws Exception {
		Complain complain=complainMapper.findCase(complainid);
		return complain;
	}
	@Override
	public List<DEPTINFO> selectDeptInfoByComplainId(int complainid) {
		return deptInfoMapper.selectDeptInfoByComplainId(complainid);
	}
	@Override
	public String findFmobileCount(HttpServletRequest request) {
		String fmobile = request.getParameter("fmobile");
		String fid=request.getParameter("fid");
		Map<String,Object> map = new HashMap<String,Object>();
		String strCount="";
		if(fmobile!=null&&fmobile!=""){
			if(fmobile.indexOf(";")!=-1){
				String[] strFmobile=fmobile.split(";");
				for (int i = 0; i < strFmobile.length; i++) {
					if(null!=fid&&!"".equals(fid)){
						 map.put("fid", fid);
					}
					map.put("fmobile", strFmobile[i]);
					strCount+="<a href='javascript:void(0)' onclick='openWinComplaint(\"fmobile\",\""+i+"\")'>"+commonMapper.findFmobileCount(map)+"</a>|";
				}
			}else{
				if(null!=fid&&!"".equals(fid)){
					 map.put("fid", fid);
				}
				map.put("fmobile", fmobile);
				strCount+="<a href='javascript:void(0)' onclick='openWinComplaint(\"fmobile\",\"\")'>"+commonMapper.findFmobileCount(map)+"</a>|";
			}
			strCount=strCount.substring(0,strCount.length()-1);
		}
	
		System.out.println(strCount);
		return strCount;
	}
	@Override
	public Integer findFqqCount(HttpServletRequest request) {
		String fqq=request.getParameter("fqq");
		String fid=request.getParameter("fid");
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=fid&&!"".equals(fid)){
			map.put("fid", fid);
		 }
		map.put("fqq", fqq);
		return commonMapper.findFqqCount(map);
	}
	@Override
	public Integer findFcusNameCount(HttpServletRequest request) {
		String fcusname=request.getParameter("fcusname");
		/*String fname = null;
		try {
			//使用平台默认的编码重新构造一个字符串
			fname=java.net.URLDecoder.decode(fcusname,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String fid=request.getParameter("fid");
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=fid&&!"".equals(fid)){
			map.put("fid", fid);
		}
		//System.out.println(fcusname);
		map.put("fcusname", fcusname);
		return commonMapper.findFcusNameCount(map);
	}
	@Override
	public Map findCaseById(String fid) {
	Map map=complainMapper.findCaseById(fid);
		return map;
	}
	@Override
	public String getAllCaseType() {
		List<CaseType> list=commonMapper.selectAllCaseType();
		return StringUtil.convertListToGson(list);
	}
	@Override
	public Complain getTargetComplain(String json, Complain from)
			throws Exception {
		JsonUtil<Complain> jsonUtil=new JsonUtil<Complain>();
		Complain target=jsonUtil.fromJsonToObj(json,new TypeToken<Complain>() {}.getType());
		if(target!=null){
			target.setFcompchannelid(from.getFcompchannelid());//投诉渠道
			target.setFcomptime(from.getFcomptime());//投诉时间
			target.setSRCode(from.getSRCode());//SRCode
			target.setFselfEmailRemark(from.getFselfEmailRemark());//个人邮箱备注
			target.setFisevidenceRemarks(from.getFisevidenceRemarks());//证据类型
			target.setFisinner(from.getFisinner());//是否是内部员工
			target.setFbusportid(from.getFbusportid());//涉及业务端
			target.setIsCharge(from.getIsCharge());//是否收费
			target.setFinicasetypeRemark(from.getFinicasetypeRemark());//初步分类备注
			target.setFnewtypemark(from.getFnewtypemark());//新分类备注
			target.setFdetaildescrip(from.getFdetaildescrip());//案件具体描述
			target.setFresponsibleName(from.getFresponsibleName());//案件负责人
			target.setFresponsibleEmail(from.getFresponsibleEmail());//负责人邮箱
			target.setRiskLevel(from.getRiskLevel());//潜在风险等级评估
			/*target.setCaseComeIntoCode(from.getCaseComeIntoCode());//进件编号
			target.setCaseComeIntoDate(from.getCaseComeIntoDate());//进件时间
			target.setCasefrom(from.getCasefrom());//案件来源ID*/			
			target.setSalePerson(from.getSalePerson());//销售人员
			target.setSuperLeader(from.getSuperLeader());//上级领导
			target.setIndirectLeader(from.getIndirectLeader());//直接领导
			target.setSaleDepart(from.getSaleDepart());//营业部
			target.setServiceName(from.getServiceName());//客服姓名
		}
		return target;
	}
	
	public List<CaseType> getNewCaseType() throws Exception{
		List<CaseType> list=commonMapper.selectAllCaseType();
		return list;
	}
	@Override
	public void setCaseTypeIsChecked(List<CaseType> newCaseType,
			List<RelInicasetype> relInicasetypes) throws Exception {
		if(relInicasetypes!=null&&relInicasetypes.size()>0){
			for (int i = 0; i < relInicasetypes.size(); i++) {
				RelInicasetype r=relInicasetypes.get(i);
				
				for (int j = 0; j < newCaseType.size(); j++) {
					CaseType c=newCaseType.get(j);
					if(StringUtil.objToInteger(c.getId())==r.getInicasetypeid()){
						c.setIschecked(1);
						List<CaseDetailType> ll=c.getDetailCaseType();
						if(ll!=null&&ll.size()>0){
							for (int k = 0; k < ll.size(); k++) {
								CaseDetailType tt=ll.get(k);
								if(StringUtil.objToInteger(tt.getId())==r.getDetailcasetypeid()){
									tt.setIschecked(1);
								}
							}
						}
					}
					
				}
				
			}
		}
		
	}
	
	/**
	 * 设置头部
	 */
	private void setHeader(HSSFWorkbook wb,HSSFSheet sheet,int rowIndex, int maxDepart,int maxInner,int maxChargeInfo){
		Row row=POIUtil.createRow(sheet, rowIndex);
		Row row1=POIUtil.createRow(sheet, rowIndex+1);
		row.setHeight((short)500);
		row.setHeight((short)500);
		
		HSSFCellStyle mainTitleCellStyle=POIUtil.createMainTitleFont(wb);//主标题
		HSSFCellStyle subjectTitleCellStyle=POIUtil.createSubjectTitleFont(wb);//副标题
		HSSFCellStyle mergerCellStyle=POIUtil.createMergCellStyle(wb);//合并单元格样式
		
		for (int i = 0; i <= 14+maxDepart*3+maxInner*4+4*maxChargeInfo + 13 + 7; i++) {
			sheet.setColumnWidth(i, 5000);
		}
		//设置第二行
		for (int i = 0; i <= 14; i++) {
			CellUtil.createCell(row1, i , "").setCellStyle(subjectTitleCellStyle);
		}
		//设置第二行
		for (int i = 15 + maxDepart*3+maxInner*4 + 4*maxChargeInfo; i < 15+maxDepart*3+maxInner*4+4*maxChargeInfo + 13 +7; i++) {
			CellUtil.createCell(row1, i , "").setCellStyle(subjectTitleCellStyle);
		}
		
		for (int i = 0; i < maxDepart; i++) {
			CellUtil.createCell(row1, 15 + i , "被投诉部门"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1, 15 + i +maxDepart, "被投诉部门"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1, 15 + i +maxDepart * 2, "被投诉部门"+(i+1)).setCellStyle(subjectTitleCellStyle);
		}
		
		for (int i = 0; i < maxInner; i++) {
			CellUtil.createCell(row1,15+3*maxDepart+i ,"内部人员"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner,"内部人员"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*2 ,"内部人员"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*3 ,"内部人员"+(i+1)).setCellStyle(subjectTitleCellStyle);
		}
		
		for (int i = 0; i < maxChargeInfo; i++) {
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*4 , "收费"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*4 + maxChargeInfo, "收费"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*4 + maxChargeInfo*2, "收费"+(i+1)).setCellStyle(subjectTitleCellStyle);
			CellUtil.createCell(row1,15+3*maxDepart+i+maxInner*4 + maxChargeInfo*3, "收费"+(i+1)).setCellStyle(subjectTitleCellStyle);
		}
		
		
		CellRangeAddress region1 =new CellRangeAddress(0, 0, 15, 14+maxDepart);//部门
		CellRangeAddress region2 =new CellRangeAddress(0, 0, 15+maxDepart, 14+maxDepart*2);//部门
		CellRangeAddress region3 =new CellRangeAddress(0, 0, 15+maxDepart*2, 14+maxDepart*3);//部门
		CellRangeAddress region4 =new CellRangeAddress(0, 0, 15+maxDepart*3, 14+maxDepart*3+maxInner);//内部人员
		CellRangeAddress region5 =new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner, 14+maxDepart*3+maxInner*2);//内部人员
		CellRangeAddress region6 =new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*2, 14+maxDepart*3+maxInner*3);//内部人员
		CellRangeAddress region7 =new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*3, 14+maxDepart*3+maxInner*4);//内部人员
		CellRangeAddress region8 =new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*4, 14+maxDepart*3+maxInner*4+maxChargeInfo);//收费
		CellRangeAddress region9 =new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*4+maxChargeInfo, 14+maxDepart*3+maxInner*4+maxChargeInfo*2);//收费
		CellRangeAddress region10=new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*4+maxChargeInfo*2, 14+maxDepart*3+maxInner*4+maxChargeInfo*3);//收费
		CellRangeAddress region11=new CellRangeAddress(0, 0, 15+maxDepart*3+maxInner*4+maxChargeInfo*3, 14+maxDepart*3+maxInner*4+maxChargeInfo*4);//收费
		
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);
		sheet.addMergedRegion(region3);
		sheet.addMergedRegion(region4);
		sheet.addMergedRegion(region5);
		sheet.addMergedRegion(region6);
		sheet.addMergedRegion(region7);
		sheet.addMergedRegion(region8);
		sheet.addMergedRegion(region9);
		sheet.addMergedRegion(region10);
		sheet.addMergedRegion(region11);
		
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region1);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region2);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region3);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region4);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region5);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region6);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region7);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region8);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region9);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region10);
		POIUtil.setMergerBorder(sheet, mergerCellStyle, region11);
		
		/*POIUtil.createRangeCell(wb,sheet,region1);
		POIUtil.createRangeCell(wb,sheet,region2);
		POIUtil.createRangeCell(wb,sheet,region3);
		POIUtil.createRangeCell(wb,sheet,region4);
		POIUtil.createRangeCell(wb,sheet,region5);
		POIUtil.createRangeCell(wb,sheet,region6);
		POIUtil.createRangeCell(wb,sheet,region7);
		POIUtil.createRangeCell(wb,sheet,region8);
		POIUtil.createRangeCell(wb,sheet,region9);
		POIUtil.createRangeCell(wb,sheet,region10);
		POIUtil.createRangeCell(wb,sheet,region11);*/
		
		CellUtil.createCell(row,15 , "所属部门").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart, "城市").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*2, "投诉所属营业部").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3 , "被投诉人").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner, "销售人员").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*2, "上级领导").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*3, "间接领导").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*4, "收费理由").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*4+maxChargeInfo, "收费人员").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*4+maxChargeInfo*2, "给付方式").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,15+maxDepart*3+maxInner*4+maxChargeInfo*3, "收费金额").setCellStyle(mainTitleCellStyle);
		
		
		CellUtil.createCell(row,15+maxDepart*3+maxInner*4+maxChargeInfo*4, "案件简介").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,16+maxDepart*3+maxInner*4+maxChargeInfo*4, "系统状态").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,17+maxDepart*3+maxInner*4+maxChargeInfo*4, "拒贷原因").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,18+maxDepart*3+maxInner*4+maxChargeInfo*4, "进件时间").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,19+maxDepart*3+maxInner*4+maxChargeInfo*4, "产品类型").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,20+maxDepart*3+maxInner*4+maxChargeInfo*4, "合同编号").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,21+maxDepart*3+maxInner*4+maxChargeInfo*4, "借款金额").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,22+maxDepart*3+maxInner*4+maxChargeInfo*4, "分期数").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,23+maxDepart*3+maxInner*4+maxChargeInfo*4, "逾期天数").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,24+maxDepart*3+maxInner*4+maxChargeInfo*4, "违约次数").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,25+maxDepart*3+maxInner*4+maxChargeInfo*4, "放款时间").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,26+maxDepart*3+maxInner*4+maxChargeInfo*4, "首次还款日期").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,27+maxDepart*3+maxInner*4+maxChargeInfo*4, "还款终止日期").setCellStyle(mainTitleCellStyle);
		
		CellUtil.createCell(row,28+maxDepart*3+maxInner*4+maxChargeInfo*4, "案件状态").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,29+maxDepart*3+maxInner*4+maxChargeInfo*4, "结案日期").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,30+maxDepart*3+maxInner*4+maxChargeInfo*4, "调查函发出时间").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,31+maxDepart*3+maxInner*4+maxChargeInfo*4, "是否确认违规").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,32+maxDepart*3+maxInner*4+maxChargeInfo*4, "经办部门违规等级判定").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,33+maxDepart*3+maxInner*4+maxChargeInfo*4, "申请结案理由").setCellStyle(mainTitleCellStyle);
		CellUtil.createCell(row,34+maxDepart*3+maxInner*4+maxChargeInfo*4, "违规人员处罚").setCellStyle(mainTitleCellStyle);
		
	}
	
	@Override
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		String fileName = "案件信息列表";// 设置excel文件的名字
		String header = "attachment; filename="
				+ new String(URLDecoder.decode(fileName + ".xls", "UTF-8")
						.getBytes(), "iso8859-1");
		response.setHeader("Content-Disposition", header);
		OutputStream out = response.getOutputStream();

		String[] headers = { "案件编号","上报时间","投诉来源渠道","投诉人","客户姓名","客户身份证号码","承办人", "案件初步违规分类","详细分类（初）","违规等级（初）",
				"Basel分类（初）","案件违规分类","详细分类（终）","违规等级（终）","Basel分类（终）"};
		
		User user = (User)request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("fcompchannelid", request.getParameter("fcompchannelid"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("fcomplainant", request.getParameter("fcomplainant"));
		map.put("fcusname", request.getParameter("fcusname"));
		map.put("floanbread", request.getParameter("floanbread"));
		map.put("typename", request.getParameter("typename"));
		
		//判断是否是张然登陆，如果是张然登陆，可以看到所有的案件信息
		if("ranzhang@creditease.cn".equals(user.getUsername())||"heguiadmin".equals(user.getUsername())){
			map.put("fcreateuserid", null);
		}else{
			map.put("fcreateuserid", user.getUsername());//为实现转部门负责人的功能，将取id改成了取用户名
		}
		
		Long long1=System.currentTimeMillis();
		
		List<ComplainForExcel>  complainForExcels=complainMapper.getIniNewCaseType(map);
		List<ComplainForExcel>  complainApplymentForExcels=complainMapper.getEndNewCaseType(map);
		List<ComplainForExcel>  complainDepartForExcels=complainMapper.getComplainDepartForExcels(map);
		List<ComplainForExcel>  complainInnerPersonForExcels=complainMapper.getComplainInnerPersonForExcels(map);
		List<ComplainForExcel>  complainChargeInfoForExcels=complainMapper.getComplainChargeForExcels(map);
		List<ComplainForExcel>  complainInvestigationForExcels=complainMapper.getComplainInvestigationForExcels(map);
		
		
		int maxDepart=complainMapper.getComplainDepartMaxForExcel(map);
		int maxInner=complainMapper.getComplainInnerPersonMaxForExcel(map);
		int maxChargeInfo=complainMapper.getComplainChargeMaxForExcel(map);
		
		
		
		Long long2=System.currentTimeMillis();
		
		//System.out.println("sql运行时间:"+(long2-long1)/1000);
		
		//System.out.println("数据大小为:"+complainDepartForExcels.size());
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFCellStyle textCellStyle = POIUtil.createTextCell(wb);//正文的样式
		HSSFCellStyle mergerCellStyle=POIUtil.createMergCellStyle(wb);//合并单元格样式
		
		HSSFSheet sheet = wb.createSheet(fileName);// 设置页的名称
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设置行高
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 5000);// 设置单元格的宽度
			ExpExcelUtil.createTxtCell(wb, row, i, headers[i], ExpExcelUtil
					.createCollStyle(cellStyle, wb));// 创建表格第一行
		}
		
		setHeader(wb,sheet,0,maxDepart,maxInner,maxChargeInfo);//动态加头部
		
		importCombineObject(complainForExcels,complainApplymentForExcels);
		
		int rowIndex=2;
		int columnIndex=15;
		
		importCaseTypeDepart(wb,sheet,textCellStyle,mergerCellStyle,rowIndex, columnIndex, complainForExcels, complainDepartForExcels,maxDepart);//导入部门表
		
		columnIndex=columnIndex+3*maxDepart;
		
		importCaseTypeInnerPerson(wb,sheet,textCellStyle,mergerCellStyle,rowIndex, columnIndex, complainForExcels, complainInnerPersonForExcels,maxInner);//导入内部人员表
		
		columnIndex=15+3*maxDepart+4*maxInner;//收费信息
		
		importCaseTypeChargePerson(wb,sheet,textCellStyle,mergerCellStyle,rowIndex, columnIndex, complainForExcels, complainChargeInfoForExcels,maxChargeInfo);//导入收费信息
		
		rowIndex=2;
		columnIndex=0;
		
		
		for (int i = 0; i < complainForExcels.size(); i++) {

			ComplainForExcel complainForExcel = complainForExcels.get(i);

			Row rr= POIUtil.createRow(sheet, rowIndex);

			//合并单元格
			for (int j = columnIndex ; j <= columnIndex + 6 ; j++) {
				CellRangeAddress region=new CellRangeAddress(rowIndex, rowIndex + complainForExcel.getRowCount() - 1, j, j);
				sheet.addMergedRegion(region);
				//POIUtil.createRangeCell(wb, sheet, region);
				POIUtil.setMergerBorder(sheet, mergerCellStyle, region);
			}
			
			for (int k = 15 + maxDepart*3+maxInner*4 + 4*maxChargeInfo; k < 15+maxDepart*3+maxInner*4+4*maxChargeInfo+13+7; k++) {
				CellRangeAddress region=new CellRangeAddress(rowIndex, rowIndex + complainForExcel.getRowCount() - 1, k, k);
				sheet.addMergedRegion(region);
				//POIUtil.createRangeCell(wb, sheet, region);
				POIUtil.setMergerBorder(sheet, mergerCellStyle, region);
			}
			
			CellUtil.createCell(rr,columnIndex,complainForExcel.getFnumber()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+1,StringUtil.dateToString(complainForExcel.getFcomptime())).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+2,complainForExcel.getChannelname()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+3,complainForExcel.getFcomplainanter()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+4,complainForExcel.getFcusName()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+5,complainForExcel.getFidcard()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,columnIndex+6,complainForExcel.getFresponsibleName()).setCellStyle(textCellStyle);
			
			//案件描述
			CellUtil.createCell(rr,15+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFdetaildescrip()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,16+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getStatus()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,17+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFdeniedloans()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,18+maxDepart*3+maxInner*4+maxChargeInfo*4,"").setCellStyle(textCellStyle);
			
			String loan=LoanBreadDictionary.getLoanBread(complainForExcel.getFloanbread());
			CellUtil.createCell(rr,19+maxDepart*3+maxInner*4+maxChargeInfo*4,loan).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,20+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFcontractnumber()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,21+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFamount()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,22+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFdeadline()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,23+maxDepart*3+maxInner*4+maxChargeInfo*4,StringUtil.parseInt(complainForExcel.getFviolatedays())).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,24+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFviolateCountHistory()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,25+maxDepart*3+maxInner*4+maxChargeInfo*4,StringUtil.dateToString(complainForExcel.getFloantime())).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,26+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFreimbstrattime()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,27+maxDepart*3+maxInner*4+maxChargeInfo*4,complainForExcel.getFreimbendtime()).setCellStyle(textCellStyle);
			
			CellUtil.createCell(rr,28+maxDepart*3+maxInner*4+maxChargeInfo*4,complainInvestigationForExcels.get(i).getStatusname()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,29+maxDepart*3+maxInner*4+maxChargeInfo*4,StringUtil.dateToString(complainInvestigationForExcels.get(i).getEndtime())).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,30+maxDepart*3+maxInner*4+maxChargeInfo*4,"").setCellStyle(textCellStyle);
			CellUtil.createCell(rr,31+maxDepart*3+maxInner*4+maxChargeInfo*4,complainInvestigationForExcels.get(i).getOutoflinename()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,32+maxDepart*3+maxInner*4+maxChargeInfo*4,complainInvestigationForExcels.get(i).getOutooflinelevelname()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,33+maxDepart*3+maxInner*4+maxChargeInfo*4,complainInvestigationForExcels.get(i).getReason()).setCellStyle(textCellStyle);
			CellUtil.createCell(rr,34+maxDepart*3+maxInner*4+maxChargeInfo*4,complainInvestigationForExcels.get(i).getComplainantSolution()).setCellStyle(textCellStyle);
			
			columnIndex=7;
			
			exportCaseType(wb,sheet,textCellStyle,rowIndex, columnIndex,complainForExcel,complainForExcel.getIniCaseTypeList());//导出案件初步违归分类
			
			columnIndex=11;//列置第一列
			
			exportCaseType(wb,sheet,textCellStyle,rowIndex, columnIndex,complainForExcel,complainApplymentForExcels.get(i).getEndCaseTypeList());//导出案件初步违归分类
			
			//行指针下移
			rowIndex=rowIndex+complainForExcel.getRowCount();	
		
			//列指针归0
			columnIndex = 0; // 列指针指向头部
		}
		
		Long long3=System.currentTimeMillis();
		//System.out.println("POI渲染时间:"+(long3-long2)/1000);
		wb.write(out);
		
		response.flushBuffer();	
		out.close();// 关闭	
	}
	
	
	/**
	 * 导出案件分类
	 * @param caseTypeForExcelList
	 * @param curRowIndex
	 * @return
	 */
	private void exportCaseType(HSSFWorkbook wb,HSSFSheet sheet,HSSFCellStyle textCellStyle,int rowIndex,int columnIndex,
			ComplainForExcel complainForExcel,List<CaseTypeForExcel> caseTypeForExcelList){
		int tmpRowIndex=rowIndex;
		if(caseTypeForExcelList!=null&&caseTypeForExcelList.size()>0){
			for (int j = 0; j < caseTypeForExcelList.size(); j++) {
				CaseTypeForExcel tmpCaseType=caseTypeForExcelList.get(j);
				
				CellRangeAddress region=new CellRangeAddress(rowIndex, rowIndex + tmpCaseType.getRowCount() - 1, columnIndex, columnIndex);
				sheet.addMergedRegion(region);
				POIUtil.createRangeCell(wb, sheet, region);
				
				Row row= POIUtil.createRow(sheet, rowIndex);
				CellUtil.createCell(row, columnIndex,tmpCaseType.getName()).setCellStyle(textCellStyle);
				
				//案件初步违归详细分类
				if(tmpCaseType.getCaseDetailTypeForExcelList()!=null&&tmpCaseType.getCaseDetailTypeForExcelList().size()>0){
					for (int k = 0; k < tmpCaseType.getCaseDetailTypeForExcelList().size(); k++) {
						CaseDetailTypeForExcel tmp=tmpCaseType.getCaseDetailTypeForExcelList().get(k);
						
						Row r= POIUtil.createRow(sheet, rowIndex+k);
						
						CellUtil.createCell(r, columnIndex+1,tmp.getName()).setCellStyle(textCellStyle);
						CellUtil.createCell(r, columnIndex+2,tmp.getBreakLevelName()).setCellStyle(textCellStyle);
						CellUtil.createCell(r, columnIndex+3,tmp.getBaseTypeName()).setCellStyle(textCellStyle);
					}
				}
				
				for (int k = tmpCaseType.getCaseDetailTypeForExcelList().size(); k < tmpCaseType.getRowCount() ; k++) {
					Row r= POIUtil.createRow(sheet, rowIndex+k);
					CellUtil.createCell(r, columnIndex+1,"").setCellStyle(textCellStyle);
					CellUtil.createCell(r, columnIndex+2,"").setCellStyle(textCellStyle);
					CellUtil.createCell(r, columnIndex+3,"").setCellStyle(textCellStyle);
				}
				rowIndex+=tmpCaseType.getRowCount();
			}
		}
		
		for (int i = rowIndex ; i <tmpRowIndex + complainForExcel.getRowCount(); i++) {
			Row row=POIUtil.createRow(sheet, i);
			
			CellUtil.createCell(row, columnIndex, "").setCellStyle(textCellStyle);
			CellUtil.createCell(row, columnIndex+1, "").setCellStyle(textCellStyle);
			CellUtil.createCell(row, columnIndex+2, "").setCellStyle(textCellStyle);
			CellUtil.createCell(row, columnIndex+3, "").setCellStyle(textCellStyle);
		}
		
	}

	/**
	 * 得到案子违归分类
	 */
	private void importCombineObject(List<ComplainForExcel> complainForExcelList,List<ComplainForExcel> complainApplymentForExcelList){
		if(complainForExcelList!=null&&complainForExcelList.size()>0&&complainApplymentForExcelList!=null&&complainApplymentForExcelList.size()>0
				&&complainForExcelList.size()==complainApplymentForExcelList.size()){
			for (int i = 0; i < complainForExcelList.size(); i++) {
				ComplainForExcel complainForExcel=complainForExcelList.get(i);
				
				List<CaseTypeForExcel> iniCaseTypeForExcelList=new ArrayList<CaseTypeForExcel>();//创建初步违归分类列表
				
				List<CaseDetailTypeForExcel> iniCaseDetailTypeList=complainForExcel.getIniCaseDetailTypeList();//得到初步违归详细分类
				if(iniCaseDetailTypeList!=null&&iniCaseDetailTypeList.size()>0){
					Set<String> tmpSet=new LinkedHashSet<String>();
					for (int j = 0; j < iniCaseDetailTypeList.size(); j++) {
						tmpSet.add(iniCaseDetailTypeList.get(j).getCaseTypeId());
					}
					if(!tmpSet.isEmpty()){
						Iterator<String> it=tmpSet.iterator();
						while (it.hasNext()) {
							String str=it.next();
							CaseTypeForExcel tmpCaseTypeForExcel=new CaseTypeForExcel();
							List<CaseDetailTypeForExcel> tmpCaseDetailTypeForExcelList=new ArrayList<CaseDetailTypeForExcel>();
							for (int j = 0; j < iniCaseDetailTypeList.size(); j++) {
								CaseDetailTypeForExcel tmp= iniCaseDetailTypeList.get(j);
								if(str.equals(tmp.getCaseTypeId())){
									tmpCaseTypeForExcel.setId(tmp.getCaseTypeId());
									tmpCaseTypeForExcel.setName(tmp.getCaseTypeName());
									tmpCaseDetailTypeForExcelList.add(tmp);
								}
							}
							tmpCaseTypeForExcel.setCaseDetailTypeForExcelList(tmpCaseDetailTypeForExcelList);
							tmpCaseTypeForExcel.setRowCount(tmpCaseDetailTypeForExcelList.size());//设置案件初步违归分类占的行数
							iniCaseTypeForExcelList.add(tmpCaseTypeForExcel);
						}
					}
					complainForExcel.setIniCaseTypeList(iniCaseTypeForExcelList);
				}
				
				
				//案件最终违归分类
				List<CaseTypeForExcel> endCaseTypeForExcelList=new ArrayList<CaseTypeForExcel>();//创建初步违归分类对象列表
				if(complainApplymentForExcelList.get(i).getEndCaseDetailTypeList()!=null&&
						complainApplymentForExcelList.get(i).getEndCaseDetailTypeList().size()>0){
					/*if(complainForExcel.getRowCount()<complainApplymentForExcels.get(i).getEndCaseDetailTypeList().size()){
						complainForExcel.setRowCount(complainApplymentForExcels.get(i).getEndCaseDetailTypeList().size());
					}*/
					Set<String> tmpSet=new LinkedHashSet<String>(); 
					for (int j = 0; j < complainApplymentForExcelList.get(i).getEndCaseDetailTypeList().size(); j++) {
						tmpSet.add(complainApplymentForExcelList.get(i).getEndCaseDetailTypeList().get(j).getCaseTypeId());
					}
					if(!tmpSet.isEmpty()){
						Iterator<String> it=tmpSet.iterator();
						while (it.hasNext()) {
							String str=it.next();
							CaseTypeForExcel tmpCaseTypeForExcel=new CaseTypeForExcel();
							List<CaseDetailTypeForExcel> tmpCaseDetailTypeForExcelList=new ArrayList<CaseDetailTypeForExcel>();
							
							for (int j = 0; j < complainApplymentForExcelList.get(i).getEndCaseDetailTypeList().size(); j++) {
								CaseDetailTypeForExcel tmp= complainApplymentForExcelList.get(i).getEndCaseDetailTypeList().get(j);
								if(str.equals(tmp.getCaseTypeId())){
									tmpCaseTypeForExcel.setId(tmp.getCaseTypeId());
									tmpCaseTypeForExcel.setName(tmp.getCaseTypeName());
									tmpCaseDetailTypeForExcelList.add(tmp);
								}
							}
							tmpCaseTypeForExcel.setCaseDetailTypeForExcelList(tmpCaseDetailTypeForExcelList);
							tmpCaseTypeForExcel.setRowCount(tmpCaseDetailTypeForExcelList.size());//设置案件初步违归分类占的行数
							endCaseTypeForExcelList.add(tmpCaseTypeForExcel);
						}
					}
					complainApplymentForExcelList.get(i).setEndCaseTypeList(endCaseTypeForExcelList);
				}
				
				
				int count=getComplainRowCount(complainForExcelList.get(i).getIniCaseTypeList(),complainApplymentForExcelList.get(i).getEndCaseTypeList());
				
				complainForExcel.setRowCount(count);
			}

		}
			
	}
	
	/**
	 * 得到行数
	 */
	private int getComplainRowCount(List<CaseTypeForExcel> iniCaseTypeList,
			List<CaseTypeForExcel> endCaseTypeList) {
		int count=0;
		if(iniCaseTypeList.size()>0){
			if(endCaseTypeList.size()>0){
				//初步分类大于最终分类
				if(iniCaseTypeList.size()>=endCaseTypeList.size()){
					for (int i = 0; i < endCaseTypeList.size(); i++) {
						CaseTypeForExcel tmpIni=iniCaseTypeList.get(i);
						CaseTypeForExcel tmpEnd=endCaseTypeList.get(i);
						
						//详细分类的大小为有一个为0
						if(tmpIni.getCaseDetailTypeForExcelList().size()==0 || tmpEnd.getCaseDetailTypeForExcelList().size()==0){
							if(tmpIni.getCaseDetailTypeForExcelList().size()==0 && tmpEnd.getCaseDetailTypeForExcelList().size() > 0){
								tmpIni.setRowCount(tmpEnd.getCaseDetailTypeForExcelList().size());
								count+=tmpEnd.getCaseDetailTypeForExcelList().size();
							}else if(tmpIni.getCaseDetailTypeForExcelList().size()>0 && tmpEnd.getCaseDetailTypeForExcelList().size()==0){
								tmpEnd.setRowCount(tmpIni.getCaseDetailTypeForExcelList().size());
								count+=tmpIni.getCaseDetailTypeForExcelList().size();
							}else if(tmpIni.getCaseDetailTypeForExcelList().size()==0 && tmpEnd.getCaseDetailTypeForExcelList().size()==0){
								count++;
							}
						}else if(tmpIni.getCaseDetailTypeForExcelList().size()>=tmpEnd.getCaseDetailTypeForExcelList().size()){
							tmpEnd.setRowCount(tmpIni.getCaseDetailTypeForExcelList().size());
							count+=tmpIni.getCaseDetailTypeForExcelList().size();
						}else{
							tmpIni.setRowCount(tmpEnd.getCaseDetailTypeForExcelList().size());
							count+=tmpEnd.getCaseDetailTypeForExcelList().size();
						}
					}
					//计算多出的初步分类
					for (int j = endCaseTypeList.size(); j < iniCaseTypeList.size(); j++) {
						if(iniCaseTypeList.get(j).getCaseDetailTypeForExcelList().size()>0){
							count+=iniCaseTypeList.get(j).getCaseDetailTypeForExcelList().size();
						}else{
							count+=1;
						}
					}
				}
				//最终分类多于初步分类
				if(iniCaseTypeList.size()<endCaseTypeList.size()){
					for (int i = 0; i < iniCaseTypeList.size(); i++) {
						CaseTypeForExcel tmpIni=iniCaseTypeList.get(i);
						CaseTypeForExcel tmpEnd=endCaseTypeList.get(i);
						if(tmpIni.getCaseDetailTypeForExcelList().size()==0 || tmpEnd.getCaseDetailTypeForExcelList().size()==0){
							if(tmpIni.getCaseDetailTypeForExcelList().size()==0 && tmpEnd.getCaseDetailTypeForExcelList().size() > 0){
								tmpIni.setRowCount(tmpEnd.getCaseDetailTypeForExcelList().size());
								count+=tmpEnd.getCaseDetailTypeForExcelList().size();
							}else if(tmpIni.getCaseDetailTypeForExcelList().size()>0 && tmpEnd.getCaseDetailTypeForExcelList().size()==0){
								tmpEnd.setRowCount(tmpIni.getCaseDetailTypeForExcelList().size());
								count+=tmpIni.getCaseDetailTypeForExcelList().size();
							}else if(tmpIni.getCaseDetailTypeForExcelList().size()==0 && tmpEnd.getCaseDetailTypeForExcelList().size()==0){
								count++;
							}
						}else if(tmpIni.getCaseDetailTypeForExcelList().size()>=tmpEnd.getCaseDetailTypeForExcelList().size()){
							tmpEnd.setRowCount(tmpIni.getCaseDetailTypeForExcelList().size());
							count+=tmpIni.getCaseDetailTypeForExcelList().size();
						}else{
							tmpIni.setRowCount(tmpEnd.getCaseDetailTypeForExcelList().size());
							count+=tmpEnd.getCaseDetailTypeForExcelList().size();
						}
					}
					for (int j = iniCaseTypeList.size(); j < endCaseTypeList.size(); j++) {
						if(endCaseTypeList.get(j).getCaseDetailTypeForExcelList().size()>0){
							count+=endCaseTypeList.get(j).getCaseDetailTypeForExcelList().size();
						}else{
							count+=1;
						}
					}
				}
				
			}else{
				//案件最终分类为NULL
				for (int i = 0; i < iniCaseTypeList.size(); i++) {
					CaseTypeForExcel tmpCaseTypeForExcel=iniCaseTypeList.get(i);
					if(tmpCaseTypeForExcel.getCaseDetailTypeForExcelList().size()>0){
						count+=tmpCaseTypeForExcel.getCaseDetailTypeForExcelList().size();
					}else{
						count+=1;
					}
				}
			}
		}else{
			//案件初步分类为NULL
			if(endCaseTypeList.size()>0){
				for (int i = 0; i < endCaseTypeList.size(); i++) {
					CaseTypeForExcel tmpCaseType=endCaseTypeList.get(i);
					if(tmpCaseType.getCaseDetailTypeForExcelList().size()>0){
						count+=tmpCaseType.getCaseDetailTypeForExcelList().size();
					}else{
						count+=1;
					}
				}
			}
		}
		
		if(count==0){
			return 1;
		}
		return count;
	}

	/**
	 * 导出部门
	 */
	private void importCaseTypeDepart(HSSFWorkbook wb,HSSFSheet sheet,HSSFCellStyle textCellStyle,HSSFCellStyle mergerCellStyle,
			int rowIndex,int columnIndex,List<ComplainForExcel> complainForExcelList,
			List<ComplainForExcel> complainDepartForExcelList,int maxDepartCount){
		if(complainForExcelList!=null&&complainForExcelList.size()>0&&complainDepartForExcelList!=null&&complainDepartForExcelList.size()>0&&
				complainForExcelList.size()==complainDepartForExcelList.size()){
			for (int i = 0; i < complainForExcelList.size(); i++) {
				List<DepartForExcel> departForExcelList=complainDepartForExcelList.get(i).getDepartForExcels();
				for (int j = 0; j < maxDepartCount; j++) {
					CellRangeAddress region1= new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j, columnIndex+j);
					CellRangeAddress region2=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxDepartCount, columnIndex+j+maxDepartCount);
					CellRangeAddress region3=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxDepartCount*2, columnIndex+j+maxDepartCount*2);
					
					sheet.addMergedRegion(region1);
					sheet.addMergedRegion(region2);
					sheet.addMergedRegion(region3);
					
					/*POIUtil.createRangeCell(wb,sheet,region1);
					POIUtil.createRangeCell(wb,sheet,region2);
					POIUtil.createRangeCell(wb,sheet,region3);*/
					
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region1);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region2);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region3);
					
					if(departForExcelList.size() > j){
						Row row=POIUtil.createRow(sheet, rowIndex);
						
						CellUtil.createCell(row,columnIndex+j,departForExcelList.get(j).getFdeptname()).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxDepartCount,departForExcelList.get(j).getFcityname()).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxDepartCount*2,departForExcelList.get(j).getFsaledepart()).setCellStyle(textCellStyle);
						
					}
				}
				rowIndex+=complainForExcelList.get(i).getRowCount();
			}
		}
	} 
	
	/**
	 * 收费
	 */
	private void importCaseTypeChargePerson(HSSFWorkbook wb,HSSFSheet sheet,HSSFCellStyle textCellStyle,HSSFCellStyle mergerCellStyle,
			int rowIndex,int columnIndex,List<ComplainForExcel> complainForExcelList,
			List<ComplainForExcel> complainChargeForExcelList,int maxChargeCount){
		if(complainForExcelList!=null&&complainForExcelList.size()>0&&complainChargeForExcelList!=null&&complainChargeForExcelList.size()>0&&
				complainForExcelList.size()==complainChargeForExcelList.size()){
			for (int i = 0; i < complainForExcelList.size(); i++) {
				List<ChargeInfoForExcel> chargeInfoForExcelList=complainChargeForExcelList.get(i).getChargeInfoForExcelList();
				
				for (int j = 0; j < maxChargeCount; j++) {
					CellRangeAddress region1=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j, columnIndex+j);
					
					CellRangeAddress region2= new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxChargeCount, columnIndex+j+maxChargeCount);
					
					CellRangeAddress region3=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxChargeCount*2, columnIndex+j+maxChargeCount*2);
					
					CellRangeAddress region4=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxChargeCount*3, columnIndex+j+maxChargeCount*3);
					
					sheet.addMergedRegion(region1);
					sheet.addMergedRegion(region2);
					sheet.addMergedRegion(region3);
					sheet.addMergedRegion(region4);
					
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region1);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region2);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region3);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region4);
					
					/*POIUtil.createRangeCell(wb, sheet, region1);
					POIUtil.createRangeCell(wb, sheet, region2);
					POIUtil.createRangeCell(wb, sheet, region3);
					POIUtil.createRangeCell(wb, sheet, region4);*/
					
					if(chargeInfoForExcelList.size()>j){
						Row row=POIUtil.createRow(sheet, rowIndex);
						
						String freceiveType=ChargeDictionary.getFreceiveType(chargeInfoForExcelList.get(j).getFreceiveType());
						String ftype=ChargeDictionary.getFtype(chargeInfoForExcelList.get(j).getFtype());
						String fmode=ChargeDictionary.getFmode(chargeInfoForExcelList.get(j).getFmode());	
						
						CellUtil.createCell(row,columnIndex+j,freceiveType).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxChargeCount,ftype).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxChargeCount*2,fmode).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxChargeCount*3,chargeInfoForExcelList.get(j).getFamount()).setCellStyle(textCellStyle);
						
					}
				}
				rowIndex+=complainForExcelList.get(i).getRowCount();
			}
		}
		
	
	}

	/**
	 * 内部人员
	 */
	private void importCaseTypeInnerPerson(HSSFWorkbook wb,HSSFSheet sheet,HSSFCellStyle textCellStyle,HSSFCellStyle mergerCellStyle,
			int rowIndex,int columnIndex,List<ComplainForExcel> complainForExcelList,
			List<ComplainForExcel> complainInnerForExcelList,int maxInnerCount){
		if(complainForExcelList!=null&&complainForExcelList.size()>0&&complainInnerForExcelList!=null&&complainInnerForExcelList.size()>0&&
				complainForExcelList.size()==complainInnerForExcelList.size()){
			for (int i = 0; i < complainForExcelList.size(); i++) {
				List<PersonForExcel> innerForExcelList=complainInnerForExcelList.get(i).getPersonForExcelList();
				
				for (int j = 0; j < maxInnerCount; j++) {
					CellRangeAddress region1=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j, columnIndex+j);
					
					CellRangeAddress region2= new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxInnerCount, columnIndex+j+maxInnerCount);
					
					CellRangeAddress region3=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxInnerCount*2, columnIndex+j+maxInnerCount*2);
					
					CellRangeAddress region4=new CellRangeAddress(rowIndex, rowIndex+complainForExcelList.get(i).getRowCount()-1,
							columnIndex+j+maxInnerCount*3, columnIndex+j+maxInnerCount*3);
					
					sheet.addMergedRegion(region1);
					sheet.addMergedRegion(region2);
					sheet.addMergedRegion(region3);
					sheet.addMergedRegion(region4);
					
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region1);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region2);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region3);
					POIUtil.setMergerBorder(sheet, mergerCellStyle, region4);
					
					/*POIUtil.createRangeCell(wb, sheet, region1);
					POIUtil.createRangeCell(wb, sheet, region2);
					POIUtil.createRangeCell(wb, sheet, region3);
					POIUtil.createRangeCell(wb, sheet, region4);*/
					
					if(innerForExcelList.size()>j){
						Row row=POIUtil.createRow(sheet, rowIndex);
						
						CellUtil.createCell(row,columnIndex+j,innerForExcelList.get(j).getFname()).setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxInnerCount,"").setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxInnerCount*2,"").setCellStyle(textCellStyle);
						CellUtil.createCell(row,columnIndex+j+maxInnerCount*3,"").setCellStyle(textCellStyle);
						
					}
				}
				rowIndex+=complainForExcelList.get(i).getRowCount();
			}
		}
	}
	@Override
	public Integer findPersonCompCountExceptSelf(HttpServletRequest request) {
		String fnameStr = request.getParameter("fname");
		String fid=request.getParameter("fid");
		/*String fname = null;
		try {
			//使用平台默认的编码重新构造一个字符串
			fname = new String(fnameStr.getBytes("ISO-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fname", fnameStr);
		map.put("fid", fid);
		return commonMapper.findPersonCompCountExceptSelf(map);
	}
	@Override
	public String findPersonRelComplianExcepSelf(HttpServletRequest request) {
		String fnameStr = request.getParameter("fname");
		String fid=request.getParameter("fid");
		/*String fname = null;
		try {
			//使用平台默认的编码重新构造一个字符串
			fname = new String(fnameStr.getBytes("ISO-8859-1"),"GBK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fname", fnameStr);
		map.put("fid", fid);
		List<Map<String,Object>> mapList = commonMapper.findPersonRelComplianExceptSelf(map);
		return StringUtil.convertListToGson(mapList);
	
	}
	
	@Override
	public String findCaseFromType() throws Exception{
		List<Dictionary> listDictionary = commonMapper.findCaseFrom();
		return StringUtil.convertListToGson(listDictionary);
	}
	
}
