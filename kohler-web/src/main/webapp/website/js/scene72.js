function ddd(json,dat,colorid)
{
							 $('.product_feature h1 span').html(dat.sku_code);
							 //清空上一个属性 配件的值
							 $(".product_parts_bar ul").html("");
							 //如果配件为空，则隐藏配件的div
							 if(dat.include_accessorys.length==0){
								 $(".product_parts_title").hide();
								  $(".product_parts_title").next().hide();
								   $(".product_parts_panel").hide();
								 }
							  //配件赋值到页面，根据配件的个数追加li
								for(var s=0;s<dat.include_accessorys.length;s++){
								$(".product_parts_bar ul").append("<li><a href="+dat.include_accessorys[s].product_url+"><span class='second_txt'>"+dat.include_accessorys[s].sku_code+"</span><span class='second_txt'>"+dat.include_accessorys[s].name+"</span></a></li>");
									}
							 //高亮按钮的默选项

							 //var attrArray  = dat.real_attr.split(";");
							 var attrArray  = dat.real_attr;
							 
							 for(var n=0;n<attrArray.length;n++)
							 {	 
								var productli=$(".product_feature").find("li");
								for(var m=0;m<productli.length;m++){
									var b=$(".product_feature").find("li:eq("+m+")").attr("realAttr");
										if(b==attrArray[n]){
											$(".product_feature").find("li:eq("+m+")").addClass("selected");
											}
									}
								
								 //获取当前颜色赋值到页面										
								 //var attrColor=attrArray[n].split(":");
								 var attrColor=attrArray[n];
								 for(var y=0;y<attrColor.length;y++){
										for(var z=0;z<json.sku_attributes[colorid].sku_attributes_values.length;z++){
											var color=json.sku_attributes[colorid].sku_attributes_values[z].attr_value;
											if(attrColor[y]==color){
												$("p span").eq(0).html(attrColor[y]);
												}
											}
										}		
						     }
							 //默认的下方小缩略图
							 var imagelist = dat;
							 $("#pdplist").children("ul").css("left",0);
							 $("#pdplist").children("ul").html("");
                             for(var key in imagelist)
                             {
                                 if(key.indexOf("url")>0)
                                 {
                                    $("#pdplist").children("ul").append("<li><img src="+imagelist[key]+"></li>");
                                 }
                             }
							 //默认第一张图为大图
							 	var fisrtimg=$("#pdplist ul li").eq(0).children().attr("src");
							 	$(".imgbox .probox").find("img").attr("src",fisrtimg);
								$(".imgbox .showbox").find("img").attr("src",fisrtimg);
								
							 	$("#pdplist ul li").click(function(){ 
									var imgsrc=$(this).children().attr("src");
									$(".imgbox .probox").find("img").attr("src",imgsrc);
									$(".imgbox .showbox").find("img").attr("src",imgsrc);
									})
							 var lilist=$("#pdplist li").length;
							 $("#pdplist ul").css("width",lilist*130);
							 if(lilist<=4){//当小缩略图个数小于四个时，不显示左右图标
								 $("#slides .previous").css("display","none");
								 $("#slides .next").css("display","none");
								 }else{
								 $("#slides .previous").css("display","block");
								 $("#slides .next").css("display","block"); 
								}
							 
							
}
$(document).ready(function() {
	 
	
    // product_feature
    //$('.product_color_warp li,.product_param_warp li').click(function() {
//        // 判断是否是跟换颜色选项
//        if($(this).parent().hasClass('product_color_warp')) {
//            $('.product_feature .color').html('<b>颜色：</b><span>'+$(this).attr('color')+'</span>');
//        }
//        $(this).addClass('selected').siblings().removeClass('selected');
//
//        var $thumbnail = $('.img-thumbnail-list .slides-bar');
//        var slidesid = 'slides'+new Date().getTime();
//        
//        // 请求数据 获取SKU及相关信息
//        $.ajax({
//            type: "GET",
//            url: "test.json",
//            data: {sku:$(this).attr('data-sku')},
//            dataType: "json",
//            success: function(result) {
//                $.each(result,function(i,item){  
//                    $('.product_feature h1 span').html(item.sku)
//
//                    content = '<ul class="clearfix">';
//                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
//                    content += '</ul>';
//                    content += '<ul class="clearfix">';
//                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
//                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
//                    content += '</ul>';
//                    content += '<div href="#" class="slidesjs-previous slidesjs-navigation"></div>';
//                    content += '<div href="#" class="slidesjs-next slidesjs-navigation"></div>';
//
//                    // $thumbnail.empty();
//                    $('#img-divs1').empty();
//                    if(!flag){
//                        var path = item.mainurl;
//                        var s7flyout_div = new s7viewers.FlyoutViewer({
//                            containerId: "img-divs1",
//                            params: {
//                                asset: "kohlerchina/"+path,
//                                serverurl: "http://s7d4.scene7.com/is/image/",
//                                contenturl: "http://s7d4.scene7.com/skins/",
//                                config: "Scene7SharedAssets/Universal_HTML5_Zoom_light"
//                            }
//                        }).init();
//                    }
//
//                    // 重新初始化缩略图
//                    $thumbnail.html(content);
//                    $('#slides').data('plugin_slidesjs', false); //去除重复初始化限制
//                    if($('#slides ul').length == 1) {
//                        $('#slides .slidesjs-previous').hide();
//                        $('#slides .slidesjs-next').hide();
//                    }else{
//                        $('#slides').slidesjs({
//                            navigation: false,
//                            pagination: false
//                        });
//                    }
//                    $('.img-thumbnail-list .slidesjs-control').css({'width':'520px','margin':'0 auto'});
//                    $('.img-thumbnail-list #slides').hide();
//
//                    // 更新配件文字内容
//                    content = '<p class="second_txt">K-1234567</p>';
//                    content += '<p class="second_txt">落枕1111</p>';
//                    $('.product_parts_bar.second_parts.p1').html(content);
//                }) 
//            }
//        })
//    })
	
    // pdp缩略图
    $('.img-thumbnail-list').hover(function() {
        $(this).addClass('show');
        $('#slides').show();
    })
    $('.img-thumbnail-list').mouseleave(function() {
        $(this).removeClass('show');
        $('#slides').hide();
    })
		 var scrollUlLeft = 0,    //.scroll_ul 初使化时的 left 值为 0
          prevAllow = true,    //为了防止连续点击上一页按钮
          nextAllow = true,    //为了防止连续点击下一页按钮
		  scrollUlWidth = $('#pdplist ul li').outerWidth(true);   //单个 li 的宽度
	 //点击上一页	
	$("#slides .previous").click(function(){
		if (prevAllow) {
			prevAllow = false;
			scrollUlLeft = scrollUlLeft - scrollUlWidth;
			$('#pdplist ul').css('left', scrollUlLeft);
			//复制最后一个 li 并插入到 ul 的最前面
            $('#pdplist ul li:last').clone(true).prependTo('#pdplist ul');
			//删除最后一个 li
            $('#pdplist ul li:last').remove();
			$('#pdplist ul').animate({
                left : scrollUlLeft + scrollUlWidth
            }, 300, function() {
                scrollUlLeft = parseInt($('#pdplist ul').css('left'), 10);
                prevAllow = true;
            })
			}
		})
		
	//点击下一页	
	$("#slides .next").click(function(){	
		if (nextAllow) {
			nextAllow = false;
			$('#pdplist ul').animate({
                left : scrollUlLeft - scrollUlWidth
            }, 300, function() {
                scrollUlLeft = parseInt($('#pdplist ul').css('left'), 10);
                scrollUlLeft = scrollUlLeft + scrollUlWidth;
                $('#pdplist ul').css('left', scrollUlLeft);
                //复制第一个 li 并插入到 ul 的最后面
                $('#pdplist ul li:first').clone(true).appendTo('#pdplist ul');
                //删除第一个 li
                $('#pdplist ul li:first').remove();
                nextAllow = true;
            })
			}
		})

    //$(document).on('click','.img-thumbnail-list li',function(){
//        $('#img-divs1').empty();
//        var path = $(this).attr('data-id');
//        var s7flyout_div = new s7viewers.FlyoutViewer({
//            containerId: "img-divs1",
//            params: {
//                asset: "kohlerchina/"+path,
//                serverurl: "http://s7d4.scene7.com/is/image/",
//                contenturl: "http://s7d4.scene7.com/skins/",
//                config: "Scene7SharedAssets/Universal_HTML5_Zoom_light",
//            }
//        }).init();
    //})
})