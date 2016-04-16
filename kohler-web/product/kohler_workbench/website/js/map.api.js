$(function() {
	// 百度地图API功能
	var map = new BMap.Map("map_api_content");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();

	$('.list_content_details').click(function() {
		var city = $(this).attr('data-city');
		var shop = $(this).find('.shop_info').text();
		var address = $(this).find('.address_info').text();
		$('.product_popup,.popup_panel.map_panel').show();

		$('.popup_panel.map_panel').css('margin-top',parseInt($(window).height())/8);
    	if($(window).height() <= 600) {
    		$('.popup_panel.map_panel').css('margin-top',0);
    	}

		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point){
			if (point) {
				map.centerAndZoom(point, 16);
				map.addOverlay(new BMap.Marker(point));
			}
		},city);

		$('.popup_shop_name').text(shop);
		$('.popup_shop_address').text(address);
	})
	
	$('.icon_close').click(function() {
		$('.product_popup').hide();
	})

})
