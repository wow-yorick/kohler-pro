<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="big" border="0" cellspacing="0" cellpadding="0">
	<tr class="head">
		<th class="head_l">CADs</th>
		<th class="head_r"><a href="javascript:void(0);" class="cads_new"
			onclick="onlyProductAlertIframe('CAD','../productCAD/unlimited/newCADPage.action?productMetadataId=${productMetadataId}','770px','550px');">Create</a>
		</th>
	</tr>
	<tr class="inside_border">
		<td colspan="2">
			<table class="small" border="0" cellspacing="0" cellpadding="0">
				<tr class="heading">
					<th style="width: 15%">Action</th>
					<th style="width: 20%">Name_CN</th>
					<th style="width: 15%">Suffix_CN</th>
					<th style="width: 50%">ImageURL</th>
				</tr>
				<c:forEach items="${cadMaplist}" var="cadlist" varStatus="status">
					<c:if test="${status.index%2 != 0 }">
						<tr class="gray">
					</c:if>
					<c:if test="${status.index%2 == 0 }">
						<tr>
					</c:if>
					<td class="tn_c">
						<span class="edit cads_new" onclick="onlyProductAlertIframe('CAD','../productCAD/unlimited/editCADPage.action?productCADMetadataId=${cadlist.productCADMetadataId}&productMetadataId=${productMetadataId}','770px','550px');"></span>
						<span class="del" onclick="deleteProductCAD('${cadlist.productCADMetadataId}');"></span>
					</td>
					<td>${cadlist.productCADName}</td>
					<td>${cadlist.suffix}</td>
					<td class="last">${cadlist.imageUrl}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function deleteProductCAD(id){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		$.post("../productCAD/unlimited/deleteProductCAD.action", "productCADMetadataId="+id,function(data) {
			var result = eval(data);
			alert(result.msg);
			//location.reload();
			$.get('<%=request.getContextPath()%>/productCAD/unlimited/productCADList.action?productMetadataId=${productMetadataId}',function(data){
				$("#productCADList").html(data);
			});
		}, "json");
	}
</script>