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
	<title>本部门实地调查——再次协助落实反馈结果信息</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#xzbmfbResultId").change(function(){//下一步方案下拉列表改变时
				var xzbmfbResultIdValue = $("#xzbmfbResultId").val();
				$("#nextstep1").hide(); $("#nextstep0").show();
				if(xzbmfbResultIdValue == 1){
					$("#nextstep0").hide(); $("#nextstep1").show();
				}else if(xzbmfbResultIdValue==2){
					$("#nextstep1").hide(); $("#nextstep0").show();
				}
			})
			$("#nextStepBtn").click(function(){//直接申请结案时
				$("#nextStepBtn").attr("disabled","disabled");
				$('#editForm').form('submit',{
					onSubmit: function(){
						if($(this).form('validate') == true){
							dataType:"text",
							$("#editForm").ajaxSubmit({
					 			success:function(json){//json为服务器端返回的信息
									if(toJson(json).success == "true"){
										window.location = $("#nextStepURL").val()+"?investigationId="+$("#investigationId").val()
											+"&zcxzlsId="+toJson(json).zcxzlsId;
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
			$("#xzbmfbResultId").validatebox({required: true,missingMessage: '必选'});
		})
	</script>
	</head>
	<body>
		<s:if test='fieldsurvey != null'>
			<div class="tableForm">
			<table>
				<tr><td class="t-title" width="150">实际调查时间：</td>
				<td><input class="t-text" type="text" disabled="disabled" 
					value='<s:date name="fieldsurvey.fieldsurveyTime" format="yyyy-MM-dd HH:mm:ss"/>'/></td>
				<td class="t-title" width="150" title="包括个人和机构">被调查人：</td>
				<td><input class="t-text" type="text" disabled="disabled" value='<s:property value="fieldsurvey.investigated"/>'/>
				</td></tr>	
				<tr><td class="t-title" title="可以填写多人请用;隔开">实地调查人：</td>
				<td><input class="t-text" type="text" disabled="disabled" name="fieldsurvey.suveyPersons" 
					value='<s:property value="fieldsurvey.suveyPersons"/>'/></td>
				<td class="t-title">实地调查负责人：</td>
				<td><input class="t-text" type="text" disabled="disabled" name="fieldsurvey.suveyChargeMan" 
					value='<s:property value="fieldsurvey.suveyChargeMan"/>'/>
				</td></tr>	
			  	<tr><td class="t-title">被调查内容：</td>
			  	<td colspan="3"><textarea disabled="disabled" rows="4" 
			  		style="width:580;"><s:property value="fieldsurvey.content"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title">调查结果：</td>
			  	<td colspan="3"><textarea disabled="disabled" rows="4" 
			  		style="width:580;"><s:property value="fieldsurvey.result"/></textarea></td></tr>
			  		
			  	<tr><td class="t-title">下一步方案：</td>
			  	<td><select disabled="disabled">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="xzbmfbResults">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="fieldsurvey.xzbmfbResultId==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select></td>
		  		<td class="t-title">协助部门负责人邮箱：</td>
			  	<td><input class="t-text" type="text" disabled="disabled" value='<s:property value="fieldsurvey.email"/>' /></td></tr>
			  		
			  	<tr><td class="t-title">协助落实部门时间：</td>
				<td><input class="t-text" type="text" disabled="disabled" readonly="readonly" 
			  		value="<s:date name="fieldsurvey.xzlsbmTime" format="yyyy-MM-dd HH:mm:ss"/>"/></td>
			  	<td class="t-title">协助落实部门：</td>
			  	<td><input class="t-text" type="text" disabled="disabled" 
					value='<s:property value="fieldsurvey.xzlsbm"/>' /></td></tr>
			  	
			  	<tr><td class="t-title">要求：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" disabled="disabled" 
			  		name="fieldsurvey.required"><s:property value="fieldsurvey.required"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title">协助落实部门负责人：</td>
			  	<td><input class="t-text" type="text" disabled="disabled" 
					value='<s:property value="fieldsurvey.xzlsbmChargeMan"/>' /></td>
			  	<td class="t-title">协助部门反馈时间：</td>
			  	<td><input class="t-text" type="text" disabled="disabled" readonly="readonly" 
			  		value="<s:date name="fieldsurvey.xzbmfbTime" format="yyyy-MM-dd HH:mm:ss"/>"/></td></tr>
			  	
			  	<tr><td class="t-title">协助部门反馈结果：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" disabled="disabled" 
			  		name="fieldsurvey.xzbmfbResult"><s:property value="fieldsurvey.xzbmfbResult"/></textarea></td></tr>
			</table>
			</div>
		</s:if>
		
		<s:if test='feedbacks != null and feedbacks.size>0'>
			<div class="search-list">
	    	<span class="list-title">协助部门反馈记录</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="feedBackTime" width="150">协助部门反馈时间</th>
						<th field="feedBackResult" width="675">协助部门反馈结果</th>
			  		</tr>
			  	</thead>
				<s:iterator value="feedbacks">
					<tr>
						<td width="150"><s:date name="feedBackTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td width="500"><s:property value="feedBackResult"/></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<div class="tableForm">
		<form action="compliance/zcxzlsfbResult!<s:if test='zcxzlsfbResult != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<input type="hidden" id="id" name="zcxzlsfbResult.id" value='<s:property value="zcxzlsfbResult.id"/>' />
			<input type="hidden" name="zcxzlsfbResult.feedBackId" value='<s:property value="feedback.id"/>' />
			<input type="hidden" id="investigationId" name="investigationId" value='<s:property value="investigationId"/>' />
			<div class="title">本部门实地调查——再次协助落实反馈结果信息</div>
			<table>
				<tr><td class="t-title" width="150">协助部门反馈时间：</td>
					<td><input class="t-text" type="text" readonly="readonly" 
						name="zcxzlsfbResult.xzbmfbTime" value='<s:date name="feedback.feedBackTime" format="yyyy-MM-dd HH:mm:ss"/>'/>
				</td></tr>
			  	
			  	<tr><td class="t-title" width="150">协助部门反馈结果：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" readonly="readonly"
			  		name="zcxzlsfbResult.xzbmfbResult"><s:property value="feedback.feedBackResult"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title" width="150">下一步方案：</td>
			  	<td><select name="zcxzlsfbResult.xzbmfbResultId" id="xzbmfbResultId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="xzbmfbResults">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="zcxzlsfbResult.xzbmfbResultId==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select></td></tr>
			</table>
			<div class="t-but" style="display:'';" id="nextstep0">
		    	<a id="editBtn" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab('editBtn','合规初步调查')"><s:if test='zcxzlsfbResult != null'>修改</s:if><s:else>新增</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	  		</div>
			<div class="t-but" style="display:none;" id="nextstep1">
		   		<a id="nextStepBtn" href="javascript:void(0);" class="but-change">下一步</a>
		   		<input id="nextStepURL" type="hidden" value="<%=request.getContextPath()%>/compliance/applysettlement!edit"/>
	  		</div>
		</form>
		</div>
	</body>
</html>
