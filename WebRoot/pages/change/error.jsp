<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>导入文件有误</title>
	
</head>
	<body>

   <h5>导入失败，所导入的文件中的第${row}行存在空值（或者身份证格式有误，接受15位到18位的居民身份证号码，或者GP出资额格式有误，只能接受数字，或者出资时间格式有误）!请检查该行再导入</h5>
   <input type="button" value="返回" onclick="javascript:history.go(-1);">
	</body>
</html>