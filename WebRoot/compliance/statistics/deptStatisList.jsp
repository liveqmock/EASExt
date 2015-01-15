<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门统计</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
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
		<table id="queryTab" width="100%" align="left">
			<tr>
				<td>使用部门：<input type="text" id="orgname" value=""></td>
				<td>
					<a href="javascript:void(0)" id="queryBtn" class="easyui-linkbutton">查询</a>
					<a href="javascript:void(0)" id="resetBtn" class="easyui-linkbutton">清空</a>
				</td>
			</tr>
		</table>
		  <p>&nbsp;</p>
	  	<table id="dg" title="统计信息" class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
		  	<thead>
		  		<tr>
		  			<th field="firstname" width="50">部门名称</th>
		  			<th field="lastname" width="50">案件数量</th>
		  		</tr>
		  	</thead>
	  		<tr>
	  			<td width="50">合规部门</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td width="50">技术部</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">10</a>
	  			</td>
	  		</tr>
		 </table>
	   <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:460px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
