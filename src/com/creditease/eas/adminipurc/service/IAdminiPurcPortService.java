package com.creditease.eas.adminipurc.service;

import java.io.File;
import java.util.List;

import com.creditease.eas.adminipurc.vo.CommonPortinfoForExcelVo;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.commbean.CommonPortinfo;
/***
 * 接口人信息
 *IPurcPortinfoService
 * @author admin 2014-5-20
 */
public interface IAdminiPurcPortService {
	public List<CommonPortinfoForExcelVo> importPortExcel(File excelFile, String filePath) throws Exception;
	/***
	 * 查询接口人信息
	* @Title: queryPage   
	* @param page
	* @return
	* @throws Exception
	* @return Pagination
	 */
	public Pagination queryPage(Pagination page) throws Exception;
	/***
	 * 查看接口人信息
	* @Title: view
	*created at 2014-6-1 上午10:40:05 by ygq  
	* @param id
	* @return
	* @throws Exception
	* @return CommonPortinfo
	 */
	public CommonPortinfo findPortinfoById(Long fid) throws Exception ;
	/***
	 * 更新接口人信息
	* @Title: update
	*created at 2014-6-1 上午10:39:13 by ygq  
	* @param portInfo
	* @return
	* @throws Exception
	* @return int
	 */
	public int update(CommonPortinfo portInfo,String fportEmailValid) throws Exception ;
	/**
	 * 删除接口人信息
	* @Title: delete
	*created at 2014-6-1 上午10:39:05 by ygq  
	* @param id
	* @return
	* @throws Exception
	* @return int
	 */
	public int delete(Long fid) throws Exception ;
}
