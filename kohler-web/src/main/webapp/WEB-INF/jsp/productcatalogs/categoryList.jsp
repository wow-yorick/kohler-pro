<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catalog</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css"
	href="${base}/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${base}/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body class="index">
	<div class="wrap" style='border:1px solid;'>
		<c:import url="/common/header.action"></c:import>

		<div class="content">
			<!-- 内容区域content -->
			<div class="bar">
				<!-- 页面的主要内容content的左边的菜单bar -->
				<ul id="tree" class="ztree"></ul>

			</div>
			
			<div class="content_main">
				<!-- 内容区域content的主要区域content_main -->
				<div class="query row">
					<!-- 内容区域content的主要区域content_main的操作及筛选区域query-->
					<div class="opera_con col-md-12 btnr">
						<c:choose>
	      						 <c:when test="${check == 0}">
	          						<a class="btn" href="javascript:void(0);" onclick="previewCatalog();">Preview</a>
	          						<a class="btn" href="javascript:void(0);" onclick="deleteCatalog();">Delete</a> 
	          						<a class="btn long" href="javascript:void(0);" onclick="initAddCatalogPage();">Create Catalog</a>
	       						</c:when>
	       						<%-- 
	       						<c:when test="${check == 1}">
	            					
	            					<a class="btn" href="javascript:void(0);" onclick="deleteCategory();">Delete</a> <a
									class="btn" href="javascript:void(0);" onclick="initViewSectionPage(-1,-1,'Preview');">Preview</a>
	       						</c:when> --%>
						       <c:otherwise>
									<a class="btn" href="javascript:void(0);" onclick="initPreviewPage();">Preview</a>
									
									<c:choose>
										<c:when test="${check eq 1 }">
											<a class="btn delete" href="javascript:void(0);" onclick="deleteProduct();">Delete</a> 
						       			</c:when>
										<c:otherwise>
											<a class="btn delete" href="javascript:void(0);" onclick="deleteCategory();">Delete</a> 	
										</c:otherwise>
									</c:choose>
						       		<a class="btn long" href="javascript:void(0);" onclick="initAddProductPage();">Create product</a>
									<a class="btn create" href="javascript:void(0);" onclick="initAddCategoryPage();">Create Category</a>
						       </c:otherwise>
						</c:choose>
						 
							 
					</div>
				</div>
				<div class="table">
					<!-- 内容区域content的主要区域content_main的表格table-->
					<table cellspacing="0">
						<thead>
							<tr>
								<c:choose>
									<c:when test="${check eq 1 }">
										<th style="width:13%;">Action</th>
										<th>Id</th>
										<th>Collection</th>
										<th>Product Name_CN</th>
										<th  class="no_rightborder"  style="width:13%;">Product Code</th>
									</c:when>
									<c:otherwise>
										<th style="width:13%;">Action</th>
										<th>Name_CN</th>
										<th  class="no_rightborder"  style="width:13%;">SeqNo</th>
									</c:otherwise>
								</c:choose>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<c:choose>
											<c:when test="${check eq 1 }">
												<td colspan ="5">
													Sorry,these is no data for this action!
												</td>
											</c:when>
											<c:otherwise>
												<td colspan ="3">
													Sorry,these is no data for this action!
												</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:when>
								<c:when test="${check eq 1 }">
									<c:forEach items="${pager.datas}" var="entity">
										<tr>
											<td>
												<div class="checkbox">
													<a href="#" class="ope_icon choose"></a> <input
														type="checkbox" name="dataIds" value="${entity.id}">
												</div>
											
												<a href="javascript:void(0);" onclick="editProduct('${entity.id}','0');" class="ope_icon edit"></a> 
												<a href="javascript:void(0);" onclick="editProduct('${entity.id}','1');"  class="ope_icon detail"></a>
											</td>
											<td class="fl_left">${entity.name}</td>
											<td class="fl_left">${entity.collectionName}</td>
											<td class="fl_left">${entity.collectionName}</td>
											<td class="no_rightborder fl_left">${entity.productCode}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${pager.datas}" var="entity">
										<tr>
											<td>
												<div class="checkbox">
													<a href="#" class="ope_icon choose"></a> <input
														type="checkbox" name="dataIds" value="${entity.id}">
												</div>
												<c:choose>
													<c:when test="${check eq 0 }">
														<a href="javascript:void(0);" onclick="editCatalog('${entity.id}','0');" class="ope_icon edit"></a> 
														<a href="javascript:void(0);" onclick="editCatalog('${entity.id}','1');"  class="ope_icon detail"></a>
													</c:when>
													<%-- 
													<c:when test="${check eq 1 }">
														<a href="javascript:void(0);" onclick="editProduct('${entity.id}');" class="ope_icon edit"></a> 
													</c:when> --%>
													<c:otherwise>
														<a href="javascript:void(0);" onclick="editCategory('${entity.id}','0');" class="ope_icon edit"></a> 
														<a href="javascript:void(0);" onclick="editCategory('${entity.id}','1');"  class="ope_icon detail"></a>
													</c:otherwise>
												</c:choose> 
												
												
											</td>
											<td class="fl_left">${entity.name}</td>
											<td class="no_rightborder fl_left">${entity.seqNo}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
			</div>
		</div>


		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">
