<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:forEach items="${bannerUnitList}" var="bannerUnit">
	<tr class="gray" id="${bannerUnit.categoryBannerUnitId }">
		<td class="tn_c">
		<span class="edit partsImg_new" 
		onclick="editbannerUnitPage('${bannerUnit.categoryBannerUnitId}')"></span><span
			class="del" onclick="delbannerUnit(this,'${bannerUnit.categoryBannerUnitId}')"></span></td>
		<td>${bannerUnit.bannerUnitFiledValue}</td>
		<td class="last">第${bannerUnit.bannerUnitRow}行第+${bannerUnit.bannerUnitColumn}列</td>
	</tr>
</c:forEach>
	