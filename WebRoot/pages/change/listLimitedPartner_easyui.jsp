<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<title>金蝶系统有限合伙公司</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
 	<script type="text/javascript">
   		$(function() {

   		     $("#queryBtn").click(function(){
 				$('#tt').datagrid('load',{  // 向服务器传递的参数
 					fname_l2: $("#fname_l2").val()
 			        }); 
 			});

 			$("#resetBtn").click(function(){
 				$("#fname_l2").val("");
 			});
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>limitedpartner/limitedpartner!listLimitedPartnerPageJson',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				columnOption: true,
				rownumbers: true,
				singleSelect: false,
				selectOnCheck: true,
				checkOnSelect: true,
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'ck',checkbox:true },
					  {field:'fcode',title:'编码',width:fixColumnWidth(0.1600)},
					  {field:'fname_l2',title:'名称',width:fixColumnWidth(0.1600)},
					  {field:'fnumber',title:'助记码',width:fixColumnWidth(0.1600)}
				]],
				queryParams: {//传递参数的方法
					//fname_l2: '${fname_l2}'
				},
				//下面 定义 分页配置 ：
				pageSize:5,
				pageList:[5,10,15,20],
				pagination:true,   // 是否 要分页 
				pageNumber:1,//设置初始页为1
				//onLoadSuccess: omitLongData()
				onLoadSuccess: omitLong()
			});
			//下面这个方法 用于 配置  分页的信息 
			displayMsg($('#tt'));
		});
   </script>
	<body>
        <div class="search">
    	<table onKeyUp="bindQuery();">
		  <tr>
            <td>有限合伙名称 </td>
            <td>
           <input id="fname_l2" name="fname_l2" class="t-text" type="text" value="">
            </td>
            <td>
                <a href="javascript:void(0)" id="queryBtn" class="but-search"></a>
            </td>
            <td>
		      	<a href="javascript:void(0)" id="resetBtn" class="but-remove">清空</a>
		    </td>
		  </tr>
	 </table>
  </div>
        <div class="search-list">
        	<span class="list-title">金蝶系统有限合伙公司</span>
        	<br/>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="message" value="${message }"/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
        <hr/>
        <div align="center" class="search">
         <a href="javascript:void(0)" class="but-remove" onclick="add()">添加</a>
         <a href="javascript:void(0)" class="but-remove" onclick="cancle()">取消</a>
         <a href="javascript:void(0)" class="but-remove" onclick="javascript:history.go(-1);">返回</a>
        </div>
        </body>
       <SCRIPT type="text/javascript">
       function add(){
				var checkedItems = $('#tt').datagrid('getChecked');
				if(checkedItems.length==0){
		 		$.messager.alert("提示", "请选择有限添加项！");
		 	    }else{
		 	        var fname_l2Array="";
			 		$.each(checkedItems, function(index, item){
			 		fname_l2Array+=item.fname_l2+",";
			 			}); 
			 		$.post(
	            	        "<%=basePath%>limitedpartner/limitedpartner!addLimitedPartner_ajax",
	            	        { fname_l2Array: fname_l2Array} ,
	        				function(data){
		        				if(data=='')
			        				 data='添加成功';
		        				$.messager.alert("提示",data,"info",function(){
		        				window.location.href="<%=basePath%>pages/change/gatherInformation.jsp";
	  					});
	        				}
	        			    );
	 	        }
		        };

	   function cancle(){
 		     $("input[type='checkbox']").prop("checked",false);
			 };
          
       </SCRIPT>
	
</html>
