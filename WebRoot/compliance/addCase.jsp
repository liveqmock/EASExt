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
  		function otherCheck(){
			if($("#otherCheckbox").attr("checked")=='checked') $("#xiangXiFengLeiOther").css({display:"block"});
			else $("#xiangXiFengLeiOther").css({display:"none"});
  		}
  		//添加外部员工
  		function fun_addOut(){
            $("#aa").css({style:"height:3000px;"})
            .accordion('add', {
            	title: '内部人员1',
            	content: "<div title=\"内部人员\" data-options=\"iconCls:'icon-save'\" style=\"overflow:auto;padding:10px;\">"+
					    	"<table border=\"0\" width=\"100%\">"+
				    		"<tr>"+
								"<td width=\"10%\">被投诉人姓名:</td>"+
								"<td width=\"23%\"><input type=\"text\" name=\"\"></td>"+
								"<td width=\"10%\">手机号码:</td>"+
								"<td width=\"23%\"><input type=\"text\" name=\"\"></td>"+
								"<td width=\"10%\">座机:</td>"+
								"<td width=\"23%\"><input type=\"text\" name=\"\"></td>"+
				    		"</tr>"+
				    		"<tr>"+
								"<td>QQ:</td>"+
								"<td><input type=\"text\" name=\"\"></td>"+
								"<td>邮箱:</td>"+
								"<td><input type=\"text\" name=\"\"></td>"+
								"<td>级别:</td>"+
								"<td><input type=\"text\" name=\"\"></td>"+
				    		"</tr>"+
							"<tr>"+
								"<td>组织信息 :</td>"+ 
								"<td><input type=\"text\" name=\"\" onclick=\"openWinNew('admin/admin!queryAllOrgadmin.action')\"/></td>"+
							"</tr>"+
					    	"</table>"+
	    				"</div>",
            	selected: true
            });
  		}
  		//删除当前行
  		function fun_del(){
  			 $('#aa').accordion('remove','内部人员1');
  		}

  		function addOther(){
			if($("#cusStatusZhengMing").val() == 3){
				$("#zhengMingOther").css({display:"block"});
			}else{
				$("#zhengMingOther").css({display:"none"});
			} 
  		}

  		function zhongJieSelectFun(){
			if($("#zhongJieSelect").val()==2){
				$("#shiFouZhongJie").css({display:"block"});
			}else{
				$("#shiFouZhongJie").css({display:"none"});
			}
  		}
  		function qiuDaoSelectFun(){
			if($("#qiuDaoSelect").find("option:selected").text() == '个人邮箱'){
				$("#qiuDaoGeRen").css({display:"block"});
			}else{
				$("#qiuDaoGeRen").css({display:"none"});
			}
  	  	}
  	</script>
  </head>
  <body>
    <div id="dlg" class="" style="width:x;height:x;padding:x x" closed="true" buttons="#dlg-buttons">
	    <form id="fm" method="post">
	    <table align="center" width="100%" border="0">
			<tr>
				<td width="15%" align="left">投诉渠道:</td>
				<td width="35%">
					<select id="qiuDaoSelect" name="isCustomer" style="width:200px;" onchange="qiuDaoSelectFun();">
		    			<option value="1">电话 </option>
		    			<option value="2">投诉邮箱</option>
		    			<option value="3">合规邮箱</option>
		    			<option value="3">个人邮箱</option>
		    			<option value="3">其他</option>
	    			</select>
	    		</td>
				<td width="10%" align="left">投诉时间 :</td>
				<td width="35%">
					<input type="text" name="complainDate" id="complainDate"/>
	    		</td>
			</tr>	
			<tr style="display: none"  id="qiuDaoGeRen">
				<td>备注</td><td rowspan="1" colspan="3"><textarea rows="1" cols="" style="width: 78%"></textarea></td>
			</tr>   
			<tr>
				<td>书面证据 :</td>
				<td>
					<select name="cusStatus" id="cusStatus" style="width:200px;" onchange="fun_cusStatus();">
		    			<option>----请选择----</option>
		    		    <option value='1'>无</option>
	  				  	<option value='2'>有</option>
		    		</select>  
	    		</td>
				<td>证据类型 :</td>
				<td>
					<select name="cusStatus" id="cusStatusZhengMing" style="width:200px;" id="cusStatus" onchange="addOther();">
		    			<option>----请选择----</option>
		    		    <option value='1'>录音</option>
	  				  	<option value='2'>视频</option>
	  				  	<option value='2'>短信</option>
	  				  	<option value='2'>书面证明资料</option>
	  				  	<option value='3'>其他</option>
	    			</select>  
	    		</td>
			</tr>	
			<tr style="display: none"  id="zhengMingOther">
				<td>备注</td><td rowspan="1" colspan="3"><textarea rows="1" cols="" style="width: 78%"></textarea></td>
			</tr>    
			<tr>
				<td>是否是内部员工:</td>
				<td>
					<select name="cusStatus" style="width:200px;" id="cusStatus" onchange="fun_cusStatus();">
		    			<option>----请选择----</option>
		    		    <option value='1'>是</option>
	  				  	<option value='2'>否</option>
		    		</select>  
	    		</td>
				<td>涉及业务端 :</td>
				<td>
					<select name="cusStatus" style="width:200px;" id="cusStatus">
		    			<option>----请选择----</option>
		    			<option>普惠金融</option>
		    			<option>财富管理</option>
		    			<option>职能端</option>
	    			</select>  
	    		</td>
			</tr>	
			<tr>
				<td>案件初步分类 :</td>
				<td colspan="3">
					<table width="80%" border="0">
						<tr>
							<td><input type="checkbox" name="cbflcheckbox" value="0">欺诈伪冒类</td>
							<td><input type="checkbox" name="cbflcheckbox" value="1">违背诚信类</td>
							<td><input type="checkbox" name="cbflcheckbox" value="2">商业贿赂类</td>
							<td><input type="checkbox" name="cbflcheckbox" value="3">信息保密类</td>
							<td><input type="checkbox" name="cbflcheckbox" value="4">服务类
							<td><input id="otherCheckbox" type="checkbox" name="cbflcheckbox" value="5" onclick="otherCheck()">其他</td>
						</tr>
					</table>
	    		</td>
			</tr>
			<tr>
				<td>案件详细分类 :</td>
				<td colspan="3">
					<table width="80%" border="0">
						<tr>
							<td><select>
				    			<option>无效信息</option>
				    			<option>虚假资料</option>
				    			<option>代客户签字</option>
				    			<option>伪冒同业</option>
				    			<option>无效信息</option>
				    			<option>同仁</option>
				    			<option>从事其他公司业务</option>
			    			</select></td>
							<td><select>
				    			<option>虚假承诺</option>
				    			<option>恶意阻拦客户需求</option>
				    			<option>虚假宣传</option>
				    			<option>违规擅自收取业务费</option>
			    			</select></td>
							<td><select>
				    			<option>员工收取</option>
				    			<option>与中介公司共同</option>
			    			</select></td>
							<td><select>
				    			<option>飞单</option>
				    			<option>串单</option>
				    			<option>泄露</option>
				    			<option>买卖客户信息</option>
			    			</select></td>
							<td><select>
				    			<option>威胁</option>
				    			<option>恐吓</option>
				    			<option>骚扰</option>
				    			<option>谩骂</option>
				    			<option>羞辱客户</option>
				    			<option>语气生硬</option>
				    			<option>缺乏耐心</option>
				    			<option>服务内容不认可，不满意</option>
			    			</select></td>
						</tr>
					</table>
	    		</td>
			</tr>
			<tr style="display: none"  id="xiangXiFengLeiOther">
				<td>备注</td><td rowspan="1" colspan="3"><textarea rows="1" cols="" style="width: 78%"></textarea></td>
			</tr>	    
			<tr>
				<td>案件具体描述 :</td>
				<td colspan="3">
					<textarea rows="2" name="caseDescription" style="width: 78.5%"></textarea>
	    		</td>
	    	</tr>    
	    </table>
    	<div id="personAll" style="width: 80%"><div align="center"><strong style="color: blue">被投诉人信息</strong> </div></div> 
	    <div id="aa" class="easyui-accordion" style="width:885px;height:170px;">  
		    <div title="内部人员&nbsp;&nbsp;<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_addOut();'>新增</a>
		    	<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_del();'>删除</a>" 
		    	data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
		    	<table border="0" width="100%">
		    		<tr>
						<td width="10%">被投诉人姓名:</td>
						<td width="23%"><input type="text" name=""></td>
						<td width="10%">手机号码:</td>
						<td width="23%"><input type="text" name="" value="可添加多个用“;”隔开"></td>
						<td width="10%">座机:</td>
						<td width="23%"><input type="text" name="" value="可添加多个用“;”隔开"></td>
		    		</tr>
		    		<tr>
						<td>QQ:</td>
						<td><input type="text" name="" value="可添加多个用“;”隔开"></td>
						<td>邮箱:</td>
						<td><input type="text" name="" value="可添加多个用“;”隔开"></td>
						<td>级别:</td>
						<td><input type="text" name=""></td>
		    		</tr>
		    		<tr>
						<td>组织信息 :</td>    
						<td colspan="4"><input type="text" name="" onclick="openWinNew('admin/admin!queryAllOrgadmin.action')"
							value="部门xx-城市xx-营业部xx" readonly="readonly"/>
						<label onclick="openWinNew('admin/admin!queryAllOrgadmin.action')"><font color="blue">选择</font></label></td>
		    		</tr>
		    		<tr>
		    			<td>入职时间:</td>
		    			<td><input type="text" value=""></td>
		    			<td>上家工作单位:</td>
		    			<td><input type="text" value=""></td>
		    			<td>学历:</td>
		    			<td><input type="text" value=""></td>
		    		</tr>
		    		<tr>
		    			<td>推荐人:</td>
		    			<td><input type="text" value=""></td>
		    			<td>被投诉的次数:</td>
		    			<td><input type="text" value="系统自动加载"></td>
		    			<td>涉及到的案子:</td>
		    			<td><a href="javascript:void(0);">案件1</a><a href="javascript:void(0);">案件2</a>...</td>
		    		</tr>
		    	</table>
		    </div>  
		</div> 
	    <div id="aa1" class="easyui-accordion" style="width:885px;height:150px;">  
		    <div title="外部人员&nbsp;&nbsp;<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_addOut();'>新增</a>
		    	<a href='javascript:void(0)' class='easyui-linkbutton' onclick='fun_del();'>删除</a>" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">  
		    	<table border="0" width="100%">
		    		<tr>
						<td width="10%">被投诉人姓名:</td>
						<td width="23%"><input type="text" name=""></td>
						<td width="10%">手机号码:</td>
						<td width="23%"><input type="text" name=""></td>
						<td width="10%">座机:</td>
						<td width="23%"><input type="text" name=""></td>
		    		</tr>
		    		<tr>
						<td>QQ:</td>
						<td><input type="text" name=""></td>
						<td>邮箱:</td>
						<td><input type="text" name=""></td>
						<td>公司名称:</td>
						<td><input type="text" name=""></td>
		    		</tr>
		    		<tr>
						<td>城市:</td>
						<td><input type="text" name=""></td>
						<td>被投诉人地址:</td>
						<td><input type="text" name=""></td>
						<td>是否为中介:</td>
						<td><select id="zhongJieSelect" onchange="zhongJieSelectFun();">
							<option value="1">是</option>
							<option value="2">否</option>
						</select> </td>
		    		</tr>
		    		<tr style="display: none"  id="shiFouZhongJie">
						<td>备注</td><td rowspan="1" colspan="5"><textarea rows="1" cols="" style="width: 94.5%"></textarea></td>
					</tr>
		    	</table>
		    </div>  
		</div>
		     <div id="dlg-buttons" align="center">
		    	<a href="compliance/casList" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
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
