/**
 * 
 */
package com.creditease.eas.compliance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.compliance.bean.BaseType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.compliance.bean.Investigation;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.util.Pagination;

/**
 * @InvestigationService.java	合规初步调查service接口
 * created at 2013-10-7 下午04:06:08 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface InvestigationService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(Investigation investigation) throws Exception;
	
	/**
	 * 
	 * 描述：查询列表分布数据
	 * 2013-9-16 上午11:05:47 by caoyong
	 * @version
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Pagination queryPage(Pagination page) throws Exception;
	
	public Pagination queryPageForEmail(Pagination page) throws Exception;
	
	/**
	 * 
	 * 描述：根据id删除数据
	 * 2013-9-16 上午11:06:12 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Integer id) throws Exception ;
	
	/**
	 * 描述：更新数据Entity
	 * 2013-10-7 下午04:10:36 by caoyong
	 * @version
	 * @param financeRentContract
	 * @return
	 * @throws Exception
	 */
	public int update(Investigation	investigation) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Investigation getInvestigationById(Integer id) throws Exception;
	/**
	 * 
	 * 描述：查找所有的调查方式下拉列表集合
	 * 2013-10-9 下午06:19:24 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getTypeIds() throws Exception;
	/**
	 * 
	 * 描述：查找案件初步分类下拉列表集合
	 * 2013-10-9 下午06:19:24 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getIniCaseTypes() throws Exception;
	/**
	 * 
	 * 描述：客户来源下拉列表集合
	 * 2013-10-9 下午06:19:24 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getCusSources() throws Exception;
	/**
	 * 
	 * 描述：查找所有的下一步方案下拉列表集合
	 * 2013-10-9 下午06:19:46 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getNextSchemeIds() throws Exception;
	/**
	 * 
	 * 描述：根据父ID查询所有的子调查记录
	 * 2013-10-12 上午11:44:39 by caoyong
	 * @version
	 * @param parentId
	 * @return
	 */
	public List<Investigation> getChildInvestigations(Integer parentId) throws Exception;
	/**
	 * 
	 * 描述：更新案件状态
	 * 2013-10-12 下午03:30:29 by caoyong
	 * @version
	 * @param statusId
	 * @param id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateStatus(Map map) throws Exception;
	/**
	 * 
	 * 描述：发送邮件给案件负责人（跟进案件最新状态）
	 * 2013-10-23 下午06:16:45 by caoyong
	 * @version
	 * @param map
	 * @throws Exception
	 */
	/**
	 * 描述：修改调查状态时发送邮件
	 * 2013-10-29 下午03:36:59 by caoyong
	 * @version
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean sendEmail(Map map) throws Exception;
	/**
	 * 描述：根据调查id获取关联的案件
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public Complain getComplainByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取关联的转部门反馈要求记录
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedbackRequired> getFeedbackRequiredByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取反馈结果
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedback> getFeedbackByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取反馈结果
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedbackResult> getFeedbackResultByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取关联的实地调查记录
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Fieldsurvey> getFieldsurveyByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取关联的再次协助落实结果记录
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<ZcxzlsResult> getZcxzlsfbResultByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取关联的申请结案记录
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Applysettlement> getApplysettlementByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 描述：根据调查id获取关联的审核记录集合
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<AuditResult> getAuditResultsByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的案件的初步分类和详细分类结果集合
	 * 2013-11-7 下午02:36:32 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Integer>> getComplainTypesByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的案件的多个被投诉人记录集合
	 * 2013-11-7 下午02:37:53 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Person> getCompalinPersonsByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的案件的多个被投诉部门记录集合
	 * 2013-11-7 下午05:30:00 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<DEPTINFO> getDeptinfosByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据人员主键查询人员entity
	 * 2013-11-7 下午04:24:42 by caoyong
	 * @version
	 * @param personId
	 * @return
	 * @throws Exception
	 */
	public Person getPersonById(Integer personId) throws Exception;

	public Pagination queryInvestFile(Pagination pagination) throws Exception ;
	public InvestFile findFileName(int id);
	public int updateByinvestigation(IFeedbackRequired investigation);
	
	/**
	 * 根据调查ID得到BaseType集合
	 * @param investigationId
	 * @return
	 */
	public List<BaseType> getBaseTypesByInvestigationId(Integer investigationId);

	/**
	 * 案件状态
	 * @return
	 */
	public List<Map> getInvestigationState();

	/**
	 * 400回访
	 * @param pagination
	 * @return
	 */
	public Pagination queryPageForCallBack(Pagination page) throws Exception;
	
	Investigation querySingleCallBackContent(Integer id) throws Exception;

	/**
	 * 根据调查ID获得新分类
	 * @param id
	 * @return
	 */
	public List<CaseType> getNewCaseType(Integer id);

	/**
	 * 导出
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
