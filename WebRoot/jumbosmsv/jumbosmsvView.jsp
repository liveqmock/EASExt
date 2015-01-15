<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>短信模板详细信息</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   	<style type="text/css"> table tr td{font-size: 12px;} </style>
</head>
<body>
  <div class="tableForm">
    <div class="title">短信模板详细信息</div>
	<table id="viewTab">
		<tr><td class="t-title">模版名称：</td>
			<td><s:property value='jumbosmsv.name' /></td>
		</tr>
		<tr><td class="t-title">模版内容：</td>
			<td><s:property value='jumbosmsv.content' /></td>
		</tr>
	</table>
  </div>
</body>
</html>
