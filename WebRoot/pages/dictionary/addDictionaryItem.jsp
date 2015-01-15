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
		$("#itemname,#itemid").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
		$("#itemname").validatebox({validType: "exist['<%=basePath %>dictionaryItem/dictionaryItemAction!ifItemNameDataExists?id=<s:property value="dictionaryBase.id"/>','#olditemname']"});
		$("#itemid").validatebox({validType: "exist['<%=basePath %>dictionaryItem/dictionaryItemAction!ifItemIDDataExists?id=<s:property value="dictionaryBase.id"/>','#olditemid']"});
		$("#itemid,#itemname").validatebox({validType: 'speci'});
		})
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/dictionaryItem/dictionaryItemAction!insert" method="post" id="editForm">
		<input type="hidden" id="baseid" name="dictionaryitem.baseid" value='<s:property value="dictionaryitem.baseid"/>' />
            <div class="title">数据信息编辑</div>
			<table>
				<tr>
				 <td class="t-title" width="100">类型关键字</td>
                    <td><input class="t-text readonly"  name="dictionaryBase.typeid" value='<s:property value="dictionaryBase.typeid"/>' readonly /></td>
                </tr>  
                <tr>
				 <td class="t-title" width="100">数据关键字</td>
                    <td><input class="t-text" id="itemid" name="dictionaryitem.itemid" value=""/><span class="required">*</span></td>
                    <input type="hidden" id="olditemid" value='<s:property value="dictionaryitem.itemid"/>'/> 
                </tr> 
				<tr>
				 <td class="t-title" width="100">数据值</td>
                    <td><input class="t-text" id="itemname" name="dictionaryitem.itemname" value="" /><span class="required">*</span></td>
                    <input type="hidden" id="olditemname" value='<s:property value="dictionaryitem.itemname"/>'/> 
             	</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','数据_详细信息')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
