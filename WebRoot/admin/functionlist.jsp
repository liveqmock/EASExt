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
	<title>功能列表</title>
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
   			var utitle = "功能列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
   	   			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
   				+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
   				+"&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"<a href='<%=basePath %>admin/functionaction!addfunctionAction' id='operval'>添加功能 </a>";
			
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>admin/functionaction!queryPageJson',
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
				  {field:'funname',title:'功能名称',width:'300',align:'center'},
				  {field:'url',title:'url',width:'300',align:'center'},
				  {field:'operation',title:'操作',width:'100',sortable:true,align:'center',
					   formatter: function(val,row) {
					   	  return "<span><a href='javascript:void(0)' onclick='changefunction("+row.id+")' style='color:blue'>编辑</a></span>"+
					   	  "&nbsp;&nbsp;&nbsp;<span><a href='javascript:void(0)' onclick='updatestudas("+row.id+")' style='color:blue'>删除</a></span>";
					   }
				   }
				]],
				
				queryParams: {//传递参数的方法
					funname: '${functionList.funname}',
					url:'${functionList.url}',
					
				},
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
			});
			displayMsg($('#tt'));
			
			
			
		//弹出窗口 设置样式 
		$("#operval, #editoper").fancybox({
			'hideOnContentClick': false,
			'width'	 : '60%',
			'height'	 : '50%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat'       : function(title){return  "";}
		});

  });
		
		     
		 function test(){
			$.fancybox.close();
			alert("恭喜你,成功了");
			$("#tt").datagrid("reload");
		}  
	  function changefunction(id){
	        var url="<%=request.getContextPath()%>/admin/functionaction!selectFunctionByKey?functionaction.id="+id;
	 		$("#editoper").attr("href",url);
			$("#editoper").click();
	  }
		
	  function updatestudas(id){
		  var url="<%=request.getContextPath()%>/admin/functionaction!updateStutas?functionaction.id="+id;
		  if(confirm("确认删除该条数据吗?")==false){
	 			return false;
			 }$.ajax({
			  		url: url,
			  		async: false
			 	});
			
	        $("#tt").datagrid("reload");
	        alert('删除成功');
	  }
	  
	   
   </script>
</head>

<body>

<div >
<table id = "tt"></table>
<a id="operval" href="#"></a>
<a id="editoper" href="#"></a>
</div>

</body>

</html>