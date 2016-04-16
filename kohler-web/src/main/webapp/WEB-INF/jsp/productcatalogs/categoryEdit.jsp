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
<title>Category</title>
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
			<form action="${base}/category/editCategorySave.action"
				id="editCategoryForm" name="addPageForm">
				<input type="hidden" name="categoryMetadataId"
					value="${parentCategory.categoryMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${parentCategory.categoryMetadataId }</div>
						<div class="col-md-2 tn_c">Level</div>
						<div class="col-md-4">
							<input type="text" name="level" value="${parentCategory.level }"
								readonly="readonly" class="disable" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">Belong to</div>
						<div class="col-md-4 ">
							<input type="text" value="${parentCatalog.categoryName}"
								readonly="readonly" class="disable" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4 ">
							<input name="seqNo" value="${parentCategory.seqNo }" type="text"
								maxlength="8" digits="true" />
						</div>
					</div>
				</div>
				<div class="tab" id="tab">
					<ul class="tab_menu">
						<c:forEach items="${categoryPojos}" var="catePojo"
							varStatus="status">
							<c:choose>
								<c:when test="${catePojo.language.isDefault == true }">
									<li class="active tn_c">${catePojo.language.localeName }</li>
								</c:when>
								<c:otherwise>
									<li class="tn_c">${catePojo.language.localeName }</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
					<div class="tab_box">
						<c:forEach items="${categoryPojos}" var="catePojo"
							varStatus="status">
							<c:choose>
								<c:when test="${catePojo.language.isDefault == true  }">

									<div class="box">
										<input type="hidden" name="categoryIds"
											value="${catePojo.category.categoryId }" />
										<%--循环取出此处不需要语言就换成id进行修改 --%>
										<div class="row">
											<div class="col-md-2 required">Name_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="categoryNames"
													value="${catePojo.category.categoryName }"
													required="required" maxlength="25"
													categoryId="${catePojo.category.categoryId }"
													isCategoryNameUnique="true"
													lang="${catePojo.category.lang }" />
											</div>
											<div class="col-md-2 tn_c required">Breadcrumb Name_${catePojo.language.localeCode }</div>
                        					<div class="col-md-4">
                        						<input type="text" name = "breadcrumbNames" value = "${catePojo.category.breadcrumbName }" required="required"
													maxlength="25" />
                       						</div> 
										</div>
										<div class="row">
											<div class="col-md-2 required">PC
												Template_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<select name="pcTemplateIds" required="required"
													value="${catePojo.category.pcTemplateId}">
													<option value="">--select--</option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="col-md-2 tn_c required">Mobile
												Template_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<select name="mobileTemplateIds" required="required"
													value="${catePojo.category.mobileTemplateId }">
													<option value="">--select--</option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (PC
												Website)_${catePojo.language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitlePcs"
													value="${catePojo.category.seoTitlePc }" maxlength="100" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPcs"
													value="${catePojo.category.seoH1tagPc }" maxlength="100" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPcs"
													value="${catePojo.category.seoKeywordsPc }" maxlength="200" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${catePojo.language.localeCode }</div>
											<div class="col-md-10">
												<textarea rows="2" name="seoDescriptionPcs" maxlength="400">${catePojo.category.seoDescriptionPc }</textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile
												Website)_${catePojo.language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobiles"
													value="${catePojo.category.seoTitleMobile }" />
											</div>
											<div class="col-md-2 tn_c">H1
												Tag_${catePojo.language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobiles"
													value="${catePojo.category.seoH1tagMobile }" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobiles"
													value="${catePojo.category.seoKeywordsMobile }" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${catePojo.language.localeCode }</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobiles">${catePojo.category.seoDescriptionMobile }</textarea>
											</div>
										</div>
										  <div class="row">
		                    				<input type="hidden" name="categoryHeroAreaIds" value="${catePojo.heroArea.categoryHeroAreaId }" />
											<div class="col-md-2 required">Hero
												Areas_${catePojo.language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${catePojo.language.localeId }" value="${catePojo.fieldValue }"
													readonly="readonly" class="disable" required="required" /> <input
													type="hidden" name="heroAreaMetadataIds" value="${catePojo.fieldValueId }" id="heroAreas${catePojo.language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${catePojo.language.localeId }','heroAreasName${catePojo.language.localeId }')">Choose</a>
											</div>
										</div>
										
										<table  class="big marginTop" border="0" cellspacing="0"
											cellpadding="0">
											<tr class="head">
												<th class="head_l">Banner Units</th>
												<th class="head_r"><a href="javascript:void(0);" class="bannerUnit_new" onclick = "newBannerUnit(${catePojo.category.lang })" >New</a></th>
											</tr>
											<tr class="inside_border">
												<td colspan="2">
													<table id = "bannerTable${catePojo.category.lang }" class="small" border="0" cellspacing="0"
														cellpadding="0">
														<tr class="heading">
															<th style="width:15%">Action</th>
															<th style="width:25%">Name</th>
															<th style="width:60%">Position</th>
														</tr>
														<c:import 
													url="/category/unlimited/getBannerUnitList.action?categoryMetadataId=${parentCategory.categoryMetadataId }&lang=${catePojo.category.lang }"></c:import>
													
													</table>
												</td>
											</tr>
										</table>
										
									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box">
										<div class="box">
											<input type="hidden" name="categoryIds"
												value="${catePojo.category.categoryId }" />
											<%--循环取出此处不需要语言就换成id进行修改 --%>
											<div class="row">
												<div class="col-md-2">Name_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="categoryNames"
														value="${catePojo.category.categoryName }"
														categoryId="${catePojo.category.categoryId }"
														isCategoryNameUnique="true" maxlength="25"
														lang="${catePojo.category.lang }" />
												</div>
												<div class="col-md-2 tn_c">Breadcrumb Name_${catePojo.language.localeCode }</div>
                        					<div class="col-md-4">
                        						<input type="text" name = "breadcrumbNames" value = "${catePojo.category.breadcrumbName }" 
													maxlength="25" />
                       						</div> 
											</div>
											<div class="row">
												<div class="col-md-2">PC
													Template_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<select name="pcTemplateIds"
														value="${catePojo.category.pcTemplateId}">
														<option value="">--select--</option>
														<c:forEach items="${templates}" var="t">
															<option value="${t.templateId}">${t.templateName}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-md-2 tn_c">Mobile
													Template_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<select name="mobileTemplateIds"
														value="${catePojo.category.mobileTemplateId }">
														<option value="">--select--</option>
														<c:forEach items="${templates}" var="t">
															<option value="${t.templateId}">${t.templateName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="row seo bold">
												<div class="col-md-9 space">SEO (PC
													Website)_${catePojo.language.localeCode }</div>
											</div>
											<div class="row">
												<div class="col-md-2">Title_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoTitlePcs"
														value="${catePojo.category.seoTitlePc }" maxlength="100" />
												</div>
												<div class="col-md-2 tn_c">H1 Tag</div>
												<div class="col-md-4">
													<input type="text" name="seoH1tagPcs"
														value="${catePojo.category.seoH1tagPc }" maxlength="100" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
												<div class="col-md-10">
													<input type="text" name="seoKeywordsPcs"
														value="${catePojo.category.seoKeywordsPc }" maxlength="200" />
												</div>
											</div>
											<div class="row larger">
												<div class="col-md-2">Description_${catePojo.language.localeCode }</div>
												<div class="col-md-10">
													<textarea rows="2" name="seoDescriptionPcs" maxlength="400" >${catePojo.category.seoDescriptionPc }</textarea>
												</div>
											</div>
											<div class="row seo bold">
												<div class="col-md-9 space">SEO (Mobile
													Website)_${catePojo.language.localeCode }</div>
											</div>
											<div class="row">
												<div class="col-md-2">Title_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoTitleMobiles"
														value="${catePojo.category.seoTitleMobile }" maxlength="100" />
												</div>
												<div class="col-md-2 tn_c">H1
													Tag_${catePojo.language.localeCode }</div>
												<div class="col-md-4">
													<input type="text" name="seoH1tagMobiles"
														value="${catePojo.category.seoH1tagMobile }" maxlength="100" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
												<div class="col-md-10">
													<input type="text" name="seoKeywordsMobiles"
														value="${catePojo.category.seoKeywordsMobile }" maxlength="200" />
												</div>
											</div>
											<div class="row larger">
												<div class="col-md-2">Description_${catePojo.language.localeCode }</div>
												<div class="col-md-10">
													<textarea name="seoDescriptionMobiles" maxlength="400" >${catePojo.category.seoDescriptionMobile }</textarea>
												</div>
											</div>
											 <div class="row">
		                    				<input type="hidden" name="categoryHeroAreaIds" value="${catePojo.heroArea.categoryHeroAreaId }" />
											<div class="col-md-2 required">Hero
												Areas_${catePojo.language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${catePojo.language.localeId }" value="${catePojo.fieldValue }"
													readonly="readonly" class="disable" required="required" /> <input
													type="hidden" name="heroAreaMetadataIds" value="${catePojo.fieldValueId }" id="heroAreas${catePojo.language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${catePojo.language.localeId }','heroAreasName${catePojo.language.localeId }')">Choose</a>
											</div>
										</div>
											 <table id = "bannerTable${catePojo.category.lang }" class="big marginTop" border="0" cellspacing="0"
											cellpadding="0">
											<tr class="head">
												<th class="head_l">Banner Units</th>
												<th class="head_r"><a href="javascript:void(0);" class="bannerUnit_new" onclick = "newBannerUnit(${catePojo.category.lang })">New</a></th>
											</tr>
											<tr class="inside_border">
												<td colspan="2">
												
													<table class="small" border="0" cellspacing="0"
														cellpadding="0">
														<tr class="heading">
															<th style="width:15%">Action</th>
															<th style="width:25%">Name</th>
															<th style="width:60%">Position</th>
														</tr>
														
														<c:import 
													url="/category/unlimited/getBannerUnitList.action?categoryMetadataId=${parentCategory.categoryMetadataId }&lang=${catePojo.category.lang }"></c:import>
														<%-- 
														<tr>
															<td class="tn_c"><span class="edit partsImg_new"></span><span
																class="del"></span></td>
															<td>Banner1</td>
															<td class="last">第1行第1列</td>
														</tr>
														<tr class="gray">
															<td class="tn_c"><span class="edit partsImg_new"></span><span
																class="del"></span></td>
															<td>Banner2</td>
															<td class="last">第4行第2列</td>
														</tr>
														--%>
													</table>
												</td>
											</tr>
										</table>
										
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>


				
				<table class="big" border="0" cellspacing="0" cellpadding="0"  tyle=" margin-top:40px;">
					<tr class="head">
						<th class="head_l">Common Attribute (For Category)</th>
						<th class="head_r"><a href="#" class="commonAttribute_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="comAttrTable" class="small" border="0" cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:20%">Name_CN</th>
									<th style="width:15%">Is Filter</th>
									<th style="width:50%">SeqNo</th>
								</tr>
								<c:forEach items="${comAttrs}" var="comAttr"
									varStatus="status">
									<tr id = "${comAttr.categoryComAttrMetadataId}">
										<td class="tn_c">
											<span class="edit cads_new" onclick="editComAttr(${comAttr.categoryComAttrMetadataId})">
											</span>
											<span class="del" onclick="delComAttr(this,${comAttr.categoryComAttrMetadataId})"></span></td>
										<td>${comAttr.categoryComAttrName}</td>
										<td class="tn_c">${comAttr.isFilter}</td>
										<td class="last tn_c">${comAttr.seqNo}</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
				<table class="big" border="0" cellspacing="0" cellpadding="0">
					<tr class="head">
						<th class="head_l">SKU Attribute (For Category)</th>
						<th class="head_r"><a href="#" class="skuAttribute_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="skuAttrTable" class="small" border="0" cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:20%">Name_CN</th>
									<th style="width:15%">Is Filter</th>
									<th style="width:50%">SeqNo</th>
								</tr>
								<c:forEach items="${skuAttrs}" var="skuAttr"
									varStatus="status">
									<tr id = "${skuAttr.categorySKUAttrMetadataId}">
										<td class="tn_c">
											<span class="edit video_new" onclick="editSkuAttrPage(${skuAttr.categorySKUAttrMetadataId})">
											</span>
											<span class="del" onclick="delSkuAttr(this,${skuAttr.categorySKUAttrMetadataId})"></span></td>
										<td>${skuAttr.categorySkuAttrName}</td>
										<td class="tn_c">${skuAttr.isFilter}</td>
										<td class="last tn_c">${skuAttr.seqNo}</td>
									</tr>
								</c:forEach>
								
							</table>
						</td>
					</tr>
				</table>
				<table class="big" border="0" cellspacing="0" cellpadding="0">
					<tr class="head">
						<th class="head_l">Search Keywords</th>
						<th class="head_r"><a href="#" class="searchKeywords_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="searchKeyTable" class="small" border="0" cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:25%">Keyword</th>
									<th style="width:60%">URL</th>
								</tr>
								<c:forEach items="${searchKeys}" var="searchKey"
									varStatus="status">
								<tr id="${searchKey.categorySearchKeywordMetadataId }">
									<td class="tn_c"><span class="edit pdf_new" onclick="editSearchKeyPage(${searchKey.categorySearchKeywordMetadataId })"></span>
									<span
										class="del" onclick="delSearchKey(this,
											${searchKey.categorySearchKeywordMetadataId})"></span></td>
									<td>${searchKey.keyword }</td>
									<td class="last">${searchKey.url }</td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
				<table class="big" border="0" cellspacing="0" cellpadding="0">
					<tr class="head">
						<th class="head_l">Accessories</th>
						<th class="head_r"><a href="#" class="accessories_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="accessoryTable" class="small" border="0" cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:25%">Accessory Name</th>
									<th style="width:60%">Type</th>
								</tr>
								<c:forEach items="${accessorys}" var="accessory"
									varStatus="status">
								<tr id="${accessory.categoryAccessoryMetadataId }">
									<td class="tn_c"><span class="edit pdf_new" onclick="editAccessoryPage(${accessory.categoryAccessoryMetadataId })"></span>
									<span
										class="del" onclick="delAccessory(this,
											${accessory.categoryAccessoryMetadataId})"></span></td>
									<td>${accessory.categoryAccessoryName }</td>
									<td class="last">${accessory.masterdataName }</td>
								</tr>
								</c:forEach>
								
								
							</table>
						</td>
					</tr>
				</table>
				<div class="text">
					<div class="row">
						<div class="col-md-2">Creator</div>
						<div class="col-md-4">${parentCategory.createUser }</div>
						<div class="col-md-6">Creation Date ${parentCategory.createTime }</div>
					</div>
					<div class="row">
						<div class="col-md-2">Modifier</div>
						<div class="col-md-4">>${parentCategory.modifyUser }</div>
						<div class="col-md-6">Modification Date2014-07-01 ${parentCategory.modifyTime }</div>
					</div>
				</div>
				<div class="btns">
					<a href="javascript:void(0);" class="confirm"
						onclick="updateCate();">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>
			</form>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
		<script>
			$(document).ready(
					function() {
						$("select").each(
								function() {
									var select = $(this);
									var selectValue = select.attr("value");

									select.find(
											"option[value='" + selectValue
													+ "']").attr("selected",
											true);
								});
						
						var isEdit = '${isEdit}';
						if(isEdit == 1){
							$("select").attr("disabled","disabled");
							setGray4DisabledSelect($("select"));
							$("input").attr("disabled","disabled");
							$("textarea").attr("disabled","disabled");
							//$(".onlyedit a").remove();
							$("a[class$='new']").remove();
							$("a.choose").remove();
							$("span.del").remove();
							$("span.edit").remove();
							$('.confirm').hide();
						}
						
					});

			//判断categoryName是否唯一
			jQuery.validator.addMethod("isCategoryNameUnique", function(value,
					element) {

				var result = false;
				var langValue = $(element).attr("lang");
				var categoryMetaId = ${parentCatalog.parentId };
				var cateId = $(element).attr("categoryId");

				// 设置同步
				$.ajaxSetup({
					async : false
				});
				var param = {
					categoryName : value,
					lang : langValue,
					categoryMetadataId : categoryMetaId,
					categoryId : cateId
				};

				$.post("unlimited/checkCategoryName.action", param, function(
						data) {
					data = eval(data);
					var flag = data.flag;
					result = ("0" == flag);
				}, "json");
				// 恢复异步
				$.ajaxSetup({
					async : true
				});
				return result;
			}, "<spring:message code="info.product.category.004" arguments="" argumentSeparator=","/>");

			function updateCate() {
				$("#errorMessage").html("");

				if ($("#editCategoryForm").valid()) {
					var heroAreas = new Array();
					heroAreas = $("#heroAreas").val();
					
					$.post("editCategorySave.action", $("#editCategoryForm")
							.serialize()+"&heroAreas="+heroAreas, function(data) {
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
			
			//弹出一个iframe层
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
			
			//关闭frame
			$(function() {
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				$('.cancel').on('click', function() {
					parent.layer.close(index); //执行关闭
				});
			});
			//banner unit
			function newBannerUnit(lang){
				
				console.info("lang="+lang);
				var id = ${parentCategory.categoryMetadataId };
				
				alertFirstIframe("Banner Unit",
						"unlimited/newBannerUnitPage.action?categoryMetadataId="
								+ id+"&lang="+lang, '850', '650px');
			}
			
			function editbannerUnitPage(categoryBannerUnitId){
				alertFirstIframe("Banner Unit",
						"unlimited/editBannerUnitPage.action?categoryBannerUnitId="
								+ categoryBannerUnitId, '850', '650px');
			}
			
			function delbannerUnit(tag, categoryBannerUnitId) {
				$(".xubox_main").addClass("a");
				$.layer({
					shade : [ 0 ],
					title : 'delbannerUnit',
					area : [ '310px', '130px' ],
					dialog : {
						msg : 'Delete this data?',
						btns : 2,
						type : -1,
						btn : [ 'OK', 'Cancel' ],
						yes : function() {

							//console.info("get data = "+categoryComAttrMetadataId);

							$.post("unlimited/deleteBannerUnit.action",
									"categoryBannerUnitId="
											+ categoryBannerUnitId,
									function(data) {
										var result = eval(data);
										//alert(result.msg);
										layer.msg(result.msg, 1, 1);//按钮一的回调函数
										//location.reload();
										$(tag).parent().parent("tr").remove();
									}, "json");

						}
					}
				});
			}
			
			
			//banner unit
			//commAttr
			$('.commonAttribute_new').on(
					'click',
					function() {
						var id = ${parentCategory.categoryMetadataId };
						alertFirstIframe("CommonAtrribute",
								"unlimited/newComAttrPage.action?categoryMetadataId="
										+ id, '700', '580px');
					});

			function delComAttr(tag, categoryComAttrMetadataId) {
				$(".xubox_main").addClass("a");
				$.layer({
					shade : [ 0 ],
					title : 'delComAttr',
					area : [ '310px', '130px' ],
					dialog : {
						msg : 'Delete this data?',
						btns : 2,
						type : -1,
						btn : [ 'OK', 'Cancel' ],
						yes : function() {

							//console.info("get data = "+categoryComAttrMetadataId);

							$.post("unlimited/deleteCateComAttr.action",
									"categoryComAttrMetadataId="
											+ categoryComAttrMetadataId,
									function(data) {
										var result = eval(data);
										//alert(result.msg);
										layer.msg(result.msg, 1, 1);//按钮一的回调函数
										//location.reload();
										$(tag).parent().parent("tr").remove();
									}, "json");

						}
					}
				});
			}

			function editComAttr(categoryComAttrMetadataId) {
				alertFirstIframe("CommonAtrribute",
						"unlimited/editComAttrPage.action?categoryComAttrMetadataId="
								+ categoryComAttrMetadataId, '700', '580px');
			}
			//commAttr

			//skuAttr
			$('.skuAttribute_new').on(
					'click',
					function() {
						var id = ${parentCategory.categoryMetadataId };
						alertFirstIframe("SKU Attribute",
								"unlimited/newSkuAttrPage.action?categoryMetadataId="
										+ id, '600', '450px');
					});

			function delSkuAttr(tag, categorySKUAttrMetadataId) {
				$(".xubox_main").addClass("a");
				$.layer({
					shade : [ 0 ],
					title : 'delSkuAttr',
					area : [ '310px', '130px' ],
					dialog : {
						msg : 'Delete this data?',
						btns : 2,
						type : -1,
						btn : [ 'OK', 'Cancel' ],
						yes : function() {

							//console.info("get data = "+categoryComAttrMetadataId);

							$.post("unlimited/deleteCateSkuAttr.action",
									"categorySKUAttrMetadataId="
											+ categorySKUAttrMetadataId,
									function(data) {
										var result = eval(data);
										//alert(result.msg);
										layer.msg(result.msg, 1, 1);//按钮一的回调函数
										//location.reload();
										$(tag).parent().parent("tr").remove();
									}, "json");

						}
					}
				});
			}

			function editSkuAttrPage(categorySKUAttrMetadataId) {
				alertFirstIframe("SKU Atrribute",
						"unlimited/editSkuAttrPage.action?categorySKUAttrMetadataId="
								+ categorySKUAttrMetadataId, '700', '550px');
			}
			//skuAttr

			//search keyword
			$('.searchKeywords_new').on(
					'click',
					function() {

						var id = ${parentCategory.categoryMetadataId };
						alertFirstIframe("Search Keyword",
								"unlimited/newSearchKeyPage.action?categoryMetadataId="
										+ id, '600', '450px');
					});

			function delSearchKey(tag, categorySearchKeywordMetadataId) {
				$(".xubox_main").addClass("a");
				$.layer({
					shade : [ 0 ],
					title : 'delSearchKey',
					area : [ '310px', '130px' ],
					dialog : {
						msg : 'Delete this data?',
						btns : 2,
						type : -1,
						btn : [ 'OK', 'Cancel' ],
						yes : function() {

							//console.info("get data = "+categoryComAttrMetadataId);

							$.post("unlimited/deleteCateSearchKey.action",
									"categorySearchKeywordMetadataId="
											+ categorySearchKeywordMetadataId,
									function(data) {
										var result = eval(data);
										//alert(result.msg);
										layer.msg(result.msg, 1, 1);//按钮一的回调函数
										//location.reload();
										$(tag).parent().parent("tr").remove();
									}, "json");

						}
					}
				});
			}

			function editSearchKeyPage(categorySearchKeywordMetadataId) {
				alertFirstIframe("SKU Atrribute",
						"unlimited/editSearchKeyPage.action?categorySearchKeywordMetadataId="
								+ categorySearchKeywordMetadataId, '600', '450px');
			}
			//search keyword

			//accessory
			$('.accessories_new').on(
					'click',
					function() {

						var id = ${parentCategory.categoryMetadataId };
						alertFirstIframe("Accessory",
								"unlimited/newAccessoryPage.action?categoryMetadataId="
										+ id, '600', '450px');

					});
			function editAccessoryPage(categoryAccessoryMetadataId) {
				alertFirstIframe("Accessory",
						"unlimited/editAccessoryPage.action?categoryAccessoryMetadataId="
								+ categoryAccessoryMetadataId, '600', '450px');
			}

			function delAccessory(tag, categoryAccessoryMetadataId) {
				$(".xubox_main").addClass("a");
				$.layer({
					shade : [ 0 ],
					title : 'delSearchKey',
					area : [ '310px', '130px' ],
					dialog : {
						msg : 'Delete this data?',
						btns : 2,
						type : -1,
						btn : [ 'OK', 'Cancel' ],
						yes : function() {

							//console.info("get data = "+categoryComAttrMetadataId);

							$.post("unlimited/deleteCateAccessory.action",
									"categoryAccessoryMetadataId="
											+ categoryAccessoryMetadataId, function(
											data) {
										var result = eval(data);
										//alert(result.msg);
										layer.msg(result.msg, 1, 1);//按钮一的回调函数
										//location.reload();
										$(tag).parent().parent("tr").remove();
									}, "json");

						}
					}
				});
			}

			//accessory
			$('.detail').on('click', function() {
				$.layer({
					type : 2,
					title : 'Catolog',
					maxmin : false,
					shadeClose : true, //开启点击遮罩关闭层
					area : [ '880px', '650px' ],
					shadeClose : false,
					offset : [ ($(window).height() - 660) / 2 + 'px', '' ],
					iframe : {
						src : 'catalog_layerbox.html'
					}
				});
			});
			$('.long').on('click', function() {
				$.layer({
					type : 2,
					title : 'Product',
					maxmin : false,
					shadeClose : true, //开启点击遮罩关闭层
					area : [ '880px', '650px' ],
					shadeClose : false,
					offset : [ ($(window).height() - 660) / 2 + 'px', '' ],
					iframe : {
						src : 'product_layerbox.html'
					}
				});
			});
		
		</script>
	</div>
</body>
</html>

