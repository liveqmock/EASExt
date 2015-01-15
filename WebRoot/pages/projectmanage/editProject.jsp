<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/record_jsp_style.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		$("#companyName,#borrower").addClass("easyui-validatebox").validatebox({required: true,validType:'njection',missingMessage: '必填'});
		$("#lenders,#repaymentAccount,#bankAccount,#account,#authentication").addClass("easyui-validatebox").validatebox({required: false,validType:'njection'});
		$("#money,#mMangExpense,#mlyinterest").validatebox({required: false,validType:'intOrFloat',missingMessage: '必须是数字'});
		$("#money").validatebox({required: true,validType:'intOrFloat',missingMessage: '必填'});
		$("#term").validatebox({required: false,validType:'integer',missingMessage: '必须是整数'});
		$("#loanNumber").validatebox({validType: "exist['<%=basePath %>/project/projectAction!findLoanNumberExist','#oldloanNumber']"});
		 //加载出借方式
	       var json = sendAjax("<%=basePath%>/project/projectAction!selectLoanway");
			for(var i=0;i<json.length;i++){
			  if($("#loanwayid").val()==(json[i].FID)){
				 var $opt = $("<option value="+json[i].FID+" selected='selected'>"+json[i].LOANWAY +"</option>");
				}else{
				 var $opt = $("<option value="+json[i].FID+" >"+json[i].LOANWAY +"</option>");
				}
				$("#selectloanway").append($opt);
			}
			
			//加载还款方式
			var json = sendAjax("<%=basePath%>/project/projectAction!selectSituationname");
			for(var i=0;i<json.length;i++){
			  if($("#repaymentwayid").val()==(json[i].FID)){
				var $opt = $("<option value="+json[i].FID+" selected='selected'>"+json[i].SITUATIONNAME +"</option>");
				}else{
				var $opt = $("<option value="+json[i].FID+" >"+json[i].SITUATIONNAME +"</option>");
				}
				$("#Situationname").append($opt);
			}
			//加载项目状态
			var json = sendAjax("<%=basePath%>/project/projectAction!selectState");
			for(var i=0;i<json.length;i++){
			   if($("#statefid").val()==(json[i].FID)){
				var $opt = $("<option value="+json[i].FID+" selected='selected'>"+json[i].STATENAME +"</option>");
				}else{
				var $opt = $("<option value="+json[i].FID+" >"+json[i].STATENAME +"</option>");
				}
				$("#state").append($opt);
			}
			//加载贷后管理负责人(PM)
			var json = sendAjax("<%=basePath%>/project/projectAction!selectPm");
			for(var i=0;i<json.length;i++){
				<s:if test='projectManage != null'>
		 			 if($("#pmuserid").val()==(json[i].USERID)){
					  var $opt = $("<option value="+json[i].USERID+" selected='selected'>"+json[i].PMNAME +"</option>");
					 }else{
				 	 var $opt = $("<option value="+json[i].USERID+" >"+json[i].PMNAME +"</option>");
					 } 
					$("#pm").append($opt);
		   		</s:if>
		   		<s:if test='projectManage == null'>
		   		  if($("#username").val()==(json[i].PMNAME)){
					  var $opt = $("<option value="+json[i].USERID+" selected='selected'>"+json[i].PMNAME +"</option>");
					 }else{
				 	 var $opt = $("<option value="+json[i].USERID+" >"+json[i].PMNAME +"</option>");
					 } 
					 $("#pm").append($opt);
		   		</s:if>
			}
			 var loanTime=$("#loanTime").val();
	    	 var expireTime=$("#expireTime").val();
	   		 var loanwayid=$("#loanwayid").val();
	    	 var term=$("#term").val();
	     if(null != loanwayid && "" != loanwayid &&  null != term && "" != term && null != expireTime && "" != expireTime && null != loanTime && "" != loanTime ){
	     	  $("#expireTime").attr("disabled",true);
	     	   $("#selectloanway").attr("disabled","disabled");
	          $("#loanTime,#term").attr("disabled",true);
	     }
			
	})
	//验证贷款编号只能为数字好字母
	function valiLoanNumber(id,title){
		var reg=/^[0-9a-zA-Z]*$/g;
   	 	var loanNumber=$("#loanNumber").val();
   		 if(!reg.test(loanNumber)){
   		  $.messager.alert('提示', '贷款编号只能是数字或字母');
   		 }else{
   		   submitTab(id,title);
   		 } 
	}
	function  ValiDate(id,title){
	    var loanTime=$("#loanTime").val();
	    var expireTime=$("#expireTime").val();
	    if(null != expireTime && "" != expireTime && null != loanTime && "" != loanTime){
	     var sDate = new Date(loanTime.replace(/\-/g, "\/"));
         var eDate = new Date(expireTime.replace(/\-/g, "\/"));
	       if(sDate>eDate){
	          $.messager.alert('提示', '出借日期晚于到期日，请重新输入。');
	       }else{
	         submitConfirm(id,title);
	       }
	    }else{
	      valiLoanNumber(id,title);
	    }
	}
	//如出借方式，出借日，到期日期限不为空在提交前提示
	function submitConfirm(id,title){
	    var loanTime=$("#loanTime").val();
	    var expireTime=$("#expireTime").val();
	    var loanwayid=$("#selectloanway").val();
	    var term=$("#term").val();
	    if(null != loanwayid && "" != loanwayid 
	                         &&  null != term && "" != term){
	     	if("1" == loanwayid){
	     	  $.messager.confirm('提示', '出借方式:p2p;到借日为:'+expireTime+';出借日为'+loanTime+';期限:'+term, function(r){
	     	     if(r){
	     	        status();  
	     	        valiLoanNumber(id,title);
		     	  }
		   	   })
	     	}else if("2" == loanwayid){
	          $.messager.confirm('提示', '出借方式:有限合伙;到借日为'+expireTime+';出借日为:'+loanTime+';期限:'+term, function(r){
		       	if(r){
		       	    status();
		     	     valiLoanNumber(id,title);
		     	  }
		   	  })
	        }else{
	           valiLoanNumber(id,title);
	        }
	     }else{
	         valiLoanNumber(id,title);
	     }
	}
    //使不可编辑的文本框可编辑
	function status(){
	   $("#expireTime").attr("disabled",false);
	   $("#selectloanway").attr("disabled",false);
	   $("#loanTime,#term").attr("disabled",false);
	}
