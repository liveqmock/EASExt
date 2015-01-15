package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.Fieldsurvey;
import com.creditease.eas.util.BaseDAO;

/**
 * @FieldsurveyMapper.java	合规（初步调查——实地调查情况Dao）
 * created at 2013-10-8 下午02:27:42 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FieldsurveyMapper extends BaseDAO<Fieldsurvey, Integer>{
   
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
    int insert(Fieldsurvey fieldsurvey);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    Fieldsurvey selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(Fieldsurvey fieldsurvey);
    
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