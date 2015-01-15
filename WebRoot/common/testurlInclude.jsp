<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String pathInclude = request.getContextPath();
String basePathInclude = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathInclude+"/";
%>
<script type="text/javascript">
/**
 * 查询数据字典,显示下拉框数据
 * @param obj 调用这个方法的select
 * @param id 数据字典数据类型id
 * @return
 */
function loadDictionary(obj,id){
    var url = "<%=basePathInclude%>/dictionary/dictionaryBaseAction!findDictionary";
	var parm = {"id":id};
	var json = {};
	$.ajax({
		   type: "POST",
		   url: url,
		   data:parm,
		   async:false,//发送同步请求
		   dataType: "json",
		   success: function(data){
			json =data;
			if(!data)
  			return;
  			if(!json)
  			return;
  			var  htmlstr = "";
  			for(var i=0;i<json.length;i++){
  				htmlstr += "<option value='"+json[i].id+"'";
  				if(json[i].id==obj.attr("lang"))
  					htmlstr += "selected=selected";
  				htmlstr += " >"+json[i].itemname+"</option>";
  			}
  			obj.append(htmlstr);
		   }
	});
}
	//调用根据数据字典数据生成下拉框方法
	$(function(){
		$("select[rel='zx_dictionary']").each(function(i,e){
		  	loadDictionary($(e),$(e).attr("data"));
		});
	})


	/**
	 * 查询数据字典,弹出数据字典复选框值
	 * @param id 数据字典数据类型id
	 * @return
	 */
	function loadCBoxDictionary(typeid,inputvalue){
	    var url = "<%=basePathInclude%>/dictionary/dictionaryBaseAction!findDictionary";
		var parm = {"id":typeid};
		var json = {};
		var valuetext = new Array();
		valuetext = inputvalue.split(","); 
		$.ajax({
			   type: "POST",
			   url: url,
			   data:parm,
			   async:false,//发送同步请求
			   dataType: "json",
			   success: function(data){
				json =data;
				if(!data)
	  			return;
	  			if(!json)
	  			return;
	  			var  htmlstr = "<table><tr>";
	  			for(var i=0;i<json.length;i++){
		  			if(i%4 == 3){
						htmlstr += "</tr><tr>"
			  			}
	  				htmlstr += "<td><input type='checkbox' name='"+typeid+"'"+" value ='"+json[i].id+"' rel='"+json[i].itemname+"'";
	  				for(var j=0;j<valuetext.length;j++){
						if(valuetext[j]==json[i].id)
							htmlstr += " checked='checked'";
		  				}
	  				htmlstr += ">"+json[i].itemname+"</input></td>";
	  			}
	  			htmlstr += "</tr></table>"
	  			$("#checkboxhtml").html(htmlstr);
			   }
		});     
		
	}
</script>

