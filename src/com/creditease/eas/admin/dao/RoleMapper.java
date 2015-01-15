package com.creditease.eas.admin.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.Role;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.BaseDAO;

public interface RoleMapper extends BaseDAO<Role, Integer>{
    
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加角色
     * 描述：
     * 2013-8-15 上午09:37:56 by Administrator
     * @version
     * @param record
     * @return
     */
    int insertRole(Role record);
    /**
     * 判断角色名称是否已经存在
     * 描述：
     * 2013-8-15 下午01:43:51 by Administrator
     * @version
     * @param rolename
     * @return
     */
    int selectedIfRoleExists(String rolename);
    /**
     * 根绝角色名称查询角色id
    * @Title: selectRoleIdByName
    *created at 2014-5-29 下午02:45:26 by ygq  
    * @param rolename
    * @return
    * @return int
     */
    int selectRoleIdByName(String rolename);
    
    
    /**
     * 删除数据时 stutas字段为1
     * 描述：
     * 2013-8-19 下午07:21:23 by Administrator
     * @version
     * @param id
     */
    public void updateStatus(Integer id);
    
    
    int insertSelective(Role record);

    /**
     * 根据角色id查找角色对象
     * 描述：
     * 2013-9-4 上午10:21:22 by Administrator
     * @version
     * @param roleid
     * @return
     */
    Role selectByPrimaryKey(Integer roleid);

    
    int updateByPrimaryKeySelective(Role record);

    /**
     * 修改角色信息
     * 描述：
     * 2013-9-4 下午01:42:18 by Administrator
     * @version
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
    /**
     * 查询角色的id和name
     * 描述：
     * 2013-8-23 上午09:38:57 by Administrator
     * @version
     * @return
     */
    List<Role> seleroleidname();
	/***
	 * 根据登陆人的id，查询人员对应的角色信息
	* @Title: seleroleidnameByUserName
	*created at 2014-6-8 下午06:16:16 by ygq  
	* @param username
	* @return
	* @return List<Role>
	 */
    List<Role> seleroleidnameByUserid(Long id);
    
    /***
	 * 根据登陆人的id，查询人员对应的角色信息
	* @Title: seleroleidnameByUserName
	*created at 2014-6-8 下午06:16:16 by ygq  
	* @param username
	* @return
	* @return List<Role>
	 */
    List<Role> seleroleidnameByUserids(List usersid);
    /***
     * 根据角色名称查询角色信息
    * @Title: selectRoleByName
    *created at 2014-6-22 下午02:51:59 by ygq  
    * @param rolename
    * @return
    * @return Role
     */
    Role selectRoleByName(String rolename);
}