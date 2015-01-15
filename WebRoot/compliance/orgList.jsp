<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择被投诉人的组织信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
  </head>
  <body>
  	<table id="dg" title="My Users" class="easyui-datagrid" style="width:1200px;height:450px" url="get_users.php"
  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
	  	<thead>
	  		<tr>
	  			<th field="firstname" width="50">客户姓名</th>
	  			<th field="lastname" width="50">身份证号</th>
	  			<th field="phone" width="50">联系方式</th>
	  		    <th field="email" width="50">客户来源</th>
	  		</tr>
	  	</thead>
	  	<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		</tr>
	 </table>
	  <div id="toolbar">
	  		<a href="compliance/addComplaint.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加投诉信息</a>
	  		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
	  		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
     </div>
  </body>
</html>
