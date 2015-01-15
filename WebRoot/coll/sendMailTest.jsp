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
<!--		  	<div align="center">-->
<!--		   		<a href="regular/regular!sendOneMailTest.action">转正预警测试(one)</a>-->
<!--		  	</div>-->
	  	<div align="center">
	   		<a href="regular/regular!sendRegularMailTest.action">转正预警测试</a>
	  	</div>
	  	<div align="center">
	   		<a href="regular/contract!sendContractMailTest.action">合同到期预警测试</a>
	  	</div>
	  	<div align="center">
	   		<a href="person/warn!cellSend.action">生日预警短信测试</a>
	  	</div>
	 	<div align="center">
	   		<a href="regular/birthday!sendBirthdayEmailTest.action">生日预警邮件测试</a>
	  	</div>
	  	<div align="center">
	  		<a href="yearofwork/yearofwork!sendYearOfWorkMailTest.action">司龄预警邮件测试</a>
	  	</div>
<!--  	<%=session.getAttribute("name") %>-->
  </body>
</html>
