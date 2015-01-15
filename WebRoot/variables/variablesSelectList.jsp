<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>

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
	<title>短信模版变量 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
   		$(function() {
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/vari!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				idField:'id',
				singleSelect: false,
				columns: [[
					{field:'ck',checkbox:true},
					 {field:'id',title:'ID',hidden:'true'},
				    {field:'codesname',title:'属性码',width:fixColumnWidth(0.5000)},
					
					{field:'variname',title:'属性名称',width:fixColumnWidth(0.5000)}
				]],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
		//选择变量		
		function chooseCaseFun(){
			var rowData = $('#tt').datagrid('getSelections');
			if(rowData){
	            for(var i=0;i<rowData.length;i++){
					window.parent.$("#contents").val(window.parent.$("#contents").val()+"\${" + rowData[i].codesname + "}");
	            }
		 		window.parent.$('#iframeSource').removeAttr('src');
		 		window.parent.$('#iframeWin').window('close');
			}else{$.messager.alert("提示","请先选择！","info");}
		}
   </script>
</head>
<body>
	<table id ="tt" toolbar="#toolbar"></table>
	<div id="toolbar">
		<a href="javascript:void(0)" onclick="chooseCaseFun();" class="easyui-linkbutton" iconCls="icon-ok" plain="true">选择变量</a>
	</div>
</body>
</html>