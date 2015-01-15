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
		<title>合同信息编辑</title>
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
   		        $("#fcontractnum").validatebox({required: true,validType:'length[1,30]',missingMessage: '必填'});
				$("#fcontractname").validatebox({required: true,validType:'njelength[1,70]',missingMessage: '必填'});
				$("#fmailbox").validatebox({required: true,validType:'njelength[1,90]',missingMessage: '必填'});
				$("#ffirstparty,#fclient,#ftransact").validatebox({required: false,validType:'njelength[0,35]',missingMessage: '必填'});
				$("#fcontractdate").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd",width:"200px"})
				.validatebox({required:true,missingMessage: '必填'});
				$("#fcontractnum")
				.validatebox({validType: "exist['<%=basePath %>doc/Dcontractaction!findContractNumExist','#oldContractNum']"});
				
			}); 
		</script>
	</head>
	<body>
    <div class="tableForm">
	 <form action="doc/Dcontractaction!<s:if test='dcontract!= null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" id="fid" name="dcontract.id" value='<s:property value="dcontract.id"/>' />
            <div class="title">合同信息编辑</div>
			<table>
				<tr><td class="t-title" width="80">合同序号</td>
					<td><input type="text"  <s:if test='dcontract!= null'>class="t-text readonly" disabled="disabled"</s:if><s:else>id="fcontractnum" class="t-text"</s:else>  
				    		name="dcontract.fcontractnum" value='<s:property value="dcontract.fcontractnum" />'/><span class="required">*</span>
					    <input type="hidden" id="oldContractNum" value="dcontract.fcontractnum"/></td>
					<td class="t-title" width="100">合同/协议名称</td>
					<td><input class="t-text" type="text" id="fcontractname" name="dcontract.fcontractname" 
				     	value='<s:property value="dcontract.fcontractname" />'/><span class="required">*</span></td>
					<td class="t-title" width="100">合同到期时间</td>
					<td><input class="t-text" type="text" id="fcontractdate" name="dcontract.fcontractdate" 
						value="<s:date name='dcontract.fcontractdate' format='yyyy-MM-dd'/>" ></td>
				</tr>
				
				<tr><td class="t-title">甲方</td>
					<td><input class="t-text" type="text" id="ffirstparty" name="dcontract.ffirstparty" 
				     	value='<s:property value="dcontract.ffirstparty" />'/></td>
					<td class="t-title">乙方</td>
					<td><input class="t-text" type="text" id="fclient" name="dcontract.fclient" 
		     	  		value='<s:property value="dcontract.fclient" />'/></td>
					<td class="t-title">办理人</td>
					<td><input class="t-text" type="text" id="ftransact" name="dcontract.ftransact" 
		        		value='<s:property value="dcontract.ftransact" />'/></td>
				</tr>
				
				<tr><td class="t-title">接口人邮箱</td>
					<td colspan="5"><input class="t-text" type="text" id="fmailbox" name="dcontract.fmailbox" 
				     	value='<s:property value="dcontract.fmailbox" />'/><span class="required">*</span></td>
				</tr>
			</table>
            <div class="t-but">
            <a id="editBtn" href="javascript:void(0)" class="but-change" onclick="submitTab('editBtn','合同列表')">保存</a>	
	    	 	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
     </div>
	</body>
</html>
