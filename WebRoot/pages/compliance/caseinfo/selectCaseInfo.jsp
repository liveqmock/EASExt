<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
  	<script type="text/javascript">
	  	$(function(){
	  		//加载案件初步分类：动态的生成table
			var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			var $tr=$("<tr></tr>");
			$tr.appendTo($tbfinicasetype);
			for(var i=0;i<json.length;i++){
				var $td = '';
				if(json[i].value == '其他'){
					$td = $("<td><input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" + json[i].value  + "' onclick=getFname()>" + json[i].value + "</td>");
				}else{
					$td = $("<td><input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" + json[i].value  + "') onclick=getFname()>" + json[i].value + "</td>");
				}
				$td.appendTo($tr);
			}
	  	})
		$(function() {
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>caseinfo/caseinfo!queryPageJson',
				queryParams:{fqq:$("#fqq").val(),selectfcusname:$("#fcusname").val(),selectfmobile:$("#fmobile").val(),fid:$("#fid").val()},
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
<%--				rownumbers:true,--%>
				singleSelect:false, 
				columns: [[
					  {field:'fnumber',title:'编号',width:fixColumnWidth(0.04000)},
					  {field:'ditchname',title:'投诉渠道',width:fixColumnWidth(0.04000)},
					  {field:'fcomplainant',title:'投诉人',width:fixColumnWidth(0.04000)},
					  {field:'fcomptime',title:'投诉时间',width:fixColumnWidth(0.08000)},
					  {field:'servicename',title:'服务类型',width:fixColumnWidth(0.04000)},
					  {field:'statusname',title:'客户状态',width:fixColumnWidth(0.04000)},
					  {field:'statusvalue',title:'案件状态',width:fixColumnWidth(0.1000)},
					  {field:'casecadeInicasetypes',title:'初步分类',width:fixColumnWidth(0.1000),
						formatter: function(v,node){
							var result='';
							if(v){
								if(v.length){
									for(var i=0,j=v.length-1;i<j;i++){
										result+=v[i].inicaseTypeName+',';	
									}
									if(v.length!=1){
										result+=v[v.length-1].inicaseTypeName;;
									}	
								}							
							}
							return result;
					  }},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.0400),
						formatter: function(val,node){
							return "<a class=\"operation-a\" href=\"javascript:void(0)\" onclick=\"openTabWin(this,"
								+node.fid+")\">查看</a>&nbsp;"
					  	}
					  }
				]],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
			 	onLoadSuccess: omitLongData()  
			});
		});
		//打开新选项卡
		function openTabWin(btn,id){
			var url = $("#detailURL").val()+id; 
			addTab("案件_详细信息", url);
		}
  	</script>
  </head>
  <body>
	<div class="search-list">
		<span class="list-title">案件信息</span>
	   	<table id = "tt" align="center"></table>
    </div>
	<input type="hidden" id="detailURL" value="<%=basePath%>caseinfo/caseinfo!viewCaseInfo?fid="/>
	<input type="hidden" value="${param.fqq}" id="fqq"/>
	<input type="hidden" value="${param.fcusname}" id="fcusname"/>
	<input type="hidden" value="${param.fmobile}" id="fmobile"/>
	<input type="hidden" value="${param.fid}" id="fid"/>
  </body>
</html>
