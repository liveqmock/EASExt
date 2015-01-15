<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实地调查</title>
    
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
		
		function fun_cusStatus(){
			var cusStatus = $("#cusStatus").val();
			if(cusStatus==1){
				$("#reasonDiv").hide();
			}else if(cusStatus == 2 || cusStatus==3){
				$("#reasonDiv").show();
			}
		}
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
			$("#dlg-buttonsContinueCase").hide();
			$("#dlg-buttonsspecialEndCase").hide();
			if(nextSchemeValue == 1){
				$("#dlg-buttons").hide();
				$("#dlg-buttonsEndCase").show();
			}else if(nextSchemeValue == 2){
				$("#dlg-buttons").hide();
				$("#dlg-buttonsContinueCase").show();
			}else if(nextSchemeValue==3){
				$("#dlg-buttons").hide();
				$("#dlg-buttonsspecialEndCase").show();
			}
		}
	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	    <form id="fm" method="post">
	    <div align="center">
	  		 <div class="fitem" id="cusSourceDiv">            
	    		<label>反馈时间:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/><br/>
	    	   <label>反馈结果:</label>            
	    	   <select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>与投诉一致</option>
	    		    <option value='2'>否决投诉内容</option>
	    		    <option value='3'>无法落实</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="reasonDiv" style="display:none;">            
	    		<label>理由 :</label>            
	    		<textarea rows="5" cols="15"></textarea>
	    	</div> 
	    	<div class="fitem" id="nextSchemeDiv">            
	    		<label>下一步方案 :</label>            
	    		<select name="nextScheme" id="nextScheme" onchange="fun_nextScheme();">
	    			<option>----请选择----</option>
	    		    <option value='1'>申请结案</option>
	    		    <option value='2'>需要补充调查</option>
	    		    <option value='3'>特殊结案</option>
	    		</select>  
	    	</div>
	    </div>
	 	<%@ include file="/compliance/include/include.jsp"%> 
	     <div id="dlg-buttons" align="center">
	    	<a href="compliance/survey/surveyList.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
<!--	        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">重置</a>-->
	    </div>
		<!-- 申请结案 -->
	     <div id="dlg-buttonsEndCase" align="center" style="display: none;">
	    	<a href="compliance/survey/endCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">下一步</a>
	    </div>
	     <!-- 需要补充调查 -->
	     <div id="dlg-buttonsContinueCase" align="center" style="display: none;">
    		<label>需要补充调查依据 :</label>            
    		<textarea rows="5" cols="10"></textarea><br/>
    		<label>案件负责人:</label>
    		<input type="text" name="complainDate" id="complainDate"/><br/>
    		<label>转出理由:</label>
    		<input type="text" name="complainDate" id="complainDate"/><br/>
	    	<a href="compliance/survey/endCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
	    </div>
	    <!-- 特殊结案-->
	     <div id="dlg-buttonsspecialEndCase" align="center" style="display: none;">
    		<label>原因:</label>            
    		<textarea rows="5" cols="10"></textarea><br/>
	    	<a href="compliance/survey/endCase.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
	    </div>
	   </form>
    </div>
    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>