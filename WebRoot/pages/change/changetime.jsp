<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<title>有限合伙人列表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	// 效验日期格式
	   function CheckDate(str){
		    //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
		    var Expression=/^((((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13578]|1[02])(\/|\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13456789]|1[012])(\/|\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)0?2(\/|\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/; 
			var objExp=new RegExp(Expression);
			if(objExp.test(str)==true){
				return false;
			}else{
				return true;
			}
	};
	
    function setParther(){
        //alert(parent.window.getElementById("time2"));
		//parent.window.getElementById("time2").value=document.getElementById("changetime");
		parent.window.returnValue=document.getElementById("changetime").value;
    	window.close();
    }
	   

	</script>
	</head>
	<body>
	<div align="center">
	<% String time = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); %>
               变更时间:<input type="text" id="changetime" name="changetime" onclick="WdatePicker()" readonly="readonly"value="<%=time%>"/>
      <input type="button" value="提交" onclick="setParther()">
	</div>
	</body>
</html>
