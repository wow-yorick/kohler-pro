$(function() {
	$('.address_info_panel .icon_select').click(function() {
		$(this).toggleClass('selected');
	})

	$('#btn_reset').click(function() {
		$('.search.province p').text("选择您所在的省份");
		$('.search.city p').text("选择您所在的城市");
		$('.search .option').removeClass('selected');
		$('.search.address input').val('');
		$('.icon_select').removeClass('selected');
	})

	$('#btn_search').click(function() {
		alert('搜索');
	})
})