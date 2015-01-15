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
    <title>文件上传下载</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/common/compliance.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
  	<script type="text/javascript">
		//打开新选项卡
function uploadFile(){
	var file = document.getElementById('fileName');
	var aa = getPath(file);
	if(aa==''){
		$.messager.alert("提示","请选择文件","info");	
 	}else{
 		$("#filePath").val(file.value);
 		mask("上传文件，请稍候...");
 		$("#myform").ajaxSubmit({
 			type: "POST",
 			url:"<%=basePath%>/file/uploadfile!upadFile?projectfid="+$('#projectfid').val(),
 			resetForm: false,
 			success:function(json){//文件上传成功后执行,json
 				$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 				unmask(); 
	 				location=location;	
 				});
 	      }
     });	
    return false;//防止刷新
  }
}
	$(function() {
		//datagrid数据查询参数
		$("#queryBtn").click(function(){
			$("#tt").datagrid("load",{
			   creatername:$("#createname").val(),
				startDate:$("#startDate").val(),
				endDate:$("#endDate").val(),
				viewName:$("#viewName").val(),
				projectfid:$("#projectfid").val()
			});
		});
		$("#resetBtn").click(function(){
		      $("#startDate,#endDate,#viewName").val("");
		});
		$('#tt').datagrid({
			//下面是 datagrid的基本 配置信息 
			url:'<%=basePath%>/file/uploadfile!queryUploadFile?projectfid='+$("#projectfid").val(),
			width: ($(window).width()-50),
			height: 'auto',
			nowrap: false,  //  是否在一行显示数据
			striped: true,   //  是否 显示 斑马线  
			fitColumns: false,  // 自动填充 列 宽
			collapsible: true,   // 是否 有滑动效果 
			loadMsg: '数据连接中.....',  //加载页面时候的提示消息
			remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
			sortOrder: 'desc',    // 排序 方法 
			singleSelect:false, 
			columns: [[
				  {field:'VIEWNAME',title:'文件名称',width:fixColumnWidth(0.2500)},
				  {field:'CREATENAME',title:'上传人名称',width:fixColumnWidth(0.2000)},
				  {field:'CREATETIME',title:'上传时间',width:fixColumnWidth(0.2500),
				    formatter:function(val){ return val.replace("T"," "); }
				  },
				  {field:'FID',title:'操作',width:fixColumnWidth(0.1500),
					    formatter: function(val,node){
						    return "&nbsp;<a href=\"javascript:void(0)\"  onclick='downloadFile("+val+")' class=\"operation-a\">下载</a>"+
						    "&nbsp;<a class=\"operation-a\" href='javascript:void(0)' onclick='deleteRecord("+val+")' >删除</a>&nbsp;";
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
	});
	function downloadFile(id){
			var url = "<%=basePath%>/file/douwnfile!isExistsFile.action?fileFid="+id;
			var jsonCount = sendAjax(url);
			if(jsonCount=="false"){
				$.messager.alert("提示","文件下载错误");
			}else{
		  		location.href="<%=basePath%>/file/douwnfile.action?fileFid="+id;
			}
		}
</script>
	</head>
	<body>
		<div class="search">
		<table id="abc" onkeyup="bindQuery();">
    		<tr>
                <td class="s-t">文件名</td>
                <td><input class="s-text" type="text" id="viewName" name="viewName" value=""/></td>
                <td class="s-t">上传日期从</td>
                <td><input type="text" id="startDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
       			class="s-text Wdate" name="startDate" value=""/></td>
				<td class="s-t">到</td>
   				<td><input type="text" id="endDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
   				class="s-text Wdate" name="endDate" value=""/></td>
   				<td> 
   				<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
                <a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
                </td>
            </tr>
    	</table>
        <form action=""  method="post" name="myform" id="myform" enctype="multipart/form-data">
             <table>
            <tr>
               <td class="s-t">选择文件</td>
                   <td colspan="6">
                      <input type="file" id="fileName" name="fileName" />
                      <input id="filePath" name="filePath" type="hidden" />
                      <a href="javascript:void(0)" name="upload" id="upload" plain=true
                       class="but-remove" onClick="uploadFile()">上传</a>
                       <input id="projectfid" name="projectfid" type="hidden" value="${param.projectfid }"/>
           	       </td>
           	  </tr> 
		</table>
		</form>
		</div>
		<div class="search-list">
			<span class="list-title">上传的附件</span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
		<div id="toolbar">
		</div>
			<input type="hidden" id="deleteURL" value="<%=basePath%>/file/uploadfile!deleteFile?fileFid="/>
	</body>
</html>
