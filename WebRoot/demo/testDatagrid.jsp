<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'testDatagrid.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
    <style type="text/css">
      div.item { width:100px;background-color: transparent; text-align:center; padding-top:0px;}
    </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/tooltip/jquery.tooltip.css" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.tooltip.js"></script>
  </head>
  
  <body>
   		 <table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fdeptname" width="273">部门名称</th>
						<th field="fcityname" width="273">城市名称</th>
						<th field="fsaledepart" width="372">营业部名称</th>
			  		</tr>
			  	</thead>
					<tr>
						<td>a</td>
						<td>b</td>
						<td>c</td>
					</tr>
			</table>
		test..........................................................................
		 <table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fdeptname" width="273">部门名称</th>
						<th field="fcityname" width="273">城市名称</th>
						<th field="fsaledepart" width="372">营业部名称</th>
			  		</tr>
			  	</thead>
					<tr>
						<td>a</td>
						<td>的</td>
						<td>c</td>
					</tr>
			</table>
  </body>
</html>
