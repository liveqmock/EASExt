<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/record_jsp_style.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	 <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ztree/jquery.ztree.core-3.3.js"></script>
	<script type="text/javascript" src="js/ztree/jquery.ztree.excheck-3.3.js"></script>
	
	<!--
	<script type="text/javascript" src="js/ztree/jquery.ztree.exedit-3.3.js"></script>
	-->
	
		<SCRIPT type="text/javascript">
		
		 	var setting = {
			data: {
				simpleData: {//节点数据可以直接使用array无需转换成json
					enable:	true
					
				}
			},
			callback:{
				onClick: zTreeOnClick
				}
			
		}; 
		 
		var	zNodes =[//获取节点值
		  <c:forEach var="list" items="${rosterOrgAdminList}" varStatus="var"  > 
			<c:choose>
			   <c:when test="${var.last}">
			    { id:"${list.FID}", pId:"${list.FPARENTID}", name:"${list.FNAME_L2}",fnumber:"${list.FNUMBER}",flevel:"${list.FLEVEL}",fid:"${list.FID}",fname_l2:"${list.FNAME_L2}",fdisplayname_l2:"${list.FDISPLAYNAME_L2}" }
			   </c:when>
			   <c:otherwise>
			     { id:"${list.FID}", pId:"${list.FPARENTID}", name:"${list.FNAME_L2}",fnumber:"${list.FNUMBER}",flevel:"${list.FLEVEL}",fid:"${list.FID}" ,fname_l2:"${list.FNAME_L2}",fdisplayname_l2:"${list.FDISPLAYNAME_L2}"}, 
			   </c:otherwise>
			</c:choose>           
            
       </c:forEach>  
       ];   

       function zTreeOnClick(event,treeId,treeNode){
            var fdisplayname = treeNode.fdisplayname_l2;//行政组织
            var temp = fdisplayname.split("_");
            var fthreeunit = temp[3];
            var ffourunit = temp[4];
            var ffiveunit = temp[5];
            var fsixunit = temp[6];
			parent.setRosterOrgAdmin(fdisplayname,fthreeunit,ffourunit,ffiveunit,fsixunit);	
			doCancel();	
			
	           }
		
		$(document).ready(function(){
			$.fn.zTree.init($("#rosterorgadmin"), setting, zNodes);//初始化
			 var treeObjs1 = $.fn.zTree.getZTreeObj("rosterorgadmin");//获取ztree对象
		     var nodes1 = treeObjs1.getNodes();//获取所有节点
		      for(var i=0;i<nodes1.length;i++){	  
		     	  if(nodes1[i].isParent==true){	 //如果是父节点可以展开节点
			        treeObjs1.expandNode(nodes1[i], true);
			        }
			   }	
		});
		
		
	</SCRIPT>
  </head>
  
  <body>
 
		<div>
			  <center>
			  	选择行政组织
			  <ul id="rosterorgadmin" class="ztree"  style="width: 400;text-align: right;" ></ul>
			  </center>
		</div> 

  </body>
</html>
