<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.creditease.eas.compliance.bean.Person"%>
<div class="tableForm" style="min-width:100%;padding:15px 0;">
	<input type="hidden" name="fisinner" value='1'/>
  	<table>
  		<tr><td class="t-title" width="110">被投诉人姓名：</td>
			<td><input type="text" disabled="disabled" value="${person.fname}" class="t-text"/></td>
			<td class="t-title" width="110">手机号码：</td>
			<td><input type="text" disabled="disabled" value="${person.fmobile }" class="t-text"/></td>
  		</tr>
  		<tr>
			<td class="t-title" width="110">座机：</td>
			<td><input type="text" disabled="disabled" value="${person.fofficephone}" class="t-text"/></td>
  			<td class="t-title">QQ：</td>
			<td><input type="text" value="${person.fqq }" disabled="disabled" class="t-text"/></td>
  		</tr>
  		<tr>
			<td class="t-title">邮箱：</td>
			<td><input type="text" value="${person.femail }" disabled="disabled" class="t-text"/></td>
			<td class="t-title">职位名称：</td>
			<td><input type="text" disabled="disabled" value="${person.flevel }" class="t-text"/></td>
  		</tr>
  		<tr><td class="t-title">组织信息：</td>    
			<td colspan="3"><input type="text" disabled="disabled" class="t-text"
				value="${person.fdeptname}-${person.fcityname}-${person.fsaledepart }" disabled="disabled"/></td>
  		</tr>
  		<tr>
  			<td class="t-title">入职时间：</td>
  			<td><input type="text" disabled="disabled" value="${person.fentrytime }" disabled="disabled" class="t-text Wdate"/></td>
  			<td class="t-title">上家工作单位：</td>
  			<td><input type="text" disabled="disabled" value="${person.flastworkunit}" class="t-text"/></td>
  		</tr>
  		<tr>
  			<td class="t-title">学历：</td>
  			<td><input type="text" disabled="disabled" value="${person.feducation}" class="t-text"/></td>
  			<td class="t-title">推荐人：</td>
  			<td><input type="text" disabled="disabled" value="${person.freferrer }" class="t-text"/></td>
  		</tr>
  		<tr>
  			<td class="t-title">被投诉的次数：</td>
  			<td><input type="text" disabled="disabled" value="${person.fcomplaintcount }" class="t-text"/></td>
  			<td class="t-title">涉及到的案子：
  				<input type="hidden"  disabled="disabled" name="frefercase" id="frefercase${status.index}" value="${person.frefercase }"/>
  				<input type="hidden" name="fext1" disabled="disabled" id="fext1${status.index}" value="${person.fext1 }"/></td>
  			<td id="fcaseInfo${status.index}">
  			  	<c:choose>
					<c:when test="${!empty person.frefercase}">
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
						</c:forTokens>	
					</c:when>	
					<c:otherwise>无</c:otherwise>		
				</c:choose>
			</td>
		</tr>
  	</table>
</div>
