<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div title="收费1">
	<table>
  		<tr>
  		<td class="t-title" width="120">收费人员：</td>
  		<td>
	  		<select id="ffreceiveType" name="ffreceiveType">
	  			<option value="0">请选择 </option>
	  			<option value="1">员工</option>
	  			<option value="2">中介</option>
	  			<option value="3">与同行串通</option>
	  		</select>
  		</td>
  		<td class="t-title" width="120">收费理由：</td>
  		<td>
	  		<select id="fftype" name="fftype">
	  			<option value="0">请选择 </option>
	  			<option value="1">好处费</option>
	  			<option value="2">客户包装费</option>
	  			<option value="3">代客户还款</option>
	  			<option value="4">办理外部业务</option>
	  			<option value="5">协办征信、流水</option>
	  		</select>
  		</td>
  		</tr>
  	
  	<tr>
  		<td class="t-title" width="120">给付方式：</td>
  		<td>
	  		<select id="ffmode" name="ffmode">
	  			<option value="0">请选择 </option>
	  			<option value="1">现金</option>
	  			<option value="2">转账</option>
	  			<option value="3">从客户账户中直接支取</option>
	  		</select>
  		</td>
  		
  		<td class="t-title">收费金额：</td>
  		<td><input type="text" validType="intOrFloat" id="ffamount" name="ffamount" class="t-text easyui-validatebox"/>元</td>
  	</tr>
  	
  	
  	</table>
</div>	
