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
	<style type="text/css">
		table td {
			font-size: 12px;
		}
	</style>
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
  			var cusStatus = $('#cusStatus').val();
  			if(cusStatus == 1){
  				document.getElementById('otherInfo1').style.display = "block";
  			}else if(cusStatus == 6){
  				document.getElementById('otherInfo2').style.display = "block";
  			}
  		}
  		
  	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
<!--    	<div class="ftitle" align="center"><font size="4">客户信息</font></div>    -->
	    <form id="fm" method="post">  
	    <div align="center">      
	    	<table width="100%" border="0">
	    		<tr>
	    		<td width="15%"><label>投诉人:</label></td>
	    		<td width="35%"><input name="lastname" class="easyui-validatebox" required="true"></td>
	    		<td width="15%"><label>是否是客户本人:</label></td>
	    		<td width="35%"><select id="isCustomer" name="isCustomer" onchange="fun_isCustomer();">
		    			<option value="1">是 </option>
		    			<option value="0">否 </option>
		    		</select></td>
	    		</tr>
	    		<tr><td width="15%"><label>身份证号:</label></td>
	    		<td width="35%"><input name="idnumber" id="idnumber"></td>
	    		<td width="15%"><label>手机号码:</label></td>
	    		<td width="35%"><input name="email" class="easyui-validatebox"></td></tr>
	    		<tr><td width="15%"><label>座机:</label></td>
	    		<td width="35%"><input name="email" class="easyui-validatebox"></td>
	    		<td width="15%"><label>QQ:</label></td>
	    		<td width="35%"><input name="email" class="easyui-validatebox"></td></tr>
	    		<tr><td width="15%"><label>邮箱:</label></td>
	    		<td width="35%"><input name="email" class="easyui-validatebox"></td>
	    		<td width="15%"><label>客户来源 :</label></td>
	    		<td width="35%"><select name="cusSource">
		    			<option value="0">朋友介绍</option>
		    			<option value="1">中介介绍</option>
		    			<option value="2">销售展业</option>
		    		</select></td></tr>
	    		<tr><td width="15%"><label>服务类型 :</label></td>
	    		<td width="35%"><select name="serviceType" id="serviceType" onchange="fun_serviceType();">
		    			<option value="0">普惠金融</option>
		    			<option value="1">财富管理</option>
		    		</select></td>
	    		<td width="15%"><label>客户状态 :</label></td>
	    		<td width="35%"><select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
		    			<option>----请选择----</option>
		    		    <option value='1'>完成放款</option>
	  				  	<option value='2'>拒贷</option>
	  				  	<option value='3'>审核中</option>
		    		</select></td></tr>
		    	
		    	<tr><td colspan="4">...</td></tr>
		    	
	    		<tr><td width="15%"><label>合同编号:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>签约时间:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td></tr>
	    		<tr><td width="15%"><label>放款时间:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>产品:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></tr>
	    		<tr><td width="15%"><label>还款起始日:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>还款终止日:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></tr>
	    		<tr><td width="15%"><label>金额:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>期限:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></tr>
	    		<tr><td width="15%"><label>有无违约:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>违约天数:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></tr>
		    	
		    	<tr><td colspan="4">...</td></tr>
		    	
	    		<tr><td width="15%"><label>理财方案:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></td>
	    		<td width="15%"><label>起止时间:</label></td>
	    		<td width="35%"><input type="text" name="sinTime"/></tr>
<!--		    	<tr align="center"><td colspan="4">-->
<!--		    		<a href="compliance/addCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">下一步</a>-->
<!--		    	</td></tr>-->
		 </table>
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
	    </form>
    </div>
  </body>
</html>