//计算左侧导航高度
	var win_h = $(document).height();
	
    var wap_h = $('.wrap').height();
    if(wap_h < win_h)
    {
    	$('.wrap').css('height',win_h);
    	
    }
    var wap_h = $('.wrap').height();
    var header_h = $('.head').height();
	var content_main_h = $('.content_main').height();
	if((wap_h-header_h) >= content_main_h)
	{
		$('.bar').css('height',(wap_h-header_h));
		
	}
	else{
		$('.bar').css('height',content_main_h);
		
	}

	
	
	
	$(document).ready(function() {
		var setting = {
			view : {
				showLine : false
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};

		var zNodes = ${categoryTree};
		
		
		
		$.fn.zTree.init($("#tree"), setting, zNodes);
		//选中默认节点
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var categoryId = ${categoryId};
		var node = treeObj.getNodeByParam("id", categoryId, null);
		treeObj.selectNode(node);
		//展开默认节点
		var node=treeObj.getNodeByParam("id",categoryId,null);
		treeObj.expandNode(node,true,true,true);
	});
	
	
	function initAddCatalogPage(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var categoryId = ${categoryId};
		var node = treeObj.getNodeByParam("id", categoryId, null);
		var id = node.id;
		//var name = node.name;
		alertFirstIframe("Catalog","createCatalog.action?categoryMetadataId="+id,'880px','650px');
	}
	
	function initAddCategoryPage(){
		if(${check == 2} || ${fn:length(pager.datas) == 0 }){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var categoryId = ${categoryId};
			var node = treeObj.getNodeByParam("id", categoryId, null);
			var id = node.id;
			var url = '${base}'+"/category/createCategory.action?categoryMetadataId="+id;
			
			//console.info("获取的url="+url);
			//var name = node.name;
			alertFirstIframe("Category",url,'920px','660px');
		}else{
			alert("you can't create category");	
		}
		
	}
	
	function initAddProductPage(){
		if(${check == 1} || ${fn:length(pager.datas) == 0 }){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var categoryId = ${categoryId};
			var node = treeObj.getNodeByParam("id", categoryId, null);
			var id = node.id;
			var url = '${base}'+"/product/createProduct.action?categoryMetadataId="+id;
			
			//console.info("获取的url="+url);
			//var name = node.name;
			alertFirstIframe("Product",url,'880px','650px');
		}else{
			alert("<spring:message code="info.product.category.002" arguments="" argumentSeparator=","/>");
		}
		
	}
	function editProduct(productId,isEdit){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var categoryId = ${categoryId};
		var node = treeObj.getNodeByParam("id", categoryId, null);
		var id = node.id;
		var url = '${base}'+"/product/editProduct.action?categoryMetadataId="+id+"&productMetadataId="+productId+"&isEdit="+isEdit;
		
		//console.info("获取的url="+url);
		//var name = node.name;
		
		alertFirstIframe("Product",url,'880px','650px');
	}
	
	function previewProduct(productId){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var categoryId = ${categoryId};
		var node = treeObj.getNodeByParam("id", categoryId, null);
		var id = node.id;
		var url = '${base}'+"/product/editProduct.action?categoryMetadataId="+id+"&productMetadataId="+productId+"&isEdit=1";
		
		alertFirstIframe("Product",url,'880px','650px');
	}
	
	function editCatalog(categoryId,isEdit){
		alertFirstIframe("Catalog","editCatalog.action?categoryMetadataId="+categoryId+"&isEdit="+isEdit,'880px','650px');
	}
	
	function editCategory(categoryId,isEdit){
		var url = '${base}'+"/category/editCategory.action?categoryMetadataId="+categoryId+"&isEdit="+isEdit;
		alertFirstIframe("Category",url,'880px','650px');
	}
	
	function deleteCatalog(){
		
		var checkboxs = $("input[name='dataIds']:checked");
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		
		if(!confirm("<spring:message code="info.product.category.001" arguments="" argumentSeparator=","/>")){
			return false;
		}

		var collectionMetadataIds = new Array();
		
		$.each(checkboxs, function(i,item){      
			collectionMetadataIds.push( $(item).val()); 
		  }); 
		//console.info(ids);
		
		
		$.post("deleteCatalog.action","categoryMetadataIds="+collectionMetadataIds, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");	
		
}
	
	function previewCatalog(){
		
		var checkboxs = $("input[name='dataIds']:checked");
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(len != 1){
			alert("<spring:message code="info.product.category.006" arguments="" argumentSeparator=","/>");
			return false;
		}

		//var collectionMetadataIds = new Array();
		
		//$.each(checkboxs, function(i,item){      
		//	collectionMetadataIds.push( $(item).val()); 
		//  }); 
		//console.info(ids);
		var categoryMetadataId = checkboxs.val();
		
		//window.open("",'_blank');
		
		window.open('${base}'+"/catalog/unlimited/previewTransfer.action?categoryMetadataId="+categoryMetadataId+"&type=catalog","catalogPreview");
		/*$.post("previewCatalog.action","categoryMetadataId="+categoryMetadataId+"&type=catalog", function(data){
			var result = eval(data);
			//window.open(result.publishUrl,'_blank');
		  },"json"); */
		
}
	
	function initPreviewPage(){
		
		var checkboxs = $("input[name='dataIds']:checked");
		
		var type = "";
		var previewTitle = "";
		if(${check == 1}){
			type = "product";
			previewTitle = "product";
		}else if(${check == 2}){
			type = "category";
			previewTitle = "category";
		}
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(len != 1){
			alert("<spring:message code="info.product.category.006" arguments="" argumentSeparator=","/>");
			return false;
		}

		//var collectionMetadataIds = new Array();
		
		//$.each(checkboxs, function(i,item){      
		//	collectionMetadataIds.push( $(item).val()); 
		//  }); 
		//console.info(ids);
		var categoryMetadataId = checkboxs.val();
		
		window.open('${base}'+"/catalog/unlimited/previewTransfer.action?categoryMetadataId="+categoryMetadataId+"&type="+type,previewTitle);
		
		/*$.post("previewCatalog.action","categoryMetadataId="+categoryMetadataId+"&type="+type, function(data){
			var result = eval(data);
			if(result.publishUrl !=""){	
				
			
				
				window.open(result.publishUrl,'_blank');
			}else{
				alert("error");
			}
		 },"json"); */
		
}
	
	function deleteCategory(){
		
		var checkboxs = $("input[name='dataIds']:checked");
		
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		
		if(!confirm("<spring:message code="info.product.category.001" arguments="" argumentSeparator=","/>")){
			return false;
		}

		var collectionMetadataIds = new Array();
		
		$.each(checkboxs, function(i,item){      
			collectionMetadataIds.push( $(item).val()); 
		  }); 
		//console.info(ids);
		
		
		$.post("deleteCatalog.action","categoryMetadataIds="+collectionMetadataIds, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");	
		
}
	
	function deleteProduct(){
			
			var checkboxs = $("input[name='dataIds']:checked");
			
			
			var len = checkboxs.length;
			if( len == 0){
				alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
				return false;
			}
			
			if(!confirm("<spring:message code="info.product.category.001" arguments="" argumentSeparator=","/>")){
				return false;
			}
	
			var collectionMetadataIds = new Array();
			
			$.each(checkboxs, function(i,item){      
				collectionMetadataIds.push( $(item).val()); 
			  }); 
			//console.info(ids);
			$.post('${base}'+"/product/unlimited/deleteProduct.action","productMetadataIds="+collectionMetadataIds, function(data){
				var result = eval(data);
				alert(result.msg);
				location.reload();
			  },"json");	
			
	}

	
	
</script>
</html>
