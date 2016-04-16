// 滚动窗口
$(function(){
	markPosition();

	// 显示详情
	$(document).on('mouseenter','.details_icon',function() {
		$('.details_info,.tri').hide();
		$('.details_icon').css('background-position','0 0');
		$(this).css('background-position','-37px 0').nextAll().show();
	})
	// 隐藏详情
	$(document).on('mouseleave','.details_info',function() {
		$('.details_icon').css('background-position','0 0');
		$(this).hide().prev('.tri').hide();
	})

	// 滚动缩略图
	$(document).on('click','.series_scroll li',function() {
		var content = '';
		var $details_box = $('.series_panel').children('.details_info_box');
		$details_box.hide();
		$('.series_scroll').find('span').removeClass('selected');
		$(this).find('span').addClass('selected');
		var index = parseInt($(this).index());
		$('.series_panel .series_panel_img').removeClass('actived');
		$('.series_panel').find('.series_panel_img').eq(index).addClass('actived');
		$('.series_panel .series_info').removeClass('showtext');
		$('.series_panel').find('.series_info').eq(index).addClass('showtext');
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
        		content += '<a href="javascript:;" class="details_icon"></a>';
        		content += '<div class="tri"></div>';
        		content += '<div class="details_info">';
        		content += '<div class="details_img"><img src="images/detail1.jpg" alt=""></div>';
        		content += '<h5>KELSTON® 凯尔登双花洒淋浴柱</h5>';
        		content += '<h5 class="series_name">玫瑰金</h5>';
        		content += '<p class="number">K-99032T-4-RGD</p>';
        		content += '<a href="javascript:;" class="details_btn">点击查看详情</a href="javascript:;">';
        		content += '<a href="javascript:;" class="details_btn dbtn2">在线购买</a href="javascript:;">';
        		content += '</div>';
        		content += '</div>';
        		$('.series_panel').append(content);
      		}
     	}
      	markPosition();
	})

	// 滚动窗口详情标记位置
	function markPosition() {
		$('.details_info_box').each(function() {
			var details_box_width = 0;
			var right_class = 'info_right';
			var width_fix = 0;
			var detailH = $(this).find('.details_info').height()*0.5;
			if($(this).hasClass('color_group_box')) {
				right_class = 'info_right2';
				width_fix = 105;
			}

			var top = $(this).attr('data-top');
			var left = $(this).attr('data-left');
			details_box_width = $('.details_info_box').parent().width();

			if((details_box_width - left) < 350+width_fix) {
				$(this).find('.tri').addClass('tri_right');
				$(this).find('.details_info').addClass(right_class);
			}else{
				$(this).find('.tri').removeClass('tri_right');
				$(this).find('.details_info').removeClass(right_class);
			}
			$(this).css({'top':top+'px','left':left+'px'});
			
			if(top <= detailH+10) {
				$(this).find('.details_info').css('top','-10px');
			}else if(top >= 540-(detailH+10)){
				$(this).find('.details_info').css('top',-(detailH*2-(540-top)+10));
			}else{
				$(this).find('.details_info').css('top',-detailH);
			}
		})
	}

	// 色彩组合推荐
	$(document).on('click','.color_group',function() {
		// alert('a');
		if($(this).hasClass('info_actived')) {
			$(this).html('色彩组合推荐').removeClass('info_actived').next('.color_group_details').hide();
		}else{
			$(this).html('隐藏色彩组合').addClass('info_actived').next('.color_group_details').show();
		}
	})
})