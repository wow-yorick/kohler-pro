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
</head>

<body>
	<div class="container queryConditions product_layer">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form action="${base}/category/createCategorySave.action"
				id="addCategoryForm" name="addPageForm">
				<input type="hidden" name="categoryMetadataId"
					value="${categoryMetadata.categoryMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${categoryMetadata.categoryMetadataId }</div>
						<div class="col-md-2 tn_c">Level</div>
						<div class="col-md-4">
							<input type="text" name="level" value="${pareCate.level+1 }"
								readonly="readonly" class="disable" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">Belong to</div>
						<div class="col-md-4 ">
							<input type="text" value="${pareCate.categoryName}"
								readonly="readonly" class="disable" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4 ">
							<input name="seqNo" type="text" maxlength="8" isIntGteZero="true" />
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
								<c:when test="${language.isDefault == true  }">
									<div class="box">
										<input type="hidden" name="langs"
											value="${language.localeId }" />
										<%--循环取出 --%>
										<div class="row">
											<div class="col-md-2 required">Name_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="categoryNames" required="required"
													maxlength="25" lang="${language.localeId }"
													isCategoryNameUnique="true" />
											</div>

											<div class="col-md-2 tn_c required">Breadcrumb Name_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="breadcrumbNames"
													required="required" maxlength="25" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 required">PC
												Template_${language.localeCode }</div>
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
												<input type="text" name="seoTitlePcs" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPcs" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPcs" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea rows="2" name="seoDescriptionPcs"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile
												Website)_${language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobiles" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobiles" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobiles" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobiles"></textarea>
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
										
										<table  class="big marginTop" border="0" cellspacing="0"
											cellpadding="0">
											<tr class="head">
												<th class="head_l">Banner Units</th>
												<th class="head_r"><a href="javascript:void(0);" class="bannerUnit_new" onclick = "newBannerUnit(${language.localeId })" >New</a></th>
											</tr>
											<tr class="inside_border">
												<td colspan="2">
													<table id = "bannerTable${language.localeId }" class="small" border="0" cellspacing="0"
														cellpadding="0">
														<tr class="heading">
															<th style="width:15%">Action</th>
															<th style="width:25%">Name</th>
															<th style="width:60%">Position</th>
														</tr>
													
													</table>
												</td>
											</tr>
										</table>

									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box">
										<input type="hidden" name="langs"
											value="${language.localeId }" />
										<%--循环取出 --%>
										<div class="row">
											<div class="col-md-2">Name_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="categoryNames" maxlength="25"
													lang="${language.localeId }" isCategoryNameUnique="true" />
											</div>
											<div class="col-md-2 tn_c required">Breadcrumb Name_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="breadcrumbNames" maxlength="25" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">PC Template_${language.localeCode }</div>
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
												<input type="text" name="seoTitlePcs" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPcs" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPcs" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea rows="2" name="seoDescriptionPcs"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile
												Website)_${language.localeCode }</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobiles" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag_${language.localeCode }</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobiles" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords_${language.localeCode }</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobiles" />
											</div>
										</div>
										<div class="row larger">
											<div class="col-md-2">Description_${language.localeCode }</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobiles"></textarea>
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
										
										<table  class="big marginTop" border="0" cellspacing="0"
											cellpadding="0">
											<tr class="head">
												<th class="head_l">Banner Units</th>
												<th class="head_r"><a href="javascript:void(0);" class="bannerUnit_new" onclick = "newBannerUnit(${language.localeId })">New</a></th>
											</tr>
											<tr class="inside_border">
												<td colspan="2">
													<table id = "bannerTable${language.localeId }" class="small" border="0" cellspacing="0"
														cellpadding="0">
														<tr class="heading">
															<th style="width:15%">Action</th>
															<th style="width:25%">Name</th>
															<th style="width:60%">Position</th>
														</tr>
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
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>

			<%-- 
				<table class="big marginTop" border="0" cellspacing="0"
					cellpadding="0">
					<tr class="head">
						<th class="head_l">Banner Units</th>
						<th class="head_r"><a href="#" class="bannerUnit_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table class="small" border="0" cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:25%">Name</th>
									<th style="width:60%">Position</th>
								</tr>
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
							</table>
						</td>
					</tr>
				</table> --%>

				<table class="big" border="0" cellspacing="0" cellpadding="0" style=" margin-top:40px;">
					<tr class="head">
						<th class="head_l">Common Attribute (For Category)</th>
						<th class="head_r"><a href="javascript:void(0);"
							class="commonAttribute_new">New </a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="comAttrTable" class="small" border="0" cellspacing="0"
								cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:20%">Name_CN</th>
									<th style="width:15%">Is Filter</th>
									<th style="width:50%">SeqNo</th>
								</tr>

							</table>
						</td>
					</tr>
				</table>
				<table class="big" border="0" cellspacing="0" cellpadding="0">
					<tr class="head">
						<th class="head_l">SKU Attribute (For Category)</th>
						<th class="head_r"><a href="javascript:void(0);"
							class="skuAttribute_new">New</a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table id="skuAttrTable" class="small" border="0" cellspacing="0"
								cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:20%">Name_CN</th>
									<th style="width:15%">Is Filter</th>
									<th style="width:50%">SeqNo</th>
								</tr>
								<%-- 
								<tr>
									<td class="tn_c"><span class="edit video_new"></span><span
										class="del"></span></td>
									<td>颜色</td>
									<td class="tn_c">V</td>
									<td class="last tn_c">1</td>
								</tr>
								<tr class="gray">
									<td class="tn_c"><span class="edit video_new"></span><span
										class="del"></span></td>
									<td>尺寸</td>
									<td class="tn_c">V</td>
									<td class="last tn_c">2</td>
								</tr>
								--%>
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
							<table id="searchKeyTable" class="small" border="0"
								cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:25%">Keyword</th>
									<th style="width:60%">URL</th>
								</tr>
								<%-- 
								<tr>
									<td class="tn_c"><span class="edit pdf_new"></span><span
										class="del"></span></td>
									<td>智能坐便器</td>
									<td class="last">/SmartToilet/</td>
								</tr>
								<tr class="gray">
									<td class="tn_c"><span class="edit pdf_new"></span><span
										class="del"></span></td>
									<td>五级旋风</td>
									<td class="last">/5Class/</td>
								</tr>
								--%>
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
							<table id="accessoryTable" class="small" border="0"
								cellspacing="0" cellpadding="0">
								<tr class="heading">
									<th style="width:15%">Action</th>
									<th style="width:25%">Accessory Name</th>
									<th style="width:60%">Type</th>
								</tr>
								<%-- 
								<tr>
									<td class="tn_c"><span class="edit pdf_new"></span><span
										class="del"></span></td>
									<td>包括配件</td>
									<td class="last">SKU Picker</td>
								</tr>
								<tr class="gray">
									<td class="tn_c"><span class="edit pdf_new"></span><span
										class="del"></span></td>
									<td>推荐配件</td>
									<td class="last">SKU Picker</td>
								</tr>
								<tr>
									<td class="tn_c"><span class="edit pdf_new"></span><span
										class="del"></span></td>
									<td>必须配件</td>
									<td class="last">Description</td>
								</tr>
								--%>
							</table>
						</td>
					</tr>
				</table>
				<div class="text">
					<div class="row">
						<div class="col-md-2">Creator</div>
						<div class="col-md-4">${categoryMetadata.createUser }</div>
						<div class="col-md-6">Creation Date
							${categoryMetadata.createTime }</div>
					</div>
					<div class="row">
						<div class="col-md-2">Modifier</div>
						<div class="col-md-4">${categoryMetadata.modifyUser }</div>
						<div class="col-md-6">Modification Date
							${categoryMetadata.modifyTime }</div>
					</div>
				</div>
				<div class="btns">
					<a href="javascript:void(0);" class="confirm"
						onclick="addCategory()">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>
			</form>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
