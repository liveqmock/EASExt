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
    <title>案件管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
  	<script type="text/javascript">
  	$(function() {
  	  	//加载投诉渠道
		loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch');
   			/******************付款方式*************************/
		    var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $td = $("<option value='" + json[i].key + "'>" + json[i].value + "</option>");
				$tbfinicasetype.append($td);
			}
			
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fcompchannelid:$("#fcompchannelid").val(),
					startDate:$("#startDate").val(),
					endDate:$("#endDate").val(),
					fcomplainant:$("#fcomplainant").val(),
					tbfinicasetype:$("#tbfinicasetype").val(),
					floanbread:$("#floanbread").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#startDate,#endDate,#fcomplainant,#fcusname,#floanbread").val("");
				$("#fcompchannelid").val(0);
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>caseinfo/caseinfo!queryPageJson?choocase=true',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:true, 
				columns: [[
					  {field:'ck',checkbox:true},
					  {field:'fnumber',title:'编号',width:fixColumnWidth(0.0700)},
					  {field:'ditchname',title:'投诉渠道',width:fixColumnWidth(0.1000)},
					  {field:'fcomplainant',title:'投诉人',width:fixColumnWidth(0.1500)},
					  {field:'fcomptime',title:'投诉时间',width:fixColumnWidth(0.1300)},
					  {field:'servicename',title:'服务类型',width:fixColumnWidth(0.1000)},
					  {field:'statusname',title:'客户状态',width:fixColumnWidth(0.1000)},
					  {field:'statusvalue',title:'案件状态',width:fixColumnWidth(0.1000)},
					  {field:'casecadeInicasetypes',title:'初步分类',width:fixColumnWidth(0.09000),
						  formatter:function(val,node){
						  	var result="";
						  	for(var i=0;i<val.length;i++){
						  		result += val[i].inicaseTypeName+"，";
						  	}
						  	return result.substring(0,result.length-1);
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
		});
		//选择案件
		function chooseCaseFun(){
			var rowData = $('#tt').datagrid('getSelected');
			if(rowData){
		 		window.parent.$("#caseId").val(rowData.fid);
		 		window.parent.$("#caseCode").val(rowData.fnumber);
		 		window.parent.$('#iframeSource').removeAttr('src');
		 		window.parent.$('#iframeWin').window('close');
			}else{$.messager.alert("提示","请先选择案件！","info");}
		}
  	</script>
  </head>
  <body>
	<div class="search" style="min-width:800px;">
  	<form action="<%=basePath%>caseinfo/caseinfo!queryPageJson" method="post" id="form">
		<table id="queryTab" onkeyup="bindQuery();">
			<tr><td class="s-t">投诉渠道： </td>
				<td><select id="fcompchannelid" name="fcompchannelid" onchange="changeQyery();">
			    	</select></td>
				<td class="s-t">投诉时间从：</td>
				<td><input class="s-text" type="text" id="startDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"
					name="begin" value=""></td>
				<td class="s-t">到：</td><td><input class="s-text" type="text" id="endDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					class="Wdate" name="end" value=""></td>
			</tr>
			<tr><td class="s-t">贷款品种：</td><td><input class="s-text" type="text" id="floanbread" name="floanbread" value=""></td>
				<td class="s-t">投诉人姓名：</td><td><input class="s-text" type="text" id="fcomplainant" name="fcomplainant" value=""></td>
				<td class="s-t">平台客户姓名：</td><td><input class="s-text" type="text" id="fcusname" name="fcusname" value=""></td></tr>
		</table>
	</form>
	</div>
	<div id="toolbar">
 		<a href="javascript:void(0)" onclick="chooseCaseFun();" class="easyui-linkbutton" iconCls="icon-ok" plain="true">选择案件</a>
		<a href="javascript:void(0)" id="queryBtn" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		<a href="javascript:void(0)" id="resetBtn" class="easyui-linkbutton" iconCls="icon-undo" plain="true">清空</a>
	</div>
	<div class="search-list">
	    <table id="tt" align="center" toolbar="#toolbar"></table>
    </div>
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
