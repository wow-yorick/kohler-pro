<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title></title>
	<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
$(function(){
	var isEdit = ${isEdit};
	if(isEdit == 1){
		$("select").attr("disabled","disabled");
		$("input").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
	}
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
  //创建方法 
	$(".confirm").click(function(){
		$.post("editSave.action", $("#spellingForm").serialize(),function(data) {
			var result = eval(data);
			if(result.msg == 'cf'){
				alert("value已存在，请重新输入！");
				return;
			}
			alert(result.msg);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
	});
});
//创建方法 
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
	            	<div class="col-md-4"><input type="text" value="${spelling.settingValue}" name="settingValue"/></div>
	            	<input type="hidden" value="${spelling.searchSynonymId}" name="searchSynonymId"/>
	            </div>        	        	
	        </div>                 
	        <div class="btns">
	        	<c:if test="${isEdit != 1 }">
		        	<a href="#" class="confirm" onclick="editSave();">Save</a>
	        	</c:if>
	        	<a href="#" class="cancel" id="closebtn">Cancel</a>
	        </div>
	     </form>
    </div>
    <!--main结束-->
    <script  type="text/javascript">
	  	function editSave(){
	  		$.post("editSave.action", $("#spellingForm").serialize(),function(data) {
				var result = eval(data);
				if(result.msg == 'cf'){
					alert("value已存在，请重新输入！");
					return;
				}
				alert(result.msg);
				parent.location.reload();
				parent.layer.close(index);
			}, "json");
	  	}
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    </script>
</div></body>
</html>
