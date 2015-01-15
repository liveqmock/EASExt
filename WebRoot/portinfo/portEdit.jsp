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
	<title>接口人信息编辑</title>
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
		$(function(){
			//easyUI验证
			$("#portmail").addClass("easyui-validatebox")
				.validatebox({required: true,validType: 'email',invalidMessage: '邮箱格式错误',missingMessage: '必填'});
			$("#orgname").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#city,#officeadd,#lastcostcenter").validatebox({validType: 'njection'});
		}); 
	</script>
	</head>
	<body>
      <div class="tableForm">
		<form action="iperson/interfacePersonList!update" method="post" id="editForm">
			<input type="hidden" name="portInfo.id" value='<s:property value="portInfo.id"/>' />
            <div class="title">接口人信息编辑</div>
			<table>
				<tr><td class="t-title" width="100">使用部门</td>
					<td><input class="t-text readonly" type="text" id="orgname" name="portInfo.orgname" readonly 
						value='<s:property value="portInfo.orgname" />'/></td>
     				<td class="t-title" width="120">地区</td>
     				<td><input class="t-text" type="text" id="city" name="portInfo.city" 
     					value='<s:property value="portInfo.city" />'/></td>
					<td class="t-title" width="120">办公室座落地点</td>
			  		<td><input class="t-text"type="text" id="officeadd" name="portInfo.officeadd" 
				     	value='<s:property value="portInfo.officeadd" />'/></td>
               </tr>
			   <tr>
			     	<td class="t-title">成本中心</td>
			     	<td><input class="t-text"type="text" id="lastcostcenter" name="portInfo.lastcostcenter" 
			     		value='<s:property value="portInfo.lastcostcenter" />'/></td>
					<td class="t-title">部门总接口人</td>
			  		<td><select name="portInfo.isport" id="isport">
						<s:iterator value="isPorts">
				  			<option value="<s:property value="key" />" 
				  				<s:if test="portInfo.isport == key">selected</s:if> ><s:property value="value" /></option>
						</s:iterator>
			  		</select></td>
			  		<td class="t-title">接口人邮箱</td>
			  		<td><input class="t-text"type="text" id="portmail" name="portInfo.portmail" 
			     		value='<s:property value="portInfo.portmail" />'/></td>
			  	</tr>
			</table>
            <div class="t-but">
			    <a id="editBtn" href="javascript:void(0)"  class="but-change"onclick="submitTab('editBtn','接口人信息列表')">修改</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
      </div>
	</body>
</html>
