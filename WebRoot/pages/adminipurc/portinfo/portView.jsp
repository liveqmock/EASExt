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
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	</head>
	<body>
    <div class="tableForm">
    	<div class="title">接口人详细信息</div>
		<table width="100%" border="0" id="viewTab">
			<tr><td class="t-title" width="100">使用部门</td>
				<td><s:property value="portinfo.forgName" /></td>
				<td class="t-title" width="100">城市</td>
				<td><s:property value="portinfo.fcity" /></td>
                <td class="t-title" width="100">办公室座落地点</td>
				<td><s:property value="portinfo.fofficeAddr" /></td>
           </tr>
           <tr>
				<td class="t-title">末级成本中心</td>
				<td><s:property value="portinfo.flastCostcenter" /></td>
				<td class="t-title">部门总接口人</td>
				<td>
						<s:property value="portinfo.fisAllPort" />
				</td>
				<td class="t-title">接口人姓名</td>
				<td>
			  		<s:property value="portinfo.fportName" />
			  	</td>
			</tr>
			<tr>
				<td class="t-title">接口人邮箱</td>
				<td><s:property value="portinfo.fportEmail" /></td>
			</tr>
		</table>
    </div>
    <br/>
		<div class="t-but" style="text-align: center;">
				  <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">返回</a>
			</div>
	</body>
</html>
