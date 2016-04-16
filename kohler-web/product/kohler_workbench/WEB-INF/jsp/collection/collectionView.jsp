<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$("input").attr("disabled", "disabled");
	});
	$(function() {
		var $tab_li = $('#tab ul li');
		$tab_li.click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			var index = $tab_li.index(this);
			$('div.tab_box > .box').eq(index).show().siblings().hide();
		});
		$("#txt span").click(function() {
			var boxClass = $(this).attr("class");
			if (boxClass == "check") {
				$(this).attr("class", "checked");
			} else {
				$(this).attr("class", "check");
			}
		});
	});
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

	function editCollectionMetadata() {
		alert("edit");
		var seqNo = $("input[name='seqNo']").val();
		var collectionMetadataId = $("input[name='collectionMetadataId']")
				.val();
		var forms = $(".language");
		var len = forms.length;
		//		var collections = '{ "collections":[';
		var collections = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				collections += JSON.stringify(formToJson(form));
			} else {
				collections += JSON.stringify(formToJson(form));
				collections += ",";
			}
		});
		collections += "]";
		//collections += "]}";
		alert("seqNo=" + seqNo + "&collections=" + collections
				+ "&collectionMetadataId=" + collectionMetadataId);
		$.post("editCollection.action",
				"seqNo=" + seqNo + "&collections=" + collections
						+ "&collectionMetadataId=" + collectionMetadataId,
				function(data) {
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
				<div class="search">
					<div class="row">
						<div class="col-md-1">ID</div>
						<div class="col-md-5">
							${collectionMetadata.collectionMetadataId }
						</div>
						</div>
						<div class="row">
						<div class="col-md-1">SeqNo</div>
						<div class="col-md-5">
							<input type="text" name="seqNo"
								value="${collectionMetadata.seqNo }" />
						</div>
					</div>
				</div>
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
									<div class="box">
										<form class="language">
											<input type="hidden" name="lang"
												value="${language.localeId }" />
											<div class="row">
												<div class="col-md-2 tn_l">Name_${language.localeCode }</div>
												<c:forEach items="${collections}" var="collection">
													<c:if test="${language.localeId eq collection.lang}">
														<input type="hidden" name="collectionId"
															value="${collection.collectionId }" />
														<div class="col-md-10" style="position: relative;">
															<input type="text" name="collectionName"
																value="${collection.collectionName }" />
														</div>
													</c:if>
												</c:forEach>
											</div>
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box">
										<form class="language">
											<input type="hidden" name="lang"
												value="${language.localeId }" />
											<div class="box">
												<div class="row">
													<div class="col-md-2 tn_l">Name_${language.localeCode }</div>
													<c:forEach items="${collections}" var="collection">
														<c:if test="${language.localeId eq collection.lang}">
															<input type="hidden" name="collectionId"
																value="${collection.collectionId }" />
															<div class="col-md-10" style="position: relative;">
																<input type="text" name="collectionName"
																	value="${collection.collectionName }" />
															</div>
														</c:if>
													</c:forEach>
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
                        		${collectionMetadata.createUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date:<fmt:formatDate value="${collectionMetadata.createTime}"
									pattern="yyyy-MM-dd HH:mm" />
                        	 </div>
                        </div>
                    	<div class="row" style="margin-top:20px;">
                        	 <div class="col-md-2">
                        		Modifier:
                        	 </div>
                        	 <div class="col-md-4">
                        		${collectionMetadata.modifyUser}
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date:<fmt:formatDate value="${collectionMetadata.modifyTime}"
									pattern="yyyy-MM-dd HH:mm" />
                        		
                        	 </div>
                        </div>
                    </div>
				<div class="btns">
        	<a href="#" class="cancel">Cancel</a>
				</div>
			</div>
			<!--main结束-->
			<script  type="text/javascript">
    		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    		
    </script>
		</div>
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>
</html>
