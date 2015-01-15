<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<title>有限合伙人列表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<style type="text/css">
 	 .tableForm .t-but{text-align:right;}
 	 .t-but input{ background:url(../css/default/images/tableForm-but-bg.png) no-repeat; height:36px; line-height:36px; color:#fff;width:78px; display:inline-block; text-align:center; font-size:14px;}
 	 .t-but .but-cancel{background-position:0 0;}
     .t-but .but-change{background-position:0 -37px;}
 	</style>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">

	 // 效验日期格式
	   function CheckDate(str){
		    //在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
		    var Expression=/^((((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13578]|1[02])(\/|\-)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)(0?[13456789]|1[012])(\/|\-)(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})(\/|\-)0?2(\/|\-)(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/; 
			var objExp=new RegExp(Expression);
			if(objExp.test(str)==true){
				return false;
			}else{
				return true;
			}
	};
	
	function check(){
	      var reg=/^([\d]+|([\d]+[.]?|[\d]+[.]?[\d]+))$/;
	      var number=/^(0|[1-9][0-9]{0,5})(.[0-9]{1,2})?$/;
          var gpcontribution=$("#gpcontribution").val();
          var gp=$("#gp").val();
          var lp=$("#lp").val();
          var proxy=$("#proxy").val();
          var place=$("#place").val();
          var time=$("#time").val();
          
          if(gp==""){
        	  $.messager.alert("提示", "请您选择普通合伙人（GP），普通合伙人不能为空！");
			  $("#gp").focus();
			  return false;
          }
          if(lp==""){
        	  $.messager.alert("提示", "请您选择原有限合伙人（LP），原有限合伙人不能为空！");
			  $("#lp").focus();
			  return false;
          }
          if(proxy==""){
        	  $.messager.alert("提示", "请您选择代理人，代理人不能为空！");
			  $("#proxy").focus();
			  return false;
          }
          if(place==""){
        	  $.messager.alert("提示", "请您选择主要经营场所，主要经营场所不能为空！");
			  $("#place").focus();
			  return false;
          }
          if(time==""){
        	  $.messager.alert("提示", "请您选择出资时间，出资时间不能为空！");
			  $("#time").focus();
			  return false;
          }
          if(CheckDate(time)){
        	  $.messager.alert("提示", "出资时间格式输入不正确,请注意日期格式（如：2007/07/17或2007-07-17）或闰年！");
			  $("#time").focus();
			  return false;
          }
          if(gpcontribution==""){
			  $.messager.alert("提示", "请您输入GP出资额，GP出资额不能为空！");
			  $("#gpcontribution").focus();
			  return false;
		  }
          if(number.test(gpcontribution)== false){
			  $.messager.alert("提示", "GP出资额输入不正确，数字整数长度只能为1到6位，且小数点后最多2位！");
			  $("#gpcontribution").focus();
			  return false;
		  }else if(parseFloat(gpcontribution)==0){
			  $.messager.alert("提示", "GP出资额输入不正确，不能为0！");
			  $("#gpcontribution").focus();
			  return false;
		  }
          
		  return true;
	  }

	$(function(){
		  $("#lp,#proxy,#place,#time,#gpcontribution").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
	  })

	</script>
	</head>
	<body>
	<div class="tableForm">
	<div class="title">补充信息编辑</div>
	<form action="<%=basePath%>limitedpartner/limitedpartner!addsupplementLimitedPartner?pid=${pid}" method="post" onsubmit="return check();">
	<table>
	<tr>
	 <td class="t-title">普通合伙人（GP）:</td>
	 <td ><select style="width: 300px;"  id="gp" name="limitedpartner.generalperson">
	 <option value="宜信卓越财富投资管理（北京）有限公司" <c:if test="${limitedpartner.generalperson=='宜信卓越财富投资管理（北京）有限公司'}">selected="selected"</c:if>>宜信卓越财富投资管理（北京）有限公司</option>
	 <option value="宜信惠民投资管理（北京）有限公司" <c:if test="${limitedpartner.generalperson=='宜信惠民投资管理（北京）有限公司'}">selected="selected"</c:if>>宜信惠民投资管理（北京）有限公司</option>
	 <option value="深圳普泽众富资产管理有限公司" <c:if test="${limitedpartner.generalperson=='深圳普泽众富资产管理有限公司'}">selected="selected"</c:if>>深圳普泽众富资产管理有限公司</option>
	 </select><span class="required">*</span></td>
	 <td class="t-title">原有限合伙人（LP）：</td>
	<td><input class="t-text" id="lp"  style="width: 280px;" type="text" name="limitedpartner.formerlimitedpartner" value="${limitedpartner.formerlimitedpartner }"/><span class="required">*</span></td>
	</tr>
	<tr>
	<td class="t-title">代理人：</td>
    <td><input class="t-text" id="proxy" style="width: 280px;" type="text" name="limitedpartner.proxy" value="${limitedpartner.proxy }"/><span class="required">*</span></td>
    <td class="t-title">主要经营场所：</td>
    <td><input class="t-text" id="place" style="width: 280px;" type="text" name="limitedpartner.businessplace" id="cardcode" value="${limitedpartner.businessplace }"/><span class="required">*</span></td>
	</tr>
	<tr>
    <td class="t-title">出资时间：</td>
    <td><input class="t-text Wdate" id="time" style="width: 280px;" type="text" name="limitedpartner.investedtime" value="${limitedpartner.investedtime }" onclick="WdatePicker()" readonly="readonly"/><span class="required">*</span></td>
    <td class="t-title">GP出资额（万元）：</td>
    <td><input class="t-text" id="gpcontribution" style="width: 280px;" type="text" name="limitedpartner.gpcontribution" value="${limitedpartner.gpcontribution }"/><span class="required">*</span></td>	
    </tr>
    <tr>
    <td class="t-title">出资比例小数位：</td>
    <td>
    <select style="width: 300px;" id="figure" name="figure">
     <option value="1" <c:if test="${limitedpartner.figure=='1'}">selected="selected"</c:if>>1</option>
	 <option value="2" <c:if test="${limitedpartner.figure=='2'}">selected="selected"</c:if>>2</option>
	 <option value="3" <c:if test="${limitedpartner.figure=='3'}">selected="selected"</c:if>>3</option>
	 <option value="4" <c:if test="${limitedpartner.figure=='4'}">selected="selected"</c:if>>4</option>
	 </select>
	 </td>
    <td class="t-title">出资比例计算方式：</td>
    <td><select style="width: 300px;"  name="calculationmethod">
	 <option value="标准" <c:if test="${limitedpartner.calculationmethod=='标准'}">selected="selected"</c:if>>标准</option>
	 <option value="倒减" <c:if test="${limitedpartner.calculationmethod=='倒减'}">selected="selected"</c:if>>倒减</option>
	 </select>	
    </tr>
   </table>
   <div class="t-but">
		    	<input type="submit"  class="but-change" value="修改"/>
		    	<input type="button"  class="but-cancel" value="取消" onclick="javascript:history.go(-1);"/>
            </div>
	</div>
	</body>
</html>
