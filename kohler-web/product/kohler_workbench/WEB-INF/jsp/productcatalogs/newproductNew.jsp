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
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4"></div>
	            	<div class="col-md-2 tn_c">SeqNo</div>
	  				<div class="col-md-4"><input type="text" name="seqNo"  /></div>
	            </div>
	        	<div class="row">			
	            	<div class="col-md-2">Catalog</div>            	
	            	<div class="col-md-4">
	                		<select name="categoryMetadataId" >
	                           <option value="">--请选择--</option>
	                            <c:forEach items="${masterData.Catalog}" var="category">
									<option value="${category.CATEGORY_METADATA_ID}">${category.CATEGORY_NAME}</option>
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
							<c:when test="${status.index == 0 }">
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
										<input type="hidden" name="lang" value="${language.localeId }" />
				                    <div class="row">
				                        <div class="col-md-2">Name_${language.localeCode}</div>
				                        <div class="col-md-4"><input type="text"  name="newArrivalName"  isUnique="${language.localeId}"  maxlength="25"  placeholder=""/></div> 
				                    </div>
				                    <div class="row">
				                        <div class="col-md-2">PC Template</div>
				                        <div class="col-md-4">
				                           <select name="pcTemplateId">
													<option></option>
													<c:forEach items="${masterData.tempList_pc}" var="templete_pc">
														<option value="${templete_pc.templateId}">${templete_pc.templateName}</option>
													</c:forEach>
											</select>
											
				                        </div> 
				                        <div class="col-md-2 tn_c">Mobile Template</div>
				                        <div class="col-md-4">
				                           <select name="mobileTemplateId">
													<option></option>
													<c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
														<option value="${templete_mobile.templateId}">${templete_mobile.templateName}</option>
													</c:forEach>
											</select>
				                        </div> 
				                    </div>
				                    
				                   <div class="row seo">
				                        <div class="col-md-9 space">SEO (PC Website)</div>
				                    </div>
				                    <div class="row">
					                        <div class="col-md-2">Title</div>
					                        <div class="col-md-4"><input type="text" name="seoTitlePC" /></div> 
					                        <div class="col-md-2 tn_c">H1 Tag</div>
					                        <div class="col-md-4"><input type="text" name="seoH1tagPC" /></div> 
					                </div>
				                    <div class="row">
				                        <div class="col-md-2">Keywords</div>
				                        <div class="col-md-10"><input type="text" name="seoKeywordsPC" /></div> 
				                    </div>
				                    <div class="row large">
				                        <div class="col-md-2">Description</div>
				                        <div class="col-md-10">
				                            <textarea  rows="2"  name="seoDescriptionPC">
				                            </textarea>
				                        </div> 
				                    </div>
				                    <div class="row seo">
				                        <div class="col-md-9 space">SEO (Mobile Website)</div>
				                    </div>
				                    <div class="row">
					                        <div class="col-md-2">Title</div>
					                        <div class="col-md-4"><input type="text"name="seoTitleMobile" /></div> 
					                        <div class="col-md-2 tn_c">H1 Tag</div>
					                        <div class="col-md-4"><input type="text"  name="seoH1tagMobile"/></div> 
					                    </div>
				                    
				                    <div class="row">
				                        <div class="col-md-2">Keywords</div>
				                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile" /></div> 
				                    </div>
				                    <div class="row large">
				                        <div class="col-md-2">Description</div>
				                        <div class="col-md-10">
				                            <textarea  rows="2" name="seoDescriptionMobile">
				                            </textarea>
				                        </div> 
				                    </div>
				                    </form>                  
				                  </div>
				                  </c:when>
							<c:otherwise>
						      <div class="hide box">
					               <form id="lan">
									   <input type="hidden" name="lang" value="${language.localeId}" />
					                   <div class="row">
					                        <div class="col-md-2">Name_${language.localeCode}</div>
					                        <div class="col-md-4"><input type="text" name="newArrivalName"  isUnique="${language.localeId}"  maxlength="25"  placeholder=""				/></div> 
					                    </div>
					                    <div class="row">
					                        <div class="col-md-2">PC Template</div>
					                        <div class="col-md-4">
					                           <select name="pcTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
					                        </div> 
					                        <div class="col-md-2 tn_c">Mobile Template</div>
					                        <div class="col-md-4">
					                           <select name="mobileTemplateId">
													<option></option>
													<c:forEach items="${templates}" var="t">
														<option value="${t.templateId}">${t.templateName}</option>
													</c:forEach>
												</select>
					                        </div> 
					                    </div>
					                    
					                   <div class="row seo">
					                        <div class="col-md-9 space">SEO (PC Website)</div>
					                    </div>
					                    <div class="row">
					                        <div class="col-md-2">Title</div>
					                        <div class="col-md-4"><input type="text" name="seoTitlePC" /></div> 
					                        <div class="col-md-2 tn_c">H1 Tag</div>
					                        <div class="col-md-4"><input type="text" name="seoH1tagPC" /></div> 
					                    </div>
					                    <div class="row">
					                        <div class="col-md-2">Keywords</div>
					                        <div class="col-md-10"><input type="text" name="seoKeywordsPC"/></div> 
					                    </div>
					                    <div class="row larger">
					                        <div class="col-md-2">Description</div>
					                        <div class="col-md-10">
					                            <textarea rows="2" name="seoDescriptionPC"></textarea>
					                        </div> 
					                    </div>
					                    <div class="row seo">
					                        <div class="col-md-9 space">SEO (Mobile Website)</div>
					                    </div>
					                   <div class="row">
					                        <div class="col-md-2">Title</div>
					                        <div class="col-md-4"><input type="text"name="seoTitleMobile" /></div> 
					                        <div class="col-md-2 tn_c">H1 Tag</div>
					                        <div class="col-md-4"><input type="text"  name="seoH1tagMobile"/></div> 
					                    </div>
					                    <div class="row">
					                        <div class="col-md-2">Keywords</div>
					                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile"/></div> 
					                    </div>
					                    <div class="row larger">
					                        <div class="col-md-2">Description</div>
					                        <div class="col-md-10">
					                            <textarea name="seoDescriptionMobile"></textarea>
					                        </div> 
					                    </div>           
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
                    <a class="choose tn_c" onclick="openDataType('93');" href="#">Choose</a>
                    <input type='hidden' name='datatype93' value='' />
                </div>
                
            </div>
            <div class="row text_row">
                <div class="col-md-2 ">Banner Units</div>
                <div class="col-md-9 border"><input type="text" id="bannerUnitMetadataId" name="bannerUnitMetadataId"  value="3"/></div> 
                <div class="col-md-1">
                    <a href="#" class="choose tn_c">Choose</a>
                </div>
            </div>
           
           <!--  
        	<div class="row">
            	 <div class="col-md-1">
            		Creator
            	 </div>
            	 <div class="col-md-5">
            		王明
            	 </div>
            	 <div class="col-md-6">
            		Creation Date2014-07-01 08:00
            	 </div>
            </div>
        	<div class="row">
            	 <div class="col-md-1">
            		Modifier
            	 </div>
            	 <div class="col-md-5">
            		王明
            	 </div>
            	 <div class="col-md-6">
            		Modification Date2014-07-01 08:00
            	 </div>
            </div>
            -->
        </div>
 	</form>
        <div class="btns">
        	<a href="javascript:;" class="confirm" onclick="addProduct();return false;">Save</a>
        	<a href="#" class="cancel">Cancel</a>
        </div>
    </div>
    <script>
    	
        function addProduct() {
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
    		//bannerUnitMetadataId heroAreaMetadataId
    		var bannerUnitMetadataId=$("#bannerUnitMetadataId").val(),
    			heroAreaMetadataId=$("#heroAreaMetadataId").val();
    		$.post("createSave.action",$("#newarrivalMetadata").serialize() +
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
    	function openDataType(id){
		    $.layer({
		        type: 2,
		        title: 'Please Select a DataType',
		        maxmin: false,
		        shadeClose: true, //开启点击遮罩关闭层
		        area : ['780px' , '650px'],
				shadeClose: false,
		        offset : [($(window).height() - 300)/2+'px', ''],
		        iframe: {src: '<%=request.getContextPath()%>/section/unlimited/dataTypePicker.action?dataTypeId='+id}
		    
			});
    	}
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