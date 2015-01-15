<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>My JSP 'update.jsp' starting page</title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/record_jsp_style.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		$("#receivableQuantity,#receivedNumber,#financialNumber,#archivesNumber,#sendbackNumber").validatebox({required: false,validType:'integer',missingMessage: '必须是整数'});
		})
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/project/projectAction!updateAgrNumber" method="post" id="editForm">
			<input type="hidden" id="fid" name="agreementNumber.fid " value='<s:property value='numberMap.FID' />' />
            <div class="title"><s:property value='numberMap.AGREEMENTNAME' /></div>
			<table>
			   <tr><td class="t-title" width="100">应收数量</td>
				<td><input class="t-text" type="text" id="receivableQuantity" name="agreementNumber.receivableQuantity" value="<s:property value='numberMap.RECEIVABLEQUANTITY' />"></td>
				<td class="t-title" width="100">收到数量</td>
				<td><input class="t-text" type="text" id="receivedNumber" name="agreementNumber.receivedNumber" value="<s:property value='numberMap.RECEIVEDNUMBER' />"></td>
				</tr>
			    <tr>
			    <td class="t-title" width="100">财务保存数量</td>
				<td><input class="t-text" type="text" id="financialNumber" name="agreementNumber.financialNumber" value="<s:property value='numberMap.FINANCIALNUMBER' />"> </td>
				<td class="t-title" width="100">档案处保存数量</td>
				<td><input type="text" id="archivesNumber" class="t-text" name="agreementNumber.archivesNumber" value="<s:property value='numberMap.ARCHIVESNUMBER' />"></td>
				</tr>
				<tr>
				<td class="t-title" width="100">寄回数量</td>
				<td><input type="text" id="sendbackNumber" class="t-text" name="agreementNumber.sendbackNumber" value="<s:property value='numberMap.SENDBACKNUMBER' />"></td>
				<td class="t-title" width="100">备注</td>
				<td><textarea rows="5" id="remarks" name="agreementNumber.remarks"><s:property value='numberMap.REMARKS'/></textarea></td>
				</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','合同信息_详细信息')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
