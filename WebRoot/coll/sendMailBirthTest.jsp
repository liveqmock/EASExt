<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sendMail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript">
		function sendMailTest(id){
			var url = $("#"+id).val();
			$.ajax({url:url,async:false,
				data:{"fromAddress":$("#fromAddress").val(),"toAddress":$("#toAddress").val()},
				cache:false,
				type:"post",
				success:function(json){
					$.messager.alert("提示",toJson(json).success,"info");
				}});
		}
	</script>
  </head>
  <body>
	  	<div align="center">
	   		<a href="regular/regular!sendRegularMailTest.action">转正预警测试</a>
	  	</div>
	  	<div align="center">
	   		<a href="regular/contract!sendContractMailTest.action">合同到期预警测试</a>
	  	</div>
	 	<div align="center">
	   		<a href="person/warn!cellSendImage.action">生日预警邮件测试</a>
	  	</div>
	  	<div align="center">
	  		<a href="yearofwork/yearofwork!sendYearOfWorkMailTest.action">司龄预警邮件测试</a>
	  	</div>
  </body>
</html>
