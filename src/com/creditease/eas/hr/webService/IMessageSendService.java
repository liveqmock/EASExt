package com.creditease.eas.hr.webService;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 信息推送的接口
 * @IMessageSendService.java
 * created at 2013-1-21 下午01:43:11 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@WebService(targetNamespace="messageSend")
public interface IMessageSendService {
	//:如果传code,则返回对应的，如果传null，则传所有的
	/**
	 * 描述：城市信息
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryCityByCode(
			@WebParam(name = "code")
			String code
	);
	/**
	 * 
	 * 描述：职位等级
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryPositionGradeByCode(
			@WebParam(name = "code")
			String code);
	/**
	 * 
	 * 描述：职位类别
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryPositionTypeByCode(
			@WebParam(name = "code")
			String code);
	/**
	 * 
	 * 描述：员工状态
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryEmployeeStatusByCode(
			@WebParam(name = "code")
			String code);

}
