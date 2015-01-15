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
		<title>菜单列表详细</title>
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>

	<body>
     <div class="tableForm">
     	<div class="title">菜单列表详细</div>
		<table id="viewTab">
			<tr><td class="t-title" width="100">叶子节点：</td>
				<td width="150"><s:iterator value="leafNames">
				    <s:if test="menu.leaf==key"><s:property value="value" /></s:if>
			    </s:iterator></td>
			    <td class="t-title" width="120">菜单级别：</td>
			    <td width="150"><s:iterator value="levelNames">
				    <s:if test="menu.level==key"><s:property value="value" /></s:if>
			    </s:iterator></td>
		    	<td class="t-title" width="120">窗口位置：</td>
		    	<td width="150"><s:iterator value="targetNames">
				    <s:if test="menu.target==key"><s:property value="value" /></s:if>
			    </s:iterator></td>
            </tr>
            <tr>
			    <td class="t-title">上级菜单名称：</td>
			    <td><s:if test="menu.parentId==null">无</s:if>
			    <s:else>
				    <s:iterator value="parentMenus" >
					     <s:if test="menu.parentId==id">
					     	<s:property value="name" />
					     </s:if>
				    </s:iterator>
			    </s:else></td>
                <td class="t-title">菜单显示名称：</td>
		    	<td><s:property value="menu.name" /></td>
		    	<td class="t-title">链接显示title：</td>
		    	<td><s:property value="menu.title" /></td>
            </tr>
			<tr><td class="t-title">链接URL地址：</td>
				<td><s:if test="menu.url==null or menu.url==''">无</s:if><s:else><s:property value="menu.url" /></s:else></td>
				<td class="t-title">同级节点显示顺序：</td>
				<td colspan="3"><s:property value="menu.subSequence" /></td>
           </tr>
	  	</table>
     </div>
	</body>
</html>
