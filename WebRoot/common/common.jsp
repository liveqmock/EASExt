<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
<!--
	//动态的取后台的值
	function sendAjax(url){
		var json = {};
		$.ajax({
			   type: "POST",
			   url: url,
			   async:false,//发送同步请求
			   dataType: "json",
			   success: function(data){
			   		json = data;
			   }
		});
		return json;
	}
//-->
</script>