<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>New ProductCAD</title>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
	
	
	$(function() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function() {
			parent.layer.close(index); //执行关闭
		});
		
			
		$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_CAD_TYPE&lang=1",function(data){
			var opt = '<option value=""></option>';
			$.each(data.result,function(name,value){
				opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
			});
			$('select[name=cadType]').html(opt);
		});
		
		$('input[name=fileId]').parents('.row').hide();
		$('input[name=fileUrl]').parents('.row').hide();
		
		$.each($('select[name=fileSource]'),function(){
			var l = $(this).parents('.box').find('input[name=lang]').val();
			$.post("<%=request.getContextPath()%>/content/unlimited/getMasterDateByName.action","masterdataTypeName=TYPE_FILE_SOURCE&lang="+l,function(data){
				var opt = '<option value=""></option>';
				$.each(data.result,function(name,value){
					opt += '<option value="'+value.masterdataMetadataId+'">'+value.masterdataName+'</option>';
				});
				$('input[name=lang][value="'+l+'"]').parents('.box').find('select[name=fileSource]').html(opt);
			});
		});
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
	function addProductCAD() {
		$("#errorMessage").html("");
		var forms = $(".tab_box form");
		var len = forms.length;
		var productCADs = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				productCADs += JSON.stringify(formToJson(form));
			} else {
				productCADs += JSON.stringify(formToJson(form));
				productCADs += ",";
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
		productCADs += "]";
		$("#addProductCADForm").valid();
        if ($("#errorMessage").html() != "") {
            alert($("#errorMessage").html());
            return false;
        }
		$.post("saveProductCAD.action", $("#addProductCADForm").serialize()
				+ "&productCADs=" + productCADs, function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productCAD/unlimited/productCADList.action?productMetadataId=${productMetadataId}',function(data){
				var index = parent.layer.getFrameIndex(window.name);
				parent.$("#productCADList").html(data);
				parent.layer.close(index);
			});
		}, "json");
	}
</script>
</head>
<body>
	<div class="container queryConditions product_layer">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<div class="search">
				<form id="addProductCADForm">
					<input type="hidden" name="productMetadataId" value="${productMetadataId}" /> 
					<%-- <input type="hidden" name="productCADMetadataId" value="${productCADMetadataEntity.productCADMetadataId}" /> --%>
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">&nbsp;</div>
						<div class="col-md-2 tn_c required">Type</div>
						<div class="col-md-4">
							<select name="cadType" required>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">Suffix</div>
						<div class="col-md-4">
							<input type="text" name="suffix" />
						</div>
					</div>
				</form>
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
											<div class="col-md-3 tn_l required">CAD
												Name_${language.localeCode}</div>
											<div class="col-md-9">
												<input type="text" maxlength="25" name="productCADName" required />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l required">File Source</div>
											<div class="col-md-9">
												<select name="fileSource" required>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l required">File URL</div>
											<div class="col-md-9">
												<input type="text" name="fileUrl" required maxlength="250"/>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l required">File Id</div>
											<div class="col-md-7">
												<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }"/> 
												<input type="text" readonly="readonly" name="notputsql_showfileName${language.localeId }" id="showfileName${language.localeId }" required/>
											</div>
											<div class='col-md-2 '>
												<a href='#'  class='choose tn_c' onclick='openDataType("10090003","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row large">
											<div class="col-md-3 tn_l">
												Description_${language.localeCode}
											</div>
											<div class="col-md-9">
												<textarea rows="2" name="description" maxlength="300"></textarea>
											</div>
										</div>
									</form>
								</div>
							</c:when>
							<c:otherwise>
								<div class="hide box" id="divlan${status.index}">
									<form id="lan${status.index}">
										<input type="hidden" name="lang" value="${language.localeId }" />
										<div class="row">
											<div class="col-md-3 tn_l">CAD
												Name_${language.localeCode}</div>
											<div class="col-md-9">
												<input type="text" maxlength="25" name="productCADName" />
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">File Source</div>
											<div class="col-md-9">
												<select name="fileSource">
													<option></option>
													<option value="0">Internal</option>
													<option value="1">External</option>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">File URL</div>
											<div class="col-md-9">
												<input type="text" name="fileUrl" maxlength="250"/>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 tn_l">File Id</div>
											<div class="col-md-7">
												<input type="hidden" name="fileId" id="hiddenfileId${language.localeId }"/> 
												<input type="text" id="showfileName${language.localeId }" />
											</div>
											<div class='col-md-2 '>
												<a href='#'  class='choose tn_c' onclick='openDataType("10090003","hiddenfileId${language.localeId }","showfileName${language.localeId }")'>Choose</a>
											</div>
										</div>
										<div class="row large">
											<div class="col-md-3 tn_l">
												Description_${language.localeCode}
											</div>
											<div class="col-md-9">
												<textarea rows="2" name="description" maxlength="300"></textarea>
											</div>
										</div>
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
					<div class="col-md-3"></div>
					<div class="col-md-7">
						Creation Date
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3"></div>
					<div class="col-md-7">
						Modification Date
					</div>
				</div>
			</div>
			<div class="btns">
        		<a href="javascript:void(0);" class="confirm" onclick="addProductCAD()">确定</a>
        		<a href="#" class="cancel" id="closebtn">取消</a>
        	</div>
        	<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
</body>
</html>