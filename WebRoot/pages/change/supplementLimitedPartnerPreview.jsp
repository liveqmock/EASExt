<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01"%>


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
		<title>有限合伙人列表</title>
		<link id="easyuiTheme" rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/icon.css">
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/inc/style.css" />
		<style type="text/css">
table tr td {
	font-size: 12px;
}
</style>
	</head>
	<body>
		<div class="tableForm">
			<div class="title">
				补充信息详请预览
			</div>
			<table id="viewTab">
				<tr>
					<td class="t-title">
						普通合伙人（GP）:
					</td>
					<td >
						${limitedpartner.generalperson}
					</td>
					<td class="t-title">
						原有限合伙人（LP）：
					</td>
					<td>
						${limitedpartner.formerlimitedpartner}
					</td>
				</tr>
				<tr>
					<td class="t-title">
						代理人：
					</td>
					<td>
						${limitedpartner.proxy}
					</td>
					<td class="t-title">
						主要经营场所：
					</td>
					<td>
						${limitedpartner.businessplace}
					</td>
				</tr>
				<tr>
					<td class="t-title">
						出资时间：
					</td>
					<td>
						${limitedpartner.investedtime}
					</td>
					<td class="t-title">
						GP出资额（万元）：
					</td>
					<td>
						${limitedpartner.gpcontributionStr}
					</td>
				</tr>
				<tr>
					<td class="t-title">
						出资比例小数位：
					</td>
					<td>
						${limitedpartner.figure}
					</td>
					<td class="t-title">
						出资比例计算方式：
					</td>
					<td colspan="3">
						${limitedpartner.calculationmethod}
					</td>
				</tr>
			</table>
		<div class="t-but">
		    	<a href="javascript:void(0)" class="but-remove" onclick="javascript:history.go(-1);">返回</a>
            </div>
		</div>
	</body>
</html>
