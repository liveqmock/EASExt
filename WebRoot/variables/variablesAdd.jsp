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
		<form action="person/vari!save" method="post" >
			<table>
				<tr>
					<td colspan="4">
						添加变量
					</td>
				</tr>
				<tr>
					
					<td>
						属性码：
					</td>
					<td>
						<input type="text" name="variables.codesname" />
					</td>
				</tr>
				<tr>
					<td>
						变量名称：
					</td>
					<td>
						<textarea name="variables.variname" cols="15" rows="5"></textarea>
					</td>
				</tr>
				
				
				
				<tr>
					<td colspan="4">
						<input type="submit" value="添加" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
