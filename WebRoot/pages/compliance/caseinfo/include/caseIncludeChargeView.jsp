<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="tableForm" style="min-width:100%;padding:15px 0;">
<table>
  		<tr>
  		<td class="t-title" width="120">收费人员：</td>
  		<td>
	  		<select id="ffreceiveType" name="ffreceiveType" disabled="disabled">
	  			<option value="0" <c:if test="${charge.freceiveType==0 }">selected</c:if>>请选择</option>
	  			<option value="1" <c:if test="${charge.freceiveType==1 }">selected</c:if>>员工</option>
	  			<option value="2" <c:if test="${charge.freceiveType==2 }">selected</c:if>>中介</option>
	  			<option value="3" <c:if test="${charge.freceiveType==3 }">selected</c:if>>与同行串通</option>
	  		</select>
  		</td>
  		<td class="t-title" width="120">收费理由：</td>
  		<td>
	  		<select id="fftype" name="fftype" disabled="disabled">
	  			<option value="0" <c:if test="${charge.ftype==0 }">selected</c:if>>请选择</option>
	  			<option value="1" <c:if test="${charge.ftype==1 }">selected</c:if>>好处费</option>
	  			<option value="2" <c:if test="${charge.ftype==2 }">selected</c:if>>客户包装费</option>
	  			<option value="3" <c:if test="${charge.ftype==3 }">selected</c:if>>代客户还款</option>
	  			<option value="3" <c:if test="${charge.ftype==4 }">selected</c:if>>办理外部业务</option>
	  			<option value="3" <c:if test="${charge.ftype==5 }">selected</c:if>>协办征信、流水</option>
	  		</select>
  		</td>
  		</tr>
  	
  	<tr>
  		<td class="t-title" width="120">给付方式：</td>
  		<td>
	  		<select id="ffmode" name="ffmode" disabled="disabled">
	  			<option value="0" <c:if test="${charge.fmode==0 }">selected</c:if>>请选择</option>
	  			<option value="1" <c:if test="${charge.fmode==1 }">selected</c:if>>现金</option>
	  			<option value="2" <c:if test="${charge.fmode==2 }">selected</c:if>>转账</option>
	  			<option value="3" <c:if test="${charge.fmode==3 }">selected</c:if>>从客户账户中直接支取</option>
	  		</select>
  		</td>
  		<td class="t-title">收费金额：</td>
  		<td><input disabled="disabled" type="text" id="ffamount" name="ffamount" value="${charge.famount}" validType="intOrFloat" class="t-text easyui-validatebox"/>元</td>
  	</tr>
  	
  	
  	</table>
 </div> 	