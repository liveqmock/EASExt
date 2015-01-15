<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="<%=basePath%>">
	<title>My JSP 'update.jsp' starting page</title>
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/record_jsp_style.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/themes/icon.css">
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
   	<link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/userrole.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#chargeCompanies').combobox({  
			    url:'<%=request.getContextPath()%>/rent/financeUser!selectAllSignatorys',  
				mode:'remote',
			    valueField:'SIGNATORY',  
			    textField:'SIGNATORY',
			    multiple:true,
			    onLoadSuccess:function(){//设置默认选中项
			 //   	var data = $(this).combobox('getData');
			 //   	$(this).combobox('setValue', $("#hiddenChargeCompanies").val())
	    		}  
			}); 
			$("#userEmail").addClass("easyui-validatebox").validatebox({required: true,validType:'emailcn',missingMessage: '必填'});
			$("#userName").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
<%--			$("#userId").addClass("easyui-validatebox").validatebox({required: true,validType: "selectRequired['#userId']"});--%>
<%--			$("#isGroupLeader").addClass("easyui-validatebox").validatebox({required: true,validType: "selectRequired"});--%>
<%--			$("#chargeCompanies").addClass("easyui-validatebox").validatebox({required: true,validType: "selectRequired"});--%>
<%--			$("#financeGroupId").addClass("easyui-validatebox").validatebox({required: true,validType: "selectRequired"});--%>
		})
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>

	<body>
    <div class="tableForm">
		<form action="rent/financeUser!<s:if test='financeUser != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
			<input type="hidden" id="id" name="financeUser.id" value='<s:property value="financeUser.id"/>' />
            <div class="title">组用户信息编辑</div>
			<table>
				<s:if test='financeUser != null'></s:if><s:else>
				<tr>
                    <td class="t-title" width="80">选择系统用户</td>
                    <td>
                        <select id="userId" name="financeUser.userId">
                            <option value="" >---请选择---</option>
                            <s:iterator value="systemUsers">
                                <option value="<s:property value='ID'/>" <s:if test="financeUser.userId==ID">selected</s:if>><s:property value="USERNAME" /></option>
                            </s:iterator>
                        </select>
                    </td>
                    </s:else>
                    <td class="t-title" width="100">姓名</td>
                    <td><input class="t-text" type="text" id="userName" name="financeUser.userName" value='<s:property value="financeUser.userName" />'/><span class="required">*</span></td>
                    <td class="t-title" width="100">邮箱</td>
                    <td><input class="t-text" type="text" id="userEmail" name="financeUser.userEmail" value='<s:property value="financeUser.userEmail" />'/><span class="required">*</span></td>
             	</tr>
                <tr>
                    <td class="t-title">所在组</td>
                    <td>
                        <select id="financeGroupId" name="financeUser.financeGroupId">
                            <option value="" >---请选择---</option>
                            <s:iterator value="financeGroups">
                                <option value="<s:property value='ID'/>" <s:if test="financeUser.financeGroupId==ID">selected</s:if>  ><s:property value="NAME" /></option>
                            </s:iterator>
                        </select>
                    </td>
                    <td class="t-title">是否组长</td>
                    <td>
                        <select id="isGroupLeader" name="financeUser.isGroupLeader">
                            <option value="" >---请选择---</option>
                            <option value="1" <s:if test="financeUser.isGroupLeader==1">selected</s:if> >组长</option>
                            <option value="0" <s:if test="financeUser.isGroupLeader==0">selected</s:if> >组员</option>
                        </select>
                    </td>
                    <%--<td class="t-title">所负责公司</td>
                    <td>
                        <input class="t-text" id="chargeCompanies" name="financeUser.chargeCompanies" class="easyui-combobox" value="<s:property value='financeUser.chargeCompanies'/>"/><span class="required">*</span>
                        <input type="hidden" id="hiddenChargeCompanies" value="<s:property value='financeUser.chargeCompanies'/>">
                    </td>
				--%></tr>
					<tr>
	  			<td class="t-title">所负责公司</td>
	  			<td colspan="5">
	  				<ul>
                       <c:forEach var="complist" items="${signatorys}">
                           <li>
                               <input type="checkbox" name="chosecomplist" value="${complist.ID}"
                               <c:forEach var="havelist" items="${choseCompanylist}">
                                        <c:if test="${complist.ID == havelist}">
                                         checked = "checked"
                                        </c:if >
                                  </c:forEach>
                               >${complist.NAME}</input>
                           </li>
                       </c:forEach>
                        <input type="hidden" id="rolenum" />
                   </ul>
				</td>
	  			</tr> 
				
			</table>
            <div class="t-but">
			    <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitFinaUserTab('editBtn','组用户列表')"><s:if test='financeUser != null'>修改</s:if><s:else>新增</s:else></a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
    </div>
	</body>
</html>
