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
    
    <title>My JSP 'ChooseOrgAdmin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link rel="stylesheet" href="css/demo.css" type="text/css">
	 <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/ztree/jquery.ztree.core-3.3.js"></script>
	<script type="text/javascript" src="js/ztree/jquery.ztree.excheck-3.3.js"></script>
	<!--
	<script type="text/javascript" src="js/ztree/jquery.ztree.exedit-3.3.js"></script>
	-->
	
		<SCRIPT type="text/javascript">
		<!--
		
		 	var setting = {
			check: {
				enable: true,
		        chkboxType: { "Y": "s", "N": "ps" }
				
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		}; 
		 
		var	zNodes =[
		  <c:forEach var="list" items="${listorg}" varStatus="var"  > 
			<c:choose>
			   <c:when test="${var.last}">
			    { id:"${list.FID}", pId:"${list.FPARENTID}", name:"${list.FNAME}",fnumber:"${list.FNUMBER}",flevel:"${list.FLEVEL}",fid:"${list.FID}" }
			   </c:when>
			   <c:otherwise>
			     { id:"${list.FID}", pId:"${list.FPARENTID}", name:"${list.FNAME}",fnumber:"${list.FNUMBER}",flevel:"${list.FLEVEL}",fid:"${list.FID}" }, 
			   </c:otherwise>
			</c:choose>           
            
       </c:forEach>  
       ];   
        
 		 
		
		$(document).ready(function(){
	        
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			 var treeObjs1 = $.fn.zTree.getZTreeObj("treeDemo");
		     var nodes1 = treeObjs1.getNodes();
		      for(var i=0;i<nodes1.length;i++){	  
		     	  if(nodes1[i].isParent==true){	 
			        treeObjs1.expandNode(nodes1[i], true);
			        }
			   }	
		});
		
		
		
		function chooseadmin(){ 
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
			var node = treeObj.getCheckedNodes(true); 
		/*	if(node.length==0){
				alert('请至少选择一个部门');
				return false;
			}  */
			var nodesnumber = ""; 
			var nodesname = "";
			var nodesfid = "";
			var bool = 0;
			for(var i=0;i<node.length;i++){	  
			  if(node[i].isParent==false){	 
			    if(node[i].flevel==3){
			       bool = 1;
			       /* 逗号在后台处理
			      if(i==node.length-1){
			  	    	nodesnumber+=node[i].fnumber;  
				     	nodesname+=node[i].name;
			     	}else{
			  	    	nodesnumber+=node[i].fnumber+',';  
				    	nodesname+=node[i].name+',';
			     	}
			     	*/
			     	nodesnumber+=node[i].fnumber+',';  
				    nodesname+=node[i].name+',';
				    nodesfid+=node[i].fid+',';
			     } 
			   }
			}
		/*	if(bool==0){
				alert('选择公司无效,请至少选择一个部门');
				return false;
			} */
			nodesnumber = nodesnumber.substring(0, nodesnumber.length-1); 
			nodesname = nodesname.substring(0, nodesname.length-1); 
			nodesfid = nodesfid.substring(0, nodesfid.length-1); 
			if (confirm("是否确定选择:  "+nodesname+"?"))  { 
			    document.getElementById('listadminnumber').value = nodesnumber;
			    document.getElementById('listadminname').value = nodesname;
			    document.getElementById('listadminfid').value = nodesfid;
				return true;
			}else{
				return false;
			}
		}
	
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
	            orgname: $('#orgname').val()
	        });  
	    }  
	    /**
	    * 操作的方法
	    **/
	    function operation(val,row){
	    	return "<a href='javascript:void(0);' onclick='changeOrgManager("+row.id+")'>修改</a>"; 
	    }
	    function changeOrgManager(obj){
	    	var　url = "<%=request.getContextPath()%>/person/orgManager!edit?orgManager.id=" + obj;
			$("#operval").attr("href",url);
			$("#operval").click();
	    }
	    /**
	    *关闭子窗体
	    **/
	    function closeChildWinow(){
			$.fancybox.close();
			$("#tt").datagrid("reload");
		}
		
	</SCRIPT>
  </head>
  <body>
  <form action="admin/admin!add.action" method="post">
    <h1>综合管理和BP人员信息表</h1> 
		<div style="width:1300px">       
        <div style="float:left;width:25%">
        	<ul id="treeDemo" class="ztree"  style="width: 280;height:280;text-align:left;" ></ul>
        	<input type="hidden" id="listadminname" name="listadminname"/>
			<input type="hidden" id="listadminnumber" name="listadminnumber"/>
			<input type="hidden" id="listadminfid" name="listadminfid"/>
			<input type="submit" onclick="return chooseadmin();" value="确定">
        </div>
        <div style="float:left; width:75%">
			<table id="tt" class="easyui-datagrid" url='<%=basePath %>person/orgManager!allList' 
			    	style="width:850px" title="" rownumbers="true" collapsible="true" 
			    	 singleSelect="true" fitColumns="true" striped="true" nowrap="true" >  
			        <thead>  
			           <tr>  
			               <th field="orgname" align="left" width="200">部门名称</th>  
			               <th field="fnumber" align="left" width="100">部门编码</th>  
			               <th field="managername" align="right" width="100">综合管理人员</th>
			                <th field="managermail" align="left" width="150">综合管理人员邮箱</th>
			               <th field="hrbpname" align="right" width="100">BP人员</th>
			               <th field="hrbpmail" align="left" width="150">BP人员邮箱</th>
			               <th field="operator" align="left" width="100" formatter="operation">操作</th>  
			           </tr>  
			       </thead>  
			    </table>  
		</div>
</div>    
   </form> 
  </body>
</html>
