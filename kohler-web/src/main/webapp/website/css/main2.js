!function(){
	var clickEvt = "click",
	App = {
		init: function() {
            this.bxSlider();
			this.buyChoice();
			this.selectDrop();
			this.goTop();
        },
        bxSlider: function() {

		},
		buyChoice: function() {
			var colorBox = $('.color-box li'),
				listBox = $('.list-box li');
			colorBox.each(function() {
				$(this).css('background-color',$(this).attr('color-hex'));
				$(this).on(clickEvt, function() {
					$(this).addClass('selected').siblings().removeClass('selected');
					$('.color').text($(this).attr('color-name'));
				})
			})
			listBox.each(function() {
				$(this).on(clickEvt, function() {
					$(this).addClass('selected').siblings().removeClass('selected');
				})
			})
		},
		selectDrop: function() {

		},
		goTop: function() {
			var goTopTxt = '<div class="goTop"></div>';
			$(goTopTxt).appendTo('body');
			var h = $(window).height() + 500;
			$(window).scroll(function() {
				if($(window).scrollTop() > h){
					$('.goTop').fadeIn();
				}else {
					$('.goTop').fadeOut();
				}
			})
			$('.goTop').on(clickEvt, function() {
				$("body,html").animate({
					scrollTop: 0},300);
			})
		}
	};
	App.init();
}();

$(document).ready(function(){

	var pros = false;
	var ords = false;
	$('.product-suite-select-txt').on('click', function() {
		var $next = $(this).next();
		$next.removeClass('hide');

		$parent = $(this).parent();
		if($parent.hasClass("propertycombobox")){
			$parent.removeClass("propertycombobox");
			$(".orderselect").hide();
			pros = true;
		}
		if($parent.hasClass("ordercombobox")){
			$parent.removeClass("ordercombobox");
			$(".propertyselect").hide();
			ords = true;
		}
	});

	$('.product-suite-select-ul li').click(function(){
		$parents = $(this).parents(".product-suite-select-option");
		if($parents.hasClass("allcombobox")){
			var $parentul = $(this).parents(".product-suite-select-ul");
			$parentul.addClass("hide");
			//$($parentul.prev()).text($(this).text());
            $($parentul.prev()).find(".product-suite-select-txt-left").text($(this).text())
		}
	});

	$(".combobox-cancle").click(function(){
		var $parentul = $(this).parents(".product-suite-select-ul");
		$parentul.addClass("hide");
		$parents = $(this).parents(".product-suite-select-option");
		if(pros){
			$parents.addClass("propertycombobox");
			$(".orderselect").show();
			pros = false;
		}
		if(ords){
			$parents.addClass("ordercombobox");
			$(".propertyselect").show();
			ords = false;
		}
	})
})