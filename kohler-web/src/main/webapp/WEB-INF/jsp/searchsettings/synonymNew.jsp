<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>spellingC
	<title>adminTemplates</title>
	<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/chinaweb.js"></script>
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
//åå»ºæ¹æ³ 
function createSeplling(){
	var setValue = $("#settingValue").val();
	if(setValue == null || setValue == ''){
		alert('Name不能为空!');
		return;
	}
	$.post("createSave.action", $("#phraseForm").serialize(),function(data) {
		var result = eval(data);
		if(result.msg == 'cf'){
			alert("value已存在，请重新输入！");
			return;
		}
		alert(result.msg);
		parent.location.reload();
		parent.layer.close(index);
		location.reload();
	}, "json");
}
</script>
</head>

<body>
<div class="container queryConditions spellingCheck">

    <!--shadowå¼å§-->
    <div class="shadow">
    </div>
    <!--shadowç»æ-->
    <!--mainå¼å§-->
    <div class="main">
    	<form id="phraseForm">
	    	<div class="search">        	   	
	        	<div class="row">
	            	<div class="col-md-2">Name</div>
            		<div class="col-md-4"><input type="text"  id="settingValue" name="settingValue"/></div>
	            </div>        	        	
	        </div>                 
	        <div class="btns">
	        	<a href="#" class="confirm" onclick="createSeplling();">Save</a>
	        	<a href="#" class="cancel" id="closebtn">Cancel</a>
	        </div>
	     </form>
    </div>
    <!--mainç»æ-->
    <script  type="text/javascript">
	  
    	var index = parent.layer.getFrameIndex(window.name); //è·åå½åçªä½ç´¢å¼
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //æ§è¡å³é­
		});
    </script>
</div></body>
</html>
