<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案件详情</title>
    
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
  		//添加外部员工
  		function fun_addOut(){
  		    var _len = $("#interiorEmployee tr").length;       
            $("#interiorEmployee").append("<tr id='tr"+_len+"'>"+
				"<td>被投诉人姓名:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td>手机号码:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td>座机:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td>QQ:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td>邮箱:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td>被投诉人级别:</td>"+
				"<td><input type='text' name=''></td>"+
				"<td><input type='button' name='delInteriorEmployee' value='删除' onclick=fun_del('tr"+_len+"')></td>"+
			"</tr>");            
  		}
  		//删除当前行
  		function fun_del(id){
			$("tr[id='"+ id +"']").remove();//删除当前行
  		}
  	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	    <form id="fm" method="post">
	    <div align="center">      
	    	<div class="fitem">            
	    		<label>投诉渠道:</label>
	    		<select id="isCustomer" name="isCustomer">
	    			<option value="1">电话 </option>
	    			<option value="2">投诉邮箱</option>
	    			<option value="3">合规邮箱</option>
	    			<option value="3">个人邮箱</option>
	    			<option value="3">其他</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusSourceDiv">            
	    		<label>投诉时间 :</label>            
	    		<input type="text" name="complainDate" id="complainDate"/>
	    	</div> 
	    	<div class="fitem" id="serviceTypeDiv">            
	    		<label>案件具体描述 :</label>            
	    		<textarea rows="5" cols="20" name="caseDescription"></textarea>
	    	</div> 
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>案件初步分类 :</label>    
	    		<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>欺诈伪冒类</option>
	    		    <option value='2'>违背诚信类</option>
	    		    <option value='3'>商业贿赂类</option>
  				  	<option value='2'>信息保密类</option>
  				  	<option value='3'>收费类</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>案件详细分类 :</label>    
	    		<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>欺诈伪冒类111</option>
	    		    <option value='2'>违背诚信类3333</option>
	    		    <option value='3'>商业贿赂类</option>
  				  	<option value='2'>信息保密类</option>
  				  	<option value='3'>收费类</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>书面证据 :</label>    
	    		<select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>无</option>
  				  	<option value='2'>有</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>证据类型 :</label>    
	    		<select name="cusStatus" id="cusStatus">
	    			<option>----请选择----</option>
	    		    <option value='1'>录音</option>
  				  	<option value='2'>视频</option>
  				  	<option value='2'>短信</option>
  				  	<option value='2'>资料</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>是否是内部员工:</label>    
	    		<select name="cusStatus" id="cusStatus">
	    		    <option value='1' selected>是</option>
  				  	<option value='2'>否</option>
	    		</select>  
	    	</div>
	    	<div class="fitem" id="cusStatusDiv">            
	    		<label>涉及业务端 :</label>    
	    		<select name="cusStatus" id="cusStatus">
	    			<option>----请选择----</option>
	    			<option>普惠金融</option>
	    			<option>财富管理</option>
	    			<option>职能端</option>
	    		</select>  
	    	</div>
	    	<table align="center" border="1" style="border-collapse: collapse;" id="outEmployee">
					<tr>
						<td colspan="13">内部人员</td>
					</tr>
					<tr>
						<td>被投诉人姓名:</td>
						<td><input type="text" name=""></td>
						<td>手机号码:</td>
						<td><input type="text" name=""></td>
						<td>座机:</td>
						<td><input type="text" name=""></td>
						<td>QQ:</td>
						<td><input type="text" name=""></td>
						<td>邮箱:</td>
						<td><input type="text" name=""></td>
						<td>被投诉人级别:</td>
						<td><input type="text" name=""></td>
						<td>组织信息 :</td>    
						<td><a href="javascript:void(0);" onclick="openWinNew('admin/admin!queryAllOrgadmin.action')">选择</a></td>
						<td><input type="button" value="添加" onclick="fun_addOut();"/></td>
					</tr>
				</table>
	    		<table align="center" border="1" style="border-collapse: collapse;" id="interiorEmployee">
					<tr>
						<td colspan="13">外部人员</td>
					</tr>
					<tr>
						<td>被投诉人姓名:</td>
						<td><input type="text" name=""></td>
						<td>手机号码:</td>
						<td><input type="text" name=""></td>
						<td>座机:</td>
						<td><input type="text" name=""></td>
						<td>QQ:</td>
						<td><input type="text" name=""></td>
						<td>邮箱:</td>
						<td><input type="text" name=""></td>
						<td>被投诉人级别:</td>
						<td><input type="text" name=""></td>
						<td><input type="button" value="添加" onclick="fun_addOut();"/></td>
					</tr>
				</table>
		     <div id="dlg-buttons" align="center">
		    	<a href="compliance/casList" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">重置</a>
		    </div>
		    </div>
	    </form>
    </div>
    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
