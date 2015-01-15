<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新入职员工信息</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
	$(document).ready(function(){
		$("#fname,#fnumber").validatebox({validType: 'speci'});
	})
		$(function() {
			$("#queryBtn").click(function(){
				//页面验证通过之后，验证开始时间小于结束时间
				var startTimeValid = $("#creattime").val();//开始时间
				var endTimeValid = $("#endtime").val();//结束时间
				if(startTimeValid!="" && endTimeValid!=""){
					if(startTimeValid>endTimeValid){
						$.messager.alert("提示","开始时间不能大于结束时间");
								return;
							}
						}
				
				$('#tt').datagrid('load',{  
					fname: $('#fname').val(),
					fnumber:$('#fnumber').val(),
					creattime:$('#creattime').val(),
					datatype:$('#datatype').val(),
					endtime:$('#endtime').val()
					
			        }); 
			})

			$("#resetBtn").click(function(){
			$("#fname,#fnumber,#creattime,#endtime,#datatype").val("");
			})
			$("#exportExcel").click(function(){
				location.href="<%=basePath%>email/personInfoAction!expExcel?fname="+$('#fname').val()+"&fnumber="+$('#fnumber').val()+"&creattime="+$('#creattime').val()+"&endtime="+$('#endtime').val()+"&datatype="+$('#datatype').val();


					})
			
				$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>email/personInfoAction!queryPageJson',
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
					rownumbers:true,
					singleSelect:false, 
					columns: [[
						  {field:'FNUMBER',title:'员工编号',width:fixColumnWidth(0.02000)},
						  {field:'FNAME',title:'姓名',width:fixColumnWidth(0.01000)},
						  {field:'POSITION',title:'职位',width:fixColumnWidth(0.0150)},
						  {field:'FDISPLAYNAME',title:'组织',width:fixColumnWidth(0.05500)},
						  {field:'EMAIL',title:'邮箱',width:fixColumnWidth(0.03000)},
						  {field:'CREATTIME',title:'创建日期',width:fixColumnWidth(0.01500)},
						  {field:'operation',title:'操作',width:fixColumnWidth(0.01500),
							   formatter: function(val,row) {
							   		return "<a class=\"operation-a\" href='javascript:void(0)' onclick='sendMessage("+row.FNUMBER+")' >发短信</a>";
							   }
				   		  }
						  
					]],
					//下面 定义 分页配置 ：
					pageSize:15,
					pageList:[5,10,15,20],
					pagination:true,   // 是否 要分页 
					pageNumber:1//设置初始页为1
					//onLoadSuccess: omitLongData()
				});
				//下面这个方法 用于 配置  分页的信息 
				displayMsg($('#tt'));

				 // if('${sessionScope.personrole}'==""){
				//	  $("#exportExcel").css("display","none");//只有付兴能看到导出按钮
				//	  }
				//  if('${sessionScope.personrole}'=='302'){
				//	  $('#tt').datagrid('hideColumn','operation');//付兴不能发短信
				//	  }
				<c:forEach items="${sessionScope.personrole}" var="personrole" >
              		 <c:if test="${personrole == '302'}">
             			$('#tt').datagrid('hideColumn','operation');//付兴不能发短信
             		</c:if>
            	 </c:forEach>

				});
	</script>

  </head>
  <body>
 <div class="search">
		<table onKeyUp="bindQuery();">
			<tr>
				<td class="s-t">编码</td><td ><input class="s-text" type="text" id="fnumber" value=""></td>
				<td class="s-t">姓名</td><td ><input class="s-text" type="text" id="fname" value=""></td>
				<td class="s-t">数据类型</td>
				<td>
					<select id="datatype">
						 <option value="">--请选择--</option>
 						 <option value ="0">系统生成</option>
 				 		 <option value ="1">手动维护</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="s-t">开始日期</td><td ><input class="s-text Wdate" type="text" id="creattime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"/></td>
				<td class="s-t">结束日期</td><td ><input class="s-text Wdate" type="text" id="endtime" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"/></td>
				<td colspan=2>
					<a href="javascript:void(0)" id="queryBtn" class="but-search" >&nbsp;</a>
					<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
					<a href="javascript:void(0)" name="exportExcel" id="exportExcel" class="but-remove">导出</a>
				</td>
			</tr>
		</table>
</div>
 <div class="search-list">
 <span class="list-title">新入职员工信息</span>
		<table id="tt"></table>
		<input type="hidden" id="sendmessage" value="<%=request.getContextPath()%>/email/personInfoAction!sendMessage?FNUMBER="/>
</div>
  </body>
</html>