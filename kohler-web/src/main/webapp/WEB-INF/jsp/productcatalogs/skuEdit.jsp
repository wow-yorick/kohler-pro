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
	$(function() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function() {
			parent.layer.close(index); //执行关闭
		});
		
		jQuery.validator.addMethod("isSKUCodeUnique", function(value, element) { 
			
			var ok = this.optional(element) ;
		   
		    if(element.value!=''){
			    $.ajax({
					url:"<%=request.getContextPath()%>/sku/unlimited/checkSKUCode.action",
					data:"skuCode="+element.value+"&skuMetadataId=${skuMetadataEntity.skuMetadataId}",
		            async:false,
		            type:'POST',
		            //dataType:"json",
				    success:function(data){
				    	ok = data.size==0?true:false;
					}
				});
		    }else{
		    	ok = true;
		    }
		    
		    return ok;
		}, "SKU Code has been existed!"); 
		
		$('.img-internal').hide();
		$('.img-external').hide();
		<c:forEach items="${skulist}" var="sku">
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_FILE_SOURCE&lang=${sku.lang}",function(data){
				var opt = '';
				$.each(data.result,function(name,value){
					if(value.masterdataMetadataId == '${sku.imageSource}'){
						opt += '<option selected="selected" value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}else{
						opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}
					
				});
				$('input[name=lang][value="${sku.lang}"]').parents('.box').find('select[name=imageSource]').html(opt);
				var fileSourceVal = '${sku.imageSource}';
				if(fileSourceVal=='10030001'){
					$('input[name=lang][value="${sku.lang}"]').parents('.box').find('.img-internal').show();
				}
				if(fileSourceVal=='10030002'){
					$('input[name=lang][value="${sku.lang}"]').parents('.box').find('.img-external').show();
				}
			});
		</c:forEach>
		
		$('select[name=imageSource]').on('change', function(){
			if($(this).val()=='10030001'){
				$(this).parents('.box').find('.img-internal').show();
				$(this).parents('.box').find('.img-external').hide();
			}else if($(this).val()=='10030002'){
				$(this).parents('.box').find('.img-internal').hide();
				$(this).parents('.box').find('.img-external').show();
			}else{
				$(this).parents('.box').find('.img-internal').hide();
				$(this).parents('.box').find('.img-external').hide();
			}
		});
	});
	
	function formToJson(form) {
		var o = {};
		var a = $(form).serializeArray();
		$.each(a, function() {
			var index = this.name.indexOf('notputsql_');
			if(index != 0){
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
	
	function openDataType(masterdataId,elementId,elementName){
	    $.layer({
	        type: 2,
	        title: 'PDF Select',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['680px' , '500px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/image/unlimited/imagepicker.action?isMulti=1&fileType='+masterdataId+'&elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
	function openSkuPicker(elementId,elementName){
	    $.layer({
	        type: 2,
	        title: 'Please Select SKU',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['680px' , '500px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/skupicker/unlimited/skuPickerList.action?elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
	function editSKU() {
		
		$("#errorMessage").html("");
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
		
		$("#editSKUForm").valid();
        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		
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
		
		$.post("updateSKU.action", $("#editSKUForm").serialize() + "&skus=" + encodeURIComponent(skus)
				+ "&skuAttrs=" + encodeURIComponent(skuAttrs) + "&accessoryvalues=" + jsonData, function(data) {
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
						<div class="col-md-2 tn_c required">SKU Code</div>
						<div class="col-md-4">
							<input type="text" name="skuCode" isSKUCodeUnique="true" required maxlength="25" value="${skuMetadataEntity.skuCode}" />
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-md-12">Purchase Property</div>
				</div>
				<table>
				<c:forEach items="${maplist}" var="map">
					<tr><td width="150px"  valign="top">
						${map.categorySKUName}
						</td>
						<td width="500px">
						<form class="skuAttrForm">
							<input type="hidden" name="categorySKUAttrMetadataId" value="${map.categorySKUAttrMetadataId}" />
							<div class="row label">
							<c:forEach items="${map.skuAttrValuesList}" var="skuAttrValues">
								<c:forEach items="${skuAttrlist}" var="skuAttr">
									<c:if test="${map.categorySKUAttrMetadataId eq skuAttr.categorySKUAttrMetadataId}">
										<c:if test="${skuAttrValues.categorySKUAttrValuesMetadataId eq skuAttr.categorySKUAttrValuesMetadataId}">
											<input type="hidden" name="skuAttrId" value="${skuAttr.skuAttrId}"/>
										</c:if>
										<div class="col-md-2">
											<input type="radio" name="categorySKUAttrValuesMetadataId"
												value="${skuAttrValues.categorySKUAttrValuesMetadataId}" 
												<c:if test="${skuAttrValues.categorySKUAttrValuesMetadataId eq skuAttr.categorySKUAttrValuesMetadataId}">checked</c:if>	
											/>
										</div>
										<div class="col-md-4">
											<label>${skuAttrValues.attrValue}</label>
										</div>
									</c:if>
								</c:forEach>
							</c:forEach>
							</div>
						</form>
					</td></tr>
				</c:forEach>
				</table>
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
								<div class="box" id="divlan${status.index}">
									<form id="lan${status.index }">
										<input type="hidden" name="lang" value="${language.localeId}" />
										<c:forEach items="${skulist}" var="sku">
											<c:if test="${sku.lang eq language.localeId}">
												<input type="hidden" name="skuId" value="${sku.skuId}" />
												<div class="row">
													<div class="col-md-3 tn_l required">Image Source</div>
													<div class="col-md-9">
														<select name="imageSource" required>
															<option></option>
															<option value="0" <c:if test="${sku.imageSource eq 0}">selected</c:if>>Internal</option>
															<option value="1" <c:if test="${sku.imageSource eq 1}">selected</c:if>>External</option>
														</select>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l required">List Image URL</div>
													<div class="col-md-9">
														<input type="text" name="listImageUrl" value="${sku.listImageUrl}" required maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l required">List Image Id</div>
													<div class="col-md-7 border">
														<input type="hidden"  name="listImageId" id="hiddenlistImageId${language.localeId }" value="${sku.listImageId}" /> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" required name="notputsql_showlistImageName${language.localeId }" readonly="readonly" id="showlistImageName${language.localeId }" value="${imagefile.listImageId }" />
															</c:if>
														</c:forEach>
													</div>
													<div class='col-md-2 border'>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddenlistImageId${language.localeId }","showlistImageName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image Alt</div>
													<div class="col-md-9">
														<input type="text" name="listImageAlt" value="${sku.listImageAlt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external ">
													<div class="col-md-3 tn_l required">Detail Image1 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Url" value="${sku.detailImage1Url}" required maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l required">Detail Image1 Id</div>
													<div class="col-md-7 border">
														<input type="hidden"  name="detailImage1Id"  value="${sku.detailImage1Id}" id="hiddendetailImage1Id${language.localeId }"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" required name="notputsql_showdetailImage1Name${language.localeId }" readonly="readonly" id="showdetailImage1Name${language.localeId }" value="${imagefile.detailImage1Id }" />
															</c:if>
														</c:forEach>
													</div>
													<div class='col-md-2 border'>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage1Id${language.localeId }","showdetailImage1Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Alt" value="${sku.detailImage1Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image2 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Url" value="${sku.detailImage2Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image2 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage2Id" value="${sku.detailImage2Id}" id="hiddendetailImage2Id${language.localeId }"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage2Name${language.localeId }" value="${imagefile.detailImage2Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage2Id${language.localeId }","showdetailImage2Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Alt" value="${sku.detailImage2Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image3 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Url" value="${sku.detailImage3Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image3 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage3Id" value="${sku.detailImage3Id}" id="hiddendetailImage3Id${language.localeId }"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage3Name${language.localeId }" value="${imagefile.detailImage3Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage3Id${language.localeId }","showdetailImage3Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Alt" value="${sku.detailImage3Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image4 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Url" value="${sku.detailImage4Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image4 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage4Id" value="${sku.detailImage4Id}" id="hiddendetailImage4Id${language.localeId }"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage4Name${language.localeId }" value="${imagefile.detailImage4Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage4Id${language.localeId }","showdetailImage4Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Alt" value="${sku.detailImage4Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image5 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Url" value="${sku.detailImage5Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image5 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage5Id" value="${sku.detailImage5Id}" id="hiddendetailImage5Id${language.localeId }"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage5Name${language.localeId }" value="${imagefile.detailImage5Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage5Id${language.localeId }","showdetailImage5Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Alt" value="${sku.detailImage5Alt}" maxlength="250"/>
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
								<div class="hide box" id="divlan${status.index}">
									<form id="lan${status.index }">
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
												<div class="row img-external">
													<div class="col-md-3 tn_l">List Image URL</div>
													<div class="col-md-9">
														<input type="text" name="listImageUrl" value="${sku.listImageUrl}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">List Image Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="listImageId" id="hiddenlistImageId${language.localeId }" value="${sku.listImageId}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showlistImageName${language.localeId }" value="${imagefile.listImageId }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddenlistImageId${language.localeId }","showlistImageName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">List Image Alt</div>
													<div class="col-md-9">
														<input type="text" name="listImageAlt" value="${sku.listImageAlt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image1 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Url" value="${sku.detailImage1Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image1 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage1Id" id="hiddendetailImage1Id${language.localeId }" value="${sku.detailImage1Id}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage1Name${language.localeId }" value="${imagefile.detailImage1Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage1Id${language.localeId }","showdetailImage1Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image1 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage1Alt" value="${sku.detailImage1Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image2 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Url" value="${sku.detailImage2Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image2 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage2Id" id="hiddendetailImage2Id${language.localeId }" value="${sku.detailImage2Id}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage2Name${language.localeId }" value="${imagefile.detailImage2Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage2Id${language.localeId }","showdetailImage2Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image2 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage2Alt" value="${sku.detailImage2Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image3 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Url" value="${sku.detailImage3Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image3 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage3Id" id="hiddendetailImage3Id${language.localeId }" value="${sku.detailImage3Id}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage3Name${language.localeId }" value="${imagefile.detailImage3Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage3Id${language.localeId }","showdetailImage3Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image3 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage3Alt" value="${sku.detailImage3Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image4 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Url" value="${sku.detailImage4Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image4 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage4Id" id="hiddendetailImage4Id${language.localeId }" value="${sku.detailImage4Id}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage4Name${language.localeId }" value="${imagefile.detailImage4Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage4Id${language.localeId }","showdetailImage4Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image4 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage4Alt" value="${sku.detailImage4Alt}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-external">
													<div class="col-md-3 tn_l">Detail Image5 URL</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Url" value="${sku.detailImage5Url}" maxlength="250"/>
													</div>
												</div>
												<div class="row img-internal">
													<div class="col-md-3 tn_l">Detail Image5 Id</div>
													<div class="col-md-7 border">
														<input type="hidden" name="detailImage5Id" id="hiddendetailImage5Id${language.localeId }" value="${sku.detailImage5Id}"/> 
														<c:forEach items="${imagelist}" var="imagefile" >
															<c:if test="${imagefile.lang eq language.localeId}">
																<input type="text" readonly="readonly" id="showdetailImage5Name${language.localeId }" value="${imagefile.detailImage5Id }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class="col-md-2 border">
														<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage5Id${language.localeId }","showdetailImage5Name${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Detail Image5 Alt</div>
													<div class="col-md-9">
														<input type="text" name="detailImage5Alt" value="${sku.detailImage5Alt}" maxlength="250"/>
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
			<div id="errorMessage" style="display: none"></div>
		</div>
	</div>
</body>
</html>