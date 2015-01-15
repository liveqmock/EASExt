package com.creditease.eas.accountr.dao;

import java.util.List;

import com.creditease.eas.accountr.bean.AccountrRemarks;
import com.creditease.eas.accountr.bean.AccountrUser;
/**
 * 报销提醒汇总邮件收件人
 * @IAccountrUserMapper.java
 * created at 2014-1-6 下午04:01:44 by sunxiaofeng
 * 
 * @author sunxiaofeng({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IAccountrUserMapper {
     /**
      * 
      * 描述：查询所有汇总收件人
      * 2014-1-6 下午04:02:26 by sunxiaofeng
      * @version
      * @return对象的集合
      */
	public List<AccountrUser> findAccountrUser();
    /**
     * 	
     * 描述：添加汇总邮件的收件人
     * 2014-1-7 下午01:19:55 by sunxiaofeng
     * @version
     * @param accountrUser 对象
     */
	public void addAccountrUser(AccountrUser accountrUser);
	/**
	 * 
	 * 描述：
	 * 2014-1-7 下午02:58:17 by sunxiaofeng
	 * @version
	 * @param fid所有选中收件人的集合
	 */
	public void deleteAccountrUser(String[] idItem);
	/**
	 * 
	 * 描述：查询公共备注
	 * 2014-1-7 下午04:10:44 by sunxiaofeng
	 * @version
	 * @return 公共备注的集合
	 */
	public List<AccountrRemarks> findAccountrRemarks();
	/**
	 * 
	 * 描述：保存选中汇总邮件的收件人
	 * 2014-1-7 下午05:02:34 by sunxiaofeng
	 * @version
	 * @param fid选中汇总邮件收件的集合
	 * @return  表示是否成功
	 */
	public int updateAccountrUser(String[] fid);
	/**
	 * 
	 * 描述：保存公共备注
	 * 2014-1-7 下午05:09:04 by sunxiaofeng
	 * @version
	 * @param accountrRemarks公共备注对象
	 * @return 表示是否成功
	 */
	public int updateAccountrRemarks(AccountrRemarks accountrRemarks);
	/**
	 * 
	 * 描述：修改没有选中的收件人
	 * 2014-1-7 下午05:42:52 by sunxiaofeng
	 * @version
	 * @param fidfid选中汇总邮件收件的集合
	 * @return 表示是否成功
	 */
	public int updateAccountrUserNot(String[] fid);
	/**
	 * 
	 * 描述：查询设置好的收件人
	 * 2014-1-15 下午06:40:42 by sunxiaofeng
	 * @version
	 * @return
	 */
	public List<AccountrUser> getSelectUser();
	/**
	 * 
	 * 描述：查询当前登录人的角色
	 * 2014-1-16 下午03:37:20 by sunxiaofeng
	 * @version
	 * @param parseInt
	 * @return
	 */
	public List<String> getRole(int userid);

}
