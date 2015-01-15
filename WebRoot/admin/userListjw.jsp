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
   			var utitle = "用户列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			//+"<a href='<%=basePath %>admin/addUser.jsp' id='operval'>添加用户</a>";
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>admin/user!queryPageJson',
				title: utitle,
				width: 1000,
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
				  {field:'username',title:'用户名',width:'200',align:'center'},
				  {field:'operation',title:'操作',width:'200',sortable:true,align:'center',
					   formatter: function(val,row) {
					   	  return "<span><a href='javascript:void(0)' onclick='changePassword("+row.id+")' style='color:blue'>编辑</a></span>";
					   
					   }
				   }
				]]
				
			});
			displayMsg($('#tt'));
			
		//弹出窗口
		$("#operval").fancybox({
			'hideOnContentClick': false,
			'width'	 : '60%',
			'height'	 : '80%',
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
		
	  function changePassword(obj){
	    	var　url = "<%=request.getContextPath()%>/admin/user!edit?user.id=" + obj;
			$("#operval").attr("href",url);
			$("#operval").click();
	    }
	    
	    function doSearch(){  
	        $('#tt').datagrid('load',{  
	            username: $('#username').val()
	        });  
	    }  
	       /**
	    *关闭子窗体
	    **/
	    function closeChildWinow(){
			$.fancybox.close();
			$("#tt").datagrid("reload");
		}
   </script>
</head>
<body>
<div style="padding:3px">  
    	<table border="0">
		  <tr>
		    <td>
		     	用户名:
		    </td>
		    <td align="right">
		     		<input id="username" style="line-height:26px;border:1px solid #ccc">
			</td>
		    <td align="right" valign="bottom">
		      	<input type="button" value="查询" onClick="doSearch()"></input>
		    </td>
		  </tr>
	 </table>
	</div>  
    <br/>
	<br/>
<table id = "tt"></table>
<a id="operval" href="#"></a> 
</body>
</html>