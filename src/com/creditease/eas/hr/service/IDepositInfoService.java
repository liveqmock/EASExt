package com.creditease.eas.hr.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="depositInfoService")
@SOAPBinding(style = Style.DOCUMENT)

public interface IDepositInfoService {
	/**
	 * 读取hr系统中带有备用金标志的人员
	 */
	public String getPersoninfo(@WebParam(name="pagesize")int pagesize,@WebParam(name="pageNo")int pageNo);
	
	/**
	 * 根据员工姓名返回员工信息
	 */
	
	public String getNamePersoninfo(@WebParam(name="pname")String pname);
}
