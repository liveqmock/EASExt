<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="tableForm" style="min-width:100%;padding:15px 0;">
	<table>
		<tr><td class="t-title" width="100">部门名称：</td>
			<td><input type="text" name="dfdeptname" id="dfdeptname" 
				value="${dept.fdeptname}" disabled="disabled" class="t-text easyui-validatebox"/></td>
			<td class="t-title" width="100">城市名称：</td>
			<td><input type="text" name="dfcityname"  
				value="${dept.fcityname}"  disabled="disabled" class="t-text easyui-validatebox"/></td>
			<td class="t-title" width="100">营业部名称：</td>
			<td><input type="text" name="dfsaledepart" 
				value="${dept.fsaledepart}" disabled="disabled" class="t-text easyui-validatebox"></td>
		</tr>
	</table>
</div>
