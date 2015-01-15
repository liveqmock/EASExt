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

		<title>添加角色列表</title>
	</head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
		
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.3.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.3.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" >
		
		<style type="text/css">
			.tableForm li{height: auto;}
			ul.ztree {margin-left: 0px;margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:380px;overflow-y:scroll;overflow-x:auto;}
		</style>
		
		<script type="text/javascript">
		var zNodes;
		$(document).ready(function(){		
			$("#rolename").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#rolename").validatebox({validType: "exist['<%=basePath %>admin/roleaction!ifRoleHasExists','#oldrolename']"});
			$("#rolename").validatebox({validType: 'speci'});
			var menuTreeList = document.getElementsByName("parentMenuIds");
					var url = "<%=basePath%>admin/roleaction!ajaxGetTree.action";
					var jsonCount = sendAjax(url);
					zNodes = jsonCount;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
			var setting = {
				check: {
					enable: true,
					chkboxType:  { "Y": "ps", "N": "ps" }
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				view: {
					showIcon: false
				}
			};
			
		</script>
	<body>
    	<div class="tableForm">
			<form action="<%=basePath %>admin/roleaction!insertRoleAction" id="editForm" method="post">
            	<div class="title">添加角色</div>
				<table>
					<tr>
						<td class="t-title" width="80">角色名称</td>
                        <td>
							<input class="t-text" type="text" id="rolename" name="role.rolename"/>
							<input type="hidden" id="oldrolename" value=""/><span class="required">*</span>
						</td>
					</tr>
					<tr>
						<td class="t-title">添加菜单权限</td>
                        <td>
                        	<%-- 
                            <ul>
                                 <c:forEach var="menuaa" items="${menulist}">
                                 	<li><input type="checkbox" name="menuidlist" value="${menuaa.id}" >${menuaa.name}</li>
                                 </c:forEach>
                             </ul>
                             --%>
                             <input type="hidden" id=menuidlist name="menuidlist"/>
							 <div class="zTreeDemoBackground left">
							 	<ul id="treeDemo" class="zTree"></ul>
							 </div>
						</td>
						<input type="hidden" id="menunum" />
					</tr>
					<%--<tr>
						<td class="t-title">添加功能权限</td>
                        <td>
                        <ul>
						 <c:forEach var="functionaa" items="${functionlist}">
						 	<li><input type="checkbox" name="functionidlist" value="${functionaa.id}" > ${functionaa.funname}</input></li>
						 </c:forEach>
                         </ul>
						</td>
					</tr>
					<tr>--%>
				</table>
                <div class="t-but">
					<a href="javascript:void(0)" class="but-change" onclick="roleSubmit('角色列表')" plain="true">添加</a>
					<a href="javascript:void(0)" class="but-cancel" iconCls="icon-cancel" plain="true">取消</a>
                </div>
			</form>
    	</div>
	</body>
</html>
