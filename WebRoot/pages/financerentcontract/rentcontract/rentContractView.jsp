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
		<title>My JSP 'update.jsp' starting page</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
	</head>

	<body>
    <div class="tableForm">
   	    <div class="title">行政相关信息</div>
		<table>	
			<tr>
                <td class="t-title" width="100">成本中心：</td>
                <td colspan="3"><s:property value="financeRentContract.lastCostCenter" /></td>
                <td class="t-title" width="100">使用部门：</td>
                <td width="150"><s:property value="financeRentContract.orgName" /></td>
            </tr>
			<tr>
                <td class="t-title">办公室座落地点：</td>
                <td colspan="3"><s:property value="financeRentContract.officeAdd" /></td>
                <td class="t-title">地区：</td>
                <td><s:property value="financeRentContract.city" /></td>
            </tr>
			<tr>
                <td class="t-title">租赁期限（月）：</td>
                <td width="150"><s:property value="financeRentContract.rentYear" /></td>
                <td class="t-title" width="100">开始日期：</td>
                <td width="150"><s:date name="financeRentContract.rentStartTime" format="yyyy-MM-dd"/></td>
                <td class="t-title">结束日期：</td>
                <td><s:date name="financeRentContract.rentEndTime" format="yyyy-MM-dd"/></td>
            </tr>
			<tr>
                <td class="t-title">面积（平方米）：</td>
                <td><s:property value="financeRentContract.areaSqm" /></td>
                <td class="t-title">付款次数：</td>
                <td><s:property value="financeRentContract.payCount" /></td>
                <td class="t-title">付款方式：</td>
                <td><s:iterator value="payTypes">
                    <s:if test="financeRentContract.paymentCycle==key">
                        <s:property value="value" />
                    </s:if>
                </s:iterator></td>
            </tr>
		</table>
        <div class="title">财务相关信息</div>
        <table>
			
			<tr>
                <td class="t-title" width="100">纸质合同编号：</td>
                <td width="150"><s:property value="financeRentContract.contractNum" /></td>
                <td class="t-title" width="100">成本中心编号：</td>
                <td width="150"><s:property value="financeRentContract.costCenterNum" /></td>
                <td class="t-title" width="100">付款部门：</td>
                <td width="150"><s:property value="financeRentContract.payOrgName" /></td>
            </tr>
			<tr>
                <td class="t-title">汇入帐户（业主）：</td>
                <td><s:property value="financeRentContract.toAccount" /></td>
                <td class="t-title">房屋押金：</td>
                <td><s:property value="financeRentContract.housingDeposit" /></td>
                <td class="t-title">首付日期：</td>
                <td><s:date name="financeRentContract.payFirstTime" format="yyyy-MM-dd"/></td>
            </tr>
			<tr>
                <td class="t-title">物业押金：</td>
                <td><s:property value="financeRentContract.propertyDeposit" /></td>
                <td class="t-title">免租期(月)：</td>
                <td><s:property value="financeRentContract.rentFreePeriod" /></td>
                <td class="t-title">年租金（元）：</td>
                <td><s:property value="financeRentContract.annualRent" /></td>
            </tr>
            <tr>
                <td class="t-title">合同月租金：</td>
                <td><s:property value="financeRentContract.monthMoney" /></td>
                <td class="t-title">签署合同公司：</td>
                <td><s:property value="financeRentContract.signatory" /></td>
                <td class="t-title">状态：</td>
                <td><s:iterator value="rentStatuses">
                        <s:if test="financeRentContract.status==key"><s:property value="value" /></s:if>
                    </s:iterator></td>
           </tr>
	  	   <tr>
           		<td class="t-title">备注：</td>
	  			<td colspan="5"><s:property value="financeRentContract.remark" /></td>
           </tr>
		</table>
	</body>
</html>
