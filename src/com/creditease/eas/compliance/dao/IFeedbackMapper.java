package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.compliance.bean.IFeedback;
import com.creditease.eas.util.BaseDAO;

/**
 * @IFeedbackMapper.java	合规（初步调查——被投诉部门调查处理反馈Dao）
 * created at 2013-10-8 下午02:39:40 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IFeedbackMapper extends BaseDAO<IFeedback, Integer>{
   
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
    int insert(IFeedback iFeedback);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    IFeedback selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(IFeedback iFeedback);
    
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
    /**
	 * 
	 * 描述：获取反馈要求Id
	 * 2013-10-15 上午11:31:59 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public int getFeedbackRequiredId(@Param("investigationId")Integer investigationId) throws Exception;
	/**
	 * 
	 * 描述：获取实际调查表外键Id
	 * 2013-10-15 上午11:31:59 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public int getFieldsurveyId(@Param("investigationId")Integer investigationId) throws Exception;
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
}