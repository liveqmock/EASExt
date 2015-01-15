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
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/jquery.table.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/change/util.js"></script>
 	
</head>
	<body>
	<select id="department" name="department"></select>
    <select id="city" name="city"></select>
	</body>
	<script type="text/javascript">
	
	// 大类可无限增加
	function BigCategoryList(){
	    this.length=4;
	    this[0] = new Option("请选择部门","");
	    this[1] = new Option("投资理财一部","投资理财一部");
	    this[2] = new Option("投资理财二部","投资理财二部");
	    this[3] = new Option("投资理财三部","投资理财三部");
	    return this;
	}
	// 小类可无限增加
	function SmallCategoryList(){
	    this.length=4;
	    this[0] = new Array(1);
	    this[0][0] = new Option("请选择城市","");
	    this[1] = new Array(3);
	    this[1][0] = new Option("上海","上海");
	    this[1][1] = new Option("成都","成都");
	    this[1][2] = new Option("九江/共青城","九江/共青城");
	    this[2] = new Array(1);
	    this[2][0] = new Option("青岛","青岛");
	    this[2][1] = new Option("烟台","烟台");
	    this[2][2] = new Option("长春","长春");
	    this[3] = new Array(3);
	    this[3][0] = new Option("无锡","无锡");
	    this[3][1] = new Option("南京","南京");
	    this[3][2] = new Option("苏州","苏州");
	    this[3][3] = new Option("芜湖","芜湖");
	    this[3][4] = new Option("宁波","宁波");
	    return this;
	}
	// 实例化大类
	bigCategoryOP = new BigCategoryList();
	// 实例化小类
	smallCategoryOp = new SmallCategoryList();
	// 移除小类显示
	function delSmallCategoryList(smallOption){
	    var len = smallOption.length;
	    for(i=0; i<len; i++){
	        smallOption.remove(0);
	    }
	}
	// 添加小类显示
	function addSmallCategoryList(bigOption, smallOption){
	    var index = bigOption.selectedIndex;
	    for(j=0; j<smallCategoryOp[index].length; j++){
	        smallOption.options.add(smallCategoryOp[index][j]);
	    }
	}
	// 当大类 onchange 函数
	function changeBigCategory(bigCategoryObj, smallOptionObj){
	    delSmallCategoryList(smallOptionObj);
	    addSmallCategoryList(bigCategoryObj,smallOptionObj);
	}
	// 初始化大类函数
	function initialize(bigCategoryObj, smallOptionObj, bigOpitonID, smallOptionID){
	    // 初始化大类列表
	    for(i=0; i<bigCategoryOP.length; i++){
	        bigCategoryObj.options.add(bigCategoryOP[i]);
	    }
	    for(j=0; j<bigCategoryObj.options.length; j++){
	        if(bigCategoryObj.options[j].value == bigOpitonID){
	            bigCategoryObj.options[j].selected = true;
	        }
	    }
	    addSmallCategoryList(bigCategoryObj, smallOptionObj);
	    for(k=0; k<smallOptionObj.options.length; k++){
	        if(smallOptionObj.options[k].value == smallOptionID){
	            smallOptionObj.options[k].value = true;
	        }
	    }
	}
	// 这个东东 你应该懂吧？
	$(document).ready(function(){
	    initialize($("#department")[0],$("#city")[0],'${department}','${city}');
	    $("#department").change(function(){
	        changeBigCategory($("#department")[0],$("#city")[0]);
	    });
	});
 	
	</script>
</html>