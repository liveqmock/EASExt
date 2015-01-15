<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page language="Java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/fckedit/ckeditor";
String basePatha = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<base href="<%=basePatha%>">
	<title>设置汇总收件人</title>
	<link type="text/css" rel="stylesheet" href="<%=path%>/fckedit/ckeditor/_samples/sample.css" />
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/common/compliance.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
<script type="text/javascript">
	$(function(){
	
	      //遍历人员
			var url = "<%=basePatha%>/accountr/accountrAction!findAccountrUser";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var html= $("<li><input type='hidden' value='"+json[i].fstatus+"' class='fstatus'><input type='checkbox' class='AccountrUserId' name='auserfid' value='" + json[i].fid + "'>" + json[i].fusername + "</li>");
			   $("#bx").append(html);
			}
			//选中人员的回写
			var fstatus=$(".fstatus");
			var AccountrUserIds=$(".AccountrUserId");
			for(var i=0;i<fstatus.length;i++){
				 if(fstatus[i].value=="1"){
				 AccountrUserIds[i].checked=true;
			 	}
			}			
			
		})	
	//删除选中的收件人	
	function delAccountrUser(){
	var AccountrUserIds=$(".AccountrUserId");
	var parm="";
		for(var i=0;i<AccountrUserIds.length;i++){
	       if(AccountrUserIds[i].checked==true){
	         parm+=AccountrUserIds[i].value+",";
	       }
		}
	   if(parm != ""){
	   parm=parm.substring(0,parm.length-1);
	     $.messager.confirm('警告', '确定需要删除此信息吗？', function(r){
		 if(r){
			$.ajax({
				 url: "<%=basePatha%>/accountr/accountrAction!deleteAccountrUser?fid="+parm,
				 async: false,
				 success:function(){
					$.messager.alert("提示","操作成功！","info",function(){
						location=location;
					})
 				}
			});
		}
	});
 }
	}
	
	
	/**
 * 表单提交
 * @param id 必选项		提交表单时点击对象的id属性
 * @param title 可选项	需要刷新tab标签页的title属性
 * @return
 */
function submitTabNotCloss(id,title){
	//if(id) $("#"+id).attr("disabled","disabled");
	$('#editForm').form('submit',{
		onSubmit: function(){
				$("#editForm").ajaxSubmit({
					dataType:"Post",
					 async: false,
		 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
						$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
								toJson(json).success == "true"?"info":"error",function(){
							if(title!=undefined && title!=null && title!=''){
								var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
								var url = $(refrTab.panel('options').content).attr('src');
									getTopWin().$('#tabs').tabs('update',{
										tab:refrTab,options:{content:createFrame(url)}
									})
							}
						});
		 			}
		 		});	
			return false;
		}
	})
}


function  fckd(){
var UserId=$(".AccountrUserId")
var ids="";
var oEditor = CKEDITOR.instances.editor1;
for(var i=0;i<UserId.length;i++){
    if(UserId[i].checked==true){
      ids+=UserId[i].value+",";
    }
}
if(ids != ""){
ids=ids.substring(0,ids.length-1);
}
 $.ajax({
        type:'POST',
        dataType:'text',
		url: "<%=basePatha%>/accountr/accountrAction!updateAccountrUserRemarks?date=<%=new Date()%>",
		async: false,
		data:{'auserfid':ids,'fremarks':oEditor.getData()},
		success:function(json){
		    $.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
				toJson(json).success == "true"?"info":"error",function(){
				location=location;
			});
 		}
	});
}

</script>
</head>

<body>

<%  
CKEditorConfig settings = new CKEditorConfig(); 
settings.addConfigValue("width",1100); 
settings.addConfigValue("height",200);
%>
    	<div class="tableForm">
			<form action="<%=basePatha%>/accountr/accountrAction!updateAccountrUserRemarks?date=<%=new Date()%>" id="editForm" method="post">
				<div class="title">设置汇总收件人</div>
				<table>
					<tr>
						<td class="t-title" width="80">
                            设置收件人:</td>
                        <td>
                        	<ul id="bx"></ul>
						</td>
					</tr>
				</table>
				 <div class="t-but">
                    <a href="javascript:void(0)" class="but-change" onclick="openTab(this,'设置汇总收件人','edit')" plain="true">新增</a>
                    <a href="javascript:void(0)" class="but-change" onclick="delAccountrUser()" plain="true">删除</a>
                </div>
				<div class="title">设置邮件内容</div>
				<table>
					<tr>
						<td class="t-title" width="80">
                            设置邮件内容</td>
                        <td>
                        <textarea cols="80"  id="editor1" name="fremarks" rows="10">${accountrRemarks.fremarks}</textarea>
						</td>
					</tr>
				</table>
                <div class="t-but">
                	<a href="javascript:void(0)" class="but-change" id="setBtn" onclick="fckd()" plain="true">保存</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
					<!--<input type="submit" value="Submit"/>-->
                </div>
			</form><ckeditor:replace  replace="editor1" config="<%=settings %>"  basePath="<%=basePath%>"/>
    	</div>
    	
    	<input type="hidden" id="editURL" value="<%=path%>/pages/account/addAccountrUser.jsp?"/>
	</body>
</html>
