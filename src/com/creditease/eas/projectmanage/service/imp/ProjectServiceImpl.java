package com.creditease.eas.projectmanage.service.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.accountr.bean.Accountr;
import com.creditease.eas.accountr.service.impl.AccountrServiceImpl;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.projectmanage.bean.AgreementNumber;
import com.creditease.eas.projectmanage.bean.EmailHistory;
import com.creditease.eas.projectmanage.bean.PmChange;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.projectmanage.bean.ProjectManage;
import com.creditease.eas.projectmanage.dao.IAgrNumberMapper;
import com.creditease.eas.projectmanage.dao.IEmailHistoryMapper;
import com.creditease.eas.projectmanage.dao.IProjectMapper;
import com.creditease.eas.projectmanage.dao.PmChangeMapper;
import com.creditease.eas.projectmanage.dao.PmInfoMapper;
import com.creditease.eas.projectmanage.service.IProjectService;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.excel.ExcelUtil;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.warn.bean.FinanceRentContract;

@Service
public class ProjectServiceImpl implements IProjectService {
	@Autowired
	private IProjectMapper projectMapper;
	@Autowired
	private PmChangeMapper pmChangeMapper;
	@Autowired
	private PmInfoMapper IPmInfoMapper;
	@Autowired
	private IAgrNumberMapper agrNumberMapper;
	@Autowired
	private IEmailHistoryMapper IEmailHistoryMapper;
	// 固定的还款账户
	private final String REPAYMENTACCOUNT = "宜信普诚信用管理（北京）有限公司;华创汇才投资管理（北京）有限公司";
	// 固定的开户行
	private final String BANKACCOUNT = "招商银行股份有限公司北京万达广场支行;招商银行股份有限公司北京东四环支行";
	// 固定的账户
	private final String ACCOUNT = "110907607910905;110908080410801";
	// 出借方式为有限合伙的账户对应还款额固定的计算公式
	private final String XIAN_FORMULA = "出借金额*月利率;普诚：出借金额*月管理费*2/3;华创：出借金额*月管理费/3";
	// 出借方式为p2p的账户对应还款额固定计算公式
	private final String p2p_FORMULA = "出借金额*(月利率+月管理费)";
	private static final Logger logger = Logger
			.getLogger(ProjectServiceImpl.class);
	// TODO 确认角色ID
	/**
	 * PM角色ID
	 */
	private final String ROLE_PM = "569";

	/**
	 * 项目信息管理员角色ID
	 */
	private final String ROLE_PROJECT_ADMIN = "567";

	/**
	 * 查询项目信息列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page, HttpServletRequest request) {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		User user = (User) request.getSession().getAttribute("user");
		List<Integer> roleList = (List<Integer>) request.getSession()
				.getAttribute("personrole");
		Map map = new HashMap();
		map.put("loanNumber", request.getParameter("loanNumber"));
		map.put("companyName", request.getParameter("companyName"));
		map.put("pmUserId", request.getParameter("pmUserId"));
		map.put("loanWayId", request.getParameter("selectloanway"));
		map.put("startloanTime", request.getParameter("startloanTime"));
		map.put("endloanTime", request.getParameter("endloanTime"));
		map.put("startexpireTime", request.getParameter("startexpireTime"));
		map.put("endexpireTime", request.getParameter("endexpireTime"));
		map.put("mlyinterest", request.getParameter("mlyinterest"));
		map.put("mMangExpense", request.getParameter("mMangExpense"));
		map.put("authentication", request.getParameter("authentication"));
		map.put("repaymentWayId", request.getParameter("repaymentWayId"));
		map.put("lenders", request.getParameter("lenders"));
		map.put("borrower", request.getParameter("borrower"));
		map.put("repaymentAccount", request.getParameter("repaymentAccount"));
		map.put("bankAccount", request.getParameter("bankAccount"));
		map.put("account", request.getParameter("account"));
		map.put("starRepaymentMoney", request
				.getParameter("starRepaymentMoney"));
		map.put("endRepaymentMoney", request.getParameter("endRepaymentMoney"));
		map.put("starMoney", request.getParameter("starMoney"));
		map.put("endMoney", request.getParameter("endMoney"));
		map.put("term", request.getParameter("term"));
		map.put("state", request.getParameter("state"));
		map.put("startinterestTime", request.getParameter("startinterestTime"));
		map.put("endinterestTime", request.getParameter("endinterestTime"));
		// List<Integer> projectFidList=projectMapper.selectInProjectFid(map);
		// pm角色 项目信息管理567
		// 判断用户拥有那些角色，如拥有项目信息管理角色就展示全部，如拥有pm 角色就展示pm所对应的数据
		boolean pmBen = false;
		boolean projectBen = false;
		for (int i = 0; i < roleList.size(); i++) {
			if (ROLE_PM.equals(roleList.get(i).toString())) {
				pmBen = true;
			} else if (ROLE_PROJECT_ADMIN.equals(roleList.get(i).toString())) {
				projectBen = true;
			}
		}
		if (true == pmBen && false == projectBen) {
			map.put("pmUserId", user.getId());
		}
		// 查询总行数的方法
		int totalCounts = projectMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapTo = PageUtil.getMap(page);
		mapTo.putAll(map);
		List<Map> list = projectMapper.queryPageByParams(mapTo);
		String timeStr = "";
		for (int i = 0; i < list.size(); i++) {
			map.put("fid", list.get(i).get("FID"));
			List<Map> listTime = projectMapper.queryIinterestTime(map);
			if (null != listTime) {
				for (int j = 0; j < listTime.size(); j++) {
					if (listTime.get(j).get("STATE").toString().equals("0")) {
						timeStr += listTime.get(j).get("LNTERESTTIME")
								+ "未还<br/>";
					} else {
						timeStr += listTime.get(j).get("LNTERESTTIME")
								+ "已还<br/>";
					}

				}
			}
			list.get(i).put("LNTERESTTIMELIST", timeStr);
			timeStr = "";
		}
		page.setRows(list);
		return page;
	}

	/**
	 * 加载出借方式
	 */
	@Override
	public String selectLoanway() {
		List<Map> listDictionary = projectMapper.findCussource();
		return StringUtil.convertListToGson(listDictionary);
	}

	/**
	 * 加载还款方式
	 */
	@Override
	public String selectSituationname() {
		List<Map> listDictionary = projectMapper.selectSituationname();
		return StringUtil.convertListToGson(listDictionary);
	}

	/**
	 * 加载项目状态
	 */
	@Override
	public String selectState() {
		List<Map> listDictionary = projectMapper.selectState();
		return StringUtil.convertListToGson(listDictionary);
	}

	/**
	 * 加载贷后管理负责人(PM)
	 */
	@Override
	public String selectPm() {
		List<Map> listDictionary = projectMapper.selectPm();
		return StringUtil.convertListToGson(listDictionary);
	}

