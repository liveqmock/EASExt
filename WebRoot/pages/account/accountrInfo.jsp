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
    <title>报销信息列表</title>
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
  	<script type="text/javascript"><!--
  	var checkedItems = [];
	$(function() {
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
				accountrNum:$("#accountrNum").val(),
				startDate:$("#startDate").val(),
				endDate:$("#endDate").val(),
				employeesNum:$("#employeesNum").val(),
				startMoney:$("#startMoney").val(),
				endMoney:$("#endMoney").val(),
				company:$("#company").val(),
				department:$("#department").val(),
				email:$("#email").val(),
				personalRemark:$("#personalRemark").val(),
				fcreateuserName:$("#fcreateuserName").val(),
				employeesName:$("#employeesName").val(),
				status:$("#status").val()
				});
			})
			$("#resetBtn").click(function(){
			$("#accountrNum,#startDate,#endDate,#employeesNum,#startMoney,#endMoney").val("");
			$("#company,#department,#email,#personalRemark,#fcreateuserName,#status,#employeesName").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>/accountr/accountrAction!queryPageJson',
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
				frozenColumns:[[
	                {field:'ck',checkbox:true}
				]],
				columns: [[
				      {field:'accountrNum',title:'报销信息编号',width:fixColumnWidth(0.07000)},
					  {field:'accountrDate',title:'报销日期',width:fixColumnWidth(0.06000),
					     formatter: function(val) {
			  				return val.substring(0,val.indexOf("T"));
					   	  }
					  },
					  {field:'employeesNum',title:'员工工号',width:fixColumnWidth(0.04000)},
					  {field:'employeesName',title:'姓名',width:fixColumnWidth(0.04000)},
					  {field:'accountrMoney',title:'报销金额',width:fixColumnWidth(0.05000)},
					  {field:'company',title:'公司',width:fixColumnWidth(0.03000)},
					  {field:'department',title:'部门',width:fixColumnWidth(0.04000)},
					  {field:'email',title:'邮箱',width:fixColumnWidth(0.04000)},
					  {field:'personalRemark',title:'备注',width:fixColumnWidth(0.04000)},
					  {field:'fcreateuserName',title:'创建人',width:fixColumnWidth(0.04000)},
					  {field:'status',title:'发送状态',width:fixColumnWidth(0.0400),
					  		formatter:function(val){
					  		   if(val=="0"){
					  		      return "未发送";
					  		   }else if(val=="1"){
					  		      return "已发送";
					  		   }else{
					  		   	  return "发送失败";
					  		   }
					 		}
					  },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.09000),
						   formatter: function(val,row) {
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"报销信息\",\"edit\","+row.fid+")' >编辑</a></span>"
						   		+"&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.fid+")' >删除</a>";
						   }
					}
					
				]],
				//下面 定义 分页配置 ：
				pageSize:10,
				pageList:[10,20,50,100],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
			 	onLoadSuccess: omitLongData()  
			});
			
			$('#tt').datagrid({  
   			 rowStyler:function(index,row){  
        	 if (row.lastupdaterId != undefined){  
           		 return 'background-color:pink;color:blue;font-weight:bold;';  
      		  }  
   			 }  
			});
		$("#tt").datagrid({ 
    	idField: 'fid', 
    	view: fileview 
 		});
 		$("#tt").datagrid({ 
    		onCheckAll:function(){
    		 addcheckItem();
    		},
    		onCheck:function(){
    		addcheckItem();
    		},
    		onUncheckAll:function(rows){
    	   //var rows = $('#tt').datagrid('getCheckbox');
    	  // alert(rows[0].fid);
    		removeAllItem(rows);
    		},
    		onUncheck:function(rowIndex, rowData){
    		removeSingleItem(rowIndex, rowData);
    		}
 		});
 	var fileview = $.extend({}, $.fn.datagrid.defaults.view, { onAfterRender: function (target) { ischeckItem(); } });
	});
	function ischeckItem() {
       for (var i = 0; i < checkedItems.length; i++) {
           $('#tt').datagrid('selectRecord', checkedItems[i]); //根据id选中行 
           
        }
   	 }
	function addcheckItem() {
        var row = $('#tt').datagrid('getChecked');
        for (var i = 0; i < row.length; i++) {
            if (findCheckedItem(row[i].fid) == -1) {
                checkedItems.push(row[i].fid);
            }
        }
    }
