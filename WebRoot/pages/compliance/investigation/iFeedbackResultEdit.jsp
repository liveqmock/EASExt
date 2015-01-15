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
	<title>被投诉部门调查处理反馈结果信息</title>
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
		//反馈结果联动
		function feedBackTypeCasecade(){
			var feedBackTypeId = $("#feedBackTypeId").val();
			$(".casecadeWithFeedBackType").hide();
			if(feedBackTypeId == 2 || feedBackTypeId == 3){
				$(".casecadeWithFeedBackType").show();
			}
		}
		//下一步方案联动
		function nextSchemeCasecade(){
			var nextSchemeId = $("#nextSchemeId").val();$("#sepcialEnd").val(false);
			$(".casecadeWithNextScheme0,.casecadeWithNextScheme1,#nextstep1").hide();
			$("#nextstep0").show();
			if(nextSchemeId == 1){
				$("#nextstep1").show(); $("#nextstep0").hide();
			}else if(nextSchemeId == 2){
				$(".casecadeWithNextScheme0,#nextstep0").show(); $("#nextstep1").hide();
			}else if(nextSchemeId == 3){
				$(".casecadeWithNextScheme1,#nextstep1").show(); $(".casecadeWithNextScheme0,#nextstep0").hide();
				$("#sepcialEnd").val(true);
			}
		}
		$(document).ready(function(){
			if($("#returnBack").val()=='true'){
				$("#returnBtn").show();$("#nextstep0,#nextstep1").remove();
				$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
			}
			feedBackTypeCasecade();
			$("#feedBackTypeId").change(function(){//反馈结果下拉列表改变时
				feedBackTypeCasecade();
			})
			nextSchemeCasecade();
			$("#nextSchemeId").change(function(){//反馈结果下拉列表改变时
				nextSchemeCasecade();
			})
			//点击下一步按钮提交表单
			$("#nextStepBtn").click(function(){
				$("#nextStepBtn").attr("disabled","disabled");
				$('#editForm').form('submit',{
					onSubmit: function(){
						if($(this).form('validate') == true){
							$("#editForm").ajaxSubmit({
								dataType:"text",
					 			success:function(json){//json为服务器端返回的信息
									if(toJson(json).success == "true"){
										window.location = $("#nextStepURL").val()+"?investigationId="
											+$("#investigationId").val()+"&sepcialEnd="+$("#sepcialEnd").val()
											+"&feedBackResultId="+toJson(json).feedBackResultId;
									}else{
										$.messager.alert("提示","操作失败！","error",function(){
											closeTab();
										});
									}
					 			}
					 		});	
						}else{$("#nextStepBtn").removeAttr("disabled");}
						return false;
					}
				})
			})
			$("#feedBackTypeId,#nextSchemeId").validatebox({required: true,missingMessage: '必选'});
		})
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form action="compliance/feedbackResult!<s:if test='feedbackResult != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);" style="display: none;"
					class="but-cancel">返回</a>
			</div>
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<input type="hidden" id="id" name="feedbackResult.id" value='<s:property value="feedbackResult.id"/>' />
			<input type="hidden" name="feedbackResult.feedBackId" value='<s:property value="feedBackId"/>' />
			<input type="hidden" name="feedBackId" value='<s:property value="feedBackId"/>' />
			<input type="hidden" id="investigationId" name="investigationId" value='<s:property value="investigationId"/>' />
			<input type="hidden" id="sepcialEnd" value="false"/>
			<div class="title">被投诉部门调查处理反馈结果信息</div>
			<table>
				<tr><td class="t-title" width="100">反馈时间：</td>
				<s:if test="feedbackResult == null">
					<td><input class="t-text" type="text" name="feedbackResult.feedBackTime" readonly="readonly"
						value='<s:date name="feedback.feedBackTime" format="yyyy-MM-dd HH:mm:ss"/>' /></td>
				</s:if><s:else>
					<td><input class="t-text" type="text" 
						value='<s:date name="feedbackResult.feedBackTime" format="yyyy-MM-dd HH:mm:ss"/>' /></td>
				</s:else>
				<td class="t-title" width="100">反馈结果：</td>
			  	<td><select name="feedbackResult.feedBackTypeId" id="feedBackTypeId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="feedBackTypes">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="feedbackResult.feedBackTypeId==key">selected</s:if> ><s:property value="value"/></option>
			  			</s:iterator>
			  		</select></td>
				</tr>	
			  	
			  	<tr class="casecadeWithFeedBackType" style="display: none"><td class="t-title">理由：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" id="reason"
			  		name="feedbackResult.reason"><s:property value="feedbackResult.reason"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title">下一步方案：</td>
			  	<td><select name="feedbackResult.nextSchemeId" id="nextSchemeId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="nextSchemes">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="feedbackResult.nextSchemeId==key">selected</s:if> ><s:property value="value"/></option>
			  			</s:iterator>
			  		</select></td>	
			  	</tr>
			  		
			  	<tr class="casecadeWithNextScheme0" style="display: none;"><td class="t-title">需要补充调查依据：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" id="basisosi"
			  		name="feedbackResult.basisosi"><s:property value="feedbackResult.basisosi"/></textarea></td></tr>
		  			
			  	<tr class="casecadeWithNextScheme0" style="display: none;"><td class="t-title">转出理由：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" id="outReason"
			  		name="feedbackResult.outReason"><s:property value="feedbackResult.outReason"/></textarea></td></tr>
			  		
			  	<tr class="casecadeWithNextScheme1" style="display: none;"><td class="t-title">原因：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" id="specialEndReason"
			  		name="feedbackResult.specialEndReason"><s:property value="feedbackResult.specialEndReason"/></textarea></td></tr>
			</table>
		  	<div class="t-but" style="display:'';" id="nextstep0">
		    	<a id="editBtn" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab('editBtn','合规初步调查')"><s:if test='feedbackResult != null'>修改</s:if><s:else>提交</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	  		</div>
		  	<div class="t-but" style="display:none;" id="nextstep1">
		   		<a id="nextStepBtn" href="javascript:void(0)" class="but-change">下一步</a>
		   		<input id="nextStepURL" type="hidden" value="<%=request.getContextPath()%>/compliance/applysettlement!edit"/>
	  		</div>
		</form>
		</div>
	</body>
</html>
