<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>上传附件</title>
<%--	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" >--%>
<%--	<meta http-equiv="pragma" content="no-cache">--%>
<%--	<meta http-equiv="cache-control" content="no-cache">--%>
<%--	<meta http-equiv="expires" content="0">    --%>
<%--	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">--%>
<%--	<meta http-equiv="description" content="This is my page">--%>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
<script type="text/javascript" src="<%=path%>/omui/jquery.js"></script>
<jsp:include page="/common/commonInclude.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/omui/ui/om-core.js"></script>
<script type="text/javascript" src="<%=path%>/omui/ui/om-fileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	  $('#file_upload').omFileUpload({
	    action : '<%=path%>/omFileUpload.hvm?fid='+$("#fid").val(),
	    swf : '<%=path%>/omui/ui/om-fileupload.swf',
	    multi  :true,
	    queueSizeLimit : 5,
	    onComplete : function(event,ID,fileObj,response,data){
	      $('#tt').datagrid('reload');
	    }
	  });
	});
	$(function() {
		//datagrid数据查询参数
		$("#queryBtn").click(function(){
			$("#tt").datagrid("load",{
			   creatername:$("#creatername").val(),
				startDate:$("#startDate").val(),
				endDate:$("#endDate").val(),
				filename:$("#filename").val(),
				fid:$("#fid").val()
			});
		});
		$("#resetBtn").click(function(){
		      $("#creatername,#startDate,#endDate,#filename").val("");
		});
		$('#tt').datagrid({
			//下面是 datagrid的基本 配置信息 
			url:'<%=basePath%>/compliance/investigation!queryInvestFile?fid='+$("#fid").val(),
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
				  {field:'filename',title:'文件名称',width:fixColumnWidth(0.2500)},
				  {field:'creatername',title:'创建人',width:fixColumnWidth(0.2000)},
				  {field:'createtime',title:'创建时间',width:fixColumnWidth(0.2500),
				    formatter:function(val){ return val.replace("T"," "); }
				  },
				  {field:'id',title:'操作',width:fixColumnWidth(0.1500),
					    formatter: function(val,node){
						    return "&nbsp;<a href=\"<%=basePath%>/download/download.action?id="+val+"\" class=\"operation-a\">下载</a>";
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
</script>
</head>
<body>
	<div class="search" style="padding-bottom:20px; border-bottom:1px solid #f2f2f2;*padding:0;"> 
   	     <table style="*width:100%; *background:#fff;* border-bottom:1px solid #f2f2f2;">
    		<tr>
                <td style="*padding:15px;"><input id="file_upload" name="file_upload" type="file" /></td>
                <td style="vertical-align:top;*padding:15px;">
                    <a href="javascript:void(0)" class="but-remove" onclick="$('#file_upload').omFileUpload('upload',0)">上传第一个</a>
                    <a href="javascript:void(0)" class="but-remove" onclick="$('#file_upload').omFileUpload('upload')">上传所有</a>
                    <a href="javascript:void(0)" class="but-remove" onclick="$('#file_upload').omFileUpload('cancel',0)">取消第一个</a>
                    <a href="javascript:void(0)" class="but-remove" onclick="$('#file_upload').omFileUpload('cancel')">取消所有</a>
                    <input type="hidden" id="fid" name="fid" value="<%=request.getParameter("fid")%>"/>
                    <div id="response" style="font-weight: bold;color: red;"></div>
                </td>
            </tr>
        </table>
    </div>
    <div class="search"style="*height:113px;">	
    	<table>
    		<tr>
                <td class="s-t">文件名</td>
                <td><input class="s-text" type="text" id="filename" name="filename" value=""/></td>
                <td class="s-t">创建人</td>
                <td><input class="s-text" type="text" id="creatername" name="creatername" value=""/></td>
                <td class="s-t">日期从</td>
                <td><input type="text" id="startDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
       			class="s-text Wdate" name="startDate" value=""/></td>
				<td class="s-t">到</td>
   				<td><input type="text" id="endDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
   				class="s-text Wdate" name="endDate" value=""/></td>
            </tr>
    	</table>
         <span class="s-but" style="display:inline-block; width:205px;">
        	<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
            <a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
        </span>
	</div>
	<div class="search-list">
	  	<span class="list-title">已上传附件</span>
	    <table id="tt" align="center"></table>
    </div>
</body>
</html>
