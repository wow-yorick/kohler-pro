<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Collection</title>
<jsp:include page="../common/common.jsp" />
</head>
<body  class="query_condition collections">
	<div class="wrap newproducts">
		<c:import url="/common/header.action"></c:import>
		<div class="content">
			<!-- 内容区域content -->
			<div class="content_main">
				<!-- 内容区域content的主要区域content_main -->
				<div class="condition">
					<!-- 内容区域content的主要区域content_main的操作及筛选区域condition-->
					<h1>Collection</h1>
					<div class="query row">
						<div class="col-md-9">
							<form id="searchForm"
								action="../collection/collectionList.action" method="post">
								<div class="col-md-1">
									<label>Name</label>
								</div>
								<div class="col-md-2">
									<input type="text" name="collectionName"
										value="${collectionName}">
								</div>
							</form>
						</div>
						<div class="opera_con col-md-3 opera_fr">
								<a class="btn" href="javascript:void(0);" onclick="searchCollection()">Search</a>
								<per:user resourceId="product.collections.create">
							 	<a class="btn long" href="#">Create</a> 
							 	</per:user>
							 	<per:user resourceId="product.collections.delete">
							 	<a class="btn delete" href="javascript:void(0);" onclick="deleteCollection()">Delete</a>
							 	</per:user>
						</div>
					</div>
				</div>
				<div class="table">
					<!-- 内容区域content的主要区域content_main的表格table-->
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:8%">Action</th>
								<th style="width:70%">Name_CN</th>
								<th style="width:6%">SeqNo</th>
								<th style="width:6%">Creator</th>
								<th class="no_rightborder" style="width:10%">Creation Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pager.datas}" var="collectionMetadate">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox"  name="collectionMetadataId"  value="${collectionMetadate.collectionMetadataId}">
										</div>
										<per:user resourceId="product.collections.edit">
										<a href="javascript:void(0);"
									onclick="editCollection(${collectionMetadate.collectionMetadataId})"
										class="ope_icon edit"></a> 
										</per:user>
										<per:user resourceId="product.collections.view">
										<a href="javascript:void(0);"
										onclick="viewCollection(${collectionMetadate.collectionMetadataId})"
										class="ope_icon detail"></a>
										</per:user>
										</td>
									<td class="fl_left">${collectionMetadate.collectionName }</td> 
									<td class="fl_left">${collectionMetadate.seqNo }</td>
									<td>${collectionMetadate.createUser}</td>
									<td class="no_rightborder"><fmt:formatDate
											value="${collectionMetadate.createTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="page">
						<span class=""></span> <span></span>
					</div>
				</div>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">

	function searchCollection(){
		$("#searchForm").submit();
	}
	
	$('.long').on('click', function(){
	    $.layer({
	        type: 2,
	        title: 'Collection',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '600px'],
			shadeClose: false,
	        offset : [($(window).height() - 550)/2+'px', ''],
	        iframe: {src: 'create.action'}
	    });
	});
	
	function editCollection(collectionMetadataId){
		$.layer({
	        type: 2,
	        title: 'Collection',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'edit.action?collectionMetadataId='+collectionMetadataId}
	    });
	}
	
	function viewCollection(collectionMetadataId){
		$.layer({
	        type: 2,
	        title: 'Collection',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'view.action?collectionMetadataId='+collectionMetadataId}
	    });
	}
	
	function deleteCollection(){
		var checkboxs = $("input[name='collectionMetadataId']:checked");
		var len = checkboxs.length;
		if( len == 0){
			//alert("请至少选择一条记录！");
			if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
				
			}
			return false;
		}
		if(!confirm("<spring:message code="info.common.confirm.delete" arguments="" argumentSeparator=","/>")){
			return false;
		}
		
		var collectionMetadataIds = "";
		$.each(checkboxs, function(i,item){      
		      if(i == len-1){
		    	  collectionMetadataIds += $(item).val() ;
		      }else{
		    	  collectionMetadataIds += $(item).val() + ",";
		      }
		  }); 
		$.post("delete.action","collectionMetadataIds="+collectionMetadataIds, function(data){
			var result = eval(data);
			alert(result.msg);
			location.reload();
		  },"json");
		
}
</script>
</html>







