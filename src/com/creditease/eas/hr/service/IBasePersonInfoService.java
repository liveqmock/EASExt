package com.creditease.eas.hr.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/***
 * 内网基本信息接口
 * @Title:IBasePersonInfoService.java
 * @Package com.creditease.eas.hr.service
 * @version 1.0
 */
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="IBasePersonInfoService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IBasePersonInfoService {
	
	public String getPersonInfoByNumber(@WebParam(name="fnumber")String fnumber);

}
