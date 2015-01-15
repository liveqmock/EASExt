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
		<title>添加用户</title>
		
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
		<script type="text/javascript">
		$(document).ready(function(){		
			$("#username,#password").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#username").validatebox({validType: "exist['<%=basePath %>admin/user!ifUserHasExists','#oldusername']"});
		})
	
		</script>
		
	</head>
	<body>
    	<div class="tableForm">
			<form action="<%=basePath %>adminAuthority/user!addUser" method="post" id="editForm">
            	<div class="title">添加用户</div>
				<table>
					<tr>
						<td class="t-title" width="60">用名</td>
                        <td>
							<input class="t-text" type="text" id="username" name="user.username"/><span class="required">*</span>
							<input type="hidden" id="oldusername" value="" />  
						</td>
					</tr>
					<tr>
						<td class="t-title">密码</td>
                        <td>
							<input class="t-text" type="password" id="password" name="user.password"/><span class="required">*</span>
						</td>
					</tr>
					<tr>
						<td class="t-title">添加角色</td>
                        <td>
						<%--<br>
						 <select name="roleidlist">
							 <s:iterator value="rolelist" >
							 	<option align="right" value="<s:property value='id'/>"><s:property value='rolename'/> </option>
							 </s:iterator>
						 </select>
						 	
						 	--%>
                          <ul>
						 	<c:forEach var="role" items="${rolelist}">
                            <li>
						  	 <input type="checkbox" name="roleidlist" value="${role.id}" >${role.rolename}</input>
                            </li>
						    </c:forEach>
						    <input type="hidden" id="rolenum" />  
                          </ul>
						</td>
					</tr>
				</table>
                <div class="t-but">
                	<%--<input class="easyui-linkbutton" type="submit"  value="添加" />&nbsp;&nbsp;
						<input class="easyui-linkbutton" type="button" value="取消" onclick="doCancel()"/>
					--%>
					<a href="javascript:void(0)" class="but-change" onclick="usersubmit('用户信息')" plain="true">添加</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
					
                </div>
			</form>
         </div>
	</body>
</html>
