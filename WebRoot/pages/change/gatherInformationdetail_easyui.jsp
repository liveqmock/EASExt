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
	<title>有限合伙人列表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript">
        function warnedit(){
        	$.messager.alert("提示", "请注意，审核后无法编辑！");
        }

        function warndelete(){
        	$.messager.alert("提示", "请注意，审核后无法删除！");
        }

   		$(function() {

   			var gatherstatus=$("#gatherstatus").val();
			
			$('#tt').datagrid({
				//下面是 datagrid的基本 配置信息 
				url:'<%=basePath%>limitedpartner/limitedpartner!queryPagedeatail',
				width: ($(window).width()-30),
				height: 'auto',
				nowrap: false,  //  是否在一行显示数据
				striped: true,   //  是否 显示 斑马线  
				fitColumns: true,  // 自动填充 列 宽
				collapsible: true,   // 是否 有滑动效果 
				columnOption: true,
				rownumbers: true,
				loadMsg: '数据连接中.....',  //加载页面时候的提示消息
				remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
				sortOrder: 'desc',    // 排序 方法 
				singleSelect:false, 
				columns: [[
					  {field:'partner_name',title:'合伙人姓名',width:fixColumnWidth(0.0500)},
					  {field:'partner_sex',title:'性别',width:fixColumnWidth(0.0200)},
					  {field:'partner_nation',title:'民族',width:fixColumnWidth(0.0300)},
					  {field:'partner_addr',title:'户籍登记地址',width:fixColumnWidth(0.1300)},
					  {field:'partner_IDCard',title:'证件号码',width:fixColumnWidth(0.1200)},
					  {field:'partner_country',title:'国籍',width:fixColumnWidth(0.0400)},
					  {field:'partner_amountStr',title:'出资额（万元）',width:fixColumnWidth(0.0600)},
					  {field:'partner_date',title:'出资时间',width:fixColumnWidth(0.0800)},
					  {field:'partner_note',title:'备注',width:fixColumnWidth(0.0400)},
					  {field:'operation',title:'操作',width:fixColumnWidth(0.0800),
						   formatter: function(val,node) {
						        if(gatherstatus==4){
						        	return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='warnedit()'>编辑</a>&nbsp;</span>"
							   		+"&nbsp;<a class=\"operation-a\" href='javascript:void(0)' onclick='warndelete()'>删除</a>";
						        } else {
						   		return "<span><a class=\"operation-a\" id=\"operation\" href='javascript:void(0)' onclick='openTab(this,\"有限合伙人\",\"edit\","
						   		+node.id+")' >编辑</a>&nbsp;</span>"
						   		+"&nbsp;<a class=\"operation-a\" href='javascript:void(0)' onclick='change_deleteRecord("+node.id+")' >删除</a>";
						        }
						   }
			   		  }
				]],
				queryParams: {//传递参数的方法
					pid: '${pid}'
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
</head>
	<body>
	    <div align="center"> 
	   <form method="post" id="myForm" enctype="multipart/form-data">
	   <script type="text/javascript">
	   function show(){
			document.getElementById("file").style.display = "block";
			document.getElementById("imp").style.display = "none";
			document.getElementById("upl").style.display = "block";
		}
		 function submitForm(){
	            var myForm=$("#myForm");
	            var option={
	         		   url:"<%=basePath%>limitedpartner/limitedpartner!uploadTemplate_ajax?pid=${pid}",//默认是form action
	         		   success:function(data){
	             		   if(data=='')
		             		   data='导入成功！';
	             		      $.messager.alert("提示",data,"info",function(){
	             			        window.location.href="<%=basePath%>limitedpartner/limitedpartner!detail?pid=${pid}";
	  					});
	         		   }}
	            $('#myForm').ajaxSubmit(option);
		 }
	   
	   </script>
		<table class="tab_line">
			<tr>
				<td>
					<input name="file" id="file" type="file" style="display: none;" />
				</td>
				<td>
					<input id="imp" name="imp" onclick="show()" type="button" value="导入" />
					<%--<input id="upl" style="display: none" type="submit" value="导入" />
					--%><input id="upl" style="display: none" type="button" onclick="submitForm();" value="导入" />
				</td>
				<td>
				    
					<input id="download" onclick="javascript:window.location.href='<%=basePath%>limitedpartner/limitedpartner!downloadTemplate'" type="button" value="下载模板" />
				</td>
				<td>
					<input id="add"  onclick="javascript:window.location.href='<%=basePath%>limitedpartner/limitedpartner!addPartnerUI?pid=${pid}'" type="button" value="増行" />
				</td>
				<td>
					<input id="save" type="button" value="暂存" />
				</td>
				<td>
					<input type="button" value="提交" />
				</td>
				<td>
					<input id="check" type="button" value="审核" />
				</td>
			</tr>
		</table>
	</form>
	<div align="left">&nbsp;总计合伙人: ${sum_partner}个
	<br/>
	&nbsp;LP出资总额：${sum_amountStr}（万元）
	</div>
	</div>
	 <div align="center"><h4>${limitedpartner.name }变更资料信息表</h4></div>
		<div class="search-list">
        	<span class="list-title">有限合伙人列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
		<input type="hidden" id="editURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!editPartner?pid=${pid}&id="/>
		<input type="hidden" id="deleteURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!deletePartner_ajax?id="/>
		<input type="hidden" id="detailURL" value="<%=request.getContextPath()%>/limitedpartner/limitedpartner!detail?pid=${pid}"/>
		<input type="hidden" id="message" value="${message }"/>
		<div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
			iconCls="icon-save" style="width:900px;height:400px;padding:10px;">
			<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
		</div>
		
		<input type="hidden" id="makeusername" value="${makeUser.username}">
        <input type="hidden" id="checkusername" value="${checkUser.username}">
        <input type="hidden" id="orderstatusOld" value="${orderstatus}">
        <input type="hidden" id="savestatus" value="${limitedpartner.savestatus}">
        <input type="hidden" id="submitstatus" value="${limitedpartner.submitstatus}">
        <input type="hidden" id="checkstatus" value="${limitedpartner.checkstatus}">
        <input type="hidden" id="username" value="${user.username}">
        <input type="hidden" id="pid" value="${pid}">
        <input type="hidden" id="flag" value="${flag}">
        <input type="hidden" id="error" value="${error}">
        <input type="hidden" id="message" value="${message}">
        <input type="hidden" id="gatherstatus" value="${limitedpartner.gatherstatus}">
        <input type="hidden" id="issupplement" value="${limitedpartner.issupplement}">
        <input type="hidden" id="ischeck" value="${sum_partner}">
        <div>&nbsp;&nbsp;&nbsp;&nbsp;制表人:<span id="maketablepeople"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人:<span id="checkpeople"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单据状态:<span id="orderstatus"></span></div>
		
	</body>
	
	<script type="text/javascript">
	// 设置ajax同步
    $.ajaxSetup({  
        async : false  
    });
 	data1=$("#makeusername").val();
    $("#maketablepeople").html(data1);
    data2=$("#orderstatusOld").val();
    $("#orderstatus").html(data2);
    data3=$("#checkusername").val();
    $("#checkpeople").html(data3);

    
    data4=$("#savestatus").val();
    data5=$("#submitstatus").val();
    data6=$("#checkstatus").val();
    data7=$("#gatherstatus").val();
    data8=$("#issupplement").val();
    data9=$("#ischeck").val();

    var error=$("#error").val();
    var fanshen2="反审核";
    var flag;  //每次按钮事件时，保存orderstatus返回的值，用于审核时判断使用
    if(data7==0)
        flag="未采集";
    else if(data7==1)
        flag="未完成";
    else if(data7==2)
        flag="已完成";
    else if(data7==4)
        flag="已审核";

    
    $(document).ready(function(){
       // if(error!=""){
         //   $.messager.alert("提示", error);
       // };
        if(data7==4){
        	$("input[type='button']").attr('disabled',true);
        	$("input[type='submit']").attr('disabled',true);
        	$("#check").attr('disabled',false);
        	$("#check").val(fanshen2);
        };
        if(data8==13){
        	$("input[type='button']").attr('disabled',true);
        	$("input[type='submit']").attr('disabled',true);
        };
    });


    $(":input").click(function(){
        var value=$(this).val();
        var pid=$("#pid").val();
        var save="暂存";
        var submit="提交";
        var check="审核";
    	var fancheck="反审核";   
    	var done="已完成";       
        if(value==save || value==submit){
        	if(value==submit && data9==0){
            	    $.messager.alert("提示", "有限合伙人记录为空，无法提交，请增加有限合伙人！");
        	}else 
                 $.post(
        	        "<%=basePath%>limitedpartner/limitedpartner!updateStatus",
        	        { pid: pid, operation: value } ,
    				function(data){
        	        	flag=data;
        	        	data3=$("#username").val();
        	            $("#maketablepeople").html(data3);
    					$("#orderstatus").html(data);
    				}
    			    );
        }else if(value==check || value==fancheck)
        {
        	if($(this).val()==check){
            	if(flag==done){
                	if(data9!=0){
           			$("input[type='button']").attr('disabled',true);
           			$("input[type='submit']").attr('disabled',true);
           			$(this).attr('disabled',false);
         		    	$(this).val(fancheck);
          		    $.post(
          	  	        "<%=basePath%>limitedpartner/limitedpartner!updateStatus",
          	  	        { pid: pid, operation: value } ,
          					function(data){
          	  	            flag=data;
          	  	        	data3=$("#username").val();
          	  	            $("#checkpeople").html(data3);
          				    $("#orderstatus").html(data);
          					}
          				);
      				
            		window.location.href="<%=basePath%>limitedpartner/limitedpartner!detail?limitedpartner.id=${pid}"; 
                	}else
                		$.messager.alert("提示", "有限合伙人记录为空，无法审核，请增加有限合伙人！");
            	}else{
            		$.messager.alert("提示", "审核前请提交！");
            		
                }
      	  }else{
      		$(this).val(check);
      		$("input[type='button']").attr('disabled',false);
      		$("input[type='submit']").attr('disabled',false);
      		$.post(
      	  	        "<%=basePath%>limitedpartner/limitedpartner!updateStatus",
      	  	        { pid: pid, operation: value } ,
      					function(data){
      	  	            flag=data;
      	          		$("#checkpeople").html("");
      				    $("#orderstatus").html(data);
      					}
      				);
      		window.location.href="<%=basePath%>limitedpartner/limitedpartner!detail?limitedpartner.id=${pid}";
      	  }
      	}
    });

 // 有限合伙系统变更
    function change_deleteRecord(id){
    	var url = $("#deleteURL").val() + id;
    	var detailurl=$("#detailURL").val();
    	$.messager.confirm('警告', '确定需要删除此信息吗？', function(r){
    		if(r){
    			$.ajax({
    				 url: url,
    				 async: false,
    				 success:function(){
    					$.messager.alert("提示","操作成功！","info",function(){
    						window.location.href=detailurl;
    					})
     				}
    			});
    		}
    	});
    }
    
	</script>
	
</html>
