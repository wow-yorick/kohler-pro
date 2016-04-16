<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<tr class="head">
		<th class="head_l">SKU</th>
		<th class="head_r"><a href="javascript:void(0);" class="sku_new"
			onclick="alertSecondIframe('SKU','../sku/unlimited/newSKUPage.action?productMetadataId=${productMetadataId}&categoryMetadataId=${categoryMetadataId}');">Create</a>
		</th>
	</tr>
	<tr class="inside_border">
		<td colspan="2">
			<table class="small" border="0" cellspacing="0" cellpadding="0">
				<tr class="heading">
					<th>Action</th>
					<th>Purchase Property</th>
					<th>SKU Code</th>
					<th>Default</th>
				</tr>
				<c:forEach items="${skuMaplist}" var="skulist" varStatus="status">
					<c:if test="${status.index%2 != 0 }">
						<tr class="gray">
					</c:if>
					<c:if test="${status.index%2 == 0 }">
						<tr>
					</c:if>
					<td class="tn_c"><span class="edit sku_new"
						onclick="alertSecondIframe('SKU','../sku/unlimited/editSKUPage.action?skuMetadataId=${skulist.skuMetadataId}&productMetadataId=${productMetadataId}&categoryMetadataId=${categoryMetadataId}');"></span>
						<span class="del" onclick="deleteSKU('${skulist.skuMetadataId}');"></span>
					<td><c:forEach items="${skulist.skuAttrValuesList}" var="skuattr">
											${skuattr.attrValue};
						</c:forEach>
					</td>
					<td>${skulist.skuCode}</td>
					<td class="last">
						<c:if test="${skulist.isDefault eq true}">
											V
						</c:if>
					</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
<script type="text/javascript">
	function deleteSKU(id){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		$.post("../sku/unlimited/deleteSKU.action", "skuMetadataId="+id,function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/sku/unlimited/SKUList.action?productMetadataId=${productMetadataId}&categoryMetadataId=${categoryMetadataId}',function(data){
				$("#SKUList").html(data);
			});
		}, "json");
	}
</script>