<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Product</title>
<jsp:include page="../common/common.jsp" />
</head>
<body>
<div class="container queryConditions suites">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
    		<form id="newarrivalMetadata">
    			<input type="hidden" id="newArrivalMetadataId" name="newArrivalMetadataId" value="${NewArrivalMetadata.newArrivalMetadataId}">
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4">${NewArrivalMetadata.newArrivalMetadataId}</div>
	            	<div class="col-md-2 tn_c">SeqNo</div>
	  				<div class="col-md-4"><input type="text" name="seqNo" value="${NewArrivalMetadata.seqNo}"  /></div>
	            </div>
	        	<div class="row">			
	            	<div class="col-md-2">Catalog</div>            	
	            	<div class="col-md-4">
	                		<select name="categoryMetadataId" >
	                           <option value="">--请选择--</option>
	                            <c:forEach items="${masterData.Catalog}" var="category">
									<option value="${category.CATEGORY_METADATA_ID}" <c:if test="${category.CATEGORY_METADATA_ID == NewArrivalMetadata.categoryMetadataId}">selected</c:if> >${category.CATEGORY_NAME}</option>
								</c:forEach>
	                   		</select>                  
	                </div>  
	            </div>
            </form>        	
        </div>              
        <div class="tab" id="tab">
        	<ul class="tab_menu">
            	<c:forEach items="${languages}" var="language" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0}">
								<li class="active tn_c bold">${language.localeName}</li>
							</c:when>
							<c:otherwise>
								<li class="tn_c bold">${language.localeName}</li>
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
										<input type="hidden" name="lang" value="${language.localeId}" />
										<c:forEach items="${NewArrival}" var="newArrival">
										  <c:if test="${newArrival.lang eq language.localeId}">
											<input type="hidden" name="newArrivalId" value="${newArrival.newArrivalId}"/>
						                    <div class="row">
						                        <div class="col-md-2">Name_${language.localeCode}</div>
						                        <div class="col-md-4"><input type="text"  name="newArrivalName" value="${newArrival.newArrivalName}"  isUnique="${language.localeId}"  maxlength="25"  placeholder=""/></div> 
						                    </div>
						                    <div class="row">
						                        <div class="col-md-2">PC Template</div>
						                        <div class="col-md-4">
						                           <select name="pcTemplateId">
															<option></option>
															<c:forEach items="${masterData.tempList_pc}" var="templete_pc">
																<option value="${templete_pc.templateId}" <c:if test="${templete_pc.templateId eq newArrival.pcTemplateId}">selected</c:if>>${templete_pc.templateName}</option>
															</c:forEach>
													</select>
						                        </div> 
						                        <div class="col-md-2 tn_c">Mobile Template</div>
						                        <div class="col-md-4">
						                           <select name="mobileTemplateId">
															<option></option>
															<c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
																<option value="${templete_mobile.templateId}" <c:if test="${newArrival.mobileTemplateId eq templete_mobile.templateId}">selected</c:if> >${templete_mobile.templateName}</option>
															</c:forEach>
													</select>
						                        </div> 
						                    </div>
				                    		<div class="row seo">
						                        <div class="col-md-9 space">SEO (PC Website)</div>
						                    </div>
						                    <div class="row">
							                        <div class="col-md-2">Title</div>
							                        <div class="col-md-4"><input type="text" name="seoTitlePC" value="${newArrival.seoTitlePC}" /></div> 
							                        <div class="col-md-2 tn_c">H1 Tag</div>
							                        <div class="col-md-4"><input type="text" name="seoH1tagPC" value="${newArrival.seoH1tagPC}" /></div> 
							                </div>
						                    <div class="row">
						                        <div class="col-md-2">Keywords</div>
						                        <div class="col-md-10"><input type="text" name="seoKeywordsPC" value="${newArrival.seoKeywordsPC}" /></div> 
						                    </div>
						                    <div class="row large">
						                        <div class="col-md-2">Description</div>
						                        <div class="col-md-10">
						                            <textarea  rows="2"  name="seoDescriptionPC">${newArrival.seoDescriptionPC}
						                            </textarea>
						                        </div> 
						                    </div>
						                    <div class="row seo">
						                        <div class="col-md-9 space">SEO (Mobile Website)</div>
						                    </div>
						                    <div class="row">
							                        <div class="col-md-2">Title</div>
							                        <div class="col-md-4"><input type="text"name="seoTitleMobile"  value="${newArrival.seoTitleMobile}" /></div> 
							                        <div class="col-md-2 tn_c">H1 Tag</div>
							                        <div class="col-md-4"><input type="text"  name="seoH1tagMobile" value="${newArrival.seoH1tagMobile}" /></div> 
							                </div>
				                    
						                    <div class="row">
						                        <div class="col-md-2">Keywords</div>
						                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile" value="${newArrival.seoKeywordsMobile}" /></div> 
						                    </div>
						                    <div class="row large">
						                        <div class="col-md-2">Description</div>
						                        <div class="col-md-10">
						                            <textarea  rows="2" name="seoDescriptionMobile">${newArrival.seoDescriptionMobile}
						                            </textarea>
						                        </div> 
						                    </div>
						                 </c:if>
                   					   </c:forEach>   
				                    </form>                  
				                  </div>
				                  </c:when>
							<c:otherwise>
						      <div class="hide box">
					               <form id="lan">
									   <input type="hidden" name="lang" value="${language.localeId}" />
									   <c:forEach items="${NewArrival}" var="newArrival">
										  <c:if test="${newArrival.lang eq language.localeId}">
										  	  <input type="hidden" name="newArrivalId" value="${newArrival.newArrivalId}"/>
							                   <div class="row">
							                        <div class="col-md-2">Name_${language.localeCode}</div>
							                        <div class="col-md-4"><input type="text" name="newArrivalName" value="${newArrival.newArrivalName}" isUnique="${language.localeId}"  maxlength="25"  placeholder=""				/></div> 
							                    </div>
							                    <div class="row">
							                        <div class="col-md-2">PC Template</div>
							                        <div class="col-md-4">
							                           <select name="pcTemplateId">
																<option></option>
																<c:forEach items="${masterData.tempList_pc}" var="templete_pc">
																	<option value="${templete_pc.templateId}" <c:if test="${templete_pc.templateId eq newArrival.pcTemplateId}">selected</c:if>>${templete_pc.templateName}</option>
																</c:forEach>
														</select>
						                        	</div> 
						                       		 <div class="col-md-2 tn_c">Mobile Template</div>
							                        <div class="col-md-4">
							                           <select name="mobileTemplateId">
																<option></option>
																<c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
																	<option value="${templete_mobile.templateId}" <c:if test="${newArrival.mobileTemplateId eq templete_mobile.templateId}">selected</c:if> >${templete_mobile.templateName}</option>
																</c:forEach>
														</select>
							                        </div> 
							                    </div>
							                    <div class="row seo">
							                        <div class="col-md-9 space">SEO (PC Website)</div>
							                    </div>
							                    <div class="row">
							                        <div class="col-md-2">Title</div>
							                        <div class="col-md-4"><input type="text" name="seoTitlePC" value="${newArrival.seoTitlePC}"  /></div> 
							                        <div class="col-md-2 tn_c">H1 Tag</div>
							                        <div class="col-md-4"><input type="text" name="seoH1tagPC" value="${newArrival.seoH1tagPC}" /></div> 
							                    </div>
							                    <div class="row">
							                        <div class="col-md-2">Keywords</div>
							                        <div class="col-md-10"><input type="text" name="seoKeywordsPC" value="${newArrival.seoKeywordsPC}" /></div> 
							                    </div>
							                    <div class="row larger">
							                        <div class="col-md-2">Description</div>
							                        <div class="col-md-10">
							                            <textarea rows="2" name="seoDescriptionPC" value="${newArrival.seoDescriptionPC}" ></textarea>
							                        </div> 
							                    </div>
							                    <div class="row seo">
							                        <div class="col-md-9 space">SEO (Mobile Website)</div>
							                    </div>
							                   <div class="row">
							                        <div class="col-md-2">Title</div>
							                        <div class="col-md-4"><input type="text"name="seoTitleMobile" value="${newArrival.seoTitleMobile}"  /></div> 
							                        <div class="col-md-2 tn_c">H1 Tag</div>
							                        <div class="col-md-4"><input type="text"  name="seoH1tagMobile" value="${newArrival.seoH1tagMobile}" /></div> 
							                    </div>
							                    <div class="row">
							                        <div class="col-md-2">Keywords</div>
							                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile" value="${newArrival.seoKeywordsMobile}" /></div> 
							                    </div>
							                    <div class="row larger">
							                        <div class="col-md-2">Description</div>
							                        <div class="col-md-10">
							                            <textarea name="seoDescriptionMobile">${newArrival.seoDescriptionMobile}</textarea>
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
			    <form id="addPageForm"  name="addPageForm">
			   
        	<div class="text">
            <div class="row text_row">
                <div class="col-md-2 ">Hero Areas</div>
                <div class="col-md-9 border"><input type="text" value="3,2" id="heroAreaMetadataId" name="heroAreaMetadataId" /></div> 
                <div class="col-md-1">
                    <a class="choose tn_c" id="choose93"  onclick="openDataType('93');" href="#">Choose</a>
                 </div>
                
            </div>
            <div class="row text_row">
                <div class="col-md-2 ">Banner Units</div>
                <div class="col-md-9 border"><input type="text" id="bannerUnitMetadataId" name="bannerUnitMetadataId"  value="3"/></div> 
                <div class="col-md-1">
                    <a href="#" id="choose94" class="choose tn_c">Choose</a>
                </div>
            </div>
           
           
        	<div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${NewArrivalMetadata.createUser}</div>
					<div class="col-md-7">
						Creation Date ${NewArrivalMetadata.createTime}
						
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${NewArrivalMetadata.modifyUser}</div>
					<div class="col-md-7">
						Modification Date ${NewArrivalMetadata.modifyTime}
						
					</div>
				</div>
			</div>
        </div>
 	</form>
        <div class="btns">
        	<a href="javascript:;" class="confirm" onclick="EditProduct();return false;">Save</a>
        	<a href="#" class="cancel">Cancel</a>
        </div>
    </div>
    <script>
    $(document).ready(function(){
    	if('${actionType}' == 'show'){
    		$('input,select,textarea').attr('disabled','disabled');
    		$('.confirm').remove();
    		$('.del,.edit').removeAttr('onclick');
    		$("#choose94,#choose93").hide();
    	}
    });
   
        function EditProduct() {
    		var forms = $(".tab_box form");
    		var len = forms.length;
    		var products = '[';
    		$.each(forms, function(i, form) {
    			if (i == len - 1) {
    				products += JSON.stringify(formToJson(form));
    			} else {
    				products += JSON.stringify(formToJson(form));
    				products += ",";
    			}
    		});
    		products += "]";
    		
    		var bannerUnitMetadataId=$("#bannerUnitMetadataId").val(),
    			heroAreaMetadataId=$("#heroAreaMetadataId").val();
    		$.post("editSave.action",$("#newarrivalMetadata").serialize() 
    				+ "&products=" + products+"&bannerUnitMetadataId="+bannerUnitMetadataId+"&heroAreaMetadataId="+heroAreaMetadataId, function(data) {
    			var result = eval(data);
    			alert(result.msg);
    			var index = parent.layer.getFrameIndex(window.name);
    			parent.location.reload();
    			parent.layer.close(index);
    		}, "json");
    		
    	}
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
    	
	</script>
    <!--main结束-->
    <script  type="text/javascript">
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('.cancel').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
		
    </script>
</div>

</body>
</html>