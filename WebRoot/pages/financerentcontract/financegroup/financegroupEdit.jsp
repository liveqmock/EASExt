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
	<title>修改新增组信息页面</title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>

	<script type="text/javascript">
		$(document).ready(function(){		
			$("#name").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#name").validatebox({validType: "exist['<%=basePath %>financegroup/financeGroupAction!selectedIfGroupExists','#oldgroupname']"});
		})
	
	</script>
	
	</head>

	<body>
    <div class="tableForm">
		<form action="financegroup/financeGroupAction!<s:if test='financeGroupAction != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" name="financeGroupAction.id" value='<s:property value="financeGroupAction.id"/>' />
            <div class="title">修改新增组信息页面</div>
            <table>	   
                 <tr>
                  <td class="t-title">组名称</td>
                  <td><input class="t-text" type="text" id="name" name="financeGroupAction.name" value='<s:property value="financeGroupAction.name" />' class="input_style_default"/><span class="required">*</span>
                        <input type="hidden" id="oldgroupname" value='<s:property value="financeGroupAction.name"/>'/>
                  </td>
                 </tr>
            </table> 
            <div class="t-but">
                <a href="javascript:void(0)" class="but-change" plain="true" id="editBtn" onclick="submitTab('editBtn','组列表')"><s:if test='financeGroupAction != null'>修改</s:if><s:else>新增</s:else></a>	
                 <a href="javascript:void(0)" class="but-cancel" plain="true" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
