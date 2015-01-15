<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投诉信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript">
		var oldComplain={};
		var newComplain={};

		var pro={fcomplainanter:'投诉人',fiscustomer:'客户本人',fcusname:'客户姓名'};
		
		$(function() {
			//加载客户来源
			var $fcussourceid = $("#fcussourceid");
			$fcussourceid.empty().append( $("<option value='0'>----请选择----</option>") );//js 长度置空，并设置默认值
			var url = "<%=basePath%>/caseinfo/caseinfo!findCussource";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +">"+json[i].value +"</option>");
				$fcussourceid.append($opt);
			}
			//加载服务类型
			var $fservicetypeid = $("#fservicetypeid");
			$fservicetypeid.empty().append( $("<option value='0'>----请选择----</option>"));//js 长度置空，并设置默认值
			var url = "<%=basePath%>/caseinfo/caseinfo!findServicetype";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +">"+json[i].value +"</option>");
				$fservicetypeid.append($opt);
			}
			//加载客户状态
			var $sel = $("#fcusstatusid");
			$sel.empty().append( $("<option value='0'>----请选择----</option>") );//js 长度置空，并设置默认值
			var url = "caseinfo/caseinfo!findCusstatus?complainInfo.fservicetypeid=1";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +">"+json[i].value +"</option>");
				$sel.append($opt);
			}
			//判断是否是客户本人
			if($("#fiscustomerValue").val()==0){
		 		$("#fiscustomer").children().eq(1).attr("selected","selected");
		 		document.getElementById('cusSourceDiv').style.display = "none";
				$('.cusRelation').show();
				$("#fcusrelation").show();
				$("#fidcard").hide();
				$("fservicenames").show();
				$("#fcusname").attr("readonly",false);

				$("#fcusname").validatebox({required: false,validType:'njelength[1,20]',missingMessage: ''});
			}else{
				//alert();
				$("#fcusname").validatebox({required: true,validType:'njelength[1,20]',missingMessage: '该输入项为必输项'});	
			}
			//判断服务类型
			var fservicetypeids=$("#fservicetypeid").children();
			for(var i=0;i<fservicetypeids.length;i++){
			if( $("#fservicetypeid").children().eq(i).val()==$("#fservicetypeidValue").val())
			 	$("#fservicetypeid").children().eq(i).attr("selected","selected");
			 	fun_serviceType('fservicetypeid','fcusstatusid');
			}
			
			var fcusstatusids=$("#fcusstatusid").children();
			for(var i=0;i<fcusstatusids.length;i++){
		  		if( $("#fcusstatusid").children().eq(i).val()==$("#fcusstatusidValue").val()){
		     		$("#fcusstatusid").children().eq(i).attr("selected","selected");
			     	fun_cusStatus('fcusstatusid');
			   }
			}
			var fcussourceids=$("#fcussourceid").children();
			for(var i=0;i<fcussourceids.length;i++){
		  		if( $("#fcussourceid").children().eq(i).val()==$("#fcussourceidValue").val()){
	     			$("#fcussourceid").children().eq(i).attr("selected","selected");
		   		}
			}
			if($("#fisviolateValue").val()==0){
	 			$("#fisviolate").children().eq(1).attr("selected","selected");
			}
			//动态加载验证信息
			var cusStatus = $('#fcusstatusid');
			if(cusStatus.find("option:selected").text() == '完成放款'){
				$("#fcontractnumber").validatebox({required: true,missingMessage: '该输入项为必填项'});//合同编号
				$("#famount").validatebox({required: true,missingMessage: '该输入项为必填项'});//金额
				$("#fdeadline").validatebox({required: true,missingMessage: '该输入项为必填项'});//期限
			}
			//增加投诉人验证信息
			$("#fcomplainanter").validatebox({required: true,validType:'njelength[1,20]',missingMessage: '该输入项为必输项'});
			//$("#fcusname").validatebox({required: true,validType:'charLength[1,20]',missingMessage: '该输入项为必输项'});


			oldComplain.fcomplainanter=$('#fcomplainanter').val();
			oldComplain.fiscustomer=$('#fiscustomer').find("option:selected").text();
			oldComplain.fcusname=$('#fcusname').val();
			

			/*
			* 案件来源
			*/
			/*var caseFrom='${complainInfo.casefrom}';
			var $caseFromID=$("#caseFromID");
			$caseFromID.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
			var caseFromIDURL= "caseinfo/caseinfo!findCaseFrom";
			var caseFromJson = sendAjax(caseFromIDURL);
			for(var i=0;i<caseFromJson.length;i++){
				var $caseFrom_opt ;
				if(caseFrom==caseFromJson[i].key){
					$caseFrom_opt = $("<option value="+caseFromJson[i].key +" selected='selected'>"+caseFromJson[i].value +"</option>");
				}else{
					$caseFrom_opt = $("<option value="+caseFromJson[i].key +" >"+caseFromJson[i].value +"</option>");
				}
				$caseFromID.append($caseFrom_opt);
			}*/
			
		});

	  	//验证手机号，qq号被投诉次数
		function fun_loadCount(id){
			var fid=$("#fid").val();
			if(id=="fmobile"){
			  	var value=$("#"+id).val();
			   	var url = "<%=basePath%>/caseinfo/caseinfo!findFmobileCount?fmobile="+value+"&fid="+fid;
				var jsonCount = sendAjax(url);
				var strCount="";
				if(jsonCount !=null && jsonCount != ""){
					$("#fmobileCount").text("");
		        	$("#fmobileCount").append(jsonCount);
				}
			}else if(id="fqq"){
		  		var value=$("#"+id).val();
		   		var url = "<%=basePath%>/caseinfo/caseinfo!findFqqCount?fqq="+value+"&fid="+fid;
				var jsonCount = sendAjax(url);
				if(jsonCount !=null && jsonCount != ""){
			  		$("#fqqCount").text(jsonCount)
		   		}else{
		     		$("#fqqCount").text("0")
		   		}
		 	}
		}
		
		function openWinComplaint(type,index){
			var url,title;
			var fid=$("#fid").val();
			var fqq=$("#fqq").val();
			var fcusname=$("#fcusname").val();
			var fmobile=$("#fmobile").val();
			switch (type) {
				case 'fqq':
					url = $("#fqqURL").val()+fqq+"&fid="+fid;  title="详细信息";
					break;
				case 'fcusname':
					url = $("#fcusnameURL").val()+encodeURI(fcusname)+"&fid="+fid;  title="详细信息";
					break;
				case 'fmobile':
				    if(fmobile.indexOf(";")==-1){
				  		url = $("#fmobileURL").val()+fmobile+"&fid="+fid;  title="详细信息";
					}else{
					    var str=fmobile.split(";")
					  	url = $("#fmobileURL").val()+str[index]+"&fid="+fid;  title="详细信息";
					}
					break;
			}
			//模态窗口自适应宽度，高度
			$('#iframeWin').window({
				title:title,
				onClose : function(){ //点击关闭按钮时清除iframe的src
					$("#iframeSource").attr("src",""); 
				} 
			})
			$("#iframeSource").attr("src",url);
			$('#iframeWin').window('open');
		}
	
		$(function() {
			//页面加载时根据手机号，qq号，客户名称查询被投诉次数
	   		var fid=$("#fid").val();
			fun_loadCount('fmobile');
			fun_loadCount('fqq');
		 	var value=escape(encodeURIComponent($("#fcusname").val())); 
		 	var url = "<%=basePath%>/caseinfo/caseinfo!findFcusNameCount?fcusname="+value+"&fid="+fid;
		 	var jsonCount = sendAjax(url);
		 	$("#fcusnameCount").text("")
			if(jsonCount !=null && jsonCount != ""){
		  		$("#fcusnameCount").text(jsonCount)
			}
			//根据客户姓名验证被投诉次数
			$("#fcusname").bind('propertychange',function(){
	     		//var value=escape(encodeURIComponent($("#fcusname").val())); 
	     		var value=$("#fcusname").val();
			   	var url = "<%=basePath%>/caseinfo/caseinfo!findFcusNameCount";
			   	var data={fcusname:value,fid:fid}
				var jsonCount = sendAjaxPOST(url,data);
				$("#fcusnameCount").text("");
				if(jsonCount !=null && jsonCount != ""){
			  		$("#fcusnameCount").text(jsonCount)
				}else{
					$("#fcusnameCount").text("0")
				}
		  	})
	 	})
	 	function fun_cus(){
			var fiscustomer = $("#fiscustomer").val();
			if(fiscustomer == 1){
				var fcomplainanter = $("#fcomplainanter").val();
			    $("#fcusname").val(fcomplainanter);
			}
		}

		function fun_fisviolate1(){
			var fisviolate = $("#fisviolate").val();
			if(fisviolate ==1){
				
				$('#lcfa').hide();//隐藏理财方案
				
				$("#fviolatedaystr").show();//违约天数
				$("#fviolateCountHistory").show();//历史违约次数
			}else{
				$('#lcfa').hide();//隐藏理财方案
				$("#fviolatedaystr").hide();//违约天数
				
				$("#fviolateCountHistory").show();//历史违约次数
			}
			var fviolate=$('#fviolate').val();
			if(fviolate!=''){
				$("#lxwysj").show();//违约时间
			}
		}


		function dosubmitCompliance1(editForm){
			var result = $('#editForm').form('validate');
			var cusStatus = $('#fcusstatusid');
			if(cusStatus.find("option:selected").text() == '完成放款'){
					var freimbstrattime = $("#freimbstrattime").val();
					if(freimbstrattime == null||freimbstrattime == ''){
						$("#freimbstrattime")[0].focus();
						result = false;
					}
					var freimbendtime = $("#freimbendtime").val();
					if(freimbendtime == null||freimbendtime == ''){
						$("#freimbendtime")[0].focus();
						result = false;
					}
			}
			if(result){
				newComplain.fcomplainanter=$('#fcomplainanter').val();
				newComplain.fiscustomer=$('#fiscustomer').find("option:selected").text();
				newComplain.fcusname=$('#fcusname').val();

				var diff=[];
				
				for(var p in oldComplain){
					if(oldComplain[p]!=newComplain[p]){
						diff.push({fieldDescription:pro[p],oldValue:oldComplain[p],newValue:newComplain[p],fieldName:p});
					}	
				}

				var json=JSON.stringify(diff);
				//alert(json);
				$('#compareJSON').val(json);
				$('#editForm')[0].submit();
			}
		}
		
	</script>
	</head>
  	<body>
  		<div class="tableForm">
	    <form method="post" action="<%=basePath%>caseinfo/caseinfo!updateCompliant" id="editForm">  
	    	<input type="hidden" id="compareJSON"/>
	    	<div class="title">投诉信息编辑</div>
			<table>
				<tr><td class="t-title" width="100">投诉人：</td>
					<td><input name="complainInfo.fcomplainanter" id="fcomplainanter" class="t-text easyui-validatebox" 
						required="true" value='${complainInfo.fcomplainanter}' onkeyup="fun_cus();"><span class="required">*</span></td>
					<td class="t-title" width="100">客户本人：</td>
					<td><input type="hidden" id="fiscustomerValue" value="${complainInfo.fiscustomer}">
		    		<select id="fiscustomer" name="complainInfo.fiscustomer" onchange="fun_isCustomer();"> 
		    			<option value="1" >是 </option> 
		    			<option value="0">否 </option> 
		    		</select></td>
	    		</tr>
	    		
				<tr><td class="t-title">客户姓名：</td>
					<td><input name="complainInfo.fcusName" id="fcusname" class="t-text" 
						 value='${complainInfo.fcusName}'> <a href="javascript:void(0)" 
						onclick='openWinComplaint("fcusname","")'><span id="fcusnameCount"></span></a>
					</td>
					<td class="t-title">客户身份证号：</td>
					<td><input name="complainInfo.fidcard"  class="t-text easyui-validatebox" 
						validType="idcard" value='${complainInfo.fidcard}'>
						
						</td>
				</tr>
				
				<tr><td class="t-title">客户手机号码：</td>
					<td><input name="complainInfo.fmobile" id="fmobile" class="t-text easyui-validatebox" 
						validType="mobiles" value='${complainInfo.fmobile}' 
						onkeyup="fun_loadCount('fmobile')"><span id="fmobileCount" style="color:red";></span></td>
					<td class="t-title">客户座机：</td>
					<td><input name="complainInfo.fofficephone" id="fofficephone" class="t-text easyui-validatebox" 
						validType="phone" value='${complainInfo.fofficephone}'></td>
				</tr>  
				
				<tr><td class="t-title">客户QQ：</td>
					<td><input name="complainInfo.fqq" id="fqq" class="t-text easyui-validatebox"  
						validType="qq" value='${complainInfo.fqq}' onkeyup="fun_loadCount('fqq')"><a href='javascript:void(0)' 
						onclick='openWinComplaint("fqq","")' ><span id="fqqCount"></span></a></td>
					<td class="t-title">客户邮箱：</td>
					<td><input name="complainInfo.femail" id="femail" class="t-text easyui-validatebox" validType="email" 
						value='${complainInfo.femail}'></td>
				</tr>
				
				<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人与客户关系：</td>
					<td colspan="3"><input name="complainInfo.fcusrelation" 
						class="t-text easyui-validatebox" validType="njection" value='${complainInfo.fcusrelation}'></td>
				</tr> 		
				<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人的手机号码：</td>
					<td><input name="complainInfo.fcompcontactinfo" class="t-text easyui-validatebox" 
						validType="mobile" value='${complainInfo.fcompcontactinfo}'></td>
					<td class="t-title">投诉人的身份证号：</td>
					<td><input name="complainInfo.fcomplainidcard" class="t-text easyui-validatebox" 
						validType="idcard" value='${complainInfo.fcomplainidcard}'></td>
				</tr>
				<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人的座机：</td>
					<td><input name="complainInfo.fext2" id="fext2" class="t-text easyui-validatebox" 
						validType="phone" value='${complainInfo.fext2}'></td>
					<td class="t-title">投诉人的QQ：</td>
					<td><input name="complainInfo.fext3" id="fext3" class="t-text easyui-validatebox"  
						validType="qq" value='${complainInfo.fext3}'></td>
				</tr>
					<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人的邮箱：</td>
					<td><input name="complainInfo.fext4" id="fext4" class="t-text easyui-validatebox" validType="email" 
						value='${complainInfo.fext4}'></td>
				</tr> 	
				
				<%--
				<tr>
	    			<td class="t-title">进件编号：</td>
	    			<td><input name="complainInfo.caseComeIntoCode" class="t-text" value="${complainInfo.caseComeIntoCode}" /></td>
	    			<td class="t-title">进件时间：</td>
	    			<td><input class="t-text Wdate" type="text" name="complainInfo.caseComeIntoDate" readonly="readonly" 
	    				value="${complainInfo.caseComeIntoDate}"
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
	    		</tr>
				
				<tr>
					<td class="t-title">案件来源：</td>
					<td>
						<select name="complainInfo.casefrom" id="caseFromID">
							<option selected="selected"></option>
						</select>
					</td>
				</tr>
				 --%>
				
				<tr id="cusSourceDiv">
					<td class="t-title">客户来源：</td>
					<td colspan="3"><input type="hidden" id="fcussourceidValue" value="${complainInfo.fcussourceid}"/>
		    		<select name="complainInfo.fcussourceid" id="fcussourceid" onchange="addOtherCus('fcussourceid','cusSourceOtherDiv')">
						<option>--请选择--</option>
					</select></td>
				</tr>
				
				<tr id="cusSourceOtherDiv" style="<s:if test="complainInfo.fcussourceid==4">display:'';</s:if><s:else>display:none;</s:else> ">
					<td class="t-title">备注：</td>
					<td colspan="3"><textarea name="complainInfo.fremarks" rows="2" style="width: 580;"><s:property value="complainInfo.fremarks"/></textarea></td>
				</tr>
				
				<tr>
					<td class="t-title" id="serviceTypeDiv">服务类型：<input type="hidden" 
						id="fservicetypeidValue"  validType="njection" class="t-text easyui-validatebox" value='${complainInfo.fservicetypeid}'/></td>
					<td><select name="complainInfo.fservicetypeid" id="fservicetypeid" 
						onchange="fun_serviceType('fservicetypeid','fcusstatusid');">
		    			<option value="0">--请选择--</option></select></td>
	    			<td class="t-title" id="cusStatusDiv">客户状态：<input type="hidden" id="fcusstatusidValue" 
	    				value='${complainInfo.fcusstatusid}'/></td>
    				<td><select name="complainInfo.fcusstatusid" id="fcusstatusid" onchange="fun_cusStatus('fcusstatusid');">
		    			<option>--请选择--</option></select></td>
				</tr>
				
			
				
				<tr class="otherInfo1" style="display:none;">
					<td class="t-title">合同编号：</td>
					<td><input type="text" name="complainInfo.fcontractnumber" id="fcontractnumber" class="t-text easyui-validatebox" validType="njection"
    					value='${complainInfo.fcontractnumber}'/><span class="required">*</span></td>
					<td class="t-title">金额：</td>
					<td><input type="text" name="complainInfo.famount" id="famount" value='${complainInfo.famount}' 
						class="t-text easyui-validatebox" validType="intOrFloat"/><span class="required">*</span></td>
				</tr>
				<tr class="otherInfo1" style="display:none;">
					<td class="t-title">期限：</td>
					<td><input type="text" name="complainInfo.fdeadline" id="fdeadline" value='${complainInfo.fdeadline}' 
						class="t-text easyui-validatebox" validType="integer"/><span class="required">*</span></td>
					<td class="t-title">还款起始日：</td>
					<td><input type="text" name="complainInfo.freimbstrattime" id="freimbstrattime" readonly="readonly" 
				 		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate" required="true" 
				 		value='${complainInfo.freimbstrattime}'/><span class="required">*</span></td>
				</tr>
				<tr class="otherInfo1" style="display:none;">
					<td class="t-title">还款终止日：</td>
					<td><input type="text" name="complainInfo.freimbendtime" id="freimbendtime"　readonly 
	 					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate" required="true" 
	 					value='${complainInfo.freimbendtime}'/><span class="required">*</span></td>
					<td class="t-title">签约时间：</td>
 					<td><input type="text" name="complainInfo.fcontracttime" readonly="readonly"
				 		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate" 
				 		value='<s:date name="complainInfo.fcontracttime" format="yyyy-MM-dd"></s:date>'/></td>
				</tr>
				<tr class="otherInfo1" style="display:none;">
					<td class="t-title">放款时间：</td>
					<td><input type="text" name="complainInfo.floantime" readonly="readonly" 
				 		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate" 
				 		value='<s:date name="complainInfo.floantime" format="yyyy-MM-dd"></s:date>'/></td>
				 		<%--
					<td class="t-title">贷款品种：</td>
					<td><select name="complainInfo.floanbread">
		    			<option value="">--请选择--</option>
				    	<s:iterator value="loanBreads"><option value="<s:property value='key'/>" 
		  				<s:if test="complainInfo.floanbread==key">selected</s:if> ><s:property value="value" /></option>
		    			</s:iterator>
		    		</select></td>
		    		 --%>
