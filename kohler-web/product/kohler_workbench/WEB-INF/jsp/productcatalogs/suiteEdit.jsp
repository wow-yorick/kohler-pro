<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Section</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
$(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index = $tab_li.index(this);
		
		$('div.tab_box > .box').eq(index).show().siblings().hide();
	});	
	$("#txt span").click(function(){
		var boxClass=$(this).attr("class");
		if(boxClass=="check"){
			$(this).attr("class","checked");
			}else{
			$(this).attr("class","check");}
		});	
	$('.confirm').on('click', function(){
		layer.closeAll()
	});

});
</script>
</head>
<body>
	<div
		class="container queryConditions suites">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		 <!--main开始-->
    <div class="main">
    	<div class="search">
    	<form id="suiteMetadataMetadata">
    	<input type="hidden" name="suiteMetadataId" value="${suiteMetadata.suiteMetadataId}" />
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4">${suiteMetadata.suiteMetadataId}</div>
            	<div class="col-md-2 tn_c">SeqNo</div>
  				<div class="col-md-4"><input type="text" name="seqNo"  required="required" maxlength="11" digits="true" value="${suiteMetadata.seqNo}" /></div>
            </div>
      	</form>
        </div>              
        <div class="tab" id="tab">
        <c:choose>
			<c:when test="${fn:length(masterData.locate) == 0 }">
				<tr>
					<td colspan ="4">Sorry,these is no data for this detail!</td>
				</tr>
			</c:when>

			<c:otherwise>
				<ul class="tab_menu">
				<c:forEach items="${masterData.locate}" var="language" varStatus="status">
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
				<c:forEach items="${suite}" var="suite" varStatus="status">
				<c:choose>
						<c:when test="${status.index == 0 }">
						<div class="box">
            	<form id="detailForm_${suite.lang}" action="addsuiteMetadata.action" action="post" >
            	<input type="hidden" name="lang" value="${suite.lang}" />
		    	<input type="hidden" name="suiteMetadataId" value="${suiteMetadata.suiteMetadataId}" />
		    	<input type="hidden" name="suiteId" value="${suite.suiteId}" />
                    <div class="row">
                        <div class="col-md-2 required">Name</div>
                        <div class="col-md-4"><input type="text" name="suiteName" isUnique="${suite.lang}"  required="required" maxlength="25" value="${suite.suiteName}" placeholder=""/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2 required">PC Template</div>
                        <div class="col-md-4">
                            <select name="pcTemplateId"  required="required" >
                            	<option value="">--请选择--</option>
                                <c:forEach items="${masterData.tempList_pc}" var="templete_pc">
										<option value="${templete_pc.templateId}" <c:if test="${suite.pcTemplateId eq templete_pc.templateId}">selected</c:if> >${templete_pc.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                        <div class="col-md-2 tn_c">Mobile Template</div>
                        <div class="col-md-4">
                            <select name="mobileTemplateId">
                            	<option value="">--请选择--</option>
                                 <c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
									<option value="${templete_mobile.templateId}" <c:if test="${suite.mobileTemplateId eq templete_mobile.templateId}">selected</c:if> >${templete_mobile.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                    </div>
                    <div class="row larger">
                        <div class="col-md-2">List Content</div>
                        <div class="col-md-10">
                            <textarea rows="4" required="required" id="list_content_${status.index}" name="listContent" id="Ueditor_list">
                            ${suite.listContent}
                            </textarea>
                        </div> 
                    </div>
                   <div class="row higher">
                             <div class="col-md-2">List Image Source</div>
                             <div class="col-md-6">
                                <select id="selectChoose">
                                    <option>External</option>
                                    <option>Internal</option>
                                </select>
                            </div>
                    </div>
                    <div class="row imgUrl_one">
                             <div class="col-md-2">List Image URL</div>
                             <div class="col-md-10">
                                <input type="text" />
                            </div>
                    </div>
                    <div class="row imgUrl_two" style="display:none;">
                            <div class="col-md-2 ">List Image URL</div>
                            <div class="col-md-9 border"><input type="text"/></div> 
                            <div class="col-md-1">
                                <a href="#" class="choose tn_c">Choose</a>
                            </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">List Image Alt</div>
                        <div class="col-md-10"><input type="text"/></div> 
                    </div>  
                    <div class="row">
                        <div class="col-md-2 ">Hot Spot</div>
                        <div class="col-md-9 border"><input type="text"/></div> 
                        <div class="col-md-1">
                            <a href="#" class="choose tn_c">Choose</a>
                        </div>
                    </div>
                   <div class="row seo">
                        <div class="col-md-9 space">SEO (PC Website)</div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitlePc" value="${suite.seoTitlePc}" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text"  name="seoH1tagPc"  value="${suite.seoH1tagPc}" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsPc" value="${suite.seoKeywordsPc}" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10">
                            <textarea name="seoDescriptionPc">
                             ${suite.seoDescriptionPc}
                            </textarea>
                        </div> 
                    </div>
                    <div class="row seo">
                        <div class="col-md-9 space">SEO (Mobile Website)</div>
                    </div>
                   <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitleMobile" value="${suite.seoTitleMobile}" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text" name="seoH1tagMobile"  value="${suite.seoH1tagMobile}"/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile"  value="${suite.seoKeywordsMobile}" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10" name="seoDescriptionMobile" >
                            <textarea>
                            ${suite.seoDescriptionMobile}
                            </textarea>
                        </div> 
                    </div>    
                   </form>               
                  </div>

                  </c:when>
							<c:otherwise>
							
            	<div class="hide box">
            	<form id="detailForm_${suite.lang}" action="addsuiteMetadata.action" action="post" >
            	<input type="hidden" name="lang" value="${suite.lang}" />
		    	<input type="hidden" name="suiteMetadataId" value="${suiteMetadata.suiteMetadataId}" />
		    	<input type="hidden" name="suiteId" value="${suite.suiteId}" />
                    <div class="row">
                        <div class="col-md-2">Name</div>
                        <div class="col-md-4"><input type="text" name="suiteName"  isUnique="${suite.lang}"  maxlength="25"  value="${suite.suiteName}" placeholder=""/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">PC Template</div>
                        <div class="col-md-4">
                            <select name="pcTemplateId" required="required" >
                            	<option value="">--请选择--</option>
                                <c:forEach items="${masterData.tempList_pc}" var="templete_pc">
										<option value="${templete_pc.templateId}" <c:if test="${suite.pcTemplateId eq templete_pc.templateId}">selected</c:if> >${templete_pc.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                        <div class="col-md-2 tn_c">Mobile Template</div>
                        <div class="col-md-4">
                            <select name="mobileTemplateId">
                            	<option value="">--请选择--</option>
                                 <c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
									<option value="${templete_mobile.templateId}"  value="${suite.mobileTemplateId eq templete_mobile.templateId}" >${templete_mobile.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                    </div>
                    <div class="row larger">
                        <div class="col-md-2">List Content</div>
                        <div class="col-md-10">
                            <textarea rows="4" required="required" id="list_content_${status.index}" name="listContent">
                            ${suite.listContent}
                            </textarea>
                        </div> 
                    </div>
                    <div class="row higher">
                             <div class="col-md-2">List Image Source</div>
                             <div class="col-md-6">
                                <select id="selectChoose">
                                    <option>External</option>
                                    <option>Internal</option>
                                </select>
                            </div>
                    </div>
                    <div class="row imgUrl_one">
                             <div class="col-md-2">List Image URL</div>
                             <div class="col-md-10">
                                <input type="text" />
                            </div>
                    </div>
                    <div class="row imgUrl_two" style="display:none;">
                            <div class="col-md-2 ">List Image URL</div>
                            <div class="col-md-9 border"><input type="text"/></div> 
                            <div class="col-md-1">
                                <a href="#" class="choose tn_c">Choose</a>
                            </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">List Image Alt</div>
                        <div class="col-md-10"><input type="text"/></div> 
                    </div>  
                    <div class="row">
                        <div class="col-md-2 ">Hot Spot</div>
                        <div class="col-md-9 border"><input type="text"/></div> 
                        <div class="col-md-1">
                            <a href="#" class="choose tn_c">Choose</a>
                        </div>
                    </div>
                   <div class="row seo">
                        <div class="col-md-9 space">SEO (PC Website)</div>
                    </div>
                    <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitlePc" value="${suite.seoTitlePc}" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text"  name="seoH1tagPc"  value="${suite.seoH1tagPc}" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsPc" value="${suite.seoKeywordsPc}" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10">
                            <textarea name="seoDescriptionPc">
                             ${suite.seoDescriptionPc}
                            </textarea>
                        </div> 
                    </div>
                    <div class="row seo">
                        <div class="col-md-9 space">SEO (Mobile Website)</div>
                    </div>
                   <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitleMobile" value="${suite.seoTitleMobile}" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text" name="seoH1tagMobile"  value="${suite.seoH1tagMobile}"/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile"  value="${suite.seoKeywordsMobile}" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10" name="seoDescriptionMobile" >
                            <textarea>
                            ${suite.seoDescriptionMobile}
                            </textarea>
                        </div> 
                    </div>    
                    </form>                  
                  </div>
                  
                  </c:otherwise>
						</c:choose>
                  </c:forEach>
            </div>
			</c:otherwise>
		</c:choose>
           
        </div>
      
        <table class="big" border="0" cellspacing="0" cellpadding="0">
        	<tr class="head">
            	<th class="head_l"></th>
            	<th class="head_r"><a href="javascript:void(0);"  onclick="skuAction.create();" class="sku_new" id="skuCreate">New</a></th>
            </tr>
            <tr class="inside_border">
            	<td colspan="2">
                    <table class="small" border="0" cellspacing="0" cellpadding="0">
                        <tr class="heading">
                            <th style="width:20%">Action</th>
                            <th style="width:80%">SKU</th>
                        </tr>
                        <tbody id="formDetailTable">
                          <c:forEach items="${suiteProduct}" var="suiteProduct" varStatus="status">
	                        <tr>
	                            <td class="tn_c">
	                            	<form class="suiteProductForm" >
		                            <input type="hidden" name="suiteProductId" value="${suiteProduct.SUITE_PRODUCT_ID}" />
		                            <input type="hidden" name="suiteMetadataId" value="${suiteProduct.SUITE_METADATA_ID}" />
		                            <input type="hidden" name="skuMetadataId" value="${suiteProduct.SKU_METADATA_ID}" />
		                            <input type="hidden" name="seqNo" value="${suiteProduct.SEQ_NO}" />
		                            <input type="hidden" data-sku="skuCode" value="${suiteProduct.SKU_CODE}" />
		                            <span class="edit" onclick="skuAction.edit(this)"></span>
		                            <span class="del" onclick="skuAction.delet(this)" ></span>
		                            </form>
	                            </td>
	                            <td class="last">${suiteProduct.SKU_CODE}</td>
	                        </tr>                     
                        </c:forEach>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
        
        <div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${suiteMetadata.createUser}</div>
					<div class="col-md-7">
						Creation Date
						<fmt:formatDate value="${suiteMetadata.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${suiteMetadata.modifyUser}</div>
					<div class="col-md-7">
						Modification Date
						<fmt:formatDate value="${suiteMetadata.modifyTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
			</div>
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm">save</a>
        	<a href="javascript:void(0);" class="cancel">cancel</a>
        </div>
    </div>
    <!--main结束-->
	</div>
</body>

<script  type="text/javascript">
$.validator.addMethod("isUnique", function(value, element, param) {
	 var result = false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
    var arguments = {
        lang: param,
        suiteName:value,
        suiteId:$("#detailForm_"+param).find("input[name='suiteId']").val(),
        
    };
    console.log(arguments);
    $.post("unlimited/uniquenessVerification.action", arguments, function(data){
        result = data;
    });
    // 恢复异步
    $.ajaxSetup({
        async: true
    });
    
	return  this.optional(element) || result;
}, 'this Name has been existed"!');

$(document).ready(function(){
	if('${actionType}' == 'show'){
		$('input,select,textarea').attr('disabled','disabled');
		$('.confirm').remove();
		$('#skuCreate').removeAttr('onclick');
		$('.del,.edit').removeAttr('onclick');
		
	}
});
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('.cancel').on('click', function(){
    		parent.layer.close(index); //执行关闭
		});
    	
    	$(".confirm").on('click',function(){
    		var forms = $(".tab_box form");
    		//suite
    		var len = forms.length;
    		var suite = '[';
    		$.each(forms, function(i, form) {
    			if (i == len - 1) {
    				suite += JSON.stringify(formToJson(form));
    			} else {
    				suite += JSON.stringify(formToJson(form));
    				suite += ",";
    			}
    		});
    		suite += "]";
    		
    		//suiteProduct
    		var formsSP = $(".suiteProductForm");
    		var lenSP = formsSP.length;
    		var suiteProduct = '[';
    		$.each(formsSP, function(i, form) {
    			if (i == lenSP - 1) {
    				suiteProduct += JSON.stringify(formToJson(form));
    			} else {
    				suiteProduct += JSON.stringify(formToJson(form));
    				suiteProduct += ",";
    			}
    		});
    		suiteProduct += "]";
    		
    		$.post("editSave.action", "jsonSuite=" + suite +"&"+$('#suiteMetadataMetadata').serialize()+'&jsonSuiteProduct='+suiteProduct, 
    				function(data) {
    				if(data.success) {
    					var index = parent.layer.getFrameIndex(window.name);
        				parent.location.reload();
        				parent.layer.close(index);
    				} else {
    					alert(data.msg);
	    				//layer.msg(data.msg, 1, 0);//按钮一的回调函数
    				}
    				
    			}, "json");
    		
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
    	
/******************************
 * SKU操作START
 *******************************/
 var skuAction = (function($){
		var tools = {
				skuObj : '#formDetailTable',
				
				init : function() {
					tools.evenHighlight();
				},
				
				evenHighlight : function() {
					$(tools.skuObj).find("tr:even").addClass('gray');
				},
				
				create : function(thisObj) {
					var suiteMetadataId = $('#suiteMetadataMetadata').find('[name=suiteMetadataId]').val();
					alertFirstIframe("SuiteProduct","unlimited/create.action?suiteMetadataId="+suiteMetadataId,'500px','325px');
				},
				
				edit : function(thisObj) {
					try{
						var arguments = $(thisObj).parent('.suiteProductForm').serialize();
						alertFirstIframe("SuiteProduct","unlimited/edit.action?"+arguments,'500px','325px');
					}catch(e) {
						alert('have some trouble,please try again!');
					}
				},
				
				delet : function(thisObj) {
					var keyId = $(thisObj).parent('form').find('[name=suiteProductId]').val();
					$.layer({
						shade: [0],
						title: 'Confirm',
						area: ['310px', '130px'],
						dialog: {
							msg: 'Delete this data?',
							btns: 2,                    
							type: -1,
							btn: ['OK','Cancel'],
							yes: function(){
								$.post("unlimited/delete.action",{'suiteProductId':keyId},function(data) {
									//layer.msg(data.msg, 1, 1);//按钮一的回调函数
									alert(data.msg);
									location.reload();
								}, "json");
											
							}
						}
					});
				}
				
			};
		tools.init();
		return tools;
	})(window.jQuery);
/******************************
 * SKU操作END
 *******************************/
 
 <c:forEach items="${masterData.locate}" var="locate" varStatus="status">
	UeditorFileLoad.init("list_content_${status.index}");
</c:forEach>

    </script>
</html>

