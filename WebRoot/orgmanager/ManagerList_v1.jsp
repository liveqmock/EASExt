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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
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
   			var vartitle = "综合管理和BP人员信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/orgManager!queryPageJson',
				title: vartitle,
				width: 1000,
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					{field:'orgname',title:'部门名称',width:0,align:'center',hidden:'true'},
				    {field:'managername',title:'综合管理人员',width:100,align:'center',formatter: function(val){
							return val;
						}
					} ,
					{field:'hrbpname',title:'BP人员',width:100,align:'center',formatter: function(val){
							return val;
						}
					} 
				]],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
			
		//弹出窗口
		$("#operval").fancybox({
			'hideOnContentClick': false,
			'width'	 : '60%',
			'height'	 : '45%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat'       : function(title){return  "";}
		});

  });
		function searchByParams(){
		    var params = $("#tt").datagrid("options").queryParams;
		    params["ifSearch"] = 1;
		    //设置页码为1
		    //$("#tt").datagrid("options").pageNumber = 1;//查询的时候需要将当前页设置为1
		    //加上参数reload   
		    $("#tt").datagrid("reload", params);
		}
		
		function test(){
			$.fancybox.close();
			 $("#tt").datagrid("reload");
		}
   </script>
</head>
<body>
<table id = "tt"></table>
<a id="operval" href="#"></a> 
<a id="edit_oper" href="#"></a> 
</body>
</html>