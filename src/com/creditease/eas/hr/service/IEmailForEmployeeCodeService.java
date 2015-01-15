package com.creditease.eas.hr.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="depositInfoService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IEmailForEmployeeCodeService {
	/**
	 * 根据邮箱前缀获得员工的12位员工编码
	 */
	public String queryFnumberByEmail(@WebParam(name="femail")String femail);
}
