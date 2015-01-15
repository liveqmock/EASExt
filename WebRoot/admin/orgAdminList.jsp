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
	<title>选择的行政组织列表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<script type="text/javascript"><!--
		//easy ui的查询的方法
		$(document).ready(function(){
			//弹出窗口
			$("#operval").fancybox({
				'hideOnContentClick': false,
				'width'	 : '80%',
				'height'	 : '80%',
				'autoScale'     	: false,
				'transitionIn'	 : 'none',
				'transitionOut'	 : 'none',
				'type'	 : 'iframe',
				'titleFormat'       : function(title){return  "";}
			});
		});
		function doSearch(){  
	        $('#tt').datagrid('load',{  
	          //  orgname: $('#orgname').val()
	        });  
	    }  
	    /**
	    * 操作的方法
	    **/
	    function operation(val,row){
	   // alert(row.fnumber);
	    	return hrefs = "<a href='javascript:void(0);' onclick='changeOrgManager('"+row.fnumber+"')'>修改</a>"; 
	    	//return "<a href='javascript:void(0);' onclick='changeOrgManager()'>修改</a>"; 
	    	//return "<a href='javascript:void(0);' onclick='ss'>修改</a>"; 
	    }
	    function changeOrgManager(obj){
	    alert('aaaa');
	    	//var　url = "<%=request.getContextPath()%>/person/orgManager!edit?orgManager.fnumber=" + obj;
	    	//alert(url);
			//$("#operval").attr("href",url);
			//$("#operval").click();
	    }
	    /**
	    *关闭子窗体
	    **/
	    function closeChildWinow(){
			$.fancybox.close();
			$("#tt").datagrid("reload");
		}
	</script>
</head>
<body>
	<a id="operval" href="#"></a> 
	<h3 align="center">选择的行政组织列表</h3>
    <table id="tt" class="easyui-datagrid" url='<%=basePath %>admin/admin!queryPageJson' 
    	style="width:1000px" title="" rownumbers="true" collapsible="true" 
    	sortOrder="desc" singleSelect="true" fitColumns="true" striped="true" nowrap="true" 
    	pagination="true" pageSize="10" pageList="[5,10,15,20]" >  
        <thead>  
           <tr>  
               <th field="fname" align="left" width="200">部门名称</th>  
               <th field="fnumber" align="left" width="100">部门编码</th>  
           </tr>  
       </thead>  
    </table>  
    
</body>
</html>