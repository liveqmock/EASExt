<%@ page language="java" import="java.util.*,com.change.eas.partner.bean.Mark" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			Mark mark = (Mark)request.getAttribute("mark");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>标签管理</title>
	<style type="text/css">
	 .divcss5{ border:1px solid #F00; width:3000px; height:10000px}
	</style>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=path%>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/change/page.css">
 	<link rel="stylesheet" href="<%=path%>/inc/style.css" />
 	<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
 	<script type="text/javascript" src="<%=path%>/js/change/jquery.table.js"></script>
 	<script type="text/javascript" src="<%=path%>/js/change/util.js"></script>
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
 	<script type="text/javascript">
	 function addMark(){
		window.location.href="<%=basePath%>limitedpartner/limitedpartner!addMarkPage";
	}
	</script>
</head>
	<body>
	<br/><br/>
	<div align="center">
	<form action="<%=basePath%>limitedpartner/limitedpartner!listMark" method="post">
		<label style="text-align: center;font-size: 20px;">标签管理</label><br/><br/>
		<table width="100%" border="3" class="tab_line">
			<tr>
				<td align="right">名称:</td>
				<td align="left">
					<input type="text" value="${mark.bookmarkname}" name="mark.bookmarkname" />
				</td>
				<td align="right">类型:</td>
				<td align="left">
					<select name="mark.isloop" class="select" style="width: 120px;" >
						<option value="-1" selected="selected">全部标签</option>
						<option value="0" <%if(0==mark.getIsloop()){%> selected='selected' <%} %>>普通标签</option>
					<!--	<option value="2" <%if(2==mark.getIsloop()){%> selected='selected' <%} %>>特殊标签</option>
					 	<option value="100" <%if(100==mark.getIsloop()){%> selected='selected' <%} %>>表格定位标签</option>
						<option value="101" <%if(101==mark.getIsloop()){%> selected='selected' <%} %>>表格循环标签</option>
						<option value="102" <%if(102==mark.getIsloop()){%> selected='selected' <%} %>>表格普通标签</option>
						<option value="103" <%if(103==mark.getIsloop()){%> selected='selected' <%} %>>表格表头标签</option>
					 -->	
						<option value="1001" <%if(1001==mark.getIsloop()){%> selected='selected' <%} %>>循环标签</option>
						<option value="1002" <%if(1002==mark.getIsloop()){%> selected='selected' <%} %>>不循环标记</option>
						<option value="1003" <%if(1003==mark.getIsloop()){%> selected='selected' <%} %>>时间标签</option>
						
						
					</select>
				</td>
				<td>
		            <input type="submit" value="查询">
		            <input type="reset" onclick="addMark()" value="添加">
				</td>
			</tr>
		</table>
	</form>
  </div>
	<div align="center"> 
	  <table id="partnerTable" class="table_list" style="margin-top:5px;"  >
		  <thead> 
		        <tr class="ttr">
		            <th>标签名称</th>  
		            <th>标签解释</th>
		            <th>标签备注</th>
		            <th>标签字段</th> 
		            <th>标签类型</th> 
		            <th>操作</th>
		        </tr>
	        </thead>
	        <tbody> 
	        <%List<Mark> list= (List<Mark>)request.getAttribute("list");
	        for(Mark lf : list){
	        %>  
	            <tr>
		            <td><%=lf.getBookmarkname()%></td>  
		            <td><%=lf.getBookmarkdesc()%></td>
		            <td><%=lf.getBookmarktext()%></td>  
		            <td><%=lf.getTablefield()%></td>
		            <td><%=lf.isLoopString()%></td>  
		            <td><label style="cursor: pointer;" onclick="javascript:$.messager.confirm('警告', '确定需要删除此信息吗?', function(r){
                if (r){
                    location.href = '<%=basePath%>limitedpartner/limitedpartner!delMark?mark.bookmarkid=<%=lf.getBookmarkid()%>';
                   }
                  });">删除</label></td>
	            </tr>  
	        <%}%>
	        	<tr>
	        		<td colspan="6">${fenye}</td>
	        	</tr>
	        </tbody> 
        </table>
     </div>
  </body>
</html>
