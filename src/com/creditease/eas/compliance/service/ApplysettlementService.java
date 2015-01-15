/**
 * 
 */
package com.creditease.eas.compliance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.util.Pagination;

/**
 * @ApplysettlementService.java	合规（申请结案记录service接口）
 * created at 2013-10-8 上午10:31:53 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ApplysettlementService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(Applysettlement applysettlement) throws Exception;
	
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
	public int update(Applysettlement applysettlement) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Applysettlement getApplysettlementById(Integer id) throws Exception;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Applysettlement getByInvestigationId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：案件最终归类下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getCfClassifyIds();
	/**
	 * 
	 * 描述：是否违规下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getOutofLineIds();
	/**
	 * 
	 * 描述：违规级别下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getOutofLineLevelIds();
	/**
	 * 
	 * 描述：特殊结案归类下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getSeClasssifys();
	void insertIntoRelInitype(int applysettlementId,String[] initypes) throws Exception;
	void deleteRelInitype(int applysettlementId) throws Exception;
	@SuppressWarnings("unchecked")
	List<Map> getRelInitypes(int applysettlementId) throws Exception; 
	
	public void insertIntoRelInitype(int applysettlementId,HttpServletRequest request,HttpSession session ) throws Exception;

	public List<RelInicasetype> getRelInicasetypesByApplysettlementId(Integer id);
}
