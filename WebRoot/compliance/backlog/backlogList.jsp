<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>待办列表</title>
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
	<script type="text/javascript">
		/**
		 * 新的方式
		 * @param url
		 * @return
		 */
		function openWinNew(url){
			$("#iframeSource").attr("src",url);
			$('#iframeWin').window('open');
		}
	</script>
  </head>
  <body>
	 <table id="dg" title="待办信息"  class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
		  	<thead>
		  		<tr>
		  			<th field="firstname" width="50">投诉渠道</th>
		  			<th field="lastname" width="50">投诉时间</th>
		  			<th field="phone" width="50">案件初步分类</th>
		  			<th field="email" width="50">被投诉人</th>
		  			<th field="caseStatus" width="50">案件状态</th>
		  			<th field="oper" width="50">操作</th>
		  		</tr>
		  	</thead>
		  <c:if test="${user.username=='heguijiekouren'||user.username=='heguiadmin'}">
		  	<tr>
	  			<td width="50">电话</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张三</td>
	  		    <td width="50"><font color="red">转部门待反馈</font></td>
	  		     <td width="50"><a href="compliance/backlog/feedback.jsp">反馈</a></td>
	  		</tr>
	  		</c:if>
	  		<c:if test="${user.username=='qiangzhang'||user.username=='heguiadmin'}">
		    <tr>
	  			<td width="50">电话</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张三</td>
	  		    <td width="50"><font color="red">转部门待审批 </font></td>
	  		     <td width="50"><a href="compliance/backlog/caseInfo.jsp">审批</a></td>
	  		</tr>
	  		 <tr>
	  			<td width="50">投诉邮箱</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张五</td>
	  		    <td width="50"><font color="red">实地调查待审批</font></td>
	  		     <td width="50"><a href="compliance/backlog/caseInfo.jsp">审批</a></td>
	  		</tr>
	  		 <tr>
	  			<td width="50">合规邮箱</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张五</td>
	  		    <td width="50"><font color="red">结案待初审</font></td>
	  		     <td width="50"><a href="compliance/backlog/caseInfo.jsp">审批</a></td>
	  		</tr>
	  		</c:if>
	  		<c:if test="${user.username=='ranzhang'||user.username=='heguiadmin'}">
		  		<tr>
		  			<td width="50">投诉邮箱</td>
		  			<td width="50">2013-09-04</td>
		  			<td width="50">欺诈伪冒类</td>
		  		    <td width="50">张五</td>
		  		    <td width="50"><font color="red">结案待终审</font></td>
		  		     <td width="50"><a href="compliance/backlog/caseInfo.jsp">审批</a></td>
		  		</tr>
	  		</c:if>
		 </table>
	   <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
