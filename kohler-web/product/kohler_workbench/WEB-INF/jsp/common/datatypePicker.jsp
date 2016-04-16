<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>datatypePicker_layerbox</title>
<link rel="stylesheet" type="text/css" href="../css/global.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/layer/jquery-1.11.1.min.js"></script>
<script src="../js/layer/layer.min.js"></script>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">

	var a;

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
		$('.confirm').on('click', function() {
			layer.closeAll();
		});
		
		$('.searchConfirm').on('click', function() {
			$('#selectList').html("");
			
			var sel = "";
			var count = 1;
			$("[id=actionSearch]").each(function(){
				var search = $(this).val();
				if(search.trim() != "" && search.trim().length > 0){
					count ++;
				}
				sel += $(this).attr('name') +"&"+search + "%";
				sel = sel.substring(0,sel.length - 1);
			}); 
			
				if(count == 0){
					alert("Please input the query information!");
					return false;
				}
				
				sel = "image_overlay_txt&数据";
				
				var strParams = "{selstr:"+sel+",dataTypeId:${dataTypeId}}";
				$.post("searchDatatype.action",{'selStr':sel,"dataTypeId":${dataTypeId}},function(data){
					var html = "";
					
					a = data;
					
					$.each(data,function(){
						var datas = $(this);
						
						$.each(datas,function(key,value){
							$.each(value,function(k,v){
								if(k == "keyId"){
									html  += "<tr><td class='tn_c'><span class='plus' onclick=\"add('"+v+"','"+value+"');\"><input type='hidden' idstr='"+v+"' /></span></td></td>";
								}
								html += "<td>"+v+"</td>";
							});
						});
				       
					});
					 html += "</tr>";
				     $('#selectList').append(html);
				});
				
				
			});
	});
	
	var myArray=new Array();
	
	function searchSKU() {
		var chooses = $("#productName").val();
		var proName = $("#productId").val();
		$("#categoryName").val($("#productId").val());
		if ((chooses == null || chooses == "")
				&& (proName == null || proName == "-- 请选择 --")) {
			alert("Please input the query information!");
			return false;
		} else {
			$("#skuPicker").submit();
		}
	}
	
	function add(idstr,map) {
		var i = 0;
		$("[id=idValue]").each(function(){
			var v = $(this).attr('idstr');
			if(v == idstr){
				alert("Please do not repeat choose!");
				i++;
			}
		}); 
		
		
		if(i!=0){
			return false;
		}
		
        var html = "";
       
        $.each(a,function(){
			var datas = $(this);
			
			$.each(datas,function(key,value){
				
				$.each(value,function(k,v){
					
					if(v == idstr){
						var count = 1;
						$.each(value,function(k,v){
							if(k == "keyId"){
								html  += "<tr><td class='tn_c'><span class='minus' onclick=\"deleteSkuSelt(this);\"><input type='hidden' id='idValue' idstr='"+v+"' /></span></td></td>";
							}
							if(count == 2){
								html += "<td>"+v+"<input type='hidden' id='nameValue' namestr='"+v+"'></td>";
							}else{
								html += "<td>"+v+"</td>";
							}
							count = count + 1;
						});
					}
				});
				});
		});
	       
        html += "</tr>";
               
		$('#hasSelectList').append(html);
		//var attr = $(thisObj).parent().parent().find('.sku_attr').text();
	}

	function deleteSkuSelt(seltSkuObj) {
		var seltSkuObj = $(seltSkuObj).parent().parent('tr');

		var keyId = seltSkuObj.find('.selectSkuIds').attr('value');
		seltSkuObj.remove();
		$('[idstr=' + keyId + ']').parent().removeAttr('disabled');
	}
	
	function okProduct() {

		var str = "";
		
		
		var forms = $("#hasSelectList");
		$("[id=idValue]").each(function(){ 
			str += $(this).attr('idstr') +",";
		}); 
		
		if(str != "" && str.length > 0){
			str = str.substring(0,str.length-1);
		}
		
		if('${isMulti}'==1&&str.indexOf(',')>0){
			alert('只能选择一项');
			return;
		}else{
			if('${maxSelect}'!=''&&'${maxSelect}'!=-1&&str.indexOf(',')>0){
				//选择的项
				var ss = str.split(',');
				if(ss.length>'${maxSelect}'){
					alert('最多选择${maxSelect}项');
					return;
				}
				
			}
		}
		
		var nameStr = "";
		$("[id=nameValue]").each(function(){ 
			nameStr += ''+$(this).attr('namestr')+''+",";
		}); 
		if(nameStr != "" && nameStr.length > 0){
			nameStr = nameStr.substring(0,nameStr.length-1);
		}
		parent.document.getElementById('${elementId}').value = str;
		parent.document.getElementById('${elementName}').value = nameStr;
		//parent.document.addPageForm.datatype${dataTypeId}.value = str;
		//parent.document.addPageForm.datatypeText${dataTypeId}.value = str;
		
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);

	}

</script>
</head>

<body>
	<div class="container queryConditions product_layer skuPicker">

		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<div class="search">
				<h1>DataType Picker</h1>
				<div class="row query">
					<form id="skuPicker" method="post">
						<input type="hidden" name="elementId" value="${elementId }">
						<input type="hidden" name="elementName" value="${elementName }">
						<input type="hidden" name="isMulti" value="${isMulti }">
						<input type="hidden" name="maxSelect" value="${maxSelect }">
						<div class="col-md-9">
							<pageTag:datatype-search datatypeId="${dataTypeId}" />
						</div>
					</form>
					<div class="opera_con col-md-3 opera_fr">
						<a class="btn searchConfirm" href="#">Search</a> <a class="btn"
							href="javascript:void(0);" onclick="okProduct();">ok</a>
					</div>
				</div>
			</div>
			<table class="big topSmall" border="0" cellspacing="0"
				cellpadding="0">
				<tr class="head">
					<th class="head_l">DataType List</th>
					<th class="head_r"><a href="#" class="sku_new"></a></th>
				</tr>
				<tr class="inside_border">
					<td colspan="2">
				
				<table class="small" border="0" cellspacing="0" cellpadding="0">
					<c:forEach items="${m}" var="map" varStatus="status">
						<c:if test="${status.first==true}">
							<tr class="heading">
								<th style="width: 10%">Action</th>
								<c:forEach items="${map}" var="entry">
									<th style="width: 10%">${entry.key}</th>
								</c:forEach>
							</tr>
						</c:if>
					</c:forEach>
					
					<tbody id="selectList">
					</tbody>
					
				</table>
				</td>
				</tr>
				
				
			</table>
			<form action="" class="skupicker" id="skupicker">
				<table class="big topSmall" border="0" cellspacing="0"
					cellpadding="0">
					<tr class="head">
						<th class="head_l">Picked</th>
						<th class="head_r"><a href="#" class="sku_new"></a></th>
					</tr>
					<tr class="inside_border">
						<td colspan="2">
							<table class="small" border="0" cellspacing="0" cellpadding="0">
								<c:forEach items="${m}" var="map" varStatus="status">
									<c:if test="${status.first==true}">
										<tr class="heading">
											<th style="width: 10%">Action</th>
											<c:forEach items="${map}" var="entry">
												<th style="width: 10%">${entry.key}</th>
											</c:forEach>
										</tr>
									</c:if>
								</c:forEach>

								<tbody id="hasSelectList">
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!--main结束-->
		<script type="text/javascript">
			var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			$('#closebtn').on('click', function() {
				parent.layer.close(index); //执行关闭
			});
		</script>

	</div>
</body>
</html>
