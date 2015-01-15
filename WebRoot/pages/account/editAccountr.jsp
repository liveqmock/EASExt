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
		$("#employeesNum,#email,#accountrMoney,#company,#department,#employeesName").addClass("easyui-validatebox").validatebox({required: true,validType:'njection',missingMessage: '必填'});
		 $("#accountrMoney").validatebox({required: true,validType:'intOrFloat',missingMessage: '必须是数字'});
		  $("#email").validatebox({required: true,validType:'emailcn',missingMessage: '请输入正确的邮箱格式'});
		$("#accountrDate").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd",width:"200px"})
				.validatebox({required:true,missingMessage: '必填'});
					 $("#personalRemark").validatebox({required: false,validType:'njection',missingMessage: ''});
		})
	
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/accountr/accountrAction!<s:if test='accountr != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" id="fid" name="accountr.fid " value='<s:property value="accountr.fid"/>' />
            <div class="title">报销信息编辑</div>
			<table>
				<tr>
				 <td class="t-title" width="100">员工姓名</td>
                    <td><input class="t-text" type="text" id="employeesName" name="accountr.employeesName" value='<s:property value="accountr.employeesName" />'/><span class="required">*</span></td>
                    <td class="t-title" width="100">员工工号</td>
                    <td><input class="t-text" type="text" id="employeesNum" name="accountr.employeesNum" value='<s:property value="accountr.employeesNum" />'/><span class="required">*</span></td>
                    <td class="t-title" width="100">邮箱</td>
                    <td><input class="t-text" type="text" id="email" name="accountr.email" value='<s:property value="accountr.email" />'/><span class="required">*</span></td>
             	</tr>
                <tr>
                <td class="t-title" width="100">报销日期</td>
                    <td><input type="text" id="accountrDate" class="t-text" name="accountr.accountrDate" value='<s:date name='accountr.accountrDate' format='yyyy-MM-dd'/>'><span class="required">*</span></td>
                    <td class="t-title" width="100">所属公司</td>
                    <td><input class="t-text" type="text" id="company" name="accountr.company" value='<s:property value="accountr.company" />'/><span class="required">*</span></td>
                    <td class="t-title" width="100">部门</td>
                    <td><input class="t-text" type="text" id="department" name="accountr.department" value='<s:property value="accountr.department" />'/><span class="required">*</span></td>
                 
				</tr>
				   <tr>
                    <td class="t-title" width="100">个人备注</td>
                    <td colspan="3"><textarea rows="10" cols="10" id="personalRemark" name="accountr.personalRemark"><s:property value="accountr.personalRemark" /></textarea></td>
                   <td class="t-title" width="100">报销金额</td>
                    <td><input class="t-text" type="text" id="accountrMoney" name="accountr.accountrMoney" value='<s:property value="accountr.accountrMoney" />'/><span class="required">*</span></td>
                 
				</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','报销信息列表')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
