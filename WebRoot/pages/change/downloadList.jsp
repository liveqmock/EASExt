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
         if(confirm("����Ҫ�ڷ���˶�̬���,��Ҫʱ��Ƚϳ�,�Ƿ������������?"))
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
                alert("��û��ѡ��������");
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
                            �Ժ��������б�,�����������
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
                                    onclick="downloadFile1('<s:property value="key" />','<s:property value="value" />')">�������</a>
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
                                    onclick="downloadFile1('<s:property value="key" />','<s:property value="value" />')">�������</a>
                            </td>
                        </tr>
                    </s:else>
                </s:iterator>
            </table>
            <div align="center">
                <input class="green_at_bn" title="ѡ�����ص��ļ�"
                    onClick="SelectAll(this.form)" type="button" value="ȫѡ">
                <input class="green_at_bn" title="����ѡ�������ļ�"
                    onClick="TurnOver(this.form)" type="button" value="��ѡ">
                <input class="green_at_bn" title="����ѡ���ļ�"
                    onClick="DownlodSelected(this.form)" type="button" value="���������ļ�">
            </div>
        </form>
        <frame src="" id="dis">

        </frame>
    </body>
</html>