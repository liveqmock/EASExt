<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.creditease.eas.compliance.bean.Person"%>
<div title="内部人员${status.index+1}">
	<input type="hidden" name="fisinner" value='1'/>
	<input type="hidden" name="fcompanyname" value=""/>
	<input type="hidden" name="fbycompliantadd" value=""/>
	<input type="hidden" name="fisagent" value=""/>
	<input type="hidden" name="fnotagentRemark" value=""/>
	<table>
		<tr><td class="t-title" width="100">被投诉人姓名：</td>
			<td><input type="text" name="fname" id="fname${status.index}" validType="charLength[1,20]" missingMessage="该输入项为必输项" required="true" value="${person.fname}" class="t-text easyui-validatebox" 
				onkeyup="fun_loadCompInfoInner('fname${status.index}','fcomplaintcount${status.index}','fcaseInfo${status.index}','frefercase${status.index}','fext1${status.index}','${requestScope.contextPath}');"/><span class="required">*</span></td>
			<td class="t-title" width="100">手机号码：</td>
			<td><input type="text" name="fmobile"  value="${person.fmobile }" validType="mobiles" class="t-text easyui-validatebox" ></td>
		</tr>
		
		<tr><td class="t-title">座机：</td>
			<td><input type="text" name="fofficephone" value="${person.fofficephone}" class="t-text easyui-validatebox" validType="phone"></td>
			<td class="t-title">QQ：</td>
			<td><input type="text" name="fqq" value="${person.fqq }" class="t-text easyui-validatebox" validType="qq"></td>
		</tr>
		
		<tr><td class="t-title">邮箱：</td>
			<td><input type="text" name="femail" value="${person.femail }" class="t-text easyui-validatebox" validType="email"></td>
			<td class="t-title">职位名称：</td>
			<td><input type="text" name="flevel" value="${person.flevel }" class="t-text easyui-validatebox"></td>
		</tr>
		
		<tr><td class="t-title">组织信息：</td>    
			<td colspan="3"><input type="text" id="showFdeptname${status.index+1}" onclick="openWinComp('部门信息','win','${status.index+1}')"
				value="${person.fdeptname}-${person.fcityname}-${person.fsaledepart }" readonly="readonly" class="t-text"/>
			<label onclick="openWinComp('部门信息','win','${status.index+1}')"><font color="blue">选择</font></label>
			<input type="hidden" name="fdeptname" id="fdeptname${status.index+1}" value="${person.fdeptname }"/>
			<input type="hidden" name="fcityname" id="fcityname${status.index+1}" value="${person.fcityname }"/>
			<input type="hidden" name="fsaledepart" id="fsaledepart${status.index+1}" value="${person.fsaledepart}"/>
		</tr>
		
		<tr><td class="t-title">入职时间：</td>
			<td><input type="text" name="fentrytime" id="fentrytime" value="${person.fentrytime }" readonly="readonly" 
 				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="t-text Wdate"/></td>
			<td class="t-title">上家工作单位：</td>
			<td><input type="text" name="flastworkunit" value="${person.flastworkunit}" class="t-text"/></td>
		</tr>
		
		<tr><td class="t-title">学历：</td>
			<td><input type="text" name="feducation" value="${person.feducation}" class="t-text"/></td>
			<td class="t-title">推荐人：</td>
			<td><input type="text" name="freferrer" value="${person.freferrer }" class="t-text"/></td>
		</tr>
		
		<tr><td class="t-title">被投诉的次数：</td>
			<td><input type="text" name="fcomplaintcount" id="fcomplaintcount${status.index}" 
				value="${person.fcomplaintcount }" readonly="readonly" class="t-text"/></td>
			<td class="t-title">涉及到的案子：
				<input type="hidden" name="frefercase" id="frefercase${status.index}" value="${person.frefercase }"/>
				<input type="hidden" name="fext1" id="fext1${status.index}" value="${person.fext1 }"/></td>
			<td id="fcaseInfo${status.index}">
			  	<c:choose><c:when test="${!empty person.frefercase}">
					<c:forTokens var="frefercase" items="${person.frefercase}" delims="," varStatus="statuss1">
						<c:forTokens var="fext1" items="${person.fext1}" delims="," varStatus="statuss2">
								<c:choose>
									<c:when test="${statuss1.index == statuss2.index && statuss2.index==0 }">
										<a href='javascript:void(0)' onclick="showModalDialogCaseInfo('${requestScope.contextPath}/caseinfo/caseinfo!viewCaseInfo?fid=${fext1 }')">${frefercase}</a>
									</c:when>
									<c:when test="${statuss1.index == statuss2.index}">
										|<a href='javascript:void(0)' onclick="showModalDialogCaseInfo('${requestScope.contextPath}/caseinfo/caseinfo!viewCaseInfo?fid=${fext1 }')">${frefercase}</a>
									</c:when>
								</c:choose>
						</c:forTokens>
					</c:forTokens></c:when>	
					<c:otherwise>无</c:otherwise>		
			</c:choose>
			</td>
		</tr>
	</table>
</div>
