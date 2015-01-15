<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>案件详细信息</title>
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
    <style type="text/css"> 
    	table tr td{font-size: 12px;}
      	div.item { width:100px;background-color: transparent; text-align:center; padding-top:0px;}
    </style>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/tooltip/jquery.tooltip.css" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.tooltip.js"></script>
  	<script type="text/javascript">
		$(function() {
			 $("div.item").tooltip();
			//加载投诉渠道
			loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch',${mapCompliant.FCOMPCHANNELID});
			//制近SRCode的显示
			var ffditchname=$("#ffditchname").val();
			if(ffditchname=='电话'){
				$("#SRCodeTh1").show();
				$("#SRCodeTh2").show();
			}else{
				$("#SRCodeTh1").hide();
				$("#SRCodeTh2").hide();
			}
			
			var tbfinicasetype = $("tbfinicasetype").val();
			//qiuDaoSelectFun();//调用下投诉渠道的显示用的方法
			//投诉渠道是个人邮箱时，需要绑定事件
			//加载证据类型
			loadSelectInfo('fevidencetypeid','<%=basePath%>/caseinfo/caseinfo!findEvidenceType','${mapCompliant.FEVIDENCETYPEID}');
			//加载服务类型
			loadSelectInfo('fbusportid','<%=basePath%>/caseinfo/caseinfo!findServicetype','${mapCompliant.FBUSPORTID}');


			//加载案件初步分类：动态的生成table
			var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			var $tr=$("#check");//给table设置一个tr
			
			for(var i=0;i<json.length;i++){//便利初步分类
				var $td = '';
				var valid=false;
				var fdetailCaseSelectedValue = '';
				 <c:forEach var="relInicasetype" items="${relInicasetypes}">
				 	if(${relInicasetype.inicasetypeid} == json[i].key){
						valid = true;
						
						fdetailCaseSelectedValue = '${relInicasetype.detailcasetypeid}';
						
						
				 }
			   </c:forEach>
			   //显示初步分类的信息
			   if(json[i].value == '其他'){
					if(valid){
						$td = $("<td><input type='checkbox' readonly id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' checked disabled='disabled'>" + json[i].value + "</td>");
						//显示初步分类的备注
<%--						$("#xiangXiFengLeiOtherDiv").css({display:"''"});
					  改成下面的方式，解决界面样式混乱的问题
--%>
						$("#xiangXiFengLeiOtherDiv").show();
					}else{
						$td = $("<td><input type='checkbox'  id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' disabled='disabled'>" + json[i].value + "</td>");
					}
					$td.appendTo($tr);//加载初步分类的信息
				}else{//初步分类中的其他的分类
					if(valid){//其他分类中有选中的项
						$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' checked disabled='disabled'>" + json[i].value + "</td>");
						//给详细分类赋值
						$td.appendTo($tr);//这段代码很关键，如果不处理好，下面的方法执行的时候会报错
						fun_fdetailCaseType('finicasetype'+(i+1),json[i].key,'fdetailCaseType'+(i+1),fdetailCaseSelectedValue);//加载案件的详细分类，并设置选中项	
					}else{//其他分类中没有选中的项
						$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' disabled='disabled'>" + json[i].value + "</td>");
						$td.appendTo($tr);
					}
				}
			}


			
			input_autocoplete();
			
			var isCustomerValue = $("#customer").val();
			if(isCustomerValue==1){
				$('.cusRelation').hide();
				$("#fcusrelation1").hide();
				$("#fcusrelation").hide();
				$("#fidcarttitle").show();
				$("#fidcarttitle1").show();
				$("#fidcard").show();
				$("#cusSourceDiv").show();
			}else{
				$('.cusRelation').show();
				$("#fcusrelation1").show();
				$("#fcusrelation").show();
				$("#fidcarttitle").hide();
				$("#fidcarttitle1").hide();
				$("#fidcard").hide();
				$("#iscustome").show();
				$("#cusSourceDiv").show();
			}
<%--			var isstatusnameValue=$("#statusname").val();--%>
<%--			 if(isstatusnameValue=="完成放款"){--%>
<%--				$("#statusnamecase").hide();--%>
<%--			 }--%>
<%----%>
			 //var isfisviolateValue=$("#fisviolate").val();
			 	//if (isfisviolateValue=="无") {
					//$("#fisviolatedays").hide();
					//$("#fisviolateday").hide();
				//}	
			 	//客户状态
			 		var isCustomerValue = $("#fcusstatusid").val();
			 		$('.otherInfo0,.otherInfo1,.otherInfo2,.otherInfo3').hide();
			 		var cusStatus = $('#fcusstatusid');

			 		//如果是普惠金融
			 		if(isCustomerValue == '完成放款'){//完成放款，则将合同相关的信息都显示出来
			 			$('.otherInfo1').show();
			 		}else if(cusStatus.val() == '拒贷' ){
			 			$('.otherInfo0').show();
			 		}
			 		//显示客服姓名 ,初审人员 ,终审人员
			 		if( (cusStatus.val() == '完成放款')){
			 			$('.otherInfo0').hide();	
			 			$('.otherInfo1').show();
			 			$('.otherInfo2').show();
			 			$('.otherInfo3').show();
			 			$('#lcfa').hide();//隐藏理财方案
			 			$("#fviolateCountHistory").show();//历史违约次数
			 			

			 			var isfisviolateValue=$("#fisviolate").val();
			 			if (isfisviolateValue=="无") {
			 				$("#fviolatedaystr").hide();//违约天数	
			 			}else{
			 				$("#fviolatedaystr").show();//违约天数	
				 		}

						var fviolate=$('#fviolate').val();
			 			
			 			if(fviolate){
			 				$("#lxwysj").show();//违约时间
			 			}else{
			 				$("#lxwysj").hide();//违约时间
			 			}
			 		}else if((cusStatus.val() == '尚未完成面审')||(cusStatus.val() == '完成面审') ){
			 			$('.otherInfo3').show();//显示销售人员
			 			$('.otherInfo0,.otherInfo1,.otherInfo2').hide();
						
				 	}else if(isCustomerValue=='完成出借'){
				 		$('.otherInfo2').show();
						$("#fservicetype").show();
						$("#fservicetype2").show();
						$("#fstartorendtime1").show();
						$("#fstartorendtime2").show();

						$("#fext5time1").hide();
						$("#fext5time2").hide();
				 	}else if(isCustomerValue=='拒贷'){
				 		$('.otherInfo0').show();	
				 		$('#dkpz').show();
					}
				 	
		});
		//加载案件详细分类的信息
		function fun_fdetailCaseType1(id,finicasetype,fdetailCaseType,selectedValue){
			var $fcussourceid = $("#"+fdetailCaseType);
			if($("#"+id).attr('checked')=='checked'){
				$fcussourceid.val(selectedValue);
			}else{
				$fcussourceid.val(0);
			}
		}
		//加载案件详细分类的信息
		function fun_fdetailCaseType(id,finicasetype,fdetailCaseType,selectedValue){
			if($("#"+id).attr('checked')=='checked'){
				var $fcussourceid = $("#"+fdetailCaseType);
				loadSelectInfo(fdetailCaseType,"${requestScope.contextPath}/caseinfo/caseinfo!findDetailCaseType?finicasetype="+finicasetype,selectedValue);
			}else{
				$("#"+fdetailCaseType).empty().append( $("<option value='0'>--请选择--</option>") );
			}
		}
		//查询部门信息:选择部门信息时用
		function setAdmin(fdeptname,fcityname,fsaledepart,num){
			var strShowFdeptname = ''
			if(fdeptname != 'undefined'){
				$("#fdeptname"+num).val(fdeptname);
				strShowFdeptname = fdeptname;
			}
			if(fcityname != 'undefined'){
				$("#fcityname"+num).val(fcityname);
				strShowFdeptname += "-"+ fcityname;
			}
			if(fsaledepart != 'undefined'){
				$("#fsaledepart"+num).val(fsaledepart);
				strShowFdeptname += "_" + fsaledepart;
			}
			$("#showFdeptname"+num).val(strShowFdeptname);
		}

		
	</script>
	</head>
	<body>
		<div class="tableForm">
		<input type="hidden" id="customer" value="${mapCompliant.FISCUSTOMER}"/>
		<table>
			<tr><td colspan="4"><div class="title">案件信息——投诉信息</div></td></tr>
			<tr id="cusSourceDiv">
				<td class="t-title" width="120">投诉人：</td>
				<td><input class="t-text" 
					value="${mapCompliant.FCOMPLAINANT}<s:property value='complainInfo.fcomplainanter'/>" disabled="disabled"></td>
		    	<td class="t-title" >客户本人：</td>
		    	<td><select disabled="disabled"> 
	    			<option value="1" <s:if test="mapCompliant.FISCUSTOMER ==1">selected</s:if> >是 </option> 
	    			<option value="0" <s:if test="mapCompliant.FISCUSTOMER==0">selected</s:if> >否 </option> 
	    		</select></td>  
			</tr>
			<tr>
				<td class="t-title" width="120">客户姓名：</td>
				<td><input class="t-text" value="${mapCompliant.FCUSNAME}" disabled="disabled"></td>
		    	<td class="t-title">客户身份证号：</td>
		    	<td><input  class="t-text" value="${mapCompliant.FIDCARD}" disabled="disabled">
		    	
		    	</td>
			</tr>
			<tr>
				<td class="t-title" width="120">客户手机号码：</td>
				<td><input class="t-text" value="${mapCompliant.FMOBILE}" disabled="disabled"></td>
		    	<td class="t-title">客户座机：</td>
		    	<td><input class="t-text" value="${mapCompliant.FOFFICEPHONE}" disabled="disabled"></td>
			</tr>
			<tr>
				<td class="t-title" width="120">客户QQ：</td>
				<td><input class="t-text" value="${mapCompliant.FQQ}" disabled="disabled"></td>
		    	<td class="t-title">客户邮箱：</td>
		    	<td><input class="t-text" value="${mapCompliant.FEMAIL}" disabled="disabled"></td>
			</tr>
			<tr class="cusRelation">
				<td class="t-title" width="120">投诉人与客户关系：</td>
				<td><input class="t-text" value="${mapCompliant.FCUSRELATION}" disabled="disabled"></td>
			</tr>
			<tr class="cusRelation">
			    <td class="t-title" width="120">投诉人的手机号码：</td>
		    	<td><input class="t-text" value="${mapCompliant.FCOMPCONTACTINFO}" disabled="disabled"></td>
				<td class="t-title">投诉人的身份证号：</td>
				<td><input class="t-text" value="${mapCompliant.FCOMPLAINIDCARD}" disabled="disabled"></td>
		    	
			</tr>
			
			<tr class="cusRelation">
					<td class="t-title" width="120">投诉人的座机：</td>
					<td><input class="t-text" value="${mapCompliant.FEXT2}" disabled="disabled"></td>
					<td class="t-title">投诉人的QQ：</td>
					<td><input class="t-text" value="${mapCompliant.FEXT3}" disabled="disabled"></td>
			</tr>
					<tr class="cusRelation">
					<td class="t-title" width="120">投诉人的邮箱：</td>
					<td><input class="t-text" value="${mapCompliant.FEXT4}" disabled="disabled"></td>
				</tr> 	
				
				
			<%--					
				<tr>
	    			<td class="t-title">进件编号：</td>
	    			<td><input class="t-text" disabled="disabled" value="${mapCompliant.CASECOMEINTOCODE}" /></td>
	    			<td class="t-title">进件时间：</td>
	    			<td><input class="t-text Wdate" type="text" readonly="readonly"  
	    				value="${mapCompliant.CASECOMEINTODATE}" disabled="disabled" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
	    		</tr>
				
				<tr>
					<td class="t-title">案件来源：</td>
					<td>
						<select disabled="disabled"><option selected="selected">${mapCompliant.CASEFROMPLACE}</option></select>
					</td>
				</tr>
				
				 --%>
				
			<tr id="cusSourceDiv">
			 <td class="t-title" width="120">客户来源：</td>
		    	<td><input class="t-text" value="${mapCompliant.SOURCENAME}" disabled="disabled"></td>
			</tr>
			<c:if test="${mapCompliant.SOURCENAME=='其他'}">
				<tr id="cusSourceOtherDiv"><td class="t-title" width="120">备注：</td>
					<td colspan="3">
							<div class="item" style="width: 590px">
								<textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FREMARKS}</textarea>
								<div class="tooltip_description" style="display:none" title="备注">
									${mapCompliant.FREMARKS}
								</div>
						 </div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="t-title" width="120">服务类型：</td>
				<td><input class="t-text" value="${mapCompliant.SERVICENAMEREL}" disabled="disabled"></td>
		    	<td class="t-title">客户状态：</td>
		    	<td><input class="t-text" id="fcusstatusid" value="${mapCompliant.STATUSNAME}" disabled="disabled"></td>
			</tr>
			
			<tr class="otherInfo1">
				<td class="t-title" width="120">合同编号：</td>
				<td><input class="t-text" value="${mapCompliant.FCONTRACTNUMBER}" disabled="disabled"></td>
		    	<td class="t-title">金额：</td>
		    	<td><input class="t-text" value="${mapCompliant.FAMOUNT}" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title" width="120">期限：</td>
				<td><input class="t-text" value="${mapCompliant.FDEADLINE}" disabled="disabled"></td>
		    	<td class="t-title">还款起始日：</td>
		    	<td><input class="t-text" value="${mapCompliant.FREIMBSTRATTIME}" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title" width="120">还款终止日：</td>
				<td><input class="t-text" value="${mapCompliant.FREIMBENDTIME}" disabled="disabled"></td>
		    	<td class="t-title">签约时间：</td>
		    	<td><input class="t-text" value="${mapCompliant.FCONTRACTTIME}" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title" width="120">放款时间：</td>
				<td><input class="t-text" value="${mapCompliant.FLOANTIME}" disabled="disabled"></td>
		    	
			</tr>
			<tr class="otherInfo1" id="dkpz">
				<td class="t-title" width="120">贷款品种：</td>
		    	<td><select disabled="disabled">
		    		<option value="">--请选择--</option>
			    	<s:iterator value="loanBreads"><option value="<s:property value='key'/>" 
	  				<s:if test="mapCompliant.FLOANBREAD==key">selected</s:if> ><s:property value="value" /></option>
    			</s:iterator>
	    		</select></td>
			</tr>
			
			<tr class="otherInfo1">
				<td class="t-title">有无违约：</td>
				<td><input class="t-text" id="fisviolate"
					value="<s:if test='mapCompliant.FISVIOLATE==0'>无</s:if><s:else>有</s:else>" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1" id="fviolatedaystr" style="display: none;">
				<td class="t-title" id="fisviolateday">违约天数：</td>
		    	<td><input class="t-text" id="fisviolatedays" value="${mapCompliant.FVIOLATEDAYS}" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1" id="fviolateCountHistory" style="display: none;">
   					<td class="t-title">历史违约次数：</td>
    				<td><input type="text" name="complainInfo.fviolateCountHistory" id="fviolate"
						value='${mapCompliant.FVIOLATECOUNTHISTORY}' class="t-text easyui-validatebox" validType="integer"/>次</td>
    		</tr>

			<tr class="otherInfo2" style="display:none;" id="lcfa">
				<td class="t-title" style="display:none;" id="fservicetype">理财方案：</td>
				<td style="display:none" id="fservicetype2"><input class="t-text" value="${mapCompliant.FINANCIALPLAN}" disabled="disabled"></td>
			</tr>
			
			<tr class="otherInfo2" style="display:none;" id="lxwysj" style="display:none;">
				<td class="t-title" id="fstartorendtime1">首次违约时间：</td>
		    	<td id="fstartorendtime2"><input  class="t-text" value="${mapCompliant.FSTARTORENDTIME}" disabled="disabled"></td>
				<td class="t-title" id="fext5time1">连续违约时间：</td>
		    	<td id="fext5time2"><input  class="t-text" value="${mapCompliant.FEXT5}" disabled="disabled"></td>
			</tr>
			
			<tr class="otherInfo3">
				<td class="t-title">销售人员姓名：</td>
				<td><input class="t-text" id="fcusstatusid" value="${mapCompliant.FSALESMANNAME}" disabled="disabled"></td>
				<td class="t-title">客服姓名：</td>
				<td><input class="t-text" value="${mapCompliant.FSERVICENAME}" disabled="disabled"></td>
			</tr>
			
			<tr class="otherInfo0" style="display: none;"><td class="t-title">拒贷原因：</td>
			<td colspan="3">
				 <div class="item" style="width: 590px">
				 			<textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FDENIEDLOANS}</textarea>
							<div class="tooltip_description" style="display:none" title="拒贷原因">
								${mapCompliant.FDENIEDLOANS}
							</div>
					</div>
			</td>
			</tr>
			
			<tr class="otherInfo3" id="personnel">
