<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
	<meta charset="UTF-8">  
	<title>预警短信详细信息</title>  
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
</head>  
<body>  
	<table width="100%" border="0" id="viewTab">
		<tr><td colspan="2"><strong>短信明细</strong></td></tr>
		<tr><td width="10%">收 件 人:</td>
			<td>${waringDetail.receiver}</td>
	</tr>
	<tr><td>短信内容:</td>
		<td>${waringDetail.cellcontent}</td>
		</tr>
	</table>
</body>  
</html>