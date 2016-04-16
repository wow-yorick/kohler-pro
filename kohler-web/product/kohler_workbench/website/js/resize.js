$(function() {
	// 屏幕主体内容根据头部菜单栏高度 重新定位
	var flag = false; 
    if(navigator.userAgent.indexOf("MSIE")>0) {   
        if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
        {   
        flag = true;  
        }   
           
    } 
    if(!flag) {
		$('.content_wrapper').css("top",parseInt($('.head_wrapper').height()));
		// $('.content_wrapper.pdp').css("top",parseInt($('.head_wrapper').height()));
		// 弹出层背景高度
    	$('.product_popup').height($(window).height());
    }else{
    	// alert($(window).height())
    	// 弹出层背景高度
    	$('.product_popup').height($(window).height()).css({'position':'absolute'},{'top':'0'});
    	$(window).scroll(function() {
    		$('.product_popup').css('top',$(window).scrollTop());
    	})
    }
    $('.popup_panel').css('margin-top',parseInt($(window).height())/10);
    $('.popup_register_panel').css('margin-top',parseInt($(window).height())/13);
    if($(window).height() <= 600) {
        $('.popup_panel.popup_register_panel').css('margin-top',0);
    }

	// 返回顶部
	$('.pub_totop').click(function() {
		$("body,html").animate({
			scrollTop:0
		},500);
	})

	// 顶部菜单hover事件
	$('.pub_nav .menu li').hover(function() {
		// $(this).parent().find('li').removeClass('hovered');
		$(this).addClass('hovered').siblings().removeClass('hovered');
		// var flag = false; 
   //  	if(navigator.userAgent.indexOf("MSIE")>0) {   
   //  	    if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
   //  	    {   
   //  	    flag = true;  
   //  	    }   
   //  	} 
   //  	if(!flag) {
			// $('.content_wrapper').css("top",parseInt($('.head_wrapper').height()));
   //  	}
	})

})

$(window).load(function() {
	if( $(window).width() >= 1280 ) {
		$('.subwrapper').css('width','1280px');
	}else if( $(window).width() < 1280 ) {
		$('.subwrapper').css('width','960px');
	}
});

$(window).resize(function() {
	if( $(window).width() >= 1280 ) {
		$('.subwrapper').css('width','1280px');
	}else if( $(window).width() < 1280 ) {
		$('.subwrapper').css('width','960px');
	}
	var flag = false; 
    if(navigator.userAgent.indexOf("MSIE")>0) {   
        if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
        {   
        flag = true;  
        }   
    } 
    if(!flag) {
    	$('.product_popup').height($(window).height());
    }else{
    	$('.product_popup').height($(window).height()).css({'position':'absolute'},{'top':'0'});
    }
});