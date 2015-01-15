<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投诉信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/common/commonCaseInclude.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			//加载客户来源
			var $fcussourceid = $("#fcussourceid");
			$fcussourceid.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
			var url = "<%=basePath%>/caseinfo/caseinfo!findCussource";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$fcussourceid.append($opt);
			}
			var json = sendAjax("<%=basePath%>/caseinfo/caseinfo!getAllDictionarys");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$("#loanBread").append($opt);
			}
			//加载服务类型
			var $fservicetypeid = $("#fservicetypeid");
			$fservicetypeid.empty().append( $("<option value='0'>--请选择--</option>"));//js 长度置空，并设置默认值;
			var url = "<%=basePath%>/caseinfo/caseinfo!findServicetype";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$fservicetypeid.append($opt);
			}
			//加载客户状态
			var $sel = $("#fcusstatusid");
			$sel.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
			var url = "caseinfo/caseinfo!findCusstatus?complainInfo.fservicetypeid=1";
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$sel.append($opt);
			}

			/*
			* 案件来源
			*/
			/*var $caseFromID=$("#caseFromID");
			$caseFromID.empty().append( $("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
			var caseFromIDURL= "caseinfo/caseinfo!findCaseFrom";
			var caseFromJson = sendAjax(caseFromIDURL);
			for(var i=0;i<caseFromJson.length;i++){
				var $opt = $("<option value="+caseFromJson[i].key +" >"+caseFromJson[i].value +"</option>");
				$caseFromID.append($opt);
			}*/
			
			//增加投诉人验证信息
			$("#fcomplainanter").validatebox({required: true,validType:'njelength[1,20]',missingMessage: '该输入项为必输项'});
			$("#fcusname").validatebox({required: true,validType:'njelength[1,20]',missingMessage: '该输入项为必输项'});
	  	});
		//
		function fun_cus(){
			var fiscustomer = $("#fiscustomer").val();
			if(fiscustomer == 1){
				var fcomplainanter = $("#fcomplainanter").val();
			    $("#fcusname").val(fcomplainanter);
			}
		}
		//验证手机号，qq号被投诉次数
		function fun_loadCount(id){
			if(id=="fmobile"){
				var value=$("#"+id).val();
			   	var url = "<%=basePath%>/caseinfo/caseinfo!findFmobileCount?fmobile="+value;
				var jsonCount = sendAjax(url);
				var strCount="";
				if(jsonCount !=null && jsonCount != ""){
					$("#fmobileCount").text("");
		        	$("#fmobileCount").append(jsonCount);
				}
			}else if(id="fqq"){
			  	var value=$("#"+id).val();
			   	var url = "<%=basePath%>/caseinfo/caseinfo!findFqqCount?fqq="+value;
				var jsonCount = sendAjax(url);
				if(jsonCount !=null && jsonCount != ""){
			  		$("#fqqCount").text(jsonCount)
			   	}
		 	}
		}
		function openWinComplaint(type,index){
			var url;
			var fqq=$("#fqq").val();
			var fcusname=$("#fcusname").val();
			var fmobile=$("#fmobile").val();
			switch (type) {
				case 'fqq':
					url = $("#fqqURL").val()+fqq; break;
				case 'fcusname':
					url = $("#fcusnameURL").val()+encodeURI(fcusname); break;
				case 'fmobile':
				    if(fmobile.indexOf(";")==-1){
					  	url = $("#fmobileURL").val()+fmobile;
					}else{
					    var str=fmobile.split(";")
					 	url = $("#fmobileURL").val()+str[index];
					}
					break;
			}
			//模态窗口自适应宽度，高度
			$('#iframeWin').window({ onClose : function(){ $("#iframeSource").attr("src",""); } })
			$("#iframeSource").attr("src",url);
			$('#iframeWin').window('open');
		}
		$(function() {
			//根据客户姓名验证被投诉次数
			$("#fcusname").bind('propertychange',function(){
		     	//var value=escape(encodeURIComponent($("#fcusname").val()));
		     	var value=$("#fcusname").val();
			   	var url = "<%=basePath%>/caseinfo/caseinfo!findFcusNameCount";
			   	//alert(url);
			   	var data={fcusname:value};
				var jsonCount = sendAjaxPOST(url,data);
				$("#fcusnameCount").text("");
				if(jsonCount !=null && jsonCount != ""){
					$("#fcusnameCount").text(jsonCount)
				}
		 	})
	 	})
	 	 
			 	var isstatusnameValue=$("#fisviolate option:selected").text();
				 if(isstatusnameValue=="无违约"){
					$("#fviolatedaystr").hide();
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

				 
	 	
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form method="post" action="<%=basePath%>caseinfo/caseinfo!addCompliant" id="editForm">  
	   	<!-- 解决表单重复提交的问题 -->
	   		<div class="title">投诉信息编辑</div>
			<table>
				<tr><td class="t-title" width="100">投诉人：</td>
					<td><input class="t-text" name="complainInfo.fcomplainanter" id="fcomplainanter" 
						onkeyup="fun_cus();"/><span class="required">*</span></td>
					<td class="t-title" width="100">客户本人：</td>
					<td><select id="fiscustomer" name="complainInfo.fiscustomer" onchange="fun_isCustomer();"> 
		    			<option value="1">是 </option> 
		    			<option value="0">否 </option> 
		    		</select></td>
	    		</tr>
	    		
	    		<!-- 是或者否的时候都显示，是的时候，默认和投诉人一样 -->
	    		<tr><td class="t-title">客户姓名：</td>
	    			<td><input name="complainInfo.fcusName" id="fcusname" readonly="readonly" 
	    				class="t-text"><a href="javascript:void(0)" 
	    					onclick='openWinComplaint("fcusname","")'><span id="fcusnameCount"></span></a>
	    			</td>
	    			<td class="t-title">客户身份证号：</td>
	    			<%-- 
	    			<td><input class="t-text" name="complainInfo.fidcard"  class="t-text easyui-validatebox" validType="idcard"></td>
	    			
	    			--%>
	    			<td><input type="text" name="complainInfo.fidcard" id="fidcard" class="t-text easyui-validatebox" 
	    				validType="idcard">
	    			</td>
	    			
	    		</tr>
	    		
	    		<tr><td class="t-title">客户手机号码：</td>
	    			<td><input name="complainInfo.fmobile" id="fmobile" class="t-text easyui-validatebox" 
	    				validType="mobiles" onkeyup="fun_loadCount('fmobile')"><span id="fmobileCount" style="color:red";></span>
	    			</td>
	    			<td class="t-title">客户座机：</td>
	    			<td><input name="complainInfo.fofficephone" id="fofficephone" class="t-text easyui-validatebox" validType="phone"></td>
	    		</tr>
	    		
	    		<tr><td class="t-title">客户QQ：</td>
	    			<td><input name="complainInfo.fqq" id="fqq" class="t-text easyui-validatebox" 
	    				validType="qq" onkeyup="fun_loadCount('fqq')"><a href='javascript:void(0)' 
    						onclick='openWinComplaint("fqq","")' ><span id="fqqCount"></span></a>
	    			</td>
	    			<td class="t-title">客户邮箱：</td>
	    			<td><input name="complainInfo.femail" id="femail" class="t-text easyui-validatebox" validType="email"></td>
	    		</tr>
	    		
	    		<tr class="cusRelation" style="display:none">
	    			<td class="t-title">投诉人与客户关系：</td>
	    			<td colspan="3"><input name="complainInfo.fcusrelation" validType="njection" class="t-text easyui-validatebox"></td>
	    		</tr>
	    		<tr class="cusRelation" style="display:none">
	    			<td class="t-title">投诉人的手机号码：</td>
	    			<td><input name="complainInfo.fcompcontactinfo" class="t-text easyui-validatebox" validType="mobile"></td>
	    			<td class="t-title">投诉人的身份证号：</td>
	    			<td><input name="complainInfo.fcomplainidcard" class="t-text easyui-validatebox" validType="idcard"></td>
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
	    			<td><input name="complainInfo.caseComeIntoCode" class="t-text" /></td>
	    			<td class="t-title">进件时间：</td>
	    			<td><input class="t-text Wdate" type="text" name="complainInfo.caseComeIntoDate" readonly="readonly" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
	    		</tr>
				
				<tr>
					<td class="t-title">案件来源：</td>
					<td><select name="complainInfo.casefrom" id="caseFromID"></select></td>
				</tr>
				 --%>
	    		
	    		<tr id="cusSourceDiv">
	    			<td class="t-title">客户来源：</td>
	    			<td colspan="3"><select name="complainInfo.fcussourceid" id="fcussourceid" 
	    				onchange="addOtherCus('fcussourceid','cusSourceOtherDiv')">
						<option>--请选择--</option>
					</select></td> 
	    		</tr>
	    	 	
	    	 	<tr id="cusSourceOtherDiv" style="display:none;">
	    			<td class="t-title">备注：</td>
	    			<td colspan="3"><textarea name="complainInfo.fremarks" rows="3" style="width:580;"></textarea></td>
	    	 	</tr>
	    		
	    		<tr>
	    			<td class="t-title" id="serviceTypeDiv">服务类型：</td>
	    			<td><select name="complainInfo.fservicetypeid" id="fservicetypeid" 
	    				onchange="fun_serviceType('fservicetypeid','fcusstatusid');">
					</select></td>
					<td class="t-title" id="cusStatusDiv">客户状态：</td>
					<td><select name="complainInfo.fcusstatusid" id="fcusstatusid" onchange="fun_cusStatus('fcusstatusid');">
		    			<option>--请选择--</option>
		    		</select></td>
	    		</tr>
	    		
	    	
	    		<tr class="otherInfo1" style="display:none;">
	    			<td class="t-title">合同编号：</td>
	    			<td><input type="text" name="complainInfo.fcontractnumber" id="fcontractnumber" validType="njection"
	    				class="t-text easyui-validatebox"/><span class="required">*</span></td>
	    			<td class="t-title">金额：</td>
	    			<td><input type="text" name="complainInfo.famount" id="famount" 
	    				class="t-text easyui-validatebox" validType="intOrFloat"/><span class="required">*</span></td>
	    		</tr>
	    		<tr class="otherInfo1" style="display:none;">
	    			<td class="t-title">期限：</td>
	    			<td><input type="text" name="complainInfo.fdeadline" id="fdeadline" 
	    				class="t-text easyui-validatebox" validType="integer"/>月<span class="required">*</span></font></td>
	    			<td class="t-title">还款起始日：</td>
	    			<td><input type="text" name="complainInfo.freimbstrattime" id="freimbstrattime" readonly="readonly" 
 						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate"/><span class="required">*</span></font></td>
	    		</tr>
	    		<tr class="otherInfo1" style="display:none;">
	    			<td class="t-title">还款终止日：</td>
	    			<td><input type="text" name="complainInfo.freimbendtime" id="freimbendtime" readonly="readonly"　 
 						 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate"/><span class="required">*</span></font></td>
	    			<td class="t-title">签约时间：</td>
	    			<td><input type="text" name="complainInfo.fcontracttime" readonly="readonly" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate"/></td>
	    		</tr>
	    		<tr class="otherInfo1" style="display:none;">
	    			<td class="t-title">放款时间：</td>
	    			<td><input type="text" name="complainInfo.floantime" readonly="readonly" 
				 		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="t-text Wdate"/></td>
	    			
	    		</tr>
	    		
	    		<tr class="otherInfo1" style="display:none;" id="dkpz">
	    			<td class="t-title">贷款品种：</td>
	    			<td><select id="loanBread" name="complainInfo.floanbread"><option value="">--请选择--</option></td>
	    		</tr>
	    		<tr class="otherInfo1" style="display:none;">
	    			<td class="t-title">有无违约：</td>
	    			<td colspan="3"><select id="fisviolate" name="complainInfo.fisviolate" onchange="fun_fisviolate1();"> 
		    			<option value="1">违约</option> 
		    			<option value="0" selected>未违约</option> 
		    		</select></td>
	    		</tr>
	    		<tr class="otherInfo1" id="fviolatedaystr" style="display: none;">
	    			<td class="t-title">违约天数：</td>
	    			<td colspan="3"><input type="text" name="complainInfo.fviolatedays" 
	    				class="t-text easyui-validatebox" validType="integer"/>天</td>
	    		</tr>
	    		
	    		<tr class="otherInfo1" id="fviolateCountHistory" style="display: none;">
	    			<td class="t-title">历史违约次数：</td>
	    			<td colspan="3"><input type="text" name="complainInfo.fviolateCountHistory"  id="fviolate"
	    				class="t-text easyui-validatebox" validType="integer" onkeyup="keyUpFun()"/>次</td>
	    		</tr>
	    	
	    		<tr class="otherInfo2" style="display:none;" id="lcfa">
	    			<td class="t-title" id="fservicetype">理财方案：</td>
	    			<td id="fservicetype2"><input class="t-text easyui-validatebox" type="text" 
	    			   validType="njection" name="complainInfo.financialplan"/></td>
	    		</tr>
	    		
				<tr class="otherInfo2" style="display:none;" id="lxwysj" style="display:none;">
						<td class="t-title" id="fstartorendtime1">首次违约时间：</td>
	    				<td id="fstartorendtime2"><input type="text" name="complainInfo.fstartorendtime" readonly="readonly" 
 						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
						<td id="fext5time1" class="t-title">连续违约时间：</td>
				    	<td id="fext5time2"><input type="text"  name="complainInfo.fext5" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
				</tr>
<%--				<tr class="otherInfo3" style="display:none;>--%>
<%--					<td class="t-title" style="display:none;">理财方案：</td>--%>
<%--					<td style="display:none"><input class="t-text" value="<s:property value='complain.financialplan'/>" disabled="disabled"></td>--%>
<%--			    	<td id="fstartorendtime1">首次违约时间：</td>--%>
<%--			    	<td id="fstartorendtime2"><input type="text"  name="complainInfo.fstartorendtime" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>--%>
<%--		    		<td id="fext5time1">连续违约时间：</td>--%>
<%--			    	<td id="fext5time2"><input type="text"  name="complainInfo.fext5" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>--%>
<%--	    		</tr>--%>
	    		
	    		<tr class="otherInfo3" style="display: none;">
	    			<td class="t-title">销售人员姓名：</td>
	    			<td><input name="complainInfo.fsalesmanname" id="fsalesmanname" validType="njection" class="t-text easyui-validatebox"></td>
	    			<td class="t-title">客服姓名：</td>
	    			<td><input type="text" name="complainInfo.fservicename" id="fservicename" validType="njection" class="t-text easyui-validatebox"></td>
	    		</tr>
	    		<tr class="otherInfo3" style="display:none;">
	    			<td class="t-title">初审人员：</td>
	    			<td><input class="t-text" type="text easyui-validatebox" validType="njection" name="complainInfo.ffirsttrial" id="ffirsttrial"/></td>
	    			<td class="t-title">终审人员：</td>
	    			<td><input class="t-text" type="text easyui-validatebox" validType="njection" name="complainInfo.flasttrial" id="flasttrial"></td>
	    		</tr>
	    		
	    		<tr class="otherInfo0" style="display:none;" id="dkpz">
	    			<td class="t-title">拒贷原因：</td>
	    			<td colspan="3"><textarea name="complainInfo.fdeniedloans" 
	    				id="fdeniedloans" rows="4" style="width:580;"></textarea></td>
	    		</tr>
	    		
    		</table>
    		<div class="t-but">
    		<a href="javascript:void(0);" class="but-change" onclick="dosubmitComplianceNext('editForm');">下一步</a>
    			<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	    		
		    </div>
		</form>
		</div>
	    <div id="iframeWin" class="easyui-window" title="详细信息" modal="true" closed="true"
			iconCls="icon-save" style="width:1000px;height:400px;padding:2px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	    <input type="hidden" id="fqqURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fqq="/>
	    <input type="hidden" id="fcusnameURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fcusname="/>
	    <input type="hidden" id="fmobileURL" value="pages/compliance/caseinfo/selectCaseInfo.jsp?fmobile="/>
	</body>
</html>
