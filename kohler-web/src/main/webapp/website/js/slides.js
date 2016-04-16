$(function() {
	$('.slides-bar').each(function(){
		var id = this.id;
		// alert(id);
		
		if($('#'+id+' ul').length == 1) {
			$('#'+id+' .slidesjs-previous').hide();
			$('#'+id+' .slidesjs-next').hide();
		}else{
			$('#'+id).slidesjs({
	    		navigation: false,
	    		pagination: false
			})
			$('.img-thumbnail-list .slidesjs-control').css({'width':'520px','margin':'0 auto'});
			$('.img-thumbnail-list #slides').hide();
		}
	})
})
function productsol(proid){
	var scrollUlLeft = 0,    //.scroll_ul 初使化时的 left 值为 0
          prevAllow = true,    //为了防止连续点击上一页按钮
          nextAllow = true,    //为了防止连续点击下一页按钮
		  scrollUlWidth = $("#"+proid+" ul li").outerWidth(true);   //每次滚动的宽度 的宽度
	var lilength=$("#"+proid+" ul li").length;
	if(lilength<=4){
		$("#"+proid+" .slidesjs_previous").hide();
		$("#"+proid+" .slidesjs_next").hide();
		}
	$("#"+proid+" ul").css("width",lilength*234);
		 //点击上一页	
	$("#"+proid+" .slidesjs_previous").click(function(){
		if (prevAllow) {
			prevAllow = false;
			scrollUlLeft = scrollUlLeft - scrollUlWidth;
			$("#"+proid+" ul").css('left', scrollUlLeft);
			//复制最后一个 li 并插入到 ul 的最前面
            $("#"+proid+" ul li:last").clone(true).prependTo("#"+proid+" ul");
			//删除最后一个 li
            $("#"+proid+" ul li:last").remove();
			$("#"+proid+" ul").animate({
                left : scrollUlLeft + scrollUlWidth
            }, 300, function() {
                scrollUlLeft = parseInt($("#"+proid+" ul").css('left'), 10);
                prevAllow = true;
            })
			}
		})
		
	//点击下一页	
	$("#"+proid+" .slidesjs_next").click(function(){	
		if (nextAllow) {
			nextAllow = false;
			$("#"+proid+" ul").animate({
                left : scrollUlLeft - scrollUlWidth
            }, 300, function() {
                scrollUlLeft = parseInt($("#"+proid+" ul").css('left'), 10);
                scrollUlLeft = scrollUlLeft + scrollUlWidth;
                $("#"+proid+" ul").css('left', scrollUlLeft);
                //复制第一个 li 并插入到 ul 的最后面
                $("#"+proid+" ul li:first").clone(true).appendTo("#"+proid+" ul");
                //删除第一个 li
                $("#"+proid+" ul li:first").remove();
                nextAllow = true;
            })
			}
		})
	
	}
