<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>详细信息</title>
    
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
	  	<table id="dg" title="统计信息" class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
		  	<thead>
		  		<tr>
		  			<th field="firstname" width="50">部门名称</th>
		  			<th field="lastname" width="50">案件数量</th>
		  		</tr>
		  	</thead>
	  		<tr>
	  			<td width="50">合规部门</th>
	  			<td width="50"><a href="caseDetail.jsp">8</a></th>
	  		</tr>
	  		<tr>
	  			<td width="50">投诉邮箱12</th>
	  			<td width="50">2013-09-04</th>
	  			<td width="50">欺诈伪冒类</th>
	  		       <td width="50">李四</td>
	  		    <td width="50">已审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">修改投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">修改案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">上传附件</a>
	  		    </td>
	  		</tr>
	  		 <tr ondblclick="fun_choose();">
	  			<td width="50">电话</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张三</td>
	  		    <td width="50">未审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">修改投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">修改案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">上传附件</a>
	  		    </td>
	  		</tr>
	  		<tr>
	  			<td width="50">投诉邮箱</th>
	  			<td width="50">2013-09-04</th>
	  			<td width="50">欺诈伪冒类</th>
	  		       <td width="50">李四</td>
	  		    <td width="50">未审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">修改投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">修改案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">上传附件</a>
	  		    </td>
	  		</tr>
		 </table>
	   <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
