<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>合规调查详细信息</title>
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	    <style type="text/css"> table tr td{font-size: 12px;} </style>
	    <style type="text/css">
	      div.item { width:100px; height:40px; background-color: transparent; text-align:center; padding-top:0px;}
	    </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/tooltip/jquery.tooltip.css" type="text/css" />
<%--	    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.min.js"></script>--%>
	    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.tooltip.js"></script>
	    <script type="text/javascript">
	      $j = jQuery.noConflict();
	      $j(document).ready(function(){
	    	  $j("div.item").tooltip();
		  });
	    </script>
	</head>
	<body>
		收拾收拾zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
			zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz
		zzzzzzz<br/>
		<div class="search-list">
	    	<span class="list-title">审批历史记录信息sb</span>
			<table class="easyui-datagrid">
			<thead>
		  		<tr>
					<th field="opinion" width="500">爱我中华</th>
					<th field="opinion2" width="500">北京你好</th>
		  		</tr>
		  		</thead>
				<tr>
					<td>
					 	爱我中华，中华情!
						<div class="item" style="width: 755px">
							<div class="tooltip_description" style="display:none" title="问题">
								亲，遇到什么问题了?????
							</div>
						</div>
					</td>
					<td>
					 	北京，你好啊!
						<div class="item" style="width: 755px">
							<div class="tooltip_description" style="display:none" title="答案">
								亲，遇到什么问题了?????没什么问题啊，你个傻冒23
							</div>
						</div>
					</td>
				</tr>
			</table>
			</div>
	</body>
</html>
