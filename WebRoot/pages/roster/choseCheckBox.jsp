<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>复选信息 </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#itemname").validatebox({validType: 'speci'});
	})
			var typeid = '<%=request.getParameter("typeid") %>';
			var inputid = '<%=request.getParameter("inputid") %>';
			var inputvalue = '<%=request.getParameter("inputvalue") %>';

			//关闭页面	
			function closedialog(){
   	   		this.parent.$("#iframeWin").window('close');
   	   		}
   	   	 			
   	   	function submitInfo(){
   	  	 	var fids="";
   	  	 	var checktext = "";
   	  	 	//获取复选框id拼接值
			for(var i=0;i<checkedItems.length;i++){
		 	  fids = fids + "," + checkedItems[i];
		  		}
	  		//获取复选框文本值拼接值
			var row = $('#stt').datagrid('getChecked');
			for(var j=0;j<row.length;j++){
				checktext = checktext + "," + row[j].ITEMNAME;
				}
			
	  		if(fids != "" && fids != null && checktext != "" && checktext !=null  ){
				fids = fids.substring(1);
				checktext = checktext.substring(1);
		  		}               
  	   		parent.setCheckBoxValue(inputid,fids,checktext);
  	   		doCancel();	
  	   	 	}
   var checkedItems = [];
  $(function() {
	  $("#queryBtn").click(function(){
			$('#stt').datagrid('load',{  
				itemname: $('#itemname').val()
		        }); 
		})
		$("#resetBtn").click(function(){
		$("#itemname").val("");
		})
			  
   	 $('#stt').datagrid({
			//下面是 datagrid的基本 配置信息 
			url:'<%=basePath%>roster/rosterAction!queryDictionaryData?typeid='+typeid,
			title: "",
			width: ($(window).width()-40),
			scrollbarSize:0,
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
			autoRowHeight:false,

			columns: [[
					{field:'ck',checkbox:true},
				  {field:'ITEMNAME',title:'数据值',width:fixColumnWidth(0.04000),align:'center'}
			
			]],
			//下面 定义 分页配置 ：
			pageSize:15,
			pageList:[5,10,15,20],
			pagination:true,   // 是否 要分页 
			pageNumber:1//设置初始页为1
			//onLoadSuccess: omitLongData()
		});
		$("#stt").datagrid({ 
			idField: 'TID', 
			view: fileview 
				 	});
		    $("#stt").datagrid({ 
			onCheckAll:function(){
		    addcheckItem();
				},
		    onCheck:function(){
			addcheckItem();
				},
		    onUncheckAll:function(rows){
		     //var rows = $('#tt').datagrid('getCheckbox');
		     // alert(rows[0].fid);
			removeAllItem(rows);
				},
		    onUncheck:function(rowIndex, rowData){
		    removeSingleItem(rowIndex, rowData);
				    		}
				 		});	
 	var fileview = $.extend({}, $.fn.datagrid.defaults.view, { onAfterRender: function (target) { ischeckItem(); } });
	});

	function ischeckItem() {
	for (var i = 0; i < checkedItems.length; i++) {
 	  $('#stt').datagrid('selectRecord', checkedItems[i]); //根据id选中行 
   
	}
	}
	function addcheckItem() {
	var row = $('#stt').datagrid('getChecked');
	for (var i = 0; i < row.length; i++) {
   	 if (findCheckedItem(row[i].TID) == -1) {
        checkedItems.push(row[i].TID);
    	}
	}
	}
	function findCheckedItem(ID) {
	for (var i = 0; i < checkedItems.length; i++) {
    	if (checkedItems[i] == ID)
    	 return i;
	}
	return -1;
	}
	function removeAllItem(rows) {
	for (var i = 0; i < rows.length; i++) {
  	 var k = findCheckedItem(rows[i].TID);
  	 if (k != -1) {
        checkedItems.splice(k, 1);
   	}
	}
	}
	function removeSingleItem(rowIndex, rowData) {
	var k = findCheckedItem(rowData.TID);
	if (k != -1) {
    checkedItems.splice(k, 1);
	}
	}

	</script>
</head>
<body>
   <div class="search" style="margin-bottom:17px;margin-top:17px;min-width:590px;">
		<table onKeyUp="bindQuery();">
			<tr>
				<td class="s-t">数据值</td><td ><input class="s-text" type="text" id="itemname" value=""></td>
				<td>
				<a href="javascript:void(0)" id="queryBtn" class="but-search" >&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
				</td>
			</tr>
		</table>
 </div>

  <div class="search-list">
	<table id = "stt"></table>
 </div>
<div class="t-but" style="float: right;margin-right:25px;">
<a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitInfo()">保存</a>	
<a href="javascript:void(0)" class="but-change" onclick="closedialog()">取消</a>
</div>
</body>
</html>