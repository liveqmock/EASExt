package com.creditease.eas.hr.service;

import javax.jws.WebParam;

public interface PersonnelInterfaceService {
	/*
	 * 根据组织ID 类型ID获取人员编码，类型名称
	 */
	public String getPersonnelInterfaceByOrgId(@WebParam(name="fid")String fid,@WebParam(name="ftypeid")String ftypeid);
	/*
	 * 根据组织编码  类型ID获取人员编码，类型名称，组织ID
	 */
	public String getPersonnelInterfaceByPerId(@WebParam(name="fnumber")String fnumber,@WebParam(name="ftypeid")String ftypeid); 
	/***
	 * 根据固定资产接口人类别，查询接口人类别相关的信息
	* @Title: getPersonnelInterfaceTypeById
	*created at 2014-6-9 下午03:59:22 by ygq  
	* @param forgId
	* @return
	* @return String
	 */
	public String getPersonnelInterfaceTypeById(@WebParam(name="fid")String fid);
}
