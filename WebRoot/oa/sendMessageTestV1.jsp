<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sendMessageTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    <a href="hr/hr!organizationChangeSend.action?orgid=1">测试组织推送正常</a>
      <a href="hr/hr!organizationChangeSend.action?orgid=0">测试组织推送异常</a>
    <a href="hr/hr!positionChangeSend.action?position=1">测试职位推送正常</a>
     <a href="hr/hr!positionChangeSend.action?position=0">测试职位推送异常</a>
    <a href="hr/hr!empChangeSend.action?emp=1">测试人员推送正常</a>
  	<a href="hr/hr!empChangeSend.action?emp=0">测试人员推送异常</a>
  </body>
</html>
