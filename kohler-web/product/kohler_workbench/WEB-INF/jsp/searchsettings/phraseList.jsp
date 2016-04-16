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
	function searchPhraseList(){
		$("#searchForm").submit();
	}
    function createPhrase(){
    	alertFirstIframe("Create","create.action",'840px','600px');
    }
    function updateSpelling(spellingId){
    	alertFirstIframe("update","edit.action?phraseId="+spellingId+"&isEdit=0",'840px','600px');
    }
    function searchSpelling(spellingId){
    	alertFirstIframe("update","edit.action?phraseId="+spellingId+"&isEdit=1",'840px','600px');
    }
    function deleteSeplling(){
    	var jsonData = "";
		var chooses = $("input[name='searchPhraseId']:checked");
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
		// 增加jquery代碼結束
		$(".table tbody tr:odd").addClass("tr_bg");
	});
	
	</script>
</head>
<body class="query_condition collections">
	<div class="wrap newproducts">
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
					<h1>Spelling Check</h1>
					<form id="searchForm" action="searchPhraseList.action" method="post">	
						<div class="query row">
							<div class="col-md-10">
								<div class="col-md-1"><label>Name</label></div>
								<div class="col-md-2"><input type="text" name="settingValue" value="${v}"></div>						
							</div>
							<div class="opera_con col-md-2 opera_fr">
								<a class="btn" href="javascript:void(0);" onclick="searchPhraseList();">Search</a>
								<a class="btn long" href="javascript:void(0);" onclick="createPhrase();">Create</a>
								<a class="btn delete" href="javascript:void(0);" onclick="deleteSeplling();">Delete</a>
							</div>
						</div>
					</form>	
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th style="width:10%">Action</th>
								<th  class="no_rightborder" style="width:90%">Name</th>
							</tr>
						</thead>
						<tbody> 
							<c:forEach items="${list.datas}" var="l">
								<tr>
										<td>
											<div class="checkbox">
												<a href="#" class="ope_icon choose"></a>
												<input type="checkbox" value="${l.searchPhraseId}"
												    name="searchPhraseId">
											</div>
											<a href="#" class="ope_icon edit" onclick="updateSpelling('${l.searchPhraseId}');"></a>
											<a href="#" class="ope_icon detail" onclick="searchSpelling('${l.searchPhraseId}');"></a>
										</td>
										<td class="no_rightborder fl_left">${l.settingValue}</td>
								</tr>			
							</c:forEach>	
						</tbody>
					</table>
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
    <script>
    
//删除按钮
	</script>
</body>
</html>