package com.creditease.eas.projectmanage.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creditease.eas.projectmanage.bean.AgreementNumber;
import com.creditease.eas.projectmanage.bean.ProjectManage;
import com.creditease.eas.util.Pagination;

public interface IProjectService {
     /**
      * 
      * 描述：查询项目信息列表
      * 2014-3-13 下午02:41:25 by sunxiaofeng
      * @version
      * @param pagination
      * @param request
      * @return
      */
	public Pagination queryPage(Pagination pagination, HttpServletRequest request);
    /**
     * 
     * 描述：加载出借方式
     * 2014-3-13 下午06:16:13 by sunxiaofeng
     * @version
     * @return  json字符串
     */
	public String selectLoanway();
	/**
	 * 
	 * 描述：加载还款方式
	 * 2014-3-13 下午06:33:22 by sunxiaofeng
	 * @version
	 * @return  json字符串
	 */
	public String selectSituationname();
	/**
	 * 
	 * 描述：加载项目状态
	 * 2014-3-13 下午06:42:38 by sunxiaofeng
	 * @version
	 * @return  json字符串
	 */
	public String selectState();
	/**
	 * 
	 * 描述：加载贷后管理负责人(PM)
	 * 2014-3-13 下午06:50:35 by sunxiaofeng
	 * @version
	 * @return json字符串
	 */
	public String selectPm();
	/**
	 * 
	 * 描述：更加id查询项目信息id
	 * 2014-3-14 下午02:06:37 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public ProjectManage edit(Integer fid);
	/**
	 * 
	 * 描述：新增项目信息
	 * 2014-3-14 下午03:25:11 by sunxiaofeng
	 * @version
	 * @param projectManage
	 */
	public void insert(ProjectManage projectManage) throws Exception;
	/**
	 * 
	 * 描述：修改项目信息
	 * 2014-3-14 下午06:17:23 by sunxiaofeng
	 * @version
	 * @param projectManage
	 */
	public void update(ProjectManage projectManage);
	/**
	 * 
	 * 描述：查询合同信息列表展示
	 * 2014-3-19 下午04:25:51 by sunxiaofeng
	 * @version
	 * @param pagination
	 * @param request
	 * @return
	 */
	public Pagination queryAgrNumber(Pagination pagination,
			HttpServletRequest request);
	/**
	 * 
	 * 描述：根据fid 查询合同数量信息
	 * 2014-3-19 下午05:17:14 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public Map editAgreement(Integer fid);
	/**
	 * 
	 * 描述：修改合同数量
	 * 2014-3-19 下午06:18:35 by sunxiaofeng
	 * @version
	 * @param agreementNumber
	 */
	public void updateAgrNumber(AgreementNumber agreementNumber);
	/**
	 * 
	 * 描述：查询利息返还日
	 * 2014-3-20 上午10:37:45 by sunxiaofeng
	 * @version
	 * @param pagination
	 * @param request
	 * @return
	 */
	public Pagination queryIinterestTime(Pagination pagination,
			HttpServletRequest request);
	/***
	 * 
	 * 描述：修改利息返还其状态
	 * 2014-3-20 下午05:22:18 by sunxiaofeng
	 * @version
	 * @param send 
	 */
	public void updateInTimeState(String state , String fid, String send) throws Exception;
	/**
	 * 
	 * 描述：判断编号是否重复
	 * 2014-3-24 下午03:42:24 by sunxiaofeng
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean findLoanNumberExist() throws Exception;
	/**
	 * 
	 * 描述：每天8点执行发送提醒邮件（发送存续期提醒是在利息返还日前两天发送，发送到期还款提醒是在到期日的前10天和前15天发送）
	 * 2014-3-24 下午03:44:45 by sunxiaofeng
	 * @version
	 */
    public void sendWarnEmail()throws Exception;
    /**
     * 
     * 描述：每天22点执行发送报告出周六日以外
     * 2014-4-23 上午08:18:36 by sunxiaofeng
     * @version
     * @throws Exception
     */
    public void sendReportEmail() throws Exception;
	/**
	 * 描述：导出项目信息
	 * @param request
	 * @throws IOException
	 */
	public void expProjectExcel(HttpServletRequest request,HttpServletResponse response) throws IOException;
	/**
	 * 
	 * 描述：手工发送逾期报告
	 * 2014-5-26 下午07:22:31 by sunxiaofeng
	 * @version
	 * @param request 
	 * @param pagination 
	 */
	public String sendOverdueReport(HttpServletRequest request) throws Exception;
	/**
	 * 
	 * 描述：导出合同信息
	 * 2014-4-4 下午05:33:39 by sunxiaofeng
	 * @version
	 * @param request
	 * @param response
	 */
	public void expExcelAgreementNumber(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
	 * 
	 * 描述：验证利息返还其是否逾期
	 * 2014-4-8 上午10:01:01 by sunxiaofeng
	 * @version
	 * @param request
	 * @return
	 */
	public String vailTimeOverdue(HttpServletRequest request) throws Exception;
	public void deleteProject(Integer fid);

}
