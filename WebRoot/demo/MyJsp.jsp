<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

js实现div跟谁鼠标悬浮移动显示 
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 <HTML>
  <style>
   #hint{
    width:198px;
    border:1px solid #000000;
    background:#99ff33;
    position:absolute;
    z-index:9;
    padding:6px;
    line-height:17px;
    text-align:left;
    top:1520px;
   }
  </style>
  <SCRIPT LANGUAGE="JavaScript">
   <!--
    function showme(){
     var oSon = window.document.getElementById("hint");
     if (oSon == null) return;
     with (oSon){
      innerText = guoguo.value;
      style.display = "block";
      style.pixelLeft = window.event.clientX + window.document.body.scrollLeft + 6;
 
      style.pixelTop = window.event.clientY + window.document.body.scrollTop + 9;
 
     }
    }
    function hidme(){
     var oSon = window.document.getElementById("hint");
     if(oSon == null) return;
     oSon.style.display="none";
    }
   //-->
  </SCRIPT>
  <BODY>
<%--   <text id="guoguo" value="ga">--%>
<%--   <a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br><br><br><br><br><a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br><br><br><br><a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br><br><br><a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br><br><a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br><a href="#" onmouseover="showme()" onmouseout="hidme()" onmousemove="showme()">dfdfd</a><br>--%>
<%--   <div id="hint" style="display:none"></div>--%>
		<form action="">
				<input type="text" name="a"/>
				<input type="text" name="a"/>
		</form>
  </BODY>
 </HTML>
