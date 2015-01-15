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
	
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	
	<script type="text/javascript">
	    $(document).ready(function(){
		$("#copypeoplemail").validatebox({validType: "emailcn['#copypeoplemail']"});
		$("#copypeoplename").validatebox({validType: "ormal['#copypeoplename']"});	
		$("#copypeoplename,#copypeoplemail").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
	//	$("#copypeoplemail").validatebox({validType: "exist['<%=basePath %>/person/orgManager!ifEmailHasExists','#oldcopypeoplemail']"});
		
		})
	</script>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/person/orgManager!<s:if test='editCopypeoMap != null'>updateCopyPeo</s:if><s:else>insertCopyPeo</s:else>" method="post" id="editForm">
            <div class="title">抄送人信息</div>
            <input type="hidden" id="fid" name="fid" value='<s:property value="editCopypeoMap.FID"/>' />
			<table>
					<tr>
					<td class="t-title" width="100">抄送人姓名</td>
					 <td><input class="t-text" type="text" id="copypeoplename" name="fpersonname" value='<s:property value="editCopypeoMap.FPERSONNAME" />'/><span class="required">*</span></td>
					</tr>
					<tr>
					<td class="t-title" width="100">抄送人邮箱</td>
                    <td><input class="t-text" type="text" id="copypeoplemail" name="fpersonemail" value='<s:property value="editCopypeoMap.FPERSONEMAIL" />'/><span class="required">*</span></td>
             		<input type="hidden" id="oldcopypeoplemail" value='<s:property value="editCopypeoMap.FPERSONEMAIL" />'/>
             		</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','抄送人信息_详细信息')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
