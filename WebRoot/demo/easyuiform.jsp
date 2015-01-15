<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<script type="text/javascript">
	    $('#ff').form({  
      		  success:function(data){  
          		 $.messager.alert('Info', data, 'info');  
      		  }  
  		});  
	</script>
  </head>
  <body>
   	    <div style="padding:3px 2px;border-bottom:1px solid #ccc">Ajax Form</div>  
	    <form id="ff" action="${pageContext.request.contextPath}/admin/user!test" method="post">  
	        <table>  
	            <tr>  
	                <td>Name:</td>  
	                <td><input name="name" type="text"></input></td>  
	            </tr>  
	            <tr>  
	                <td>Email:</td>  
	                <td><input name="email" type="text"></input></td>  
	            </tr>  
	            <tr>  
	                <td>Phone:</td>  
	                <td><input name="phone" type="text"></input></td>  
	            </tr>  
	            <tr>  
	                <td></td>  
	                <td><input type="submit" value="Submit"></input></td>  
	            </tr>  
	        </table>  
	    </form>  
  </body>
</html>
