<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Suite</title>
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
	
	//imgurlchoose显示隐藏
    $('#selectChoose').bind('change',function(){ 
       var $select2=$("#selectChoose").find('option:selected').text();
       if($select2=='Internal')
       {
           $('.imgUrl_one').hide();  
           $('.imgUrl_two').show(); 
       } 
       else
       {
           $('.imgUrl_one').show();  
           $('.imgUrl_two').hide();     
       }  
   })

});

function openSkuPicker(elementId,isMulti,elementName){
    $.layer({
        type: 2,
        title: 'Please Select SKU',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['700px' , '500px'],
		shadeClose: false,
        offset : [($(window).height() - 300)/2+'px', ''],
        iframe: {src: '<%=request.getContextPath()%>/skupicker/unlimited/skuPickerList.action?elementId='+elementId+'&isMulti='+isMulti+'&elementName='+elementName}
    
	});
}
</script>
</head>
<body>
	<div class="container queryConditions suites">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		 <!--main开始-->
    <div class="main">
    	<div class="search">
    	<form id="suiteMetadata">
        	<div class="row">
            	<div class="col-md-2">Id</div>
            	<div class="col-md-4"></div>
            	<div class="col-md-2 tn_c">SeqNo</div>
  				<div class="col-md-4"><input type="text" name="seqNo" required="required" maxlength="11" digits="true" /></div>
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
				<c:forEach items="${masterData.locate}" var="locate" varStatus="status">
				<c:choose>
						<c:when test="${status.index == 0 }">
						<div class="box">
            	<form id="detailForm_${locate.localeCode}" action="addSuite.action" action="post" >
            	<input type="hidden" name="lang" value="${locate.localeId}" />
                    <div class="row">
                        <div class="col-md-2 required">Name</div>
                        <div class="col-md-4"><input type="text" name="suiteName" isUnique="${locate.localeId}"  required="required" maxlength="25" placeholder=""/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">PC Template</div>
                        <div class="col-md-4">
                            <select name="pcTemplateId"  required="required" >
                            	<option value="">--请选择--</option>
                                <c:forEach items="${masterData.tempList_pc}" var="templete_pc">
										<option value="${templete_pc.templateId}">${templete_pc.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                        <div class="col-md-2 tn_c">Mobile Template</div>
                        <div class="col-md-4">
                            <select name="mobileTemplateId">
                            	<option value="">--请选择--</option>
                                 <c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
									<option value="${templete_mobile.templateId}">${templete_mobile.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                    </div>
                    <div class="row larger">
                        <div class="col-md-2">List Content</div>
                        <div class="col-md-10">
                            <textarea rows="4"  required="required"  id="list_content_${status.index}" name="listContent">
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
                        <div class="col-md-4"><input type="text" name="seoTitlePc" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text"  name="seoH1tagPc" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsPc" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10">
                            <textarea name="seoDescriptionPc">
                            </textarea>
                        </div> 
                    </div>
                    <div class="row seo">
                        <div class="col-md-9 space">SEO (Mobile Website)</div>
                    </div>
                   <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitleMobile" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text" name="seoH1tagMobile" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10" name="seoDescriptionMobile" >
                            <textarea>
                            </textarea>
                        </div> 
                    </div>    
                   </form>               
                  </div>

                  </c:when>
							<c:otherwise>
							
            	<div class="hide box">
            	<form id="detailForm_${locate.localeCode}" action="addSuite.action" action="post" >
            	<input type="hidden" name="lang" value="${locate.localeId}" />
                    <div class="row">
                        <div class="col-md-2">Name</div>
                        <div class="col-md-4"><input type="text" name="suiteName"  isUnique="${locate.localeId}"  maxlength="25"  placeholder=""/></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">PC Template</div>
                        <div class="col-md-4">
                            <select name="pcTemplateId" >
                            	<option  value="" >--请选择--</option>
                                <c:forEach items="${masterData.tempList_pc}" var="templete_pc">
										<option value="${templete_pc.templateId}">${templete_pc.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                        <div class="col-md-2 tn_c">Mobile Template</div>
                        <div class="col-md-4">
                            <select name="mobileTemplateId">
                                 <option value="">--请选择--</option>
                                 <c:forEach items="${masterData.tempList_mobile}" var="templete_mobile">
									<option value="${templete_mobile.templateId}">${templete_mobile.templateName}</option>
								</c:forEach>
                            </select>
                        </div> 
                    </div>
                    <div class="row larger">
                        <div class="col-md-2">List Content</div>
                        <div class="col-md-10">
                            <textarea rows="4"  id="list_content_${status.index}"  name="listContent">
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
                        <div class="col-md-4"><input type="text" name="seoTitlePc" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text"  name="seoH1tagPc" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsPc" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10">
                            <textarea name="seoDescriptionPc">
                            </textarea>
                        </div> 
                    </div>
                    <div class="row seo">
                        <div class="col-md-9 space">SEO (Mobile Website)</div>
                    </div>
                   <div class="row">
                        <div class="col-md-2">Title</div>
                        <div class="col-md-4"><input type="text" name="seoTitleMobile" /></div> 
                        <div class="col-md-2 tn_c">H1 Tag</div>
                        <div class="col-md-4"><input type="text" name="seoH1tagMobile" /></div> 
                    </div>
                    <div class="row">
                        <div class="col-md-2">Keywords</div>
                        <div class="col-md-10"><input type="text" name="seoKeywordsMobile" /></div> 
                    </div>
                    <div class="row large">
                        <div class="col-md-2">Description</div>
                        <div class="col-md-10"  >
                            <textarea name="seoDescriptionMobile">
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
            	<th class="head_r"><a href="javascript:void(0);" onclick="skuAction.create();" class="sku_new">New</a></th>
            </tr>
            <tr class="inside_border">
            	<td colspan="2">
                    <table class="small" border="0" cellspacing="0" cellpadding="0">
                        <tr class="heading">
                            <th style="width:20%">Action</th>
                            <th style="width:80%">SKU</th>
                        </tr>
                        <tbody id="formDetailTable">
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
        
        <div class="text">
			<div class="row">
				<div class="col-md-2">Creator</div>
				<div class="col-md-4"></div>
				<div class="col-md-3">Creation Date</div>
				<div class="col-md-3">
					
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">Modifier</div>
				<div class="col-md-4"></div>
				<div class="col-md-3">Modification Date</div>
				<div class="col-md-3">
				</div>
			</div>
		</div>
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm" onclick="mainConfirm()">save</a>
        	<a href="javascript:void(0);" class="cancel">cancel</a>
        </div>
    </div>
    <!--main结束-->
	</div>
	
	<div id="errorMessage" style="display:none"></div>
</body>
<script  type="text/javascript">
$.validator.addMethod("isUnique", function(value, element, param) {
	 var result = false;
	 if(value == "") {
		 return true;
	 }
     // 设置同步
     $.ajaxSetup({
         async: false
     });
     var arguments = {
         lang: param,
         suiteName:value
     };
     $.post("unlimited/uniquenessVerification.action", arguments, function(data){
         result = data;
     });
     // 恢复异步
     $.ajaxSetup({
         async: true
     });
     
	return  this.optional(element) || result;
}, 'this Name has been existed"!');

//sku操作类  新增，删除 编辑
var skuAction = (function($){
	var tools = {
			skuObj : '#formDetailTable',
			
			init : function() {
				tools.evenHighlight();
			},
			
			evenHighlight : function() {
				$(tools.skuObj).find("tr:even").addClass('gray');
			},
			
			create : function() {
				alertFirstIframe("SuiteProduct","unlimited/create.action",'500px','325px');
			},
			
			edit : function(thisObj) {
				try{
					var skuCode = $(thisObj).parent('.suiteProductForm').find('[data-sku=skuCode]').val()
					var arguments = $(thisObj).parent('.suiteProductForm').serialize()+'&skuCode='+skuCode;
					alertFirstIframe("SuiteProduct","editSuiteProductPage.action?"+arguments,'500px','325px');
				}catch(e) {
					//layer.msg('have some trouble,please try again!',1,0);
					alert('<spring:message code="info.common.update.failed" arguments="" argumentSeparator=","/>')
				}
			},
			
			delet : function(thisObj) {
				$(".xubox_main").addClass("a")
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
							$(thisObj).parent().parent().parent('tr').remove();
							//layer.msg('Delete the success', 1, 1);//按钮一的回调函数
							alert('<spring:message code="info.common.delete.success" arguments="" argumentSeparator=","/>')
						}
					}
				});
			}
			
		};
	tools.init();
	return tools;
})(window.jQuery);

