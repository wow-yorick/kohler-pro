$(document).ready(function() {
	var timerid;

	// banner
	var timecontrolstart = function() {
		timerid = setInterval(function(){
			if(timer == 6) {
				timer = 1;
			}else if(timer == 1){
				timer = 2;
			}
			$('.banner_page').find('span').removeClass('selected');
			$('.banner_page span:nth-child('+timer+')').addClass('selected');
			var index = $('.banner_page span:nth-child('+timer+')').attr('index');
			$('.subwrapper .bgimg').fadeOut(500).removeClass('actived');
			$('.subwrapper .bgimg:nth-child('+index+')').fadeIn(500).addClass('actived');
			$('.learnmore,.logo').attr('href',$('.subwrapper .bgimg:nth-child('+index+')').attr('href'));
			timer ++;
		},5000);
	}

	var timecontrolstop = function() {
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
			$(this).parent().find('span').removeClass('selected');
			$(this).addClass('selected');
			var index = $(this).attr('index');
			$('.subwrapper .bgimg:nth-child('+index+')').fadeIn(500).addClass('actived');
			$('.learnmore,.logo').attr('href',$('.subwrapper .bgimg:nth-child('+index+')').attr('href'));
			timer = parseInt(index) + 1;
			timecontrolstart();
		}
	})
})