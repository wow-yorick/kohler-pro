<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>change password</title>
<jsp:include page="../common/common.jsp" />
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
/*var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
$('.cancel').on('click', function(){
	parent.layer.close(index); //执行关闭
});*/
</script>
</head>

<body>
<div class="container queryConditions product_layer">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
   		<form>
	    	<div class="search">
	        	<div class="row">
	            	<div class="col-md-2">Date</div>
	            	<div class="col-md-4"> ${publishLog.publishTime }</div>
	            </div>
	            <div class="row larger">
	            	<div class="col-md-2">Description</div>
	                <div class="col-md-10">
	                	<textarea rows="4"style="text-align:left" name="remark">${publishLog.remark }</textarea>
	               </div>  
	            </div>
	                           	
	        </div>              
	        <div class="btns">
	        	<a href="javascript:void(0);" class="confirm" onclick="updateManualPublish(${publishLog.publishLogId })">Publish</a>
	        	<a href="#" class="cancel" id="closebtn">Cancel</a>
	        </div>
        </form>
    <!--main结束-->
    <script  type="text/javascript">
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    	
    	
    	
    	
    	function updateManualPublish(publishLogId){
    		//$("#errorMessage").html("");
    		//$("#SwqNoFrom").valid();
    		var remark = $("textarea[name=remark]").val();
    		$.post("unlimited/editSave.action","publishLogId=" + publishLogId
    				+ "&remark=" + remark,
    			function(data) {
    				var result = eval(data);
    				alert(result.msg);
    				var index = parent.layer.getFrameIndex(window.name);
    				//parent.location.reload();
    				parent.layer.close(index);
    			}, "json"
    		);
    		
    	}
    	
    </script>
  
</div></body>
</html>
