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
    <title>合同信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/common/compliance.js" ></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
  	<script type="text/javascript">
	$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
				});
			})
			$("#resetBtn").click(function(){
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>/project/projectAction!queryAgrNumber?projectFid='+$("#fid").val()+'&loanWayId='+$("#loanWayId").val(),
				width: 'auto',
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
				      {field:'AGREEMENTNAME',title:'协议名称',width:fixColumnWidth(0.15000)},
					  {field:'RECEIVABLEQUANTITY',title:'应收数量',width:fixColumnWidth(0.10000)},
					  {field:'RECEIVEDNUMBER',title:'收到数量',width:fixColumnWidth(0.10000)},
					  {field:'FINANCIALNUMBER',title:'财务保存数量',width:fixColumnWidth(0.10000)},
					  {field:'ARCHIVESNUMBER',title:'档案处保存数量',width:fixColumnWidth(0.10000)},
					  {field:'SENDBACKNUMBER',title:'寄回数量',width:fixColumnWidth(0.06000)},
					  {field:'REMARKS',title:'备注',width:fixColumnWidth(0.18000)},
					   {field:'operation',title:'操作',width:fixColumnWidth(0.10000),
						   formatter: function(val,row) {
						    <c:forEach items="${sessionScope.personrole}" var="roleid" >
            				     <c:if test="${roleid != '569'}">
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"合同信息\",\"edit\","+row.FID+")' >编辑</a></span>&nbsp;";
						   		 </c:if>
						   	</c:forEach>
						   }
					}
					
				]],
				//下面 定义 分页配置 ：
			 	onLoadSuccess: omitLongData()  
			});
			
	$("#exportExcel").click(function(){
 		location.href='<%=basePath %>project/projectAction!expExcelAgreementNumber?projectFid='+$("#fid").val()+'&loanWayId='+$("#loanWayId").val();
    });
});

</script>

	</head>
	<body>
		<div class="search-list">
			<span class="list-title"><c:if test="${projectManage.loanWayId==2}">出借方式：有限合伙</c:if><c:if test="${projectManage.loanWayId==1}">出借方式：P2P</c:if></span>
			<div class="t-but" style="float:right; margin-right: 10px;"><span><a href="javascript:void(0)" name="exportExcel" id="exportExcel" plain=true class="but-change" >导出</a></span></div>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
		<input type="hidden" id="editURL" value="<%=basePath%>/project/projectAction!editAgreement?agreementNumber.fid="/>
	    <input type="hidden" id="fid" value="<s:property value='projectManage.fid'/>"/>
	    <input type="hidden" id="loanWayId" value="<s:property value='projectManage.loanWayId' />">
	</body>
</html>
