<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>adminMasterdata</title>
	<jsp:include page="../common/common.jsp" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ztree.all-3.5.min.js"></script>
	<script>
	$(document).ready(function(){
		$(".shadow").hover(function(){
			$(this).children("a.me_active").addClass("active");
			$(this).children("ul").css("display","block");
		},function(){
			$(this).children("a.me_active").removeClass("active");
			$(this).children("ul").css("display","none");
		});
		$(".activation").click(function(){
			if($(this).hasClass("active"))
				$(this).removeClass("active");
			else{
				$(this).addClass("active");
			}
		});

		// 增加jquery代碼開始
		
		// 增加jquery代碼結束
		$(".table tbody tr:odd").addClass("tr_bg");
	});

	</script>
</head>
<body class="admin_user">
	<div class="wrap">
		<!-- 公共头部head开始 -->
		<div class="head">
			<c:import url="/common/header.action"></c:import>
		</div>
		<!-- 公共头部head结束 -->

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>Masterdate</h1>
					<div class="query row">
						<div class="col-md-10">
							<form id="masterDataFrom" action="masterdataList.action" method="get">
								<div class="col-md-1"><label>Value</label></div>
								<div class="col-md-2"><input type="text" name="masterdataName" value="${list.masterdataName}"></div>
								<div class="col-md-1"><label>Type</label></div>
								<div class="col-md-2">
	                            	<select name="masterdataTypeId">
	                                	<c:if test="${list.masterdataTypeId == null}">
	                                		<option value="">-- Please select --</option>
	                                	</c:if>
	                                	
	                                	
	                                	<c:if test="${list.masterdataTypeId != null}">
	                                		<c:forEach items="${typeList}" var="allType">
		                                		<c:if test="${list.masterdataTypeId == allType.templateId}">
			                                		<option value="${allType.templateId}">${allType.templateName}</option>
			                                		<option value="">-- Please select --</option>
			                                	</c:if>
		                                	</c:forEach>
	                                	</c:if>
	                                	<c:forEach items="${typeList}" var="allType">
	                                		<c:if test="${list.masterdataTypeId != allType.templateId}">
			                                		<option value="${allType.templateId}">${allType.templateName}</option>
			                                	</c:if>
	                                	</c:forEach>
	                                </select>
	                            </div>
                            </form>
						</div>
						<div class="opera_con col-md-2">
							<a class="btn" href="javascript:void(0);" onclick="serchMasterData();">Search</a>
							<a class="btn new" href="javascript:void(0);" onclick="newMasterDataPage();">Create</a>
							<a class="btn delete" href="javascript:void(0);" onclick="deleteMasterdataMetadata();">Delete</a>
						</div>
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:10%">Action</th>
								<th>Type</th>
                                <th>Code</th>
                                <th style="width:20%">Value</th>
								<th>Creator</th>
								<th  class="no_rightborder" style="width:10%">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
							<c:forEach items="${masterData.datas}" var="mas">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="masterdataMetadataId" value="${mas.masterdataId}">
										</div>
										<a href="javascript:void(0);" onclick="updateMasterData('${mas.masterdataId}');" class="ope_icon edit"></a>
										<a href="javascript:void(0);" onclick="searchMasterData('${mas.masterdataId}');" class="ope_icon detail"></a>
									</td>
									<td>${mas.masterdataTypeId}</td>
	                                <td>${mas.masterdataCode}</td>
									<td>${mas.masterdataName}</td><!--如果要居左加class fl_left-->
									<td>${mas.createUser}</td>
									<td class="no_rightborder">${mas.createTime}</td>
								</tr>
                           </c:forEach>
						</tbody>
					</table>
					<!-- 翻页部分table_con开始-->
					<div class="table_con">
						<div class="page">
							<jsp:include page="../common/page.jsp"></jsp:include>
						</div>
					</div>
    <script type="text/javascript">
    	function serchMasterData(){
    		$("#masterDataFrom").submit();
    	}
		function newMasterDataPage(){
			alertFirstIframe("Create","create.action",'840px','600px');
		}
		function updateMasterData(masterDataId){
			alertFirstIframe("update","edit.action?masterDataMetadataId="+masterDataId+"&isEdit=0",'840px','600px');
		}
		function searchMasterData(masterDataId){
			alertFirstIframe("update","edit.action?masterDataMetadataId="+masterDataId+"&isEdit=1",'840px','600px');
		}
		function deleteMasterdataMetadata(){
			var jsonData = "";
			var chooses = $("input[name='masterdataMetadataId']:checked");
			if(chooses.length == 0){
				alert("Please choose a section or a page at least!");
				return false;
			}else{
				//组织id和type以json格式传给后台
				sectionId = $(chooses[0]).val();
				sectionType = $(chooses).parent().parent().parent().children("td").eq(1).text();
				chooses.each(function(index,item){
					if(index == chooses.length-1){
						jsonData += $(this).val();
					}else{
						jsonData += $(this).val()+',';
					}
				  });
			}
			var isok = confirm("Sure you want to delete？");
			if(isok){
				$.post("delete.action", "data="+jsonData,function(data) {
					var result = eval(data);
					alert(result.msg);
					location.reload();
				}, "json");
			}
		}
	</script>
					<div class="page">
						<span class=""></span>
						<span></span>
					</div>
				</div>
				<!-- 表格table结束-->
				<!-- 翻页部分table_con开始-->
				<!--<div class="table_con">
					<div class="page">
						<span class="page_cur">1</span>/<span class="page_all">8</span>
						<a class="page_down" href="#"></a>
						<a class="page_up" href="#"></a>
					</div>
				</div>
				 翻页部分table_con开始-->
			</div>
		</div>
		<!-- 内容区域content结束 -->
	</div>
</body>
</html>