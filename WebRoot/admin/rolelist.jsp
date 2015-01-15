<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
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
   			var utitle = "";

			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息  
				url:'<%=basePath%>admin/roleaction!queryPageJson',
				title: utitle,
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
				  {field:'rolename',title:'角色名称',width:fixColumnWidth(0.25)},
				  {field:'operation',title:'操作',width:fixColumnWidth(0.35),
					   formatter: function(val,row) {                                          
					   	  return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openRoleTab(this,\"角色列表\",\"editmenu\","+row.id+")'>修改菜单权限</a></span>"+
					   	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openRoleTab(this,\"角色列表\",\"editrole\","+row.id+")'>修改角色</a></span>"+
					   	 // "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href='javascript:void(0)' onclick='changefunc("+row.id+")' style='color:blue'>修改功能权限</a></span>"+
					   	  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.id+")'>删除角色</a></span>";
					   }
				   }
				]],
				
				queryParams: {//传递参数的方法
					rolename: '${rolelist.rolename}'
					
				},
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
			});
			displayMsg($('#tt'));
  });
		

	 
	  //修改功能权限(别删)
	 // function changefunc(id){
	 //       var url="<%=request.getContextPath()%>/admin/roleaction!selerolefuncid?rolefunction.roleId="+id;
	 //		$("#edioperb").attr("href",url);
	//		$("#edioperb").click();
	//  }
	  
   </script>
</head>

<body>
<div class="search-list">
	<span class="list-title">角色列表</span>
    <table id = "tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/admin/roleaction!updatestutas?rolefunction.roleId="/>
		<input type="hidden" id="addURL" value="<%=request.getContextPath()%>/admin/roleaction!addRoleAction"/>
		<input type="hidden" id="editmenuURL" value="<%=request.getContextPath()%>/admin/roleaction!selerolemenuid?rolefunction.roleId="/>
		<input type="hidden" id="editroleURL" value="<%=request.getContextPath()%>/admin/roleaction!selectRolebyid?role.id="/>

<div id="toolbar">
	<a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openRoleTab(this,'角色列表','addrole')">新增 </a>
</div>	
</body>

</html>