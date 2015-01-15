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
		<title>综合管理和BP人员编辑</title>
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	    <style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
		$("#managermail").validatebox({validType: "emailcn['#managermail']"});
		$("#hrbpmail").validatebox({validType: "emailcn['#hrbpmail']"});
		})
	</script>
	</head>
	<body>
    	<div class="tableForm">
		<form action="<%=basePath %>person/orgManager!save?orgManager.fnumber=${fnumber}" method="post" id="editForm">
        	<div class="title">综合管理和BP人员编辑</div>
			<table>
				<tr><td class="t-title" width="100">部门名称</td>
					<td><input class="t-text readonly" type="text" id="orgname" disabled="disabled" name="orgManager.orgname" value="${orgname}"/></td>
					<td class="t-title" width="100">部门编码</td>
					<td><input class="t-text readonly" type="text" disabled="disabled" name="orgManager.fnumber" id="fnumber" value="${fnumber}"/></td>
				</tr>
				
				<tr><td class="t-title">综合管理</td>
					<td><input class="t-text" type="text" name="orgManager.managername" id="managername" value="${orgManager.managername}"/></td>
					<td class="t-title">综合管理邮箱</td>
					<td><input class="t-text" type="text" name="orgManager.managermail" id="managermail" value="${orgManager.managermail}"/></td>
				</tr>
				
				<tr><td class="t-title">HRBP</td>
					<td><input class="t-text" type="text" name="orgManager.hrbpname" id="hrbpname" value="${orgManager.hrbpname}"/></td>
					<td class="t-title">HRBP邮箱</td>
					<td><input class="t-text" type="text" name="orgManager.hrbpmail" id="hrbpmail" value="${orgManager.hrbpmail}"/></td>
				</tr>
			</table>
            <div class="t-but">	
           		 <a href="javascript:void(0)" class="but-change" id="editBtn" onclick="submitTab('editBtn','综合管理和BP人员')">修改</a>
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
        </div>
	</body>
</html>
