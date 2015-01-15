<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>房屋合同详细信息</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>
	<body>
    <div class="tableForm">
    	 <div class="title">行政相关信息</div>
		<table>
			<tr>
				<td class="t-title" width="100">成本中心：</td>
				<td colspan="3"><s:property value="rentContract.lastcostcenter" /></td>
    			<td class="t-title" width="100">使用部门：</td>
    			<td width="200"><s:property value="rentContract.orgname" /></td>
			</tr>

			<tr>
				<td class="t-title">办公室座落地点：</td>
				<td colspan="3"><s:property value="rentContract.officeadd" /></td>
				<td class="t-title">地区：</td>
				<td><s:property value="rentContract.city" /></td>
			</tr>
		  	
			<tr>
				<td class="t-title">租赁期限（月）：</td>
				<td width="200"><s:property value="rentContract.rentyear" /></td>
				<td class="t-title" width="100">开始日期：</td>
				<td width="200"><s:property value="rentContract.rentstarttime" /></td>
				<td class="t-title">结束日期：</td>
				<td><s:property value="rentContract.rentendtime" /></td>
			</tr>
  			
			<tr class="administration">
				<td class="t-title">面积（平方米）：</td>
				<td><s:property value="rentContract.areaSqm" /></td>
				<td class="t-title">付款次数：</td>
				<td><s:property value="rentContract.paycount" /></td>
				<td class="t-title">付款方式：</td>
				<td><s:iterator value="payTypes">
		  				<s:if test="rentContract.paymentcycle==key"><s:property value="value" /></s:if>
		  			</s:iterator></td>
			</tr>
		</table>
        <div class="title">财务相关信息</div>
        <table>	
			<tr>
				<td class="t-title" width="100">纸质合同编号：</td>
				<td width="200"><s:property value="rentContract.contractNum" /></td>
				<td class="t-title" width="100">成本中心编号：</td>
				<td width="200"><s:property value="rentContract.costcenterNum" /></td>
				<td class="t-title" width="100">付款部门：</td>
				<td width="200"><s:property value="rentContract.payorgName" /></td>
            </tr>
            <tr>
				<td class="t-title">汇入帐户业主：</td>
				<td><s:property value="rentContract.toAccount" /></td>
				<td class="t-title">房屋押金：</td>
				<td><s:property value="rentContract.housingDeposit" /></td>
				<td class="t-title">首付日期：</td>
				<td><s:property value="rentContract.payfirsttime" /></td>
            </tr>
            <tr>
				<td class="t-title">物业押金：</td>
				<td><s:property value="rentContract.propertyDeposit" /></td>
				<td class="t-title">免租期（月）：</td>
				<td><s:property value="rentContract.rentFreePeriod" /></td>
				<td class="t-title">年租金（元）：</td>
				<td><s:property value="rentContract.annualRent" /></td>
            </tr>
            <tr>
				<td class="t-title">合同月租金：</td>
				<td colspan="5"><s:property value="rentContract.monthmoney" /></td>
			</tr>
		</table>
        <div class="title">其他相关信息</div>
        <table>	
			<tr>
				<td class="t-title" width="100">状态：</td>
				<td colspan="3"><s:iterator value="rentStatuses">
  					<s:if test="rentContract.status==key"><s:property value="value" /></s:if></s:iterator></td>
			</tr>

			<tr>
				<td class="t-title">备注：</td>
				<td colspan="3"><s:property value="rentContract.remark" /></td>
			</tr>
		</table>
    </div>
	</body>
</html>
