/**
 * 导入excel
 */
function importExcelFun(){
	var file = document.getElementById('excelFile');
	var aa = getPath(file);
	if(aa==''){
		$.messager.alert("提示","请选择文件","info");	
 	}else if(!isExcel(file.value)){
		$.messager.alert("提示","请选择excel类型文件","info");	
 	}else{
 		$("#nn").val(file.value);
 		mask("正在导入数据，请稍候...");
 		$("#myform").ajaxSubmit({
 			type: $("#myform").method,
 			url:$("#myform").action,
 			resetForm: true,
 			success:function(json){//文件上传成功后执行,json
 				$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 				unmask(); 
	 				$("#tt").datagrid("reload");  			
 				});
 			}
 		});	
 		return false;//防止刷新
 	}
}
/**
 * 导入excel
 * 2014-5-25 将 $("#nn").val(file.value); 更改为 $("#filePath").val(file.value)，由于nn代表的是文件路径，名字取的不直观
 * 故重载该方法
 * redictPath:导入的信息如果有问题，则跳转到其他的页面下载有问题的信息
 */
function importExcelFun_filePath(validTr){
	$("#" + validTr).css("display","none");//先将导出信息按钮隐藏掉
	var file = document.getElementById('excelFile');
	var aa = getPath(file);
	if(aa==''){
		$.messager.alert("提示","请选择文件","info");	
 	}else if(!isExcel(file.value)){
		$.messager.alert("提示","请选择excel类型文件","info");	
 	}else{
 		$("#filePath").val(file.value);
 		mask("正在导入数据，请稍候...");
 		$("#myform").ajaxSubmit({
 			type: $("#myform").method,
 			url:$("#myform").action,
 			resetForm: true,
 			success:function(json){//文件上传成功后执行,json
 				$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 				unmask(); 
	 				if(toJson(json).success == "false" && toJson(json).message == '有未验证通过的信息'){
	 					$("#" + validTr).css("display","block");  
	 				}
	 				//刷新页面
	 				$("#tt").datagrid("reload");
 				});
 			}
 		});	
 		return false;//防止刷新
 	}
}
/**
 * 获取文件路径
 * @param obj
 * @return
 */
function getPath(obj)  {
	if(obj){
	  if (window.navigator.userAgent.indexOf("MSIE")>=1){
	       obj.select();
	       window.parent.document.body.focus();
	      return document.selection.createRange().text;
      }else if(window.navigator.userAgent.indexOf("Firefox")>=1){        
	      if(obj.files && obj.files.length>0){          
	           return window.URL.createObjectURL(obj.files[0]);       
	      }        
	      return obj.value;        
      }  
         return obj.value;   
	}  
} 
/**
 * 判断文件类型
 * @param file
 * @return
 */
function isExcel(file){
	var strFilter=".xlsx|xls"
	if(file.indexOf(".")>-1){
		var p = file.lastIndexOf(".");
		var strPostfix=file.substring(p,file.length);
		strPostfix = strPostfix.toLowerCase();
		if(strFilter.indexOf(strPostfix)>-1){
			return true;
		}
	}        
	return false;            
} 