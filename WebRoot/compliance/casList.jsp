<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案件管理</title>
    
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
  	<script type="text/javascript">
		function uploadFun(){
			$('#xxx').dialog({  
			    title: '上传',  
			    width: 400,  
			    height: 90,  
			    closed: false,  
			    cache: false,  
			    modal: true,
			    content:'</br></br><input type=\"fil\e" id="\file\"/>'+
			    		'<input type=\"button\" id=\"upload\" value=\"上传\" onclick=\"chooseFile()\">'  
			});
			$('#xxx').window('open');
		}
		function chooseFile(){
			$.messager.progress();
			setTimeout(function(){
				$('#xxx').window('close');
				$.messager.progress('close');
				$.messager.alert('提示','上传成功！','info');
			},3410);
		}
  	</script>
  </head>
  <body>
		<table id="abc" width="100%" align="left" onkeyup="bindQuery();">
			<tr>
				<td>投诉渠道：<input type="text" id="orgname" value=""></td>
				<td>投诉时间从：<input type="text" id="city" value="">到<input type="text" id="city" value=""></td>
				<td>被投诉人：<input type="text" id="officeadd" value=""></td>
			</tr>
			<tr>
				<td>投诉涉及部门：<input type="text" id="orgname" value=""></td>
				<td>投诉涉及城市：<input type="text" id="orgname" value=""></td>
				<td>投诉涉及营业部：<input type="text" id="orgname" value=""></td>
			</tr>
			<tr>
				<td>投诉人姓名：<input type="text" id="orgname" value=""></td>
				<td>平台客户姓名：<input type="text" id="orgname" value=""></td>
				<td>案件初步分类：
					<select name="nextScheme" id="nextScheme">
		    			<option>----请选择----</option>
		    		    <option value='1'>欺诈伪冒类</option>
		    		    <option value='2'>欺诈伪冒类</option>
		    		    <option value='3'>商业贿赂类</option>
		    		    <option value='3'>信息保密类</option>
	  				  	<option value='4'>服务类</option>
	  				  	<option value='5'>其他</option>
		    		</select>  
	    	  </td>
			</tr>
			<tr>
				<td>
					<a href="javascript:void(0)" id="queryBtn" class="easyui-linkbutton">查询</a>
					<a href="javascript:void(0)" id="resetBtn" class="easyui-linkbutton">清空</a>
				</td>
		   </tr>
		</table>
		  <p>&nbsp;</p>
	  	<table id="dg" title="案件列表"  class="easyui-datagrid" style="height:400px;" width="100%"
	  	toolbar="#toolbar"        rownumbers="true" fitColumns="true" singleSelect="true">
		  	<thead>
		  		<tr>
		  			<th field="firstname" width="50">投诉渠道</th>
		  			<th field="lastname" width="50">投诉时间</th>
		  			<th field="phone" width="50">案件初步分类</th>
		  			 <th field="email" width="50">被投诉人</th>
		  			  <th field="serviceType" width="50">服务类型</th>
		  		     <th field="cusStatus" width="50">客户状态</th>
		  			  <th field="caseStatus" width="50">案件状态</th>
		  		    <th field="ifHave" width="50">是否有书面证据</th>
		  		    <th field="search" width="40">查看案件信息</th>
		  		     <th field="editCom" width="40">更新投诉信息</th>
		  		    <th field="editCas" width="40">修改案件信息</th>
		  		    <th field="update" width="40">上传证据</th>
		  		</tr>
		  	</thead>
	  		<tr>
	  			<td width="50">投诉邮箱11</th>
	  			<td width="50">2013-09-04</th>
	  			<td width="50">欺诈伪冒类</th>
	  		       <td width="50">李四</td>
	  		    <td width="50">普惠金融</td>
	  		    <td width="50">贷款品种</td>
	  		      <td width="50">已审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaintv1.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint2.jsp');">更新投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">更新案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="uploadFun()">上传附件</a>
	  		    </td>
	  		</tr>
	  		<tr>
	  			<td width="50">投诉邮箱12</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		       <td width="50">李四</td>
	  		      <td width="50">普惠金融</td>
	  		    <td width="50">贷款品种</td>
	  		    <td width="50">已审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">更新投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">更新案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="uploadFun()">上传附件</a>
	  		    </td>
	  		</tr>
	  		 <tr ondblclick="fun_choose();">
	  			<td width="50">电话</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">张三</td>
	  		     <td width="50">普惠金融</td>
	  		    <td width="50">贷款品种</td>
	  		    <td width="50">未审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">更新投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">更新案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="uploadFun()">上传附件</a>
	  		    </td>
	  		</tr>
	  		<tr>
	  			<td width="50">投诉邮箱</td>
	  			<td width="50">2013-09-04</td>
	  			<td width="50">欺诈伪冒类</td>
	  		    <td width="50">李四</td>
	  		    <td width="50">普惠金融</td>
	  		    <td width="50">完成面审</td>
	  		    <td width="50">未审批</td>
	  		    <td width="50">有</td>
	  		    <td width="50"><a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">查看</a></td>
	  		    <td width="50">
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp');">更新投诉信息</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="openWinNew('compliance/addCase.jsp');">更新案件详情</a>
	  		    </td>
	  		    <td>	
	  		    	<a href="javascript:void(0);" onclick="uploadFun()">上传附件</a>
	  		    </td>
	  		</tr>
		 </table>
		  <div id="toolbar">
<!--		  		<a href="javascript:void(0);" onclick="openWinNew('compliance/addComplaint.jsp')" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加案件</a>-->
		  		<a href="compliance/addComplaint.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加案件</a>
<!--		  		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改案件</a>-->
<!--		  		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除案件</a>-->
	     </div>
	   <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:1000px;height:500px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		<div id="xxx"></div>  
		
  </body>
</html>
