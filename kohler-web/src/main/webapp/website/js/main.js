$(document).ready(function(){
    $('#kvslides').slidesjs({width: 640,height: 710,play:{auto: false,interval: 3000},navigation:{active: false},
        callback: {
            loaded: function() {
                $(".slidesjs-pagination").css({"left":($(window).width()-$(".slidesjs-pagination").width())/2+"px"});
                $(".slidesjs-pagination").find("a").addClass("slidesjs-aitem").html(" ");
                $(".slidesjs-pagination .active").parent().addClass("slidesjs-pagination-itemactive");
            },
            start: function(){
                $(".slidesjs-pagination > li").removeClass("slidesjs-pagination-itemactive");
                $(".slidesjs-pagination .active").parent().addClass("slidesjs-pagination-itemactive");
            }
        }
    });

    var isopen1 = false;
    var isopen2 = false;
    var isopen3 = false;

    $(".btn-category").click(function(){
        $(".itemlist").hide();

        if(!isopen1){
            $(this).find("img").attr('src','images/arrow1.png');
            $(".featurelist").show();
            isopen1 = true;
        }else{
            $(this).find("img").attr('src','images/arrow2.png');
            hideItems();
            isopen1 = false;
        }        
    });

    $(".btn-property").click(function(){
        $(".itemlist").hide();

        if(!isopen2){
            $(this).find("img").attr('src','images/arrow1.png');
            $(".propertylist").show();
            $(".btn-order").hide();

            $(this).css({'width':'95%'});  
            isopen2 = true;
        }else{
            hideItems();
            isopen2 = false;
        }
    });

    $(".btn-order").click(function(){
        $(".itemlist").hide();

        if(!isopen3){
            $(this).find("img").attr('src','images/arrow1.png');
            $(".orderlist").show();
            $(".btn-property").hide();

            $(this).css({'width':'95%'});
            isopen3 = true;
        }else{
            hideItems();
            isopen3 = false;
        }            
    });

    function hideItems(){
        $(".btn-feature").find("img").attr('src','images/arrow2.png');
        $(".btn-feature").css({'width':'42%'});  
        $(".btn-feature").show();
    }
});