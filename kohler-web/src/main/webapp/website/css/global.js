
/**
 * @author Administrator
 */
Date.prototype.ParseLocal = function() {
    return this.getFullYear() + '-' + (this.getMonth() + 1) + '-' + this.getDate() + ' '
        + (this.getHours()).toString().replace(/^(\d)$/, '0$1') + ':' + (this.getMinutes()).toString().replace(/^(\d)$/, '0$1');
}
var GLOBALSIZE;
var objectPrototype = Object.prototype;
$.extend($,{
    isArray : function(value)
    {
        return objectPrototype.toString.apply(value) === '[object Array]';
    },
    isString : function(value)
    {
        return typeof value === 'string';
    },
    /**
     */
    isEmpty : function(value)
    {
        return (value === null) || (value === undefined) || ((core.isArray(value) && !value.length));
    },
    isFunction: function(value) {
        return objectPrototype.toString.apply(value) === '[object Function]';
    },
    isObject: function(value) {
        return !!value && !value.tagName && objectPrototype.toString.call(value) === '[object Object]';
    },
    /**
     */
    packing : function(elems)
    {
        if(elems instanceof $)
        {
            return elems;
        }
        else if($.isArray(elems) || elems.nodeType)
        {
            return $(elems);
        }
        else if($.isString(elems))
        {
            if(elems.indexOf('#')>=0 || elems.indexOf('.')>0)
            {
                return $(elems);
            }
            else
            {
                return $('#'+elems);
            }
        }
        else
        {
            return $([]);
        }
    },
    /**
     */
    nameSpace : function()
    {
        var a = arguments,
            o = null,
            globalObj,
            i = 1,
            j,
            d,
            arg;
        if(window[arguments[0]])
        {
            globalObj = window[arguments[0]];
        }
        else
        {
            window[arguments[0]] = {};
        }
        for(;i<a.length;i++)
        {
            o   = window[arguments[0]];
            arg = arguments[i];
            if(arg.indexOf('.'))
            {
                d = arg.split('.');
                for(j=0;j<d.length;j++)
                {
                    o[d[j]] = o[d[j]] || {};
                    o       = o[d[j]];
                }
            }
            else
            {
                o[arg] = o[arg] || {};
            }
        }
        return;
    }
});
$.extend($, {
    setSessionCache: function() {
        var arglen = arguments.length;
        if (arglen == 2)
        {
            sessionStorage.setItem(arguments[0], arguments[1]);
        }
        else if (arglen == 1)
        {
            if ($.isObject(arguments[0]))
            {
                for (var i in arguments[0])
                {
                    sessionStorage.setItem(i, arguments[0][i]);
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    },
    setLocalCache: function() {
        var arglen = arguments.length;
        if (arglen == 2)
        {
            localStorage.setItem(arguments[0], arguments[1]);
        }
        else if (arglen == 1)
        {
            if ($.isObject(arguments[0]))
            {
                for (var i in arguments[0])
                {
                    localStorage.setItem(i, arguments[0][i]);
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    },
    getSessionCache: function(key) {
        if ($.isString(key)) {
            return sessionStorage.getItem(key);
        }
    },
    getLocalCache: function(key) {
        if ($.isString(key)) {
            return localStorage.getItem(key);
        }
    },
    deleteSessionCache: function(key) {
        if ($.isString(key)) {
            $(key.split(',')).each(function(){
                sessionStorage.removeItem(this+'');
            });
        }
    },
    clearSessionCache: function() {
        sessionStorage.clear();
    },
    appendEmByWidth: function() {
        var screenW = document.body.clientWidth,
            style = document.createElement('style'),
            size = (screenW / 720) * 24,
            styles = 'body{font-size:' + size + 'px !important;}';
        GLOBALSIZE = size;
        (document.getElementsByTagName("head")[0] || document.body).appendChild(style);
        if (style.styleSheet) {
            style.styleSheet.cssText = styles;
        }
        else {
            style.appendChild(document.createTextNode(styles));
        }
    },

    getPageSize: function() {
        var xScroll, yScroll;
        if (window.innerHeight && window.scrollMaxY) {
            xScroll = window.innerWidth + window.scrollMaxX;
            yScroll = window.innerHeight + window.scrollMaxY;
        } else {
            if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
                xScroll = document.body.scrollWidth;
                yScroll = document.body.scrollHeight;
            } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
                xScroll = document.body.offsetWidth;
                yScroll = document.body.offsetHeight;
            }
        }
        var windowWidth, windowHeight;
        if (self.innerHeight) { // all except Explorer
            if (document.documentElement.clientWidth) {
                windowWidth = document.documentElement.clientWidth;
            } else {
                windowWidth = self.innerWidth;
            }
            windowHeight = self.innerHeight;
        } else {
            if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
                windowWidth = document.documentElement.clientWidth;
                windowHeight = document.documentElement.clientHeight;
            } else {
                if (document.body) { // other Explorers
                    windowWidth = document.body.clientWidth;
                    windowHeight = document.body.clientHeight;
                }
            }
        }
        // for small pages with total height less then height of the viewport
        if (yScroll < windowHeight) {
            pageHeight = windowHeight;
        } else {
            pageHeight = yScroll;
        }
        // for small pages with total width less then width of the viewport
        if (xScroll < windowWidth) {
            pageWidth = xScroll;
        } else {
            pageWidth = windowWidth;
        }
        return {
            'pageWidth': pageWidth,
            'pageHeight': pageHeight,
            'windowWidth': windowWidth,
            'windowHeight': windowHeight
        }
    },
    //全局AJAX LOADING遮罩页面
    loadingStart: function() {
        var mypage = $.getPageSize();
     /*   var outheight = $(document.body).outerHeight(true);
        if (mypage.pageHeight < outheight) {
            mypage.pageHeight = outheight;
        }
        //mypage.pageHeight = ; */
        if (document.getElementById('jsLoadingStart') == null) {
            var odiv = document.createElement('div'),
                odiv2 = document.createElement('div'),
                odiv3 = document.createElement('div'),
                body = document.getElementsByTagName('body')[0],
                oimg = document.createElement('img');
            oimg.src = 'images/loading9.gif';
            odiv.id = 'jsLoadingStart';
            $(odiv).css({
                'position': 'absolute',
                'left': '0px',
                'top': '0px',
                'z-index': '999999',
                'width': mypage.pageWidth,
                'height': mypage.pageHeight
            });
            $(oimg).css({
                'position': 'fixed',
                'width': '2em',
                'height': '2em',
                'left': '50%',
                'margin-left': '-1em',
                'top': '50%',
                'margin-top': '-1em'
            });
            $(odiv3).css({
                'position': 'absolute',
                'z-index': '2',
                'left': '0px',
                'top': '0px',
                'width': '100%',
                'height': '100%'
            });
            $(odiv2).addClass('jsmask');
            $(body).append(odiv);
            $(odiv).append(odiv2);
            $(odiv).append(odiv3);
            $(odiv3).append(oimg);

        }
        else {
            $('#jsLoadingStart').css({
                'width': mypage.pageWidth,
                'height': mypage.pageHeight
            });
            $('#jsLoadingStart').css('display', 'block');
        }

    },
    //关闭LOADING遮罩
    loadingEnd: function() {
        $('#jsLoadingStart').css('display', 'none');
    },
    easyErrorBox: function(str, callback) {
        var mypage = $.getPageSize();
        if (document.getElementById('jserrorbox') == null) {
            var odiv = document.createElement('div'),
                body = document.getElementsByTagName('body')[0],
                html = "<div class='errormask'></div>" +
                    "<div class='errorboxbg'></div>";
            $(body).append(odiv);
            odiv.id = 'jserrorbox';
            $(odiv).addClass('jsmsgbox');
            $(odiv).append(html);
            $('#jserrorbox').css('height', mypage.pageHeight);
        }
        $('#jserrorbox').css('height', mypage.pageHeight);
        $('#jserrorbox').css('display', 'block');
        $('#jserrorbox').find('div.mask').css({
            'width': mypage.pageWidth,
            'height': mypage.pageHeight
        });
        $('#jserrorbox').find('div.errorboxbg').text(str);
        window.setTimeout(function() {
            $('#jserrorbox').css('display', 'none');
            if ($.isFunction(callback)) {
                callback();
            }
        }, 2000)
    },
    getUrl:function(api,options)
    {
        var url = BASEURL+api+'?';
        url = url + 'deviceNumber='+ $.getSessionCache('deviceNumber')+'&';
        for(var i in options)
        {
            url = url + i +'=' + options[i]+'&';
        }
        return url.substring(0,url.length-1);
    },
    getStrLen : function(str)
    {
        return str.replace(/[^\x00-\xff]/g,"**").length;
    },
    clearTemp:function()
    {
        $.deleteSessionCache('tempContent');
        $.deleteSessionCache('tempTime');
        $.deleteSessionCache('tempTitle');
    },
    getUrlData: function() {
        var url = window.location.href,
            urlstr = url.split('?')[1];
        if (urlstr) {
            var urlarr = urlstr.split('&'),
                data = {};
            $(urlarr).each(function() {
                var arr = (this + '').split('=');
                data[arr[0]] = arr[1];
            });
            return data;
        }
        else {
            return false;
        }
    }


});
$(document).ready(function() {

    $.appendEmByWidth();

});

