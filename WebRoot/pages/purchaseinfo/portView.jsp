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
		<title>接口人详细信息</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
	<body>
    <div class="tableForm">
    	<div class="title">接口人详细信息</div>
		<table width="100%" border="0" id="viewTab">
			<tr><td class="t-title" width="100">使用部门：</td>
				<td><s:property value="portInfo.orgname" /></td>
				<td class="t-title" width="100">地区：</td>
				<td><s:property value="portInfo.city" /></td>
                <td class="t-title" width="100">办公室座落地点：</td>
				<td><s:property value="portInfo.officeadd" /></td>
           </tr>
           <tr>
				<td class="t-title">成本中心：</td>
				<td><s:property value="portInfo.lastcostcenter" /></td>
				<td class="t-title">部门总接口人：</td>
				<td><s:iterator value="isPorts">
					<s:if test="portInfo.isport == key">
						<s:property value="value" />
					</s:if>
				</s:iterator></td>
				<td class="t-title">接口人邮箱：</td>
				<td><s:property value="portInfo.portmail" /></td>
			</tr>
			
			<tr><td class="t-title">创建人：</td>
				<td><s:property value="portInfo.creator" /></td>
				<td class="t-title">创建时间：</td>
				<td><s:date name="portInfo.creatortime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                <td colspan="2"></td>
			</tr>
			
			<tr><td class="t-title">修改人：</td>
				<td><s:property value="portInfo.lastupdater" /></td>
				<td class="t-title">修改时间：</td>
				<td><s:date name="portInfo.lastupdatetime" format="yyyy-MM-dd hh:mm:ss"></s:date></td>
                <td colspan="2"></td>
			</tr>
		</table>
    </div>
	</body>
</html>
