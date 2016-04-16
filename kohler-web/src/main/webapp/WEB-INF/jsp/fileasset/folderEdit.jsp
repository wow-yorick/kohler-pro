<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Folder</title>

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
        var isEdit = ${isEdit};
        if(isEdit == 1){
            $("select").attr("disabled","disabled");
            $("input").attr("disabled","disabled");
            $("textarea").attr("disabled","disabled");
        }
});
	
	
</script>
</head>

<body>
<div class="container queryConditions product_layer folder">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
	    <div class="main">
	    	<div class="search">
		    <form id="folderForm">
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4">${folder.folderId}</div>
	                <div class="col-md-2">Parent Folder</div>
	            	<div class="col-md-4 input_small">
	                	<input type="text" value="${folder.folderName}" readonly="readonly" />
	                	<input type="hidden" name="folderId" value="${folder.folderId}" />
	                </div>  
	            </div>        	
	        	<div class="row">
	            	<div class="col-md-2">Folder_CN</div>
	            	<div class="col-md-4 input_small"><input type="text" name="folderName" value="${folder.folderName}"/></div>
	                <div class="col-md-2">Path</div>
	            	<div class="col-md-4 input_small"><input type="text" name="folderPath" value="${folder.folderPath}"/></div>
	            </div>    
	        	<div class="row">
	            	<div class="col-md-2">Type</div>
	            	<div class="col-md-4 select_small">
	                	<select name="fileType">
	                	   <c:forEach items="${allType}" var="at">
	                	          <c:if test="${at.masterdataId == folder.fileType}">
	                	              <option value="${at.masterdataId}" selected = "selected">${at.masterdataName}</option>
	                	          </c:if>
	                	          <c:if test="${at.masterdataId != folder.fileType}">
	                    	          <option value="${at.masterdataId}">${at.masterdataName}</option>
	                    	      </c:if>    
	                   	   </c:forEach>
	                    </select>
	                </div> 
	            </div>        	
            </form>    	
	        </div>         
	        <div class="text">
	            <div class="row">
	                 <div class="col-md-2">
	                    Creator:
	                 </div>
	                 <div class="col-md-4">
	                   ${folder.createUser}
	                 </div>
	                 <div class="col-md-6">
	                    Creation Date :
	                    <fmt:formatDate value="${folder.createTime}" pattern="yyyy-MM-dd HH:mm" />
	                 </div>
	            </div>
	            <div class="row">
	                 <div class="col-md-2">
	                    Modifier:
	                 </div>
	                 <div class="col-md-4">
	                   ${folder.modifyUser}
	                 </div>
	                 <div class="col-md-6">
	                    Modification Date:
	                    <fmt:formatDate value="${folder.modifyTime}" pattern="yyyy-MM-dd HH:mm" />
	                 </div>
	            </div>
	          </div>
	        <div class="btns">
		        <c:if test="${isEdit != 1 }">
		        	<a href="#" class="confirm" onclick="updateFolder();">Save</a>
	        	</c:if>
	        	<a href="#" class="cancel" id="closebtn">Cancel</a>
	        </div>
	    </div>
   
    <!--main结束-->
    <script  type="text/javascript">
		
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	    $('#closebtn').on('click', function(){
	    parent.layer.close(index); //执行关闭
	    });
	    function updateFolder(){
			$.post("editFolderSave.action",$("#folderForm").serialize(), function(data) {
			    var result = eval(data);
			    alert(result.msg);
			    var index = parent.layer.getFrameIndex(window.name);
			    parent.location.reload();
			    parent.layer.close(index);
			}, "json");
		}
    </script>
</div></body>
</html>
