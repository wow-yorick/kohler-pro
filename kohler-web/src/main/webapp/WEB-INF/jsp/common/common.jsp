<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/validate-methods.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/verify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/front-add.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/global.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/page-util.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".shadow").hover(function() {
			$(this).children("a.me_active").addClass("active");
			$(this).children("ul").css("display", "block");
		}, function() {
			$(this).children("a.me_active").removeClass("active");
			$(this).children("ul").css("display", "none");
		});
		$(".bar a").click(function() {
			$(".bar a").removeClass("active");
			$(this).addClass("active");
			if ($(this).siblings("ul").hasClass("active")) {
				$(this).siblings("ul").css("display", "none");
				$(this).siblings("ul").removeClass("active");
			} else {
				$(this).siblings("ul").css("display", "block");
				$(this).siblings("ul").addClass("active");
			}
		});
		$(".activation").click(function() {
			if ($(this).hasClass("active"))
				$(this).removeClass("active");
			else {
				$(this).addClass("active");
			}
		});
		$(".table tbody tr:odd").addClass("tr_bg");
		
		var $tab_li = $('#tab ul li');
		$tab_li.click(function(){
			$(this).addClass('active').siblings().removeClass('active');
			var index = $tab_li.index(this);
			$('div.tab_box > .box').eq(index).show().siblings().hide();
		});	
		$("#txt span").click(function(){
			var boxClass=$(this).attr("class");
			if(boxClass=="check"){
				$(this).attr("class","checked");
				}else{
				$(this).attr("class","check");}
			});	
		$('.confirm').on('click', function(){
			layer.closeAll()
		});
	    $(".checkbox input").click(function(){
	        if($(this).parent(".checkbox").children("a").hasClass("active")){
	            $(this).parent(".checkbox").children("a").removeClass("active");
	            $(this).attr("checked",false);
	            if($(this).hasClass("this_dis"))
	            	$(".new_togg").css("display","none");
	        }
	        else{
	            $(this).parent(".checkbox").children("a").addClass("active");
	            $(this).attr("checked",true);
	            if($(this).hasClass("this_dis"))
	            	$(".new_togg").css("display","block");
	        }
	    }); 
	});
</script>