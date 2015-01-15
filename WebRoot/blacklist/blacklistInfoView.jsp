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
		<title>例外人员名单详细信息</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
	<body>
    <div class="tableForm">
    	<div class="title">例外人员名单详细信息</div>
		<table width="100%" border="0" id="viewTab">
			<tr><td class="t-title" width="100">人员名称：</td>
				<td><s:property value="blackListInfo.pname" /></td>
				<td class="t-title" width="120">人员编码：</td>
				<td><s:property value="blackListInfo.pnumber" /></td>
				<td class="t-title" width="120">人员邮箱：</td>
				<td><s:property value="blackListInfo.pmail" /></td>
            </tr>
			<tr>
  				<td class="t-title">替代人员名称：</td>
  				<td><s:property value="blackListInfo.raplacename" /></td>
  				<td class="t-title">替代人员编码：</td>
  				<td><s:property value="blackListInfo.raplacenumber" /></td>
  				<td class="t-title">替代人员邮箱：</td>
  				<td><s:property value="blackListInfo.raplacemail" /></td>
  			</tr>
            <tr>
           	    <td class="t-title">预警类型：</td>
				<td><s:if test="warntype.id==1">全部</s:if><s:else><s:property value="warntype.typename" /></s:else></td>
				<td class="t-title">处理方式：</td>
  				<td colspan="3"><s:property value="promode.processmode" /></td>
            </tr>
		</table>
    </div>
	</body>
</html>
