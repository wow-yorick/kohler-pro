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
<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行</td>
		<td><a href="result.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行page_pc_cn</td>
		<td><a href="result_page_pc_cn.action">运行</a></td>
	</tr>
</table>
	
<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行page_pc_en</td>
		<td><a href="result_page_pc_en.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行category_pc_cn</td>
		<td><a href="result_category_pc_cn.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行category_pc_en</td>
		<td><a href="result_category_pc_en.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行newarrival_pc_cn</td>
		<td><a href="result_newarrival_pc_cn.action">运行</a></td>
	</tr>
</table>
	
<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行newarrival_pc_en</td>
		<td><a href="result_newarrival_pc_en.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行suitedetail_pc_cn</td>
		<td><a href="result_suitedetail_pc_cn.action">运行</a></td>
	</tr>
</table>

<table width="100%" style="font-size:36px;">
	<tr>
		<td>运行suitedetail_pc_en</td>
		<td><a href="result_suitedetail_pc_en.action">运行</a></td>
	</tr>
</table>

</body>
</html>