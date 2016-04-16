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
<title>Collection</title>
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
    		<form id="CollectionStyleMetadata">
	    		<input type="hidden"  name="collectionStyleMetadataId" value="${CollectionStyleMetadata.collectionStyleMetadataId}" />
	        	<div class="row">
	            	<div class="col-md-2">Id</div>
	            	<div class="col-md-4">${CollectionStyleMetadata.collectionStyleMetadataId}</div>
	            </div>
	        	<div class="row">
	            	<div class="col-md-2">SeqNo</div>
	  				<div class="col-md-4"><input type="text" name="seqNo" value="${CollectionStyleMetadata.seqNo}" /></div>
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
										<c:forEach items="${CollectionStyle}" var="CollectionStyle">
										  <c:if test="${CollectionStyle.lang eq language.localeId}">
							                    <div class="row">
							                        <div class="col-md-2">Name_${language.localeCode}</div>
							                        <div class="col-md-4"><input type="text"  name="collectionStyleName" value="${CollectionStyle.collectionStyleName}" isUnique="${language.localeId}"  maxlength="25"  placeholder=""/></div> 
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
									   	<c:forEach items="${CollectionStyle}" var="CollectionStyle">
										  <c:if test="${CollectionStyle.lang eq language.localeId}">
							                   <div class="row">
							                        <div class="col-md-2">Name_${language.localeCode}</div>
							                        <div class="col-md-4"><input type="text"  name="collectionStyleName" value="${CollectionStyle.collectionStyleName}"  isUnique="${language.localeId}"  maxlength="25"  placeholder=""/></div> 
							                    </div>
							              </c:if>
                   					    </c:forEach>   
					              </form>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    </div>
			    <div class="text">
					<div class="row">
						<div class="col-md-2">Creator</div>
						<div class="col-md-3">${CollectionStyleMetadata.createUser}</div>
						<div class="col-md-7">
							Creation Date ${CollectionStyleMetadata.createTime}
							
						</div>
	
					</div>
					<div class="row">
						<div class="col-md-2">Modifier</div>
						<div class="col-md-3">${CollectionStyleMetadata.modifyUser}</div>
						<div class="col-md-7">
							Modification Date ${CollectionStyleMetadata.modifyTime}
						</div>
					</div>
				</div>
		<div class="btns">
        	<a href="javascript:;" class="confirm" onclick="editCollectionStyle();return false;">Save</a>
        	<a href="#" class="cancel">Cancel</a>
        </div>
    </div>
    <script>
    $(document).ready(function(){
    		if('${actionType}' == 'show'){
	    		$('input,select,textarea').attr('disabled','disabled');
	    		$('.confirm').remove();
	    		$('.del,.edit').removeAttr('onclick');
    		}
   	});
        function editCollectionStyle() {
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
    		$.post("editSave.action",$("#CollectionStyleMetadata").serialize() 
    				+ "&products=" + products, function(data) {
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