<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.creditease.eas.admin.bean.User" %>
<%@ page import="com.change.eas.util.Config" %>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/ztree/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.core-3.3.js"></script>
	    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<title>法律文件模板配置</title>
		<script type="text/javascript">
	var setting = {
		data : {
			key : {
				title : "t",
				iname : "value"
			},
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : onClick
		},
		view: {
			showLine: false
		}
	};
	var zNodes =[<%=request.getAttribute("tree")%>];

//	function onClick(event, treeId, treeNode, clickFlag) {
//		if(treeNode.id > 10 && treeNode.id < 100){
//			$("#valueAdd").val(treeNode.value);
//		}else if(treeNode.id > 1000 || treeNode.id == 1000){
//			$("#valueUpdate").val(treeNode.value);
//		}else{
//			$("#valueAdd").val("");
//			$("#valueUpdate").val("");
//		}
//	}
function onClick(event, treeId, treeNode, clickFlag) {
		if(treeNode.id >= 1000){
			$("#valueAdd").val(treeNode.value);
		}else{
			$("#valueAdd").val("");
		}
	}
	<%User user = (User) request.getSession().getAttribute("user");%>
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	function addTemplate(){
		//var value = $("#valueAdd").val();
		//if(value){
		//	window.location.href='<%=Config.getKey("word.url")%>/Template/TemplateEdit.jsp?FileType=.doc&UserName=<%=user.getUsername()%>&CityID='+value;
		//}else{
		//	$.messager.alert("提示", "请选择要添加模板的城市！");
		//}
		
		window.location.href='<%=request.getContextPath()%>/limitedpartner/limitedpartner!addTemplatePage';
	}
	function updateTemplate(){
		var value = $("#valueUpdate").val();
		if(value){
			window.location.href="<%=Config.getKey("word.url")%>/Template/TemplateEdit.jsp?FileType=.doc&UserName=<%=user.getUsername()%>&RecordID="+value;
		}else{
			$.messager.alert("提示", "请选择要修改的模板！");
		}
	}
	function delTemplate(){
		//var value = $("#valueUpdate").val();
		//if(value){
		//	$.messager.confirm('警告', '确定需要删除此模板吗?', function(r){
       //         if (r){
        //            location.href = "<%=basePath%>limitedpartner/limitedpartner!deleteLawTemplate?RecordID="+value;
        //           }
       //           });
			
		//}else{
		//		$.messager.alert("提示", "请选择要删除的模板！");
		//}
		var value = $("#valueAdd").val();
		//value = templateID
		if(value){
		//	alert(value);
			$.messager.confirm('警告', '确定需要删除此模板吗?', function(flag){ if (flag)location.href = "<%=request.getContextPath()%>/limitedpartner/limitedpartner!deleteLawTemplate?RecordID="+value; });
		}else{
			$.messager.alert("提示", "请选择要删除的模板！");
		}
	}
	function toMarkPage(){
		window.location.href="<%=basePath%>limitedpartner/limitedpartner!listMark";
	}
</script>
	</head>
	<body>
		<input name="value" type="hidden" id="valueAdd" />
		<input name="value" type="hidden" id="valueUpdate" />
		<table style="text-align: center;width:100%">
			<tr>
				<td>
					<input type="button" onclick="addTemplate()" value="新增模板"/>
				<!--	<input type="button" onclick="updateTemplate()" value="修改模板 "/>-->
					<input type="button" onclick="delTemplate()" value="删除模板  "/>
					<input type="button" onclick="toMarkPage()" value="标签管理"/>
				</td>
			</tr>
			<tr style="width: 100%">
				<td style="width: 100%" colspan="5">
					<div style="width: 100%" class="zTreeDemoBackground left">
						<ul style="width: 100%" id="treeDemo" class="ztree"></ul>
					</div>
				</td>
			</tr>
		</table>
		<object id="WebOffice" width="0%" height="0%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="<%=request.getContextPath()%>/iWebOffice2009.cab#version=10,7,2,4"></object>
	</body>
</html>