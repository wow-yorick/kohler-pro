$(document).on('mouseenter','.mm_list_detail',function() {
    $('.mm_list_detail').css('z-index','0');
    $('.mm_list_popup,.preview').hide();
    $(this).css('z-index','1').find('.mm_list_popup').show();
    $(this).find('.preview').show();
})
$(document).on('mouseleave','.mm_list_detail',function() {
    $('.mm_list_detail').css('z-index','0');
    $('.mm_list_popup,.preview').hide();
})