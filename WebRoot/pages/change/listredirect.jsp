<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		String city=	request.getParameter("city");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>法律文件查询</title>
	<%--<style type="text/css">
	 .divcss5{ border:1px solid #F00; width:3000px; height:10000px}
	</style>
	--%>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
	function initCity(xxx){
		var obj=$(xxx).val();
		var city = $("#city");
		city.empty();
		city.append('<option value="">请选择城市</option>');
		if(obj=="投资理财一部"){
			city.append("<option value='上海'>上海</option>");
			city.append("<option value='成都'>成都</option>");
			city.append("<option value='九江/共青城'>九江/共青城</option>");
			}
		else if(obj=="投资理财二部"){
			city.append("<option value='青岛'>青岛</option>");
			city.append("<option value='烟台'>烟台</option>");
			city.append("<option value='长春'>长春</option>");
			}
		else if(obj=="投资理财三部"){
			city.append("<option value='无锡'>无锡</option>");
			city.append("<option value='南京'>南京</option>");
			city.append("<option value='苏州'>苏州</option>");
			city.append("<option value='芜湖'>芜湖</option>");
			city.append("<option value='宁波'>宁波</option>");
			};
	};
	
   		$(function() {
   			initCity($("#department")); // 初始化部门和城市
   			
   			$("#queryBtn").click(function(){
				$('#tt').datagrid('load',{  // 向服务器传递的参数
					department: $('#department').val(),
			        city:$("#city").val(),
			        limitedpartnername:$("#limitedpartnername").val()
			        }); 
			})

			$("#resetBtn").click(function(){
			$("#department").val("");
			$('#city').val("");
			$("#limitedpartnername").val("");
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>limitedpartner/limitedpartner!queryLawFilePageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				columnOption: true,
				rownumbers: true,
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'department',title:'部门 ',width:fixColumnWidth(0.0600)},
					  {field:'city',title:'地区',width:fixColumnWidth(0.0600)},
					  {field:'limitedpartnername',title:'有限合伙公司',width:fixColumnWidth(0.1500)},
					  {field:'done',title:'完成数量',width:fixColumnWidth(0.0600)},
					  {field:'undone',title:'未完成数量',width:fixColumnWidth(0.0600)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1500),
						   formatter: function(val,node) {
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"法律文件\",\"view\","
						   		+node.limitedpartnerid+")' >查看法律文件</a>&nbsp;</span>";
						   }
			   		  }
				]],
				queryParams: {//传递参数的方法
					//name: '${name}'
				   // gatherstatus: '${limitedpartnerList.gatherstatus}'
				},
				//下面 定义 分页配置 ：
				pageSize:5,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
				//onLoadSuccess: omitLongData()
				onLoadSuccess: omitLong()
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
   </script>
</head>
	<body>
	<div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
            <td>部门</td>
            <td>
            <select id="department" name="department" onchange="initCity(this)">
				 <option value="">请选择部门</option>
	             <option value="投资理财一部">投资理财一部</option>
	             <option value="投资理财二部">投资理财二部</option>
	             <option value="投资理财三部">投资理财三部</option>
            	</select>
            </td>
             <td>城市</td>
             <td>
             	<select id="city" name="city">
	             <option value="">请选择城市</option>
            	</select>
           	 </td>
		  </tr>
		  <tr>
            <td>有限合伙人名称</td>
            <td>
            	<input id="limitedpartnername" name="limitedpartnername" style="width:95%" type="text" value="">
            </td>
            <td>
                <a href="javascript:void(0)" id="queryBtn" class="but-search"></a>
            </td>
            <td>
		      	<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
		    </td>
		  </tr>
	 </table>
  </div>
		<div class="search-list">
		<span class="list-title">法律文件查询</span>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!queryDocumentFileJson?pid="/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		
	</body>
</html>