	/**
	 * 根据id 查询项目信息
	 */
	@Override
	public ProjectManage edit(Integer fid) {
		return projectMapper.edit(fid);
	}

	/**
	 * 添加项目信息
	 */
	@Override
	public void insert(ProjectManage projectManage) throws Exception{
		PmInfo pmInfo = IPmInfoMapper.queryPmInfoByUserId(projectManage
				.getPmUserId());// 查询根据pmUserId查询pm信息获得pm名称
		if (null != pmInfo) {
			projectManage.setHead(pmInfo.getPmName());
		}
		projectManage.setFext1("1");//1表示没有被删除
		int i = projectMapper.insert(calculationMoney(projectManage));// 添加项目信息
		getInterestTime(projectManage);// 添加利息返还其
		insertAgrNumber(projectManage);// 初始化协议数量
	}

	/**
	 * 
	 * 描述：计算金额和拼接 2014-4-22 下午06:07:50 by sunxiaofeng
	 * 
	 * @version
	 * @param projectManage
	 * @return
	 */
	private ProjectManage calculationMoney(ProjectManage projectManage) {
		String money = "";
		ProjectManage proMan = null;
		if (null != projectManage.getFid()) {
			proMan = projectMapper.edit(projectManage.getFid());
		}
		if (!StringUtil.isNull(StringUtil.objToString(projectManage
				.getLoanWayId()))) {
			if (1 == projectManage.getLoanWayId()) {// 判断出借方式1表示p2p
				if (!StringUtil.isNull(StringUtil.objToString(projectManage
						.getMoney()))// 判断出借金额，月利率，月管理费是否为空
						&& !StringUtil.isNull(StringUtil
								.objToString(projectManage.getMlyinterest()))
						&& !StringUtil.isNull(StringUtil
								.objToString(projectManage.getmMangExpense()))) {
					// 计算账户对应还款额
					Double repaymentMoney = projectManage.getMoney()
							* (projectManage.getMlyinterest() / 100 + projectManage
									.getmMangExpense() / 100);
					money = Utils.getBigDecimal(2, repaymentMoney);
				}
				projectManage.setRepaymentMoney(money);
			} else if (2 == projectManage.getLoanWayId()) {// 2表示有限合伙
				if (!StringUtil.isNull(StringUtil.objToString(projectManage
						.getMoney()))// 判断出借金额，月利率，月管理费是否为空
						&& !StringUtil.isNull(StringUtil
								.objToString(projectManage.getMlyinterest()))
						&& !StringUtil.isNull(StringUtil
								.objToString(projectManage.getmMangExpense()))) {
					// 计算账户对应还款额
					Double repaymentMoneyOne = projectManage.getMoney()
							* (projectManage.getMlyinterest() / 100);
					Double repaymentMoneyTo = projectManage.getMoney()
							* projectManage.getmMangExpense() / 100 * 2 / 3;
					Double repaymentMoneyThree = projectManage.getMoney()
							* projectManage.getmMangExpense() / 100 / 3;
					money = Utils.getBigDecimal(2, repaymentMoneyOne) + ";"
							+ Utils.getBigDecimal(2, repaymentMoneyTo) + ";"
							+ Utils.getBigDecimal(2, repaymentMoneyThree);
				}
				if (!StringUtil.isNull(projectManage.getRepaymentAccount())// 判断开户行，还款账户，账户，有一个为空都不添加到数据库
						&& !StringUtil.isNull(projectManage.getBankAccount())
						&& !StringUtil.isNull(projectManage.getAccount())) {
					if (null == proMan
							|| (StringUtil.isNull(proMan.getRepaymentAccount())
									&& StringUtil.isNull(proMan
											.getBankAccount()) && StringUtil
									.isNull(proMan.getAccount()))) {
						projectManage.setRepaymentAccount(projectManage
								.getRepaymentAccount()
								+ ";" + REPAYMENTACCOUNT);
						projectManage.setBankAccount(projectManage
								.getBankAccount()
								+ ";" + BANKACCOUNT);
						projectManage.setAccount(projectManage.getAccount()
								+ ";" + ACCOUNT);
					}
				} else {
					projectManage.setRepaymentAccount("");
					projectManage.setBankAccount("");
					projectManage.setAccount("");
				}
				projectManage.setRepaymentMoney(money);
			}
		}
		return projectManage;
	}

	/**
	 * 修改项目信息
	 */
	public void update(ProjectManage projectManage) {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		ProjectManage projectM = projectMapper.edit(projectManage.getFid());// 查询修改前的项目信息
		PmInfo pmInfo = IPmInfoMapper.queryPmInfoByUserId(projectManage
				.getPmUserId());// 查询根据pmUserId查询pm信息获得pm名称
		if (!"".equals(projectManage.getPmUserId())
				&& null != projectManage.getPmUserId()
				&& !"".equals(projectM.getHead()) && null != projectM.getHead()) {
			if (!projectM.getPmUserId().equals(projectManage.getPmUserId())) {// 判断修改后项目项目信息负责人（pm）有没有变更
				PmChange pmChange = new PmChange();
				pmChange.setBeforePm(projectM.getHead());
				pmChange.setBeforePmId(projectM.getPmUserId());
				pmChange.setAfterPm(pmInfo.getPmName());
				pmChange.setAfterPmId(projectManage.getPmUserId());
				pmChange.setLoanNumber(projectManage.getLoanNumber());
				pmChange.setfCreateUserId(user.getId().intValue());
				pmChange.setfCreateUserName(user.getUsername());
				pmChangeMapper.insertPmChange(pmChange);// 往pm变更记录变添加数据
			}
		}
		getInterestTime(projectManage);
		insertAgrNumber(projectManage);
		if (null != pmInfo) {
			projectManage.setHead(pmInfo.getPmName());
		}
		projectManage.setLastupdaterId(user.getId().intValue());
		int i = projectMapper.update(calculationMoney(projectManage));

	};

