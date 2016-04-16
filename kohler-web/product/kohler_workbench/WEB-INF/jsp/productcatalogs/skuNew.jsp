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
	
	function addSKU() {
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
		
		$.post("saveSKU.action", $("#addSKUForm").serialize() + "&skus=" + skus + "&skuAttrs="+skuAttrs+"&accessoryvalues=" + jsonData, 
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
						<div class="col-md-2 tn_c">SKU Code</div>
						<div class="col-md-4">
							<input type="text" name="skuCode" />
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
								<div class="col-md-1">
									<input type="radio" name="categorySKUAttrValuesMetadataId"
											value="${skuAttrValues.categorySKUAttrValuesMetadataId}" />
								</div>
								<div class="col-md-1">
									<label>${skuAttrValues.attrValue}</label>
								</div>
							</c:forEach>
						</form>
					</div>
				</c:forEach>
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
								<div class="box">
									<form id="lan">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-3 tn_l">Image Source</div>
											<div class="col-md-9">
												<select name="imageSource">
													<option></option>
													<option value="0">Internal</option>
													<option value="1">External</option>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image URL</div>
											<div class="col-md-9">
												<input type="hidden" name="listImageId"/>
												<input type="text" name="listImageUrl" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image Alt</div>
											<div class="col-md-9">
												<input type="text" name="listImageAlt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage1Id"/>
												<input type="text" name="detailImage1Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage2Id"/>
												<input type="text" name="detailImage2Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage3Id"/>
												<input type="text" name="detailImage3Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage4Id"/>
												<input type="text" name="detailImage4Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage5Id"/>
												<input type="text" name="detailImage5Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Alt" />
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
								<div class="hide box">
									<form id="lan">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-3 tn_l">Image Source</div>
											<div class="col-md-9">
												<select name="imageSource">
													<option></option>
													<option value="0">Internal</option>
													<option value="1">External</option>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image URL</div>
											<div class="col-md-9">
												<input type="hidden" name="listImageId"/>
												<input type="text" name="listImageUrl" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">List Image Alt</div>
											<div class="col-md-9">
												<input type="text" name="listImageAlt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage1Id"/>
												<input type="text" name="detailImage1Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image1 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage1Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage2Id"/>
												<input type="text" name="detailImage2Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image2 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage2Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage3Id"/>
												<input type="text" name="detailImage3Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image3 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage3Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage4Id"/>
												<input type="text" name="detailImage4Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image4 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage4Alt" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 URL</div>
											<div class="col-md-9">
												<input type="hidden" name="detailImage5Id"/>
												<input type="text" name="detailImage5Url" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">Detail Image5 Alt</div>
											<div class="col-md-9">
												<input type="text" name="detailImage5Alt" />
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
		</div>
	</div>
</body>
</html>