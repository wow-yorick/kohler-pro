<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="big marginTop" border="0" cellspacing="0" cellpadding="0">
	<tr class="head">
		<th class="head_l">Parts File</th>
		<th class="head_r"><a href="javascript:void(0);"
			class="partsImg_new"
			onclick="onlyProductAlertIframe('Parts File','../productPart/unlimited/newPartPage.action?productMetadataId=${productMetadataId}','770px','550px');">Create</a>
		</th>
	</tr>
	<tr class="inside_border">
		<td colspan="2">
			<table class="small" border="0" cellspacing="0" cellpadding="0">
				<tr class="heading">
					<th style="width: 15%">Action</th>
					<th style="width: 25%">Name_CN</th>
					<th style="width: 60%">ImageURL</th>
				</tr>
				<c:forEach items="${partMaplist}" var="partlist" varStatus="status">
					<c:if test="${status.index%2 != 0 }">
						<tr class="gray">
					</c:if>
					<c:if test="${status.index%2 == 0 }">
						<tr>
					</c:if>
					<td class="tn_c">
						<span class="edit partsImg_new" onclick="onlyProductAlertIframe('Parts File','../productPart/unlimited/editPartPage.action?productPartMetadataId=${partlist.productPartMetadataId}&productMetadataId=${productMetadataId}','770px','550px');"></span>
						<span class="del" onclick="deleteProductPart('${partlist.productPartMetadataId}');"></span>
					</td>
					<td>${partlist.productPartName}</td>
					<td class="last">${partlist.imageUrl}</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function deleteProductPart(id){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		$.post("../productPart/unlimited/deleteProductPart.action", "productPartMetadataId="+id,function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productPart/unlimited/productPartList.action?productMetadataId=${productMetadataId}',function(data){
				$("#productPartList").html(data);
			});
		}, "json");
	}
</script>