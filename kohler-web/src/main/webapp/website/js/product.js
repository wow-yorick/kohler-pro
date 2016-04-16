var imgUrlPre = $("#getUrl").attr("name");
var lang = 1;
var isScroll = true;
var isWaterfall = true;
var rowNum = 3;//the li num of each row

if(null!=$("#getUrl").attr("type"))
	lang = $("#getUrl").attr("type");
function getUrl() {
	var url = window.location.href;
	url = url.split("?");
	if (2 == url.length) {
		url = url[1].split("=");
		$("div.category").find("li[name='" + url[1] + "']").click();
	}
}

function setBanner(){
	$("#banner_unit").find("li").each(function(){
		var row = $(this).attr("name");
		row = row.split(":");
		var num = parseInt(row[0] - 1)*rowNum+parseInt(row[1]);
		if(0>=num)
			return true;
		insertBanner(num,$(this));
	});
}

function insertBanner(num,obj){
	console.log("insert banner  , num:"+num);
	obj.css("z-index","0");
	obj.find("span:last").attr("class","large_txt");
	if(0==$("ul.item-panel").find("li").length){
		obj.appendTo($("ul.item-panel"));
	} else {
		var i = 0;
		var size = 0;
		var isInsert = false;
		$("ul.item-panel").find("li").each(function(){
			i++;
			if($(this).attr("class")=="large")
				size += 2;
			else
				size ++;
			if(size>=num){
				if(0==size%3){
					$(this).after(obj);
				} else {
					$(this).before(obj);
				}
				isInsert = true;
				return false;
			}
		});
		if(!isInsert)
			obj.appendTo($("ul.item-panel"));
	}
}

/**
 * set lang
 */
function setLang(lan) {
	if (isNaN(lan)) {
		console.log("para:" + lan + " is not number");
		return;
	}
	lang = lan;
}

/**
 * get lang
 */
function getLang() {
	lang = $("#getUrl").attr("type")?$("#getUrl").attr("type"):1;
}

function liHover() {
	$('.item-panel li').hover(function() {
		$('.item-panel li').css('z-index','0');
		$('.detail_popup,.preview').hide();
		$(this).css('z-index','1').find('.detail_popup').show();
		$(this).find('.preview').show();
	});
}
$(document).on('mouseleave','.item-panel li',function() {
	$('.item-panel li').css('z-index','0');
	$('.detail_popup,.preview').hide();
});
function getCondition() {
	var names = new Array();
	$(".icon_close.selected")
			.each(
					function() {
						if (typeof (names[$(this).attr("name")]) == "undefined") {
							// alert($(this).attr("name") + ":" +
							// $(this).attr("content"));
							names[$(this).attr("name")] = dbToSolr($(this)
									.attr("name"))
									+ ":" + $(this).attr("content");
						} else {
							names[$(this).attr("name")] += " or "
									+ dbToSolr($(this).attr("name")) + ":"
									+ $(this).attr("content");
						}
					});
	var condition = "";
	for ( var i in names) {
		condition += names[i] + ",";
	}
	return condition;
}
var d_page = 1;
var perNum = 15;// num of per page
/**
 * arguments: 0->lang 1->page 2->sort field 3->ASC or DESC(true:ASC)
 */