<%--				<td class="t-title">销售人员姓名：</td>--%>
<%--				<td><input class="t-text" id="fcusstatusid" value="${mapCompliant.FSALESMANNAME}" disabled="disabled"></td>--%>
<%--		    	<td class="t-title">客服姓名：</td>--%>
<%--		    	<td><input class="t-text" value="${mapCompliant.FSERVICENAME}" disabled="disabled"></td>--%>
					<td class="t-title" id="ffirsttrial">初审人员：</td>
				<td><input class="t-text" value="${mapCompliant.FFIRSTTRIAL}" disabled="disabled"></td>
				<td class="t-title">终审人员：</td>
		    	<td><input class="t-text" value="${mapCompliant.FLASTTRIAL}" disabled="disabled"></td>
			</tr>
<%--			<tr class="otherInfo3" id="personnel2" >--%>
<%--				--%>
<%--		    	<td class="t-title">终审人员：</td>--%>
<%--		    	<td><input class="t-text" value="${mapCompliant.FLASTTRIAL}" disabled="disabled"></td>--%>
<%--			</tr>--%>
			<tr><td colspan="4"><div class="title">案件信息——详细信息</div></td></tr>
			<tr>
				<td class="t-title">投诉渠道：</td>
				<td><input class="t-text" value="${mapCompliant.DITCHNAME}" id="ffditchname" disabled="disabled"></td>
		    	<td class="t-title">投诉时间：</td>
		    	<td><input class="t-text" value="<s:date name="mapCompliant.FCOMPTIME" format="yyyy-MM-dd"/>" disabled="disabled"></td>
			</tr>
			<tr>
				<td id="SRCodeTh1" class="t-title">SR编码：</td>
				<td id="SRCodeTh2" colspan="3">
					<input type="text" disabled="disabled" value="${mapCompliant.SRCODE}" class="t-text easyui-validatebox" validType="number"/>
				</td>
			</tr>
			<tr>
				<td class="t-title">个人邮箱备注：</td>
				<td colspan="3">
					<div class="item" style="width: 590px">
								<textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FSELFEMAILREMARK}</textarea>
								<div class="tooltip_description" style="display:none" title="个人邮箱备注">
									${mapCompliant.FSELFEMAILREMARK}
								</div>
						 </div>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="t-title">书面证据：</td><td><select disabled="disabled"> --%>