<%--					<input class="t-text" type="text" name="complainInfo.floanbread" value='${complainInfo.floanbread}'/>--%>
				</tr>
				
				<tr class="otherInfo1" style="display:none;" id="dkpz">
					<td class="t-title">贷款品种：</td>
					<td><select name="complainInfo.floanbread">
		    			<option value="">--请选择--</option>
				    	<s:iterator value="loanBreads"><option value="<s:property value='key'/>" 
		  				<s:if test="complainInfo.floanbread==key">selected</s:if> ><s:property value="value" /></option>
		    			</s:iterator>
		    		</select></td>
				</tr>
				
				
				<tr class="otherInfo1" style="display:none;">
					<td class="t-title">有无违约：</td>
					<td colspan="3"><input type="hidden" id="fisviolateValue" value="${complainInfo.fisviolate}"/>
						<select id="fisviolate" name="complainInfo.fisviolate" onchange="fun_fisviolate1();"> 
		    			<option value="1" <c:if test="${complainInfo.fisviolate==1}">selected</c:if>>违约</option> 
		    			<option value="0" <c:if test="${complainInfo.fisviolate==0}">selected</c:if>>未违约</option> 
		    		</select></td>
				</tr>
				<tr class="otherInfo1" id="fviolatedaystr" style="display: none;">
					<td class="t-title">违约天数：</td>
					<td colspan="3"><input type="text" name="complainInfo.fviolatedays" value='${complainInfo.fviolatedays}' 
						class="t-text easyui-validatebox" validType="integer"/>天</td>
					</td>
				</tr>
				<tr class="otherInfo1" id="fviolateCountHistory" style="display: none;">
   					<td class="t-title">历史违约次数：</td>
    				<td><input type="text" name="complainInfo.fviolateCountHistory" id="fviolate"
						value='${complainInfo.fviolateCountHistory }' class="t-text easyui-validatebox" validType="integer" onkeyup="keyUpFun()"/>次</td>
    			</tr>
    			<tr class="otherInfo2" style="display:none;" id="lcfa">
	    			<td class="t-title" id="fservicetype">理财方案：</td>
	    			<td id="fservicetype2"><input validType="njection" class="t-text easyui-validatebox"
	    			 type="text" name="complainInfo.financialplan" value='${complainInfo.financialplan}'/></td>
	    			
	    		</tr>
				<tr class="otherInfo2" style="display:none;" id="lxwysj">
						<td class="t-title" id="fstartorendtime1">首次违约时间：</td>
	    				<td id="fstartorendtime2"><input type="text" name="complainInfo.fstartorendtime" value='${complainInfo.fstartorendtime}' readonly="readonly" 
 						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
						<td id="fext5time1" class="t-title">连续违约时间：</td>
				    	<td id="fext5time2"><input type="text"  name="complainInfo.fext5" readonly="readonly" value='${complainInfo.fext5}' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
				</tr>
    			