function getList() {
	var width = "232px";
	var height = "200px";
	var isEmpty = arguments[0] ? arguments[0] : false;// get skus or filter
	var sort = arguments[2] ? arguments[2] : "price";// sort attr
	var page = arguments[1] ? arguments[1] : d_page;// page num
	d_page = page;
	var isAsc = arguments[3] ? arguments[3] : false;// ASC or DESC
	var category = $("div.category").find("h1").text();
	var subcategory = $("ul.cate_list").find("li.active").find("h2").attr("name");
	var categoryCondition = "";
	if ("全部分类" != subcategory && "ALL" != subcategory)
		categoryCondition += dbToSolr("subCategoryName") + ":" + subcategory;
	var condition = dbToSolr("categoryName")+":"+category + ","+dbToSolr("lang")+":"+lang + "," + getCondition();
	console.log("condition:" + condition + ";isEmpty:" + isEmpty);
	console.log("categoryCondition:"+categoryCondition);
	condition = encodeURIComponent(encodeURIComponent(condition));
	categoryCondition = encodeURIComponent(encodeURIComponent(categoryCondition));
	if (!isEmpty) {
		isWaterfall = true;
		d_page = 1;
		page = 1;
	}
	
	var search_url = china_search + "/search/list.action?categoryCondition="+categoryCondition+"&conditions="
			+ condition + "&sort=" + sort + "&page=" + page + "&perNum="
			+ perNum + "&isAsc=" + isAsc + "&lang=" + lang + "&jsoncallback=?";
//
//	$.getJSON(search_url, {
//		"condition" : condition
//	}, function(json) {
//		addLi(json, isEmpty, width, height);
//	});
	$.ajax({
		async : false,
		url : search_url,
		type : "GET",
		dataType : "jsonp",
		data : "",
		timeout : 5000,
		beforeSend : function() {
			isScroll = false;
		},
		success : function(json) {
			addLi(json, isEmpty, width, height);
			isScroll = true;
		},
		error : function() {
			console.log("get skus failed!");
			isScroll = true;
		}
	});
}
function addLi(json, isEmpty, width, height) {
	// json = decodeURI(json);
	if (!isEmpty) {
		$("ul.item-panel").empty();
		$("div.product_popup").empty();
		var fact = json.facet;
		fact = fact[0].list;
		var factArray = new Array();
		for(var i in fact) {
			factArray[fact[i]["name"]] = fact[i]["count"];
		}
		$(".cate_list").find("li").each(function(){
			var li_name = $(this).attr("type");
			if("undefined"==typeof(factArray[li_name]))
				$(this).find("h2").text($(this).find("h2").attr("name")+"(0)");
			else
				$(this).find("h2").text($(this).find("h2").attr("name")+"("+factArray[li_name]+")");
		});
	}
	var record = json.record;
//	console.log("record length:"+record.length+";perNum:"+perNum);
	if(0==record.length&&!isEmpty)
	{
		$("<li/>").attr("class", "cate1").text("无符合的单品").appendTo($("ul.item-panel"));
	}
	if (record.length < perNum)
	{
		isWaterfall = false;
		d_page = 1;
	}
	for (var i = 0; i < record.length; i++) {
		var id = "sku_" + record[i]["skuId"];
		// console.log("id:"+id);
		// list sku
		var li = $("<li/>").attr("class", "cate1").appendTo($("ul.item-panel"));
		var div = $("<div/>").appendTo(li);
		$("<img/>").css({
			"width" : width,
			"height" : height
		}).attr({
			"src" : record[i]["listImageUrl"],
			"alt" : record[i]["listImageAlt"]
		}).appendTo($("<div/>").attr("class", "item_img").appendTo(div));
		var span = $("<span/>").appendTo(div);
		$("<h3/>").text(record[i]["collectionName"]).appendTo(span);
		$("<p/>").attr("class", "type").text(record[i]["skuName"]).appendTo(
				span);
		$("<p/>").attr("class", "type").text(record[i]["skuCode"]).appendTo(
				span);
		div = $("<a/>").attr({"class":"detail_popup","href":record[i]["targetUrl"]}).appendTo(li);
		$("<img/>").css({
			"width" : width,
			"height" : height
		}).attr({
			"src" : record[i]["listImageUrl"],
			"alt" : record[i]["listImageAlt"]
		}).appendTo($("<div/>").attr("class", "item_img").appendTo(div));
		span = $("<span/>").appendTo(div);
		$("<h3/>").text(record[i]["collectionName"]).appendTo(span);
		$("<p/>").attr("class", "type").text(record[i]["skuName"]).appendTo(
				span);
		$("<p/>").attr("class", "type").text(record[i]["skuCode"]).appendTo(
				span);
		$("<div/>").attr({
			"class" : "preview",
			"name" : id
		}).css("display","none").text("快速预览").appendTo(li);
		// preview sku
		div = $("<div/>").attr({
			"class" : "popup_panel",
			"id" : id
		}).css({
			"margin-top" : "67.8px",
			"display" : "none"
		}).appendTo($("div.product_popup"));
		$("<img/>").attr({
			"src" : (record[i]["detailImageUrl"]),
			"alt" : (record[i]["detailImageAlt"])
		}).appendTo($("<div/>").attr("class", "pop_img").appendTo(div));
		var pop_div = $("<div/>").attr("class", "pop_info").appendTo(div);
		$("<img/>").attr("src", $("#getUrl").attr("name") + "images/icon_close.png").appendTo(
				$("<div/>").attr("class", "pop_close").appendTo(pop_div));
		var product_feature = $("<div/>").attr("class", "product_feature")
				.appendTo(pop_div);
		var product_pop_details = $("<div/>").attr("class",
				"product_pop_details").appendTo(product_feature);
		$("<h1/>").text((record[i]["collectionName"])).appendTo(
				product_pop_details);
		$("<h1/>").text((record[i]["skuName"])).appendTo(product_pop_details);
		$("<p/>").text((record[i]["productName"]))
				.appendTo(product_pop_details);
		var product_titles = $("<div/>").attr("class", "product_titles")
				.appendTo(product_feature);
		$("<h3/>").text((record[i]["skuCode"])).appendTo(product_titles);
		var p = $("<p/>").attr("class", "color").appendTo(product_titles);
		$("<b/>").text("颜色：").appendTo(p);
		$("<span/>").text("白色").appendTo(p);
		$("<li/>").attr({
			"color" : "白色",
			"class" : "selected"
		}).appendTo(
				$("<ul/>").attr("class", "product_color_warp clearfix")
						.appendTo(product_titles));
		$("<li/>").attr("class", "product_btn_car").text("点击查看详情").appendTo(
				$("<a/>").attr({
					"class" : "product_car_btn clearfix",
					"href" : "bathspd.html"
				}).appendTo(product_feature));
		var ul = $("<ul/>").attr("class", "product_bottom_btn clearfix")
				.appendTo(product_feature);
		$("<li/>").attr({"class":"product_btn compare","name":record[i]["skuMetadataId"]}).text("产品对比").appendTo(
				ul);
		$("<li/>").attr("class", "product_btn").text("收藏").appendTo(ul);
		$("<li/>").attr("class", "product_btn2").appendTo(ul);
		
		
	}
	liHover();
}
$(document)
		.ready(
				function() {
					var cplang = $('#thisCatLang').val();

					$(document).on('click','.submenu_panel p,.pub_subnav_warp li',
						function() {
							var h1 = parseInt($('.head_wrapper')
									.height());
							var h2 = parseInt($(
									'#' + $(this).attr("index"))
									.offset().top);
							$("body,html").animate({
								scrollTop : h2 - h1
							}, 500);
						});

					$(document).on('click', '.category li', function() {
						var classname = $(this).attr('class');
						$('.item-panel li').hide();
						if (classname == 'all') {
							$('.item-panel li').show();
						} else {
							$('.item-panel li.' + $(this).attr('class')).show()
						}
					});

					$(document).on('click','ul.select-panel li',function() {
						getList(false, 1, $(this).attr("type"), $(this)
								.attr("name"));
					});

					$(document).on('click','.filter_list_first',
						function(e) {
							if ($(this).parent()
									.hasClass('actived')) {
								$(this).parent().find('.icon_first').css('backgroundImage','url('+ $("#getUrl").attr("name")+ 'images/icon_plus.png)');
								$(this).parent().removeClass('actived');
								$(this).parent().find('.filter_list_second').hide();
							} else {
								$(this).parent().find('.icon_first').css('backgroundImage','url('+ $("#getUrl").attr("name") + 'images/icon_minus.png)');
								$(this).parent().addClass('actived');
								$(this).parent().find('.filter_list_second').show();
							}
							if (!$(this).nextAll().hasClass(
									'filter_list_second')) {
								getList();
							}
						});

					$(document).on('click','.filter_list_second_title',function() {
						if ($(this).nextAll().hasClass('filter_list_third')) {
							if ($(this).parent().hasClass(
									'actived')) {
								$(this).parent().find('.icon_second').css('backgroundImage','url('+ $("#getUrl").attr("name")+ 'images/icon_plus.png)');
								$(this).parent().removeClass('actived');
								$(this).parent().find('.filter_list_third').hide();
							} else {
								$(this).parent().find('.icon_second').css('backgroundImage','url('
														+ $("#getUrl").attr("name")
														+ 'images/icon_minus.png)');
								$(this).parent().addClass(
										'actived');
								$(this).parent().find(
										'.filter_list_third')
										.show();
							}
						} else {
							if ($(this).parent().hasClass(
									'selected')) {
								$(this).parent().removeClass(
										'selected');
								$(this)
										.next('.icon_close')
										.removeClass('selected');
							} else {
								$(this).parent().addClass(
										'selected');
								$(this).next('.icon_close')
										.addClass('selected');
							}
							getList();
						}
					});

					$(document).on(
							'click',
							'.filter_list_third_title',
							function() {
								if ($(this).parent().hasClass('selected')) {
									$(this).parent().removeClass('selected');
									$(this).next('.icon_close').removeClass(
											'selected');
								} else {
									$(this).parent().addClass('selected');
									$(this).next('.icon_close').addClass(
											'selected');
								}
							});

					$(document).on(
							'click',
							'.filter_list_second_colorpanel li',
							function() {
								// $(this).parent().find('.icon_choose').hide();
								if ($(this).hasClass('selected')) {
									$(this).removeClass('selected').find(
											'.icon_choose').hide();
								} else {
									$(this).addClass('selected').find(
											'.icon_choose').show();
								}
							});

					$(document).on('click', '.icon_close', function() {
						$(this).parent().removeClass("selected");
						$(this).removeClass("selected");
//						$(this).parent().hide();
					});
					// btn_clear
					$(document).on('click', '.filter_title .btn_clear',
							function() {
								$('.filter .filter_list').find(".selected").removeClass("selected");
								getList();
//								$('.filter .filter_list').hide();
							});

					$(document).on('click', '.filter p', function() {
						// $(this).remove();
						// $('.item-panel').find('li.test').hide();
					});

					// collection down_arrow
					$(document).on('click','.arrow_bottom',function() {
						var ulHeight = ($('.pub_subnav_warp ul').length)
								* ($('.pub_subnav_warp ul')
										.height());
						$('.pub_subnav').css('height',
								ulHeight + 12);
						$('.pub_subnav_warp').css('height',
								ulHeight + 12);
						$(this).hide();
					});

					liHover();
					$(document).on('mouseleave', '.item-panel li', function() {
						$('.item-panel li').css('z-index', '0');
						$('.detail_popup').hide();
					})

					var flag = false;
					if (navigator.userAgent.indexOf("MSIE") > 0) {
						if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
							flag = true;
						}
					}

					$(document).on('click', '.pop_close', function() {
						$('.product_popup').hide();
					});

					$(document).on('click', '.preview', function() {
						var index = $(this).attr("name");
						console.log("name:" + index);
						$('.product_popup').show();
						$('.popup_panel').hide();
						$('.popup_panel#' + index + '').show();
						// $('.popup_compare_panel').show();
					});
					
					// 产品快速预览弹出层 悬浮高度
					$('.popup_panel').css('margin-top',
							parseInt($(window).height()) / 10);
					if ($(window).height() <= 600) {
						$('.popup_panel').css('margin-top', 0);
					}
					if(cplang!=null&&cplang!=undefined){
					// 产品比较详情栏弹出层 悬浮高度
					if ($(window).height() <= 925) {
						$('.popup_compare_panel').css('margin-top', 0);
					}
					
					// 显示产品对比详情
					$(document).on('click', '.compare_btn', function(){
						var c = $.cookie('compareskumetadataids');
						$('.compare_details_info.clearfix').html('');
						$.getJSON(chinaweb + "/utility/compare/getCompareAttrList.action","skuMetadataIds="+ c
												+ "&lang="+cplang+"&jsoncallback=?",
							function(json) {
	
								var tablewidth = json.ll[0].length * 234;
								var listlength = json.ll.length;
								$('.compare_details_info.clearfix').html('');
								var baselist = json.skucomparelist.length;
								var t = '<table class="defaultlisttable"  border="0" style="width:'
										+ tablewidth
										+ 'px;"  cellpadding="4" cellspacing="0">';
								$.each(json.ll,function(i,val) {
									if (i % 2 == 0) {
										t += '<tr>';
									} else {
										t += '<tr class="even">';
									}
									$.each(val,function(j,value) {
										if (j == 0) {
											t += "<td class='firstgrid'><p><b>" + value + "</b></p></td>";
										} else {
											t += "<td class='left'><p>"+ value + "</p></td>";
										}
	
									});
									if (i == listlength - 1) {
										t += '</tr>';
									} else {
										t += '</tr>';
									}
	
								});
								$('.compare_details_info.clearfix').append(t);
								if (baselist > 0) {
									var count = 0;
									$.each($('.compare_details_list'),function() {
										if (count != 0) {
											$(this).remove();
										}
										count = count + 1;
									});
	
									$.each(json.skucomparelist, function(name,value) {
										var content = '<div class="compare_details_list clearfix">';
										content += '<div class="compare_close"><input type="hidden" name="closeskuid" value="'
												+ value.skuMetadataId
												+ '"></div>';
										content += '<div class="item_img"><img src="'
												+ value.fileImageUrl
												+ '" alt=""></div>';
										content += '<p class="item_title">'
												+ value.productName
												+ '</p>';
										content += '<p class="item_type">'
												+ value.skuCode
												+ '</p>';
										content += '<div class="compare_btns">';
										content += '<div class="compare_btn_buy">';
										content += '<div class="icon_car"></div>';
										content += '<p>'+PRODUCT_COMPARE_MESSAGE[1][cplang-1]+'</p>';
										content += '</div>';
										content += '<div class="compare_btn_favority"></div>';
										content += '<div class="compare_btn_share"></div>';
										content += '</div>';
										content += '</div>';
										$('.compare_details_list').last().after(content);
									});
								}
								$('.product_popup.footer_pop').show();
								$('.popup_panel').hide();
								$('.popup_compare_panel').show();
								$('.compare_info_list').css('border-right','1px solid #e5e5e5').last().css('border','none');
							});
	
						});

					// 显示对比栏
					$(document).on('click', '.compare_btn_show', function() {
						$('.compare_panel_small').hide();
						$('.compare_panel_large').show();
					});
					// 取消对比
					$(document).on('click', '.compare_btn_hide', function() {
						$.cookie('compareskumetadataids',null,{ path: '/' });
						$('.compare_panel_small').hide();
						$('.compare_panel_large').hide();
					});

					// 隐藏对比栏
					$(document).on('click', '.p_hide', function() {
						$('.compare_panel_small').show();
						$('.compare_panel_large').hide();
					});
					
					$(function() {
						var c = $.cookie('compareskumetadataids');
						if(c!=null){
							$.getJSON(chinaweb+"/utility/compare/getCompareList.action?skuMetadataIds="+c+"&lang="+cplang+"&jsoncallback=?",function(json){
								$('.compare_bar_list').html('');
								var content = '';
								$.each(json.skulist,function(name,value){
									content = '<div class="compare_list">';
									content += '<div class="compare_img">';
									content += '<img src="'+value.fileImageUrl+'" alt="">';
									content += '<div class="compare_close p_list"><input type="hidden" name="closeskuid" value="'+value.skuMetadataId+'"></div>';
									content += '</div>';
									content += '<div class="compare_txt">';
									content += '<p class="txt">'+value.productName+'</p>';
									content += '<p class="type">'+value.skuCode+'</p>';
									content += '</div>';
									content += '</div>';
									$('.compare_bar_list').append(content);
								});
								
								if($('.compare_list').length < 4) {
									content = '<div class="compare_list add">';
									content += '<div class="compare_img add">';
									content += '<img src="'+$("#getUrl").attr("name")+'images/compare_add.jpg" alt="">';
									content += '</div>';
									content += '<div class="compare_txt">';
									content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][cplang-1]+'</p>';
									content += '</div>';
									content += '</div>';
									$('.compare_bar_list').append(content);
								}
								$('.compare_info_list').last().not('.first_list').css('border','none');
								
							});
							$('.compare_panel_small').show();
							$('.compare_panel_large').hide();
						}
					});

					// 删除对比商品
					$(document).on('click','.compare_close',
						function() {
							var removeskuid = $(this).find('input[name=closeskuid]').val();
							// 从cookie中删除该id
							var c = $.cookie('compareskumetadataids');
							if (c != null) {
								var isIn = c.indexOf(','
										+ removeskuid + ',');
								var temp = '';
								if (isIn > -1) {
									temp = c.substring(0, isIn);
									temp += c.substring(isIn
											+ removeskuid.length
											+ 1);
									c = temp;
								}
								$.cookie('compareskumetadataids',c,{ path: '/' });
							}

							if ($(this).hasClass('p_list')) { // 产品对比栏删除商品
								var $comdiv = $(this).parents('.compare_list');
								var index = $comdiv.index() + 1;
								$comdiv.remove();
								$('.compare_details_list:eq('+ index + ')').remove();
							} else { // 产品详情栏删除商品
								var index = $(this).parent('.compare_details_list').index();
								$(this).parent('.compare_details_list').remove();
								$('.compare_list:eq('+ (index - 1) + ')').remove();
							}
							$.each($('.defaultlisttable tr'),function() {
								$(this).find('td:eq(' + index + ')').remove();
							});
							// 修改table宽度
							var w = $('.defaultlisttable').width() - 234;
							$('.defaultlisttable').width(w + 'px');

							// 产品对比栏商品小于4个时，增加他“添加”图标
							if ($('.compare_list.add').length == 0) {
								var content = '';
								content += '<div class="compare_list add">';
								content += '<div class="compare_img add">';
								content += '<img src="'+$("#getUrl").attr("name")+'images/compare_add.jpg" alt="">';
								content += '</div>';
								content += '<div class="compare_txt">';
								content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][cplang-1]+'</p>';
								content += '</div>';
								content += '</div>';
								$('.compare_bar_list').append(content);
							}
							// 谷歌浏览器缓存
							$('.compare_bar_list').html($('.compare_bar_list').html());
						});

					// 清空
					$(document).on('click', '.compare_empty', function() {
						$.cookie('compareskumetadataids', null,{ path: '/' });
						var content = '';
						content += '<div class="compare_list add">';
						content += '<div class="compare_img add">';
						content += '<img src="'+$("#getUrl").attr("name")+'images/compare_add.jpg" alt="">';
						content += '</div>';
						content += '<div class="compare_txt">';
						content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][cplang-1]+'</p>';
						content += '</div>';
						content += '</div>';
						$('.compare_bar_list').html(content);

						$('.compare_panel_large').hide();
						$('.compare_panel_small').hide();
					});

					$(document).on('click', '.compare_img.add', function() {

					});

					var i = 1;
					// 产品对比按钮
					$(document).on('click', '.product_btn.compare', function() {
							var skuid = $(this).attr('name');
							if (skuid != null
									&& skuid != 'undefined') {
								var c = $
										.cookie('compareskumetadataids');
								if (c == null) {
									c = ',' + skuid + ',';
								} else {
									var cp = c.split(',');
									if (cp.length > 5) {
										alert(PRODUCT_COMPARE_MESSAGE[2][cplang-1]);
										return;
									}
									var isIn = c.indexOf(","
											+ skuid + ",");
									if (isIn > -1) {
										alert(PRODUCT_COMPARE_MESSAGE[3][cplang-1]);
										return;
									} else {
										c = c + '' + skuid + ',';
									}
								}
								$.cookie('compareskumetadataids',c,{ path: '/' });

								$.getJSON(chinaweb + "/utility/compare/getCompareList.action?skuMetadataIds=" + c
														+ "&lang="+cplang+"&jsoncallback=?",
									function(json) {
										$('.compare_bar_list').html('');
										var content = '';
										$.each(json.skulist,function(name,value) {
											content = '<div class="compare_list">';
											content += '<div class="compare_img">';
											content += '<img src="'
													+ value.fileImageUrl
													+ '" alt="">';
											content += '<div class="compare_close p_list"><input type="hidden" name="closeskuid" value="'
													+ value.skuMetadataId
													+ '"></div>';
											content += '</div>';
											content += '<div class="compare_txt">';
											content += '<p class="txt">'
													+ value.productName
													+ '</p>';
											content += '<p class="type">'
													+ value.skuCode
													+ '</p>';
											content += '</div>';
											content += '</div>';
											$('.compare_bar_list').append(content);
										});

										if ($('.compare_list').length < 4) {
											content = '<div class="compare_list add">';
											content += '<div class="compare_img add">';
											content += '<img src="'
													+ $("#getUrl").attr("name")
													+ 'images/compare_add.jpg" alt="">';
											content += '</div>';
											content += '<div class="compare_txt">';
											content += '<p class="txt">'+PRODUCT_COMPARE_MESSAGE[0][cplang-1]+'</p>';
											content += '</div>';
											content += '</div>';
											$('.compare_bar_list').append(content);
										}

										$('.product_popup').hide();
										$('.compare_panel_large').show();
										$('.compare_panel_small').hide();
										$('.compare_info_list').last().not('.first_list').css('border','none');

									});
							}

						});
					}
					$(document).on('click','.popup_compare_details_panel .icon_close2',function() {
						$('.product_popup,.popup_compare_panel').hide();
					});
					// 产品比较详情栏 高度适应屏幕
					var windowheight = parseFloat($(window).height());
					var popdetailheight = 925;
					if (windowheight < 925) {
						$('.popup_compare_panel').height(windowheight);
					}

					// 产品比较栏 始终置于屏幕底部
					$(window).scroll(function() {
						var docheight = parseFloat($(document)
								.height());
						var windowheight = parseFloat($(window)
								.height());
						var scrollheight = parseFloat($(window)
								.scrollTop());
						if (windowheight + scrollheight > (docheight - 31)) {
							var H = (windowheight + scrollheight)
									- (docheight - 31)
							$('.compare_panel_small').css(
									'bottom', H);
						}
						if (windowheight + scrollheight <= (docheight - 31)) {
							$('.compare_panel_small').css(
									'bottom', '0px');
						}
					});

					$("ul.cate_list").find("li").click(
							function() {
								$("ul.cate_list").find("li.active")
										.removeClass("active");
								$(this).addClass("active");
								getList();
							});
					getUrl();
					getLang();
					setBanner();

				})