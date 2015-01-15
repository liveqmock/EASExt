<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>查询组织信息</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
   		$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fname_l2:$("#fname_l2").val(),
					fdeptname:$("#fdeptname").val(),
					fcityname:$("#fcityname").val(),
					fsaledepart:$("#fsaledepart").val(),
					teamdepart:$("#teamdepart").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#fname_l2,#fdeptname,#fcityname,#fsaledepart,#teamdepart").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>caseinfo/caseinfo!selectAdmin',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					   {field:'FCOMPNAME',title:'公司名称',width:fixColumnWidth(0.0700)},
					   {field:'FDEPTNAME',title:'部门名称',width:fixColumnWidth(0.0700)},
					   {field:'FCITYNAME',title:'城市名称',width:fixColumnWidth(0.0500)},
					   {field:'FSALEDEPART',title:'营业部名称',width:fixColumnWidth(0.1000)},
					   {field:'TEAMDEPART',title:'团队名称',width:fixColumnWidth(0.0500)},
					   {field:'operation',title:'操作',width:fixColumnWidth(0.02000),
					   	  	formatter: function(val,node) {
					   	  		var fdeptname = node.FDEPTNAME;
					   	  		var fcityname = node.FCITYNAME;
					   	  		var fsaledepart = node.FSALEDEPART;
						   	 	var result = "<span><a class=\"operation-a\" onclick=setCostCenter('"
							   	 	+fdeptname+"','"+fcityname+"','"+fsaledepart+"') href='javascript:void(0);'>选择</a></span>";
						  		return result;
						   }
				  		 }
				]],
				queryParams: {//传递参数的方法
					fdeptname: '${param.fdeptname}',
					fcityname:'${param.fcityname}',
					fsaledepart:'${param.fsaledepart}'
				},
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
			 	onLoadSuccess: omitLongData()  
								
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
   		});
   		/*
   		*给页面的数据赋值
   		*/
   		function setCostCenter(fdeptname,fcityname,fsaledepart){
			var id = $("#id").val();
   			parent.setAdmin(fdeptname,fcityname,fsaledepart,id);
			doCancel();
   		}
   </script>
</head>
<body>
	<div class="search">
	<input type="hidden" name="id" id="id" value="${param.id }"/>
	<table id="queryTab" onkeyup="bindQuery();">
		<tr><td class="s-t">部门：<input class="s-text" type="text" id="fdeptname" name="fdeptname" value=""></td>
			<td class="s-t">城市：<input class="s-text" type="text" id="fcityname" name="fcityname" value=""></td>
			<td class="s-t">团队：<input class="s-text" type="text" id="teamdepart" name="teamdepart" value=""></td>
		</tr>
		
		<tr><td class="s-t">营业部：<input class="s-text" type="text" id="fsaledepart" name="fsaledepart" value=""></td>
			<td colspan="2">
				<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
			</td>
		</tr>
	</table>
	</div>
	<div class="search-list">
		<span class="list-title">部门信息</span>
		<table id="tt"></table>
	</div>
</body>
</html>