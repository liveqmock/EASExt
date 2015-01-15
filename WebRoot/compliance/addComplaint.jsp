<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
  	<script type="text/javascript">
  		//是否是客户本人
  		function fun_isCustomer(){
			var isCustomerValue = $('#isCustomer').val();
			if(isCustomerValue==1){
				document.getElementById('cusSourceDiv').style.display = "block";
				document.getElementById('cusRelation').style.display = "none";
			}else{
				document.getElementById('cusSourceDiv').style.display = "none";
				document.getElementById('cusRelation').style.display = "block";
			}
  		}
  		//服务类型
  		function fun_serviceType(){
  			var serviceType = $('#serviceType').val();
  			$("#cusStatus")[0].options.length=0;
  			$("#cusStatus").append("<option>----请选择----</option>");
  			if(serviceType==0){
  				  $("#cusStatus").append("<option value='1'>尚未完成面审</option>");//entity 变量
  				  $("#cusStatus").append("<option value='2'>完成面审</option>");
  				  $("#cusStatus").append("<option value='3'>完成调查</option>"); 
  				  $("#cusStatus").append("<option value='1'>完成初定费率</option>");//entity 变量
  				  $("#cusStatus").append("<option value='2'>完成终审</option>");
  				  $("#cusStatus").append("<option value='3'>等待签订合同</option>");
  				  
  				  $("#cusStatus").append("<option value='1'>已签订合同</option>");//entity 变量
  				  $("#cusStatus").append("<option value='2'>有条件待签订合同</option>");
  				  $("#cusStatus").append("<option value='3'>终审后客户放弃</option>");
  			
  				  $("#cusStatus").append("<option value='1'>完成放款</option>");//entity 变量
  				  $("#cusStatus").append("<option value='2'>拒贷</option>");
  				  $("#cusStatus").append("<option value='3'>超时冻结</option>");
  			}else{
				 $("#cusStatus").append("<option value='6'>展业获取客户</option>");//entity 变量
  				 $("#cusStatus").append("<option value='6'>意向接洽</option>");//entity 变量
  				 $("#cusStatus").append("<option value='6'>协助签约</option>");//entity 变量
  				 $("#cusStatus").append("<option value='6'>完成出借</option>");//entity 变量
  				 $("#cusStatus").append("<option value='6'>后期维护</option>");//entity 变量
  			}
  		}
  		//客户状态
  		function fun_cusStatus(){
  			document.getElementById('otherInfo1').style.display = "none";
  			document.getElementById('otherInfo2').style.display = "none";
  			document.getElementById('otherInfo0').style.display = "none";
  			document.getElementById('otherInfo3').style.display = "none";
  			var cusStatus = $('#cusStatus').val();
  			if(cusStatus == 1){
  				document.getElementById('otherInfo1').style.display = "block";
  			}else if(cusStatus == 6){
  				document.getElementById('otherInfo2').style.display = "block";
  			}else if(cusStatus == 2 && $('#cusStatus').find("option:selected").text() == '拒贷' ){
  				document.getElementById('otherInfo0').style.display = "block";
  			}
  			if( (cusStatus == 1 && $('#cusStatus').find("option:selected").text() == '完成放款') ||
  					(cusStatus == 1 && $('#cusStatus').find("option:selected").text() == '尚未完成面审') ||
  					(cusStatus == 2 && 	$('#cusStatus').find("option:selected").text() == '完成面审' )){
  				document.getElementById('otherInfo3').style.display = "block";
  			}
  		}

  		function addOther(){
			if($("#cusSource").val() == 3){
				$("#cusSourceOtherDiv").css({display:"block"});
			}else{
				$("#cusSourceOtherDiv").css({display:"none"});
			} 
  		}
  		
  	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
