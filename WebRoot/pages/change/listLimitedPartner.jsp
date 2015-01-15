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
 		.th1{
 			background-image:url('<%=path%>/images/left-top-right.gif');
 			border-bottom:0px;
 			border-right:0px;
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
 		.tab_line{
 			width:80%;
 			border: none;
 		}
 		.tab_line tr td {
 			border: none;
 		}
 		.tab_line tr{
 			border: none;
 		}
 	</style>
</head>
	<body>
	<div align="center">
	<form action="<%=basePath%>limitedpartner/limitedpartner!listLimitedPartnerPage" method="post">
         有限合伙名称 :<input type="text" id="fname_l2" name="fname_l2" value="${fname_l2 }"/> 
            <input type="submit"  value="查询">
            <input type="button" id="reset" value="取消">
</form>
  </div>
	<div align="center"> 
	  <table id="partnerTable" class="table_list">
        <tr class="ttr">
            <th>选中</th>  
            <th>编码 </th>  
            <th>名称 </th>
            <th>助记码</th>
        </tr>
        <s:iterator value="#listBaseUnit" id="array">  
            <tr>
            <td><s:checkbox id="check" name="check"/></td>  
            <td><s:property value="fcode"/></td>
            <td><s:property value="fname_l2"/></td>  
            <td><s:property value="fnumber"/></td>
            </tr>  
        </s:iterator>
        <tr>
	        		<td colspan="6">${fenye}</td>
	        	</tr>
        </table>
        </div>
        <hr/>
        <div align="center">
         <input type="button" id="ok" value="添加" />
         <input type="button" id="cancle" value="取消"/>
         <input type="button" value="返回" onclick="javascript:history.go(-1);">
        </div>
        </body>
        <SCRIPT type="text/javascript">

    	$(function(){
	    	//TablePage("#partnerTable",12);
            $("#ok").click(function(){
            	var fname_l2Array="";
                $("table tr td input[type='checkbox']:checked").each(function(i,j){
                	fname_l2Array+=$(this).parents('tr').children('td').eq(2).text()+","
                });
                if(fname_l2Array!=""){
                     window.location.href="<%=basePath%>limitedpartner/limitedpartner!addLimitedPartner?fname_l2Array="+fname_l2Array;
                }else
                    $.messager.alert("提示", "请选择需要添加的有限合伙人！");
            });

            $("#cancle").click(function(){
            	$("input[type='checkbox']").prop("checked",false);
            });

            $("#reset").click(function(){
            	$("#fname_l2").val("");
            	//window.location.href="<%=basePath%>limitedpartner/limitedpartner!listLimitedPartnerPage";
            	
            });
        });
    	
        </SCRIPT>
	
</html>
