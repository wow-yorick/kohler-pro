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
			<form id="addAccessoryForm">
				<input type="hidden" name="categoryMetadataId"
					value="${categoryMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp</div>
						<div class="col-md-2 required">key name</div>
						<div class="col-md-4">
							<input type="text" name="keyName"  required="required"
													maxlength="25"  />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4">
							<input type="text" name="seqNo" digits="true" />
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
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-2 tn_l">Name</div>
											<div class="col-md-10">
												<input type="text" name = "categoryAccessoryName" maxlength="25" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">Type</div>
											<div class="col-md-10">
												<select name = "categoryAccessoryType">
													<c:forEach items="${typeList}" var="allType">
	                       								<option value="${allType.masterdataId}">${allType.masterdataName}</option>
	                       							</c:forEach>
												</select>
											</div>
										</div>
									</form>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box">

									<form name="lan${status.index}" id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-2 tn_l">Name</div>
											<div class="col-md-10">
												<input type="text" name = "categoryAccessoryName" maxlength="25" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">Type</div>
											<div class="col-md-10">
												<select name = "categoryAccessoryType">
													<c:forEach items="${typeList}" var="allType">
	                       								<option value="${allType.masterdataId}">${allType.masterdataName}</option>
	                       							</c:forEach>
												</select>
											</div>
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
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">王明</div>
					<div class="col-md-7">Creation Date2014-07-01 08:00</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">王明</div>
					<div class="col-md-7">Modification Date2014-07-01 08:00</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm"
					onclick="addAccessory()">Save</a> <a href="#" class="cancel"
					id="closebtn">Cancel</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
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
		function addAccessory() {
			var forms = $(".tab_box form");
			var len = forms.length;
			var accessorys = '[';
			$.each(forms, function(i, form) {
				if (i == len - 1) {
					accessorys += JSON.stringify(formToJson(form));
				} else {
					accessorys += JSON.stringify(formToJson(form));
					accessorys += ",";
				}
			});
			accessorys += "]";
			$("#errorMessage").html("");
			if ($("#addAccessoryForm").valid()) {
			$
					.post(
							"saveAccessory.action",
							$("#addAccessoryForm").serialize() + "&accessorys="
									+ accessorys,
							function(data) {
								var result = eval(data);
								alert(result.msg);
								
								
								var addHtml = "";
								if (result.isSuccess == 1) {

									addHtml = "<tr id = '"+result.accessory.categoryAccessoryMetadataId+"'>"
											+ "<td class='tn_c'><span class='edit pdf_new' onclick='editAccessoryPage("
											+ result.accessory.categoryAccessoryMetadataId
											+ ")'></span>"
											+ "<span class='del' onclick='delAccessory(this,"
											+ result.accessory.categoryAccessoryMetadataId
											+ ")'></span></td>"
											+ "<td>"
											+ result.accessory.categoryAccessoryName
											+ "</td>"
											+ "<td class='last'>"
											+ result.accessory.masterdataName
											+ "</td>"
											+ "</tr>";
								}
								$('#accessoryTable', parent.document).append(
										addHtml);
								var index = parent.layer
										.getFrameIndex(window.name);
								//parent.location.reload();
								parent.layer.close(index);

							}, "json");
			} else {
				alert($("#errorMessage").html());
			}
		}
	</script>

</body>
</html>