<!--    	<div class="ftitle" align="center"><font size="4">客户信息</font></div>    -->
	    <form id="fm" method="post">  
	    <div align="center">      
	    	<div class="fitem">
	    		<label>投诉人:</label><input name="lastname" class="easyui-validatebox" required="true">        
	    	</div>   
	    	<div class="fitem">            
	    		<label>是否是客户本人:</label>
	    		<select id="isCustomer" name="isCustomer" onchange="fun_isCustomer();">
	    			<option value="1">是 </option>
	    			<option value="0">否 </option>
	    		</select>  
	    	</div>
	    	<div class="fitem">            
	    		<label>身份证号:</label>            
	    			<input name="idnumber" id="idnumber">        
	    	</div>        
	    	<div class="fitem">            
	    		<label>手机号码:</label>            
	    		<input name="email" class="easyui-validatebox">        
	    	</div> 
	    	<div class="fitem">            
	    		<label>座机:</label>            
	    		<input name="email" class="easyui-validatebox">        
	    	</div> 
	    	<div class="fitem">            
	    		<label>QQ:</label>            
	    		<input name="email" class="easyui-validatebox">        
	    	</div> 
	    	<div class="fitem">            
	    		<label>邮箱:</label>            
	    		<input name="email" class="easyui-validatebox">        
	    	</div> 
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>客户来源 :</label>            
	    		<select name="cusSource" id="cusSource" onchange="addOther()">
	    			<option value="0">朋友介绍</option>
	    			<option value="1">中介介绍</option>
	    			<option value="2">销售展业</option>
	    			<option value="3">其他</option>
	    		</select>  
	    	</div> 
	    	<div class="fitem"  id="cusSourceOtherDiv" style="display:none;">
	    		<label>备注 :</label> 
	    		<textarea rows="2" cols=""></textarea>
	    	</div>
	    	<div class="fitem" id="serviceTypeDiv">            
	    		<label>服务类型 :</label>            
	    		<select name="serviceType" id="serviceType" onchange="fun_serviceType();">
	    			<option value="0">普惠金融</option>
	    			<option value="1">财富管理</option>
	    			<option value="2">职能端</option>
	    		</select>  
	    	</div> 
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>客户状态 :</label>    
	    		<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
  				  	<option value='1'>尚未完成面审</option>
  				    <option value='2'>完成面审</option>
	  				 <option value='3'>完成调查</option> 
	  				 <option value='1'>完成初定费率</option>
	  				 <option value='2'>完成终审</option>
	  				 <option value='3'>等待签订合同</option>
	  				  <option value='1'>已签订合同</option>
	  				 <option value='2'>有条件待签订合同</option>
	  				  <option value='3'>终审后客户放弃</option>
	  				  <option value='1'>完成放款</option>
	  				  <option value='2'>拒贷</option>
	  				  <option value='3'>超时冻结</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="otherInfo0" style="display:none;">
	    		<label>拒贷原因:</label>    
	    		<textarea rows="" cols=""></textarea>
	    	</div>   
	    	<div class="fitem" id="otherInfo1" style="display:none;">   
	    		<table>
	    			<tr>
	    				<td>合同编号</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>签约时间</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>放款时间</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>贷款品种</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    				<tr>
	    				<td>还款起始日</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>还款终止日</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>金额</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>期限</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>有无违约</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>违约天数</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    		</table>         
	    	</div>
	    	<div class="fitem" id="otherInfo2" style="display:none;">   
	    		<table>
	    			<tr>
	    				<td>理财方案</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    			<tr>
	    				<td>起止时间</td>
	    				<td><input type="text" name="sinTime"/></td>
	    			</tr>
	    		</table>
	    	</div>
	    	<div class="fitem" id="otherInfo3" style="display:none;">   
	    		<label>销售人员姓名 :</label> <input type="text"><br/>
	    		<label>客服姓名 :</label> <input type="text"><br/>
	    		<label>初审人员 :</label> <input type="text"><br/>
	    		<label>终审人员 :</label> <input type="text"><br/>
	    	</div>
	    <div id="cusRelation" style="display:none">
		    <div class="fitem">            
		    		<label>与客户关系:</label>            
		    		<input name="email" class="easyui-validatebox">        
		    	</div>
		    	<div class="fitem">            
		    		<label>与投诉人的联系方式:</label>            
		    		<input name="email" class="easyui-validatebox">        
		    	</div> 
		    	<div class="fitem">            
		    		<label>投诉人的身份证号:</label>            
		    		<input name="email" class="easyui-validatebox">        
		    	</div>  
		 </div>
		 <div id="dlg-buttons" align="center">    
		    	<a href="compliance/addCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">下一步</a>
		    </div>
	    </form>
    </div>
  </body>
</html>
