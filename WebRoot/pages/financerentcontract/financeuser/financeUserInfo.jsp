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
	<title>组用户列表 </title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
	$(document).ready(function(){
		$("#orgName,#lastCostCenter").validatebox({validType: 'speci'});
	})
	$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					orgName:$("#orgName").val(),
					startDate:$("#startDate").val(),
					paymentCycle:$("#paymentCycle").val(),
					lastCostCenter:$("#lastCostCenter").val(),
					endDate:$("#endDate").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#orgName,#startDate,#paymentCycle,#lastCostCenter,#endDate").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>rent/financeUser!queryPageJson',
				title: "",
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
					  {field:'userName',title:'姓名',width:fixColumnWidth(0.10000)},
					  {field:'userEmail',title:'邮箱',width:fixColumnWidth(0.20000)},
					  {field:'isGroupLeader',title:'是否组长',width:fixColumnWidth(0.10000),
						formatter:function(val){ if(val==0) val="组员"; else if(val==1) val="组长"; return val; }},
					  {field:'chargeCompanies',title:'所负责公司',width:fixColumnWidth(0.30000)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.30000),
				   	  	formatter: function(val,node) {                                                   
					  		return result = "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"组用户列表\",\"view\","+node.id+")' >查看</a>&nbsp;</span>"
					  			+"&nbsp;<a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"组用户列表\",\"edit\","+node.id+")' >编辑</a></span>"
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
<%--			showMessage(new Date('2013','09','16'),'信息',--%>
<%--					'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈！',5000,'slide');--%>
		});
   </script>
</head>
<body>
<div class="search-list">
	<span class="list-title">组用户列表</span>
	<table id = "tt" align="center" toolbar="#toolbar"></table>
	<input type="hidden" id="userTypeId" value="<c:if test='${empty user.typeid}'>0</c:if><c:if test='${not empty user.typeid}'>${user.typeid}</c:if>"/>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/rent/financeUser!view?financeUser.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/rent/financeUser!edit?financeUser.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/rent/financeUser!delete?financeUser.id="/>
	<div id="toolbar">
		<a href="javascript:void(0)" id="insertBtn" iconCls="icon-add" class="easyui-linkbutton" plain="true" onclick="openTab(this,'组用户列表','edit');">新增</a>
	</div>
</div>
</body>
</html>