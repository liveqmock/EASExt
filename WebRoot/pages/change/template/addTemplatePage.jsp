<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传模板</title>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
<script type="text/javascript">
	function getCityList(obj){
		var dept = obj.value;
		$.post("<%=request.getContextPath()%>/limitedpartner/limitedpartner!getCityList",
						{
							"dept" : dept
						}, function(data) {
							$("#cityId").empty();
							$("#cityId").html(data);
						}, "html");
	}
	function checkForm() {
		var flag = true;
		var dept = $("#deptment").val();
		if (null==dept||dept==-1) {
			$.messager.alert("错误","请选择部门");
			flag = false;
		}
		if (null==$("#cityId").val()||$("#cityId").val()==-1) {
			$.messager.alert("错误","请选择城市");
			flag = false;
		}
		if (null==$("#file").val()) {
			$.messager.alert("错误","请选择要添加的模板");
			flag = false;
		}
		if (flag) {
			document.getElementById("form").submit();
		}
	}
</script>
</head>
<body>
	<fieldset>
		<legend>新增模板</legend>
		<form id="form" method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/limitedpartner/limitedpartner!addTemplate">
			<table>
				<tr>
					<td>部门</td>
					<td><select onchange="getCityList(this)" id="deptment"
						name="dept">
							<option selected="selected" value="-1">请选择部门</option>
							<c:forEach items="${deptList}" var="dept">
								<option value="${dept.partherCode}">${dept.partherName}</option>
							</c:forEach>
					</select></td>
					<td></td>
				</tr>
				<tr>
					<td>城市</td>
					<td><select id="cityId" name="cityIdId">
							<option value="-1">请选择城市</option>
					</select></td>
					<td></td>
				</tr>
				<tr id="template">
					<td>模板</td>
					<td><input id="file" type="file" name="file" /></td>
					<td></td>
				</tr>
				<tr>
					<td><input type="reset" value="重置"></td>
					<td><input type="button" onclick="checkForm()" value="提交"></td>
					<td></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<table>
	</table>
</body>
</html>