<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>案件管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>

	<%--
		<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	 --%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/common/compliance.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/jquery.autocomplete.js" charset="utf-8"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/js/common/jquery.autocomplete.css" type="text/css"  />
	<c:set var="contextPath" value="<%=request.getContextPath() %>" scope="request"></c:set>
	
  	<script type="text/javascript">
	  	function dateOp(begintime,endtime){
			 if(begintime.length>0&&endtime.length>0){
			  　　if(Date.parse(begintime.replace("-", "/")) > Date.parse(endtime.replace("-", "/"))){
				   //alert('开始时间不能大于结束时间!');
				   $.messager.alert("提示","开始时间不能大于结束时间");
			   　　　return false;
			   　　}
			  }
			  return true;
		}
	
  	$(function(){
	  	//加载案件初步分类：动态的生成table
			var $tbfinicasetype = $("#tbfinicasetype");
			var url = "<%=basePath%>/caseinfo/caseinfo!findInicaseType";
			var json = sendAjax(url);
			var $tr=$("<tr></tr>");
			var $td=$("<td></td>");
			for(var i=0;i<json.length;i++){
				if(json[i].value == '其他'){
					$td.append("<input type='checkbox' id='finicasetypeOther' name='finicasetype' value='" 
							+ json[i].key  + "' onclick=getFname()>" + json[i].value);
				}else{
					$td.append("<input type='checkbox' name='finicasetype' id='finicasetype"+(i+1)+"' value='" 
							+ json[i].key  + "') onclick=getFname()>" + json[i].value);
				}
			}
			$td.appendTo($tr);
			$tr.appendTo($tbfinicasetype);
			var json = sendAjax("<%=basePath%>/caseinfo/caseinfo!getAllDictionarys");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$("#floanbread").append($opt);
			}
	  	})
  		$(function() {
  			//加载投诉渠道
			loadSelectInfo('fcompchannelid','<%=basePath%>/caseinfo/caseinfo!findDitch');
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				var result = $('#form').form('validate');
				
				var startDate=$("#startDate").val();
				var endDate=$("#endDate").val();
				if(dateOp(startDate,endDate) && result ){
					

					
					$("#tt").datagrid("load",{
						fcompchannelid:$("#fcompchannelid").val(),
						startDate:startDate,
						endDate:endDate,
						fcomplainant:$("#fcomplainant").val(),
						floanbread:$("#floanbread").val(),
						fcusname:$("#fcusname").val(),
						typename:$("#typename").val()
					});	
				}
			})
			
			
			
			$("#resetBtn").click(function(){
				$("#startDate,#endDate,#fcomplainant,#fcusname,#floanbread,#typename").val("");
				$("#fcompchannelid").children().eq(0).attr("selected","selected");
				 var string=document.getElementsByName("finicasetype");
		   		 for(var i= 0; i <string.length; i++){
			  		 string[i].checked=false;
	  			 }
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>caseinfo/caseinfo!queryPageJson',
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
					  {field:'fnumber',title:'案件编号',width:fixColumnWidth(0.04000)},
					  {field:'ditchname',title:'投诉渠道',width:fixColumnWidth(0.04500)},
					  {field:'fcomplainant',title:'投诉人',width:fixColumnWidth(0.04000)},
					  {field:'fcusname',title:'平台客户姓名',width:fixColumnWidth(0.05800)},
					  {field:'fcomptime',title:'投诉时间',width:fixColumnWidth(0.05000)},
					  {field:'servicename',title:'服务类型',width:fixColumnWidth(0.04500)},
					  {field:'statusname',title:'客户状态',width:fixColumnWidth(0.04500)},
					  {field:'statusvalue',title:'案件状态',width:fixColumnWidth(0.06000)},
					  {field:'casecadeInicasetypes',title:'初步分类',width:fixColumnWidth(0.06500),
						  formatter:function(val,node){
						  	var result="";
						  	if(val){
						  		for(var i=0;i<val.length;i++){
							  		result += val[i].inicaseTypeName+"，";
							  	}
							}
						  	return result.substring(0,result.length-1);
				  		}
					  },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1400),
				      	formatter: function(val,node){
					   		var result = "<a class=\"operation-a\" href=\"javascript:void(0)\" onclick=\"openTabWin(this,1,"
								+node.fid+")\">查看</a>&nbsp;";
					    	if(node.statusvalue!='已结案'){
				   	 			result += "<a class=\"operation-a\" href=\"javascript:void(0)\" onclick=\"openTabWin(this,2,"
					   	 			+node.fid+")\">更新</a>&nbsp;"
				   	 				+"<a class=\"operation-a\" href=\"javascript:void(0)\" onclick=\"openTabWin(this,3,"
					   	 			+node.fid+")\">更新</a>";
					    	}
						    return result;
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
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
		function getFname(){
	  		var fname="";
		  	var string=document.getElementsByName("finicasetype");
	   		for(var i= 0; i <string.length; i++){
			   if(string[i].checked){
			     fname+=string[i].value+",";
			   }
		   	}
		   	var fnames=fname.substring(0,fname.length-1);
		   	$("#typename").val(fnames);
		}
		//打开新选项卡
		function openTabWin(btn,type,id){
			var url = $("#detailURL"+type).val()+id; 
			if(type==4) url = $("#detailURL4").val();
			addTab("案件"+(type==1?"_详细信息":"_编辑信息"), url);
		}

		/*导出*/
   		function exportExcel(){
   			var fcompchannelid=$("#fcompchannelid").val();
   			var startDate=$("#startDate").val();
   			var endDate=$("#endDate").val();
   			var fcomplainant=$("#fcomplainant").val();
   			var floanbread=$("#floanbread").val();
   			var fcusname=$("#fcusname").val();
   			var typename=$("#typename").val();
			
			var url='<%=basePath%>/caseinfo/caseinfo!exportExcel?fcompchannelid='+fcompchannelid+'&startDate='+startDate+'&endDate='+endDate+
					'&fcomplainant='+fcomplainant+'&floanbread='+floanbread+'&fcusname='+fcusname+'&typename='+typename;
			//alert(url);
			location.href=url;
		}
	</script>
	</head>
	<body>
		<div class="search">
  		<form action="<%=basePath%>caseinfo/caseinfo!queryPageJson" method="post" id="form">
			<table id="abc" onkeyup="bindQuery();">
				<tr><td class="s-t">投诉渠道</td>
				<td><select id="fcompchannelid" name="fcompchannelid" onchange="changeQyery();"></select></td>
				<td class="s-t">投诉时间从</td>
				<td><input type="text" id="startDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="begin" value=""></td>
			    <td class="s-t">至</td>
			    <td><input type="text" id="endDate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" 
			    	class="s-text Wdate" name="end" value=""></td>
				</tr>
				<tr><td class="s-t">投诉人</td>
				<td><input class="s-text t-text easyui-validatebox" type="text" validType="njection" id="fcomplainant" name="fcomplainant" value=""></td>
				<td class="s-t">平台客户姓名</td>
				<td><input class="s-text t-text easyui-validatebox" type="text" validType="njection" id="fcusname" name="fcusname" value=""></td>
				<td class="s-t">贷款品种</td>
				<td><select id="floanbread" name="floanbread" onchange="changeQyery();"><option value="">--请选择--</option></select>
				</td>
				</tr>
				<tr><td class="s-t">案件初步分类 </td>
					<td colspan="3">
						<input type="hidden" value="" id="typename" name="typename"/>
						<table id="tbfinicasetype" onclick="changeQyery();"></table>
		    		</td>
		    		<td colspan="2">
						<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
						<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
					</td>
				</tr>
			</table>
			<br/>
		</form>
		</div>
		<div class="search-list">
			<span class="list-title">案件信息</span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
		<div id="toolbar">
  			<a onclick="openTabWin(this,4)" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
  			<!-- 
  				<a onclick="exportExcel()" class="easyui-linkbutton" iconCls="icon-add" plain="true">数据导出</a>	
  			 -->
  			 <a onclick="exportExcel()" href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-redo" plain="true">导出excel文件</a>
  			 <span style="color: red;"></span>
		</div>
		<input type="hidden" id="detailURL1" value="<%=basePath%>caseinfo/caseinfo!viewCaseInfo?fid="/>
		<input type="hidden" id="detailURL2" value="<%=basePath%>caseinfo/caseinfo!findCompliant?id="/>
		<input type="hidden" id="detailURL3" value="<%=basePath%>caseinfo/caseinfo!findCase?id="/>
		<input type="hidden" id="detailURL4" value="<%=basePath%>pages/compliance/caseinfo/addComplaint.jsp"/>
	</body>
</html>
