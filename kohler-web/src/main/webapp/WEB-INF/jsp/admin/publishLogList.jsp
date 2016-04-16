<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manual Publish</title>
<jsp:include page="../common/common.jsp" />

</head>
<body class="admin_user">
	<div class="wrap">
		<c:import url="/common/header.action"></c:import>

		<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->
				<div class="condition">
					<h1>Manual Publish</h1>
					<div class="query row">
						<form id="searchForm"
							action="<%=request.getContextPath()%>/manualPublish/manualPublishList.action"
							method="post">
							<div class="col-md-8">
								<div class="col-md-1">
									<label>Start Date</label>
								</div>
								<div class="col-md-3">
									<input class="laydate-icon" name="beginDate"
										value="${beginDate }" readonly="readonly" 
										onClick="laydate()" />
								</div>
								<div class="col-md-1">
									<label>End Date</label>
								</div>
								<div class="col-md-3">
									<input class="laydate-icon" name="endDate" value="${endDate }"
										readonly="readonly"  onClick="laydate()">
								</div>
								<div class="col-md-1">
									<label>status</label>
								</div>
								<div class="col-md-2">
									<select name="publishStatus" value="${status}">
										<option value="">--select--</option>
										<c:forEach items="${typeList}" var="allType">
											<option value="${allType.masterdataId}">${allType.masterdataName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</form>
						<div class="opera_con col-md-4">
							<a class="btn" href="javascript:void(0);"
								onclick="searchPublishLog()">Search</a> <a class="btn"
								href="javascript:void(0);" onclick="lockOpenPublish()">Lock</a>
							<a class="btn " href="javascript:void(0);" onclick="unlockPublish()" >Unlock</a> <a
								class="btn big Publish" href="javascript:void(0);" id=""
								onclick="executeManualPublish()">Manual Publish</a> <a
								class="btn Rollback" href="javascript:void(0);" onclick="rollBackPublish()" >Rollback</a>
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
								<th>Version Id</th>
								<th style="width:15%">Start Time</th>
								<th style="width:15%">Publish Time</th>
								<th>Status</th>
								<th style="width:35%">Descrption</th>
								<th class="no_rightborder" style="width:10%">Previous Id</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${fn:length(pager.datas) == 0 }">
									<tr>
										<td colspan="7">Sorry,these is no data for this action!</td>
									</tr>
								</c:when>

								<c:otherwise>
									<c:forEach items="${pager.datas}" var="publishLog">
										<tr>
											<td>
												<div class="checkbox">
													<a href="#" class="ope_icon choose"></a> <input
														type="checkbox" name="publishLogId"
														value="${publishLog.publish_log_id }">
												</div> <a href="javascript:void(0);" onclick="view('${publishLog.publish_log_id }','1')" class="ope_icon detail"></a>
											</td>
											<td class="fl_left">${publishLog.version_Id }</td>
											<td class="fl_left">${publishLog.start_Time }</td>
											<td class="fl_left">${publishLog.publish_Time }</td>
											<!--如果要居左加class fl_left-->
											<td class="fl_left">${publishLog.statusName }</td>
											<td class="fl_left">${publishLog.remark }</td>
											<td class="no_rightborder">${publishLog.version_Id -1}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="page">
						<span class=""></span> <span></span>
					</div>
				</div>
				<!-- 表格table结束-->
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>
		<!-- 内容区域content结束 -->
		<c:import url="/common/footer.action"></c:import>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("select").each(
						function() {
							var select = $(this);
							var selectValue = select.attr("value");

							select.find("option[value='" + selectValue + "']")
									.attr("selected", true);
						});
				
				$(".checkbox input").unbind("click");

				// 增加jquery代碼開始
				$(".checkbox input").click(function(){
					if($(this).parent(".checkbox").children("a").hasClass("active")){
						$(this).parent(".checkbox").children("a").removeClass("active");
						$(this).attr("checked",false);
					}
					else{
						if($(this).attr("name")){
							var name=$(this).attr("name");
							$(document).find("input[name='"+name+"']").parent(".checkbox").children("a").removeClass("active");
							$(document).find("input[name='"+name+"']").attr("checked",false);
						}
						$(this).parent(".checkbox").children("a").addClass("active");
						$(this).attr("checked",true);
					}
				});
				// 增加jquery代碼結束
				
			});
	
	function view(publishLogId,isEdit){
		$.layer({
			type : 2,
			title : 'Manual Publish',
			maxmin : false,
			shadeClose : true, //开启点击遮罩关闭层
			area : [ '880px', '600px' ],
			shadeClose : false,
			offset : [ ($(window).height() - 550) / 2 + 'px', '' ],
			iframe : {
				src : 'view.action?publishLogId=' + publishLogId+'&isEdit='+isEdit
			}
		});
	}
	
	function searchPublishLog() {
		$("#searchForm").submit();
	}

	function executeManualPublish() {
		var checkValue = $("input[name='publishLogId']");
		
		var publishLogIds = new Array();
		
		var status = "";
		$.each(checkValue, function(i, item) {
			if($(item).attr("checked")){
				status = $(this).parents("tr").find("td:eq(4)").text();
				publishLogIds.push($(item).val());			
			}
		});
		
		var len = publishLogIds.length;
		if (len == 0) {
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(status != "已锁定"){
			alert("<spring:message code="info.publish.unlock.warning" arguments="" argumentSeparator=","/>");
			return false;
		}
		var publishLogId = publishLogIds[0];
		
		$.layer({
			type : 2,
			title : 'Manual Publish',
			maxmin : false,
			shadeClose : true, //开启点击遮罩关闭层
			area : [ '880px', '600px' ],
			shadeClose : false,
			offset : [ ($(window).height() - 550) / 2 + 'px', '' ],
			iframe : {
				src : 'view.action?publishLogId=' + publishLogId+'&isEdit=0'
			}
		});
	}

	function rollBackPublish() {

		var checkValue = $("input[name='publishLogId']");
		
		var publishLogIds = new Array();
		
		var status = "";
		$.each(checkValue, function(i, item) {
			if($(item).attr("checked")){
				status = $(this).parents("tr").find("td:eq(4)").text();
				publishLogIds.push($(item).val());			
			}
		});
		console.info(publishLogIds);
		console.info(status);
		var len = publishLogIds.length;
		if (len == 0) {
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(status != "已发布"){
			alert("<spring:message code="info.publish.published.warning" arguments="" argumentSeparator=","/>");
			return false;
		}
		var publishLogId = publishLogIds[0];
		$.post("unlimited/checkpublish.action", "publishLogId=" + publishLogId,
							function(data) {
								var result = eval(data);
								if(result.check != 1){
										alert("<spring:message code="info.publish.lastpublished.warning" arguments="" argumentSeparator=","/>");
										return false;				
								}else{
									
									$(".xubox_main").addClass("a");
									$.layer({
										shade : [ 0 ],
										title : 'locked',
										area : [ '310px', '130px' ],
										dialog : {
											msg : 'Sure you want to rollback?',
											btns : 2,
											type : -1,
											btn : [ 'OK', 'Cancel' ],
											yes : function() {
												$.post("rollback.action", "publishLogId=" + publishLogId,
														function(data) {
															var result = eval(data);
															//alert(result.msg);
															layer.msg(result.msg, 1, 1);//按钮一的回调函数
															location.reload();

														}, "json");

											}
										}
									});
									
								}

							}, "json");

	}
	
	function lockOpenPublish() {

		var checkValue = $("input[name='publishLogId']");
		
		var publishLogIds = new Array();
		
		var status = "";
		$.each(checkValue, function(i, item) {
			if($(item).attr("checked")){
				status = $(this).parents("tr").find("td:eq(4)").text();
				publishLogIds.push($(item).val());			
			}
		});
		console.info(publishLogIds);
		console.info(status);
		var len = publishLogIds.length;
		if (len == 0) {
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(status != "打开"){
			alert("<spring:message code="info.publish.lock.warning" arguments="" argumentSeparator=","/>");
			return false;
		}
		var publishLogId = publishLogIds[0];
		console.info(publishLogId);

		$(".xubox_main").addClass("a");
		$.layer({
			shade : [ 0 ],
			title : 'locked',
			area : [ '310px', '130px' ],
			dialog : {
				msg : 'Sure you want to lock?',
				btns : 2,
				type : -1,
				btn : [ 'OK', 'Cancel' ],
				yes : function() {
					$.post("lock.action", "publishLogId=" + publishLogId,
							function(data) {
								var result = eval(data);
								//alert(result.msg);
								layer.msg(result.msg, 1, 1);//按钮一的回调函数
								location.reload();

							}, "json");

				}
			}
		});
	}
	
	function unlockPublish() {

		var checkValue = $("input[name='publishLogId']");
		
		var publishLogIds = new Array();
		
		var status = "";
		$.each(checkValue, function(i, item) {
			if($(item).attr("checked")){
				status = $(this).parents("tr").find("td:eq(4)").text();
				publishLogIds.push($(item).val());			
			}
		});
		console.info(publishLogIds);
		console.info(status);
		var len = publishLogIds.length;
		if (len == 0) {
			alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
			return false;
		}
		if(status != "已锁定"){
			alert("<spring:message code="info.publish.unlock.warning" arguments="" argumentSeparator=","/>");
			return false;
		}
		var publishLogId = publishLogIds[0];
		console.info(publishLogId);

		$(".xubox_main").addClass("a");
		$.layer({
			shade : [ 0 ],
			title : 'locked',
			area : [ '310px', '130px' ],
			dialog : {
				msg : 'Sure you want to lock?',
				btns : 2,
				type : -1,
				btn : [ 'OK', 'Cancel' ],
				yes : function() {
					$.post("unlock.action", "publishLogId=" + publishLogId,
							function(data) {
								var result = eval(data);
								//alert(result.msg);
								layer.msg(result.msg, 1, 1);//按钮一的回调函数
								location.reload();

							}, "json");

				}
			}
		});
	}
	
</script>
</html>