<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>违规类型统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#fcityname,#fdeptname").validatebox({validType: 'speci'});
	})
		$(function() {
			
   				var vartitle = "";
				$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>statis/statisAction!queryPageJson',
					title: vartitle,
					width: ($(window).width()-30),
					height: 'auto',
					nowrap: false,  //  是否在一行显示数据
					striped: true,   //  是否 显示 斑马线  
					fitColumns: true,  // 自动填充 列 宽
					collapsible: true,   // 是否 有滑动效果 
					loadMsg: '数据连接中.....',  //加载页面时候的提示消息
					remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
					sortOrder: 'desc',    // 排序 方法 
					singleSelect:false, 
					columns: [[
						  {field:'fcityname',title:'城市名称',width:fixColumnWidth(0.05000)},
						  {field:'fdeptname',title:'部门名称',width:fixColumnWidth(0.05000)},
						  {field:'qizha',title:'欺诈伪冒类',width:fixColumnWidth(0.05000)},
						  {field:'weichengxin',title:'违背诚信类',width:fixColumnWidth(0.05000)},
						  {field:'huilu',title:'商业贿赂类',width:fixColumnWidth(0.05000)},
						  {field:'baomi',title:'信息保密类',width:fixColumnWidth(0.05000)},
						  {field:'fuwu',title:'服务类',width:fixColumnWidth(0.05000)},
						  {field:'other',title:'其他',width:fixColumnWidth(0.05000)},
						  {field:'countnum',title:'总数',width:fixColumnWidth(0.05000)}
					]],
					//下面 定义 分页配置 ：
					pageSize:10,
					pageList:[5,10,15,20],
					pagination:true,   // 是否 要分页 
					pageNumber:1//设置初始页为1
					//onLoadSuccess: omitLongData()
				});
				//下面这个方法 用于 配置  分页的信息 
				displayMsg($('#tt'));
			});
	</script>
<script type="text/javascript">
		function checkboxQuery(){
			 //获取复选框的值
			  var checkboxvalue =$("input[name='inicasetypecheckbox']:checked");
			   var checkValue=[];
			   $.each(checkboxvalue,function(){
			   checkValue.push($(this).val());
			  });
			  // alert(checkValue.join(","));
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fcityname:$("#fcityname").val(),
					fdeptname:$("#fdeptname").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#fcityname,#fdeptname").val("");
				//将选中的复选框改为未选中状态
				var code_Values = document.all['inicasetypecheckbox']; 
				if(code_Values.length){   
				for(var i=0;i<code_Values.length;i++)   
				{   
				code_Values[i].checked = false;   
				}   
				}else{   
				code_Values.checked = false;   
				}   

			})

			var vartitle = "";

			$('#tt').datagrid({
			//下面是 datagrid的基本 配置信息 
			url:'<%=basePath%>statis/statisAction!queryPageJson?checkValue='+checkValue,
			title: vartitle,
			width: ($(window).width()-30),
			height: 'auto',
			nowrap: false,  //  是否在一行显示数据
			striped: true,   //  是否 显示 斑马线  
			fitColumns: true,  // 自动填充 列 宽
			collapsible: true,   // 是否 有滑动效果 
			loadMsg: '数据连接中.....',  //加载页面时候的提示消息
			remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
			sortOrder: 'desc',    // 排序 方法 
			singleSelect:false, 
			columns: [[
				  {field:'fcityname',title:'城市名称',width:fixColumnWidth(0.05000)},
				  {field:'fdeptname',title:'部门名称',width:fixColumnWidth(0.05000)},
				  {field:'qizha',title:'欺诈伪冒类',width:fixColumnWidth(0.05000)},
				  {field:'weichengxin',title:'违背诚信类',width:fixColumnWidth(0.05000)},
				  {field:'huilu',title:'商业贿赂类',width:fixColumnWidth(0.05000)},
				  {field:'baomi',title:'信息保密类',width:fixColumnWidth(0.05000)},
				  {field:'fuwu',title:'服务类',width:fixColumnWidth(0.05000)},
				  {field:'other',title:'其他',width:fixColumnWidth(0.05000)},
				  {field:'countnum',title:'总数',width:fixColumnWidth(0.05000)},
				  {field:'manytypecount',title:'多种类型',width:fixColumnWidth(0.05000)}
					  
			]],
			//下面 定义 分页配置 ：
			pageSize:10,
			pageList:[5,10,15,20],
			pagination:true,   // 是否 要分页 
			pageNumber:1//设置初始页为1
			//onLoadSuccess: omitLongData()
		});
		//下面这个方法 用于 配置  分页的信息 
		displayMsg($('#tt'));
	}
	</script>
  </head>
  <body>
   <div class="search">
		<table>
			<tr>
				<td class="s-t">城市</td><td><input class="s-text" type="text" id="fcityname" value=""></td>
                <td class="s-t">部门</td><td><input class="s-text" type="text" id="fdeptname" value=""></td>
                <td></td>
			 </tr>
             <tr>
				<td class="s-t">请选择</td>
                <td colspan="3">
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="qizhaCount>0"/>欺诈伪冒类
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="weichengxinCount>0"/>违背诚信类
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="huiluCount>0"/>商业贿赂类
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="baomiCount>0"/>信息保密类
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="fuwuCount>0"/>服务类
                    <input name="inicasetypecheckbox" id="box" type="checkbox" value="otherCount>0"/>其他
				</td>
                <td><a href="javascript:void(0)" id="queryBtn" class="but-search"  onclick='checkboxQuery();' >&nbsp;</a>
					<a href="javascript:void(0)" id="resetBtn"  class="but-remove">清空</a>
				</td>
			</tr>
			
		</table>
     </div>
     <div class="search-list">
		<span class="list-title">违规类型统计</span>
		<table id="tt" toolbar="#toolbar"></table>
     </div>
  </body>
</html>