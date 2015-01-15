$(function(){
	if($("#returnBack").val()=='true'){
		$("#returnBtn").show();$("#nextstep0,#nextstep1").remove();
		$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
	}
	nextSchemeCasecade();
	$("#nextSchemeId").change(function(){//下一步方案下拉列表改变时
		nextSchemeCasecade();
	})
	$("#nextStepBtn").click(function(){//直接申请结案时
		$("#nextStepBtn").attr("disabled","disabled");
		$('#editForm').form('submit',{
			onSubmit: function(){
				if($(this).form('validate') == true){
					$("#editForm").ajaxSubmit({
						dataType:"text",
			 			success:function(json){//json为服务器端返回的信息
							if(toJson(json).success == "true"){
								window.location = $("#nextStepURL").val()+"?investigationId="+
								(($("#parentId").val()==null||$("#parentId").val()=='')?toJson(json).investigationId:$("#parentId").val())
								+"&sepcialEnd="+$("#sepcialEnd").val();
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
	$("#caseCode,#typeId,#nextSchemeId").validatebox({required: true,missingMessage: '必选'});
	$("#communicatePerson").validatebox({required: true,validType:'njelength[1,50]',missingMessage: '必填'});
	$("#cpRelationWAccused,#bbmsddcfs").validatebox({validType:'njelength[0,100]'});
	$("#communicateTime").addClass("easyui-my97 Wdate t-text").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
		.validatebox({required:true,missingMessage: '必填'});
})

function nextSchemeCasecade(){
	var nextSchemeValue = $("#nextSchemeId").val();
	$(".casecadeWithNextScheme0,.casecadeWithNextScheme1,.casecadeWithNextScheme2,#nextstep1").hide();
	$("#nextstep0").show();$("#sepcialEnd").val(false);
	$("#nextCommunicateTime").removeClass();
	if(nextSchemeValue == 1){ 
		$(".casecadeWithNextScheme2").show();
		$("#nextCommunicateTime").addClass("easyui-my97 Wdate t-text").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
		.validatebox({required:true,missingMessage: '必填'});
	}else if(nextSchemeValue == 2){ 
		$(".casecadeWithNextScheme0").show();
	}else if(nextSchemeValue==3){ 
		$(".casecadeWithNextScheme1").show();
	}else if(nextSchemeValue==4){//申请结案
		$("#nextstep0").hide();$("#nextstep1").show();
	}else if(nextSchemeValue==5){
		$("#sepcialEnd").val(true);$("#nextstep0").hide();$("#nextstep1").show();
	}
}