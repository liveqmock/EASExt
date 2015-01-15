<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
		<title>添加用户</title>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript">
		$(document).ready(function(){		
			$("#fuseremail,#fusername").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			  $("#fuseremail").validatebox({required: true,validType:'emailcn',missingMessage: '请输入正确的邮箱格式'});
		})
	
		</script>
	</head>
	<body>
    	<div class="tableForm">
			<form action="<%=basePath%>/accountr/accountrAction!addAccountrUser" method="post" id="editForm">
            	<div class="title">添加收件人</div>
				<table>
					<tr>
						<td class="t-title" width="60">收件人名称：</td>
                        <td>
							<input class="t-text" type="text" id="fusername" name="accountrUser.fusername"/><span class="required">*</span>
						</td>
					</tr>
					<tr>
						<td class="t-title">收件人邮箱：</td>
                        <td>
							<input class="t-text" type="text" id="fuseremail" name="accountrUser.fuseremail"/><span class="required">*</span>
						</td>
					</tr>
				</table>
                <div class="t-but">
                	<a href="javascript:void(0)" class="but-change" id="edituserBtn" onclick="submitTab('edituserBtn','汇总邮件接收人设置')" plain="true">保存</a>
					<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()" plain="true">取消</a>
                </div>
			</form>
         </div>
	</body>
</html>
