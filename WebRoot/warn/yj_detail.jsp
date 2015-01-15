<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>司龄明细</title>  
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
</head>  
<body>  
	<table width="100%" border="0" id="viewTab">
   		<tr><td><b>主题：</b>${waringDetail.theme}</td></tr>
  		<tr><td><b>收件人：</b>${waringDetail.receiver}</td></tr>
		<s:if test="waringDetail.copyperson!=null">
	  		<tr><b>抄送人:</b>${waringDetail.copyids}</td></tr>
	  		<tr><td>${htmlContent[0]}<img src="<%=basePath %>images/logo.png">${htmlContent[1]}</td></tr>
		</s:if>
	 	<s:else>
  			<tr><td><img src="<%=basePath %>person/warn!test?waringDetail.id=${waringDetail.id}"/></td></tr>
		</s:else>
  	</table>
</body>  
</html>