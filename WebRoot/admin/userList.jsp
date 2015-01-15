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
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
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
			$("#username").validatebox({validType: 'speci'});
			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  
			      username: $('#username').val()
			        }); 
			})

			$("#resetBtn").click(function(){
			$("#username").val("");
			})

   			var utitle = "";
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>admin/user!queryPageJson',
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
				  {field:'username',title:'用户名',width:fixColumnWidth(0.5)},
				//  {field:'rolename',title:'角色名称',width:'300',align:'center'},
				  {field:'operation',title:'操作',width:fixColumnWidth(0.5),align:'center',       
					   formatter: function(val,row) {
					   	  return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openUserTab(this,\"用户列表\",\"edit\","+row.id+")'>编辑</a></span>"+
					   	  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.id+")'>删除</a></span>";
					   
					   }
				   }
				]],
				queryParams: {//传递参数的方法
					username: '${useractionlist.username}',
					rolename: '${userroelmap[useractionlist.id]}'
					
				},
			
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
				
			});
			displayMsg($('#tt'));
			

  });
		
		function manyUsersLicens(){
			var url="<%=request.getContextPath()%>/admin/user!manyUsersLicens";
			$.messager.confirm('警告', '确定批量为没有角色的用户授权吗?', function(r){
				if(r){
					$.ajax({
						 url: url,
						 async: false,
						 success:function(data){
						 $.messager.alert("提示", data); 
						}
			        });
			        
		    	 }else{
					return false;
		    	 }
			});
		}
	   
   </script>
</head>
<body>
	<div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
		  	<td class="s-t">用户名</td>
            <td><input class="s-text" id="username" type="text" value=""></td>
            <td><a href="javascript:void(0)" id="queryBtn"  class="but-search">&nbsp;</a>
		      	<a href="javascript:void(0)" id="resetBtn"  class="but-remove">清空</a></td>
		  </tr>
	 </table>
  </div>
  <div class="search-list">
  	<span class="list-title">用户列表</span>
    <table id = "tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/admin/user!updatestutas?user.id="/>
		<input type="hidden" id="addURL" value="<%=request.getContextPath()%>/admin/user!adduserjsp"/>
		<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/admin/user!edit?user.id="/>
  <div id="toolbar">
	<a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="openUserTab(this,'用户列表','add');">新增</a>
	<a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick='manyUsersLicens()' >批量授权</a>
  </div>	
</body>
</html>