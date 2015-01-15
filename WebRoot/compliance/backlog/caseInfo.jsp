<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案件审批</title>
    
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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<style type="text/css">
		table td {
			font-size: 12px;
		}
	</style>
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
    <form id="fm" method="post">
    	<table width="100%" border="0">
    		<tr><td><label>投诉渠道:</label></td>
	    		<td><select id="isCustomer" name="isCustomer">
	    			<option value="1">电话 </option>
	    			<option value="2">投诉邮箱</option>
	    			<option value="3">合规邮箱</option>
	    			<option value="3">个人邮箱</option>
	    			<option value="3">其他</option>
	    		</select></td>
	    		<td><label>投诉时间 :</label></td>            
	    		<td><input type="text" name="complainDate" id="complainDate"/></td>
	    		<td></td>
	    		<td></td>
	    	</tr>
	    	<tr>
	    		<td><label>案件具体描述 :</label></td>            
	    		<td colspan="5"><textarea rows="4" style="width: 100%" name="caseDescription"></textarea></td>
	    	</tr>
	    	<tr>  
	    		<td><label>案件初步分类 :</label></td>    
	    		<td><select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>欺诈伪冒类</option>
	    		    <option value='2'>违背诚信类</option>
	    		    <option value='3'>商业贿赂类</option>
  				  	<option value='2'>信息保密类</option>
  				  	<option value='3'>收费类</option>
	    		</select></td>
	    		<td><label>案件详细分类 :</label></td>   
	    		<td><select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>欺诈伪冒类111</option>
	    		    <option value='2'>违背诚信类3333</option>
	    		    <option value='3'>商业贿赂类</option>
  				  	<option value='2'>信息保密类</option>
  				  	<option value='3'>收费类</option>
	    		</select></td>  
	    		<td><label>书面证据 :</label></td>    
	    		<td><select name="cusStatus" id="cusStatus" onchange="fun_cusStatus();">
	    			<option>----请选择----</option>
	    		    <option value='1'>无</option>
  				  	<option value='2'>有</option>
	    		</select></td>  
	    	</tr>
			<tr>
				<td><label>证据类型 :</label></td>
	    		<td><select name="cusStatus" id="cusStatus">
	    			<option>----请选择----</option>
	    		    <option value='1'>录音</option>
  				  	<option value='2'>视频</option>
  				  	<option value='2'>短信</option>
  				  	<option value='2'>资料</option>
	    		</select></td>
	    		<td><label>是否是内部员工:</label></td>   
	    		<td><select name="cusStatus" id="cusStatus">
	    		    <option value='1' selected>是</option>
  				  	<option value='2'>否</option>
	    		</select></td>
	    		<td><label>涉及业务端 :</label></td>    
	    		<td><select name="cusStatus" id="cusStatus">
	    			<option>----请选择----</option>
	    			<option>普惠金融</option>
	    			<option>财富管理</option>
	    			<option>职能端</option>
	    		</select></td>
	    		<td></td>
	    		<td></td>  
			</tr>
	    	<tr><td colspan="6" align="center"><font color="red">内部员工</font></td></tr>
	    	<tr>
	    		<td><label>被投诉人姓名:</label></td>    
	    		<td><input type="text" name=""></td>
	    		<td><label>手机号码:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><label>座机:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    	</tr>
	    	<tr>
	    		<td><label>QQ:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><label>邮箱:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><label>被投诉人级别:</label></td>    
	    		<td><input type="text" name=""></td>
	    	</tr>
	    	<tr>
	    		<td><label>组织信息 :</label></td>    
	    		<td><input type="button" value="动态添加1"/></td>
	    	</tr>
	    	<tr><td colspan="6" align="center"><font color="red">外部人员</font></td></tr>
	    	<tr>
	    		<td><label>被投诉人姓名:</label></td>    
	    		<td><input type="text" name=""></td>
	    		<td><label>手机号码:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><label>座机:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    	</tr>
	    	<tr>
	    		<td><label>QQ:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><label>邮箱:</label></td>            
	    		<td><input name="email" class="easyui-validatebox"></td>        
	    		<td><input type="button" value="动态添加2"/></td>
	    	</tr>
    		<tr>
	    		<td><label>是否同意:</label></td>    
	    		<td><input type="radio" value="0" name="ifagree">是<input type="radio" value="0" name="ifagree">否</td>
			</tr>
			<tr>
				<td><label>审批意见：</label></td>
				<td colspan="5"><textarea rows="4" style="width: 100%"></textarea></td>
			</tr>	
			<tr>
		    	<td colspan="6" align="center"><a href="compliance/backlog/backlogList.jsp" class="easyui-linkbutton" iconCls="icon-ok">审批通过</a>
		       	<a href="compliance/backlog/backlogList.jsp" class="easyui-linkbutton" iconCls="icon-ok">退回</a></td>
			</tr>
	    </table>
    </form>
    <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
  </body>
</html>
