<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<title>房屋合同预警邮件 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
   		$(function() {
   			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					orgname:$("#orgname").val(),
					city:$("#city").val(),
					officeadd:$("#officeadd").val(),
					lastcostcenter:$("#lastcostcenter").val(),
					portmail:$("#portmail").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#orgname,#city,#officeadd,#lastcostcenter,#portmail").val("");
			})

			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>sendPortInfo/sendPortInfo!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
				 	  {field:'orgname',title:'使用部门',width:fixColumnWidth(0.1000)},
					  {field:'city',title:'地区',width:fixColumnWidth(0.0700)},
					  {field:'officeadd',title:'办公室座落地点',width:fixColumnWidth(0.1800)},
					  {field:'lastcostcenter',title:'成本中心',width:fixColumnWidth(0.1000)},
					  {field:'portmail',title:'收件人邮箱',width:fixColumnWidth(0.1500)},
<%--					  {field:'content',title:'邮件内容',width:fixColumnWidth(0.1800)},--%>
					  {field:'contentfile',title:'附件',width:fixColumnWidth(0.2000)},
					  {field:'ext1',title:'状态',width:fixColumnWidth(0.0500)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.0700),
			   		  	formatter: function(val,node) {
				   			return "<span><a  class=\"operation-a\" href='javascript:void(0)' class=\"operation-a\""+
				   				" onclick='openTab(this,\"房屋合同预警邮件\",\"view\","+node.id+")' >查看</a>&nbsp;</span>";
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
			showMessage(new Date('2013','09','16'),'信息',
					'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈！',5000,'slide');
		});
   </script>
</head>
<body>
 <div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
        	<td class="s-t">使用部门</td>
            <td><input class="s-text" type="text" id="orgname" value=""></td>
			<td class="s-t">地区</td>
            <td><input class="s-text" type="text" id="city" value=""></td>
			<td class="s-t">办公室座落地点</td>
            <td><input class="s-text" type="text" id="officeadd" value=""></td>
        	<td class="s-t">成本中心</td>
            <td><input class="s-text" type="text" id="lastcostcenter" value=""></td>
        </tr>
		<tr>
			<td class="s-t">收件人邮箱</td>
            <td><input class="s-text" type="text" id="portmail" value=""></td>
			<td colspan="6">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a>
			</td></tr>
	</table>
 </div>
 <div class="search-list">
  	<span class="list-title">房屋合同预警邮件</span>
	<table id = "tt"></table>
 </div>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/sendPortInfo/sendPortInfo!view?sendPortInfo.id="/>
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>