package com.creditease.eas.compliance.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.compliance.bean.AuditResult;
import com.creditease.eas.util.BaseDAO;

/**
 * @AuditresultMapper.java	合规（初步调查——审核结果Dao）
 * created at 2013-10-8 上午11:34:25 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface AuditResultMapper extends BaseDAO<AuditResult, Integer>{
   
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
    int insert(AuditResult auditResult);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    AuditResult selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(AuditResult auditResult);
    
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
	 * 描述：根据调查记录id查询相关的申请记录id
	 * 2013-10-15 下午02:56:49 by caoyong
	 * @version
	 * @param investigationId
	 * @return
	 * @throws Exception
	 */
	public int getApplysettlementId(@Param("investigationId")Integer investigationId) throws Exception;
}