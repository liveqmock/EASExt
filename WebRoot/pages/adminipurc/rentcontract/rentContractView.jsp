<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibInclude.jsp" %>
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
		<title>采购合同详细信息</title>
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    	<style type="text/css"> table tr td{font-size: 12px;} </style>
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	</head>
	<body>
    <div class="tableForm">
		<table>
			<tr>
				<td class="t-title" width="100">合同编号</td>
				<td width="200"><s:property value="contractInfo.fnumber" /></td>
    			<td class="t-title" width="100">使用部门</td>
    			<td width="200"><s:property value="contractInfo.forgname" /></td>
    			<td class="t-title">城市</td>
				<td><s:property value="contractInfo.fcity" /></td>
			</tr>
			<tr>
				<td class="t-title" width="100">末级成本中心</td>
				<td width="200"><s:property value="contractInfo.flastcostcenter" /></td>
				<td class="t-title">办公室座落地点</td>
				<td width="200"><s:property value="contractInfo.fofficeaddr" /></td>
				<td class="t-title" width="100">合同类别</td>
				<td width="200">
					${elself:getDictionaryValue(listFcontractType,contractInfo.fcontracttypeid)}
				</td>
            </tr>
            <tr>
           		<td class="t-title" width="100">供应商名称</td>
				<td width="200"><s:property value="contractInfo.fsuppliername" /></td>
				<td class="t-title">合同额</td>
				<td><s:property value="contractInfo.fcontractamount" />元</td>
				<td class="t-title">合同期限</td>
				<td><s:property value="contractInfo.fdurtime" /></td>
            </tr>
            <tr>
				<td class="t-title">开始日期</td>
				<td>
					<fmt:formatDate value="${contractInfo.fbegindate}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="t-title">结束日期</td>
				<td>
					<fmt:formatDate value="${contractInfo.fenddate}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="t-title">月费用</td>
				<td><s:property value="contractInfo.fmonthcost" />元</td>
            </tr>
            <tr>
				<td class="t-title">打印设备押金</td>
				<td><s:property value="contractInfo.fprintdeposit" /></td>
				<td class="t-title">状态</td>
				<td>
					${contractInfo.fext10}
				</td>
				<td class="t-title">合同到期跟进</td>
				<td>
					${elself:getDictionaryValue(listContractStatus,contractInfo.fcontractstatus)}
				</td>
			</tr>
			 <tr>
			 	<td class="t-title">付款方式</td>
				<td>${elself:getDictionaryValue(listFpaytype,contractInfo.fpaytypeid)}</td>
				<td class="t-title">备注</td>
				<td colspan="3"><s:property value="contractInfo.fremark" /></td>
			</tr>
		</table>
    </div>
      <br/>
		<div class="t-but" style="text-align: center;">
				  <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">返回</a>
		</div>
	</body>
</html>
