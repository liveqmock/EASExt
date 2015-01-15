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
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#orgname,#lastcostcenter").validatebox({validType: 'speci'});
	})

		$(function() {
			var userTypeId=$("#userTypeId").val();
   			/******************付款方式*************************/
			$.ajax({
				   type: "POST",
				   url: "<%=basePath%>/rent/rentContract!getAllDictionarys",
				   async:false,//发送同步请求
				   dataType: "json",
				   success: function(data){
			   		   for(var i=0;i<data.length;i++){
			       			$("#paymentcycle").append($("<option value="+data[i].key +">"+data[i].value +"</option>"));;
					   }
				   }
			});
   			/******************付款方式*************************/
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				//页面验证通过之后，验证开始时间小于结束时间
				var startTimeValid = $("#startDate").val();//开始时间
				var endTimeValid = $("#endDate").val();//结束时间
				if(startTimeValid!="" && endTimeValid!=""){
					if(startTimeValid>endTimeValid){
						$.messager.alert("提示","开始时间不能大于结束时间");
								return;
							}
						}
				
				$("#tt").datagrid("load",{
					orgname:$("#orgname").val(),
					startDate:$("#startDate").val(),
					paymentcycle:$("#paymentcycle").val(),
					lastcostcenter:$("#lastcostcenter").val(),
					endDate:$("#endDate").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#orgname,#startDate,#paymentcycle,#lastcostcenter,#endDate").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>rent/rentContract!queryPageJson',
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
					  {field:'orgname',title:'使用部门',width:fixColumnWidth(0.1300)},
					  {field:'city',title:'地区',width:fixColumnWidth(0.0700)},
					  {field:'officeadd',title:'办公室座落地点',width:fixColumnWidth(0.2000)},
					  {field:'lastcostcenter',title:'成本中心',width:fixColumnWidth(0.2000)},
					  {field:'paymentcycle',title:'付款周期',width:fixColumnWidth(0.0700),
						formatter:function(val){
							if(val == "month") val="每月";
							else if(val == "twoMonths") val="两月";
							else if(val == "quarter") val="季度";
							else if(val == "fourMonths") val="四月";
							else if(val == "fiveMonths") val="五月";
							else if(val == "halfYear") val="半年";
							else if(val == "sevenMonths") val="七月";
							else if(val == "eightMonths") val="八月";
							else if(val == "nineMonths") val="九月";
							else if(val == "tenMonths") val="十月";
							else if(val == "elevenyear") val="十一月";
							else if(val == "year") val="一年";
							return val;
						}
					  },
					  {field:'paycount',title:'付款次数',width:fixColumnWidth(0.0700)},
					  {field:'rentstarttime',title:'开始日期',width:fixColumnWidth(0.1100)},
					  {field:'rentendtime',title:'结束日期',width:fixColumnWidth(0.1100)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
				   	  	formatter: function(val,node) {
					  		var id=node.id; var orgDiffer = node.ext1;
					  		return handleOperationPurview(this,"房屋合同信息列表",id,userTypeId,orgDiffer,"rentContract");
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
			if(userTypeId==10) $("#insertBtn").remove();
			showMessage(new Date('2013','09','16'),'信息',
					'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈！',5000,'slide');
		});
   </script>
</head>
<body>
  <div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
			<td class="s-t">付款周期</td>
			<td><select id="paymentcycle" onChange="changeQyery();"> <option value="">--全部--</option> </select> </td>
            <td class="s-t">使用部门</td>
        	<td><input class="s-text" type="text" id="orgname" value=""></td>
			<td class="s-t">开始日期</td>
            <td><input class="s-text Wdate" type="text" id="startDate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
            <td class="s-t">结束日期</td>
            <td><input class="s-text Wdate" type="text" id="endDate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td></tr>
		<tr><td class="s-t">成本中心</td>
        	<td><input class="s-text" type="text" id="lastcostcenter" value=""></td>
			<td colspan="6">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain="true">清空</a>
			</td></tr>
	</table>
 </div>
 <div class="search-list">
  	<span class="list-title">房屋合同信息列表</span>
	<table id = "tt" toolbar="#toolbar"></table>
	<div id="toolbar"><a href="javascript:void(0)" id="insertBtn" iconCls="icon-add" class="easyui-linkbutton" plain="true" 
		onclick="openTab(this,'房屋合同信息列表','edit');">新增</a></div>
 </div>
	<input type="hidden" id="userTypeId" 
		value="<c:if test='${empty user.typeid}'>0</c:if><c:if test='${not empty user.typeid}'>${user.typeid}</c:if>"/>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/rent/rentContract!view?rentContract.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/rent/rentContract!edit?rentContract.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/rent/rentContract!delete?rentContract.id="/>
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>