package com.creditease.eas.adminipurc.service;

import java.io.File;
import java.util.List;

import com.creditease.eas.adminipurc.bean.AdminContractInfo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExcelVo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExportExcelVo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoVo;
import com.creditease.eas.util.Pagination;
/***
 * 合同信息
 *IPurcPortinfoService
 * @author admin 2014-5-20
 */
public interface IAdminiPurcContractService {
	/***
	 * 导入合同信息
	 * @param excelFile
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public List<AdminContractInfoForExcelVo> importContractExcel(File excelFile, String filePath) throws Exception;
	/***
	 * 查询合同信息
	* @Title: queryPage   
	* @param page
	* @return
	* @throws Exception
	* @return Pagination
	 */
	public Pagination queryPage(Pagination page) throws Exception;
	/***
	 * 查看合同信息
	* @Title: view
	*created at 2014-6-1 上午10:40:05 by ygq  
	* @param id
	* @return
	* @throws Exception
	* @return AdminContractInfo
	 */
	public AdminContractInfo findContractInfoById(Long fid) throws Exception;
	/***
	 * 更新合同信息
	* @Title: update
	*created at 2014-6-1 上午10:39:13 by ygq  
	* @param portInfo
	* @return
	* @throws Exception
	* @return int
	 */
	public int update(AdminContractInfo adminContractInfo) throws Exception ;
	/**
	 * 删除合同信息
	* @Title: delete
	*created at 2014-6-1 上午10:39:05 by ygq  
	* @param id
	* @return
	* @throws Exception
	* @return int
	 */
	public int delete(Long fid) throws Exception ;
	/**   
	 * 导出行政采购合同预警的相关信息
	* @Title: queryDatasetByParams
	*created at 2014-6-3 上午08:30:31 by ygq  
	* @return
	* @throws Exception
	* @return List<AdminContractInfo>  
	*/   
	List<AdminContractInfoForExportExcelVo> queryDatasetByParams() throws Exception;
	
}
