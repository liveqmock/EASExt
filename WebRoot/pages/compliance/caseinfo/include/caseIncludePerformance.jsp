<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div title="业绩${status.index+1}">
	<table>
		<tr>
			<td class="t-title" width="120">
				被投诉人员业绩：
			</td>
			<td>
				<input type="text" validType="intOrFloat"
					id="ffcomplanantPerformance" name="ffcomplanantPerformance" value="${p.fcomplanantPerformance}"
					class="t-text easyui-validatebox" />
				元
			</td>
			<%--
			<td class="t-title" width="120">
				所在团队业绩：
			</td>
			<td>
				<input type="text" id="fftermPerformance" validType="intOrFloat" value="${p.ftermPerformance}"
					name="fftermPerformance" class="t-text easyui-validatebox" />
				万元
			</td>
			 --%>
		</tr>
	</table>
</div>