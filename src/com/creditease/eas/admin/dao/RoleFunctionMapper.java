package com.creditease.eas.admin.dao;

import java.util.List;
import java.util.Map;


import com.creditease.eas.admin.bean.RoleFunction;

public interface RoleFunctionMapper {
   
	/**
     * 根据用户id查询拥有的权限
     * 描述：
     * 2013-8-14 下午05:16:12 by Administrator
     * @version
     * @param id
     * @return
     */
	//public List<RoleFunction> seleFunctionid(Integer id);
	
	/**
	 * map中保存fonctionid和linktype,根据这两个字段删除数据
	 * 
	 */
    int deleteByPrimaryKey(Map map);

    /**
     * 插入数据
     * 描述：
     * 2013-8-15 上午10:32:08 by Administrator
     * @version
     * @param record
     * @return
     */
    int insert(RoleFunction record);
    
    /**
     * 根据角色id和功能类型查询菜单权限、功能权限的id集合
     * 描述：
     * 2013-8-16 下午02:34:52 by Administrator
     * @version
     * @param roleid
     * @param linktype
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Integer> selerolefonctionid(Map map);
    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_function
     *
     * @mbggenerated Wed Aug 14 16:11:18 CST 2013
     */
    int insertSelective(RoleFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_function
     *
     * @mbggenerated Wed Aug 14 16:11:18 CST 2013
     */
    RoleFunction selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_function
     *
     * @mbggenerated Wed Aug 14 16:11:18 CST 2013
     */
    int updateByPrimaryKeySelective(RoleFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_function
     *
     * @mbggenerated Wed Aug 14 16:11:18 CST 2013
     */
    int updateByPrimaryKey(RoleFunction record);
   
    /**
     * 删除数据时 stutas字段为1
     * 描述：
     * 2013-8-19 下午07:21:23 by Administrator
     * @version
     * @param id
     */
    public void updateStatus(Integer id);
    
}