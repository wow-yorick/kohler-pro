/**
 * 一级弹出层弹出方法(type用于指定弹出页的类型，2为iframe，目前不做扩展)
 * @param pageTitle 弹出层标题
 * @param requestUrl 请求页面的url
 */

function alertFirstIframe(pageTitle , requestUrl , width , height){
	$.layer({
        type: 2,
        title: pageTitle,
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : [width , height],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: requestUrl}
    });
}

function onlyProductAlertIframe(pageTitle , requestUrl , width , height){
	$.layer({
        type: 2,
        title: pageTitle,
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : [width , height],
		shadeClose: false,
        offset : ['10px', ''],
        iframe: {src: requestUrl}
    });
}

/**
 * 二级弹出层弹出方法(type用于指定弹出页的类型，2为iframe，目前不做扩展)
 * @param pageTitle 弹出层标题
 * @param requestUrl 请求页面的url
 */

function alertSecondIframe(pageTitle , requestUrl){
	$.layer({
        type: 2,
        title: pageTitle,
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600px' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: requestUrl}
    });
}

function alertConfirmDialog(message){
	$.layer({
	    shade: [0],
		title: 'Confirm',
		shadeClose: true, //开启点击遮罩关闭层
	    area: ['310px', '130px'],
	    dialog: {
	        msg: message,
	        btns: 2,                    
	        type: -1,
	        btn: ['OK','Cancel'],
	        yes: function(){
	            layer.msg(message, 1, 1);//按钮一的回调函数			
	        },
	        no: function(){
	        	return false;
	        }
	    }
	});
}
	
/**
 * 给disabled 的 dropdown 加上灰色背景。
 */
function setGray4DisabledSelect(objSelect) {
	if (objSelect != null || objSelect != 'undefined') {
		objSelect.css("background","#ebebe4");
	}
}