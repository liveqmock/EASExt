<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="tableForm" style="min-width:100%;padding:15px 0;">  
	<input type="hidden" name="fisinner" value='0'/>
  	<table>
  		<tr><td class="t-title" width="110">被投诉人姓名：</td>
			<td><input type="text" value="${outPerson.fname}" disabled="disabled" class="t-text"/></td>
			<td class="t-title" width="110">手机号码：</td>
			<td><input type="text" value="${outPerson.fmobile }" disabled="disabled" class="t-text"/></td>
  		</tr>
		
  		<tr><td class="t-title">座机：</td>
			<td><input type="text" value="${outPerson.fofficephone}" disabled="disabled" class="t-text"/></td>
			<td class="t-title">QQ：</td>
			<td><input type="text" value="${outPerson.fqq }" disabled="disabled" class="t-text"/></td>
  		</tr>
  		
  		<tr><td class="t-title">邮箱：</td>
			<td><input type="text" value="${outPerson.femail }" disabled="disabled" class="t-text"/></td>
			<td class="t-title">公司名称：</td>
			<td><input type="text" disabled="disabled" value="${outPerson.fcompanyname }" class="t-text"/></td>
  		</tr>
  		
  		<tr><td class="t-title">城市：</td>
			<td><input type="text" disabled="disabled" value="${outPerson.fcityname }" class="t-text"/></td>
			<td class="t-title">被投诉人地址：</td>
			<td><input type="text" disabled="disabled" value="${outPerson.fbycompliantadd }" class="t-text"/></td>
  		</tr>
  		
  		<tr><td class="t-title">是否为中介：</td>
			<td colspan="3"><select id="fisagent" name="fisagent" disabled="disabled" onchange="zhongJieSelectFun();">
				<option value="1" <c:if test="${outPerson.fisagent==1 }">selected</c:if>>是</option>
				<option value="0" <c:if test="${outPerson.fisagent==0 }">selected</c:if>>否</option>
			</select> </td>
  		</tr>
  		
  		<c:if test="${outPerson.fisagent==1 }">
  			<tr style="display:none" id="shiFouZhongJie">
				<td class="t-title">备注：</td>
				<td colspan="3"><textarea name="fnotagentRemark" disabled="disabled" rows="3" 
					style="width:580">${outPerson.fnotagentRemark }</textarea></td>
			</tr>	
  		</c:if>
  		
  		
  		<c:if test="${outPerson.fisagent==0 }">
  			<tr id="shiFouZhongJie">
				<td class="t-title">备注：</td>
				<td colspan="3"><textarea name="fnotagentRemark" disabled="disabled" rows="3" 
					style="width:580">${outPerson.fnotagentRemark }</textarea></td>
			</tr>	
  		</c:if>
  		
	</table>
</div>  
