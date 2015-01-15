<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div title="外部人员${status.index+1}">  
	<input type="hidden" name="fisinner" value='0'/>
	<table>
		<tr><td class="t-title" width="100">被投诉人姓名：</td>
			<td><input type="text" name="fname" id="fname" value="${outPerson.fname}" 
				class="t-text easyui-validatebox" validType="charLength[1,20]" missingMessage="该输入项为必输项" required="true"/><span class="required">*</span></td>
			<td class="t-title" width="100">手机号码：</td>
			<td><input type="text" name="fmobile" value="${outPerson.fmobile }" validType="mobiles" class="t-text easyui-validatebox"/></td>
		</tr>
		
		<tr><td class="t-title">座机：</td>
			<td><input type="text" name="fofficephone" value="${outPerson.fofficephone}" 
				class="t-text easyui-validatebox" validType="phone"/></td>
			<td class="t-title">QQ：</td>
			<td><input type="text" name="fqq" value="${outPerson.fqq }" class="t-text easyui-validatebox" validType="qq"/></td>
		</tr>
		
		<tr><td class="t-title">邮箱：</td>
			<td><input type="text" name="femail" value="${outPerson.femail }" class="t-text easyui-validatebox" validType="email"/></td>
			<td class="t-title">公司名称：</td>
			<td><input type="text" name="fcompanyname" value="${outPerson.fcompanyname }" class="t-text"/></td>
		</tr>
		
		<tr><td class="t-title">城市：</td>
			<td><input type="text" name="fcityname" value="${outPerson.fcityname }" class="t-text"/></td>
			<td class="t-title">被投诉人地址：</td>
			<td><input type="text" name="fbycompliantadd" value="${outPerson.fbycompliantadd }" class="t-text"/></td>
		</tr>
		
		<tr><td class="t-title">是否为中介：</td>
			<td colspan="3"><select id="fisagent${status.index+1}" name="fisagent" onchange="zhongJieSelectFun(${status.index+1});">
				<option value="1" <c:if test="${outPerson.fisagent==1 }">selected</c:if>>是</option>
				<option value="0" <c:if test="${outPerson.fisagent==0 }">selected</c:if>>否</option>
			</select></td>
		</tr>
		
		<c:if test="${outPerson.fisagent==1 }">
			<tr style="display: none" id="shiFouZhongJie${status.index+1}">
				<td class="t-title">备注：</td>
				<td colspan="3"><textarea name="fnotagentRemark" rows="3" style="width:580;">${outPerson.fnotagentRemark }</textarea></td>
			</tr>	
		</c:if>
		
		<c:if test="${outPerson.fisagent==0 }">
			<tr id="shiFouZhongJie${status.index+1}">
				<td class="t-title">备注：</td>
				<td colspan="3"><textarea name="fnotagentRemark" rows="3" style="width:580;">${outPerson.fnotagentRemark }</textarea></td>
			</tr>	
		</c:if>
		
		
 	</table>
</div>  
