$.nameSpace('KOLO', 'MobileDrag');
KOLO.MobileDrag = function () { };
$.extend(KOLO.MobileDrag, {
    options: [],
    //初始化OPTION对象
    initOption: function (obj, i, callback) {
        //滑动对象
        var o = {};
        o.draging = false;
        o.target = $.packing(obj);
        //拖拽对象
        o.drager = o.target.find('.container');
        //鼠标onmousedown的起始位置
        o.startX = 0;
        o.upX = 0;
        o.marginLeft = 0;
        o.callback = callback;
        KOLO.MobileDrag.options[i] = o;
    },
    //创建OPTION对象的值 this=event
    createOption: function (e, i) {
        var pageX = e.touches[0].pageX;
        //初始化 鼠标点击的位置
        KOLO.MobileDrag.options[i].draging = true;
        KOLO.MobileDrag.options[i].marginLeft = parseInt(KOLO.MobileDrag.options[i].drager.css('margin-left'));
        KOLO.MobileDrag.options[i].startX = pageX;
    },
    //清除OPTION对象的值
    clearOption: function (e, i) {
        $(document).scroll(function () { return false; });
        var pageX = e.changedTouches[0].pageX;
        if (pageX == KOLO.MobileDrag.options[i].startX) return;

        KOLO.MobileDrag.options[i].upX = pageX;
        if ($.isFunction(KOLO.MobileDrag.options[i].callback)) {
            KOLO.MobileDrag.options[i].callback.call(KOLO.MobileDrag.options[i]);
        }
        KOLO.MobileDrag.options[i].draging = false;
        KOLO.MobileDrag.options[i].startX = 0;
        KOLO.MobileDrag.options[i].upX = 0;


    },
    //水平移动
    horMove: function (e, i) {
        var o = KOLO.MobileDrag.options[i],
            pageX = e.touches[0].pageX,
            thisX = pageX,
            left = thisX - o.startX,
            marginLeft = KOLO.MobileDrag.options[i].marginLeft + left;
        o.drager.css('margin-left', marginLeft);
    }
});
$.extend(KOLO.MobileDrag.prototype, {
    create: function (obj, callback) {
        $.packing(obj).each(function (i) {
            //this.dragI = i;
            KOLO.MobileDrag.initOption(this, i, callback);
            this.addEventListener('touchstart', function (e) {
                KOLO.MobileDrag.createOption(e, i);
            }, false);
            this.addEventListener('touchmove', function (e) {
                e.preventDefault();
                var o = KOLO.MobileDrag.options[i];
                if (o && o.draging != false) {
                    KOLO.MobileDrag.horMove(e, i);
                }
            }, false);
            this.addEventListener('touchend', function (e) {
                KOLO.MobileDrag.clearOption(e, i);
            });
        });
    }
});
$(document).ready(function () {
    var imgs = $('.jsBanner img'),
        banner = $('.jsBanner'),
        container = banner.find('div.container'),
        len = imgs.length,
        index = 0,
        imgwidth = banner.width();

    imgs.each(function () {
        $(this).css({
            'width': imgwidth,
            'height': 'auto'
        });
    });
    function loopmove() {
        var itembtns = banner.find('div.btnitem');
        if (index == len - 1) {
            index = 0;
        }
        else {
            index++;
        }
        var marginleft = -imgwidth * index;
        container.animate({
            'margin-left': marginleft
        }, 'normal', 'easeInOutCubic');
        itembtns.each(function (i) {
            if (i == index) {
                $(this).addClass('focus');
            }
            else {
                $(this).removeClass('focus');
            }
        });
    };
  //  window.setInterval(loopmove,5000);
    function move() {
        var i = arguments[0];
        oldleft = -imgwidth * i;
        container.animate({
            'margin-left': oldleft
        }, 'normal', 'easeInOutCubic');
    }
    (function createBtns() {
        var width = 8 * len + 6 * (len - 1),
            odiv = document.createElement('div'),
            odivin = document.createElement('div'),
            i = 0, odiv2, items;
        $(odiv).addClass('btns');
        $(odivin).addClass('btnsin');
        $(odiv).append(odivin);
        for (i; i < len; i++) {

            odiv2 = document.createElement('div');
            $(odiv2).addClass('btnitem');
            $(odivin).append(odiv2);
            if (i == 0) {
                $(odiv2).addClass('focus');
            }
        }
        $(odiv).css({
            'left': '50%',
            'margin-left': -parseInt(width / 2),
            'cursor': 'pointer'
        });
        banner.append(odiv);
        items = $(odiv).find('div.btnitem');
        /* items.each(function (z) {
         $(this).click(function () {
         items.each(function (j) {
         if (j == z) {
         $(this).css('background-color', '#535353');
         }
         else {
         $(this).css('background-color', '#cbcbcb');
         }
         });
         move(z);
         });
         });*/
    })();
    function moveto() {

    };
    var mydrag = new KOLO.MobileDrag();
    mydrag.create('div.jsBanner', function () {
        var drager = this.drager,
            startX = this.startX,
            upX = this.upX,
            marginLeft = parseInt(drager.css('margin-left')),
            width = imgs.eq(0).width(),
            maxLeft = -width * (len - 1),
            moveDirection;
        if (startX < upX) {
            moveDirection = 'right';
        }
        else {
            moveDirection = 'left';
        }
        //第一个 禁止右拖拽
        if (index == 0 && moveDirection == 'right') {
            drager.animate({
                'margin-left': 0
            }, 'normal', 'easeInOutCubic');
        }
        //最后一个 禁止左拖拽
        else if (index == len - 1 && moveDirection == 'left') {
            drager.animate({
                'margin-left': maxLeft
            }, 'normal', 'easeInOutCubic');
        }
        //普通状态，可滚动状态
        else {
            //左移
            if (moveDirection == 'left') {
                index++;
                drager.animate({
                    'margin-left': -width * index
                }, 'normal', 'easeInOutCubic');
            }
            //右移
            else {
                index--;
                drager.animate({
                    'margin-left': -width * index
                }, 'normal', 'easeInOutCubic');
            }
            banner.find('.btnitem').each(function (z) {
                if (z == index) {
                    $(this).addClass('focus');
                }
                else {
                    $(this).removeClass('focus');
                }
            });
        }
    })

});
/**
 * Created by apple on 14-10-14.
 */