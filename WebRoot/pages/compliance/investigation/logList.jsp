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
  	<script type="text/javascript">
  		$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					startDate:$.trim($("#startDate").val()),
					endDate:$.trim($("#endDate").val()),
					fieldname:$.trim($("#fieldname").val()),
					opname:$.trim($("#opname").val()),
					fnumber:$.trim($("#fnumber").val())	
				});
			})
			$("#resetBtn").click(function(){
				$("#startDate,#endDate,#fieldname,#opname,#fnumber").val("");
			})
			
			//时间格式化
			Date.prototype.format = function(format){
			    if(!format){
			        format = "yyyy-MM-dd hh:mm:ss";
			    }
			    var o = {
			            "M+": this.getMonth() + 1, // month
			            "d+": this.getDate(), // day
			            "h+": this.getHours(), // hour
			            "m+": this.getMinutes(), // minute
			            "s+": this.getSeconds(), // second
			           "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
			           "S": this.getMilliseconds()
			   	};

			   if (/(y+)/.test(format)) {
			        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    }
			    for (var k in o) {
			        if (new RegExp("(" + k + ")").test(format)) { 
			            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" +o[k]).length));
			       }
			    }
			    return format;
			};
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>caseinfo/log!queryPageJson',
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
					{field:'opName',title:'操作人',width:fixColumnWidth(0.04000)},
					{field:'complainId',title:'操作案件编号',width:fixColumnWidth(0.04000)},
					{field:'opcontent',title:'操作内容',width:fixColumnWidth(0.04500)},
					{field:'credateStr',title:'操作时间',width:fixColumnWidth(0.04000)},
						/*formatter:function(value,row){
							var d = new Date(value).format("yyyy-MM-dd hh:mm:ss");
							//return $.fn.datebox.defaults.formatter(d);
							return d;
						}
					 },*/
					  //{field:'opIp',title:'操作IP',width:fixColumnWidth(0.05800)},
					  {field:'updateFielddescription',title:'字段',width:fixColumnWidth(0.05800)},
					  {field:'tt1',title:'更新前',width:fixColumnWidth(0.05800)},
					  {field:'tt2',title:'更新后',width:fixColumnWidth(0.05800)}
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
		
	</script>
	</head>
	<body>
		<div class="search">
  		<form action="<%=basePath%>caseinfo/caseinfo!queryPageJson" method="post" id="form">
			<table id="abc" onkeyup="bindQuery();">
				<tr>
					<td class="s-t">时间从</td>
					<td><input type="text" id="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
						class="s-text Wdate" name="begin" value=""></td>
				    <td class="s-t">至</td>
				    <td><input type="text" id="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
				    	class="s-text Wdate" name="end" value=""></td>
				    
				    <td class="s-t">案件编号:</td>
					<td><input class="s-text" type="text" id="fnumber" name="fnumber" value=""></td>
					
				</tr>
				<tr>		
				    <td class="s-t">字段名称:</td>
					<td><input class="s-text" type="text" id="fieldname" name="fieldname" value=""></td>	
				    <td class="s-t">操作人:</td>
					<td><input class="s-text" type="text" id="opname" name="opname" value=""></td>	
				    	
				    	
				    <td colspan="2">
							<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
							<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
					</td>	
				</tr>
			</table>
			<br/>
		</form>
		</div>
		<div class="search-list">
			<span class="list-title">日志列表</span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
		
	</body>
</html>
