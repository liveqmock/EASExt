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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>房屋合同信息汇总 </title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript">
	$(document).ready(function(){
		$("#orgName,#lastCostCenter,#city,#contractNum,#signatory2,#monthMoney").validatebox({validType: 'speci'});
	})

	$(function() {
   			/******************付款方式*************************/
		
			$.ajax({
				   type: "POST",
				   url: "<%=basePath%>/rent/financeRentContract!getAllDictionarys",
				   async:false,//发送同步请求
				   dataType: "json",
				   success: function(data){
			   		   for(var i=0;i<data.length;i++){
			       			$("#paymentCycle").append($("<option value="+data[i].key +">"+data[i].value +"</option>"));
					   }
				   }
			});
			$("#exportExcel").click(function(){
			 location.href="<%=basePath %>/rent/financeRentContract!expExcel?orgName="+$('#orgName').val()+"&startDate="+$('#startDate').val()+"&paymentCycle="+$('#paymentCycle').val()+
			 "&lastCostCenter="+$('#lastCostCenter').val()+"&endDate="+$('#endDate').val()+"&monthMoney="+$('#monthMoney').val()+"&city="+$('#city').val()+"&contractNum="+
			 $('#contractNum').val()+"&signatory2="+$('#signatory2').val()+"&status="+$('#status').val();
			})
   			/******************付款方式*************************/
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				//页面验证通过之后，验证开始时间小于结束时间
				var startTimeValid = $("#startDate").val();//开始时间
				var endTimeValid = $("#endDate").val();//结束时间
				if(startTimeValid!="" && endTimeValid!=""){
					if(startTimeValid>endTimeValid){
						$.messager.alert("提示","开始时间不能大于结束时间");
								return;
							}
						}
				
				$("#tt").datagrid("load",{
					orgName:$("#orgName").val(),
					startDate:$("#startDate").val(),
					paymentCycle:$("#paymentCycle").val(),
					lastCostCenter:$("#lastCostCenter").val(),
					endDate:$("#endDate").val(),
					monthMoney:$("#monthMoney").val(),
					city:$("#city").val(),
					contractNum:$("#contractNum").val(),
					signatory2:$("#signatory2").val(),
					status:$("#status").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#orgName,#startDate,#paymentCycle,#lastCostCenter,#endDate,#monthMoney,#city,#contractNum,#signatory2,#status").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>rent/financeRentContract!queryPageJson',
				title: "",
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
<%--   					  {field:'ck',checkbox:true},	--%>
					  {field:'orgName',title:'使用部门',width:fixColumnWidth(0.0600)},
					  {field:'city',title:'地区',width:fixColumnWidth(0.0300)},
					  {field:'officeAdd',title:'办公室座落地点',width:fixColumnWidth(0.1000)},
					  {field:'lastCostCenter',title:'成本中心',width:fixColumnWidth(0.0900)},
<%--					  {field:'signatory',title:'签约方',width:'150'},--%>
					  {field:'paymentCycle',title:'付款周期',width:fixColumnWidth(0.0400),
						formatter:function(val){
						  if(val == "month") val="每月";
							else if(val == "twoMonths") val="两月";
							else if(val == "quarter") val="季度";
							else if(val == "fourMonths") val="四月";
							else if(val == "fiveMonths") val="五月";
							else if(val == "halfYear") val="半年";
							else if(val == "sevenMonths") val="七月";
							else if(val == "eightMonths") val="八月";
							else if(val == "nineMonths") val="九月";
							else if(val == "tenMonths") val="十月";
							else if(val == "elevenyear") val="十一月";
							else if(val == "year") val="一年";
							return val;
						}
					  },
					  {field:'payCount',title:'付款次数',width:fixColumnWidth(0.0400)},
					  {field:'rentStartTime',title:'开始日期',width:fixColumnWidth(0.0600),
						  formatter:function(val){if(val!=null && val!='') return val.substring(0,val.lastIndexOf("T"));}},
					  {field:'rentEndTime',title:'结束日期',width:fixColumnWidth(0.0600),
							  formatter:function(val){if(val!=null && val!='') return val.substring(0,val.lastIndexOf("T"));}},
<%--					  {field:'payFirstTime',title:'首付日期',width:'100',align:'center'},--%>
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1100),       
				   	  	formatter: function(val,node) {
					   	  	var result = "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"合同信息列表\",\"view\","+node.id+")' >查看</a>&nbsp;</span>";
					   	 	if(${isgroupleader}!=1 && ${isgroupleader}!=0 ){
					   	 		result += "&nbsp;<a class=\"operation-a\" id=\"editbut\" href='javascript:void(0)' onclick='openTab(this,\"合同信息列表\",\"edit\","+node.id+")' >编辑</a></span>"
					  				+"&nbsp;<a class=\"operation-d\" id=\"deletebut\" href='javascript:void(0)' onclick='deleteRecord("+node.id+")' >删除</a>";
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
<%--			showMessage(new Date('2013','09','16'),'信息',--%
<%--					'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈！',5000,'slide');--%>
           if(${isgroupleader}==1 ||${isgroupleader}==0 ) $("#insertBtn, #importExcel, #wenzi, #excelFile").css("display","none");//组长和组员不能看到导入和新增按钮
           
   		});
   </script>
</head>
<body>
  <div class="search">
	<table onkeyup="bindQuery();">
		<tr><td class="s-t">使用部门</td><td><input class="s-text" type="text" id="orgName" value=""></td>
			<td class="s-t">公司</td><td><input class="s-text" type="text" id="signatory2" value=""></td>
			<td class="s-t">创建日期从</td><td><input class="s-text Wdate" type="text" id="startDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/></td>
			<td class="s-t">创建日期至</td><td><input class="s-text Wdate" type="text" id="endDate" onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" /></td>
		</tr>
        <tr>
        	<td class="s-t">月租金</td><td><input class="s-text" type="text" id="monthMoney" value=""></td>
			<td class="s-t">付款周期</td><td><select id="paymentCycle" onchange="changeQyery();"> <option value="">--全部--</option> </select> 
			<td class="s-t">状态</td>
            <td>
				<select id="status">
				 <option value="">--全部--</option>
 					 <option value ="0">在用</option>
 				 	<option value ="1">停租</option>
 				 	<option value="2">转租</option>
 				 	<option value="3">变更</option>
			</select>
			</td>
        	<td class="s-t">成本中心</td><td><input class="s-text" type="text" id="lastCostCenter" value=""></td>
        </tr>
		<tr>
			<td class="s-t">地区</td><td><input class="s-text" type="text" id="city" value=""></td>
			
			<td class="s-t">合同编号</td><td><input class="s-text" type="text" id="contractNum" value=""></td>
			<td colspan="4">
				<a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
			</td>
        </tr>
		 </table>
            <form action="<%=basePath %>/rent/financeRentContract!importExcel"  method="post" name="myform" id="myform" enctype="multipart/form-data">
              <table>
            <tr>
             <td class="s-t">选择合同信息</td>
               <td colspan='7'> 
                    <input type="file" id="excelFile" name="excelFile" />
                    <input id="nn" name="filePath" type="hidden" />
                    <a href="javascript:void(0)" name="importExcel" id="importExcel" class="but-remove" onclick="importExcelFun()">导入</a>
                    <a href="javascript:void(0)" name="exportExcel" id="exportExcel" class="but-remove">导出</a>
                </td>
          </tr>
    </table>
            </form>
       
 </div>
 <div class="search-list">
	<span class="list-title">合同信息列表</span>
	<table id = "tt"  toolbar="#toolbar"></table>
	<input type="hidden" id="userTypeId" value="<c:if test='${empty user.typeid}'>0</c:if><c:if test='${not empty user.typeid}'>${user.typeid}</c:if>"/>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/rent/financeRentContract!view?financeRentContract.id="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/rent/financeRentContract!edit?financeRentContract.id="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/rent/financeRentContract!delete?financeRentContract.id="/>
	 <div id="toolbar">
	<a href='javascript:void(0)' class="easyui-linkbutton" iconCls="icon-add" plain="true" onClick="openTab(this,'合同信息列表','edit');">新增</a>
  	</div>
 </div>	
</body>
</html>