</body>
<script>
	//判断categoryName是否唯一
	jQuery.validator
			.addMethod(
					"isCategoryNameUnique",
					function(value, element) {

						var result = false;
						var langValue = $(element).attr("lang");
						var categoryMetaId = ${pareCate.categoryMetadataId };

						// 设置同步
						$.ajaxSetup({
							async : false
						});
						var param = {
							categoryName : value,
							lang : langValue,
							categoryMetadataId : categoryMetaId
						};

						$.post("unlimited/checkCategoryName.action", param,
								function(data) {
									data = eval(data);
									var flag = data.flag;
									result = ("0" == flag);
								}, "json");
						// 恢复异步
						$.ajaxSetup({
							async : true
						});
						return result;
					},
					"<spring:message code="info.product.category.004" arguments="" argumentSeparator=","/>");

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
	        area : ['780px' , '650px'],
			shadeClose: false,
			move: false,
			offset : [($(window).height() - 600)/2+'px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id+'&elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
	function addCategory() {
		$("#errorMessage").html("");
		if ($("#addCategoryForm").valid()) {
			
			var heroAreas = new Array();
			heroAreas = $("#heroAreas").val();
			$.post("createCategorySave.action", $("#addCategoryForm")
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
	/*$('.bannerUnit_new').on('click', function() {
		var id = ${categoryMetadata.categoryMetadataId };
		alertFirstIframe("CommonAtrribute",
				"unlimited/newComAttrPage.action?categoryMetadataId="
						+ id, '600', '450px');
	}); */
	//banner unit
	function newBannerUnit(lang){
		
		console.info("lang="+lang);
		var id = ${categoryMetadata.categoryMetadataId };
		
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
			title : 'delComAttr',
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
				var id = ${categoryMetadata.categoryMetadataId };
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
				var id = ${categoryMetadata.categoryMetadataId };
				alertFirstIframe("SKU Attribute",
						"unlimited/newSkuAttrPage.action?categoryMetadataId="
								+ id, '850', '650px');
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
						+ categorySKUAttrMetadataId, '850', '650px');
	}
	//skuAttr

	//search keyword
	$('.searchKeywords_new').on(
			'click',
			function() {

				var id = ${categoryMetadata.categoryMetadataId };
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

				var id = ${categoryMetadata.categoryMetadataId };
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
	//删除按钮
	$('.del').on('click', function() {
		$(".xubox_main").addClass("a");
		$.layer({
			shade : [ 0 ],
			title : 'Confirm',
			area : [ '310px', '130px' ],
			dialog : {
				msg : 'Delete this data?',
				btns : 2,
				type : -1,
				btn : [ 'OK', 'Cancel' ],
				yes : function() {
					layer.msg('Delete the success', 1, 1);//按钮一的回调函数			
				}
			}
		});
	});
</script>
</html>

