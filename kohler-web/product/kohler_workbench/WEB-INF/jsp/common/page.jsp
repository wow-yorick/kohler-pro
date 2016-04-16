<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- 内容区域content的主要区域content_main的翻页部分table_con-->
	<div class="page">
		<span class="page_cur">${pager.currentPage }</span>/<span
			class="page_all">${pager.totalPage }</span>
		<c:choose>
			<c:when test="${pager.currentPage lt pager.totalPage}">
				<a class="page_down" href="${pager.url}currentPage=${pager.currentPage+1}"></a>
			</c:when>
			<c:otherwise>
				<a class="page_down" ></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pager.currentPage gt 1}">
				<a class="page_up" href="${pager.url}currentPage=${pager.currentPage-1}"></a>
			</c:when>
			<c:otherwise>
				<a class="page_up" ></a>
			</c:otherwise>
		</c:choose>
		
	</div>
