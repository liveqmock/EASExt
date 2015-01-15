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
		<title>菜单列表编辑</title>
		<jsp:include page="/common/commonInclude.jsp"></jsp:include>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script type="text/javascript">
			$(function(){
				getMaxSubSequence();
				$("#parentId").change(function(){
					getMaxSubSequence(($("#parentId").val()==null||$("#parentId").val()=="")?"":$("#parentId").val());
				})
				$("#name").addClass("easyui-validatebox").validatebox({required: true,validType:'charLength[1,100]',missingMessage: '必填'});
<%--				$("#url").addClass("easyui-validatebox").validatebox({validType: 'URL',invalidMessage: 'URL格式错误'});--%>
				$("#name,#title,#url").validatebox({validType: 'speci'});
			})
			//获取同级菜单的节点顺序的最大值
			function getMaxSubSequence(parentId){
				$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/menu/menu!getMaxSubSequence",
				   async:false,//发送同步请求
				   dataType:'text',
				   data:{parentId:parentId||(($("#parentId").val()==null||$("#parentId").val()=="")?"":$("#parentId").val())},
				   success: function(data){
					   if($("#parentId").val()!=null && $("#parentId").val()!=""){
					 		$("#subSequence").val($("#parentId").val()==$("#oldParentId").val()?$("#oldSubSequence").val():data);			
					   }else{
					 		$("#subSequence").val($("#insertFlag").val()=="update"?$("#oldSubSequence").val():data);			
					   }
				   }
				});
			}
		</script>
	</head>

	<body>
      <div class="tableForm">
		<form action="menu/menu!<s:if test='menu != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" name="menu.id" value='<s:property value="menu.id"/>' />
			<input type="hidden" id="insertFlag" value="<s:if test='menu != null'>update</s:if><s:else>insert</s:else>">
			<input type="hidden" id="oldSubSequence" value="<s:property value="menu.subSequence" />">
			<input type="hidden" id="oldParentId" value="<s:property value="menu.parentId" />">
            <div class="title">菜单列表编辑</div>
			<table>
				<tr><td class="t-title" width="100">叶子节点</td>
					<td><select name="menu.leaf" id="leaf">
				    	<s:iterator value="leafNames">
				  			<option value="<s:property value='key'/>" 
				  				<s:if test="menu.leaf==key">selected</s:if> ><s:property value="value" /></option>
				    	</s:iterator>
			  		</select></td>
					<td class="t-title" width="100">菜单级别</td>
					<td><select name="menu.level" id="level">
	    		 		<s:iterator value="levelNames">
				  			<option value="<s:property value='key'/>" 
				  				<s:if test="menu.level==key">selected</s:if> ><s:property value="value" /></option>
			    		</s:iterator>
			  		</select></td>
                    <td class="t-title" width="100">窗口位置</td>
		  			<td><select name="menu.target" id="target">
	    		 		<s:iterator value="targetNames">
				  			<option value="<s:property value='key'/>" 
				  				<s:if test="menu.target==key">selected</s:if> ><s:property value="value" /></option>
			    		</s:iterator>
			  		</select></td>
                </tr>	
		  		<tr>
			  		<td class="t-title" >上级菜单名称</td>
			  		<td><select name="menu.parentId" id="parentId">
			  			<option value="" >---无---</option>
	    		 		<s:iterator value="parentMenus">
				  			<option value="<s:property value='id'/>" 
				  				<s:if test="menu.parentId==id">selected</s:if> ><s:property value="name" /></option>
			    		</s:iterator>
					</select></td>
					<td class="t-title" >菜单显示名称</td>
		  			<td><input class="t-text" type="text" id="name" name="menu.name" value='<s:property value="menu.name" />'/><span class="required">*</span></td>
					<td class="t-title" >链接显示title</td>
					<td><input class="t-text" type="text" id="title" name="menu.title" value='<s:property value="menu.title" />'/></td>			  
		  		</tr>
		  	
		  		<tr><td class="t-title" >链接URL地址</td>
		  			<td><input class="t-text" type="text" id="url" name="menu.url" value='<s:property value="menu.url" />'/></td>
		  			<td class="t-title" >同级节点显示顺序</td>
		  			<td colspan="3"><input class="t-text readonly"  type="text" id="subSequence" readonly 
		  				name="menu.subSequence" value='<s:property value="menu.subSequence" />'/></td>
                </tr>
			</table>
            <div class="t-but">
		    	<a href="javascript:void(0)" class="but-change" id="editBtn" onclick="submitTab('editBtn','菜单列表')"><s:if test='menu != null'>修改</s:if><s:else>新增</s:else></a>
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
      </div>
	</body>
</html>
