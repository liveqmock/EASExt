<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="page" uri="http://java.sun.com/jsp/jstl/mytag01" %>
<%@ page import="com.change.eas.util.Config" %>
<%@ page import="com.creditease.eas.admin.bean.User" %>
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
 			border: 4px;
 			overflow: scroll;
 		}
 		.table_list tbody tr:hover{
 			background-color: #dddddd;
 		}
 		.table_list tbody{
 			text-align: center;
 		}
 		.table_list tbody tr td{
 			border-left: 0px;
 			border-right: 0px;
 		}
 		.table_list thead tr th{
 			border-left: 0px;
 			border-right: 0px;
 			
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
        <input style="width: 80px; height: 30px;" type="button"  value="补充信息" />&nbsp;&nbsp;
        <input style="width: 100px; height: 30px;" type="button" value="生成法律文件" />&nbsp;&nbsp;
       	<input style="width: 100px; height: 30px;" type="button" value="工商变更完成" />&nbsp;&nbsp;
        <input style="width: 100px; height: 30px;" type="button" value="税务变更完成" />&nbsp;&nbsp;
	<div align="left">&nbsp;总计合伙人: ${sum_partner}个
	<br/>
	&nbsp;LP出资总额：${sum_amountStr}（万元）
	<br/>
	&nbsp;GP出资比例：${limitedpartner.gpcontributionpercentstr} 
	<br/>
	&nbsp;总出资额：${limitedpartner.totalcontributionStr}（万元）
	</div>
	</div>
	 <div align="center"><h2>${limitedpartner.name }变更资料信息表</h2></div>
	 <div style="overflow:scroll;width:100%;border: 3px">
	  <table id="partnerTable" class="table_list" >
		  <thead> 
	        <tr class="ttr">  
	            <th>合伙人姓名</th>  
	            <th>性别</th>
	            <th>民族</th> 
	            <th>户籍登记住址</th> 
	            <th>证件号码</th>
	            <th>国籍</th>   
	            <th>出资额（万元）</th>   
	            <th>出资比例</th>   
	            <th>出资时间</th>   
	            <th>备注</th>
	        </tr>
         </thead>
         <tbody style="overflow-x:scroll"> 
        <s:iterator value="#listPartner" id="array">  
            <tr>
	            <td><s:property value="partner_name"/></td>  
	            <td><s:property value="partner_sex"/></td>
	            <td><s:property value="partner_nation"/></td>  
	            <td><div class="div" title=<s:property value="partner_addr"/>><s:property value="partner_addr"/></div></td>
	            <td><s:property value="partner_IDCard"/></td>  
	            <td><s:property value="partner_country"/></td>
	            <td><s:property value="partner_amountStr"/></td>  
	            <td><s:property value="partner_percentstr"/></td>
	            <td><s:property value="partner_date"/></td>  
	            <td><div class="div" title=<s:property value="partner_note"/>><s:property value="partner_note"/></div></td>  
            </tr>  
        </s:iterator>
        	<tr>
        		<td colspan="10">${fenye}</td>
        	</tr>
        </tbody> 
        </table>
        </div>
        <br/><br/>
        <input type="hidden" id="makeusername" value="${makeUser.username}">
        <input type="hidden" id="checkusername" value="${checkUser.username}">
        <input type="hidden" id="orderstatusOld" value="${orderstatus}">
        <input type="hidden" id="savestatus" value="${limitedpartner.savestatus}">
        <input type="hidden" id="submitstatus" value="${limitedpartner.submitstatus}">
        <input type="hidden" id="checkstatus" value="${limitedpartner.checkstatus}">
        <input type="hidden" id="username" value="${user.username}">
        <input type="hidden" id="pid" value="${pid}">
        <input type="hidden" id="issupplement" value="${limitedpartner.issupplement}">
        <input type="hidden" id="isfileproducted" value="${limitedpartner.isfileproducted}">
        <input type="hidden" id="gschangestatus" value="${limitedpartner.gschangestatus}">
        <input type="hidden" id="taxchangestatus" value="${limitedpartner.taxchangestatus}">
        <input type="hidden" id="lfmakestatus" value="${limitedpartner.lfmakestatus}">
        <input type="hidden" id="gschangedate" value="${gcchangedatestr}">
        <input type="hidden" id="swchangedate" value="${shuiwuchnangedatestr}">
        
        
        <input type="hidden" id="gcchangedatestr" value="${limitedpartner.gcchangedatestr}">
        <input type="hidden" id="shuiwuchnangedatestr" value="${limitedpartner.shuiwuchnangedatestr}">
        
          <div>&nbsp;&nbsp;&nbsp;&nbsp;制表人:<span id="maketablepeople"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审核人:<span id="checkpeople"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单据状态:<span id="orderstatus"></span>
          <br/><br/>
          &nbsp;&nbsp;&nbsp;&nbsp;工商变更完成时间:<span id="gc"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;税务变更完成时间:<span id="tax"></span></div>
          
          <input type="hidden" id="time2" value=""/>
	</body>
	<script type="text/javascript">
 	var data1=$("#makeusername").val();
    $("#maketablepeople").html(data1);
    var data2=$("#orderstatusOld").val();
    $("#orderstatus").html(data2);
    var data3=$("#checkusername").val();
    $("#checkpeople").html(data3);

    var gschangedate=$("#gschangedate").val();
    //$("#gc").html(gschangedate);
    var swchangedate=$("#swchangedate").val();
   // $("#tax").html(swchangedate);

    var gcchangedatestr=$("#gcchangedatestr").val();
    $("#gc").html(gcchangedatestr);
    var shuiwuchnangedatestr=$("#shuiwuchnangedatestr").val();
    $("#tax").html(shuiwuchnangedatestr);
    
    var issupplement=$("#issupplement").val();
    var isfileproducted=$("#isfileproducted").val();
    var gschangestatus=$("#gschangestatus").val();
    var taxchangestatus=$("#taxchangestatus").val(); 
    var lfmakestatus=$("#lfmakestatus").val();
    

    $(document).ready(function(){
        $(":input").click(function(){
        	var change=$(this).val();
        	var pid=$("#pid").val();
        	var puchong="补充信息";
        	var lawfile="生成法律文件";
        	var gschange="工商变更完成";
        	var taxchange="税务变更完成";

        	  var url;                                 //转向网页的地址;
        	  var name;                           //网页名称，可为空;
        	  var iWidth;                          //弹出窗口的宽度;
        	  var iHeight;                        //弹出窗口的高度;
        	  var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
        	  var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;

        	function buildLawFile(){
        		window.location.href="<%=basePath%>limitedpartner/limitedpartner!toBuildLawFile?id=${limitedpartner.id}&name=${limitedpartner.name }";
            };

            function buildpuchong(){
            	window.location.href="<%=basePath%>limitedpartner/limitedpartner!supplementLimitedPartnerUI?pid=${pid}";
            };

            function buildpuchongPreview(){
            	window.location.href="<%=basePath%>limitedpartner/limitedpartner!supplementLimitedPartnerPreview?pid=${pid}";
            };

            function buildgschange(obj){
            	var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
          	    var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
          	    
            	//window.location.href="<%=basePath%>limitedpartner/limitedpartner!updateTime?pid=${pid}&flag=0";
            	//alert(window.open ("<%=basePath%>pages/change/changetime.jsp?pid=${pid}", "", "height=100, width=100, top="+iTop+",left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no"));
            	var time = window.showModalDialog("<%=basePath%>pages/change/changetime.jsp", "", "dialogHeight=250px, dialogWidth=50px, top=0,left=0,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") 
            	if(time)
            	window.location.href="<%=basePath%>limitedpartner/limitedpartner!updateChangeTime?flag="+obj+"&pid=${pid}&changetime="+time;
            };

            function buildtaxchange(){
            	window.location.href="<%=basePath%>limitedpartner/limitedpartner!updateTime?pid=${pid}&flag=1";
            };
                    	
        	if(change==puchong){
            	if(lfmakestatus!=3){
      			    //$.messager.alert("提示", "请注意，法律文件已经生成了，当前你只能进行预览！");
                	buildpuchongPreview();
            	}else{
            		buildpuchong();
            	}
        	}else if(change==lawfile ){
            	if(issupplement!=13){
            		$.messager.alert("提示", "生成法律文件前请补充信息！");
            	}else if(lfmakestatus==2){
                	$.messager.alert("提示", "法律文件已全部生成，请勿重复生成！");
            	}else{
            		buildLawFile();
            	}
            }else if(change==gschange)
            {
                if(lfmakestatus!=2){
                    $.messager.alert("提示", "工商变更前请生成全部法律文件！");
                }else if(gschangestatus==2){
                    $.messager.alert("提示", "请注意，工商变更先前已完成！");
                }else {
                //alert($(this).val()+"时间："+new Date().toLocaleDateString());
                buildgschange(0);
                }
            }else if(change==taxchange) 
            {
                if(gschangestatus!=2){
                    $.messager.alert("提示", "税务变更前请工商变更！");
                }else if(taxchangestatus==2){
                    $.messager.alert("提示", "请注意，税务变更先前已完成！");                   
                }else{
                {
                	// alert($(this).val()+"时间："+new Date().toLocaleDateString());
                	 buildgschange(1);
                }   
            }    
        }
        })
    });


	</script>
</html>