var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
$('.cancel').on('click', function(){
  		parent.layer.close(index); //执行关闭
});
  	
 	function mainConfirm(){
 		$("#errorMessage").html('');
 		var isValid = true;
 		var forms = $(".tab_box form");
 		var len = forms.length;
 		var suite = '[';
 		$.each(forms, function(i, form) {
 			if(!forms.valid()) {
 				isValid = false;
 			}
 			console.log(forms.valid());
 			if (i == len - 1) {
 				
 				suite += JSON.stringify(formToJson(form));
 			} else {
 				suite += JSON.stringify(formToJson(form));
 				
 				suite += ",";
 			}
 		});
 		suite += "]";
 		
 		if(!isValid) {
 			if ($("#errorMessage").html() != "") {
 				alert($("#errorMessage").html());
 				return false;
 			}
 			return false;
 		}
 		
 		var formSKU = $('.suiteProductForm');
 		var skuLen = formSKU.length;
 		var suiteProduct = '[';
 		$.each(formSKU, function(i, form) {
 			if (i == skuLen - 1) {
 				suiteProduct += JSON.stringify(formToJson(form));
 			} else {
 				suiteProduct += JSON.stringify(formToJson(form));
 				suiteProduct += ",";
 			}
 		});
 		suiteProduct += "]";
 		$.post("createSave.action", "suite=" + encodeURIComponent(suite) +"&"+$('#suiteMetadata').serialize()+"&suiteProduct="+encodeURIComponent(suiteProduct), 
				function(data) {
 				if(data.success) {
 					var index = parent.layer.getFrameIndex(window.name);
 					parent.location.reload();
 					parent.layer.close(index);
 				}else{
 					//layer.msg(data.msg, 1, 0);//按钮一的回调函数
 					alert(data.msg);
 				}
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
    	<c:forEach items="${masterData.locate}" var="locate" varStatus="status">
	    	UeditorFileLoad.init("list_content_${status.index}");
    	</c:forEach>
    </script>
</html>

