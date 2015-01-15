<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>申请结案信息</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/multiselect.css">
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/multiselect.js"></script>
	<script type="text/javascript">
		function outofLineCasecade(){
			var outofLineIdValue = $("#outofLineId").val();
			$(".casecadeWithOutofLine,.casecadeWithOutofLine1").hide();$("#outofLineLevelId").removeClass();
			if(outofLineIdValue == 1){
				$(".casecadeWithOutofLine").hide();$(".casecadeWithOutofLine1").show();
				$("#outofLineLevelId").validatebox({required: true,missingMessage: '必选'});
			}else if(outofLineIdValue==2 || outofLineIdValue==3){
				$(".casecadeWithOutofLine").show();$(".casecadeWithOutofLine1").hide();
			}
		}
		function seClasssifyCasecade(){
			if($("#seClasssifyId").val()==5)	$(".casecadeWithSeClasssify").show();
			else $(".casecadeWithSeClasssify").hide();
		}
		$(document).ready(function(){
			if($("#returnBack").val()=='true'){
				$("#returnBtn").show();$("#nextstep0").remove();
				$('#editForm :input*:not(#returnBtn)').attr("disabled","disabled");
			}

			//使多选框可用
			var checkeds = $('input[name="newfinicasetype"]:checked');
			for ( var i = 0, j = checkeds.length; i < j; i++) {
				var o = checkeds[i];
				if(o.value!=27){//不是其他新
					if($("#fnewdetailtypeid" + o.value).length>0){
						 $("#fnewdetailtypeid" + o.value).removeAttr("disabled");		
					}
				}
			}
			
			outofLineCasecade();
			$("#outofLineId").change(function(){//确认是否违规时下拉列表改变时
				outofLineCasecade();
			})
			seClasssifyCasecade();
			$("#seClasssifyId").change(function(){
				seClasssifyCasecade();
			})
			//校验
			$("#applyPerson").validatebox({required: true,validType:'njelength[1,50]',missingMessage: '必填'});
			$("#applyTime").addClass("easyui-my97 Wdate").my97({dateFmt:"yyyy-MM-dd HH:mm:ss"})
			.validatebox({required:true,missingMessage: '必填'});
			if($("#sepcialEnd").val()=='true'){
				$("#seClasssifyId").validatebox({required: true,missingMessage: '必选'});
				$("#specialEndCase").val(1);
			}else{
				$("#outofLineId").validatebox({required: true,missingMessage: '必选'});
				$("#complainants").validatebox({required: true,validType:'njelength[1,500]',missingMessage: '必填'});
				$("#directChargeMan,#indirectChargeMan").validatebox({validType:'njelength[0,50]'});
			}
			

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
						if($("#returnBack").val()=='true'){
							$td = $("<td><input disabled='disabled' type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' checked >" + json[i].value + "</td>");		

						}else{
							$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' checked >" + json[i].value + "</td>");
						}
						
						
						//显示初步分类的备注
<%--						$("#xiangXiFengLeiOtherDiv").css({display:"''"});
					  改成下面的方式，解决界面样式混乱的问题
--%>
						//$("#xiangXiFengLeiOtherDiv").show();
					}else{
						if($("#returnBack").val()=='true'){
							$td = $("<td><input type='checkbox' disabled='disabled' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "'>" + json[i].value + "</td>");
						}else{
							$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "'>" + json[i].value + "</td>");
						}
						
					}
					$td.appendTo($tr);//加载初步分类的信息
				}else{//初步分类中的其他的分类
					if(valid){//其他分类中有选中的项
						if($("#returnBack").val()=='true'){
							$td = $("<td><input type='checkbox' disabled='disabled' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' checked onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");	
						}else{
							$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' checked onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
						}
						
						//给详细分类赋值
						$td.appendTo($tr);//这段代码很关键，如果不处理好，下面的方法执行的时候会报错
						fun_fdetailCaseType('finicasetype'+(i+1),json[i].key,'fdetailCaseType'+(i+1),fdetailCaseSelectedValue);//加载案件的详细分类，并设置选中项	
					}else{//其他分类中没有选中的项
						if($("#returnBack").val()=='true'){
							$td = $("<td><input type='checkbox' disabled='disabled' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
						}else{
							$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' onclick=fun_fdetailCaseType('finicasetype"+(i+1)+"','"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
						}
						
						
						$td.appendTo($tr);
					}
				}
			}
			//input_autocoplete();

			

			
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
				loadSelectInfo(fdetailCaseType,"<%=basePath%>/caseinfo/caseinfo!findDetailCaseType?finicasetype="+finicasetype,selectedValue);
			}else{
				$("#"+fdetailCaseType).empty().append( $("<option value='0'>--请选择--</option>") );
			}
		}

			/**
			 * 表单提交
			 * @param id 必选项		提交表单时点击对象的id属性
			 * @param title 可选项	需要刷新tab标签页的title属性
			 * @return
			 */
			function submitTab1(id,title){
				$('#editForm').form('submit',{
					onSubmit: function(){
						var result = $(this).form('validate');
						if(result == true){
							if(id){
								$("#"+id).attr("disabled","disabled");
								$("#"+id).removeAttr('onclick');
							}
							var checkeds = $('input[name="newfinicasetype"]:checked');
							for ( var i = 0, j = checkeds.length; i < j; i++) {
								var o = checkeds[i];
								if(o.value!=27){//不是其他新
									if($("#fnewdetailtypeid" + o.value).length>0){
										var op = $("#fnewdetailtypeid" + o.value)[0].options;
										for ( var m = 0; m < op.length; m++) {
											op[m].selected = true;
										}		
									}
								}
							} 
							$("#editForm").ajaxSubmit({
								dataType:"text",
					 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
									$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
											toJson(json).success == "true"?"info":"error",function(){
										if(title!=undefined && title!=null && title!=''){
											var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
											if(refrTab){
											var url = $(refrTab.panel('options').content).attr('src');
												getTopWin().$('#tabs').tabs('update',{
													tab:refrTab,options:{content:createFrame(url)}
														})
											}
											
										}
										closeTab();
									});
					 			}
					 		});	
						}else{
							if(id) $("#"+id).removeAttr("disabled");
						}
						return false;
					}
				})
			}
			//alert();

			multipleModel.imgSrc='<%=basePath%>/images/pic7.gif';
		
		
		
	</script>
	</head>
	<body>
		<div class="tableForm">
		<form action="compliance/applysettlement!<s:if test='applysettlement != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);" style="display: none;"
					class="but-cancel">返回</a>
			</div>
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<input type="hidden" id="id" name="applysettlement.id" value='<s:property value="applysettlement.id"/>' />
			<input type="hidden" id="investigationId" name="applysettlement.investigationId" value='<s:property value="investigationId"/>' />
			<input type="hidden" id="feedBackResultId" name="applysettlement.feedBackResultId" value='<s:property value="feedBackResultId"/>' />
			<input type="hidden" id="zcxzlsId" name="applysettlement.zcxzlsId" value='<s:property value="zcxzlsId"/>' />
			<input type="hidden" id="fieldSurveyId" name="applysettlement.fieldSurveyId" value='<s:property value="fieldSurveyId"/>' />
			<input type="hidden" id="sepcialEnd" name="sepcialEnd" value='<s:property value="sepcialEnd"/>' />
			<input type="hidden" id="specialEndCase" name="applysettlement.specialEndCase" value='0' />
			<div class="title">申请结案信息</div>
			<table>
				<tr><td class="t-title" width="130">申请结案人：</td>
					<td><input class="t-text" type="text" id="applyPerson" name="applysettlement.applyPerson" 
						value='<s:property value="applysettlement.applyPerson"/>'/>
					</td>
				<td class="t-title" width="100">申请时间：</td>
				<td>
					<input class="t-text" type="text" id="applyTime" name="applysettlement.applyTime" 
					value="<s:date name='applysettlement.applyTime' format='yyyy-MM-dd HH:mm:ss'/>" >
				</td>
				</tr>	
					
				<s:if test="sepcialEnd=='true'">
					<tr><td class="t-title" width="130">特殊结案归类：</td>
				  	<td colspan="3"><select name="applysettlement.seClasssifyId" id="seClasssifyId">
				  			<option value="">--请选择--</option>
				  			<s:iterator value="seClasssifys">
				  				<option value="<s:property value='key'/>" 
				  					<s:if test="applysettlement.seClasssifyId==key">selected</s:if> ><s:property value="value" /></option>
				  			</s:iterator>
				  		</select></td></tr>
			  		<tr class="casecadeWithSeClasssify" style="display: none;"><td class="t-title" width="130">其他备注：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="seClasssifyOther"
				  		name="applysettlement.seClasssifyOther"><s:property value="applysettlement.seClasssifyOther"/></textarea>
			  		</td></tr>
			  		<tr><td class="t-title" width="130">特殊结案理由：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="seReason"
				  		name="applysettlement.seReason"><s:property value="applysettlement.seReason"/></textarea>
			  		</td></tr>
				</s:if>
				<s:else>
				  	<tr><td class="t-title" width="130">案件处理方案：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="arocessingScheme"
				  		name="applysettlement.arocessingScheme"><s:property value="applysettlement.arocessingScheme"/></textarea>
			  		</td></tr>
			  		
			  		<tr><td class="t-title" width="130">确认是否违规：</td>
				  	<td><select name="applysettlement.outofLineId" id="outofLineId">
				  			<option value="">--请选择--</option>
				  			<s:iterator value="outofLineIds">
				  				<option value="<s:property value='key'/>" 
				  					<s:if test="applysettlement.outofLineId==key">selected</s:if> ><s:property value="value" /></option>
				  			</s:iterator>
				  		</select></td>
			  		<td class="t-title casecadeWithOutofLine1" style="display:none;">违规级别：</td>
				  	<td class="casecadeWithOutofLine1" style="display:none;">
				  		<select name="applysettlement.outofLineLevelId" id="outofLineLevelId">
				  			<option value="">--请选择--</option>
				  			<s:iterator value="outofLineLevelIds">
				  				<option value="<s:property value='key'/>" 
				  					<s:if test="applysettlement.outofLineLevelId==key">selected</s:if> ><s:property value="value"/></option>
				  			</s:iterator>
				  		</select></td></tr>
				  	
				  	<tr class="casecadeWithOutofLine" style="display:none;"><td class="t-title" width="130">理由：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="reason"
				  		name="applysettlement.reason"><s:property value="applysettlement.reason"/></textarea></td></tr>
				  		
				  		
				<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title" width="130">案件最终归类：</td>
					<td colspan="3" style="padding:0;">
	      			<table style="margin:0px;">
						<tr id="check"></tr>
					</table></td>
				</tr>
		     	<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title" width="130">案件最终详细归类：</td>
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
				
				
				<tr class="casecadeWithOutofLine1" style="display:none;">
					<td class="t-title" width="130">案件违规分类:</td>
					<td colspan="3" style="padding: 0;padding: 0;">
						<table style="margin: 0;padding: 0;" id="initable">
							<tr>
								<s:set value="0" var="tmpSelectOthers"></s:set>
								<s:iterator value="newCaseType" var="n" status="s">
									<td> 
									<s:if test="ischecked==1">
										<s:if test="id==27">
											<s:set value="1" var="tmpSelectOthers"></s:set>
											<input title="<s:property value="name"/>" checked="checked"  
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
										
											<input title="<s:property value="name"/>" 
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
										  			<li style="width:370px;"> <input type="checkbox" checked="checked" title="<s:property value="#i.name"/>" name="detailCaseTypeIni<s:property value="#n.id"/>" value="<s:property value="#i.id"/>"/>
												  		<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
										  			</li>	
										  		</s:if>
										  		
										  		<s:else>
										  			
										  			<li style="width:370px;"> <input type="checkbox" title="<s:property value="#i.name"/>" name="detailCaseTypeIni<s:property value="#n.id"/>" value="<s:property value="#i.id"/>"/>
												  		<a href="javascript:void(0)" title="<s:property value="#i.name" />"><s:property value="#i.name" /></a>
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
				
				<tr class="casecadeWithOutofLine1" style="display:none;">
					<td class="t-title" width="130"></td>
					<td colspan="3">
						<div>
							<ul style="list-style: none;margin: 0;padding: 0;" id="newfinicasetypeUL">
								<s:iterator value="newCaseType" var="n" status="s">
									<s:if test="ischecked==1">
										<s:if test="id!=27">
										<li id="newfinicasetypeli<s:property value="id"/>" style="float: left;width: 400px;height: 120px;margin: 0 20px 20px 0;padding: 0;">
										<span style="height: 60px;vertical-align: bottom;display: inline-block;width: 80px;padding: 0;margin: 0;text-align: right;border:0;"><s:property value="name"/></span>
								
			 

