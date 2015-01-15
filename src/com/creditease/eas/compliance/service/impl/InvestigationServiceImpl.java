/**
 * 
 */
package com.creditease.eas.compliance.service.impl;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.EmailConfigMapper;
import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.compliance.bean.BaseType;
import com.creditease.eas.compliance.bean.CaseDetailType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.compliance.bean.Investigation;
import com.creditease.eas.compliance.bean.NextCommunicateTimeForEmail;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.compliance.dao.ComplainCommonMapper;
import com.creditease.eas.compliance.dao.ComplainMapper;
import com.creditease.eas.compliance.dao.InvestigationMapper;
import com.creditease.eas.compliance.dao.PersonMapper;
import com.creditease.eas.compliance.service.ICaseInfoService;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.Constant;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.InvestigatonEmailUtil;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
/**
 * @InvestigationServiceImpl.java	合规初步调查service实现类
 * created at 2013-10-7 下午04:01:17 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class InvestigationServiceImpl implements InvestigationService {
	/** 合规初步调查DAO  **/
	@Autowired
	private InvestigationMapper investigationMapper;
	
	@Autowired
	private ComplainMapper complainMapper;
	
	@Resource
	private ComplainCommonMapper commonMapper;
	public void setCommonMapper(ComplainCommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}
	
	@Autowired
	private PersonMapper personMapper;
	/** 合规初步调查Entity**/
	private Investigation investigation;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(InvestigationServiceImpl.class);

	public int insert(Investigation investigation) throws Exception {
		return investigationMapper.insert(investigation);
	}

	public int delete(Integer id) throws Exception {
		return investigationMapper.deleteByPrimaryKey(id);
	}
	public int update(Investigation investigation) throws Exception {
		return investigationMapper.updateByPrimaryKey(investigation);//修改
	}
	public Investigation getInvestigationById(Integer id) throws Exception {
		investigation = investigationMapper.selectByPrimaryKey(id);
		return investigation;
	}
	
	@Autowired
	private EmailConfigMapper emailConfigMapper;
	public void setEmailConfigMapper(EmailConfigMapper emailConfigMapper) {
		this.emailConfigMapper = emailConfigMapper;
	}
	
	private boolean isContailEmail(String singleEmail,String moreEmail){
		if(moreEmail != null && !"".equals(moreEmail)){
			String [] emails = moreEmail.split(";");
			for (int i = 0; i < emails.length; i++) {
				if(singleEmail.equals(emails[i])){
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		User user = (User)request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		map.put("email", user.getUsername());//系统登陆用户
		map.put("createrId", user.getId().intValue());
		map.put("heguiAdmin", Constant.HEGUIADMIN);
		
		EmailConfig emailConfig1= emailConfigMapper.getEmailConfigByFid(3);
		EmailConfig emailConfig2= emailConfigMapper.getEmailConfigByFid(4);
		EmailConfig emailConfig3= emailConfigMapper.getEmailConfigByFid(5);
		
		int roleId = 0;
		if(isContailEmail(user.getUsername(),emailConfig1.getFemail())){
			roleId = 1;
		}
		if(isContailEmail(user.getUsername(),emailConfig2.getFemail())){
			roleId = 2;
		}
		if(isContailEmail(user.getUsername(),emailConfig3.getFemail())){
			roleId = 3;
		}
		
		map.put("roleId", roleId);
		
		map.put("firstAuditCfglzn", Constant.COMPLIANCE_FIRST_AUDIT_CFGLZN);
		map.put("firstAuditPhjr", Constant.COMPLIANCE_FIRST_AUDIT_PHJR);
		map.put("finalAudit", Constant.COMPLIANCE_FINAL_AUDIT);
		
		map.put("type", request.getParameter("type"));//用以区分列表(1调查列表，2待办列表，3已办列表)
		map.put("compStartTime", request.getParameter("compStartTime"));//投诉开始时间
		map.put("compEndTime", request.getParameter("compEndTime"));//投诉结束时间
		map.put("complainanter", request.getParameter("complainanter"));//投诉人
		map.put("customerName", request.getParameter("customerName"));//客户姓名
		map.put("iniCaseTypeId", request.getParameter("iniCaseTypeId"));//案件初步分类
		map.put("responsibleName", request.getParameter("responsibleName"));//案件负责人
		map.put("cusSourceId", request.getParameter("cusSourceId"));//客户来源
		map.put("loanBread", request.getParameter("loanBread"));//贷款品种
		map.put("state", request.getParameter("investigationState"));
		map.put("fcompchannelid", request.getParameter("fcompchannelid"));
		map.put("findex", request.getParameter("findex"));
		
		map.put("communicateStartTime", request.getParameter("communicateStartTime"));
		map.put("communicateEndTime", request.getParameter("communicateEndTime"));
		
		
		//查询总行数的方法
		int totalCounts = investigationMapper.getTotalCounts(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = investigationMapper.queryData(map1);
		page.setRows(list);
		return page;
	}
	@SuppressWarnings("unchecked")
	public List<Map> getNextSchemeIds() throws Exception{
		return investigationMapper.getNextSchemeIds();
	}
	@SuppressWarnings("unchecked")
	public List<Map> getTypeIds() throws Exception{
		return investigationMapper.getTypeIds();
	}
	public List<Investigation> getChildInvestigations(Integer parentId) throws Exception{
		return investigationMapper.getChildInvestigations(parentId);
	}
	@SuppressWarnings("unchecked")
	public void updateStatus(Map map) throws Exception {
		investigationMapper.updateStatus(map);
	}
	@SuppressWarnings("unchecked")
	public boolean sendEmail(Map map) throws Exception{
		int statusId = Integer.parseInt(map.get("statusId").toString());
		int investigationId = Integer.parseInt(map.get("id").toString());
		//案件信息
		Complain complain = this.getComplainByInvestigationId(investigationId);
		//接口人邮件（发送预警邮件时邮件接收人地址）
		String receieveEmail = complain.getFresponsibleEmail();
		boolean sendSuccess = false;//是否发送成功
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("HEGUI_USERNAME");
		String[] toAddress = {receieveEmail};
		toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		String logoPath = pr.getProperty("logoPath");
		
		EmailConfig emailConfig1= emailConfigMapper.getEmailConfigByFid(5);//终审人员
		EmailConfig emailConfig2= emailConfigMapper.getEmailConfigByFid(4);//初审人员
		EmailConfig emailConfig3= emailConfigMapper.getEmailConfigByFid(3);//初审人员
		
		String htmlContent = ContractHtmlContent.htmlContent2CaseChargeMan(complain,statusId,emailConfig1.getFemail(),emailConfig2.getFemail(),emailConfig3.getFemail());
		String[] ccs = null;
		//需要给案件负责人发送邮件(给审批人员的邮件也不发）
		//（1、继续调查 2、被投诉部门调查处理待审批 3、被投诉部门调查处理待反馈 4、结案（特殊结案）待初审 5、本部门实地调查待审批 不需要发送邮件）
		if(statusId!=2 && statusId!=3 && statusId!=6 && statusId!=8 && statusId!=11 && statusId!=13
				&& statusId!=15 && statusId!=16){
			sendSuccess = SendMailUtil.sendComplianceMail(fromAddress, toAddress, 
					complain.getFnumber()+"案件进度跟进", htmlContent, logoPath,ccs);
		}
		//待审批的需要给审批人员（初审/终审）发送邮件
		if(statusId==3 || statusId==8 || statusId==13 || statusId==15 || statusId==16){
			if(statusId==15){
				//String[] firstAuditPersonEmail = {Constant.COMPLIANCE_FINAL_AUDIT};
				//EmailConfig emailConfig= emailConfigMapper.getEmailConfigByFid(5);//终审人员
				if(emailConfig1 != null && emailConfig1.getFemail() != null &&!"".equals(emailConfig1.getFemail())){
					String[] firstAuditPersonEmail = emailConfig1.getFemail().split(";");
					//firstAuditPersonEmail = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
					sendSuccess = SendMailUtil.sendComplianceMail(fromAddress, firstAuditPersonEmail, 
							complain.getFnumber()+"案件进度跟进", htmlContent, logoPath,ccs);
				}
			}else{
				String[] secondAuditPersonEmail=null;
				if(2==complain.getFservicetypeid()||3==complain.getFservicetypeid()){
					//EmailConfig emailConfig= emailConfigMapper.getEmailConfigByFid(4);//初审人员
					if(emailConfig2 != null && emailConfig2.getFemail() != null && !"".equals(emailConfig2.getFemail())){
						secondAuditPersonEmail=emailConfig2.getFemail().split(";");
					}
					//secondAuditPersonEmail={Constant.COMPLIANCE_FIRST_AUDIT_CFGLZN};
				}else{
					//EmailConfig emailConfig= emailConfigMapper.getEmailConfigByFid(3);//初审人员
					if(emailConfig3 != null && emailConfig3.getFemail() != null && !"".equals(emailConfig3.getFemail())){
						secondAuditPersonEmail=emailConfig3.getFemail().split(";");
					}
					//secondAuditPersonEmail={Constant.COMPLIANCE_FIRST_AUDIT_PHJR};
				}
				/*String[] secondAuditPersonEmail = {(2==complain.getFservicetypeid()||3==complain.getFservicetypeid())?
						Constant.COMPLIANCE_FIRST_AUDIT_CFGLZN:Constant.COMPLIANCE_FIRST_AUDIT_PHJR};*/
				//secondAuditPersonEmail = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
				sendSuccess = SendMailUtil.sendComplianceMail(fromAddress, secondAuditPersonEmail, 
						complain.getFnumber()+"案件进度跟进", htmlContent, logoPath,ccs);
			} 
		}
		//要求反馈人员的邮箱
		String feedbackPersonEmail = map.get("feedbackPersonEmail")!=null?map.get("feedbackPersonEmail").toString():"";
		//待反馈的需要给反馈人员发送邮件
		if(feedbackPersonEmail!=null && !"".equals(feedbackPersonEmail)){
			String[] toAddress2fbpEmail = {feedbackPersonEmail};
			//toAddress2fbpEmail = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			htmlContent = ContractHtmlContent.htmlContent2FeedbackPerson(complain,statusId,feedbackPersonEmail);
			sendSuccess = SendMailUtil.sendComplianceMail(fromAddress, toAddress2fbpEmail, 
					complain.getFnumber()+"案件反馈要求", htmlContent, logoPath,ccs);
		}
		return sendSuccess;
	}
	@SuppressWarnings("unchecked")
	public List<Map> getCusSources() throws Exception {
		return investigationMapper.getCusSources();
	}
	@SuppressWarnings("unchecked")
	public List<Map> getIniCaseTypes() throws Exception {
		return investigationMapper.getIniCaseTypes();
	}
	public Complain getComplainByInvestigationId(Integer investigationId)
			throws Exception {
		return investigationMapper.getComplainByInvestigationId(investigationId);
	}
	public List<IFeedbackRequired> getFeedbackRequiredByInvestigationId(
			Integer investigationId) throws Exception {
		return investigationMapper.getFeedbackRequiredByInvestigationId(investigationId);
	}
	public List<IFeedback> getFeedbackByInvestigationId(
			Integer investigationId) throws Exception {
		return investigationMapper.getFeedbackByInvestigationId(investigationId);
	}
	public List<IFeedbackResult> getFeedbackResultByInvestigationId(Integer investigationId) throws Exception {
		return investigationMapper.getFeedbackResultByInvestigationId(investigationId);
	}
	public List<Fieldsurvey> getFieldsurveyByInvestigationId(Integer investigationId)
			throws Exception {
		return investigationMapper.getFieldsurveyByInvestigationId(investigationId);
	}
	public List<ZcxzlsResult> getZcxzlsfbResultByInvestigationId(Integer investigationId)
			throws Exception {
		return investigationMapper.getZcxzlsfbResultByInvestigationId(investigationId);
	}
	public List<Applysettlement> getApplysettlementByInvestigationId(
			Integer investigationId) throws Exception {
		return investigationMapper.getApplysettlementByInvestigationId(investigationId);
	}
	public List<AuditResult> getAuditResultsByInvestigationId(
			Integer investigationId) throws Exception {
		return investigationMapper.getAuditResultsByInvestigationId(investigationId);
	}
	public List<Map<String, Integer>> getComplainTypesByInvestigationId(
			Integer investigationId)throws Exception {
		return investigationMapper.getComplainTypesByInvestigationId(investigationId);
	}
	public List<Person> getCompalinPersonsByInvestigationId(
			Integer investigationId)throws Exception{
		return investigationMapper.getCompalinPersonsByInvestigationId(investigationId);
	}
	public List<DEPTINFO> getDeptinfosByInvestigationId(
			Integer investigationId)throws Exception{
		return investigationMapper.getDeptinfosByInvestigationId(investigationId);
	}
	public Person getPersonById(Integer personId) throws Exception{
		return personMapper.selectByPrimaryKey(new Long(personId.toString()));
	}
    /**
     * 展示文件信息列表
     */
	@Override
	public Pagination queryInvestFile(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		
		Map map = HashMap.class.newInstance();
		map.put("creatername", request.getParameter("creatername"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("filename", request.getParameter("filename"));
		map.put("fid", request.getParameter("fid"));
		//查询总行数的方法
		int totalCounts = investigationMapper.getInvestFileCounts(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = investigationMapper.queryInvestFile(map1);
		page.setRows(list);
		return page;
	}
   /**
    * 根据id查着文件信息
    */
	@Override
	public InvestFile findFileName(int id) {
		return investigationMapper.findFileName(id);
	}
	/**
	 * 根据investigationId修改信息
	 */

@Override
public int updateByinvestigation(IFeedbackRequired investigation) {
	// TODO Auto-generated method stub
	 return investigationMapper.updateByinvestigation(investigation);
}
/**
 * 查询修改邮箱列表页数据
 */
@SuppressWarnings("unchecked")
public Pagination queryPageForEmail(Pagination page) throws Exception {
	HttpServletRequest request = ServletActionContext.getRequest();
	int currentPage = PageUtil.strToPage(request.getParameter("page"));
	int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
	User user = (User)request.getSession().getAttribute("user");
	Map map = HashMap.class.newInstance();
	map.put("email", user.getUsername());//系统登陆用户
	map.put("createrId", user.getId().intValue());
	map.put("heguiAdmin", Constant.HEGUIADMIN);
	map.put("firstAuditCfglzn", Constant.COMPLIANCE_FIRST_AUDIT_CFGLZN);
	map.put("firstAuditPhjr", Constant.COMPLIANCE_FIRST_AUDIT_PHJR);
	map.put("finalAudit", Constant.COMPLIANCE_FINAL_AUDIT);
	
	map.put("type", request.getParameter("type"));//用以区分列表(1调查列表，2待办列表，3已办列表)
	map.put("compStartTime", request.getParameter("compStartTime"));//投诉开始时间
	map.put("compEndTime", request.getParameter("compEndTime"));//投诉结束时间
	map.put("complainanter", request.getParameter("complainanter"));//投诉人
	map.put("customerName", request.getParameter("customerName"));//客户姓名
	map.put("iniCaseTypeId", request.getParameter("iniCaseTypeId"));//案件初步分类
	map.put("responsibleName", request.getParameter("responsibleName"));//案件负责人
	map.put("cusSourceId", request.getParameter("cusSourceId"));//客户来源
	map.put("loanBread", request.getParameter("loanBread"));//贷款品种
	//查询总行数的方法
	int totalCounts = investigationMapper.getTotalCountsForEmail(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
	// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = investigationMapper.queryDataForEmail(map1);
		page.setRows(list);
	return page;
}


@Override
	public List<BaseType> getBaseTypesByInvestigationId(Integer investigationId) {
		// TODO Auto-generated method stub
		return investigationMapper.selectBaseTypeByInvestigationId(investigationId);
	}

	@Override
	public List<Map> getInvestigationState() {
		return investigationMapper.selectInvestigationState();
	}

	@Override
	public Pagination queryPageForCallBack(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		User user = (User)request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		map.put("email", user.getUsername());//系统登陆用户
		map.put("createrId", user.getId().intValue());
		map.put("heguiAdmin", Constant.HEGUIADMIN);
		map.put("firstAuditCfglzn", Constant.COMPLIANCE_FIRST_AUDIT_CFGLZN);
		map.put("firstAuditPhjr", Constant.COMPLIANCE_FIRST_AUDIT_PHJR);
		map.put("finalAudit", Constant.COMPLIANCE_FINAL_AUDIT);
		
		map.put("type", request.getParameter("type"));//用以区分列表(1调查列表，2待办列表，3已办列表)
		map.put("compStartTime", request.getParameter("compStartTime"));//投诉开始时间
		map.put("compEndTime", request.getParameter("compEndTime"));//投诉结束时间
		map.put("complainanter", request.getParameter("complainanter"));//投诉人
		map.put("customerName", request.getParameter("customerName"));//客户姓名
		map.put("iniCaseTypeId", request.getParameter("iniCaseTypeId"));//案件初步分类
		map.put("responsibleName", request.getParameter("responsibleName"));//案件负责人
		map.put("cusSourceId", request.getParameter("cusSourceId"));//客户来源
		map.put("loanBread", request.getParameter("loanBread"));//贷款品种
		//查询总行数的方法
		int totalCounts = investigationMapper.getTotalCountForCallBack(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = investigationMapper.queryDataForCallBack(map1);
		page.setRows(list);
		return page;
	
	}

	@Override
	public Investigation querySingleCallBackContent(Integer id)
			throws Exception {
		return investigationMapper.querySingleCallBackContent(id);
	}

	@Override
	public List<CaseType> getNewCaseType(Integer id) {
		List<CaseType> allTypeList=commonMapper.selectAllCaseType();
		
		List<CaseType> list=investigationMapper.getNewCaseType(id);
		
		//List<CaseType> ll=new ArrayList<CaseType>();
		
		//Set<Integer> set=new LinkedHashSet<Integer>();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CaseType cc =list.get(i);
				for (int j = 0; j < allTypeList.size(); j++) {
					CaseType c=allTypeList.get(j);
					if(StringUtil.objToInteger(c.getId())==StringUtil.objToInteger(cc.getId())){
						c.setIschecked(1);
						List<CaseDetailType> ll=c.getDetailCaseType();
						if(ll!=null&&ll.size()>0){
							for (int k = 0; k < ll.size(); k++) {
								CaseDetailType tt=ll.get(k);
								if(StringUtil.objToInteger(tt.getId())==StringUtil.objToInteger(cc.getDetailcasetypeid())){
									tt.setIschecked(1);
								}
							}
						}
					}
					
				}
			}
			/*
			for (int i = 0; i < list.size(); i++) {
				CaseType t=list.get(i);
				set.add(StringUtil.strToInt(t.getId()));
			}
			if(!set.isEmpty()){
				Iterator<Integer> it=set.iterator();
				while (it.hasNext()) {
					Integer integer=it.next();
					CaseType cc=new CaseType();
					for (int i = 0; i < list.size(); i++) {
						CaseType t=list.get(i);
						if(integer==StringUtil.strToInt(t.getId())){
							if(t.getDetailcasetypeid()!=null && !"".equals(t.getDetailcasetypeid())){
								CaseDetailType dd=new CaseDetailType();
								dd.setId(t.getDetailcasetypeid());
								dd.setName(t.getDetailcasetypename());
								cc.getDetailCaseType().add(dd);
							}
							cc.setId(t.getId());
							cc.setName(t.getName());
						}
					}
					ll.add(cc);
				}
			}
		*/}
		return allTypeList;
	}
	
	@Override
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		response.setContentType("application/vnd.ms-excel");
		String fileName = "项目信息列表";// 设置excel文件的名字
		String header = "attachment; filename="
				+ new String(URLDecoder.decode(fileName + ".xls", "UTF-8")
						.getBytes(), "iso8859-1");
		response.setHeader("Content-Disposition", header);
		OutputStream out = response.getOutputStream();

		String[] headers = { "序号", "贷款编号", "公司名称", "贷后管理负责人(PM)", "出借方式",
				"出借日", "到期日", "期限(月)", "利息返还日", "金额(万元)", "月利率(%)", "月管理费(%)",
				"认股权证", "出借方", "借款方", "还款方式", "还款账户", "开户行", "账号",
				"账户对应还款额(万元)", "状态" };
		String[] title = { "", "LOANNUMBER", "COMPANYNAME", "HEAD", "LOANWAY",
				"LOANTIME", "EXPIRETIME", "TERM", "INTERESTTIME", "MONEY",
				"MLYINTEREST", "MMANGEXPENSE", "AUTHENTICATION", "LENDERS",
				"BORROWER", "REPAYMENTWAY", "REPAYMENTACCOUNT", "BANKACCOUNT",
				"ACCOUNT", "REPAYMENTMONEY", "STATE" };
		
		User user = (User)request.getSession().getAttribute("user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		
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
			//判断是否是张然登陆，如果是张然登陆，可以看到所有的案件信息
			if("ranzhang@creditease.cn".equals(user.getUsername())||"heguiadmin".equals(user.getUsername())){
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
		/*int totalCounts = complainMapper.getTotalCountsByParams(map);
		//page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);*/
		//List<Map> list = complainMapper.queryPageByParamss(map2);
		//page.setRows(list);
		
		
		
		List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String,Object>>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFSheet sheet = wb.createSheet(fileName);// 设置页的名称
			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) 500);// 设置行高
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, 5000);// 设置单元格的宽度
				ExpExcelUtil.createTxtCell(wb, row, i, headers[i], ExpExcelUtil
						.createCollStyle(cellStyle, wb));// 创建表格第一行
			}
			if (mapList != null && !mapList.isEmpty()) {// 判断数据是否为空
				Field field = null;
				Object[] objs = mapList.toArray();// 转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1 = sheet.createRow(i + 1);// 创建行，设置从第二行开始
					row1.setHeight((short) 700);
					Map t = (Map) objs[i];
					for (int j = 0; j < title.length; j++) {
						if (title[j] != "") {
							String getMethodName = title[j];
							Object value = t.get(getMethodName);
							if ("REPAYMENTACCOUNT".equals(getMethodName)
									|| "BANKACCOUNT".equals(getMethodName)
									|| "ACCOUNT".equals(getMethodName)) {
								sheet.setColumnWidth(j, 10000);// 设置单元格的宽度
							}
							ExpExcelUtil.createTxtCell(wb, row1, j, value,
									ExpExcelUtil.createCollStyle(wb
											.createCellStyle(), wb));
						} else {
							ExpExcelUtil.createTxtCell(wb, row1, j, i + 1,
									ExpExcelUtil.createCollStyle(wb
											.createCellStyle(), wb));
						}
					}
				}
			}
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
			/*logger.error(e.getMessage());
			logger.error("导出项目信息失败");
			e.printStackTrace();*/
		}
		out.close();// 关闭
		response.flushBuffer();
	
		
	}
	
	/**
	 * 定时发送邮件
	 */
	public void sendWarnNextCommunicate(){
		String communicateTime=StringUtil.getTomorrowTime();
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("communicateStartTime", communicateTime+" 00:00:00");
		map.put("communicateEndTime", communicateTime+" 23:59:59");
		
		try {
			List<NextCommunicateTimeForEmail> list=investigationMapper.getNextCommunicateMap(map);
			
			InvestigatonEmailUtil.sendEmail(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
