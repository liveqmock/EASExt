<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function downloadFile1(filenames,filepaths){
        location.href=encodeURI("<%=basePath%>limitedpartner/download!execute?filenames="+filenames+"&filepaths="+filepaths);
    }
    function SelectAll(oForm)
    {
        for(var i=0;i<oForm.url.length;i++)
        {
            oForm.url[i].checked=true;
        }
    }
    function TurnOver(oForm)
    {
        for(var i=0;i<oForm.url.length;i++)
        {
            oForm.url[i].checked=!oForm.url[i].checked;
        }
    }
    function DownlodSelected(oForm){
         if(confirm("因需要在服务端动态打包,需要时间比较长,是否继续批量下载?"))
            {
             var arrDownloadList = [];
            for(var i=0;i<oForm.url.length;i++){
                if(oForm.url[i].checked==true){
                    if(arrDownloadList.length==0){
                        arrDownloadList[0] = oForm.url.value;
                    }
                    arrDownloadList[arrDownloadList.length] = oForm.url[i].value;
                }
            }
            if (arrDownloadList.length>0){
                var temp = [];
                var filenames="";
                var filepaths="";
                for(var i=1;i<arrDownloadList.length;i++){
                    temp = arrDownloadList[i].split(",")
                    if(filenames=="" && filepaths==""){
                        filenames=temp[0]
                        filepaths=temp[1]
                    }else{    
                        filenames=filenames+"|"+temp[0];
                        filepaths=filepaths+"|"+temp[1];
                    }
                }
                downloadFile1(filenames,filepaths);
            }else{
                alert("还没有选中下载项");
            }
           }
    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
        <title>Insert title here</title>
        <script type="text/javascript" src="dwr/engine.js"></script>
        <script type="text/javascript" src="dwr/util.js"></script>
        <script type="text/javascript" src="dwr/interface/downloaddwr.js"></script>
    </head>
    <body>
        <form name="myform" style="display: inline" onSubmit="return false">
            <table width="50%" align="center">
                <tr>
                    <td colspan="2">
                        <h3>
                            以后是下载列表,点击进行下载
                        </h3>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <font color="red"><s:fielderror></s:fielderror> </font>
                    </td>
                </tr>
                <s:iterator value="#request.fileMap" status="stuts">
                    <s:if test="#stuts.odd == true">
                        <tr style="background-color: #77D9F6">
                            <td>
                                <input name="url" type="checkbox" id="url"
                                    value="<s:property value="key" />,<s:property value="value" />">
                            </td>
                            <td>
                                <s:property value="key" />
                            </td>
                            <td>
                                <a href="#"
                                    onclick="downloadFile1('<s:property value="key" />','<s:property value="value" />')">点击下载</a>
                            </td>
                        </tr>
                    </s:if>
                    <s:else>
                        <tr style="background-color: #D7F2F4">
                            <td>
                                <input name="url" type="checkbox" id="url"
                                    value="<s:property value="key" />,<s:property value="value" />">
                            </td>
                            <td>
                                <s:property value="key" />
                            </td>

                            <td>
                                <a href="#"
                                    onclick="downloadFile1('<s:property value="key" />','<s:property value="value" />')">点击下载</a>
                            </td>
                        </tr>
                    </s:else>
                </s:iterator>
            </table>
            <div align="center">
                <input class="green_at_bn" title="选择下载的文件"
                    onClick="SelectAll(this.form)" type="button" value="全选">
                <input class="green_at_bn" title="反向选择下载文件"
                    onClick="TurnOver(this.form)" type="button" value="反选">
                <input class="green_at_bn" title="下载选中文件"
                    onClick="DownlodSelected(this.form)" type="button" value="批量下载文件">
            </div>
        </form>
        <frame src="" id="dis">

        </frame>
    </body>
</html>