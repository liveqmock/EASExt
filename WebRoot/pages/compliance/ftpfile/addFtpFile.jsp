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
	<title>文件下载编辑信息</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
           $("#filename,#filepath").validatebox({required: true,missingMessage: '必选'});
		})
	</script>
	</head>
	<body>
		<div class="tableForm">
			<form action="<%=path %>/ftpfile/ftpfile!addFtpFile" method="post" id="editForm">
				<div class="title">菜单列表编辑</div>
				<table>
					<tr><td class="t-title">文件名</td>
					<td><input class="t-text" type="text" id="filename" name="ftpFile.filename" value=''/><span class="required">*</span></td>
					<td  class="t-title" width="80">文件路径</td>
					<td>
						<input class="t-text" type="text" id="filepath" name="ftpFile.filepath" value=''/><span class="required">*</span>
					</td>
					<td class="t-title" width="80">文件类型</td>
					<td>
						<input class="t-text" id="filetype" name="ftpFile.filetype" value=""/>
					</td>
					</tr>
				</table>
				<div class="t-but">
					<a href="javascript:void(0)" class="but-change" id="editBtn" onclick="submitTab('editBtn','文件下载')">新增</a>	
			    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            	</div>
			</form>
		</div>
	</body>
</html>
