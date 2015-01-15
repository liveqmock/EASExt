<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div title="被投诉部门">
	<table>
		<tr><td class="t-title" width="60">部门名称：</td>
			<td><input type="text" id="dfdeptname" name="dfdeptname" id="dfdeptname" value="${dept.fdeptname}" class="t-text easyui-validatebox">
			<span class="required">*</span></td>
			<td class="t-title" width="60">城市名称：</td>
			<td><input type="text" id="dfcityname" name="dfcityname"  value="${dept.fcityname}"  class="t-text easyui-validatebox" ></td>
			<td class="t-title" width="80">营业部名称：</td>
			<td><input type="text" id="dfsaledepart" name="dfsaledepart" value="${dept.fsaledepart}" class="t-text easyui-validatebox"></td>
  		</tr>
	</table>
</div>
