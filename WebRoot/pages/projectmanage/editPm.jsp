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
		<title>邮箱绑定</title>
	  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	   	<style type="text/css"> table tr td{font-size: 12px;} </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	  	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script type="text/javascript">
			$(function(){
				//easyUI验证	    	
   		        $("#pmEmail").validatebox({required: true,validType:'emailcncom',missingMessage: '输入正确的邮箱格式'});
			}); 
		</script>
	</head>
	<body>
    <div class="tableForm">
	 <form action="<%=basePath %>pmInfo/pmInfoAction!updatePmEmail" method="post" id="editForm">
			<input type="hidden" id="pmId" name="pmInfo.id" value="${pmInfo.id }"/>
            <div class="title">邮箱绑定</div>
			<table>
					<tr>
						<td class="t-title" width="60">用户名</td>
						<td><input class="t-text readonly" type="text" id="pmName" name="pmInfo.pmName" value="${pmInfo.pmName}" readonly/>
						</td>
					</tr>
					<tr>
						<td class="t-title">邮箱</td>
						<td><input class="t-text" type="text" name="pmInfo.pmEmail" id="pmEmail" value="${pmInfo.pmEmail}" />
						</td>
					</tr>
				</table>
            <div class="t-but">
            <a  href="javascript:void(0)" class="but-change" id="editBtn" onclick="submitTab('editBtn','邮箱绑定')">保存</a>	
	    	 	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
     </div>
	</body>
</html>