<%--		    			<option value="1" <s:if test="mapCompliant.FISEVIDENCE==1">selected</s:if> >有 </option> --%>
<%--		    			<option value="0" <s:if test="mapCompliant.FISEVIDENCE==0">selected</s:if> >无 </option> --%>
<%--		    		</select></td>--%>
<%--		    	<td class="t-title">证据类型：</td>--%>
<%--		    	<td><input class="t-text" value="${mapCompliant.EVIDENCETYPENAME}" disabled="disabled"></td>--%>
<%--			</tr>--%>
<%--			<tr><td class="t-title">证据类型备注：</td>--%>
<%--				<td colspan="3"><textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FISEVIDENCEREMARKS}</textarea></td>--%>
<%--			</tr>--%>
			<tr>
				<td class="t-title">是否是内部员工：</td><td><select disabled="disabled"> 
		    			<option value="1" <s:if test="mapCompliant.FISINNER==1">selected</s:if> >是 </option> 
		    			<option value="0" <s:if test="mapCompliant.FISINNER==0">selected</s:if> >否 </option> 
		    		</select></td>
		    	<td class="t-title">涉及业务端：</td>
		    	<td><input class="t-text" value="${mapCompliant.SERVICENAME}" disabled="disabled"></td>
			</tr>
			
			<%--李如意 --%>
			<tr>
				<td class="t-title" >客服姓名：</td>
		    	<td><input class="t-text"  name="mapCompliant.FSERVICENAME" value="${mapCompliant.FSERVICENAME}" disabled="disabled"></td>
				<td class="t-title">是否收费：</td>
				<td>
					<select disabled="disabled">
						<option value='1' <c:if test="${mapCompliant.ISCHARGE==1 }">selected</c:if>>是</option>
						<option value='0' <c:if test="${mapCompliant.ISCHARGE!=1 }">selected</c:if>>否</option>
					</select>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="t-title">客户来源备注：</td>--%>
