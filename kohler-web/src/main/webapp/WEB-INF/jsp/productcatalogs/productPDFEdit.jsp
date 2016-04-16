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
		<c:forEach items="${productPDFlist}" var="pdf">
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_PDF_TYPE&lang=${pdf.lang}",function(data){
				var opt = '<option value=""></option>';
				$.each(data.result,function(name,value){
					if(value.masterdataMetadataId == '${pdf.pdfType}'){
						opt += '<option selected="selected" value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}else{
						opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}
				});
				$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('select[name=pdfType]').html(opt);
			});
			
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_FILE_SOURCE&lang=${pdf.lang}",function(data){
				var opt = '<option value=""></option>';
				$.each(data.result,function(name,value){
					if(value.masterdataMetadataId == '${pdf.fileSource}'){
						opt += '<option selected="selected" value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}else{
						opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
					}
				});
				var fileSourceVal = '${pdf.fileSource}';
				if(fileSourceVal=='10030001'){
					$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('input[name=fileId]').parents('.row').show();
					$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('input[name=fileUrl]').parents('.row').hide();
				}
				if(fileSourceVal=='10030002'){
					$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('input[name=fileId]').parents('.row').hide();
					$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('input[name=fileUrl]').parents('.row').show();
				}
				$('input[name=lang][value="${pdf.lang}"]').parents('.box').find('select[name=fileSource]').html(opt);
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
	function editProductPDF() {
		$("#errorMessage").html("");
		var forms = $(".tab_box form");
		var len = forms.length;
		var productPDFs = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				productPDFs += JSON.stringify(formToJson(form));
			} else {
				productPDFs += JSON.stringify(formToJson(form));
				productPDFs += ",";
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
		$("#editProductPDFForm").valid();
        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		productPDFs += "]";
		$.post("updateProductPDF.action", $("#editProductPDFForm").serialize()
				+ "&productPDFs=" + productPDFs, function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productPDF/unlimited/productPDFList.action?productMetadataId=${productMetadataId}',function(data){
				var index = parent.layer.getFrameIndex(window.name);
				parent.$("#productPDFList").html(data);
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
				<form id="editProductPDFForm">
					<input type="hidden" name="productMetadataId"
						value="${productMetadataId}" /> <input type="hidden"
						name="productPDFMetadataId"
						value="${pdfMetadataEntity.productPDFMetadataId}" />
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${pdfMetadataEntity.productPDFMetadataId}</div>
					</div>
				</form>
			</div>
			<div class="tab" id="tab">
				<ul class="tab_menu">
					<c:forEach items="${languages}" var="language"  varStatus="status">
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
										<c:forEach items="${productPDFlist}" var="pdf" >
											<c:if test="${pdf.lang eq language.localeId}">
												<input type="hidden" name="productPDFId"
													value="${pdf.productPDFId}" />
												<div class="row">
													<div class="col-md-3 tn_l required">Type</div>
													<div class="col-md-9">
														<select name="pdfType" required>
															<!-- <option></option>
															<option value="0"
																<c:if test="${pdf.pdfType eq 0}">selected</c:if>>尺寸图</option>
															<option value="1"
																<c:if test="${pdf.pdfType eq 1}">selected</c:if>>安装指导图</option>
															-->
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">PDF Source</div>
													<div class="col-md-9">
														<select name="fileSource" required>
															<!--  <option></option>
															<option value="0"
																<c:if test="${pdf.fileSource eq 0}">selected</c:if>>Internal</option>
															<option value="1"
																<c:if test="${pdf.fileSource eq 1}">selected</c:if>>External</option>
														-->
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">PDF URL</div>
													<div class="col-md-9">
														<input type="text" name="fileUrl" required value="${pdf.fileUrl}" maxlength="250"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l required">PDF</div>
													<div class="col-md-7">
														<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }" value="${pdf.fileId }"/> 
														<c:forEach items="${fileNameMap}" var="fnm" >
															<c:if test="${fnm.key eq language.localeId}">
																<input type="text" required name="notputsql_showfileName${language.localeId }" readonly="readonly"  id="showfileName${language.localeId }" value="${fnm.value }"/>
															</c:if>
														</c:forEach>
													</div>
													<div class='col-md-2 '>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090004","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row large">
													<div class="col-md-3">
														Description_${language.localeCode}</div>
													<div class="col-md-9">
														<textarea rows="2" name="description">${pdf.description}</textarea>
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
										<c:forEach items="${productPDFlist}" var="pdf">
											<c:if test="${pdf.lang eq language.localeId}">
												<input type="hidden" name="productPDFId"
													value="${pdf.productPDFId}" />
												<div class="row">
													<div class="col-md-3 tn_l">Type</div>
													<div class="col-md-9">
														<select name="pdfType">
															<option></option>
															<option value="0"
																<c:if test="${pdf.pdfType eq 0}">selected</c:if>>尺寸图</option>
															<option value="1"
																<c:if test="${pdf.pdfType eq 1}">selected</c:if>>安装指导图</option>
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">PDF Source</div>
													<div class="col-md-9">
														<select name="fileSource">
															<option></option>
															<option value="0"
																<c:if test="${pdf.fileSource eq 0}">selected</c:if>>Internal</option>
															<option value="1"
																<c:if test="${pdf.fileSource eq 1}">selected</c:if>>External</option>
														</select>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">PDF URL</div>
													<div class="col-md-9">
														<input type="text" name="fileUrl" value="${pdf.fileUrl}" maxlength="250"/>
													</div>
												</div>
												<div class="row">
													<div class="col-md-3 tn_l">PDF</div>
													<div class="col-md-7">
														<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }" value="${pdf.fileId }"/> 
														<c:forEach items="${fileNameMap}" var="fnm" >
															<c:if test="${fnm.key eq language.localeId}">
																<input type="text" readonly="readonly"  id="showfileName${language.localeId }" value="${fnm.value }"/>
															</c:if>
														</c:forEach>
														
													</div>
													<div class='col-md-2 '>
														<a href='#'  class='choose tn_c' onclick='openDataType("10090004","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
													</div>
												</div>
												<div class="row large">
													<div class="col-md-3">
														Description_${language.localeCode}</div>
													<div class="col-md-9">
														<textarea rows="2" name="description">${pdf.description}</textarea>
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
					<div class="col-md-3">${pdfMetadataEntity.createUser}</div>
					<div class="col-md-3">
						Creation Date
					</div>
					<div class="col-md-4">
						<fmt:formatDate value="${pdfMetadataEntity.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${pdfMetadataEntity.modifyUser}</div>
					<div class="col-md-3">
						Modification Date
					</div>
					<div class="col-md-4">
						<fmt:formatDate value="${pdfMetadataEntity.modifyTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" class="confirm"
					onclick="editProductPDF()">确定</a> <a href="#" class="cancel"
					id="closebtn">取消</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
	</div>
</body>
</html>