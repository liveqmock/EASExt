package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

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
import com.creditease.eas.util.BaseDAO;

/**
 * @InvestigationMapper.java	合规初步调查Dao
 * created at 2013-10-7 下午04:12:32 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface InvestigationMapper extends BaseDAO<Investigation, Integer>{
   
	/**
	 * 
	 * 描述：根据主键id删除记录
	 * 2013-9-16 上午11:10:52 by caoyong
	 * @version
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
    
	/**
	 * 
	 * 描述：插入数据Entity
	 * 2013-9-16 上午11:11:10 by caoyong
	 * @version
	 * @param record
	 * @return
	 */
    int insert(Investigation investigation);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    Investigation selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(Investigation investigation);
    
    /**
     * 
     * 描述：查询列表而记录条数
     * 2013-9-16 下午05:46:12 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public int getTotalCounts(Map map);
    
    @SuppressWarnings("unchecked")
	public int getTotalCountsForEmail(Map map);
@SuppressWarnings("unchecked")
	public List<Map> queryDataForEmail(Map map) throws Exception;

    /**
     * 
     * 描述：查询待办列表而记录条数
     * 2013-9-16 下午05:46:12 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public int getPrepareDoTotalCounts(Map map);
    /**
     * 
     * 描述：查询已办列表而记录条数
     * 2013-9-16 下午05:46:12 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    public int getAlreadyDoTotalCounts(Map map);
    /**
     * 
     * 描述：查询待办列表而记录
     * 2013-9-16 下午05:46:12 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map> queryPrepareDoData(Map params);
    /**
     * 
     * 描述：查询已办列表而记录
     * 2013-9-16 下午05:46:12 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map> queryAlreadyDoData(Map params);
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
	 * @param map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateStatus(Map map) throws Exception;
	@SuppressWarnings("unchecked")
	public List<Map> queryData(Map map) throws Exception;
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
	public Complain getComplainByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：获取关联的转部门调查要求信息记录集合
	 * 2013-11-4 下午01:40:03 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedbackRequired> getFeedbackRequiredByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 描述：根据关联的转部门反馈要求记录id获取反馈结果
	 * 2013-10-29 下午03:38:13 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedback> getFeedbackByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取反馈结果
	 * 2013-11-4 下午02:11:04 by caoyong
	 * @version
	 * @param feedbackId
	 * @return
	 * @throws Exception
	 */
	public  List<IFeedbackResult> getFeedbackResultByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的实地调查记录
	 * 2013-11-4 下午02:31:11 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Fieldsurvey> getFieldsurveyByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的再次协助落实结果记录
	 * 2013-11-4 下午02:47:09 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<ZcxzlsResult> getZcxzlsfbResultByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的申请结案记录
	 * 2013-11-4 下午03:02:33 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<Applysettlement> getApplysettlementByInvestigationId(Integer investigationId)throws Exception;
	/**
	 * 
	 * 描述：根据调查id获取关联的审核记录集合
	 * 2013-11-4 下午03:02:47 by caoyong
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
    * 描述：保存上传文件的信息
    * 2013-10-26 下午03:54:01 by sunxiaofeng
    * @version
    * @param investfile
    */
	public int addInvestFile(InvestFile investfile);
   /**
    * 
    * 描述：查询文件信息数量
    * 2013-10-29 上午10:44:27 by sunxiaofeng
    * @version
    * @param map
    * @return
    */
    public int getInvestFileCounts(Map map);
    /**
     * 
     * 描述：查询文件列表信息
     * 2013-10-29 上午10:45:37 by sunxiaofeng
     * @version
     * @param map1
     * @return
     */
	public List queryInvestFile(Map map1);
	/**
	 * 修该协助负责人信息
	 * @param investigation
	 * @return
	 */
	public int updateByinvestigation(IFeedbackRequired investigation);

	public InvestFile findFileName(int id);
	
		
	/**
	 * 根据调查ID得到BaseType集合
	 * @param investigationId
	 * @return
	 */
	public List<BaseType> selectBaseTypeByInvestigationId(Integer investigationId);

	/**
	 * 得到案件状态
	 * @return
	 */
	List<Map> selectInvestigationState();

	/**
	 * 得到回访总数
	 * @param map
	 * @return
	 */
	int getTotalCountForCallBack(Map map);

	/**
	 * 得到回访集合
	 * @param map1
	 * @return List
	 */
	List queryDataForCallBack(Map map1);
	
	Investigation querySingleCallBackContent(Integer id);

	List<CaseType> getNewCaseType(Integer id);
	
	//List<CaseDetailType> getCaseDetailType(List<String> ids);
	
	/**
	 * 反欺诈提报合规，保存在t_com_investigation表中
	 */
	int SaveInvestigationLoanAnti(Map map) throws Exception;
	
	/**
	 * 由案件的进件编号获取调查的数量
	 * @param code 进件编号
	 * @return
	 * @throws Exception
	 */
	int getInvestigationCountByComeIntoCode(String code) throws Exception;
	
	/**
	 * 由案件的进件编号获取调查的主键
	 * @param code
	 * @return
	 * @throws Exception
	 */
	Integer getInvestigationIdByComeIntoCode(String code) throws Exception;
	
	List<NextCommunicateTimeForEmail> getNextCommunicateMap(Map map) throws Exception;
	
}