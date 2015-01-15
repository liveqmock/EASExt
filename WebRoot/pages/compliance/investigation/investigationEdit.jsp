<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>合规初步调查信息添加</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab_common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/pages/compliance/investigation/investigationEdit.js" ></script>
	<style>
	.datagrid-body .datagrid-cell, .datagrid-footer .datagrid-cell {white-space:normal}
	</style>
	
	<script type="text/javascript">
		/*function f(value,row,index){
			alert(value);
			if( "" != value && null != value){
				//alert(value);
      			var c=value.replace(/\n/g,"<br/>");         
      			//alert(c);
      			return c;
      	    }
		}*/
	</script>
	</head>

	<body>
		<s:if test='parentId != null'>
	    	<div class="search-list">
	    	<span class="list-title">合规初步调查历史记录</span>
			<table class="easyui-datagrid">
			  	<thead>
			  		<tr>
						<th field="communicateTime" width="150">沟通时间</th>
						<th field="communicatePerson" width="80">沟通对象</th>
						<th field="cpRelationWAccused" width="140">沟通对象与投诉人的关系</th>
						<th field="content" width="500">沟通内容</th>
						<th field="typeId" width="120">调查方式</th>
						<th field="nextSchemeId" width="150">下一步方案</th>
						<th field="detail" width="50">详情</th>
			  		</tr>
			  	</thead>
				<s:iterator value="investigations">
					<tr>
						<td><s:date name="communicateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="communicatePerson"/></td>
						<td><s:property value="cpRelationWAccused"/></td>
						<td>${content}</td>
						<td>
				  			<s:iterator value="typeIds">
			  					<s:if test="typeId==key"><s:property value="value"/></s:if>
				  			</s:iterator>
				  		</td>
						<td>
				  			<s:iterator value="nextSchemeIds">
			  					<s:if test="nextSchemeId==key"><s:property value="value"/></s:if>
				  			</s:iterator>
						</td>
						<td><a class="operation-a" href="<%=request.getContextPath()%>
							/compliance/investigation!edit?investigation.id=<s:property value='id'/>&returnBack=true">查看</a></td>
					</tr>
				</s:iterator>
			</table>
			</div>
		</s:if>
		<div class="tableForm">
		<form action="compliance/investigation!<s:if test='investigation != null'>update</s:if><s:else>insert</s:else>" 
			method="post" id="editForm">
			<input type="hidden" id="id" name="investigation.id" value='<s:property value="investigation.id"/>' />
			<input type="hidden" id="parentId" name="investigation.parentId" value='<s:property value="parentInvestigation.id"/>' />
			<input type="hidden" id="sepcialEnd" value="false"/>
			<input type="hidden" id="returnBack" value="<s:property value='returnBack'/>"/>
			<div class="t-but" style="text-align: left;">
				<a href="javascript:void(0)" id="returnBtn" onclick="javascript:window.history.go(-1);" style="display: none;"
					class="but-cancel">返回</a>
			</div>
			<div class="title">合规调查编辑</div>
			<table>
				<s:if test='parentId != null'>
					<tr><td class="t-title" width="100">关联案件：</td>
						<td><input class="t-text" type="text" disabled="disabled" value='<s:property value="complain.fnumber"/>'/>
							<input type="hidden" name="investigation.caseId" value='<s:property value="parentInvestigation.caseId"/>'/>
						</td>
				</s:if>
				<s:else>
					<tr><td class="t-title" width="100">选择一个案件：</td>
						<td><input class="t-text" type="text" id="caseCode" readonly value="<s:property value="complain.fnumber"/>"
							onclick="openWinNew('pages/compliance/investigation/chooseCase.jsp')"/>
							<input type="hidden" id="caseId" name="investigation.caseId" value='<s:property value="investigation.caseId"/>'/>
						</td>
				</s:else>
				<td class="t-title" width="100">沟通时间：</td>
				<td>
					<input class="t-text" type="text" id="communicateTime" name="investigation.communicateTime" 
						value="<s:date name='investigation.communicateTime' format='yyyy-MM-dd HH:mm:ss'/>"/> 
	  			</td></tr>
			  	
			  	<tr><td class="t-title">沟通对象：</td>
				<td><input class="t-text" type="text" id="communicatePerson" name="investigation.communicatePerson"
					value='<s:property value="investigation.communicatePerson"/>' /></td>
			  	<td class="t-title">与投诉人的关系：</td>
			  	<td><input class="t-text" type="text" id="cpRelationWAccused" name="investigation.cpRelationWAccused"
			  		value='<s:property value="investigation.cpRelationWAccused"/>'/></td></tr>
			  	
			  	<tr><td class="t-title">沟通内容：</td>
			  	<td colspan="3"><textarea id="investContent" rows="4" style="width:580;" 
			  		name="investigation.content"><s:property value="investigation.content"/></textarea></td></tr>
			  	
			  	<tr><td class="t-title" width="100">调查方式：</td>
			  	<td><select name="investigation.typeId" id="typeId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="typeIds">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="investigation.typeId==key">selected</s:if> ><s:property value="value"/></option>
			  			</s:iterator>
			  		</select></td>
			  		<td class="t-title" width="100">下一步方案：</td>
			  	<td><select name="investigation.nextSchemeId" id="nextSchemeId">
			  			<option value="">--请选择--</option>
			  			<s:iterator value="nextSchemeIds">
			  				<option value="<s:property value='key'/>" 
			  					<s:if test="investigation.nextSchemeId==key">selected</s:if> ><s:property value="value" /></option>
			  			</s:iterator>
			  		</select>
			  		<s:if test="investigation!=null"><input type="hidden" name="investigation.nextSchemeId" 
			  			value='<s:property value="investigation.nextSchemeId"/>'/> </s:if>
			  		</td></tr>
			  		
			  	<tr style="display:none;" class="casecadeWithNextScheme0"><td class="t-title">转出理由：</td>
			  	<td colspan="3"><textarea rows="4" style="width:580;" id="outReason"
			  		name="investigation.outReason"><s:property value="investigation.outReason"/></textarea></td></tr>
			  	
			  	<tr style="display:none;" class="casecadeWithNextScheme1"><td class="t-title">计划调查时间：</td>
				<td>
					<input type="text" id="fPreplanITime" name="investigation.preplanITime" readonly 
			  		value="<s:date name="investigation.preplanITime" format="yyyy-MM-dd HH:mm:ss"/>" 
			  		onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/>
				</td>
				<td class="t-title">实地调查方式：</td>
				<td><input class="t-text" type="text" id="bbmsddcfs" name="investigation.bbmsddcfs" 
					value='<s:property value="investigation.bbmsddcfs"/>' /></td></tr>
			  	
			  	<tr style="display:none;" class="casecadeWithNextScheme2"><td class="t-title">下一次沟通时间：</td>
				<td colspan="3">
					<input class="t-text" type="text" id="nextCommunicateTime" name="investigation.nextCommunicateTime" 
						value="<s:date name='investigation.nextCommunicateTime' format='yyyy-MM-dd HH:mm:ss'/>" > 
				</td></tr>
			</table>
			<div class="t-but" style="display:'" id="nextstep0">
			<a href="javascript:void(0)" id="editBtn" class="but-change" 
			    	onclick="submitTab('editBtn','合规初步调查')"><s:if test='investigation != null'>修改</s:if><s:else>新增</s:else></a>	
		    	<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
	  		</div>
			<div class="t-but" style="display:none;" id="nextstep1">
		   		<a id="nextStepBtn" href="javascript:void(0);" class="but-change">下一步</a>
		   		<input id="nextStepURL" type="hidden" value="<%=request.getContextPath()%>/compliance/applysettlement!edit"/>
	  		</div>
		</form>
		</div>
		<div id="iframeWin" class="easyui-window" title="选择案件" modal="true" closed="true" maximizable=true
			iconCls="icon-save" style="width:1000px;height:400px;padding:2px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</body>
</html>
