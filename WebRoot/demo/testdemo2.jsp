<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'employee_onlist.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
	/*
		首先利用id选择器获得表的jquery对象，然后调用它的 datagrid方法
		datagrid里面是json数据
		其中：url表示表里的数据来源，格式为json;
		title是表的名字；
		columns:是对表具体每一行表头的 设置，它为二维数组，猜测底层为：
		将从url的数据得到后，然后将其放入数组的每一行的每一列（这里第一个循环，为嵌套循环），
		当第一行 循环填充值以后，接着循环下一行（此为二维数组外层循环）；
		注意： columns 二维数组里面是json对象，其中有field是属性名字，对应，Url传过来的json数据的属性名字；
		title和宽度以及是否居中都是自己设置的
		*/
		$(function() {
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>person/warn!queryPageJson',
				title:'在职员工列表',
				width: 700,
				height: 450 ,
				iconCls: 'icon-print',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				rownumbers : true,      // 是否显示 行号 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortName: '证件号',    // 那一列 可以排序
				sortOrder: 'desc',    // 排序 方法 
				//下面 定义复选框    
				frozenColumns: [
					[
					 {field:'ids',checkbox:true}
					]
				],
				//下面定义 列的属性 ，会根据 属性名字 自动 填充  获得 json数据 
				columns: [[
				   {field:'typeid',title:'预警类别',width:100,align:'center'},
					{field:'wayid',title:'预警方式',width:60,align:'center'}
				]],
				//下面增加工具栏，用于添加 删除  修改 ，其中，text是工具名称，iconCls是工具图标，handle是 连接的方法
				toolbar:[{
					text : '修改员工信息',
					iconCls: 'icon-edit',
					handler: function() {
					//获得  第一个 被选中的 行对象 
						var node = $('#tt').datagrid('getSelected');
						if(node) {
							window.location = "Employee_preUpdate?id="+node.id + "&ct=" + new Date().getTime(); ;
						}else{
							$.messager.alert('提示框','请选择要修改的数据','error');
						}
					}
				  },{
					text: '删除员工信息',
					iconCls:'icon-cut',
					handler:function() {
						//获得所有 被选择的 行对象， 返回值是数组 
						var nodes = $('#tt').datagrid('getSelections');
						if(nodes!=0) {
							var ids = '';
							for(i in nodes) {
								ids += nodes[i].id + ',';
							}
							ids.length = ids.length-1;
							//提示框的 第一个属性为  标题，第二个 为一个 选项返回 boolean类型，第三个为一个回调函数，所得参数为 第二个的返回值
							$.messager.confirm('提示框','确认删除吗？',function(r) {
								if(r) {
									alert('cofirmed:'+r);
									window.location = "Employee_delete?ids=" + ids + "&ct=" + new Date().getTime();
								}
							})
						}else {
							$.messager.alert('提示框','请选择至少一条数据','error');
						}
					}
				  }
				  ],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				//当刷新时，异步获得数据请求的方法
				onRefresh: function(pageNumber,pageSize) {
					//获得  表格 的 datagrid然后  配置它的 属性  url
					$('#tt').datagrid({
						url:'<%=basePath%>person/warn!queryPageJson?page=' + pageNumber + '&rows=' + pageSize + '&state=在职' + '&dc='+new Date().getTime(),
						loadMsg:'更新数据中...'
					});
				},
				//当选择页面时，异步获得数据请求的方法 
				onSelectPage: function(pageNumber,pageSize) {
					//获得  表格 的 datagrid然后  配置它的 属性  url
					$('#tt').datagrid({
						url:'<%=basePath%>person/warn!queryPageJson?page=' + pageNumber + '&rows=' + pageSize + '&state=在职' + '&dc='+new Date().getTime(),
						loadMsg:'更新数据中...'
					});
				}
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
	
	</script>
  </head>
  
  <body>
  	<table id = "tt"></table>
  </body>
</html>
