<%@ page language="java" import="java.util.*,com.creditease.eas.admin.bean.User"  pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案件详情</title>
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/multiselect.css">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/multiselect.js"></script>
  	<script type="text/javascript">
		$(function() {
		$("#fresponsibleEmail").validatebox({required: true,missingMessage: '该输入项为必填项'});
			//加载投诉渠道
			loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch');
			//投诉渠道是个人邮箱时，需要绑定事件
			//加载证据类型
			loadSelectInfo('fevidencetypeid','<%=basePath%>/caseinfo/caseinfo!findEvidenceType');
			//加载服务类型
			loadSelectInfo('fbusportid','<%=basePath%>/caseinfo/caseinfo!findServicetype');
			//初始化投诉时间
			$('#fcompTime').val(iniDate());
			//加载案件初步分类：动态的生成table
			var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			var $tr=$("<tr></tr>");
			$tr.appendTo($tbfinicasetype);
			for(var i=0;i<json.length;i++){
				var $td = '';
				if(json[i].value == '其他'){
					$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' onclick=fun_otherCheck('finicasetypeOther','xiangXiFengLeiOtherDiv')>" + json[i].value + "</td>");
				}else{
					$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
				}
				$td.appendTo($tr);
			}


			
			input_autocoplete();
		});

		function selectCaseType(i){
			var fdetailCaseType=$("#fdetailCaseType"+i);
			if(fdetailCaseType.val()!='0'){
				$("#finicasetype"+i).attr("checked","true");
			}else{
				$("#finicasetype"+i).removeAttr("checked");	
			}
		}

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
		function fun_fdetailCaseType(id,finicasetype,fdetailCaseType){
			if($("#"+id).attr('checked')=='checked'){
				var $fcussourceid = $("#"+fdetailCaseType);
				loadSelectInfo(fdetailCaseType,"<%=basePath%>/caseinfo/caseinfo!findDetailCaseType?finicasetype="+finicasetype);
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

		var numChargeEdit=1;
		function fun_addChargeEdit(){
			numChargeEdit += 1;
		    $("#charge").tabs('add',{
		    	title: '收费'+numChargeEdit,
		    	content:'<table><tr>'+
		    	'<td class="t-title" width="120">收费人员：</td>'+
		    	'<td><select id="ffreceiveType" name="ffreceiveType">'+
		    	'<option value="0">请选择 </option>'+
		    	'<option value="1">员工</option>'+
		    	'<option value="2">中介</option>'+
		    	'<option value="3">与同行串通</option>'+
		    	'</select></td>'+
		    	'<td class="t-title" width="120">收费理由：</td><td><select id="fftype" name="fftype"><option value="0">请选择 </option><option value="1">好处费</option>'+
		    	'<option value="2">客户包装费</option><option value="3">代客户还款</option><option value="4">办理外部业务</option><option value="5">协办征信、流水</option></select></td> </tr>'+
		    	' <tr><td class="t-title" width="120">给付方式：</td><td><select id="ffmode" name="ffmode"><option value="0">请选择</option>'+
		    	'<option value="1">现金</option><option value="2">转账</option><option value="3">从客户账户中直接支取</option></select></td>'+
		    	'<td class="t-title">收费金额：</td><td><input type="text" validType="intOrFloat" id="ffamount" name="ffamount" value="" class="t-text easyui-validatebox"/>元</td></tr>',
				closable:true
		    });
		}
		var numPerformanceEdit=1;
		function fun_addPerformanceEdit(){
			numPerformanceEdit += 1;
		    $("#performance").tabs('add',{
		    	title: '业绩'+numPerformanceEdit,
		    	content:'<div title="业绩'+numPerformanceEdit+'">'+
		    	'<table><tr><td class="t-title" width="120">被投诉人员业绩：</td>'+
		    	'<td><input type="text" validType="intOrFloat" id="ffcomplanantPerformance" name="ffcomplanantPerformance" class="t-text easyui-validatebox" />'+
		    	'元</td>'+
		    	//'<td class="t-title" width="120">所在团队业绩：</td><td><input type="text" id="fftermPerformance" validType="intOrFloat" name="fftermPerformance" class="t-text easyui-validatebox" />万元</td>'+
		    	'</tr></table></div>',
				closable:true
		    });
		}

		//是否收费
		function changeCharge(selectValue){
			if(selectValue=="1"){
				$("#charge").css("display","block");
			}else{
				$("#charge").css("display","none");
			}
		}

		multipleModel.imgSrc='<%=basePath%>/images/pic7.gif';

		


	</script>
	</head>
  	<body>
  		<div class="tableForm">
	   	<form method="post" action="<%=basePath%>caseinfo/caseinfo!addCaseInfo" id="editForm">
	   		<input type="hidden" value='${jsonComplainInfo}' name="jsonComplainInfo" id="jsonComplainInfo"/>
			<input type="hidden" name="complainInfo.fid" value="${param.fid }">
			<div class="title">案件详细编辑</div>
			<table>
				<tr><td class="t-title" width="100">投诉渠道：</td>
					<td><select id="fcompchannelid" name="complainInfo.fcompchannelid" onchange="qiuDaoSelectFun();"></select></td>
					<td class="t-title">投诉时间 ：</td>
					<td><input class="t-text Wdate" type="text" name="complainInfo.fcomptime" id="fcomptime" readonly="readonly" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
				</tr>
				
				<tr>
					<td id="SRCodeTh1" style="display: none;" class="t-title" width="100">SR编码：</td>
					<td id="SRCodeTh2" style="display: none;">
						<input type="text" name="complainInfo.SRCode" class="t-text easyui-validatebox" validType="number"/>
					</td>
				</tr>
				
				<tr style="display: none" id="qiuDaoGeRenDiv">
					<td class="t-title">个人邮箱备注 ：</td>
					<td colspan="3"><textarea name="fselfEmailRemark" rows="3" style="width:580"></textarea></td>
				</tr>
				
<%--				<tr><td class="t-title">书面证据：</td>--%>
<%--					<td><select id="fisevidence" name="complainInfo.fisevidence">--%>
<%--						<option value='1'>有</option><option value='0'>无</option>--%>
<%--					</select></td>--%>
<%--					<td class="t-title">证据类型：</td>--%>
<%--					<td><select name="complainInfo.fevidencetypeid" id="fevidencetypeid" --%>
<%--						onchange="addOtherCommon('fevidencetypeid','5','zhengMingOtherDiv');"></select></td>--%>
<%--				</tr>--%>
				
				<tr style="display: none" id="zhengMingOtherDiv">
					<td class="t-title">证据类型备注：</td>
					<td colspan="3"><textarea rows="3" name="complainInfo.fisevidenceRemarks" style="width:580"></textarea></td>
				</tr>
				
				<tr><td class="t-title">是否是内部员工：</td>
					<td><select id="fisinner" name="complainInfo.fisinner">
						<option value='1'>是</option><option value='0'>否</option>
					</select></td>
					<td class="t-title">涉及业务端：</td>
					<td><select name="complainInfo.fbusportid" id="fbusportid" validType="njection" class="t-text easyui-validatebox"></select></td>
				</tr>
				
				<%--李如意 --%>
				<tr>
					<td class="t-title">是否收费：</td>
					<td colspan="5"> 
						<select id="isCharge" name="complainInfo.isCharge" onchange="changeCharge(this.options[this.options.selectedIndex].value);">
							<option value='1'>是</option>
							<option value='0'>否</option>
						</select>
					</td>
				</tr>
				
				<tr><td class="t-title">案件初步分类：</td>
					<td colspan="3"><table id="tbfinicasetype"></table></td>
				</tr>
				
				<tr><td class="t-title">案件详细分类：</td>
					<td colspan="3">
						<table>
							<tr><td><select id="fdetailCaseType1" name="fdetailCaseType1"><option value='0'>--请选择--</option></select></td>
								<td><select id="fdetailCaseType2" name="fdetailCaseType2"><option value='0'>--请选择--</option></select></td>
								<td><select id="fdetailCaseType3" name="fdetailCaseType3"><option value='0'>--请选择--</option></select></td>
							</tr>
							<tr>
								<td><select id="fdetailCaseType4" name="fdetailCaseType4"><option value='0'>--请选择--</option></select></td>
								<td colspan="2"><select id="fdetailCaseType5" 
									name="fdetailCaseType5"><option value='0'>--请选择--</option></select></td>
							</tr>
						</table>
		    		</td>
				</tr>
				
				<tr style="display: none"  id="xiangXiFengLeiOtherDiv">
					<td class="t-title">案件初步分类备注：</td>
						<td colspan="3"><textarea name="complainInfo.finicasetypeRemark" rows="3" style="width:580"></textarea></td>
				</tr>
				    
				    
				  <tr>
				  
					<td nowrap="nowrap" class="t-title">案件初步违规分类:</td>
					<td nowrap="nowrap" colspan="5" style="padding: 0">
						<table style="margin: 0" id="initable">
							<tr>
								<s:iterator value="newCaseType" var="n" status="s">
									<s:if test="id!=27">
										<td> <input title="<s:property value="name"/>" onclick='multipleModel.produce_newCaseTypeDetail(this)' 
									type="checkbox" name="newfinicasetype" id="newfinicasetype<s:property value="id"/>" 
									value="<s:property value="id"/>"><s:property value="name"/>	
									
										<div id="popdiv_<s:property value="id"/>" class="pop-div">
										  <ul>
										  	<s:iterator value="#n.detailCaseType" var="i">
										  		<li style="width: 370px;"> <input type="checkbox" title="<s:property value="#i.name"/>" name="detailCaseTypeIni<s:property value="#n.id"/>" value="<s:property value="#i.id"/>"/>
										  			<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
										  		</li>
										  	</s:iterator>
										  	
										  </ul>
										  
										 <div style="clear: left;" align="center">
										  	<input class="pop-but" type="button"  value="确定" onclick="multipleModel.makesureCaseType(<s:property value="id"/>)"/>
										  	<input class="pop-but" type="button" value="取消" onclick="multipleModel.cancleCaseType(<s:property value="id"/>)"/>
								  		 </div>
										  
									</div>
									
									</td>
									
									</s:if>
									<s:else>
										
										<td> <input title="<s:property value="name"/>" onclick='fun_otherCheck("finicasetypeOther1","xiangXiFengLeiOtherDiv1")' 
									type="checkbox" name="newfinicasetype" id="finicasetypeOther1" 
									value="<s:property value="id"/>"><s:property value="name"/> </td>
										
									</s:else>
									
									
									
								
									
									
								</s:iterator>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td class="t-title"></td>
					<td colspan="5">
						<div>
							<ul style="list-style: none;margin: 0;padding: 0;" id="newfinicasetypeUL">
								
								<%--
								<li style="float: left;width: 400px;height: 150px;"> 
									<span style="height:150px; vertical-align:top; display:inline-block; width:90px; overflow:hidden">000000</span>
									
									<select multiple="multiple" size="10" style="text-align:left;width:300px;height:150px;"> 
											<option >0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>
											<option>0000000000</option>						
									 </select>
							
							
								</li>
								
								<li style="float: left;width: 400px;height: 150px;"> 
									<select multiple="multiple" size="10" style="text-align:left;width:300px;height:150px;"> 
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>						
									</select>
								</li>
								
								<li style="float: left;width: 400px;height: 150px;"> 
									<select multiple="multiple" size="10" style="text-align:left;width:300px;height:150px;"> 
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>
										<option>0000000000</option>						
									</select>
								 --%>
							</ul>
							
							
						</div>
							
							
						
						
					</td>
					
				</tr>
				
				
				<tr style="display: none;"  id="xiangXiFengLeiOtherDiv1">
					<td class="t-title">新分类备注：</td>
					<td colspan="5"><textarea name="complainInfo.fnewtypemark" 
						rows="2" style="width:830"></textarea></td>
				</tr>	   
				    
				<tr><td class="t-title">案件具体描述：</td>
					<td colspan="3"><textarea rows="3" name="complainInfo.fdetaildescrip" style="width:580"></textarea></td>
		    	</tr>
		    	
		    	<%--
		    	<tr>
		    		<td class="t-title">销售人员：</td>
		    		<td><input type="text" name="complainInfo.salePerson" class="t-text" /></td>
		    		<td class="t-title">上级领导：</td>
		    		<td><input type="text" name="complainInfo.superLeader" class="t-text" /></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">间接领导：</td>
		    		<td><input type="text" name="complainInfo.indirectLeader" class="t-text" /></td>
		    		<td class="t-title">营业部：</td>
		    		<td><input type="text" name="complainInfo.saleDepart" class="t-text" /></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">客服姓名：</td>
		    		<td><input type="text" name="complainInfo.serviceName" class="t-text" /></td>
		    	</tr>
		    	  --%>
		    	  
		    	<tr>
					<td class="t-title">案件负责人：</td>
					<td><input type="text" name="complainInfo.fresponsibleName" value="${user.email }" 
							class="t-text easyui-validatebox" required="true" missingMessage="该输入项为必输项"/><span class="required">*</span></td>
		    		<td class="t-title">负责人邮箱：</td>
					<td colspan="3"><input type="text" name="complainInfo.fresponsibleEmail" id="fresponsibleEmail" 
						class="t-text easyui-validatebox" validType="email" 
						value="${user.email }" required="true" missingMessage="该输入项为必输项"/><span class="required">*</span></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">潜在风险等级评估：</td>
			  		<td>
				  		<select id="complainInfo.riskLevel" name="complainInfo.riskLevel">
				  			<option value="1">高</option>
				  			<option value="2">中</option>
				  			<option value="3">低</option>
				  		</select>
			  		</td>
		    	</tr>
		    </table>
		    
		    <%--李如意添加的 --%>
		     <div id="performance" class="easyui-tabs tabs-header-tab" style="width:1000px;height:120px;" tools="#performance-tab-tools">
		     	<%@include file="/pages/compliance/caseinfo/include/caseEmptyPerformance.jsp" %>
		    </div>
		    <div id="performance-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addPerformanceEdit();"></a>
			</div>
		    
		    
		    <div id="charge" class="easyui-tabs tabs-header-tab" style="width:1000px;height:170px;"  tools="#charge-tab-tools">
		 		<%@include file="/pages/compliance/caseinfo/include/caseEmptyCharge.jsp" %>
		  	</div>
		  	<div id="charge-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addChargeEdit()"></a>
			</div>
		    
<%--	 	 		<div id="dept" class="easyui-accordion" style="width:1000px;height:160px;">  --%>
<%--		 		<div title="被投诉部门&nbsp;&nbsp;<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_addDept();'>新增</a>--%>
<%--				    	<a href='javascript:fun_del('dept','被投诉部门');' class='easyui-linkbutton' onclick=fun_del('dept','被投诉部门')>删除 </a>" --%>
<%--				    	data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">--%>
		    <div id="dept" class="easyui-tabs" style="width:1050px;height:150px;" tools="#dept-tab-tools">
				<div title="被投诉部门" closable=true>
					<table>
			    		<tr><td class="t-title" width="60">部门名称：</td>
							<td><input type="text" name="dfdeptname" id="dfdeptname" required="true" validType="charLength[1,20]" 
								 missingMessage="该输入项为必输项" 
								 class="t-text easyui-validatebox"/><span class="required">*</span></td>
							<td class="t-title" width="60">城市名称：</td>
							<td><input type="text" name="dfcityname" value="" class="t-text easyui-validatebox"/></td>
							<td class="t-title" width="80">营业部名称：</td>
							<td><input type="text" name="dfsaledepart" value="" class="t-text easyui-validatebox"/></td>
			    		</tr>
			    	</table>
				</div>
			</div>
			<div id="dept-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addDept()"></a>
			</div>
			
			
<%--	     <div id="personAll" style="width: 80%"><div align="center"><strong style="color: blue">被投诉人信息</strong></div></div> --%>
<%--			    <div id="aa" class="easyui-accordion" style="width:1000px;height:200px;">  --%>
<%--				    <div title="内部人员&nbsp;&nbsp;<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_addInnerOut();'>新增</a>--%>
<%--				    	<a href='javascript:fun_del('aa','内部人员');' class='easyui-linkbutton' onclick=fun_del('aa','内部人员')>删除</a>" --%>
<%--				    	data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">--%>
			
			<div id="aa" class="easyui-tabs" style="width:1000px;height:450px;" tools="#inner-tab-tools">
				<div title="内部人员" closable=true>
		    		<input type="hidden" name="fisinner" value='1'/>
		    		<input type="hidden" name="fcompanyname" value=""/>
		    		<input type="hidden" name="fbycompliantadd" value=""/>
		    		<input type="hidden" name="fisagent" value=""/>
		    		<input type='hidden' name='fnotagentRemark' value=''/>
			    	<table>
	    				<tr><td class="t-title" width="100">被投诉人姓名：</td>
							<td><input type="text" name="fname" id="fname" class="t-text easyui-validatebox" required="true" 
								validType="charLength[1,20]" missingMessage="该输入项为必输项" 
								onkeyup="fun_loadCompInfo('fname','fcomplaintcount','fcaseInfo0','frefercase0','fext10','${requestScope.contextPath}');"/>
								<span class="required">*</span></td>
							<td class="t-title" width="100">手机号码：</td>
							<td><input type="text" name="fmobile" value="" class="t-text easyui-validatebox" validType="mobiles"/></td>
			    		</tr>
			    		
			    		<tr><td class="t-title">座机：</td>
							<td><input type="text" name="fofficephone" class="t-text easyui-validatebox" validType="phone"/></td>
			    			<td class="t-title">QQ：</td>
							<td><input type="text" name="fqq" value="" class="t-text easyui-validatebox" validType="qq"/></td>
			    		</tr>
			    		
			    		<tr><td class="t-title">邮箱：</td>
							<td><input type="text" name="femail" value="" class="t-text easyui-validatebox" validType="email"/></td>
							<td class="t-title">职位名称：</td>
							<td><input type="text" name="flevel" class="t-text"/></td>
			    		</tr>
			    		
			    		<tr><td class="t-title">组织信息：</td>    
							<td colspan="3">
								<input type="text" id="showFdeptname" onclick="openWinComp('部门信息','win','')"
									value="" readonly="readonly" class="t-text"/>
								<label onclick="openWinComp('部门信息','win','')"><font color="blue">选择</font></label>
								<input type="hidden" name="fdeptname" id="fdeptname"/>
								<input type="hidden" name="fcityname" id="fcityname"/>
								<input type="hidden" name="fsaledepart" id="fsaledepart"/>
							</td>
			    		</tr>
				    	
				    	<tr><td class="t-title">入职时间：</td>
			    			<td><input type="text" name="fentrytime" id="fentrytime" readonly="readonly" 
 						 		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
			    			<td class="t-title">上家工作单位：</td>
			    			<td><input type="text" name="flastworkunit" class="t-text"/></td>
			    		</tr>
			    		
			    		<tr><td class="t-title">学历：</td>
			    			<td><input type="text" name="feducation" class="t-text"/></td>
			    			<td class="t-title">推荐人：</td>
			    			<td><input type="text" name="freferrer" value="" class="t-text"/></td>
			    		</tr>
				    		
			    		<tr><td class="t-title">被投诉的次数：</td>
			    			<td><input type="text" name="fcomplaintcount" id="fcomplaintcount" value="0" 
			    				readonly="readonly" class="t-text"/></td>
			    			<td class="t-title">涉及到的案子：<input type="hidden" name="frefercase" id="frefercase0"/>
			    				<input type="hidden" name="fext1" id="fext10"/></td>
			    			<td id="fcaseInfo0">无</td>
			    		</tr>
			    	</table>
			    </div>
			</div> 
			<div id="inner-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addInnerOut('${requestScope.contextPath}')"></a>
			</div>
<%--				<div id="aa1" class="easyui-accordion" style="width:1000px;height:200px;">--%>
<%--			    <div title="外部人员&nbsp;&nbsp;<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_addOut();'>新增</a>--%>
<%--			    	<a href='javascript:void(0)' class='easyui-linkbutton' onclick=fun_del('aa1','外部人员')>删除</a>" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">  --%>
			<div id="aa1" class="easyui-tabs" style="width:1000px;height:400px;" tools="#out-tab-tools">
    			<input type="hidden" name="fisinner" value='0'/>
    			<div title="外部人员" closable=true>
		    	<table>
	    			<tr><td class="t-title" width="100">被投诉人姓名：</td>
						<td><input type="text" name="fname" class="t-text easyui-validatebox"
						validType="charLength[1,20]" missingMessage="该输入项为必输项" required="true" /><span class="required">*</span></td>
						<td class="t-title" width="100">手机号码：</td>
						<td><input type="text" name="fmobile" value="" class="t-text easyui-validatebox" validType="mobiles"/></td>
		    		</tr>
		    		
		    		<tr><td class="t-title">座机：</td>
						<td><input type="text" name="fofficephone" value="" class="t-text easyui-validatebox" validType="phone"/></td>
	    				<td class="t-title">QQ：</td>
						<td><input type="text" name="fqq" value="" class="t-text easyui-validatebox" validType="qq"/></td>
		    		</tr>
		    		
		    		<tr><td class="t-title">邮箱：</td>
						<td><input type="text" name="femail" value="" class="t-text easyui-validatebox" validType="email"/></td>
						<td class="t-title">公司名称：</td>
						<td><input type="text" name="fcompanyname" class="t-text"/></td>
		    		</tr>
		    		
		    		<tr><td class="t-title">城市：</td>
						<td><input type="text" name="fcityname" class="t-text"/></td>
						<td class="t-title">被投诉人地址：</td>
						<td><input type="text" name="fbycompliantadd" class="t-text"/></td>
		    		</tr>
		    		
		    		<tr><td class="t-title">是否为中介：</td>
						<td colspan="3"><select id="fisagent" name="fisagent" onchange="zhongJieSelectFun();">
							<option value="1">是</option><option value="0">否</option></select></td>
		    		</tr>
		    		
		    		<tr style="display: none" id="shiFouZhongJie">
						<td class="t-title">备注：</td>
						<td colspan="3"><textarea name="fnotagentRemark" rows="3" style="width:580"></textarea></td>
					</tr>
		    	</table>
		    </div>  
		</div>
		<div id="out-tab-tools">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addOut()"></a>
		</div>
		
		<div class="t-but">
    		<a href="javascript:void(0);" class="but-change" onclick="multipleModel.dosubmitCompliance1('editForm');">保存</a>
	    </div>
	    <input type="hidden" id="winURL" value="pages/compliance/caseinfo/selectAdmin.jsp"/>
	    <input type="hidden" id="winCaseTypeURL" value="pages/compliance/caseinfo/selectCaseTypeInfo.jsp"/>
		</form>
		</div>
     	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
	  		iconCls="icon-save" style="width:1000px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<%--新分类 --%>
		<div id="iframeCaseType" class="easyui-window" title="案件初步违规详细分类" modal="true" closed="true"
	  		iconCls="icon-save" style="width:1000px;height:400px;padding:10px;">
			<iframe id="iframeCaseTypeSource" name="iframeCaseTypeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
