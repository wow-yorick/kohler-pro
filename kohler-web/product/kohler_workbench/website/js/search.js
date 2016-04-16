$(function() {
	$('.pub_searchbar input').focus(function() {
		$('.pub_searchhistory').show();
	})
	$('.pub_searchhistory li').click(function() {
		$('.pub_searchbar input').val($(this).text());
		$('.pub_searchhistory').hide();
	})

	var flag = false;
   	if(navigator.userAgent.indexOf("MSIE")>0) {   
   	    if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
   	    {   
   	    flag = true;  
   	    }   
   	       
   	} 

	$("body").click(function(e){  
	    if ($(e.target).is('.search_panel')) {
	    	$('.pub_search').show();
			if(!flag) {
				$('.content_wrapper').css("top",parseInt($('.head_wrapper').height()));
				// $('.content_wrapper.pdp').css("padding-top",parseInt($('.head_wrapper').height())+35);
    		}
	    } else if ($(e.target).is('.pub_searchicon')) {
	    	alert('搜索');
	    } else if ($(e.target).is('.pub_searchbar input')) {
	    	$('.pub_searchhistory').show();
	    } else if ($(e.target).is('.pub_searchhistory li') || $(e.target).is('.pub_searchhistory') || $(e.target).is('.pub_searchpanel') || $(e.target).is('.pub_search')) {
	    	return;
	    } else{
	    	$('.pub_search').hide();
	    	$('.search_fail_panel .pub_search').show();
	    	$('.pub_searchhistory').hide();
	    	if(!flag) {
				$('.content_wrapper').css("top",parseInt($('.head_wrapper').height()));
    		}
	    }
    })

	// 下拉列表选择栏目
	$('.search p').click(function() {
		$(this).next('.select-panel').show();
	})
	$('.option').click(function() {
		$(this).addClass('selected').siblings().removeClass('selected').parent().prev().text($(this).text());
	})
	$("body").click(function(e){  
	    if ($(e.target).is('.search p')) {
	    	return;
	    } 
	    $(".select-panel").hide(); 
    })

    $('.dltitle.first_bar').click(function() {
    	if($(this).parent().hasClass('active')) {
    		$(this).parent().removeClass('active').find('.file_bar.second').hide().parent().find('.icon_first').css('backgroundImage','url(images/icon_plus.png)');
    	} else {
    		$(this).parent().addClass('active').find('.file_bar.second').show().parent().find('.icon_first').css('backgroundImage','url(images/icon_minus.png)');
    	}
    })

})