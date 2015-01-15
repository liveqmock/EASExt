<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div title="内部人员">
	<input type="hidden" name="fisinner" value='1'/>
	<input type="hidden" name="fcompanyname" value=""/>
	<input type="hidden" name="fbycompliantadd" value=""/>
	<input type="hidden" name="fisagent" value=""/>
	<input type="hidden" name="fnotagentRemark" value=""/>
	<table>
   		<tr><td class="t-title" width="100">被投诉人姓名：</td>
			<td><input type="text" name="fname" id="fname" class="t-text easyui-validatebox" validType="charLength[1,20]" missingMessage="该输入项为必输项" required="true"
				onkeyup="fun_loadCompInfoInner('fname','fcomplaintcount','fcaseInfo0','frefercase0','fext10','${requestScope.contextPath}')"/><span class="required">*</span></td>
			<td class="t-title" width="100">手机号码：</td>
			<td><input type="text" name="fmobile" value="" validType="mobiles" class="t-text easyui-validatebox"/></td>
   		</tr>
   		
   		<tr><td class="t-title">座机：</td>
			<td><input type="text" name="fofficephone" class="t-text easyui-validatebox" validType="phone"/></td>
			<td class="t-title">QQ：</td>
			<td><input type="text" name="fqq" value="" class="t-text easyui-validatebox" validType="qq"/></td>
   		</tr>
   		
   		<tr>
			<td class="t-title">邮箱：</td>
			<td><input type="text" name="femail" value="" class="t-text easyui-validatebox" validType="email"/></td>
			<td class="t-title">职位名称：</td>
			<td><input type="text" name="flevel" class="t-text"/></td>
   		</tr>
   		<tr>
			<td class="t-title">组织信息：</td>    
			<td colspan="3"><input type="text"  id="showFdeptname" onclick="openWinComp('部门信息','win','')"
				value="" readonly="readonly" class="t-text"/>
				<label  onclick="openWinComp('部门信息','win','')"><font color="blue">选择</font></label>
				<input type="hidden" name="fdeptname" id="fdeptname"/>
				<input type="hidden" name="fcityname" id="fcityname"/>
				<input type="hidden" name="fsaledepart" id="fsaledepart"/></td>
   		</tr>
   		
   		<tr><td class="t-title">入职时间：</td>
   			<td><input type="text" name="fentrytime" id="fentrytime" readonly="readonly" 
		 		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/>
   			</td>
   			<td class="t-title">上家工作单位：</td>
   			<td><input type="text" name="flastworkunit" class="t-text"/></td>
   		</tr>
   		
   		<tr>
   			<td class="t-title">学历：</td>
   			<td><input type="text" name="feducation" class="t-text"/></td>
   			<td class="t-title">推荐人：</td>
   			<td><input type="text" name="freferrer" value="" class="t-text"/></td>
   		</tr>
   		
   		<tr><td class="t-title">被投诉的次数：</td>
   			<td><input type="text" name="fcomplaintcount" id="fcomplaintcount" value="0" readonly="readonly" class="t-text"/></td>
   			<td class="t-title">涉及到的案子：
   				<input type="hidden" name="frefercase" id="frefercase0"/>
   				<input type="hidden" name="fext1" id="fext10"/>
   			</td>
   			<td id="fcaseInfo0">无</td>
   		</tr>
   	</table>
</div>
