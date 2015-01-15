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
	<title>预警发送记录 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript">
   		$(function() {
   		   	//加载贷后管理负责人(PM)
			var json = sendAjax("<%=basePath%>/project/projectAction!selectPm");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].PMNAME+" >"+json[i].PMNAME +"</option>");
				$("#head").append($opt);
			}
   			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					warnType:$("#warnType").val(),
					head:$("#head").val(),
					loanNo:$("#loanNo").val(),
					loanTimeStart:$("#loanTimeStart").val(),
					loanTimeEnd:$("#loanTimeEnd").val(),
					expireTimeStart:$("#expireTimeStart").val(),
					expireTimeEnd:$("#expireTimeEnd").val(),
					interestTimeStart:$("#interestTimeStart").val(),
					interestTimeEnd:$("#interestTimeEnd").val(),
					sendState:$("#sendState").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#warnType").val("");
				$("#loanNo").val("");
				$("#loanTimeStart").val("");
				$("#loanTimeEnd").val("");
				$("#expireTimeStart").val("");
				$("#expireTimeEnd").val("");
				$("#interestTimeStart").val("");
				$("#interestTimeEnd").val("");
				$("#sendState").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>pmInfo/pmInfoAction!selectAllEmailHistory',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信息 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					 {field:'WARNTYPE',title:'预警类型',width:fixColumnWidth(0.1200),align:'center',
					 	formatter:function(val){
					 		var warnText = "";
					 		if('1' == val){
					 			warnText = "结清报告";
					 		}else if('2' == val){
					 			warnText = "到期还款提醒";
					 		}else if('3' == val){
					 			warnText = "存续期还款提醒";
					 		}else if('4' == val){
					 			warnText = "紧急！！！逾期报告";
					 		}else if('5' == val){
					 			warnText = "紧急！！！逾期报告(含本金)";
					 		}
					 		return warnText;
					 	}},
					  {field:'LOANNUMBER',title:'贷款编号',width:fixColumnWidth(0.1200),align:'center'},
					  {field:'HEAD',title:'PM',width:fixColumnWidth(0.1700),align:'center'},
					  {field:'LOANTIME',title:'出借日',width:fixColumnWidth(0.1700),align:'center',
					  	formatter: function(val) {
					  		if(null != val && '' != val){
					  			return val.substring(0,val.indexOf("T"));
					  		}
					   }},
					  {field:'EXPIRETIME',title:'到期日',width:fixColumnWidth(0.1700),align:'center',
					  	formatter: function(val) {
					  		if(null != val && '' != val){
					  			return val.substring(0,val.indexOf("T"));
					  		}
					   }},
					  {field:'INTERESTTIME',title:'利息返还日',width:fixColumnWidth(0.1700),align:'center',
					  	formatter: function(val) {
					  		if(null != val && '' != val){
					  			return val.substring(0,val.indexOf("T"));
					  		}
					   }},
					  {field:'SENDDATE',title:'发送时间',width:fixColumnWidth(0.1700),align:'center'},
					 {field:'SENDSTATE',title:'发送状态',width:fixColumnWidth(0.1200),align:'center',
					 	formatter:function(val){
					 		var sendS = "";
					 		if('1' == val){
					 			sendS = "成功";
					 		}else if('0' == val){
					 			sendS = "失败";
					 		}
					 		return sendS;
					 	}}
				]],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
				onLoadSuccess: omitLong() //omitLongData()超出21字符会显示省略号，不太友好
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
			
	});
   </script>
</head>
<body>
  <div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
			
			<td class="s-t">预警类型</td>
            <td>
            <select id="warnType" onchange="changeQyery();">
            	<option value="" selected>全部</option>
            	<option value="1">结清报告</option>
            	<option value="2">到期还款提醒</option>
            	<option value="3">存续期还款提醒</option>
            	<option value="4">紧急！！！逾期报告</option>
            	<option value="5">紧急！！！逾期报告(含本金)</option>
            </select>
            </td>
            
			<td class="s-t">贷款编号</td>
            <td><input class="s-text" type="text" id="loanNo" ></td>
            
			<td class="s-t">出借日</td>
            <td><input class="s-text Wdate" type="text" id="loanTimeStart" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">至</td>
            <td><input class="s-text Wdate" type="text" id="loanTimeEnd" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			
		</tr>
		<tr>
			<td class="s-t">到期日</td>
            <td><input class="s-text Wdate" type="text" id="expireTimeStart" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">至</td>
            <td><input class="s-text Wdate" type="text" id="expireTimeEnd" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">利息返还日</td>
            <td><input class="s-text Wdate" type="text" id="interestTimeStart" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">至</td>
            <td><input class="s-text Wdate" type="text" id="interestTimeEnd" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>	
		</tr>
		<tr>
			<td class="s-t">发送状态</td>
         	<td> 
         	<select id="sendState" onchange="changeQyery();">
            	<option value="">全部</option>
            	<option value="1">成功</option>
            	<option value="0">失败</option>
            </select>
            </td>
			<td class="s-t">PM</td>
         	<td><select id="head" onchange="changeQyery();"><option value="">全部</option></select></td>
			<td colspan="4">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a>
			</td>
		</tr>
    </table>
 </div>
 <div class="search-list">
  	<span class="list-title">预警发送记录</span>
	<table id = "tt" align="center" toolbar="#toolbar"></table>
 </div>
	<div id="iframeWin" class="easyui-window" title="预警发送记录" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>