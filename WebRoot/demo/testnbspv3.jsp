<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href=<%=basePath%>>
    
    <title>My JSP 'testnbsp.jsp' starting page</title>
    
	<meta http-equiv=pragma content=no-cache>
	<meta http-equiv=cache-control content=no-cache>
	<meta http-equiv=expires content=0>    
	<meta http-equiv=keywords content=keyword1,keyword2,keyword3>
	<meta http-equiv=description content=This is my page>
	<!--
	<link rel=stylesheet type=text/css href=styles.css>
	-->
  </head>
  <body>
		<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>
			各位好！ 
			根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。
			为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。 
			以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：
			<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'> 
				<tr bgcolor='#0066cc' align='center'> 
					<td nowrap><font color='white' size='2'>员工编号</font></td> 
					<td nowrap><font color='white' size='2'>身份证姓名</font></td> 
					<td nowrap height='20'><font color='white' size='2'>一级部门</font></td> 
					<td nowrap height='20'><font color='white' size='2'>二级部门</font></td> 
					<td nowrap height='20'><font color='white' size='2'>三级部门</font></td> 
					<td nowrap height='20'><font color='white' size='2'>四级部门</font></td> 
					<td nowrap height='20'><font color='white' size='2'>五级部门</font></td> 
					<td nowrap><font color='white' size='2'>城市</font></td> 
					<td nowrap><font color='white' size='2'>岗位</font></td> 
					<td nowrap><font color='white' size='2'>任职资格</font></td> 
					<td nowrap><font color='white' size='2'>人员状态</font></td> 
					<td nowrap><font color='white' size='2'>入职日期</font></td> 
					<td nowrap><font color='white' size='2'>预计转正日期</font></td> 
					<td nowrap bgcolor='orange'><font color='white' size='2'>预警类型</font></td> 
				</tr>
			</table>
			附：员工转正审批流程： 
			1、主管级（含）以下员工 
			 由部门根据员工日常工作表现进行评估、审批，并根据审批结果进行相关手续办理： 
			 ▉如部门同意员工转正： 
			 a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审批； 
			 b.部门完成审批手续后将《新员工转正评估表》提交总部人力资源与行政部备案（外埠地区将表单以扫描件形式予以呈报）。 
				▉如部门不同意员工转正 
			 a.请部门务必于试用期到期前与员工协商一致、依据公司相关规定办理正常离职手续。 
			2、经理级以上员工 
			 由部门根据员工实际工作表现进行评估、审核，并根据审核结果进行相关手续办理： 
			 ▉如部门同意员工转正： 
			 a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审核； 
			 b.部门完成审核手续后将《新员工转正评估表》提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）； 
			 c.如该转正申请未通过审批，总部人力资源与行政部将予以邮件反馈。 
			 ▉如部门不同意员工转正 
			 a.部门将不同意员工转正的意见及相关材料提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）； 
			 b.根据员工实际情况进行相关手续办理。 
			感谢您对人力资源与行政部工作的大力支持！谢谢。
			------------------------------------------------------------------
			传真： 010- 5738 2188
			网站： www.CreditEase.cn
			地址： 北京市朝阳区建国路88号SOHO现代城C座16层
			邮编： 100022
		</div>
  </body>
</html>
