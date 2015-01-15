<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>利息返还日</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/common/compliance.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
  	<script type="text/javascript"><!--
	$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
				});
			})
			$("#resetBtn").click(function(){
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>/project/projectAction!queryIinterestTime?fid='+$("#fid").val(),
				width: 'auto',
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
				      {field:'LNTERESTTIME',title:'利息返还时间',width:fixColumnWidth(0.17000)},
					  {field:'FID',title:'返还状态',width:fixColumnWidth(0.16000),
					     formatter: function(val,row) {
					         if(row.STATE==0){
					           return "<select onchange='updateTime(this.value,"+val+")'><option value=\"0\">未还</option><option value=\"1\">已还</option></select>";
					         }else{
					           return "<select onchange='updateTime(this.value,"+val+")'><option value=\"0\" disabled=\"disabled\">未还</option><option value=\"1\" selected=\"selected\">已还</option></select>";
					         }
						   		
						   }
					     },
					   {field:'operation',title:'操作',width:fixColumnWidth(0.19000),
						   formatter: function(val,row) {
						   		return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='sendRecord("+row.FID+")' >发送逾期报告</a></span>&nbsp;";
						   		
						   }
					}
					
				]],
				//下面 定义 分页配置 ：
			 	onLoadSuccess: omitLongData()  
			});
			});
	//修改利息返还日状态
   function updateTime(value,fid){
	   var json = sendAjax("<%=basePath%>/project/projectAction!vailTimeReport?state="+value+"&fid="+fid);
	      if(json.vail == "1"){
	     	  $.messager.confirm('警告', json.message, function(r){
	               if(r){
	                 var jsont = sendAjax("<%=basePath%>/project/projectAction!updateInTimeState?state="+value+"&fid="+fid+"&send="+1);
	                  $.messager.alert("提示",jsont.success == "true"?"操作成功！":"操作失败！",
									jsont.success == "true"?"info":"error",function(){})
	               }
	      	   })
	      }else{
	      	  $.messager.alert("提示",json.success == "true"?"操作成功！":"操作失败！",
									json.success == "true"?"info":"error",function(){
		 	  })
		  }
   }
 function sendRecord(id){
	var　url = $("#sendURL").val() + id;
	$.messager.confirm('警告', '确定要发送逾期报告？', function(r){
		if(r){
			$.ajax({
			     type: "get",
				 url: url,
				 success:function(json){
					$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
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
			<span class="list-title">贷款编号:<s:property value='projectManage.loanNumber'/></span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
	    <input type="hidden" id="fid" value="<s:property value='projectManage.fid'/>"/>
	    <input type="hidden" id="sendURL" value="<%=basePath%>/project/projectAction!sendOverdueReport?date=<%= new Date() %>&fid="/>
	</body>
</html>
