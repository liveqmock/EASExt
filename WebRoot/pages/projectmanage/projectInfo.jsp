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
    <title>项目信息列表</title>
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
	       //加载出借方式
	       var json = sendAjax("<%=basePath%>/project/projectAction!selectLoanway");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].FID+" >"+json[i].LOANWAY +"</option>");
				$("#selectloanway").append($opt);
			}
			//加载还款方式
			var json = sendAjax("<%=basePath%>/project/projectAction!selectSituationname");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].FID+" >"+json[i].SITUATIONNAME +"</option>");
				$("#Situationname").append($opt);
			}
			//加载项目状态
			var json = sendAjax("<%=basePath%>/project/projectAction!selectState");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].FID+" >"+json[i].STATENAME +"</option>");
				$("#state").append($opt);
			}
			//加载贷后管理负责人(PM)
			var json = sendAjax("<%=basePath%>/project/projectAction!selectPm");
			for(var i=0;i<json.length;i++){
				var $opt = $("<option value="+json[i].USERID+" >"+json[i].PMNAME +"</option>");
				$("#pm").append($opt);
			}
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
				loanNumber:$("#loanNumber").val(),
				companyName:$("#companyName").val(),
				pmUserId:$("#pm").val(),
				selectloanway:$("#selectloanway").val(),
				startloanTime:$("#startloanTime").val(),
				endloanTime:$("#endloanTime").val(),
				startexpireTime:$("#startexpireTime").val(),
				endexpireTime:$("#endexpireTime").val(),
				mlyinterest:$("#mlyinterest").val(),
				mMangExpense:$("#mMangExpense").val(),
				authentication:$("#authentication").val(),
				repaymentWayId:$("#Situationname").val(),
				startinterestTime:$("#startinterestTime").val(),
				endinterestTime:$("#endinterestTime").val(),
				lenders:$("#lenders").val(),
				borrower:$("#borrower").val(),
				repaymentAccount:$("#repaymentAccount").val(),
				bankAccount:$("#bankAccount").val(),
				account:$("#account").val(),
				starRepaymentMoney:$("#starRepaymentMoney").val(),
				endRepaymentMoney:$("#endRepaymentMoney").val(),
				term:$("#term").val(),
				starMoney:$("#starMoney").val(),
				endMoney:$("#endMoney").val(),
				state:$("#state").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#loanNumber,#companyName,#pm,#selectloanway,#startloanTime,#endloanTime,#startexpireTime,#endexpireTime").val("");
				$("#mlyinterest,#mMangExpense,#authentication,#Situationname,#startinterestTime,#endinterestTime,#lenders").val("");
				$("#borrower,#repaymentAccount,#bankAccount,#account,#starRepaymentMoney,#endRepaymentMoney,#term,#starMoney,#endMoney,#state").val("");
			})
			$("#exportExcel").click(function(){
			 	location.href="<%=basePath %>project/projectAction!expExcel?loanNumber="+$('#loanNumber').val()+
			 	"&companyName="+$('#companyName').val()+
			 	"&pmUserId="+$('#pm').val()+
			 	"&selectloanway="+$('#selectloanway').val()+
			 	"&startloanTime="+$('#startloanTime').val()+
			 	"&endloanTime="+$('#endloanTime').val()+
			 	"&startexpireTime="+$('#startexpireTime').val()+
			 	"&endexpireTime="+$('#endexpireTime').val()+
			 	"&mlyinterest="+$('#mlyinterest').val()+
			 	"&mMangExpense="+$('#mMangExpense').val()+
			 	"&authentication="+$('#authentication').val()+
			 	"&repaymentWayId="+$('#Situationname').val()+
			 	"&startinterestTime="+$('#startinterestTime').val()+
			 	"&endinterestTime="+$('#endinterestTime').val()+
			 	"&lenders="+$('#lenders').val()+
			 	"&borrower="+$('#borrower').val()+
			 	"&repaymentAccount="+$('#repaymentAccount').val()+
			 	"&bankAccount="+$('#bankAccount').val()+
			 	"&account="+$('#account').val()+
			 	"&starRepaymentMoney="+$('#starRepaymentMoney').val()+
			 	"&endRepaymentMoney="+$('#endRepaymentMoney').val()+
			 	"&term="+$('#term').val()+
			 	"&starMoney="+$('#starMoney').val()+
			 	"&state="+$('#state').val()+
			 	"&endMoney="+$('#endMoney').val();
			})
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>/project/projectAction!queryPageJson',
				width: 'auto',
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: false,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false,
				columns: [[
				      {field:'LOANNUMBER',title:'贷款编号',width:fixColumnWidth(0.17000)},
					  {field:'COMPANYNAME',title:'公司名称',width:fixColumnWidth(0.16000)},
					  {field:'HEAD',title:'贷后管理负责人(PM)',width:fixColumnWidth(0.14000)},
					  {field:'LOANWAY',title:'出借方式',width:fixColumnWidth(0.14000)},
					  {field:'LOANTIME',title:'出借日',width:fixColumnWidth(0.15000),
					   		formatter:function(val){
                              if( "" != val && null != val){
                               return val.substring(0,val.indexOf("T"));
                              }
					   		}
					  },
					  {field:'EXPIRETIME',title:'到期日',width:fixColumnWidth(0.13000),
					   		formatter:function(val){
					   		   if( "" != val && null != val){
					   		    return val.substring(0,val.indexOf("T"));
					   		   }
					   		}
					  },
					  {field:'TERM',title:'期限(月)',width:fixColumnWidth(0.14000)},
					  {field:'LNTERESTTIMELIST',title:'利息返还日',width:fixColumnWidth(0.18000)},
					  {field:'MONEY',title:'金额(元)',width:fixColumnWidth(0.14000)},
					  {field:'MLYINTEREST',title:'月利率(%)',width:fixColumnWidth(0.14000)},
					  {field:'MMANGEXPENSE',title:'月管理费(%)',width:fixColumnWidth(0.14000)},
					  {field:'AUTHENTICATION',title:'认股权证',width:fixColumnWidth(0.14000)},
					  {field:'LENDERS',title:'出借方',width:fixColumnWidth(0.14000)},
					  {field:'BORROWER',title:'借款方',width:fixColumnWidth(0.14000)},
					  {field:'SITUATIONNAME',title:'还款方式',width:fixColumnWidth(0.14000)},
					  {field:'REPAYMENTACCOUNT',title:'还款账户',width:fixColumnWidth(0.24000),
					     formatter:function(val){
					     	 if( "" != val && null != val){
					      		return val.replace(/;/g,"<br/>");
					      	  }
					     }
					  },
					  {field:'BANKACCOUNT',title:'开户行',width:fixColumnWidth(0.24000),
					      formatter:function(val){
					     	 if( "" != val && null != val){
					      		return val.replace(/;/g,"<br/>");
					      	  }
					     }
					  },
					  {field:'ACCOUNT',title:'账号',width:fixColumnWidth(0.20000),
					       formatter:function(val){
					     	 if( "" != val && null != val){
					      		return val.replace(/;/g,"<br/>");
					      	  }
					     }
					  },
					  {field:'REPAYMENTMONEY',title:'账户对应还款额(元)',width:fixColumnWidth(0.140000),
					  		formatter:function(val){
					     	   if( "" != val && null != val){
					      		return val.replace(/;/g,"<br/>");
					      	    }
					      }
					   },
					  {field:'STATENAME',title:'状态',width:fixColumnWidth(0.14000)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.40000),
						   formatter: function(val,row) {
						   	 <c:forEach items="${sessionScope.personrole}" var="roleid" >
            							<c:if test="${roleid != '569'}">
                                          return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"项目信息\",\"edit\","+row.FID+")' >编辑</a></span>&nbsp;"+
						   						 "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"合同信息\",\"view\","+row.FID+")' >查看合同信息</a></span>&nbsp;"+
						   						 "<span><a class=\"operation-a\"  href='javascript:void(0)' onclick='openViewTab(this,\"利息返还日\",\"viewIinterestTime\","+row.FID+")'>查看利息返还日</a></span>&nbsp"+
						   						 "&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.FID+")' >删除</a>&nbsp;"+
						   						 "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openViewTab(this,\"合同文件\",\"viewFile\","+row.FID+")' >合同文件上传下载</a></span>&nbsp;";
           								 </c:if>
           								   return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"合同信息\",\"view\","+row.FID+")' >查看合同信息</a></span>&nbsp;"+
           								          "&nbsp;<a class=\"operation-d\" href='javascript:void(0)' onclick='deleteRecord("+row.FID+")' >删除</a>&nbsp;"+
           								          "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openViewTab(this,\"合同文件\",\"viewFile\","+row.FID+")' >合同文件上传下载</a></span>&nbsp;";
       						</c:forEach>
						   		
						   }
					}
					
				]],
				//下面 定义 分页配置 ：
				pageSize:5,
				pageList:[5,15,20,25,30,50],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
			 	onLoadSuccess: omitLong()  
			});
	});
		   
	//查看利息返还日
   function openViewTab(btn,parentTitle,type,id){
	 	var me = $(btn);
		var url,title=parentTitle;
		switch (type) {
			case 'viewIinterestTime':
				url = $("#viewIinterestTime").val()+id;	title+="_详细信息";
				break;
			case 'viewFile':
				url = $("#viewFile").val()+id;	title+="_详细信息";
				break;	
     	}
     	
	  addTab(title, url);
	}
