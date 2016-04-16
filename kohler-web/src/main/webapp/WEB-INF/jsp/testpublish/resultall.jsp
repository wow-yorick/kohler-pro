<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>publish test</title>
	<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />

</head>
<body class="query_condition collections">
<table>
<caption>${type}</caption>
	<tr>
		<td>预览</td>
		<td><a href="${url}" target="_blank" >预览</a></td>
	</tr>
	<tr>
		<td>结果</td>
		<td>${success}</td>
	</tr>
	<tr>
		<td>信息</td>
		<td>${msg}</td>
	</tr>
	<tr>
		<td>日志</td>
		<td>${log}</td>
	</tr>
</table>
	
<table>
	
</body>
</html>