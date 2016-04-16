<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SKU Attribute</title>
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
		//选中select里面的内容显示隐藏	
		$('#select').bind('change', function() {
			var $select = $("#select").find('option:selected').text();
			if ($select == 'Textbox') {
				$('#txt .droplist').css('display', 'none');
			} else {
				$('#txt .droplist').show();
			}
		});
		//imgurlchoose显示隐藏
		$('#selectChoose').bind('change', function() {
			var $select2 = $("#selectChoose").find('option:selected').text();
			if ($select2 == 'Internal') {
				$('.imgUrl_one').hide();
				$('.imgUrl_two').show();
			} else {
				$('.imgUrl_one').show();
				$('.imgUrl_two').hide();
			}
		});
	});
</script>
</head>

<body>
	<div
		class="container queryConditions product_layer partsImages skuAttribute">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form id="editSkuAttrForm">
			
				<input type = "hidden" name = "categoryMetadataId" value = "${categorySkuAttrMetadata.categoryMetadataId }" />
				<input type="hidden" name="categorySKUAttrMetadataId" value="${categorySkuAttrMetadata.categorySKUAttrMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${categorySkuAttrMetadata.categorySKUAttrMetadataId }</div>
						<div class="col-md-2 required">key name</div>
						<div class="col-md-4">
							<input type="text" name="keyName"
								value="${categorySkuAttrMetadata.keyName }" required="required"
													maxlength="25" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4">
							<input type="text" name="seqNo" maxlength="8" digits="true"
								value="${categorySkuAttrMetadata.seqNo }" />
						</div>
						<div class="col-md-4 tn_c">Is Filter</div>
						<div class="col-md-1">
							<!-- <span class="check"></span> -->
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> <input type="checkbox"
									name="isFilter" id = "isFilter" value="${categorySkuAttrMetadata.isFilter }">
							</div>
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
				<div class="tab_left">
					<div class="tab_box">

						<c:forEach items="${languages}" var="language" varStatus="status">
							<c:choose>
								<c:when test="${language.isDefault == true }">
									<div class="box">
										<form id="lan">
											<c:forEach items="${skuAttrList }" var="skuAttr">
												<c:if test="${skuAttr.lang eq language.localeId}">
													<input type = "hidden" name = "categorySKUAttrMetadataId" value = "${skuAttr.categorySKUAttrMetadataId }" />
													<input type="hidden" name="lang" value="${skuAttr.lang }" />
													<input type="hidden" name="categorySKUAttrId"
														value="${skuAttr.categorySKUAttrId}" />
													<div class="row">
														<div class="col-md-3 required">Name_${language.localeCode}</div>
														<div class="col-md-9">
															<input type="text" name="categorySkuAttrName"
																value="${skuAttr.categorySkuAttrName }" required="required"
													maxlength="25" />
														</div>
													</div>
													<div class="row">
														<div class="col-md-5 tn_c">
															<label style="margin-left:15px;">Name_${language.localeCode}</label>
														</div>
													</div>
													<c:forEach items="${attrValues}" var="map">
														
														<c:if test="${map.key eq language.localeCode }">
															<c:forEach items="${map.value}"  var="attrData" varStatus="status" >
																
																			<div class="row">
																		<div class="col-md-3 tn_l">Values_${status.index+1}</div>
																		<div class="col-md-9">
																			
																			<input type="text"  name="attrValues" id="attrValue${status.index+1}" value="${attrData.attrValue }"  />
																		</div>
																		</div>
																	
																	
																	
															</c:forEach>
														</c:if>
													</c:forEach>
													<%-- 
													<div class="row">
															<div class="col-md-3 tn_l">Values_1</div>
															<div class="col-md-9">
																<c:set var="test" value="${language.localeCode}" />
																<input type="text"  name="attrValues" id="attrValue1" value="${test}"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_2</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue2"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_3</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue3"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_4</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue4"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_5</div>
															<div class="col-md-9">
																<input type="text"  name="attrValues" id="attrValue5"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_6</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue6"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_7</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue7"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_8</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue8"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_9</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue9"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_10</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" id="attrValue10" />
															</div>
														</div>
														--%>
													<div class="row higher">
														<div class="col-md-3">Image Icon Source</div>
														<div class="col-md-9">
															<select id="selectChoose" name = "imageSource" value = "${skuAttr.imageSource}" >
																<option value="0">External</option>
																<option value="1">Internal</option>
															</select>
														</div>
													</div>
													<div class="row imgUrl_one">
														<div class="col-md-3">Image URL</div>
														<div class="col-md-9">
															<input type="text" name="imageUrl" value = "${skuAttr.imageUrl}" />
														</div>
													</div>
													
													<div class="row imgUrl_two" style="display:none;">
														<div class="col-md-3 ">Image URL</div>
														<div class="col-md-6 smaller">
															<input type="text" name="imageId" value = "${skuAttr.imageId}" id="imageId${language.localeId }" />
														</div>
														<div class="col-md-3">
															<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageId${language.localeId }','1')" >Choose</a>
														</div>
													</div>
													
													<div class="row">
														<div class="col-md-3">Tooltip</div>
														<div class="col-md-9">
															<input type="text" name="imageTooltip" value = "${skuAttr.imageTooltip}" />
														</div>
													</div>
												</c:if>
											</c:forEach>

										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box">
										<div class="box">
											<form id="lan">
												<c:forEach items="${skuAttrList }" var="skuAttr">
													<c:if test="${skuAttr.lang eq language.localeId}">
														<input type = "hidden" name = "categorySKUAttrMetadataId" value = "${skuAttr.categorySKUAttrMetadataId }" />
														<input type="hidden" name="lang" value="${skuAttr.lang }" />
														<input type="hidden" name="categorySKUAttrId"
															value="${skuAttr.categorySKUAttrId}" />
														<div class="row">
															<div class="col-md-3 ">Name_${language.localeCode}</div>
															<div class="col-md-6">
																<input type="text" name="categorySkuAttrName"
																	value="${skuAttr.categorySkuAttrName }" maxlength="25" />
															</div>
														</div>
														<div class="row">
															<div class="col-md-5 tn_c">
																<label style="margin-left:15px;">Name_${language.localeCode}</label>
															</div>
														</div>
														
														<c:forEach items="${attrValues}" var="map">
														
														<c:if test="${map.key eq language.localeCode }">
															<c:forEach items="${map.value}"  var="attrData" varStatus="status" >
																
																			<div class="row">
																		<div class="col-md-3 tn_l">Values_${status.index+1}</div>
																		<div class="col-md-9">
																			
																			<input type="text"  name="attrValues" value="${attrData.attrValue }"  />
																		</div>
																		</div>
																	
																	
																	
															</c:forEach>
														</c:if>
													</c:forEach>
													
														<%-- 
														<div class="row">
															<div class="col-md-3 tn_l">Values_1</div>
															<div class="col-md-9">
																<input type="text"  name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_2</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_3</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_4</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_5</div>
															<div class="col-md-9">
																<input type="text"  name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_6</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_7</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_8</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_9</div>
															<div class="col-md-9">
																<input type="text" name="attrValues"  />
															</div>
														</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_10</div>
															<div class="col-md-9">
																<input type="text" name="attrValues" />
															</div>
														</div>
														--%>
														<div class="row higher">
															<div class="col-md-3">Image Icon Source</div>
															<div class="col-md-9">
																<select id="selectChoose" name = "imageSource" value = "${skuAttr.imageSource}" >
																	<option value="0">External</option>
																	<option value="1">Internal</option>
																</select>
															</div>
														</div>
														<div class="row imgUrl_one">
															<div class="col-md-3">Image URL</div>
															<div class="col-md-9">
																<input type="text" name="imageUrl"  value = "${skuAttr.imageUrl}" />
															</div>
														</div>
														
														<div class="row imgUrl_two" style="display:none;">
															<div class="col-md-3 ">Image URL</div>
															<div class="col-md-6 smaller">
																<input type="text" name="imageId" value = "${skuAttr.imageId}" id="imageId${language.localeId }" />
															</div>
															<div class="col-md-3">
																<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageId${language.localeId }','1')" >Choose</a>
															</div>
														</div>
														
														<div class="row">
															<div class="col-md-3">Tooltip</div>
															<div class="col-md-9">
																<input type="text" name="imageTooltip" value = "${skuAttr.imageTooltip}"  />
															</div>
														</div>
													</c:if>
												</c:forEach>
											</form>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>



				<div class="tab_right">
					<div class="row">ImageURL</div>
					<c:forEach items="${metaList}" var="skuAttrValuesMeta" varStatus="status">
						<div class="row">
							<div class="col-md-8 border">
								<input type="text" name="valuesImageUrl" readonly="readonly" class="disable" value="${skuAttrValuesMeta.imageId }" id = "imageUrl${status.index+1}" />
							</div>
							<div class="col-md-4">
								<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl${status.index+1}','1')">Choose</a>
							</div>
						</div>
					</c:forEach>
					<%-- 
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id = "imageUrl1" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl1','1')">Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id = "imageUrl2" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl2','1')">Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl3" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl3','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl4"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl4','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl5"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl5','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl6"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl6','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl7"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl7','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl8"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl8','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl9"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl9','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl10" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl10','1')">Choose</a>
						</div>
					</div>
				--%>
				</div>
				<div class="clear"></div>

				<div class="text">
					<div class="row">
						<div class="col-md-2">Creator</div>
						<div class="col-md-3">${categorySkuAttrMetadata.createUser }</div>
						<div class="col-md-7">Creation Date
							${categorySkuAttrMetadata.createTime }</div>
					</div>
					<div class="row">
						<div class="col-md-2">Modifier</div>
						<div class="col-md-3">${categorySkuAttrMetadata.modifyUser }</div>
						<div class="col-md-7">Modification Date
							${categorySkuAttrMetadata.modifyTime }</div>
					</div>
				</div>
				<div class="btns">
					<a href="javascript:void(0);" class="confirm"
						onclick="editSkuAttr()">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>


			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
	<script type="text/javascript">
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
				
				var isFilter = '${categorySkuAttrMetadata.isFilter }';
				if (isFilter == 'true') {
					$("#isFilter").parent(".checkbox").children("a").addClass("active");
					$("#isFilter").attr("checked", true);
				}
				
			});
	
	
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
				if(this.name != "attrValues"){
					if (o[this.name]) {
						if (!o[this.name].push) {
							o[this.name] = [ o[this.name] ];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				}	
			});

			return o;
		};
		
		function formToJsonOnlyAttValue(form) {
			var o = {};
			var a = $(form).serializeArray();
			$.each(a, function() {
				if(this.name == "attrValues" || this.name == "lang"){
					if (o[this.name]) {
						if (!o[this.name].push) {
							o[this.name] = [ o[this.name] ];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				}	
			});

			return o;
		};
		
		
		function imageCheck(){
			var result = false;
			var thisId = "";
			var messageStr = "";
			var thisValue = "";
			var thisImageValue = "";
			var lastStr = "";
			$("input[id*='attrValue']").each(function(){
				thisId = $(this).attr("id");
				thisValue = $(this).val();
				lastStr = thisId.substr(thisId.length-1);
				
				if(lastStr == "0"){
					lastStr = "10";
				}
				
				if(thisValue != ""){
					//console.info("get name = "+lastStr);
					thisImageValue = $("#imageUrl"+lastStr).val();
					if(thisImageValue == ""){
						messageStr = "Values_"+lastStr+": <spring:message code="info.product.category.005" arguments="" argumentSeparator=","/>";
						result = false;
					}else{
						result = true;	
					}
				}else{
					result = true;
				}
				if(!result){
					alert(messageStr);
					return false;
				}
			});
			return result;
		}
		
		function editSkuAttr(){
			var forms = $(".tab_box form");
			var isFilter;
			if (document.getElementById("isFilter").checked == true) {
				isFilter = true;
			} else {
				isFilter = false;
			}
			
			var len = forms.length;
			var skuAttrs = '[';
			$.each(forms, function(i, form) {
				if (i == len - 1) {
					skuAttrs += JSON.stringify(formToJson(form));
				} else {
					skuAttrs += JSON.stringify(formToJson(form));
					skuAttrs += ",";
				}
			});
			skuAttrs += "]";
			
			var attrValues = '[';
			$.each(forms, function(i, form) {
				if (i == len - 1) {
					attrValues += JSON.stringify(formToJsonOnlyAttValue(form));
				} else {
					attrValues += JSON.stringify(formToJsonOnlyAttValue(form));
					attrValues += ",";
				}
			});
			attrValues += "]";
			

			var imageUrls = new Array();
			
			imageUrlInputs = $("input[name='valuesImageUrl']");
			
			$.each(imageUrlInputs, function(i,item){      
				imageUrls.push( $(item).val()); 
			  }); 
			
			var result = imageCheck();
			
			if(result){
				$("#errorMessage").html("");
				if ($("#editSkuAttrForm").valid()) {
				$.post(
							"editSkuAttr.action",
							$("#editSkuAttrForm").serialize()+"&isFilter="+isFilter+ "&skuAttrs="
									+ skuAttrs+"&attrValues="+attrValues+"&imageUrls="+imageUrls,
							function(data) {
								var result = eval(data);
								alert(result.msg);

								var editHtml = "";
								if (result.isSuccess == 1) {

									editHtml = 
											 "<td class='tn_c'><span class='edit video_new' onclick='editSkuAttrPage("
											+ result.skuAttr.categorySKUAttrMetadataId
											+ ")'></span>"
											+ "<span class='del' onclick='delSkuAttr(this,"
											+ result.skuAttr.categorySKUAttrMetadataId
											+ ")'></span></td>"
											+ "<td>"
											+ result.skuAttr.categorySkuAttrName
											+ "</td>"
											+ "<td class='tn_c'>"
											+ result.skuAttr.isFilter
											+ "</td>"
											+ "<td class='last tn_c'>"
											+ result.skuAttr.seqNo
											+ "</td>"
											;
									$('#'+result.skuAttr.categorySKUAttrMetadataId, parent.document).html(editHtml);
								}
								var index = parent.layer
										.getFrameIndex(window.name);
								//parent.location.reload();
								parent.layer.close(index);

							}, "json");
				} else {
					alert($("#errorMessage").html());
				}
			}
			
		}
		
		function openImgPicker(masterdataId,elementId,isMulti){
		    $.layer({
		        type: 2,
		        title: 'Please Select',
		        maxmin: false,
		        shadeClose: true, //开启点击遮罩关闭层
		        area : ['680px' , '450px'],
				shadeClose: false,
		        offset : [($(window).height() - 300)/2+'px', ''],
		        iframe: {src: '<%=request.getContextPath()%>/image/unlimited/imagepicker.action?fileType='+masterdataId+'&elementId='+elementId+'&isMulti='+isMulti}
		    
			});
		}
		
	</script>

</body>
</html>


