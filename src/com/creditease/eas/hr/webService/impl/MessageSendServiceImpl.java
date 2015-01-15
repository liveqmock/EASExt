package com.creditease.eas.hr.webService.impl;

import javax.jws.WebService;

import com.creditease.eas.hr.kingdee.query.MessageSendQuery;
import com.creditease.eas.hr.webService.IMessageSendService;
@WebService(endpointInterface = "com.creditease.eas.hr.webService.IMessageSendService",targetNamespace="messageSend",portName="messageImpl")
public class MessageSendServiceImpl implements IMessageSendService{
	//:如果传code,则返回对应的，如果传null，则传所有的
	/**
	 * 
	 * 描述：城市信息
	 * 需要增加日志信息
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryCityByCode(String code){
		//return "hello:::" + code;
		try{
			String msg = MessageSendQuery.selectCityByCode(code,"001001");
			return msg;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 描述：职位等级
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryPositionGradeByCode(String code){
		try{
			String msg = MessageSendQuery.selectPositionGradeByCode(code,"001002");
			return msg;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 描述：职位类别
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryPositionTypeByCode(String code){
		try{
			String msg = MessageSendQuery.selectPositionTypeByCode(code,"001003");
			return msg;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 描述：员工状态
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public String queryEmployeeStatusByCode(String code){
		try{
			String msg = MessageSendQuery.selectEmployeeStatusByCode(code,"001004");
			return msg;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		MessageSendServiceImpl m = new MessageSendServiceImpl();
//		String str = m.queryEmployeeStatusByCode(null);
		String str = m.queryCityByCode(null);
		System.out.println(str);
	}
}
