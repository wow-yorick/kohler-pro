<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>skuPicker_layerbox</title>
<link rel="stylesheet" type="text/css"  href="../css/global.css">
    <link href="../css/main.css" rel="stylesheet" type="text/css" />
	<script src="../js/layer/jquery-1.11.1.min.js"></script>
	<script src="../js/layer/layer.min.js"></script>
	<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
$(function(){
	$('#product').attr('action',location.href);
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
    $(".checkbox input").click(function(){
        if($(this).parent(".checkbox").children("a").hasClass("active")){
            $(this).parent(".checkbox").children("a").removeClass("active");
            $(this).attr("checked",false);
            if($(this).hasClass("this_dis"))
            $(".new_togg").css("display","none");
        }
        else{
            $(this).parent(".checkbox").children("a").addClass("active");
            $(this).attr("checked",true);
            if($(this).hasClass("this_dis"))
            $(".new_togg").css("display","block");
        }
    });    
});
	function searchProduct(){
		var chooses = $("#fileAssetName").val();
		var proName = $("#productId").val();
		$("#categoryName").val($("#productId").val());
		if(chooses == null || chooses == ""){
			alert("Please input the query information!");
			return false;
		}else{
			$("#product").submit();
		}
	}
	function add(caId,proName,thisObj){
		if($(thisObj).attr('disabled')){
			alert('不要重复选择');
			return false;
		}
		//alert(caId+"@@"+proName+"@@"+caName);
		var html = '<tr>'
        +'<td class="tn_c"><span class="minus" onclick="deleteSkuSelt(this)"></span><input type="hidden" name="fileAssetId" class="selectSkuIds" value="'+caId+'"></td>'
        +'<td>'+proName+'<input type="hidden" class="selectSkuIds" name="fileAssetName" value="'+proName+'"></td>'
    	+'</tr>';
		$('#hasSelectList').append(html);
		$(thisObj).attr('disabled','disabled');
	}
	
	function deleteSkuSelt(seltSkuObj) {
		var seltSkuObj = $(seltSkuObj).parent().parent('tr');
		
		var keyId = seltSkuObj.find('.selectSkuIds').attr('value');
		seltSkuObj.remove();
		$('[idstr='+keyId+']').parent().removeAttr('disabled');
	}
	function okProduct() {
		var forms = $(".propicker");
		var len = forms.length;
		var pickerName = '[';
		$.each(forms, function(i, form) {
			if (i == len - 1) {
				pickerName += JSON.stringify(formToJson(form));
			} else {
				pickerName += JSON.stringify(formToJson(form));
				pickerName += ",";
			}
		});
		pickerName += "]";
		//alert(pickerName);
		
		var str = "";
		$("#hasSelectList input[name=fileAssetId]").each(function(){ 
			str += $(this).val() +",";
		}); 
		if(str != "" && str.length > 0){
			str = str.substring(0,str.length-1);
		}
		if('${isMulti}'==1&&str.indexOf(',')>0){
			alert('只能选择一项');
			return;
		}
		
		var namestr = "";
		
		$("#hasSelectList input[name=fileAssetName]").each(function(){ 
			namestr += $(this).val() +",";
		}); 
		if(namestr != "" && namestr.length > 0){
			namestr = namestr.substring(0,namestr.length-1);
		}
		
		parent.document.getElementById('${elementId}').value = str;
		
		if('${elementName}'!=null&&'${elementName}'!=''){
			parent.document.getElementById('${elementName}').value = namestr;
		}
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		/*$.post(".action",$("#productpicker").serialize(), function(data) {
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload();
			parent.layer.close(index);
		}, "json");*/
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
</head>

<body>
<div class="container queryConditions product_layer skuPicker">

    <!--shadow开始-->
    <div class="shadow">
    </div>
    <!--shadow结束-->
    <!--main开始-->
    <div class="main">
    	<div class="search">
			<h1>File Picker</h1>
			<div class="row query">
				<div class="col-md-9">
					<form action="imagepicker.action" id="product" method="post">
						<div class="col-md-2 tn_c"><label>Name</label></div>
						<div class="col-md-3"><input type="text" value="${file.fileAssetName}" id="fileAssetName" name="fileAssetName"></div>
						<div class="col-md-2 tn_c"><label>Category</label></div>
						<div class="col-md-3">
							<select name="productId" id="productId"" disabled>
								<option selected = "selected" value="">${fileType}</option>
							</select>
						</div>	
						<input type="hidden" name="categoryName" id="categoryName"> 
					</form>					
				</div>
				<div class="opera_con col-md-3 opera_fr">
					<a class="btn" href="javascript:void(0);" onclick="searchProduct();">Search</a>
					<a class="btn" href="javascript:void(0);" onclick="okProduct();" >ok</a>
				</div>
			</div>
        </div>
        <table class="big topSmall" border="0" cellspacing="0" cellpadding="0">
        	<tr class="head">
            	<th class="head_l">File List</th>
            	<th class="head_r"><a href="#" class="sku_new"></a></th>
            </tr>
            <tr class="inside_border">
            	<td colspan="2">
                    <table class="small" border="0" cellspacing="0" cellpadding="0">
                        <tr class="heading">
                            <th style="width:20%">Action</th>
                            <th>Category</th>
                        </tr>
                        <tbody>
                        <c:forEach items="${fileLsit}" var="li">
	                        <tr>
	                            <td class="tn_c"><span class="plus" onclick="add('${li.fileAssetId}','${li.fileAssetName}',this);"><input type="hidden" idstr="${cat.fileAssetId}}"></input></span></td>
	                            <td>${li.fileAssetName}</td>
	                        </tr>
	                    </c:forEach>
	                    </tbody>
                    </table>
                </td>
            </tr>
        </table>
        <form action="" class="propicker" id="productpicker">
	         <table class="big topSmall" border="0" cellspacing="0" cellpadding="0">
	        	<tr class="head">
	            	<th class="head_l">Picked</th>
	            	<th class="head_r"><a href="#" class="sku_new"></a></th>
	            </tr>
	            <tr class="inside_border">
	            	<td colspan="2">
	                    <table class="small" border="0" cellspacing="0" cellpadding="0">
	                        <tr class="heading">
	                            <th style="width:20%">Action</th>
	                            <th>Category</th>
	                        </tr>
	                        <tbody id="hasSelectList">
	                        </tbody>
	                    </table>
	                </td>
	            </tr>
	        </table>
       </form>
    </div>
    <!--main结束-->
    <script  type="text/javascript">
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    </script>
 <script>
//弹出一个iframe层
$('.sku_new').on('click', function(){
    $.layer({
        type: 2,
        title: 'SKU',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: 'sku_layerbox.html'}
    });
});
$('.partsImg_new').on('click', function(){
    $.layer({
        type: 2,
        title: 'Parts & Images',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: 'partsImages_layerbox.html'}
    });
});
$('.cads_new').on('click', function(){
    $.layer({
        type: 2,
        title: 'CAD',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: 'cads_layerbox.html'}
    });
});
$('.video_new').on('click', function(){
    $.layer({
        type: 2,
        title: 'Video',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: 'videos_layerbox.html'}
    });
});
$('.pdf_new').on('click', function(){
    $.layer({
        type: 2,
        title: 'PDF',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['600' , '450px'],
		shadeClose: false,
        offset : [($(window).height() - 550)/2+'px', ''],
        iframe: {src: 'pdfs_layerbox.html'}
    });
});
$('.detail').on('click', function(){
    $.layer({
        type: 2,
        title: 'Catolog',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'catalog_layerbox.html'}
    });
});
$('.long').on('click', function(){
    $.layer({
        type: 2,
        title: 'Product',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'product_layerbox.html'}
    });
});
//删除按钮
$('.del').on('click', function(){
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
            layer.msg('Delete the success', 1, 1);//按钮一的回调函数			
        }
    }
});});
</script>
</div></body>
</html>
