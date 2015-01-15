<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sendMail.jsp' starting page</title>
    
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
		<a href="person/conf!setEmail.action">创建企业邮箱测试</a> <br>
		
		<a href="person/conf!setEmpty.action">关联空联系人测试</a> <br>
		
		<a href="person/conf!trimName.action">去掉用户名的空格、清空错误邮箱测试</a><br>
	<%--	
		这个代码先去掉
	<a href="person/conf!setEmail2.action">创建企业邮箱测试（二）</a> <br>--%>
  </body>
</html>
