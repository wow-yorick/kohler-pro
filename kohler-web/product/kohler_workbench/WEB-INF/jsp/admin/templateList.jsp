<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>adminTemplates</title>
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
		//$(".checkbox input").click(function(){
		//	if($(this).parent(".checkbox").children("a").hasClass("active")){
		//		$(this).parent(".checkbox").children("a").removeClass("active");
		//		$(this).attr("checked",false);
		//	}
		//	else{
		//		$(this).parent(".checkbox").children("a").addClass("active");
		//		$(this).attr("checked",true);
		//	}
		//});
		// 增加jquery代碼結束
		$(".table tbody tr:odd").addClass("tr_bg");
	});

	</script>
</head>
<body class="admin_condition">
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
					<h1>Template</h1>
					<div class="query row">
						<form id="templateFrom" action="templateList.action" method="post">
							<div class="col-md-10">
								<div class="col-md-1"><label>Name</label></div>
								<div class="col-md-2"><input type="text" name="templateName" value="${searchTem.templateName}"></div>
								<div class="col-md-1"><label>Type</label></div>
								<div class="col-md-2"><select name="templateType">
															<c:if test="${searchTem.templateType == null}">
																<option value="">--Please select--</option>
															</c:if>
															<c:if test="${searchTem.templateType != null}">
																<c:forEach  items="${typeList}" var="li">
																	<c:if test="${searchTem.templateType == li.masterdataId}">
			                            								<option value="${li.masterdataId}">${li.masterdataName}</option>
			                            								<option value="">--Please select--</option>
			                            							</c:if>
			                                                	</c:forEach>
															</c:if>
															<c:forEach  items="${typeList}" var="li">
																<c:if test="${searchTem.templateType != li.masterdataId}">
			                            							<option value="${li.masterdataId}">${li.masterdataName}</option>
			                            						</c:if>
			                                                </c:forEach>    
	                            						</select></div>
							</div>
						</form>
						<div class="opera_con col-md-2">
							<a class="btn" href="javascript:void(0);" onclick="searchTem();">Search</a>
							<a class="btn new" href="javascript:void(0);" onclick="createTemplate();">Create</a>
							<a class="btn delete" href="javascript:void(0);" onclick="deleteTemplate();">Delete</a>
                            <a class="btn" href="javascript:void(0);" onclick="previewTemplate();">Preview</a>
						</div>
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th>Action</th>
								<th>Type</th>
								<th>Name</th>
								<th>Creator</th>
								<th  class="no_rightborder">Creation Date</th>
							</tr>
						</thead>
						<tbody> 
								<c:forEach items="${list.datas}" var="template">
									<tr>
										<td>
											<div class="checkbox">
												<a href="#" class="ope_icon choose"></a>
												<input type="checkbox" name="templateId" value="${template.templateId}">
											</div>
											<a href="javascript:void(0);" onclick="updateTemplate('${template.templateId}');" class="ope_icon edit"></a>
											<a href="javascript:void(0);"  onclick="searchTemplate('${template.templateId}');" class="ope_icon detail"></a>
										</td>
										<td>${template.templateType}</td>
										<td>${template.templateName}</td>
										<td>${template.createUser}</td>
										<td class="no_rightborder">${template.createTime}</td>
									</tr>
							 	</c:forEach>
						</tbody>
						<c:import url="/common/footer.action"></c:import>
					</table>
                   	<script>
//弹出一个iframe层
//$('.edit').on('click', function(){
//    $.layer({
//       type: 2,
//        title: 'adminTemplates',
//        maxmin: false,
//        shadeClose: true, //开启点击遮罩关闭层
//        area : ['880px' , '600px'],
//		shadeClose: false,
//        offset : [($(window).height() - 550)/2+'px', ''],
//        iframe: {src: 'adminTemplateslayer.html'}
//    });
//});
//$('.detail').on('click', function(){
//    $.layer({
//        type: 2,
//        title: 'adminTemplates',
//        maxmin: false,
//        shadeClose: true, //开启点击遮罩关闭层
//        area : ['880px' , '600px'],
//		shadeClose: false,
//        offset : [($(window).height() - 550)/2+'px', ''],
//        iframe: {src: 'adminTemplateslayer.html'}
//    });
//});
//$('.new').on('click', function(){
//    $.layer({
//        type: 2,
//        title: 'adminTemplates',
//        maxmin: false,
//        shadeClose: true, //开启点击遮罩关闭层
//        area : ['880px' , '600px'],
//		shadeClose: false,
//       offset : [($(window).height() - 550)/2+'px', ''],
//        iframe: {src: 'adminTemplateslayer.html'}
//    });
//});
//删除按钮
//$('.delete').on('click', function(){
//	$(".xubox_main").addClass("a")
//	$.layer({
//   shade: [0],
//	title: 'Confirm',
//    area: ['310px', '130px'],
//    dialog: {
//        msg: 'Delete this data?',
//        btns: 2,                    
//        type: -1,
//        btn: ['OK','Cancel'],
//        yes: function(){
//            layer.msg('Delete the success', 1, 1);//按钮一的回调函数			
//        }
//    }
//});});
	function createTemplate(){
		alertFirstIframe("Create","create.action",'840px','600px');
	}
	function searchTem(){
		$("#templateFrom").submit();
	}
	function deleteTemplate(){
		var jsonData = "";
		var chooses = $("input[name='templateId']:checked");
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
	function updateTemplate(templateId){
		alertFirstIframe("update","edit.action?templateId="+templateId+"&isEdit=0",'840px','600px');
	}
	function searchTemplate(templateId){
		alertFirstIframe("search","edit.action?templateId="+templateId+"&isEdit=1",'840px','600px');
	}
	function previewTemplate(){
		var chooses = $("input[name='templateId']:checked");
		if(chooses.length == 0){
			alert("Please choose a section or a page at least!");
			return false;
		}
		if(chooses.length > 1){
			alert("Please only choose a data!");
			return false;
		}
		alertFirstIframe("search","view.action?templateId="+chooses,'840px','600px');
	}
	</script>
					<div class="page">
						<span class=""></span>
						<span></span>
					</div>
				</div>
				<!-- 表格table结束-->
				<!-- 翻页部分table_con开始-->
				<div class="table_con">
					<div class="page">
						<jsp:include page="../common/page.jsp"></jsp:include>
					</div>
				</div>
				<!-- 翻页部分table_con开始-->
			</div>
		</div>
		<!-- 内容区域content结束 -->
	</div>
</body>
</html>