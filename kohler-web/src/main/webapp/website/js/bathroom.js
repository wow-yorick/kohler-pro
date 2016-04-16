jQuery(function($){
	// banner
	(function() {
		$('.pub_banner .banner_page a').hover(function() {
			$(this).parent().find('a').removeClass('selected');
			$(this).addClass('selected');
			var index = $(this).attr('index');
			// console.log(index);
			$('.pub_banner .banner_panel').find('.item').removeClass('actived');
			$('.pub_banner .banner_panel').find('.item:nth-child('+index+')').addClass('actived');
		})
	}());
});