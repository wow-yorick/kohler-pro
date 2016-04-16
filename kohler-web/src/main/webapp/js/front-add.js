$(function(){
	// 页面底部的线开始
	var iScreen=$(window).height();
	var dHeight=$(document).height();
	var bodyHeight=iScreen>dHeight?iScreen:dHeight;
	if(iScreen==dHeight){
		bodyHeight=iScreen-11;
	}
	$(".wrap").height(bodyHeight);
	// 页面底部的线结束
});