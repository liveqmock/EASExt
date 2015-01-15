/**
 * 
 */
package com.creditease.eas.compliance.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.util.Pagination;

/**
 * @FieldsurveyService.java	合规（初步调查——实地调查情况service接口）
 * created at 2013-10-8 下午02:26:27 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FieldsurveyService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(Fieldsurvey fieldsurvey) throws Exception;
	
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
	public int update(Fieldsurvey fieldsurvey) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Fieldsurvey getFieldsurveyById(Integer id) throws Exception;
	/**
	 * 
	 * 描述：获取协助部门反馈结果下拉列表集合
	 * 2013-10-17 上午11:17:20 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map> getXzbmfbResults() throws Exception;
	public Fieldsurvey getFieldsurvey(Integer investigationId) throws Exception;
}
