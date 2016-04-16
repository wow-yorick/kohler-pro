<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>catalog preview</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css"
	href="${base}/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${base}/js/jquery.ztree.all-3.5.min.js"></script>
</head>

<script type="text/javascript">
$(document).ready(function() {
	var categoryMetadataId = ${categoryMetadataId};
	var type = '${type}';
	
	
	/*$.post('${base}'+"/catalog/previewCatalog.action","categoryMetadataId="+categoryMetadataId+"&type="+type, function(data){
		var result = eval(data);
		//window.open(result.publishUrl,'_blank');
		window.location.href = result.publishUrl;
	  },"json"); */
	
	$.ajax({
		type : "POST",
		async: false,
		url : '${base}'+"/catalog/previewCatalog.action",
		data : "categoryMetadataId="+categoryMetadataId+"&type="+type,
		error : function(request) {
			alert("the request is error");
			window.location.href = "about:bank";
		},
		success : function(data) {
			var result = eval(data);
			if(result.success == true){
				window.location.href = result.publishUrl;	
			}else if(result.success == false){
				alert("the request is error");
			}
		}
	});
	//window.location.href = '${base}'+"/catalog/previewCatalog.action?categoryMetadataId="+categoryMetadataId+"&type="+type;
});
</script>
</html>