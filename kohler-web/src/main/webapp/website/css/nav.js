
$(function(){
	//头部js
	$(".head_w .home").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(".head_w .hid").hide();
		}
		else{
			$(this).addClass("active");
			$(".head_w .hid").show();
		}
	});
	$(".menu div").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(this).siblings("ul").hide();
		}
		else{
			$(this).addClass("active");
			$(this).siblings("ul").show();
		}
	});
	$(".head_w .search").click(function(){
		$(".head_b").css("display","block");
	});
	$(".head_b .home").click(function(){
		$(".head_b").css("display","none");
	});
	// 尾部js代码开始
    $(".menu_one h2").each(function (i) {
        $(this).on("click",function () {
            if ($(this).next().css("display") == "none") 
            {
                $(this).next().show();
                $(this).addClass("active");
            } 
            else 
            {
                $(this).next().hide();
                $(this).removeClass("active");
            }
            var $menu_two=$(this).parent().siblings().children('.menu_two');
            if($menu_two.css('display','block'))
            {
                $menu_two.hide();
                $menu_two.prev().removeClass("active"); 
            }
        });
    });

	if($('.slider').length != null){
		$('.slider').bxSlider({
			auto: false,
			controls: false
		});
	}
});