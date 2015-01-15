package com.creditease.eas.accountr.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.creditease.eas.accountr.bean.Accountr;
import com.creditease.eas.accountr.bean.AccountrRemarks;
import com.creditease.eas.accountr.bean.AccountrUser;
import com.creditease.eas.util.Pagination;

/**
 * 
 * @IAccountrService.java
 * created at 2014-1-6 下午03:49:55 by sunxiaofeng
 * 财务报销提醒接口
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IAccountrService {
/** 
 * 
 * 描述：查询所有汇总邮件的收件人
 * 2014-1-6 下午03:50:44 by sunxiaofeng
 * @version
 * @return json字符串
 */
public String findAccountrUser();
/**
 * 
 * 描述：添加汇总收件人
 * 2014-1-7 下午01:16:14 by sunxiaofeng
 * @version
 * @param accountrUser（收件人对象）
 */
public void addAccountrUser(AccountrUser accountrUser);
/**
 * 
 * 描述：删除汇总邮件的收件人
 * 2014-1-7 下午02:55:14 by sunxiaofeng
 * @version
 * @param fid(所有选中收件人id的字符串)
 */
public void deleteAccountrUser(String fid);
/**
 * 
 * 描述：查询公共备注
 * 2014-1-7 下午04:05:58 by sunxiaofeng
 * @version
 * @return 公共备注对象
 */
public AccountrRemarks findAccountrRemarks();
/**
 * 
 * 描述：保存选中汇总邮件收件人及公共备注
 * 2014-1-7 下午04:57:56 by sunxiaofeng
 * @version
 * @param fid 选中汇总邮件收件人的id集合
 * @param accountrRemarks 公共备注对象
 */
public void updateAccountrUserRemarks(String[] fid,
		AccountrRemarks accountrRemarks);
/**
 * 
 * 描述：查询报销列表信息
 * 2014-1-8 上午10:59:59 by sunxiaofeng
 * @version
 * @param pagination分页对象
 * @param request
 * @return 分页对象
 */
public Pagination queryPage(Pagination pagination, HttpServletRequest request);
/**
 * 
 * 描述：根据id查询报销信息
 * 2014-1-8 下午03:38:45 by sunxiaofeng
 * @version
 * @param fid 报销信息id
 * @return报销信息对象
 */
public Accountr findAccountr(Integer fid);
/**
 * 
 * 描述：新增报销信息
 * 2014-1-8 下午03:53:04 by sunxiaofeng
 * @version
 * @param accountr报销信息对象
 */
public void insert(Accountr accountr);
/**
 * 
 * 描述：修改报销信息
 * 2014-1-8 下午04:08:10 by sunxiaofeng
 * @version
 * @param accountr报销信息对象
 */
public void update(Accountr accountr);
/**
 * 
 * 描述：删除报销信息
 * 2014-1-8 下午05:31:14 by sunxiaofeng
 * @version
 * @param fid 报销信息id
 */
public void deleteAccountr(Integer fid);
/**
 * 
 * 描述：导入报销信息
 * 2014-1-9 上午10:25:02 by sunxiaofeng
 * @version
 * @param excelFile
 * @param filePath
 * @return json字符串
 */
public String importExcel(File excelFile, String filePath)  throws Exception;
/**
 * 
 * 描述：验证excel数据是否符合导入权限
 * 2014-1-13 上午10:22:46 by sunxiaofeng
 * @version
 * @param excelFile
 * @param filePath
 * @return
 */
public String importExcelValidation(File excelFile, String filePath) throws Exception;
/**
 * 
 * 描述：补发邮件
 * 2014-1-15 下午01:56:39 by sunxiaofeng
 * @version
 * @param fids
 * @param status 
 */
public void reissueEmail(String fids) throws Exception;
/**
 * 
 * 描述：批量发送邮件及汇总邮件
 * 2014-1-20 下午01:46:27 by sunxiaofeng
 * @version
 * @param fids
 * @param request 
 */
public void batchEmail(String fids, HttpServletRequest request) throws Exception;

}
