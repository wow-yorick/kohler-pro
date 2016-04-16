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
			<form id="addSkuAttrForm">
				<input type="hidden" name="categoryMetadataId"
					value="${categoryMetadataId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp</div>
						<div class="col-md-2 required">key name</div>
						<div class="col-md-4">
							<input type="text" name="keyName" required="required"
													maxlength="25" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">SeqNo</div>
						<div class="col-md-4">
							<input type="text" name="seqNo" maxlength="8" digits="true" />
						</div>
						-
						<div class="col-md-4 tn_c">Is Filter</div>
						<div class="col-md-1">
							<!-- <span class="check"></span> -->
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> <input type="checkbox"
									name="isFilter" id="isFilter" />
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
										<form name="lan${status.index}" id="lan${status.index}">
											<input type="hidden" name="lang"
												value="${language.localeId }" />
											<div class="row">
												<div class="col-md-3 required">Name_${language.localeCode}</div>
												<div class="col-md-9">
													<input type="text" name="categorySkuAttrName" required="required"
													maxlength="25" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-5 tn_c">
													<label style="margin-left:15px;">Name_${language.localeCode}</label>
												</div>
												
											</div>
														<div class="row">
															<div class="col-md-3 tn_l">Values_1</div>
															<div class="col-md-9">
																<input type="text"  name="attrValues" id="attrValue1"  />
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
														
											<div class="row higher">
												<div class="col-md-3">Image Icon Source</div>
												<div class="col-md-9">
													<select id="selectChoose" name="imageSource">
														<option value="0">External</option>
														<option value="1">Internal</option>
													</select>
												</div>
											</div>
											<div class="row imgUrl_one">
												<div class="col-md-3">Image URL</div>
												<div class="col-md-9">
													<input type="text" name="imageUrl" />
												</div>
											</div>
											<div class="row imgUrl_two" style="display:none;">
													<div class="col-md-3 ">Image URL</div>
													<div class="col-md-6 smaller">
														<input type="text" name="imageId" id="imageId${language.localeId }" />
													</div>
													<div class="col-md-3">
														<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageId${language.localeId }','1')" >Choose</a>
													</div>
												</div>
											<div class="row">
												<div class="col-md-3">Tooltip</div>
												<div class="col-md-9">
													<input type="text" name="imageTooltip" />
												</div>
											</div>
										</form>
									</div>

								</c:when>
								<c:otherwise>
									<div class="hide box">
										<div class="box">
											<form name="lan${status.index}" id="lan${status.index}">
												<input type="hidden" name="lang"
													value="${language.localeId }" />
												<div class="row">
													<div class="col-md-3 ">Name_${language.localeCode }</div>
													<div class="col-md-9">
														<input type="text" name="categorySkuAttrName" maxlength="25" />
													</div>
												</div>
												<div class="row">
													<div class="col-md-5 tn_c">
														<label style="margin-left:15px;">Name</label>
													</div>
													
												</div>
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
												<div class="row higher">
													<div class="col-md-3">Image Icon Source</div>
													<div class="col-md-9">
														<select name="imageSource">
															<option value="0">External</option>
															<option value="1">Internal</option>
														</select>
													</div>
												</div>
												<div class="row imgUrl_one">
													<div class="col-md-3">Image URL</div>
													<div class="col-md-9">
														<input type="text" name="imageUrl" />
													</div>
												</div>
												<div class="row imgUrl_two" style="display:none;">
													<div class="col-md-3 ">Image URL</div>
													<div class="col-md-6 smaller">
														<input type="text" name="imageId" id="imageId${language.localeId }" />
													</div>
													<div class="col-md-3">
														<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageId${language.localeId }','1')" >Choose</a>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3">Tooltip</div>
													<div class="col-md-9">
														<input type="text" name="imageTooltip" />
													</div>
												</div>
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
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id = "imageUrl1" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl1','1')">Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id = "imageUrl2" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl2','1')">Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl3" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl3','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl4" readonly="readonly" class="disable"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl4','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl5" readonly="readonly" class="disable"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl5','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl6" readonly="readonly" class="disable"  />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl6','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl7" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl7','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl8" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl8','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl9" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl9','1')" >Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 border">
							<input type="text" name="valuesImageUrl" id="imageUrl10" readonly="readonly" class="disable" />
						</div>
						<div class="col-md-4">
							<a href="javascript:void(0);" class="choose tn_c" onclick = "openImgPicker('${masterDataId}','imageUrl10','1')">Choose</a>
						</div>
					</div>

				</div>
				<div class="clear"></div>
				
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
						onclick="addSkuAttr()">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
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
			
			
			function addSkuAttr() {
				var forms = $(".tab_box form");
				var len = forms.length;
				var isFilter;
				if (document.getElementById("isFilter").checked == true) {
					isFilter = true;
				} else {
					isFilter = false;
				}
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
				
				
				//console.info($(".tab_box form").valid());
				
				var imageUrls = new Array();
				
				imageUrlInputs = $("input[name='valuesImageUrl']");
				
				$.each(imageUrlInputs, function(i,item){      
					imageUrls.push( $(item).val()); 
				  }); 
				
				var result = imageCheck();
				console.info("check="+result);
				if(result){
				$("#errorMessage").html("");
				if ($("#addSkuAttrForm").valid()) {
				$.post("saveSkuAttr.action",
								$("#addSkuAttrForm").serialize()+"&isFilter="+isFilter+ "&skuAttrs="
										+ skuAttrs+"&attrValues="+attrValues+"&imageUrls="+imageUrls,
								function(data) {
									var result = eval(data);
									alert(result.msg);

									var addHtml = "";
									if (result.isSuccess == 1) {

										addHtml = "<tr id = '"+result.skuAttr.categorySKUAttrMetadataId+"'>"
												+ "<td class='tn_c'><span class='edit video_new' onclick='editSkuAttrPage("
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
												+ "</td>" + "</tr>";
									}
									$('#skuAttrTable', parent.document).append(
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
	</div>
</body>
</html>


