$(document).ready(function() {
    // Adobe Scene 7 init
    var path = $("#img-divs1").attr("data-id");
    var s7flyout_div = new s7viewers.FlyoutViewer({
        containerId: "img-divs1",
        params: {
            asset: "kohlerchina/"+path,
            serverurl: "http://s7d4.scene7.com/is/image/",
            contenturl: "http://s7d4.scene7.com/skins/",
            config: "Scene7SharedAssets/Universal_HTML5_Zoom_light",
        }
    }).init();


    var flag = false; 
    if(navigator.userAgent.indexOf("MSIE")>0) {   
        if(navigator.userAgent.indexOf("MSIE 6.0")>0)  
        {   
        flag = true;  
        }   
    } 

    // product_feature
    $('.product_color_warp li,.product_param_warp li').click(function() {
        // 判断是否是跟换颜色选项
        if($(this).parent().hasClass('product_color_warp')) {
            $('.product_feature .color').html('<b>颜色：</b><span>'+$(this).attr('color')+'</span>');
        }
        $(this).addClass('selected').siblings().removeClass('selected');

        var $thumbnail = $('.img-thumbnail-list .slides-bar');
        var slidesid = 'slides'+new Date().getTime();
        
        // 请求数据 获取SKU及相关信息
        $.ajax({
            type: "GET",
            url: "test.json",
            data: {sku:$(this).attr('data-sku')},
            dataType: "json",
            success: function(result) {
                $.each(result,function(i,item){  
                    $('.product_feature h1 span').html(item.sku)

                    content = '<ul class="clearfix">';
                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
                    content += '</ul>';
                    content += '<ul class="clearfix">';
                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1-black"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1-black?wid=120&hei=120" alt=""></li>';
                    content += '<li data-id="toilet-pd1"><img src="http://s7d4.scene7.com/is/image/kohlerchina/toilet-pd1?wid=120&hei=120" alt=""></li>';
                    content += '</ul>';
                    content += '<div href="#" class="slidesjs-previous slidesjs-navigation"></div>';
                    content += '<div href="#" class="slidesjs-next slidesjs-navigation"></div>';

                    // $thumbnail.empty();
                    $('#img-divs1').empty();
                    if(!flag){
                        var path = item.mainurl;
                        var s7flyout_div = new s7viewers.FlyoutViewer({
                            containerId: "img-divs1",
                            params: {
                                asset: "kohlerchina/"+path,
                                serverurl: "http://s7d4.scene7.com/is/image/",
                                contenturl: "http://s7d4.scene7.com/skins/",
                                config: "Scene7SharedAssets/Universal_HTML5_Zoom_light"
                            }
                        }).init();
                    }

                    // 重新初始化缩略图
                    $thumbnail.html(content);
                    $('#slides').data('plugin_slidesjs', false); //去除重复初始化限制
                    if($('#slides ul').length == 1) {
                        $('#slides .slidesjs-previous').hide();
                        $('#slides .slidesjs-next').hide();
                    }else{
                        $('#slides').slidesjs({
                            navigation: false,
                            pagination: false
                        });
                    }
                    $('.img-thumbnail-list .slidesjs-control').css({'width':'520px','margin':'0 auto'});
                    $('.img-thumbnail-list #slides').hide();

                    // 更新配件文字内容
                    content = '<p class="second_txt">K-1234567</p>';
                    content += '<p class="second_txt">落枕1111</p>';
                    $('.product_parts_bar.second_parts.p1').html(content);
                }) 
            }
        })
    })

    // pdp缩略图
    $('.img-thumbnail-list').hover(function() {
        $(this).addClass('show');
        $('#slides').show();
    })
    $('.img-thumbnail-list').mouseleave(function() {
        $(this).removeClass('show');
        $('#slides').hide();
    })
    $(document).on('click','.img-thumbnail-list li',function(){
        $('#img-divs1').empty();
        var path = $(this).attr('data-id');
        var s7flyout_div = new s7viewers.FlyoutViewer({
            containerId: "img-divs1",
            params: {
                asset: "kohlerchina/"+path,
                serverurl: "http://s7d4.scene7.com/is/image/",
                contenturl: "http://s7d4.scene7.com/skins/",
                config: "Scene7SharedAssets/Universal_HTML5_Zoom_light",
            }
        }).init();
    })
})