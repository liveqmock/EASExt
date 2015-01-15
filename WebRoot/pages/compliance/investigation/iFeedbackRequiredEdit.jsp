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
	<title>被投诉部门调查处理反馈要求信息</title>
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
			if($("#returnBack").val()=='true'){
				$("#returnBtn").show();$("#nextstep0").remove();
				$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
			}
<%--			$("#email").bind("change",function(){$(this).val($(this).val()+"@creditease.cn")});--%>
			$("#sendType,#assistedPerson,#assistedPersonOrg").validatebox({validType:'charLength[0,100]'});
<%--		$("#email").validatebox({required: true,validType:'charLength[1,200]',missingMessage: '必填'});--%>
			$("#email").validatebox({required: true,validType:'emailnew',missingMessage: '必填'});
			$("#sendTime,#feedBackTime").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
			.validatebox({required:true,missingMessage: '必填'});
		})
	</script>
	</head>

	<body>
		<div class="tableForm">
		<form action="compliance/feedbackRequired!<s:if test='feedbackRequired != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<input type="hidden" id="id" name="feedbackRequired.id" value='<s:property value="feedbackRequired.id"/>' />
			<input type="hidden" name="feedbackRequired.investigationId" value='<s:property value="investigationId"/>' />
			<input type="hidden" name="investigationId" value='<s:property value="investigationId"/>' />
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);" 
					style="display: none;" class="but-cancel">返回</a>
			</div>
			<div class="title">被投诉部门调查处理反馈要求信息</div>
			<table>
				<tr><td class="t-title" width="100">发出时间：</td>
				<td>
					<input class="t-text" type="text" id="sendTime" name="feedbackRequired.sendTime" 
						value="<s:date name='feedbackRequired.sendTime' format='yyyy-MM-dd HH:mm:ss'/>" > 
		  		</td>
				<td class="t-title" width="100">发送方式：</td>
				<td><input class="t-text" type="text" name="feedbackRequired.sendType" id="sendType"
					value='<s:property value="feedbackRequired.sendType"/>'/>
				</td></tr>	
			  	
			  	<tr><td class="t-title">协助负责人：</td>
			  	<td><input class="t-text" type="text" id="assistedPerson" name="feedbackRequired.assistedPerson" 
					value='<s:property value="feedbackRequired.assistedPerson"/>' /></td>
				<td class="t-title">协助负责人邮箱：</td>
			  	<td><input class="t-text" type="text" id="email" name="feedbackRequired.email" 
					value='<s:property value="feedbackRequired.email"/>' /></td></tr>
			  		
			  	<tr>
				<td class="t-title">协助负责人所属部门：</td>
			  	<td><input class="t-text" type="text" id="assistedPersonOrg" name="feedbackRequired.assistedPersonOrg" 
					value='<s:property value="feedbackRequired.assistedPersonOrg"/>' /></td>
		  		<td class="t-title">要求反馈时间：</td>
			  	<td>
			  		<input class="t-text" type="text" id="feedBackTime" name="feedbackRequired.feedBackTime" 
						value="<s:date name='feedbackRequired.feedBackTime' format='yyyy-MM-dd HH:mm:ss'/>" > 
		  		</td></tr>
		  		
		  		<tr><td class="t-title">协助要求：</td>
			  	<td colspan="3"><textarea id="assistedRequired" rows="4" style="width:580;" 
			  	name="feedbackRequired.assistedRequired"><s:property value="feedbackRequired.assistedRequired"/></textarea></td></tr>
			</table>
			<div class="t-but" style="display:'" id="nextstep0">
		    	   <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','合规初步调查');">
			    	<s:if test='feedbackRequired != null'>修改</s:if><s:else>新增</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	  		</div>
		</form>
		</div>
	</body>
</html>
