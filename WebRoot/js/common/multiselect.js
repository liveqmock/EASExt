var multipleModel=(function(mod) {
	mod.produce_newCaseTypeDetail = function(_self) {
		var id = _self.id;
		var title = _self.title;
		var checkedValue = _self.value;
		
		var isCheck = $(_self).attr("checked");
		if (isCheck) {
			var $cur_li;
			var insertId = 0;
			var checkeds = $('input[name="newfinicasetype"]:checked');
			var array = [];
			// alert(checkeds.length);
			checkeds.each(function() {
				if($(this).val()!=27){
					array.push($(this).val());
				}
			});
			for ( var i = 0, j = array.length; i < j; i++) {
				if (parseInt(array[i]) > parseInt(checkedValue)) {
					$cur_li = $("#newfinicasetypeli" + array[i]);
					insertId = array[i];
					break;
				}
			}
			
			var $ul = $("#newfinicasetypeUL");
			var $li = '<li id="newfinicasetypeli'
					+ checkedValue
					+ '" style="float: left;width: 400px;height: 120px;margin: 0 20px 0 0;padding: 0;">'
					+ '<span style="height: 120px;vertical-align: middle;display: inline-block;width: 80px;overflow: hidden;padding: 0;margin: 0;text-align: right;">'
					+ title
					+ '</span>'
					+ '<select multiple="multiple" size="6" style="text-align: left;width: 250px;height: 110px;padding: 0;margin: 0;" id="fnewdetailtypeid'
					+ checkedValue
					+ '" name="fnewdetailtypeid'
					+ checkedValue
					+ '">'
					+ ''
					+ '</select><span style="height: 120px;vertical-align: middle;display: inline-block;width: 50px;text-align: left;overflow: hidden;padding: 0;margin: 0;">'
					+ '<img alt="选择" src="'+mod.imgSrc+'" onclick="multipleModel.openWinCaseType('
					+ checkedValue + ')"></span>' + '</li>';
			if (insertId == "0") {
				$ul.append($li);
			} else {
				if ($cur_li) {
					$cur_li.before($li);
				}
			}
		} else {
			var $cur_li = $("#newfinicasetypeli" + checkedValue);
			$cur_li.remove();
		}
	}

	mod.openWinCaseType = function(id) {
		var obj = $("#newfinicasetypeli" + id);
		var offset = obj.offset();
		var popdiv = $("#popdiv_" + id);
		popdiv.css("position", "absolute");

		var width = document.body.offsetWidth;
		var left = 0;
		if (offset.left + obj.width() + popdiv.width() > width) {
			left = offset.left + obj.width() - popdiv.width();
		} else {
			left = offset.left + obj.width();
		}
		popdiv.css("top", offset.top);
		popdiv.css("left", left);
		popdiv.css("display", "block");
	}

	mod.makesureCaseType = function(id) {
		var detailCaseTypeIni = $('input[name=detailCaseTypeIni' + id + ']:checked');
		// alert(detailCaseTypeIni.length);
		var html = '';
		for ( var i = 0, j = detailCaseTypeIni.length; i < j; i++) {
			var o = detailCaseTypeIni[i];
			html += '<option value="' + o.value + '">' + o.title + '</option>';
		}
		$('#fnewdetailtypeid' + id).html(html);
		$("#popdiv_" + id).css("display", "none");// 关闭层
	}

	mod.cancleCaseType = function(id) {
		$("#popdiv_" + id).css("display", "none");
	}

	mod.dosubmitCompliance1 = function(editForm) {
		var result = $('#editForm').form('validate');
		var cusStatus = $('#fcusstatusid');
		if (cusStatus.find("option:selected").text() == '完成放款') {
			var freimbstrattime = $("#freimbstrattime").val();
			if (freimbstrattime == null || freimbstrattime == '') {
				$("#freimbstrattime")[0].focus();
				result = false;
			}
			var freimbendtime = $("#freimbendtime").val();
			if (freimbendtime == null || freimbendtime == '') {
				$("#freimbendtime")[0].focus();
				result = false;
			}
		}
		if (result) {
			var checkeds = $('input[name="newfinicasetype"]:checked');
			for ( var i = 0, j = checkeds.length; i < j; i++) {
				var o = checkeds[i];
				if(o.value!=27){//不是其他新
					var op = $("#fnewdetailtypeid" + o.value)[0].options;
					for ( var m = 0; m < op.length; m++) {
						op[m].selected = true;
					}
				}
			}
			
			$('#editForm').form('submit',{
				onSubmit: function(){
						$("#editForm").ajaxSubmit({
							dataType:"text",
				 			success:function(json){
									$.messager.alert("提示","操作成功！","info",function(){
											var title="案件信息";
											var refrTab = getTopWin().$('#tabs').tabs('getTab',title);
											if(refrTab){
											var url = $(refrTab.panel('options').content).attr('src');
												getTopWin().$('#tabs').tabs('update',{
													tab:refrTab,options:{content:createFrame(url)}
														})
											}
											
										closeTab();
									})
				 			}
				 		});	
					return false;
				}
			})
			
			//$('#editForm')[0].submit();
		}
	}
	
	return mod;
})(window.multipleModel || {})