package com.creditease.eas.compliance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.compliance.bean.DEPTINFO;
import com.creditease.eas.compliance.bean.Person;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.util.Pagination;
/***
 * 案件相关的信息
 * @CaseInfoService.java
 * created at 2013-10-8 下午02:03:08 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ICaseInfoService {
	/**
	 * 描述：添加投诉的基本信息
	 * 2013-10-11 上午11:02:46 by ygq
	 * @version
	 * @param request
	 * @return
	 */
	public int addCompliant(Complain complain,HttpSession session) throws Exception;
	/**
	 * 描述：添加案件的基本信息
	 * 2013-10-11 上午11:02:38 by ygq
	 * @version
	 * @param request
	 * @return
	 */
	public int addCaseInfo(String json,Complain complain,HttpServletRequest request,HttpSession session) throws Exception;
	/**
	 * 描述：修改案件的基本信息
	 * 2013-10-11 上午11:02:38 by ygq
	 * @version
	 * @param request
	 * @return
	 */
	public int updateCaseInfo(Complain complain,HttpServletRequest request,HttpSession session) throws Exception;
	/**
	 * 描述：查询案件的列表信息
	 * 2013-10-11 下午05:15:38 by ygq
	 * @version
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Pagination queryPage(Pagination page,HttpServletRequest request) throws Exception;
	/**
	 * 描述：查询客户来源
	 * 2013-10-14 下午03:55:06 by ygq
	 * @version
	 * @return
	 */
	public String findCussource();
	
	/**
	 * 描述：查询服务类型
	 * 2013-10-14 下午03:55:06 by ygq
	 * @version
	 * @return
	 */
	public String findServicetype();
	/**
	 * 描述：查询客户状态
	 * 2013-10-14 下午03:55:06 by ygq
	 * @version
	 * @return
	 */
	public String findCusstatus(Integer fservicetypeid);
	/*******************************addCase 相关的信息*********************************************************************************************/
	/***
	 * 
	 * 描述：投诉渠道 
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return
	 */
	public String findDitch();
	/***
	 * 描述:证据类型
	 * 2013-10-16 下午09:02:46 by ygq
	 * @version
	 * @return 证据类型的json代码
	 */
	public String findEvidenceType();
	/**
	 * 查询组织信息
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public Pagination selectAdmin(Pagination pagination) throws Exception;
	/**
	 * 描述：查询组织信息
	 * 2014-1-15 上午10:01:57 by caoyong
	 * @version
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List getOrgData() throws Exception;
	/**
	 * 查询案件初步分类 
	 * @return
	 */
	public String findInicaseType();
	/**
	 * 查询案件详细分类
	 * @return
	 */
	public String findDetailCaseType(Integer finicasetype);
	/***
	 * 根据被投诉人的姓名查询这个人被投诉的次数
	 * @param map
	 * @return
	 */
	public Integer findPersonCompCount(HttpServletRequest request);
	/***
	 * 根据被投诉人的姓名查询涉及到的案子
	 * @param map
	 * @return
	 */
	public String findPersonRelComplian(HttpServletRequest request);
	/**
	 * 根据id查询案件信息
	 * 描述：
	 * @return
	 */
	public Complain findCase(String fid);
	/**
	 * 
	 * 编辑案件信息
	 * @param complainInfo
	 */
	public void updateCompliant(Complain complainInfo);
	/***
	 * 根据案件id，查询关联的案件的初步分类
	 * @param complainid
	 * @return
	 * @throws Exception
	 */
	public List<RelInicasetype> selectInicaseTypeByComplainId(Integer complainid) throws Exception;
	  /**
     *根据案件id，查询关联的人员信息
     * @param complainid
     * @return
     * @throws Exception
     */
    public List<Person> selectPersonByComplainId(Integer complainid) throws Exception;
    /***
     * 根据案件id，查询案件信息
     * @param complainid
     * @return
     * @throws Exception
     */
    public Complain findCaseInfoById(String complainid) throws Exception;
    /**
     * 根据案件id查询部门信息
     * @param complainid
     * @return
     */
	public List<DEPTINFO> selectDeptInfoByComplainId(int complainid);
	/**
	 * 
	 * 描述：根据手机号查询该手机被投诉的次数
	 * 2013-11-12 下午03:12:04 by sunxiaofeng
	 * @version
	 * @param request
	 * @return
	 */
	public String findFmobileCount(HttpServletRequest request);
	/**
	 * 
	 * 描述：根据QQ号查询该QQ号被投诉次数
	 * 2013-11-12 下午03:48:52 by sunxiaofeng
	 * @version
	 * @param request
	 * @return
	 */
	public Integer findFqqCount(HttpServletRequest request);
     /**
      * 
      * 描述：根据客户姓名查询该客户被投诉次数
      * 2013-11-13 下午02:18:29 by sunxiaofeng
      * @version
      * @param request
      * @return
      */
	public Integer findFcusNameCount(HttpServletRequest request);
	/**
	 * 
	 * 描述：根据案件id查询相关案件信息
	 * 2013-11-15 下午03:45:21 by sunxiaofeng
	 * @version
	 * @param parameter
	 * @return
	 */
	public Map findCaseById(String fid);
	
	public String getAllCaseType();
	
	/**
	 * 由缓存的Complain对象JSON字符串和Complain对象，返回一个Complain对象
	 * @param json
	 * @param target
	 * @return
	 */
	public Complain getTargetComplain(String json,Complain complian) throws Exception;
	
	
	public List<CaseType> getNewCaseType() throws Exception;
	
	public void setCaseTypeIsChecked(List<CaseType> newCaseType,List<RelInicasetype> relInicasetypes) throws Exception;
	
	/**
	 * 导出
	 * @param request HttpRequest
	 * @param response HttpResponse
	 * @throws Exception 
	 */
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	/**
	 * 去除关联自身的案子
	 * @param request
	 * @return 
	 */
	public Integer findPersonCompCountExceptSelf(HttpServletRequest request);
	
	/**
	 * 查找除去自身的案子
	 * @param request
	 * @return
	 */
	public String findPersonRelComplianExcepSelf(HttpServletRequest request);
	
	public String findCaseFromType() throws Exception;

}
