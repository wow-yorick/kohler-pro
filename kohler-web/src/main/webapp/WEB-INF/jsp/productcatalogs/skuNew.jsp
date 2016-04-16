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
					data:"skuCode="+element.value,
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
		
		//$('.img-internal').hide();
		$('.img-external').hide();
		
		
		$.each($('select[name=imageSource]'),function(){
			var l = $(this).parents('.box').find('input[name=lang]').val();
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_FILE_SOURCE&lang="+l,function(data){
				var opt = '';
				$.each(data.result,function(name,value){
					opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
				});
				$('input[name=lang][value="'+l+'"]').parents('.box').find('select[name=imageSource]').html(opt);
			});
		});
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
	
	function addSKU() {
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
		var attrForms=$(".skuAttrForm");
		var skuAttrs='[';
		$.each(attrForms, function(i, form) {
			if (i == attrForms.length - 1) {
				skuAttrs += JSON.stringify(formToJson(form));
			} else {
				skuAttrs += JSON.stringify(formToJson(form));
				skuAttrs += ",";
			}
			
		});
		$("#addSKUForm").valid();
        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		
		skuAttrs += "]";
		if($("#isDefault").prop("checked") == true){
			$("#hIsDefault").prop("checked",true);
		}else{
			$("#hIsDefault").prop("checked",false);
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
		$.post("saveSKU.action", $("#addSKUForm").serialize() + "&skus=" + encodeURIComponent(skus) + "&skuAttrs="+encodeURIComponent(skuAttrs)+"&accessoryvalues=" + jsonData, 
				function(data) {
					var result = eval(data);
					alert(result.msg);
					
				//	parent.location.reload();
					
					$.get('<%=request.getContextPath()%>/sku/unlimited/SKUList.action?productMetadataId=${productMetadataId}&categoryMetadataId=${categoryMetadataId}',function(data){
						var index = parent.layer.getFrameIndex(window.name);
						parent.$("#SKUList").html(data);
						parent.layer.close(index);
					});
					//.append("<tr><td  class='tn_c'></td><td></td><td></td><td></td></tr>");
    				
				}, "json");
		}
</script>
</head>
<body>
	<div class="container queryConditions product_layer">
		<div class="shadow"></div>
		<div class="main">
			<div class="search">
				<form id="addSKUForm">
					<input type="hidden" name="productMetadataId" value="${productMetadataId}" /> 
					<%-- <input type="hidden" name="skuMetadataId" value="${skuMetadataEntity.skuMetadataId}" /> --%>
					<input type="checkbox" name="isDefault" id="hIsDefault" style="display: none;"/>
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp;</div>
						<div class="col-md-2 tn_c required">SKU Code</div>
						<div class="col-md-4">
							<input type="text" name="skuCode" maxlength="25" isSKUCodeUnique="1" required/>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="">Purchase Property</div>
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
							<c:forEach items="${map.skuAttrValuesList}" var="skuAttrValues" varStatus="attrstatus">
								<div class="col-md-2">
									<input type="radio" name="categorySKUAttrValuesMetadataId"
											value="${skuAttrValues.categorySKUAttrValuesMetadataId}" />
								</div>
								<div class="col-md-4">
									<label>${skuAttrValues.attrValue}</label>
								</div>
							</c:forEach>
							</div>
						</form>
					<!--  </div>-->
					</td></tr>
				</c:forEach>
				</table>
				<div class="row">
					<div class="col-md-6">
						<div class="col-md-4">Default</div>
						<div class="col-md-2 tn_c">
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> 
								<input type="checkbox" id="isDefault" class="this_dis"/>
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
									<form id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-3 tn_l required">Image Source</div>
											<div class="col-md-9">
												<select name="imageSource" required>
													
												</select>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l required">List Image URL</div>
											<div class="col-md-9">
												<input type="text" name="listImageUrl" required maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l required">List Image Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="listImageId" id="hiddenlistImageId${language.localeId }"/> 
												<input type="text" readonly="readonly" required name="notputsql_showlistImageName${language.localeId }" id="showlistImageName${language.localeId }" />
											</div>
											<div class='col-md-2 border'>
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddenlistImageId${language.localeId }","showlistImageName${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image Alt</div>
											<div class="col-md-9">
												<input type="text" name="listImageAlt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l required">Detail Image1 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Url" required maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l required">Detail Image1 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage1Id" id="hiddendetailImage1Id${language.localeId }"/> 
												<input type="text" required readonly="readonly" name="notputsql_showdetailImage1Name${language.localeId }" id="showdetailImage1Name${language.localeId }" />
											</div>
											<div class='col-md-2 border'>
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage1Id${language.localeId }","showdetailImage1Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image2 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image2 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage2Id" id="hiddendetailImage2Id${language.localeId }"/> 
												<input type="text" readonly="readonly"  id="showdetailImage2Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage2Id${language.localeId }","showdetailImage2Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image3 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image3 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage3Id" id="hiddendetailImage3Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage3Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage3Id${language.localeId }","showdetailImage3Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image4 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image4 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage4Id" id="hiddendetailImage4Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage4Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage4Id${language.localeId }","showdetailImage4Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image5 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image5 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage5Id" id="hiddendetailImage5Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage5Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage5Id${language.localeId }","showdetailImage5Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Alt" maxlength="250"/>
											</div>
										</div>
									</form>
									<div class="skuaccessoryform">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productaccessory-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" />
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box" id="divlan${status.index}">
									<form id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-3 tn_l">Image Source</div>
											<div class="col-md-9">
												<select name="imageSource">
													
												</select>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">List Image URL</div>
											<div class="col-md-9">
												<input type="text" name="listImageUrl" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">List Image Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="listImageId" id="hiddenlistImageId${language.localeId }"/> 
												<input type="text" id="showlistImageName${language.localeId }" readonly="readonly"/>
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddenlistImageId${language.localeId }","showlistImageName${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image Alt</div>
											<div class="col-md-9">
												<input type="text" name="listImageAlt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image1 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image1 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage1Id" id="hiddendetailImage1Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage1Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage1Id${language.localeId }","showdetailImage1Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image2 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image2 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage2Id" id="hiddendetailImage2Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage2Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage2Id${language.localeId }","showdetailImage2Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image3 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image3 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage3Id" id="hiddendetailImage3Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage3Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage3Id${language.localeId }","showdetailImage3Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image4 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image4 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage4Id" id="hiddendetailImage4Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage4Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage4Id${language.localeId }","showdetailImage4Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Alt" maxlength="250"/>
											</div>
										</div>
										<div class="row img-external">
											<div class="col-md-3 tn_l">Detail Image5 URL</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Url" maxlength="250"/>
											</div>
										</div>
										<div class="row img-internal">
											<div class="col-md-3 tn_l">Detail Image5 Id</div>
											<div class="col-md-7 border">
												<input type="hidden" name="detailImage5Id" id="hiddendetailImage5Id${language.localeId }"/> 
												<input type="text" readonly="readonly" id="showdetailImage5Name${language.localeId }" />
											</div>
											<div class="col-md-2 border">
												<a href='#'  class='choose tn_c' onclick='openDataType("10090001","hiddendetailImage5Id${language.localeId }","showdetailImage5Name${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Alt" maxlength="250"/>
											</div>
										</div>
									</form>
									<div class="skuaccessoryform">
										<input type="hidden" name="lang" value="${language.localeId }">
										<pageTag:productaccessory-edit categoryMetadataId="${categoryMetadataId}" lang="${language.localeId }" />
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
					<div class="col-md-3">sf</div>
					<div class="col-md-7">
						Creation Date
						2014-10-22 08:00
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">sf</div>
					<div class="col-md-7">
						Modification Date
						2014-10-22 08:00
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm" onclick="addSKU()">确定</a>
				<a href="#" class="cancel" id="closebtn">取消</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
	</div>
</body>
</html>