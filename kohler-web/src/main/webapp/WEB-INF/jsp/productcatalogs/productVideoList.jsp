<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="big" border="0" cellspacing="0" cellpadding="0">
	<tr class="head">
		<th class="head_l">Videos</th>
		<th class="head_r"><a href="javascript:void(0);"
			class="video_new"
			onclick="onlyProductAlertIframe('Video','../productVideo/unlimited/newVideoPage.action?productMetadataId=${productMetadataId}','770px','550px');">Create</a>
		</th>
	</tr>
	<tr class="inside_border">
		<td colspan="2">
			<table class="small" border="0" cellspacing="0" cellpadding="0">
				<tr class="heading">
					<th style="width: 15%">Action</th>
					<th style="width: 25%">Name_CN</th>
					<th style="width: 60%">VideoURL</th>
				</tr>
				<c:forEach items="${videoMaplist}" var="videolist" varStatus="status">
					<c:if test="${status.index%2 != 0 }">
						<tr class="gray">
					</c:if>
					<c:if test="${status.index%2 == 0 }">
						<tr>
					</c:if>
					<td class="tn_c">
						<span class="edit video_new" onclick="onlyProductAlertIframe('Video','../productVideo/unlimited/editVideoPage.action?productVideoMetadataId=${videolist.productVideoMetadataId}&productMetadataId=${productMetadataId}','770px','550px');"></span>
						<span class="del" onclick="deleteProductVideo('${videolist.productVideoMetadataId}');"></span></td>
					<td>${videolist.productVideoName}</td>
					<td class="last">${videolist.imageUrl}</td>
					</tr> 
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function deleteProductVideo(id){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		$.post("../productVideo/unlimited/deleteProductVideo.action", "productVideoMetadataId="+id,function(data) {
			var result = eval(data);
			alert(result.msg);
			$.get('<%=request.getContextPath()%>/productVideo/unlimited/productVideoList.action?productMetadataId=${productMetadataId}',function(data){
				$("#productVideoList").html(data);
			});
		}, "json");
	}
</script>