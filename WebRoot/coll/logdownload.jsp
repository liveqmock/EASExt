<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>下载日志记录文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
<!--  		<a href="<%=basePath %>person/uplog!test">下载日志文件</a> &nbsp; <br>-->
  		<form action="<%=basePath %>person/uplog!test"  method="post" name="myform"> 
  		选择时间：
			<input id="mydate" name="mydate" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',maxDate: '%y-%M-%d' })"/>
			<input type="submit" name="upload" value="下载Log日志文件">
  		</form>
  </body>
</html>
