package com.creditease.eas.warn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.FinanceRentContract;
import com.creditease.eas.warn.bean.FinanceUser;

/**
 * @FinanceRentContractMapper.java 财务房租合同信息Dao
 * created at 2013-9-16 上午11:10:18 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FinanceRentContractMapper extends BaseDAO<FinanceRentContract, Integer>{
   
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
    int insert(FinanceRentContract financeRentContract);

    /**
     * 
     * 描述：根据主键id获取Entity
     * 2013-9-16 上午11:12:04 by caoyong
     * @version
     * @param id
     * @return
     */
    FinanceRentContract selectByPrimaryKey(Integer id);
   
    
    /**
     * 
     * 描述：更新数据Entity
     * 2013-9-16 上午11:12:29 by caoyong
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(FinanceRentContract financeRentContract);
    /**
     * 
     * 描述：根据条件查询结果
     * 2013-9-16 下午03:48:02 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	List<Map> selectRentByParams(Map map);
    
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
     * 描述：查询所有的财务房屋合同记录结果
     * 2013-9-24 上午09:55:20 by caoyong
     * @version
     * @return
     */
    public List<FinanceRentContract> selectAllRents();
    /**
     * 查询修改的数据
     * 描述：
     * 2013-10-1 下午05:38:49 by sunxiaofeng
     * @version
     * @return
     */
	List<Map<String, Object>> selectFinanceOptionUpdate(Map map);
  /**
   * 查询创建的数据
   * 描述：
   * 2013-10-1 下午05:39:41 by admin
   * @version
   * @return
   */
	List<Map<String, Object>> selectFinanceOptionCreate(Map map);

     public FinanceRentContract findFinanceKeID(Integer id);

	List expAllqueryPage(Map map);

	void InsertAll(@Param("list")List<FinanceRentContract> list);

}