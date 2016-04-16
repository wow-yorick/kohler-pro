<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		$('input[name=fileId]').parents('.row').hide();
		$('input[name=fileUrl]').parents('.row').hide();
		<c:forEach items="${productVideolist}" var="video">
			
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_FILE_SOURCE&lang=${video.lang}",function(data){
				var opt = '<option value=""></option>';
				$.each(data.result,function(name,value){
					if(value.masterdataMetadataId == '${video.fileSource}'){
						opt += '<option selected="selected" value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}else{
						opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}
				});
				var fileSourceVal = '${video.fileSource}';
				if(fileSourceVal=='10030001'){
					$('input[name=lang][value="${video.lang}"]').parents('.box').find('input[name=fileId]').parents('.row').show();
					$('input[name=lang][value="${video.lang}"]').parents('.box').find('input[name=fileUrl]').parents('.row').hide();
				}
				if(fileSourceVal=='10030002'){
					$('input[name=lang][value="${video.lang}"]').parents('.box').find('input[name=fileId]').parents('.row').hide();
					$('input[name=lang][value="${video.lang}"]').parents('.box').find('input[name=fileUrl]').parents('.row').show();
				}
				$('input[name=lang][value="${video.lang}"]').parents('.box').find('select[name=fileSource]').html(opt);
			});
		</c:forEach>
		
		$('select[name=fileSource]').on('change', function(){
			if($(this).val()=='10030001'){
				$(this).parents('.box').find('input[name=fileId]').parents('.row').show();
				$(this).parents('.box').find('input[name=fileUrl]').parents('.row').hide();
			}else if($(this).val()=='10030002'){
				$(this).parents('.box').find('input[name=fileId]').parents('.row').hide();
				$(this).parents('.box').find('input[name=fileUrl]').parents('.row').show();
			}else{
				$(this).parents('.box').find('input[name=fileId]').parents('.row').hide();
				$(this).parents('.box').find('input[name=fileUrl]').parents('.row').hide();
			}
		});
	});
	
	function openDataType(masterdataId,elementId,elementName){
	    $.layer({
	        type: 2,
	        title: 'PDF Select',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['680px' , '480px'],
			shadeClose: false,
	        offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/image/unlimited/imagepicker.action?isMulti=1&fileType='+masterdataId+'&elementId='+elementId+'&elementName='+elementName}
	    
		});
	}
	
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
	function editProductVideo() {
		$("#errorMessage").html("");
		var forms = $(".tab_box form");
		var len = forms.length;
		var productVideos = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				productVideos += JSON.stringify(formToJson(form));
			} else {
				productVideos += JSON.stringify(formToJson(form));
				productVideos += ",";
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
		$("#editProductVideoForm").valid();
        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		productVideos += "]";
		$.post("updateProductVideo.action", $("#editProductVideoForm")
				.serialize()
				+ "&productVideos=" + productVideos, function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productVideo/unlimited/productVideoList.action?productMetadataId=${productMetadataId}',function(data){
				var index = parent.layer.getFrameIndex(window.name);
				parent.$("#productVideoList").html(data);
				parent.layer.close(index);
			});
		}, "json");
	}
</script>
</head>

<body>
	<div class="container queryConditions product_layer partsImages">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<div class="search">
				<form id="editProductVideoForm">
					<input type="hidden" name="productMetadataId" value="${productMetadataId}" /> 
					<input type="hidden" name="productVideoMetadataId" value="${videoMetadataEntity.productVideoMetadataId}" />
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${videoMetadataEntity.productVideoMetadataId}</div>
					</div>
				</form>
			</div>
			<div class="tab" id="tab">
				<ul class="tab_menu">
					<c:forEach items="${languages}" var="language" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0 }">
								<li class="active tn_c">${language.localeName}</li>
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
										<c:forEach items="${productVideolist}" var="video">
											<c:if test="${video.lang eq language.localeId}">
												<input type="hidden" name="productVideoId" value="${video.productVideoId}" />
												<div class="row">
													<div class="col-md-3 tn_l required">Video
														Name_${language.localeCode}</div>
													<div class="col-md-9">
														<input type="text" required="required" maxlength="25" name="productVideoName" value="${video.productVideoName}" />
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">Video Source</div>
													<div class="col-md-9">
														<select name="fileSource" required>
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">Video URL</div>
													<div class="col-md-9">
														<input type="text" name="fileUrl" required value="${video.fileUrl}" maxlength="250"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">File</div>
													<div class="col-md-7">
														<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }" value="${video.fileId }"/> 
														<c:forEach items="${fileNameMap}" var="fnm" >
															<c:if test="${fnm.key eq language.localeId}">
																<input type="text" required readonly="readonly" name="notputsql_showfileName${language.localeId }" id="showfileName${language.localeId }" value="${fnm.value }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class='col-md-2 '>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090002","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row large">
													<div class="col-md-3">
														Description_${language.localeCode}</div>
													<div class="col-md-9">
														<textarea rows="2" name="description" maxlength="300">${video.description}</textarea>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</form>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box" id="divlan${status.index}">
									<form id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<c:forEach items="${productVideolist}" var="video">
											<c:if test="${video.lang eq language.localeId}">
												<input type="hidden" name="productVideoId" value="${video.productVideoId}" />
												<div class="row">
													<div class="col-md-3 tn_l">Video
														Name_${language.localeCode}</div>
													<div class="col-md-9">
														<input type="text" name="productVideoName" maxlength="25" value="${video.productVideoName}" />
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Video Source</div>
													<div class="col-md-9">
														<select name="fileSource">
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">Video URL</div>
													<div class="col-md-9">
														<input type="text" name="fileUrl" value="${video.fileUrl}" maxlength="250" />
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">File</div>
													<div class="col-md-7">
														<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }" value="${video.fileId }"/> 
														<c:forEach items="${fileNameMap}" var="fnm" >
															<c:if test="${fnm.key eq language.localeId}">
																<input type="text" id="showfileName${language.localeId }" value="${fnm.value }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class='col-md-2 '>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090002","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row large">
													<div class="col-md-3">
														Description_${language.localeCode}</div>
													<div class="col-md-9">
														<textarea rows="2" name="description" maxlength="300">${video.description}</textarea>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</form>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${videoMetadataEntity.createUser}</div>
					<div class="col-md-3">
						Creation Date
					</div>
					<div class="col-md-4">
						<fmt:formatDate value="${videoMetadataEntity.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${videoMetadataEntity.modifyUser}</div>
					<div class="col-md-3">
						Modification Date
					</div>
					<div class="col-md-4">
						<fmt:formatDate value="${videoMetadataEntity.modifyTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm" onclick="editProductVideo()">确定</a> 
				<a href="#" class="cancel" id="closebtn">取消</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
	</div>
</body>
</html>