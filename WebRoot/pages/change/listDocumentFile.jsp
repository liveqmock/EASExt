<%@ page language="java" import="java.util.*,java.io.File,com.change.eas.util.Config" pageEncoding="utf-8"%>
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
	<title>法律文件查询</title>
	<style type="text/css">
	 .divcss5{ border:1px solid #F00; width:3000px; height:10000px}
	</style>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/change/page.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/jquery.table.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/util.js"></script>
 	<style type="text/css">
 		.ttr{
 			background-image: url('<%=path%>/images/content-bg.gif');
 			border-left-color: #dddddd;
 		}
 		.table_list tbody tr:hover{
 			background-color: #dddddd;
 		}
 		.table_list tbody tr td{
 			border-left: 0px;
 			border-right: 0px;
 		}
 		.table_list thead tr th{
 			border-left: 0px;
 			border-right: 0px;
 		}
 	</style>
 	<script type="text/javascript">
 	function downloadFile(){
 		var items = $("input[name='item'][type='checkbox']:checked");
 		if(items.length==0){
	 		$.messager.alert("提示", "请选择下载项！");
	 	}
 		else{
	 		var ids = "";
	 		for(var i=0;i<items.length;i++){
				ids +=items[i].value+";";
	 	 	}
	 	 	window.location.href="<%=basePath%>limitedpartner/limitedpartner!downloadFile?ids="+ids;
		}
 	 }
	 function boxChange(){
		 var is = $("#box").is(':checked');
		$("input[name='item'][type='checkbox']").attr("checked",is);
	}
 	</script>
</head>
	<body>
	<br/><br/>
	<div> 
		<label onclick="downloadFile()">下载</label>
	  <table id="partnerTable" width="95%" class="table_list">
		  <thead> 
	        <tr class="ttr">
	            <th><input id="box" onclick="boxChange()" type="checkbox" name="box"/></th>  
	            <th>序号</th>  
	            <th>法律文件名称</th>
	            <th>制作人</th>
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody> 
	        <%int i=1; %>
	        <s:iterator value="#listDocumentFile" id="array">  
	          <tr>
	            <td><input type="checkbox" value="${id}" name="item"/></td>  
	            <td><%=i++ %></td>  
	            <td><a href="<%=Config.getKey("word.url")+"/Document.jsp?RecordID="%><s:property value="id"/>"><s:property value="filename"/></a></td>
	            <td><s:property value="makeuser"/></td>  
	            <td>
	            	<a href="<%=Config.getKey("word.url")+"/Document.jsp?RecordID="%><s:property value="id"/>">修改</a>
					<a href="javascript:$.messager.confirm('警告', '确定需要删除此信息吗?', function(r){
                    if (r){
                    location.href = '<%=basePath%>limitedpartner/limitedpartner!deleteDocumentFile?documentFile.limitedpartnerid=${pid}&documentFile.id=${id}';
                   }
                  });">删除</a>
				</td>
	          </tr>  
	        </s:iterator>
	        	<tr>
	        		<td colspan="4">${fenye}</td>
	        	</tr>
	        </tbody> 
        </table>
        </div>
        <object id="WebOffice" width="0%" height="0%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="<%=request.getContextPath()%>/iWebOffice2009.cab#version=10,7,2,4"></object>
        </body>
</html>
