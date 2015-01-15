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
    
    <title>转部门修改处理信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#assistedPerson").validatebox({required: true,validType:'charLength[0,100]',missingMessage: '必填'});
		$("#email").validatebox({required: true,validType:'emailnew',missingMessage: '必填'});
	})
	</script>
	
  </head>
  
  <body>
   <div class="tableForm">
		<form id="editForm" action="compliance/investigation!updateByinvestigation">
		<div class="title">修改邮箱</div>
		<table>
		<input type="hidden" id="id" name="feedbackRequired.investigationId" class="t-text easyui-validatebox"
		value='<s:property value="feedbackRequired.investigationId"/>' />
		<tr><td class="t-title" width="160">原协助负责人：</td>
			  	<td><input class="t-text" type="text" id="oldassistedPerson" class="t-text easyui-validatebox" 
					value='<s:property value="feedbackRequired.assistedPerson"/>'  disabled="disabled"/></td>
				<td class="t-title" width="160">原协助负责人邮箱：</td>
			  	<td><input class="t-text" type="text" id="oldemail" class="t-text easyui-validatebox" 
					value='<s:property value="feedbackRequired.email"/>' disabled="disabled"/></td></tr><br/>
					
				<tr><td class="t-title">新协助负责人：</td>
			  	<td><input class="t-text" type="text" id="assistedPerson" class="t-text easyui-validatebox" name="feedbackRequired.assistedPerson"/></td>
				<td class="t-title">新协助负责人邮箱：</td>
			  	<td><input class="t-text" type="text" id="email" class="t-text easyui-validatebox" name="feedbackRequired.email" /></td></tr>
				</table>	
					<div class="t-but" style="display:'" id="nextstep0">
		    	  <a href="javascript:void(0)" id="editBtn1" class="but-change" onclick="submitTab('editBtn1','合规初步调查');">
			    	提交</a>	 
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	  		</div>
		</form>
		</div>
  </body>
</html>
