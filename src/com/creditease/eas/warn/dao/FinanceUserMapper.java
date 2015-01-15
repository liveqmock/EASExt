package com.creditease.eas.warn.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.FinanceUser;
/**
 * @FinanceUserMapper.java	财务房租合同信息用户Dao
 * created at 2013-9-17 下午01:38:58 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FinanceUserMapper extends BaseDAO<FinanceUser, Integer>{
	/**
	 * 描述：根据主键id删除记录
	 * 2013-9-17 下午01:39:33 by caoyong
	 * @version
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
    /**
     * 描述：插入数据Entity
     * 2013-9-17 下午01:39:52 by caoyong
     * @version
     * @param financeRentContract
     * @return
     */
    int insert(FinanceUser financeUser);
    /**
     * 描述：根据主键id获取Entity
     * 2013-9-17 下午01:40:17 by caoyong
     * @version
     * @param id
     * @return
     */
    FinanceUser selectByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：根据用户id获取Entity
     * 2013-9-26 下午03:25:23 by zhangxin
     * @version
     * @param id
     * @return
     */
    FinanceUser selectByUserid(Integer id);
    /**
     * 
     * 描述：根据组id获取Entity集合
     * 2013-9-26 下午04:02:06 by zhangxin
     * @version
     * @param id
     * @return
     */
    List<FinanceUser> selectByGroupid(Integer id);
  
    /**
     * 
     * 描述：查询所有用户Entitiy集合
     * 2013-10-8 下午01:52:48 by zhangxin
     * @version
     * @return
     */
    List<FinanceUser> selectAllUser();
    
    /**
     * 描述：更新数据Entity
     * 2013-9-17 下午01:40:36 by caoyong
     * @version
     * @param financeRentContract
     * @return
     */
    int updateByPrimaryKey(FinanceUser financeUser);
    /**
     * 描述：查询列表而记录条数
     * 2013-9-17 下午01:41:41 by caoyong
     * @version
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public int getTotalCounts(Map map);
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
     * 描述：查询所有未添加到财务表中的系统用户id、name键值对
     * 2013-9-22 下午12:40:54 by caoyong
     * @version
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public List<Map> selectAllusers();
    /**
     * 
     * 描述：根据id查询组用户
     * 2013-9-28 下午01:35:10 by sunxiaofeng
     * @version
     * @param id
     * @return
     */
	FinanceUser getFinanceUser(Integer id);
	//根据组id查询组长的邮箱
	List<FinanceUser> getFinanGroupLeader(Integer financeGroupId);
	 /**
     * 
     * 描述：根据签署公司查询组用户
     * 2013-9-29 中午12:35:10 by sunxiaofeng
     * @version
     * @param id
     * @return
     */
	List<FinanceUser> findFinUser();
}