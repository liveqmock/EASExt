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
	<title>有限合伙人列表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
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
		}
	
	  //检查号码是否符合规范，包括长度，类型
	  isCardNo = function(card)
	  {
	  //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	  var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
	  if(reg.test(card)== false)
	  {
	  return false;
	  }
	  return true;
	  };
	
	  function check(){
		  var name=$("#name").val();
		  var card=$("#cardcode").val();
		  var money=$("#money").val();
		  var addr=$("#addr").val();
		  var country=$("#country").val();
		  var time=$("#time").val();
		  
		  //var reg1 = /(^\d+(?:\.\d{2})?$)/;
		  var reg1=/^([\d]+|([\d]+[.]?|[\d]+[.]?[\d]+))$/;
		  
		  if(name==""){
			  alert( "请您输入姓名，姓名不能为空！");
			  $("#name").focus();
			  return false;
		  }
          if(addr==""){
        	  alert("请您输入户籍地址，户籍地址不能为空");
			  $("#addr").focus();
			  return false;
          }
		  if(card==""){
			  alert("请您输入证件号码，证件号码不能为空");
			  $("#cardcode").focus();
			  return false;
		  }
		  //校验长度，类型
		  if(isCardNo(card) == false)
		  {
		  alert("您输入的身份证号码不正确，请重新输入15位或18位号码");
		  $("#cardcode").focus();
		  return false;
		  }
		  if(country==""){
        	  alert("请您输入国家，国家不能为空");
			  $("#country").focus();
			  return false;
          }
		  if(money==""){
			  alert("请您输入出资金额，出资金额不能为空");
			  $("#money").focus();
			  return false;
		  }
		  if(reg1.test(money)== false){
			  alert("出资金额输入不正确，不能包含非数字");
			  $("#money").focus();
			  return false;
		  }
		  if(time==""){
        	  alert("请您输入出资时间，出资时间不能为空");
			  $("#time").focus();
			  return false;
          }
          if(CheckDate(time)){
        	  alert("出资时间格式输入不正确,请注意日期格式（如：2007/07/17或2007-07-17）或闰年！");
			  $("#time").focus();
			  return false;
          }
		  return true;
	  }

	</script>
	</head>
	<body>
	<%--<div style="width: 800px;height: 500px;">
	--%>
	<div align="center" class="tableForm" style="width: 800px;height: 500px;">
	<br/>
	<br/>
	<h4 style="margin-left: 200px;">添加有限合伙人</h4>
	<form action="<%=basePath%>limitedpartner/limitedpartner!addPartner?limitedpartner.id=${pid}" method="post" onsubmit="return check();">
		<table style="float: left;margin-left: 100px;">
			<tr>
			 <td align="right">姓名：</td>
			 <td><input  type="text" name="partner.partner_name" id="name" value=""/></td>
			 <td align="right">性别：</td>
			 <td>
				 <input type="radio" id="1" name="partner.partner_sex" value="男" checked="checked"/>男&nbsp;&nbsp;&nbsp;
				 <input type="radio" id="2" name="partner.partner_sex" value="女"/>女 
			 </td>
		</tr>
		<tr>
			<td align="right">民族：</td>
			<td>
				<select style="width:155px;" name="partner.partner_nation">
					<option value="汉族">
						汉族
					</option>
					<option value="蒙古族">
						蒙古族
					</option>
					<option value="彝族">
						彝族
					</option>
					<option value="侗族">
						侗族
					</option>
					<option value="哈萨克族">
						哈萨克族
					</option>
					<option value="畲族">
						畲族
					</option>
					<option value="纳西族">
						纳西族
					</option>
					<option value="仫佬族">
						仫佬族
					</option>
					<option value="仡佬族">
						仡佬族
					</option>
					<option value="怒族">
						怒族
					</option>
					<option value="保安族">
						保安族
					</option>
					<option value="鄂伦春族">
						鄂伦春族
					</option>
					<option value="回族">
						回族
					</option>
					<option value="壮族">
						壮族
					</option>
					<option value="瑶族">
						瑶族
					</option>
					<option value="傣族">
						傣族
					</option>
					<option value="高山族">
						高山族
					</option>
					<option value="景颇族">
						景颇族
					</option>
					<option value="羌族">
						羌族
					</option>
					<option value="锡伯族">
						锡伯族
					</option>
					<option value="乌孜别克族">
						乌孜别克族
					</option>
					<option value="裕固族">
						裕固族
					</option>
					<option value="赫哲族">
						赫哲族
					</option>
					<option value="藏族">
						藏族
					</option>
					<option value="布依族">
						布依族
					</option>
					<option value="白族">
						白族
					</option>
					<option value="黎族">
						黎族
					</option>
					<option value="拉祜族">
						拉祜族
					</option>
					<option value="柯尔克孜族">
						柯尔克孜族
					</option>
					<option value="布朗族">
						布朗族
					</option>
					<option value="阿昌族">
						阿昌族
					</option>
					<option value="俄罗斯族">
						俄罗斯族
					</option>
					<option value="京族">
						京族
					</option>
					<option value="门巴族">
						门巴族
					</option>
					<option value="维吾尔族">
						维吾尔族
					</option>
					<option value="朝鲜族">
						朝鲜族
					</option>
					<option value="土家族">
						土家族
					</option>
					<option value="傈僳族">
						傈僳族
					</option>
					<option value="水族">
						水族
					</option>
					<option value="土族">
						土族
					</option>
					<option value="撒拉族">
						撒拉族
					</option>
					<option value="普米族">
						普米族
					</option>
					<option value="鄂温克族">
						鄂温克族
					</option>
					<option value="塔塔尔族">
						塔塔尔族
					</option>
					<option value="珞巴族">
						珞巴族
					</option>
					<option value="苗族">
						苗族
					</option>
					<option value="满族">
						满族
					</option>
					<option value="哈尼族">
						哈尼族
					</option>
					<option value="佤族">
						佤族
					</option>
					<option value="东乡族">
						东乡族
					</option>
					<option value="达斡尔族">
						达斡尔族
					</option>
					<option value="毛南族">
						毛南族
					</option>
					<option value="塔吉克族">
						塔吉克族
					</option>
					<option value="德昂族">
						德昂族
					</option>
					<option value="独龙族">
						独龙族
					</option>
					<option value="基诺族">
						基诺族
					</option>
				</select>
			</td>
			<td  align="right">户籍地址：</td>
		    <td>
		    	<input type="text" id="addr" name="partner.partner_addr" value=""/>
		    </td>
		</tr>
		<tr>
			<td  align="right">证件号码：</td>
		    <td>
		    	<input type="text" name="partner.partner_IDCard" id="cardcode" value=""/>
		    </td>
		    <td  align="right">国家：</td>
		    <td>
		    	<input type="text"  id="country" name ="partner.partner_country" value="中国" />
		    </td>
	    </tr>
		<tr>
			<td align="right">出资总额(万元)：</td>
		    <td>
		    	<input type="text" name="partner.partner_amount" id="money" value=""/>
		    </td>
		    <td align="right">出资时间：</td>
		    <td>
		    	<input type="text" id="time" name="partner.partner_date" onclick="WdatePicker()" readonly="readonly"/>
		    </td>
		</tr>
		<tr>
		    <td align="right">备注：</td>
		    <td colspan="3">
		    	<textarea style="width:470px;height: 40px" rows="30" cols="30" name="partner.partner_note"></textarea>
		    </td>
		</tr>
	    <tr>
	       	<td colspan="2" align="center">
	       		<input type="submit" value="提交">
	     	 </td>
	      	<td colspan="2" align="center">
	      		<input type="button" value="返回" onclick="javascript:history.go(-1);">
	    	</td>
		</tr>
	</table>
</form>
	</div>
	</body>
</html>
