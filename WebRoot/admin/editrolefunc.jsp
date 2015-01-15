<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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

		<title>修改角色功能权限</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
			
			function check(){
				var functionidlist1= document.getElementsByName("functionidlist");
				var isSel2 ="";//判断功能项是否有选中项
				for(var j=0;j<functionidlist1.length;j++){
					if(functionidlist1[j].checked==true){
						isSel2+=functionidlist1[j].value+",";
						}
						}
					if(isSel2==""){
				  	     alert("请选择功能权限！");
				  		 return false;
				  	    }
				 return true;
			}
			 
		</script>
	
	
	
	</head>
	<body>
	<div id="dlg" class="easyui-dialog" 
        buttons="#dlg-buttons">  

			<form action="<%=basePath %>admin/roleaction!updateRoleFuncAction" onsubmit="return check();" method="post">
				
				<table align="center">
					
					<tr>
						<td align="left"><strong>
						<input type="hidden" id="id" name="rolefunction.roleId" value="${rolefunction.roleId}"/>
						修改功能权限：
						</strong>
						<br>
						<c:forEach var="function" items="${functionlist}">
						   <input type="checkbox" name="functionidlist" value="${function.id}" 
						     <c:forEach var="rolefoncid" items="${rolefoncidlist}">
						    	<c:if test="${function.id== rolefoncid}">
						    	 checked = "checked"
						   		</c:if >
						     </c:forEach>
						   >${function.funname}</input>
						</c:forEach>
						
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
