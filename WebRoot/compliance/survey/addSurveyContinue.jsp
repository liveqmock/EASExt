<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合规初步调查信息添加</title>
    
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
		/**
		 * 新的方式
		 * @param url
		 * @return
		 */
		function openWinNew(url){
			$("#iframeSource").attr("src",url);
			$('#iframeWin').window('open');
		}
		/*
		*下一步方案
		**/
		function fun_nextScheme(){
			var nextSchemeValue = $("#nextScheme").val();
			$("#dlg-buttonsEndCase").hide();
			$("#dlg-buttons").show();
			$("#turnDepartmentDiv").hide();
			$("#planCheckTimeDiv").hide();
			if(nextSchemeValue == 2){
				$("#turnDepartmentDiv").show();
			}else if(nextSchemeValue==3){
				$("#planCheckTimeDiv").show();
			}
			else if(nextSchemeValue==4){//申请结案
				$("#dlg-buttons").hide();
				$("#dlg-buttonsEndCase").show();
			}
		}
	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	<p>&nbsp;</p>
	 <table align="center" style="border-collapse: collapse" border="1">
	 	<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">继续调查</th>
  		</tr>
  		<tr>
  			<td width="50">张三</th>
  			<td width="50">133333</th>
  			<td width="50">abc</th>
  		    <td width="50">朋友介绍</th>
  		     <td width="50">继续调查</th>
  		</tr>
	 </table>
	 <form id="fm" method="post">
	    <div align="center">  
	    	<div class="fitem">            
	    		<label>选择一个案件:</label>
	    		<a href="javascript:void(0);" onclick="openWinNew('compliance/casList.jsp');">选择</a>
	    	</div>    
	    	<div class="fitem">            
	    		<label>沟通时间:</label>
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>沟通内容 :</label>            
	    		<textarea rows="5" cols="20"></textarea>		
	    	</div> 
	    	<div class="fitem" id="serviceTypeDiv">            
	    		<label>调查方式 :</label>            
    			<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>电话调查</option>
	    		    <option value='2'>网络查询</option>
	    		    <option value='3'>邮件/信函</option>
 				  	<option value='2'>其他</option>
	    		</select>  
	    	</div> 
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>下一步方案 :</label>    
	    		<select name="nextScheme" id="nextScheme" onchange="fun_nextScheme();">
	    			<option>----请选择----</option>
	    		    <option value='1'>继续调查</option>
	    		    <option value='2'>转部门协助</option>
	    		    <option value='3'>实地检查</option>
  				  	<option value='4'>申请结案</option>
	    		</select>  
	    	</div>
	    	<div style="display:none;" id="turnDepartmentDiv">
		 		<div class="fitem">            
		    		<label>案件负责人:</label>
		    		<input type="text" name="complainDate" id="complainDate"/>
		    	</div>
		    	<div class="fitem">            
		    		<label>转出理由:</label>
		    		<input type="text" name="complainDate" id="complainDate"/>
		    	</div>
		    </div>
		    <div style="display:none;" id="planCheckTimeDiv">
		    	<div class="fitem">            
		    		<label>计划调查时间:</label>
		    		<input type="text" name="complainDate" id="complainDate"/>
		    	</div>
		    	<div class="fitem">            
		    		<label>调查方式:</label>
		    		<input type="text" name="complainDate" id="complainDate"/>
		    	</div>
	    	</div>
		</div>
	     <div id="dlg-buttons" align="center">
	    	<a href="compliance/survey/surveyList.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">重置</a>
	    </div>
	     <!-- 申请结案 -->
	     <div id="dlg-buttonsEndCase" align="center" style="display: none;">
	    	<a href="compliance/survey/endCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">下一步</a>
	    </div>
	  </form>
    </div>
    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>