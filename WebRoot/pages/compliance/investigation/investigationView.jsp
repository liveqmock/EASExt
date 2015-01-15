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
		<title>合规调查详细信息</title>
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	    <style type="text/css"> table tr td{font-size: 12px;} </style>
	    <style type="text/css">
	      div.item { width:100px;background-color: transparent; text-align:center; padding-top:0px;}
	    </style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/tooltip/jquery.tooltip.css" type="text/css" />
	    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.tooltip.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tooltip/jquery.tooltip.js"></script>
	    <script type="text/javascript">
	      $j = jQuery.noConflict();
	      $j(document).ready(function(){

	    	//$j(".datagrid-header-inner").css({display:block,width:100%});
	    	$j(".datagrid-header-inner").css({width:"100%"});
	    	$j(".datagrid-header-inner table").css({width:"100%"});
	    	$j(".datagrid-body table").css({width:"100%"});
	    	$j(".datagrid-header table").css('width','100%');
		      
	    	  $j("div.item").tooltip();
	    	 
	    	 	var showOldMark=false;
				$j("input[name='initypes']").each(function(){
					if($j(this).val()=='6'){
						showOldMark=true;		
					}
				});
				if(showOldMark){
					$j("#basisMark").css("display","");
				}

				var showNewMark=false;
				$j("input[name='iniDetailTypes']").each(function(){
					if($j(this).val()=='14'){
						showNewMark=true;		
					}
				});
				if(showNewMark){
					$j("#newMark").css("display","");
				}
	    	 
	    	  var isCustomerValue = $j("#customer").val();
				if(isCustomerValue==1){
					$j('.cusRelation').hide();
					$j("#fcusrelation1").hide();
					$j("#fcusrelation").hide();
					$j("#fidcarttitle1").show();
					$j("#fidcard").show();
				}else{
					$j('.cusRelation').show();
					$j("#fcusrelation1").show();
					$j("#fcusrelation").show();
					$j("#fidcarttitle1").hide();
					$j("#fidcard").hide();
					$j("#iscustome").show();
				}
				
				
				//客户状态
					var isCustomer = $j('#fcusstatusids').find("option:selected").text();
						$j('.otherInfo0,.otherInfo1,.otherInfo2,.otherInfo3').hide();
						
						var cusStatus = $j('#fcusstatusids');
						//如果是普惠金融
						if(isCustomer == '完成放款'){//完成放款，则将合同相关的信息都显示出来
							$j('.otherInfo0').hide();	
				 			$j('.otherInfo1').show();
				 			$j('.otherInfo2').show();
				 			$j('.otherInfo3').show();
				 			$j('#lcfa').hide();//隐藏理财方案
				 			$j("#fviolateCountHistory").show();//历史违约次数
				 			

				 			var isfisviolateValue=$j("#fisviolate").val();
				 			if (isfisviolateValue==0) {
				 				$j("#fviolatedaystr").hide();//违约天数	
				 			}else{
				 				$j("#fviolatedaystr").show();//违约天数	
					 		}

							var fviolate=$j('#fviolate').val();
				 			
				 			if(fviolate){
				 				$j("#lxwysj").show();//违约时间
				 			}else{
				 				$j("#lxwysj").hide();//违约时间
				 			}
						}else if(isCustomer== '完成出借'){//
							$j('.otherInfo2').show();
							$j("#fservicetype").show();
							$j("#fservicetype2").show();
							$j("#fstartorendtime1").show();
							$j("#fstartorendtime2").show();
							
							$j("#fext5time1").hide();
							$j("#fext5time2").hide();
						}else if(isCustomer == '拒贷' ){
							$j('.otherInfo0').show();
							$j('#dkpz').show();
						}else if(isCustomer == '尚未完成面审'||isCustomer == '完成面审'){
				 			$j('.otherInfo3').show();//显示销售人员
				 			$j('.otherInfo0,.otherInfo1,.otherInfo2').hide();
							
					 	}


						var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
						var json = sendAjax$J(url);
						
						var $tr=$j("#check");//给table设置一个tr
						for(var i=0;i<json.length;i++){//便利初步分类
							var $td = '';
							var valid=false;
							var fdetailCaseSelectedValue = '';
							 <c:forEach var="relInicasetype" items="${baseTypes}">
							 	if('${relInicasetype.basetypeId}' == json[i].key){
									valid = true;
									fdetailCaseSelectedValue = '${relInicasetype.detailId}';
							    }
						   </c:forEach>
						   //显示初步分类的信息
						   if(json[i].value == '其他'){
								if(valid){
									$td = $j("<td><input disabled='disabled' type='checkbox' value='" + json[i].key + "' checked >" + json[i].value + "</td>");
									$j("#basisMark").show();
								}else{
									$td = $j("<td><input type='checkbox' disabled='disabled' value='" + json[i].key + "'>" + json[i].value + "</td>");
									$j("#basisMark").hide();
								}
								$td.appendTo($tr);//加载初步分类的信息
							}else{//初步分类中的其他的分类
								if(valid){//其他分类中有选中的项
									$td = $j("<td><input type='checkbox' disabled='disabled' id='finicasetype"+(i+1)+"' value='" + json[i].key + "' checked>" + json[i].value + "</td>");	
									//给详细分类赋值
									$td.appendTo($tr);//这段代码很关键，如果不处理好，下面的方法执行的时候会报错
									fun_fdetailCaseType$j('finicasetype'+(i+1),json[i].key,'fdetailCaseType'+(i+1),fdetailCaseSelectedValue);//加载案件的详细分类，并设置选中项	
								}else{//其他分类中没有选中的项
									$td = $j("<td><input type='checkbox' disabled='disabled' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].key + "'>" + json[i].value + "</td>");
									$td.appendTo($tr);

									setDetailCaseTypeDisabled('fdetailCaseType'+(i+1));
								}
							}
						}
		      });

	    //加载案件详细分类的信息
		function fun_fdetailCaseType$j(id,finicasetype,fdetailCaseType,selectedValue){
			if($j("#"+id).attr('checked')=='checked'){
				var $fcussourceid = $j("#"+fdetailCaseType);
				loadSelectInfo$J(fdetailCaseType,"<%=basePath%>/caseinfo/caseinfo!findDetailCaseType?finicasetype="+finicasetype,selectedValue);
			}else{
				$j("#"+fdetailCaseType).empty().append( $("<option value='0'>--请选择--</option>") );
			}
		}

		function sendAjax$J(url){
			var json = {};
			$j.ajax({
			   type: "POST",
			   url: url,
			   async:false,//发送同步请求
			   dataType: "json",
			   success: function(data){
			   		json = data;
			   }
			});
			return json;
		}


		/**
		 * 加载页面上的下拉项,用于修改
		 */
		function loadSelectInfo$J(id,url,selectedValue){
			var $id = $j("#"+id);
			$id.empty().append( $j("<option value='0'>--请选择--</option>") );//js 长度置空，并设置默认值
			var json = sendAjax$J(url);
			for(var i=0;i<json.length;i++){
				var opt = '';
				if(selectedValue == json[i].key){
					$opt = $j("<option value="+json[i].key +" selected>"+json[i].value +"</option>");
				}else{
					$opt = $j("<option value="+json[i].key +">"+json[i].value +"</option>");
				}
				$id.append($opt);
			}
			$id.attr('disabled','disabled');
		}

		function setDetailCaseTypeDisabled(id){
			var o=$j("#"+id);
			o.attr('disabled','disabled');
		}
	    </script>
	</head>

	<body>
		<div class="tableForm">
		<input type="hidden" id="customer" value="${complain.fiscustomer}"/>
		<table>
			<tr><td colspan="4"><div class="title">案件信息——投诉信息</div></td></tr>
			<tr id="cusSourceDiv">
				<td class="t-title">投诉人：</td>
				<td><input class="t-text" value="<s:property value='complain.fcomplainanter'/>" disabled="disabled"></td>
		    	<td class="t-title" width="100">客户本人：</td>
		    	<td><select disabled="disabled">
		    			<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fiscustomer==1">selected</s:if> >是</option> 
		    			<option value="0" <s:if test="complain.fiscustomer==0">selected</s:if> >否</option> 
		    		</select></td>  
			</tr>
			<tr>
				<td class="t-title">客户姓名：</td>
				<td><input class="t-text" value="<s:property value='complain.fcusName'/>" disabled="disabled"></td>
		    	<td class="t-title">客户身份证号：</td>
		    	<td><input class="t-text" value="<s:property value='complain.fidcard'/>" disabled="disabled">
		    	
		    	</td>
			</tr>
			<tr>
				<td class="t-title">客户手机号码：</td>
				<td><input class="t-text" value="<s:property value='complain.fmobile'/>" disabled="disabled"></td>
		    	<td class="t-title">客户座机：</td>
		    	<td><input class="t-text" value="<s:property value='complain.fofficephone'/>" disabled="disabled"></td>
			</tr>
			<tr>
				<td class="t-title">客户QQ：</td>
				<td><input class="t-text" value="<s:property value='complain.fqq'/>" disabled="disabled"></td>
		    	<td class="t-title">客户邮箱：</td>
		    	<td><input class="t-text" value="<s:property value='complain.femail'/>" disabled="disabled"></td>
			</tr>
			 <tr class="cusRelation" style="display:none">
				<td class="t-title" >投诉人与客户关系：</td>
				<td><input class="t-text" value="<s:property value='complain.fcusrelation'/>" disabled="disabled"></td>
		    	
			</tr>
			<tr class="cusRelation" style="display:none">
				<td class="t-title">投诉人的身份证号：</td>
				<td><input class="t-text" value="<s:property value='complain.fcomplainidcard'/>" disabled="disabled"></td>
		    	<td class="t-title">投诉人的手机号码：</td>
		    	<td><input class="t-text" value="<s:property value='complain.fcompcontactinfo'/>" disabled="disabled"></td>
			</tr>
			
			<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人的座机：</td>
					<td><input class="t-text" value="<s:property value='complain.fext2'/>" disabled="disabled"></td>
					<td class="t-title">投诉人的QQ：</td>
					<td><input class="t-text" value="<s:property value='complain.fext3'/>" disabled="disabled"></td>
			</tr>
					<tr class="cusRelation" style="display:none">
					<td class="t-title">投诉人的邮箱：</td>
					<td><input class="t-text" value="<s:property value='complain.fext4'/>" disabled="disabled"></td>
				</tr> 
				
					<%--			
				<tr>
	    			<td class="t-title">进件编号：</td>
	    			<td><input class="t-text" disabled="disabled" value="<s:property value='complain.caseComeIntoCode'/>" /></td>
	    			<td class="t-title">进件时间：</td>
	    			<td><input class="t-text Wdate" type="text" readonly="readonly"  
	    				value="<s:property value='complain.caseComeIntoDate'/>" disabled="disabled" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
	    		</tr>
				
				<tr>
					<td class="t-title">案件来源：</td>
					<td>
						<select disabled="disabled"><option selected="selected"><s:property value='complain.casefromPlace'/></option></select>
					</td>
				</tr>
				 --%>
				
				
				<tr id="cusSourceDiv">
						<td class="t-title">客户来源：</td>
				    	<td>
		    		<select disabled="disabled">
		    			<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fcussourceid==1">selected</s:if> >朋友介绍</option> 
		    			<option value="0" <s:if test="complain.fcussourceid==2">selected</s:if> >中介介绍</option> 
		    			<option value="1" <s:if test="complain.fcussourceid==3">selected</s:if> >销售展业</option> 
		    			<option value="0" <s:if test="complain.fcussourceid==4">selected</s:if> >其他</option> 
		    		</select>
	    		</td>
				</tr>
				<s:if test="complain.fcussourceid==4">
					<tr id="cusSourceOtherDiv">
						<td class="t-title">备注：</td>
						<td colspan="3">
							<div class="item" style="width: 590px">
										<textarea rows="4" disabled="disabled"
											style="width:590;"><s:property value="complain.fremarks"/></textarea>
										<div class="tooltip_description" style="display:none" title="备注">
											<s:property value="complain.fremarks"/>
										</div>
								 </div>
						</td>
					</tr>
			</s:if>
			<tr>
				<td class="t-title">服务类型：</td>
				<td id="serviceTypeDiv">
		    		<select disabled="disabled"> 
		    			<option>--请选择--</option> 
		    			<option value="1" <s:if test="complain.fservicetypeid==1">selected</s:if> >普惠金融</option> 
		    			<option value="0" <s:if test="complain.fservicetypeid==2">selected</s:if> >财富管理</option> 
		    			<option value="1" <s:if test="complain.fservicetypeid==3">selected</s:if> >职能端</option> 
		    		</select>
				</td>
		    	<td class="t-title">客户状态：</td>
	    		<td >
		    		<select disabled="disabled" id="fcusstatusids"> 
		    			<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fcusstatusid==1">selected</s:if> >尚未完成面审</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==2">selected</s:if> >完成面审</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==3">selected</s:if> >完成调查</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==4">selected</s:if> >完成初定费率</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==5">selected</s:if> >完成终审</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==6">selected</s:if> >等待签订合同</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==7">selected</s:if> >已签订合同</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==8">selected</s:if> >有条件待签订合同</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==9">selected</s:if> >终审后客户放弃</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==10">selected</s:if> >完成放款</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==11">selected</s:if> >拒贷</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==12">selected</s:if> >超时冻结</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==13">selected</s:if> >展业获取客户</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==14">selected</s:if> >意向接洽</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==15">selected</s:if> >协助签约</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==16">selected</s:if> >完成出借</option> 
		    			<option value="1" <s:if test="complain.fcusstatusid==17">selected</s:if> >后期维护</option> 
		    		</select>
	    		</td>
			</tr>
<%--			<tr class="otherInfo3" id="fservicename">--%>
<%--				<td class="t-title">客服姓名：</td>--%>
<%--		    	<td><input class="t-text" value="<s:property value='complain.fservicename'/>" disabled="disabled"></td>--%>
<%--			</tr >--%>
			
			<tr class="otherInfo1">
				<td class="t-title">合同编号：</td>
				<td><input class="t-text" value="<s:property value='complain.fcontractnumber'/>" disabled="disabled"></td>
		    	<td class="t-title">金额：</td>
		    	<td><input class="t-text" value="<s:property value='complain.famount'/>" disabled="disabled"></td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title">期限：</td>
				<td><input class="t-text" value="<s:property value='complain.fdeadline'/>月" disabled="disabled"></td>
		    	<td class="t-title">还款起始日：</td><td>
		    		<input class="t-text" value="<s:property value='complain.freimbstrattime'/>" disabled="disabled">
	    		</td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title">还款终止日：</td>
				<td><input class="t-text" value="<s:property value='complain.freimbendtime'/>" disabled="disabled"></td>
		    	<td class="t-title">签约时间：</td>
		    	<td>
		    		<input class="t-text" type="text" disabled="disabled" 
		    			value="<s:date name="complain.fcontracttime" format="yyyy-MM-dd"/>"/>
	    		</td>
			</tr>
			<tr class="otherInfo1">
				<td class="t-title">放款时间：</td>
		    	<td>
		    		<input class="t-text" type="text" disabled="disabled" 
		    			value="<s:date name="complain.floantime" format="yyyy-MM-dd"/>"/>
	    		</td>
		    	
			</tr>
			
			<tr class="otherInfo1" id="dkpz">
				<td class="t-title">贷款品种：</td>
		    	<td>
		    	
		    	
		    	<s:set name="tempUsed" value="true"></s:set>
		    	<s:iterator value="loanBreads">
					<s:if test="complain.floanbread==key">
						<s:set name="tempUsed" value="false"></s:set>
	  					<input class="t-text" value="<s:property value="value" />" disabled="disabled">
	  				</s:if>
	   			</s:iterator>
		    	
		    	<s:if test="#tempUsed">
		    		<input class="t-text" value="<s:property value="complain.floanbread" />" disabled="disabled">
		    	</s:if>
		    	<%--
		    		<input class="t-text" value="<s:property value='complain.floanbread'/>" disabled="disabled">
		    	 --%>
		    	
		    	</td>
			</tr>
			
			<tr class="otherInfo1">
				<td class="t-title">有无违约：</td>
				<td>
					<select disabled="disabled" id="fisviolate">
						<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fisviolate==1">selected</s:if> >违约</option> 
		    			<option value="0" <s:if test="complain.fisviolate==0">selected</s:if> >未违约</option> 
		    		</select>
				</td>
				
			
			<tr class="otherInfo1" id="fviolatedaystr" style="display: none;">
	    			<td class="t-title">违约天数：</td>
	    			<td colspan="3"><input type="text" value="<s:property value="complain.fviolatedays"/>" disabled="disabled"
	    				class="t-text easyui-validatebox" validType="integer"/>天</td>
	    		</tr>
	    		
	    		<tr class="otherInfo1" id="fviolateCountHistory" style="display: none;">
	    			<td class="t-title">历史违约次数：</td>
	    			<td colspan="3"><input type="text" disabled="disabled"  id="fviolate" 
	    				class="t-text easyui-validatebox" validType="integer" value="<s:property value="complain.fviolateCountHistory"/>" />次</td>
	    		</tr>
	    	
	    		<tr class="otherInfo2" style="display:none;" id="lcfa">
	    			<td class="t-title" id="fservicetype">理财方案：</td>
	    			<td id="fservicetype2"><input class="t-text" type="text" disabled="disabled" value="<s:property value="complain.financialplan"/>" /></td>
	    		</tr>
	    		
				<tr class="otherInfo2" style="display:none;" id="lxwysj">
						<td class="t-title" id="fstartorendtime1">首次违约时间：</td>
	    				<td id="fstartorendtime2"><input type="text" name="complainInfo.fstartorendtime" value='${complain.fstartorendtime}' readonly="readonly" 
 						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
						<td id="fext5time1" class="t-title">连续违约时间：</td>
				    	<td id="fext5time2"><input type="text"  name="complainInfo.fext5" readonly="readonly" value='${complain.fext5}' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
				</tr>
		  
				<tr class="otherInfo3" style="display: none;">
		    			<td class="t-title">销售人员姓名：</td>
		    			<td><input class="t-text" type="text" disabled="disabled" 
		    				id="fsalesmanname" value="${complain.fsalesmanname}"></td>
		    			<td class="t-title">客服姓名：</td>
		    			<td><input class="t-text" type="text" disabled="disabled"  
		    				id="fservicename" value="${complain.fservicename}"></td>
	    		</tr>
    			<tr class="otherInfo3" style="display:none;">
	    			<td class="t-title">初审人员：</td>
	    			<td><input class="t-text" type="text" disabled="disabled" 
	    				id="ffirsttrial" value="${complain.ffirsttrial}"></td>
	    			<td class="t-title">终审人员：</td>
	    			<td><input class="t-text" type="text" disabled="disabled" 
	    				id="flasttrial" value="${complain.flasttrial}"></td>
    			</tr>
    			
    		<tr class="otherInfo0" style="display: none;"><td class="t-title">拒贷原因：</td><td colspan="3"><textarea rows="4" disabled="disabled"
				style="width:590;"><s:property value="complain.fdeniedloans"/></textarea></td>
			</tr>
			
					
		
			
			<tr><td colspan="4"><div class="title">案件信息——详细信息</div></td></tr>
			<tr>
				<td class="t-title">投诉渠道：</td>
				<td>
					<select disabled="disabled">
						<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fcompchannelid==1">selected</s:if> >电话</option> 
		    			<option value="2" <s:if test="complain.fcompchannelid==2">selected</s:if> >投诉邮箱</option> 
		    			<option value="3" <s:if test="complain.fcompchannelid==3">selected</s:if> >合规邮箱</option> 
		    			<option value="4" <s:if test="complain.fcompchannelid==4">selected</s:if> >个人邮箱</option> 
		    			<option value="5" <s:if test="complain.fcompchannelid==5">selected</s:if> >其他</option> 
		    			<option value="6" <s:if test="complain.fcompchannelid==6">selected</s:if> >信管反欺诈</option> 
		    		</select>
				</td>
		    	<td class="t-title">投诉时间：</td>
		    	<td>
		    		<input class="t-text" type="text" disabled="disabled" 
		    			value="<s:date name="complain.fcomptime" format="yyyy-MM-dd"/>"/>
	    		</td>
			</tr>
			
			<tr <s:if test="complain.fcompchannelid!=1">style='display:none;'</s:if> >
				<td id="SRCodeTh1" class="t-title">SR编码：</td>
				<td id="SRCodeTh2">
					<input type="text" value="${complain.SRCode}" disabled="disabled" name="complainInfo.SRCode" class="t-text easyui-validatebox" validType="number"/>
				</td>
			</tr>
			
			<tr>
				<td class="t-title">个人邮箱备注：</td>
				<td colspan="3">
						<div class="item" style="width: 590px">
							<textarea rows="4" disabled="readonly" style="width:590;"><s:property value="complain.fselfEmailRemark"/></textarea>
							<div class="tooltip_description" style="display:none" title="个人邮箱备注">
								<s:property value="complain.fselfEmailRemark"/>
							</div>
						</div>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="t-title">书面证据：</td><td><select disabled="disabled">--%>
<%--						<option>--请选择--</option>--%>
<%--		    			<option value="1" <s:if test="complain.fisevidence==1">selected</s:if> >有 </option> --%>
<%--		    			<option value="0" <s:if test="complain.fisevidence==0">selected</s:if> >无 </option> --%>
<%--		    		</select></td>--%>
<%--		    	<td class="t-title">证据类型：</td>--%>
<%--		    	<td>--%>
<%--		    		<select disabled="disabled">--%>
<%--		    			<option>--请选择--</option>--%>
<%--		    			<option value="1" <s:if test="complain.fevidencetypeid==1">selected</s:if> >录音</option> --%>
<%--		    			<option value="2" <s:if test="complain.fevidencetypeid==2">selected</s:if> >视频</option> --%>
<%--		    			<option value="3" <s:if test="complain.fevidencetypeid==3">selected</s:if> >短信</option> --%>
<%--		    			<option value="4" <s:if test="complain.fevidencetypeid==4">selected</s:if> >书面证明资料</option> --%>
<%--		    			<option value="5" <s:if test="complain.fevidencetypeid==5">selected</s:if> >其他</option> --%>
<%--		    		</select>--%>
<%--	    		</td>--%>
<%--			</tr>--%>
<%--			<tr><td class="t-title">证据类型备注：</td><td colspan="3"><textarea rows="4" disabled="disabled"--%>
<%--				style="width:590;"><s:property value="complain.fisevidenceRemarks"/></textarea></td>--%>
<%--			</tr>--%>
			<tr>
				<td class="t-title">是否是内部员工：</td><td><select disabled="disabled">
						<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fisinner==1">selected</s:if> >是 </option> 
		    			<option value="0" <s:if test="complain.fisinner==0">selected</s:if> >否 </option> 
		    		</select></td>
		    	<td class="t-title">涉及业务端：</td>
	    		<td>
	    			<select disabled="disabled">
	    				<option>--请选择--</option>
		    			<option value="1" <s:if test="complain.fbusportid==1">selected</s:if> >普惠金融</option> 
		    			<option value="2" <s:if test="complain.fbusportid==2">selected</s:if> >财富管理</option> 
		    			<option value="3" <s:if test="complain.fbusportid==3">selected</s:if> >职能端</option> 
		    		</select>
   				</td>
			</tr>
			<tr>
			<td class="t-title">客服姓名：</td>
		    	<td><input class="t-text" value="<s:property value='complain.fservicename'/>" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td class="t-title">案件初步分类：</td>
				<td colspan="3">	
					<table>
						<tr id="check">
							
			  			</tr>
			  		</table>
		  		</td>
			</tr>
			
			
			<tr id="basisMark"><td class="t-title">案件初步分类备注：</td><td colspan="3"><div class="item"><textarea rows="4" disabled="disabled"
				style="width:590;"><s:property value="complain.finicasetypeRemark"/></textarea><div class="tooltip_description" style="display:none" title="案件初步分类备注">
				<s:property value="complain.finicasetypeRemark"/></div></div></td>
			</tr>
			
			
			
			<tr>
				<td class="t-title">案件详细分类：</td>
				<td colspan="3" style="padding:0;">
	      			<table style="margin:0px;">
						<tr>
							<td><select id="fdetailCaseType1" name="fdetailCaseType1"><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType2" name="fdetailCaseType2"><option value='0'>--请选择--</option></select></td>
			    			<td><select id="fdetailCaseType3" name="fdetailCaseType3"><option value='0'>--请选择--</option></select></td>
						</tr>
						<tr>
							<td><select id="fdetailCaseType4" name="fdetailCaseType4"><option value='0'>--请选择--</option></select></td>
	    					<td colspan="2"><select id="fdetailCaseType5" name="fdetailCaseType5"><option value='0'>--请选择--</option></select></td>
						</tr>
					</table>
		  		</td>
			</tr>
			
			<tr>
				<td class="t-title">案件初步违规分类：</td>
				<td colspan="3">
					<table style="margin: 0" id="initable">
							<tr>
								<s:set value="0" var="tmpSelectOthers"></s:set>
								
								<s:iterator value="newCaseType" var="n" status="s">
									<td> 
									<s:if test="ischecked==1">
										<s:if test="id==27">
											<s:set value="1" var="tmpSelectOthers"></s:set>
											<input title="<s:property value="name"/>" checked="checked"  
											type="checkbox" disabled="disabled"
											value="<s:property value="id"/>"><s:property value="name"/>	
											
										</s:if>
										<s:else>
											<input title="<s:property value="name"/>" checked="checked" disabled="disabled"
											type="checkbox" value="<s:property value="id"/>"><s:property value="name"/>
										</s:else>
									</s:if>
									
									<s:else>
										<input title="<s:property value="name"/>" type="checkbox" disabled="disabled"
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
				<td colspan="3">
						<div>
							<ul style="list-style: none;margin: 0;padding: 0;">
								<s:iterator value="newCaseType" var="n" status="s">
									<s:if test="ischecked==1">
										<s:if test="id!=27">
										<li style="float: left;width: 400px;height: 120px;margin: 0 20px 20px 0;padding: 0;">
										<span style="height: 60px;vertical-align: bottom;display: inline-block;width: 80px;padding: 0;margin: 0;text-align: right;border:0;"><s:property value="name"/></span>
								
			 

<select style="text-align: left;width: 250px;height: 120px;" size="10" multiple="multiple"> 
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
				<tr id="newMark"><td class="t-title">案件新分类备注：</td><td colspan="3"><div class="item"><textarea rows="4" disabled="disabled"
					style="width:590;"><s:property value="complain.fnewtypemark"/></textarea><div class="tooltip_description" style="display:none" title="案件新分类备注">
					<s:property value="complain.fnewtypemark"/></div></div></td>
				</tr>	
			</s:if>
			<s:else>
				<tr id="newMark" style="display: none;"><td class="t-title">案件新分类备注：</td><td colspan="3"><div class="item"><textarea rows="4" disabled="disabled"
					style="width:590;"><s:property value="complain.fnewtypemark"/></textarea><div class="tooltip_description" style="display:none" title="案件新分类备注">
					<s:property value="complain.fnewtypemark"/></div></div></td>
				</tr>
			</s:else>
			
			<%--李如意 --%>
			<tr>
				<td class="t-title">是否收费：</td>
				<td colspan="5">
					<select disabled="disabled">
						<option value='1' <s:if test="complain.isCharge==1">selected</s:if>>是</option>
						<option value='0' <s:if test="complain.isCharge!=1">selected</s:if>>否</option>
					</select>
				</td>
			<tr>
			   <td class="t-title">案件具体描述：</td>
			   <td colspan="3">
					 <div class="item">
					 	<textarea style="width:590;" rows="3" disabled="disabled"><s:property value="complain.fdetaildescrip"/></textarea>
						<div class="tooltip_description" style="display:none" title="案件具体描述"><s:property value="complain.fdetaildescrip"/></div>
					</div>
				</td>
			</tr>
			
				<%--		
				<tr>
		    		<td class="t-title">销售人员：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="<s:property value='complain.salePerson'/>"/></td>
		    		<td class="t-title">上级领导：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="<s:property value='complain.superLeader'/>"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">间接领导：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="<s:property value='complain.indirectLeader'/>" /></td>
		    		<td class="t-title">营业部：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="<s:property value='complain.saleDepart'/>"/></td>
		    	</tr>
		    	
		    	<tr>
		    		<td class="t-title">客服姓名：</td>
		    		<td><input type="text" disabled="disabled" class="t-text" value="<s:property value='complain.serviceName'/>"/></td>
		    	</tr>
			 --%>
			
			
			
			<tr>
				<td class="t-title">案件负责人：</td>
				<td><input class="t-text" value="<s:property value='complain.fresponsibleName'/>" disabled="disabled"></td>
		    	<td class="t-title">负责人邮箱：</td>
		    	<td><input class="t-text" value="<s:property value='complain.fresponsibleEmail'/>" disabled="disabled"></td>
			</tr>
			<tr>
		    		<td class="t-title" nowrap="nowrap">潜在风险等级评估：</td>
			  		<td>
				  		<select name="complain.riskLevel" disabled="disabled">
				  			<option value="1" <s:if test="complain.riskLevel==1">selected</s:if>>高</option>
				  			<option value="2" <s:if test="complain.riskLevel==2">selected</s:if>>中</option>
				  			<option value="3" <s:if test="complain.riskLevel==3">selected</s:if>>低</option>
				  		</select>
			  		</td>
  					<td class="t-title">案件被退回次数：</td>
  					<td><input type="text" class="t-text" value="<s:property value='returnedCount'/>" disabled="disabled"> </td>
		    	</tr>
			
		</table>
		</div>
		
		
		<s:if test="performances!=null and performances.size>0">
		 	<div class="search-list">
		 	<span class="list-title">案件信息——业绩信息</span>
		 	<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fcomplanantPerformance" width="240">被投诉人员业绩</th>
						<%--
						<th field="ftermPerformance" width="240">所在团队业绩</th>
						 --%>
			  		</tr>
			  	</thead>
				<s:iterator value="performances" var="p">
					<tr>
						<td><s:property value="fcomplanantPerformance"/></td>
						<%--
						<td><s:property value="ftermPerformance"/> </td>
						 --%>
					</tr>
				</s:iterator>
			</table>
		 	</div>
    	</s:if>
		
		<%--李如意  收费 --%>
		<s:if test="chargeInfos!=null and chargeInfos.size>0">
			
		 	<div class="search-list" id="chargeDiv">
		 	<span class="list-title">案件信息——收费信息</span>
		 	<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="freceiveType" width="120">收费人员</th>
						<th field="ftype" width="120">收费理由</th>
						<th field="fmode" width="120">给付方式</th>
						<th field="famount" width="120">收费金额</th>
			  		</tr>
			  	</thead>
				<s:iterator value="chargeInfos">
					<tr>
						<td> 
							<s:if test="freceiveType==0">
								请选择
							</s:if>
							<s:if test="freceiveType==1">
								员工
							</s:if>
							<s:elseif test="freceiveType==2">
								中介
							</s:elseif>
							<s:else>
								与同行串通
							</s:else>
						</td>
						
						<td> 
							<s:if test="ftype==0">
								请选择
							</s:if>
							<s:if test="ftype==1">
								好处费
							</s:if>
							<s:elseif test="ftype==2">
								客户包装费
							</s:elseif>
							
							
							<s:elseif test="ftype==3">
								代客户还款
							</s:elseif>
							
							<s:elseif test="ftype==4">
								办理外部业务
							</s:elseif>
							
							<s:elseif test="ftype==5">
								协办征信、流水
							</s:elseif>
							
						</td>
						
						<td> 
							<s:if test="fmode==0">
								请选择
							</s:if>
							
							<s:if test="fmode==1">
								现金
							</s:if>
							<s:elseif test="fmode==2">
								转账
							</s:elseif>
							<s:else>
								从客户账户中直接支取
							</s:else>
						</td>
						
						
						<td> 
							<s:property value="famount"/>
						</td>
						
					</tr>
				</s:iterator>
			</table>
		 	</div>
    	
    	</s:if>
    	
    	
		
		
		
		<s:if test="deptinfos!=null and deptinfos.size>0">
			<div class="search-list">
	    	<span class="list-title">案件信息——被投诉部门信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fdeptname" width="273">部门名称</th>
						<th field="fcityname" width="273">城市名称</th>
						<th field="fsaledepart" width="372">营业部名称</th>
			  		</tr>
			  	</thead>
				<s:iterator value="deptinfos">
					<tr>
						<td><s:property value="fdeptname"/></td>
						<td><s:property value="fcityname"/></td>
						<td><s:property value="fsaledepart"/></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
			
		<s:if test="compalinPersons!=null and compalinPersons.size>0">
			<div class="search-list">
	    	<span class="list-title">案件信息——被投诉人信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fisinner" width="100">是否内部人员</th>
						<th field="fname" width="120">被投诉人姓名</th>
						<th field="fmobile" width="120">手机号码</th>
						<th field="fofficephone" width="150">座机</th>
						<th field="fqq" width="130">QQ</th>
						<th field="femail" width="165">邮箱</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="compalinPersons">
					<tr>
						<td><s:if test="fisinner==0">外部人员</s:if><s:if test="fisinner==1">内部人员</s:if></td>
						<td><s:property value="fname"/></td>
						<td><s:property value="fmobile"/></td>
						<td><s:property value="fofficephone"/></td>
						<td><s:property value="fqq"/></td>
						<td><s:property value="femail"/></td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>/compliance/investigation!viewPerson?
							person.fid=<s:property value='fid'/>&returnBack=true">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
			
		<div class="search-list">
    	<span class="list-title">历史调查记录信息</span>
		<table class="easyui-datagrid">
		  	<thead>
		  		<tr>
					<th field="communicateTime" width="150">沟通时间</th>
					<th field="communicatePerson" width="80">沟通对象</th>
					<th field="cpRelationWAccused" width="140">沟通对象与投诉人的关系</th>
					<th field="content" width="145">沟通内容</th>
					<th field="typeId" width="120">调查方式</th>
					<th field="nextSchemeId" width="150">下一步方案</th>
					<th field="detail" width="50">详情</th>
		  		</tr>
		  	</thead>
			<s:iterator value="investigations">
				<tr>
					<td><s:date name="communicateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="communicatePerson"/></td>
					<td><s:property value="cpRelationWAccused"/></td>
					<td>
						<div class="item" style=""><s:property value="content"/>
							<div class="tooltip_description" style="display:none;" title="反馈结果">
								<s:property value="content"/>
							</div>
						</div>
					</td>
					<td>
			  			<s:iterator value="typeIds">
		  					<s:if test="typeId==key"><s:property value="value"/></s:if>
			  			</s:iterator>
			  		</td>
					<td>
			  			<s:iterator value="nextSchemeIds">
		  					<s:if test="nextSchemeId==key"><s:property value="value"/></s:if>
			  			</s:iterator>
					</td>
					<td>
						<a class="operation-a" href="<%=request.getContextPath()%>
						/compliance/investigation!edit?investigation.id=<s:property value='id'/>&returnBack=true">查看</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		</div>
			
		<s:if test="feedbackRequireds!=null and feedbackRequireds.size>0">
			<div class="search-list">
	    	<span class="list-title">被投诉部门调查处理反馈要求信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="sendTime" width="150">发出时间</th>
						<th field="sendType" width="100">发送方式</th>
						<th field="assistedPerson" width="100">协助负责人</th>
						<th field="email" width="150">协助负责人邮箱</th>
						<th field="assistedPersonOrg" width="134">协助负责人所属部门</th>
						<th field="feedBackTime" width="150">要求反馈时间</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="feedbackRequireds">
					<tr>
						<td><s:date name="sendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="sendType"/></td>
						<td><s:property value="assistedPerson"/></td>
						<td><s:property value="email"/></td>
						<td><s:property value="assistedPersonOrg"/></td>
				  		<td><s:date name="feedBackTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>/compliance/feedbackRequired!edit?
							feedbackRequired.id=<s:property value='id'/>&returnBack=true">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="feedbacks!=null and feedbacks.size>0">
			<div class="search-list">
	    	<span class="list-title">历史反馈信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="feedBackTime" width="150">反馈时间</th>
						<th field="feedBackResult" width="755">反馈结果</th>
			  		</tr>
			  	</thead>
				<s:iterator value="feedbacks">
					<tr>
						<td><s:date name="feedBackTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td style="">
						<div class="item" style="width: 755px">
							<s:property value="feedBackResult"/>
							<div class="tooltip_description" style="display:none" title="反馈结果">
								<s:property value="feedBackResult"/>
							</div>
						</div>
				</td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="feedbackResults!=null and feedbackResults.size>0">
			<div class="search-list">
	    	<span class="list-title">被投诉部门调查处理反馈结果信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="feedBackTime" width="150">反馈时间</th>
						<th field="feedBackTypeId" width="100">反馈结果</th>
						<th field="reason" width="425">理由</th>
						<th field="nextSchemeId" width="150">下一步方案</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="feedbackResults">
					<tr>
						<td><s:date name="feedBackTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<s:if test="feedBackTypeId==1">与投诉一致</s:if>
							<s:if test="feedBackTypeId==2">否决投诉内容</s:if>
							<s:if test="feedBackTypeId==3">无法落实</s:if>
						</td>
						<td>
							 <div class="item" style="width: 755px">
							<s:property value="reason"/>
								<div class="tooltip_description" style="display:none" title="理由">
									<s:property value="reason"/>
								</div>
						 </div>
						 </td>
						<td>
							<s:if test="nextSchemeId==1">申请结案</s:if>
							<s:if test="nextSchemeId==2">需要补充调查</s:if>
							<s:if test="nextSchemeId==3">特殊结案</s:if>
						</td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>/compliance/feedbackResult!edit?
							feedbackResult.id=<s:property value='id'/>&returnBack=true">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="fieldsurveys!=null and fieldsurveys.size>0">
			<div class="search-list">
	    	<span class="list-title">本部门实地调查录入信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="fieldsurveyTime" width="150">实际调查时间</th>
						<th field="investigated" width="80">被调查人</th>
						<th field="suveyPersons" width="210">实地调查人</th>
						<th field="suveyChargeMan" width="100">实地调查负责人</th>
						<th field="content" width="267">被调查内容</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="fieldsurveys">
					<tr>
						<td><s:date name="fieldsurveyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="investigated"/></td>
						<td><s:property value="suveyPersons"/></td>
						<td><s:property value="suveyChargeMan"/></td>
						<td><s:property value="content"/></td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>/compliance/fieldsurvey!edit?
							fieldsurvey.id=<s:property value='id'/>&returnBack=true">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="zcxzlsfbResults!=null and zcxzlsfbResults.size>0">
			<div class="search-list">
	    	<span class="list-title">本部门实地调查——再次协助落实反馈结果信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="xzbmfbTime" width="150">协助部门反馈时间</th>
						<th field="xzbmfbResult" width="650">协助部门反馈结果</th>
						<th field="xzbmfbResultId" width="120">下一步方案</th>
			  		</tr>
			  	</thead>
				<s:iterator value="zcxzlsfbResults">
					<tr>
						<td><s:date name="xzbmfbTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="xzbmfbResult"/></td>
						<td>
							<s:if test="xzbmfbResultId ==1">申请结案</s:if>
							<s:if test="xzbmfbResultId ==2">需要再次协助落实</s:if>
						</td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="applysettlements!=null and applysettlements.size>0">
			<div class="search-list">
	    	<span class="list-title">历史申请结案信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="applyPerson" width="120">申请结案人</th>
						<th field="applyTime" width="150">申请时间</th>
						<th field="arocessingScheme" width="233">案件处理方案</th>
						<th field="directChargeMan" width="150">直接领导姓名</th>
						<th field="indirectChargeMan" width="150">间接领导姓名</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="applysettlements">
					<tr>
						<td><s:property value="applyPerson"/></td>
						<td><s:date name="applyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="arocessingScheme"/></td>
						<td><s:property value="directChargeMan"/></td>
						<td><s:property value="indirectChargeMan"/></td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>/compliance/applysettlement!edit?
							applysettlement.id=<s:property value='id'/>&returnBack=true
								&sepcialEnd=<s:if test='specialEndCase==1'>true</s:if>
									<s:if test='specialEndCase==0'>false</s:if>">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		
		<s:if test="auditResults!=null and auditResults.size>0">
			<div class="search-list">
	    	<span class="list-title">审批历史记录信息</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="auditTypeId" width="100">审批类型</th>
						<th field="user.username" width="180">审批人员</th>
						<th field="createTime" width="100">审批时间</th>
						<th field="isAgree" width="60">是否同意</th>
						<th field="opinion" width="500">审批意见</th>
			  		</tr>
			  	</thead>
				<s:iterator value="auditResults">
					<tr>
						<td>
							<s:if test="auditTypeId==1">转部门协助</s:if>
							<s:if test="auditTypeId==2">实地调查</s:if>
							<s:if test="auditTypeId==3">结案初审</s:if>
							<s:if test="auditTypeId==4">结案终审</s:if>
							<s:if test="auditTypeId==5">结案终审未通过</s:if>
						</td>
						<td>
							<s:property value="user.username"/>
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> 
						</td>
						
						<td>
							<s:if test="isAgree==0">是</s:if>
							<s:if test="isAgree==1">否</s:if>
						</td>
						<td>
							<div class="item" style="width: 590px">
								<s:property value="opinion"/>
								<div class="tooltip_description" style="display:none" title="审批意见">
									<s:property value="opinion"/>
							   </div>
							</div>
						</td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
	</body>
</html>
