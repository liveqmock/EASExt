<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
		<script type="text/javascript">
	    $(document).ready(function(){
		$("#pmail,#raplacemail").validatebox({validType: "emailcn['#copypeoplemail']"});
		})
	</script>
		  <style type="text/css">
		  .rowelem_50 {
	background-color: #F9F8F2;
	padding-top: 5px;
	padding-bottom: 5px;
	line-height: 24px;
	background-repeat: repeat-y;
	background-position: right;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #E3E1D7;
	border-right-style: solid;
	border-right-color: #E3E1D7;
	border-top-width: 1px;
	border-left-width: 0px;
	border-top-style: solid;
	border-left-style: solid;
	border-top-color: #FFFFFF;
	border-left-color: #FFFFFF;
	width: 50%;
	display: block;
	height: 24px;
	border-right-width: 0px;
	background-image: url(../images/form_bg1.jpg);
	float: left;
	*margin-right:-1px !important;
}

.input_style_default {
	font-size: 12px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #D3C6AF;
	border-right-color: #EEE9DF;
	border-bottom-color: #EEE9DF;
	border-left-color: #D3C6AF;
	color: #333333;
	font-family: Tahoma, Geneva, sans-serif;
	padding: 3px;
	background-color: #F7F8E9;
}
.form_right_width_auto {
	margin-left: 118px;
	display: block;
	margin-right: 10px;
	word-break: break-all;
}
.rowelem {
	background-color: #F9F8F2;
	padding-top: 5px;
	padding-bottom: 5px;
	line-height: 24px;
	background-image: url(../images/form_bg1.jpg);
	background-repeat: repeat-y;
	background-position: right;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #E3E1D7;
	border-right-width: 0px;
	border-right-style: solid;
	border-right-color: #E3E1D7;
	border-top-width: 1px;
	border-left-width: 0px;
	border-top-style: solid;
	border-left-style: solid;
	border-top-color: #FFFFFF;
	border-left-color: #FFFFFF;
	float: left;
	width: 100%;
}
		  </style>
	</head>

	<body>
		<form action="person/black!save" method="post" >
			<div class="rowelem_50">
			    &nbsp;人员名称: <input type="text" id="pname" name="blackListInfo.pname" class="input_style_default" />
			</div>
			  <div class="rowelem_50">
			     &nbsp;人员编码: <input type="text" id="pname" name="blackListInfo.pnumber" class="input_style_default" />
			  </div>
			  
			  <div class="rowelem">
			     &nbsp;人员邮箱:  <input type="text" id="pmail" name="blackListInfo.pmail" class="input_style_default" />
			  </div>
			  
			  <div class="rowelem_50">
		   			&nbsp;预警类型：
					<select name="blackListInfo.warntype">
							<s:iterator value="typeList">
								<option value="<s:property value="id"/>" >
									<s:if test="id==1">全部</s:if>
									<s:else>
										<s:property value="typename" />
									</s:else>
								</option>
							</s:iterator>
					</select>
  				</div>
  				
			 <div class="rowelem_50">
		   			&nbsp;处理方式：
					<select name="blackListInfo.modeid">
							<s:iterator value="modeList">
								<option value="<s:property value="fid"/>" >
									<s:property value="processmode" />
								</option>
							</s:iterator>
						</select>
  				</div>
  			
  			<div class="rowelem_50">
			     &nbsp;替代人员名称: <input type="text" id="raplacename" name="blackListInfo.raplacename" class="input_style_default" />
			</div>
			  <div class="rowelem_50">
			     &nbsp;替代人员编码:  <input type="text" id="raplacenumber" name="blackListInfo.raplacenumber" class="input_style_default" />
			  </div>
			  
			  <div class="rowelem">
			   &nbsp;替代人员邮箱:  <input type="text" id="raplacemail" name="blackListInfo.raplacemail" class="input_style_default" />
			  </div>
			  
  			<br>
			    <div class="rowelem" align="center">
				     <input type="submit" value="添加" />&nbsp;&nbsp;
				     <input type="button" value="取消" onclick="javascript:parent.$.fancybox.close();"/>
				</div>
		</form>
	</body>
</html>
