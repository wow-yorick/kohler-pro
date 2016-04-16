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
<title>Banner Unit</title>
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
	<div class="container queryConditions ">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form id="addBannerUnitForm" name="addPageForm">
				<input type = "hidden" name="lang" value="${lang }" />
				<input type = "hidden" name="categoryMetadataId" value = ${categoryMetadataId } />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp</div>
					</div>
					<div class="row">
						<div class="col-md-2 ">Banner Unit</div>
						<div class="col-md-4 border">
							<input type="text" id="datatypeText${brannerKey}"
								readonly="readonly" class="disable" />
							<input type="hidden" id="bannerUnitId${brannerKey}" name="bannerUnitMetadataId"   />
						</div>
						<div class="col-md-2">
							<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${brannerKey}','bannerUnitId${brannerKey}','datatypeText${brannerKey}','1')">Choose</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 tn_l">Row</div>
						<div class="col-md-4">
							<input type="text" name="bannerUnitRow" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 tn_l">Column</div>
						<div class="col-md-4">
							<input type="text" name="bannerUnitColumn" />
						</div>
					</div>
				</div>
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
					<a href="javascript:void(0);" class="confirm" onclick="addBannerUnit()">Save</a> <a href="#" class="cancel"
						id="closebtn">Cancel</a>
				</div>
			</form>
		</div>
		<!--main结束-->
			</div>
		<script type="text/javascript">
			//关闭frame
			$(function() {
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				$('.cancel').on('click', function() {
					parent.layer.close(index); //执行关闭
				});
			});
			
			function openDataType(id,elementId,elementName,isMulti){
				$.layer({
			        type: 2,
			        title: 'Content Picker',
			        maxmin: false,
			        shadeClose: true, //开启点击遮罩关闭层
			        area : ['780px' , '650px'],
					shadeClose: false,
					move: false,
					offset : [($(window).height() - 600)/2+'px', ''],
			        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id+'&elementId='+elementId+'&elementName='+elementName+'&isMulti='+isMulti}
			    
				});
			}
			
			
			function addBannerUnit() {
				
				
				$.post(
								"saveBannerUnit.action",
								$("#addBannerUnitForm").serialize()
										,
								function(data) {
									var result = eval(data);
									alert(result.msg);

									var addHtml = "";
									
									if (result.isSuccess == 1) {

										addHtml = "<tr id = '"+result.bannerUnit.categoryBannerUnitId+"'>"
												+ "<td class='tn_c'><span class='edit pdf_new' onclick='editbannerUnitPage("
												+ result.bannerUnit.categoryBannerUnitId
												+ ")'></span>"
												+ "<span class='del' onclick='delbannerUnit(this,"
												+ result.bannerUnit.categoryBannerUnitId
												+ ")'></span></td>"
												+ "<td>"
												+ result.bannerUnit.bannerUnitFiledValue
												+ "</td>"
												+ "<td class='last'>第"
												+ result.bannerUnit.bannerUnitRow+"行第"+result.bannerUnit.bannerUnitColumn+"列"
												+ "</td>" + "</tr>";
									}
									$('#bannerTable'+result.bannerUnit.lang, parent.document)
											.append(addHtml);
									var index = parent.layer
											.getFrameIndex(window.name);
									//parent.location.reload();
									parent.layer.close(index);

								}, "json");
			}
		</script>

</body>
</html>


