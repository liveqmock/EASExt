<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>MIS服务管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/index.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript"> 
		$(function() {
			tabCloseEven();
			$('.cs-navi-tab,#changePwdBtn').click(function() {
				var $this = $(this);
				var href = $this.attr('src');
				var title = $this.text();
				addTab(title, href);
			});
		});
	</script>
</head>
<body class="easyui-layout" >
   <div title="出错了！">
		<img src="<%=request.getContextPath() %>/images/error.png">
	</div>
</body>
<script type="text/javascript">
	function outLogon() {
		location.replace("${pageContext.request.contextPath}/admin/login!exitLogin"); //outLogon即是你所要转的退出登录的Action地址
	}
</script>
</html>