	/**
	 * 
	 * 描述：计算利息返还日，并保存数据库 2014-3-17 下午03:18:16 by sunxiaofeng
	 * 
	 * @version
	 * @param projectManage
	 */
	@SuppressWarnings("unchecked")
	private void getInterestTime(ProjectManage projectManage){
		Map map = new HashMap();
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		// 判断出借方式和期限是否为空
		if (!StringUtil.isNull(StringUtil.objToString(projectManage
				.getLoanWayId()))
				&& !StringUtil.isNull(StringUtil.objToString(projectManage
						.getTerm()))
				&& !StringUtil.isNull(StringUtil.objToString(projectManage
						.getLoanTime()))
				&& !StringUtil.isNull(StringUtil.objToString(projectManage
						.getExpireTime()))) {
			map.put("fid", projectManage.getFid());
			List<Map> mapTime = projectMapper.queryIinterestTime(map);
			if (mapTime.size() == 0) {
				if ((1 == projectManage.getLoanWayId())) {// 判断出借方式，1为p2p并且到期日不为空
					boolean bolena = false;
					Calendar calendar = Calendar.getInstance();
					Calendar calendarTo = Calendar.getInstance();
					calendarTo.setTime(projectManage.getLoanTime());
					calendarTo.add(Calendar.MONTH, 1);
					calendar.setTime(projectManage.getExpireTime());
					if (1 == calendar.get(Calendar.MONTH)
							&& 28 == calendar.get(Calendar.DATE)) {// 1表示是2月28表示是28号，判断是否是2月28号
						bolena = true;
					}
					map.put("projectFid", projectManage.getFid());
					map.put("state", 0);// 0表示没有还的状态
					map.put("fcreateuserId", user.getId().intValue());
					map.put("fext1", 1);// 1表示没有被删除
					for (int i = 1; calendar.getTime().compareTo(
							calendarTo.getTime()) > 0; i++) {// 根据到期日推出利息返还日并保存到数据库中
						calendar.setTime(projectManage.getExpireTime());
						calendar.add(Calendar.MONTH, -i);
						if (bolena) {
							if(1 == calendar.get(Calendar.MONTH)){
								calendar.set(Calendar.DAY_OF_MONTH, 28);
							}else{
								calendar.set(Calendar.DAY_OF_MONTH, 30);  
							}
						}
						map.put("lnterestTime", calendar.getTime());
						projectMapper.insertInterestTime(map);// 保存利息返还日
					}
					map.put("lnterestTime", projectManage.getExpireTime());
					projectMapper.insertInterestTime(map);// 保存利息返还日
				} else if ((2 == projectManage.getLoanWayId())) { // 2 表示为出借方式为
					Calendar calendar = Calendar.getInstance();
					Calendar calendarTo = Calendar.getInstance();
					calendar.setTime(projectManage.getLoanTime());
					calendarTo.setTime(projectManage.getExpireTime());
					calendarTo.add(Calendar.MONTH, -1);
					map.put("projectFid", projectManage.getFid());
					map.put("state", 0);// 0表示没有还的状态
					map.put("fcreateuserId", user.getId().intValue());
					map.put("fext1", 1);// 1表示没有被删除
					for (int i = 1; calendar.getTime().compareTo(
							calendarTo.getTime()) < 0; i++) {// 根据出借期日推出利息返还日并保存到数据库中
						calendar.setTime(projectManage.getLoanTime());
						calendar.add(Calendar.MONTH, i);
						map.put("lnterestTime", calendar.getTime());
						projectMapper.insertInterestTime(map);// 保存利息返还日
					}
					map.put("lnterestTime", projectManage.getExpireTime());
					projectMapper.insertInterestTime(map);// 保存利息返还日
				}
			}
		}
	}

	/**
	 * 
	 * 描述：数据库初始化协议数量 2014-3-19 上午11:22:40 by sunxiaofeng
	 * 
	 * @version
	 * @param projectManage
	 */
	private void insertAgrNumber(ProjectManage projectManage) {
		if (!"".equals(projectManage.getLoanWayId())
				&& null != projectManage.getLoanWayId()) {// 出借方式是否为空
			List<Integer> agrList = agrNumberMapper
					.selectAgreement(projectManage.getLoanWayId());
			Map map = new HashMap();
			map.put("projectFid", projectManage.getFid());
			map.put("list", agrList);
			Integer number = agrNumberMapper.selectAgrNumber(map);
			if (null == number || number <= 0) {
				User user = (User) ServletActionContext.getRequest()
						.getSession().getAttribute("user");
				List<AgreementNumber> agrNumberList = new ArrayList<AgreementNumber>();
				for (int i = 0; i < agrList.size(); i++) {
					AgreementNumber AgreementNumber = new AgreementNumber();
					AgreementNumber.setProjectFid(projectManage.getFid());
					AgreementNumber.setAgreementFid(agrList.get(i));
					AgreementNumber.setFext1("1");//1表示没有被删除
					AgreementNumber.setFcreateuserId(user.getId().intValue());
					agrNumberList.add(initNumber(i, projectManage
							.getLoanWayId(), AgreementNumber));
					// int n=
					// agrNumberMapper.insertAgreementNumber(AgreementNumber);
				}
				int n = agrNumberMapper.insertAgreementNumber(agrNumberList);
			}

		}

	}

	/**
	 * 
	 * 描述： 2014-4-4 下午04:58:59 by sunxiaofeng
	 * 
	 * @version
	 * @param c
	 *            变量
	 * @param LoanWayId
	 *            出借方式
	 * @param agreementNumber
	 *            合同数量对象
	 * @return
	 */
	private AgreementNumber initNumber(Integer c, Integer LoanWayId,
			AgreementNumber agreementNumber) {
		if (1 == LoanWayId) {
			switch (c) {
			case 0:
				agreementNumber.setReceivableQuantity(4);
				break;
			case 1:
				agreementNumber.setReceivableQuantity(8);
				break;
			case 2:
				agreementNumber.setReceivableQuantity(8);
				break;
			case 3:
				agreementNumber.setReceivableQuantity(2);
				break;
			case 4:
				agreementNumber.setReceivableQuantity(4);
				break;
			case 6:
				agreementNumber.setReceivableQuantity(4);
				break;
			case 7:
				agreementNumber.setReceivableQuantity(2);
				break;
			case 8:
				agreementNumber.setReceivableQuantity(2);
				break;
			}
		} else if (2 == LoanWayId) {
			switch (c) {
			case 0:
				agreementNumber.setReceivableQuantity(10);
				break;
			case 1:
				agreementNumber.setReceivableQuantity(6);
				break;
			case 2:
				agreementNumber.setReceivableQuantity(6);
				break;
			case 3:
				agreementNumber.setReceivableQuantity(4);
				break;
			case 5:
				agreementNumber.setReceivableQuantity(4);
				break;
			case 6:
				agreementNumber.setReceivableQuantity(2);
				break;
			case 7:
				agreementNumber.setReceivableQuantity(2);
				break;
			}
		}

		return agreementNumber;
	}

	/**
	 * 查询合同信息列表展示
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryAgrNumber(Pagination page, HttpServletRequest request) {
		Map map = new HashMap();
		map.put("projectFid", request.getParameter("projectFid"));
		List<Integer> agrList = agrNumberMapper.selectAgreement(Integer
				.parseInt(request.getParameter("loanWayId")));
		map.put("list", agrList);
		List<Map> list = agrNumberMapper.queryAgrNumber(map);
		page.setRows(list);
		return page;
	}

	/**
	 * 根据fid 查询合同数量信息
	 */
	@Override
	public Map editAgreement(Integer fid) {
		return agrNumberMapper.editAgreement(fid);
	}

