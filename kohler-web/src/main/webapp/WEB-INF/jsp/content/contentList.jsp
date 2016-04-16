<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Content</title>
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
					<div class="opera_con col-md-7">
						<form id="searchForm"
							action="../content/contentList.action" method="post">
							<div class="col-md-12" >
								<pageTag:datatype-search datatypeId="${datatypeId}" />
							</div>
						</form>
					</div>
					
					<div class="opera_con col-md-4" style="float:right;">
						<a class="btn searchConfirm" href="#">Search</a>
						<per:user resourceId="content.create">
						<a class="btn long" href="javascript:void(0);" onclick="initAddContent();">Create</a>
						</per:user>
						<a class="btn" href="javascript:void(0);" onclick="deleteContent();">Delete</a>
						<!--<a class="btn" href="javascript:void(0);" onclick="initViewContent(-1,'Preview');">Preview</a>  -->
					</div>
				</div>
				<div class="table">
					<!-- 内容区域content的主要区域content_main的表格table-->
					<table cellspacing="0">
						<thead>
							<tr>
								<th>Action</th>
								<th>Id</th>
								<c:forEach items="${fieldlist}" var="field">
									<th>${field}</th>
								</c:forEach>
								<th>MODIFY TIME</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<td colspan ="${fn:length(fieldlist)+3}">Sorry,these is no data for this action!</td>
									</tr>
								</c:when>

								<c:otherwise>
									<c:forEach items="${pager.datas}" var="content">
										<tr>
											<c:forEach items="${content}" var="item" varStatus="status">
												<c:if test="${status.index==0 }">
													<td width="15%">
														<div class="checkbox">
															<a href="#" class="ope_icon choose"></a> <input
																type="checkbox" name="contentMetadataId" value="${item.value}">
														</div> <a href="javascript:void(0);" onclick="initEditContent('${item.value}');" class="ope_icon edit"></a> 
														<a href="javascript:void(0);" onclick="initViewContent('${item.value}','view');"  class="ope_icon detail"></a>
													<!--  <td>${item.value}</td>-->
													</td>
													<td>${item.value}</td>
												</c:if>
												<c:if test="${status.index!=0 }">
													<td>${item.value}</td>
												</c:if>
											</c:forEach>
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

		var zNodes = ${datatypeTree};
		$.fn.zTree.init($("#tree"), setting, zNodes);
		//选中默认节点
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var datatypeId = ${datatypeId};
		var node = treeObj.getNodeByParam("id", datatypeId, null);
		treeObj.selectNode(node);
		//展开默认节点
		var node=treeObj.getNodeByParam("id",datatypeId,null);
		treeObj.expandNode(node,true,true,true);
		
		$('.searchConfirm').on('click', function() {
			$('#selectList').html("");
			
			var sel = "";
			var count = 1;
			$("[id=actionSearch]").each(function(){
				var search = $(this).val();
				if(search.trim() != "" && search.trim().length > 0){
					count ++;
				}
				sel += $(this).attr('name') +"="+encodeURIComponent(search) + "&";
				//sel = sel.substring(0,sel.length - 1);
			}); 
			sel = sel.substring(0,sel.length - 1);
				if(count == 0){
					alert("Please input the query information!");
					return false;
				}
				
				location.href = "contentList.action?selStr="+encodeURIComponent(sel)+"&datatypeId="+'${datatypeId}';
				
				
				
			});
	});
	
	document.onkeypress = function(){
		if(event.keyCode == 13) {return false;}}
	
	$(function() {
		<c:forEach items="${mlist}" var="map">
			$('#searchForm input[name="${map.fieldname}"]').val('${map.fieldvalue}');
		</c:forEach>
	});
	
	function initAddContent(){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var datatypeId = ${datatypeId};
		var node = treeObj.getNodeByParam("id", datatypeId, null);
		var id = node.id;
		var name = node.name;
		alertFirstIframe("${d.datatypeName}","create.action?datatypeId="+datatypeId,'880px','600px');
	}
	
	function initEditContent(contentMatadataId){
		
		alertFirstIframe("${d.datatypeName}","edit.action?contentMetadataId="+contentMatadataId+"&isEdit=1",'880px','600px');
		
	}
	
	function initViewContent(contentMetadataId,viewType){
		if(viewType == 'Preview'){
			var chooses = $("input[name='contentMetadataId']:checked");
			if(chooses.length != 1){
				alert("Please choose a content or a page!");
				return false;
			}else{
				contentMetadataId = $(chooses[0]).val();
			}
		}
		alertFirstIframe("${d.datatypeName}","edit.action?contentMetadataId="+contentMetadataId+"&isEdit=0",'880px','600px');
	}
	
	function deleteContent(){
		if(!confirm("Do you want to delete this data？")){
			return false;
		}
		//alertConfirmDialog('Delete this data?');
		var jsonData = "[";
		var chooses = $("input[name='contentMetadataId']:checked");
		if(chooses.length == 0){
			alert("Please choose a content at least!");
			return false;
		}else{
			
			//id以json格式传给后台
			chooses.each(function(index,item){
				if(index == chooses.length-1){
					jsonData += '{"id":'+$(this).val()+'}';
				}else{
					jsonData += '{"id":'+$(this).val()+'},';
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
