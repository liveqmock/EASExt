package com.creditease.eas.admin.dao;

import java.util.List;

import com.creditease.eas.admin.bean.Function;
import com.creditease.eas.util.BaseDAO;

public interface FunctionMapper extends BaseDAO<Function, Integer>{
   
	/**
	 * 查询所有的功能
	 * 描述：
	 * 2013-8-5 下午05:42:09 by Administrator
	 * @version
	 * @return
	 */
	public List<Function> functionList();
	
	/**
	 * 删除功能时，将状态值改为1
	 * 描述：
	 * 2013-8-5 下午05:42:01 by Administrator
	 * @version
	 * @param id
	 */
	public void updateStatus(Integer id);
	
	/**
	 * 修改功能信息，主要是名称、url,  stutas不让修改
	 * 描述：
	 * 2013-8-5 下午06:53:18 by Administrator
	 * @version
	 * @param record
	 */
	public int updateByPrimaryKey(Function record);
	
	/**
	 * 插入数据
	 * 描述：
	 * 2013-8-5 下午07:13:54 by Administrator
	 * @version
	 * @param record
	 */
	public int insert(Function record);
	
	
	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_function
     *
     * @mbggenerated Mon Aug 05 15:52:48 CST 2013
     */
    int deleteByPrimaryKey(Integer id);

   
    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_function
     *
     * @mbggenerated Mon Aug 05 15:52:48 CST 2013
     */
    int insertSelective(Function record);

    /**
     * 根据id查找功能对象
     * 描述：
     * 2013-8-9 下午04:37:42 by Administrator
     * @version
     * @param id
     * @return
     */
    Function selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_function
     *
     * @mbggenerated Mon Aug 05 15:52:48 CST 2013
     */
    int updateByPrimaryKeySelective(Function record);

    
    /**
     * 判断功能名称是否已经存在
     * 描述：
     * 2013-8-13 下午04:16:09 by Administrator
     * @version
     * @param username
     * @return
     */
    int selectedIfFunctionExists(String username);
    
   /**
    * 查询功能列表id、name
    * 描述：
    * 2013-8-19 下午06:46:45 by Administrator
    * @version
    * @return
    */
    List<Function> selefuncidname();
    
}