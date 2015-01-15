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
	<title>文件下载详细信息</title>
  	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
	<body>
		<div class="tableForm">
     	<div class="title">文件下载详细</div>
		<table id="viewTab">
			<tr><td class="t-title" width="100">文件名：</td>
				<td><s:property value="ftpFile.filename"/></td>
				<td class="t-title" width="100">文件路径：</td>
				<td><s:property value="ftpFile.filepath"/></td>
				<td class="t-title" width="100">文件类型：</td>
				<td><s:property value="ftpFile.filetype"/></td>
			</tr>
		</table>
		</div>
	</body>
</html>