</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>/project/projectAction!<s:if test='projectManage != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" id="fid" name="projectManage.fid " value='<s:property value="projectManage.fid"/>' />
            <input type="hidden"  id="fext1" name="projectManage.fext1 " value="<s:property value="projectManage.fext1"/>"/> 
            <div class="title">项目信息编辑</div>
			<table>
			   <tr><td class="t-title" width="100">贷款编号</td>
				<td><input class="t-text" type="text" id="loanNumber" name="projectManage.loanNumber" value="<s:property value='projectManage.loanNumber' />">
				 <input type="hidden" id="oldloanNumber" value="<s:property value='projectManage.loanNumber' />"/></td>
				<td class="t-title" width="100">公司名称</td>
				<td><input class="t-text" type="text" id="companyName" name="projectManage.companyName" value="<s:property value='projectManage.companyName' />"><span class="required">*</span></td>
			    <td class="t-title" width="100">PM</td>
				<td><input type="hidden" id="pmuserid" value="<s:property value='projectManage.pmUserId' />"/> <select id="pm" class="t-text" name="projectManage.pmUserId"><option value="">----------请选择-----------</option></select></td>
				</tr>
				<tr>
				<td class="t-title" width="100">出借日</td>
				<td><input type="hidden" id="loanTimeValue" name="loanTime" value=""/><input type="text" id="loanTime" class="t-text Wdate" name="projectManage.loanTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="<s:date name='projectManage.loanTime' format='yyyy-MM-dd'/>"></td>
			    <td class="t-title" width="100">到期日</td>
			     <td><input type="hidden" id="expireTimeValue" name="expireTime" value=""/><input type="text" id="expireTime" class="t-text Wdate" name="projectManage.expireTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='projectManage.expireTime' format='yyyy-MM-dd'/>"></td>
			    <td class="t-title" width="100">出借方式</td>
				<td><input type="hidden" id="loanwayidValue" name="loanwayid" value=""/><input type="hidden" id="loanwayid"  value="<s:property value='projectManage.loanWayId' />"/> <select id="selectloanway" class="t-text"   name="projectManage.loanWayId"><option value="">---------请选择---------</option></select></td>
				</tr>
				 <tr>
				<td class="t-title" width="100">月利率</td>
				<td><input type="text" id="mlyinterest" class="t-text" name="projectManage.mlyinterest" value="<s:property value='projectManage.mlyinterest' />">%</td>
				<td class="t-title" width="100">月管理费</td>
				<td><input type="text" id="mMangExpense" class="t-text" name="mMangExpense" value="${projectManage.mMangExpense}">%</td>
				<td class="t-title" width="100">还款方式</td>
				<td><input type="hidden" id="repaymentwayid" value="<s:property value='projectManage.repaymentWayId' />"/><select id="Situationname" class="t-text" name="projectManage.repaymentWayId"><option value="">----------请选择----------</option></select></td>
				</tr>
				<tr>
				 <td class="t-title" width="100">出借方</td>
			    <td><input type="text" id="lenders" class="t-text" name="projectManage.lenders" value="<s:property value='projectManage.lenders' />"></td>
			     <td class="t-title" width="100">借贷方</td>
			    <td><input type="text" id="borrower" class="t-text" name="projectManage.borrower" value="<s:property value='projectManage.borrower' />"><span class="required">*</span></td>
			     <td class="t-title" width="100">金额(元)</td>
				<td><input class="t-text" type="text" id="money" name="projectManage.money" value="<fmt:formatNumber value='${projectManage.money}' pattern='0.00'/>"><span class="required">*</span></td>
				</tr>
				<tr>
				<td class="t-title" width="100">还款账户</td>
				<td><input class="t-text" type="text" id="repaymentAccount" name="projectManage.repaymentAccount" value="<s:property value='projectManage.repaymentAccount' />"></td>
				<td class="t-title" width="100">开户行</td>
				<td><input class="t-text" type="text" id="bankAccount" name="projectManage.bankAccount" value="<s:property value='projectManage.bankAccount' />"></td>
				<td class="t-title" width="100">账号</td>
				<td><input class="t-text" type="text" id="account" name="projectManage.account" value="<s:property value='projectManage.account' />"></td>
				</tr>
				<tr>
				 <td class="t-title" width="100">认股权证</td>
			    <td><input type="text" id="authentication" class="t-text" name="projectManage.authentication" value="<s:property value='projectManage.authentication' />"></td>
			    <td class="t-title" width="100">期限(月)</td>
			    <td><input type="hidden" id="termValue" name="term" value=""/><input class="t-text" type="text" id="term" name="projectManage.term" value="<s:property value='projectManage.term' />"></td>
			     <td class="t-title" width="100">状态</td>
				<td><input type="hidden" id="statefid" value="<s:property value='projectManage.stateFid' />"/><select id="state" class="t-text" name="projectManage.stateFid"><option value="">---------请选择-----------</option></select></td>
				</tr>
			</table>
            <div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="ValiDate('editBtn','项目信息列表')">保存</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
		<input type="hidden"  id="username" value="${user.username}"/> 
    </div>
	</body>
</html>
