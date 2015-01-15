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
		$("#typename,#typeid").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
		$("#typeid").validatebox({validType: "exist['<%=basePath %>dictionary/dictionaryBaseAction!typeidIfExists','#oldtypeid']"});
		$("#typeid,#typename").validatebox({validType: 'speci'});
		})
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/dictionary/dictionaryBaseAction!<s:if test='dictionarybase != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
            <div class="title">字典信息编辑</div>
            <input type="hidden" id="id" name="dictionarybase.id" value='<s:property value="dictionarybase.id"/>' />
			<table>
					<tr>
					<td class="t-title" width="100">类型关键字</td>
					<s:if test='dictionarybase != null'>
					 <td><input class="t-text readonly" type="text" id="typeid" name="dictionarybase.typeid" value='<s:property value="dictionarybase.typeid" />' readonly/></td>
					 <input type="hidden" id="oldtypeid" value='<s:property value="dictionarybase.typeid"/>'/>
					</s:if>
					<s:else>
					 <td><input class="t-text" type="text" id="typeid" name="dictionarybase.typeid" value=""/></td>
					 <input type="hidden" id="oldtypeid" value='<s:property value="dictionarybase.typeid"/>'/>
					</s:else>
					</tr>
					<tr>
					<td class="t-title" width="100">字典类型名称</td>
                    <td><input class="t-text" type="text" id="typename" name="dictionarybase.typename" value='<s:property value="dictionarybase.typename" />'/></td>
             		</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','数据字典')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
