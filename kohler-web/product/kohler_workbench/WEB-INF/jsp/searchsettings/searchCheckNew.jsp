<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>spellingC
	<title>adminTemplates</title>
	<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
$(function(){
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
//创建方法 
function createSeplling(){
	$.post("createSave.action", $("#spellingForm").serialize(),function(data) {
		var result = eval(data);
		alert(result.msg);
		parent.layer.close(index);
		location.reload();
	}, "json");
}
</script>
</head>

<body>
<div class="container queryConditions spellingCheck">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<form id="spellingForm">
	    	<div class="search">        	   	
	        	<div class="row">
	            	<div class="col-md-2">Name</div>
	            	<div class="col-md-4"><input type="text"  name="settingValue"/></div>
	            </div>        	        	
	        </div>                 
	        <div class="btns">
	        	<a href="#" class="confirm" onclick="createSeplling();">Save</a>
	        	<a href="#" class="cancel" id="closebtn">Cancel</a>
	        </div>
	     </form>
    </div>
    <!--main结束-->
    <script  type="text/javascript">
	  
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    </script>
</div></body>
</html>
