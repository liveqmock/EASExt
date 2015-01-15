<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.creditease.eas.util.mail.SendMailUtil"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href=<%=basePath%>>
    
    <title>My JSP 'testnbsp.jsp' starting page</title>
    
	<meta http-equiv=pragma content=no-cache>
	<meta http-equiv=cache-control content=no-cache>
	<meta http-equiv=expires content=0>    
	<meta http-equiv=keywords content=keyword1,keyword2,keyword3>
	<meta http-equiv=description content=This is my page>
	<!--
	<link rel=stylesheet type=text/css href=styles.css>
	-->
	<style type="text/css">
		div {
			line-height: 150%;
			font-family: 宋体;
			font-size: 14px;
		}
		.div28{
			margin-left:28px;
			line-height: 150%;
			font-family: 宋体;
			font-size: 14px;
		}
		.div28{
			margin-left:50px;
			line-height: 150%;
			font-family: 宋体;
			font-size: 14px;
		}
		.div72{
			margin-left:72px;
			line-height: 150%;
			font-family: 宋体;
			font-size: 14px;
		}
	</style>
  </head>
  <body>
		<div>
				<div>各位好！</div>
				<div style='margin-left:28px'>根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。</div>
  		</div>
  		<table>
  			<tr>
  				<td width="200px"></td>
  			</tr>
  		</table>
  </body>
</html>
