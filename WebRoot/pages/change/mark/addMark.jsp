<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		Map<String,String> map=(Map<String,String>)request.getAttribute("map");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>标签管理</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=path%>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
 	<link rel="stylesheet" href="<%=path%>/inc/style.css" />
 	<style type="text/css">
 	 .tableForm .t-but{text-align:right;}
 	 .t-but input{ background:url(../css/default/images/tableForm-but-bg.png) no-repeat; height:36px; line-height:36px; color:#fff;width:78px; display:inline-block; text-align:center; font-size:14px;}
 	 .t-but .but-cancel{background-position:0 0;}
     .t-but .but-change{background-position:0 -37px;}
 	</style>
 	<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">

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
		  var pattern=/^([a-z]*[A-Z]*)+$/; // 只支持英文
		  
		  if(name==""){
			  $.messager.alert("提示", "请您输入标签名称，标签名称不能为空！");
			  return false;
		  }
		//  if(pattern.test(name)==false){
		//	  $.messager.alert("提示", "标签名称输入不符合格式，只能输入英文！");
		//	  return false;
		//  }
		  $.post("<%=basePath%>limitedpartner/limitedpartner!checkMarkName",{
			  "mark.bookmarkname":name
			  },
				  function(data,status){
				    if("OK"!=data){
						$.messager.alert("警告", "您输入的标签名称有重复，不能使用！");
					    $("#name").val("");
					}
				  });
	  }

	  $(function(){
		  $("#name").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
	  })

	</script>
	</head>
	<body>
	
	<div class="tableForm">
	<div class="title">标签新增</div>
	<form action="<%=basePath%>limitedpartner/limitedpartner!addMark" onsubmit="return check()" method="post">
	<table>
	         <tr>
				 <td class="t-title">标签名称：</td>
				 <td>
				 	<input class="t-text"  id="name" type="text"  name="mark.bookmarkname"/><span class="required">*</span>
				 	<input type="hidden" name="mark.bookmarkid" value="${mark.bookmarkid}" />
				 </td>
			 </tr>
			 <tr>
				 <td class="t-title">标签解释：</td>
				 <td>
					 <input class="t-text" type="text" name="mark.bookmarkdesc" />
				 </td>
			</tr>
			<tr>
				<td class="t-title">标签备注：</td>
				<td>
					<textarea class="t-text" style="width:200px;height: 60px" rows="3" cols="100" name="mark.bookmarktext"></textarea>
				</td>
			</tr>
			<tr>
				<td class="t-title">标签值：</td>
			    <td>
			    	<input class="t-text" type="text" name="mark.tablefield" value=""/>
			    </td>
			</tr>
			<tr>
				<td class="t-title">标签类型：</td>
			    <td>
			    	<select style="width: 220px;" name="mark.isloop">
			    		<option value="1000" selected="selected">普通标签</option>
			    		<% for(Map.Entry<String,String> en : map.entrySet()){ %>
				    	<option value="<%=en.getKey() %>"><%=en.getValue() %></option>
				    	<%} %>
			    	</select>
			    </td>
		    </tr>
	</table>
	 <div class="t-but">
		    	<input type="submit"  class="but-change" value="确定"/>
		    	<input type="button"  class="but-cancel" value="取消" onclick="javascript:history.go(-1);"/>
            </div>
	</form>
	</div>
	
	</body>
</html>
