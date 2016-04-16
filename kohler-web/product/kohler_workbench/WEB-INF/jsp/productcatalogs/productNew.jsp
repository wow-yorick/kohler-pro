<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create Product</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name);
	$('#closebtn').on('click', function() {
		parent.layer.close(index);
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
		if (document.getElementById("isCombineProduct").checked == true) {
			o['isCombineProduct'] = true;
		} else {
			o['isCombineProduct'] = false;
		}
		if (document.getElementById("isDiscontinue").checked == true) {
			o['isDiscontinue'] = true;
		} else {
			o['isDiscontinue'] = false;
		}
		o['combineProductMetadataId'] = $("#combineProductMetadataId").val();
		return o;
	};
	
	function openDataType(masterdataId,elementId,isMulti){
	    $.layer({
	        type: 2,
	        title: 'Please Select',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['750px' , '520px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/image/unlimited/imagepicker.action?fileType='+masterdataId+'&elementId='+elementId+'&isMulti='+isMulti}
	    
		});
	}
	
	function openSkuPicker(elementId,isMulti,elementName){
	    $.layer({
	        type: 2,
	        title: 'Please Select SKU',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['750px' , '520px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/skupicker/unlimited/skuPickerList.action?elementId='+elementId+'&isMulti='+isMulti+'&elementName='+elementName}
	    
		});
	}
	
	function openProductPicker(elementId,isMulti,elementName){
	    $.layer({
	        type: 2,
	        title: 'Please Select Product',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['750px' , '520px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/productpicker/unlimited/init.action?elementId='+elementId+'&isMulti='+isMulti+'&elementName='+elementName}
	    
		});
	}
	
	function addProduct() {
	    $("#errorMessage").html("");
		var forms = $(".tab_box form");
		var len = forms.length;
		var products = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				products += JSON.stringify(formToJson(form));
			} else {
				products += JSON.stringify(formToJson(form));
				products += ",";
			}
			
			if (!$("#lan"+i).is(":visible")) {
				// form 所属的div让它暂时显示 （为了验证)。
				document.getElementById( "divlan" + i).style.display= "block";
				$("#lan"+i).valid();
				// 验证结束后， 把div 重新设定为不可视。
				document.getElementById( "divlan" + i).style.display= "none";
			} else {
				$("#lan"+i).valid();
			}
		});
		products += "]";
		
		//11.12whh comattr值
		var jsonData = "[";
		$('.comattr').each(function(){
			var langsel = "lang="+$(this).find('input[name=lang]').val()+"&";
			$(this).find('input[type=text]').each(function(){
				var search = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+search + "&";
			}); 
			$(this).find('input[type=hidden]').each(function(){
				var search = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+search + "&";
			});
			$(this).find('select').each(function(){
				var search = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+search + "&";
			}); 
			var thislang = $(this).find('input[name=lang]').val();
			$(this).find("textarea").each(function(){
				if($(this).attr("class")=='richtext'){
					langsel += $(this).attr("name")+"="+encodeURIComponent(eval('editor_'+$(this).attr("name").replace(/[ ]/g,"")+thislang).getContent())+ "&";
				}else{
					var search = encodeURIComponent($(this).val());
					langsel += $(this).attr('name') +"="+search + "&";
				}
			});
			
			langsel=langsel.substring(0,langsel.length-1);
			jsonData += '{"lang":"'+encodeURIComponent(langsel)+'"},';
		}); 
		if(jsonData!="["){
			jsonData = jsonData.substring(0,jsonData.length-1);
		}
		jsonData += "]";
		$("#addProductForm").valid();

        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		$.post("createProductSave.action", $("#addProductForm").serialize()
				+ "&products=" + products + "&comattrs=" + jsonData, function(data) {
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
	<div class="container queryConditions product_layer">
		<div class="shadow"></div>
		<div class="main">
			<form id="addProductForm">
				<input type="hidden" name="productMetadataId"
					value="${productMetadataEntity.productMetadataId}" /> 
				<input type="hidden" name="categoryMetadataId"
					value="${categoryMetadataId}" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">
							<label>${productMetadataEntity.productMetadataId}</label>
						</div>
						<div class="col-md-2">Publish Date</div>
						<div class="col-md-4 laydate">
							<input class="laydate-icon" name="publishTime"
								readonly="readonly" required="true" onClick="laydate()">
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4 input_small">
							<input type="text" name="seqNo" onkeyup="checkNum(this)"
								onafterpaste="checkNum(this)" />
						</div>
						<div class="col-md-2">Category</div>
						<div class="col-md-4 ">
							<select name="categoryMetadataId" disabled="disabled">
								<option>${categoryEntity.categoryName}</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">Collection</div>
						<div class="col-md-4 select_small">
							<select name="collectionMetadataId">
								<option></option>
								<c:forEach items="${collections}" var="c">
									<option value="${c.collectionMetadataId}">${c.collectionName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">Product Code</div>
						<div class="col-md-4 ">
							<input type="text" name="productCode" />
						</div>
					</div>
				</div>
			</form>
			<table class="big topSmall" border="0" cellspacing="0" cellpadding="0" id="SKUList">
				<c:import url="/sku/unlimited/SKUList.action?productMetadataId=${productMetadataEntity.productMetadataId}&categoryMetadataId=${categoryMetadataId}"></c:import>
			</table>
			<div class="txt" id="txt">
				<div class="row">
					<div class="col-md-6">
						<div class="col-md-4">Combine with another product</div>
						<div class="col-md-1">
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> <input type="checkbox"
									class="this_dis" name="isCombineProduct" id="isCombineProduct">
							</div>
						</div>
					</div>
					<div class="col-md-6 new_togg">
						<div class="col-md-3">Product</div>
						<div class="col-md-6 border">
							<input type="hidden" name="combineProductMetadataId"
								id="combineProductMetadataId" /> <input type="text"
								name="combineProductName" />
						</div>
						<div class="col-md-3">
							<a href="#" class="choose tn_c">Choose</a>
						</div>
					</div>
				</div>
				<div class="col-md-12">
            		<div class="col-md-2">Is Discountiune</div>
					<div class="col-md-1">
						<div class="checkbox">
							<a href="#" class="ope_icon choo"></a> <input type="checkbox"
								name="isDiscontinue" id="isDiscontinue">
						</div>
					</div>
				</div>
			</div>
			<div class="tab" id="tab">
				<ul class="tab_menu">
					<c:forEach items="${languages}" var="language" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0 }">
								<li class="active tn_c bold">${language.localeName }</li>
							</c:when>
							<c:otherwise>
								<li class="tn_c bold">${language.localeName }</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<div class="tab_box">
					<c:forEach items="${languages}" var="language" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0 }">
								<div class="box" id="divlan${status.index}">
									<form name ="lan${status.index}" id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-2 tn_l">
												Product<br />Name_${language.localeCode}
											</div>
											<div class="col-md-10" style="position: relative;">
												<input type="text" required="true" name="productName" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">PC Template</div>
											<div class="col-md-10">
												<select name="pcTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">
												Mobile<br />Template
											</div>
											<div class="col-md-10">
												<select name="mobileTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (PC Website)</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title</div>
											<div class="col-md-4">
												<input type="text" name="seoTitlePC" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPC" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPC" required="true"/>
											</div>
										</div>
										<div class="row large">
											<div class="col-md-2">Description</div>
											<div class="col-md-10">
												<textarea rows="2" name="seoDescriptionPC"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile Website)</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobile"/>
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobile" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobile" />
											</div>
										</div>
										<div class="row large">
											<div class="col-md-2">Description</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobile"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">Properties</div>
										</div>
									</form>
									<div class="comattr">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productcomattr-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" />
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box" id="divlan${status.index}">
									<form name ="lan${status.index}" id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId}" />
										<div class="row">
											<div class="col-md-2 tn_l">
												Product<br />Name_${language.localeCode}
											</div>
											<div class="col-md-10" style="position: relative;">
												<input type="text" name="productName" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">PC Template</div>
											<div class="col-md-10">
												<select name="pcTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 tn_l">
												Mobile<br />Template
											</div>
											<div class="col-md-10">
												<select name="mobileTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (PC Website)</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title</div>
											<div class="col-md-4">
												<input type="text" name="seoTitlePC" required="true"/>
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagPC" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsPC" />
											</div>
										</div>
										<div class="row large">
											<div class="col-md-2">Description</div>
											<div class="col-md-10">
												<textarea rows="2" name="seoDescriptionPC"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">SEO (Mobile Website)</div>
										</div>
										<div class="row">
											<div class="col-md-2">Title</div>
											<div class="col-md-4">
												<input type="text" name="seoTitleMobile" />
											</div>
											<div class="col-md-2 tn_c">H1 Tag</div>
											<div class="col-md-4">
												<input type="text" name="seoH1tagMobile" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-2">Keywords</div>
											<div class="col-md-10">
												<input type="text" name="seoKeywordsMobile" />
											</div>
										</div>
										<div class="row large">
											<div class="col-md-2">Description</div>
											<div class="col-md-10">
												<textarea name="seoDescriptionMobile"></textarea>
											</div>
										</div>
										<div class="row seo bold">
											<div class="col-md-9 space">Properties</div>
										</div>
									</form>
									<div class="comattr">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productcomattr-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" />
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<div id="productPartList">
					<c:import url="/productPart/unlimited/productPartList.action?productMetadataId=${productMetadataEntity.productMetadataId}"></c:import>
				</div>
				<div id="productCADList">
					<c:import url="/productCAD/unlimited/productCADList.action?productMetadataId=${productMetadataEntity.productMetadataId}"></c:import>
				</div>
				<div id="productVideoList">
					<c:import url="/productVideo/unlimited/productVideoList.action?productMetadataId=${productMetadataEntity.productMetadataId}"></c:import>
				</div>
				<div id="productPDFList">
					<c:import url="/productPDF/unlimited/productPDFList.action?productMetadataId=${productMetadataEntity.productMetadataId}"></c:import>
				</div>
				<div class="text">
					<div class="row">
						<div class="col-md-3">Creator</div>
						<div class="col-md-3">${productMetadataEntity.createUser}</div>
						<div class="col-md-3">
							Creation Date
						</div>
						<div class="col-md-3">
							<fmt:formatDate value="${productMetadataEntity.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">Modifier</div>
						<div class="col-md-3">${productMetadataEntity.modifyUser}</div>
						<div class="col-md-3">
							Modification Date
						</div>
						<div class="col-md-3">
							<fmt:formatDate value="${productMetadataEntity.modifyTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</div>
				</div>
				<div class="btns">
					<a href="javascript:void(0);" class="confirm"
						onclick="addProduct()">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>
				
				<div id="errorMessage" style="display: none"></div>
			</div>
		</div>
	</div>
</body>
<script>
function reloadTable(){
	$.ajax({
		url:"../sku/unlimited/SKUList.action.action",
		data: {productMetadataId:"${productMetadataEntity.productMetadataId}",categoryMetadataId:"${categoryMetadataId}"},
		type:'post',
		success:function(data){
			/* var result = eval(data);
			if(result.status="0"){
				window.location.href="${base}/common/index.action";
			}else{
				alert(result.msg);
			} */
		},
	});
}
</script>
</html>
