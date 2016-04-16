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
    	<form id="sysUserFrom" action="editSysUser.action"  method="post">
    		<input type="hidden" name="sysUserId" value="${user.sysUserId}">
    		<input type="hidden" name="userName" value="${user.userName}">
    		<input type="hidden" name="realName" value="${user.realName}">
    		<input type="hidden" name="isEnable" value="${user.isEnable}">
	    	<div class="search">
	        	<div class="row">
	            	<div class="col-md-3 tn_r">Login Name：</div>
	            	<div class="col-md-4">${user.userName}</div>
	            </div>
	        	<div class="row">
	            	<div class="col-md-3 tn_r">Old Password：</div>
	            	<div class="col-md-4 ">
	                	<input type="password" name="oldPwd" value=""/>
	                </div>
	            </div>
	        	<div class="row">
	            	<div class="col-md-3 tn_r">New Password：</div>
	            	<div class="col-md-4 "><input type="password" name="password" id="passwords" required="required" /></div>
	            </div>
	            <div class="row">
	            	<div class="col-md-3 tn_r">Confirm Password：</div>
	            	<div class="col-md-4 "><input type="password" name="passwordAgain" equalTo="#password" id="passwordAgain" required="required" /></div>
	            </div>
	        </div>     
	      </form>          
        <div class="btns">
        	<a href="javascript:void(0);" onclick="editChangePassword()" class="confirm">Save</a>
        	<a href="#" class="cancel" id="closebtn">Cancel</a>
        </div>
    </div>
    <!--main结束-->
    <script  type="text/javascript">
    /*$("#sysUserFrom").validate({
		rules: {
			password: {
				required: true,
				minlength: 5
			},
			passwordAgain: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},

		}
    });*/
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    	
    	function editChangePassword(){
    		var oldPwd=$("#oldPwd").val();
    		var pass = $("#passwords").val();
    		var againPass=$("#passwordAgain").val();
    		if(oldPwd == ""){
    		       alert('The original password can not be empty');
    		       return;
     		 }else if(pass == ""){
   		       alert('Password can not be empty');
   		       return;
    		 }else if(pass!= againPass){
    			alert("Please make sure that the two input password");
    			return;
    		}else{
    		$.post("editSysUser.action",$("#sysUserFrom").serialize(), function(data) {
                var result = eval(data);
                alert(result.msg);
                if(result.msg == "The original password input error"){
                	return;
                }else if(result.msg == "update Success!"){
	                var index = parent.layer.getFrameIndex(window.name);
	                //parent.location.reload();
	                parent.layer.close(index);
                }
            });
    		}
    		
    	}
    	
    </script>
  
</div></body>
</html>
