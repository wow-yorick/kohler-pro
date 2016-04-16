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
        var contH = $('.head_wrapper').height() + $('.submenu_panel.bread').height();   
		$('.content_wrapper').css("top",contH);
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
        $('.dtv_wrapper .slidesjs-control,.dtv_wrapper .slidesjs-container,.subwrapper').css({'width':'1280px'});
        $('.subwrapper .bgimg img').css('margin','0 auto');
        $('.large_pre_btn').css('left','200px');
        $('.large_next_btn').css('right','200px');
        $('.icon_play2').css('left','620px');
	}else if( $(window).width() < 1280 ) {
        $('.dtv_wrapper .slidesjs-control,.dtv_wrapper .slidesjs-container,.subwrapper').css({'width':'940px'});
        $('.subwrapper .bgimg img').css('margin-left','-170px');
        $('.large_pre_btn').css('left','30px');
        $('.large_next_btn').css('right','30px');
        $('.icon_play2').css('left','450px');
	}

    // 屏幕底部调整

});

$(window).resize(function() {
	if( $(window).width() >= 1280 ) {
        $('.dtv_wrapper .slidesjs-control,.dtv_wrapper .slidesjs-container,.subwrapper').css({'width':'1280px'});
        $('.subwrapper .bgimg img').css('margin','0 auto');
        $('.large_pre_btn').css('left','200px');
        $('.large_next_btn').css('right','200px');
        $('.icon_play2').css('left','620px');
	}else if( $(window).width() < 1280 ) {
        $('.dtv_wrapper .slidesjs-control,.dtv_wrapper .slidesjs-container,.subwrapper').css({'width':'940px'});
        $('.subwrapper .bgimg img').css('margin-left','-170px');
        $('.large_pre_btn').css('left','30px');
        $('.large_next_btn').css('right','30px');
        $('.icon_play2').css('left','450px');
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