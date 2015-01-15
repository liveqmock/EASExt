<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>短信邮件明细</title>  
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   	<style type="text/css"> table tr td{font-size: 12px;} </style>
</head>  
<body>  
	<table width="100%" border="0" id="viewTab">
		<tr><td><b>主题:</b>${waringDetail.theme}</td></tr>
 		<tr><td><b>收件人:</b>${waringDetail.receiver}</td></tr>
 		<tr><td><img src="<%=basePath %>person/warn!imageCell"/></td></tr>
 	</table>
</body>  
</html>