package com.creditease.eas.webservices.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService(targetNamespace="http://com/creditease/eas/webservices",serviceName="itoumiService")
@SOAPBinding(style = Style.DOCUMENT)
public interface ItoumiService {
	/**
	 * 获取新入职员工信息
	 * @author guoxu
	 * @since 2014-7-7
	 * @param dataTime Json格式字符串 例如{'beginTime':'2014-02-01 00:00:00', 'endTime':'2014-02-28 00:00:00'}
	 * @return 员工编号、姓名、工作所在城市、部门、员工状态
	 */
	public String EnrollEmployeeQuery(@WebParam(name = "dataTime")String dataTime);
	/**
	 * 获取异动人员信息
	 * @author guoxu
	 * @since 2014-7-7
	 * @param dataTiem Json格式字符串 例如{'beginTime':'2014-02-01 00:00:00', 'endTime':'2014-02-28 00:00:00'}
	 * @return 员工编号、姓名、工作所在城市、部门、员工状态
	 */
	public String FluctuationEmployeeQuery(@WebParam(name = "dataTime")String dataTime);
	/**
	 * 获取离职人员列表
	 * @author guoxu
	 * @since 2014-7-7
	 * @param dataTime Json格式字符串 例如{'beginTime':'2014-02-01 00:00:00', 'endTime':'2014-02-28 00:00:00'}
	 * @return 员工编号
	 */
	public String ResignEmployeeQuery(@WebParam(name = "dataTime")String dataTime);
	/**
	 *根据姓名、身份证号、员工编号验证是否为宜信员工
	 * @author lining
	 * @since 2014-7-7
	 * @param dataTime Json格式字符串 例如{'beginTime':'2014-02-01 00:00:00', 'endTime':'2014-02-28 00:00:00'}
	 * @return 员工编号、姓名
	 */
	public String ValidateEmployeeQuery(@WebParam(name = "dataTime")String empInfo);
	
}
