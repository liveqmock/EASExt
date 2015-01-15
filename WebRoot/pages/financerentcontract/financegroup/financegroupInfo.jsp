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
	<title>组列表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>

 		<style type="text/css">
		a{
			color:blue;
			text-decoration: none;
		}
		a：v:VISITED{
			color:blue;
			text-decoration: none;
		}
	</style>
	<script type="text/javascript">
	$(function() {
		
   			var vartitle = ""
			//+"<a href='javascript:void(0)' id='insertBtn' class='easyui-linkbutton' onclick='openWin(\"edit\")'>新增</a>"
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>financegroup/financeGroupAction!queryPageJson',
				title: vartitle,
				width: ($(window).width()-40),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				rownumbers:true,
				singleSelect:false, 
				columns: [[
					  {field:'name',title:'组名称',width:fixColumnWidth(0.05000)},
					  {field:'creator',title:'创建人',width:fixColumnWidth(0.06000)},
					  {field:'creatortime',title:'创建时间',width:fixColumnWidth(0.05000),
					   formatter: function(val) {
					     return val.substring(0,val.indexOf("T"));
					    }
					  },
					  {field:'lastupdater',title:'修改人',width:fixColumnWidth(0.06000)},
					  {field:'lastupdatetime',title:'修改时间',width:fixColumnWidth(0.10000)},
					 
					  {field:'operation',title:'操作',width:fixColumnWidth(0.10000),
						   formatter: function(val,row) {
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"组列表\",\"edit\","+row.id+")' >编辑</a></span>"
						   		+"&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.id+")' >删除</a>";
						   }
			   		  }
				]],
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
   </script>
</head>
<body>
	<div class="search-list">
		<span class="list-title">组列表</span>
		<table id = "tt"  toolbar="#toolbar"></table>
		<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/financegroup/financeGroupAction!edit?financeGroupAction.id="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/financegroup/financeGroupAction!delete?financeGroupAction.id="/>
		
         <div id="toolbar">
                     <a  href="javascript:void(0)" id="insertBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openTab(this,'组列表','edit');">新增</a>
         </div>	
    </div>
</body>
</html>