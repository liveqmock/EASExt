/**
 * 打开新的选项卡（easyUI的tab）,用户列表菜单，添加用户和修改用户使用
 * @param btn 必选项 调用此函数的对象，传入this
 * @param parentTitle 必选项 当关闭编辑或者详细信息页面时需要刷新页面的title
 * @param type 必选项 类型，add——添加页面；edit——编辑页面
 * @param id 可选项（记录id） 不传时默认详细信息页面，传值时打开新增或者编辑页面
 * @return
 */
function openUserTab(btn,parentTitle,type,id){
	var me = $(btn);
	var url,title=parentTitle;
	switch (type) {
		case 'add':
			url = $("#addURL").val();	title="添加页面";
			break;
		case 'edit':
			url = $("#editURL").val()+id;	title="编辑页面";
			if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
			break;
	}
	addTab(title, url);
}

/**
 * 打开新的选项卡（easyUI的tab）,角色列表菜单，添加角色、修改菜单权限和修改角色名称页面使用
 * @param btn 必选项 调用此函数的对象，传入this
 * @param parentTitle 必选项 当关闭编辑或者详细信息页面时需要刷新页面的title
 * @param type 必选项 类型，addrole——添加角色页面；editmenu——编辑菜单权限页面；editrole--编辑角色页面
 * @param id 可选项（记录id） 不传时默认详细信息页面，传值时打开新增或者编辑页面
 * @return
 */
function openRoleTab(btn,parentTitle,type,id){
	var me = $(btn);
	var url,title=parentTitle;
	switch (type) {
	case 'addrole':
		url = $("#addURL").val();	title="添加角色";
		break;
	case 'editmenu':
		url = $("#editmenuURL").val()+id;	title="修改菜单权限";
		if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
		break;
	case 'editrole':
		url = $("#editroleURL").val()+id;	title="修改角色";
		if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
		break;
	}
	addTab(title, url);
}

/**
 * 添加用户submit
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function usersubmit(title){
	var roleidlist= document.getElementsByName("roleidlist");
	var isSel2 =""; //判断角色项是否有选中项
	for(var j=0;j<roleidlist.length;j++){
		if(roleidlist[j].checked==true){
			isSel2+=roleidlist[j].value+",";
			}
			}
	if(isSel2 == ""){
		$("#rolenum").val("0");
	}else{
		$("#rolenum").val("1");
	}
	
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var result1=true;
			
			if($("#rolenum").val()=="0"){
				 $.messager.alert("提示", "请选择角色!","info");
				result1=false;
			}
			if(result == true&&result1==true){
				$("#editForm").ajaxSubmit({
		 			success:function(){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示","操作成功！","info",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
									})
							}
							closeTab();
						})
		 			}
		 		});	
			}
			return false;
		}
	})
}

/**
 * 表单提交,财务房屋合同编辑用户页面
 * @param id 必选项		提交表单时点击对象的id属性
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function submitFinaUserTab(id,title){
	var roleidlist= document.getElementsByName("chosecomplist");
	var isSel2 =""; //判断角色项是否有选中项
	for(var j=0;j<roleidlist.length;j++){
		if(roleidlist[j].checked==true){
			isSel2+=roleidlist[j].value+",";
			}
			}
	if(isSel2 == ""){
		$("#rolenum").val("0");
	}else{
		$("#rolenum").val("1");
	}
	
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var result1=true;
			
			if($("#rolenum").val()=="0"){
				 $.messager.alert("提示", "请选择所负责公司!","info");
				result1=false;
			}
			
			if(result == true&&result1==true){
				if(id){
					$("#"+id).attr("disabled","disabled");
					$("#"+id).removeAttr('onclick');
				} 
				$("#editForm").ajaxSubmit({
					dataType:"text",
		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
								toJson(json).success == "true"?"info":"error",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								if(refrTab){
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
											})
								}
								
							}
							closeTab();
						});
		 			}
		 		});	
			}else{
				if(id) $("#"+id).removeAttr("disabled");
			}
			return false;
		}
	})
}


/**
 * 修改用户submit
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function editsubmit(title){
	var userrolechoseid= document.getElementsByName("userrolechoseid");
	var isSel2 =""; //判断角色项是否有选中项
	for(var j=0;j<userrolechoseid.length;j++){
		if(userrolechoseid[j].checked==true){
			isSel2+=userrolechoseid[j].value+",";
			}
			}
	if(isSel2 == ""){
		$("#rolenum").val("0");
	}else{
		$("#rolenum").val("1");
	}
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var result1=true;
			
			if($("#rolenum").val()=="0"){
				$.messager.alert("提示", "请选择角色!","info");
				result1=false;
			}
			if(result == true&&result1==true){
				$("#editForm").ajaxSubmit({
		 			success:function(){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示","操作成功！","info",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
									})
							}
							closeTab();
						})
		 			}
		 		});	
			}
			return false;
		}
	})
}


/**
 * 添加角色、修改角色菜单权限submit
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function roleSubmit(title){
//	var menuidlist1 = document.getElementsByName("menuidlist");
	var isSel="";//判断菜单项是否有选中项
/*	for(var i=0;i<menuidlist1.length;i++){
				if(menuidlist1[i].checked==true) {
					isSel +=menuidlist1[i].value+",";					
				  }
				   }*/
				   
	var menuidlist1;
//	alert(menuTreeList.length + menuTreeList[0].value);
		
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
//		alert(nodes.length + "," + nodes[0].id);
		for(var j=0;j<nodes.length;j++) {
			isSel += nodes[j].id + ",";
//			alert(isSel);
		}
	if(isSel == ""){
		$("#menunum").val("0");
	}else{
		$("#menuidlist").val(isSel);
		$("#menunum").val("1");
	}
	
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var result1=true;
			if($("#menunum").val()=="0"){
				 $.messager.alert("提示", "请选择菜单权限!","info");
				result1=false;
			}
			if(result == true&&result1==true){
				$("#editForm").ajaxSubmit({
		 			success:function(){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示","操作成功！","info",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
									})
							}
							closeTab();
						})
		 			}
		 		});	
			}
			return false;
		}
	})
}
/**
 * 修改角色名称submit
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function editRoleNameSubmit(title){
	$('#editForm').form('submit',{
		onSubmit: function(){
			var result = $(this).form('validate');
			var result1=true;
			if(result == true&&result1==true){
				$("#editForm").ajaxSubmit({
		 			success:function(){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示","操作成功！","info",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
									})
							}
							closeTab();
						})
		 			}
		 		});	
			}
			return false;
		}
	})
}	


/**
 * 数据字典数据信息添加数据时用这个方法，
 * 由于添加页面时需要传递一个id值，所以单独写了这个方法
 * @param btn
 * @param parentTitle
 * @param type
 * @param id
 * @return
 */
function openItemTab(btn,parentTitle,type,id){
	var me = $(btn);
	var url,title=parentTitle;
	if(type == 'add'){
		url = $("#addURL").val()+id;	title+="_编辑信息";
		if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
		}
	addTab(title, url);
}