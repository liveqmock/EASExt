package com.creditease.eas.webservices.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="emailWhetherJobService")
@SOAPBinding(style = Style.DOCUMENT)
/**
 * 
 * <p>Title: IEmailWhetherJobService</p>
 * <p>Description: 根据宜信邮箱判断此邮箱账户对应人员是否正式在职员工，反馈结果TRUE OR FALSE</p>
 * <p>date : 2014-7-21 下午02:11:35 </p>
 * @author gaoliya_thinking
 * @version 1.0
 */
public interface IEmailWhetherJobService {
	
	public boolean emailWorkingConditions(@WebParam(name="cfmail")String cfmail);
	

}
