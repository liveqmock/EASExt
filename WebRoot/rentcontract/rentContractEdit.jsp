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
	<title>房屋合同信息编辑</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#userTypeId").val() == 10){//财务人员进入时不可编辑行政字段
				$(".administration input").each(function(){ $(this).attr("readonly","readonly");});
				$("#rentstarttime,#rentendtime,#paymentcycle").attr("disabled","disabled");
			}else{
				$(".finance input").each(function(){ $(this).attr("readonly","readonly") ;});
				$("#payfirsttime").attr("disabled","disabled");
			}
			if($("#id").val()!='') $("#orgname").attr("readonly","readonly");
			$("#areaSqm, #rentyear, #housingDeposit,#propertyDeposit,#annualRent,#monthmoney")
				.addClass("easyui-validatebox").validatebox({validType: 'intOrFloat'});
			$("#paycount").addClass("easyui-validatebox").validatebox({validType: 'integer'});
			$("#lastcostcenter,#orgname,#city,#officeadd,#paycount")
			.addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
			$("#orgname").bind("change",function(){
				var orgnameVal = $("#orgname").val();
				if(orgnameVal=='投一' || orgnameVal=='投二' || orgnameVal=='投三' || orgnameVal=='基金团队'
					|| orgnameVal=='理财产品与投资策略' || orgnameVal=='保险团队')
				$("#ext1").val('2');
			});
			$("#officeadd,#orgname,#lastcostcenter,#city,#rentyear,#areaSqm,#paycount,#remark").validatebox({validType: 'njection'});
		})
	</script>
	</head>

	<body>
    <div class="tableForm">
		<form action="rent/rentContract!<s:if test='rentContract != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden"  name="userTypeId" id="userTypeId" value="${user.typeid }" />
			<input type="hidden" id="id" name="rentContract.id" value='<s:property value="rentContract.id"/>' />
			<s:if test='rentContract == null'>
				<input type="hidden" id="ext1" name="rentContract.ext1" value='1'/>
			</s:if>
            <div class="title">行政相关信息</div>
			<table>
				<tr class="administration">
					<td class="t-title" width="90">成本中心</td>
					<td colspan="3"><input class="t-text" style="width:528px" type="text" id="lastcostcenter" name="rentContract.lastcostcenter" 
						value='<s:property value="rentContract.lastcostcenter" />'/><span class="required">*</span></td>
     				<td class="t-title" width="90">使用部门</td>
     				<td><input class="t-text" type="text" id="orgname" name="rentContract.orgname" 
     					value='<s:property value="rentContract.orgname" />'/><span class="required">*</span></td> 
                </tr>
	
				<tr class="administration">
					<td class="t-title">办公室座落地点</td>
					<td colspan="3"><input class="t-text" style="width:528px" type="text" id="officeadd" name="rentContract.officeadd" 
	     				value='<s:property value="rentContract.officeadd" />'/><span class="required">*</span></td>
					<td class="t-title">地区</td>
					<td><input class="t-text" type="text" id="city" name="rentContract.city" value='<s:property value="rentContract.city" />'/><span class="required">*</span></td>
               </tr>
	
				<tr class="administration">
					<td class="t-title">租赁期限（月）</td>
					<td><input class="t-text" type="text" id="rentyear" name="rentContract.rentyear" 
		     			value='<s:property value="rentContract.rentyear" />'/></td>
					<td class="t-title" width="100">开始日期</td>
					<td><input class="t-text Wdate" type="text" id="rentstarttime" name="rentContract.rentstarttime" readonly 
		     			value="<s:property value="rentContract.rentstarttime" />" 
	     				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
					<td class="t-title">结束日期</td>
					<td><input class="t-text Wdate" type="text" id="rentendtime" name="rentContract.rentendtime" readonly 
		     			value="<s:property value="rentContract.rentendtime" />" 
		     			onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
                </tr>
	  			
				<tr class="administration">	
                	<td class="t-title">面积（平方米）</td>
					<td><input class="t-text" type="text" id="areaSqm" name="rentContract.areaSqm" 
		     			value='<s:property value="rentContract.areaSqm" />'/></td>
					<td class="t-title">付款次数</td>
					<td><input class="t-text" type="text" id="paycount" name="rentContract.paycount" 
		     			value='<s:property value="rentContract.paycount" />'/><span class="required">*</span></td>
					<td class="t-title">付款方式</td>
					<td colspan="5"><select name="rentContract.paymentcycle" id="paymentcycle">
			  			<s:iterator value="payTypes">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="rentContract.paymentcycle==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select></td>
				</tr>
			</table>
            <div class="title">财务相关信息</div>
            <table>
				
				<tr class="finance">
					<td class="t-title" width="90">纸质合同编号</td>
					<td><input class="t-text readonly" type="text" id="contractNum" name="rentContract.contractNum" 
	     		 		value='<s:property value="rentContract.contractNum" />'/></td>
					<td class="t-title" width="100">成本中心编号</td>
					<td><input class="t-text readonly" type="text" id="costcenterNum" name="rentContract.costcenterNum" 
		     			value='<s:property value="rentContract.costcenterNum" />'/></td>
					<td class="t-title" width="100">付款部门</td>
					<td><input class="t-text readonly" type="text" id="payorgName" name="rentContract.payorgName" 
		     			value='<s:property value="rentContract.payorgName" />'/></td>
            	</tr>

				<tr class="finance">
					<td class="t-title">汇入帐户业主</td>
					<td><input class="t-text readonly" type="text" id="toAccount" name="rentContract.toAccount" 
		     			value='<s:property value="rentContract.toAccount" />'/></td>
					<td class="t-title">房屋押金</td>
					<td><input class="t-text readonly" type="text" id="housingDeposit" name="rentContract.housingDeposit" 
		     			value='<s:property value="rentContract.housingDeposit" />'/></td>
					<td class="t-title">首付日期</td>
					<td><input class="t-text Wdate readonly" type="text" id="payfirsttime" name="rentContract.payfirsttime" readonly 
		     			value="<s:property value="rentContract.payfirsttime" />" 
	     				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
				</tr>

				<tr class="finance">
					<td class="t-title">物业押金</td>
					<td><input class="t-text readonly" type="text" id="propertyDeposit" name="rentContract.propertyDeposit" 
		     			value='<s:property value="rentContract.propertyDeposit" />'/></td>
					<td class="t-title">免租期（月）</td>
					<td><input class="t-text readonly" type="text" id="rentFreePeriod" name="rentContract.rentFreePeriod" 
		     			value='<s:property value="rentContract.rentFreePeriod" />'/></td>
					<td class="t-title">年租金（元）</td>
					<td><input class="t-text readonly" type="text" id="annualRent" name="rentContract.annualRent" 
		     			value='<s:property value="rentContract.annualRent" />'/></td>				
               </tr>

				<tr class="finance">
					<td class="t-title">合同月租金</td>
					<td colspan="5"><input class="t-text readonly" type="text" id="monthmoney" name="rentContract.monthmoney" 
		     			value='<s:property value="rentContract.monthmoney" />'/></td>
				</tr>
			</table>
            <div class="title">其他相关信息</div>
            <table>
				<tr class="other">
					<td class="t-title" width="90">状态</td>
					<td colspan="3"><select name="rentContract.status" id="status" >
			  			<s:iterator value="rentStatuses">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="rentContract.status==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select></td>
				</tr>

				<tr class="other">
					<td class="t-title">备注</td>
					<td colspan="3"><textarea rows="4" cols="1" name="rentContract.remark" 
			  			id="remark"><s:property value="rentContract.remark" /></textarea></td>
				</tr>
			</table>
            <div class="t-but">
			    <a id="editBtn" href="javascript:void(0)" onclick="submitTab('editBtn','房屋合同信息列表')" class="but-change"><s:if test='rentContract!=null'>修改</s:if><s:else>新增</s:else></a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
	</body>
</html>
