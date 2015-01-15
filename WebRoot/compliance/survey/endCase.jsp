<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>结束案件</title>
    
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
			$("#turnDepartmentDiv").hide();
			$("#planCheckTimeDiv").hide();
			if(nextSchemeValue == 2){
				$("#turnDepartmentDiv").show();
			}else if(nextSchemeValue==3){
				$("#planCheckTimeDiv").show();
			}
		}
	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	    <form id="fm" method="post">
	    <div align="center">  
	    	<div class="fitem">            
	    		<label>申请结案人:</label>
	    		<a href="javascript:void(0);" onclick="openWinNew('compliance/casList.jsp');">选择</a>
	    	</div>    
	    	<div class="fitem">            
	    		<label>申请时间:</label>
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>案件处理方案:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>案件最终归类:</label>            
	    		<select name="nextScheme" id="nextScheme" onchange="fun_nextScheme();">
	    			<option>----请选择----</option>
	    		    <option value='1'>继续调查</option>
	    		    <option value='2'>转部门协助</option>
	    		</select>  
	    	</div> 
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>确认是否违规:</label>            
	    		<select name="nextScheme" id="nextScheme">
	    		    <option value='1'>是</option>
	    		    <option value='2'>无法落实</option>
	    		    <option value='3'>否</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>理由:</label>            
	    	    <textarea rows="5" cols="20" name="reason"></textarea>
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>违规级别:</label>            
	    		<select name="nextScheme" id="nextScheme">
	    		    <option value='1'>一级</option>
	    		    <option value='2'>二级</option>
	    		    <option value='3'>三级</option>
	    		    <option value='3'>三级</option>
	    		     <option value='3'>三级</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>直接领导姓名:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    		<label>处罚结果:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
			<div class="fitem" id="cusSourceDiv">            
	    		<label>间接领导姓名:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    		<label>处罚结果:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>对投诉人解决方案:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    		<label>处罚结果:</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div>
		</div>
	     <div id="dlg-buttons" align="center">
	    	<a href="compliance/survey/surveyList.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
	    </div>
	    </form>
    </div>
    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>