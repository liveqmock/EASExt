<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>  
    <head>  
        <meta charset="UTF-8">  
        <title>Complex Toolbar on Dialog - jQuery EasyUI Demo</title>  
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
    </head>  
    <body>  
        <div class="demo-info">  
            <div class="demo-tip icon-tip"></div>  
            <div>This sample shows how to create complex toolbar on dialog.</div>  
        </div>  
        <div style="margin:10px 0;">  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')">Open</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">Close</a>  
        </div>  
        <div id="dlg" class="easyui-dialog" title="显示内容" style="width:400px;height:200px;padding:10px"  
                data-options="  
                    iconCls: 'icon-save',  
                    toolbar: '#dlg-toolbar',  
                    buttons: '#dlg-buttons'">  
            The dialog content.  
        </div>  
    </body>  
    </html>