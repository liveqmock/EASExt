<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
		$(function() {
			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  
					copypeoplename: $('#copypeoplename').val(),
					copypeoplemail:$('#copypeoplemail').val()
					
			        }); 
			})
			$("#resetBtn").click(function(){
			$("#copypeoplename,#copypeoplemail").val("");
			})
			
			var fnumber = '<%=request.getParameter("fnumber")%>';
			
			$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>/person/orgManager!querycopypeopleJson?fnumber='+fnumber,
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
						  {field:'FPERSONNAME',title:'抄送人姓名',width:fixColumnWidth(0.02000)},
						  {field:'FPERSONEMAIL',title:'抄送人邮箱',width:fixColumnWidth(0.02000)},
						  {field:'operation',title:'操作',width:fixColumnWidth(0.01500),
							   formatter: function(val,row) {
							   		return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"抄送人信息_详细信息\",\"edit\","+row.FID+")'>编辑</a></span>"+
								   	  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.FID+")'>删除</a></span>";
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

				});
	</script>

  </head>
  <body>
 <div class="search">
		<table onKeyUp="bindQuery();">
			<tr>
				<td class="s-t">姓名</td><td ><input class="s-text" type="text" id="copypeoplename" value=""></td>
				<td class="s-t">邮箱</td><td ><input class="s-text" type="text" id="copypeoplemail" value=""></td>
				<td><a href="javascript:void(0)" id="queryBtn" class="but-search" >&nbsp;</a></td>
				<td><a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a></td>
			</tr>
		</table>
</div>
 <div class="search-list">
 <span class="list-title">抄送人信息</span>
	<table id = "tt" toolbar="#toolbar"></table></div>
	<div id="toolbar">
	<a onclick="openTab(this,'抄送人信息_详细信息','edit')" id="addbut" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
 	</div>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/person/orgManager!editCopyPeo?fid="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/person/orgManager!deleteCopyPeo?fid="/>
  </body>
</html>