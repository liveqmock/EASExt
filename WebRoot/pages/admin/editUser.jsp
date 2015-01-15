<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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

		<title>修改用户</title>
		
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
			$("#username,#password").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
		})
	
		</script>
		
	<body>
    	<div class="tableForm">
			<form action="<%=basePath %>adminAuthority/user!update" method="post" id="editForm">
				<input type="hidden" id="userid" name="user.id" value="${user.id }"/>
                <div class="title">修改用户</div>
				<table>
					<tr>
						<td class="t-title" width="60">用户名</td>
						<td><input class="t-text readonly" type="text" id="username" name="user.username" value="${user.username }" readonly/>
						</td>
					</tr>
					<tr>
						<td class="t-title">密码</td>
						<td><input class="t-text" type="password" name="user.password" id="password"/>
							(提示：旧密码已经置为空!)
						</td>
					</tr>
					
					<tr>
						<td class="t-title">修改角色</td>
						<td>
                        	<ul>
                                <c:forEach var="role" items="${rolelist}">
                                 <li>
                                   <input type="checkbox" name="userrolechoseid" value="${role.id}" 
                                     <c:forEach var="userrole" items="${userroleidList}">
                                        <c:if test="${role.id == userrole}">
                                         checked = "checked"
                                        </c:if >
                                     </c:forEach>
                                   >${role.rolename}</input>
                                   </li>
                                </c:forEach>
                                <input type="hidden" id="rolenum" /> 
                            </ul>
						</td>
					</tr>
				</table>
                <div class="t-but">
                	<a href="javascript:void(0)" class="but-change" onclick="editsubmit('用户信息')" plain="true">修改</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
                </div>
			</form>
    	</div>
	</body>
</html>