	/**
	 * 修改合同数量
	 */
	@Override
	public void updateAgrNumber(AgreementNumber agreementNumber) {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		agreementNumber.setLastupdaterId(user.getId().intValue());
		int i = agrNumberMapper.updateAgrNumber(agreementNumber);
	}

	/**
	 * 查询利息返还日
	 */
	@Override
	public Pagination queryIinterestTime(Pagination page,
			HttpServletRequest request) {
		Map map = new HashMap();
		map.put("fid", request.getParameter("fid"));
		List<Map> list = projectMapper.queryIinterestTime(map);
		page.setRows(list);
		return page;
	}

	/**
	 * 验证当前系统时间是否逾期
	 */
	@Override
	public String vailTimeOverdue(HttpServletRequest request) throws Exception {
		String importMessage = "操作成功";
		String jsonResult = "";
		String state = request.getParameter("state");
		String fid = request.getParameter("fid");
		Map map = projectMapper.selectByTimeFid(Integer.parseInt(fid));
		//Date dateOne = Utils.toDate(map.get("LNTERESTTIME"));// 利息返还日
		//Date dateTo = Utils.toDate(Utils.getNowDate());// 当前系统时间
		if (state.equals("1")) {
			importMessage = "系统将会发生结清报告，是否确认。";
			return jsonResult = "{\"vail\":\"1\", \"message\":\""
					+ importMessage + "\"}";
		} else {
			updateInTimeState(state, fid, "0");
		}
		return jsonResult = "{\"success\":\"true\", \"message\":\""
				+ importMessage + "\"}";
	}

