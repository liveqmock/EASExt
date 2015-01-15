<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%--	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>--%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>合规初步调查信息添加 </title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/pages/compliance/investigation/investigationInfo.js" ></script>
	
	
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
		function loadRemoteSelect(id,url){
			var $id = $("#"+id);
			$id.empty().append( $("<option value=''>--请选择--</option>") );
			var json = sendAjax(url);
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].key +" >"+json[i].value +"</option>");
				$id.append($opt);
			}
		}
   		$(function() {
   			loadRemoteSelect("iniCaseTypeId","<%=basePath%>/compliance/investigation!getIniCaseTypeDictionary");
   			loadRemoteSelect("cusSourceId","<%=basePath%>/compliance/investigation!getCusSourceDictionary");
   			loadRemoteSelect("loanBread","<%=basePath%>/caseinfo/caseinfo!getAllDictionarys");
   	   		var locationHref = window.location.href;
   	   		var type = locationHref.substring(locationHref.lastIndexOf("=")+1,locationHref.length);
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					compStartTime:$("#compStartTime").val(),
					compEndTime:$("#compEndTime").val(),
					complainanter:$("#complainanter").val(),
					customerName:$("#customerName").val(),
					iniCaseTypeId:$("#iniCaseTypeId").val(),
					responsibleName:$("#responsibleName").val(),
					cusSourceId:$("#cusSourceId").val(),
					loanBread:$("#loanBread").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#compStartTime,#compEndTime,#complainanter,#customerName").val("");
				$("#iniCaseTypeId,#responsibleName,#cusSourceId,#loanBread").val("");
			})
			var previewColumns = [];
			if(type == 1) {
				previewColumns.push({field:'ck',checkbox:true});
				$('#tt').attr("toolbar","#toolbar");
			}else if(type == 2){
				$("#toolbar").remove();
				$(".list-title").html("待办列表");
			}else if(type == 3){
				$("#toolbar").remove();
				$(".list-title").html("已办列表");
			}
			previewColumns.push(
				{field:'caseCode',title:'编号',width:fixColumnWidth(0.0800)},
			  	{field:'compTime',title:'投诉时间',width:fixColumnWidth(0.1500)},
			  	{field:'complainanter',title:'投诉人',width:fixColumnWidth(0.1000)},
			  	{field:'fcusname',title:'平台客户姓名',width:fixColumnWidth(0.1300)},
			  	{field:'casecadeInicasetypes',title:'案件初步分类',width:fixColumnWidth(0.1700),
			  		formatter:function(val,node){
					  	var result="";
					  	for(var i=0;i<val.length;i++){
					  		result += val[i].inicaseTypeName+"，";
					  	}
					  	return result.substring(0,result.length-1);
			  		}
			  	},
			  	{field:'responsibleName',title:'案件负责人',width:fixColumnWidth(0.1500)},
			  	{field:'operation',title:'操作',width:fixColumnWidth(0.2000),
		   	  		formatter: function(val,node) {
			  		var  result = "<span><a class=\"operation-a\" href='javascript:void(0)' "
			  			+"onclick='openTab1(this,\"协助负责人信息修改\",\"edit\","+node.id+")' >回访</a>&nbsp;</span>";
		  				return result;
			   		}
		   		});
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息
				url:'<%=basePath%>/compliance/investigation!queryPageJsonForCallBack?type='+type,
<%--				title: type==1?"调查列表":(type==2?"待办列表":"已办列表"),--%>
				width:($(window).width()-20),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:true, 
				columns: [previewColumns],
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
   		function openTab1(btn,parentTitle,type,id){
			var me = $(btn);
			var url,title=parentTitle;
			switch (type) {
				case 'view':
					url = $("#detailURL").val()+id;	title+="_详细信息";
					break;
				case 'edit':
					//url = $("#editURL1").val()+id;	title+="_编辑信息";
					url = $("#editURL1").val();	title+="_编辑信息";
					if(id == null || id == '') url = url.substring(0,url.indexOf("?"));
					break;
			}
			addTab(title, url);
		}
   </script>
</head>
<body>
<div class="search">
	<table onKeyUp="bindQuery();">
		<tr>
            <td class="s-t">投诉时间从</td>
            <td><input class="s-text Wdate" type="text" id="compStartTime" 
                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
            <td class="s-t">至</td>
            <td><input class="s-text Wdate" type="text" id="compEndTime" 
                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
            <td class="s-t">投诉人</td>
            <td><input class="s-text" type="text" id="complainanter" onKeyUp="changeQyery();"/></td>
            <td class="s-t">案件负责人</td>
            <td><input class="s-text" type="text" id="responsibleName"/></td>
        </tr>
		<tr>
			<td class="s-t">客户姓名</td>
            <td><input class="s-text" type="text" id="customerName"/></td>
            <td class="s-t">客户来源</td>
            <td><select id="cusSourceId" onChange="changeQyery();">
				<option value="">--全部--</option>
				<s:iterator value="cusSources">
					<option value="<s:property value='key'/>"><s:property value="value" /></option>
				</s:iterator>
				</select></td>
			<td class="s-t">贷款品种</td>
            <td><select id="loanBread" onchange="changeQyery();"></select></td>
			<td class="s-t">案件初步分类</td>
            <td><select id="iniCaseTypeId" onChange="changeQyery();">
				<option value="">--全部--</option>
				<s:iterator value="iniCaseTypes">
					<option value="<s:property value='key'/>"><s:property value="value" /></option>
				</s:iterator>
				</select></td>
			
        </tr>
	</table>
    <span class="s-but" style="display:inline-block; width:205px;"><a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a></span>
</div>
<div class="search-list">
	<table id="tt" align="center"></table>
</div>
	
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/compliance/investigation!view?investigation.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/compliance/investigation!edit?investigation.id="/>
	<input type="hidden" id="continueNextURL2" value="<%=request.getContextPath()%>/compliance/investigation!edit"/>
	<input type="hidden" id="feedbackRequiredURL" value="<%=request.getContextPath()%>/compliance/feedbackRequired!edit"/>
	<input type="hidden" id="feedbackResultURL" value="<%=request.getContextPath()%>/compliance/feedbackResult!edit"/>
	<input type="hidden" id="updateStatusURL" value="<%=request.getContextPath()%>/compliance/investigation!updateStatus"/>
	<input type="hidden" id="fieldsurveyURL" value="<%=request.getContextPath()%>/compliance/fieldsurvey!edit"/>
	<input type="hidden" id="zcxzlsfbResultURL" value="<%=request.getContextPath()%>/compliance/zcxzlsfbResult!edit"/>
	<input type="hidden" id="fileUrl" value="<%=path%>/pages/compliance/investigation/upload.jsp?fid="/>
	<input type="hidden" id="feedbackURL" value="<%=request.getContextPath()%>/compliance/feedback!edit"/>
	<input type="hidden" id="auditResultURL" value="<%=request.getContextPath()%>/compliance/auditResult!edit"/>
	<input type="hidden" id="continueNextURL6" value="<%=request.getContextPath()%>/compliance/investigation!addInvestInturning"/>
	<input type="hidden" id="editURL1" value="<%=path%>/pages/compliance/investigation/callofCenterReturnAdd.jsp" />
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true" maximizable=true
		iconCls="icon-save" style="width:1069px;height:400px;padding:2px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>