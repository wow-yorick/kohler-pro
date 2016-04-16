<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<input type="text"  id="bannerUnitName${categoryMetadataId }${langCode }" 	
														 value="${bannerUnitValue }" class="disable" readonly="readonly" />
<input type="hidden" name="bannerUnitMetadataIds" id="bannerUnit${categoryMetadataId }${langCode }" value="${bannerUnitValueId }" />