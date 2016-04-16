<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="big" border="0" cellspacing="0" cellpadding="0">
	<tr class="head">
		<th class="head_l">PDFs</th>
		<th class="head_r"><a href="javascript:void(0);" class="pdf_new"
			onclick="onlyProductAlertIframe('PDF','../productPDF/unlimited/newPDFPage.action?productMetadataId=${productMetadataId}','770px','550px');">Create</a>
		</th>
	</tr>
	<tr class="inside_border">
		<td colspan="2">
			<table class="small" border="0" cellspacing="0" cellpadding="0">
				<tr class="heading">
					<th style="width: 15%">Action</th>
					<th style="width: 25%">Type</th>
					<th style="width: 60%">PDF URL</th>
				</tr>
				<c:forEach items="${pdfMaplist}" var="pdflist" varStatus="status">
					<c:if test="${status.index%2 != 0 }">
						<tr class="gray">
					</c:if>
					<c:if test="${status.index%2 == 0 }">
						<tr>
					</c:if>
					<td class="tn_c"><span class="edit pdf_new"
						onclick="onlyProductAlertIframe('PDF','../productPDF/unlimited/editPDFPage.action?productPDFMetadataId=${pdflist.productPDFMetadataId}&productMetadataId=${productMetadataId}','770px','550px');"></span>
						<span class="del" onclick="deleteProductPDF('${pdflist.productPDFMetadataId}');"></span></td>
					<td>${pdflist.pdfType}</td>
					<td class="last">${pdflist.fileUrl}</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function deleteProductPDF(id){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		$.post("../productPDF/unlimited/deleteProductPDF.action", "productPDFMetadataId="+id,function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productPDF/unlimited/productPDFList.action?productMetadataId=${productMetadataId}',function(data){
				$("#productPDFList").html(data);
			});
		}, "json");
	}
</script>