	/**
	 * 修改利息返还其状态
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateInTimeState(String state, String fid, String send)
			throws Exception {
		Map map = new HashMap();
		map.put("state", state);
		map.put("fid", fid);
		int i = projectMapper.updateInTimeState(map);
		if (send.equals("1")) {// 1表示要发送结清报告
			Map mapTo = projectMapper.selectByTimeFid(Integer.parseInt(fid));// 查看项目信息机利息返还日
			// 判断利息返还日和到期人是否为空
			if (!"".equals(StringUtil.objToString(mapTo.get("LNTERESTTIME")))) {
				sendReport(mapTo, 1);// 1表示结清
			} else {
				logger.error("手工发送结清报告失败");
			}
		}
	}

	/**
	 * 判断贷款编号是否重复
	 */
	public boolean findLoanNumberExist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return projectMapper.findExists(Utils.setParams("T_PRO_PROJECTMANAGE",
				"LOANNUMBER", request.getParameter("columnValue"))) == 0 ? false
				: true;
	}

	/**
	 * ：每天8点执行发送提醒邮件（发送存续期提醒是在利息返还日前两天发送，发送到期还款提醒是在到期日的前10天和前15天发送）
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sendWarnEmail() throws Exception {
		// List<Map>
		// interestTimeList=projectMapper.selectAllInterestTime();//查询所有利息返还日
		Map map = new HashMap();
		List<Map> projectList = projectMapper.queryAllProject();// 获得所有项目信息
		String strFidOne = "";
		String strFidTo = "";
		for (int i = 0; i < projectList.size(); i++) {
			map.put("fid", projectList.get(i).get("FID"));
			List<Map> listTime = projectMapper.queryIinterestTime(map);// 获得项目信息所对应的利息返还日
			for (int j = 0; j < listTime.size(); j++) {
				if (!"".equals(listTime.get(j).get("LNTERESTTIME"))
						&& null != listTime.get(j).get("LNTERESTTIME")) {
					Date expiretime = Utils.toDate(projectList.get(i).get(
							"EXPIRETIME"));
					Date lnteresttime = Utils.toDate(listTime.get(j).get(
							"LNTERESTTIME"));
					if (expiretime.equals(lnteresttime)) {// 判断到期日其是否等于利息返还日
						String dateOne = Utils.lastDay(Utils.toDate(listTime
								.get(j).get("LNTERESTTIME")), -10);
						String dateTo = Utils.lastDay(Utils.toDate(listTime
								.get(j).get("LNTERESTTIME")), -15);
						if (dateOne.equals(Utils.getCurrentDate())
								|| dateTo.equals(Utils.getCurrentDate())) {// 判断当前系统时间是否是到期日的前10天或15天
							strFidOne += listTime.get(j).get("FID") + ",";
						}
					} else {
						String dateThree = Utils.lastDay(Utils.toDate(listTime
								.get(j).get("LNTERESTTIME")), -2);
						if (dateThree.equals(Utils.getCurrentDate())) {
							strFidTo += listTime.get(j).get("FID") + ",";
						}
					}
				}
			}

		}
		sendEmail(strFidOne, strFidTo);
	}

	/**
	 * 
	 * 描述：获得需要汇总的邮件 2014-3-24 下午05:30:18 by sunxiaofeng
	 * 
	 * @version
	 * @param strFidOne
	 *            (所有发送到期提醒的fid)
	 * @param strFidTo
	 *            （所有发送存续期提醒的fid）
	 * @return
	 * @throws Exception
	 */
	private void sendEmail(String strFidOne, String strFidTo) throws Exception {
		// 发送到期提醒的邮件
		if (!"".equals(strFidOne)) {
			List<List<Map>> mapOne = getProjectList(strFidOne);
			for (int i = 0; i < mapOne.size(); i++) {
				sendEmailExcel(mapOne.get(i), "1");// 1表示到期
			}
		}
		// 发送逾期提醒邮件
		if (!"".equals(strFidTo)) {
			List<List<Map>> mapTo = getProjectList(strFidTo);
			for (int i = 0; i < mapTo.size(); i++) {
				sendEmailExcel(mapTo.get(i), "2");// 2表示存续期期
			}
		}
	}

	/**
	 * 
	 * 描述：发送邮件 2014-5-10 上午11:30:25 by sunxiaofeng
	 * 
	 * @version
	 * @param dataset
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	private void sendEmailExcel(List<Map> dataset, String flag) {
		boolean success = false;
		EmailHistory emailHistory = new EmailHistory();
		String type = "";// 预警类型
		String state = "";// 发送状态
		if (null != dataset && dataset.size() > 0) {
			try {
				List<PmInfo> pmInfoList = IPmInfoMapper.selectByRoleid();// 查询所有管理员
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < pmInfoList.size(); i++) {// 得到所有管理员不为空 的邮箱
					if (null != pmInfoList.get(i).getPmEmail()
							&& !"".equals(pmInfoList.get(i).getPmEmail())) {
						list.add(pmInfoList.get(i).getPmEmail());
					} else {
						logger.info("管理员" + pmInfoList.get(i).getPmName()
								+ "邮箱为空");
					}
				}
				// 根据pmuserid得到负责人邮箱
				PmInfo pminfo = IPmInfoMapper.queryPmInfoByUserId(Integer
						.parseInt(dataset.get(0).get("PMUSERID").toString()));
				if(null != pminfo){
					if (null != pminfo.getPmEmail() && !"".equals(pminfo.getPmEmail())) {
						list.add(pminfo.getPmEmail());
					} else {
						logger.info("负责人" + pminfo.getPmName() + "邮箱为空");
					}
				}
				String[] toAddress = list.toArray(new String[list.size()]);
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				if (pr.getProperty("to_local_Address") != null
						&& "yes".equals(pr.getProperty("to_local_Address"))) {
					toAddress = pr.getProperty("toAddress").split(",");// 代码正式提交时邮件接收人为接口人邮箱，此行注释掉
				}
				if (toAddress.length != 0) {
					Map comMap = this.exportExcel(dataset, flag);// 汇总excel
					String title = "";// 标题
					String content = "";// 内容
					if ("1".equals(flag)) {// 1表示是到期提醒
						title = "到期还款提醒";
						content = "<p>各位好：</p><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								+ comMap.get("company")
								+ "借款即将到期，请与融资方沟通准备支付本金及利息，详情请查看附件！";

					} else {
						title = "存续期还款提醒";
						content = "<p>各位好：</p><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								+ comMap.get("company")
								+ "将支付利息，请注意提醒融资方，具体情况见附件！";

					}
					String fromAddress = pr
							.getProperty("PROJECTMANAGE_USERNAME");
					String password = pr.getProperty("PROJECTMANAGE_PASSWORD");
					success = SendMailUtilNew.sendFileMailTo(fromAddress,
							password, toAddress, title, comMap.get("filestr")
									.toString(), content);
					if (true == success) {
						logger.info("发送还款提醒信息成功");
						state = "1";
					} else {
						logger.error("发送还款提醒信息失败");
						state = "0";
					}

				}else{
					logger.error("发送还款提醒信息失败,原因管理员和pm都为空");
					state = "0";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
				state = "0";
			} finally {
				if ("1".equals(flag)) {
					type = "2";// 2表示到期提醒类型
				} else {
					type = "3";// 3表示逾期提醒类型
				}
				// isWarnType
				for (int i = 0; i < dataset.size(); i++) {
					emailHistory.setSendState(state);
					emailHistory.setWarnType(type);
					emailHistory.setLoanNumber(StringUtil.objToString(dataset
							.get(i).get("LOANNUMBER")));
					emailHistory.setLoanTime(Utils.toDate(dataset.get(i).get(
							"LOANTIME")));
					emailHistory.setExpireTime(Utils.toDate(dataset.get(i).get(
							"EXPIRETIME")));
					emailHistory.setHead(StringUtil.objToString(dataset.get(i)
							.get("HEAD")));
					emailHistory.setInterestTime(Utils.toDate(dataset.get(i)
							.get("LNTERESTTIME")));
					IEmailHistoryMapper.insertEmailHistory(emailHistory);
				}

			}
		}
	}

	/**
	 * 
	 * 描述：创建excel附件 2014-5-10 上午11:06:40 by sunxiaofeng
	 * 
	 * @version
	 * @param dataset
	 * @return
	 * @throws Exception
	 */
	private Map exportExcel(List<Map> dataset, String flag) throws Exception {
		String[] headersTo = null;
		String[] titleTo = null;
		String sheetName = "";
		String companyName = "";// 拼接公司名称
		Map mapName = new HashMap();
		List<Map> listMap = new ArrayList<Map>();
		if ("1".equals(flag)) {// 1表示是到期
			for (int i = 0; i < dataset.size(); i++) {
				if ("1".equals(StringUtil.objToString(dataset.get(i).get(
						"LOANWAYID")))) {// 表示是p2p
					Double dou = StringUtil.strToDouble(StringUtil
							.objToString(dataset.get(i).get("MONEY")))
							+ StringUtil.strToDouble(StringUtil
									.objToString(dataset.get(i).get(
											"REPAYMENTMONEY")));
					dataset.get(i).put("REPAYMENTMONEY", dou);
					dataset.get(i).put("LOANWAY", "P2P");
				} else if ("2".equals(StringUtil.objToString(dataset.get(i)
						.get("LOANWAYID")))) {// 2表示有限合伙
					if (null != dataset.get(i).get("REPAYMENTACCOUNT")// 判断开户行，还款账户，账户是否为空
							&& !"".equals(dataset.get(i)
									.get("REPAYMENTACCOUNT"))
							&& null != dataset.get(i).get("BANKACCOUNT")
							&& !"".equals(dataset.get(i).get("BANKACCOUNT"))
							&& null != dataset.get(i).get("REPAYMENTMONEY")
							&& !"".equals(dataset.get(i).get("REPAYMENTMONEY"))) {

						String[] repaymentAccount = StringUtil.objToString(
								dataset.get(i).get("REPAYMENTACCOUNT")).split(
								";");
						String[] bankAccount = StringUtil.objToString(
								dataset.get(i).get("BANKACCOUNT")).split(";");
						String[] account = StringUtil.objToString(
								dataset.get(i).get("ACCOUNT")).split(";");
						String[] Money = StringUtil.objToString(
								dataset.get(i).get("REPAYMENTMONEY"))
								.split(";");
						String strRepayment = "";// 主要是把还款账户，开户行，账户拼接
						Double repaymentmoney = 0d;
						dataset.get(i).put("LOANWAY", "有限合伙");
						for (int j = 0; j < repaymentAccount.length; j++) {
							strRepayment = repaymentAccount[j] + ";"
									+ bankAccount[j] + ";" + account[j];
							if(0 == j){
								repaymentmoney = Double.parseDouble(Money[j])
								+ Double.parseDouble(dataset.get(i).get(
										"MONEY").toString());
							}else{
								repaymentmoney = Double.parseDouble(Money[j]);
							}
							dataset.get(i)
									.put("STRREPAYMENT" + j, strRepayment);
							dataset.get(i).put("MONEY" + j,
									Utils.getBigDecimal(2, repaymentmoney));
						}
					} else {
						dataset.get(i).put("LOANWAY", "有限合伙");
					}
				}
			}
			sheetName = "到期提醒";
		} else {// 表示存续期
			for (int i = 0; i < dataset.size(); i++) {
				if ("2".equals(StringUtil.objToString(dataset.get(i).get(
						"LOANWAYID")))) {
					if (null != dataset.get(i).get("REPAYMENTACCOUNT")// 判断开户行，还款账户，账户是否为空
							&& !"".equals(dataset.get(i)
									.get("REPAYMENTACCOUNT"))
							&& null != dataset.get(i).get("BANKACCOUNT")
							&& !"".equals(dataset.get(i).get("BANKACCOUNT"))
							&& null != dataset.get(i).get("REPAYMENTMONEY")
							&& !"".equals(dataset.get(i).get("REPAYMENTMONEY"))) {

						String[] repaymentAccount = StringUtil.objToString(
								dataset.get(i).get("REPAYMENTACCOUNT")).split(
								";");
						String[] bankAccount = StringUtil.objToString(
								dataset.get(i).get("BANKACCOUNT")).split(";");
						String[] account = StringUtil.objToString(
								dataset.get(i).get("ACCOUNT")).split(";");
						String[] Money = StringUtil.objToString(
								dataset.get(i).get("REPAYMENTMONEY"))
								.split(";");
						String strRepayment = "";// 主要是把还款账户，开户行，账户拼接
						Double repaymentmoney = 0d;
						dataset.get(i).put("LOANWAY", "有限合伙");
						for (int j = 0; j < repaymentAccount.length; j++) {
							strRepayment = repaymentAccount[j] + ";"
									+ bankAccount[j] + ";" + account[j];
							repaymentmoney = Double.parseDouble(Money[j]);
							dataset.get(i)
									.put("STRREPAYMENT" + j, strRepayment);
							dataset.get(i).put("MONEY" + j,
									Utils.getBigDecimal(2, repaymentmoney));
						}
					} else {
						dataset.get(i).put("LOANWAY", "有限合伙");
					}
				} else if ("1".equals(StringUtil.objToString(dataset.get(i)
						.get("LOANWAYID")))) {
					dataset.get(i).put("LOANWAY", "P2P");
				}
			}
			sheetName = "存续期提醒";
		}
		/**
		 * 1表示P2p excel格式,2表示有限合伙excel格式
		 */
		if ("1".equals(StringUtil.objToString(dataset.get(0).get("LOANWAYID")))) {
			String[] headers = { "公司名称", "出借方式", "出借日", "到期日", "借款金额(元)",
					"月利率(%)", "月管理费率(%)", "本月划扣金额(元)" };
			String[] title = { "COMPANYNAME", "LOANWAY", "LOANTIME",
					"EXPIRETIME", "MONEY", "MLYINTEREST", "MMANGEXPENSE",
					"REPAYMENTMONEY" };
			headersTo = headers;
			titleTo = title;
		} else if ("2".equals(StringUtil.objToString(dataset.get(0).get(
				"LOANWAYID")))) {
			String[] headers = { "公司名称", "出借方式", "出借日", "到期日", "借款金额",
					"月利率(%)", "月管理费率(%)", "应付利息还款账户", "应付利息还款金额(元)",
					"服务费(谱成)还款账户", "服务费(谱成)还款金额(元)", "服务费(华创)还款账户",
					"服务费(华创)还款金额(元)" };
			String[] title = { "COMPANYNAME", "LOANWAY", "LOANTIME",
					"EXPIRETIME", "MONEY", "MLYINTEREST", "MMANGEXPENSE",
					"STRREPAYMENT0", "MONEY0", "STRREPAYMENT1", "MONEY1",
					"STRREPAYMENT2", "MONEY2" };
			headersTo = headers;
			titleTo = title;
		}

		String filestr = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFSheet sheet = wb.createSheet(sheetName);// 设置页的名称
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);// 设置行高
		for (int i = 0; i < headersTo.length; i++) {
			sheet.setColumnWidth(i, 5000);// 设置单元格的宽度
			ExpExcelUtil.createTxtCell(wb, row, i, headersTo[i], ExpExcelUtil
					.createCollStyleCenter(cellStyle, wb));// 创建表格第一行
		}
		if (null != dataset && !dataset.isEmpty()) {// 判断数据是否为空
			Object[] objs = dataset.toArray();// 转换为数组
			for (int i = 0; i < objs.length; i++) {
				HSSFRow rowTo = sheet.createRow(i + 1);// 创建行，设置从第二行开始
				rowTo.setHeight((short) 500);
				Map t = (Map) objs[i];
				for (int j = 0; j < titleTo.length; j++) {
					if (titleTo[j] != "") {
						String getMethodName = titleTo[j];
						Object value = t.get(getMethodName);
						ExpExcelUtil.createTxtCell(wb, rowTo, j, value,
								ExpExcelUtil.createCollStyle(cellStyle, wb));
					}
				}
				companyName += t.get("COMPANYNAME") + ",";
				mapName.put("company", companyName.substring(0, companyName
						.length() - 1));
			}
		}
		FileOutputStream fout;
		try {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
			//portfile = "D:/";// 测试时用本地路径，上线时将此行注释掉
			if ("1".equals(flag)) {
				filestr = portfile + "到期提醒" + Utils.getNowcurrTime() + ".xls";
			} else {
				filestr = portfile + "存续期提醒" + Utils.getNowcurrTime() + ".xls";
			}
			mapName.put("filestr", filestr);
			fout = new FileOutputStream(filestr);
			wb.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return mapName;
	}

	/**
	 * 
	 * 描述：判断那些邮件需要汇总 2014-5-9 下午01:57:01 by sunxiaofeng
	 * 
	 * @version
	 * @param strFid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<List<Map>> getProjectList(String strFid) {
		List list = new ArrayList();
		if (!"".equals(strFid)) {
			List<Map> projectManage = projectMapper.selectProjectGroupBy();// 得到所有要发到期提醒的项目信息和利息返还日
			for (int i = 0; i < projectManage.size(); i++) {
				Map mapPro = new HashMap();
				mapPro.put("ids", strFid.substring(0, strFid.length() - 1)
						.split(","));
				mapPro.put("head", projectManage.get(i).get("HEAD"));
				mapPro.put("lnteresttime", StringUtil.objToString(projectManage
						.get(i).get("LNTERESTTIME")));
				mapPro.put("loanwayid", projectManage.get(i).get("LOANWAYID"));
				List<Map> projectMap = projectMapper.selectProject(mapPro);
				list.add(projectMap);
			}
		}
		return list;
	};

	/**
	 * 每天10:30点发送报告处周六日外
	 */
	@Override
	public void sendReportEmail() throws Exception {
		Calendar c = Calendar.getInstance();
		// 判断当前日期是不是周六日，等于7代表周六，等于1表示周日
		if (7 != c.get(Calendar.DAY_OF_WEEK)
				&& 1 != c.get(Calendar.DAY_OF_WEEK)) {
			Map map = new HashMap();
			List<Map> projectList = projectMapper.queryAllProject();
			for (int i = 0; i < projectList.size(); i++) {
				map.put("fid", projectList.get(i).get("FID"));
				List<Map> listTime = projectMapper.queryIinterestTime(map);// 获得项目信息所对应的利息返还日
				for (int j = 0; j < listTime.size(); j++) {
					if (!"".equals(listTime.get(j).get("LNTERESTTIME"))
							&& null != listTime.get(j).get("LNTERESTTIME")) {
						String dateThree = Utils.getDate(Utils.toDate(listTime
								.get(j).get("LNTERESTTIME")));// 获得利息返还日
						if (2 == c.get(Calendar.DAY_OF_WEEK)) {// 2等于周一
						if (null != isType(listTime.get(j))) {
							if (0 == isType(listTime.get(j))) {
								Calendar calendarTo = Calendar.getInstance();
								calendarTo.add(Calendar.DATE, -4);// 计算当前系统时间的前4天----------------2改成3改为4（2014-8-27）
								Calendar calendarTree = Calendar.getInstance();
								calendarTree.add(Calendar.DATE, -1);//表示周六
							   if (dateThree.compareTo(Utils.getDate(calendarTo
									.getTime())) >= 0
									&& dateThree.compareTo(Utils.getDate(calendarTree
											.getTime())) <0) {//-----------------------------------<=改为<
									Map mapTime = projectMapper
											.selectByTimeFid(Integer
													.parseInt(listTime.get(j)
															.get("FID")
															.toString()));
									sendReport(mapTime, 0);
								};
							}/*else{
								Calendar calendarTo = Calendar.getInstance();
								calendarTo.add(Calendar.DATE, -2);// 计算当前系统时间的前3天----------------2改成3
								if (dateThree.compareTo(Utils.getDate(calendarTo
										.getTime())) >= 0
										&& dateThree.compareTo(Utils
												.getCurrentDate()) <=0) {//-----------------------------------<=改为<
										Map mapTime = projectMapper
												.selectByTimeFid(Integer
														.parseInt(listTime.get(j)
																.get("FID")
																.toString()));
									 sendReport(mapTime, 1);
								  };
							}*/
						  }
						} else {
							if (null != isType(listTime.get(j))) {
								if (1 == isType(listTime.get(j))) {/*
									if (dateThree.equals(Utils.getCurrentDate())) {// 判断利息返还日是否等于当前系统时间
										if (null != isType(listTime.get(j))) {
											Map mapTime = projectMapper
													.selectByTimeFid(Integer
															.parseInt(listTime.get(j)
																	.get("FID")
																	.toString()));
											sendReport(mapTime,1);
										};
									}
								*/}else{
									Calendar afferDate = Calendar.getInstance();
									afferDate.add(Calendar.DATE, -2);//--------有1改为2（利息返还日后两天发送逾期报告）2014-8-27
									if (dateThree.equals(Utils.getDate(afferDate.getTime()))) {// 判断利息返还日是否等于系统时间
										if (null != isType(listTime.get(j))) {
											Map mapTime = projectMapper
													.selectByTimeFid(Integer
															.parseInt(listTime.get(j)
																	.get("FID")
																	.toString()));
											sendReport(mapTime,0);
										};
									}
								}
							}
							
						}
					}
				}
			}
		}

	}

	/**
	 * 
	 * 描述：判断类型 2014-5-26 下午04:25:10 by sunxiaofeng
	 * 
	 * @version
	 * @param map
	 * @return
	 */
	private Integer isType(Map map) {
		if ("1".equals(StringUtil.objToString(map.get("STATE")))) {// 1表示结清
			return 1;
		} else if ("0".equals(StringUtil.objToString(map.get("STATE")))) {// 0表示逾期
			return 0;
		}
		return null;
	}

	private Integer isWarnType(Integer state, Integer flag) {
		if (1 == state) {// 结清
			return 1;
		} else if (0 == state) {// 逾期
			if (1 == flag) {// 1表示本金逾期
				return 5;
			} else if (2 == flag) {// 2表示逾期
				return 4;
			}
		}
		return 0;
	}

	/**
	 * 
	 * 描述： 2014-5-26 下午07:37:45 by sunxiaofeng
	 * 
	 * @version
	 * @param map
	 * @param state
	 *            类型
	 */
	private void sendReport(Map map, Integer state) throws Exception {
		EmailHistory emailHistory = new EmailHistory();
		Integer flag = 0;// 标识本金逾期和逾期
		boolean isSuccess = false;
		try {
			if (null != map && !"".equals(map)) {
				String title = "";// 标题
				String content = "";// 内容
				if (1 == state) {
					title = "结清报告";
					content = "<p>各位好：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
							+ map.get("COMPANYNAME") + "本月已完成支付，请知悉。</p>";
				} else if (0 == state) {
					String dateTo = Utils.getDate(Utils.toDate(map
							.get("LNTERESTTIME")));// 利息返还日
					String dateOne = Utils.getDate(Utils.toDate(map
							.get("EXPIRETIME")));// 到期日
					if (dateTo.equals(dateOne)) {// 判断利息返还日是否d等于到期日
						title = "紧急！！！本金逾期报告";
						content = "<p>各位好：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
								+ map.get("COMPANYNAME")
								+ "本次利息与本金支付时间应于"
								+ dateOne + "进行，目前尚未支付。</p>";
						flag = 1;
					} else {
						title = "紧急！！！逾期报告";
						content = "<p>各位好：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
								+ map.get("COMPANYNAME")
								+ "本次应于"
								+ dateTo
								+ "支付计息，目前尚未支付。</p>";
						flag = 2;
					}
				}
				List<PmInfo> pmInfoList = IPmInfoMapper.selectByRoleid();// 查询所有管理员
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < pmInfoList.size(); i++) {// 得到所有管理员不为空 的邮箱
					if (null != pmInfoList.get(i).getPmEmail()
							&& !"".equals(pmInfoList.get(i).getPmEmail())) {
						list.add(pmInfoList.get(i).getPmEmail());
					} else {
						logger.info("管理员" + pmInfoList.get(i).getPmName()
								+ "邮箱为空");
					}
				}
				// 根据pmuserid得到负责人邮箱
				PmInfo pminfo = IPmInfoMapper.queryPmInfoByUserId(Integer
						.parseInt(StringUtil.objToString(map.get("PMUSERID"))));
				if(null != pminfo){
				  if (null != pminfo.getPmEmail() && !"".equals(pminfo.getPmEmail())) {
				  	list.add(pminfo.getPmEmail());
				   } else {
					logger.info("负责人" + pminfo.getPmName() + "邮箱为空");
				  }
				}
				String[] toAddress = list.toArray(new String[list.size()]);
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				if (pr.getProperty("to_local_Address") != null
						&& "yes".equals(pr.getProperty("to_local_Address"))) {
					toAddress = pr.getProperty("toAddress").split(",");// 代码正式提交时邮件接收人为接口人邮箱，此行注释掉
				}
				if (toAddress.length != 0) {
					String fromAddress = pr
							.getProperty("PROJECTMANAGE_USERNAME");
					String password = pr.getProperty("PROJECTMANAGE_PASSWORD");
					isSuccess = SendMailUtilNew.sendMailToManyPerson(
							fromAddress, password, toAddress, title, content);

					if (isSuccess) {
						emailHistory.setSendState("1");
						logger.info("发送报告成功");
					} else {
						emailHistory.setSendState("0");
						logger.info("发送报告失败");
					}
				}else{
					emailHistory.setSendState("0");
					logger.info("发送报告失败,管理员和pm邮箱都为空");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			emailHistory.setSendState("0");
		} finally {
			// isWarnType
			emailHistory.setWarnType(StringUtil.objToString(isWarnType(state,
					flag)));
			emailHistory.setLoanNumber(StringUtil.objToString(map
					.get("LOANNUMBER")));
			emailHistory.setLoanTime(Utils.toDate(map.get("LOANTIME")));
			emailHistory.setExpireTime(Utils.toDate(map.get("EXPIRETIME")));
			emailHistory.setHead(StringUtil.objToString(map.get("HEAD")));
			emailHistory.setInterestTime(Utils.toDate(map.get("LNTERESTTIME")));
			IEmailHistoryMapper.insertEmailHistory(emailHistory);
		}
	}

	/**
	 * 手工发送逾期报告
	 */
	@Override
	public String sendOverdueReport(HttpServletRequest request)
			throws Exception {
		String importMessage = "操作成功";
		String jsonResult = "";
		Map map = projectMapper.selectByTimeFid(Integer.parseInt(request
				.getParameter("fid")));// 查看项目信息机利息返还日
		// 判断利息返还日和到期人是否为空
		if (!"".equals(StringUtil.objToString(map.get("LNTERESTTIME")))
				&& !"".equals(StringUtil.objToString(map.get("EXPIRETIME")))) {
			if (1 != isType(map)) {
				sendReport(map, 0);// 0表示逾期
			} else {
				importMessage = "该利息返还日已经结清，不能发送逾期报告。";
			}

		}
		return jsonResult = "{\"success\":\"true\", \"message\":\""
				+ importMessage + "\"}";
	}

	/**
	 * 导出项目信息列表EXCEL
	 */
	public void expProjectExcel(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNumber", request.getParameter("loanNumber"));
		map.put("companyName", request.getParameter("companyName"));
		map.put("pmUserId", request.getParameter("pmUserId"));
		map.put("loanWayId", request.getParameter("selectloanway"));
		map.put("startloanTime", request.getParameter("startloanTime"));
		map.put("endloanTime", request.getParameter("endloanTime"));
		map.put("startexpireTime", request.getParameter("startexpireTime"));
		map.put("endexpireTime", request.getParameter("endexpireTime"));
		map.put("mlyinterest", request.getParameter("mlyinterest"));
		map.put("mMangExpense", request.getParameter("mMangExpense"));
		map.put("authentication", request.getParameter("authentication"));
		map.put("repaymentWayId", request.getParameter("repaymentWayId"));
		map.put("lenders", request.getParameter("lenders"));
		map.put("borrower", request.getParameter("borrower"));
		map.put("repaymentAccount", request.getParameter("repaymentAccount"));
		map.put("bankAccount", request.getParameter("bankAccount"));
		map.put("account", request.getParameter("account"));
		map.put("starRepaymentMoney", request
				.getParameter("starRepaymentMoney"));
		map.put("endRepaymentMoney", request.getParameter("endRepaymentMoney"));
		map.put("starMoney", request.getParameter("starMoney"));
		map.put("endMoney", request.getParameter("endMoney"));
		map.put("term", request.getParameter("term"));
		map.put("state", request.getParameter("state"));
		map.put("startinterestTime", request.getParameter("startinterestTime"));
		map.put("endinterestTime", request.getParameter("endinterestTime"));
		List<HashMap<String, Object>> mapList = projectMapper
				.seletAllPmForExp(map);// 获取结果集
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
			logger.error(e.getMessage());
			logger.error("导出项目信息失败");
			e.printStackTrace();
		}
		out.close();// 关闭
		response.flushBuffer();
	}

	/**
	 * 导出项目信息
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void expExcelAgreementNumber(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("GBK");
		response.setContentType("application/vnd.ms-excel");
		String fileName = "合同信息列表";// 设置excel文件的名字
		String header = "attachment; filename="
				+ new String(URLDecoder.decode(fileName + ".xls", "UTF-8")
						.getBytes(), "iso8859-1");
		response.setHeader("Content-Disposition", header);
		OutputStream out = response.getOutputStream();
		String[] headers = { "贷款编号", "出借方式", "协议名称，", "应收数量", "收到数量", "财务保存数量",
				"档案处保存数量", "寄回数量", "备注" };
		String[] title = { "LOANNUMBER", "LOANWAY", "AGREEMENTNAME",
				"RECEIVABLEQUANTITY", "RECEIVEDNUMBER", "FINANCIALNUMBER",
				"ARCHIVESNUMBER", "SENDBACKNUMBER", "REMARKS" };
		Map map = new HashMap();
		map.put("projectFid", request.getParameter("projectFid"));
		List<Integer> agrList = agrNumberMapper.selectAgreement(Integer
				.parseInt(request.getParameter("loanWayId")));
		map.put("list", agrList);
		List<Map> list = agrNumberMapper.queryAgrNumber(map);
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
			if (list != null && !list.isEmpty()) {// 判断数据是否为空
				Object[] objs = list.toArray();// 转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1 = sheet.createRow(i + 1);// 创建行，设置从第二行开始
					row1.setHeight((short) 700);
					Map t = (Map) objs[i];
					for (int j = 0; j < title.length; j++) {
						if (title[j] != "") {
							String getMethodName = title[j];
							Object value = t.get(getMethodName);
							if ("REMARKS".equals(getMethodName)) {
								sheet.setColumnWidth(j, 10000);// 设置单元格的宽度
							}
							ExpExcelUtil.createTxtCell(wb, row1, j, value,
									ExpExcelUtil.createCollStyle(wb
											.createCellStyle(), wb));
						}
					}
				}
			}
			wb.write(out);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("导出合同信息失败");
			e.printStackTrace();
		}
		out.close();// 关闭
		response.flushBuffer();
	}
	/**
	 * 删除项目信息
	 */
	@Override
	public void deleteProject(Integer fid) {
		int n=projectMapper.deleteTime(fid);//删除利息返还日
		int m=agrNumberMapper.deleteAgrNumber(fid);//删除合同数量信息
		int i=projectMapper.deleteProject(fid);//删除项目信息
	}
}
