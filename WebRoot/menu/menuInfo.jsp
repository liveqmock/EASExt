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
	<title>菜单列表</title>
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
				url:'<%=basePath%>menu/menu!queryPageJson',
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
<%--   					  {field:'ck',checkbox:true},	--%>
					  {field:'leafName',title:'叶子节点',width:fixColumnWidth(0.05000)},
					  {field:'levelName',title:'菜单级别',width:fixColumnWidth(0.0600)},
					  {field:'targetName',title:'窗口位置',width:fixColumnWidth(0.0600)},
					  {field:'parentName',title:'上级菜单名称',width:fixColumnWidth(0.0800)},
					  {field:'name',title:'菜单显示名称',width:fixColumnWidth(0.1100)},
					  {field:'title',title:'链接显示title',width:fixColumnWidth(0.1100)},
					  {field:'url',title:'链接URL地址',width:fixColumnWidth(0.1200)},
<%--					  {field:'subSequence',title:'显示顺序',width:fixColumnWidth(0.0500)},--%>
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node) {
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"菜单列表\",\"view\","
						   		+node.id+")' >查看</a>&nbsp;</span>"
						   		+"&nbsp;<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"菜单列表\",\"edit\","
						   		+node.id+")' >编辑</a></span>"
						   		+"&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
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
        	<span class="list-title">菜单列表</span>
            <table id = "tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/menu/menu!view?menu.id="/>
		<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/menu/menu!edit?menu.id="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/menu/menu!delete?menu.id="/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<div id="toolbar">
		<a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" 
			onclick="openTab(this,'菜单列表','edit');">新增</a>
		</div>
	</body>
</html>