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
   		$(function() {
			
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
					  {field:'partner_percentstr',title:'出资比例',width:fixColumnWidth(0.0500)},
					  {field:'partner_date',title:'出资时间',width:fixColumnWidth(0.0800)},
					  {field:'partner_note',title:'备注',width:fixColumnWidth(0.0400)}
					  
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
		<div class="search-list">
        	<span class="list-title">有限合伙人列表</span>
            <table id="tt" toolbar="#toolbar"></table></div>
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
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;税务变更完成时间:<span id="tax"></span>
          </div>
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