function findCheckedItem(ID) {
        for (var i = 0; i < checkedItems.length; i++) {
            if (checkedItems[i] == ID)
             return i;
        }
        return -1;
    }


function removeAllItem(rows) {
       for (var i = 0; i < rows.length; i++) {
           var k = findCheckedItem(rows[i].fid);
           if (k != -1) {
                checkedItems.splice(k, 1);
           }
       }
    }
 function removeSingleItem(rowIndex, rowData) {
        var k = findCheckedItem(rowData.fid);
        if (k != -1) {
            checkedItems.splice(k, 1);
        }
    }
			
		//打开新选项卡
function importExcelValidation(){
	var file = document.getElementById('excelFile');
	var aa = getPath(file);
	if(aa==''){
		$.messager.alert("提示","请选择文件","info");	
 	}else if(!isExcel(file.value)){
		$.messager.alert("提示","请选择excel类型文件","info");	
 	}else{
 		$("#nn").val(file.value);
 		mask("正在校验数据，请稍候...");
 		$("#myform").ajaxSubmit({
 			type: "POST",
 			url:"<%=basePath%>/accountr/accountrAction!importExcelValidation",
 			resetForm: false,
 			success:function(json){//文件上传成功后执行,json
 				unmask();
 				importExcel(json);
 				//$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 			//	$("#tt").datagrid("reload");  			
 				//});
 			
 	      }
     });	
    return false;//防止刷新
  }
}
function importExcel(json){
$.messager.confirm('提示',toJson(json).message, function(r){
if(r){
    mask("正在导入数据，请稍候...");
	 $("#myform").ajaxSubmit({
 			type: "POST",
 			url:"<%=basePath%>/accountr/accountrAction!importExcel",
 			resetForm: true,
 			success:function(json){//文件上传成功后执行,json
 				$.messager.alert("提示",toJson(json).message,toJson(json).success == "true"?"info":"error",function(){
	 				unmask(); 
	 				$("#tt").datagrid("reload");  			
 				});
 	      }
     });
    }	
  });
}
var timer=null;
function reissueEmail(status){
 mask("邮件发送中，请稍候...")
 if(status=="reissue"){
   timer = setInterval(reissue , 100);
  }else{
   timer = setInterval(batchEmail , 1000);
  }
}
//补发邮件
function reissue(){
clearInterval(timer);
  //var row = $('#tt').datagrid('getChecked');

  var fids="";
  for(var i=0;i<checkedItems.length;i++){
    fids+=checkedItems[i]+",";
  }
  alert(fids);
  if(fids != ""){
  $.ajax({
   		type:'POST',
        dataType:'text',
		url: "<%=basePath%>/accountr/accountrAction!reissueEmail?fid="+fids.substring(0,fids.length-1)+"&date"+new Date(),
		async: false,
		success:function(json){
		    $.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
				toJson(json).success == "true"?"info":"error",function(){
				 unmask();
				$("#tt").datagrid("reload");			
				});
 		}
	});
  }else{
  	$.messager.alert("提示","请选择报销信息","info");
  	unmask();
  }
}

