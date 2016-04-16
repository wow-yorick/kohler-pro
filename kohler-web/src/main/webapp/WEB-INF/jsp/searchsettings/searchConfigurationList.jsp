<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>searchsettings</title>
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
		$(".checkbox input").click(function(){
			if($(this).parent(".checkbox").children("a").hasClass("active")){
				$(this).parent(".checkbox").children("a").removeClass("active");
				$(this).attr("checked",false);
			}
			else{
				$(this).parent(".checkbox").children("a").addClass("active");
				$(this).attr("checked",true);
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
			<form id="updateForm">
				<!-- content的主要区域content_main开始 -->
				<div class="content_main">
					<!-- 操作及筛选区域condition开始-->	
					<div class="condition searchcondition">
						<h1>Search Configuration</h1>
						<div class="query row">
							<div class="col-md-5">
								<div class="col-md-4"><label>Did You Mean Threshold</label></div>
								<div class="col-md-4"><input type="text" id="masterdataName" name="masterdataName"></div>
							</div>
						</div>
	                    
					</div>
					<!-- 操作及筛选区域condition结束-->
					<!--底部按钮开始-->
					<div class="Searchbtns">
	        			<a href="#" class="confirm" onclick="editSave();">Save</a>
	        			<a href="#" class="cancel">Cancel</a>
	        		</div>
		 		</div>
		 	</form>
		</div>
		<!-- 内容区域content结束 -->
	</div>
    <script  type="text/javascript">
	  	function editSave(){
	  		var masterdataName = $("#masterdataName").val();
	  		if(masterdataName == '' || masterdataName == null){
	  			alert("请输入想修改的参数！");
	  			return;
	  		}
	  		$.post("unlimited/editSave.action", $("#updateForm").serialize(),function(data) {
				var result = eval(data);
				alert(result.msg);
				parent.location.reload();
			}, "json");
	  	}
    	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		$('#closebtn').on('click', function(){
    	parent.layer.close(index); //执行关闭
		});
    </script>
</body>
</html>