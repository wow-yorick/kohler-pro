<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
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


//判断sectionName是否唯一
jQuery.validator.addMethod("isCollectionNameUnique", function(value, element, param) { 
	var pathName = window.document.location.pathname;
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	var collectionId = $("#collectionId").text();
    var result = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
    
    var paramCol = {
    		collectionName: value,
    		localeId:param[0],
    		collectionId:collectionId
	    };
    $.post("unlimited/uniquenessVerification.action", paramCol, function(data){
    	result = data;
    	// 恢复异步
        $.ajaxSetup({
            async: true
        });
    },"json");
    
    return result;
}, "<spring:message code="info.product.collection.001" arguments="" argumentSeparator=","/>"); 
	//关闭frame

	function addCollectionMetadata() {
		$("#errorMessage").html("");
		$("#SwqNoFrom").valid();
		
		var seqNo = $("input[name='seqNo']").val();
		var forms = $(".language");
		var len = forms.length;
		//			var collections = '{ "collections":[';
		var collections = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				collections += JSON.stringify(formToJson(form));
			} else {
				collections += JSON.stringify(formToJson(form));
				collections += ",";
			}
			if (!$("#language"+i).is(":visible")) {
				// form 所属的div让它暂时显示 （为了验证)。
				document.getElementById( "divlan" + i).style.display= "block";
				$("#language"+i).valid();
				// 验证结束后， 把div 重新设定为不可视。
				document.getElementById( "divlan" + i).style.display= "none";
			} else 
			{
				$("#language"+i).valid();
			}
		});
		collections += "]";
		if ($("#errorMessage").html() != "") {
			alert($("#errorMessage").html());
			return false;
		}

		

		
		$.post("createSave.action", "seqNo=" + seqNo + "&collections="
				+ collections, function(data) {
			var result = eval(data);
			alert(result.msg);
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
	}
	
</script>
</head>

<body>
		<div class="container queryConditions">
			<!--shadow开始-->
			<div class="shadow"></div>
			<!--shadow结束-->
			<!--main开始-->
			<div class="main">
			<form id="SwqNoFrom">
			    <input type="text" style="display:none"/>
				<div class="search">
				<div class="row">
						<div class="col-md-1">ID</div>
						<div class="col-md-5" id="collectionId">
							&nbsp;
						</div>
						</div>
					<div class="row">
						<div class="col-md-1 required">SeqNo</div>
						<div class="col-md-5">
							<input type="text" name="seqNo" required="required" maxlength="11" digits="true"/>
						</div>
					</div>
				</div>
				</form>
				<div class="tab" id="tab">
					<ul class="tab_menu">
						<c:forEach items="${languages}" var="language" varStatus="status">
							<c:choose>
								<c:when test="${status.index == 0 }">
									<li class="active tn_c">${language.localeName }</li>
								</c:when>
								<c:otherwise>
									<li class="tn_c">${language.localeName }</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<div class="tab_box">
						<c:forEach items="${languages}" var="language" varStatus="status">
							<c:choose>
								<c:when test="${status.index == 0 }">
									<div class="box"  id="divlan${status.index}">
										<form class="language" id="language${status.index}">
										    <input type="text" style="display:none"/>
											<input type="hidden" name="lang"
												value="${language.localeId }" />
											<div class="row">
												<div class="col-md-2 tn_l required"  id="Name_${status.index}">Name_${language.localeCode }</div>
												<div class="col-md-10" style="position: relative;">
													<input type="text" name="collectionName" id="collectionName${status.index}" isCollectionNameUnique="${language.localeId }"  required="true" maxlength="25" />
												</div>
											</div>
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box" id="divlan${status.index}">
										<form class="language"  id="language${status.index}">
										    <input type="text" style="display:none"/>
											<input type="hidden" name="lang"
												value="${language.localeId }" />
											<div class="box">
												<div class="row">
													<div class="col-md-2 tn_l" id="Name_${status.index}">Name_${language.localeCode }</div>
													<div class="col-md-10" style="position: relative;">
														<input type="text" name="collectionName" id="collectionName${status.index}" isCollectionNameUnique="${language.localeId }" maxlength="25" />
													</div>
												</div>
											</div>
										</form>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>

					</div>
				</div>
				<div class="text" style="float:left;margin-left:110px;margin-top:20px;width:450px;margin-bottom:20px;">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator:
                        	 </div>
                        	 <div class="col-md-4">
                        		&nbsp
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:
                        	 </div>
                        </div>
                    	<div class="row" style="margin-top:20px;">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		&nbsp
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:
                        		
                        	 </div>
                        </div>
                    </div>
				<div class="btns">
					<a href="javascript:void(0);" onclick="addCollectionMetadata()"
						class="confirm">Save</a> <a href="#" class="cancel">Cancel</a>
				</div>
			</div>
			<div id="errorMessage" style="display: none"></div>
			<!--main结束-->
			<script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    		
    </script>
		</div>
</body>
</html>
