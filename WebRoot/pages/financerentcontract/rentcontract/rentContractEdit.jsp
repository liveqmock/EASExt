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
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){		
<%--			$("#administration div input").each(function(){ $(this).attr("readonly","readonly");});--%>
<%--			$("#rentStartTime,#rentEndTime,#paymentCycle").attr("disabled","disabled");--%>
			$("#areaSqm, #rentYear, #housingDeposit,#propertyDeposit,#annualRent,#monthMoney")
				.addClass("easyui-validatebox").validatebox({validType: 'intOrFloat'});
			$("#payCount").addClass("easyui-validatebox").validatebox({validType: 'integer'});
			$("#lastCostCenter,#orgName,#city,#officeAdd,#payCount,#signatory,#contractNum,#costCenterNum,#annualRent")
			.addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#orgName,#city,#rentYear,#areaSqm,#payCount,#contractNum,#costCenterNum,#payOrgName,#toAccount,#housingDeposit,#propertyDeposit,#rentFreePeriod,#annualRent,#monthMoney,#signatory,#remark").validatebox({validType: 'njection'});
			$("#contractNum").validatebox({validType: "exist['<%=basePath %>rent/financeRentContract!findContractNumExist','#oldContractNum']"});
			$("#officeAdd").validatebox({validType: "exist['<%=basePath %>rent/financeRentContract!findOfficeAddExist','#oldOfficeAdd']"});
		})
		function setCostCenter(lastCostCenter,costCenterNum,fid){
			$("#lastCostCenter").val(lastCostCenter);
			$("#costCenterNum").val(costCenterNum);
			$("#fid").val(fid);
		}
	</script>
	</head>
	<body>
	<div class="tableForm">
		<form action="rent/financeRentContract!<s:if test='financeRentContract != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" id="id" name="financeRentContract.id" value='<s:property value="financeRentContract.id"/>' />
            <div class="title">行政相关信息</div>
			<table>
				<tr>
                    <td class="t-title" width="90">成本中心</td>
                    <td colspan="3"><input style="width:528px;" class="t-text" type="text" onclick="openSan('win');" id="lastCostCenter" name="financeRentContract.lastCostCenter" 
                        value='<s:property value="financeRentContract.lastCostCenter" />' /><span class="required">*</span>
                    </td>
                    <td class="t-title" width="90">使用部门</td>
                    <td><input class="t-text" type="text" id="orgName"  name="financeRentContract.orgName" value='<s:property value="financeRentContract.orgName" />' /><span class="required">*</span>
                    </td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">办公室座落地点</td>
                    <td colspan="3">
                        <input style="width:528px;" class="t-text" type="text" id="officeAdd" name="financeRentContract.officeAdd" value='<s:property value="financeRentContract.officeAdd" />' /><span class="required">*</span>
                        <input type="hidden" id="oldOfficeAdd" value='<s:property value="financeRentContract.officeAdd" />' />
                    </td>
                    <td class="t-title">地区</td>
                    <td><input class="t-text" type="text" id="city" name="financeRentContract.city" value='<s:property value="financeRentContract.city" />' /><span class="required">*</span>
                    </td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">租赁期限（月）</td>
                    <td><input class="t-text" type="text" id="rentYear" name="financeRentContract.rentYear" value='<s:property value="financeRentContract.rentYear" />' /></td>
                    <td class="t-title" width="90">开始日期</td>
                    <td><input class="t-text Wdate" type="text" id="rentStartTime" name="financeRentContract.rentStartTime" readonly 
                        value="<s:date name="financeRentContract.rentStartTime" format="yyyy-MM-dd"/>" 
                        onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
                    <td class="t-title">结束日期</td>
                    <td><input class="t-text Wdate" type="text" id="rentEndTime" name="financeRentContract.rentEndTime" readonly 
                        value="<s:date name="financeRentContract.rentEndTime" 
                        format="yyyy-MM-dd"/>" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">面积（平方米）</td>
                    <td><input class="t-text" type="text" id="areaSqm" name="financeRentContract.areaSqm" value='<s:property value="financeRentContract.areaSqm" />' /></td>
                    <td class="t-title">付款次数</td>
                    <td><input class="t-text" type="text" id="payCount" name="financeRentContract.payCount" value='<s:property value="financeRentContract.payCount" />' />
                    <span class="required">*</span>
                    </td>
                    <td class="t-title">付款方式</td>
                    <td><select name="financeRentContract.paymentCycle" id="paymentCycle">
                            <s:iterator value="payTypes">
                                <option value="<s:property value='key'/>" <s:if test="financeRentContract.paymentCycle==key">selected</s:if> ><s:property value="value" /></option>
                            </s:iterator>
                        </select></td>
                </tr>
			</table>
            <div class="title">财务相关信息</div>
            <table>
			  	<tr>
                    <td class="t-title" width="90">纸质合同编号</td>
                    <td>
                        <input class="t-text easyui-validatebox" type="text" id="contractNum" name="financeRentContract.contractNum"
                            value='<s:property value="financeRentContract.contractNum" />' /><span class="required">*</span>
                        <input type="hidden" id="oldContractNum" value='<s:property value="financeRentContract.contractNum" />' />
                    </td>
                    <td class="t-title" width="90">成本中心编号</td>
                    <td><input class="t-text" type="text" id="costCenterNum" name="financeRentContract.costCenterNum" value='<s:property value="financeRentContract.costCenterNum" />' /><span class="required">*</span>
                    </td>
                    <td class="t-title" width="90">付款部门</td>
                    <td><input class="t-text" type="text" id="payOrgName" name="financeRentContract.payOrgName" value='<s:property value="financeRentContract.payOrgName" />' /></td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">汇入帐户业主</td>
                    <td><input class="t-text" type="text" id="toAccount" name="financeRentContract.toAccount" value='<s:property value="financeRentContract.toAccount" />' /></td>
                    <td class="t-title">房屋押金</td>
                    <td><input class="t-text" type="text" id="housingDeposit" name="financeRentContract.housingDeposit" value='<s:property value="financeRentContract.housingDeposit" />' /></td>
                    <td class="t-title">首付日期</td>
                    <td><input  class="t-text Wdate"type="text" id="payFirstTime" name="financeRentContract.payFirstTime" readonly 
                        value="<s:date name="financeRentContract.payFirstTime" format="yyyy-MM-dd"/>" 
                        onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">物业押金</td>
                    <td><input class="t-text" type="text" id="propertyDeposit" name="financeRentContract.propertyDeposit" value='<s:property value="financeRentContract.propertyDeposit" />' /></td>
                    <td class="t-title">免租期（月）</td>
                    <td><input class="t-text" type="text" id="rentFreePeriod" name="financeRentContract.rentFreePeriod" value='<s:property value="financeRentContract.rentFreePeriod" />' /></td>
                    <td class="t-title">年租金（元）</td>
                    <td><input class="t-text" type="text" id="annualRent" name="financeRentContract.annualRent" value='<s:property value="financeRentContract.annualRent" />' /><span class="required">*</span></td>
                </tr>
			  	
			  	<tr>
                    <td class="t-title">合同月租金</td>
                    <td><input class="t-text" type="text" id="monthMoney" name="financeRentContract.monthMoney" value='<s:property value="financeRentContract.monthMoney" />' /></td>
                    <td class="t-title">签署合同公司</td>
                    <td><input class="t-text" type="text" id="signatory" name="financeRentContract.signatory" value='<s:property value="financeRentContract.signatory" />' /><span class="required">*</span>
                    </td>
                    <td class="t-title">状态</td>
                    <td><select name="financeRentContract.status" id="status">
                            <s:iterator value="rentStatuses">
                                <option value="<s:property value='key'/>" <s:if test="financeRentContract.status==key">selected</s:if> ><s:property value="value" /></option>
                            </s:iterator>
                        </select></td>
                </tr>
			  		
			  	<tr>
                    <td class="t-title">备注</td>
                    <td colspan="5"><textarea rows="2" name="financeRentContract.remark" id="remark"><s:property value="financeRentContract.remark" /></textarea></td>
                </tr>
			</table>
             <div class="t-but">
			    <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','合同信息列表')"><s:if test='financeRentContract != null'>修改</s:if><s:else>新增</s:else></a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
             </div>
			<input type="hidden" id="winURL" value="<%=path%>/pages/financerentcontract/rentcontract/selectCostcenter.jsp"/>
			<input type="hidden" id="fid" name="financeRentContract.ext1" value='<s:property value="financeRentContract.ext1" />'/>
		</form>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
 </div>
</body>
</html>
