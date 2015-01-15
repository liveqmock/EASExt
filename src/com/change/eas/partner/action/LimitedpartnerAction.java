package com.change.eas.partner.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.change.eas.partner.bean.BaseUnit;
import com.change.eas.partner.bean.City;
import com.change.eas.partner.bean.DocumentFile;
import com.change.eas.partner.bean.LawFile;
import com.change.eas.partner.bean.LawTemplate;
import com.change.eas.partner.bean.Limitedpartner;
import com.change.eas.partner.bean.Mark;
import com.change.eas.partner.bean.Partner;
import com.change.eas.partner.service.LawTemplateService;
import com.change.eas.partner.service.LimitedpartnerService;
import com.change.eas.partner.service.MarkService;
import com.change.eas.util.Config;
import com.change.eas.util.Excel;
import com.change.eas.util.IUtils;
import com.change.eas.util.LimitedpartnerUtil;
import com.change.eas.util.PageView;
import com.change.eas.util.StringUtil;
import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.service.RoleService;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.util.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class LimitedpartnerAction extends BaseAction {

	static Logger log = Logger.getLogger(LimitedpartnerAction.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private LimitedpartnerService limitedpartnerService;

	@Autowired
	private UserRoleService userRoleService;
	private int pageSize;

	@Autowired
	private RoleService roleservice;
	@Autowired
	private LawTemplateService lawTemplate;
	@Autowired
	private MarkService markService;

	public MarkService getMarkService() {
		return markService;
	}

	public void setMarkService(MarkService markService) {
		this.markService = markService;
	}

	private File file;

	private Long pid; // 信息采集明细表的父表id（信息采集表）

	private Limitedpartner limitedpartner; // 有限合伙公司

	private Partner partner; // 有限合伙人

	private List<Partner> listPartner;

	private List<LawFile> listLawFile;

	private List<DocumentFile> listDocumentFile;

	private DocumentFile documentFile;

	private int sum_partner; // 有限合伙人总数

	private double sum_amount; // 有限合伙人出资总额

	private User user; // 当前用户

	private User makeUser; // 制表人

	private User checkUser; // 审核人

	private String operation; // 暂存，提交

	private String change; // 工商变更，税务变更

	private String orderstatus; // 单据状态：未采集、未完成、已完成、已审核

	private List<Integer> userroleidList;// 当前用户的角色集合

	private Role role;

	private String department; // 投资理财部

	private String city; // 城市

	private String limitedpartnername; // 部门城市下的有限合伙公司名称

	private String fname_l2;// 金蝶系统中有限合伙名称

	private List<BaseUnit> listBaseUnit; // 金蝶系统中有限合伙实体

	private String fname_l2Array;

	private String calculationmethod; // 比例计算方式：标准，倒减

	private int figure; // 出资比例计算位数：1,2,3,4

	private double sumAccount; // 加上gp出资额后的总出资额

	private double gpAccountPercent; // gp出资比例

	private String gpAccountPercentStr; // gp出资比例（百分比且字符串形式）

	private Long RecordID;// lawtemplate模板的recordid

	private String error = "";

	private int row; // 导入excel时出错的行号

	private String message = "";

	private int flag; // 0:工商变更 1:税务变更

	private String gcchangedatestr; // 工商变更完成时间（字符串形式）

	private String shuiwuchnangedatestr; // 税务变更完成时间（字符串形式）

	private int number;

	private String fileName; // 文件名称

	private String changetime; // 工商变更或税务变更后的时间

	public String getChangetime() {
		return changetime;
	}

	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getGcchangedatestr() {
		return gcchangedatestr;
	}

	public void setGcchangedatestr(String gcchangedatestr) {
		this.gcchangedatestr = gcchangedatestr;
	}

	public String getShuiwuchnangedatestr() {
		return shuiwuchnangedatestr;
	}

	public void setShuiwuchnangedatestr(String shuiwuchnangedatestr) {
		this.shuiwuchnangedatestr = shuiwuchnangedatestr;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGpAccountPercentStr() {
		return gpAccountPercentStr;
	}

	public void setGpAccountPercentStr(String gpAccountPercentStr) {
		this.gpAccountPercentStr = gpAccountPercentStr;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public DocumentFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(DocumentFile documentFile) {
		this.documentFile = documentFile;
	}

	public List<DocumentFile> getListDocumentFile() {
		return listDocumentFile;
	}

	public void setListDocumentFile(List<DocumentFile> listDocumentFile) {
		this.listDocumentFile = listDocumentFile;
	}

	public double getSumAccount() {
		return sumAccount;
	}

	public void setSumAccount(double sumAccount) {
		this.sumAccount = sumAccount;
	}

	public double getGpAccountPercent() {
		return gpAccountPercent;
	}

	public void setGpAccountPercent(double gpAccountPercent) {
		this.gpAccountPercent = gpAccountPercent;
	}

	public String getCalculationmethod() {
		return calculationmethod;
	}

	public void setCalculationmethod(String calculationmethod) {
		this.calculationmethod = calculationmethod;
	}

	public int getFigure() {
		return figure;
	}

	public void setFigure(int figure) {
		this.figure = figure;
	}

	public double getSum_amount() {
		return sum_amount;
	}

	public void setSum_amount(double sumAmount) {
		sum_amount = sumAmount;
	}

	public int getSum_partner() {
		return sum_partner;
	}

	public void setSum_partner(int sumPartner) {
		sum_partner = sumPartner;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public List<Partner> getListPartner() {
		return listPartner;
	}

	public void setListPartner(List<Partner> listPartner) {
		this.listPartner = listPartner;
	}

	public List<LawFile> getListLawFile() {
		return listLawFile;
	}

	public void setListLawFile(List<LawFile> listLawFile) {
		this.listLawFile = listLawFile;
	}

	List<Limitedpartner> limitedpartnerList;

	public List<Limitedpartner> getLimitedpartnerList() {
		return limitedpartnerList;
	}

	public void setLimitedpartnerList(List<Limitedpartner> limitedpartnerList) {
		this.limitedpartnerList = limitedpartnerList;
	}

	public Limitedpartner getLimitedpartner() {
		return limitedpartner;
	}

	public void setLimitedpartner(Limitedpartner limitedpartner) {
		this.limitedpartner = limitedpartner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public User getMakeUser() {
		return makeUser;
	}

	public void setMakeUser(User makeUser) {
		this.makeUser = makeUser;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public List<Integer> getUserroleidList() {
		return userroleidList;
	}

	public void setUserroleidList(List<Integer> userroleidList) {
		this.userroleidList = userroleidList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLimitedpartnername() {
		return limitedpartnername;
	}

	public void setLimitedpartnername(String limitedpartnername) {
		this.limitedpartnername = limitedpartnername;
	}

	public String getFname_l2() {
		return fname_l2;
	}

	public void setFname_l2(String fnameL2) {
		fname_l2 = fnameL2;
	}

	public List<BaseUnit> getListBaseUnit() {
		return listBaseUnit;
	}

	public void setListBaseUnit(List<BaseUnit> listBaseUnit) {
		this.listBaseUnit = listBaseUnit;
	}

	public String getFname_l2Array() {
		return fname_l2Array;
	}

	public void setFname_l2Array(String fnameL2Array) {
		fname_l2Array = fnameL2Array;
	}

	public Long getRecordID() {
		return RecordID;
	}

	public void setRecordID(Long recordID) {
		RecordID = recordID;
	}

	User currentUser() {
		return (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
	}

	/**
	 * 导入有限合伙人信息
	 */
	private String fileFileName;

	public String uploadTemplate() throws Exception {
		try {
			if (!fileFileName.endsWith(".xls")
					&& !fileFileName.endsWith(".xlsx")) {
				error = "导入文件类型有误，请导入后缀名为.xls或者.xlsx的文件！";
				return "todetailByerror";
			}
			InputStream in = new FileInputStream(file);
			Excel.checkIsExcel2003(fileFileName);
			Excel excel = Excel.getExcelInstance(in);
			excel.analysisExcel();
			Map<Integer, Map<Integer, String>> map = excel.getValueMap();
			for (int j = 2; j < map.size(); j++) {
				if (map.get(j).get(1) == null
						|| ("").equals(map.get(j).get(1).trim())
						|| map.get(j).get(2) == null
						|| ("").equals(map.get(j).get(2).trim())
						|| map.get(j).get(3) == null
						|| ("").equals(map.get(j).get(3).trim())
						|| map.get(j).get(4) == null
						|| ("").equals(map.get(j).get(4).trim())
						|| map.get(j).get(5) == null
						|| ("").equals(map.get(j).get(5).trim())
						|| !IUtils.validateCard(map.get(j).get(5))
						|| map.get(j).get(6) == null
						|| ("").equals(map.get(j).get(6).trim())
						|| map.get(j).get(7) == null
						|| ("").equals(map.get(j).get(7).trim())
						|| !IUtils.isNumber(map.get(j).get(7))
						|| !IUtils.verificationOfDateIsCorrect(map.get(j)
								.get(8))) {
					row = j + 1; // 出错行
					if (!IUtils.validateCard(map.get(j).get(5)))
						error = "导入有误,所导入的文件中的第" + row
								+ "行证件号码格式有误或者证件号码是无效的，请检查该行再导入！";
					else if (!IUtils.isNumber(map.get(j).get(7)))
						error = "导入有误,所导入的文件中的第" + row
								+ "行GP出资额格式有误，只能接受非负正数，且不能为0，请检查该行再导入！";
					else if (!IUtils.verificationOfDateIsCorrect(map.get(j)
							.get(8)))
						error = "导入有误,所导入的文件中的第" + row + "行出资时间格式有误，请检查该行再导入";
					else
						error = "导入有误,所导入的文件中的第" + row + "行存在空值，请检查该行再导入！";
					return "todetailByerror";
				}
			}
			List listIDCard = new ArrayList();
			for (int i = 2; i < map.size(); i++) {
				listIDCard.add(map.get(i).get(5));
			}
			for (int i = 0; i < listIDCard.size(); i++) {
				for (int j = i + 1; j < listIDCard.size(); j++) {
					if (listIDCard.get(i).equals(listIDCard.get(j))) {
						i = i + 3;
						j = j + 3;
						error = "导入有误，所导入的文件中第" + i + "行与第" + j
								+ "行的证件号码重复，证件号码必须唯一,请检查该行再导入！";
						return "todetailByerror";
					}
				}
			}

			List<Partner> listPartner = limitedpartnerService
					.getPartnerAllIDCard(pid);
			for (int i = 0; i < listIDCard.size(); i++) {
				for (int j = 0; j < listPartner.size(); j++) {
					Partner partner = listPartner.get(j);
					if (listIDCard.get(i).equals(partner.getPartner_IDCard())) {
						error = "导入有误，所导入文件中的证件号码与数据库中有重复，证件号码必须唯一,重复的证件号码为:"
								+ partner.getPartner_IDCard() + ",请检查该行再导入";
						return "todetailByerror";
					}
				}
			}
			for (int i = 2; i < map.size(); i++) {
				Partner par = new Partner();
				if ("".equalsIgnoreCase(map.get(i).get(0))
						|| map.get(i).get(0) == null) {
					continue;
				}
				par.setPartner_name(map.get(i).get(1).trim());
				par.setPartner_sex(map.get(i).get(2).trim());
				par.setPartner_nation(map.get(i).get(3).trim());
				par.setPartner_addr(map.get(i).get(4).trim());
				par.setPartner_IDCard(map.get(i).get(5).trim());
				par.setPartner_country(map.get(i).get(6).trim());
				par.setPartner_amount(Double.valueOf(map.get(i).get(7)));
				par.setPartner_date(map.get(i).get(8).trim());
				par.setPartner_note(map.get(i).get(9).trim());
				par.setPid(pid);
				limitedpartnerService.insertPartner(par);
				log.info(currentUser().getUsername() + "为有限合伙公司添加有限合伙人!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		return "todetailByerror";
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void uploadTemplate_ajax() throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!fileFileName.endsWith(".xls")
					&& !fileFileName.endsWith(".xlsx")) {
				error = "导入文件类型有误，请导入后缀名为.xls或者.xlsx的文件！";
				response.getWriter().print(error);
				return;
			}
			InputStream in = new FileInputStream(file);
			Excel.checkIsExcel2003(fileFileName);
			Excel excel = Excel.getExcelInstance(in);
			excel.analysisExcel();
			Map<Integer, Map<Integer, String>> map = excel.getValueMap();
			for (int j = 2; j < map.size(); j++) {
				if (map.get(j).get(1) == null
						|| ("").equals(map.get(j).get(1).trim())
						|| map.get(j).get(2) == null
						|| ("").equals(map.get(j).get(2).trim())
						|| map.get(j).get(3) == null
						|| ("").equals(map.get(j).get(3).trim())
						|| map.get(j).get(4) == null
						|| ("").equals(map.get(j).get(4).trim())
						|| map.get(j).get(5) == null
						|| ("").equals(map.get(j).get(5).trim())
						|| !IUtils.validateCard(map.get(j).get(5))
						|| map.get(j).get(6) == null
						|| ("").equals(map.get(j).get(6).trim())
						|| map.get(j).get(7) == null
						|| ("").equals(map.get(j).get(7).trim())
						|| !IUtils.isNumber(map.get(j).get(7))
						|| !IUtils.verificationOfDateIsCorrect(map.get(j)
								.get(8))) {
					row = j + 1; // 出错行
					if (!IUtils.validateCard(map.get(j).get(5)))
						error = "导入有误,所导入的文件中的第" + row
								+ "行证件号码格式有误或者证件号码是无效的，请检查该行再导入！";
					else if (!IUtils.isNumber(map.get(j).get(7)))
						error = "导入有误,所导入的文件中的第" + row
								+ "行GP出资额格式有误，只能接受非负正数，且不能为0，请检查该行再导入！";
					else if (!IUtils.verificationOfDateIsCorrect(map.get(j)
							.get(8)))
						error = "导入有误,所导入的文件中的第" + row + "行出资时间格式有误，请检查该行再导入";
					else
						error = "导入有误,所导入的文件中的第" + row + "行存在空值，请检查该行再导入！";
					
					response.getWriter().print(error);
					return;
				}
				
			}
			
			row=0;
			for (int j = 2; j < map.size(); j++) {
				if(map.get(j).get(2)!=null && map.get(j).get(5)!=null){
					row = j + 1; // 出错行
					String sex=map.get(j).get(2).trim();
					String checkSex=map.get(j).get(5).trim().substring(map.get(j).get(5).trim().length()-2,map.get(j).get(5).trim().length()-1);
					if(Integer.parseInt(checkSex)%2==0 && "男".equals(sex)){
						error = "导入有误,所导入的文件中的第" + row + "行记录性别为男性，而身份证号为女性，不符，请检查该行再导入！";
					  }else if(Integer.parseInt(checkSex)%2==1 && "女".equals(sex)){
						  error = "导入有误,所导入的文件中的第" + row + "行记录性别为女性，而身份证号为男性，不符，请检查该行再导入！";
					  }
				}
				response.getWriter().print(error);
				return;
			}

			List listIDCard = new ArrayList();
			for (int i = 2; i < map.size(); i++) {
				listIDCard.add(map.get(i).get(5));
			}
			for (int i = 0; i < listIDCard.size(); i++) {
				for (int j = i + 1; j < listIDCard.size(); j++) {
					if (listIDCard.get(i).equals(listIDCard.get(j))) {
						i = i + 3;
						j = j + 3;
						error = "导入有误，所导入的文件中第" + i + "行与第" + j
								+ "行的证件号码重复，证件号码必须唯一,请检查该行再导入！";
						response.getWriter().print(error);
						return;
					}
				}
			}

			List<Partner> listPartner = limitedpartnerService
					.getPartnerAllIDCard(pid);
			for (int i = 0; i < listIDCard.size(); i++) {
				for (int j = 0; j < listPartner.size(); j++) {
					Partner partner = listPartner.get(j);
					if (listIDCard.get(i).equals(partner.getPartner_IDCard())) {
						error = "导入有误，所导入文件中的证件号码与数据库中有重复，证件号码必须唯一,重复的证件号码为:"
								+ partner.getPartner_IDCard() + ",请检查该行再导入";
						response.getWriter().print(error);
						return;
					}
				}
			}

			for (int i = 2; i < map.size(); i++) {
				Partner par = new Partner();
				if ("".equalsIgnoreCase(map.get(i).get(0))
						|| map.get(i).get(0) == null) {
					continue;
				}
				par.setPartner_name(map.get(i).get(1).trim());
				par.setPartner_sex(map.get(i).get(2).trim());
				par.setPartner_nation(map.get(i).get(3).trim());
				par.setPartner_addr(map.get(i).get(4).trim());
				par.setPartner_IDCard(map.get(i).get(5).trim());
				par.setPartner_country(map.get(i).get(6).trim());
				par.setPartner_amount(Double.valueOf(map.get(i).get(7)));
				par.setPartner_date(map.get(i).get(8).trim());
				par.setPartner_note(map.get(i).get(9).trim());
				par.setPid(pid);
				limitedpartnerService.insertPartner(par);
				response.getWriter().print(error);
				log.info(currentUser().getUsername() + "为有限合伙公司添加有限合伙人!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void Sum_partner_total() throws Exception {
		try {
			sum_partner = limitedpartnerService.getTotalCountsByPid(pid);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(sum_partner);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取本地城市
	 */
	public String getLocalCity() {
		getCityTree();
		return "lawfile";
		
	}
    
	// TODO add by 0820 14:05
		public String addTemplatePage() {
			List<City> list = lawTemplate.getDept();
			request.setAttribute("deptList", list);
			return "addTemplatePage";
		}
	/**
	 * 获取本地部门和城市
	 */
		private void getCityTree() {
			String tree = lawTemplate.getCityTree();
			log.info(tree);
			String sub = tree.substring(0, tree.length() - 1);
			request.setAttribute("tree", sub);
		
//		StringBuffer sb = new StringBuffer();
//		sb.append("{id: 1,pId : 0,name:'投资理财一部',value:'1',t:'投资理财一部'}, ");
//		sb.append("{id: 11,pId : 1,name:'上海',value:'11',t:'上海'}, ");
//		sb.append("{id: 12,pId : 1,name:'成都',value:'12',t:'成都'}, ");
//		sb.append("{id: 13,pId : 1,name:'九江/共青城',value:'13',t:'九江/共青城'}, ");
//		sb.append("{id: 2,pId : 0,name:'投资理财二部',value:'1',t:'投资理财二部'}, ");
//		sb.append("{id: 21,pId : 2,name:'青岛',value:'21',t:'青岛'}, ");
//		sb.append("{id: 22,pId : 2,name:'烟台',value:'22',t:'烟台'}, ");
//		sb.append("{id: 23,pId : 2,name:'长春',value:'23',t:'长春'}, ");
//		sb.append("{id: 3,pId : 0,name:'投资理财三部',value:'1',t:'投资理财三部'}, ");
//		sb.append("{id: 31,pId : 3,name:'无锡',value:'31',t:'无锡'}, ");
//		sb.append("{id: 32,pId : 3,name:'南京',value:'32',t:'南京'}, ");
//		sb.append("{id: 33,pId : 3,name:'苏州',value:'33',t:'苏州'}, ");
//		sb.append("{id: 34,pId : 3,name:'芜湖',value:'34',t:'芜湖'}, ");
//		sb.append("{id: 35,pId : 3,name:'宁波',value:'35',t:'宁波'}, ");
//		List<LawTemplate> list = lawTemplate.listTemplate();
//		
//		
//		for (LawTemplate law : list) {
//			sb.append("{ ");
//			sb.append("id: 100" + law.getTemplateID() + ",");
//			sb.append("pId: " + law.getCityID() + ",");
//			sb.append("value: \"" + law.getRecordID() + "\",");
//			sb.append("name: \"" + law.getFileName() + "\",");
//			sb.append("t: \"" + law.getFileName() + "\"");
//			sb.append("}, ");
//		}
//		CharSequence sub = sb.subSequence(0, sb.length() - 2);
//		request.setAttribute("tree", sub.toString());
	}

	/**
	 * 为有限合伙公司下载模板
	 */
	public void downloadTemplate() throws IOException {
		try {
			response.setHeader("content-disposition",
					"attachment;filename=template.xls");
			ServletOutputStream out = response.getOutputStream();
			File file2 = new File(Config.getKey("template.file"));
			if (file2.exists() && file2.isFile()) {
				FileInputStream in = new FileInputStream(file2);
				byte[] by = new byte[102400];
				int length = 0;
				while ((length = in.read(by)) != -1) {
					out.write(by, 0, length);
				}
				out.close();
				in.close();
				log.info(currentUser().getUsername() + "为有限合伙公司下载模板!");
			} else {
				out.write("请联系管理员进行配置模板。".getBytes());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 下载法律文件
	 */
	public void downloadFile() throws IOException {
		try {
			response.setHeader("content-disposition",
					"attachment;filename=lawfile.zip");
			String ids = request.getParameter("ids");
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = lawTemplate.getFileInputStream(ids);
			byte[] by = new byte[102400];
			int length = 0;
			while ((length = in.read(by)) != -1) {
				out.write(by, 0, length);
			}
			out.close();
			in.close();
			log.info(currentUser().getUsername() + "为有限合伙公司下载法律!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String id;
	private String name;

	/**
	 * 建立法律文件
	 */
	public String toBuildLawFile() {
		getCityTree();
		log.info(currentUser().getUsername() + "建立法律文件!");
		return "buildLawfile";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		if (name == null) {
			return null;
		} else {
			try {
//				return new String(name.getBytes(Config.getKey("charset.from")), Config.getKey("charset.to"));
				return name;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}
	}

	public String  buildLawfileCheck() throws Exception {
		// error,pid,RecordID,city
		try {
			user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			
			Limitedpartner lp = limitedpartnerService.findLimitedpartnerById(pid);
	     	String	companyname=lp.getName();
			if (RecordID != null) {
				name = limitedpartnerService.getCityNameByTemplateRecordID(RecordID);
			} else if (city != null && !"".equals(city)) {
				name = Config.getCityMap(Long.parseLong(city));
			}
			
			
			if (lp.getName().contains(name)) {
//				response.sendRedirect(Config.getKey("word.url")
//						+ "/servlet/BuildFileServlet?id=" + pid + "&UserName="
//						+ user.getUsername() + "&RecordID=" + RecordID
//						+ "&city=" + city);
				 lawTemplate.setData(city,pid,RecordID,user.getUsername(),companyname);
			} else if (name.contains("九江") && lp.getName().contains("九江")) {
				/*response.sendRedirect(Config.getKey("word.url")
						+ "/servlet/BuildFileServlet?id=" + pid + "&UserName="
						+ user.getUsername() + "&RecordID=" + RecordID
						+ "&city=" + city);*/
			 	lawTemplate.setData(city,pid,RecordID,user.getUsername(),companyname);
			} else if (name.contains("共青城") && lp.getName().contains("共青城")) {
				/*response.sendRedirect(Config.getKey("word.url")
						+ "/servlet/BuildFileServlet?id=" + pid + "&UserName="
						+ user.getUsername() + "&RecordID=" + RecordID
						+ "&city=" + city);*/
				lawTemplate.setData(city,pid,RecordID,user.getUsername(),companyname);
			} else {
				id = pid.longValue() + "";
				name=new String(lp.getName().getBytes("UTF-8"),"ISO-8859-1");
				//name = new String(lp.getName().getBytes(Config.getKey("charset.from")), Config.getKey("charset.to"));
				/*response.sendRedirect(request.getContextPath()
						+ "/limitedpartner/limitedpartner!toBuildLawFile?id="
						+ pid + "&name=" + name + "&error=" + true);*/
			}
			return "buildLawfileCheck"; 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 查询有限合伙公司信息采集表
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson() throws Exception {
		try {
			listLawFile = limitedpartnerService
					.lawfilelist(new HashMap<String, Object>());
			Iterator<LawFile> iterator = listLawFile.iterator();
			while (iterator.hasNext()) {
				LawFile l = iterator.next();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", l.getLimitedpartnerid());
				params.put("undone", l.getUndone());
				limitedpartnerService.updateLawFileStatus(params); // 根据有限合伙公司已存在的法律文件完成情况
																	// 更新有限合伙公司的法律文件制作状态，工商变更及税务变更状态
			}

			limitedpartnerList = limitedpartnerService
					.selectLimitedpartnerList();
			for (int j = 0; j < limitedpartnerList.size(); j++) {
				Limitedpartner lp = limitedpartnerList.get(j);
				if (limitedpartnerService.getisexists(lp.getId()) == 0
						&& lp.getLfmakestatus() == 3) {
					limitedpartnerService
							.updateLimitedpartnerstatus(lp.getId()); // 更新不存在法律文件的有限合伙公司(且法律文件制作状态是未制作)的法律文件制作状态，工商变更及税务变更状态
				}
			}

			String roleName = "客户部";
			user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			userroleidList = userRoleService.userroleidlist(user.getId()
					.intValue());
			Role checkRole = roleservice.selectByPrimaryKey(Integer
					.parseInt(Config.getKey("heguiroleid"))); // 库表中合规审批权限id(102)
			for (int i = 0; i < userroleidList.size(); i++) {
				role = roleservice.selectByPrimaryKey(userroleidList.get(i));
				if (checkRole.getId().intValue() == role.getId().intValue()) {
					roleName = "合规部";
				}
			}
			this.pagination = limitedpartnerService.queryPage2(pagination,
					roleName);
			limitedpartnerList = pagination.getRows(); // 有限合伙人集合
			log.info(currentUser().getUsername() + "查看有限合伙公司信息表!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}

	public String queryPagedeatail() throws Exception {
		try {
			this.pagination = limitedpartnerService.queryPagedeatail(
					pagination, pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}

	public String queryPageDocumentFile() throws Exception {
		try {
			this.pagination = limitedpartnerService.queryPageDocumentFile(
					pagination, pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "queryPageJson";
	}

	/**
	 * 查询有限合伙公司生成的法律文件数量统计情况
	 */
	public String queryLawFilePageJson() {
		this.pagination = limitedpartnerService.queryLawFilePage(pagination);
		return "queryPageJson";
	}

	public String addLimitedPartnerUI() throws Exception {
		log.info(currentUser().getUsername() + "打开金蝶系统库，以添加有限合伙公司信息!");
		return "addLimitedPartnerUI";
	}

	/**
	 * 判断用户的权限
	 */
	public void isrole() {
		String roleName = "客户部";
		try {
			user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			List<Integer> userroleidList = userRoleService.userroleidlist(user
					.getId().intValue());
			Role checkRole = roleservice.selectByPrimaryKey(Integer
					.parseInt(Config.getKey("heguiroleid")));
			for (int i = 0; i < userroleidList.size(); i++) {
				Role role = roleservice.selectByPrimaryKey(userroleidList
						.get(i));
				if (checkRole.getId().intValue() == role.getId().intValue()) {
					roleName = "合规部";
				}
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			roleName = roleName.trim();
			log.info(currentUser().getUsername() + "获取自身所在的权限!");
			response.getWriter().print(roleName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 展示金蝶系统库中要添加的有限合伙人公司列表
	 */
	public String listLimitedPartner() throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			PageView view = new PageView(
					this.getPageSize(),
					0,
					this.getCurrent(),
					request.getContextPath()
							+ "/limitedpartner/limitedpartner!listLimitedPartner?fname_l2="
							+ fname_l2 + "&pageSize=" + this.getPageSize());
			params.put("fname_l2", fname_l2);
			params.put("view", view);
			listBaseUnit = limitedpartnerService.queryBaseUnit(params);
			ActionContext.getContext().put("listBaseUnit", listBaseUnit);
			fenye = view.pageView();
			log.info(currentUser().getUsername() + "打开了金蝶系统库中展示的要添加的有限合伙人公司列表!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "listLimitedPartner";
	}

	/**
	 * 添加金蝶系统库中的有限合伙公司名称
	 */
	public String addLimitedPartner() throws Exception {
		if (fname_l2Array != null && !("").equals(fname_l2Array)) {
			fname_l2Array = new String(fname_l2Array.getBytes("ISO-8859-1"),
					"UTF-8");
		}
		try {
			String limitedPartnerNames[] = fname_l2Array.split(",");
			Map<String, Object> params = new HashMap<String, Object>();
			for (String name : limitedPartnerNames) {
				Limitedpartner limitedpartner = new Limitedpartner();
				params.put("name", name);
				int total = limitedpartnerService
						.getRepeatLimitedpartnerName(params);
				if (total == 0) {
					limitedpartner.setName(name);
					if (!"".equals(name))
						limitedpartnerService
								.insertLimitedpartner(limitedpartner);
					else
						message = "所添加的名称中有空值，添加失败，请添加有名称值的有限合伙公司！";
				} else {
					message = "与数据库中有重复的名称，添加失败，请勿添加有重复的有限合伙公司名称！";
				}
			}
			log.info(currentUser().getUsername() + "增加有限合伙人公司!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "newLimitedPartner";
	}

	public void addLimitedPartner_ajax() throws Exception {
		// if (fname_l2Array != null && !("").equals(fname_l2Array)) {
		// fname_l2Array = new
		// String(fname_l2Array.getBytes("ISO-8859-1"),"UTF-8");
		// }
		try {
			String limitedPartnerNames[] = fname_l2Array.split(",");
			Map<String, Object> params = new HashMap<String, Object>();
			for (String name : limitedPartnerNames) {
				Limitedpartner limitedpartner = new Limitedpartner();
				params.put("name", name);
				int total = limitedpartnerService
						.getRepeatLimitedpartnerName(params);
				if (total == 0) {
					limitedpartner.setName(name);
					if (!"".equals(name))
						limitedpartnerService
								.insertLimitedpartner(limitedpartner);
					else
						message = "所添加的名称中有空值，添加失败，请添加有名称值的有限合伙公司！";
				} else {
					message = "与数据库中有重复的名称，添加失败，请勿添加有重复的有限合伙公司名称！";
				}
			}
			log.info(currentUser().getUsername() + "增加有限合伙人公司!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

	/**
	 * 删除有限合伙公司信息采集表
	 */
	public void delete() throws Exception {
		try {
			int status; // 为1时有限合伙信息表已审核
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			limitedpartner = limitedpartnerService
					.findLimitedpartnerById(limitedpartner.getId());
			if (limitedpartner.getGatherstatus() == 4) {
				status = 1;
				response.getWriter().print(status);
			} else {
				limitedpartnerService.deleteById(limitedpartner.getId());
			}
			log.info(currentUser().getUsername() + "删除有限合伙人公司!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private String str[];

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}

	/**
	 * 新建有限合伙人页面
	 */
	public String addPartnerUI() throws Exception {
		try {
			log.info(currentUser().getUsername() + "新建有限合伙人页面!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "addPartnerUI";
	}

	/**
	 * 新建补充信息页面
	 */
	public String supplementLimitedPartnerUI() throws Exception {
		try {
			log.info(currentUser().getUsername() + "新建补充信息页面!");
			limitedpartner = limitedpartnerService.findLimitedpartnerById(pid);
			if (limitedpartner.getIssupplement() == 13)
				return "editsupplementLimitedPartnerUI";
			else
				return "supplementLimitedPartnerUI";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加补充信息到有限合伙公司
	 */
	public String addsupplementLimitedPartner() throws Exception {
		// String result;
		try {
			List<Partner> list = limitedpartnerService
					.selectPartnerListByPID(pid);
			Iterator<Partner> iterator = list.iterator();
			while (iterator.hasNext()) {
				Partner p = iterator.next();
				sumAccount += p.getPartner_amount();
			}
			double gp = limitedpartner.getGpcontribution();
			sumAccount += gp;
			Iterator<Partner> iterator2 = list.iterator();
			if (("标准").equals(calculationmethod)) {
				String gp_new = LimitedpartnerUtil.getPercent_new(
						limitedpartner.getGpcontribution(), sumAccount, figure);
				String gpstr = LimitedpartnerUtil.getStr(gp_new, figure);
				gpAccountPercentStr = gpstr;

				// String par = LimitedpartnerUtil.getPar(gp/sumAccount,
				// figure);
				// String par2 = LimitedpartnerUtil.getPar2(par);
				// gpAccountPercentStr = LimitedpartnerUtil.getPar3(par2);
				while (iterator2.hasNext()) {
					Partner p = iterator2.next();
					String partner_new = LimitedpartnerUtil.getPercent_new(
							p.getPartner_amount(), sumAccount, figure);
					String partnerstr = LimitedpartnerUtil.getStr(partner_new,
							figure);
					p.setPartner_percentstr(partnerstr);

					// String par1 =
					// LimitedpartnerUtil.getPar(p.getPartner_amount()/sumAccount,
					// figure);
					// String par21 = LimitedpartnerUtil.getPar2(par1);
					// String par31 = LimitedpartnerUtil.getPar3(par21);
					// p.setPartner_percentstr(par31);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", p.getId());
					map.put("partner_percent", p.getPartner_percent());
					map.put("partner_percentstr", p.getPartner_percentstr());
					limitedpartnerService.updatePartnerpercentById(map);
				}
			} else {
				double add = 0;
				while (iterator2.hasNext()) {
					Partner p = iterator2.next();
					String partner_new = LimitedpartnerUtil.getPercent_new(
							p.getPartner_amount(), sumAccount, figure);
					String partnerstr = LimitedpartnerUtil.getStr(partner_new,
							figure);
					p.setPartner_percentstr(partnerstr);
					add += LimitedpartnerUtil.getAdd(partnerstr);
					// String par =
					// LimitedpartnerUtil.getPar(p.getPartner_amount()/sumAccount,
					// figure);
					// double parseDouble = Double.parseDouble(par);
					// add = add + parseDouble;
					// String par2 = LimitedpartnerUtil.getPar2(par);
					// String par3 = LimitedpartnerUtil.getPar3(par2);
					// p.setPartner_percentstr(par3);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", p.getId());
					map.put("partner_percent", p.getPartner_percent());
					map.put("partner_percentstr", p.getPartner_percentstr());
					limitedpartnerService.updatePartnerpercentById(map);
				}
				gpAccountPercent = 1 - add;

				String gp_new = LimitedpartnerUtil.getPercent_new(
						gpAccountPercent, 1d, figure);
				String gpstr = LimitedpartnerUtil.getStr(gp_new, figure);
				gpAccountPercentStr = gpstr;

				// String par =
				// LimitedpartnerUtil.getPar(gpAccountPercent,figure);
				// String par2 = LimitedpartnerUtil.getPar2(par);
				// gpAccountPercentStr = LimitedpartnerUtil.getPar3(par2);
			}
			limitedpartner.setId(pid);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", limitedpartner.getId());
			params.put("generalperson", limitedpartner.getGeneralperson());
			params.put("formerlimitedpartner",
					limitedpartner.getFormerlimitedpartner());
			params.put("proxy", limitedpartner.getProxy());
			params.put("businessplace", limitedpartner.getBusinessplace());
			params.put("investedtime", limitedpartner.getInvestedtime());
			params.put("gpcontribution", limitedpartner.getGpcontribution());
			params.put("gpcontributionpercent", gpAccountPercent);
			params.put("totalcontribution", sumAccount);
			params.put("gpcontributionpercentstr", gpAccountPercentStr);
			params.put("figure", figure);
			params.put("calculationmethod", calculationmethod);
			limitedpartnerService.updateLimitedPartnerBySupplement(params);
			// result = "";
			// try {
			// result = detail();
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			log.info(currentUser().getUsername() + "添加了补充信息!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "todetail";
	}

	/**
	 * 补充信息预览
	 */
	public String supplementLimitedPartnerPreview() throws Exception {
		try {
			limitedpartner = limitedpartnerService.findLimitedpartnerById(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "supplementLimitedPartnerPreview";
	}

	/**
	 * 增加有限合伙公司人
	 */
	public String addPartner() throws Exception {
		try {
			pid = limitedpartner.getId();
			partner.setPid(limitedpartner.getId());
			limitedpartnerService.insertPartner(partner);
			log.info(currentUser().getUsername() + "添加了有限合伙人!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "todetail";
		// return detail();
	}

	private String card; // 证件号码

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * 判断证件号码是否与数据库中有重复和身份证号码有效性
	 * @throws Exception 
	 */
	public void isrepeatIDCard() throws Exception{
		try {
			List<Partner> listPartner = limitedpartnerService
					.getPartnerAllIDCard(pid);
			Long partner_id = -1l;
			if (request.getParameter("id") != null
					&& !("").equals(request.getParameter("id")))
				partner_id = Long.valueOf(request.getParameter("id"));
			int flag = 0;
			for (int i = 0; i < listPartner.size(); i++) {
				Partner p = listPartner.get(i);
				if (card.equals(p.getPartner_IDCard())
						&& (!card.equals("") || card != null)
						&& p.getId().intValue() != partner_id.intValue()) { // 修改时不和自身比较
					flag = 1;
					break;
				}
			}
			if (!IUtils.validateCard(card))
				flag = 2;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 编辑有限合伙公司人
	 */
	public String editPartner() throws Exception {
		try {
			Long partner_id = Long.valueOf(request.getParameter("id"));
			if (partner_id != null)
				partner = limitedpartnerService.selectPartnerById(partner_id);
			log.info(currentUser().getUsername() + "打开编辑有限合伙人页面!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "editPartner";
	}

	/**
	 * 更新有限合伙公司人
	 */
	public String updatePartner() throws Exception {
		try {
			Long partner_id = Long.valueOf(request.getParameter("id"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", partner_id);
			params.put("partner_name", partner.getPartner_name());
			params.put("partner_sex", partner.getPartner_sex());
			params.put("partner_nation", partner.getPartner_nation());
			params.put("partner_addr", partner.getPartner_addr());
			params.put("partner_IDCard", partner.getPartner_IDCard());
			params.put("partner_country", partner.getPartner_country());
			params.put("partner_amount", partner.getPartner_amount());
			params.put("partner_date", partner.getPartner_date());
			params.put("partner_note", partner.getPartner_note());
			limitedpartnerService.updatePartner(params);
			log.info(currentUser().getUsername() + "更新了有限合伙人!");
			if (limitedpartner != null) {
				limitedpartner.setId(pid);
			} else {
				limitedpartner = new Limitedpartner();
				limitedpartner.setId(pid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// return detail();
		return "todetail";

	}

	/**
	 * 删除有限合伙公司人
	 */
	public String deletePartner() throws Exception {
		try {
			Long partner_id = Long.valueOf(request.getParameter("id"));
			if (partner_id != null)
				limitedpartnerService.deletePartnerById(partner_id);
			else
				throw new Exception();
			log.info(currentUser().getUsername() + "删除了有限合伙人!");
			if (limitedpartner != null) {
				limitedpartner.setId(pid);
			} else {
				limitedpartner = new Limitedpartner();
				limitedpartner.setId(pid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// return "todetail";
		return detail();
	}

	public void deletePartner_ajax() throws Exception {
		try {
			Long partner_id = Long.valueOf(request.getParameter("id"));
			if (partner_id != null)
				limitedpartnerService.deletePartnerById(partner_id);
			else
				throw new Exception();
			log.info(currentUser().getUsername() + "删除了有限合伙人!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除法律文件
	 */
	public String deleteDocumentFile() throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", documentFile.getLimitedpartnerid());
			params.put("fileid", documentFile.getId());
			limitedpartnerService.deleteDocumentFile(params);
			pid = documentFile.getLimitedpartnerid();
			if (limitedpartnerService.getisexists(pid) == 0) {
				limitedpartnerService.updateLimitedpartnerstatus(pid);
			}
			log.info(currentUser().getUsername() + "删除了法律文件!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return queryDocumentFile();
	}

	public void deleteDocumentFile_Ajax() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", documentFile.getLimitedpartnerid());
			params.put("fileid", documentFile.getId());
			limitedpartnerService.deleteDocumentFile(params);
			pid = documentFile.getLimitedpartnerid();
			if (limitedpartnerService.getisexists(pid) == 0) {
				limitedpartnerService.updateLimitedpartnerstatus(pid);
			}
			log.info(currentUser().getUsername() + "删除了法律文件!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除法律文件模板
	 */
	public String deleteLawTemplate() throws Exception {
		try {
			limitedpartnerService.deleteLawTemplate(RecordID);
			log.info(currentUser().getUsername() + "删除了法律文件模板!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return getLocalCity();
	}

	private String sum_amountStr;

	public String getSum_amountStr() {
		return sum_amountStr;
	}

	public void setSum_amountStr(String sum_amountStr) {
		this.sum_amountStr = sum_amountStr;
	}

	private int xiaojian;

	public int getXiaojian() {
		return xiaojian;
	}

	public void setXiaojian(int xiaojian) {
		this.xiaojian = xiaojian;
	}

	/**
	 * 查看有限合伙公司旗下的有限合伙人详细
	 */
	public String detail() throws Exception {
		try {
			if (limitedpartner != null) {
				pid = limitedpartner.getId();
			}
			/*
			 * if(!"".equals(error)){ error = URLDecoder.decode(getError(),
			 * "utf-8"); setError(error); }
			 */

			sum_partner = limitedpartnerService.getTotalCountsByPid(pid);
			if (sum_partner == 0)
				sum_amount = 0;
			else
				sum_amount = limitedpartnerService.getSumAmountByPid(pid);
			sum_amountStr = LimitedpartnerUtil.getNumber(LimitedpartnerUtil
					.getdouble(sum_amount)); // LP出资总额保留四位小数
			limitedpartner = limitedpartnerService.findLimitedpartnerById(pid);
			limitedpartner.setTotalcontribution(LimitedpartnerUtil
					.getdouble(limitedpartner.getTotalcontribution())); // 总出资额保留四位小数
			if (limitedpartner.getGschangedate() != null)
				gcchangedatestr = limitedpartner.getGschangedate()
						.toLocaleString();
			else
				gcchangedatestr = "";
			if (limitedpartner.getSwchangedate() != null)
				shuiwuchnangedatestr = limitedpartner.getSwchangedate()
						.toLocaleString();
			else
				shuiwuchnangedatestr = "";
			// limitedpartner.setGpcontributionpercent(limitedpartner.getGpcontributionpercent()*100);
			// // 在页面显示百分比
			orderstatus = LimitedpartnerUtil.getStatusValue(limitedpartner
					.getGatherstatus());
			PageView view = new PageView(
					this.getPageSize(),
					0,
					this.getCurrent(),
					request.getContextPath()
							+ "/limitedpartner/limitedpartner!detail?limitedpartner.id="
							+ pid + "&pageSize=" + this.getPageSize());
			// listPartner =
			// limitedpartnerService.selectPartnerListByPID(limitedpartner.getId());
			listPartner = limitedpartnerService.selectPartnerListByFenyePID(
					view, limitedpartner.getId());
			fenye = view.pageView();
			makeUser = limitedpartnerService.findUserById(limitedpartner
					.getTabulator_id());
			checkUser = limitedpartnerService.findUserById(limitedpartner
					.getAuditor_id());
			ActionContext.getContext().put("listPartner", listPartner);
			user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			log.info(currentUser().getUsername() + "查看有限合伙人信息明细列表!");

			String roleName = "客户部";
			user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			userroleidList = userRoleService.userroleidlist(user.getId()
					.intValue());
			Role checkRole = roleservice.selectByPrimaryKey(Integer
					.parseInt(Config.getKey("heguiroleid"))); // 库表中合规审批权限id(102)
			for (int i = 0; i < userroleidList.size(); i++) {
				role = roleservice.selectByPrimaryKey(userroleidList.get(i));
				if (checkRole.getId().intValue() == role.getId().intValue()) {
					roleName = "合规部";
				}
			}
			if ("合规部".equals(roleName))
				return "checkdetail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// if (limitedpartner.getGatherstatus() == 4)
		// return "checkdetail";
		return "detail";
	}

	private String fenye;

	public String getFenye() {
		return fenye;
	}

	public void setFenye(String fenye) {
		this.fenye = fenye;
	}

	/**
	 * 更新有限合伙公司的暂存，提交，审核等状态
	 */
	public void updateStatus() throws Exception {
		try {
			user = (User) ServletActionContext.getRequest().getSession()
					.getAttribute("user");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			limitedpartnerService.updateGatherstatus(pid, operation,
					user.getId());
			limitedpartner = limitedpartnerService.findLimitedpartnerById(pid);
			orderstatus = LimitedpartnerUtil.getStatusValue(limitedpartner
					.getGatherstatus());
			orderstatus = orderstatus.trim();
			response.getWriter().print(orderstatus);
			log.info(currentUser().getUsername() + "更新有限合伙公司" + operation
					+ "状态!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public String updateTime() throws Exception {
		try {
			if (flag == 0)
				change = "工商变更完成";
			else
				change = "税务变更完成";
			limitedpartnerService.updateTime(pid, change);
			limitedpartner = new Limitedpartner();
			limitedpartner.setId(pid);
			log.info(currentUser().getUsername() + "更新有限合伙公司" + change + "时间!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return detail();
	}

	/**
	 * 更新有限合伙公司的工商变更和税务变更时间
	 */
	public String updateChangeTime() throws Exception {
		try {
			if (flag == 0)
				change = "工商变更完成";
			else
				change = "税务变更完成";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pid", pid);
			params.put("changetime", changetime);
			params.put("change", change);
			limitedpartnerService.updateChangeTime(params);
			limitedpartner = new Limitedpartner();
			limitedpartner.setId(pid);
			log.info(currentUser().getUsername() + "更新有限合伙公司" + change + "时间!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// return detail();
		return "todetail";
	}

	/**
	 * 查看有限合伙公司法律文件完成情况
	 */
	public String lawfilelist() throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			PageView view = new PageView(
					this.getPageSize(),
					0,
					this.getCurrent(),
					request.getContextPath()
							+ "/limitedpartner/limitedpartner!lawfilelist?department="
							+ department + "&city=" + city
							+ "&limitedpartnername=" + limitedpartnername
							+ "&pageSize=" + this.getPageSize());
			if ("null".equals(department)) {
				department = null;
			}
			if ("null".equals(city)) {
				city = null;
			}
			if ("null".equals(limitedpartnername)) {
				limitedpartnername = null;
			}
			params.put("department", department);
			params.put("city", city);
			params.put("limitedpartnername", limitedpartnername);
			listLawFile = limitedpartnerService.lawfilePageList(view, params);
			fenye = view.pageView();
			request.setAttribute("listLawFile", listLawFile);
			log.info(currentUser().getUsername() + "查看有限合伙公司法律文件完成情况!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "lawfilelist";
	}

	/**
	 * 显示金蝶系统库中展示的要添加的有限合伙人公司列表
	 */
	public String listLimitedPartnerPage() throws Exception {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			PageView view = new PageView(
					this.getPageSize(),
					0,
					this.getCurrent(),
					request.getContextPath()
							+ "/limitedpartner/limitedpartner!listLimitedPartnerPage?fname_l2="
							+ fname_l2 + "&pageSize=" + this.getPageSize());
			if (fname_l2 == null || fname_l2.equals("null")) {
				fname_l2 = "";
			}
			params.put("fname_l2", fname_l2);
			listBaseUnit = limitedpartnerService
					.queryBaseUnitPage(view, params);
			fenye = view.pageView();
			ActionContext.getContext().put("listBaseUnit", listBaseUnit);
			log.info(currentUser().getUsername() + "打开了金蝶系统库中展示的要添加的有限合伙人公司列表!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "listLimitedPartner";
	}

	public String listLimitedPartnerPageJson() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (fname_l2 == null || fname_l2.equals("null")) {
			fname_l2 = "";
		}
		params.put("fname_l2", fname_l2);
		this.pagination = limitedpartnerService.listLimitedPartnerPageJson(
				pagination, params);
		return "queryPageJson";
	}

	/**
	 * 查看法律文件列表
	 */
	public String queryDocumentFile() throws Exception {
		try {
			PageView view = new PageView(
					this.getPageSize(),
					0,
					this.getCurrent(),
					request.getContextPath()
							+ "/limitedpartner/limitedpartner!queryDocumentFile?pid="
							+ pid + "&pageSize=" + this.getPageSize());
			listDocumentFile = limitedpartnerService.queryDocumentFile(view,
					pid);
			fenye = view.pageView();
			ActionContext.getContext()
					.put("listDocumentFile", listDocumentFile);
			log.info(currentUser().getUsername() + "查看法律文件列表!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "listDocumentFile";
	}

	public String queryDocumentFileJson() throws Exception {
		return "listDocumentFileJson";
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getPageSize() {
		HttpSession session = request.getSession();
		if (newPageSize != 0) {
			session.setAttribute("pageSize", newPageSize);
		}
		Object obj = session.getAttribute("pageSize");
		if (obj != null) {
			pageSize = (Integer) obj;
		} else if (pageSize == 0) {
			return 5;
		}
		return pageSize;
	}

	private int newPageSize;

	public int getNewPageSize() {
		return newPageSize;
	}

	public void setNewPageSize(int newPageSize) {
		this.newPageSize = newPageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private int current;

	public int getCurrent() {
		if (current == 0) {
			return 1;
		}
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	private Mark mark = new Mark();

	public String listMark() throws Exception {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String method = request.getMethod();
			if (method.toLowerCase().equals("get")) {
				String name = mark.getBookmarkname();
				// String string = new
				// String(name.getBytes("ISO-8859-1"),"utf-8");
				mark.setBookmarkname(name);
			}
			String urlPar = mark.getUrlPar();
			String url = request.getContextPath()
					+ "/limitedpartner/limitedpartner!listMark?pageSize="
					+ this.getPageSize() + urlPar;
			PageView view = new PageView(this.getPageSize(), 0,
					this.getCurrent(), url);
			List<Mark> list = markService.listMark(this.getMark(), view);
			fenye = view.pageView();
			request.setAttribute("list", list);
			request.setAttribute("mark", mark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "listMark";
	}

	public String queryMarkPageJson() {
		this.pagination = markService.queryMarkPageJson(pagination);
		return "queryPageJson";
	}

	@SuppressWarnings("static-access")
	public String addMarkPage() {
		long id = markService.getNewMarkID();
		mark.setBookmarkid(id);
		request.setAttribute("map", mark.getTypeList());
		return "addMark";
	}

	public void checkMarkName() throws IOException {
		try {
			PrintWriter out = response.getWriter();
			if (!markService.checkMarkName(mark.getBookmarkname())) {
				out.write("您输入的标签名称有重复，不能使用。");
			} else {
				out.write("OK");
			}
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String addMark() throws Exception {
		mark.setTablefield("'" + mark.getTablefield() + "'");
		markService.addMark(mark);
		// return listMark();
		return "listMark_redirect";
		// return "listMark";
	}

	public String delMark() throws Exception {
		markService.delMark(mark.getBookmarkid());
		return listMark();
	}

	public void delMark_Ajax() throws Exception {
		try {
			markService.delMark(mark.getBookmarkid());
			log.info(currentUser().getUsername() + "删除了标签!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String editMark() throws Exception {

		return "editMark";
	}
	
	// TODO 140820 15:56
		private String fileContentType; // 文件类型

		private int dept;
		private int cityIdId;

		public String addTemplate() throws IOException {
			try {
				String deptName = lawTemplate.getDeptById(dept);
				String cityName = lawTemplate.getCityById(cityIdId);
				String path = deptName + File.separator + cityName + File.separator
						+ fileFileName;
				File dist = new File(Config.getKey("lawfile.word"), path);
				FileUtils.copyFile(file, dist);
				// Add a template
				LawTemplate temp = new LawTemplate();
				temp.setCityID(cityIdId);
				temp.setFileDate(new java.sql.Date(new Date().getTime()));
				temp.setFileName(StringUtil.getFileName(fileFileName));
				temp.setFilePath(path);
				temp.setFileSize(file.length());
				temp.setFileType(StringUtil.getFileType(fileFileName));
				temp.setRecordID(new Date().getTime());
				temp.setUserName(currentUser().getUsername());
				lawTemplate.saveTemplate(temp);
				log.info("添加模板:" + temp.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "addTemplate";
		}

		public String getFileContentType() {
			return fileContentType;
		}

		public void setFileContentType(String fileContentType) {
			this.fileContentType = fileContentType;
		}

		public int getDept() {
			return dept;
		}

		public void setDept(int dept) {
			this.dept = dept;
		}

		public int getCityIdId() {
			return cityIdId;
		}

		public void setCityIdId(int cityId) {
			this.cityIdId = cityId;
		}
		
		// TODO add by 0820
		public void getCityList() throws IOException {
			String parameter = request.getParameter("dept");
			int dept = -1;
			if (parameter != null) {
				dept = Integer.parseInt(parameter);
			}
			List<City> list = lawTemplate.getCityList(dept);
			for (City city : list) {
				System.out.println(city);
			}
			StringBuffer sb = new StringBuffer();
			sb.append("<option value='-1'>请选择城市</option>");
			for (City city : list) {
				sb.append("<option value='" + city.getId() + "'>"
						+ city.getCityName() + "</option>");
			}
			response.getWriter().write(sb.toString());
		}
}
