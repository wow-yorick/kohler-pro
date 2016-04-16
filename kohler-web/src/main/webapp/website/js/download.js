$(function() {
    $('.banner_fix').mouseover(function(){
    	$('.manu_hover').hide();
        $(this).find('.manu_hover').show();
    })
    $('.banner_fix').mouseout(function(){
        $(this).find('.manu_hover').hide();
    })
})
    