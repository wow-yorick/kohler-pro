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
    <form id="detailForm" action="">
    	<input type="hidden" name="suiteProductId" value="${suiteProduct.suiteProductId}" />
    	<input type="hidden" name="suiteMetadataId" value="${suiteProduct.suiteMetadataId}" />
    	<input type="hidden" name="oldSkuMetadataId" value="${suiteProduct.skuMetadataId}" />
    	<div class="search">
        	<div class="row">
            	<div class="col-md-3">Id</div>
            	<div class="col-md-4">${suiteProduct.suiteProductId}</div> 
            </div>  
            <div class="row">
                <div class="col-md-3 ">Product & SKU</div>
                <div class="col-md-4 ">
                <input type="text" name="skuMetadataId" value="${suiteProduct.skuMetadataId}" />
                <input type="text" name="skuCode" value="${suiteProduct.skuCode}" /></div> 
                <div class="col-md-2">
                    <a href="javascript:void(0);" class="choose tn_c">Choose</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 tn_l">SeqNo</div>
                <div class="col-md-6"> 
                    <input type="text" name="seqNo" value="${suiteProduct.seqNo}" />
                </div> 
            </div>          	
        </div>
         <div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-3">${suiteProduct.createUser}</div>
					<div class="col-md-7">
						Creation Date
						<fmt:formatDate value="${suiteProduct.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>

				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-3">${suiteProduct.modifyUser}</div>
					<div class="col-md-7">
						Modification Date
						<fmt:formatDate value="${suiteProduct.modifyTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
				</div>
			</div>
			</form>
        <div class="btns">
        	<a href="javascript:void(0);" class="confirm" onclick="chooseSaveAction()">Save</a>
        	<a href="javascript:void(0);" class="cancel" id="closebtn">Cancel</a>
        </div>
    </div>
    <!--main结束-->
</div>

<script  type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
$('#closebtn').on('click', function(){
  	parent.layer.close(index); //执行关闭
});

//弹出一个iframe层
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

function chooseSaveAction() {
	var formObj = $('#detailForm');
	var suiteProId = formObj.find('[name=suiteProductId]').val();
	if(null == suiteProId || '' == suiteProId) {
		saveSuiteProductTEMP();
	}else {
		savaSuiteProductDB();
	}
	
}

//修改数据库中 提交保存套间产品
function savaSuiteProductDB() {
	var detailForm = $('#detailForm');
	$.post("unlimited/editSave.action", detailForm.serialize(), 
			function(data) {
				if(!data.success) {
					alert(data.msg);
					//layer.msg(data.msg, 1, 0);//按钮一的回调函数	
					return false;
				} else {
					var suiteProductId = detailForm.find('[name=suiteProductId]').val();
					var skuMetadataId = detailForm.find('[name=skuMetadataId]').val();
					var suiteMetadataId = detailForm.find('[name=suiteMetadataId]').val();
					var seqNo = detailForm.find('[name=seqNo]').val();
					var skuCode = detailForm.find('[name=skuCode]').val();
					
					var parentOldInfoObj = $('#formDetailTable', parent.document).find('[name=suiteProductId][value='+suiteProductId+']').parent('.suiteProductForm');

					parentOldInfoObj.find('[name=suiteProductId]').val(suiteProductId);
					parentOldInfoObj.find('[name=suiteMetadataId]').val(suiteMetadataId);
					parentOldInfoObj.find('[name=skuMetadataId]').val(skuMetadataId);
					parentOldInfoObj.find('[name=seqNo]').val(seqNo);
					
					parentOldInfoObj.find('[data-sku=skuCode]').val(skuCode);
					parentOldInfoObj.parent().parent('tr').find('.last').text(skuCode);
					
					parent.skuAction.evenHighlight();
					var index = parent.layer.getFrameIndex(window.name);
					parent.location.reload();
					parent.layer.close(index);
				}
		}, "json");
	
}

//未入数据库的修改
function saveSuiteProductTEMP() {
	
	var detailForm = $('#detailForm');
	var OldKey = detailForm.find('[name=oldSkuMetadataId]').val();
	var skuMetadataId = detailForm.find('[name=skuMetadataId]').val();
	var seqNo = detailForm.find('[name=seqNo]').val();
	var skuCode = detailForm.find('[name=skuCode]').val();
	var parentOldInfoObj = $('#formDetailTable', parent.document).find('[name=skuMetadataId][value='+OldKey+']').parent('.suiteProductForm');

	parentOldInfoObj.find('[name=skuMetadataId]').val(skuMetadataId);
	parentOldInfoObj.find('[name=seqNo]').val(seqNo);
	parentOldInfoObj.find('[data-sku=skuCode]').val(skuCode);
	parentOldInfoObj.parent().parent('tr').find('.last').text(skuCode);
	parent.skuAction.evenHighlight();
	parent.layer.close(index);
}
</script>
</body>
</html>

