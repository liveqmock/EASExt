package com.creditease.eas.warn.dao;


import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.FinanceGroupBean;

public interface FinanceGroupBeanMapper  extends BaseDAO<Function, Integer>{
	
	/**
	 * 根据id删除组信息
	 * 描述：
	 * 2013-9-17 下午03:51:18 by zhangxin
	 * @version
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 
     * 描述：添加组信息
     * 2013-9-17 下午03:05:47 by zhangxin
     * @version
     * @param record
     * @return
     */
    int insert(FinanceGroupBean record);
    
    /**
     * 
     * 描述：判断组名是否存在
     * 2013-9-18 下午01:37:20 by zhangxin
     * @version
     * @param username
     * @return
     */
    int selectedIfGroupExists(String username);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_finance_group
     *
     * @mbggenerated Mon Sep 16 14:45:42 CST 2013
     */
    int insertSelective(FinanceGroupBean record);

    /**
     * 
     * 描述：根据id查询组信息
     * 2013-9-17 下午02:38:04 by zhangxin
     * @version
     * @param id
     * @return
     */
    FinanceGroupBean selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_finance_group
     *
     * @mbggenerated Mon Sep 16 14:45:42 CST 2013
     */
    int updateByPrimaryKeySelective(FinanceGroupBean record);

    /**
     * 
     * 描述:修改组信息
     * 2013-9-17 下午03:54:08 by zhangxin
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(FinanceGroupBean record);
}