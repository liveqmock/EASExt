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
	<title>合规初步调查审批</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("input[name='auditResult.isAgree']").click(function(){
				if($(this).val()==0){$("#auditPass").show();$("#auditNotPass").hide();}
				else if($(this).val()==1){$("#auditNotPass").show();$("#auditPass").hide();}
			})
			if($("#auditTypeId").val()==5){
				$("input[name='auditResult.isAgree']").eq(1).attr("checked","checked");
				$("input[name='auditResult.isAgree']").eq(0).attr("disabled","disabled");
				$("#auditNotPass").show();$("#auditPass").hide();
			}
			$("#auditOpinion").validatebox({validType:'charLength[0,2000]'});
		})
	</script>
	</head>

	<body>
		<div class="tableForm">
		<form action="compliance/auditResult!<s:if test='auditResult != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<input type="hidden" id="id" name="auditResult.id" value='<s:property value="auditResult.id"/>' />
			<input type="hidden" id="auditTypeId" name="auditResult.auditTypeId" value='<s:property value="auditTypeId"/>' />
			<input type="hidden" id="applysettlementId" name="auditResult.applysettlementId" value='<s:property value="applysettlementId"/>' />
			<input type="hidden" id="investigationId" name="auditResult.investigationId" value='<s:property value="investigationId"/>' />
		 	<div class="title">合规初步调查审批编辑</div>
			<table>
				<tr><td class="t-title" width="100">是否同意：</td>
				<td><input type="radio" name="auditResult.isAgree" value="0" checked="checked"/>是 
				<input type="radio" name="auditResult.isAgree" value="1"/>否</td></tr>	
			  	
			  	<tr><td class="t-title">审批意见：</td>
			  	<td><textarea id="auditOpinion" rows="4" style="width:580;" 
			  		name="auditResult.opinion"><s:property value="auditResult.opinion"/></textarea></td></tr>
			</table>
			<div class="t-but">
			    <a id="auditPass" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab('auditPass','待办列表');">审批通过</a>	
	  		</div>
			<div class="t-but">
			    <a id="auditNotPass" style="display: none;" href="javascript:void(0)" 
			    	class="but-cancel" onclick="submitTab('auditNotPass','待办列表');">退回</a>	
	  		</div>
		</form>
		</div>
	</body>
</html>
