<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Common Attribute</title>
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
		//选中select里面的内容显示隐藏	
		$('#select').bind('change', function() {
			var $select = $("#select").find('option:selected').text();
			if ($select == '文本框') {
				$('#txt .droplist').css('display', 'none');
			} else {
				$('#txt .droplist').show();
			}
		});
		//imgurlchoose显示隐藏
		$("select[name='imageSources']").bind('change', function() {
			var $select2 = $(this).find('option:selected').text();
			if ($select2 == '内部') {
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
	<div class="container queryConditions product_layer commonAttribute">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
		<form id="editCommonAttrForm">
			<input type="hidden" name="categoryComAttrMetadataId"
					value="${categoryComAttrMetadataPojo.categoryComAttrMetadataId }" />
			<div class="search">
				<div class="row">
					<div class="col-md-2">Id</div>
					<div class="col-md-4">${categoryComAttrMetadataPojo.categoryComAttrMetadataId }</div>
					<div class="col-md-2">key name</div>
					<div class="col-md-4">
						<input type="text" name = "keyName" value = "${categoryComAttrMetadataPojo.keyName }" />
					</div>
				</div>
			</div>
			<div class="tab" id="tab">
				<ul class="tab_menu bold">
					<c:forEach items="${categoryComAttrPojos}" var="comAttr" varStatus="status">
						<c:choose>
							<c:when test="${comAttr.language.isDefault == true }">
								<li class="active tn_c">${comAttr.language.localeName}</li>
							</c:when>
							<c:otherwise>
								<li class="tn_c">${comAttr.language.localeName }</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
				<div class="tab_box">
					<c:forEach items="${categoryComAttrPojos}" var="comAttr" varStatus="status">
						<c:choose>
							<c:when test="${comAttr.language.isDefault == true }">
								<div class="box">
								<input type="hidden" name="categoryComAttrIds"
											value="${comAttr.categoryComAttr.categoryComAttrId }" />
									<div class="row">
										<div class="col-md-2 tn_l">Name_${comAttr.language.localeCode }</div>
										<div class="col-md-6">
											<input type="text" name = "categoryComAttrNames" value = "${comAttr.categoryComAttr.categoryComAttrName }" />
										</div>
									</div>
									<div class="row higher">
										<div class="col-md-2">Image Icon Source_${comAttr.language.localeCode }</div>
										<div class="col-md-6">
											<select id="selectChoose" name = "imageSources" value="${comAttr.categoryComAttr.imageSource}" >
												<option value="">--select--</option>
												<c:forEach items="${typleFile}" var="typeF">
	                       							<option value="${typeF.masterdataId}">${typeF.masterdataName}</option>
                								</c:forEach>
											</select>
										</div>
									</div>
									<div class="row imgUrl_one">
										<div class="col-md-2">Image URL_${comAttr.language.localeCode }</div>
										<div class="col-md-10">
											<input type="text" name = "imageUrls" value = "${comAttr.categoryComAttr.imageUrl }" />
										</div>
									</div>
									<div class="row imgUrl_two" style="display:none;">
										<div class="col-md-2 ">Image URL</div>
										<div class="col-md-9 smaller">
											<input type="text" name = "imageIds" id="imageId${language.localeCode }" readonly="readonly" value = "${comAttr.categoryComAttr.imageId }" />
										</div>
										<div class="col-md-1">
											<a href="javascript:void(0);" class="choose tn_c"
											onclick="openImgPicker('${masterDataId}','imageId${language.localeCode }','1')">Choose</a>
										</div>
									</div>
									<div class="row">
										<div class="col-md-2">Tooltip_${comAttr.language.localeCode }</div>
										<div class="col-md-10">
											<input type="text" name = "imageTooltips" value = "${comAttr.categoryComAttr.imageTooltip }" />
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box">
									<input type="hidden" name="categoryComAttrIds"
											value="${comAttr.categoryComAttr.categoryComAttrId }" />
									<div class="row">
										<div class="col-md-2 tn_l">Name_${comAttr.language.localeCode }</div>
										<div class="col-md-6">
											<input type="text" name = "categoryComAttrNames" value = "${comAttr.categoryComAttr.categoryComAttrName }" />
										</div>
									</div>
									<div class="row higher">
										<div class="col-md-2">Image Icon Source_${comAttr.language.localeCode }</div>
										<div class="col-md-6">
											<select id="selectChoose" name = "imageSources" value = "${comAttr.categoryComAttr.imageSource }" >
												<option value="">--select--</option>
												<c:forEach items="${typleFile}" var="typeF">
	                       							<option value="${typeF.masterdataId}">${typeF.masterdataName}</option>
                								</c:forEach>
											</select>
										</div>
									</div>
									<div class="row imgUrl_one">
										<div class="col-md-2">Image URL_${comAttr.language.localeCode }</div>
										<div class="col-md-10">
											<input type="text" name = "imageUrls" value = "${comAttr.categoryComAttr.imageUrl }"/>
										</div>
									</div>
									<div class="row imgUrl_two" style="display:none;">
										<div class="col-md-2 ">Image URL</div>
										<div class="col-md-9 smaller">
											<input type="text" name = "imageIds" id="imageId${language.localeCode }" readonly="readonly" value = "${comAttr.categoryComAttr.imageId }" />
										</div>
										<div class="col-md-1">
											<a href="javascript:void(0);" class="choose tn_c"
											onclick="openImgPicker('${masterDataId}','imageId${language.localeCode }','1')">Choose</a>
										</div>
									</div>
									<div class="row">
										<div class="col-md-2">Tooltip_${comAttr.language.localeCode }</div>
										<div class="col-md-10">
											<input type="text" name = "imageTooltips" value = "${comAttr.categoryComAttr.imageTooltip }" />
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			
			<div class="txt" id="txt">
				<div class="row">
					<div class="col-md-2">SeqNo</div>
					<div class="col-md-6 ">
						<input type="text" name = "seqNo" value = "${categoryComAttrMetadataPojo.seqNo }" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">Input Type</div>
					<div class="col-md-6">
						<select id="select" name = "inputType" value = "${categoryComAttrMetadataPojo.inputType }">
								<c:forEach items="${typeList}" var="allType">
	                       			<option value="${allType.masterdataId}">${allType.masterdataName}</option>
                				</c:forEach>
						</select>
					</div>
				</div>
				<div class="row droplist">
					<div class="row">
						<div class="col-md-2">Is Filter</div>
						<div class="col-md-1">
							<!-- <span class="check"></span> -->
							<div class="checkbox">
								<a href="#" class="ope_icon choo"></a> <input type="checkbox" name = "isFilter" value = "${categoryComAttrMetadataPojo.isFilter }" >
							</div>
						</div>
					</div>
					<div class="col-md-12 higher">
						<div class="col-md-2">Dropdown Typecode</div>
						<div class="col-md-6">
							<select name = "dropdownTypeCode" value = "${categoryComAttrMetadataPojo.dropdownTypeCode }">
								<option value="">--select--</option>
								<c:forEach items="${dropDownType}" var="dropDown">
	                       			<option value="${dropDown}">${dropDown}</option>
                				</c:forEach>
							</select>
						</div>
					</div>
				</div>
				
			</div>
			</div>
			<div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${categoryComAttrMetadataPojo.createUser }</div>
					<div class="col-md-7">Creation Date ${categoryComAttrMetadataPojo.createTime }</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${categoryComAttrMetadataPojo.modifyUser }</div>
					<div class="col-md-7">Modification Date ${categoryComAttrMetadataPojo.modifyTime }</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm" onclick = "editCommonAttr();">Save</a> <a href="#" class="cancel"
					id="closebtn">Cancel</a>
			</div>
			</form>
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
								
								var $select = $(this).find('option:selected').text();
								if ($select == '文本框') {
									$('#txt .droplist').css('display', 'none');
								} else {
									$('#txt .droplist').show();
								}
								
								var $select2 = $(this).find('option:selected').text();
								if ($select2 == '内部') {
									$('.imgUrl_one').hide();
									$('.imgUrl_two').show();
								} else {
									$('.imgUrl_one').show();
									$('.imgUrl_two').hide();
								}
							});
				});
		
			//关闭frame
			$(function() {
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				$('.cancel').on('click', function() {
					parent.layer.close(index); //执行关闭
				});
			});
			
			function editCommonAttr() {
				
					$.post("editCommonAttrSave.action", $("#editCommonAttrForm").serialize(), function(data) {
						var result = eval(data);
						alert(result.msg);
						var editHtml = "";
						if(result.isSuccess == 1){
							
							editHtml = "<td class='tn_c'><span class='edit cads_new' onclick='editComAttr("+result.comAttr.categoryComAttrMetadataId+")'></span>"
										+"<span class='del' onclick='delComAttr(this,"+result.comAttr.categoryComAttrMetadataId+")'></span></td>"
										+"<td>"+result.comAttr.categoryComAttrName+"</td>"
										+"<td class='tn_c'>"+result.comAttr.isFilter+"</td>"
										+"<td class='last tn_c'>"+result.comAttr.seqNo+"</td>";
							$('#'+result.comAttr.categoryComAttrMetadataId, parent.document).html(editHtml);
						}
						
						var index = parent.layer.getFrameIndex(window.name);
						//parent.location.reload();
						parent.layer.close(index);
					}, "json");
			
			}
			
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
			
			function openImgPicker(masterdataId,elementId,isMulti){
			    $.layer({
			        type: 2,
			        title: 'File Asset Picker',
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


