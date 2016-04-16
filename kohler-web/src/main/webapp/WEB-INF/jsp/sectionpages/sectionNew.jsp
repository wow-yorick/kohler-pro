<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Section</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<jsp:include page="../common/common.jsp" />
<script type="text/javascript">
	$(function() {
		var $tab_li = $('#tab ul li');
		$tab_li.click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			var index = $tab_li.index(this);
			$('div.tab_box > .box').eq(index).show().siblings().hide();
		});
		$("#txt span").click(function() {
			var boxClass = $(this).attr("class");
			if (boxClass == "check") {
				$(this).attr("class", "checked");
			} else {
				$(this).attr("class", "check");
			}
		});

	});
</script>
</head>

<body>
	<div
		class="container queryConditions section_lay_one">
		<!--shadow开始-->
		<div class="shadow"></div>
		<!--shadow结束-->
		<!--main开始-->
		<div class="main">
			<form id="addSectionForm">
				<input type="hidden" name="site" value="1" />
				<input type="hidden" name="sectionId" value="${section.sectionId }" />
				<div class="search">
					<div class="row">
						<div class="col-md-2">Id</div>
						<div class="col-md-4">${section.sectionId }</div>
						<div class="col-md-2">Parent</div>
						<div class="col-md-4">
							<select name="parentId" disabled>
								<option value="${section.parentId}">${section.sectionName}</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 required">Name</div>
						<div class="col-md-4">
							<input type="text" name="sectionName"  required="required" maxlength="25" isSectionNameUnique="true" />
						</div>
						<div class="col-md-2 required">Folder Select</div>
						<div class="col-md-4">
							<select name="publishFolderId" required="required">
								<option></option>
								<c:forEach items="${publishFolders}" var="publishFolder">
									<option value="${publishFolder.publishFolderId}">${publishFolder.publishFolderName}</option>
								</c:forEach>
							</select>
							
						</div>
					</div>
				</div>
			</form>
			<div class="text">
				<div class="row">
					<div class="col-md-2">Creator</div>
					<div class="col-md-4">${section.createUser }</div>
					<div class="col-md-3">Creation Date</div>
					<div class="col-md-3">
						<fmt:formatDate value="${section.createTime}"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">Modifier</div>
					<div class="col-md-4">${section.modifyUser }</div>
					<div class="col-md-3">Modification Date</div>
					<div class="col-md-3">
						<fmt:formatDate value="${section.modifyTime}"
							pattern="yyyy-MM-dd HH:mm" />
					</div>
				</div>
			</div>
			<div class="btns">
				<a href="javascript:void(0);" onclick="addSection();"
					class="confirm">Save</a> <a href="#" class="cancel">Cancel</a>
			</div>
			<div id="errorMessage" style="display: none"></div>
		</div>
		<!--main结束-->
	</div>
</body>
<script type="text/javascript">
//判断sectionName是否唯一
		jQuery.validator.addMethod("isSectionNameUnique", function(value, element) { 
			var pathName = window.document.location.pathname;
			// 获取带"/"的项目名，如：/uimcardprj
			var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		    var result = false;
		    // 设置同步
		    $.ajaxSetup({
		        async: false
		    });
		    var param = {
		        sectionName: value
		    };
		    console.log(param);
		    $.post(projectName + "/section/unlimited/checkSectionName.action", param, function(data){
		    	data = eval(data);  
				var flag = data.flag;
		        result = ("0" == flag);
		    },"json");
		    // 恢复异步
		    $.ajaxSetup({
		        async: true
		    });
		    return result;
		}, "this section name has been existed!"); 
			//关闭frame
			$(function() {
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				$('.cancel').on('click', function() {
					parent.layer.close(index); //执行关闭
				});
			});

			function addSection() {
				$("#errorMessage").html("");
				if ($("#addSectionForm").valid()) {
					$.post("createSave.action", $("#addSectionForm").serialize(),function(data) {
						var result = eval(data);
						alert(result.msg);
						var index = parent.layer.getFrameIndex(window.name);
						parent.location.reload();
						parent.layer.close(index);
					}, "json");
				}else{
					alert($("#errorMessage").html());
				}
			}
		</script>
</html>

