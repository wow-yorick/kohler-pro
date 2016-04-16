<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/taglib/permissionTag.tld" prefix="per"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suites</title>
<jsp:include page="../common/common.jsp" />
</head>
<body class="index">
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main" style="width:100%">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
				<form id="searchForm" action="${pager.url}" method="get" >
					<h1>Suites</h1>
					<div class="query row">
						<div class="col-md-8">
							<div class="col-md-1"><label>Name</label></div>
							<div class="col-md-2"><input type="text" name="suiteName" value="${suiteName}"></div>						
						</div>
						<div class="opera_con btnr">
							<per:user resourceId="product.collections.view">
								<a class="btn" href="javascript:void(0);" onclick="previewCatalog()">Preview</a>
							</per:user>
							<per:user resourceId="product.collections.delete">
								<a class="btn delete" href="javascript:void(0);" onclick="deletePage()">Delete</a>
							</per:user>
							<per:user resourceId="product.collections.create">
							<a class="btn long" href="javascript:void(0);" onclick="initNewPage();">Create</a>
							</per:user>
							<a class="btn"  href="javascript:void(0);" onclick="searchBtn()">Search</a>
							
						</div>
					</div>
					</form>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0" id="dataTableList">
						<thead>
							<tr>
								<th style="width:10%">Action</th>
								<th style="width:50%">Name_CN</th>
								<th style="width:10%">Creator</th>
								<th  class="no_rightborder" style="width:30%">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
							<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<td colspan ="4">Sorry,these is no data for this action!</td>
									</tr>
								</c:when>

								<c:otherwise>
									<c:forEach items="${pager.datas}" var="suite">
										<tr>
											<td>
												<div class="checkbox">
													<a href="#" class="ope_icon choose"></a> <input
														type="checkbox" name="suiteMetadataId" value="${suite.suiteMetadataId}">
												</div> <a href="javascript:void(0);" onclick="initEditPage('${suite.suiteMetadataId}');" class="ope_icon edit"></a> 
												<a href="javascript:void(0);" onclick="initViewPage('${suite.suiteMetadataId}');"  class="ope_icon detail"></a>
											</td>
											<td class="fl_left">${suite.suiteName}</td>
											<td>${suite.createUser}</td>
											<td  class="no_rightborder">${fn:substring(suite.createTime, 0, 16)}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					<!-- 翻页部分table_con开始-->
					<jsp:include page="../common/page.jsp"></jsp:include>
					<!-- 翻页部分table_con开始-->
				</div>
				<!-- 表格table结束-->
				
			</div>
		</div>
		<!-- 内容区域content结束 -->

		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">

function alertInfo() {
	if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
		
	}
}
/**
 * 搜索按钮
 */
function searchBtn() {
	$('#searchForm').submit();
}

/**
 * 新增
 */
function initNewPage() {
	alertFirstIframe("Suite","create.action",'800px','650px');
}
/**
 * 显示编辑页
 */
function initEditPage(keyId) {
	if(keyId=="" || keyId==null || keyId == undefined){
		keyId = pageUtil.getSelectVals();
	}
	if(!keyId) {
		alertInfo();
		//layer.msg("Please choose a suite at first!", 1, 0);//按钮一的回调函数	
		return false;
	}
	alertFirstIframe("Suite","edit.action?suiteMetadataId="+keyId,'800px','650px');
}

/**
 * 显示详情页面
 */
function initViewPage(keyId) {
	if(keyId=="" || keyId==null || keyId == undefined){
		keyId = pageUtil.getSelectVals();
	}
	if(!keyId) {
		alertInfo()
		//layer.msg("Please choose a suite at first!", 1, 0);//按钮一的回调函数	
		return false;
	}
	alertFirstIframe("Suite","view.action?suiteMetadataId="+keyId,'800px','650px');
}

/**
 * 删除按钮
 */
function deletePage(keyId) {
	if(keyId=="" || keyId==null || keyId == undefined){
		keyId = pageUtil.getSelectVals();
	}
	
	if(!keyId) {
		alertInfo();
		//layer.msg("Please choose a suite at first!", 1, 0);//按钮一的回调函数	
		return false;
	}else {
		$(".xubox_main").addClass("a")
		$.layer({
			shade: [0],
			title: 'Confirm',
			area: ['310px', '130px'],
			dialog: {
				msg: 'Delete this data?',
				btns: 2,                    
				type: -1,
				btn: ['OK','Cancel'],
				yes: function(){
					$.post("delete.action",{'suiteMetadataId':keyId},function(data) {
						alert(data.msg);
						//layer.msg(result.msg, 1, 0);
						location.reload();
					}, "json");
								
				}
			}
		});
	}
	
	
}

var pageUtil = {
	'dataTable' : '#dataTableList',
	'getSelectVals' : function() {
		var _keyId='';
		var check = $(this.dataTable+' >tbody tr').find('td input[type=checkbox]:checked');
		if (check.length > 1) {
			check.each(function() {
				_keyId += ',' + $(this).val();
			});
			_keyId = _keyId.substr(1);
		} else if (check.length == 1) {
			_keyId = check.val();
		} else {
			_keyId = null;
		}
		return _keyId;
	
	}
		
}

function pagelog() {
	var msg = '[page.define] ' + Array.prototype.join.call(arguments, '');
	if (window.console && window.console.log) {
		window.console.log(msg);
	}else if (window.opera && window.opera.postError) {
		window.opera.postError(msg);
	}

};


	function previewCatalog(){
		
		var checkboxs = $("input[name='suiteMetadataId']:checked");
		
		var len = checkboxs.length;
		if( len == 0){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(len != 1){
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}

		var suiteMetadataId = checkboxs.val();
		
		
		window.open("unlimited/previewTransfer.action?suiteMetadataId="+suiteMetadataId+"&type=suite","suitePreview");
		
	}

</script>
</html>
