<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>编辑功能列表</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	</head>
		<script type="text/javascript">
			function ifFunctionHasExists(obj){
				$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath }/admin/functionaction!ifFunctionHasExists",
				   data: {"funname": obj},
				   dataType:"json",
				   success:function(data){
			   		 if(data >= "1"){
			   		 	$("#showMsg").html("<font color='red'>抱歉，该功能已经存在，请重新输入!</font>");
			   		 	$("#funname").attr("value","");//清空
			   		 	$("#funname").focus();
			   		 }else{
			   		 	$("#showMsg").html("恭喜，可以使用该功能名!");
			   		 }
			  	   }
				});
			}
			</script>
		<script type="text/javascript">
			
			function check(){
				var funname = $("#funname").val();
				var url = $("#url").val();
				if(funname== ""){
					alert("请输入功能名称!");
					return false;
				}
				if(url == ""){
					alert("请输入url!");
					 $("#url").focus();
					return false;
				}
				return true;
			}
		</script>
	<body>
	<div id="dlg" class="easyui-dialog" 
         buttons="#dlg-buttons">  
			<form action="<%=basePath %>admin/functionaction!updateFunction" onsubmit="return check();" method="post">
				
				<input type="hidden" id="id" name="functionaction.id" value="${functionaction.id}"/>
				<table align="center">
					<tr>
						<td colspan="2" align="center"><strong>
							修改功能
						</strong></td>
					</tr>
					<tr>
						<td align="right"><strong>
							功能名：
						</strong></td>
						<td>
							<input type="text" id="funname" name="functionaction.funname" value="${functionaction.funname }"onkeyup="ifFunctionHasExists(this.value);"/>
							<font style="color:red;font-size:20px">*</font> 
							<span id="showMsg"></span>
						</td>
					</tr>
					<tr>
						<td align="right"><strong>
							URL：
						</strong></td>
						<td>
							<input type="text" name="functionaction.url" id="url" value="${functionaction.url}"/>
							<font style="color:red;font-size:20px">*</font> 
						</td>
					</tr>
					<tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" value="修改" />&nbsp;&nbsp;<input type="button" value="取消" onclick="javascript:parent.$.fancybox.close();"/>
						</td>
					</tr>
				</table>
			</form>
		</div>  
	</body>
</html>
