<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.creditease.eas.admin.bean.User" %>
<%@ page import="com.change.eas.util.Config" %>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/ztree/jquery.ztree.core-3.3.js"></script>
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<title>法律文件生成</title>
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
	function onClick(event, treeId, treeNode, clickFlag) {
		
		if(treeNode.id > 999){
			$("#template").val(treeNode.value);
		}else if(treeNode.id > 10 && treeNode.id<99){
			$("#city").val(treeNode.value);
		}
	
	}
	<%User user = (User) request.getSession().getAttribute("user");%>
	$(document).ready(function(){
		var message = "${error}";
		if(message){
			$.messager.alert("提示","您所选择的模板与公司名称不匹配，请重新选择");
			//alert("提示","您所选择的模板与公司名称不匹配，请重新选择");
		}
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	function fullTemplate(){
		
		var value = $("#template").val();
		var city = $("#city").val();
		if(value||city){
			
			window.location.href="<%=request.getContextPath()%>/limitedpartner/limitedpartner!buildLawfileCheck?pid=${id}&RecordID="+value+"&city="+city;
			//window.location.href="<%=Config.getKey("word.url")%>/servlet/BuildFileServlet?id=${id}&UserName=<%=user.getUsername()%>&RecordID="+value+"&city="+city;
		}else{
      	  $.messager.alert("提示", "请选择要填充的模板！");
		}
	}
</script>
	</head>
	<body>
		<input name="value" type="hidden" id="template" />
		<input name="value" type="hidden" id="city" />
		<table style="text-align: center;width: 90%">
			<tr>
				<td colspan="4"><input type="button" onclick="fullTemplate()" value="生成法律文件"/></td>
			</tr>
			<tr>
				<td>有限合伙人公司名称：</td>
				<td colspan="3" align="left">${name}</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="zTreeDemoBackground left" style="width: 80%;z-index: -1">
						<ul id="treeDemo" class="ztree" style="width: 80%"></ul>
					</div>
				</td>
			</tr>
		</table>
		<object id="WebOffice" width="0%" height="0%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="<%=request.getContextPath()%>/iWebOffice2009.cab#version=10,7,2,4"></object>
	</body>
</html>