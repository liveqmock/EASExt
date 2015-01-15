package com.employeeinfo;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="personnelInterfaceService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IEmployeeInfoService {

	/**
	 * 离职流程接口根据员工ID，类型ID返回员工编码，类型名称，部门ID,部门名称
	 */
	public String getPersonnelInterfaceByOrgId(@WebParam(name="fid")String fid,@WebParam(name="ftypeid")String ftypeid);
	/**
	 * 离职流程接口根据员工编码，类型ID返回员工编码，类型名称，部门ID,部门名称
	 */
	public String getPersonnelInterfaceByPerId(@WebParam(name="fnumber")String fnumber,@WebParam(name="ftypeid")String ftypeid);
	/**
	 * 特殊人员接口根据员工编码返回职位编码
	 */
	public String getSpecialWorkerServiceBycode(@WebParam(name="fnumber")String fnumber);
	
	/**
	 * 描述：根据员工编码获取（是否是备用金负责人及获取员工是否借款）
	 */
	public String queryFuumber(@WebParam(name="fnumber")String fnumber);
	/***
	 * 根据固定资产接口人类别，查询接口人类别相关的信息
	* @Title: getPersonnelInterfaceTypeById
	*created at 2014-6-9 下午03:59:22 by ygq  
	* @param forgId
	* @return
	* @return String
	 */
	public String getPersonnelInterfaceTypeById(@WebParam(name="fid")String fid);
	/**
	 * 查询人员基本信息
	* @Title: queryPersonInfoByParams
	*created at 2014-6-15 下午02:49:52 by ygq  
	* @param json
	* @return
	* @return String
	 */
	public String queryPersonInfoByParams(@WebParam(name="json")String json);
}
