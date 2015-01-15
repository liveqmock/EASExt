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
	<title>合同信息汇总 </title>
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
   			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fcontractname:$("#fcontractname").val(),
					begin:$("#startDate").val(),
					end:$("#endDate").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#fcontractname").val("");
				$("#startDate").val("");
				$("#endDate").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>doc/Dcontractaction!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					 {field:'fcontractnum',title:'合同序号',width:fixColumnWidth(0.1200),align:'center',
					 formatter: function(val) {
				     	if(val.indexOf(".")!=-1){
				      		val= val.substring(0,val.indexOf("."));
				     	}
			      		return val;
					   }
			   		  },
					  {field:'fcontractname',title:'合同/协议名称',width:fixColumnWidth(0.1200)},
					  {field:'ffirstparty',title:'甲方',width:fixColumnWidth(0.1300)},
					  {field:'fclient',title:'乙方',width:fixColumnWidth(0.1300)},
					  {field:'ftransact',title:'办理人',width:fixColumnWidth(0.1000)},
					  {field:'fcontractdate',title:'合同到期时间',width:fixColumnWidth(0.1000),
					   formatter: function(val) {
					  return val.substring(0,val.indexOf("T"));
					   }
					  },
					  {field:'fmailbox',title:'接口人邮箱',width:fixColumnWidth(0.1700),
						  formatter:function(val){if(val==0) val="是"; else if(val==1) val="否"; return val;}},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1300),align:'center',
					   formatter: function(val,node) {
						  return "&nbsp;<a href='javascript:void(0)' class=\"operation-a\" onclick='openTab(this,\"合同列表\",\"edit\","
						  	+node.id+")' >编辑</a></span>"
						  	+"&nbsp;<a href='javascript:void(0)' class=\"operation-d\" onclick='deleteRecord("+node.id+")' >删除</a>"
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
			
	});
   </script>
</head>
<body>
  <div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
			<td class="s-t">合同/协议名称</td>
            <td><input class="s-text" type="text" id="fcontractname" value=""></td>
			<td class="s-t">开始时间</td>
            <td><input class="s-text Wdate" type="text" id="startDate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">结束时间</td>
            <td><input class="s-text Wdate" type="text" id="endDate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td>
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a>
			</td>
		</tr>
		<tr>
        	<form action="<%=basePath %>doc/Dcontractaction!importExcel"  method="post" name="myform" id="myform" enctype="multipart/form-data"> 
                <td class="s-t">选择合同信息</td>
                <td colspan="6">
                    <input type="file" id="excelFile" name="excelFile" />
                    <input id="nn" name="nn" type="hidden" />
                    <a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true
                        class="but-remove" onClick="importExcelFun()">导入</a>
              
           	    </td>
           </form>
        </tr>
    </table>
 </div>
 <div class="search-list">
  	<span class="list-title">合同信息列表</span>
	<table id = "tt" align="center" toolbar="#toolbar"></table>
	<div id="toolbar"><a href="javascript:void(0)" id="insertBtn" iconCls="icon-add" class="easyui-linkbutton" plain="true" 
		onclick="openTab(this,'合同列表','edit');">新增</a></div>
 </div>
 <input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/doc/Dcontractaction!deleteDcontract?id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/doc/Dcontractaction!edit?dcontract.id="/>
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>