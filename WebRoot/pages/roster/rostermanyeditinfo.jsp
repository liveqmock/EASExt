<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投三花名册批量修改</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/inc/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min_1.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/utils.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/tab.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validate.js"></script>
	<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/upload.js"></script>
	<%@include file="/common/testurlInclude.jsp" %>
	<script type="text/javascript">

	
	var fids = '<%=request.getAttribute("manyfids")%>';
	$(function() {

				$("#resetBtn").click(function(){
				$("#fpersonstatus,#foldfourunitpage,#fposition,#fourunit,#fiveunit").val("");
				})
		
				$('#tt').datagrid({
					//下面是 datagrid的基本 配置信息 
					url:'<%=basePath%>roster/rosterAction!queryManyEditJson?fids='+fids,
					title: "",
					width: ($(window).width()-30),
					scrollbarSize:0,
					height: 'auto',
					nowrap: false,  //  是否在一行显示数据
					striped: true,   //  是否 显示 斑马线  
					fitColumns: true,  // 自动填充 列 宽
					collapsible: true,   // 是否 有滑动效果 
					loadMsg: '数据连接中.....',  //加载页面时候的提示消息
					remoteSort: false,    // 是否使用本地 排序，注意 选择 本地 排序后，其他自定义排序都将失去效果 
					sortOrder: 'desc',    // 排序 方法 
					rownumbers:true,
					singleSelect:false, 
					columns: [[
						  {field:'fnumber',title:'5位编号',width:fixColumnWidth(0.01700)},
						  {field:'longfnumber',title:'12位员工编号',width:fixColumnWidth(0.03500)},
						  {field:'fname',title:'姓名',width:fixColumnWidth(0.01800)},
						  {field:'fpersonstatusname',title:'人员状态',width:fixColumnWidth(0.0200)},
						  {field:'fpositionname',title:'岗位',width:fixColumnWidth(0.03500)},
						  {field:'ffourunitname',title:'四级部门',width:fixColumnWidth(0.06000)},
						  {field:'foldfourunitname',title:'原四级部门',width:fixColumnWidth(0.02500)},
						  {field:'ffiveunitname',title:'五级部门',width:fixColumnWidth(0.01500)},
						  {field:'fentrydate',title:'入职日期',width:fixColumnWidth(0.02900),
								formatter:function(val){
									if(val!=null && val!="")
									return val.substring(0,val.indexOf("T"));
								}
							  },
						  {field:'fleavedate',title:'离职日期',width:fixColumnWidth(0.02900),
								  formatter:function(val){
									if(val!=null && val!="")
									return val.substring(0,val.indexOf("T"));
								}
								  }
					
					]],
					//下面 定义 分页配置 ：
					pageSize:15,
					pageList:[5,10,15,20],
					pagination:true,   // 是否 要分页 
					pageNumber:1//设置初始页为1
					//onLoadSuccess: omitLongData()
				});
				//下面这个方法 用于 配置  分页的信息 
				displayMsg($('#tt'));
					 
	});		

	function submitManyFormTab(id,title){
		$('#editForm').form('submit',{
			onSubmit: function(){
				
				//判断下拉框是否有值
				var notnull = false;
				var fpersonstatus = $("#fpersonstatus").val();
				var fposition = $("#fposition").val();
				var fourunit = $("#fourunit").val();
				var fiveunit = $("#fiveunit").val();
				var foldfourunitpage = $("#foldfourunitpage").val();
				if(fpersonstatus == "" && fposition == "" && fourunit == "" && foldfourunitpage == ""  && fiveunit == ""){
					$.messager.alert("提示","请选择要批量修改的字段信息.");
					notnull = false;
					}else{
					notnull = true;
						}
				
				if(notnull == true){
					if(id){
						$("#"+id).attr("disabled","disabled");
						$("#"+id).removeAttr('onclick');
					} 
					$.messager.confirm('警告', '确定批量修改这些信息吗？', function(r){
						if(r){
					$("#editForm").ajaxSubmit({
						dataType:"text",
			 			success:function(json){//文件上传成功后执行,msg为服务器端返回的信息
							$.messager.alert("提示",toJson(json).success == "true"?"操作成功！":"操作失败！",
									toJson(json).success == "true"?"info":"error",function(){
								if(title!=undefined && title!=null && title!=''){
									var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
									if(refrTab){
									var url = $(refrTab.panel('options').content).attr('src');
										getTopWin().$('#tabs').tabs('update',{
											tab:refrTab,options:{content:createFrame(url)}
												})
									}
									
								}
								closeTab();
							});
			 			}
			 		});	
						}else{
							closeTab();
							}
						
						});
				}else{
					if(id) $("#"+id).removeAttr("disabled");
				}
				return false;
			}
		})
	}
	</script>
  </head>
  <body>
  <form action="<%=basePath%>roster/rosterAction!updateManyEditInfo" method="post" id="editForm">
   <div class="search">
		<table>
			<tr>
				<td class="s-t">人员状态</td><td >
				<select  id="fpersonstatus" name="fpersonstatus" rel="zx_dictionary" data="pstatus">
				<option value=""></option>
  		 		</select> 
				</td>
				
				<td class="s-t">岗位</td><td >
			 	<select id="fposition" name="fposition" rel="zx_dictionary" data="fposition">
				<option value=""></option>
  		 		</select>
				</td>
				
				<td class="s-t">原四级部门</td><td >
			 	<select style="width:140px;" id="foldfourunitpage" name="foldfourunitpage" rel="zx_dictionary" data="oldfourunit">
				<option value=""></option>
  		 		</select>
				</td>
				
				<td class="s-t">四级部门</td><td >
			 	<select  style="width:270px;" id="fourunit" name="fourunit" rel="zx_dictionary" data="fourunit">
				<option value=""></option>
  		 		</select>
				</td>
				
			</tr>
				<tr>
				<td class="s-t">五级部门</td><td >
			 	<select id="fiveunit" name="fiveunit" rel="zx_dictionary" data="fiveunit">
				<option value=""></option>
  		 		</select>
				</td>
				<td colspan='3'> 
				<a href="javascript:void(0)" class="but-remove" id="editManyBtn" onclick="submitManyFormTab('editManyBtn','人事花名册')">批量修改</a>
				<a href="javascript:void(0)" class="but-remove" id="resetBtn">清空</a>
				</td>
				</tr>
			</table>
				
 </div>
 </form>
  <div class="search-list">
 <span class="list-title">批量修改数据信息</span>
	<table id = "tt" toolbar="#toolbar"></table></div>
  </body>
</html>