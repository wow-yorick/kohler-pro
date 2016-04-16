<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>catalog</title>
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

	});
</script>
</head>

<body>
	<div class="container queryConditions">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<form action = "${base}/category/editCatalog.action" id = "editCatalogForm" name="addPageForm" >
    		<input type = "hidden" name = "categoryMetadataId" value = "${parentCategory.categoryMetadataId }" />
	    	<div class="search">
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4">${parentCategory.categoryMetadataId }</div>
	            	<div class="col-md-2 tn_c">Level</div>
	            	<div class="col-md-4">
	                		<input type = "text" name = "level" value = "${parentCategory.level }" class="disable" readonly = "readonly" /> 
	                </div>    
	            </div>
	        	<div class="row">
	            	<div class="col-md-2">SeqNo</div>
	            	<div class="col-md-4"><input type="text" name = "seqNo" maxlength="8" isIntGteZero = "true" value = "${parentCategory.seqNo }" /></div>            	
	            </div>        	
	        </div>              
	        <div class="tab" id="tab">
	        	<ul class="tab_menu">
	            	<c:forEach items="${categoryPojos}" var="catePojo" varStatus="status">
								<c:choose>
									<c:when test="${catePojo.language.isDefault == true }">
										<li class="active tn_c">${catePojo.language.localeName }</li>
									</c:when>
									<c:otherwise>
										<li class="tn_c">${catePojo.language.localeName }</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
	            </ul>
            <div class="tab_box">
            	<c:forEach items="${categoryPojos}" var="catePojo" varStatus="status">
   						<c:choose>
								<c:when test="${catePojo.language.isDefault == true  }">
												
		            	<div class="box">
		            		<input type="hidden" name="categoryIds"
												value="${catePojo.category.categoryId }" /><%--循环取出此处不需要语言就换成id进行修改 --%>
		                    <div class="row">
		                        <div class="col-md-2 required">Name_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "categoryNames" value = "${catePojo.category.categoryName }" categoryId = "${catePojo.category.categoryId }" required="required" maxlength="25" lang= "${catePojo.category.lang }" isCatalogNameUnique = "true" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">PC Template_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4 required">
		                            <select name = "pcTemplateIds" value = "${catePojo.category.pcTemplateId}" required="required" >
		                                <option value="">--select--</option>
		                                <c:forEach items="${templates}" var="t">
											<option value="${t.templateId}">${t.templateName}</option>
										</c:forEach>
		                            </select>
		                        </div> 
		                        <div class="col-md-2 tn_c">Mobile Template_${catePojo.language.localeCode }</div>
		                         <div class="col-md-4 required">
		                            <select name = "mobileTemplateIds" value = "${catePojo.category.mobileTemplateId }" required="required" >
		                               <option value="">--select--</option>
		                               <c:forEach items="${templates}" var="t">
											<option value="${t.templateId}">${t.templateName}</option>
										</c:forEach>
		                            </select>
		                        </div> 
		                    </div>
		                    <div class="row seo bold">
		                        <div class="col-md-9 space">SEO (PC Website)_${catePojo.language.localeCode }</div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Title_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoTitlePcs" value = "${catePojo.category.seoTitlePc }" /></div> 
		                        <div class="col-md-2 tn_c">H1 Tag</div>
		                        <div class="col-md-4"><input type="text" name = "seoH1tagPcs" value = "${catePojo.category.seoH1tagPc }" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10"><input type="text" name = "seoKeywordsPcs" value = "${catePojo.category.seoKeywordsPc }" /></div> 
		                    </div>
		                    <div class="row larger">
		                        <div class="col-md-2">Description_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10">
		                            <textarea rows="2" name = "seoDescriptionPcs">${catePojo.category.seoDescriptionPc }</textarea>
		                        </div> 
		                    </div>
		                    <div class="row seo bold">
		                        <div class="col-md-9 space">SEO (Mobile Website)_${catePojo.language.localeCode }</div>
		                    </div>
		                   <div class="row">
		                        <div class="col-md-2">Title_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoTitleMobiles" value = "${catePojo.category.seoTitleMobile }" /></div> 
		                        <div class="col-md-2 tn_c">H1 Tag_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoH1tagMobiles" value = "${catePojo.category.seoH1tagMobile }" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10"><input type="text" name = "seoKeywordsMobiles" value = "${catePojo.category.seoKeywordsMobile }" /></div> 
		                    </div>
		                    <div class="row larger">
		                        <div class="col-md-2">Description_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10">
		                            <textarea name = "seoDescriptionMobiles" >${catePojo.category.seoDescriptionMobile }</textarea>
		                        </div> 
		                    </div>
		                    <div class="row">
		                    				<input type="hidden" name="categoryHeroAreaIds" value="${catePojo.heroArea.categoryHeroAreaId }" />
											<div class="col-md-2 required">Hero
												Areas_${catePojo.language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${catePojo.language.localeId }" value="${catePojo.fieldValue }"
													readonly="readonly" required="required" class="disable" /> <input
													type="hidden" name="heroAreaMetadataIds" id="heroAreas${catePojo.language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${catePojo.language.localeId }','heroAreasName${catePojo.language.localeId }')">Choose</a>
											</div>
							</div>
	                    	
	                    	<div class="row">
	                    	</div>
		                   <div class="row seo bold">
											<div class="col-md-9 space">Banner Units</div>
										</div>
										<c:forEach items="${childrenCate}" var="cate" varStatus="status">
											<div class="row">
												<div class="col-md-3 ">${cate.categoryName}</div>
												<div class="col-md-8 border">
													<%-- 	<c:choose>
													<c:when test="${cate.lang == catePojo.language.localeId }"> --%>
													<c:import 
													url="/catalog/unlimited/getBannerUnit.action?catalogMetadataId=${parentCategory.categoryMetadataId }&categoryMetadataId=${cate.categoryMetadataId }&lang=${catePojo.category.lang }&langCode=${catePojo.language.localeCode }"></c:import>
														<%-- 
														<input type="text"  id="bannerUnitName${cate.categoryMetadataId }${catePojo.language.localeCode }" 	
														 value="${cate.breadcrumbName }" readonly="readonly" /> --%>
													<%-- 
													</c:when>
													<c:otherwise>
														<input type="text"  id="bannerUnitName${cate.categoryMetadataId }" 	
														 readonly="readonly" />
													</c:otherwise>
												</c:choose>	 --%>
												
													<input type="hidden" name="bannerUnitMetadataIds" id="bannerUnit${cate.categoryMetadataId }${catePojo.language.localeCode }" />
													
													<input type="hidden" name="categoryIds" value="${cate.categoryMetadataId }" />
												</div>
												<div class="col-md-1">
													<a href="javascript:void(0);" class="choose tn_c" 
													onclick="openDataType('${brannerKey}','bannerUnit${cate.categoryMetadataId }${catePojo.language.localeCode }','bannerUnitName${cate.categoryMetadataId }${catePojo.language.localeCode }')">Choose</a>
												</div>
									
											</div>
										</c:forEach>
										<%-- 
										<div class="row">
											<div class="col-md-2 ">浴缸</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 ">浴室龙头</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 ">梳妆</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 ">商用产品</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
										</div>
										<div class="row">
											<div class="col-md-2 ">艺术系列</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
											<div class="col-md-2">
												<input type="text" />
											</div>
											<div class="col-md-1">
												<a href="#" class="open_file tn_c"></a>
											</div>
										</div>
		                    --%>
		                  
		                  </div>
		               </c:when>
						<c:otherwise>
			                <div class="hide box">
			                   <div class="box">
		            		<input type="hidden" name="categoryIds"
												value="${catePojo.category.categoryId }" /><%--循环取出此处不需要语言就换成id进行修改 --%>
		                    <div class="row">
		                        <div class="col-md-2">Name_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "categoryNames" value = "${catePojo.category.categoryName }" categoryId = "${catePojo.category.categoryId }" maxlength="25" lang= "${catePojo.category.lang }" isCatalogNameUnique = "true" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">PC Template_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4">
		                            <select name = "pcTemplateIds" value = "${catePojo.category.pcTemplateId}" >
		                           	 <option value="">--select--</option>
		                                <c:forEach items="${templates}" var="t">
											<option value="${t.templateId}">${t.templateName}</option>
										</c:forEach>
		                            </select>
		                        </div> 
		                        <div class="col-md-2 tn_c">Mobile Template_${catePojo.language.localeCode }</div>
		                         <div class="col-md-4">
		                            <select name = "mobileTemplateIds" value = "${catePojo.category.mobileTemplateId }" >
		                                <option value="">--select--</option>
		                                <c:forEach items="${templates}" var="t">
											<option value="${t.templateId}">${t.templateName}</option>
										</c:forEach>
		                            </select>
		                        </div> 
		                    </div>
		                    <div class="row seo bold">
		                        <div class="col-md-9 space">SEO (PC Website)_${catePojo.language.localeCode }</div>
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Title_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoTitlePcs" value = "${catePojo.category.seoTitlePc }" /></div> 
		                        <div class="col-md-2 tn_c">H1 Tag</div>
		                        <div class="col-md-4"><input type="text" name = "seoH1tagPcs" value = "${catePojo.category.seoH1tagPc }" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10"><input type="text" name = "seoKeywordsPcs" value = "${catePojo.category.seoKeywordsPc }" /></div> 
		                    </div>
		                    <div class="row larger">
		                        <div class="col-md-2">Description_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10">
		                            <textarea rows="2" name = "seoDescriptionPcs">${catePojo.category.seoDescriptionPc }</textarea>
		                        </div> 
		                    </div>
		                    <div class="row seo bold">
		                        <div class="col-md-9 space">SEO (Mobile Website)_${catePojo.language.localeCode }</div>
		                    </div>
		                   <div class="row">
		                        <div class="col-md-2">Title_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoTitleMobiles" value = "${catePojo.category.seoTitleMobile }" /></div> 
		                        <div class="col-md-2 tn_c">H1 Tag_${catePojo.language.localeCode }</div>
		                        <div class="col-md-4"><input type="text" name = "seoH1tagMobiles" value = "${catePojo.category.seoH1tagMobile }" /></div> 
		                    </div>
		                    <div class="row">
		                        <div class="col-md-2">Keywords_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10"><input type="text" name = "seoKeywordsMobiles" value = "${catePojo.category.seoKeywordsMobile }" /></div> 
		                    </div>
		                    <div class="row larger">
		                        <div class="col-md-2">Description_${catePojo.language.localeCode }</div>
		                        <div class="col-md-10">
		                            <textarea name = "seoDescriptionMobiles" >${catePojo.category.seoDescriptionMobile }</textarea>
		                        </div> 
		                    </div>
		                    <div class="row">
		                    				<input type="hidden" name="categoryHeroAreaIds" value="${catePojo.heroArea.categoryHeroAreaId }" />
											<div class="col-md-2 required">Hero
												Areas_${catePojo.language.localeCode }</div>
											<div class="col-md-9 border">
												<input type="text"  id="heroAreasName${catePojo.language.localeId }" value="${catePojo.fieldValue }"
													readonly="readonly" class="disable" required="required" /> <input
													type="hidden" name="heroAreaMetadataIds" id="heroAreas${catePojo.language.localeId }"
													 />
											</div>
											<div class="col-md-1">
												<a href="javascript:void(0);" class="choose tn_c" onclick="openDataType('${heroAreasKey}','heroAreas${catePojo.language.localeId }','heroAreasName${catePojo.language.localeId }')">Choose</a>
											</div>
							</div>
		                   <div class="row">
	                    	</div>
		                   <div class="row seo bold">
											<div class="col-md-9 space">Banner Units</div>
										</div>
										<c:forEach items="${childrenCate}" var="cate" varStatus="status">
										<div class="row">
												<div class="col-md-3 ">${cate.categoryName}</div>
												<div class="col-md-8 border">
												<%-- 
												<c:choose>
													<c:when test="${cate.lang == catePojo.language.localeId }"> --%>
														<c:import 
													url="/catalog/unlimited/getBannerUnit.action?catalogMetadataId=${parentCategory.categoryMetadataId }&categoryMetadataId=${cate.categoryMetadataId }&lang=${catePojo.category.lang }&langCode=${catePojo.language.localeCode }"></c:import>
													<%-- 
													</c:when>
													<c:otherwise>
														<input type="text"  id="bannerUnitName${cate.categoryMetadataId }" 	
														  readonly="readonly" />
													</c:otherwise>
												</c:choose>	 --%>
													<input type="hidden" name="bannerUnitMetadataIds" id="bannerUnit${cate.categoryMetadataId }${catePojo.language.localeCode }" 
													 />
													
													<input type="hidden" name="childCategoryIds" value="${cate.categoryMetadataId }" />
												</div>
												<div class="col-md-1">
													<a href="javascript:void(0);" class="choose tn_c" 
													onclick="openDataType('${brannerKey}','bannerUnit${cate.categoryMetadataId }${catePojo.language.localeCode }','bannerUnitName${cate.categoryMetadataId }${catePojo.language.localeCode }')">Choose</a>
												</div>
									
											</div>
										</c:forEach>
		                    
		                  </div>
			                </div>
						</c:otherwise>
	              	</c:choose>
                </c:forEach>
            </div>
        </div>       
        <div class="text">
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Creator
                        	 </div>
                        	 <div class="col-md-4">
                        		${parentCategory.createUser }
                        	 </div>
                        	 <div class="col-md-6">
                        		Creation Date ${parentCategory.createTime }
                        	 </div>
                        </div>
                    	<div class="row">
                        	 <div class="col-md-2">
                        		Modifier
                        	 </div>
                        	 <div class="col-md-4">
                        		${parentCategory.modifyUser }
                        	 </div>
                        	 <div class="col-md-6">
                        		Modification Date ${parentCategory.modifyTime }
                        	 </div>
                        </div>
                    </div>
	        <div class="btns">
	        	<a href="javascript:void(0);" class="confirm" onclick = "updateCatalog();">Save</a>
	        	<a href="#" class="cancel">Cancel</a>
	        </div>
        </form>
        <div id="errorMessage" style="display: none"></div>
    </div>
    <!--main结束-->
</div>
</body>
<script type="text/javascript">
			$(document).ready(function() {
				$("select").each(function(){
					var select = $(this);
					var selectValue = select.attr("value");
					
					select.find("option[value='"+selectValue+"']").attr("selected",true);
				});
			});
			
			//判断catalogName是否唯一
			jQuery.validator.addMethod("isCatalogNameUnique", function(value, element) { 
				
			    var result = false;
			    var langValue = $(element).attr("lang");
			    var categoryMetaId = ${parentCategory.parentId};
			    var cateId = $(element).attr("categoryId");
			    
			    // 设置同步
			    $.ajaxSetup({
			        async: false
			    });
			    var param = {
			        catalogName: value,
			        lang:langValue,
			        categoryMetadataId:categoryMetaId,
			        categoryId:cateId
			    };
			    $.post("unlimited/checkCatalogName.action", param, function(data){
			    	data = eval(data);  
					var flag = data.flag;
			        result = ("0" == flag);
			    },"json");	
			    // 恢复异步
			    $.ajaxSetup({
			        async: true
			    }); 
			    return result;
			}, "<spring:message code="info.product.category.003" arguments="" argumentSeparator=","/>"); 
			
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
			
			function openDataType(id,elementId,elementName){
				$.layer({
			        type: 2,
			        title: 'Content Picker',
			        maxmin: false,
			        shadeClose: true, //开启点击遮罩关闭层
			        area : ['780px' , '650px'],
					shadeClose: false,
					move: false,
					offset : [($(window).height() - 600)/2+'px', ''],
			        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id+'&elementId='+elementId+'&elementName='+elementName}
			    
				});
			}
			
			function updateCatalog() {
				$("#errorMessage").html("");
				if ($("#editCatalogForm").valid()) {
					$.post("editCatalogSave.action", $("#editCatalogForm").serialize(),function(data) {
							var result = eval(data);
							alert(result.msg);
							var index = parent.layer.getFrameIndex(window.name);
							parent.location.reload();
							parent.layer.close(index);
						}, "json"); 
				}else{
					alert($("#errorMessage").html());
				}
			}
			
			//弹出一个iframe层
			function openData(){
				var id = ${brannerKey};
				
				$.layer({
			        type: 2,
			        title: 'Content Picker',
			        maxmin: false,
			        shadeClose: true, //开启点击遮罩关闭层
			        area : ['750' , '550px'],
					shadeClose: false,
			        offset : [($(window).height() - 400)/2+'px', ''],
			        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id}
			    
				}); 
			   /* alertFirstIframe("Content Picker",
						'${base}'+"/section/unlimited/dataTypePicker.action?dataTypeId="+id
								, '880px', '450px'); */
	    	}
		</script>
</html>

