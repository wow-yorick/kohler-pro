!function(){
	var clickEvt = "ontouchstart" in document.documentElement ? "touchstart" : "click",
	App = {
		init: function() {
			this.goTop();
			this.jf_toggle();
			this.selectDrop();
			this.showHide();
			this.checkBox();
        },
		goTop: function() {
			var goTopTxt = '<div class="goTop"></div>';
			$(goTopTxt).appendTo('body');
			var h = $(window).height();
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
		},
		jf_toggle: function(){
			var b = $('.view_jf_dedatil'), c = $('.user-jf');
			b.on(clickEvt, function() {
				c.toggleClass('hide');
				if(c.is(':visible')){
					b.addClass('on');
				}else{
					b.removeClass('on');
				}
			})
		},
		selectDrop: function() {
			var selectBtn = $('.product-suite-select-txt'),
				selectBox = $('.product-suite-select-ul'),
				selectBoxLi = $('.product-suite-select-ul li');
				selectBtn.on(clickEvt, function() {
					$(this).next(selectBox).toggleClass('hide');
				})
				selectBoxLi.on(clickEvt, function() {
					selectBox.addClass('hide');
				})
		},
		showHide: function(){
			var b = $('.toggle-btn'),
				bx = $('.toggle-box');
			b.on(clickEvt, function() {
				$(this).next(bx).toggleClass('hide');
				if($(this).next(bx).is(':visible')){
					$(this).find('span').text('-');
				}else{
					$(this).find('span').text('+');
				}
			})
		},
		checkBox: function(){
			var c = $('.ui-checkbox');
			c.on(clickEvt, function() {
				$(this).toggleClass('on');
			})
		},
	};
	App.init();
}();

$('.ui-textarea').on('click focus',function(){
	if( $(this).val() == '详细地址：'){
		$(this).val('')
	}
})

$('.ui-select-txt').on('click', function() {
	var $next = $(this).next();
	$next.toggleClass('hide');
	$(this).parents(".ui-select").addClass('on');
});
$('.ui-select-dropdown li').click(function(){
	$parents = $(this).parents(".ui-select");
	if($parents.hasClass("allcombobox")){
		var $parentul = $(this).parents(".ui-select-dropdown");
		$parentul.addClass("hide");
		$parents.removeClass('on');
        $parents.find(".ui-select-txt").text($(this).text());
	}
});

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