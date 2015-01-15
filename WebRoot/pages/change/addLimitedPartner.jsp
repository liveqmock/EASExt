<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>法律文件查询</title>
	
</head>
	<body>
<%
	//response.sendRedirect(request.getContextPath()+"/limitedpartner/limitedpartner!listLimitedPartner");
    response.sendRedirect(request.getContextPath()+"/limitedpartner/limitedpartner!listLimitedPartnerPage");
    //response.sendRedirect(request.getContextPath()+"/limitedpartner/limitedpartner!listLimitedPartnerPageJson");

%>
	</body>
</html>