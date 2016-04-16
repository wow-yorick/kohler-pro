<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>catalog</title>
<link href="${base}/css/main.css" rel="stylesheet" type="text/css" />
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

	});
</script>
<style>
textarea{font-family:微软雅黑;}
</style>
</head>

<body>
	<div class="container queryConditions product_layer">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form action="${base}/catalog/createCatalogSave.action"
				id="addCatalogForm" name="addPageForm">
				<input type="hidden" name="parentId"
					value="${pareCate.categoryMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp</div>
						<div class="col-md-2 tn_c">Level</div>
						<div class="col-md-4">
							<input type="text" name="level" value="${pareCate.level+1 }"
								readonly="readonly" class="disable" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4">
							<input type="text" name="seqNo" maxlength="8" digits="true" />
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
								<c:when test="${language.isDefault == true }">

									<div class="box">
										<input type="hidden" name="langs"
											value="${language.localeId }" />
										<%--循环取出 --%>
										<div class="row">
											<div class="col-md-2 required">Name_${language.localeCode}</div>
											<div class="col-md-4">
												<input type="text" name="categoryNames" required="required"
													maxlength="25" lang="${language.localeId }"
													isCatalogNameUnique="true" />
											</div>

										</div>
										<div class="row">
											<div class="col-md-2 required">PC
												Template_${language.localeCode}</div>
											<div class="col-md-4">
												<select name="pcTemplateIds" required="required">
													<option value="">--select--</option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>

											<div class="col-md-2 tn_c required">Mobile
												Template_${language.localeCode }</div>
											<div class="col-md-4">
												<select name="mobileTemplateIds" required="required">
													<option value="">--select--</option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>

										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (PC
												Website)_${language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitlePcs" maxlength="100" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPcs" maxlength="100" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPcs" maxlength="200" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea  name="seoDescriptionPcs" maxlength="400" ></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile
												Website)_${language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobiles" maxlength="100" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobiles" maxlength="100" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobiles" maxlength="200" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobiles" maxlength="400" ></textarea>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 required">Hero
												Areas_${language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${language.localeId }"
													readonly="readonly" required="required" class="disable" /> <input
													type="hidden" name="heroAreaMetadataIds" id="heroAreas${language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${language.localeId }','heroAreasName${language.localeId }')">Choose</a>
											</div>
										</div>

										<div class="row seo bold">
											<div class="col-md-9 space">Banner Units</div>
										</div>
									
										
									

									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box">
										<div class="box">
											<input type="hidden" name="langs"
												value="${language.localeId }" />
											<div class="row">
												<div class="col-md-2">Name_${language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="categoryNames" maxlength="25"
														lang="${language.localeId }" isCatalogNameUnique="true" />
												</div>

											</div>
											<div class="row">
												<div class="col-md-2">PC
													Template_${language.localeCode }</div>
												<div class="col-md-4">
													<select name="pcTemplateIds">
														<option value="">--select--</option>
														<c:forEach items="${templates}" var="t">
															<option value="${t.templateId}">${t.templateName}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-md-2 tn_c">Mobile
													Template_${language.localeCode }</div>
												<div class="col-md-4">
													<select name="mobileTemplateIds">
														<option value="">--select--</option>
														<c:forEach items="${templates}" var="t">
															<option value="${t.templateId}">${t.templateName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="row seo bold">
												<div class="col-md-9 space">SEO (PC
													Website)_${language.localeCode }</div>
											</div>
											<div class="row">
												<div class="col-md-2">Title_${language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoTitlePcs" maxlength="100" />
												</div>
												<div class="col-md-2 tn_c">H1
													Tag_${language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoH1tagPcs" maxlength="100" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">Keywords_${language.localeCode }</div>
												<div class="col-md-10">
													<input type="text" name="seoKeywordsPcs" maxlength="200" />
												</div>
											</div>
											<div class="row larger">
												<div class="col-md-2">Description_${language.localeCode }</div>
												<div class="col-md-10">
													<textarea  name="seoDescriptionPcs" maxlength="400" ></textarea>
												</div>
											</div>
											<div class="row seo bold">
												<div class="col-md-9 space">SEO (Mobile Website)</div>
											</div>
											<div class="row">
												<div class="col-md-2">Title_${language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoTitleMobiles" maxlength="100" />
												</div>
												<div class="col-md-2 tn_c">H1
													Tag_${language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoH1tagMobiles" maxlength="100" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">Keywords_${language.localeCode }</div>
												<div class="col-md-10">
													<input type="text" name="seoKeywordsMobiles" maxlength="200" />
												</div>
											</div>
											<div class="row larger">
												<div class="col-md-2">Description_${language.localeCode }</div>
												<div class="col-md-10">
													<textarea name="seoDescriptionMobiles" maxlength="400"></textarea>
												</div>
											</div>
											 
											<div class="row">
											<div class="col-md-2 required">Hero
												Areas_${language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${language.localeId }"
													readonly="readonly" required="required" class="disable" /> <input
													type="hidden" name="heroAreaMetadataIds" id="heroAreas${language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${language.localeId }','heroAreasName${language.localeId }')">Choose</a>
											</div>
										</div>
											<div class="row seo bold">
                        <div class="col-md-9 space">Banner Units</div>
                    </div>
                   
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<div class="text">
					<div class="row">
						<div class="col-md-2">Creator</div>
						<div class="col-md-4">${sysUser.realName }</div>
						<div class="col-md-6">Creation Date</div>
					</div>
					<div class="row">
						<div class="col-md-2">Modifier</div>
						<div class="col-md-4">&nbsp</div>
						<div class="col-md-6">Modification Date</div>
					</div>
				</div>
				<div class="btns">
					<a href="javascript:void(0);" class="confirm"
						onclick="addCatalog()">Save</a> <a href="#" class="cancel">Cancel</a>
				</div>
			</form>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
</body>
<script type="text/javascript">
	//判断catalogName是否唯一
	jQuery.validator.addMethod("isCatalogNameUnique", function(value, element) {

		var result = false;
		var langValue = $(element).attr("lang");
		var categoryMetaId = ${pareCate.categoryMetadataId };

		// 设置同步
		$.ajaxSetup({
			async : false
		});
		var param = {
			catalogName : value,
			lang : langValue,
			categoryMetadataId : categoryMetaId
		};
		$.post("unlimited/checkCatalogName.action", param, function(data) {
			data = eval(data);
			var flag = data.flag;
			result = ("0" == flag);
		}, "json");
		// 恢复异步
		$.ajaxSetup({
			async : true
		});
		return result;
	}, "<spring:message code="info.product.category.003" arguments="" argumentSeparator=","/>");

	//关闭frame
	$(function() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('.cancel').on('click', function() {
			parent.layer.close(index); //执行关闭
		});
	});
	
	
	function openDataType(id,elementId,elementName){
		$.layer({
	        type: 2,
	        title: 'Content Picker',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['680px' , '550px'],
			shadeClose: false,
			move: false,
			offset : [($(window).height() - 600)/2+'px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id+'&elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
	
	function addCatalog() {
		$("#errorMessage").html("");
		if ($("#addCatalogForm").valid()) {
			$.post("createCatalogSave.action",
					$("#addCatalogForm").serialize(), function(data) {
						var result = eval(data);
						alert(result.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.location.reload();
						parent.layer.close(index);
					}, "json");
		} else {
			alert($("#errorMessage").html());
		}
	}
</script>
</html>

