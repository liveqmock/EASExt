<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/common.jsp" %>
<%
	//说明：预警明细表，提供了一些删除的功能。
    //这个界面的提供只是为了删除冗余的测试发送用的数据
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>预警明细</title>
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
	<script type="text/javascript"><!--
		//下面这个方法 用于 配置  分页的信息 
		$(function() {
			displayMsg($('#tt'));
			//获得里面的取值
			/******************预警类别*************************/
			url = "<%=basePath%>person/warn!selectAllWaringType";
			var json = sendAjax(url);
			var $sel = $("#typeid");
			var typeid = "${param.typeid}";
			for(var i=0;i<json.length;i++){
				var $opt = "";
				if(typeid==json[i].ID){
					$opt = $("<option value="+json[i].ID +" selected>"+json[i].TYPENAME +"</option>");
				}else{
					$opt = $("<option value="+json[i].ID +">"+json[i].TYPENAME +"</option>");
				}
				$sel.append($opt);
			}
			/******************预警方式*************************/
			var url = "<%=basePath%>person/warn!selectAllSendWays";
			var json = sendAjax(url);
			var $sel = $("#wayid");
			var wayid = "${param.wayid}";
			for(var i=0;i<json.length;i++){
				var $opt = "";
				if(wayid==json[i].ID){
					$opt = $("<option value="+json[i].ID +" selected>"+json[i].WAYNAME +"</option>");
				}else{
					$opt = $("<option value="+json[i].ID +">"+json[i].WAYNAME +"</option>");
				}
				$sel.append($opt);
			}
			
		//弹出窗口
		$("#operval").fancybox({
			'hideOnContentClick': false,
			'width'	 : '95%',
			'height'	 : '90%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat': function(title){return  "";},
			'scrolling':'yes'
		});		
		//弹出窗口
		$("#operval_cell").fancybox({
			'hideOnContentClick': false,
			'width'	 : '45%',
			'height'	 : '50%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat': function(title){return  "";},
			'scrolling':'no'
		});	
		//弹出窗口
		$("#yearofwork").fancybox({
			'hideOnContentClick': false,
			'width'	 : '60%',
			'height'	 : '120%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat': function(title){return  "";},
			'scrolling':'yes'
		});			
	});	
		/**
		*弹窗事件
		**/
		function openNewDialog(id,typeid,wayid){
			var url = "";
			if(typeid==1 && wayid==1){//短信
				//alert("短信");
				url = "<%=request.getContextPath()%>/person/warn!queryDetailCell?waringDetail.id=" + id;	
			$("#operval_cell").attr("href",url);
			$("#operval_cell").click();
			}else if (typeid==2||typeid==4){//邮件
				url = "<%=request.getContextPath()%>/person/warn!queryDetail?waringDetail.id=" + id;		
				$("#operval").attr("href",url);
				$("#operval").click();
			}else if (typeid==3){//邮件
				url = "<%=request.getContextPath()%>/person/warn!yearOfWorkDetail?waringDetail.id=" + id;		
			$("#yearofwork").attr("href",url);
			$("#yearofwork").click();
			}else if(typeid==1 && wayid==2){//短信
				//alert("短信");
				url = "<%=request.getContextPath()%>/person/warn!imageCellDetail?waringDetail.id=" + id;	
			$("#yearofwork").attr("href",url);
			$("#yearofwork").click();
			}
		}
		//删除预警明细
		//批量删除
		//使用ajax技术吗？
		function deleteWaringDetail(id){
			var url = "<%=request.getContextPath()%>/person/warn!deleteWaringDetailById?waringDetail.id=" + id;	
			//window.location.href= url;
			$.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   success:function(data){
			   			$('#tt').datagrid('reload');    // reload the current page data 
			  	   }
				});
		}
		//删除所有的明细信息
		function deleteWaringDetailAll(){
			var ids = [];   
			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 for (var i = 0; i < rows.length; i++) {   
			 	ids.push(rows[i].id);   
			}
			var url = "<%=request.getContextPath()%>/person/warn!deleteWaringDetailAll?ids=" + ids;	
			$.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   success:function(data){
			   			$('#tt').datagrid('reload');    // reload the current page data 
			  	   }
			});
		}
		/************格式化日期************************/
	 	 function formatDate(val,row){
			if(null != val && ""!=val){
				val = val.replace("T"," ");
			}
			return val;
		}
		//操作
		function operation(val,node){
	   		return "<span><a href='javascript:void(0)' onclick='openNewDialog("+node.id+","+node.typeid+","+node.wayid+")' style='color:blue'>详细</a></span>|<span><a href='javascript:void(0)' onclick='deleteWaringDetail("+node.id+")' style='color:blue'>删除</a></span>";
	    }
	</script>
</head>
<body>
	<form action="<%=basePath%>person/warn!queryPageJsonBackDelete" method="post">
		<table align="left" border="0">
		  <tr>
			    <td>开始时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			    <td align="right">
					<input type="text" id="dbengin" name="begin" value="${param.begin }" readonly  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',  maxDate:'#F{$dp.$D(\'dend\')||\'2020-10-01\'}'})" class="Wdate" style="width:150px"/>
			    </td>
			    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</td>
			    <td align="right">
			    	<input type="text" id="dend" name="end" value="${param.end }" readonly onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',   minDate:'#F{$dp.$D(\'dbengin\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:150px"/>
			    </td>
			    <td align="right">&nbsp;</td>
		  </tr>
		  <tr>
			    <td>收  件  人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="receiver" value="${param.receiver}"  style="width:150px"></input>
			    </td>
			    <td>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预警主题:
				</td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="theme" value="${param.theme}"  style="width:150px"></input>
				</td>
			    <td>&nbsp;</td>
		  </tr>
		  <tr>
			    <td>
			    	预警类别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>
			    <td align="left">
					 <select id="typeid" name="typeid" style="width:150px;">
						<option value="">--------全部---------</option>
					</select>	
				</td>	
			    <td>
			     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预警方式:
			    </td>
			    <td align="right">
			     		<select id="wayid" name="wayid" style="width:150px;">
								<option value="">--------全部---------</option>
						</select>	
				</td>
			    <td align="right" valign="bottom">
			      	<input type="submit" value="查询"></input>
			    </td>
		  </tr>
		</table>
	</form>
    <br/>
	<br/>
	<p>&nbsp;</p><p>&nbsp;</p>
   	  <a id="operval" href="#"></a> 
      <a id="operval_cell" href="#"></a> 
      <a id="yearofwork" href="#"></a> 
      <input type="button" value="删除全部" onclick="deleteWaringDetailAll();"/>${param.typeid}
      <table id="tt" title="Checkbox Select" class="easyui-datagrid" style="width:1100px;height:250px"
			url="<%=basePath%>person/warn!queryPageJson"
			idField="id" pagination="true" selectOnCheck="true"
			iconCls="icon-save">
		<thead>
			<tr>
				<th field="ck" checkbox="true">&nbsp;</th>
				<th field="id" width="80">id</th>
				<th field="typename" width="80">预警方式</th>
				<th field="wayname" width="80">预警类别</th>
				<th field="theme" width="120">主 题</th>
				<th field="sendtime" width="150" formatter="formatDate">发送时间</th>
				<th field="departname" width="80">部 门</th>
				<th field="postname" width="120">职 位</th>
				<th field="email" width="150">收件人邮箱</th>
				<th field="receiver" width="80">收件人</th>
				<th field="operation" width="80" formatter="operation">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>