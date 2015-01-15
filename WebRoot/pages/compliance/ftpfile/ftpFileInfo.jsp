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
    <title>案件管理</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
  	<script type="text/javascript">
  		$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
			   		startDate:$("#startDate").val(),
			   		endDate:$("#endDate").val(),
			   		filename:$("#filename").val(),
			   		creatername:$("#creatername").val(),
			   		filetype:$("#filetype").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#startDate,#endDate,#filename,#creatername,#filetype").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>ftpfile/ftpfile!queryPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'filename',title:'文件名称',width:fixColumnWidth(0.1100)},
					  {field:'creatername',title:'创建人',width:fixColumnWidth(0.1100)},
					  {field:'filetype',title:'文件类型',width:fixColumnWidth(0.2000)},
					  {field:'creatertime',title:'创建时间',width:fixColumnWidth(0.2000),
					  	 formatter: function(val) { return val.replace("T"," "); }
					   },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1100),
						    formatter: function(val,node){
						   	 	return "<a class=\"operation-a\" href='javascript:void(0)'"
							   	 	+" onclick='openTab(this,\"文件下载\",\"view\","+node.fid+")'>查看</a>&nbsp;"
							   	 	+"<a class=\"operation-a\" href='javascript:void(0)' onclick='downloadFile("+node.fid+")'>下载</a>"
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
		//文件下载
		function downloadFile(id){
			var url = "<%=basePath%>ftpfile/ftpfile!isExistsFile.action?ftpFile.fid="+id;
			var jsonCount = sendAjax(url);
			if(jsonCount=="false"){
				$.messager.alert("提示","文件路径错误");
			}else{
		  		location.href="<%=basePath%>ftpfile/ftpfile.action?ftpFile.fid="+id;
			}
		}
		function openWin(btn,title){
			addTab(title+"_编辑信息", $("#editURL").val());
		}
	</script>
</head>
<body>
	<div class="search">
		<table id="abc" onkeyup="bindQuery();">
			<tr>
				<td class="s-t">创建日期从</td>
				<td><input type="text" id="startDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
					class="s-text Wdate" name="begin" value=""></td>
			    <td class="s-t">至</td>
			    <td><input type="text" id="endDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
			    	class="s-text Wdate" name="end" value=""></td>
				<td class="s-t">文件名称</td>
				<td><input class="s-text" type="text" id="filename" name="filename" value=""></td>
				<td class="s-t">文件类型</td>
				<td><input class="s-text" type="text" id="filetype" name="filetype" value=""></td>
			</tr>
			<tr>
				<td class="s-t">创建人</td>
				<td><input class="s-text" type="text" id="creatername" name="creatername" value=""></td>
				<td colspan="6">
					<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
					<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="search-list">
   		<span class="list-title">文件下载</span>
    	<table id = "tt" toolbar="#toolbar"></table>
    </div>
    <input type="hidden" id="editURL" value="<%=basePath%>pages/compliance/ftpfile/addFtpFile.jsp"/>
	<input type="hidden" id="detailURL" value="<%=basePath%>ftpfile/ftpfile!view?ftpFile.fid="/>
	<div id="toolbar">
 		<a href="javascript:void(0)" id="insertBtn" iconCls="icon-add" 
			class="easyui-linkbutton" onclick="openWin(this,'文件下载');" plain="true">新增</a>
	</div>

</body>
</html>
