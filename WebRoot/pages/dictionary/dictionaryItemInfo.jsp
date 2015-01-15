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
					itemid: $('#id').val(),
					itemname:$('#itemname').val()
					
			        }); 
			})
			$("#resetBtn").click(function(){
			$("#id,#itemname").val("");
			})
			
			var baseid = <%=request.getParameter("baseid")%>
			
			$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>dictionaryItem/dictionaryItemAction!queryPageJson?baseid='+baseid,
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
						  {field:'TYPEID',title:'类型关键字',width:fixColumnWidth(0.02000)},
						  {field:'ITEMID',title:'数据关键字',width:fixColumnWidth(0.02000)},
						  {field:'ITEMNAME',title:'数据值',width:fixColumnWidth(0.01000)},
						  {field:'operation',title:'操作',width:fixColumnWidth(0.01500),
							   formatter: function(val,row) {
							  var result="<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"数据_详细信息\",\"edit\","+row.TID+")'>编辑</a></span>";
							  <c:forEach items="${sessionScope.personrole}" var="personrole" >
						        <c:if test="${personrole == '627'}"> //数据字典管理员		
						        result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.TID+")'>删除</a></span>";
								</c:if>
						        </c:forEach>

									return result;
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
				<td class="s-t">数据关键字</td><td ><input class="s-text" type="text" id="id" value=""></td>
				<td class="s-t">数据值</td><td ><input class="s-text" type="text" id="itemname" value=""></td>
				<td><a href="javascript:void(0)" id="queryBtn" class="but-search" >&nbsp;</a></td>
				<td><a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a></td>
			</tr>
		</table>
</div>
 <div class="search-list">
 <span class="list-title">数据信息</span>
	<table id = "tt" toolbar="#toolbar"></table></div>
	<div id="toolbar">
	<a onclick="openItemTab(this,'数据_详细信息','add','<%=request.getParameter("baseid")%>')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
 	</div>
 	<input type="hidden" id="addURL" value="<%=request.getContextPath()%>/dictionaryItem/dictionaryItemAction!add?dictionaryitem.baseid="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/dictionaryItem/dictionaryItemAction!edit?dictionaryitem.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/dictionaryItem/dictionaryItemAction!delete?dictionaryitem.id="/>
  </body>
</html>