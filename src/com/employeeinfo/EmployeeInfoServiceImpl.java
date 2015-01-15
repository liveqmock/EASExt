package com.employeeinfo;

import javax.annotation.Resource;
import javax.jws.WebParam;

import com.creditease.eas.hr.service.IBasicPersonInfoService;
import com.creditease.eas.hr.service.ISeparationProcessesService;
import com.creditease.eas.hr.service.ISpecialWorkerService;
import com.creditease.eas.hr.service.PersonnelInterfaceService;

public class EmployeeInfoServiceImpl implements IEmployeeInfoService {
	@Resource
	private ISpecialWorkerService specwork;
	@Resource
	private PersonnelInterfaceService personface;
	@Resource
	private ISeparationProcessesService separservice;
	//查询人员基本信息
	@Resource
	private IBasicPersonInfoService basicPersonInfoService;

		
	/**
	 * 根据组织ID 类型ID获取人员编码，类型名称
	 */
	@Override
	public String getPersonnelInterfaceByOrgId(String fid, String ftypeid) {
		return personface.getPersonnelInterfaceByOrgId(fid, ftypeid);
	}
	
	/**
	 * 根据组织编码  类型ID获取人员编码，类型名称，组织ID
	 */
	@Override
	public String getPersonnelInterfaceByPerId(String fnumber, String ftypeid) {
		return personface.getPersonnelInterfaceByPerId(fnumber, ftypeid);
	}
	/**
	 * 根据人员编码  获取职位编码
	 */
	@Override
	public String getSpecialWorkerServiceBycode(String fnumber) {
		return specwork.getSpecialWorkerServiceBycode(fnumber);
	}

	/**
	 * 根据编码 判断是否是备用金负责人及员工借款金额
	 */
	@Override
	public String queryFuumber(String fnumber) {
		return separservice.queryFuumber(fnumber);
	}
	/***
	 * 根据固定资产接口人类别，查询接口人类别相关的信息
	* @Title: getPersonnelInterfaceTypeById
	*created at 2014-6-9 下午03:59:22 by ygq  
	* @param forgId
	* @return
	* @return String
	 */
	public String getPersonnelInterfaceTypeById(@WebParam(name="fid")String fid){
		return personface.getPersonnelInterfaceTypeById(fid);
	}
	
	@Override
	public String queryPersonInfoByParams(String json) {
		return basicPersonInfoService.queryPersonInfoByParams(json);
	}
	
	

}
