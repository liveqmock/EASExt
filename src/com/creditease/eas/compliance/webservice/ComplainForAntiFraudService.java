package com.creditease.eas.compliance.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="complainForAntiFraudService")
@SOAPBinding(style = Style.DOCUMENT)
public interface ComplainForAntiFraudService {
	String hello(@WebParam(name="name")String name);
	
	/**
	 * 信审系统调用投诉系统接口，将"投诉类案件"信息传递到投诉系统进行处理
	 * @param complainJson 
	 * @return 返回结果
	 */
	String saveComplainToCompliance(@WebParam(name="complainJson") String complainJson);
	
	/**
	 * 信审系统反馈调查结果给投诉系统
	 * @param investigationJson 
	 * @return 返回结果
	 */
	String saveInvestigationToCompliance(@WebParam(name="investigationJson")String investigationJson);
}
