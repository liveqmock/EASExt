$(function(){
	if($("#returnBack").val()=='true'){
		$("#returnBtn").show();
		$("#nextstep0,#nextstep1").remove();
		$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
	}

	$("#caseCode,#typeId,#nextSchemeId").validatebox({required: true,missingMessage: '必选'});
	$("#communicatePerson").validatebox({required: true,validType:'charLength[1,50]',missingMessage: '必填'});
	$("#cpRelationWAccused,#bbmsddcfs").validatebox({validType:'charLength[0,100]'});
	$("#communicateTime").addClass("easyui-my97 Wdate t-text").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
		.validatebox({required:true,missingMessage: '必填'});
})