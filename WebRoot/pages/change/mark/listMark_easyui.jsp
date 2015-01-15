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
	<title>金蝶系统有限合伙公司</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
 	<script type="text/javascript">
   		$(function() {

   		     $("#queryBtn").click(function(){
 				$('#tt').datagrid('load',{  // 向服务器传递的参数
 					bookmarkname: $("#bookmarkname").val(),
 					isloop:$("#isloop").val()
 			        }); 
 			});

 			$("#resetBtn").click(function(){
 				$("#bookmarkname").val("");
 				$("#isloop").val("-1");
 			});
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>limitedpartner/limitedpartner!queryMarkPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				columnOption: true,
				rownumbers: true,
				singleSelect: false,
				selectOnCheck: true,
				checkOnSelect: true,
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'bookmarkname',title:'标签名称',width:fixColumnWidth(0.1600)},
					  {field:'bookmarkdesc',title:'标签解释',width:fixColumnWidth(0.1600)},
					  {field:'bookmarktext',title:'标签备注',width:fixColumnWidth(0.1600)},
					  {field:'tablefield',title:'标签字段',width:fixColumnWidth(0.1600)},
					  {field:'loopstring',title:'标签类型',width:fixColumnWidth(0.1600)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1600),
						  formatter: function(val,node) {
						  //return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"有限合伙人\",\"edit\","
					   		//+node.bookmarkid+")' >编辑</a>&nbsp;</span>"
						        if(node.isloop>=100 || node.isloop<=0)
					        	return "<a class=\"operation-a\" href='javascript:void(0)' onclick='deleteRecord("+node.bookmarkid+")' >删除</a>"
					   }
						  }
				]],
				queryParams: {//传递参数的方法
					//fname_l2: '${fname_l2}'
				},
				//下面 定义 分页配置 ：
				pageSize:5,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
				//onLoadSuccess: omitLongData()
				onLoadSuccess: omitLong()
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
   </script>
	<body>
        <div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
            <td>标签名称 </td>
            <td>
           <input type="text" name="bookmarkname"  id="bookmarkname" value="" />
            </td>
            <td>标签类型</td>
            <td>
              <select name="isloop" id="isloop" class="select" style="width: 120px;" >
						<option value="-1" selected="selected">全部标签</option>
						<option value="1000">普通标签</option>
						<!-- <option value="100">表格定位标签</option>
						<option value="101">表格循环标签</option>
						<option value="102">表格普通标签</option>
						<option value="103">表格表头标签</option> -->
						<option value="1001">循环标签</option>
						<option value="1002">不循环标记</option>
						<option value="1003">时间标签</option>
						<option value="1004">循环显示字段标签</option>
						<option value="1005">特殊标签</option>
						 
					</select>
            </td>
            <td>
                <a href="javascript:void(0)" id="queryBtn" class="but-search"></a>
            </td>
            <td>
		      	<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
		    </td>
		    <td>
		      	<a href="javascript:void(0)" class="but-remove" onclick="addMark()">添加标签</a>
		    </td>
		  </tr>
	 </table>
  </div>
        <div class="search-list">
        	<span class="list-title">标签列表</span>
        	<br/>
            <table id="tt" toolbar="#toolbar"></table></div>
        <input type="hidden" id="editURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!editMark?mark.bookmarkid="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!delMark_Ajax?mark.bookmarkid="/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
        <hr/>
        </body>
       <SCRIPT type="text/javascript">
       function addMark(){
    	   window.location.href="<%=basePath%>limitedpartner/limitedpartner!addMarkPage";
		        };

          
       </SCRIPT>
	
</html>
