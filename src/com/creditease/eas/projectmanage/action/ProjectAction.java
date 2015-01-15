package com.creditease.eas.projectmanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.core.utils.StringUtils;
import com.creditease.eas.projectmanage.bean.AgreementNumber;
import com.creditease.eas.projectmanage.bean.ProjectManage;
import com.creditease.eas.projectmanage.service.IProjectService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.action.FinanceRentContractAction;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.interceptor.annotations.Allowed;

/**
 * 项目信息管理模块
 * 
 * @ProjectAction.java created at 2014-3-13 下午02:39:33 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Controller
@Scope("prototype")
public class ProjectAction extends BaseAction {
	private IProjectService projectServiceImpl;
	private ProjectManage projectManage;
	private AgreementNumber agreementNumber;
	private Map numberMap;
	private static Logger logger = Logger
			.getLogger(FinanceRentContractAction.class);

	/**
	 * 
	 * 描述：查询项目信息 2014-3-13 下午02:40:38 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = projectServiceImpl.queryPage(pagination, request);
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：加载出借方式 2014-3-13 下午06:15:33 by sunxiaofeng
	 * 
	 * @version
	 */
	public void selectLoanway() {
		try {
			String json = projectServiceImpl.selectLoanway();
			writerJsonToClient(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * 描述加载还款方式 2014-3-13 下午06:41:27 by sunxiaofeng
	 * 
	 * @version
	 */
	public void selectSituationname() {
		try {
			String json = projectServiceImpl.selectSituationname();
			writerJsonToClient(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * 描述：加载项目信息 2014-3-13 下午06:46:33 by sunxiaofeng
	 * 
	 * @version
	 */
	public void selectState() {
		try {
			String json = projectServiceImpl.selectState();
			writerJsonToClient(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * 描述：加载贷后管理负责人(PM) 2014-3-13 下午06:49:59 by sunxiaofeng
	 * 
	 * @version
	 */
	public void selectPm() {
		try {
			String json = projectServiceImpl.selectPm();
			writerJsonToClient(json);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 
	 * 描述：跳转编辑和新增页面 2014-3-14 下午02:05:03 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String edit() {
		if (null != projectManage) {
			projectManage = projectServiceImpl.edit(projectManage.getFid());
		}
		return "edit";
	}

	/**
	 * 
	 * 描述：新增项目信息 2014-3-14 下午03:24:47 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception {
		try {
			if (!"".equals(request.getParameter("mMangExpense"))
					&& null != request.getParameter("mMangExpense")) {
				projectManage.setmMangExpense(Double.parseDouble(request
						.getParameter("mMangExpense")));
			}
			projectServiceImpl.insert(projectManage);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		} finally {
			this.writerJsonToClient(this.json);
		}
	}

	/**
	 * 
	 * 描述：修改项目信息 2014-3-14 下午06:16:19 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception {
		try {
			if (!"".equals(request.getParameter("mMangExpense"))
					&& null != request.getParameter("mMangExpense")) {
				projectManage.setmMangExpense(Double.parseDouble(request
						.getParameter("mMangExpense")));
			}
			projectServiceImpl.update(projectManage);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		} finally {
			this.writerJsonToClient(this.json);
		}
	}

	/**
	 * 
	 * 描述：查询项目信息 2014-3-19 下午04:01:52 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String selectProject() {
		projectManage = projectServiceImpl.edit(projectManage.getFid());
		return "selectProject";
	}

	/**
	 * 
	 * 描述：查询合同信息列表展示 2014-3-19 下午04:24:49 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String queryAgrNumber() {
		this.pagination = projectServiceImpl
				.queryAgrNumber(pagination, request);
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：跳转合同编辑页面 2014-3-19 下午05:14:55 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String editAgreement() {
		numberMap = projectServiceImpl.editAgreement(agreementNumber.getFid());
		return "editAgreement";
	}

	/**
	 * 
	 * 描述：修改合同数量 2014-3-19 下午06:15:54 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void updateAgrNumber() throws Exception {
		try {
			projectServiceImpl.updateAgrNumber(agreementNumber);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		} finally {
			this.writerJsonToClient(this.json);
		}
	}

	/**
	 * 
	 * 描述：查看利息返还日 2014-3-20 下午05:19:26 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String selectInterestTime() {
		projectManage = projectServiceImpl.edit(projectManage.getFid());
		return "interestTime";
	}

	/**
	 * 
	 * 描述： 2014-4-8 上午09:57:58 by sunxiaofeng
	 * 
	 * @version
	 * @throws IOException
	 */
	public void vailTimeReport() throws IOException {
		try {
			this.json = projectServiceImpl.vailTimeOverdue(request);
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\", \"message\":\"验证失败\"}";
		} finally {
			response.getWriter().print(this.json);
		}
	}

	/**
	 * 
	 * 描述：修改利息返还日状态 2014-3-20 下午05:19:44 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void updateInTimeState() throws Exception {
		try {
			projectServiceImpl.updateInTimeState(request.getParameter("state"),
					request.getParameter("fid"), request.getParameter("send"));
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		} finally {
			this.writerJsonToClient(this.json);
		}
	}

	/**
	 * 
	 * 描述：查询利息返还日 2014-3-20 上午10:36:27 by sunxiaofeng
	 * 
	 * @version
	 * @return
	 */
	public String queryIinterestTime() {
		this.pagination = projectServiceImpl.queryIinterestTime(pagination,
				request);
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：判断贷款编号是否重复 2014-3-24 下午03:41:54 by sunxiaofeng
	 * 
	 * @version
	 */
	public void findLoanNumberExist() {
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = projectServiceImpl.findLoanNumberExist();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.print(exist);
			pw.close();
		}
	}

	public void expExcel() throws Exception {
		projectServiceImpl.expProjectExcel(request, response);
	}

	/**
	 * 
	 * 描述：手工发送逾期报告 2014-5-26 下午07:18:59 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void sendOverdueReport() throws Exception {
		try {
			this.json = projectServiceImpl.sendOverdueReport(request);
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\", \"message\":\"发送失败\"}";
		} finally {
			response.getWriter().print(this.json);
		}
	}
	/**
	 * 
	 * 描述：删除项目信息
	 * 2014-4-18 下午04:25:10 by sunxiaofeng
	 * @version
	 */
	public void deleteProject(){
    	projectServiceImpl.deleteProject(projectManage.getFid());
    }
    
	public void expExcelAgreementNumber() throws Exception {
		projectServiceImpl.expExcelAgreementNumber(request, response);
	}

	public ProjectManage getProjectManage() {
		return projectManage;
	}

	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}

	public AgreementNumber getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(AgreementNumber agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public Map getNumberMap() {
		return numberMap;
	}

	public void setNumberMap(Map numberMap) {
		this.numberMap = numberMap;
	}

	public IProjectService getProjectServiceImpl() {
		return projectServiceImpl;
	}

	public void setProjectServiceImpl(IProjectService projectServiceImpl) {
		this.projectServiceImpl = projectServiceImpl;
	}
    
}
