$(function() {
	// 百度地图API功能
	var map = new BMap.Map("map_api_content");
	var point = new BMap.Point(121.487899,31.249162);//116.331398,39.897445);
	//map.centerAndZoom(point,12);
	map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom();
	
	
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
                    '地址：上海协丽建材有限公司旗舰店<br/>电话：(010)59928888<br/>简介：上海协丽建材有限公司旗舰店' +
                  '</div>';

    //创建检索信息窗口对象
    var searchInfoWindow = null;
   
   
   
   
	searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
			title  : "科勒(旗舰店)",      //标题
			width  : 290,             //宽度
			height : 105,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true,     //自动平移
			searchTypes   :[
				BMAPLIB_TAB_SEARCH,   //周边检索
				BMAPLIB_TAB_TO_HERE,  //到这里去
				BMAPLIB_TAB_FROM_HERE //从这里出发
			]
		});
    var marker = new BMap.Marker(point); //创建marker对象
    marker.enableDragging(); //marker可拖拽
    marker.addEventListener("click", function(e){
	    searchInfoWindow.open(marker);
    })
    map.addOverlay(marker); //在地图中添加marker
	
	
	
	
	
	
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
				searchInfoWindow.open(marker);
				map.addOverlay(marker);
			}
		},city);
		searchInfoWindow.open(marker);
		map.addOverlay(marker);
		$('.popup_shop_name').text(shop);
		$('.popup_shop_address').text(address);
	})
		$('.icon_close').click(function() {
		$('.product_popup').hide();
	})
//	// 百度地图API功能
//	var map = new BMap.Map("map_api_content");
//	var point = new BMap.Point(116.331398,39.897445);
//	map.centerAndZoom(point,12);
//	// 创建地址解析器实例
//	var myGeo = new BMap.Geocoder();
//
//	$('.list_content_details').click(function() {
//		var city = $(this).attr('data-city');
//		var shop = $(this).find('.shop_info').text();
//		var address = $(this).find('.address_info').text();
//		$('.product_popup,.popup_panel.map_panel').show();
//
//		$('.popup_panel.map_panel').css('margin-top',parseInt($(window).height())/8);
//    	if($(window).height() <= 600) {
//    		$('.popup_panel.map_panel').css('margin-top',0);
//    	}
//
//		// 将地址解析结果显示在地图上,并调整地图视野
//		myGeo.getPoint(address, function(point){
//			if (point) {
//				map.centerAndZoom(point, 16);
//				map.addOverlay(new BMap.Marker(point));
//			}
//		},city);
//
//		$('.popup_shop_name').text(shop);
//		$('.popup_shop_address').text(address);
//	})
//	
//	$('.icon_close').click(function() {
//		$('.product_popup').hide();
//	})

})
