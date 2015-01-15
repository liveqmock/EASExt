/**
 * 
 */
package com.creditease.eas.compliance.service;

import java.util.List;

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.compliance.bean.ZcxzlsResult;
import com.creditease.eas.util.Pagination;

/**
 * @ZcxzlsfbresultService.java	合规（初步调查——再次协助落实反馈结果表service接口）
 * created at 2013-10-8 下午05:10:24 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ZcxzlsResultService {
	/**
	 * 描述：插入新记录
	 * 2013-10-7 下午04:09:09 by caoyong
	 * @version
	 * @param investigation
	 * @return
	 * @throws Exception
	 */
	public int insert(ZcxzlsResult zcxzlsfbResult) throws Exception;
	
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
	public int update(ZcxzlsResult zcxzlsfbResult) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ZcxzlsResult getZcxzlsfbResultById(Integer id) throws Exception;
	/**
	 * 
	 * 描述：根据调查记录外键id获取所有的反馈结果集合
	 * 2013-10-18 上午09:53:47 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public List<IFeedback> getRelationFeedBacks(Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：根据调查记录外键id获取关联的本部门实地调查记录
	 * 2013-10-18 下午03:36:41 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public Fieldsurvey getFieldsurvey(Integer investigationId) throws Exception;
}