//批量发送邮件
function batchEmail(){
clearInterval(timer);
  var startDate=$("#startDate").val();
  var endDate=$("#endDate").val();
  //var row = $('#tt').datagrid('getChecked');
  var fids="";
  for(var i=0;i<checkedItems.length;i++){
    fids+=checkedItems[i]+",";
  }
  $.ajax({
       type:'POST',
        dataType:'text',
		url: "<%=basePath%>/accountr/accountrAction!batchEmail?fid="+fids.substring(0,fids.length-1),
		async: false,
		data:{'startDate':$("#startDate").val(),'endDate':$("#endDate").val(),'accountrNum':$("#accountrNum").val(),
			  'employeesNum':$("#employeesNum").val(),'startMoney':$("#startMoney").val(),'endMoney':$("#endMoney").val(),
			  'company':$("#company").val(),'department':$("#department").val(),'email':$("#email").val(),
			  'personalRemark':$("#personalRemark").val(),'fcreateuserName':$("#fcreateuserName").val(),'status':$("#status").val(),
			  'employeesName':$("#employeesName").val()},
		success:function(json){
		    $.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
				toJson(json).success == "true"?"info":"error",function(){
				unmask();
				$("#tt").datagrid("reload");
			});
			
 		}
	});
}
</script>
	</head>
	<body>
		<div class="search">
  		<form action="<%=basePath%>/accountr/accountrAction!queryPageJson" method="post" id="form">
			<table id="abc" onkeyup="bindQuery();">
				<tr><td class="s-t">报销信息编号</td>
				<td><input class="s-text" type="text" id="accountrNum" name="accountrNum" value=""></td>
				<td class="s-t">报销日期</td>
				<td><input type="text" id="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="begin" value=""></td>
			    <td class="s-t">至</td>
			    <td><input type="text" id="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
			    	class="s-text Wdate" name="end" value=""></td>
			    <td class="s-t">员工工号</td>
				<td><input class="s-text" type="text" id="employeesNum" name="employeesNum" value=""></td>
				</tr>
				 <tr>
				<td class="s-t">创建人姓名</td>
				<td><input class="s-text" type="text" id="fcreateuserName" name="fcreateuserName" value=""></td>
				<td class="s-t">报销金额</td>
				<td><input type="text" id="startMoney" class="s-text" name="startMoney" value=""></td>
			    <td class="s-t">至</td>
			    <td><input type="text" id="endMoney" class="s-text" name="endMoney" value=""></td>
				<td class="s-t">邮箱</td>
				<td><input class="s-text" type="text" id="email" name="email" value=""></td>
				</tr>
				<tr>
				<td class="s-t">所属公司</td>
				<td><input class="s-text" type="text" id="company" name="company" value=""></td>
				<td class="s-t">部门</td>
				<td><input class="s-text" type="text" id="department" name="department" value=""></td>
				<td class="s-t">备注</td>
				<td><input class="s-text" type="text" id="personalRemark" name="personalRemark" value=""></td>
				<td class="s-t">发送状态</td>
				<td><select id="status" name="status" onchange="changeQyery();">
				  <option value="">全部</option><option value="0">未发送</option><option value="2">发送失败</option><option value="1">已发送</option>
				</select></td>
				</tr>
				<tr>
				<td class="s-t">员工姓名</td>
				<td><input class="s-text" type="text" id="employeesName" name="employeesName" value=""></td>
				  <td colspan="2">
				      <a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
					<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
				   </td>
				</tr>
			</table>
			<br/>
		</form>
		<form action=""  method="post" name="myform" id="myform" enctype="multipart/form-data">
             <table>
            <tr>
               <td class="s-t">选择报销信息</td>
                   <td colspan="6">
                      <input type="file" id="excelFile" name="excelFile" />
                      <input id="nn" name="filePath" type="hidden" />
                      <a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true
                       class="but-remove" onClick="importExcelValidation()">导入</a>
                    
                       <c:forEach items="${sessionScope.personrole}" var="roleid" >
                         <c:if test="${roleid != '363'}">
                       		<a href="javascript:void(0)" id="batch" onclick="reissueEmail('batch')" class="but-remove">批量发送</a>
                       	</c:if>
                       </c:forEach>
                        <a href="javascript:void(0)" id="reissue" onclick="reissueEmail('reissue')" class="but-remove">补发</a>
           	       </td>
           	  </tr> 
		</table>
		</form>
		</div>
		<div class="search-list">
			<span class="list-title">报销信息列表</span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div>
		<div id="toolbar">
  			<a onclick="openTab(this,'报销信息','edit')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</div>
		<input type="hidden" id="editURL" value="<%=basePath%>/accountr/accountrAction!edit?accountr.fid="/>
		<input type="hidden" id="deleteURL" value="<%=basePath%>/accountr/accountrAction!deleteAccountr?accountr.fid="/>
	</body>
</html>
