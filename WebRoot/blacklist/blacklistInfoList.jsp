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
	<title>例外人员名单 </title>
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
				url:'<%=basePath%>person/black!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				rownumbers:true,
				columns: [[
					 {field:'pname',title:'人员名称',width:fixColumnWidth(0.0700)},
					  {field:'pnumber',title:'人员编码',width:fixColumnWidth(0.1400)},
					  {field:'pmail',title:'人员邮箱',width:fixColumnWidth(0.1500)},
					  {field:'typename',title:'预警类型',width:fixColumnWidth(0.1000),
					  	formatter: function(val){ return val=='生日提醒'?"全部":val; }
					  },
					  {field:'raplacename',title:'替代人员',width:fixColumnWidth(0.0700)},
					  {field:'raplacemail',title:'替代人员邮箱',width:fixColumnWidth(0.1500)},
					  {field:'processmode',title:'处理方式',width:fixColumnWidth(0.1300)},
					{field:'operation',title:'操作',width:fixColumnWidth(0.2000),
					   formatter: function(val,node) {
					   		return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"例外人员名单\",\"view\","
					   			+node.pid+")' >查看</a>&nbsp;<a class=\"operation-a\" id='edit_btn' href='javascript:void(0)'"
					   			+" onclick='openTab(this,\"例外人员名单\",\"edit\","+node.pid+")' >编辑</a></span>&nbsp;<a"
					   			+" class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+node.pid+")' >删除</a>";
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
        <input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/person/black!view?blackListInfo.pid="/>
        <input type="hidden" id="editURL" value="<%=request.getContextPath()%>/person/black!edit?blackListInfo.pid="/>
        <input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/person/black!delete?blackListInfo.pid="/>
        <table id="tt" toolbar="#toolbar"></table>
        <div id="toolbar">
            <a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" 
                onclick="openTab(this,'例外人员名单','edit');">新增</a>
        </div>
    </div>
</body>
</html>