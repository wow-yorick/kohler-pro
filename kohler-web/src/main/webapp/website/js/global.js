/**
 * @author Administrator
 */
var userAgent = navigator.userAgent.toLowerCase();
jQuery.easing['jswing'] = jQuery.easing['swing'];
jQuery.extend( jQuery.easing,
{
	def: 'easeOutQuad',
	swing: function (x, t, b, c, d) {
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeInQuad: function (x, t, b, c, d) {
		return c*(t/=d)*t + b;
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	},
	easeInOutQuad: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	},
	easeInCubic: function (x, t, b, c, d) {
		return c*(t/=d)*t*t + b;
	},
	easeOutCubic: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t + 1) + b;
	},
	easeInOutCubic: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t + b;
		return c/2*((t-=2)*t*t + 2) + b;
	},
	easeInQuart: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t + b;
	},
	easeOutQuart: function (x, t, b, c, d) {
		return -c * ((t=t/d-1)*t*t*t - 1) + b;
	},
	easeInOutQuart: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
		return -c/2 * ((t-=2)*t*t*t - 2) + b;
	},
	easeInQuint: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t*t + b;
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	},
	easeInOutQuint: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
		return c/2*((t-=2)*t*t*t*t + 2) + b;
	},
	easeInSine: function (x, t, b, c, d) {
		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	},
	easeOutSine: function (x, t, b, c, d) {
		return c * Math.sin(t/d * (Math.PI/2)) + b;
	},
	easeInOutSine: function (x, t, b, c, d) {
		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	},
	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	},
	easeOutExpo: function (x, t, b, c, d) {
		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	},
	easeInOutExpo: function (x, t, b, c, d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	},
	easeInCirc: function (x, t, b, c, d) {
		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	},
	easeOutCirc: function (x, t, b, c, d) {
		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	},
	easeInOutCirc: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	},
	easeInElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	},
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},
	easeInOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	},
	easeInBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	},
	easeOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	},
	easeInOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158; 
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	},
	easeInBounce: function (x, t, b, c, d) {
		return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
	},
	easeOutBounce: function (x, t, b, c, d) {
		if ((t/=d) < (1/2.75)) {
			return c*(7.5625*t*t) + b;
		} else if (t < (2/2.75)) {
			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
		} else if (t < (2.5/2.75)) {
			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
		} else {
			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
		}
	},
	easeInOutBounce: function (x, t, b, c, d) {
		if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
		return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
	}
});
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
	 * @explain 判断是否为空,包括空数组,NULL,UNDEFINED等值
	 * @explain 类似仅仅是类似PHP中的 empty(),注意区分is_set()
	 * @param {object} value
	 * @return boolean
	 */
	isEmpty : function(value)
	{
		return (value === null) || (value === undefined) || ((core.isArray(value) && !value.length));
	},
	/**
	 * JQ装箱操作
	 * 1 如果elems = 数组 或者他是element DOM元素，则直接调用$()装箱，返回JQUERY对象
	 * 2 如果elems instanceof JQ对象，则直接返回elems本身
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
	 * @param {string} arguments[0] 需要创建的全局对象的名称
	 * @param {string} arguments[1-...]需要添加的对象
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
$.extend(jQuery,{
	browser : {
		version: (userAgent.match( /.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/ ) || [])[1],
		safari: /webkit/.test(userAgent ),
		opera: /opera/.test(userAgent ),
		msie: /msie/.test(userAgent ) && !/opera/.test(userAgent ),
		mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent ),
		chrome : userAgent.indexOf('chrome')>=0,
		ie  : userAgent.indexOf('msie')>=0,
		ie6 : userAgent.indexOf('msie 6.0')>=0,
		ie7 : userAgent.indexOf('msie 7.0')>=0,
		ie8 : userAgent.indexOf('msie 8.0')>=0,
		ie9 : userAgent.indexOf('msie 9.0')>=0
	},
	//获取浏览器的大小
	getWinSize : function()
	{
		var width,height;
		if(document.all)
		{
			width = document.documentElement.clientWidth;
	 		height = document.documentElement.clientHeight;						
		}
		else
		{
			width = window.innerWidth;
			height = window.innerHeight;	
		}
		return {
			'x' : parseInt(width),
			'y' : parseInt(height)
		}	
	},
	getWebSize : function()
	{
		var width,height;
		width = document.body.clientWidth ;
		height = document.body.clientHeight  ;
		return {
			'x' : parseInt(width),
			'y' : parseInt(height)
		}
	},
	getScrollSize : function()
	{
		var width,height;
		if(document.all)
		{
			width = document.documentElement.clientWidth;
	 		height = document.documentElement.clientHeight;						
		}
		else
		{
			width = window.innerWidth;
			height = window.innerHeight;	
		}
		scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
		return {
			'x' : parseInt(width),
			'y' : parseInt(height)+scrollTop*2
		}	
	},
	getRealSize : function()
	{
		var width,height;
		if(document.all)
		{
			width = document.documentElement.clientWidth;
	 		height = document.documentElement.clientHeight;					
		}
		else
		{
			width = window.innerWidth;
			height = window.innerHeight;	
		}
		return {
			'x' : parseInt(width),
			'y' : parseInt(document.body.scrollHeight)
		}	
	},
	getMousePosition : function(e)
	{
		var posX,posY;
		if(e.pageX || e.pageY)
		{
			posX = e.pageX;
			posY = e.pageY;
		}
		else
		{
			posX = e.clientX + document.documentElement.scrollLeft;
			posY = e.clientY + document.documentElement.scrollTop;
		}
		return {
			'x' : posX,
			'y' : posY
		}
	}
});
