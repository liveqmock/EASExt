<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'coll.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
  </head>
  <body>
  		<p align="center">
	    	<a href="oa/sendMessageTest.jsp">OA推送接口的跳转页面</a>
	    	<br/>
	    	<a href="coll/sendMailTest.jsp">预警发送邮件</a><br/>
	    	<a href="coll/sendMailFushTubeTest.jsp">预警发送邮件信管中心</a><br/>
	    	<a href="configInfo/configInfoList.jsp">定时任务启动，停止配置页面</a><br/>
	    	<a href="tomcatlog.jsp">tomcat日志下载</a><br/>
	    	<a href="logdownload.jsp">预警日志下载</a><br/>
	    	<a href="configInfo/sendMailTest.jsp">创建企业邮箱</a><br/>
	    	<a href="coll/handPushInfoToRP.jsp">向制度平台手动推送数据</a>
  		</p>
  </body>
</html>
