<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div title="外部人员">
   	<input type="hidden" name="fisinner" value='0'/>
   	<table>
   		<tr><td class="t-title" width="100">被投诉人姓名：</td>
			<td><input type="text" name="fname" id="fname" validType="charLength[1,20]" missingMessage="该输入项为必输项" required="true" value="" class="t-text easyui-validatebox"/><span class="required">*</span></td>
			<td class="t-title" width="100">手机号码：</td>
			<td><input type="text" name="fmobile" value="" validType="mobiles" class="t-text easyui-validatebox"/></td>
   		</tr>
   		
   		<tr><td class="t-title">座机：</td>
			<td><input type="text" name="fofficephone" value="" class="t-text easyui-validatebox" validType="phone"/></td>
			<td class="t-title">QQ：</td>
			<td><input type="text" name="fqq" value="" class="t-text easyui-validatebox" validType="qq"/></td>
   		</tr>
   		
   		<tr><td class="t-title">邮箱：</td>
			<td><input type="text" name="femail" value="" class="t-text easyui-validatebox" validType="email"/></td>
			<td class="t-title">公司名称：</td>
			<td><input type="text" name="fcompanyname" value="" class="t-text"/></td>
   		</tr>
   		
   		<tr><td class="t-title">城市：</td>
			<td><input type="text" name="fcityname" value="" class="t-text"/></td>
			<td class="t-title">被投诉人地址：</td>
			<td><input type="text" name="fbycompliantadd" value="" class="t-text"/></td>
   		</tr>
   		
   		<tr><td class="t-title">是否为中介：</td>
			<td colspan="3"><select id="fisagent" name="fisagent" onchange="zhongJieSelectFun();">
				<option value="1">是</option>
				<option value="0">否</option>
			</select> </td>
   		</tr>
   		<tr style="display: none" id="shiFouZhongJie">
			<td class="t-title">备注：</td>
			<td colspan="3"><textarea name="fnotagentRemark" rows="3" style="width:580;"></textarea></td>
		</tr>
	</table>
</div>  
