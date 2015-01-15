<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
		<base href="<%=basePath%>">
		<title>导入数据异常数据行号</title>
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
  <body>
    <div class="tableForm">
     	<div class="title">异常数据行号</div>
		<table id="viewTab">
		<tr>
		<td><%=request.getAttribute("errorNum")%></td>
		</tr>
		
		</table>
		
	 </div>
  </body>
</html>