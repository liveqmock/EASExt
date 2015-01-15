/**
 * 
 */
package com.creditease.eas.compliance.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.bean.IFeedbackResult;
import com.creditease.eas.util.Pagination;

/**
 * @IFeedbackResultService.java	合规（初步调查——被投诉部门调查处理反馈结果service接口）
 * created at 2013-10-8 下午04:37:43 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IFeedbackResultService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param iFeedbackResult
	 * @return
	 * @throws Exception
	 */
	public int insert(IFeedbackResult iFeedbackResult) throws Exception;
	
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
	 * @param iFeedbackResult
	 * @return
	 * @throws Exception
	 */
	public int update(IFeedbackResult iFeedbackResult) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IFeedbackResult getIFeedbackResultById(Integer id) throws Exception;
	/**
	 * 
	 * 描述：根据调查记录id查询相关的部门反馈表记录id
	 * 2013-10-15 下午02:56:49 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public int getFeedBackId(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：获取反馈结果下拉列表集合
	 * 2013-10-15 下午04:13:12 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getFeedBackTypes() throws Exception;
	/**
	 * 
	 * 描述：获取下一步方案下拉列表集合
	 * 2013-10-15 下午04:13:12 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getNextSchemes() throws Exception;
	public IFeedbackRequired getFeedbackRequiredById(int feedbackResultId) throws Exception;
}
