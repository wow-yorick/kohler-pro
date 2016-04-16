$(document).ready(function() {
	// // search_bar
	// $('.search_icon').click(function() {
	// 	alert('搜索')
	// })

	// product left filter
	$('.submenu_panel p,.pub_subnav_warp li').click(function() {
		var h1 = parseInt($('.head_wrapper').height()); 
		var h2 = parseInt($('#'+$(this).attr("index")).offset().top);
		$("body,html").animate({
			scrollTop:h2-h1
		},500);
	})

	$('.category li').click(function() {
		var classname = $(this).attr('class');
		$('.item-panel li').hide();
		if(classname == 'all') {
			$('.item-panel li').show();
		}else{
			$('.item-panel li.'+$(this).attr('class')).show()
		}
	})

	$('.filter_list_first_title').click(function(e) {
		if($(this).parent().hasClass('actived')) {
			$(this).parent().find('.icon_first').css('backgroundImage','url(images/icon_plus.png)');
			$(this).parent().removeClass('actived');
			$(this).parent().find('.filter_list_second').hide();
		}else{
			$(this).parent().find('.icon_first').css('backgroundImage','url(images/icon_minus.png)');
			$(this).parent().addClass('actived');
			$(this).parent().find('.filter_list_second').show();

		}
	})
	$('.filter_list_second_title').click(function() {
		if($(this).nextAll().hasClass('filter_list_third')) {
			if($(this).parent().hasClass('actived')) {
				$(this).parent().find('.icon_second').css('backgroundImage','url(images/icon_plus.png)');
				$(this).parent().removeClass('actived');
				$(this).parent().find('.filter_list_third').hide();
			}else{
				$(this).parent().find('.icon_second').css('backgroundImage','url(images/icon_minus.png)');
				$(this).parent().addClass('actived');
				$(this).parent().find('.filter_list_third').show();
			}
		}else{
			if($(this).parent().hasClass('selected')) {
				$(this).parent().removeClass('selected');
				$(this).next('.icon_close').removeClass('selected');
			}else{
				$(this).parent().addClass('selected');
				$(this).next('.icon_close').addClass('selected');
			}
		}
	})

	$('.filter_list_third_title').click(function() {
		if($(this).parent().hasClass('selected')) {
			$(this).parent().removeClass('selected');
			$(this).next('.icon_close').removeClass('selected');
		}else{
			$(this).parent().addClass('selected');
			$(this).next('.icon_close').addClass('selected');
		}
	})

	$('.filter_list_second_colorpanel li').click(function() {
		// $(this).parent().find('.icon_choose').hide();
		if($(this).hasClass('selected')) {
			$(this).removeClass('selected').find('.icon_choose').hide();
		}else{
			$(this).addClass('selected').find('.icon_choose').show();
		}
	})

	$('.icon_close').click(function() {
		$(this).parent().hide();
	})
	// btn_clear
	$('.filter_title .btn_clear').click(function() {
		$('.filter .filter_list').hide();
	})

	$('.filter p').click(function() {
		// $(this).remove();
		// $('.item-panel').find('li.test').hide();
	})



	// collection down_arrow
	$('.arrow_bottom').click(function() {
		var ulHeight = ($('.pub_subnav_warp ul').length) * ($('.pub_subnav_warp ul').height());
		$('.pub_subnav').css('height',ulHeight+12);
		$('.pub_subnav_warp').css('height',ulHeight+12);
		$(this).hide();
	})



    $('.item-panel li').hover(function() {
		$('.item-panel li').css('z-index','0');
		$('.detail_popup').hide();
		$(this).css('z-index','1').find('.detail_popup').show();
	})
	$(document).on('mouseleave','.item-panel li',function() {
		$('.item-panel li').css('z-index','0');
		$('.detail_popup').hide();
	})

	var flag = false; 
    if(navigator.userAgent.indexOf("MSIE")>0) {   
        if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
        {   
        flag = true;  
        }   
    } 

	$('.pop_close').click(function() {
		$('.product_popup').hide();
	})
	$('.preview').click(function() {
		var index = parseInt($(this).parents('li').index())+1;
		$('.product_popup').show();
		$('.popup_panel').hide();
		$('.popup_panel#pop'+index+'').show();
		// $('.popup_compare_panel').show();
	})

	// 产品快速预览弹出层 悬浮高度
	$('.popup_panel').css('margin-top',parseInt($(window).height())/10);
    if($(window).height() <= 600) {
    	$('.popup_panel').css('margin-top',0);
    }

    // 产品比较详情栏弹出层 悬浮高度
    if($(window).height() <= 925) {
    	$('.popup_compare_panel').css('margin-top',0);
    }

    // 显示产品对比详情
	$('.compare_btn').click(function() {
		$('.product_popup').show();
		$('.popup_panel').hide();
		$('.popup_compare_panel').show();
		$('.compare_info_list').css('border-right','1px solid #e5e5e5').last().css('border','none');
	})


	// 显示对比栏
	$('.compare_btn_show').click(function() {
		$('.compare_panel_small').hide();
		$('.compare_panel_large').show();
	})
	// 取消对比
	$('.compare_btn_hide').click(function() {
		$('.compare_panel_small').hide();
	})

	// 隐藏对比栏
	$('.p_hide').click(function() {
		$('.compare_panel_small').show();
		$('.compare_panel_large').hide();
	})

	// 删除对比商品
	$(document).on('click','.compare_close',function() {
		if($(this).hasClass('p_list')) {
			var $comdiv = $(this).parents('.compare_list');
			var index = $comdiv.index()+1;
			$comdiv.remove();
			$('.compare_details_list:eq('+index+')').remove();
			// alert(index);
		}else{
			var index = $(this).parent('.compare_details_list').index();
			$(this).parent('.compare_details_list').remove();
		}
		$('.compare_info_list:eq('+index+')').remove();
		$('.compare_list:eq('+(index-1)+')').remove();
		$('.compare_info_list').css('border-right','1px solid #e5e5e5').last().css('border','none');
	})

	// 清空
	$('.compare_empty').click(function() {
		var content = '';
		
		content += '<div class="compare_list add">';
        content += '<div class="compare_img add">';
        content += '<img src="images/compare_add.jpg" alt="">';
        content += '</div>';
        content += '<div class="compare_txt">';
        content += '<p class="txt">添加其他产品</p>';
        content += '</div>';
        content += '</div>';
        $('.compare_bar_list').html(content);

        content = '<div class="compare_details_list clearfix"></div>';
        content += '<div class="clearfix"></div>';
        content += '<div class="compare_details_info clearfix">';
        content += '<div class="compare_info_list">';
        content += '<div class="compare_grid first_grid"><p><b>分体/连体</b></p></div>';
        content += '<div class="compare_grid bkwhite"><p><b>最小坑距</b></p></div>';
        content += '<div class="compare_grid"><p><b>冲水系统</b></p></div>';
        content += '<div class="compare_grid bkwhite"><p><b>所配盖板</b></p></div>';
        content += '<div class="compare_grid"><p><b>总体尺寸</b></p></div>';
        content += '<div class="compare_grid bkwhite"><p><b>缸体高度</b></p></div>';
        content += '<div class="compare_grid"><p><b>缸体类型</b></p></div>';
        content += '<div class="compare_grid last_grid bkwhite"><p><b>备注</b></p></div>';
        content += '</div>';
        content += '</div>';
        $('.compare_content_bar').html(content);
        $('.compare_panel_large').hide();
        $('.compare_panel_small').hide();
	})

	$('.compare_img.add').click(function() {

	})

	var i = 1;
	// 产品对比按钮
	$('.product_btn.compare').click(function() {
		// alert($('.compare_list').length);
		var content = '';
		
		// 添加新产品对比
		if($('.compare_list').last().hasClass("add")) {
			content = '<div class="compare_img">';
        	content += '<img src="images/baths5.jpg" alt="">';
        	content += '<div class="compare_close p_list"></div>';
        	content += '</div>';
        	content += '<div class="compare_txt">';
        	content += '<p class="txt">SOK® 素克'+i+'</p>';
        	content += '<p class="txt">溢流型水疗按摩浴缸<br />（灯光）</p>';
        	content += '<p class="type">K-1189-C1</p>';
        	content += '</div>';
			$('.compare_list.add').html(content).removeClass('add');

			content = '<div class="compare_details_list clearfix">';
            content += '<div class="compare_close"></div>';
            content += '<div class="item_img"><img src="images/toilets11.jpg" alt=""></div>';
            content += '<p class="item_title">SOK® 素克'+i+'<br />溢流型水疗按摩浴缸（灯光）</p>';
            content += '<p class="item_type">K-1189-C1</p>';
            content += '<div class="compare_btns">';
            content += '<div class="compare_btn_buy">';
            content += '<div class="icon_car"></div>';
            content += '<p>在线购买</p>';
            content += '</div>';
            content += '<div class="compare_btn_favority"></div>';
            content += '<div class="compare_btn_share"></div>';
            content += '</div>';
            content += '</div>';
            $('.compare_details_list').last().after(content);

            content = '<div class="compare_info_list">';
            content += '<div class="compare_grid first_grid"><p>连体式'+i+'</p></div>';
            content += '<div class="compare_grid bkwhite"><p>地排水305mm</p></div>';
            content += '<div class="compare_grid"><p>Class 5 五级旋风</p></div>';
            content += '<div class="compare_grid bkwhite"><p></p></div>';
            content += '<div class="compare_grid"><p></p></div>';
            content += '<div class="compare_grid bkwhite"><p></p></div>';
            content += '<div class="compare_grid"><p></p></div>';
            content += '<div class="compare_grid last_grid bkwhite"><p></p></div>';
            content += '</div>';
            $('.compare_details_info').append(content);
            i++;
		}
		// 判断对比产品是否满4个
		if($('.compare_list').length < 4) {
			content = '<div class="compare_list add">';
            content += '<div class="compare_img add">';
            content += '<img src="images/compare_add.jpg" alt="">';
            content += '</div>';
            content += '<div class="compare_txt">';
            content += '<p class="txt">添加其他产品</p>';
            content += '</div>';
            content += '</div>';
			$('.compare_bar_list').append(content)
		}

		$('.product_popup').hide();
		$('.compare_panel_large').show();
		$('.compare_info_list').last().not('.first_list').css('border','none');
	})

	$('.popup_compare_details_panel .icon_close2').click(function() {
		$('.product_popup,.popup_compare_panel').hide();
	})

	// 产品比较详情栏 高度适应屏幕
	var windowheight = parseFloat($(window).height());
	var popdetailheight = 925;
	if(windowheight < 925) {
		$('.popup_compare_panel').height(windowheight);
	}

	// 产品比较栏 始终置于屏幕底部
	$(window).scroll(function() {
        var docheight=parseFloat($(document).height());
        var windowheight = parseFloat($(window).height());
        var scrollheight = parseFloat($(window).scrollTop());
        if(windowheight+scrollheight > (docheight - 31)) {
        	var H = (windowheight + scrollheight) - (docheight - 31)
        	$('.compare_panel_small').css('bottom',H);
        	// $('.compare_panel_large').css('bottom',H);
        }
        if(windowheight + scrollheight <= (docheight - 31)){
			$('.compare_panel_small').css('bottom','0px');
			// $('.compare_panel_large').css('bottom','0px');
        }
	});
	$("#solr_search").click(function(){
		var condition = "skuCode:"+$("#test_color").val();
		var url = china_search+"/search/list.action?skuName=&conditions="+condition+"&sort=id&page=1&perNum=10&isAsc=true&lang=1&jsoncallback=?";
		
		$.getJSON(url,{"condition":condition},function(json){
			var record = json.record;
			
			$("ul.item-panel").empty();
			for(var i=0;i<record.length;i++)
			{
				var li = $("<li/>").attr("class","cate1").appendTo($("ul.item-panel"));
				var div = $("<div/>").appendTo(li);
				$("<img/>").attr("src","images/baths1.jpg").appendTo($("<div/>").attr("class","item_img").appendTo(div));
				var span = $("<span/>").appendTo(div);
				$("<h3/>").text(record[i]["skuCode"]).appendTo(span);
				$("<p/>").attr("class","type").text(record[i]["skuName"]).appendTo(span);
				div = $("<div/>").attr("class","detail_popup").appendTo(li);
				$("<img/>").attr("src","images/baths1.jpg").appendTo($("<div/>").attr("class","item_img").appendTo(div));
				var span = $("<span/>").appendTo(div);
				$("<h3/>").text(record[i]["skuCode"]).appendTo(span);
				$("<p/>").attr("class","type").text(record[i]["skuName"]).appendTo(span);
			}
			
		});
	});

})