$.inputerIndex = 0;
$.fn.inputer = function (idkey, data, defaultvalue, funCallBack, unitValueId) {//data [{text:'aa',value:123},{test:'aa',value:123}]
    var obj = this;
    var objElementValue = unitValueId;
    var arrayElement = idkey;
    var tempData = '';
    var elementDiv = "inputer_div_" + idkey;
    var elementUL = "inputer_ul_" + idkey;
    var elementLI = "inputer_li_" + idkey + "_";
    var tempText = obj.val(); //默认为初始值
    var tempValue = $("#" + unitValueId).val();
    var objOffSet = obj.offset();
    var tempKeyUpCount = -1; //计算当前定位ID

    //添加结果隐藏域
 //   $(document.body).append('<input type="hidden" id="' + objElementValue + '" value="' + defaultvalue + '"/>');
    //初始数据
 //   obj.val(tempText); //默认为第一行
    $("#" + objElementValue).val(tempValue);
    if (tempData == '' || obj.val() == '')
        tempData = data;
    $.each(tempData, function (ovar, opt) {
        if (defaultvalue == opt.value) {//匹配项
            obj.val(_inputer_setContent(opt.text)); //默认用户定义
            $("#" + objElementValue).val(opt.value);
            tempText = _inputer_setContent(opt.text);
            tempValue = opt.value;
        }
    });
    obj.focus(function () {//获取焦点
        obj.val("");
        $("#" + objElementValue).val(defaultvalue);

        if (tempData == '' || obj.val() == '')
            tempData = data;
        _inputer(tempData);
    });
    obj.blur(function () { //失去焦点
        obj.val(tempText);
        $("#" + objElementValue).val(tempValue);
    });

    obj.keyup(function (event) { //模糊搜索
        if (event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 13) {
            KeyUpOrDownOrPress(event.keyCode);
            return;
        }

        var _tempData = '';
        if (obj.val() != null && obj.val() != "") {
            $.each(data, function (ovar, opt) {
                var _tempIndex = opt.text.toLowerCase().indexOf(obj.val().toLowerCase());
                if (_tempIndex > -1) {
                    if (_tempData != '') _tempData += ",";
                    _tempData += "{text:'" + opt.text.replace(opt.text.substr(_tempIndex, obj.val().length), "<font color=\"red\">" + opt.text.substr(_tempIndex, obj.val().length) + "</font>") + "',value:'" + opt.value + "'}";
                }
            });
            if (_tempData != '') {
                _tempData = "[" + _tempData + "]";
                _tempData = eval(_tempData);
            }
            else
                _tempData = eval("[{text:'没有搜索到匹配的项!',value:'" + defaultvalue + "'}]");
            tempData = _tempData;
        }
        else {
            tempData = data;
        }
        _inputer(tempData);
    });

    function _inputer(tempData) {
        if (_inputer_exist(elementDiv)) { $("#" + elementDiv).remove(); }

        $(document.body).append('<div id="' + elementDiv + '" class="inputer_div"></div>');
        arrayElement += "|" + elementDiv;
        $("#" + elementDiv).css({
            width: obj.width() + ($.browser.msie ? 2 : 2) + (idkey == "ffourunitname" ? 110 : 14) + "px",
            height: tempData.length > 10 ? "208px" : "auto",
            "overflow-y": tempData.length > 10 ? "auto" : "hidden",
            left: objOffSet.left,
            top: objOffSet.top + obj.height() + ($.browser.msie ? 6 : 6)
        });

        $("#" + elementDiv).append('<ul id="' + elementUL + '" class="inputer_ul"></ul>');
        arrayElement += "|" + elementUL;

        $.each(tempData, function (ovar, opt) {
            var item = elementLI + ovar;
            $("#" + elementUL).append('<li id=' + item + ' class="inputer_li">' + opt.text + '</li>');
            arrayElement += "|" + item;

            $("#" + item).mouseout(function () { this.className = "inputer_li"; });
            $("#" + item).mouseover(function () { this.className = "inputer_li_hover"; });

			if(_inputer_setContent(opt.text) != "没有搜索到匹配的项!") {
	            $("#" + item).click(function () {
	                $("#" + elementDiv).css("display", "none");
	                obj.val(_inputer_setContent(opt.text));
	
	                //临时变量赋值
	                tempText = _inputer_setContent(opt.text);
	                tempValue = opt.value;
	                $("#" + objElementValue).val(opt.value);
	                //拼接团队
	                if(idkey == "foldfourunitname") {
						var fiveValue = $("#ffiveunitname").val();
						$("#fteam").val(tempText+"_"+fiveValue);
			        }
			        if(idkey == "ffiveunitname") {
			        	var yFourValue = $("#foldfourunitname").val();
						$("#fteam").val(yFourValue+"_"+tempText);
			        }
	
	                //触发外部事件
	                if (funCallBack != null && typeof funCallBack == "function")
	                    funCallBack();
	            });
            }
        });

        $(document.body).click(function () {
            var e = arguments[0] || window.event;
            var eventSource = e.srcElement || e.target;
            var _arrayElement = arrayElement.split('|');
            var flag = false;
            for (var i = 0; i < _arrayElement.length; i++) {
                if (eventSource.id == _arrayElement[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) $("#" + elementDiv).css("display", "none");
        });
    }

    function KeyUpOrDownOrPress(keyCode) {
        //处理事件
        var arrayLIlist = $("#" + elementUL + ">li");
        var arrayLIdata = null;

        switch (keyCode) {
        	//方向键up
            case 38:
                {
                    if (tempKeyUpCount <= 0)
                        tempKeyUpCount = 0;
                    else
                        tempKeyUpCount -= 1;

                    $.each(tempData, function (ovar, opt) {
                        arrayLIlist[ovar].className = "inputer_li";
                    });
                    arrayLIlist[tempKeyUpCount].className = "inputer_li_hover";

                    if (tempKeyUpCount < 8)
                        $("#" + elementDiv).scrollTop(0);
                    break;
                }
			//方向键down
            case 40:
                {
                    if (tempKeyUpCount >= tempData.length - 1)
                        tempKeyUpCount = tempData.length - 1;
                    else
                        tempKeyUpCount += 1;

                    $.each(tempData, function (ovar, opt) {
                        arrayLIlist[ovar].className = "inputer_li";
                    });
                    arrayLIlist[tempKeyUpCount].className = "inputer_li_hover";

                    if (tempKeyUpCount % 8 == 0)
                        $("#" + elementDiv).scrollTop(26 * tempKeyUpCount);
                    break;
                }
            //回车键
            case 13:
                {
                    arrayLIdata = tempData[tempKeyUpCount];

					if(_inputer_setContent(arrayLIdata.text) != "没有搜索到匹配的项!") {
                    $("#" + elementDiv).css("display", "none");
                    obj.val(_inputer_setContent(arrayLIdata.text));

                    //临时变量赋值
                    tempText = _inputer_setContent(arrayLIdata.text);
                    tempValue = arrayLIdata.value;

                    //隐藏域赋值
    //                if (_inputer_exist(objElementValue) == false)
   //                     $(document.body).append('<input type="hidden" id="' + objElementValue + '" class="inputer_div"/>');
                    $("#" + objElementValue).val(arrayLIdata.value);
                    //拼接团队
	                if(idkey == "foldfourunitname") {
						var fiveValue = $("#ffiveunitname").val();
						$("#fteam").val(tempText+"_"+fiveValue);
			        }
			        if(idkey == "ffiveunitname") {
			        	var yFourValue = $("#foldfourunitname").val();
						$("#fteam").val(yFourValue+"_"+tempText);
			        }

                    //触发外部事件
                    if (funCallBack != null && typeof funCallBack == "function")
                        funCallBack();
                    //自动失去焦点
                    tempKeyUpCount = 0;
                    obj.blur();
                    break;
                    }
                }
        }
    }

    function _inputer_exist(id) {
        var element = $("#" + id);
        if (element.length > 0) { return true }
        else { return false }
    }

    function _inputer_setContent(str) {
        str = str.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
        str.value = str.replace(/[ | ]*\n/g, '\n'); //去除行尾空白
        str = str.replace(/\n[\s| | ]*\r/g, '\n'); //去除多余空行
        return str;
    }
};

/**
 * 查询数据字典,显示部门，并提供搜索功能
 * @param obj 调用这个方法的select
 * @param id 数据字典数据类型id
 * @return
 */
function loadQueryUnit(obj,id){
		//window是当前的浏览器窗口对象,window.location.是当前浏览器的里面的URL
	//  var localObj = window.location;
	//  var contextPath = localObj.pathname.split("/")[1];//域名后的第一个值
	//  var basePath = localObj.protocol+"//"+localObj.host;
	//  var server_context=basePath;
	//  if(contextPath!='pages'){
	//  server_context = server_context + "/"+contextPath;}
	//  var url = server_context+ "/dictionary/dictionaryBaseAction!findDictionary";


	
	var url =  "dictionary/dictionaryBaseAction!findDictionary";
//	var url = "http://easext.creditease.corp/dictionary/dictionaryBaseAction!findDictionary";
	var parm = {"id":id};
	var json = {};
	var  htmlstr = "";
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
  			for(var i=0;i<json.length;i++){
  				/*htmlstr += "<option value='"+json[i].id+"'";
  				if(json[i].id==obj.attr("lang"))
  					htmlstr += "selected=selected";
  				htmlstr += " >"+json[i].itemname+"</option>";*/
  				
  				htmlstr += "{text:'" + json[i].itemname + "',value:'" +json[i].id + "'},";
  			}
  	//		obj.append(htmlstr);
		   }
	});
	htmlstr = htmlstr.substring(0,htmlstr.length-1);
  	htmlstr = "[" + htmlstr + "]";
	return htmlstr;
}