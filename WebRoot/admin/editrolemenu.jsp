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

		<title>修改角色菜单权限</title>
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
		
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.core-3.3.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/jquery.ztree.excheck-3.3.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/zTreeStyle/zTreeStyle.css" >
		
		<style type="text/css">
			.tableForm li{height: auto;}
			ul.ztree {margin-left: 17px;margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:380px;overflow-y:scroll;overflow-x:auto;}
		</style>
		<script type="text/javascript">
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
			
			var zNodes;
			
			$(document).ready(function(){
			//	var menuTreeList = document.getElementsByName("parentMenuIds");
			//	for(var i=0;i<menuTreeList.length;i++) {
			//		var treeId = "#treeDemo_" + menuTreeList[i].value;
					
					var url = "<%=basePath%>admin/roleaction!ajaxGetTree.action?rolefunction.roleId="+$("#id").val();
					var jsonCount = sendAjax(url);
					zNodes = jsonCount;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//	}
			});
		</script>
	</head>
	<body>
    	<div class="tableForm">
			<form action="<%=basePath %>admin/roleaction!updateRoleMenuAction" id="editForm" method="post">
				<input type="hidden" id="id" name="rolefunction.roleId" value="${rolefunction.roleId}"/>
				<input type="hidden" id=menuidlist name="menuidlist"/>
		<!-- 		<c:forEach var="parentMenuId" items="${parentMenuIds}">
					<input type="hidden" value="${parentMenuId }" name="parentMenuIds">
				</c:forEach> -->
				<div class="title">修改菜单权限</div>
				<!-- 
				<table>
					<tr>
						<td class="t-title" width="80">
                            <input type="hidden" id="id" name="rolefunction.roleId" value="${rolefunction.roleId}"/>
                            修改菜单权限</td>
                        <td>
                        	<ul>
                              <c:forEach var="menulist" items="${menulist}">
                              	<li>
                                  <input type="checkbox" name="menuidlist" value="${menulist.id}" 
                                   <c:forEach var="rolemenuid" items="${rolemenuidlist}">
                                        <c:if test="${menulist.id==rolemenuid}"> 
                                          checked="checked"
                                        </c:if>
                                     </c:forEach>>${menulist.name}</input>
                                </li>
                              </c:forEach>
                            </ul>
						</td>
						<input type="hidden" id="menunum" />
					</tr>
				</table>
				-->
				<div class="zTreeDemoBackground left">
					<input type="hidden" id="menunum" />
					<ul id="treeDemo" class="zTree"></ul>
				</div>
                <div class="t-but">
					<a href="javascript:void(0)" class="but-change" onclick="roleSubmit('角色列表')"  plain="true">修改</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
                </div>
			</form>
    	</div>
	</body>
</html>
