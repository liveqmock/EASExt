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
	<title>接口人信息编辑</title>
  	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			//加载校验信息
			$("#portname").addClass("easyui-validatebox").validatebox({required: true,validType:'ormal',missingMessage: '必填'});
			//修改接口人信息的函数
   			$("#editBtn").click(function(){
   	   			var fportEmailValid = $("#fportEmailValid").val();
   	   			var portmail = $("#portmail").val();
   	   			if(fportEmailValid != portmail){
	   				$.messager.confirm('确认', '你确定要修改接口人吗？', function(r){
	   					if(r){//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
	   						submitTab('editBtn','接口人信息');
	   					}
	   				});
   	   			}else{
   	   				submitTab('editBtn','接口人信息');
   	   	   		}
			})
		})
	</script>
	</head>
	<body>
      <div class="tableForm">
		<form action="adminiPurc/adminiPortPurc!update" method="post" id="editForm">
			<input type="hidden" name="portinfo.fid" value='<s:property value="portinfo.fid"/>' />
			<!-- 缓存该变量信息，用于验证邮箱是否发生变化了 -->
			<input type="hidden" id ="fportEmailValid"  name="fportEmailValid" value='<s:property value="portinfo.fportEmail"/>' />
            <div class="title">接口人信息编辑</div>
			<table>
				<tr><td class="t-title" width="100">使用部门</td>
					<td><input class="t-text readonly" type="text" id="orgname" name="portinfo.forgName" readonly 
						value='<s:property value="portinfo.forgName" />'/></td>
     				<td class="t-title" width="120">城市</td>
     				<td><input class="t-text readonly" type="text" id="city" name="portinfo.fcity" readonly
     					value='<s:property value="portinfo.fcity" />'/></td>
					<td class="t-title" width="120">办公室座落地点</td>
			  		<td><input class="t-text readonly"type="text" id="officeadd" name="portinfo.fofficeAddr" readonly
				     	value='<s:property value="portinfo.fofficeAddr" />'/></td>
               </tr>
			   <tr>
			     	<td class="t-title">末级成本中心</td>
			     	<td><input class="t-text readonly"type="text" id="lastcostcenter" name="portinfo.flastCostcenter" readonly
			     		value='<s:property value="portinfo.flastCostcenter" />'/></td>
					<td class="t-title">部门总接口人</td>
			  		<td>
						<input class="t-text readonly"type="text" id="fisAllPort" name="portinfo.fisAllPort" readonly
			     		value='<s:property value="portinfo.fisAllPort" />'/>
			  		</td>
			  		<td class="t-title">接口人姓名</td>
			  		<td><input class="t-text"type="text" id="portname" name="portinfo.fportName" 
			     		value='<s:property value="portinfo.fportName" />'/></td>
			  	</tr>
			  	<tr>
			  		<td class="t-title">接口人邮箱</td>
			  		<td><input  class="t-text easyui-validatebox" id="portmail" name="portinfo.fportEmail" 
			  			validType="emailnew" value='<s:property value="portinfo.fportEmail"/>' /></td>
			  	</tr>
			</table>
            <div class="t-but">
			    <a id="editBtn" href="javascript:void(0)"  class="but-change">修改</a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
      </div>
	</body>
</html>
