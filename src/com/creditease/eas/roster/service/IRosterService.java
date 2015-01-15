package com.creditease.eas.roster.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.creditease.eas.roster.bean.Roster;
import com.creditease.eas.util.Pagination;

/**
 * 投三花名册service层接口方法
 * @IRosterService.java
 * created at 2014-3-17 下午05:23:31 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IRosterService {
	/**
	 * 
	 * 描述：查询方法
	 * 2014-3-10 下午03:38:50 by zhangxin
	 * @version
	 * @param page
	 * @return 带分页功能的json数据
	 */
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：导出数据
	 * 2014-7-14 下午04:00:13 by zhangxin
	 * @version
	 * @return
	 */
	public List<Map> expQuery();
	
	/**
	 * 
	 * 描述：查询批量修改数据
	 * 2014-6-26 上午11:32:01 by zhangxin
	 * @version
	 * @param page 
	 * @param fids 批量修改id集合
	 * @return 带分页功能的json数据
	 */
	public Pagination queryManyEditInfoPage(Pagination page);
	
	/**
	 * 
	 * 描述：根据typeid查询数据字典信息
	 * 2014-7-7 下午01:23:35 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	public Pagination queryDictionaryData(Pagination page);
	/**
	 * 
	 * 描述：查询某条数据的具体信息
	 * 2014-3-18 下午02:21:55 by zhangxin
	 * @version
	 * @param id
	 * @return
	 */
	public Roster selectByPrimaryKey(Integer id);
	/**
	 * 
	 * 描述：删除某条花名册信息
	 * 2014-3-18 下午02:42:55 by zhangxin
	 * @version
	 * @param id
	 */
	public void deleteRoster(Integer id);
	/**
	 * 
	 * 描述：插入花名册信息
	 * 2014-3-19 下午03:58:09 by zhangxin
	 * @version
	 * @param roster
	 * @return
	 */
	public int insertRoster(Roster roster);
	/**
	 * 
	 * 描述：更新花名册信息
	 * 2014-3-19 下午04:11:30 by zhangxin
	 * @version
	 * @param roster
	 * @return
	 */
	public int updateRoster(Roster roster);
	
	/**
	 * 
	 * 描述：批量更新花名册信息
	 * 2014-6-29 下午03:29:47 by zhangxin
	 * @version
	 * @return
	 */
	public int updateManyEditInfo();
	/**
	 * 
	 * 描述：导出excel
	 * 2014-3-20 下午04:19:46 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void expExcel() throws Exception;
	
	/**
	 * 
	 * 描述：导出简单版excel
	 * 2014-3-20 下午04:19:46 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void expExcelSimple() throws Exception;
	
	/**
	 * 
	 * 描述：导入人员信息
	 * 2014-3-31 下午04:48:23 by zhangxin
	 * @version
	 * @param excelFile
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String importExcel(File excelFile, String filePath)  throws Exception;
	/**
	 * 
	 * 描述：验证导入人员信息是否符合导入数据权限
	 * 2014-3-31 下午04:48:46 by zhangxin
	 * @version
	 * @param excelFile
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String importExcelValidation(File excelFile, String filePath) throws Exception;
	
	/**
	 * 
	 * 描述：查看导入数据时错误的数据行数
	 * 2014-4-11 下午02:10:37 by zhangxin
	 * @version
	 */
	public void viewErrorData();
	/**
	 * 
	 * 描述：查询12位员工编号是否存在
	 * 2014-4-24 上午10:38:00 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean ifNumHasExists() throws Exception;
	/**
	 * 
	 * 描述：查询邮箱是否存在
	 * 2014-4-24 上午10:38:00 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean ifEmailHasExists() throws Exception;
	
	
	
}
