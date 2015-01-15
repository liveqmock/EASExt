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
	<title>短信模版 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
   		$(function() {
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/jumb!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					{field:'id',title:'ID',width:0,align:'center',hidden:'true'},
				    {field:'name',title:'模版名称',width:fixColumnWidth(0.2000)},
					{field:'content',title:'模版内容',width:fixColumnWidth(0.4000)},
					{field:'operation',title:'操作',width:fixColumnWidth(0.2000),
					   formatter: function(val,node) {
				   		return "<span><a href='javascript:void(0)' onclick='openTab(this,\"短信模板列表\",\"view\","
				   			+node.id+")' class=\"operation-a\">查看</a>&nbsp;"
				   			+"&nbsp;<a href='javascript:void(0)' onclick='openTab(this,\"短信模板列表\",\"edit\","
				   			+node.id+")' class=\"operation-a\">编辑</a></span>"
				   			+"&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
					   	}
				   },
				   {field:'isuse',title:'是否启用',width:fixColumnWidth(0.2000),
			   			formatter: function(val,node) {
					   		if(node.isuse==1){
					   			return "<span>已启用&nbsp;</span>";
					   		}else{
					   			return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='isuse_btn("+
					   				node.id+")'>未启用</a>&nbsp;</span>";
					   		}
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
		
		//启用、停用
		function isuse_btn(id){
			var　url = "<%=request.getContextPath()%>/person/jumb!isuse?jumbosmsv.id=" + id;
			$.messager.confirm('提示', '确定启用此信息模板吗？', function(r){
				if(r){
					$.ajax({url: url,async: false,
						 success:function(){
							$.messager.alert("提示","操作成功！","info",function(){
								$("#tt").datagrid("reload");
							})
		 				}
					});
				}
			});
		}
   </script>
</head>
<body>
<div class="search-list">
  	<span class="list-title">短信模版列表</span>
    <table id="tt" toolbar="#toolbar"></table>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/person/jumb!view?jumbosmsv.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/person/jumb!edit?jumbosmsv.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/person/jumb!delete?jumbosmsv.id="/>
	<div id="toolbar"><a href="javascript:void(0)" id="insertBtn" iconCls="icon-add" class="easyui-linkbutton" plain="true" 
		onclick="openTab(this,'短信模板列表','edit');">新增</a>
	</div>
	<%--			+"<a href='<%=basePath %>jumbosmsv/jumbosmsvAdd.jsp' id='add_jumb'>添加模版信息</a>";--%>
</div>
</body>
</html>