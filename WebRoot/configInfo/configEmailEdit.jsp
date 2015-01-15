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

		<title>邮箱查看</title>
		
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<SCRIPT type="text/javascript">
			function doSubmit(){
				$('#editForm').form('submit',{
					onSubmit: function(){
						var result = $(this).form('validate');
						if(result){
						  $('#editForm')[0].submit();
						}
					}
				})
			}
		</SCRIPT>
	</head>

	<body>
		<form action="admin/emailConfig!update" method="post" id="editForm">
			<input type="hidden" value="<s:property value="emailConfig.fid"/>" name="emailConfig.fid">
			<table align="center">
			
				<tr>
					<td colspan="2" align="center">
						<b>添加定时任务配置模版</b> 
					</td>
				</tr>
				<tr>
					<td align="right">模块：</td>
					<td><input type="text" name="emailConfig.fmodule" value="<s:property value="emailConfig.fmodule" />" disabled="disabled" /></td>
				</tr>
				<tr>
					<td align="right">角色：</td>
					<td><input type="text" name="emailConfig.frole" value="<s:property value="emailConfig.frole"/>" disabled="disabled" /></td>
				</tr>
				
				<tr>
					<td align="right">邮箱：</td>
					<td>
						<input type="text" name="emailConfig.femail" 
							value="<s:property value="emailConfig.femail"/>" 
							<s:if test="isView == 1">disabled="disabled"</s:if>
							class="t-text easyui-validatebox" validType="severalemail" size="80" />
					</td>
				</tr>
				
				<s:if test="isView != 1">
					<tr>
						<td align="center" colspan="2">
							<input type="button" onclick="doSubmit();" value="更新" />&nbsp;&nbsp;<input type="button" value="取消" onclick="javascript:parent.$.fancybox.close();"/>
						</td>
					</tr>	
				</s:if>
				
			</table>
		</form>
	</body>
</html>
