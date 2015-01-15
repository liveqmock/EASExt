<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sendMessageTest.jsp' starting page</title>
    
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
  			//职位
  			function posSendFun(){
  				var beginTime = $("#dbengin").val();
  				var endTime = $("#dend").val();
  				document.form1.action = 'hr/hr!positionChangeSend.action?position=1&beginTime='+ beginTime + "&endTime=" + endTime;
    			document.form1.submit();
  			}
  			//人员
  			function empSendFun(){
  				var beginTime = $("#dbengin").val();
  				var endTime = $("#dend").val();
  				document.form1.action='hr/hr!empChangeSend.action?emp=1&beginTime='+ beginTime + "&endTime=" + endTime;
    			document.form1.submit();
  			}
  			//人员特殊
  			function empSendFunTeShu(){
  				var beginTime = $("#dbengin").val();
  				var endTime = $("#dend").val();
  				document.form1.action = 'hr/hr!empChangeSendTeshu.action?emp=1&beginTime='+ beginTime + "&endTime=" + endTime;
    			document.form1.submit();
  			}
  	</script>
  </head>
  <body>
   <form action="" method="post" name="form1">
  	<table>
  		<tr>
		    <td> 开始时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		    <td align="right">
				<input type="text" id="dbengin" name="begin" value="${param.begin }" readonly  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',  maxDate:'#F{$dp.$D(\'dend\')||\'2020-10-01\'}'})" class="Wdate" style="width:150px"/>
		    </td>
		    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</td>
		    <td align="right">
		    	<input type="text" id="dend" name="end" value="${param.end }" readonly onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',   minDate:'#F{$dp.$D(\'dbengin\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:150px"/>
		    </td>
  		</tr>
  		<tr>
  			<td>编码</td>
  			<td><input type="text" name="fnumber"/></td>
  		</tr>
  	 </table>
    <a href="javascript:void(0)" onclick="orgSendFun();">测试组织推送正常</a>
    <a href="javascript:void(0)" onclick="posSendFun();">测试职位推送正常</a>
    <a href="javascript:void(0)" onclick="empSendFun();">测试人员推送正常</a>
  	<!-- 处理赵若冰的问题 -->
<!--  	 <a href="javascript:void(0)" onclick="empSendFunTeShu();">测试人员推送正常（特殊）</a>-->
</form>
  </body>
</html>
