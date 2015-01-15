<%@ page language="java" import="java.util.*,java.io.File,com.change.eas.util.Config" pageEncoding="utf-8"%>
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
	<title>法律文件列表</title>
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

	function downloadFile(){
			var checkedItems = $('#tt').datagrid('getChecked'); // jquery.easyui.min_1.3.js支持getChecked方法
			if(checkedItems.length==0){
	 		$.messager.alert("提示", "请选择下载项！");
	 	    }else{
		 		var ids = "";
		 		$.each(checkedItems, function(index, item){
		 			ids+=item.id+";";
		 			}); 
		 	 	window.location.href="<%=basePath%>limitedpartner/limitedpartner!downloadFile?ids="+ids;
 	        }
	        };
	
   		$(function() {

			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>limitedpartner/limitedpartner!queryPageDocumentFile',
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
                      {field:'ck',checkbox:true },
					  {field:'filename',title:'法律文件名称',width:fixColumnWidth(0.1600)},
					  {field:'makeuser',title:'制作人',width:fixColumnWidth(0.0600)},
					  {field:'operator',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node) {
						        	return "<span><a class=\"operation-a\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
						   }
			   		  }
				]],
				queryParams: {//传递参数的方法
					pid: '${pid}'
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
</head>
	<body>
		<div class="search-list">
        	<span class="list-title">法律文件列表</span>
        	<br/>
        	<button onclick="downloadFile()">下载</button>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="editURL" value="<%=Config.getKey("word.url")%>/Document.jsp?basePath=<%=basePath%>&pid=<%=request.getParameter("pid")%>&RecordID="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!deleteDocumentFile_Ajax?documentFile.limitedpartnerid=${pid}&documentFile.id="/>
		<input type="hidden" id="message" value="${message }"/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		
	</body>
</html>
