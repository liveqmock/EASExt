<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>导入合同信息 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
			function exportvalidContractInfo(url){
				window.location.href = url;
				$("#validTr").css("display","none");
			}
	</script>
</head>
<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div class="search" align="center">
		<form action="<%=basePath %>adminiPurc/adminiPurc!importContractExcel"  method="post" name="myform" id="myform" enctype="multipart/form-data"> 
			<table align="center" width="80%">
				<tr>
					<td width="40%" align="right">&nbsp;</td>
					<td width="60%" align="left">
						<input type="file" id="excelFile" name="excelFile"/>
						<input id="filePath" name="filePath" type="hidden"/><br/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true class="but-remove" onClick="importExcelFun_filePath('validTr')">导入</a>
					</td>
				</tr>
				<tr id="validTr" style="display: none;">
					<td>&nbsp;</td>
					<td align="center">
						<a href="javascript:void(0)" onclick="exportvalidContractInfo('<%=basePath %>adminiPurc/adminiPurc!exportvalidContractInfo');" plain=true class="but-remove" name="downExportvalidInfo">下载验证信息</a>
					</td>
				</tr>
			</table>
		</form>
 </div>
</body>
</html>