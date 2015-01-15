<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="elself" uri="/eltag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>测试EL表达式</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%--   		测试: ${el:countChar("ad") }--%>
<%--   		测试自己定义的EL表达式:${el:validIsAdmin(personrole,87));--%>
<%--		${personrole }--%>
<%--		${el:validIsAdmin(personrole,87)}--%>
			${personrole }<br/>
			${el:validIsAdmin(personrole,87)}
  </body>
</html>
