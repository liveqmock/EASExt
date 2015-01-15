<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
		<base href="<%=basePath%>">
		<title>删除数据字典缓存</title>
    	 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/record_jsp_style.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript">
  
  /**
	 * 删除数据字典中的缓存数据
 	* @param id记录id
 	* @return
 	*/
function delectCache(){
	$.messager.confirm('警告', '确定需要删除此信息吗？', function(r){
		if(r){
			$.ajax({
				 url: "<%=request.getContextPath()%>/dictionary/dictionaryBaseAction!deleteCacheData",
				 async: false,
				 success:function(){
					$.messager.alert("提示","操作成功！");
 				}
			});
		}
	});
}
  
  </script>
	
	</head>
	
  <body>
   <div class="tableForm">
     	<div class="title">操作页面</div>
		<table id="viewTab">
		<tr>
		<td> <a class="operation-a" href="javascript:void(0)" onclick="delectCache()">删除字典缓存数据</a></td>
		</tr>
		
		</table>
		
	 </div>
          
  </body>
</html>