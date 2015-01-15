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
	<title>被投诉人信息</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
<%--	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>--%>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#returnBack").val()=='true'){
				$("#returnBtn").show(); $('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
			}
		})
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form method="post" id="editForm">
			<input type="hidden" id="fisinner" value="<s:property value='person.fisinner'/>"/>
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);" 
					style="display: none;" class="but-cancel">返回</a>
			</div>
			<div class="title">被投诉人信息</div>
			<table>
				<tr><td class="t-title" width="100">被投诉人姓名 ：</td>
				<td><input class="t-text" type="text" value='<s:property value="person.fname"/>'/></td>
				<td class="t-title" width="100">手机号码：</td>
				<td><input class="t-text" type="text" value='<s:property value="person.fmobile"/>'/></td></tr>	
				
				<tr><td class="t-title">座机 ：</td>
				<td><input class="t-text" type="text" value='<s:property value="person.fofficephone"/>'/></td>
				<td class="t-title">QQ：</td>
				<td><input class="t-text" type="text" value='<s:property value="person.fqq"/>'/></td></tr>	
				
				<tr><td class="t-title">邮箱 ：</td>
				<td><input class="t-text" type="text" value='<s:property value="person.femail"/>'/></td>
				<s:if test="person.fisinner ==1">
					<td class="t-title">职位名称：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.flevel"/>'/></td>
				</s:if>
				<s:if test="person.fisinner ==0">
					<td class="t-title">公司名称：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fcompanyname"/>'/></td>
				</s:if>
				</tr>	
				<s:if test="person.fisinner ==1">
					<tr><td class="t-title">组织名称 ：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fdeptname"/>'/></td>
					<td class="t-title">城市名称：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fcityname"/>'/></td></tr>	
					<tr><td class="t-title">营业部 ：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fsaledepart"/>'/></td>
					<td class="t-title">入职时间：</td>
					<td><input class="t-text" type="text" value="<s:property value="person.fentrytime"/>"/></td></tr>	
					<tr><td class="t-title">上家工作单位 ：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.flastworkunit"/>'/></td>
					<td class="t-title">学历：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.feducation"/>'/></td></tr>	
					<tr><td class="t-title">推荐人 ：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.freferrer"/>'/></td>
					<td class="t-title">被投诉的次数：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fcomplaintcount"/>'/></td></tr>	
				</s:if>
				<s:if test="person.fisinner ==0">
					<tr><td class="t-title">城市 ：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fcityname"/>'/></td>
					<td class="t-title">被投诉人地址：</td>
					<td><input class="t-text" type="text" value='<s:property value="person.fbycompliantadd"/>'/></td></tr>	
					<tr><td class="t-title">是否为中介 ：</td>
						<td>
							<select disabled="disabled"> 
				    			<option value="1" <s:if test="person.fisagent==1">selected</s:if> >是 </option> 
				    			<option value="0" <s:if test="person.fisagent==0">selected</s:if> >否 </option> 
			    			</select>
					</td></tr>	
					<tr><td class="t-title">备注：</td><td colspan="3"><textarea rows="4" disabled="disabled"
						style="width:580;"><s:property value="person.fnotagentRemark"/></textarea></td>
					</tr>
				</s:if>
			  	
			</table>
		</form>
		</div>
	</body>
</html>
