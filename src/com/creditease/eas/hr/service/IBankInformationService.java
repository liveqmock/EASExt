package com.creditease.eas.hr.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="bankInformationService")
@SOAPBinding(style = Style.DOCUMENT)

public interface IBankInformationService {
	 public String getBankInfo(@WebParam(name="pageSize")int pageSize,@WebParam(name="pageNo")int pageNo);
	 public String getBankInfoById(@WebParam(name="fid")String fid,@WebParam(name="pageSize")int pageSize,@WebParam(name="pageNo")int pageNo);

}
