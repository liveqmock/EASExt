<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>违规类型统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
		/**
		 * 新的方式
		 * @param url
		 * @return
		 */
		function openWinNew(url){
			$("#iframeSource").attr("src",url);
			$('#iframeWin').window('open');
		}
	</script>
  </head>
  <body>
		<table id="queryTab" width="100%" align="left">
			<tr>
				<td>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市：<input type="text" id="endTime" value=""></td>
				<td>部门：<input type="text" id="endTime" value=""></td>
			</tr>
			<tr>
<!--				<td>违规类型：-->
<!--					<select name="comtype" style="width:150px;">-->
<!--						<option>---请选择----</option>-->
<!--						<option>欺诈伪冒类</option>-->
<!--						<option>违背诚信类</option>-->
<!--						<option>商业贿赂类</option>-->
<!--						<option>信息保密类</option>-->
<!--						<option>服务类</option>-->
<!--						<option>其他</option>-->
<!--					</select>-->
<!--				</td>-->
				<td>投诉时间从：<input type="text" id="city" value="">到<input type="text" id="city" value=""></td>
				<td align="center">
					<a href="javascript:void(0)" id="queryBtn" class="easyui-linkbutton">查询</a>
				</td>
			</tr>
		</table>
		  <p>&nbsp;</p>
	  	<table id="dg" title="统计信息" class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
		  	<thead>
		  		<tr>
		  			<th field="cityname" width="50">城市</th>
		  			<th field="depname" width="50">部门</th>
		  			<th field="depname1" width="50">欺诈伪冒类</th>
		  			<th field="depname2" width="50">违背诚信类</th>
		  			<th field="depname3" width="50">商业贿赂类</th>
		  			<th field="depname4" width="50">信息保密类</th>
		  			<th field="depname5" width="50">服务类</th>
		  			<th field="depname6" width="50">其他</th>
<!--		  			<th field="depname7" width="50">投诉时间</th>-->
					<th field="depname8" width="50">总数</th>
		  		</tr>
		  	</thead>
	  		<tr>
	  			<td width="50">北京</td>
	  			<td width="50">个贷</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">48</a>
	  			</td>
<!--	  			<td width="50">-->
<!--					2013-9-23-->
<!--	  			</td>-->
	  		</tr>
	  		<tr>
	  			<td width="50">上海</td>
	  			<td width="50">个贷</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">6</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">8</a>
	  			</td>
	  			<td width="50">
	  				<a href="javascript:void(0);" onclick="openWinNew('compliance/statistics/caseDetail.jsp');">46</a>
	  			</td>
<!--	  			<td width="50">-->
<!--					2013-9-22-->
<!--	  			</td>-->
	  		</tr>
		 </table>
	   <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:460px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>