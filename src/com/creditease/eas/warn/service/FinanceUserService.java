/**
 * 
 */
package com.creditease.eas.warn.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.FinanceUser;
/**
 * @FinanceUserService.java	财务房租合同信息用户service接口
 * created at 2013-9-17 下午02:11:01 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FinanceUserService {
	/**
	 * 描述：插入新记录
	 * 2013-9-17 下午02:11:14 by caoyong
	 * @version
	 * @param financeRentContract
	 * @return
	 * @throws Exception
	 */
	public boolean insert(FinanceUser financeUser,String username) throws Exception;
	/**
	 * 描述：查询列表分布数据
	 * 2013-9-17 下午02:11:51 by caoyong
	 * @version
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Pagination queryPage(Pagination page) throws Exception;
	/**
	 * 描述：根据id删除数据
	 * 2013-9-17 下午02:12:08 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Integer id,String username) throws Exception ;
	/**
	 * 描述：更新数据Entity
	 * 2013-9-17 下午02:12:17 by caoyong
	 * @version
	 * @param financeRentContract
	 * @return
	 * @throws Exception
	 */
	public int update(FinanceUser financeUser) throws Exception ;
	/**
	 * 描述：根据主键id获取记录
	 * 2013-9-17 下午02:12:40 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FinanceUser getFinanceUserById(Integer id) throws Exception;
	 /**
     * 
     * 描述：查询所有财务房屋合同组表id和组名的name的键值对
     * 2013-9-18 下午05:27:12 by caoyong
     * @version
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map> selectFinanceGroups();
    /**
     * 
     * 描述：查询所有的签署合同公司signatory和签署合同公司signatory的键值对
     * 2013-9-18 下午04:41:21 by caoyong
     * @version
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map> selectAllSignatorys();
    /**
     * 
     * 描述：查询所有的未添加到财务用户表中的系统用户id和用户名userName的键值对
     * 2013-9-22 下午12:38:25 by caoyong
     * @version
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Map> selectAllusers();
    /**
     * 
     * 描述：根据id查询组用户
     * 2013-9-30 下午01:32:41 by sunxiaofeng
     * @version
     * @param id
     */
	public FinanceUser getFinanceUser(Integer id);
    
    /**
     * 
     * 描述：根据用户id查找用户信息
     * 2013-9-26 下午03:28:18 by zhangxin
     * @version
     * @param userid
     * @return
     */
    public FinanceUser selectByUserid(Integer userid);
    
    /**
     * 
     * 描述：根据分组id查找Entity集合
     * 2013-9-26 下午04:06:29 by zhangxin
     * @version
     * @param groupid
     * @return
     */
    public List<FinanceUser> selectByGroupid(Integer groupid);
    //查询组长的邮箱
	public  List<FinanceUser> getFinanGroupLeader(Integer financeGroupId);
    
}
