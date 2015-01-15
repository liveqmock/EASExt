<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>房屋合同信息汇总 </title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#fname_l2").validatebox({validType: 'speci'});
		})
	
   		$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fname_l2:$("#fname_l2").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#fname_l2").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>rent/financeRentContract!selectCostcenter',
				title: "",
				width: ($(window).width()-50),
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
                       {field:'fname_l2',title:'成本中心',width:'170'},
					  {field:'fnumber',title:'成本中心编号',width:'140'},
					  {field:'fdisplayname_l2',title:'使用部门',width:'200'},
					  {field:'operation',title:'操作',width:'100',
				   	  	formatter: function(val,node) {
					   	  	var result = "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='setCostCenter(\""+node.fname_l2+"\",\""+node.fnumber+"\",\""+node.fid+"\")' >选择</a>&nbsp;</span>";
					  		return result;
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
   		
   		function setCostCenter(fname_l2,fnumber,fid){
   			parent.setCostCenter(fname_l2,fnumber,fid);
			doCancel();
   		}
   		
   </script>
</head>
<body>
 <div class="search" style="min-width:600px;">
	<table onkeyup="bindQuery();">
		<tr>
            <td class="s-t">成本中心</td>
            <td><input class="s-text" type="text" id="fname_l2" value=""></td>
			<td>
				<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
			</td></tr>
	</table>
 </div>
 <div class="search-list">
 	<span class="list-title">房屋合同信息汇总</span>
	<table id = "tt"></table>
 </div>
</body>
</html>