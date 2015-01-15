<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%@taglib  prefix="elself" uri="/eltag" %>
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
	<title>采购合同信息汇总 </title>
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">
   		$(function() {
   	   		//导出excel的方法
   			$("#exportExcelBtn").click(function(){
   				$.messager.confirm('确认', '确定要导出查询的信息吗？', function(r){
   					if(r){//使用ajax函数，同步获取数据的方式,也就是上面的数据加载完成之后，才会执行
   						var url="<%=basePath %>upload/upload!exportAdminiPrucContractExcel.action?fnumber=" + $("#fnumber").val()+"&forgname="
   						+$("#forgname").val()+"&forgname="+$("#forgname").val()+"&fcontracttypeid="+$("#fcontracttypeid").val()+"&fbegindate="
   						+$("#fbegindate").val()+"&fenddate="+$("#fenddate").val()+"&fcity="+$("#fcity").val()+"&fcontractstatus="
   						+$("#fcontractstatus").val()+"&flastcostcenter="+$("#flastcostcenter").val();
   						var fireFox = 0;
   			   	   		if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
   			   	   			fireFox = 1;
   			   	   	   	}
   			   	   	   	url += "&fireFox="+fireFox;
   						location.href=url;
   					}
   				});
			})
   			/******************付款方式*************************/
			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					fnumber:$("#fnumber").val(),
					forgname:$("#forgname").val(),
					fcontracttypeid:$("#fcontracttypeid").val(),
					fbegindate:$("#fbegindate").val(),
					fenddate:$("#fenddate").val(),
					fcity:$("#fcity").val(),
					fcontractStatus:$("#fcontractStatus").val(),
					flastcostcenter:$("#flastcostcenter").val(),
					fStatus:$("#fStatus").val()
				});
			})
			$("#resetBtn").click(function(){
				$("#fnumber,#forgname,#fcontracttypeid,#fbegindate,#fenddate,#fcity,#fcontractStatus,#flastcostcenter,#fStatus").val("");
			})
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>/adminiPurc/adminiPrucContract!queryPageJson',
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
				singleSelect:false, 
				columns: [[
				      {field:'FNUMBER',title:'合同编号',width:fixColumnWidth(0.1900)},
					  {field:'FORGNAME',title:'使用部门',width:fixColumnWidth(0.1200)},
					  {field:'FCITY',title:'城市',width:fixColumnWidth(0.0700)},
					  {field:'FOFFICEADDR',title:'办公室座落地点',width:fixColumnWidth(0.1710)},
					  {field:'FLASTCOSTCENTER',title:'末级成本中心',width:fixColumnWidth(0.1490)},
					  {field:'FBEGINDATE',title:'开始日期',width:fixColumnWidth(0.1400)},
					  {field:'FENDDATE',title:'结束日期',width:fixColumnWidth(0.1400)},
					  {field:'ITEMNAME',title:'合同类别',width:fixColumnWidth(0.1000)},
					  {field:'FSTATUS',title:'状态',width:fixColumnWidth(0.0700)},
					  {field:'FCONTRACTSTATUSNAME',title:'合同到期跟进',width:fixColumnWidth(0.1500),
						  formatter: function(val,node){
								var result = node.FCONTRACTSTATUSNAME;
								if(result == '生效'|| result==undefined){
									result = '-';
								}
						  		return result;
						  	}
					   },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.2100),sortable:true,
						   formatter: function(val,node){
					   	 	var result="<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"采购合同信息汇总 \",\"view\","+node.FID+")'>查看</a></span>";
<%--					   	 	if(${elself:validIsAdmin(personrole,442)==false}){//行政采购组的人员，不能修改接口人的信息--%>
								if(node.FSTATUS != '终止'){
									result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"合同信息\",\"edit\","+node.FID+")'>编辑</a></span>";
								}
<%--					   	 	}--%>
							<%--		
							删除功能暂时不需要，如果后续需要，则需要考虑总接口人不能删除自己的信息			   	 		
							result += "<a class=\"operation-a\" href=\"javascript:void(0)\"  onclick='deleteRecord("+node.fid+")'>删除</a>&nbsp;";--%>
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
			//给字段增加验证
			$("#fnumber,#forgname,#fcity,#flastcostcenter").addClass("easyui-validatebox").validatebox({required: false,validType:'njection'});
		});
		//导出合同信息
   		function exportvalidContractInfo(url){
   			var fireFox = 0;
   	   		if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
   	   			fireFox = 1;
   	   	   	}
   	   	   	url += "?fireFox="+fireFox;
   			window.location.href = url;
			$("#validTr").css("display","none");
		}
   </script>