<select style="text-align: left;width: 250px;height: 120px;" id="fnewdetailtypeid<s:property value="id"/>" name="fnewdetailtypeid<s:property value="id"/>" size="10" multiple="multiple"> 
	<s:iterator value="#n.detailCaseType" var="i">
		<s:if test="#i.ischecked==1">
			<option value="<s:property value="#i.id"/>"><s:property value="#i.name"/></option>
		</s:if>
	</s:iterator>
</select>
						
							
							

										
					<s:if test="returnBack!='true'">
							<span style="height: 120px;vertical-align: middle;display: inline-block;width: 50px;text-align: left;overflow: hidden;padding: 0;margin: 0;">
								<img alt="选择" src="<%=path%>/images/pic7.gif" onclick="multipleModel.openWinCaseType(<s:property value="id"/>)">
							</span>	
					</s:if>
					
					
										
										</li>
										
										
										</s:if>
									</s:if>
								
								</s:iterator>
								
							</ul>
							
							
						</div>
							
							
						
						
					</td>
					
				</tr>
				
					<%--
				  	<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title">案件最终归类：</td>
				  		<td colspan="3"><table>
				  		
					  		<s:iterator value="cfClassifyIds" status="st">
					  		<s:if test="#st.count==1 || #st.count==6 ">
					  			<tr>		
					  		</s:if>
					  		
					  			<td>
					  			<input type="checkbox" name="initypes" value="<s:property value='key'/>"
					  				<s:iterator value="relInitypes" >
					  					<s:if test="INICASETYPEID == key">checked=checked</s:if>
					  				</s:iterator>
					  			/>
					  			<s:property value="value"/>
					  			</td>
					  			<s:if test=" #st.count==5 || #st.count==10 ">
						  			</tr>		
						  		</s:if>
					  			
				  			</s:iterator>
			  			
			  			</table>
			  		</td></tr>
			  		 --%>
			  		
				  	<tr><td class="t-title" width="130">被投诉人：</td>
				  	<td colspan="3"><textarea rows="2" style="width:580;" id="complainants"
				  		name="applysettlement.complainants"><s:property value="applysettlement.complainants"/></textarea></td></tr>
				  		
				  	<tr><td class="t-title" width="130">直接领导姓名：</td>
				  	<td><input class="t-text" type="text" id="directChargeMan" name="applysettlement.directChargeMan" 
						value='<s:property value="applysettlement.directChargeMan"/>' /></td>
					<td class="t-title">间接领导姓名：</td>
				  	<td><input class="t-text" type="text" id="indirectChargeMan" name="applysettlement.indirectChargeMan" 
						value='<s:property value="applysettlement.indirectChargeMan"/>' /></td>
			  		</tr>
				  		
				  	<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title" width="130">直接领导处罚结果：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="dcmpResult"
				  		name="applysettlement.dcmpResult"><s:property value="applysettlement.dcmpResult"/></textarea>
			  		</td></tr>
						
				  	<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title" width="130">间接领导处罚结果：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="idcmpResult"
				  		name="applysettlement.idcmpResult"><s:property value="applysettlement.idcmpResult"/></textarea>
			  		</td></tr>
				  	
				  	<tr><td class="t-title" width="130">对违规人员的处理方案：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="complainantSolution"
				  		name="applysettlement.complainantSolution"><s:property value="applysettlement.complainantSolution"/></textarea>
			  		</td></tr>
				  		
				  	<tr class="casecadeWithOutofLine1" style="display:none;"><td class="t-title" width="130">对被投诉人处罚结果：</td>
				  	<td colspan="3"><textarea rows="4" style="width:580;" id="cspResult"
				  		name="applysettlement.cspResult"><s:property value="applysettlement.cspResult"/></textarea>
			  		</td></tr>
				</s:else>
			</table>
	  		<div class="t-but" style="display:'" id="nextstep0">
	  		<a id="editBtn" href="javascript:void(0)" class="but-change" 
			    	onclick="submitTab1('editBtn','合规初步调查')"><s:if test='applysettlement != null'>修改</s:if><s:else>新增</s:else></a>	
		    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
			    
	  		</div>
		</form>
		</div>
	</body>
</html>
