<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="eltag" uri="/eltag"%>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/plugins/jquery.my97.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
		$("#longfnumber,#fname").addClass("easyui-validatebox").validatebox({required: true,missingMessage: '必填'});
		$("#longfnumber").validatebox({validType: "exist['<%=basePath %>roster/rosterAction!ifNumHasExists','#oldlongfnumber']"});
		$("#femail").validatebox({validType: "exist['<%=basePath %>roster/rosterAction!ifEmailHasExists','#oldfemail']"});
		})
		//设置下拉框默认选项
		$(function(){
			<s:if test='roster == null'>
			$("#fpersonstatus option[value='147']").attr("selected", true);//人员状态 试用
			$("#fpositionlevel option[value='220']").attr("selected", true);//职级  无
			$("#foldfourunit option[value='180']").attr("selected", true);//原四级部门 无
			$("#fthreeunit option[value='257']").attr("selected", true);//三级部门 无
			$("#ffourunit option[value='279']").attr("selected", true);//四级部门 无
			$("#ffiveunit option[value='235']").attr("selected", true);//五级部门 无
			$("#fsixunit option[value='334']").attr("selected", true);//六级部门 无
			$("#frecruitmentchannels option[value='504']").attr("selected", true);//招聘渠道 校园招聘
			var str=document.getElementsByName("chosefinaitemid"); 
			for(var i=0;i<str.length;i++){ 
			            if(str[i].value=='559'){ 
			            	str[i].checked = true;
			           } 
			        } 
			</s:if>
             
	      });

		
		
		function rosterTree(){
			var url="<%=basePath%>rosterorgadmin/rosterOrgAdminAction!viewRosterOrgAdmin"
				$('#iframeWin').window({
					title:"",
					left:8+$(window).scrollLeft(),
					top:6+$(window).scrollTop(),
					width:($(window).width()-20),height:($(window).height()),//原来-120
					onClose : function(){ //点击关闭按钮时清除iframe的src
						$("#iframeSource").attr("src",""); 
					} 
				})
				$("#iframeSource").attr("src",url);
				$('#iframeWin').window('open');
			}
		$(function(){
			
			//团队由原四级部门_五级部门拼接而成
			$("#foldfourunit,#ffiveunit").change(function(){
				 var yvalue = $("#foldfourunit").find("option:selected").text();
				 var fiveValue = $("#ffiveunit").find("option:selected").text();
				 $("#fteam").val(yvalue+"_"+fiveValue);
			   });
		})
		
		
		//根据text设置下拉框选中项
		function getSelectTrue(id,text){
			var count=$("#"+id+" option").length;
			 for(var i=0;i<count;i++){ 
				 if($("#"+id).get(0).options[i].text == text)  
				{  
  		 	 $("#"+id).get(0).options[i].selected = true;  
         	  break;  
                }  
			 }
		}
					
			//通过组织架构树带出行政组织、三级部门、四级部门、五级部门、六级部门的字段值
			function setRosterOrgAdmin(fadminfid1,fthreeunit1,ffourunit1,ffiveunit1,fsixunit1){
				$("#fadminfid").val(fadminfid1);
				//$("#fthreeunit option[text='"+fthreeunit1+"']").attr("selected", true);
				getSelectTrue("fthreeunit",fthreeunit1);
				getSelectTrue("ffourunit",ffourunit1);
				getSelectTrue("ffiveunit",ffiveunit1);
				getSelectTrue("fsixunit",fsixunit1);
			}
			//设置复选框全选、全不选
			$(function(){
				
				$("#allchose,#nochose").click(function(){
					var radioval = $("input[type='radio']:checked").val();
					if(radioval == '1'){//1--全选
						$('input[name="choseunititemid"]').attr("checked",true);  
						} else{//--反选
							$('input[name="choseunititemid"]').each(function(){  
          					    var $a=$(this).attr("checked");  
								 $(this).attr("checked",!$a);  
							});
				   }
			});
			});
			
	</script>
	<style type="text/css">
		table tr td{font-size: 12px;}
	</style>
	</head>
	<body>
	<form action="<%=basePath%>roster/rosterAction!<s:if test='roster != null'>update</s:if><s:else>insert</s:else>" method="post" id="editForm">
	 <input type="hidden" id="fid" name="roster.fid" value='<s:property value="roster.fid"/>' />
	 <div class="tableForm">
     	<div class="title">基本信息</div>
		<table id="viewTab">
		<tr>
		<td class="t-title" width="120">5位员工编号</td>
		 <td><input class="t-text" type="text" id="fnumber" name="roster.fnumber" value='<s:property value="roster.fnumber"/>'/></td>
		<td class="t-title" width="120">12位员工编号</td>
		<td><input class="t-text" type="text" id="longfnumber" name="roster.longfnumber" value='<s:property value="roster.longfnumber"/>'/><span class="required">*</span></td>
		<input type="hidden" id="oldlongfnumber" value='<s:property value="roster.longfnumber"/>'/>
		<td class="t-title" width="120">身份证号码</td>
		<td><input class="t-text" type="text" id="fcardnum" name="roster.fcardnum" value='<s:property value="roster.fcardnum"/>'/></td>
		</tr>
		
		<tr>
		<td class="t-title">姓名</td>
		<td><input class="t-text" type="text" id="fname" name="roster.fname" value='<s:property value="roster.fname"/>'/><span class="required">*</span></td>
		<td class="t-title">性别</td>
		<td>
			<select name="roster.fgender" id="fgender" rel="zx_dictionary" data="68" lang="${roster.fgender}">
  		 	</select>
		</td>
		<td class="t-title">年龄</td>
		<td><input class="t-text" type="text" id="fage" name="roster.fage" value='<s:property value="roster.fage"/>'/></td>
		</tr>
		
		<tr>
		<td class="t-title">人员状态</td>
		<td>
			<select name="roster.fpersonstatus" id="fpersonstatus" rel="zx_dictionary" data="84" lang="${roster.fpersonstatus}">
  		 	</select>
		</td>
		<td class="t-title">电子邮箱</td>
		<td><input class="t-text" type="text" id="femail" name="roster.femail" value='<s:property value="roster.femail"/>'/></td>
		<input type="hidden" id="oldfemail" value='<s:property value="roster.femail"/>'/>
		<td class="t-title">工作城市</td>
		<td>
				<select name="roster.fcity" id="fcity" rel="zx_dictionary" data="85" lang="${roster.fcity}">
  		 		</select>
		</td>
		</tr>
		</table>
		
        <div class="title">部门信息</div>
	  	<table id="viewTab">
	  	<tr>
	  	<td class="t-title" width="140">行政组织</td>
	  	<td  colspan="5" ><input class="t-text"  type="text"  id="fadminfid" name="roster.fadminfid" style="width:860px;" onclick="rosterTree()" value='<s:property value="roster.fadminfid"/>' /></td>
	  	</tr>
	  	
	  	<tr>
		<td class="t-title" width="140">职类</td>
		<td>
				<select name="roster.fpositiontype" id="fpositiontype" rel="zx_dictionary" data="92" lang="${roster.fpositiontype}">
  		 		</select>
		</td>
		<td class="t-title" width="160">岗位</td>
		<td>
				<select name="roster.fposition" id="fposition" rel="zx_dictionary" data="93" lang="${roster.fposition}">
  		 		</select>
		</td>
		<td class="t-title" width="180">职级</td>
		<td>
				<select name="roster.fpositionlevel" id="fpositionlevel" rel="zx_dictionary" data="94" lang="${roster.fpositionlevel}">
  		 		</select>
		</td>
		</tr>
		
		<tr>
		<td class="t-title">三级部门</td>
		<td>
			<select name="roster.fthreeunit" id="fthreeunit" rel="zx_dictionary" data="86" lang="${roster.fthreeunit}" >
			<option value="-1"></option>
  		 	</select>
		</td>
		<td class="t-title">四级部门</td>
		<td>
			<select name="roster.ffourunit" id="ffourunit" rel="zx_dictionary" data="87" lang="${roster.ffourunit}" >
  		 	<option value="-1"></option>
  		 	</select>
		</td>
		<td class="t-title">原四级部门</td>
		<td>
			<select name="roster.foldfourunit" id="foldfourunit" rel="zx_dictionary" data="89" lang="${roster.foldfourunit}" >
  		 	<option value="-1"></option>
  		 	</select>
		</td>
		</tr>
		<tr>
		<td class="t-title">五级部门</td>
		<td>
		 	
			<select name="roster.ffiveunit" id="ffiveunit" rel="zx_dictionary" data="90" lang="${roster.ffiveunit}" >
  		 	<option value="-1"></option>
  		 	</select>
		</td>
		<td class="t-title">六级部门</td>
		<td>
		 	
			<select name="roster.fsixunit" id="fsixunit" rel="zx_dictionary" data="91" lang="${roster.fsixunit}">
  		 	<option value="-1"></option>
  		 	</select>
		</td>
		<td class="t-title">团队</td>
		<td><input class="t-text" type="text" id="fteam" name="roster.fteam" value='<s:property value="roster.fteam"/>'/></td>
		</tr>
		
		<tr>
		<td class="t-title">上级团队经理</td>
		 <td><input class="t-text" type="text" id="parentteammanager" name="roster.parentteammanager" value='<s:property value="roster.parentteammanager"/>'/></td>
		<td class="t-title">上级大团队经理</td>
		<td><input class="t-text" type="text" id="parentbigteammanager" name="roster.parentbigteammanager" value='<s:property value="roster.parentbigteammanager"/>'/></td>
		<td class="t-title">上级营销部经理</td>
		<td><input class="t-text" type="text" id="parentsalemanager" name="roster.parentsalemanager" value='<s:property value="roster.parentsalemanager"/>'/></td>
		</tr>
		
		<tr>
		<td class="t-title">大团队经理任职日期</td>
		<td><input class="t-text Wdate" type="text" name="roster.fteamleaderdate" style="width:200px;"  id="fteamleaderdate" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<fmt:formatDate value="${roster.fteamleaderdate}" pattern="yyyy-MM-dd"/>" /></td>
		<td class="t-title">保险从业资格证</td>
		<td>	
			<select name="roster.insurancecert" id="insurancecert" rel="zx_dictionary" data="149" lang="${roster.insurancecert}">
  		 	</select>
		</td>
		<td class="t-title">基金从业资格证</td>
		<td>	
			<select name="roster.fundcert" id="fundcert" rel="zx_dictionary" data="150" lang="${roster.fundcert}">
  		 	</select>
		</td>
		</tr>
		<tr>
		<td class="t-title">内部推荐人姓名</td>
		<td><input class="t-text" type="text" id="frecommendname" name="roster.frecommendname" value='<s:property value="roster.frecommendname"/>'/></td>
		<td class="t-title">入司日期</td>
		<td><input class="t-text Wdate" type="text" name="roster.fmobdate" style="width:200px;"  id="fmobdate" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<fmt:formatDate value="${roster.fmobdate}" pattern="yyyy-MM-dd"/>" /></td>
		<td class="t-title">招聘渠道</td>
		<td>
				<select name="roster.frecruitmentchannels" id="frecruitmentchannels" rel="zx_dictionary" data="96" lang="${roster.frecruitmentchannels}">
  		 		</select>
		</td>
		</tr>
		
		<tr>
		<td class="t-title">是否hope成员</td>
		<td colspan="4">
				<select name="roster.fishopeperson" id="fishopeperson" rel="zx_dictionary" data="95" lang="${roster.fishopeperson}">
  		 		</select>
		</td>
		</tr>
		
		<tr>
		<td class="t-title">入职日期</td>
		<td><input class="t-text Wdate" type="text" name="roster.fentrydate" style="width:200px;"  id="fentrydate" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<fmt:formatDate value="${roster.fentrydate}" pattern="yyyy-MM-dd"/>" /></td>
		<td class="t-title">转正日期</td>
		<td><input class="t-text Wdate" type="text" name="roster.fconfirmdate" style="width:200px;"  id="fconfirmdate" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<fmt:formatDate value="${roster.fconfirmdate}" pattern="yyyy-MM-dd"/>" /></td>
		<td class="t-title">离职日期</td>
		<td><input class="t-text Wdate" type="text" name="roster.fleavedate" style="width:200px;"  id="fleavedate" 
				  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="<fmt:formatDate value="${roster.fleavedate}" pattern="yyyy-MM-dd"/>" /></td>
		</tr>
		
		<tr>
	  			<td class="t-title">理财规划师持证</td>
	  			<td colspan="5">
	  				<ul>
                       <c:forEach var="finalist" items="${financlist}">
                           <li>
                               <input type="checkbox" name="chosefinaitemid" value="${finalist.id}"
                               <c:forEach var="chosefinalist" items="${chosefinanclist}">
                                        <c:if test="${finalist.id == chosefinalist}">
                                         checked = "checked"
                                        </c:if >
                                     </c:forEach>
                               >${finalist.itemname}</input>
                           </li>
                       </c:forEach>
                   </ul>
				</td>
	  	</tr>    
		
		
		<tr>
	  	<td class="t-title">备注</td>
	  	<td   colspan="5"><textarea id="fremarks" rows="3" style="width:860px;" name="roster.fremarks" value='<s:property value="roster.fremarks"/>'/></textarea>
	  	</tr>
	  	<s:if test='roster != null'>
		<c:forEach items="${sessionScope.personrole}" var="personrole" >
			<c:if test="${personrole == '587' || personrole == '568'}">
				<tr> <td colspan="1"></td>
				<td>
	  			<input type="radio" name="allnochose" id="allchose" value="1" />全选 
	  			<input type="radio" name="allnochose" id="nochose" value="2"/>反选
	  			</td>
	  			</tr> 
				<tr>
	  			<td class="t-title">负责营销部</td>
	  			<td colspan="5">
	  				<ul>
                       <c:forEach var="oldunit" items="${oldfourunitlist}">
                           <li>
                               <input type="checkbox" name="choseunititemid" value="${oldunit.id}"
                               <c:forEach var="choseunit" items="${choseoldfourunitlist}">
                                        <c:if test="${oldunit.id == choseunit}">
                                         checked = "checked"
                                        </c:if >
                                     </c:forEach>
                               >${oldunit.itemname}</input>
                           </li>
                       </c:forEach>
                   </ul>
				</td>
	  			</tr>
			</c:if>
		</c:forEach>
		</s:if>
	  	<input type="hidden" id="rostertreeURL" value="<%=basePath%>rosterorgadmin/rosterOrgAdminAction!viewRosterOrgAdmin"/>
	  	</table>
	  	<div class="t-but">
            <a href="javascript:void(0)" id="editBtn" class="but-change" onclick="submitTab('editBtn','人事花名册')">保存</a>	
			<a href="javascript:void(0)" class="but-cancel" onclick="closeTab()">取消</a>
        </div>
     </div>
     </form>
     <div id="iframeWin" class="easyui-window" title="信息" modal="true" closed="true"
		iconCls="icon-save" style="width:600px;height:500px;padding:10px;">
		<iframe id="iframeSource" name="iframeSource" frameborder="0" width="100%" height="100%"></iframe>
	</div>
	</body>
</html>
