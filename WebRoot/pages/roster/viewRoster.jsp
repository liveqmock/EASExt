<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
		<base href="<%=basePath%>">
		<title>花名册详细信息</title>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
   		 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    	 <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
  <body>
    <div class="tableForm">
     	<div class="title">基本信息</div>
		<table id="viewTab">
		<tr>
		<td class="t-title" width="120">5位员工编号</td>
		<td width="150"><s:property value="roster.fnumber"/></td>
		<td class="t-title" width="120">12位员工编号</td>
		<td width="150"><s:property value="roster.longfnumber"/></td>
		<td class="t-title">工作城市</td>
		<td><s:property value="roster.fcityname"/></td>
		</tr>
		
		<tr>
		<td class="t-title">姓名</td>
		<td><s:property value="roster.fname"/></td>
		<td class="t-title">性别</td>
		<td><s:property value="roster.fgendername"/></td>
		<td class="t-title">年龄</td>
		<td><s:property value="roster.fage"/></td>
		</tr>
		
		<tr>
		<td class="t-title">人员状态</td>
		<td><s:property value="roster.fpersonstatusname"/></td>
		<c:forEach items="${sessionScope.personrole}" var="personrole" >
				<c:if test="${personrole != '589'}"> 
		<td class="t-title">电子邮箱</td>
		<td><s:property value="roster.femail"/></td>
		<td class="t-title" width="120">身份证号码</td>
		<td width="150"><s:property value="roster.fcardnum"/></td>
		</c:if>
		</c:forEach>
		</tr>
		
		<c:forEach items="${sessionScope.personrole}" var="personrole" >
				<c:if test="${personrole != '589'}"> 
		<tr>
	  	<td class="t-title">手机号码</td>
	  	<td colspan="5"><s:property value="roster.ext1"/></td>
	  	</tr>
				</c:if>
		</c:forEach>
		</table>
		
	 </div>
	 <div class="tableForm">	
        <div class="title">部门信息</div>
	  	<table id="viewTab">
	  	<tr>
	  	<td class="t-title">行政组织</td>
	  	<td colspan="5"><s:property value="roster.fadminfid"/></td>
	  	</tr>
	  	
	  	<tr>
		<td class="t-title" width="120">职类</td>
		<td width="150"><s:property value="roster.fpositiontypename"/></td>
		<td class="t-title" width="120">岗位</td>
		<td width="150"><s:property value="roster.fpositionname"/></td>
		<td class="t-title" width="160">职级</td>
		<td width="150"><s:property value="roster.fpositionlevelname"/></td>
		</tr>
		
		<tr>
		<td class="t-title">三级部门</td>
		<td><s:property value="roster.fthreeunitname"/></td>
		<td class="t-title">四级部门</td>
		<td><s:property value="roster.ffourunitname"/></td>
		<td class="t-title">原四级部门</td>
		<td><s:property value="roster.foldfourunitname"/></td>
		</tr>
		
		<tr>
		<td class="t-title">五级部门</td>
		<td><s:property value="roster.ffiveunitname"/></td>
		<td class="t-title">六级部门</td>
		<td><s:property value="roster.fsixunitname"/></td>
		<td class="t-title">团队</td>
		<td><s:property value="roster.fteam"/></td>
		</tr>
		
		<tr>
		<td class="t-title">上级团队经理</td>
		<td><s:property value="roster.parentteammanager"/></td>
		<td class="t-title">上级大团队经理</td>
		<td><s:property value="roster.parentbigteammanager"/></td>
		<td class="t-title">上级营销部经理</td>
		<td><s:property value="roster.parentsalemanager"/></td>
		</tr>
		
		<tr>
		<td class="t-title">大团队经理任职日期</td>
		<td><fmt:formatDate value="${roster.fteamleaderdate}" pattern="yyyy-MM-dd"/></td>
		<td class="t-title">保险从业资格证</td>
		<td><s:property value="roster.insurancecertname"/></td>
		<td class="t-title">基金从业资格证</td>
		<td><s:property value="roster.fundcertname"/></td>
		</tr>
		
		<tr>
		<td class="t-title">内部推荐人姓名</td>
		<td><s:property value="roster.frecommendname"/></td>
		<td class="t-title">入司日期</td>
		<td><fmt:formatDate value="${roster.fmobdate}" pattern="yyyy-MM-dd"/></td>
		<td class="t-title">招聘渠道</td>
		<td><s:property value="roster.frecruitmentchannelsname"/></td>
		</tr>
		
		<tr>
		<td class="t-title">转签之前是否是hope人员成员</td>
		<td><s:property value="roster.fishopepersonname"/></td>
		<td class="t-title">理财规划师持证</td>
		<td colspan="3"><s:property value="roster.financialpalncerttext"/></td></td>
		</tr>
		
		<tr>
		<td class="t-title">入职日期</td>
		<td><fmt:formatDate value="${roster.fentrydate}" pattern="yyyy-MM-dd"/></td>
		<td class="t-title">转正日期</td>
		<td><fmt:formatDate value="${roster.fconfirmdate}" pattern="yyyy-MM-dd"/></td>
		<td class="t-title">离职日期</td>
		<td><fmt:formatDate value="${roster.fleavedate}" pattern="yyyy-MM-dd"/></td>
		</tr>
		
		<tr>
		<td class="t-title">负责营销部</td>
		<td colspan="4"><%=request.getAttribute("datapowervalue")%></td>
		</tr>
		
		<tr>
	  	<td class="t-title">备注</td>
	  	<td><s:property value="roster.fremarks"/></td>
	  	</tr>
	  	</table>
     </div>
  </body>
</html>