<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div title="被投诉部门${status.index+1}">
	<table>
  		<tr><td class="t-title" width="60">部门名称：</td>
			<td><input type="text" name="dfdeptname" id="dfdeptname" value="${dept.fdeptname}" 
			required="true" validType="charLength[1,20]" missingMessage="该输入项为必输项" class="t-text easyui-validatebox"/>
				<span class="required">*</span>
			</td>
			<td class="t-title" width="60">城市名称：</td>
			<td><input type="text" name="dfcityname"  value="${dept.fcityname}"  class="t-text easyui-validatebox"/></td>
			<td class="t-title" width="80">营业部名称：</td>
			<td><input type="text" name="dfsaledepart" value="${dept.fsaledepart}" class="t-text easyui-validatebox"/></td>
  		</tr>
  	</table>
</div>
