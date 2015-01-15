<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>房屋合同预警邮件详细信息</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
	<body>
    <div class="tableForm">
   	    <div class="title">房屋合同预警邮件详细信息</div>
		<table id="viewTab">
			<tr><td class="t-title" width="100">部门：</td>
				<td><s:property value="sendPortInfo.orgname" /></td>
				<td class="t-title" width="100">城市：</td>
				<td><s:property value="sendPortInfo.city" /></td>
				<td class="t-title" width="100">办公室地址：</td>
				<td><s:property value="sendPortInfo.officeadd" /></td>
			</tr>
			<tr>
				<td class="t-title">末级成本中心：</td>
				<td><s:property value="sendPortInfo.lastcostcenter" /></td>
                <td class="t-title">收件人邮箱 ：</td>
				<td><s:property value="sendPortInfo.portmail" /></td>
				<td class="t-title">邮件内容 ：</td>
				<td><s:property value="sendPortInfo.content" /></td>
			</tr>
			
			<tr><td class="t-title">附件 ：</td>
				<td><s:property value="sendPortInfo.contentfile" /></td>
				<td class="t-title">发送时间 ：</td>
				<td><s:date name="sendPortInfo.creatortime" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="t-title">状态 ：</td>
				<td><s:property value="sendPortInfo.ext1" /></td>
			</tr>
		</table>
     </div>
	</body>
</html>
