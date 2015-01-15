<%@page contentType="text/html; charset=gbk" pageEncoding="gbk" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html>
<head>
<title>管理页面</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" >
	<meta http-equiv="refresh" content="6000">
	<meta http-equiv=Content-Type content=text/html;charset=gb2312>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/inc/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="wbox/wbox.css" />
	<style type="text/css">
		.a1:link {
		 color: #FFFFFF;
		}
		.a1:visited {
		 color: #FF0088;
		}
		.a1:hover {
		 color: #FF0088;
		}
		.a1:active {
		 color: #993399;
		}
	</style>
<script language=JavaScript1.2>
	$(document).ready(function(){
		//弹出窗口
		$("#operval, #editself").fancybox({
			'hideOnContentClick': false,
			'width'	 : 500,
			'height'	 : '180%',
			'autoScale'     	: false,
			'transitionIn'	 : 'none',
			'transitionOut'	 : 'none',
			'type'	 : 'iframe',
			'titleFormat': function(title){return  "";},
			'scrolling':'no'
		});		
	});
	
	
function openNewDialog(){
			var　url = "<%=request.getContextPath()%>/admin/user!editself";
			alert(url);
			$("#operval").attr("href",url);
			$("#operval").click();
	}		
			
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
 	function changePassword(){
    	var　url = "<%=request.getContextPath()%>/admin/user!editself";
    	 var id=window.showModalDialog(url, window," center：yes;dialogWidth:500px;dialogHeight:200px;status:no;resizable:yes");
		//window.showModalDialog(url,window,"dialogWidth:800px;dialogHeight:400px;screenX:600px;screenY:400px;");
		//$("#editself").attr("href",url);
			//$("#editself").click();

    }



 	//function slide(){        
 	 //	$.messager.show({               
 	 	// 	title:'提示',               
 	 	 //	msg:'9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈。',              
 	 	 	//timeout:15000,                
 	 	 	//showType:'slide'           
 	 	 	 //	});
	 	 	   //    }


    
</script>
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="64%" height="38" class="admin_txt">登录名：<b>${user.username } </b> 您好,感谢登陆使用！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="a1" href="upload/HR.docx" >帮助文档</a>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="a1" href="upload/shipin.exe" >视频下载</a>
        	<%--<c:if test="${user.typeid>=2||user.typeid <=10}"> 
        		<marquee scrollamount="8" loop="2" style="color:blue;font-size:14px;">
        		9月5日--10月15日为试运行阶段，如使用过程中发现问题请向所属部门总接口人及时反馈。</marquee>
        	</c:if>
       			 
        --%></td>
        
        <td width="16%"><input type="button" style="height: 18px;background-color:#336699;color: white;border: 0px;font-family: fantasy" value="修改密码" onclick="changePassword();"/></td>
        <td width="16%"><a href="${pageContext.request.contextPath}/admin/login!exitLogin" target="_parent"><img src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="4">&nbsp;</td>
       </tr>
    </table></td>
  </tr>
</table>
<a href="#" id="operval" target="_"></a>
<a href="#" id="editself"></a>
</body>
</html>

