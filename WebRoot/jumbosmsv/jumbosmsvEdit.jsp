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
	<title>短信模板编辑</title>
 	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#chooseBtn").click(function(){
				$('#iframeWin').window({onClose : function(){$("#iframeSource").attr("src","");} })
				$("#iframeSource").attr("src",'<%=request.getContextPath()%>/variables/variablesSelectList.jsp');
				$('#iframeWin').window('open');
			})
		})
	</script>
	</head>
	<body>
      <div class="tableForm">
		<form action="person/jumb!<s:if test='jumbosmsv != null'>update</s:if><s:else>save</s:else>" method="post" id="editForm">
			<input type="hidden" name="jumbosmsv.id" value="<s:property value='jumbosmsv.id' />"/>
            <div class="title">短信模板编辑</div>
			<table>
				<s:if test='jumbosmsv != null'>
					<tr>
						<td colspan="2" style="text-align:left" class="t-but"><a href="javascript:void(0)" id="chooseBtn" class="but-change">添加变量</a></td>
					</tr>
				</s:if>
				<tr><td class="t-title">模版名称</td>
					<td><input  class="t-text" type="text" name="jumbosmsv.name" value="<s:property value='jumbosmsv.name' />"/></td>
				</tr>
				<tr><td class="t-title">模版内容</td>
					<td><textarea name="jumbosmsv.content" rows="5" id="contents"><s:property value='jumbosmsv.content' /></textarea></td>
				</tr>
			</table>
            <div class="t-but">	
            <a href="javascript:void(0)" class="but-change" id="editBtn"
						onclick="submitTab('editBtn','短信模版列表')"><s:if test='jumbosmsv != null'>修改</s:if><s:else>新增</s:else></a>
		    	 	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
           </div>
		</form>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true" maximizable=true
			iconCls="icon-save" style="width:1000px;height:400px;padding:2px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
      </div>
	</body>
</html>
