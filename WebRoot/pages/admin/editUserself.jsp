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
<title>修改密码</title>
<style type="text/css"> table tr td{font-size: 12px;} </style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
<script type="text/javascript">
	$(function(){
		$("#newPwd,#newPwdAgain").validatebox({required: true,missingMessage: '必选'});
		$("#newPwdAgain").validatebox({validType:"same['newPwd']"});
	})
</script>
</head>
<body>
	<div class="tableForm">
	<form action="<%=basePath %>adminAuthority/user!updateSelf" method="post" id="editForm">
		<input type="hidden" id="userid" name="user.id" value="${sessionScope.user.id }"/>
        <div class="title">修改密码</div>
		<table>
			<tr><td class="t-title">用户名</td>
				<td><input class="t-text required" type="text" disabled="disabled" value="${sessionScope.user.username}"/>
					<input type="hidden" name="user.username" value="${sessionScope.user.username}"/>
				</td>
			</tr>
			<tr>
				<td class="t-title">请输入新密码</td>
				<td><input class="t-text required" type="password" name="user.password" id="newPwd"/></td>
			</tr>
			<tr>
				<td class="t-title">请再次输入新密码</td>
				<td><input class="t-text required" type="password" id="newPwdAgain"/></td>
			</tr>
		</table>
        <div class="t-but">	
		    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab();"  plain="true">取消</a>
			<a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn')" plain="true">修改</a>
		</div>
	</form>
    </div>
</body>
</html>
