<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
	<meta charset="UTF-8">  
	<title>Complex Toolbar on Dialog - jQuery EasyUI Demo</title>  
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
</head>  
<body>  
	<table width="100%" border="0" id="viewTab">
		<tr><td><table>
			<tr><td><b>主题：</td><td></b>${waringDetail.theme}</td></tr>
   			<tr><td><b>收件人：</td><td></b>${waringDetail.receiver}</td></tr>
   			<tr><td><b>抄送人：</td><td></b>${waringDetail.copyids}</td></tr>
    	</table></td></tr>    
	
		<tr><td width="100%">
			<table><tr><td>${htmlContent[0]}<img src="<%=basePath %>images/logo.png">${htmlContent[1]}</td></tr></table>
		</td></tr>	
 	</table>
</body>  
</html>