<%@ page language="java" import="java.util.*,com.change.eas.partner.bean.LawFile" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		String city=	request.getParameter("city");
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
 	<script type="text/javascript">
	function initCity(xxx){
		var obj=$(xxx).val();
		var city = $("#city");
		city.empty();
		city.append('<option value="">请选择城市</option>');
		if(obj=="投资理财一部"){
			city.append("<option value='上海' <%if("上海".equals(city)){%> selected='selected' <%} %>>上海</option>");
			city.append("<option value='成都' <%if("成都".equals(city)){%> selected='selected' <%} %>>成都</option>");
			city.append("<option value='九江/共青城' <%if("九江/共青城".equals(city)){%> selected='selected' <%} %>>九江/共青城</option>");
			}
		else if(obj=="投资理财二部"){
			city.append("<option value='青岛' <%if("青岛".equals(city)){%> selected='selected' <%} %>>青岛</option>");
			city.append("<option value='烟台' <%if("烟台".equals(city)){%> selected='selected' <%} %>>烟台</option>");
			city.append("<option value='长春' <%if("长春".equals(city)){%> selected='selected' <%} %>>长春</option>");
			}
		else if(obj=="投资理财三部"){
			city.append("<option value='无锡' <%if("无锡".equals(city)){%> selected='selected' <%} %>>无锡</option>");
			city.append("<option value='南京' <%if("南京".equals(city)){%> selected='selected' <%} %>>南京</option>");
			city.append("<option value='苏州' <%if("苏州".equals(city)){%> selected='selected' <%} %>>苏州</option>");
			city.append("<option value='芜湖' <%if("芜湖".equals(city)){%> selected='selected' <%} %>>芜湖</option>");
			city.append("<option value='宁波' <%if("宁波".equals(city)){%> selected='selected' <%} %>>宁波</option>");
			}else{

			}
	}
	 $(document).ready(function(){
		 initCity($("#department"))
	});
	</script>
</head>
	<body>
	<br/><br/>
	<div align="center">
	<form action="<%=basePath%>limitedpartner/limitedpartner!lawfilelist" method="post">
		<table class="tab_line">
			<tr>
				<td>部门</td>
				<td>
					<select onchange="initCity(this)" name="department" id="department" class="select" style="width: 120px;" >
						<option value="" <%if("".equals(request.getParameter("department"))||null==request.getParameter("department")){%> selected='selected' <%} %> >请选择部门</option>
						<option value="投资理财一部" <%if("投资理财一部".equals(request.getParameter("department"))){%> selected='selected' <%} %>>投资理财一部</option>
						<option value="投资理财二部" <%if("投资理财二部".equals(request.getParameter("department"))){%> selected='selected' <%} %>>投资理财二部</option>
						<option value="投资理财三部" <%if("投资理财三部".equals(request.getParameter("department"))){%> selected='selected' <%} %>>投资理财三部</option>
					</select>
				</td>
				<td>城市</td>
				<td>
					<select name="city" id="city" class="select" style="width: 120px;" >
						<option value="">请选择城市</option>
					</select>
				</td>
				<td>有限合伙人名称</td>
				<td>
					<input name="limitedpartnername" class="t-text"  id="name" type="text" value="<%=request.getParameter("limitedpartnername")==null?"":request.getParameter("limitedpartnername") %>">
				</td>
				<td>
		            <input type="submit" value="查询">
		            <input type="reset" value="取消">
				</td>
			</tr>
		</table>
	</form>
  </div>
	<div align="center"> 
	  <table id="partnerTable" class="table_list" style="margin-top:5px;"  >
		  <thead> 
		        <tr class="ttr">
		            <th>部门</th>  
		            <th>地区</th>
		            <th>有限合伙名称</th>
		            <th>完成数量</th> 
		            <th>未完成数量</th> 
		            <th>操作</th>
		        </tr>
	        </thead>
	        <tbody> 
	        <%List<LawFile> list= (List<LawFile>)request.getAttribute("listLawFile");
	        for(LawFile lf : list){
	        %>  
	            <tr>
		            <td><%=lf.getDepartment()%></td>  
		            <td><%=lf.getCity()%></td>
		            <td><%=lf.getLimitedpartnername()%></td>  
		            <td><%=lf.getDone()%></td>
		            <td><%=lf.getUndone()%></td>  
		            <td><a href="<%=basePath%>limitedpartner/limitedpartner!queryDocumentFile?pid=<%=lf.getLimitedpartnerid()%>">查看法律文件</a></td>
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
