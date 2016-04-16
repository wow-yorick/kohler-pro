<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Edit Content</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
	var UeditorForContent = (function() {
		return {
			init : function(selector,words) {
				var selector = selector || "ueditor";
				
				var editor = UE.getEditor(selector, {
				    toolbars: [
				        ['source', '|', 'undo', 'redo', '|',
			                'bold', 'italic', 'underline', 'forecolor', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain']
				    ],
				    initialFrameWidth : 416,
			        initialFrameHeight: 100,
			        maximumWords:words||250
				});
				editor.addListener( 'ready', function( editor ) {
				} );
				return editor;
			}
		}
	})();
	
	var UeditorForContentOnlyRead = (function() {
		return {
			init : function(selector,words) {
				var selector = selector || "ueditor";
				
				var editor = UE.getEditor(selector, {
				    toolbars: [
				        ['source', '|', 'undo', 'redo', '|',
			                'bold', 'italic', 'underline', 'forecolor', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain']
				    ],
				    initialFrameWidth : 416,
			        initialFrameHeight: 100,
			        maximumWords:words||250,
			        readonly:true
				});
				editor.addListener( 'ready', function( editor ) {
				} );
				return editor;
			}
		}
	})();
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

	});
</script>
</head>

<body>
	<div
		class="container queryConditions section_lay_one">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			
			<div class="search">
				<form id="updateContentForm">
					<input type="hidden" name="contentMetadataId" value="${contentMetadata.contentMetadataId }" />
					<div class="row">
						<div class="col-md-4">Id</div>
						<div class="col-md-4">${contentMetadata.contentMetadataId }</div>
						
					</div>
					<div class="row">
						<div class="col-md-3">seqNo</div>
						<div class="col-md-6"><input maxLength="8" digits="true" type="text" name="seqNo" value="${contentMetadata.seqNo }"></div>
					</div>
				</form>
				<form id="nolangForm">
					<div id="nolang">
						<!--<div class="row">
							<c:if test="${isEdit == 0 }">
								<pageTag:datatype-edit datatypeId="${datatypeId}" lang="0" contentId="${contentMetadata.contentMetadataId }" isEdit="0"/>
							</c:if>
							<c:if test="${isEdit != 0 }">
								<pageTag:datatype-edit datatypeId="${datatypeId}" lang="0" contentId="${contentMetadata.contentMetadataId }"/>
							</c:if>
						</div>  -->
					</div>
				</form>
			</div>
			
			<div class="search">
				
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
									<div class="box" style="height:500px" id="divlan${status.index}">
										<form class="language" id="language${language.localeId }">
											<input type="hidden" name="lang"
												value="${language.localeId }" />
												<c:if test="${isEdit == 0 }">
													<pageTag:datatype-edit datatypeId="${datatypeId}" lang="${language.localeId }" contentId="${contentMetadata.contentMetadataId }" isEdit="0"/>
												</c:if>
												<c:if test="${isEdit != 0 }">
													<pageTag:datatype-edit datatypeId="${datatypeId}" lang="${language.localeId }" contentId="${contentMetadata.contentMetadataId }"/>
												</c:if>
												
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="hide box" style="height:500px" id="divlan${status.index}">
										<form class="language" id="language${language.localeId }">
											<input type="hidden" name="lang"
												value="${language.localeId }" />
												<c:if test="${isEdit == 0 }">
													<pageTag:datatype-edit datatypeId="${datatypeId}" lang="${language.localeId }" contentId="${contentMetadata.contentMetadataId }" isEdit="0"/>
												</c:if>
												<c:if test="${isEdit != 0 }">
													<pageTag:datatype-edit datatypeId="${datatypeId}" lang="${language.localeId }" contentId="${contentMetadata.contentMetadataId }"/>
												</c:if>
												
										</form>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="text">
				<div class="row">
					
					<div class="col-md-2">Creator</div>
					<div class="col-md-4">${contentMetadata.createUser }</div>
					<div class="col-md-3">Creation Date</div>
					<div class="col-md-3">
						<fmt:formatDate value="${contentMetadata.createTime}"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
				</div>
				<div class="row"><br/></div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-4">${contentMetadata.modifyUser }</div>
					<div class="col-md-3">Modification Date</div>
					<div class="col-md-3">
						<fmt:formatDate value="${contentMetadata.modifyTime}"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" onclick="updateContent();"
					class="confirm">Save</a> <a href="#" class="cancel">Cancel</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
