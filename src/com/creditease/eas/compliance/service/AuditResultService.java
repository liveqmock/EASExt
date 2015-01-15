/**
 * 
 */
package com.creditease.eas.compliance.service;

import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.util.Pagination;

/**
 * @AuditresultService.java	合规（初步调查——审核结果service接口）
 * created at 2013-10-8 上午11:51:10 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface AuditResultService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(AuditResult auditResult) throws Exception;
	
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
	public int update(AuditResult auditResult) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AuditResult getAuditResultById(Integer id) throws Exception;
	/**
	 * 
	 * 描述：根据调查记录id查询相关的申请记录id
	 * 2013-10-15 下午02:56:49 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public int getApplysettlementId(Integer investigationId) throws Exception;
}
