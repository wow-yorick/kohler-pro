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
<div class="container queryConditions product_layer">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    <form action="" id="suiteProductDetailForm">
    	<input type="hidden" name="suiteMetadataId" value="" />
    	<div class="search">
        	<div class="row">
            	<div class="col-md-3">Id</div>
            	<div class="col-md-4"></div> 
            </div>  
            <div class="row">
                <div class="col-md-3 ">Product & SKU</div>
                <div class="col-md-4 "><input type="text" name="skuCode" id="skuCode" />skuCode
                <input type="text" id="skuMetadataId"  name="skuMetadataId" />skuMetadataId</div> 
                <div class="col-md-2">
                    <a href="javascript:void(0);" class="choose tn_c">Choose</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 tn_l">SeqNo</div>
                <div class="col-md-6"> 
                    <input type="text"  name="seqNo"  />
                </div> 
            </div>          	
        </div>
        </form>
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm" onclick="chooseSave();">Save</a>
        	<a href="javascript:void(0);" class="cancel" id="closebtn">Cancel</a>
        </div>
    </div>
    <!--main结束-->
</div>

<script  type="text/javascript">
//修改中新增页面设置suiteMetadataId
$(document).ready(function(){
	var suiteMetadataId = PAGE.util.getQueryString('suiteMetadataId');
	if(suiteMetadataId) {
		$('[name=suiteMetadataId]').val(suiteMetadataId);
	}
});
var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引

//关闭按钮
$('#closebtn').on('click', function(){
  	parent.layer.close(index); //执行关闭
});

//选择产品
$('.choose').on('click', function(){
    parent.$.layer({
        type: 2,
        title: 'SKU Picker',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['800' , '600px'],
        shadeClose: false,
        offset : [($(window).height() - 400)/2+'px', ''],
        iframe: {src: 'skuPicker_layerbox.html'}
    });
});

//选择新增suite中的新增suiteProduct 或者 编辑suite中的suiteProduct的新增
function chooseSave() {
	var suiteMetadataId = $('[name=suiteMetadataId]').val();
	if(suiteMetadataId) {
		addSuiteProductToDB();
	}else {
		addSuiteProductToHTMLTEMP();
	}
	
}

//for suiteEdite
function addSuiteProductToDB() {
	var detailForm = $('#suiteProductDetailForm');
	$.post("unlimited/createSave.action", detailForm.serialize(), 
			function(data) {
				if(data.success) {
					var suiteProductId = data.obj;
					var suiteMetadataId = detailForm.find('[name=suiteMetadataId]').val();
					var skuMetadataId = detailForm.find('[name=skuMetadataId]').val();
					var seqNo = detailForm.find('[name=seqNo]').val();
					var skuCode = detailForm.find('[name=skuCode]').val();
					var html = '<tr>'
						    +'<td class="tn_c">'
						    +'<form class="suiteProductForm">'
						    +'<input type="hidden" name="suiteProductId" value="'+suiteProductId+'" />'
						    +'<input type="hidden" name="suiteMetadataId" value="'+suiteMetadataId+'" />'
							+'<input type="hidden" name="skuMetadataId" value="'+skuMetadataId+'" />'
						    +'<input type="hidden" name="seqNo" value="'+seqNo+'" />'
						    +'<input type="hidden" data-sku="skuCode" value="'+skuCode+'" />'
						    +'<span class="edit" onclick="skuAction.edit(this)" ></span>'
						    +'<span class="del"  onclick="skuAction.delet(this)" ></span>'
						    +'</form>'
						+'</td>'
						+'<td class="last">'+skuCode+'</td>'
						+'</tr>';
					$('#formDetailTable', parent.document).append(html);
					parent.skuAction.evenHighlight();
					
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}else{
					//layer.msg(data.msg, 1, 0);//按钮一的回调函数
					alert(data.msg);
				}
		}, "json");
}

//for suiteNew
function addSuiteProductToHTMLTEMP() {
	var detailForm = $('#suiteProductDetailForm');
	var skuMetadataId = detailForm.find('[name=skuMetadataId]').val();
	var seqNo = detailForm.find('[name=seqNo]').val();
	var skuCode = detailForm.find('[name=skuCode]').val();
	var html = '<tr>'
		    +'<td class="tn_c">'
		    +'<form class="suiteProductForm">'
		    +'<input type="hidden" name="suiteProductId" value="" />'
		    +'<input type="hidden" name="suiteMetadataId" value="" />'
			+'<input type="hidden" name="skuMetadataId" value="'+skuMetadataId+'" />'
		    +'<input type="hidden" name="seqNo" value="'+seqNo+'" />'
		    +'<input type="hidden" data-sku="skuCode" value="'+skuCode+'" />'
		    +'<span class="edit" onclick="skuAction.edit(this)" ></span>'
		    +'<span class="del"  onclick="skuAction.delet(this)" ></span>'
		    +'</form>'
		+'</td>'
		+'<td class="last">'+skuCode+'</td>'
		+'</tr>';
	$('#formDetailTable', parent.document).append(html);
	parent.skuAction.evenHighlight();
	parent.layer.close(index);
}

</script>
</body>
</html>