<%--					<tr class="otherInfo3" style="display:none;>--%>
<%--						<td class="t-title" style="display:none;">理财方案：</td>--%>
<%--						<td  style="display:none"><input class="t-text" value="<s:property value='complain.financialplan'/>" disabled="disabled"></td>--%>
<%--		    			<td class="t-title" id="fstartorendtime1">首次违约时间：</td>--%>
<%--		    			<td id="fstartorendtime2"><input type="text"  name="complainInfo.fstartorendtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>--%>
<%--	    				<td class="t-title" id="fext5time1">连续违约时间：</td>--%>
<%--		    			<td id="fext5time2"><input type="text"  name="complainInfo.fext5" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>--%>
<%--	    		</tr>--%>
    			
    			<tr class="otherInfo3" style="display: none;">
	    			<td class="t-title">销售人员姓名：</td>
	    			<td><input class="t-text" type="text" validType="njection" class="t-text easyui-validatebox" name="complainInfo.fsalesmanname" 
	    				id="fsalesmanname" value="${complainInfo.fsalesmanname}"></td>
	    			<td class="t-title">客服姓名：</td>
	    			<td><input class="t-text" type="text" validType="njection" class="t-text easyui-validatebox" name="complainInfo.fservicename" 
	    				id="fservicename" value="${complainInfo.fservicename}"></td>
    			</tr>
    			<tr class="otherInfo3" style="display:none;">
	    			<td class="t-title">初审人员：</td>
	    			<td><input class="t-text" type="text" validType="njection" class="t-text easyui-validatebox" name="complainInfo.ffirsttrial" 
	    				id="ffirsttrial" value="${complainInfo.ffirsttrial}"></td>
	    			<td class="t-title">终审人员：</td>
	    			<td><input class="t-text" type="text" validType="njection" class="t-text easyui-validatebox" name="complainInfo.flasttrial" 
	    				id="flasttrial" value="${complainInfo.flasttrial}"></td>
    			</tr>
    			
    			<%--移动拒贷原因 --%>
    			<tr class="otherInfo0" style="display:none;" id="dkpz">
					<td class="t-title">拒贷原因：</td>
					<td colspan="3"><textarea name="complainInfo.fdeniedloans" 
						id="fdeniedloans" rows="10" style="width:580">${complainInfo.fdeniedloans}</textarea></td>
				</tr>
   			</table>
		 	<div class="t-but">
		 	<a href="javascript:void(0);" class="but-change" onclick="dosubmitCompliance('editForm');">保存</a>
		 		<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
		    </div>
		    <input type="hidden" id="fid" name="complainInfo.fid" value="${complainInfo.fid}"/>
		    <input type="hidden" name="complainInfo.ftempstatus" value="${complainInfo.ftempstatus}"/>
		    <input type="hidden" name="complainInfo.fcompchannelid" value="${complainInfo.fcompchannelid}"/>
	    </form>
	    </div>
	    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:1000px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
    	</div>
      	<input type="hidden" id="fqqURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fqq="/>
    	<input type="hidden" id="fcusnameURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fcusname="/>
    	<input type="hidden" id="fmobileURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fmobile="/>
  	</body>
</html>
