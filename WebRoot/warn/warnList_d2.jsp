<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/common.jsp" %>
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
	<title>预警明细 Demo</title>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<style type="text/css">
		a{
			color:blue;
			text-decoration: none;
		}
	</style>
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
			+"&postname=${param.postname}&theme=${param.theme}&typeid=${param.typeid}&wayid=${param.wayid}'>"
			+"生成excel文件</a>";
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/warn!queryPageJson',
				title:vartitle,
				width: 1000,
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:true,
				columns: [[
				    {field:'typename',title:'预警类别',width:100,align:'center'},
					{field:'wayname',title:'预警方式',width:60,align:'center'},
					{field:'theme',title:'主 题',width:150,align:'center'},
					{field:'sendtime',title:'发送时间',width:150,align:'center',//格式化日期 的方法
						formatter: function(val){
							if(null != val && ""!=val){
								val = val.replace("T"," ");
							}
							return val;
						}
					},
					{field:'departname',title:'部 门',width:110,align:'center'},
					{field:'postname',title:'职 位',width:100,align:'center'},
					{field:'email',title:'收件人邮箱',width:160,align:'center'},
					{field:'receiver',title:'收件人',width:120,align:'center'},
					{field:'operation',title:'操作',width:120,sortable:true,align:'center',
					   formatter: function(val,node) {
					   		return "<span><a href='javascript:void(0)' onclick='openNewDialog("+node.id+")' style='color:red'>详细</a></span>";
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
					wayid:'${param.wayid}'
				},
				//下面 定义 分页配置 ：
				pageSize:5,
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
		});
		/**
		*弹窗事件
		**/
		function openNewDialog(id){
			var　url = "<%=request.getContextPath()%>/person/warn!queryDetail?waringDetail.id=" + id;
			alert("url" + url );
			$('#mydialog').dialog({  
			    title: '查看预警明细',  
			    width: 900,  
			    height: 650,  
			    closed: false,  
			    cache: false,  
			    href: url,  
			    modal: true  
			});  
		}
	-->
	</script>
</head>
<body>
	<form action="<%=basePath%>person/warn!queryPageJsonBack" method="post">
		<table width="" border="0">
		  <tr>
			    <td><strong>发送时间从</strong></td>
			    <td align="right">
					<input type="text" id="begin" name="begin" value="${param.begin }" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:150px"/>
			    </td>
			    <td><strong>至</strong></td>
			    <td align="right">
			    	<input type="text" id="end" name="end" value="${param.end }" readonly onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:150px"/>
			    </td>
			    <td align="right">&nbsp;</td>
		  </tr>
		  <tr>
			    <td><strong>收件人:</strong></td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="receiver" value="${param.receiver}"></input>
			    </td>
			    <td><strong>
			      <label for="subject">收件部门:</label>
			    </strong></td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="departname" value="${param.departname}"></input>
			    </td>
			    <td align="right">&nbsp;</td>
		  </tr>
		  <tr>
		        <td><strong>收件职位:</strong></td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="postname" value="${param.postname}"></input>
			    </td>
			    <td>
			    	<strong>预警主题:</strong>
			    </td>
			    <td align="right">
			    	<input class="easyui-validatebox" type="text" name="theme" value="${param.theme}"></input>
				</td>
			    <td>&nbsp;</td>
		  </tr>
		  <tr>
			    <td>
			    	<strong>预警类别:</strong>
			    </td>
			    <td align="left">
					 <select id="typeid" name="typeid" style="width:150px;">
						<option value="">--------全部---------</option>
					</select>	
				</td>	
			    <td>
			     	<strong>预警方式:</strong>
			    </td>
			    <td align="right">
			     		<select id="wayid" name="wayid" style="width:150px;">
								<option value="">--------全部---------</option>
						</select>	
				</td>
			    <td align="right" valign="bottom">
			      	<input type="submit" value="查询" onClick="searchByParams()"></input>
			    </td>
		  </tr>
		</table>
	</form>
    <br/>
	<br/>
	<table id = "tt"></table>
   <div id="mydialog"></div>  
</body>
</html>