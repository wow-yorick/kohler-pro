$(document).ready(function() {
	var imgUrlForPdp = $("#imgUrlForPdp").val();
	var pdpcplang = $("#thisPageLang").val();
	
    $(document).on('click','.pop_close',function() {
		$('.product_popup').hide();
	});

    $('.compare_panel_large').css('top','165px');
    $('.compare_panel_large').css('background','rgb(249, 249, 249)');
    // 产品比较详情栏弹出层 悬浮高度
    if($(window).height() <= 925) {
    	$('.popup_compare_panel').css('margin-top',0);
    }

    // 显示产品对比详情
    $(document).on('click','.compare_btn',function() {
		var c = $.cookie('compareskumetadataids');
		$('.compare_details_info.clearfix').html('');
		$.getJSON(chinaweb+"/utility/compare/getCompareAttrList.action","skuMetadataIds="+c+"&lang="+pdpcplang+"&jsoncallback=?",function(json){
			
			var tablewidth = json.ll[0].length*234;
			var listlength = json.ll.length;
			$('.compare_details_info.clearfix').html('');
			var baselist = json.skucomparelist.length;
			var t = '<table class="defaultlisttable"  border="0" style="width:'+tablewidth+'px;"  cellpadding="4" cellspacing="0">';
			$.each(json.ll,function(i,val){
				
				if(i%2==0){
					t += '<tr>';
				}else{
					t += '<tr class="even">';
				}
				$.each(val,function(j,value){
					if(j==0){
						t += "<td class='firstgrid'><p><b>"+value+"</b></p></td>";
					}else{
						t += "<td class='left'><p>"+value+"</p></td>";
					}
					
				});
				if(i == listlength-1){
					t += '</tr>';
				}else{
					t += '</tr>';
				}
				
			});
			$('.compare_details_info.clearfix').append(t);
			if(baselist>0){
				var count = 0;
				$.each($('.compare_details_list'),function(){
					if(count !=0){
						$(this).remove();
					}
					count=count+1;
				});
				
				$.each(json.skucomparelist,function(name,value){
					var content = '<div class="compare_details_list clearfix">';
					content += '<div class="compare_close"><input type="hidden" name="closeskuid" value="'+value.skuMetadataId+'"></div>';
					content += '<div class="item_img"><img src="'+value.fileImageUrl+'" alt=""></div>';
					content += '<p class="item_title">'+value.productName+'</p>';
					content += '<p class="item_type">'+value.skuCode+'</p>';
					content += '<div class="compare_btns">';
					content += '<div class="compare_btn_buy">';
					content += '<div class="icon_car"></div>';
					content += '<p>'+PRODUCT_COMPARE_MESSAGE[1][pdpcplang-1]+'</p>';
					content += '</div>';
					content += '<div class="compare_btn_favority"></div>';
					content += '<div class="compare_btn_share"></div>';
					content += '</div>';
					content += '</div>';
					$('.compare_details_list').last().after(content);
				});
				
			}
			$('.product_popup').show();
			$('.popup_compare_panel').show();
			$('.compare_info_list').css('border-right','1px solid #e5e5e5').last().css('border','none');
		});
		
	});
    
    $(function() {
		var c = $.cookie('compareskumetadataids');
		if(c!=null){
			$.getJSON(chinaweb+"/utility/compare/getCompareList.action?skuMetadataIds="+c+"&lang="+pdpcplang+"&jsoncallback=?",function(json){
				$('.compare_bar_list').html('');
				var content = '';
				$.each(json.skulist,function(name,value){
					content = '<div class="compare_list">';
					content += '<div class="compare_img">';
					content += '<img src="'+value.fileImageUrl+'" alt="">';
					content += '<div class="compare_close p_list"><input type="hidden" name="closeskuid" value="'+value.skuMetadataId+'"></div>';
					content += '</div>';
					content += '<div class="compare_txt">';
					content += '<p class="txt">'+value.productName+'</p>';
					content += '<p class="type">'+value.skuCode+'</p>';
					content += '</div>';
					content += '</div>';
					$('.compare_bar_list').append(content);
				});
				
				if($('.compare_list').length < 4) {
					content = '<div class="compare_list add">';
					content += '<div class="compare_img add">';
					content += '<img src="'+imgUrlForPdp+'images/compare_add.jpg" alt="">';
					content += '</div>';
					content += '<div class="compare_txt">';
					content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][pdpcplang-1]+'</p>';
					content += '</div>';
					content += '</div>';
					$('.compare_bar_list').append(content);
				}
				$('.compare_info_list').last().not('.first_list').css('border','none');
				
			});
			$('.compare_panel_small').show();
			$('.compare_panel_large').hide();
		}
	});

	// 显示对比栏
    $(document).on('click','.compare_btn_show',function() {
		$('.compare_panel_small').hide();
		$('.compare_panel_large').show();
	});
	// 取消对比
    $(document).on('click','.compare_btn_hide',function() {
    	$.cookie('compareskumetadataids',null,{ path: '/' });
		$('.compare_panel_small').hide();
		$('.compare_panel_large').hide();
	});

	// 隐藏对比栏
    $(document).on('click','.p_hide',function() {
		$('.compare_panel_small').show();
		$('.compare_panel_large').hide();
	});

	// 删除对比商品
    $(document).on('click','.compare_close',function() {
		var removeskuid = $(this).find('input[name=closeskuid]').val();
		//从cookie中删除该id
		var c = $.cookie('compareskumetadataids');
		if(c!=null){
			var isIn = c.indexOf(','+removeskuid+',');
			var temp = '';
			if(isIn > -1){
				temp = c.substring(0, isIn);
				temp += c.substring(isIn+removeskuid.length+1);
				c = temp;
			}
			$.cookie('compareskumetadataids', c,{ path: '/' });
		}
		var index = 0;
		if($(this).hasClass('p_list')) { // 产品对比栏删除商品
			var $comdiv = $(this).parents('.compare_list');
			index = $comdiv.index()+1;
			$comdiv.remove();
			//$('.compare_details_list:eq('+index+')').remove();
		}else{ // 产品详情栏删除商品
			index = $(this).parent('.compare_details_list').index();
			$(this).parent('.compare_details_list').remove();
			$('.compare_list:eq('+(index-1)+')').remove();
		}
		$.each($('.defaultlisttable tr'),function(){
			$(this).find('td:eq('+index+')').remove();
		});
		//修改table宽度
		var w = $('.defaultlisttable').width()-234;
		$('.defaultlisttable').width(w+'px');
		
		// 产品对比栏商品小于4个时，增加他“添加”图标
		if($('.compare_list.add').length == 0) {
			var content = '';
			content += '<div class="compare_list add">';
        	content += '<div class="compare_img add">';
        	content += '<img src="images/compare_add.jpg" alt="">';
        	content += '</div>';
        	content += '<div class="compare_txt">';
        	content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][pdpcplang-1]+'</p>';
        	content += '</div>';
        	content += '</div>';
        	$('.compare_bar_list').append(content);			
		}
		//谷歌浏览器缓存
		$('.compare_bar_list').html($('.compare_bar_list').html());
	});

	// 清空
    $(document).on('click','.compare_empty',function() {
		$.cookie('compareskumetadataids',null,{ path: '/' });
		var content = '';
		
		content += '<div class="compare_list add">';
        content += '<div class="compare_img add">';
        content += '<img src="images/compare_add.jpg" alt="">';
        content += '</div>';
        content += '<div class="compare_txt">';
        content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][pdpcplang-1]+'</p>';
        content += '</div>';
        content += '</div>';
        $('.compare_bar_list').html(content);

        $('.compare_panel_large').hide();
        $('.compare_panel_small').hide();
	});

	$(document).on('click','.compare_img.add',function() {

	});

	// 产品对比按钮
	$(document).on('click','.product_btn.compare',function() {
		var skuid = $('#pdpSkuMetadataId').val();
		if(skuid!=null&&skuid!='undefined'){
			var c = $.cookie('compareskumetadataids');
			if(c==null){
				c = ','+skuid+',';
			}else{
				var cp = c.split(',');
				if(cp.length>5){
					alert(PRODUCT_COMPARE_MESSAGE[2][pdpcplang-1]);
					return;
				}
				var isIn = c.indexOf(","+skuid+",");
				if(isIn > -1){
					alert(PRODUCT_COMPARE_MESSAGE[3][pdpcplang-1]);
					return;
				}else{
					c = c+''+skuid+',';
				}
			}
			$.cookie('compareskumetadataids', c, { path: '/' });
			
			
			$.getJSON(chinaweb+"/utility/compare/getCompareList.action?skuMetadataIds="+c+"&lang="+pdpcplang+"&jsoncallback=?",function(json){
				$('.compare_bar_list').html('');
				var content = '';
				$.each(json.skulist,function(name,value){
					content = '<div class="compare_list">';
					content += '<div class="compare_img">';
					content += '<img src="'+value.fileImageUrl+'" alt="">';
					content += '<div class="compare_close p_list"><input type="hidden" name="closeskuid" value="'+value.skuMetadataId+'"></div>';
					content += '</div>';
					content += '<div class="compare_txt">';
					content += '<p class="txt">'+value.productName+'</p>';
					content += '<p class="type">'+value.skuCode+'</p>';
					content += '</div>';
					content += '</div>';
					$('.compare_bar_list').append(content);
				});
				
				if($('.compare_list').length < 4) {
					content = '<div class="compare_list add">';
					content += '<div class="compare_img add">';
					content += '<img src="'+imgUrlForPdp+'images/compare_add.jpg" alt="">';
					content += '</div>';
					content += '<div class="compare_txt">';
					content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][pdpcplang-1]+'</p>';
					content += '</div>';
					content += '</div>';
					$('.compare_bar_list').append(content);
				}

				$('.product_popup').hide();
				$('.compare_panel_large').show();
				$('.compare_panel_small').hide();
				$('.compare_info_list').last().not('.first_list').css('border','none');
				
			});
		}
		
	});

	$(document).on('click','.popup_compare_details_panel .icon_close2',function() {
		$('.product_popup,.popup_compare_panel').hide();
	});

	// 产品比较详情栏 高度适应屏幕
	var windowheight = parseFloat($(window).height());
	if(windowheight < 925) {
		$('.popup_compare_panel').height(windowheight);
	}

	// 产品比较栏 始终置于屏幕底部
	$(window).scroll(function() {
        var docheight=parseFloat($(document).height());
        var windowheight = parseFloat($(window).height());
        var scrollheight = parseFloat($(window).scrollTop());
        if(windowheight+scrollheight > (docheight - 31)) {
        	var H = (windowheight + scrollheight) - (docheight - 31);
        	$('.compare_panel_small').css('bottom',H);
        }
        if(windowheight + scrollheight <= (docheight - 31)){
			$('.compare_panel_small').css('bottom','0px');
        }
	});
	
});