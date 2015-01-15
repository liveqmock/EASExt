<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投三花名册</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#fnumber,#longfnumber,#fname,#fteam").validatebox({validType: 'speci'});
		
	})
		var checkedItems = [];
		$(function() {
			$("#queryBtn").click(function(){
				//页面验证通过之后，验证开始时间小于结束时间
				var fentrydate = $("#fentrydate").val();//入职日期从
				var fentrydate2 = $("#fentrydate2").val();//入职日期至
				var fleavedate = $("#fleavedate").val();//离职日期从
				var fleavedate2 = $("#fleavedate2").val();//离职日期至
				if(fentrydate!="" && fentrydate2!=""){
					if(fentrydate>fentrydate2){
						$.messager.alert("提示","时间段起始日期应小于终止日期");
								return;
							}
						}
				if(fleavedate!="" && fleavedate2!=""){
					if(fleavedate>fleavedate2){
						$.messager.alert("提示","时间段起始日期应小于终止日期");
								return;
							}
						}

				$('#tt').datagrid('load',{  
					fnumber: $('#fnumber').val(),
					longfnumber: $('#longfnumber').val(),
					fentrydate: $('#fentrydate').val(),
					fentrydate2: $('#fentrydate2').val(),
					fname: $('#fname').val(),
					fposition: $('#fpositionhidden').val(),
					fpersonstatus: $('#fpersonstatushidden').val(),
					foldfourunitpage: $('#foldfourunithidden').val(),
					fourunitpage: $('#fourunithidden').val(),
					fleavedate: $('#fleavedate').val(),
					fleavedate2: $('#fleavedate2').val(),
					fteam: $('#fteam').val(),
					datatype:$('#datatype').val()
			        }); 
			})

			$("#resetBtn").click(function(){
			$("#fnumber,#longfnumber,#fentrydate,#fentrydate2,#fname,#fposition,#fpositionhidden,#fpersonstatus,#fpersonstatushidden,#foldfourunit,#foldfourunithidden,#fourunit,#fourunithidden,#fleavedate,#fleavedate2,#fteam,#datatype").val("");
			})
			
			$("#exportExcel").click(function(){
				var url = "<%=basePath %>roster/rosterAction!expExcel?fnumber="+$('#fnumber').val()+"&longfnumber="+$('#longfnumber').val()+"&fentrydate="+$('#fentrydate').val()
				+"&fentrydate2="+$('#fentrydate2').val()+"&fname="+$('#fname').val()+"&fposition="+$('#fpositionhidden').val()+"&fpersonstatus="+$('#fpersonstatushidden').val()+
				"&foldfourunitpage="+$('#foldfourunithidden').val()+"&fourunitpage="+$('#fourunithidden').val()+"&fleavedate="+$('#fleavedate').val()+
				"&fleavedate2="+$('#fleavedate2').val()+"&fteam="+$('#fteam').val()+"&datatype="+$('#datatype').val();
			 	var fireFox = 0;
	   	   		if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
	   	   			fireFox = 1;
	   	   	   	}
	   	   	   	url += "&fireFox="+fireFox;
				location.href=url;
			 
			})
			
			$("#exportExcelSimple").click(function(){
			 var url ="<%=basePath %>roster/rosterAction!expExcelSimple?fnumber="+$('#fnumber').val()+"&longfnumber="+$('#longfnumber').val()+"&fentrydate="+$('#fentrydate').val()
				+"&fentrydate2="+$('#fentrydate2').val()+"&fname="+$('#fname').val()+"&fposition="+$('#fpositionhidden').val()+"&fpersonstatus="+$('#fpersonstatushidden').val()+
				"&foldfourunitpage="+$('#foldfourunithidden').val()+"&fourunitpage="+$('#fourunithidden').val()+"&fleavedate="+$('#fleavedate').val()+
				"&fleavedate2="+$('#fleavedate2').val()+"&fteam="+$('#fteam').val()+"&datatype="+$('#datatype').val();
				 var fireFox = 0;
	   	   		if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
	   	   			fireFox = 1;
	   	   	   	}
	   	   	   	url += "&fireFox="+fireFox;
				location.href=url;
			})
			
			<c:forEach items="${sessionScope.personrole}" var="personrole" >
         		 	<c:if test="${personrole == '588'}">   //投三人事岗 删除 、导入 、简版导出按钮看不到
         		 		$("#deletebut").css("display","none");
         		 		$("#importExcel").css("display","none");
         		 		$("#exportExcelSimple").css("display","none");
        			</c:if>
        			<c:if test="${personrole == '589'}">  //投三数据岗 新增、修改、批量修改、导入、删除按钮看不到
        				$("#addbut, #editbut, #importExcel, #deletebut,#manyeditbutton").css("display","none");
    				</c:if>
       		</c:forEach>
			
			
			
				$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>roster/rosterAction!queryPageJson',
					title: "",
					width: ($(window).width()-30),
					scrollbarSize:0,
					height: 'auto',
					nowrap: false,  //  是否在一行显示数据
					striped: true,   //  是否 显示 斑马线  
					fitColumns: true,  // 自动填充 列 宽
					collapsible: true,   // 是否 有滑动效果 
					loadMsg: '数据连接中.....',  //加载页面时候的提示消息
					remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
					sortOrder: 'desc',    // 排序 方法 
					rownumbers:true,
					singleSelect:false, 
				 <c:forEach items="${sessionScope.personrole}" var="personrole" >
		         	 <c:if test="${personrole == '588'||personrole == '587' || personrole == '568'}"> //投三人事岗、管理员
					frozenColumns:[[
					                {field:'ck',checkbox:true}
								]],
					</c:if>
		         </c:forEach>	
								
					columns: [[
						  {field:'fnumber',title:'5位编号',width:fixColumnWidth(0.02400)},
						  {field:'longfnumber',title:'12位员工编号',width:fixColumnWidth(0.05500)},
						  {field:'fname',title:'姓名',width:fixColumnWidth(0.02400)},
						  {field:'fpositionname',title:'岗位',width:fixColumnWidth(0.03500)},
						  {field:'foldfourunitname',title:'原四级部门',width:fixColumnWidth(0.03200)},
						  {field:'ffiveunitname',title:'五级部门',width:fixColumnWidth(0.02000)},
						  {field:'fpersonstatusname',title:'人员状态',width:fixColumnWidth(0.0200)},
						  {field:'fentrydate',title:'入职日期',width:fixColumnWidth(0.04000),
								formatter:function(val){
									if(val!=null && val!="")
									return val.substring(0,val.indexOf("T"));
								}
							  },
						  {field:'fleavedate',title:'离职日期',width:fixColumnWidth(0.04000),
								  formatter:function(val){
									if(val!=null && val!="")
									return val.substring(0,val.indexOf("T"));
								}
								  },
						  {field:'operation',title:'操作',width:fixColumnWidth(0.09500),
							   formatter: function(val,row) {
							   var result =  "<span><a class=\"operation-a\" href='javascript:void(0)' id=\"viewbut\" onclick='openTab(this,\"人事花名册\",\"view\","+row.fid+")'>查看</a></span>";
							   <c:forEach items="${sessionScope.personrole}" var="personrole" >
					         	<c:if test="${personrole == '588'}"> //投三人事岗
					         		result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" id=\"editbut\" href='javascript:void(0)' onclick='openTab(this,\"人事花名册\",\"edit\","+row.fid+")'>编辑</a></span>"; 		
					        	</c:if>
					        	<c:if test="${personrole == '587' || personrole == '568'}">  //投三一般管理员、超级管理员
					        		result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" id=\"editbut\" href='javascript:void(0)' onclick='openTab(this,\"人事花名册\",\"edit\","+row.fid+")'>编辑</a></span>"+
						        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-d\" id=\"deletebut\" href='javascript:void(0)' onclick='deleteRecord("+row.fid+")'>删除</a></span>";
					    		</c:if>
					       		</c:forEach>	
					       		return result;	
							   }
				   		  }
					]],
					 //将离职人员信息表格设置成灰色
					 rowStyler:function(index,row){  
		   			 if (row.fleavedate != null && row.fleavedate!=""){  
		      		 return 'background-color:#D1D1D1;';  
		 		  }  
					 }, 
					//下面 定义 分页配置 ：
					pageSize:15,
					pageList:[5,10,15,20],
					pagination:true,   // 是否 要分页 
					pageNumber:1//设置初始页为1
					//onLoadSuccess: omitLongData()
				});
				//下面这个方法 用于 配置  分页的信息 
				displayMsg($('#tt'));

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

		//导入功能
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
		 			url:"<%=basePath%>roster/rosterAction!importExcelValidation",
		 			resetForm: false,
		 			success:function(json){//文件上传成功后执行,json
		 				unmask();
		 				importExcel(json);
		 	      }
		     });	
		    return false;//防止刷新
		  }
		}
		function importExcel(json){
		//如果有错误数据，打开错误数据行号页面
		if(toJson(json).sign == "1"){
			$.messager.confirm('提示',toJson(json).message, function(r){
			if(r){
				openDataTab(this,"人事花名册","viewdata");
				}
				});
			}else{
		$.messager.confirm('提示',toJson(json).message, function(r){
		if(r){
		    mask("正在导入数据，请稍候...");
			 $("#myform").ajaxSubmit({
		 			type: "POST",
		 			url:"<%=basePath%>roster/rosterAction!importExcel",
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
		}

	function getfids(){
		var fids="";
		for(var i=0;i<checkedItems.length;i++){
		   fids+=checkedItems[i]+",";
		  	}
	  	if(fids != 'null' && fids.length>0){
	  		openDataTab(this,'人事花名册','manyedit',fids);
		  	}
	  	else{
	  		$.messager.alert("提示","请选择您要修改的数据.");
		  	}
		}
	//获得弹出框传回的值 ,inputid文本框id,value复选框值,text复选框文本值
	function setCheckBoxValue(inputid,value,text){
		$("#"+inputid).val(text);
		$("#"+inputid+"hidden").val(value);
		}
	
	</script>

  </head>
  <body>
 <div class="search">
		<table onKeyUp="bindQuery();">
			<tr>
				<td class="s-t">5位员工编号</td><td ><input class="s-text" type="text" id="fnumber" value=""></td>
				<td class="s-t">12位员工编号</td><td ><input class="s-text" type="text" id="longfnumber" value=""></td>
				<td class="s-t">姓名</td><td ><input class="s-text" type="text" id="fname" value=""></td>
				<td class="s-t">原四级部门</td><td >
				<input class="s-text" type="text" id="foldfourunit" value="" onclick="openDialog('oldfourunit','foldfourunit')">
				<input type="hidden" id="foldfourunithidden" value="">
				</td>
			</tr>
			<tr>
				<td class="s-t">人员状态</td><td >
				<input class="s-text" type="text" id="fpersonstatus" value="" onclick="openDialog('pstatus','fpersonstatus')">
				<input type="hidden" id="fpersonstatushidden" value="">
				</td>
				<td class="s-t">入职日期从</td><td ><input class="s-text Wdate" type="text" id="fentrydate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"></td>
				<td class="s-t">至</td><td ><input class="s-text Wdate" type="text" id="fentrydate2" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"></td>
				<td class="s-t">四级部门</td><td>
			 	<input class="s-text" type="text" id="fourunit" value="" onclick="openDialog('fourunit','fourunit')">
			 	<input type="hidden" id="fourunithidden" value="">
				</td>
			</tr>
			<tr>
				<td class="s-t">岗位</td><td >
				<input class="s-text" type="text" id="fposition" value="" onclick="openDialog('fposition','fposition')">
				<input type="hidden" id="fpositionhidden" value="">
				</td>
				<td class="s-t">离职日期从</td><td ><input class="s-text Wdate" type="text" id="fleavedate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"></td>
				<td class="s-t">至</td><td ><input class="s-text Wdate" type="text" id="fleavedate2" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:169px"></td>
				<td class="s-t">是否在职</td>
				<td>
					<select id="datatype">
						 <option value=""> </option>
 						 <option value ="0">在职</option>
 				 		 <option value ="1">离职</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="s-t">团队</td><td ><input class="s-text" type="text" id="fteam" value=""></td>
				<td colspan='3'>
				<a href="javascript:void(0)" id="queryBtn" class="but-search" >&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
				</td>
			</tr>
			 
		</table>
		<form action=""  method="post" name="myform" id="myform" enctype="multipart/form-data">
		 <table>
            <tr>
            <td class="s-t">&nbsp;&nbsp;花名册信息</td>
             <td colspan='3'>   
             <input type="file" id="excelFile" name="excelFile" />
             <input id="nn" name="filePath" type="hidden" />
              <a href="javascript:void(0)" name="importExcel" id="importExcel" class="but-remove" onclick="importExcelValidation()">导入</a>
              <a href="javascript:void(0)" name="exportExcel" id="exportExcel" class="but-remove">导出</a>
              <a href="javascript:void(0)" name="exportExcelSimple" id="exportExcelSimple" class="but-remove">简版导出</a></td>      
           </tr> 
		</table>
          </form>
</div>
 <div class="search-list">
 <span class="list-title">花名册信息</span>
	<table id = "tt" toolbar="#toolbar"></table></div>
	<div id="toolbar">
	<a onclick="openTab(this,'人事花名册','edit')" id="addbut" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	<a  onclick="getfids()"  class="easyui-linkbutton" iconCls="icon-edit" plain="true" id = "manyeditbutton">批量修改</a>
 	</div>
 	<input type="hidden" id="errorDataURL" value="<%=request.getContextPath()%>/roster/rosterAction!viewErrorData"/>
 	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/roster/rosterAction!viewRoster?roster.fid="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/roster/rosterAction!edit?roster.fid="/>
	<input type="hidden" id="manyeditURL" value="<%=request.getContextPath()%>/roster/rosterAction!manyeditinfo?fids="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/roster/rosterAction!delete?roster.fid="/>
	<input type="hidden" id="viewCheckBox" value="<%=request.getContextPath()%>/pages/roster/choseCheckBox.jsp"/>

     <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true" iconCls="icon-save" style="width:650px;height:400px;padding:3px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	 </div>
     
  </body>
</html>