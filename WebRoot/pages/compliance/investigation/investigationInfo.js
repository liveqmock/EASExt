/**
 * 打开新窗口（easyUI的模态窗口）
 * @param type（类型，view——详细信息；edit——编辑页面）
 * @param id （记录id）
 * @return
 */
function continueNext(btn,id,statusId,applyLastStep,title){
	var url;
	switch(statusId){
		case 2://继续调查
			url = $("#continueNextURL2").val()+"?parentId="+id;
			break;
		case 3://被投诉部门调查处理待审批
			url = $("#auditResultURL").val()+"?investigationId="+id+"&auditTypeId=1";
			break;
		case 4://被投诉部门调查处理审批未通过
			url = $("#continueNextURL2").val()+"?parentId="+id;
			break;
		case 5://被投诉部门调查处理审批已通过
			url = $("#feedbackRequiredURL").val()+"?investigationId="+id;
			break;
		case 6://被投诉部门调查处理待反馈
			url = $("#feedbackURL").val()+"?investigationId="+id+"&statusId="+statusId;
			break;
		case 7://被投诉部门调查处理已反馈
			url = $("#feedbackResultURL").val()+"?investigationId="+id;
			break;
		case 8://本部门实地调查待审批
			url = $("#auditResultURL").val()+"?investigationId="+id+"&auditTypeId=2";
			break;
		case 9://本部门实地调查审批已通过
			url = $("#fieldsurveyURL").val()+"?investigationId="+id;
			break;
		case 10://本部门实地调查审批未通过
			url = $("#continueNextURL2").val()+"?parentId="+id;
			break;
		case 11://再次协助落实待反馈
			url = $("#feedbackURL").val()+"?investigationId="+id+"&statusId="+statusId;
			break;
		case 12://再次协助落实已反馈
			url = $("#zcxzlsfbResultURL").val()+"?investigationId="+id;
			break;
		case 13://待结案初审
			url = $("#auditResultURL").val()+"?investigationId="+id+"&auditTypeId=3";
			break;
		case 14://结案初审未通过
			if(applyLastStep ==1) 	   url = $("#feedbackResultURL").val()+"?investigationId="+id;
			else if(applyLastStep ==2) url = $("#fieldsurveyURL").val()+"?investigationId="+id;
			else if(applyLastStep ==3) url = $("#zcxzlsfbResultURL").val()+"?investigationId="+id;
			else if(applyLastStep ==4) url = $("#continueNextURL2").val()+"?parentId="+id;
			break;
		case 15://待结案终审
			url = $("#auditResultURL").val()+"?investigationId="+id+"&auditTypeId=4";
			break;
			
		case 16://结案终审未通过时流转至初审人员
			url = $("#auditResultURL").val()+"?investigationId="+id+"&auditTypeId=5";
			break;
	}
	//特殊结案时重启案件状态为(继续调查2)
	if(statusId==17){
		url = $("#updateStatusURL").val();
		$.ajax({url:url,async:false,data:{investigationId:id,statusId:statusId},cache:false,type:"post",success:function(json){
			$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
					toJson(json).success == "true"?"info":"error",function(){
				$("#tt").datagrid("reload");
			});
		}})
	}
	if(statusId!=17){
		addTab((statusId==3||statusId==6||statusId==8||statusId==11||statusId==13||statusId==15)
				?(title||"待办列表_编辑信息"):(title||"合规初步调查_编辑信息"), url);
	}
}
/**
	上传多个附件
 */
function uploadFiles(){
	var rowData = $('#tt').datagrid('getSelected');
	if(rowData){
		openFile('file',rowData.id);
	}else{$.messager.alert("提示","请先关联案件！","info");}
}

function continueNext2(btn,id,statusId){
	var url;
	switch(statusId){
		case 6:
			url = $("#continueNextURL6").val()+"?parentId="+id;
			break;
		case 7:
			url = $("#continueNextURL6").val()+"?parentId="+id;
			break;	
	}
	addTab("合规初步调查_编辑信息",url);
}


function openFile(type,id){
	$('#iframeWin').window({
		title:"上传附件",
		onClose : function(){ $("#iframeSource").attr("src",""); } 
	})
	$("#iframeSource").attr("src",$("#fileUrl").val()+id);
	$('#iframeWin').window('open');

}