/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */     
package com.creditease.eas.webservices.service;    

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**   
 * This class is used for 爱投米员工验证接口
 * @author GUOXU
 * @version   1.0, 2014-7-25 下午05:42:26   
 */   
@WebService(targetNamespace="http://com/creditease/eas/webservice",serviceName="CEPurseService")
@SOAPBinding(style=Style.DOCUMENT)
public interface CEPurseService {
	/**  
	 * @param params  开始结束时间json字符串
	 * @return  验证完毕信息json字符串
	 * @date 2014-7-25下午05:45:45
	 * @author GUOXU
	 */    
	public String validateEmployeeQuery(@WebParam(name="params")String params);
}
  