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
	<title>反馈信息</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#feedBackTime").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
			.validatebox({required:true,missingMessage: '必填'});	
		})
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form action="compliance/feedback!<s:if test='feedback != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<input type="hidden" id="id" name="feedback.id" value='<s:property value="feedback.id"/>' />
			<input type="hidden" name="feedback.feedBackRequiredId" value='<s:property value="feedbackRequiredId"/>' />
			<input type="hidden" name="feedback.fieldsurveyId" value='<s:property value="fieldsurveyId"/>' />
			<input type="hidden" name="feedbackRequiredId" value='<s:property value="feedbackRequiredId"/>' />
			<input type="hidden" name="investigationId" value='<s:property value="investigationId"/>' />
			<div class="title">反馈信息</div>
			<table>
				<tr><td class="t-title" width="100">反馈时间：</td>
				<td>
					<input class="t-text" type="text" id="feedBackTime" name="feedback.feedBackTime" 
						value="<s:date name='feedback.feedBackTime' format='yyyy-MM-dd HH:mm:ss'/>" >
				</td>
				</tr>	
			  	
			  	<tr><td class="t-title" width="100">反馈结果：</td>
			  	<td colspan="3"><textarea id="feedBackResult" rows="4" style="width:580;" 
			  		name="feedback.feedBackResult"><s:property value="feedback.feedBackResult"/></textarea></td></tr>
			</table>
			<div class="t-but" style="display:'';" id="nextstep0">  		
		    	<a id="editBtn" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab('editBtn','待办列表')"><s:if test='feedback != null'>修改</s:if><s:else>新增</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
			    
	  		</div>
		</form>
		</div>
	</body>
</html>
