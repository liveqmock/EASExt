/**
 * 
 */
package com.creditease.eas.compliance.service;

import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.util.Pagination;

/**
 * @IFeedbackService.java	合规（初步调查——被投诉部门调查处理反馈要求service接口）
 * created at 2013-10-8 下午02:55:07 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IFeedbackRequiredService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(IFeedbackRequired feedbackRequired) throws Exception;
	
	
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
	public int update(IFeedbackRequired	feedbackRequired) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IFeedbackRequired getFeedbackRequiredById(Integer id) throws Exception;
	public void insertUserByEmail(String email,String createUserName) throws Exception;
	
	public IFeedbackRequired getFeedbackRequiredByInvestigationId(
			Integer investigationId) throws Exception ;
}
