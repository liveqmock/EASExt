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
		<title>例外人员名单编辑信息</title>
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
		$("#pmail").validatebox({validType: "emailcn['#pmail']"});
		$("#raplacemail").validatebox({validType: "emailcn['#raplacemail']"});
		})
	</script>
	</head>
	<body>
    	<div class="tableForm">
		<form action="person/black!<s:if test='blackListInfo != null'>update</s:if><s:else>save</s:else>" method="post" id="editForm">
			<input type="hidden" name="blackListInfo.pid" value='<s:property value="blackListInfo.pid"/>' />
            <div class="title">例外人员名单编辑信息</div>
			<table>
				<tr><td class="t-title" width="100">人员名称</td>
					<td><input  class="t-text" type="text" id="pname" name="blackListInfo.pname" 
						value='<s:property value="blackListInfo.pname" />'/></td>
					<td class="t-title" width="120">人员编码</td>
					<td><input class="t-text" type="text" id="pname" name="blackListInfo.pnumber" 
						value='<s:property value="blackListInfo.pnumber" />'/></td>
					<td class="t-title" width="120">人员邮箱</td>
					<td><input class="t-text" type="text" id="pmail" name="blackListInfo.pmail" 
			     		value='<s:property value="blackListInfo.pmail" />'/></td>
                </tr>
				<tr>
					<td class="t-title">替代人员名称</td>
					<td><input class="t-text" type="text" id="raplacename" name="blackListInfo.raplacename" 
						value='<s:property value="blackListInfo.raplacename" />'/></td>
					<td class="t-title">替代人员编码</td>
					<td><input class="t-text" type="text" id="raplacenumber" name="blackListInfo.raplacenumber" 
						value='<s:property value="blackListInfo.raplacenumber" />'/></td>
					<td class="t-title">替代人员邮箱</td>
					<td><input class="t-text" type="text" id="raplacemail" name="blackListInfo.raplacemail" 
						value='<s:property value="blackListInfo.raplacemail" />'/></td>
				</tr>
                <tr>
                	<td class="t-title">预警类型</td>
					<td><select name="blackListInfo.warntype">
						<s:iterator value="typeList">
							<option value="<s:property value="id"/>" <s:if test="blackListInfo.warntype == id">selected</s:if> >
								<s:if test="id==1">全部</s:if><s:else><s:property value="typename" /></s:else>
							</option>
						</s:iterator>
					</select></td>
					<td class="t-title">处理方式</td>
					<td colspan="3"><select name="blackListInfo.modeid">
						<s:iterator value="modeList">
							<option value="<s:property value="fid"/>" <s:if test="blackListInfo.modeid == fid">selected</s:if> >
								<s:property value="processmode" />
							</option>
						</s:iterator>
					</select></td>
                </tr>
			</table>
            <div class="t-but">
		    	<a href="javascript:void(0)" class="but-change" id="editBtn"  onclick="submitTab('editBtn','例外人员名单')"><s:if test='blackListInfo != null'>修改</s:if><s:else>新增</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
        </div>
	</body>
</html>
