<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Section/Pages</title>
<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
</head>
<body class="index">
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>

		<div class="content">
			<!-- 内容区域content -->
			<div class="bar">
				<!-- 页面的主要内容content的左边的菜单bar -->
				<ul id="tree" class="ztree"></ul>

			</div>
			<div class="content_main">
				<!-- 内容区域content的主要区域content_main -->
				<div class="query row">
					<!-- 内容区域content的主要区域content_main的操作及筛选区域query-->
					<div class="opera_con col-md-4" style="float:right;">
						<a class="btn long" href="javascript:void(0);" onclick="initAddSectionPage();">New Section</a>
						<a class="btn long createPage"	href="#">New Page</a>
						<a class="btn" href="javascript:void(0);" onclick="deleteSectionOrPage();">Delete</a>
						<a class="btn" href="javascript:void(0);" onclick="previewPage();">Preview</a>
					</div>
				</div>
				<div class="table">
					<!-- 内容区域content的主要区域content_main的表格table-->
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width: 13%;">Action</th>
								<th>Type</th>
								<th class="no_rightborder" style="width: 70%;">Name</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<td colspan ="3">Sorry,these is no data for this action!</td>
									</tr>
								</c:when>

								<c:otherwise>
									<c:forEach items="${pager.datas}" var="section">
										<tr>
											<td>
												<div class="checkbox">
													<a href="#" class="ope_icon choose"></a> <input
														type="checkbox" name="sectionId" value="${section.id}">
												</div> <a href="javascript:void(0);" onclick="initEditSectionPage('${section.id}','${section.type}');" class="ope_icon edit"></a> 
												<a href="javascript:void(0);" onclick="initViewSectionPage('${section.id}','${section.type}','view');"  class="ope_icon detail"></a>
											</td>
											<td class="fl_left">${section.type}</td>
											<td class="no_rightborder fl_left">${section.sectionName}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
			</div>
		</div>


		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		var setting = {
			view : {
				showLine : false
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};

		var zNodes = ${sectionTree};
		$.fn.zTree.init($("#tree"), setting, zNodes);
		//选中默认节点
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var sectionId = ${sectionId};
		var node = treeObj.getNodeByParam("id", sectionId, null);
		treeObj.selectNode(node);
		//展开默认节点
		var node=treeObj.getNodeByParam("id",sectionId,null);
		treeObj.expandNode(node,true,true,true);
		
		
		$('.createPage').on('click', function(){
			var name = node.name;
		    $.layer({
		        type: 2,
		        title: 'Please select a page type',
		        maxmin: false,
		        shadeClose: true, //开启点击遮罩关闭层
		        area : ['400px' , '300px'],
				shadeClose: false,
		        offset : [($(window).height() - 300)/2+'px', ''],
		        iframe: {src: '<%=request.getContextPath()%>/section/selectPageType.action?sectionId=${sectionId}&sectionName='+name}
		    });
		});
		
	});
	
	function initAddSectionPage(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var sectionId = ${sectionId};
		var node = treeObj.getNodeByParam("id", sectionId, null);
		var id = node.id;
		var name = node.name;
		alertFirstIframe("Section","create.action?parentId="+id+"&sectionName="+name,'700px','525px');
	}
	
	function initEditSectionPage(sectionId,sectionType){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var treeSectionId = ${sectionId};
		var node = treeObj.getNodeByParam("id", treeSectionId, null);
		var name = node.name;
		if(sectionType == "Section"){
			alertFirstIframe("Section","edit.action?sectionId="+sectionId+"&isEdit=1&parentName="+name,'700px','525px');
		}else{
			alertFirstIframe("Page","editPage.action?pageId="+sectionId+"&sectionName="+name,'880px','640px');
		}
	}
	
	function initViewSectionPage(sectionId,sectionType,viewType){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var treeSectionId = ${sectionId};
		var node = treeObj.getNodeByParam("id", treeSectionId, null);
		var name = node.name;
		if(viewType == 'Preview'){
			var chooses = $("input[name='sectionId']:checked");
			if(chooses.length != 1){
				alert("Please choose a section or a page!");
				return false;
			}else{
				sectionId = $(chooses[0]).val();
				sectionType = $(chooses).parent().parent().parent().children("td").eq(1).text();
			}
		}
		if(sectionType == "Section"){
			alertFirstIframe("Section","edit.action?sectionId="+sectionId+"&isEdit=0",'700px','525px');
		}else{
			alertFirstIframe("Section","editPage.action?pageId="+sectionId+"&sectionName="+name+"&isEdit=0",'880px','640px');
		}
	}
	
	function previewPage(){
			
			var checkboxs = $("input[name='sectionId']:checked");
			
			var len = checkboxs.length;
			if( len == 0){
				alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
				return false;
			}
			if(len != 1){
				alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
				return false;
			}
			
			if(checkboxs.parents('tr').find('td').eq('1').html() != 'Page'){
				alert('Section cannot preview');
				return false;
			}
	
			var pageId = checkboxs.val();
			
			//window.open('${base}'+"/catalog/unlimited/previewTransfer.action?categoryMetadataId=1&type=catalog","catalogPreview");
			window.open('${base}'+"/section/unlimited/previewTransfer.action?pageId="+pageId+"&type=page","pagePreview");
			
	}
	
	function deleteSectionOrPage(){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		//alertConfirmDialog('Delete this data?');
		//取id和type用于区分删除的是section表还是page表
		var jsonData = "[";
		var chooses = $("input[name='sectionId']:checked");
		if(chooses.length == 0){
			alert("Please choose a section or a page at least!");
			return false;
		}else{
			
			//组织id和type以json格式传给后台
			sectionId = $(chooses[0]).val();
			sectionType = $(chooses).parent().parent().parent().children("td").eq(1).text();
			chooses.each(function(index,item){
				if(index == chooses.length-1){
					jsonData += '{"id":'+$(this).val()+','+'"type":"'+$(this).parent().parent().parent().children("td").eq(1).text()+'"}';
				}else{
					jsonData += '{"id":'+$(this).val()+','+'"type":"'+$(this).parent().parent().parent().children("td").eq(1).text()+'"},';
				}
			  });
		}
		jsonData += "]";
		
		$.post("delete.action", "data="+jsonData,function(data) {
			var result = eval(data);
			alert(result.msg);
			location.reload();
		}, "json");
	}
</script>
</html>
