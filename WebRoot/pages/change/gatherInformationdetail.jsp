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
	<title>采集信息明细表</title>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/change/page.css">
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/jquery.table.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/util.js"></script>
 	<style type="text/css">
 		.ttr{
 			background-image: url('<%=path%>/images/content-bg.gif');
 			border-left-color: #dddddd;
 		}
 		.table_list{
 			width: 1200px;
 			border: 4px;
 			overflow: scroll;
 		}
 		.table_list tbody tr:hover{
 			background-color: #dddddd;
 		}
 		.table_list tbody tr td{
 			border-left: 0px;
 			border-right: 0px;
 		}
 		.table_list thead tr th{
 			border-left: 0px;
 			border-right: 0px;
 		}
 		.tab_line{
 			border: none;
 		}
 		.tab_line tr td {
 			border: none;
 		}
 		.tab_line{
 			border: none;
 			width: 30%;
 		}
 		.div{
 		overflow:hidden;
 		text-overflow: ellipsis; 
 		white-space: nowrap; 
 		}
 	</style>
</head>
	<body>
	<div align="center"> 
	<form method="post" action="<%=basePath%>limitedpartner/limitedpartner!uploadTemplate?pid=${pid}" enctype="multipart/form-data">
		<script type="text/javascript">
			function show(){
				document.getElementById("file").style.display = "block";
				document.getElementById("imp").style.display = "none";
				document.getElementById("upl").style.display = "block";
			}
			
		</script>
		<table class="tab_line">
			<tr>
				<td>
					<input name="file" id="file" type="file" style="display: none;" />
				</td>
				<td>
					<input id="imp" name="imp" onclick="show()" type="button" value="导入" />
					<input id="upl" style="display: none" type="submit" value="导入" />
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
					<input id="submit" type="button" value="提交" />
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
	 <div style="overflow:scroll;width:100%;border: 3px">
	  <table id="partnerTable" class="table_list">
	  <thead> 
        <tr class="ttr">  
            <th>合伙人姓名</th>  
            <th>性别</th>
            <th>民族</th> 
            <th>户籍登记住址</th> 
            <th>证件号码</th>
            <th>国籍</th>   
            <th>出资额（万元）</th>   
            <th>出资时间</th>   
            <th>备注</th>
            <th>操作</th>     
        </tr>
        </thead>
        <tbody style="overflow-x:scroll"> 
        <s:iterator value="#listPartner" id="array">  
            <tr>
            <td><s:property value="partner_name"/></td>  
            <td><s:property value="partner_sex"/></td>
            <td><s:property value="partner_nation"/></td>  
            <td>
            <div class="div" title=<s:property value="partner_addr"/>><s:property  value="partner_addr"/></div>
            </td>
            <td><s:property value="partner_IDCard"/></td>  
            <td><s:property value="partner_country"/></td>
            <td><s:property value="partner_amountStr"/></td>  
            <td><s:property value="partner_date"/></td>  
            <td>
            <div class="div" title=<s:property value="partner_note"/>><s:property value="partner_note"/></div>
            </td> 
            <td>
            <input id="edit" name="edit"  onclick="javascript:window.location.href='<%=basePath%>limitedpartner/limitedpartner!editPartner?id=${id}&pid=${pid}'"  type="button" value="编辑" />
            <input id="del"  name="del" onclick="javascript:$.messager.confirm('警告', '确定需要删除此信息吗?', function(r){
                if (r){
                    location.href = '<%=basePath%>limitedpartner/limitedpartner!deletePartner?id=${id}&pid=${pid}';
                   }
                  });" type="button" value="删除" />
          	</td>
            </tr>  
        </s:iterator>
        	<tr>
        		<td colspan="10">${fenye}</td>
        	</tr>
        </tbody> 
        </table>
        </div>
        <br/><br/>
        <!-- ${sessionScope.user.username} -->
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
        if(error!=""){
            $.messager.alert("提示", error);
        };
        if(data7==4){
        	$("input[type='button']").attr('disabled',true);
        	$("input[type='submit']").attr('disabled',true);
        	$("#check").attr('disabled',false);
        	$("#check").val(fanshen2);
        };
        if(data8==13){
        	$("input[type='button']").attr('disabled',true);
        	$("input[type='submit']").attr('disabled',true);
        }
        $(":input").click(function(){
            var value=$(this).val();
            var pid=$("#pid").val();
            var save="暂存";
            var submit="提交";
            var check="审核";
        	var fancheck="反审核";   
        	var done="已完成";       
            if(value==save || value==submit){
                if(value==submit && data9==0)
                	$.messager.alert("提示", "有限合伙人记录为空，无法提交，请增加有限合伙人！");
                else
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
          	  }
          	}
        })
    });
    
	</script>
</html>
