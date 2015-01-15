package com.creditease.eas.test.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;
@WebService(endpointInterface = "com.cxf.ITestService", serviceName = "testService",targetNamespace="my")
public interface ITestService {
public String helloWord(
		@WebParam(name = "testStr")
		String testStr
	);
}
