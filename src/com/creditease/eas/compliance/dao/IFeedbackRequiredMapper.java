package com.creditease.eas.compliance.dao;

import java.util.Map;

import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.util.BaseDAO;

/**
 * @IFeedbackMapper.java	合规（初步调查——被投诉部门调查处理反馈要求Dao）
 * created at 2013-10-8 下午02:39:40 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IFeedbackRequiredMapper extends BaseDAO<IFeedbackRequired, Integer>{
   
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
    int insert(IFeedbackRequired feedbackRequired);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    IFeedbackRequired selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(IFeedbackRequired feedbackRequired);
    
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
    
    public IFeedbackRequired selectIfeedbackRequireByVisitigation(Integer id);
}