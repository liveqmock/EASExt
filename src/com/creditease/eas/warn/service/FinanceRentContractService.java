/**
 * 
 */
package com.creditease.eas.warn.service;

import java.io.File;
import java.util.List;

import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.FinanceRentContract;
import com.creditease.eas.warn.bean.OrgCostcenter;
import com.creditease.eas.warn.bean.WaringDetailView;

/**
 * @FinanceRentContractService.java 财务房租合同信息service接口
 * created at 2013-9-16 上午11:04:16 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface FinanceRentContractService {
	/**
	 * 
	 * 描述：插入新记录
	 * 2013-9-16 上午11:05:20 by caoyong
	 * @version
	 * @param financeRentContract
	 * @return
	 * @throws Exception
	 */
	public int insert(FinanceRentContract financeRentContract) throws Exception;
	
	/**
	 * 
	 * 描述：查询列表分布数据
	 * 2013-9-16 上午11:05:47 by caoyong
	 * @version
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Pagination queryPage(Pagination page) throws Exception;
	
	/**
	 * 
	 * 描述：根据id删除数据
	 * 2013-9-16 上午11:06:12 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Integer id) throws Exception ;
	
	/**
	 * 
	 * 描述：更新数据Entity
	 * 2013-9-16 上午11:06:37 by caoyong
	 * @version
	 * @param rentContract
	 * @return
	 * @throws Exception
	 */
	public boolean update(FinanceRentContract financeRentContract) throws Exception ;
	
	/**
	 * 
	 * 描述：根据主键id获取记录
	 * 2013-9-16 上午11:07:47 by caoyong
	 * @version
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FinanceRentContract getFinanceRentContractById(Integer id) throws Exception;
	
	/**
	 * 
	 * 描述：导入数据（批量新增数据，已经存在的记录执行更新操作）
	 * 2013-9-16 下午01:47:50 by caoyong
	 * @version
	 * @throws Exception
	 */
	public String importExcel(File excelFile,String filePath) throws Exception;
/**
 * 
 * 描述：查询合同信息
 * 2013-10-7 下午01:13:13 by sunxiaofeng
 * @version
 * @param page
 * @return
 * @throws Exception
 */
	public List<FinanceRentContract> expqueryPage(Pagination page)throws Exception;
	/**
	 * 
	 * 描述：给乔燕霞发送邮件的定时任务（汇总三种类型需要提醒的合同数据，生成excel，以附件形式发送） 2013-9-24 上午10:38:44 by
	 * caoyong
	 * @version
	 */
	public void sendEmailToJiao();
	/**
	 * 描述：给王祺发送邮件的定时任务（汇总创建合同时、合同变更时、合同到期时合同数据
	 * (remark字段包括合同的创建时间、创建人、修改时间以及修改人这些信息)，生成excel，以附件形式发送， 提醒周期为每周一次，每周一上午10:00）
	 * 2013-9-24 上午10:38:44 by caoyong
	 * @version
	 */
	public void sendEmailToWang();

	/**
	 * 
	 * 描述：查询合同编号是否已经存在
	 * 2013-10-7 下午01:47:15 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean findContractNumExist() throws Exception;
	/**
	 * 
	 * 描述：查询办公室地址是否已经存在
	 * 2013-10-7 下午01:47:42 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean findOfficeAddExist() throws Exception;

	public Pagination selectCostcenter(Pagination pagination) throws Exception;
}