</head>
<body>
  <div class="search">
	<table id="queryTab" onKeyUp="bindQuery();">
		<tr>
			<td class="s-t">合同编码</td>
        	<td><input class="s-text" type="text" id="fnumber" value=""></td>
            <td class="s-t">使用部门</td>
        	<td><input class="s-text" type="text" id="forgname" value=""></td>
        	  <td class="s-t">合同类别</td>
        	<td>
				<select id="fcontracttypeid" rel="zx_dictionary" data="xzcgct">
					<option value="">全部</option>
  		 		</select>
			</td>
			<td class="s-t">开始日期</td>
            <td><input class="s-text Wdate" type="text" id="fbegindate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/></td>
            
		</tr>
		
		<tr>
		
		<td class="s-t">结束日期</td>
            <td>
            	<input class="s-text Wdate" type="text" id="fenddate" 
				onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/></td>
			<td class="s-t">城市</td>
        	<td>
        		<input class="s-text" type="text" id="fcity" value="">
            </td>
            <td class="s-t">合同到期跟进</td>
        	<td>
  		 		<select id="fcontractStatus" rel="zx_dictionary" data="xzcghtzt">
  		 			<option value="">全部</option>
 		 		</select>
            </td>
			<td class="s-t">末级成本中心</td>
        	<td><input class="s-text" type="text" id="flastcostcenter" value=""></td>
		</tr>
		<tr>
        	<td class="s-t">状态</td>
	        	<td>
	        		<select name="fStatus" id="fStatus">
	        			<option value="">全部</option>
	        			<option value="1">生效</option>
	        			<option value="2">终止</option>
	        		</select>
	        </td>
			<td colspan="8">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain="true">清空</a>
			</td>
		</tr>
	</table>
	<c:if test="${elself:validIsAdmin(personrole,720)==true}">
		<form action="<%=basePath %>adminiPurc/adminiPurc!importContractExcel"  method="post" name="myform" id="myform" enctype="multipart/form-data"> 
			<table align="center" width="80%">
				<tr>
					<td align="left">
						&nbsp;&nbsp;<input type="file" id="excelFile" name="excelFile"/>
						<input id="filePath" name="filePath" type="hidden"/>
						&nbsp;&nbsp; <a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true class="but-remove" onClick="importExcelFun_filePath('validTr')">导入</a>
					</td>
					<td  id="validTr" style="display: none;" align="left">
						<a href="javascript:void(0)" onclick="exportvalidContractInfo('<%=basePath %>adminiPurc/adminiPurc!exportvalidContractInfo');" plain=true class="but-remove" name="downExportvalidInfo">下载验证信息</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
 </div>
 <div class="search-list">
  	<span class="list-title">采购合同信息列表</span>
	<table id = "tt" toolbar="#toolbar"></table>
	<div id="toolbar" align="right">
		<a href='javascript:void(0)' id="exportExcelBtn" class="easyui-linkbutton" iconCls="icon-redo" plain="true">导出合同</a><span id="tipMsg" style="color: red;"></span>
   </div>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPrucContract!view?contractInfo.fid="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPrucContract!edit?contractInfo.fid="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPrucContractdelete?contractInfo.fid="/>
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</div>
</body>
</html>