</script>
	</head>
	<body>
	
		<div class="search">
  		<form action="<%=basePath%>/project/projectAction!queryPageJson" method="post" id="form">
			<table id="abc" onkeyup="bindQuery();">
				<tr><td class="s-t">贷款编号</td>
				<td><input class="s-text" type="text" id="loanNumber" name="loanNumber" value=""></td>
				<td class="s-t">公司名称</td>
				<td><input class="s-text" type="text" id="companyName" name="companyName" value=""></td>
			    <td class="s-t">贷后管理负责人</td>
				<td><select id="pm" onchange="changeQyery();"><option value="">全部</option></select></td>
				<td class="s-t">出借方式</td>
				<td><select id="selectloanway" onchange="changeQyery();"><option value="">全部</option></select></td>
				</tr>
				<tr>
				<td class="s-t">出借日</td>
				<td><input type="text" id="startloanTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="startloanTime" value=""></td>
				<td class="s-t">至</td>
				<td><input type="text" id="endloanTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="endloanTime" value=""></td>
			    <td class="s-t">到期日</td>
			      <td><input type="text" id="startexpireTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
			    	class="s-text Wdate" name="startexpireTime" value=""></td>
			    <td class="s-t">至</td>
				<td><input type="text" id="endexpireTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="endexpireTime" value=""></td>
				</tr>
				 <tr>
				<td class="s-t">月利率</td>
				<td><select id="mlyinterest" onchange="changeQyery();"><option value="">全部</option><option value="1">大于等于1%</option><option value="0">等于1%</option><option value="-1">小于等于1%</option></select></td>
				<td class="s-t">月管理费</td>
				<td><input type="text" id="mMangExpense" class="s-text" name="mMangExpense" value=""></td>
			    <td class="s-t">认股权证</td>
			    <td><input type="text" id="authentication" class="s-text" name="authentication" value=""></td>
				<td class="s-t">还款方式</td>
				<td><select id="Situationname" onchange="changeQyery();"><option value="">全部</option></select></td>
				</tr>
				<tr>
				<td class="s-t">利息返还日</td>
				<td><input type="text" id="startinterestTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
					class="s-text Wdate" name="startinterestTime" value=""></td>
			    <td class="s-t">至</td>
			    <td><input type="text" id="endinterestTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
			    	class="s-text Wdate" name="endinterestTime" value=""></td>
				 <td class="s-t">出借方</td>
			    <td><input type="text" id="lenders" class="s-text" name="lenders" value=""></td>
			     <td class="s-t">借贷方</td>
			    <td><input type="text" id="borrower" class="s-text" name="borrower" value=""></td>
				</tr>
				<tr>
				<td class="s-t">还款账户</td>
				<td><input class="s-text" type="text" id="repaymentAccount" name="repaymentAccount" value=""></td>
				<td class="s-t">开户行</td>
				<td><input class="s-text" type="text" id="bankAccount" name="bankAccount" value=""></td>
				<td class="s-t">账号</td>
				<td><input class="s-text" type="text" id="account" name="account" value=""></td>
				<td class="s-t">期限(月)</td>
			    <td><input class="s-text" type="text" id="term" name="term" value=""></td>
				</tr>
				<tr>
			    <td class="s-t">账户对应还款额</td>
				<td><input class="s-text" type="text" id="starRepaymentMoney" name="starRepaymentMoney" value=""></td>
				<td class="s-t">至</td>
				<td><input class="s-text" type="text" id="endRepaymentMoney" name="endRepaymentMoney" value=""></td>
			    <td class="s-t">金额</td>
				<td><input class="s-text" type="text" id="starMoney" name="starMoney" value=""></td>
				<td class="s-t">至</td>
				<td><input class="s-text" type="text" id="endMoney" name="endMoney" value=""></td>
				</tr>
				<tr>
				<td class="s-t">状态</td>
				<td><select id="state" onchange="changeQyery();"><option value="">全部</option></select></td>
				 <td colspan="3">
				      <a href="javascript:void(0)" id="queryBtn" class="but-search">&nbsp;</a>
					<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
                       <c:forEach items="${sessionScope.personrole}" var="roleid" >
                         <c:if test="${roleid != '569'}">
                       		<a href="javascript:void(0)" name="exportExcel" id="exportExcel" plain=true
                       class="but-remove" >导出</a>
                       	</c:if>
                       </c:forEach>
				   </td>
				</tr>
			</table>
			<br/>
		</form>
		</div>
		<div class="search-list">
			<span class="list-title">项目信息列表</span>
			<table id="tt" align="center" toolbar="#toolbar"></table>
		</div><!--
	 <s:form name="showForm">
	  <table style="display:block" bgcolor="#8E8E8E">
	      <tr id="div_applyInfo" style="display:block" bgcolor="#FFFFFF" >
             <td colspan="21">
                <iframe name="showFrame" id="showFrame"  frameBorder="0" width="100%" scrolling="no" onload="setIframHeight(this)" />
             </td>
          </tr>
	<table>
    </s:form>
		--><div id="toolbar">
  			<a onclick="openTab(this,'项目信息','edit')" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</div>
		<input type="hidden" id="editURL" value="<%=basePath%>/project/projectAction!edit?projectManage.fid="/>
		<input type="hidden" id="detailURL" value="<%=basePath%>/project/projectAction!selectProject?projectManage.fid="/>
		<input type="hidden" id="viewIinterestTime" value="<%=basePath%>/project/projectAction!selectInterestTime?projectManage.fid="/>
		<input type="hidden" id="deleteURL" value="<%=basePath%>/project/projectAction!deleteProject?projectManage.fid="/>
		<input type="hidden" id="viewFile" value="<%=basePath%>/pages/projectmanage/agreemenFile.jsp?projectfid="/>
	</body>
	</html>
