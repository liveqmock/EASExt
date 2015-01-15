package com.creditease.eas.hr.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="fixedAssetsService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IFixedAssetsService {
	public String getFixedAssetsByOrgId(@WebParam(name="fid")String fid);
	public String getFixedAssetsByCode(@WebParam(name="fnumber")String fnumber); 
	
}
