<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="elself" uri="/eltag" %>
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
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/multiselect.js"></script>
  	<script type="text/javascript">
		$(function() {
			var isCharge=$("#fisCharge").val()==null || $("#fisCharge").val()=="" ? 0 :$("#fisCharge").val();
	
			$("#isCharge").val(isCharge);
			if(isCharge=="1"){
				$("#charge").css("display","block");
			}else{
				$("#charge").css("display","none");
			}
			
			
			$("#fresponsibleEmail").validatebox({required: true,missingMessage: '该输入项为必填项'});
			//加载投诉渠道
			loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch',${complainInfo.fcompchannelid});
			var selectedcomchannelid=${complainInfo.fcompchannelid};
			if(selectedcomchannelid==1){
				$("#SRCodeTh1").show();
				$("#SRCodeTh2").show();
			}else{
				$("#SRCodeTh1").hide();
				$("#SRCodeTh2").hide();
			}
			var tbfinicasetype = $("tbfinicasetype").val();
			qiuDaoSelectFun();//调用下投诉渠道的显示用的方法
			//投诉渠道是个人邮箱时，需要绑定事件
			//加载证据类型
			loadSelectInfo('fevidencetypeid','<%=basePath%>/caseinfo/caseinfo!findEvidenceType','${complainInfo.fevidencetypeid}');
			addOtherCommon('fevidencetypeid','5','zhengMingOtherDiv');
			//加载服务类型
			loadSelectInfo('fbusportid','<%=basePath%>/caseinfo/caseinfo!findServicetype','${complainInfo.fbusportid}');

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
				 	if('${relInicasetype.inicasetypeid}' == json[i].key){
						valid = true;
						fdetailCaseSelectedValue = '${relInicasetype.detailcasetypeid}';
				 }
			   </c:forEach>
			   //显示初步分类的信息
			   if(json[i].value == '其他'){
					if(valid){
						$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' checked onclick=fun_otherCheck('finicasetypeOther','xiangXiFengLeiOtherDiv')>" + json[i].value + "</td>");
						//显示初步分类的备注
<%--						$("#xiangXiFengLeiOtherDiv").css({display:"''"});
					  改成下面的方式，解决界面样式混乱的问题
--%>
						$("#xiangXiFengLeiOtherDiv").show();
					}else{
						$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' onclick=fun_otherCheck('finicasetypeOther','xiangXiFengLeiOtherDiv')>" + json[i].value + "</td>");
					}
					$td.appendTo($tr);//加载初步分类的信息
				}else{//初步分类中的其他的分类
					if(valid){//其他分类中有选中的项
						$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' checked onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
						//给详细分类赋值
						$td.appendTo($tr);//这段代码很关键，如果不处理好，下面的方法执行的时候会报错
						fun_fdetailCaseType('finicasetype'+(i+1),json[i].key,'fdetailCaseType'+(i+1),fdetailCaseSelectedValue);//加载案件的详细分类，并设置选中项	
					}else{//其他分类中没有选中的项
						$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
						$td.appendTo($tr);
					}
				}
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
		var numEdit = ${requestScope.innPersonSize}=="" ?0:${requestScope.innPersonSize};
		//添加内部员工
		function fun_addInnerOutEdit(){
			numEdit += 1;//每添加一条记录数量加1
		    $("#aa").tabs('add',{
		    	title: '内部人员' + numEdit,
		    	content:"<input type='hidden' name='fisinner' value='1'/>"+
	    				"<input type='hidden' name='fcompanyname' value=''/>"+
			    	    "<input type='hidden' name='fbycompliantadd' value=''/>"+
			    	    "<input type='hidden' name='fisagent' value=''/>"+
			    	    "<input type='hidden' name='fnotagentRemark' value=''/>"+
				    	"<table>"+
			    		"<tr>"+
						"<td class=\"t-title\" width=\"100\">被投诉人姓名：</td>"+
						"<td><input type=\"text\" name=\"fname\" id=\"fname"+numEdit+"\" validType=\"charLength[1,20]\" missingMessage=\"该输入项为必输项\" required=\"true\" class=\"t-text easyui-validatebox\" " +
						"onkeyup=\"fun_loadCompInfoInner('fname"+numEdit+"','fcomplaintcount"+numEdit+"','fcaseInfo"+numEdit+"','frefercase"+numEdit+"','fext1"+numEdit+"','${requestScope.contextPath}');\"/><span class=\"required\">*</span></td>"+
						"<td class=\"t-title\" width=\"100\">手机号码：</td>"+
						"<td><input type=\"text\" name=\"fmobile\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"mobiles\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
						"<td class=\"t-title\">座机：</td>"+
						"<td><input type=\"text\" name=\"fofficephone\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"phone\"/></td>"+
						"<td class=\"t-title\">QQ：</td>"+
						"<td><input type=\"text\" name=\"fqq\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"qq\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
						"<td class=\"t-title\">邮箱：</td>"+
						"<td><input type=\"text\" name=\"femail\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"email\"/></td>"+
						"<td class=\"t-title\">职位名称：</td>"+
						"<td><input type=\"text\" name=\"flevel\" class=\"t-text\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
						"<td class=\"t-title\">组织信息：</td>"+    
						"<td colspan=\"3\">"+
						"<input type='text'  id='showFdeptname"+numEdit+"' onclick=openWinComp('部门信息','win',"+numEdit+") value='' readonly='readonly' class=\"t-text\"/>"+
						"<label  onclick=openWinComp('部门信息','win',"+numEdit+")><font color='blue'>选择</font></label>"+
						"<input type='hidden' name='fdeptname' id='fdeptname"+numEdit+"'/>"+
						"<input type='hidden' name='fcityname' id='fcityname"+numEdit+"'/>"+
						"<input type='hidden' name='fsaledepart' id='fsaledepart"+numEdit+"'/>"+
						"</td>"+
						"</tr>" +
						"<tr>" +
						"<td class=\"t-title\">入职时间：</td>"+
						"<td>"+
	    				"<input type=\"text\" name=\"fentrytime\" id=\"fentrytime\" readonly=\"readonly\" "+ 
						"onfocus=\"WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" class=\"t-text Wdate\"/>"+
						"</td>"+
						"<td class=\"t-title\">上家工作单位：</td>"+
						"<td><input type=\"text\" name=\"flastworkunit\" class=\"t-text\"/></td>"+
						"</tr>"+
						"<tr>"+
						"<td class=\"t-title\">学历：</td>"+
						"<td><input type=\"text\" name=\"feducation\" class=\"t-text\"/></td>" +
						"<td class=\"t-title\">推荐人：</td>"+
						"<td>"+
						"<input type=\"text\" name=\"freferrer\" value=\"\" class=\"t-text\"/>"+
						"</td>"+
					 	"</tr>"+
					 	"<tr>"+
						"<td class=\"t-title\">被投诉的次数：</td>"+
						"<td><input type=\"text\" name=\"fcomplaintcount\" id=\"fcomplaintcount"+numEdit+"\" value=\"0\" readonly=\"readonly\" class=\"t-text\"/></td>"+
						"<td class=\"t-title\">涉及到的案子：<input type='hidden' name='frefercase' id='frefercase"+numEdit+"'/><input type='hidden' name='fext1' id='fext1"+numEdit+"'/></td>"+
						"<td id=\"fcaseInfo"+numEdit+"\">无"+
						"</td>"+
					 	"</tr>"+
			    	"</table>",
		    	closable:true
		    });
		}
		var numOutEdit = ${requestScope.outPersonSize}==null ?0:${requestScope.outPersonSize};
		/***
		 * 添加外部人员
		 * @return
		 */
		function fun_addOutEdit(){
			
			numOutEdit += 1;
			
		    $("#aa1").tabs('add',{
		    	title: '外部人员'+numOutEdit,
		    	content:"<input type='hidden' name='fisinner' value='0'/>"+
				    	"<table>"+
			    		"<tr>"+
							"<td class=\"t-title\" width=\"100\">被投诉人姓名：</td>"+
							"<td><input type=\"text\" name=\"fname\" validType=\"charLength[1,20]\" missingMessage=\"该输入项为必输项\" required=\"true\" class=\"t-text easyui-validatebox\"/><span class=\"required\">*</span></td>"+
							"<td class=\"t-title\" width=\"100\">手机号码：</td>"+
							"<td><input type=\"text\" name=\"fmobile\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"mobiles\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
							"<td class=\"t-title\">座机：</td>"+
							"<td><input type=\"text\" name=\"fofficephone\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"phone\"/></td>"+
							"<td class=\"t-title\">QQ：</td>"+
							"<td><input type=\"text\" name=\"fqq\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"qq\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
							"<td class=\"t-title\">邮箱：</td>"+
							"<td><input type=\"text\" name=\"femail\" value=\"\" class=\"t-text easyui-validatebox\" validType=\"email\"/></td>"+
							"<td class=\"t-title\">公司名称：</td>"+
							"<td><input type=\"text\" name=\"fcompanyname\" class=\"t-text\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
							"<td class=\"t-title\">城市：</td>"+
							"<td><input type=\"text\" name=\"fcityname\" class=\"t-text\"/></td>"+
							"<td class=\"t-title\">被投诉人地址：</td>"+
							"<td><input type=\"text\" name=\"fbycompliantadd\" class=\"t-text\"/></td>"+
			    		"</tr>"+
			    		"<tr>"+
							"<td class=\"t-title\">是否为中介：</td>"+
							"<td colspan=\"3\">" +
							"<select id=\"fisagent"+numOutEdit+"\" name=\"fisagent\" onchange=\"zhongJieSelectFun("+numOutEdit+")\">"+
							"<option value='1'>是</option>"+
							"<option value='0'>否</option>"+
							"</select></td>"+
			    		"</tr>"+
			    		"<tr style='display: none' id='shiFouZhongJie"+numOutEdit+"'>"+
							"<td class=\"t-title\">备注：</td><td colspan=\"3\">" +
							"<textarea name=\"fnotagentRemark\" rows=\"3\" style=\"width:580;\"></textarea></td>"+
						"</tr>"+
					    "</table>"+
						"</div>",
				closable:true
		    });
		}

		//
		var numChargeEdit=${requestScope.chargeSize}==null ?0:${requestScope.chargeSize};
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

		var numPerformanceEdit=${requestScope.performanceSize}==null ?0:${requestScope.performanceSize};
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
		

		

		//multipleModel.test();


	</script>
  	</head>
	<body>
	<input type="hidden" value="${complainInfo.isCharge}" id="fisCharge"/>
		<div class="tableForm">
   		<form method="post" action="<%=basePath%>caseinfo/caseinfo!updateCaseInfo" id="editForm">
	  		<input type="hidden" name="complainInfo.fid" id="complainFId" value="${complainInfo.fid }">
	    	<input type="hidden" id="winURL" value="pages/compliance/caseinfo/selectAdmin.jsp"/>
	  		<div class="title">案件详细信息编辑</div>
			<table>				
				<tr>
					<td class="t-title" width="80">投诉渠道：</td>
					<td><select id="fcompchannelid" name="complainInfo.fcompchannelid" onchange="qiuDaoSelectFun();"></select></td>
					<td class="t-title">投诉时间：</td>
					<td><input type="text" name="complainInfo.fcomptime" id="fcomptime" readonly   
						onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
						class="t-text Wdate" value="<fmt:formatDate value="${complainInfo.fcomptime}" pattern="yyyy-MM-dd"/>"/></td>
                    
				</tr>
				
				<tr>
					<td id="SRCodeTh1" class="t-title" width="80">SR编码：</td>
					<td id="SRCodeTh2">
						<input type="text"  value="${complainInfo.SRCode}" name="complainInfo.SRCode" class="t-text easyui-validatebox" validType="number"/>
					</td>
					<td class="t-title" nowrap="nowrap">是否是内部员工：</td>
					<td><select id="fisinner" name="complainInfo.fisinner">
						<option value='1' <c:if test="${complainInfo.fisinner==1 }">selected</c:if>>是</option>
						<option value='0' <c:if test="${complainInfo.fisinner==0 }">selected</c:if>>否</option>
					</select></td>
				</tr>
			    
			    <tr style="display: none" id="qiuDaoGeRenDiv">
					<td class="t-title" width="80">个人邮箱备注：</td>
					<td colspan="3"><textarea style="width:400;" 
						name="complainInfo.fselfEmailRemark" rows="2">${complainInfo.fselfEmailRemark }</textarea>
					</td>
				</tr>   
				
				<tr>
<%--				<td class="t-title">书面证据：</td>--%>
<%--					<td><select id="fisevidence" name="complainInfo.fisevidence">--%>
<%--						<option value='1' <c:if test="${complainInfo.fisevidence==1 }">selected</c:if>>有</option>--%>
<%--						<option value='0' <c:if test="${complainInfo.fisevidence==0 }">selected</c:if>>无</option>--%>
<%--					</select></td>--%>
<%--					<td class="t-title">证据类型：</td>--%>
<%--					<td><select name="complainInfo.fevidencetypeid" id="fevidencetypeid" --%>
<%--						onchange="addOtherCommon('fevidencetypeid','5','zhengMingOtherDiv');"></select></td>--%>
                   <td class="t-title" width="80">客服姓名：</td>
	    			<td colspan="3"><input class="t-text" type="text" name="complainInfo.fservicename" style="width:183px;"
	    				id="fservicename" value="${complainInfo.fservicename}"></td>
				</tr>
				
				<%--李如意 --%>
				<tr>
					<td class="t-title" width="80">是否收费：</td>
					<td> 
						<select id="isCharge" name="complainInfo.isCharge" onchange="changeCharge(this.options[this.options.selectedIndex].value);">
							<option value='1'>是</option>
							<option value='0'>否</option>
						</select>
					</td>
					 <td class="t-title">涉及业务端：</td>
					<td><select name="complainInfo.fbusportid" id="fbusportid" validType="njection" class="t-text easyui-validatebox"></select></td>
				</tr>
				
				<tr style="display: none" id="zhengMingOtherDiv">
					<td class="t-title" width="80">证据类型备注：</td>
					<td colspan="3"><textarea rows="2" name="complainInfo.fisevidenceRemarks" style="width:580;"></textarea></td>
				</tr>
				
			
				
				<tr><td class="t-title" width="80">案件初步分类：</td>
					<td colspan="3" style="padding:0;">
	      			<table style="margin:0px;">
						<tr id="check"></tr>
					</table></td>
				</tr>
		     	<tr><td class="t-title" width="80">案件详细分类：</td>
    		      	<td colspan="3" style="padding:0;">
	      			<table style="margin:0px;">
						<tr><td><select id="fdetailCaseType1" name="fdetailCaseType1"><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType2" name="fdetailCaseType2"><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType3" name="fdetailCaseType3"><option value='0'>--请选择--</option></select></td>
						</tr>
						<tr><td><select id="fdetailCaseType4" name="fdetailCaseType4"><option value='0'>--请选择--</option></select></td>
	    					<td colspan="2">
	    						<select id="fdetailCaseType5" name="fdetailCaseType5"><option value='0'>--请选择--</option></select>
    						</td>
						</tr>
					</table>
    		      	</td>
   		      	</tr>
   		      	
				<tr style="display: none;"  id="xiangXiFengLeiOtherDiv">
					<td class="t-title" width="80">案件初步分类备注：</td>
					<td colspan="3"><textarea name="complainInfo.finicasetypeRemark" 
						rows="2" style="width:830">${complainInfo.finicasetypeRemark }</textarea></td>
				</tr>
				
				<tr>
					<td class="t-title" width="80">案件违规分类:</td>
					<td colspan="3" style="padding: 0">
						<table style="margin: 0" id="initable">
							<tr>
								<s:set value="0" var="tmpSelectOthers"></s:set>
								<s:iterator value="newCaseType" var="n" status="s">
									<td> 
									<s:if test="ischecked==1">
										<s:if test="id==27">
											<s:set value="1" var="tmpSelectOthers"></s:set>
											<input title="<s:property value="name"/>" checked="checked" onclick='fun_otherCheck("finicasetypeOther1","xiangXiFengLeiOtherDiv1")' 
											type="checkbox" name="newfinicasetype" id="finicasetypeOther1" 
											value="<s:property value="id"/>"><s:property value="name"/>	
											
										</s:if>
										<s:else>
											<input title="<s:property value="name"/>" checked="checked" onclick='multipleModel.produce_newCaseTypeDetail(this)' 
											type="checkbox" name="newfinicasetype" id="newfinicasetype<s:property value="id"/>" 
											value="<s:property value="id"/>"><s:property value="name"/>
										</s:else>
											
											
											
									</s:if>
									<s:else>
									<s:if test="id==27">
										
											<input title="<s:property value="name"/>" onclick='fun_otherCheck("finicasetypeOther1","xiangXiFengLeiOtherDiv1")' 
											type="checkbox" name="newfinicasetype" id="finicasetypeOther1" 
											value="<s:property value="id"/>"><s:property value="name"/>
									</s:if>
									<s:else>
										<input title="<s:property value="name"/>" onclick='multipleModel.produce_newCaseTypeDetail(this)' 
											type="checkbox" name="newfinicasetype" id="newfinicasetype<s:property value="id"/>" 
											value="<s:property value="id"/>"><s:property value="name"/>
									</s:else>
										
									</s:else>
									
									<s:if test="id!=27">
									
									
									<div id="popdiv_<s:property value="id"/>" class="pop-div">
										  <ul>
										  	<s:iterator value="#n.detailCaseType" var="i">
										  	
										  		<s:if test="#i.ischecked==1">
										  			<li style="width: 370px;"> <input type="checkbox" checked="checked" title="<s:property value="#i.name"/>" name="detailCaseTypeIni<s:property value="#n.id"/>" value="<s:property value="#i.id"/>"/>
												  		<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
												  		
												  		<%--
												  		
												  		<s:if test="%{#i.name.length()>8}">    
												  			 <a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name.substring(0,8)+'...'" /></a>      
												  		</s:if>
												  		<s:else>
												  			<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
												  		</s:else>
										  		 		--%>
										  		 		
										  			</li>
										  		</s:if>
										  		
										  		<s:else>
										  		
										  			<li style="width:370px;"> <input type="checkbox" title="<s:property value="#i.name"/>" name="detailCaseTypeIni<s:property value="#n.id"/>" value="<s:property value="#i.id"/>"/>
												  		<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
												  		<%--
												  		<s:if test="%{#i.name.length()>8}">    
												  			 <a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name.substring(0,8)+'...'" /></a>      
												  		</s:if>
												  		<s:else>
												  			<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
												  		</s:else>
												  		 --%>
												  		 
												  	</li>
										  		</s:else>
										  		
										  		
										  		
										  	</s:iterator>
										  	
										  </ul>
										  
										 <div style="clear: left;" align="center">
										  	<input class="pop-but" type="button"  value="确定" onclick="multipleModel.makesureCaseType(<s:property value="id"/>)"/>
										  	<input class="pop-but" type="button" value="取消" onclick="multipleModel.cancleCaseType(<s:property value="id"/>)"/>
								  		 </div>
										  
									</div>
								</s:if>	
									
									</td>
								</s:iterator>
							</tr>
						</table>
					</td>
				</tr>
				
				
				<tr>
					<td class="t-title" width="80"></td>
					<td colspan="3">
						<div>
							<ul style="list-style: none;margin: 0;padding: 0;" id="newfinicasetypeUL">
								<s:iterator value="newCaseType" var="n" status="s">
									<s:if test="ischecked==1">
										<s:if test="id!=27">
										<li id="newfinicasetypeli<s:property value="id"/>" style="float: left;width: 400px;height: 120px;margin: 0 20px 0 0;padding: 0;">
											<span style="height: 120px;vertical-align: middle;display: inline-block;width: 80px;overflow: hidden;padding: 0;margin: 0;text-align: right;"><s:property value="name"/></span>
												<select multiple="multiple" size="6" style="text-align: left;width: 250px;height: 110px;padding: 0;margin: 0;" id="fnewdetailtypeid<s:property value="id"/>" name="fnewdetailtypeid<s:property value="id"/>"> 
													<s:iterator value="#n.detailCaseType" var="i">
														<s:if test="#i.ischecked==1">
																<option value="<s:property value="#i.id"/>"><s:property value="#i.name"/></option>
														</s:if>
													</s:iterator>
												</select>
												
												<span style="height: 120px;vertical-align: middle;display: inline-block;width: 50px;text-align: left;overflow: hidden;padding: 0;margin: 0;">
													<img alt="选择" src="<%=path%>/images/pic7.gif" onclick="multipleModel.openWinCaseType(<s:property value="id"/>)">
												</span>
											
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
						<td class="t-title" width="80">新分类备注：</td>
						<td colspan="3"><textarea name="complainInfo.fnewtypemark" 
							rows="2" style="width:830">${complainInfo.fnewtypemark }</textarea></td>
					</tr>	
				</s:if>
				<s:else>
					<tr style="display: none;"  id="xiangXiFengLeiOtherDiv1">
						<td class="t-title" width="80">新分类备注：</td>
						<td colspan="3"><textarea name="complainInfo.fnewtypemark" 
							rows="2" style="width:830">${complainInfo.fnewtypemark }</textarea></td>
					</tr>
				</s:else>
					 
					    
				<tr><td class="t-title" width="80">案件具体描述：</td>
					<td colspan="3"><textarea rows="10" name="complainInfo.fdetaildescrip" 
						style="width:830px;">${complainInfo.fdetaildescrip }</textarea></td>
		    	</tr>
		    	
		    	<%--
		    	<tr>
		    		<td class="t-title">销售人员：</td>
		    		<td><input type="text" name="complainInfo.salePerson" class="t-text" value="${complainInfo.salePerson}"/></td>
		    		<td class="t-title">上级领导：</td>
		    		<td><input type="text" name="complainInfo.superLeader" class="t-text" value="${complainInfo.superLeader}"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">间接领导：</td>
		    		<td><input type="text" name="complainInfo.indirectLeader" class="t-text" value="${complainInfo.indirectLeader}"/></td>
		    		<td class="t-title">营业部：</td>
		    		<td><input type="text" name="complainInfo.saleDepart" class="t-text" value="${complainInfo.saleDepart}"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">客服姓名：</td>
		    		<td><input type="text" name="complainInfo.serviceName" class="t-text" value="${complainInfo.serviceName}"/></td>
		    	</tr>
		    	 --%>
		    	 
		    	<tr><td class="t-title" width="80">案件负责人</td>
					<td>
					<input type="text" name="complainInfo.fresponsibleName" value="${complainInfo.fresponsibleName }" 
						class="t-text easyui-validatebox" required
<%--						使用自定义的函数，控制用户的权限信息	--%>
							<c:if test="${elself:validIsAdmin(personrole,87)==false}">readonly</c:if>
						/>
						<span class="required">*</span>
					</td>
		    		<td class="t-title">负责人邮箱 ：</td>
					<td>
						<input type="text" name="complainInfo.fresponsibleEmail" id="fresponsibleEmail" validType="email"
							value="${complainInfo.fresponsibleEmail }" class="t-text easyui-validatebox"
							<%--使用自定义的函数，控制用户的权限信息	--%>
							<c:if test="${elself:validIsAdmin(personrole,87)==false}">readonly</c:if>
							/><span class="required">*</span>
					</td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title" width="80">潜在风险等级评估：</td>
			  		<td colspan="3">
				  		<select id="complainInfo.riskLevel" name="complainInfo.riskLevel">
				  			<option value="1" <c:if test="${complainInfo.riskLevel==1 }">selected</c:if>>高</option>
				  			<option value="2" <c:if test="${complainInfo.riskLevel==2 }">selected</c:if>>中</option>
				  			<option value="3" <c:if test="${complainInfo.riskLevel==3 }">selected</c:if>>低</option>
				  		</select>
			  		</td>
  					
		    	</tr>
		    </table>
		  	
		  	  
		    <%--李如意添加的 --%>
		    <div id="performance" class="easyui-tabs tabs-header-tab" style="width:1000px;height:120px;" tools="#performance-tab-tools">
		    	<c:choose>
		    		<c:when test="${!empty performances}">
		    			<c:forEach var="p" items="${performances}" varStatus="status">
		    				<%@include file="/pages/compliance/caseinfo/include/caseIncludePerformance.jsp" %>
		    			</c:forEach>
		    		</c:when>
		    		<c:otherwise>
		    			<%@include file="/pages/compliance/caseinfo/include/caseEmptyPerformance.jsp" %>
		    		</c:otherwise>
		    	</c:choose>
		    </div>
		    <div id="performance-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addPerformanceEdit();"></a>
			</div>
		  	
		  	<%--李如意  收费 --%>
		  	<div id="charge" class="easyui-tabs tabs-header-tab" style="width:1000px;height:180px;"  tools="#charge-tab-tools">
		 		<c:choose>
				     <c:when test="${!empty chargeInfos}">
			     		  <c:forEach var="charge" items="${chargeInfos}" varStatus="status">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeCharge.jsp" %>
			      		 </c:forEach>
			    	</c:when>
			    	<c:otherwise>
			         	<%@include file="/pages/compliance/caseinfo/include/caseEmptyCharge.jsp" %>
			    	</c:otherwise>
	    		</c:choose>
		  	</div>
		  	<div id="charge-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addChargeEdit()"></a>
			</div>
		  	
		  	
		  	<!-- 被投诉部门 -->
		  	<div id="dept" class="easyui-tabs tabs-header-tab" style="width:1000px;height:150px;" tools="#dept-tab-tools">
		 		<c:choose>
				     <c:when test="${!empty deptinfo}">
			     		  <c:forEach var="dept" items="${deptinfo}" varStatus="status">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeDept.jsp" %>
			      		 </c:forEach>
			    	</c:when>
			    	<c:otherwise>
			         	<%@include file="/pages/compliance/caseinfo/include/caseEmptyDept.jsp" %>
			    	</c:otherwise>
	    		</c:choose>
		  	</div>
		  	<div id="dept-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fun_addDept()"></a>
			</div>
<%--		 	<div id="deptAll" style="width: 80%"><div align="center"><strong style="color: blue">被投诉部门信息</strong></div></div>--%>
<%--		 	<div id="dept" class="easyui-accordion" style="width:1000px;height:160px;">  --%>
<%--	 	 	</div>--%>

			<div id="aa" class="easyui-tabs" style="width:1000px;height:450px;" tools="#inner-tab-tools">
		    	<c:choose>
		    	    <c:when test="${!empty InnPersons}">
		    	    	<c:forEach var="person" items="${InnPersons}" varStatus="status" >
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeInner.jsp" %>
						</c:forEach>
		    	    </c:when>
		    	    <c:otherwise>
		    	   		 <%@include file="/pages/compliance/caseinfo/include/caseEmptyInner.jsp" %>
		    	    </c:otherwise>
		    	 </c:choose>
			</div>
			<div id="inner-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
					onclick="${!empty InnPersons}?fun_addInnerOutEdit():fun_addInnerOut()"></a>
			</div>
			
			<div id="aa1" class="easyui-tabs" style="width:1000px;height:400px;" tools="#out-tab-tools">
	    		<c:choose>
		    	    <c:when test="${!empty outPersons}">
		    	    	<c:forEach var="outPerson" items="${outPersons}" varStatus="status">
							<%@include file="/pages/compliance/caseinfo/include/caseIncludeOutter.jsp" %>
						</c:forEach>
		    	    </c:when>
		    	    <c:otherwise>
		    	   		 <%@include file="/pages/compliance/caseinfo/include/caseEmptyOutter.jsp" %>
		    	    </c:otherwise>
		    	</c:choose>
			</div>
			
			<div id="out-tab-tools">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
					onclick="${!empty outPersons}?fun_addOutEdit():fun_addOut()"></a>
			</div>
<%--	     <div id="personAll" style="width: 80%"><div align="center"><strong style="color: blue">被投诉人信息</strong></div></div> --%>
<%--			     <div id="aa" class="easyui-accordion" style="width:1000px;height:200px;">  --%>
<%--			    	</div>--%>
<%--			    	<div style="height:25px;"></div>--%>
<%--			    	<div id="aa1" class="easyui-accordion" style="width:1000px;height:200px;">--%>
<%--			    	</div>--%>
	     	<div class="t-but" style="margin-top:15px;">
	     	<a href="javascript:void(0);" class="but-change" onclick="multipleModel.dosubmitCompliance1('editForm');">保存</a>
	     		<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
		    </div>
		</form>
		</div>
     	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true" 
<%--     	maximized=true--%>
	  		iconCls="icon-save" style="width:1000px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
