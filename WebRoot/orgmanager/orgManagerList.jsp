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
	<title>综合管理和BP人员信息表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
   		$(function() {
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/orgManager!allList',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				rownumbers:true,//显示序号
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
				  {field:'orgname',title:'部门名称',width:fixColumnWidth(0.2000)},
				  {field:'fnumber',title:'部门编码',width:fixColumnWidth(0.1000)},
				  {field:'managername',title:'综合管理人员',width:fixColumnWidth(0.1000)},
				  {field:'managermail',title:'综合管理人员邮箱',width:fixColumnWidth(0.2000)},
				  {field:'hrbpname',title:'BP人员',width:fixColumnWidth(0.1000)},
				  {field:'hrbpmail',title:'BP人员邮箱',width:fixColumnWidth(0.2000)},
				  {field:'operation',title:'操作',width:fixColumnWidth(0.2500),
			   		formatter: function(val,node) {
	  					return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' "
	  						+"onclick='openTab(this,\"综合管理和BP人员\",\"edit\",&quot;"+node.fnumber.toString()+"&quot;)'>编辑</a></span>"
	  						+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' "
	  						+"onclick='openTab(this,\"抄送人信息\",\"view\",&quot;"+node.fnumber.toString()+"&quot;)'>抄送人信息</a></span>"
					   }
				   }
				]],
				onLoadSuccess: omitLongData()
			});
			displayMsg($('#tt'));
  		});
   </script>
</head>
<body>
<div class="search-list">
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/orgmanager/copyPeopleInfo.jsp?fnumber="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/person/orgManager!edit?orgManager.fnumber="/>
	<table id="tt"></table>
</div>
</body>
</html>