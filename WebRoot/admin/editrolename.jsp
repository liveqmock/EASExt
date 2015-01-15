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

		<title>编辑角色名称</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	</head>
		<script type="text/javascript">
		$(document).ready(function(){		
			$("#rolename").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#rolename").validatebox({validType: "exist['<%=basePath %>admin/roleaction!ifRoleHasExists','#oldrolename']"});
			$("#rolename").validatebox({validType: 'speci'});
		})
		</script>
	<body>
    	<div class="tableForm">
			<form action="<%=basePath %>admin/roleaction!updateRoleName" id="editForm" method="post">
				<input type="hidden" id="id" name="role.id" value="${role.id}"/>
                <div class="title">编辑角色名称</div>
				<table>
					<tr>
						<td class="t-title">角色名称</td>
                        <td>
							<input class="t-text" type="text" id="rolename" name="role.rolename" value="${role.rolename}" />
							<input type="hidden" id="oldrolename" value="${role.rolename}"/>
						</td>
						<td class="t-but">
						<a href="javascript:void(0)" class="but-change" onclick="editRoleNameSubmit('角色列表')"  plain="true">修改</a>
						<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
						</td>
					</tr>
				</table>
			</form>
    	</div>
	</body>
</html>
