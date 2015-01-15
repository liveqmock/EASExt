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

		<title>My JSP 'update.jsp' starting page</title>

	</head>

	<body>
		<form action="person/conf!save" method="post" >
			<table align="center">
				<tr>
					<td colspan="2" align="center">
						<b>添加定时任务配置模版</b> 
					</td>
				</tr>
				<tr>
					
					<td align="right">
						配置名称：
					</td>
					<td>
						<input type="text" name="configInfo.configname" />
					</td>
				</tr>
				<tr>
					<td align="right">
						配置说明：
					</td>
					<td>
						<input type="text" name="configInfo.markinfo" />
					</td>
				</tr>
				
				
				
				<tr>
					<td align="center">
						<input type="submit" value="添加" />&nbsp;&nbsp;<input type="button" value="取消" onclick="javascript:parent.$.fancybox.close();"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
