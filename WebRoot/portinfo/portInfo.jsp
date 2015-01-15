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
	<title>接口人信息汇总 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/dategridCommon.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab_common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#orgname,#lastcostcenter,#city,#officeadd").validatebox({validType: 'speci'});
	})
		$(function() {
   			var userTypeId=$("#userTypeId").val(); 
   			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					orgname:$("#orgname").val(),
					city:$("#city").val(),
					officeadd:$("#officeadd").val(),
					lastcostcenter:$("#lastcostcenter").val(),
					isport:$("#isport").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#orgname,#city,#officeadd,#lastcostcenter,#isport").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>iperson/interfacePersonList!queryPageJson',
				width: ($(window).width()-50),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					 {field:'orgname',title:'使用部门',width:fixColumnWidth(0.1600)},
					  {field:'city',title:'地区',width:fixColumnWidth(0.0600)},
					  {field:'officeadd',title:'办公室座落地点',width:fixColumnWidth(0.2000)},
					  {field:'lastcostcenter',title:'成本中心',width:fixColumnWidth(0.1800)},
					  {field:'portmail',title:'接口人邮箱',width:fixColumnWidth(0.1500)},
					  {field:'isport',title:'总接口人',width:fixColumnWidth(0.0500),
						  formatter:function(val){if(val==0) val="是"; else if(val==1) val="否"; return val;}},
					  {field:'creatortime',title:'创建时间',width:fixColumnWidth(0.1200),
					  	formatter:function(val){
					 		return val.substring(0,val.lastIndexOf('T'));
					  	}
					  },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1400),
					   formatter: function(val,node) {
						  var id=node.id; var orgDiffer = node.ext1;
						  return handleOperationPurview(this,"接口人信息列表",id,userTypeId,orgDiffer,"portInfo");
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
			
		//目前暂时控制只有谢允儿和于静有导入权限
		if(!(userTypeId==4 || userTypeId==8)) $("#myform").css("display","none");
		showMessage(new Date('2013','09','16'),'信息',
				'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈！',5000,'slide'); 
	});
   </script>
</head>
<body>
<div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
			<td class="s-t">部门总接口人</td>
            <td>
				<select id="isport" onChange="changeQyery();">
				<option value="">--全部--</option> <option value="0">是</option> <option value="1">否</option></select> 
			</td>
        	<td class="s-t">使用部门</td>
            <td><input class="s-text" type="text" id="orgname" value=""></td>
			<td class="s-t">地区</td>
            <td><input class="s-text" type="text" id="city" value=""></td>
			<td class="s-t">办公室地址</td>
            <td><input class="s-text" type="text" id="officeadd" value=""></td>
        </tr>
		<tr>
        	<td class="s-t">成本中心</td>
            <td><input class="s-text" type="text" id="lastcostcenter" value=""></td>
			<td colspan="6">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a>
			</td>
        </tr>
	</table>
	<form action="<%=basePath %>/iperson/interfacePersonList!importExcel"  method="post" name="myform" 
		id="myform" enctype="multipart/form-data"> 
		<table>
            <tr>
	             <td class="s-t">选择合同信息</td>
	             <td colspan='3'>   
					<input type="file" id="excelFile" name="excelFile"/>
					<input id="nn" name="nn" type="hidden"/>
					<a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true
						class="but-remove" onClick="importExcelFun()">导入</a>
				</td>
			 </tr> 
		</table>
	</form>
 </div>
 <div class="search-list">
  	<span class="list-title">接口人信息列表</span>
	<table id = "tt" align="center"></table>
</div>
	<input type="hidden" id="userTypeId" 
		value="<c:if test='${empty user.typeid}'>0</c:if><c:if test='${not empty user.typeid}'>${user.typeid}</c:if>"/>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/iperson/interfacePersonList!view?portInfo.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/iperson/interfacePersonList!edit?portInfo.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/iperson/interfacePersonList!delete?portInfo.id="/>
<!--弹出框-->
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>