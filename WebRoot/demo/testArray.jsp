<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案件详情</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
  <script type="text/javascript">
		$(function() {
			//加载投诉渠道
			loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch');
			//投诉渠道是个人邮箱时，需要绑定事件
			//加载证据类型
			loadSelectInfo('fevidencetypeid','<%=basePath%>/caseinfo/caseinfo!findEvidenceType');
			//加载服务类型
			loadSelectInfo('fbusportid','<%=basePath%>/caseinfo/caseinfo!findServicetype');
			//初始化投诉时间
			$('#fcompTime').val(iniDate());
			//加载案件初步分类：动态的生成table
			var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			var $tr=$("<tr></tr>");
			$tr.appendTo($tbfinicasetype);
			for(var i=0;i<json.length;i++){
				var $td = '';
				if(json[i].value == '其他'){
					$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].key + "' onclick=fun_otherCheck('finicasetypeOther','xiangXiFengLeiOtherDiv')>" + json[i].value + "</td>");
				}else{
					$td = $("<td><input type='checkbox' name='finicasetype' value='" + json[i].key + "' onclick=fun_fdetailCaseType('"+json[i].key+"','fdetailCaseType"+(i+1)+"')>" + json[i].value + "</td>");
				}
				$td.appendTo($tr);
			}
		});
		//加载案件详细分类的信息
		function fun_fdetailCaseType(finicasetype,fdetailCaseType){
			var $fcussourceid = $("#"+fdetailCaseType);
			loadSelectInfo(fdetailCaseType,"<%=basePath%>/caseinfo/caseinfo!findDetailCaseType?finicasetype="+finicasetype);
		}
		//查询部门信息:选择部门信息时用
		function setAdmin(lastCostCenter,costCenterNum,fid){
<%--			$("#lastCostCenter").val(lastCostCenter);--%>
<%--			$("#costCenterNum").val(costCenterNum);--%>
<%--			$("#fid").val(fid);--%>
		}
	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	   <form method="post" action="<%=basePath%>caseinfo/caseinfo!addCaseInfo" id="editForm">
<%--			  <input type="text" name="personInfo.fname">--%>
<%--			 <input type="text" name="personInfo.fname" >--%>
	  		<input type="text" name="personInfo.fname">
			 <input type="text" name="personInfo.fmobile" >
		     <div id="dlg-buttons" align="center">
		    	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="dosubmitCompliance('editForm');">保存</a>
		    </div>
		    <input type="hidden" id="winURL" value="pages/compliance/caseinfo/selectAdmin.jsp"/>
	    </form>
    </div>
     <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
	  iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
	<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
