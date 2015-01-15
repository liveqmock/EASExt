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
	    <form id="fm" method="post">
		    <table align="center">
		    	<tr>
		    		<td width="40%">选择案件:</td>
		    		<td  width="60%"><a href="javascript:void(0);" onclick="openWinNew('compliance/casList.jsp');">选择</a></td>
		    	</tr>
		    	<tr>
		    		<td>沟通时间:</td>
		    		<td><input type="text" name="complainDate" id="complainDate"/></td>
		    	</tr>
		    	<tr>
		    		<td>沟通内容 :</td>
		    		<td><input type="text" name="complainDate" id="complainDate"/></td>
		    	</tr>
		    	<tr>
		    		<td>调查方式 :</td>
		    		<td>
		    			<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();" style="width:155px;">
		    			<option>----请选择----</option>
		    		    <option value='1'>电话调查</option>
		    		    <option value='2'>网络查询</option>
		    		    <option value='3'>邮件/信函</option>
	 				  	<option value='2'>其他</option>
		    			</select>  
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>下一步方案 :</td>
		    		<td>
		    			<select name="nextScheme" id="nextScheme" onchange="fun_nextScheme();" style="width:155px;">
			    			<option>----请选择----</option>
			    		    <option value='1'>继续调查</option>
			    		    <option value='2'>转部门协助</option>
			    		    <option value='3'>实地检查</option>
		  				  	<option value='4'>申请结案</option>
		    			</select>  
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>
		    			<a href="compliance/survey/surveyList.jsp" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">提交</a>
		    		</td>
		    		<td>
		    			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">重置</a>
		    		</td>
		    	</tr>
		    </table>
	    </form>
  </body>
</html>