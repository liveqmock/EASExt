<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibInclude.jsp" %>
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
	<title>采购合同信息编辑</title>
  	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
    <style type="text/css"> table tr td{font-size: 12px;} </style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			//加载校验信息
			$("#portname").addClass("easyui-validatebox").validatebox({required: true,validType:'ormal',missingMessage: '必填'});
			//校验合同额,合同期限,月费用,打印设备押金
			$("#fcontractamount,#fmonthcost,#fprintdeposit,#fdurtime").addClass("easyui-validatebox").validatebox({validType: 'intOrFloat'});
			//备注设置3000个
			//length
			$("#fremark").addClass("easyui-validatebox").validatebox({validType: 'length[1,2000]'});
		})
		function fun_valid(){
			var fcontracttypeid = $("#fcontracttypeid option:selected").text();//获得合同类别的文
<%--			alert("fcontracttypeid          " + fcontracttypeid);--%>
			var fcontractamount = $("#fcontractamount").val();//合同额
			var fmonthcost = $("#fmonthcost").val();//月费用
			//增加判断信息
			if(fcontracttypeid != '快递合同' && fcontracttypeid != '总部框架协议' && fcontracttypeid != '酒店协议' && fcontracttypeid !='通讯（电话/宽带）协议'
				&& fcontracttypeid != '饮用水合同'){
<%--				if(fcontractamount == ''||fcontractamount == '0'|| fcontractamount == '0.00'){--%>
<%--				if(/^0$|^[1-9]+\d*(\.\d*[1-9])?$|^0\.\d+$/.test(fcontractamount)){--%>
				if(fcontractamount==0){
					alert("合同额不能为空或零!");
					$("#fcontractamount").focus();
					return;
				}
				if(fmonthcost == 0){
					alert("月费用不能为空!");
					$("#fmonthcost").focus();
					return;
				}
			}else if(fcontracttypeid == '快递合同' || fcontracttypeid == '总部框架协议' || fcontracttypeid == '酒店协议'|| fcontracttypeid == '通讯（电话/宽带）协议' || fcontracttypeid == '饮用水合同'){
				if(fcontractamount == ''||fcontractamount==null){
					 $("#fcontractamount").val('0.00');
				}
				if(fmonthcost == ''){
					//合同额可以为0的合同类别，其月费用可以为0
					$("#fmonthcost").val('0.00');
				}
			}
			//判断结束日期不能为空
			var dbengin = $("#dbengin").val();
			var dend = $("#dend").val();
			if(dbengin == null || dbengin == ""){
				alert("开始日期不能为空");
				return;
			}
			if(fcontracttypeid != '通讯（电话/宽带）协议' && fcontracttypeid!='饮用水合同' && dend==''){
				alert("结束日期不能为空");
				return;
			}
			
			submitTab('editBtn','合同信息');
		}
	</script>
	</head>
	<body>
    <div class="tableForm">
		<form action="<%=basePath%>adminiPurc/adminiPrucContract!update" method="post" id="editForm">
			<input type="hidden" id="id" name="contractInfo.fid" value='<s:property value="contractInfo.fid"/>' />
			<table>
				<tr class="administration">
					<td class="t-title" width="90">合同编号</td>
					<td><input class="t-text readonly" type="text" id="fnumber" name="contractInfo.fnumber"  readonly
	     		 		value='<s:property value="contractInfo.fnumber" />'/>
	     		 	</td>
     				<td class="t-title" width="90">使用部门</td>
     				<td>
     					<input class="t-text readonly" type="text" id="forgname" name="contractInfo.forgname" readonly
     					value='<s:property value="contractInfo.forgname" />'/><span class="required">*</span>
     				</td> 
               		<td class="t-title">城市</td>
					<td>
						<input class="t-text readonly" type="text" id="fcity" name="contractInfo.fcity" readonly
						value='<s:property value="contractInfo.fcity" />'/><span class="required">*</span>
					</td>
                </tr>
				<tr class="administration">
					<td class="t-title">办公室地址</td>
					<td>
						<input class="t-text readonly"  type="text" id="fofficeaddr" name="contractInfo.fofficeaddr" readonly
	     				value='<s:property value="contractInfo.fofficeaddr" />'/><span class="required">*</span>
	     			</td>
	     			<td class="t-title" width="90">末级成本中心</td>
					<td>
						<input class="t-text readonly"  type="text" id="lastcostcenter" name="contractInfo.flastcostcenter" readonly
						value='<s:property value="contractInfo.flastcostcenter" />'/><span class="required">*</span>
					</td>
					<td class="t-title" width="100">合同类别</td>
					<td width="200">
						<select class="t-text readonly" disabled="disabled" name="contractInfo.fcontracttypeid" id="fcontracttypeid"  rel="zx_dictionary" data="xzcgct" lang="${contractInfo.fcontracttypeid}">
 		 				</select>
					</td>
               </tr>
				 <tr>
           		<td class="t-title" width="100">供应商名称</td>
				<td width="200">
					<input class="t-text"  type="text" id="fsuppliername" name="contractInfo.fsuppliername" 
						value='<s:property value="contractInfo.fsuppliername" />'/>
				</td>
				<td class="t-title">合同额(元)</td>
				<td>
					<input class="t-text"  type="text" id="fcontractamount" name="contractInfo.fcontractamount" 
						value='<s:property value="contractInfo.fcontractamount" />'/>
					</td>
				<td class="t-title">合同期限</td>
				<td>
					<input class="t-text"  type="text" id="fdurtime" name="contractInfo.fdurtime" 
						value='<s:property value="contractInfo.fdurtime" />'/>
				</td>
            </tr>
            <tr>
				 <td class="t-title">开始日期</td>
			     <td>
					<input class="t-text Wdate" type="text" id="dbengin" name="contractInfo.fbegindate" value="<fmt:formatDate value="${contractInfo.fbegindate }" pattern="yyyy-MM-dd"/>" readonly  
					onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',  maxDate:'#F{$dp.$D(\'dend\')||\'2020-10-01\'}'})" />
			    </td>
			    <td class="t-title">结束日期</td>
			    <td>
			    	<input class="t-text Wdate" type="text" id="dend" name="contractInfo.fenddate" value="<fmt:formatDate value="${contractInfo.fenddate }" pattern="yyyy-MM-dd"/>" 
			    	readonly onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',   minDate:'#F{$dp.$D(\'dbengin\')}',maxDate:'2020-10-01'})"/>
			    </td>
				<td class="t-title">月费用(元)</td>
				<td>
					<input class="t-text"  type="text" id="fmonthcost" name="contractInfo.fmonthcost" 
					value='<s:property value="contractInfo.fmonthcost" />'/>				
				</td>
            </tr>
            <tr>
				<td class="t-title">打印设备押金</td>
				<td>
					<input class="t-text"  type="text" id="fprintdeposit" name="contractInfo.fprintdeposit" 
					value='<s:property value="contractInfo.fprintdeposit" />'/>			
				</td>
				<td class="t-title">状态</td>
				<td>
						${contractInfo.fext10}
				</td>
				<td class="t-title">合同到期跟进</td>
				<td>
 		 			<select name="contractInfo.fcontractstatus" id="fcontractstatus" rel="zx_dictionary" data="xzcghtzt" lang="${contractInfo.fcontractstatus}">
 		 				<option value="">---请选择---</option>
 		 			</select>
				</td>
			</tr>
			 <tr>
			 	<td class="t-title">付款方式</td>
				<td>
					<select name="contractInfo.fpaytypeid" id="fpaytypeid" rel="zx_dictionary" data="xzcgpw" lang="${contractInfo.fpaytypeid}">
 		 			</select>
				</td>
				<td class="t-title">备注</td>
				<td colspan="3">
					<textarea rows="4" style="width: 97%" id="fremark" name="contractInfo.fremark"><s:property value="contractInfo.fremark" /></textarea>
				</td>
			</tr>
			</table>
            <div class="t-but">
			    <a id="editBtn" href="javascript:void(0)" onclick="fun_valid();" class="but-change"><s:if test='contractInfo!=null'>修改</s:if><s:else>新增</s:else></a>	
			    <a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
            </div>
		</form>
		</div>
	</body>
</html>
