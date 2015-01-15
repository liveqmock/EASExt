<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/common.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
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
		$(function() {
			var vartitle = "在职员工列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+"<a href='<%=basePath %>upload/upload!exportExcel.action?begin=${param.begin}"
			+"&end=${param.end}&receiver=${param.receiver}&departname=${param.departname}"
			+"&postname=${param.postname}&theme=${param.theme}&typeid=${param.typeid}&wayid=${param.wayid}&issend=${param.issend}'>"
			+"生成excel文件</a>";
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/warn!queryPageJson',
				title:vartitle,
				width: 1100,
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:true,
				rownumbers:true,
				columns: [[
				    {field:'typename',title:'预警类别',width:100,align:'center'},
					{field:'wayname',title:'预警方式',width:60,align:'center'},
					{field:'theme',title:'主 题',width:220,align:'center'},
					{field:'sendtime',title:'发送时间',width:150,align:'center',//格式化日期 的方法
						formatter: function(val){
							if(null != val && ""!=val){
								val = val.replace("T"," ");
							}
							return val;
						}
					},
					//{field:'departname',title:'部 门',width:110,align:'center'},
					//{field:'filename',title:'职 位',width:100,align:'center'},
					{field:'email',title:'收件人邮箱',width:160,align:'center'},
					{field:'receiver',title:'收件人',width:160,align:'center'},
					{field:'ext1',title:'状态',width:80,align:'center',
						formatter: function(val){
							if(val != '失败'){
								val = '成功';
							}else{
								val = '失败';
							}
							return val;
						}
					},
					{field:'operation',title:'操作',width:120,sortable:true,align:'center',
					   formatter: function(val,node) {
					   	  //return "<span><a href='javascript:void(0)' onclick='openNewDialog("+node.id+","+node.typeid+","+node.wayid+")' style='color:blue'>详细</a></span>";
					   	  if(node.issend==0){
					   	  	return "<span><a href='javascript:void(0)' onclick='openNewDialog("+node.id+","+node.typeid+","+node.wayid+")' style='color:blue'>详细</a>"+
					   	  	"&nbsp;<a href='javascript:void(0)' onclick='sendNewDialog("+node.id+")' style='color:blue'>重发</a></span>";
					   	  }else{
					   	  	return "<span><a href='javascript:void(0)' onclick='openNewDialog("+node.id+","+node.typeid+","+node.wayid+")' style='color:blue'>详细</a></span>";
					   	  }
					   }
				   }
				]],
				queryParams: {//传递参数的方法
					begin: '${param.begin}',
					end:'${param.end}',
					receiver:'${param.receiver}',
					departname:'${param.departname}',
					postname:'${param.postname}',
					theme:'${param.theme}',
					typeid: '${param.typeid}',
					wayid:'${param.wayid}',
					issend:'${param.issend}'
				},
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1//设置初始页为1
			});
			//下面这个方法 用于 配置  分页的信息 
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
			//alert(copyids);
				url = "<%=request.getContextPath()%>/person/warn!yearOfWorkDetail?waringDetail.id=" + id;		
			$("#yearofwork").attr("href",url);
			$("#yearofwork").click();
			}else if(typeid==1 && wayid==2){//短信邮件
				//alert("短信");
				url = "<%=request.getContextPath()%>/person/warn!imageCellDetail?waringDetail.id=" + id;	
			$("#yearofwork").attr("href",url);
			$("#yearofwork").click();
			}
			
		}
		
		
		function sendNewDialog(id){
			var url = "";
			url = "<%=request.getContextPath()%>/person/warn!retryMail?waringDetail.id=" + id;	
			if(confirm("确定重发邮件吗？")==false){
   					return false;
   				}
   			//$.get(url);
   			//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
   			//后面 的reload方法
   			 $.ajax({
			  url: url,
			  async: false
			 });
   			$("#tt").datagrid("reload");
		}
	-->
	


	</script>
</head>
<body>
	<form action="<%=basePath%>person/warn!queryPageJsonBack" method="post">
		<table align="left" border="0">
		  <tr>
			    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间:</td>
			    <td align="right">
					<input type="text" id="dbengin" name="begin" value="${param.begin }" readonly  onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',  maxDate:'#F{$dp.$D(\'dend\')||\'2020-10-01\'}'})" class="Wdate" style="width:150px"/>
			    </td>
			    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</td>
			    <td align="right">
			    	<input type="text" id="dend" name="end" value="${param.end }" readonly onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',   minDate:'#F{$dp.$D(\'dbengin\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:150px"/>
			    </td>
<!--			    <td align="right">&nbsp;</td>-->
			    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收  件  人:</td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="receiver" value="${param.receiver}"  style="width:150px"></input>
			    </td>
		  </tr>
		  <tr>
			    <td>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预警主题:
				</td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="theme" value="${param.theme}"  style="width:150px"></input>
				</td>
			      <td>
			    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预警类别:
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
		  </tr>
		  <tr>
		  		 <td>
			     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发送状态:
			    </td>
			    <td align="right">
			     		<select id="issend" name="issend" style="width:150px;">
								<option value="">--------全部---------</option>
								<option value="1" <c:if test="${param.issend==1}">selected</c:if>>成功</option>
								<option value="0" <c:if test="${param.issend==0}">selected</c:if>>失败</option>
						</select>	
				</td>
			    <td align="right" valign="bottom">
			      	<input type="submit" value="查询"></input>
			    </td>
			    <td>&nbsp;</td>
		  </tr>
		</table>
	</form>
    <br/>
	<br/>
	<p>&nbsp;</p><p>&nbsp;</p>
	<table id = "tt"></table>
   <a id="operval" href="#"></a> 
    <a id="operval_cell" href="#"></a> 
      <a id="yearofwork" href="#"></a> 
</body>
</html>