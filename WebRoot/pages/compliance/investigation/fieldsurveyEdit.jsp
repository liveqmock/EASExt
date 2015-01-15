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
	<title>本部门实地调查信息</title>
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
		function nextSchemeCasecade(){
			var xzbmfbResultValue = $("#xzbmfbResultId").val();
			$("#nextstep0").show();	$("#nextstep1").hide(); $(".casecadeWithXzbmfbResult").hide();
			$("#email,#xzlsbmTime,#xzbmfbTime").removeClass();
			if(xzbmfbResultValue == 1){
				$("#nextstep1").show();$("#nextstep0").hide(); $(".casecadeWithXzbmfbResult").hide()
			}else if(xzbmfbResultValue==2){
				$("#nextstep0").show();$("#nextstep1").hide(); $(".casecadeWithXzbmfbResult").show()
				$("#email").bind("change",function(){$(this).val($(this).val()+"@creditease.cn")});
				$("#email").addClass("t-text").validatebox({required: true,validType:'charLength[1,200]',missingMessage: '必填'});
				$("#xzlsbmTime,#xzbmfbTime").addClass("t-text easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
				.validatebox({required:true,missingMessage: '必填'});
			}
		}
		$(document).ready(function(){
			if($("#returnBack").val()=='true'){
				$("#returnBtn").show();$("#nextstep0,#nextstep1").remove();
				$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
			}
			nextSchemeCasecade();
			$("#xzbmfbResultId").change(function(){//下一步方案下拉列表改变时
				nextSchemeCasecade();
			})
			$("#nextStepBtn").click(function(){
				$("#nextStepBtn").attr("disabled","disabled");
				$('#editForm').form('submit',{
					onSubmit: function(){
						if($(this).form('validate') == true){
							$("#editForm").ajaxSubmit({
								dataType:"text",
					 			success:function(json){//json为服务器端返回的信息
									if(toJson(json).success == "true"){
										window.location = $("#nextStepURL").val()+"?investigationId="+$("#investigationId").val()
											+"&fieldSurveyId="+toJson(json).fieldSurveyId;
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
			$("#suveyPersons").validatebox({required: true,validType:'charLength[1,500]',missingMessage: '必填'});
			$("#suveyChargeMan").validatebox({required: true,validType:'charLength[1,50]',missingMessage: '必填'});
			$("#xzbmfbResultId").validatebox({required: true,missingMessage: '必选'});
			$("#xzlsbm").validatebox({validType:'charLength[0,100]'});
			$("#investigated").validatebox({validType:'charLength[0,200]'});
			$("#xzlsbmChargeMan").validatebox({validType:'charLength[0,50]'});
			$("#fieldsurveyTime").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
			.validatebox({required:true,missingMessage: '必填'});
		})
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form action="compliance/fieldsurvey!<s:if test='fieldsurvey != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);"
					style="display: none;" class="but-cancel">返回</a>
			</div>
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<input type="hidden" id="id" name="fieldsurvey.id" value='<s:property value="fieldsurvey.id"/>' />
			<input type="hidden" name="fieldsurvey.investigationId" value='<s:property value="investigationId"/>' />
			<input type="hidden" id="investigationId" name="investigationId" value='<s:property value="investigationId"/>' />
			<div class="title">本部门实地调查信息</div>
			<table>
				<tr><td class="t-title" width="100">实际调查时间：</td>
				<td>
					<input class="t-text" type="text" id="fieldsurveyTime" name="fieldsurvey.fieldsurveyTime" 
						value="<s:date name='fieldsurvey.fieldsurveyTime' format='yyyy-MM-dd HH:mm:ss'/>" >
		  		</td>
				<td class="t-title" width="100" title="包括个人和机构">被调查人：</td>
				<td><input class="t-text" type="text" name="fieldsurvey.investigated" id="investigated"
					value='<s:property value="fieldsurvey.investigated"/>'/>
				</td></tr>	
				
				<tr><td class="t-title" title="可以填写多人请用;隔开">实地调查人：</td>
				<td><input class="t-text" type="text" id="suveyPersons" name="fieldsurvey.suveyPersons" 
					value='<s:property value="fieldsurvey.suveyPersons"/>'/></td>
				<td class="t-title">实地调查负责人：</td>
				<td><input class="t-text" type="text" id="suveyChargeMan" name="fieldsurvey.suveyChargeMan" 
					value='<s:property value="fieldsurvey.suveyChargeMan"/>'/>
				</td></tr>	
			  	
			  	<tr><td class="t-title">被调查内容：</td>
			  	<td colspan="3"><textarea rows="4" style="width:600;" id="investgatedContent"
			  		name="fieldsurvey.content"><s:property value="fieldsurvey.content"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title">调查结果：</td>
			  	<td colspan="3"><textarea rows="4" style="width:600;" id="result"
			  		name="fieldsurvey.result"><s:property value="fieldsurvey.result"/></textarea></td></tr>
			  		
			  	<tr><td class="t-title">下一步方案：</td>
			  	<td><select name="fieldsurvey.xzbmfbResultId" id="xzbmfbResultId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="xzbmfbResults">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="fieldsurvey.xzbmfbResultId==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select></td>
		  		<td class="t-title casecadeWithXzbmfbResult" style="display:none;">协助部门负责人邮箱：</td>
			  	<td class="casecadeWithXzbmfbResult" style="display:none;"><input class="t-text" type="text" id="email"
			  		name="fieldsurvey.email" value='<s:property value="fieldsurvey.email"/>' /></td></tr>
			  		
			  	<tr class="casecadeWithXzbmfbResult" style="display:none;"><td class="t-title">协助落实部门时间：</td>
				<td>
					<input class="t-text" type="text" id="xzlsbmTime" name="fieldsurvey.xzlsbmTime" 
						value="<s:date name='fieldsurvey.xzlsbmTime' format='yyyy-MM-dd HH:mm:ss'/>" >
		  		</td>
			  	<td class="t-title">协助落实部门：</td>
			  	<td><input class="t-text" type="text" id="xzlsbm" name="fieldsurvey.xzlsbm" 
					value='<s:property value="fieldsurvey.xzlsbm"/>' /></td></tr>
			  	
			  	<tr class="casecadeWithXzbmfbResult" style="display:none;"><td class="t-title">要求：</td>
			  	<td colspan="3"><textarea rows="4" style="width:600;" id="required"
			  		name="fieldsurvey.required"><s:property value="fieldsurvey.required"/></textarea></td></tr>
			  	
			  	<tr class="casecadeWithXzbmfbResult" style="display:none;"><td class="t-title">协助落实部门负责人：</td>
			  	<td><input class="t-text" type="text" id="xzlsbmChargeMan" name="fieldsurvey.xzlsbmChargeMan" 
					value='<s:property value="fieldsurvey.xzlsbmChargeMan"/>' /></td>
			  	<td class="t-title">协助部门反馈时间：</td>
			  	<td>
			  		<input class="t-text" type="text" id="xzbmfbTime" name="fieldsurvey.xzbmfbTime" 
						value="<s:date name='fieldsurvey.xzbmfbTime' format='yyyy-MM-dd HH:mm:ss'/>" >
		  		</td></tr>
			  	
			  	<tr class="casecadeWithXzbmfbResult" style="display:none;"><td class="t-title">协助部门反馈结果：</td>
			  	<td colspan="3"><textarea rows="4" style="width:600;" id="xzbmfbResult"
			  		name="fieldsurvey.xzbmfbResult"><s:property value="fieldsurvey.xzbmfbResult"/></textarea></td></tr>
			</table>
			<div class="t-but" style="display:'';" id="nextstep0">
			<a id="editBtn" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab('editBtn','合规初步调查')"><s:if test='fieldsurvey != null'>修改</s:if><s:else>新增</s:else></a>
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
