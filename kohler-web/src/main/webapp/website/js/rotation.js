var timerid;
var timecontrolstart;
var timecontrolstop;
$(document).ready(function() {
	var bannerlength=$(".subwrapper .bgimg").length;
	var bannerlist=bannerlength+1;

	// 判断顶部图片数量是否需要轮播
	if(bannerlength > 1) {
	// banner
	timecontrolstart = function() {
		timerid = setInterval(function(){
			if(timer == bannerlist) {
				timer = 1;
			}else if(timer == 1){
				timer = 2;
			}
			$('.banner_page').find('span').removeClass('selected');
			$('.banner_page span:nth-child('+timer+')').addClass('selected');
			var index = $('.banner_page span:nth-child('+timer+')').attr('index');
			$('.subwrapper .bgimg').fadeOut(500).removeClass('actived');
			$('.logo_bar .logo').removeClass('logo_actived');
			$('.subwrapper .bgimg:nth-child('+index+')').fadeIn(500).addClass('actived');
			$('.logo_bar .logo:nth-child('+index+')').addClass('logo_actived');
			// checkImg();
			$('.learnmore,.logo').attr('href',$('.subwrapper .bgimg:nth-child('+index+')').attr('href'));
			timer ++;
		},10000);
	}

	 timecontrolstop = function() {
        window.clearTimeout(timerid);
    };

    timecontrolstart();
	var timer = 1; 
	$('.pub_banner .banner_page span').mouseenter(function() {
		if($(this).hasClass('selected')){
			return;
		}else{
			timecontrolstop();
			$('.subwrapper .bgimg').fadeOut(500).removeClass('actived');
			$('.logo_bar .logo').removeClass('logo_actived');
			$(this).parent().find('span').removeClass('selected');
			$(this).addClass('selected');
			var index = $(this).attr('index');
			$('.subwrapper .bgimg:nth-child('+index+')').fadeIn(500).addClass('actived');
			$('.logo_bar .logo:nth-child('+index+')').addClass('logo_actived');
			// checkImg();
			$('.learnmore,.logo').attr('href',$('.subwrapper .bgimg:nth-child('+index+')').attr('href'));
			timer = parseInt(index) + 1;
			timecontrolstart();
		}
	})
	$(".pub_banner .banner_page span").mouseover(function(){
  		timecontrolstop();//鼠标放在上面的时候停止切换
	}).mouseout(function(){
		timecontrolstart();//鼠标离开后继续切换
		});
	}else{
		$('.banner_page').hide();
	}
	// function checkImg() {
	// 	console.log($('.bgimg.actived').find('img').length);
	// 	if($('.bgimg.actived').find('img').length>0) {
	// 		$('.subwrapper.index .logo').show();
	// 	}else{
	// 		$('.subwrapper.index .logo').hide();
	// 	}
	// }
})