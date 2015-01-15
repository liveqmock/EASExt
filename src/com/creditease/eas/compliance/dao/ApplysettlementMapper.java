package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.util.BaseDAO;

/**
 * @ApplysettlementMapper.java	合规（申请结案记录Dao）
 * created at 2013-10-8 上午10:17:27 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface ApplysettlementMapper extends BaseDAO<Applysettlement, Integer>{
   
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
    int insert(Applysettlement applysettlement);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    Applysettlement selectByPrimaryKey(Integer id);
    /**
     * 
     * 描述：根据外键investigationId获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    Applysettlement getByInvestigationId(Integer investigationId);
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(Applysettlement applysettlement);
    
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
	 * 描述：特殊结案归类下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getSeClasssifys();
	/**
	 * 
	 * 描述：违规级别下拉列表集合
	 * 2013-10-10 下午06:25:43 by caoyong
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<Map> getOutofLineLevelIds();
	@SuppressWarnings("unchecked")
	List<Map> getRelInitypes(int applysettlementId) throws Exception;

	List<RelInicasetype> getRelInicasetypesByApplysettlementId(Integer id); 
}