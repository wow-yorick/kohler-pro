"use strict";

Function.prototype.getName = function() {
	return this.name || this.toString().match(/function\s*([^(]*)\(/)[1];
}

String.prototype.trim = function() {
	return this.replace(/(^\s+)|(\s+$)/g, "");
}
/**
 * 命名空间定义
 * 
 * @access public
 * @return void
 */
var PAGE = PAGE || {};
PAGE.namespace = function(ns_string) {
	var parts = ns_string.split('.'),
	parent = PAGE,
	i;

	if (parts[0] === 'PAGE') {
		parts = parts.slice(1);
	}

	for (i = 0; i < parts.length; i++) {
		if (typeof parent[parts[i]] === 'undefined') {
			parent[parts[i]] = {};
		}
		parent = parent[parts[i]];
	}

	return parent;
}


/**
 * Detail
 * 
 * @access public
 * @return void
 */
var Detail = function(selecter) {
	var selecter = selecter || ".data_formDetail";
	return {
		init : function(callback) {
			var objForm = $(selecter);
		}
	}
};

Detail.setData = function(objForm, jsonStr) {
	try {
		var jsonDataInfo = $.parseJSON(jsonStr);
		// !---------- BEGIN: MODIFY ---------- //
		for ( var k in jsonDataInfo) {
			var axObj = objForm.find("[name=" + k + "]"), type = axObj.attr('type');
			if (Detail.util.isEmpty(jsonDataInfo[k]) || type == 'file') {
				continue;
			}

			if (axObj.length === 1) {
				var tag = axObj[0].tagName;
				if (tag == "SELECT") {
					objForm.find("select[name=" + k + "] option[value='"+ jsonDataInfo[k] + "']").attr("selected",true);
				} else if (tag == "INPUT" && "checkbox" == type) {
					if ("" != jsonDataInfo[k] && 0 != jsonDataInfo[k]) {
						var checkboxObj = objForm.find('[name=' + k + ']');
						checkboxObj.attr("checked", true);
					}
				} else {
					objForm.find('[name=' + k + ']').val(jsonDataInfo[k]);
				}
			} else if (axObj.length > 1) {
				switch (type) {
				case 'radio':
					objForm.find('input:radio[name="' + k + '"][value='+ jsonDataInfo[k] + ']').attr("checked",true);
					break;
				default:
					// TODO 增加控件类型
					pagelog("未实现的控件！");
				}
			}
		}
	} catch (e) {
		pagelog(e);
	}
	// !---------- END : MODIFY ---------- //
}

Detail.util = {
	filterQuotes : function(str) {
		var str = new String(str);
		return str.replace(/["]+[']+/g, '');
	},
	isEmpty : function(str) {
		if ('' != str && null != str && undefined != str) {
			return false;
		} else {
			return true;
		}
	}

}

// 表单验证
var formValidate = function(selecter) {
	var formObj = $(".data_formDetail");
	if (selecter != "" && typeof (selecter) == 'string') {
		formObj = $(selecter);
	} else {
		throw new TypeError("必须传入选择器字符串！");
	}

	// 绑定键盘enter键提交
	formObj.find('input').keypress(function(e) {
		if (e.which == 13) {
			if (formObj.validate().form()) {
				formObj.submit();
			}
			return false;
		}

	});

	$.extend($.validator.messages, {
		required : "必选字段",
		remote : "请修正该字段",
		email : "请输入正确格式的电子邮件",
		url : "请输入合法的网址",
		date : "请输入合法的日期",
		dateISO : "请输入合法的日期 (ISO).",
		number : "请输入合法的数字",
		digits : "只能输入整数",
		creditcard : "请输入合法的信用卡号",
		equalTo : "请再次输入相同的值",
		accept : "请输入拥有合法后缀名的字符串",
		maxlength : jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength : jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength : jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range : jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max : jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min : jQuery.validator.format("请输入一个最小为 {0} 的值")
	});

	function addAsteriskMark(valiObj) {
		valiObj.parents('td').prev('td').find('label.control-label').prepend(
				'<span class="required">*</span>');
	}

	var rules = {}, msg = {}, attrSet = {};
	$(formObj).find('[data-validate]').each(
			function() {
				var name = $(this).attr('name'),
				validate = $(this).attr('data-validate'),
				message = $(this).attr('data-message'),
				validate = '{' + name + ':' + validate + '}';
				validate = eval('(' + validate + ')');

				$.extend(rules, validate);

				if (message) {
					message = '{' + name + ':' + message + '}';
					message = eval('(' + message + ')');
					$.extend(msg, message);
				}

				addAsteriskMark($(this));

			});

	attrSet = {
		"rules" : rules,
		"massage" : msg
	}

	return {
		init : function(settings) {
			var defaults = {
				errorElement : 'span', // default input error message container
				errorClass : 'help-block', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "",
				invalidHandler : function(event, validator) {
					return false;
				},
				highlight : function(element) { // hightlight error inputs
					$(element).addClass('has-error');
				},
				unhighlight : function(element) { 
					$(element).removeClass('has-error'); 
				},
				success : function(label) {
				},
				errorPlacement : function(error, element) {
					//error.insertAfter(element);
				},
				submitHandler : function(formObj) {
					
				}
			};

			var settings = $.extend(defaults, attrSet, settings);
			formObj.validate(settings);
		}
	};
};

var UeditorFileLoad = (function() {
	return {
		init : function(selector,width,height,settings) {
			var selector = selector || "ueditor";
			settings = settings || {};
			var defaultSet = {
				    toolbars: [
						        ['source', '|', 'undo', 'redo', '|',
					                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain']
						    ],
						   // autoHeightEnabled: true,
						    //autoFloatEnabled: true,
						    initialFrameWidth : width || 416,
					        initialFrameHeight: height || 100
						};
			var settings = $.extend(defaultSet, settings);
			var editor = UE.getEditor(selector, settings);
			editor.addListener( 'ready', function( editor ) {
				//$("#edui1,#edui1_iframeholder").removeAttr('style');
			} );
			return editor;
		}
	}
})();

/**
 * loadjscssfile helper 动态加载js或css
 * 
 * @param filename
 *            $filename
 * @param filetype
 *            $filetype
 * @access public
 * @return void
 */
function loadjscssfile(filename, filetype) {
	if (filetype == "js") {
		var fileref = document.createElement('script');
		fileref.setAttribute("type", "text/javascript");
		fileref.setAttribute("src", filename);
	} else if (filetype == "css") {
		var fileref = document.createElement('link');
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename);
	}
	if (typeof fileref != "undefined") {
		document.getElementsByTagName("head")[0].appendChild(fileref);
	}

}


/**
 * pagelog helper 日志输出
 * 
 * @access public
 * @return void
 */
function pagelog() {
	 var msg = '[page.define] ' + Array.prototype.join.call(arguments, '');
	 if (window.console && window.console.log) {
		 window.console.log(msg);
	 }else if (window.opera && window.opera.postError) {
		 window.opera.postError(msg);
	 }

}

PAGE.namespace('util');
PAGE.util = {
		getQueryString : function(name) { 
		    // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空 
		    if(location.href.indexOf("?")==-1 || location.href.indexOf(name+'=')==-1) 
		    { 
		        return ''; 
		    } 
		  
		    // 获取链接中参数部分 
		    var queryString = location.href.substring(location.href.indexOf("?")+1); 
		  
		    // 分离参数对 ?key=value&key2=value2 
		    var parameters = queryString.split("&"); 
		  
		    var pos, paraName, paraValue; 
		    for(var i=0; i<parameters.length; i++) 
		    { 
		        // 获取等号位置 
		        pos = parameters[i].indexOf('='); 
		        if(pos == -1) { continue; } 
		  
		        // 获取name 和 value 
		        paraName = parameters[i].substring(0, pos); 
		        paraValue = parameters[i].substring(pos + 1); 
		  
		        // 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格 
		        if(paraName == name) 
		        { 
		            return unescape(paraValue.replace(/\+/g, " ")); 
		        } 
		    } 
		    return ''; 
		}		
};


