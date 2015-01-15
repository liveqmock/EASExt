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
	<title>接口人信息汇总 </title>
	<jsp:include page="/common/commonInclude.jsp"></jsp:include>
	<script type="text/javascript">
   		$(function() {
   			var userTypeId=$("#userTypeId").val(); 
   			//datagrid数据查询参数
			$("#queryBtn").click(function(){
				$("#tt").datagrid("load",{
					orgname:$("#orgname").val(),
					city:$("#city").val(),
					officeadd:$("#officeadd").val(),
					lastcostcenter:$("#lastcostcenter").val(),
					isport:$("#isport").val()
				});
			})
			//重置按钮
			$("#resetBtn").click(function(){
				$("#orgname,#city,#officeadd,#lastcostcenter,#isport").val("");
			})
			//加载
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>adminiPurc/adminiPortPurc!queryPageJson',
				width: ($(window).width()-20),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				loadMsg: '数据连接中.....',  //加载页面时候的提示信心 
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				columns: [[
					 {field:'forgName',title:'使用部门',width:fixColumnWidth(0.0700)},
					  {field:'fcity',title:'城市',width:fixColumnWidth(0.0600)},
					  {field:'fofficeAddr',title:'办公室座落地点',width:fixColumnWidth(0.1400)},
					  {field:'flastCostcenter',title:'末级成本中心',width:fixColumnWidth(0.1100)},
					  {field:'fportEmail',title:'接口人邮箱',width:fixColumnWidth(0.1800)},
					  {field:'fportName',title:'接口人名称',width:fixColumnWidth(0.0800)},
					  {field:'fisAllPort',title:'是否总接口人',width:fixColumnWidth(0.1200),
					   formatter:function(val){if(val==0) val="是"; else if(val==1) val="否"; return val;}},
					  {field:'fcreatetime',title:'创建时间',width:fixColumnWidth(0.1100),
					  	formatter:function(val){
					 		return val.substring(0,val.lastIndexOf('T'));
					  	}
					  },
					  {field:'operation',title:'操作',width:fixColumnWidth(0.1600),sortable:true,
						  formatter: function(val,node){
					   	 	var result="<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"接口人信息汇总\",\"view\","+node.fid+")'>查看</a></span>";
					   	 	var userEmail = '${sessionScope.user.username}';
					   	 	if(${elself:validIsAdmin(personrole,719)==true}||(${elself:validIsAdmin(personrole,720)==true}&&userEmail!=node.fportEmail)){//行政采购组的人员，不能修改接口人的信息
								result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a class=\"operation-a\" href='javascript:void(0)' onclick='openTab(this,\"接口人信息\",\"edit\","+node.fid+")'>编辑</a></span>";
					   	 	}
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
				onLoadSuccess: omitLongDataOverload()
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
			//给字段增加验证
			$("#orgname,#city,#officeadd,#lastcostcenter").addClass("easyui-validatebox").validatebox({required: false,validType:'njection'});
	});
   	//导出接口人信息
   	function exportvalidPortInfo(url){
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
			<td class="s-t">部门总接口人</td>
            <td>
				<select id="isport" onChange="changeQyery();">
				<option value="">--全部--</option> <option value="1">是</option> <option value="0">否</option></select> 
			</td>
        	<td class="s-t">使用部门</td>
            <td><input class="s-text" type="text" id="orgname" value=""></td>
			<td class="s-t">城市</td>
            <td><input class="s-text" type="text" id="city" value=""></td>
			<td class="s-t">办公室地址</td>
            <td><input class="s-text" type="text" id="officeadd" value=""></td>
        </tr>
		<tr>
        	<td class="s-t">末级成本中心</td>
            <td><input class="s-text" type="text" id="lastcostcenter" value=""></td>
			<td colspan="6">
				<a href="javascript:void(0)" id="queryBtn" class="but-search" plain=true>&nbsp;</a>
				<a href="javascript:void(0)" id="resetBtn" class="but-remove" plain=true>清空</a>
			</td>
        </tr>
	</table>
	<c:if test="${elself:validIsAdmin(personrole,720)==true}">
		<form action="<%=basePath %>adminiPurc/adminiPurc!importPortExcel"  method="post" name="myform" id="myform" enctype="multipart/form-data"> 
			<table align="center" width="80%">
				<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="file" id="excelFile" name="excelFile"/>
						<input id="filePath" name="filePath" type="hidden"/>
						&nbsp;&nbsp; <a href="javascript:void(0)" name="importExcel" id="importExcel" plain=true class="but-remove" onClick="importExcelFun_filePath('validTr')">导入</a>
					</td>
					<td  id="validTr" style="display: none;" align="left">
						<a href="javascript:void(0);" onclick="exportvalidPortInfo('<%=basePath %>adminiPurc/adminiPurc!exportvalidPortInfo')" plain=true class="but-remove" name="downExportvalidInfo">下载验证信息</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
 </div>
 <div class="search-list">
  	<span class="list-title">接口人信息列表</span>
	<table id = "tt" align="center"></table>
</div>
	<input type="hidden" id="userTypeId" 
		value="<c:if test='${empty user.typeid}'>0</c:if><c:if test='${not empty user.typeid}'>${user.typeid}</c:if>"/>
	<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPortPurc!view?portinfo.fid="/>
	<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPortPurc!edit?portinfo.fid="/>
	<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/adminiPurc/adminiPortPurc!delete?portinfo.fid="/>
<!--弹出框-->
	<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
</body>
</html>