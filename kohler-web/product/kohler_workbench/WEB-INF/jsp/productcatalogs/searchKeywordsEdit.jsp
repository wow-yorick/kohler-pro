<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Keyword</title>
<link rel="stylesheet" type="text/css" href="${base}/css/main.css">
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
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
		$('.confirm').on('click', function() {
			layer.closeAll();
		});
		$(".checkbox input").click(
				function() {
					if ($(this).parent(".checkbox").children("a").hasClass(
							"active")) {
						$(this).parent(".checkbox").children("a").removeClass(
								"active");
						$(this).attr("checked", false);
						if ($(this).hasClass("this_dis"))
							$(".new_togg").css("display", "none");
					} else {
						$(this).parent(".checkbox").children("a").addClass(
								"active");
						$(this).attr("checked", true);
						if ($(this).hasClass("this_dis"))
							$(".new_togg").css("display", "block");
					}
				});
	});
</script>
</head>

<body>
	<div
		class="container queryConditions product_layer partsImages accessories_layerbox">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form id="editSearchKeyForm">
			<input type = "hidden" name = "categoryMetadataId" value = "${categorySearchKeywordMetadata.categoryMetadataId }" />
			<input type = "hidden" name = "categorySearchKeywordMetadataId" value = "${categorySearchKeywordMetadata.categorySearchKeywordMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${categorySearchKeywordMetadata.categorySearchKeywordMetadataId }</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4">
							<input type="text" name="seqNo" value = "${categorySearchKeywordMetadata.seqNo }" />
						</div>
					</div>
				</div>
			</form>
			<div class="tab" id="tab">
				<ul class="tab_menu bold">
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
							<c:when test="${language.isDefault == true }">
								<div class="box">
									<form name="lan${status.index}" id="lan${status.index}">
									<c:forEach items="${searchKeywordList }" var="searchKey">
												<c:if test="${searchKey.lang eq language.localeId}">
										<input type="hidden" name="categorySearchKeywordId"	value="${searchKey.categorySearchKeywordId }" />	
										<input type="hidden" name="lang" value="${searchKey.lang }" />
										<input type="hidden" name="categorySearchKeywordMetadataId" value="${searchKey.categorySearchKeywordMetadataId }" />
										<div class="row">
											<div class="col-md-2 tn_l">Keyword</div>
											<div class="col-md-10">
												<input type="text" name="keyword" value = "${searchKey.keyword }" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">URL</div>
											<div class="col-md-10">
												<input type="text" name="url" value = "${searchKey.url }" />
											</div>
										</div>
											</c:if>
											</c:forEach>
									</form>
								</div>
								</c:when>
								<c:otherwise>
								<div class="hide box">

									<form name="lan${status.index}" id="lan${status.index}">
									<c:forEach items="${searchKeywordList }" var="searchKey">
												<c:if test="${searchKey.lang eq language.localeId}">
										<input type="hidden" name="categorySearchKeywordId"	value="${searchKey.categorySearchKeywordId }" />	
										<input type="hidden" name="lang" value="${searchKey.lang }" />
										<input type="hidden" name="categorySearchKeywordMetadataId" value="${searchKey.categorySearchKeywordMetadataId }" />
									<div class="row">
											<div class="col-md-2 tn_l">Keyword</div>
											<div class="col-md-10">
												<input type="text" name="keyword" value = "${searchKey.keyword }" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">URL</div>
											<div class="col-md-10">
												<input type="text" name="url" value = "${searchKey.url }" />
											</div>
										</div>
										</c:if>
										</c:forEach>
									</form>
								</div>
							</c:otherwise>
								</c:choose>
								</c:forEach>
				</div>
			</div>

			<div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${categorySearchKeywordMetadata.createUser }</div>
					<div class="col-md-7">Creation Date ${categorySearchKeywordMetadata.createTime }</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${categorySearchKeywordMetadata.modifyUser }</div>
					<div class="col-md-7">Modification Date ${categorySearchKeywordMetadata.modifyTime }</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm" onclick="editSearchKey()">Save</a> 
				<a href="#" class="cancel"
					id="closebtn">Cancel</a>
			</div>
		</div>
		<!--main结束-->
	</div>
	<script type="text/javascript">
		//关闭frame
		$(function() {
			var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('.cancel').on('click', function() {
				parent.layer.close(index); //执行关闭
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
		function editSearchKey() {
			var forms = $(".tab_box form");
			var len = forms.length;
			var searchKeys = '[';
			$.each(forms, function(i, form) {
				if (i == len - 1) {
					searchKeys += JSON.stringify(formToJson(form));
				} else {
					searchKeys += JSON.stringify(formToJson(form));
					searchKeys += ",";
				}
			});
			searchKeys += "]";
			$
					.post(
							"editSearchKey.action",
							$("#editSearchKeyForm").serialize() + "&searchKeys="
									+ searchKeys,
							function(data) {
								var result = eval(data);
								alert(result.msg);

								var editHtml = "";
								if (result.isSuccess == 1) {

									editHtml = "<td class='tn_c'><span class='edit pdf_new' onclick='editSearchKeyPage("
											+ result.searchKey.categorySearchKeywordMetadataId
											+ ")'></span>"
											+ "<span class='del' onclick='delSearchKey(this,"
											+ result.searchKey.categorySearchKeywordMetadataId
											+ ")'></span></td>"
											+ "<td>"
											+ result.searchKey.keyword
											+ "</td>"
											+ "<td class='last'>"
											+ result.searchKey.url
											+ "</td>"
											;
									$('#'+result.searchKey.categorySearchKeywordMetadataId, parent.document).html(editHtml);
								}
								
								var index = parent.layer
										.getFrameIndex(window.name);
								//parent.location.reload();
								parent.layer.close(index);

							}, "json");
		}
	</script>
	
</body>
</html>


