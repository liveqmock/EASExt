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
	   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	   	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>

	<body>
    <div class="tableForm">
    	<div class="title">组用户信息页面</div>
		<table>
			<tr>
                <td class="t-title" width="80">系统用户：</td>
                <td width="200">
                    <%--<s:iterator value="systemAllUsers">
                        <s:if test="financeUser.userId==ID"><s:property value="USERNAME" /></s:if>
                    </s:iterator>
                --%>
                    <s:property value="user.username" />
                </td>
                <td class="t-title" width="100">姓名：</td>
                <td width="200"><s:property value="financeUser.userName" /></td>
                <td class="t-title" width="100">邮箱：</td>
                <td width="200"><s:property value="financeUser.userEmail" /></td>
            </tr>
            <tr>
                <td class="t-title">所在组：</td>
                <td>
                    <s:iterator value="financeGroups">
                        <s:if test="financeUser.financeGroupId==ID"><s:property value="NAME" /></s:if>
                    </s:iterator>
                </td>
                <td class="t-title">是否组长：</td>
                <td>
                    <s:if test="financeUser.isGroupLeader==1">组长</s:if>
                    <s:if test="financeUser.isGroupLeader==0">组员</s:if>
                </td>
                <td class="t-title">所负责公司：</td>
                <td>
                    <s:property value='financeUser.chargeCompanies'/>
                </td>
            </tr>
		</table>
    </div>
	</body>
</html>
