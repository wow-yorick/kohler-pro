<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询条件-编辑</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css"
	href="../css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css">
	<script src="../js/layer/jquery-1.11.1.min.js"></script>
	<script src="../js/layer/layer.min.js"></script>
    <script src="../js/laydate/laydate.js"></script>
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
function createMasterDataMeta() {
		var forms = $(".language");
		var len = forms.length;
		//			var collections = '{ "collections":[';
		var masterdataName = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				masterdataName += JSON.stringify(formToJson(form));
			} else {
				masterdataName += JSON.stringify(formToJson(form));
				masterdataName += ",";
			}
		});
		masterdataName += "]";
		$.post("createSave.action",$("#masterForm").serialize()+"&masterdataName="
				+ masterdataName, function(data) {
			var result = eval(data);
			alert(result.msg);
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
	}
	function formToJson(form) {
		var o = {};
		var a = $(form).serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
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
    	<div class="search">
	        <form id="masterForm">
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4">${masterdataMateDeta.masterdataMetadataId}</div>
	            	<input type="hidden" name="masterdataMetadataId" value="${masterdataMateDeta.masterdataMetadataId}"> 
	            </div>
	        	<div class="row">
	            	<div class="col-md-2"><label>Type</label></div>
	            	<div class="col-md-4 input_small">
	                	<select name="masterdataTypeId">
	                		<c:forEach items="${typeList}" var="allType">
	                       		<option value="${allType.templateId}">${allType.templateName}</option>
	                       	</c:forEach>
	                	</select>
	                </div>
	            </div>
	        	<div class="row">
	            	<div class="col-md-2">Code</div>
	            	<div class="col-md-4 select_small"><input type="text" name="masterdataCode"/></div>
	            </div>
	            <div class="row">
	            	<div class="col-md-2">SeqNo</div>
	            	<div class="col-md-4 select_small"><input type="text" name="seqNo"/></div>
	            </div>
	        </form>    
        </div>
        <div class="tab" id="tab">
        	<ul class="tab_menu">
        		<c:forEach items="${allLocale}" var="language" varStatus="status">
        			<c:choose>
						<c:when test="${status.index == 0 }">
							<li class="active tn_c bold">${language.localeName}</li>
						</c:when>
						<c:otherwise>
							<li class="tn_c bold">${language.localeName }</li>
						</c:otherwise>
					</c:choose>
	            </c:forEach>    
            </ul>
            <div class="tab_box">
            	<c:forEach items="${allLocale}" var="language" varStatus="status">
            		<c:choose>
						<c:when test="${status.index == 0 }">
			            	<div class="box">
			            		<form class="language" id="testform">
				                    <div class="row">
				                    	<input type="hidden" value="${language.localeId}">
				                        <div class="col-md-2 tn_l">Value_${language.localeCode}</div>
				                        <div class="col-md-4"  style="position:relative;"><input type="text" name="masterdataName" value="" /></div> 
				                    </div>
				                </form>
			                </div>
	                	</c:when>
	                	<c:otherwise>
			                <div class="hide box">
			                    <div class="box">
			                    <form class="language">
				                    <div class="row">
				                        <input type="hidden" value="${language.localeId}">
				                        <div class="col-md-2 tn_l">Value_${language.localeCode}</div>
				                        <div class="col-md-4"  style="position:relative;"><input type="text" name="masterdataName" value="" /></div> 
				                    </div>
				                </form>
			                </div>
		                </c:otherwise>
		             </c:choose>
	             </c:forEach>
            </div>
        </div>
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator
                        	 </div>
                        	 <div class="col-md-4">
                        		${masterdataMateDeta.createUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		${masterdataMateDeta.createTime}
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Modifier
                        	 </div>
                        	 <div class="col-md-4">
                        		${masterdataMateDeta.modifyUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		${masterdataMateDeta.modifyTime}
                        	 </div>
                        </div>
                    </div>
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm" onclick="createMasterDataMeta();">Save</a>
        	<a href="#" class="cancel" id="closebtn">Cancel</a>
        </div>
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
