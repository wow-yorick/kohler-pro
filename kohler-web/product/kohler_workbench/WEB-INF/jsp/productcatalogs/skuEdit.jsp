<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>New</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		var isDefault = '${skuMetadataEntity.isDefault}';
		if (isDefault == 'true') {
			$("#isDefault").parent(".checkbox").children("a").addClass("active");
			$("#isDefault").prop("checked", true);
			$("#hIsDefault").prop("checked", true);
		}
	});
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	$('#closebtn').on('click', function() {
		parent.layer.close(index); //执行关闭
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
	
	function openSkuPicker(elementId,elementName){
	    $.layer({
	        type: 2,
	        title: 'Please Select SKU',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['520px' , '400px'],
			shadeClose: false,
	        offset : [($(window).height() - 300)/2+'px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/skupicker/unlimited/skuPickerList.action?elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
	function editSKU() {
		var forms = $(".tab_box form");
		var len = forms.length;
		var skus = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				skus += JSON.stringify(formToJson(form));
			} else {
				skus += JSON.stringify(formToJson(form));
				skus += ",";
			}
		});
		skus += "]";
		//SKUATTR
		var attrForms = $(".skuAttrForm");
		var skuAttrs = '[';
		$.each(attrForms, function(i, form) {
			if (i == attrForms.length - 1) {
				skuAttrs += JSON.stringify(formToJson(form));
			} else {
				skuAttrs += JSON.stringify(formToJson(form));
				skuAttrs += ",";
			}
		});
		skuAttrs += "]";
		if ($("#isDefault").prop("checked") == true) {
			$("#hIsDefault").prop("checked", true);
		} else {
			$("#hIsDefault").prop("checked", false);
		}
		
		//11.24whh skuaccessory值
		var jsonData = "[";
		$('.skuaccessoryform').each(function(){
			var langsel = "";
			
			$(this).find('input[type=hidden]').each(function(){
				var thisvalue = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+thisvalue + "&";
			});
			
			var thislang = $(this).find('input[name=lang]').val();
			$(this).find("textarea").each(function(){
				var thisvalue = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+thisvalue + "&";
				
			});
			
			langsel=langsel.substring(0,langsel.length-1);
			jsonData += '{"lang":"'+encodeURIComponent(langsel)+'"},';
		}); 
		if(jsonData!="["){
			jsonData = jsonData.substring(0,jsonData.length-1);
		}
		jsonData += "]";
		
		$.post("updateSKU.action", $("#editSKUForm").serialize() + "&skus=" + skus
				+ "&skuAttrs=" + skuAttrs + "&accessoryvalues=" + jsonData, function(data) {
			var result = eval(data);
			alert(result.msg);
			//console.log(window.parent);
			$.get('<%=request.getContextPath()%>/sku/unlimited/SKUList.action?productMetadataId=${productMetadataId}&categoryMetadataId=${categoryMetadataId}',function(data){
				var index = parent.layer.getFrameIndex(window.name);
				parent.$("#SKUList").html(data);
				parent.layer.close(index);
			});
			//var index = parent.layer.getFrameIndex(window.name);
			//parent.location.reload();
	//		window.parent.reloadDiv();
			//parent.layer.close(index);
		}, "json");
	}
</script>
</head>
<body>
	<div class="container queryConditions product_layer">
		<div class="shadow"></div>
		<div class="main">
			<div class="search">
				<form id="editSKUForm">
					<input type="hidden" name="productMetadataId" value="${productMetadataId}" /> 
					<input type="hidden" name="skuMetadataId" value="${skuMetadataEntity.skuMetadataId}" />
					<input type="checkbox" name="isDefault" id="hIsDefault" style="display: none;" />
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${skuMetadataEntity.skuMetadataId}</div>
						<div class="col-md-2 tn_c">SKU Code</div>
						<div class="col-md-4">
							<input type="text" name="skuCode" value="${skuMetadataEntity.skuCode}" />
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-md-12">Purchase Property</div>
				</div>
				<c:forEach items="${maplist}" var="map">
					<div class="row label">
						<form class="skuAttrForm">
							<input type="hidden" name="categorySKUAttrMetadataId" value="${map.categorySKUAttrMetadataId}" />
							<div class="col-md-2">${map.categorySKUName}</div>
							<c:forEach items="${map.skuAttrValuesList}" var="skuAttrValues">
								<c:forEach items="${skuAttrlist}" var="skuAttr">
									<c:if test="${map.categorySKUAttrMetadataId eq skuAttr.categorySKUAttrMetadataId}">
										<c:if test="${skuAttrValues.categorySKUAttrValuesMetadataId eq skuAttr.categorySKUAttrValuesMetadataId}">
											<input type="hidden" name="skuAttrId" value="${skuAttr.skuAttrId}"/>
										</c:if>
										<div class="col-md-1">
											<input type="radio" name="categorySKUAttrValuesMetadataId"
												value="${skuAttrValues.categorySKUAttrValuesMetadataId}" 
												<c:if test="${skuAttrValues.categorySKUAttrValuesMetadataId eq skuAttr.categorySKUAttrValuesMetadataId}">checked</c:if>	
											/>
										</div>
										<div class="col-md-1">
											<label>${skuAttrValues.attrValue}</label>
										</div>
									</c:if>
								</c:forEach>
							</c:forEach>
						</form>
					</div>
				</c:forEach>
				<div class="row">
					<div class="col-md-6">
						<div class="col-md-4">Default</div>
						<div class="col-md-2 tn_c">
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> <input type="checkbox"
									id="isDefault" class="this_dis" />
							</div>
						</div>
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
									<form id="lan">
										<input type="hidden" name="lang" value="${language.localeId}" />
										<c:forEach items="${skulist}" var="sku">
											<c:if test="${sku.lang eq language.localeId}">
												<input type="hidden" name="skuId" value="${sku.skuId}" />
												<div class="row">
													<div class="col-md-3 tn_l">Image Source</div>
													<div class="col-md-9">
														<select name="imageSource">
															<option></option>
															<option value="0" <c:if test="${sku.imageSource eq 0}">selected</c:if>>Internal</option>
															<option value="1" <c:if test="${sku.imageSource eq 1}">selected</c:if>>External</option>
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image URL</div>
													<div class="col-md-9">
														<input type="hidden" name="listImageId" value="${sku.listImageId}"/> 
														<input type="text" name="listImageUrl" value="${sku.listImageUrl}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image Alt</div>
													<div class="col-md-9">
														<input type="text" name="listImageAlt" value="${sku.listImageAlt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage1Id" value="${sku.detailImage1Id}"/> 
														<input type="text" name="detailImage1Url" value="${sku.detailImage1Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Alt" value="${sku.detailImage1Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage2Id" value="${sku.detailImage2Id}"/> 
														<input type="text" name="detailImage2Url" value="${sku.detailImage2Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Alt" value="${sku.detailImage2Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage3Id" value="${sku.detailImage3Id}"/> 
														<input type="text" name="detailImage3Url" value="${sku.detailImage3Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Alt" value="${sku.detailImage3Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage4Id" value="${sku.detailImage4Id}"/> 
														<input type="text" name="detailImage4Url" value="${sku.detailImage4Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Alt" value="${sku.detailImage4Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage5Id" value="${sku.detailImage5Id}"/> 
														<input type="text" name="detailImage5Url" value="${sku.detailImage5Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Alt" value="${sku.detailImage5Alt}"/>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</form>
									<div class="skuaccessoryform">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productaccessory-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" skuMetadataId="${skuMetadataEntity.skuMetadataId }"/>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box">
									<form id="lan">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<c:forEach items="${skulist}" var="sku">
											<c:if test="${sku.lang eq language.localeId}">
												<input type="hidden" name="skuId" value="${sku.skuId}" />
												<div class="row">
													<div class="col-md-3 tn_l">Image Source</div>
													<div class="col-md-9">
														<select name="imageSource">
															<option></option>
															<option value="0" <c:if test="${sku.imageSource eq 0}">selected</c:if>>Internal</option>
															<option value="1" <c:if test="${sku.imageSource eq 1}">selected</c:if>>External</option>
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image URL</div>
													<div class="col-md-9">
														<input type="hidden" name="listImageId" value="${sku.listImageId}"/> 
														<input type="text" name="listImageUrl" value="${sku.listImageUrl}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image Alt</div>
													<div class="col-md-9">
														<input type="text" name="listImageAlt" value="${sku.listImageAlt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage1Id" value="${sku.detailImage1Id}"/> 
														<input type="text" name="detailImage1Url" value="${sku.detailImage1Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Alt" value="${sku.detailImage1Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage2Id" value="${sku.detailImage2Id}"/> 
														<input type="text" name="detailImage2Url" value="${sku.detailImage2Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Alt" value="${sku.detailImage2Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage3Id" value="${sku.detailImage3Id}"/> 
														<input type="text" name="detailImage3Url" value="${sku.detailImage3Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Alt" value="${sku.detailImage3Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage4Id" value="${sku.detailImage4Id}"/> 
														<input type="text" name="detailImage4Url" value="${sku.detailImage4Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Alt" value="${sku.detailImage4Alt}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 URL</div>
													<div class="col-md-9">
														<input type="hidden" name="detailImage5Id" value="${sku.detailImage5Id}"/> 
														<input type="text" name="detailImage5Url" value="${sku.detailImage5Url}"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Alt" value="${sku.detailImage5Alt}"/>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</form>
									<div class="skuaccessoryform">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productaccessory-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" skuMetadataId="${skuMetadataEntity.skuMetadataId }"/>
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
					<div class="col-md-3">${skuMetadataEntity.createUser}</div>
					<div class="col-md-7">
						Creation Date
						<fmt:formatDate value="${skuMetadataEntity.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${skuMetadataEntity.modifyUser}</div>
					<div class="col-md-7">
						Modification Date
						<fmt:formatDate value="${skuMetadataEntity.modifyTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm" onclick="editSKU()">确定</a>
				<a href="#" class="cancel" id="closebtn">取消</a>
			</div>
		</div>
	</div>
</body>
</html>