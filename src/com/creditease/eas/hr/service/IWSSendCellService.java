package com.creditease.eas.hr.service;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/***
 * 发送短信的接口，供其他系统调用
 * @Title:IWSSendCellService.java
 * @Package com.creditease.eas.hr.service
 * created at 2014-7-9 下午05:56:29 by ygq
 * @author ygq
 * @version 1.0
 */
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="ISendCellSerivice")
@SOAPBinding(style = Style.RPC)
public interface IWSSendCellService {
	/**
	 * 2013-12-6:发送短信给员工(EAS使用)
	 * @param fname 姓名
	 * @param fnumber 员工编码
	 * @param fmail 邮箱
	 * @param fkey 密码
	 * @param fdescription 描述
	 * @param fmobilePhone 手机号码
	 */
	public String sendCell(@WebParam(name="fname")String fname,@WebParam(name="fnumber")String fnumber,@WebParam(name="fmail")String fmail,@WebParam(name="fkey")String fkey,@WebParam(name="fdescription")String fdescription,@WebParam(name="fmobilePhone")String fmobilePhone);
	/***
	 * 单条发送短信(内网使用)
	* @Title: oneMsgSend
	*created at 2014-7-9 下午05:41:36 by ygq  
	* @param phone
	* @param keywords
	* @param batchid
	* @param org_no
	* @param mod_type
	* @return
	* @return String
	 */
	public  String oneMsgSend(String phone,String keyword1,String keyword2,String batchid,String org_no,String mod_type);
	/***
	 * 多手机号发送相同短信内容批量发送(内网使用)
	* @Title: sameMessageManyPhoneSend
	*created at 2014-7-9 下午05:42:16 by ygq  
	* @param phone
	* @param keywords
	* @param batchid
	* @param org_no
	* @param mod_type
	* @return
	* @return String
	 */
	public  String sameMessageManyPhoneSend(String phone,String keyword1,String keyword2,String batchid,String org_no,String mod_type);
	
}
