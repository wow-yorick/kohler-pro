// 滚动窗口
$(function(){
	markPosition();

	// 显示详情
	$(document).on('mouseenter','.details_icon',function() {
		$('.details_info,.tri').hide();
		$(this).nextAll().show();
	})
	// 隐藏详情
	$(document).on('mouseleave','.details_info',function() {
		$(this).hide().prev('.tri').hide();
	})

	// 滚动缩略图
	$(document).on('click','.series_scroll li',function() {
		var content = '';
		var $details_box = $('.series_panel').children('.details_info_box');
		$details_box.hide();
		$('.series_scroll').find('span').removeClass('selected');
		$(this).find('span').addClass('selected');
		var index = parseInt($(this).index()) + 1;
		$('.series_panel .series_panel_img').removeClass('actived');
		$('.series_panel').find('.series_panel_img').eq(index).addClass('actived');
		$('.series_info').html('<h4>Persuade® 珀斯唯一体化盆'+index+'</h4><p class="actived">蕴聚传统风格与魅力，三层雕刻外形，精雕细琢。珀特勒浴缸为装修添彩，使现代家居更趋完美。</p>');
		var icon_num = $(this).attr('icon-nums');
		if(icon_num > 0) {
			var topArr = $(this).attr('data-top').split(',');
			var leftArr = $(this).attr('data-left').split(',');
		}
		
		var $details_box = $('.series_panel').children('.details_info_box');
		var length =  $details_box.length;

     	for(var i = 0; i < icon_num; i++) {
      		$details_box.eq(i).attr({'data-top':topArr[i],'data-left':leftArr[i]}).show();

      		if(i >= length) {
      			content += '<div class="details_info_box" data-top="'+topArr[i]+'" data-left="'+leftArr[i]+'">';
        		content += '<a href="" class="details_icon"></a>';
        		content += '<div class="tri"></div>';
        		content += '<div class="details_info">';
        		content += '<div class="details_img"><img src="images/detail1.jpg" alt=""></div>';
        		content += '<h5>KELSTON<span class="mark">®</span> 凯尔登双花洒淋浴柱</h5>';
        		content += '<h5 class="series_name">玫瑰金</h5>';
        		content += '<p class="number">K-99032T-4-RGD</p>';
        		content += '<a href="" class="details_btn">点击查看详情</a href="">';
        		content += '</div>';
        		content += '</div>';
        		$('.series_panel').append(content);
      		}
     	}
      	markPosition();
	})

	// 滚动窗口详情标记位置
	function markPosition() {
		var details_box_width = 0;
		$('.details_info_box').each(function() {
			var top = $(this).attr('data-top');
			var left = $(this).attr('data-left');
			details_box_width = $('.details_info_box').parent().width();
			if((details_box_width - left) < 350) {
				$(this).find('.tri').addClass('tri_right');
				$(this).find('.details_info').addClass('info_right');
			}else{
				$(this).find('.tri').removeClass('tri_right');
				$(this).find('.details_info').removeClass('info_right');
			}
			$(this).css({'top':top+'px','left':left+'px'});
			if ( top <= 155 ) {
				$(this).find('.details_info').css('top','0');
			} else if ( top > 155 && top <= 250 ) {
				$(this).find('.details_info').css('top','-140px');
			} else if ( top > 250 && top < 340 ) {
				$(this).find('.details_info').css('top','-240px');
			} else {
				$(this).find('.details_info').css('top','-340px');
			}
		})
	}

})