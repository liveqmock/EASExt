<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>预警明细</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#exportExcelBtn").click(function(){
				$.messager.confirm('确认', '确定要导出查询的信息吗？', function(r){
   					if(r){//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
						var url="<%=basePath %>upload/upload!exportExcel.action?begin=" + $("#begin").val()+"&end="
							+$("#end").val()+"&receiver="+$("#receiver").val()+"&issend="+$("#issend").val()+"&theme="
							+$("#theme").val()+"&typeid="+$("#typeid").val()+"&wayid="+$("#wayid").val();
						var fireFox = 0;
   			   	   		if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
   			   	   			fireFox = 1;
   			   	   	   	}
   			   	   	   	url += "&fireFox="+fireFox;
						location.href=url;
   					}
				});
			})
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				var startTimeValid = $("#begin").val();//开始时间
				var endTimeValid = $("#end").val();//结束时间
				var result=$('#form1').form('validate');
				
				if(startTimeValid!="" && endTimeValid!=""){
					if(startTimeValid>endTimeValid){
						$.messager.alert("提示","开始时间不能大于结束时间");
						return;
					}
				}

				if(result){


				
				$("#tt").datagrid("load",{
					begin:$("#begin").val(),
					end:$("#end").val(),
					receiver:$("#receiver").val(),
					issend:$("#issend").val(),
					theme:$("#theme").val(),
					typeid:$("#typeid").val(),
					wayid:$("#wayid").val()
				});
				}
			})
			$("#resetBtn").click(function(){
				$("#begin,#end,#receiver,#issend,#theme,#typeid,#wayid").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/warn!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:true,
				rownumbers:true,
				columns: [[
				    {field:'typename',title:'预警类别',width:fixColumnWidth(0.0900)},
					{field:'wayname',title:'预警方式',width:fixColumnWidth(0.0700)},
					{field:'theme',title:'主题',width:fixColumnWidth(0.1800)},
					{field:'sendtime',title:'发送时间',width:fixColumnWidth(0.1900),//格式化日期 的方法
						formatter: function(val){
							return (null!=val && ""!=val)?val.replace("T"," "):"";
						}
					},
					{field:'email',title:'收件人邮箱',width:fixColumnWidth(0.1400)},
					{field:'receiver',title:'收件人',width:fixColumnWidth(0.0700)},
					{field:'sendcount',title:'发送次数',width:fixColumnWidth(0.0600)},
					{field:'ext1',title:'状态',width:fixColumnWidth(0.0500),
						formatter: function(val){
							return val!='失败'?'成功':'失败';
						}
					},
					{field:'operation',title:'操作',width:fixColumnWidth(0.1200),
					   formatter: function(val,node) {
					   	  return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openNewDialog(this,"+
					   	  	node.id+","+node.typeid+","+node.wayid+")'>查看</a>"+
					   	  	"&nbsp;<a class=\"operation-a\" href='javascript:void(0)' onclick='sendNewDialog("+node.id+")'>重发</a></span>";
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

			//获得里面的取值
			/******************预警类别*************************/
			url = "<%=basePath%>person/warn!selectAllWaringType";
			var json = sendAjax(url);
			var $sel = $("#typeid");
			var typeid = "${param.typeid}";
			for(var i=0;i<json.length;i++){
				var $opt = "";
				if(typeid==json[i].ID){
					$opt = $("<option value="+json[i].ID +" selected>"+json[i].TYPENAME +"</option>");
				}else{
					$opt = $("<option value="+json[i].ID +">"+json[i].TYPENAME +"</option>");
				}
				$sel.append($opt);
			}
			/******************预警方式*************************/
			var url = "<%=basePath%>person/warn!selectAllSendWays";
			var json = sendAjax(url);
			var $sel = $("#wayid");
			var wayid = "${param.wayid}";
			for(var i=0;i<json.length;i++){
				var $opt = "";
				if(wayid==json[i].ID){
					$opt = $("<option value="+json[i].ID +" selected>"+json[i].WAYNAME +"</option>");
				}else{
					$opt = $("<option value="+json[i].ID +">"+json[i].WAYNAME +"</option>");
				}
				$sel.append($opt);
			}
			/******************显示批量重发对话框*************************/
			$("#oneKeyResend").click(function(){
			//$("#resendMsgDialog").attr("style.display","block")
			});
		});	
		/**
		*弹窗事件
		**/
		function openNewDialog(btn,id,typeid,wayid,title){
			var url = "";
			if(typeid==1 && wayid==1){//短信
				url = "<%=request.getContextPath()%>/person/warn!queryDetailCell?waringDetail.id=" + id;	
			}else if (typeid==2||typeid==4||typeid==1){//邮件
				url = "<%=request.getContextPath()%>/person/warn!queryDetail?waringDetail.id=" + id;		
			}else if (typeid==3){//邮件
				url = "<%=request.getContextPath()%>/person/warn!yearOfWorkDetail?waringDetail.id=" + id;		
			}
			addTab(title||"预警明细_详细信息", url);
		}
		function sendNewDialog(id){
			var url = "";
			url = "<%=request.getContextPath()%>/person/warn!retryMail?waringDetail.id=" + id;	
			$.messager.confirm('确认', '确定重发邮件吗？', function(r){
				if(r){//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
					$.ajax({url: url, async: false });
					$("#tt").datagrid("reload");
				}
			});
		}
		//批量
		function sendNewDialogPiLiang(){
			var begin = $("#begin").val();
			var end = $("#end").val();
			var typeid = $("#typeid").val();
			var wayid = $("#wayid").val();
			var url = "";
			url = '<%=request.getContextPath()%>/person/warn!resendMsgsForFail?queryParam.beginTime='+begin + '&queryParam.endTime='+end + '&queryParam.typeId='+typeid + '&queryParam.wayId='+wayid;	
			$.ajax({url: url, async: false });
			$("#tt").datagrid("reload");
			/***$.messager.confirm('确认', '确定重发邮件吗？', function(r){
				if(r){//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
					$.ajax({url: url, async: false });
					$("#tt").datagrid("reload");
				}
			});**/
		}
	</script>
</head>
<body>
<div class="search">
<form id="form1">
	<table onkeyup="bindQuery();">
		<tr><td class="s-t">开始时间</td>
		    <td><input type="text" id="begin" name="begin" readonly  
		    	onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
		    	class="s-text Wdate"/></td>
		    <td class="s-t">结束时间</td>
		    <td><input type="text" id="end" name="end" readonly 
		    	onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
		    	class="s-text Wdate" /></td>
		    <td class="s-t">收件人</td>
		    <td><input class="s-text easyui-validatebox" type="text" id="receiver" name="receiver" validType="njection"/></td>
	     	<td class="s-t">发送状态</td>
		    <td><select id="issend" name="issend" onChange="changeQyery();">
				<option value="">--全部--</option>
				<option value="1">成功</option>
				<option value="0">失败</option>
			</select></td>
	  </tr>
	  <tr><td class="s-t">预警主题</td>
	  	  <td><input class="s-text easyui-validatebox" type="text" id="theme" name="theme" type="text " validType="njection"/></td>
	      <td class="s-t">预警类别</td>
   		  <td><select id="typeid" name="typeid" onChange="changeQyery();">
  		  	  <option value="">--全部--</option></select>	</td>	
	      <td class="s-t">预警方式</td>
	      <td><select id="wayid" name="wayid" onChange="changeQyery();">
	          <option value="">--全部--</option></select></td>
		  <td colspan="2"><a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
			  <a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a></td>
	  </tr>
	</table>
	</form>
  </div>
  <div class="search-list">
	<span class="list-title">预警明细</span>
	<table id="tt" toolbar="#toolbar"></table>
	<div id="toolbar" align="right">
		<a href='javascript:void(0)' id="exportExcelBtn" class="easyui-linkbutton" iconCls="icon-redo" plain="true">excel文件</a><span id="tipMsg" style="color: red;"></span>
<%--		<span style="float:right;margin-right: 37px;margin-top: 4px;"><a id="oneKeyResend" class="operation-a" href='javascript:void(0)' onclick ='sendNewDialogPiLiang()'>一键批量重发</a></span>--%>
	</div>
<%--	<div id="resendMsgDialog" style="display: none;padding: 10px;height: 550px;">--%>
<%--		<script type="text/javascript">--%>
<%----%>
<%--	</script>--%>
<%--	<form id="resendMsgForm" action="" method="post" style="">--%>
<%--		<h4>批量重发</h4><br><hr><br>--%>
<%--		<table style="vertical-align: middle;">--%>
<%--			--%>
<%--		</table>--%>
<%--		--%>
<%--	</form>--%>
<%--	</div>--%>
 </div>
</body>
</html>