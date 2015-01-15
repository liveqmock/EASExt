<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>预警明细 Demo</title>
	<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../themes/icon.css">
	<link rel="stylesheet" type="text/css" href="demo.css">
	<script type="text/javascript" src="../jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../jquery.easyui.min.js"></script>
</head>
<body>
	<h2>预警明细Demo</h2>
	<!--<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip"></div>
		<div>生日预警、司龄预警，一人次一条信息</div>
        <div>转正预警、合同到期预警，一批次一条信息，但是收件人是多人还是单人？如果是多人，职位信息如何显示</div>
	</div>-->
	<div style="background:#fafafa;padding:5px;width:700px;height:120px;">
			<form id="ff" method="post">
				<table width="" border="0">
<!--					<tr>-->
<!--						<td>-->
<!--							<strong>发送时间从</strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-datetimebox" name="begin"-->
<!--								formatter="2012-11-11 11:00:00" value="" style="width: 150px">-->
<!--						</td>-->
<!--						<td>-->
<!--							<strong>至</strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-datetimebox" name="end" value=""-->
<!--								style="width: 150px">-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							&nbsp;-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td>-->
<!--							<strong>收件人:</strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-validatebox" type="text" name="email"-->
<!--								validType="email"></input>-->
<!--						</td>-->
<!--						<td>-->
<!--							<strong> <label for="subject">-->
<!--									收件部门:-->
<!--								</label> </strong>-->
<!--						</td>-->
<!---->
<!--						<td align="right">-->
<!--							<input class="easyui-validatebox" type="text" name="subject"></input>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							&nbsp;-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td>-->
<!--							<strong>收件职位:</strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-validatebox" type="text" name="email" validType="email"></input>-->
<!--						</td>-->
<!--						<td>-->
<!--							<strong> <label for="message">-->
<!--									预警主题:-->
<!--								</label> </strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input name="message">-->
<!--						</td>-->
<!--						<td>-->
<!--							&nbsp;-->
<!--						</td>-->
<!--					</tr>-->
<!--					<tr>-->
<!--						<td>-->
<!--							<strong> <label for="yjtype">-->
<!--									预警类别:-->
<!--								</label> </strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-combobox" name="yjtype"-->
<!--								url="combobox_data.json" valueField="id" textField="text"-->
<!--								panelHeight="auto">-->
<!--						</td>-->
<!--						<td>-->
<!--							<strong> <label for="yjtype">-->
<!--									预警方式:-->
<!--								</label> </strong>-->
<!--						</td>-->
<!--						<td align="right">-->
<!--							<input class="easyui-combobox" name="yjitem"-->
<!--								url="combobox_data2.json" valueField="id" textField="text"-->
<!--								panelHeight="auto">-->
<!--						</td>-->
<!--						<td align="right" valign="bottom">-->
<!--							<input type="submit" onClick="loaddata()"></input>-->
<!--						</td>-->
<!--					</tr>-->
					<s:iterator value="page.result" var="person">
						<tr>
							<td>
								${person.fid }
							</td>
							<td>
								${person.fname}
							</td>
						</tr>
					</s:iterator>
				</table>
				<page:page></page:page>
			</form>
		</div>
<!--	<table id="tt"></table>-->
</body>
</html>