<%--				<td colspan="3">--%>
<%--					<div class="item" style="width: 590px">--%>
<%--							<textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FREMARKS}</textarea>--%>
<%--							<div class="tooltip_description" style="display:none" title="客户来源备注">--%>
<%--								${mapCompliant.FREMARKS}--%>
<%--							</div>--%>
<%--					 </div>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<!--解决案件初步分类和详细分类的问题 -->
				<tr><td class="t-title">案件初步分类：</td>
					<td colspan="5" style="padding:0;">
	      			<table style="margin:0px;">
						<tr id="check"></tr>
					</table></td>
				</tr>
		       	<tr><td class="t-title">案件详细分类：</td>
    		      	<td colspan="5" style="padding:0;">
	      			<table style="margin:0px;">
						<tr><td><select id="fdetailCaseType1" name="fdetailCaseType1" disabled='disabled'><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType2" name="fdetailCaseType2" disabled='disabled'><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType3" name="fdetailCaseType3" disabled='disabled'><option value='0'>--请选择--</option></select></td>
						</tr>
						<tr><td><select id="fdetailCaseType4" name="fdetailCaseType4" disabled='disabled'><option value='0'>--请选择--</option></select></td>
	    					<td colspan="2">
	    						<select id="fdetailCaseType5" name="fdetailCaseType5" disabled='disabled'><option value='0'>--请选择--</option></select>
    						</td>
						</tr>
					</table>
    		      	</td>
   		      	</tr>
   		      	
				<tr style="display: none;"  id="xiangXiFengLeiOtherDiv">
					<td class="t-title">案件初步分类备注：</td>
					<td colspan="5"><textarea name="complainInfo.finicasetypeRemark" disabled="disabled"
						rows="2" style="width:830">${mapCompliant.FINICASETYPEREMARK }</textarea></td>
				</tr>
				
				<tr>
					<td class="t-title">案件初步违规分类:</td>
					<td colspan="5" style="padding: 0">
						<table style="margin: 0" id="initable">
							<tr>
								<s:set value="0" var="tmpSelectOthers"></s:set>
								<s:iterator value="newCaseType" var="n" status="s">
									<td> 
									<s:if test="ischecked==1">
										<s:if test="id==27">
											<s:set value="1" var="tmpSelectOthers"></s:set>	
										</s:if>
										<input disabled="disabled" title="<s:property value="name"/>" checked="checked"  
										 type="checkbox" name="newfinicasetype" id="newfinicasetype<s:property value="id"/>" 
										 value="<s:property value="id"/>"><s:property value="name"/>
										
									</s:if>
									<s:else>
										<input disabled="disabled" title="<s:property value="name"/>"  
											type="checkbox" name="newfinicasetype" id="newfinicasetype<s:property value="id"/>" 
											value="<s:property value="id"/>"><s:property value="name"/>
									
									</s:else>
									
									
									
									</td>
								</s:iterator>
							</tr>
							
							
							
						</table>
					</td>
				</tr>
				
				
				
				<tr>
					<td class="t-title"></td>
					<td colspan="5">
						<div width="1000px">
							<ul style="list-style: none;margin: 0;padding: 0;" id="newfinicasetypeUL">
								<s:iterator value="newCaseType" var="n" status="s">
									<s:if test="ischecked==1">
										<s:if test="id!=27">
										
										
										<li id="newfinicasetypeli<s:property value="id"/>" style="float: left;width: 400px;height: 120px;margin: 0 20px 0 0;padding: 0;">
											<span style="height: 60px;vertical-align: bottom;display: inline-block;width: 80px;overflow: hidden;padding: 0;margin: 0;text-align: right;"><s:property value="name"/></span>
											
												<select multiple="multiple" size="6" style="text-align: left;width: 250px;height: 110px;padding: 0;margin: 0;" id="fnewdetailtypeid<s:property value="id"/>" name="fnewdetailtypeid<s:property value="id"/>"> 
													<s:iterator value="#n.detailCaseType" var="i">
														<s:if test="#i.ischecked==1">
																<option value="<s:property value="#i.id"/>"><s:property value="#i.name"/></option>
															</s:if>
													</s:iterator>
													
												</select>
												
											
										</li>
										
										</s:if>
									</s:if>
								
								</s:iterator>
								
							</ul>
							
							
						</div>
							
							
						
						
					</td>
					
				</tr>
				
				
				<s:if test="#tmpSelectOthers==1">
					<tr id="xiangXiFengLeiOtherDiv1">
						<td class="t-title">新分类备注：</td>
						<td colspan="5"><textarea name="complainInfo.fnewtypemark" disabled="disabled"
							rows="2" style="width:830">${mapCompliant.FNEWTYPEMARK }</textarea></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;"  id="xiangXiFengLeiOtherDiv1">
						<td class="t-title">新分类备注：</td>
						<td colspan="5"><textarea name="complainInfo.fnewtypemark" disabled="disabled"
							rows="2" style="width:830">${mapCompliant.FNEWTYPEMARK }</textarea></td>
					</tr>	
				</s:else>				
				
			<tr>
				<td class="t-title">案件具体描述：</td>
				<td colspan="3">
					<div class="item" style="width: 590px">
							<textarea rows="4" disabled="disabled" style="width:590;">${mapCompliant.FDETAILDESCRIP}</textarea>
								<div class="tooltip_description" style="display:none" title="案件具体描述">
									${mapCompliant.FDETAILDESCRIP}
								</div>
					 </div>
				</td>
			</tr>
			
				<%--		
				<tr>
		    		<td class="t-title">销售人员：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="${mapCompliant.SALEPERSON}"/></td>
		    		<td class="t-title">上级领导：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="${mapCompliant.SUPERLEADER}"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">间接领导：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="${mapCompliant.INDIRECTLEADER}"/></td>
		    		<td class="t-title">营业部：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="${mapCompliant.SALEDEPART}"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">客服姓名：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="${mapCompliant.SERVICENAME}"/></td>
		    	</tr>
			 --%>
			
			
			<tr>
				<td class="t-title">案件负责人：</td>
				<td><input class="t-text" value="${mapCompliant.FRESPONSIBLENAME}" disabled="disabled"></td>
		    	<td class="t-title">负责人邮箱：</td>
		    	<td><input class="t-text" value="${mapCompliant.FRESPONSIBLEEMAIL}" disabled="disabled"></td>
			</tr>
			
			<tr>
		    		<td class="t-title">潜在风险等级评估：</td>
			  		<td>
				  		<select id="complainInfo.riskLevel" name="complainInfo.riskLevel" disabled="disabled">
				  			<option value="1" <c:if test="${mapCompliant.FRISKLEVEL==1 }">selected</c:if>>高</option>
				  			<option value="2" <c:if test="${mapCompliant.FRISKLEVEL==2 }">selected</c:if>>中</option>
				  			<option value="3" <c:if test="${mapCompliant.FRISKLEVEL==3 }">selected</c:if>>低</option>
				  		</select>
			  		</td>
  					
		    	</tr>
		</table>
		</div>
		 <div style="background:#fff">
			 <%--李如意添加的 --%>
		    <div id="performance" class="easyui-tabs tabs-header-tab" style="width:1000px;height:130px;">
		    	<c:choose>
		    		<c:when test="${!empty performances}">
		    			<c:forEach var="p" items="${performances}" varStatus="status">
		    				<div title="业绩${status.index+1}">
		    					<%@include file="/pages/compliance/caseinfo/include/caseIncludePerformanceView.jsp" %>	
		    				</div>
		    			</c:forEach>
		    		</c:when>
		    		<%--
		    		<c:otherwise>
		    			<%@include file="/pages/compliance/caseinfo/include/caseEmptyPerformanceView.jsp" %>
		    		</c:otherwise>
		    		 --%>
		    	</c:choose>
		    </div>
		<%--李如意  收费 --%>
			<c:if test="${!empty chargeInfos}">
			 	<!-- 收费 -->
			 	<div class="easyui-tabs tabs-header-tab" style="width:1000px;height:190px;">
	    		  	<c:forEach var="charge" items="${chargeInfos}" varStatus="status">
	    		  		<div title="收费${status.index+1}">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeChargeView.jsp" %>
						</div>
	     		 	</c:forEach>
			 	</div>
	    	</c:if>
			
		    <c:if test="${!empty deptinfo}">
			 	<!-- 被投诉部门 -->
			 	<div id="deptAll" class="easyui-tabs" style="width:1000px;height:150px;">
	    		  	<c:forEach var="dept" items="${deptinfo}" varStatus="status">
	    		  		<div title="被投诉部门${status.index+1}">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeDeptView.jsp" %>
						</div>
	     		 	</c:forEach>
			 	</div>
	    	</c:if>
			
	   	    <c:if test="${!empty InnPersons}">
			  	<div id="personAll" class="easyui-tabs" style="width:1000px;height:400px;">
	    	    	<c:forEach var="person" items="${InnPersons}" varStatus="status" >
	    		  		<div title="内部人员${status.index+1}" style="padding:0;">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeInnerView.jsp" %>
						</div>
					</c:forEach>
		    	</div>
	   	    </c:if>
	   	    
	  	    <c:if test="${!empty outPersons}">
			  	<div id="personAll" class="easyui-tabs" style="width:1000px;height:320px;">
		  	    	<c:forEach var="outPerson" items="${outPersons}" varStatus="status">
	    		  		<div title="外部人员${status.index+1}" style="padding:0;">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeOutterView.jsp" %>
				    	</div>
					</c:forEach>
		    	</div>
	  	    </c:if>
	  	</div>
    	<input type="hidden" id="winURL" value="pages/compliance/caseinfo/selectAdmin.jsp"/>
	</body>
</html>