</body>
<script type="text/javascript">
	jQuery.validator.addMethod("pattern", function(value, element) {  
	    var patt =  eval(element.getAttribute('pattern'));
	    return this.optional(element) || (patt.test(value));  
	}, "this value is wrong"); 
	
	jQuery.validator.addMethod("isFieldValueUnique", function(value, element) { 
		var ok = this.optional(element) ;
	   
	    if(element.value!=''){
		    $.ajax({
				url:"<%=request.getContextPath()%>/content/unlimited/checkContentValue.action",
				data:"fieldname="+element.name+"&fieldvalue="+element.value+"&lang="+element.getAttribute('isFieldValueUnique')+"&metadataId=${contentMetadata.contentMetadataId }&datatypeId=${datatypeId}",
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
	}, "this value has been existed!"); 
	
	document.onkeypress = function(){
		if(event.keyCode == 13) {return false;}}
	//关闭frame
	$(function() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('.cancel').on('click', function() {
			parent.layer.close(index); //执行关闭
		});
		var isEdit = ${isEdit};
		if(isEdit == 0){
			$("select").attr("disabled","disabled");
			setGray4DisabledSelect($("select"));
			$("input").attr("disabled","disabled");
			$("textarea").attr("disabled","disabled");
			$("a.choose").remove();
			$('.confirm').hide();
		}
		
		
	});
	
	function openDataTypePicker(datatypeId,elementId,elementName,maxSelect){
	    $.layer({
	        type: 2,
	        title: 'Please Select',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['750px' , '520px'],
			shadeClose: false,
			offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+datatypeId+'&elementId='+elementId+'&elementName='+elementName+'&maxSelect='+maxSelect}
	    
		});
	}
	
	function openDataType(masterdataId,elementId,isMulti,elementName){
	    $.layer({
	        type: 2,
	        title: 'Please Select',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['750px' , '520px'],
			shadeClose: false,
			offset : ['10px', ''],
	        iframe: {src: '<%=request.getContextPath()%>/image/unlimited/imagepicker.action?fileType='+masterdataId+'&elementId='+elementId+'&isMulti='+isMulti+'&elementName='+elementName}
	    
		});
	}
	
	function updateContent() {
		$("#errorMessage").html("");
		var sel = "";
		$("#nolang input[type=text]").each(function(){
			var search = encodeURIComponent($(this).val());
			sel += $(this).attr('name') +"="+search + "&";
		}); 
		$("#nolang select").each(function(){
			var search = encodeURIComponent($(this).val());
			sel += $(this).attr('name') +"="+search + "&";
		}); 
		$("#nolang input[type=radio]:checked").each(function(){
			var search = encodeURIComponent($(this).val());
			sel += $(this).attr('name') +"="+search + "&";
		});
		$("#nolang input[type=checkbox]:checked").each(function(){
			var search = encodeURIComponent($(this).val());
			if(sel.indexOf("&"+$(this).attr('name')+"=")>0){
				sel = sel.substring(0,sel.length-1)+search + ",&";
			}else{
				sel += $(this).attr('name') +"=,"+search + ",&";
			}
		});
		
		
		sel = sel.substring(0,sel.length-1);
		var jsonData = "[";
		$('.tab_box form').each(function(){
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
			$(this).find('input[type=radio]:checked').each(function(){
				var search = encodeURIComponent($(this).val());
				langsel += $(this).attr('name') +"="+search + "&";
			});
			$(this).find('input[type=checkbox]:checked').each(function(){
				var search = encodeURIComponent($(this).val());
				if(langsel.indexOf("&"+$(this).attr('name')+"=")>0){
					langsel = langsel.substring(0,langsel.length-1)+search + ",&";
				}else{
					langsel += $(this).attr('name') +"=,"+search + ",&";
				}
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
		$("#nolangForm").valid();
		$("#updateContentForm").valid()
		$.each($('.language'), function(i, form) {
			
			if (!$(this).is(":visible")) {
				document.getElementById( "divlan" + i).style.display= "block";
				$(this).valid();
				// 验证结束后， 把div 重新设定为不可视。
				document.getElementById( "divlan" + i).style.display= "none";
			}else{
				$(this).valid();
			}
			
		});
		
		if ($("#errorMessage").html() != "") {
			alert($("#errorMessage").html());
			return false;
		}
		$.post("editSave.action", $("#updateContentForm").serialize()+"&selStr="+encodeURIComponent(sel)+"&data="+jsonData,function(data) {
			var result = eval(data);
			alert(result.msg);
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");
		
	}
</script>
</html>

