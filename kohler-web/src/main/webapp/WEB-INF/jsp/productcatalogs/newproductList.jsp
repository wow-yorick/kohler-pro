<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pageTag" uri="/WEB-INF/taglib/pageTag.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Product</title>
<jsp:include page="../common/common.jsp" />
</head>
<body class="query_condition">
	<div class="wrap newproducts">
		<c:import url="/common/header.action"></c:import>
	<!-- 内容区域content开始 -->
		<div class="content">
			<!-- content的主要区域content_main开始 -->
			<div class="content_main">
				<!-- 操作及筛选区域condition开始-->	
				<div class="condition">
					<h1>New Product</h1>
					<div class="query row">
					 <form id="newproductFrom" action="newProductList.action" method="post">
						<div class="col-md-9">
							<div class="col-md-1"><label>Name</label></div>
							<div class="col-md-2"><input type="text" name="searchname" value="${searchname}"></div>						
						</div>
						<div class="opera_con col-md-3 opera_fr">
							<a class="btn search" id="searchnew" href="#">Search</a>
							<a class="btn long" href="#">Create</a>
							<a class="btn delete" href="#">Delete</a>
							<a class="btn" href="javascript:void(0);" onclick="previewNewProduct();">Preview</a>
						</div>
					  </form>	 
					</div>
				</div>
				<!-- 操作及筛选区域condition结束-->
				<!-- 表格table开始-->
				<div class="table">
					<table cellspacing="0">
						<thead>
							<tr>
								<th>Action</th>
								<th>Catalog</th>
								<th>Name_CN</th>
								<th>Creator</th>
								<th  class="no_rightborder">Creation Date</th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${pager.datas}" var="newproductlist">
								<tr>
									<td>
										<div class="checkbox">
											<a href="#" class="ope_icon choose"></a>
											<input type="checkbox" name="newproduct"  value="${newproductlist.NEW_ARRIVAL_METADATA_ID}">
										</div>
										<a href="javascript:void(0);"
										onclick="editnewProduct(${newproductlist.NEW_ARRIVAL_METADATA_ID})"
										class="ope_icon edit"></a> <a href="javascript:void(0);"
										onclick="viewnewProduct(${newproductlist.NEW_ARRIVAL_METADATA_ID})"
										class="ope_icon detail"></a></td>
									<td class="fl_left">${newproductlist.CATEGORY_NAME}</td>
									<td class="fl_left">${newproductlist.NEW_ARRIVAL_NAME}</td>
									
									<td class="fl_left">${newproductlist.CREATE_USER}</td>
									<td class="no_rightborder">${newproductlist.CREATE_TIME}</td>
								</tr>
							</c:forEach>
						 </tbody>
					</table>
					      <div class="page">
						<span class=""></span> <span></span>
					</div>
				</div>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>
		<c:import url="/common/footer.action"></c:import>
	</div>
    <script>
//弹出一个iframe层
//弹出一个iframe层
function editnewProduct(NewArrivalId){
		$.layer({
	        type: 2,
	        title: 'New Product',
	        maxmin: false,
	        shadeClose: true, //开启点击遮罩关闭层
	        area : ['880px' , '650px'],
			shadeClose: false,
	        offset : [($(window).height() - 660)/2+'px', ''],
	        iframe: {src: 'edit.action?NewArrivalMetadataId='+NewArrivalId}
	    });
	}
function viewnewProduct(NewArrivalId){
	$.layer({
        type: 2,
        title: 'adminRoles',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['880px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'view.action?NewArrivalMetadataId='+NewArrivalId}
    });
}

$('.delete').on('click', function(){
	$(".xubox_main").addClass("a");
	$.layer({
    shade: [0],
	title: 'Confirm',
    area: ['310px', '130px'],
    dialog: {
        msg: 'Do you want to delete this data?',
        btns: 2,                    
        type: -1,
        btn: ['OK','Cancel'],
        yes: function(){
            	//layer.msg('Delete the success', 1, 1);//按钮一的回调函数
            var checkboxs = $("input[name='newproduct']:checked"),len = checkboxs.length,newproductid = "";
            /**
			if( len == 0){
				//alert("请至少选择一条记录！");
				if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
					
				}
				return false;
			}
			if(!confirm("<spring:message code="info.common.confirm.delete" arguments="" argumentSeparator=","/>")){
				return false;
			}
			**/
            if( len == 0){
				//alert("请至少选择一条记录！");
				if(!confirm("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>")){
					
				}
				return false;
			}
			$.each(checkboxs, function(i,item){      
			      if(i == len-1){
			    	  newproductid +=$(item).val() ;
			      }else{
			    	  newproductid +=$(item).val()+ ",";
			      }
			  });
			$.post("delete.action","NewArrivalArr="+newproductid, function(data){
					var result = eval(data);
					alert(result.msg);
					location.reload();
			  },"json");
		  }	
            
        }
    });
});
$('.long').on('click', function(){
    $.layer({
        type: 2,
        title: 'New Product',
        maxmin: false,
        shadeClose: true, //开启点击遮罩关闭层
        area : ['980px' , '650px'],
		shadeClose: false,
        offset : [($(window).height() - 660)/2+'px', ''],
        iframe: {src: 'create.action'}
    });
});
$('#searchnew').on("click",function(){
		$("#newproductFrom").submit();
})

function previewNewProduct(){
	var checkboxs = $("input[name='newproduct']:checked");
	var len = checkboxs.length;
	if( len == 0){
		alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
		return false;
	}
	if(len != 1){
		alert("<spring:message code="info.common.alert.select" arguments="" argumentSeparator=","/>");
		return false;
	}
	var newProductMetadataId = checkboxs.val();
	window.open('${base}'+"/newProduct/unlimited/previewTransfer.action?newProductMetadataId="+newProductMetadataId);
}

	</script>

</body>
</html>