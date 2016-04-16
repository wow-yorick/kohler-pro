$(document).ready(function(){
	// search_bar
	// $('.search_icon').click(function() {
	// 	alert('搜索')
	// })

	// flag:true => ie 6,7,8
    var flag = true; 
    if(navigator.userAgent.indexOf("MSIE")>0) {   
        if(navigator.userAgent.indexOf("MSIE 9.0")>0) {  
        	flag = false; 
        } 
        if(navigator.userAgent.indexOf("MSIE 6.0")>0) {
        	flag = '6'; //ie6
        }
    } else {  
    	flag = false;  
    }  

	// nav & submenu
	$('.pub_nav .menu li').hover(function() {
		$(this).parent().find('li').removeClass('hovered');
		$(this).addClass('hovered');
		var index = parseInt($('.menu .hovered').index())+1;
		$('.pub_submenu.home_page .submenu_panel').not().hide();
		$('.pub_submenu.home_page :nth-child('+index+')').show();
		$('.pub_submenu.home_page').show();
	})
	$(document).on('mouseleave','.pub_submenu.home_page',function() {
		if(!$(this).hasClass('pdp_menu')) {
			$('.pub_nav .menu').find('li').removeClass('hovered');
			$(this).hide();			
		}
	})

		
	// 动态更新滚动内容宽度
	var length = $('#scroll_panel').children().length
	var liWidth = length * 160; 
	var marginWidth = (length - 1) * 10;
	var scrollWidth = liWidth + marginWidth + 'px';
	$('#scroll_panel').css('width',scrollWidth);
	// 初始化滚动条
	// if(flag) {
	// 	$('.series_scroll').css('overflow','auto');
	// 	$('.series_scroll').css('height','120px');
	// 	$('.series_scroll').css('margin-bottom','60px');
	// 	$('.scroll').hide();
	// } else {
		var scroll = new Sly('#series_scroll',{ horizontal: 1, scrollBar: '#scrollbar', scrollBy: 50, dynamicHandle: 1, mouseDragging: 1, touchDragging: 1, dragHandle: 1}).init();
	if(flag == 6) {
		// $('.index_series .series_info').css('top','440px');
		// $('.index_series .scrollbars').css('margin','0px');
		// $('.handles').css('height','2')
		// alert($('.handles').height());
	}
	// }
	$('#scroll_panel').css('position','relative');
});