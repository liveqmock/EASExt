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
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript"><!--
		//easy ui的查询的方法
		function doSearch(){  
	        $('#tt').datagrid('load',{  
	            username: $('#username').val()
	        });  
	    }  
	    /**
	    * 操作的方法
	    **/
	    function test(val,row){
	    	/**if(row.fforbidden==0){
		    	  return "<a href='javascript:void(0);' onclick='tt()'>修改密码</a>|<a href='javascript:void(0);' onclick='tt()'>禁用</a>"; 
	    	}else{
	    	     return "<a href='javascript:void(0);' onclick='tt()'>启用</a>"; 
	    	}**/
	    	return "<a href='javascript:void(0);' onclick='tt()'>修改密码</a>"; 
	    }
	    function tt(){
	    	alert("ok");
	    }
	    function isUserFun(val,row){
	    	if(row.fforbidden==0){
	    		return "正在使用";
	    	}else{
	    		return "<font color='red'>已禁用</font>";
	    	}
	    }
	</script>
</head>
<body>
	 <div style="padding:3px">  
    	<table border="0">
		  <tr>
		    <td>
		     	用户名:
		    </td>
		    <td align="right">
		     		<input id="username" style="line-height:26px;border:1px solid #ccc">
			</td>
		    <td align="right" valign="bottom">
		      	<input type="button" value="查询" onClick="doSearch()"></input>
		    </td>
		  </tr>
	 </table>
	</div>  
    <br/>
	<br/>
    <table id="tt" class="easyui-datagrid" url='<%=basePath %>admin/user!queryPageJson' 
    	style="width:1000px" title="" rownumbers="true" collapsible="true"
    	sortOrder="desc" singleSelect="true" fitColumns="true" striped="true" nowrap="true" 
    	pagination="true" pageSize="5" pageList="[5,10,15,20]" >  
        <thead>  
           <tr>  
               <th field="username" align="center" width="150">用户名</th>  
<!--               <th field="fforbidden" align="center" width="150" formatter="isUserFun">是否在用</th>  -->
               <th field="operator" align="center" width="200" formatter="test">操作</th>  
           </tr>  
       </thead>  
    </table>  
</body>
</html>