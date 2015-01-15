package com.creditease.eas.util.consts;
public class AddressConfig {
	//ESB 生产机
public static final String EMPWSDLLOCATION = "http://esb.creditease.cn:18099/empChangeSuceed/services/empchangeSuceedService?wsdl";
public static final String POSITIONWSDLLOCATION = "http://esb.creditease.cn:18099/PositionInfoChange/services/PositionInfoChangeService?wsdl";
public static final String ORGWSDLLOCATION = "http://esb.creditease.cn:18099/OrganizationChange/services/OrganizationChangeService?wsdl";

//	public static final String EMPWSDLLOCATION = "http://10.100.31.12:18099/empChangeSuceed/services/empchangeSuceedService?wsdl";
//	public static final String POSITIONWSDLLOCATION = "http://10.100.31.12:18099/PositionInfoChange/services/PositionInfoChangeService?wsdl";
//	public static final String ORGWSDLLOCATION = "http://10.100.31.12:18099/OrganizationChange/services/OrganizationChangeService?wsdl";
	
	public static  int ORGCST = 1;
	public static  int POSITIONCST = 1;
	public static  int EMPCST = 1;
}
