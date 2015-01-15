<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>向制度平台手动推送EAS数据</title>
    
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
  	<script type="text/javascript">
  			//组织
  			function orgSendFun(){
  				var beginTime = $("#dbengin").val();
  				var endTime = $("#dend").val();
  				document.form1.action = 'hr/hr!organizationChangeSend.action?orgid=1&beginTime='+ beginTime + "&endTime=" + endTime;
    			document.form1.submit();
  			}
  	</script>
  </head>
  <body>
  <form name="pushInfoForm" action="" method="post" >
  	<table>
  		<tr>
  			<td align="right">
  			<input id="orgOperate" type="radio" name="operate" value="pushOrgInitInfo" checked/>推送组织数据
  			</td>
  			<td align="right">
  			<input id="personOperate" type="radio" name="operate" value="pushPersonInitInfo" />推送人员数据
  			</td>
  		</tr>
  		<tr>
		    <td> 开始时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		    <td align="left">
				<input type="text" id="dbengin" name="beginTime" value="${param.begin }" readonly  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',  maxDate:'#F{$dp.$D(\'dend\')||\'2020-10-01\'}'})" class="Wdate" style="width:150px"/>
		    </td>
		    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</td>
		    <td align="left">
		    	<input type="text" id="dend" name="endTime" value="${param.end }" readonly onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',   minDate:'#F{$dp.$D(\'dbengin\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:150px"/>
		    </td>
  		</tr>
  		<tr>
  			<td>组织名称:</td>
  			<td align="left"><input id="orgName" type="text" name="orgName" style="border-color:  blue"/></td>
  		</tr>
  		<tr>
  			<td align="center" ><input id="pushButton" type="button" value="推送数据"/></td>
  		</tr>
  	 </table>
  	 <div id="msg"></div>
  </form>
  </body>
</html>
