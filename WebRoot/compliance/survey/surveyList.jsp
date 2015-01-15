<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>合规初步调查信息管理</title>
    
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
    <table id="abc" width="100%" align="left" onkeyup="bindQuery();">
			<tr>
				<td>客户姓名：<input type="text" id="orgname" value=""></td>
				<td>客户来源：
						<select name="cusSource">
		    				<option value="0">朋友介绍</option>
		    				<option value="1">中介介绍</option>
		    				<option value="2">销售展业</option>
	    				</select>  
	    		</td>
			</tr>
			<tr>
				<td colspan="2" align='center'>
					<a href="javascript:void(0)" id="queryBtn" class="easyui-linkbutton">查询</a>
					<a href="javascript:void(0)" id="resetBtn" class="easyui-linkbutton">清空</a>
				</td>
		   </tr>
		</table>
	<p>&nbsp;</p>
  	 	<table id="dg" title="调查列表"  class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
	  	<thead>
	  		<tr>
	  			<th field="firstname" width="100">客户姓名</th>
	  			<th field="lastname" width="100">身份证号</th>
	  			<th field="phone" width="100">联系方式</th>
	  		    <th field="email" width="100">客户来源</th>
	  		    <th field="status" width="100">状态</th>
	  		    <th field="nextStep" width="100">继续</th>
	  		</tr>
	  	</thead>
	  	<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">继续调查</th>
		    <td>	
  		   		 	<a href="compliance/survey/addSurveyContinue.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">转部门待审批</th>
  		</tr>
  		<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">转部门已审批</th>
		    <td>	
  		    	<a href="compliance/survey/nextStatus.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">转部门待反馈</th>
  		</tr>
  		<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">转部门已反馈</th>
		    <td>	
  		    	<a href="compliance/survey/turnDepartment.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">实地调查待审批</th>
  		    <td>	
  		    	&nbsp;
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">实地调查已审批</th>
  		    <td>	
				<a href="compliance/survey/fieldSurvey.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</td>
  			<td width="50">133333</td>
  			<td width="50">abc</td>
  		    <td width="50">朋友介绍</td>
  		    <td width="50">退回</td>
  		    <td>	
  		    	<a href="compliance/survey/addSurveySendBack.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">再次协助落实待反馈</th>
<!--  		    <td>	-->
<!--				<a href="compliance/survey/fieldSurveyContinue.jsp">继续</a>-->
<!--  		    </td>-->
  		</tr>
  			<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">再次协助落实已反馈</th>
  		    <td>	
				<a href="compliance/survey/fieldSurveyContinue.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">待结案初审</th>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">结案初审未通过</th>
  		    <td>	
				<a href="compliance/survey/fieldSurveySendBack.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">待结案终审</th>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">结案终审未通过</th>
  		     <td>	
				<a href="compliance/survey/fieldSurveyContinue.jsp">继续</a>
  		    </td>
  		</tr>
  		<tr>
  			<td width="50">李四</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		    <td width="50">已结案</th>
  		</tr>
	 </table>
	  <div id="toolbar">
	  		<a href="compliance/survey/addSurvey.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加信息</a>
<!--	  		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>-->
<!--	  		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>-->
     </div>
  </body>
</html>
